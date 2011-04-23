package zws.hi.intralink.proconfly;/*
DesignState - Design Compression Technology
@author: dstewart
@version: 1.0
Created on February 22, 2004, 9:14 PM
Copywrite (c) 2004 Zero Wait-State Inc. All rights reserved */

import zws.IntralinkAccess;
import zws.IntralinkClient;
import zws.PrinterAccess;
import zws.Synchronizer;
import zws.application.server.webapp.Names;
import zws.application.Properties;
import zws.data.Metadata;
import zws.exception.zwsException;
import zws.util.FileNameUtil;
import zws.util.comparator.metadata.PartNumberOrder;
import zws.hi.IEE.hiIEE;
import zws.hi.report.MetadataAdapter;
import zws.hi.report.hiReport;
import zws.op.ThreadedOpBase;
import zws.origin.Origin;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

public class hiProcOnFly extends hiReport {

   public Comparator getComparator() {
     PartNumberOrder c = new PartNumberOrder();
     String sortKeyFields=Properties.get(Names.SORT_KEY_FIELDS);
     if (sortKeyFields==null || "".equals(sortKeyFields.trim())) {
      {} //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      {} //System.out.println(Names.SORT_KEY_FIELDS + " not configured!");
      {} //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      sortKeyFields="name";
     }
     c.setKeyFields(sortKeyFields);
     return c; 
   }

   public String getSelectedReportName() { return Properties.get("proc-on-fly-report"); }
   protected MetadataAdapter createNewMetadataAdapter() { return new PDFMetadataAdapter(); }
  
  public boolean idChoosesItem(String id, Object item) {
    String o = ((MetadataAdapter)item).getOrigin().toString();
    return id.equals(o);
  }
  
  public boolean isChosen(Object o) {
    Origin origin = ((MetadataAdapter)o).getOrigin();
    Iterator i = getChosenItems().iterator();
    Origin x;
    while (i.hasNext()) if( ((MetadataAdapter)i.next()).getOrigin().isTheSameAs(origin)) return true;
    return false;
  }
  
  public void startRequest() {
    snapshotQueue = null;
    if (getChosenItems()==null) return;
    Iterator i =null;
    PDFMetadataAdapter data;
    int idx;
    if (selectedPrinterNames!=null) {
	    i = getChosenItems().iterator();
	    idx=1;
	    String n,q;
	    while (i.hasNext() && idx < selectedPrinterNames.length) {
	      data =(PDFMetadataAdapter)i.next();
	      n = selectedPrinterNames[idx];
	      q = selectedPrintQuantities[idx];
	      data.setPrintQuantity(selectedPrintQuantities[idx]);
	      data.setPrinterName(selectedPrinterNames[idx]);
	      idx++;
	    }
    }
    if (selectedVerifications!=null) {
	    i = getChosenItems().iterator();
 	    idx=0;
 	    String v;
 	    while (i.hasNext() && idx < selectedVerifications.length) {
 	      data =(PDFMetadataAdapter)i.next();
 	      v = selectedVerifications[idx];
 	      data.setVerification(selectedVerifications[idx]);
 	      idx++;
 	    }
 	  }
    if (selectedReleaseLevels!=null) {
	    i = getChosenItems().iterator();
	    idx=0;
	    String r;
	    while (i.hasNext() && idx < selectedReleaseLevels.length) {
	      data =(PDFMetadataAdapter)i.next();
	      r = selectedReleaseLevels[idx];
	      data.setPromoteTo(selectedReleaseLevels[idx]);
	      idx++;
	    }
	  }
    if (selectedImageTypes!=null) {
	    i = getChosenItems().iterator();
	    idx=0;
	    String v;
	    while (i.hasNext() && idx < selectedImageTypes.length) {
	      data =(PDFMetadataAdapter)i.next();
	      v = selectedImageTypes[idx];
	      data.setImageType(selectedImageTypes[idx]);
	      idx++;
	    }
	  }
  }

  protected boolean pickItem(String s) {
    if ("true".equalsIgnoreCase(getAutoSnapshot())) generateImageSnapshot();
    return super.pickItem(s);
  }
  
  
  public String getAutoSnapshot() { return autoSnapshot; }
  public void setAutoSnapshot(String s) { autoSnapshot = s; }
  
