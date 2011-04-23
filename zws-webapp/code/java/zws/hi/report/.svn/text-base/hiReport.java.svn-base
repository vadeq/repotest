package zws.hi.report; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Downloader;
import zws.IntralinkAccess;
import zws.application.Names;
import zws.application.Properties;
import zws.bill.intralink.BillOfMaterials;
import zws.data.Metadata;
import zws.exception.InvalidSyntax;
import zws.hi.treeview.agile.AgilePublishResult;
import zws.hi.treeview.bill.BillView;
import zws.log.failure.Failure;
import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.report.Report;
import zws.security.Authentication;
import zws.service.report.EJBLocator;
import zws.service.report.ReportServiceRemote;
import zws.util.AdapterCollection;
import zws.util.AdapterTreeSet;
import zws.util.AdapterVector;
import zws.hi.report.PublishMetadataAdapter;

import com.zws.hi.hiList;
import com.zws.session.Member;
import com.zws.session.SessionData;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;

public class hiReport extends hiList {
  public void bind() {
    //if (null==reportNames) reportNames = new Vector();
    //if(null==reports) reports = new HashMap();
    /*
    try { //find and cache reports.
      //Configurator.load();
      ReportServiceRemote service = EJBLocator.findService(Properties.get(Names.CENTRAL_SERVER));
      Collection rpts = service.getReports();
      Report r;
      reportNames.clear();
      Iterator i = rpts.iterator();
      while (i.hasNext()) {
        r = (Report)i.next();
        reportNames.add(r.getName());
        reports.put(r.getName(), r);
      }
    }
    catch (Exception e) { e.printStackTrace(); } // log form error
     */
  }

  public String download() {
    try {
      Origin origin= OriginMaker.materialize(getID());
      URL url = Downloader.getURL(origin);
      //+++ check for null url
      String urlString = url.toString();
      String name = URLDecoder.decode(urlString.substring(urlString.lastIndexOf("/")+1), "UTF-8");
      {} //System.out.println("Downloading File: " + name);
      {} //System.out.println(urlString);
      String contents="application/octet-stream";
      String disposition = "attachment; filename="+name;
      getHttpResponse().setContentType(contents);
      getHttpResponse().setHeader("Content-Disposition", disposition);
      int dataLen = 0;
      if (0<dataLen) getHttpResponse().setContentLength(dataLen);
      streamDataToResponse(url.openStream());
      return ctrlLIST;
    }
    catch (Exception e) { e.printStackTrace(); return ctrlSYSTEM_ERROR; }

/*
    String name = getID().substring(getID().lastIndexOf('=')+1);
    try {
      String contents="application/octet-stream";
      String disposition = "attachment; filename="+name;
      getHttpResponse().setContentType(contents);
      getHttpResponse().setHeader("Content-Disposition", disposition);
      int dataLen = 0;
      if (0<dataLen) getHttpResponse().setContentLength(dataLen);
      Origin origin=new Origin(getID());
      streamData(Downloader.download(origin));
      return ctrlLIST;
    }
    catch (Exception e) { e.printStackTrace(); return ctrlSYSTEM_ERROR; }
 */
  }

  //  public String getCriteria() {return criteria;}
  public void setCriteria(String x) {criteria=x;}
  //public Collection getReportNames() { return reportNames; }

  public String getSelectedReportName() {
    if (null!=selectedReportName) return selectedReportName;
    //if (0<reportNames.size()) selectedReportName = (String)reportNames.toArray()[0];
    if (0<presentationReports.size()) selectedReportName = (String)presentationReports.toArray()[0];
    else selectedReportName = "[no reports found]";
    return selectedReportName;
  }

  public void setSelectedReportName(String s) { selectedReportName=s; resetReport(); getItems().clear(); }

  public String[] getMetadataFields() {
    if (null==getReport()) {
      try{
        ReportServiceRemote service = EJBLocator.findService(Properties.get(Names.CENTRAL_SERVER));
        report = service.getReport(getSelectedReportName());
      }
      catch (Exception e) {
        //logFormError("Report not found"); //++ place correct message here
        e.printStackTrace();
        return new String[0];
      }
    }
    return getReport().getMetadataFields();
  }

  /*
  public String download() {
    hiDocument hiDoc = (hiDocument)searchResults.item(Integer.valueOf(getID()).intValue());
    Document doc = hiDoc.getFromHistory(getRev(), getVer());
    Finder finder = (Finder)doc.getFinder();
    try {
      finder.find(true);
      hiFileDownloader.streamForViewing(getHttpResponse(), finder); return ctrlLIST;
    }
    catch (Exception e) { e.printStackTrace(); return this.ctrlSYSTEM_ERROR; }
  }
  */

  public String search() {
    bill=null;
    latestBill=null;
    boolean authenticate=true;
    getItems().clear();
    String serverName = Properties.get(Names.CENTRAL_SERVER);
    if (null==getCriteria() || "".equals(getCriteria())) {
      logFormWarning(warnNO_CRITERIA);
      return ctrlLIST;
    }
    if (null==getReport()) {
      ReportServiceRemote service;
      try { service = EJBLocator.findService(serverName); }
      catch (Exception e) { logFormError("err.server.not.found", serverName);  return ctrlLIST; }
      try { report = service.getReport(getSelectedReportName()); }
      catch (Exception e) { logFormError("err.report.not.found", getSelectedReportName(), serverName);  return ctrlLIST; }
      authenticate = "true".equalsIgnoreCase(com.zws.application.Properties.get(com.zws.application.Properties.enableAuthentication));
    }

    if(authenticate && getReport().getAuthenticationRequired()) {
      Member member = (Member)getSession().getAttribute(SessionData.keyMEMBER);
      Authentication token = new Authentication();
      token.setUsername(member.getUser().getUsername());
      token.setPassword(member.getUser().getPassword());
      getReport().setAuthenticationToken(token);
    }
    try {
      getReport().setCriteria(getCriteria());
      getReport().setFiltersEnabled(filtersEnabled);
      getReport().setTransformersEnabled(transformersEnabled);
      getReport().setCriteriaModifiersEnabled(modifiersEnabled);
      getReport().generate();
      if (null!=getReport().getFailures()){
        Iterator i = getReport().getFailures().iterator();
        Failure f;
        while (i.hasNext()) {
          f=(Failure)i.next();
          logFormError(f.getMessageKey());
          {} //System.out.println ("Caught a failure:" + f);
        }
      }
      if (null!=getReport().getWarnings()){
        Iterator i = getReport().getFailures().iterator();
        Failure f;
        while (i.hasNext()) {
          f=(Failure)i.next();
          logFormError(f.getMessageKey());
          {} //System.out.println ("Caught a failure:" + f);
        }
      }
      if (0==getReport().getResults().size()) logFormError(getZeroResultsKey());
      else if (1==getReport().getResults().size()) logFormStatus(statFOUND_ONE_RESULT);
      else logFormStatus(statFOUND_RESULTS, ""+getReport().getResults().size());
    }
    catch (InvalidSyntax e) { logFormError("err.invalid.syntax", getCriteria());  return ctrlLIST; }
    catch (Exception e) {  e.printStackTrace(); logFormError("system.err");  return ctrlSYSTEM_ERROR; }
    setItems(adapt(getReport()));
    if (null!=getItems() && 0<getItems().size()) this.getHttpRequest().setAttribute("metadata", getItems().toArray()[0]);
    return stateAfterSearch();
  }
  private String stateAfterSearch() { return ctrlLIST; }

  protected String getZeroResultsKey() { return statFOUND_ZERO_RESULTS; }