  public String processSelectedItems() {
    IntralinkAccess access = IntralinkAccess.getAccess();
    Iterator i = getChosenItems().iterator();
    PDFMetadataAdapter d;
    Metadata update;
    while (i.hasNext()) {
      d = (PDFMetadataAdapter) i.next();
	    if (!"--------".equals(d.getVerification())) {
	      modelcheckLog.add(d);
	      try {
	        access.modelCheck(d.getOrigin(), getAuthentication());
	        d.setVerificationStatus("Passed");
	        logFormStatus("status.model.check.ok", d.getName(), d.getPromotedFrom(), d.getPromoteTo());
	      }
	      catch(Exception e) {
	        d.setVerificationStatus("Failed");
	        logFormError("status.model.check.failed", d.getName());          
		      i.remove();
	        continue;
	      }
	    }
	    if (!"--------".equals(d.getPromoteTo())) {
	      d.setPromotedFrom(d.get("Release-Level"));
	      try {
	        access.promote(d.getOrigin(), d.getPromoteTo(), "required", "as-stored", getAuthentication());
	        logFormStatus("status.promoted.ok", d.getName(), d.getPromotedFrom(), d.getPromoteTo());
	        //update = d.getMetadata();
	        //update.set("Release-Level", d.getPromoteTo()); //hack for release level of the snapshot  
	        //d.readapt(update);
	        if (null!=getItems()) getItems().clear();
	        promotionLog.add(d);
	      }
	      catch (zwsException e) {
	        String key = e.getType();
	        logFormError(key, d.getName());          
		      i.remove();
	        continue;
	      }
	      catch (Exception e) {
	        e.printStackTrace();
	        logFormError("err.promote", d.getName());
	        return ctrlERROR;
	      }
	    }
	    if (! "--------".equals(d.getImageType())) {
	      try {
	        clearImage(d, d.getImageType());
	        String imageRepository= Properties.get(Names.IMAGE_REPOSITORY);
	        if (null==imageRepository|| "".equals(imageRepository.trim())) imageRepository=null;
	        access.snapshotImage(d.getOrigin(), d.getImageType(), getAuthentication());
	        logFormWarning("status.generating.image", d.getName(), d.getImageType());
	      }
	      catch(Exception e) {  e.printStackTrace(); }
	    }
	    if (!("--------".equals(d.getVerification()) && "--------".equals(d.getPromoteTo()) && "--------".equals(d.getImageType()))) {
	      i.remove();
	    }
	    else {
       logFormWarning("warning.no.action.selected", d.getName());	      
	    }
    }
   return ctrlOK;
	}
    
 
  public Collection getSnapshotQueue() { //+++ scale to include multiple servers and datasources
    if (null!=snapshotQueue) return snapshotQueue;
    IntralinkClient client = hiIEE.getIntralinkClient(getAuthentication());
    try {
      Collection servers = client.getServerList();
      Collection repositories;
      if (null==servers) return null;

      String serverName;
      String datasourceName;
      Iterator s = servers.iterator();
      Iterator r;
      snapshotQueue = new Vector();
      while(s.hasNext()) {
        serverName = (String) s.next(); 
        IntralinkAccess access = IntralinkAccess.getAccess();
        try { repositories = client.getRepositories(serverName); }
        catch(Exception ignore) { ignore.printStackTrace(); continue; }
        if (repositories==null) continue;
        r = repositories.iterator();
        while (r.hasNext()) {
          datasourceName = (String) r.next();
          snapshotQueue.addAll(access.getQueuedSnapshots(serverName, datasourceName));
        }
      }
      if (snapshotQueue.isEmpty()) snapshotQueue = null;
      return snapshotQueue;
    }
    catch (Exception e) { e.printStackTrace(); return null;}
  }

/*  
  public Collection getSnapshotQueue() { //+++ scale to include multiple servers and datasources
    if (null!=snapshotQueue) return snapshotQueue;
    IntralinkAccess access = IntralinkAccess.getAccess();
    try { 
      String serverName = Properties.get(Names.CENTRAL_SERVER);
      String datasourceName = Properties.get(Names.DEFAULT_DATASOURCE_NAME);
      if (null==serverName || "".equals(serverName)) throw new Exception("central-server not set");
      if (null==datasourceName || "".equals(datasourceName)) throw new Exception("default-datasource not set");
      snapshotQueue = access.getQueuedSnapshots(serverName, datasourceName);
      if (null==snapshotQueue || snapshotQueue.isEmpty()) snapshotQueue = null;
      return snapshotQueue;
    }
    catch (Exception e) { e.printStackTrace(); return null;}
  }
  */
  
  public String updateQueue(){ 
    snapshotQueue = null;
    resetSynchronization(getItems()); 
    resetSynchronization(getChosenItems()); 
    resetSynchronization(printLog);     
    return ctrlOK; 
  }
  
  private void resetSynchronization(Collection c) {
    if (null==c) return;
    PDFMetadataAdapter a;
    Iterator i = c.iterator();
    while(i.hasNext()) {
      a = (PDFMetadataAdapter)i.next();
      a.resetSynchronizations();
    }
  }

  public String generateNEUImageSnapshot(){
	  PDFMetadataAdapter adapter = (PDFMetadataAdapter)findItemByID(getID());
	  Origin o = adapter.getOrigin();
	  String sourceType = FileNameUtil.getFileNameExtension(o.getName()).toLowerCase();
	  return generateImageSnapshot(o, NEU);
  }
  
  public String generateSTPImageSnapshot(){
	  PDFMetadataAdapter adapter = (PDFMetadataAdapter)findItemByID(getID());
	  Origin o = adapter.getOrigin();
	  String sourceType = FileNameUtil.getFileNameExtension(o.getName()).toLowerCase();
	  return generateImageSnapshot(o, STP);
  }
  
  public String generateIGSImageSnapshot(){
	  PDFMetadataAdapter adapter = (PDFMetadataAdapter)findItemByID(getID());
	  Origin o = adapter.getOrigin();
	  String sourceType = FileNameUtil.getFileNameExtension(o.getName()).toLowerCase();
	  return generateImageSnapshot(o, IGS);
  }
  
  public String generatePDFImageSnapshot(){
	  PDFMetadataAdapter adapter = (PDFMetadataAdapter)findItemByID(getID());
	  Origin o = adapter.getOrigin();
	  String sourceType = FileNameUtil.getFileNameExtension(o.getName()).toLowerCase();
	  return generateImageSnapshot(o, PDF);
  }
  
  public String generatePSImageSnapshot(){
	  PDFMetadataAdapter adapter = (PDFMetadataAdapter)findItemByID(getID());
	  Origin o = adapter.getOrigin();
	  String sourceType = FileNameUtil.getFileNameExtension(o.getName()).toLowerCase();
	  return generateImageSnapshot(o, PS);
  }
  
  public String generateDWGImageSnapshot(){
	  PDFMetadataAdapter adapter = (PDFMetadataAdapter)findItemByID(getID());
	  Origin o = adapter.getOrigin();
	  String sourceType = FileNameUtil.getFileNameExtension(o.getName()).toLowerCase();
	  return generateImageSnapshot(o, DWG);
  }
  
  public String generateDXFImageSnapshot(){
	  PDFMetadataAdapter adapter = (PDFMetadataAdapter)findItemByID(getID());
	  Origin o = adapter.getOrigin();
	  String sourceType = FileNameUtil.getFileNameExtension(o.getName()).toLowerCase();
	  return generateImageSnapshot(o, DXF);
  }
  
  public String generateHPGImageSnapshot(){
	  PDFMetadataAdapter adapter = (PDFMetadataAdapter)findItemByID(getID());
	  Origin o = adapter.getOrigin();
	  String sourceType = FileNameUtil.getFileNameExtension(o.getName()).toLowerCase();
	  return generateImageSnapshot(o, HPG);
  }
  