  public Collection adapt(Report r) {
    MetadataAdapter prototype = createNewMetadataAdapter();
    if (null==prototype) return r.getResults();

    AdapterCollection c;
    if (null==getComparator()) c = new AdapterVector();
    else c = new AdapterTreeSet(getComparator());
    prototype.setMetadataFields(r.getMetadataFields());
    c.setAdapterPrototype(prototype);
    c.addAll(r.getResults());
    return c;
  }

  public Metadata adapt(Metadata data) {
    MetadataAdapter prototype = createNewMetadataAdapter();
    prototype.setMetadataFields(getReport().getMetadataFields());
    prototype.adapt(data);
    return prototype;
  }

  protected Comparator getComparator() { return null; }
  protected MetadataAdapter createNewMetadataAdapter() { return new MetadataAdapter(); }

  public Report getReport() { return report; }
  public void resetReport() { report=null; }

  public void setCriteriaModifiersEnabled(boolean b) { modifiersEnabled=b; }
  public void setFiltersEnabled(boolean b) { filtersEnabled=b; }
  public void setTransformersEnabled(boolean b) { transformersEnabled=b; }
  public String getCriteria() { return criteria; }

  //temporary-security-measure
  public Collection getPresentationReports() {
    if (null==presentationReports) populatePresentationReports();
    return presentationReports;
  }

  protected String getPresentationReportsProperty() {
    return getMember().getRole()+"-presentation-reports";
  }
  private void populatePresentationReports() {
	  presentationReports = new Vector();
   String prop = getPresentationReportsProperty();
   String reports = Properties.get(prop);
   StringTokenizer tokens = new StringTokenizer(reports, Names.DELIMITER);
   while(tokens.hasMoreTokens()) presentationReports.add(tokens.nextToken().trim());
  }

  //demo Bill of Materials
  private BillOfMaterials bill = null;
  private BillOfMaterials latestBill = null;

  public BillOfMaterials getBillOfMaterials() { return bill; }
  public BillOfMaterials getLatestBillOfMaterials() { return latestBill; }

/*
  public void loadBill() {
   setNav(null); //controlller hack
	 try {
     IntralinkAccess ilink = IntralinkAccess.getAccess();
     Origin o = OriginMaker.materialize(getID());
     bill = ilink.reportBill(o, getAuthentication());
     billName = o.getName();
	 }
	 catch (Throwable e) { e.printStackTrace(); }
  }
*/
  public String reportBill() {
      latestBill=null;
      loadBill();
      return ctrlVIEW;
     }
  public String reportLatestBill() {
      bill=null;
   loadLatestBill();
   return ctrlVIEW;
  }

  public void loadBill() {
   if (null==getChosenItem()) bill=null;
   setNav(null); //controlller hack
   bill = ((MetadataAdapter)getChosenItem()).getBillOfMaterials(getAuthentication());
  }

  public void loadLatestBill() {
   if (null==getChosenItem()) latestBill=null;
   setNav(null); //controlller hack
   latestBill = ((MetadataAdapter)getChosenItem()).getLatestBillOfMaterials(getAuthentication());
  }

  public String getBillView() {
	 try {
     loadBill();
     String s = bill.structuredXML();
     {} //System.out.println(s);
     ByteArrayInputStream xml = new ByteArrayInputStream(s.getBytes());
     BillView bView = new BillView();
     setNav(null); //controlller hack
		 return bView.getTreeView(xml);
	 }
	 catch (Throwable e) { e.printStackTrace(); return e.getMessage(); }
  }

  public String getLatestBillView() {
 	 try {
    loadLatestBill();
    String s = latestBill.structuredXML();
    {} //System.out.println(s);
    ByteArrayInputStream xml = new ByteArrayInputStream(s.getBytes());
    BillView bView = new BillView();
    setNav(null); //controlller hack
  	 return bView.getTreeView(xml);
   }
   catch (Throwable e) { e.printStackTrace(); return e.getMessage(); }
  }