  public String generateCGMImageSnapshot(){
	  PDFMetadataAdapter adapter = (PDFMetadataAdapter)findItemByID(getID());
	  Origin o = adapter.getOrigin();
	  String sourceType = FileNameUtil.getFileNameExtension(o.getName()).toLowerCase();
	  return generateImageSnapshot(o, CGM);
  }
  
  public String generateIDFImageSnapshot(){
	  PDFMetadataAdapter adapter = (PDFMetadataAdapter)findItemByID(getID());
	  Origin o = adapter.getOrigin();
	  String sourceType = FileNameUtil.getFileNameExtension(o.getName()).toLowerCase();
	  return generateImageSnapshot(o, IDF);
  }
  
  public String generateImageSnapshot(){
	  PDFMetadataAdapter adapter = (PDFMetadataAdapter)findItemByID(getID());
	  Origin o = adapter.getOrigin();
	  String sourceType = FileNameUtil.getFileNameExtension(o.getName()).toLowerCase();
	  String imageType = Properties.get("default-"+sourceType+"-snapshot");
	  return generateImageSnapshot(o, imageType);
  }
  
  
  public String generateImageSnapshot(Origin o, String imageType){
    Collection c = getSnapshotQueue();
    boolean isInQueue=false;
    if (null!=c) {
      Iterator i = c.iterator();
      while (i.hasNext() && !isInQueue) {
         if (i.next().toString().equals(getID())) isInQueue=true;
      }
    }
    if (isInQueue) return ctrlOK; //return already queued  
    //else queue for image snapshot
    //PDFMetadataAdapter adapter = (PDFMetadataAdapter)findItemByID(getID());
    //Origin o = adapter.getOrigin();
    IntralinkAccess access = IntralinkAccess.getAccess();
    try {
      String sourceType = FileNameUtil.getFileNameExtension(o.getName());
      //String imageType = Properties.get("default-"+sourceType+"-snapshot");
      {} //System.out.println("source: " + sourceType);
      {} //System.out.println("default-"+sourceType+"-snapshot:" + imageType);
      if (null==imageType || "".equals(imageType.trim())) imageType="pdf";
      String imageRepository= Properties.get(Names.IMAGE_REPOSITORY);
      {} //System.out.println("image-repository" + imageRepository);
      if (null==imageRepository|| "".equals(imageRepository.trim())) imageRepository=null;
      access.snapshotImage(o, imageType, imageRepository, getAuthentication());
      return ctrlOK;
    }
    catch (Exception e) {
      e.printStackTrace();
      return ctrlERROR;
    }
  }
  
  public String printSelectedItems() {
    //check if chosen items have a selected printer
    Iterator i = getChosenItems().iterator();
    PDFMetadataAdapter m;
    while (i.hasNext()) {
      m = (PDFMetadataAdapter)i.next();
      if (m.getPrinterName().equals("--------")) {
        logFormWarning("warning.select.printer");
        return ctrlERROR;
      }
    }
    PrintQueueDameon d = new PrintQueueDameon();
    d.setPrintLog(printLog);
    d.queuePrintJobs(getChosenItems());
    getChosenItems().clear();
    try { 
      d.execute(); 
      printDameons.add(d);
      return ctrlOK;
    }
    catch (Exception e) { 
      e.printStackTrace();  
      return ctrlERROR; 
    }
  }

  public Collection getPrintQueue() {
    Iterator i = printDameons.iterator();
    PrintQueueDameon d;
    Collection q = new Vector();
    while(i.hasNext()) {
      d = (PrintQueueDameon) i.next();
      if (null!=d.getPrintQueue()) q.addAll(d.getPrintQueue());
    }
    if (q.isEmpty()) return null;
    return q;
  }
  
  private Collection printDameons = new Vector();
  
  public String printSelectedItemsold() {
    if (getChosenItems()==null) return ctrlOK;
    Iterator i = getChosenItems().iterator();
    PDFMetadataAdapter data;
    String printer;
    int quantity;
    Origin o;
    while (i.hasNext()) {
      data = (PDFMetadataAdapter) i.next();
      o = data.getPDFOrigin();
      printer = data.getPrinterName();
      quantity = Integer.valueOf(data.getPrintQuantity()).intValue();
      try { 
        PrinterAccess service = PrinterAccess.getClient(Properties.get(Names.CENTRAL_SERVER));
        service.print(o, printer, quantity);
        PDFMetadataAdapter print = new PDFMetadataAdapter(); //create a print record
        print.addBinaryOrigin(data.getPDFOrigin());
        print.setPrintQuantity(data.getPrintQuantity());
        print.setPrinterName(data.getPrinterName());
        printLog.add(print);
        i.remove();
      }
      catch (Exception e) { e.printStackTrace(); } //log this and somehow report to the screen (need an opresponse!)
    }
    return ctrlOK;
  }

  public String clearPDF() { return clearLinks(); } //+ update JSPs to use clearLinks
  
  public String clearLinks() { 
   Synchronizer synkService = Synchronizer.getClient(Properties.get(Names.CENTRAL_SERVER));    
    if (null==getID() || "".equals(getID().trim())) return ctrlOK;
    try {
      PDFMetadataAdapter data = (PDFMetadataAdapter)findItemByID(getID());
      Origin origin = data.getOrigin();
      synkService.purgeByUID(origin.getDomainName(), origin.getServerName(), origin.getDatasourceName(), origin.getUniqueID());
      Collection c = data.getAllBinaryOrigins();
      Iterator i = c.iterator();
      String ext;
      Origin o;
      while(i.hasNext()) {
        o=(Origin)i.next();
        if (o.getName().equals(origin.getName())) continue;
        synkService.purgeByUID(o.getDomainName(), o.getServerName(), o.getDatasourceName(), o.getUniqueID());
        ext = FileNameUtil.getFileNameExtension(o.getName());
        {} //System.out.println("evaluating " + o.toString());
        if (o.getDatasourceType().equals(o.FROM_ILINK)) {
         if (ext.equalsIgnoreCase("prt")) continue;
         if (ext.equalsIgnoreCase("asm")) continue;
         if (ext.equalsIgnoreCase("drw")) continue;
         {} //System.out.println("Wanting to delete" + o.toString());
         IntralinkAccess.getAccess().deleteFromRepository(o, getAuthentication());
        }
      }
      data.resetSynchronizations();
      return ctrlOK;
    }
    catch (Exception e) {
      e.printStackTrace();
      logFormError(keyRUNTIME_ERROR);
      return ctrlSYSTEM_ERROR;
    }
  }
  
  public String clearImage(PDFMetadataAdapter data, String type) {
      Synchronizer synkService = Synchronizer.getClient(Properties.get(Names.CENTRAL_SERVER));    
       if (null==getID() || "".equals(getID().trim())) return ctrlOK;
       try {
         Origin o;
         Collection c = data.getBinaryOrigins(type);
         Iterator i = c.iterator();
         while(i.hasNext()) {
           o=(Origin)i.next();
           synkService.purgeByUID(o.getDomainName(), o.getServerName(), o.getDatasourceName(), o.getUniqueID());
         }
         return ctrlOK;
       }
       catch (Exception e) {
         e.printStackTrace();
         logFormError(keyRUNTIME_ERROR);
         return ctrlSYSTEM_ERROR;
       }
     }
  
  
  public void render() { 
    synchronizePDFs(getItems()); 
    synchronizePDFs(getChosenItems()); 
    synchronizePDFs(printLog); 
  }
  
  private void synchronizePDFs(Collection items) {
   if (null==items) return;
   PDFMetadataAdapter data;
   Iterator i = items.iterator();
   while (i.hasNext()) {
     data = (PDFMetadataAdapter)i.next();
     data.findSynchronizations();
   }
  }
  