  /*
  public String downloadBill() {
    try {
      if (null==bill) loadBill();
      String contents="application/xml";
      String disposition = "attachment; filename="+billName+".xml";
      getHttpResponse().setContentType(contents);
      getHttpResponse().setHeader("Content-Disposition", disposition);
      int dataLen = 0;
      if (0<dataLen) getHttpResponse().setContentLength(dataLen);
      streamData(new ByteArrayInputStream(bill.structuredXML().getBytes()));
      return ctrlLIST;
    }
    catch (Exception e) { e.printStackTrace(); return ctrlSYSTEM_ERROR; }
  }

  public String downloadCSV() {
    try {
      if (null==bill) loadBill();
      String contents="application/xl";
      String disposition = "attachment; filename="+billName+".csv";
      getHttpResponse().setContentType(contents);
      getHttpResponse().setHeader("Content-Disposition", disposition);
      String csv = bill.flatCSV();
      byte[] csvBytes = csv.getBytes();
      int dataLen = csvBytes.length;
      if (0<dataLen) getHttpResponse().setContentLength(dataLen);
      streamData(new ByteArrayInputStream(csvBytes));
      return ctrlLIST;
    }
    catch (Exception e) { e.printStackTrace(); return ctrlSYSTEM_ERROR; }
  }
  */

 /* private String compressSpaces(String s) {
    byte[] chars = s.getBytes();
    StringBuffer buf = new StringBuffer("");
    for (int i=0; i < chars.length; i++) {
        {} //System.out.println(chars[i]);
      if (' '==chars[i]) {
        buf.append(' ');
        while (' '==chars[++i]) ; //skip extra spaces
      }
      else buf.append(chars[i]);
    }
    return buf.toString();
  }*/

  // some demo stuff:

  public String pick() {
    if (pickItem(getID())) return ctrlPICK;
    return ctrlINDEX;
  }

  public String pickAll() {
    boolean isSynchronized = false;
    String itemName  = null;
    Iterator i = getItems().iterator();
    Object item = null;
    Object o;
    while (i.hasNext()) {
      o = i.next();
      item=o;
      if (null!=item) {
        itemName = ((MetadataAdapter)item).getOrigin().getName();
        if(item instanceof PublishMetadataAdapter) {
          isSynchronized = ((PublishMetadataAdapter)item).isSynchronized();
        }
        if(isSynchronized) {
          logFormWarning(statALREADY_PUBLISHED, itemName);
        } else {
          i.remove();
          getChosenItems().add(item);
        }
      }
    }
    return ctrlINDEX;
  }

  public boolean idChoosesItem(String id, Object item) {
      String o = ((MetadataAdapter)item).getOrigin().toString();
      return id.equals(o);
    }

  public String info() {
    bill=null;
    latestBill=null;
    publishResult=null;
    chooseItem(getID());
    return ctrlVIEW;
  }

  public String publishToAgile(){
      try {
        Collection origins = new Vector();
        origins.add(((Metadata)findItemByID(getID())).getOrigin());
        publishResult = IntralinkAccess.getAccess().publishToAgile(origins, getAuthentication());
        return ctrlVIEW;
      }
      catch (Exception e) { e.printStackTrace(); return ctrlVIEW; }
    }
  public String getPublishResult() {
    if (null==publishResult) return null;
    try{
		 		 AgilePublishResult pubView = new AgilePublishResult();
			 	 return pubView.getTreeView(new ByteArrayInputStream(publishResult.getBytes()));
    }
    catch (Throwable t) { t.printStackTrace();  return null; }
  }

  private String publishResult=null;


  private String criteria=null;
  private String selectedReportName=null;
  private Report report=null;
  //private Map reports=null; //= new HashMap();
  //private Collection reportNames=null; //= new Vector();
  private Collection presentationReports=null;

  private boolean filtersEnabled=true;
  private boolean modifiersEnabled=true;
  private boolean transformersEnabled=true;
}