  /*
  public String status(){
    if (op.getThread().isAlive()) return ctrlRUNNING;
    getChosenItems().clear();
    getItems().clear();
    return ctrlCOMPLETE;
    
    Object[] generatedViewables = op.getGeneratedViewableNames().toArray();
    String criteria="";
    if (generatedViewables.length>0) criteria = Names.CRITERIA_GROUP_START_BLOCK + SPACE + "Name=" + (String)generatedViewables[0] + SPACE + Names.CRITERIA_GROUP_END_BLOCK;
    for (int idx=1; idx < generatedViewables.length; idx++) 
      criteria += SPACE + Names.CRITERIA_OR_OP + SPACE + Names.CRITERIA_GROUP_START_BLOCK + SPACE + "Name=" + (String)generatedViewables[idx] + SPACE + Names.CRITERIA_GROUP_END_BLOCK;
    String c =getCriteria();
    setCriteria(criteria);
    setFiltersEnabled(false);
    setTransformersEnabled(false);
    setCriteriaModifiersEnabled(false);
    search();
    setFiltersEnabled(true);
    setTransformersEnabled(true);
    setCriteriaModifiersEnabled(true);
    setCriteria(c);
  }

  public String processOnFly(){
    if (null==generatedImages) {
      generatedImages = new AdapterVector();
      MetadataAdapter prototype = new MetadataAdapter();
      prototype.setMetadataFields(getReport().getMetadataFields());
      generatedImages.setAdapterPrototype(prototype);
    }
    op = new ModelCheckOp();
    op.setItems(getChosenItems());
    op.setGeneratedImages(getGeneratedImages());
    op.setAuthentication(getAuthentication());
    op.setViewableType(getViewableType());
    op.setEnablePromotion(false);
    op.setRecipients(getMember().getUser().getEmail());
    try {
      op.execute();
  	  return ctrlRUNNING;
    }
    catch(Exception e){
      e.printStackTrace();
      return ctrlERROR;
    }
  }
  
  public int getNumberOfGeneratedImages() { 
    if (null==generatedImages) return 0;
    return getGeneratedImages().size(); 
  }
  public Collection getGeneratedImages() { return generatedImages; }

  public String getViewableType() { return viewableType; }
  public void setViewableType(String s) { viewableType=s; }
  */

  public Collection getPrintLog() {
    if (printLog.isEmpty()) return null;
    return printLog;
  }

  public Collection getPromotionLog() {
	  if (promotionLog.isEmpty()) return null;
	  return promotionLog;
	}

  public String clearPromotionLog() {
	  promotionLog.clear();
	  return ctrlOK;
	}
	
  public Collection getModelcheckLog() {
	  if (modelcheckLog.isEmpty()) return null;
	  return modelcheckLog;
	}

  public String clearModelcheckLog() {
	  modelcheckLog.clear();
	  return ctrlOK;
	}

  public Collection getPrinterNames() {
    if (null==printerNames) {
      PrinterAccess client = PrinterAccess.getClient(Properties.get(Names.CENTRAL_SERVER));
      try { printerNames = client.getPrinterNames(); }
      catch (Exception e) { e.printStackTrace(); printerNames = new Vector(); }
    }
    Collection c = new Vector();
    c.add("--------");
    c.addAll(printerNames);
    return c; 
  }

  public String getPrintingIsEnabled() {
    String s = Properties.get(Names.PRINTING_IS_ENABLED);
    if (s==null || "".equals(s.trim())) s = "true";
    return s.toLowerCase();
  }
  
  public String[] getSelectedPrinterNames() { return selectedPrinterNames; }
  public void setSelectedPrinterNames(String[] names) { selectedPrinterNames=names; }

  public String[] getSelectedPrintQuantities() { return selectedPrintQuantities; }
  public void setSelectedPrintQuantities(String[] quantities) { selectedPrintQuantities=quantities; }

  public String[] getSelectedVerifications() { return selectedVerifications; }
  public void setSelectedVerifications(String[] levels) { selectedVerifications=levels; }

  public String[] getSelectedReleaseLevels() { return selectedReleaseLevels; }
  public void setSelectedReleaseLevels(String[] levels) { selectedReleaseLevels=levels; }

  public String[] getSelectedImageTypes() { return selectedImageTypes; }
  public void setSelectedImageTypes(String[] types) { selectedImageTypes=types; }

  private Collection printerNames=null;
  private String[] selectedPrinterNames;
  private String[] selectedPrintQuantities;
  private String[] selectedVerifications;
  private String[] selectedReleaseLevels;
  private String[] selectedImageTypes;

  private String autoSnapshot="true";
  private Collection snapshotQueue=null;
  private Collection printLog=Collections.synchronizedCollection(new Vector());
  private Collection promotionLog=Collections.synchronizedCollection(new Vector());
  private Collection modelcheckLog=Collections.synchronizedCollection(new Vector());
  //private String viewableType = PDF;
  //private AdapterCollection generatedImages = null;
  
  //ModelCheckOp op=null;
  //Drawing Formats
  private static String PS ="ps";
  private static String PDF ="pdf";
  private static String DXF ="dxf";
  private static String HPG ="hpg";
  private static String DWG ="dwg";
  private static String CGM ="cgm";

  //3D Model Formats
  private static String IGS ="igs";
  private static String STP ="stp";
  private static String NEU ="neu";
  private static String IDF ="idf";  //Electronic

  //Other formats?
  private static String STL ="stl";

  private static String ZIP = "zip";
  private static String DRW = "drw";

  //Agile Formats
  private static String AGILE ="";
  
  private static String NONE = "none";
  private static String SPACE = " ";
  
  public class PrintQueueDameon extends ThreadedOpBase {
   
    public void executeRun() {
      status = RUNNING;
      Iterator i;
      PDFMetadataAdapter data;
      Origin imageToPrint=null;
      String imageType=null;
      String sourceType=null;
      while (null!=printQueue && !printQueue.isEmpty()) {
        {} //System.out.println("Queueing " + printQueue.size() + " print jobs");
        i = printQueue.iterator();
        while(i.hasNext()) {
          data = (PDFMetadataAdapter)i.next();
          sourceType = FileNameUtil.getFileNameExtension(data.getName());
          imageType = Properties.get("default-"+sourceType+"-snapshot");
          if (PDF.equalsIgnoreCase(imageType)) imageToPrint = data.getPDFOrigin();
          else if (PS.equalsIgnoreCase(imageType)) imageToPrint = data.getPSOrigin();
          if (null==imageToPrint) data.findSynchronizations();
          if (null!=imageToPrint) {
            {} //System.out.println("printing " + data.getOrigin().getUniqueID()); 
            print(data, imageToPrint);
            i.remove();
            PDFMetadataAdapter print = (PDFMetadataAdapter) data.copy();
/*            
            PDFMetadataAdapter print = new PDFMetadataAdapter(); //create a print record
            print.setPDFOrigin(data.getPDFOrigin());
            print.setPrintQuantity(data.getPrintQuantity());
            print.setPrinterName(data.getPrinterName());
 */
            printLog.add(print);
          }
        }
        if (!printQueue.isEmpty()) {
         {} //System.out.println("Sleeping....");       
          try { getThread().sleep(1500); }
          catch (InterruptedException ignore) {}
        }
      }
      {} //System.out.println("Print Jobs Completed.");
      status = STOPPED;
    }

    private void print(PDFMetadataAdapter data, Origin imageToPrint) { 
      String printer = data.getPrinterName();
      if (null==printer || "".equals(printer)) printer = getPrinterNames().toArray()[0].toString();
      int quantity = Integer.valueOf(data.getPrintQuantity()).intValue();
      try {
        PrinterAccess service = PrinterAccess.getClient(Properties.get(Names.CENTRAL_SERVER));
        service.print(imageToPrint, printer, quantity);
      }
      catch (Exception e) { e.printStackTrace(); } //log this and somehow report to the screen (need an opresponse!)
    }

    public String getStatus() { return status; }
    public void queuePrintJobs(Collection c) { 
      printQueue = new Vector();
      printQueue.addAll(c);
    }

    public void setPrintLog(Collection c) { printLog = c; }
    public Collection getPrintQueue() { return printQueue; }
    private String status=UNINITIALIZED;
    private Collection printLog=null;
    private Collection printQueue=null;
  }

    public static String UNINITIALIZED="running";
    public static String RUNNING="running";
    public static String STOPPED="stopped";
    //private Synchronizer synkService = Synchronizer.getClient(Properties.get(Names.CENTRAL_SERVER)); 

    
}
