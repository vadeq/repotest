package zws.hi.event.service; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Dec 12, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

import zws.AgileAccess;
import zws.IntralinkAccess;
import zws.Recorder;
import zws.Server;
import zws.Synchronizer;
import zws.application.Names;
import zws.application.Properties;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.eco.AffectedItem;
import zws.data.eco.ECO;
import zws.event.EventMaker;
import zws.event.custom.cisco.PublishLibraryItem;
import zws.event.custom.cisco.RenumberItem;
import zws.event.eco.Approved;
import zws.event.eco.Rejected;
import zws.event.eco.StatusChanged;
import zws.event.hi.i2a.AttachFiles;
import zws.event.hi.i2a.BroadcastLifeCyclePhase;
import zws.event.hi.i2a.BroadcastPLMStatus;
import zws.event.hi.i2a.BroadcastPartNumber;
import zws.event.hi.i2a.BroadcastRevision;
import zws.event.hi.i2a.Inactivate;
import zws.event.hi.i2a.PromoteItem;
import zws.event.hi.i2a.PromoteLatestItem;
import zws.event.hi.i2a.UpdateItem;
import zws.event.hi.i2a.UpdateWithNewPartNumber;
import zws.hi.demo.cisco.ec.CiscoMetadataAdapter;
import zws.hi.demo.cisco.ec.hiCiscoPublish2Agile;
import zws.hi.demo.kla.KLAMetadataAdapter;
import zws.hi.demo.kla.hiKLAPublish2Agile;
import zws.hi.demo.zws.I2AMetadataAdapter;
import zws.hi.demo.zws.hiZWSPublish2Agile;
import zws.origin.Origin;
import zws.origin.OriginBase;
import zws.origin.OriginMaker;
import zws.util.PrintUtil;
import zws.util.RoutedEvent;
import zws.util.RoutedEventBase;

import com.zws.hi.hiItem;
import com.zws.service.account.InvalidPasswordException;
import com.zws.service.account.UserNotFoundException;
import zws.util.FileNameUtil;

import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;


public class hiEventService extends hiItem {

	public String fire() {
    try { login(getUsername(), getPassword()); }
    catch(UserNotFoundException e) {return ctrlERR_EVENT_NO_USER;}
    catch (InvalidPasswordException e ) {return ctrlERR_EVENT_BAD_LOGIN;}
    catch (Exception e) { setErrorMessage(e.getMessage()); return ctrlERR_EVENT_ERROR;}
	  {} //System.out.println(getEv());
	  RoutedEventBase ev = null;
	  String response=null;
	  try { ev = EventMaker.materializeXML(getEv()); }
	  catch (Exception e) {
			{} //System.out.println(e.getMessage());
			return ctrlERR_EVENT_NOT_RECOGNIZED;
		}
		try {
      Recorder r = Recorder.getClient();
      r.recordFiredEvent(ev);
    }
		catch (Exception e) { {} //System.out.println("could not record event to log" + e.getMessage()); }
		  
		}
    response = fire(ev);
	  logout(getUsername());
	  {} //System.out.println("Returning response: " + response);
	  return response;
	}

	private String fire(RoutedEventBase ev) {
	  //cisco custom events
	  {} //System.out.println("Event CLASSNAME=" + ev.getClass().getName());

	  //These are cisco ec events - uncomment to turn them back on.
	  //if (ev.getClass().equals(zws.event.custom.cisco.PublishLibraryItem.class)) return customCiscoPublishLibrary(ev);
	  //else if (ev.getClass().equals(zws.event.custom.cisco.RenumberItem.class)) return customCiscoRenumberItem(ev);

	  //these are generic event handlers (using custom cisco events)
	  if (ev.getClass().equals(zws.event.custom.cisco.PublishLibraryItem.class)) return zwsPublishLibrary(ev);
	  else if (ev.getClass().equals(zws.event.custom.cisco.RenumberItem.class)) return zwsRenumberItem(ev);

	  //hi events
	  else if (ev.getClass().equals(PromoteItem.class)) return promote(ev);
	  else if (ev.getClass().equals(PromoteLatestItem.class)) return inactivate(ev);
	  else if (ev.getClass().equals(Inactivate.class)) return inactivate(ev);
	  else if (ev.getClass().equals(UpdateItem.class)) return update(ev);
	  else if (ev.getClass().equals(RenumberItem.class)) return renumberItem(ev);
	  else if (ev.getClass().equals(UpdateWithNewPartNumber.class)) return updateWithNewPartNumber(ev);
	  else if (ev.getClass().equals(AttachFiles.class)) return attachFiles(ev);
	  else if (ev.getClass().equals(BroadcastPartNumber.class)) return broadcastPartNumber(ev);
	  else if (ev.getClass().equals(BroadcastRevision.class)) return broadcastRevision(ev);
	  else if (ev.getClass().equals(BroadcastLifeCyclePhase.class)) return broadcastLifeCyclePhase(ev);
	  else if (ev.getClass().equals(BroadcastPLMStatus.class)) return broadcastPLMStatus(ev);
	  else if (ev.getClass().equals(StatusChanged.class)) return updateECOStatus(ev);
	  else if (ev.getClass().equals(Approved.class)) return approveECO(ev);
	  else if (ev.getClass().equals(Rejected.class)) return rejectECO(ev);
	  else {} //System.out.println("No handler found");
    return ctrlERR_EVENT_HANDLER_NOT_DEFINED;
	}

	private String customCiscoPublishLibrary(RoutedEventBase ev) {
	 	 {} //System.out.println("Handler found!");
		 hiCiscoPublish2Agile form = new hiCiscoPublish2Agile();

		 CiscoMetadataAdapter source = null;
		 PublishLibraryItem event = (PublishLibraryItem) ev;
		 try {
	     source = form.adaptSourceDesign(event.getOrigin());
       //check to see if source matches policy:
	     String location = source.getMetadata().get("folder");
       {} //System.out.println(source.getName() + "["+location+"]");
	     if (null==location) return ctrlEVENT_FIRED;
	     if (location.toLowerCase().indexOf("cisco standard") < 0) {
	       {} //System.out.println(source.getName() + "["+location+"] not located in standard lib");
	       return ctrlEVENT_FIRED;
	     }
	     if (null==source) return this.ctrlERR_EVENT_NOT_RECOGNIZED;
		   form.getChosenItems().add(source);

	 		 //String aggilePartNumber = form.findLastPublishedPartNumber(source.getOrigin());
		   {} //System.out.println("Publishing rev update ");
		   form.createNewECO("Intralink ECO - Cisco Standard", "Intralink ECO - Cisco Standard Auto Release");
		   form.orderCiscoRevUpdate();
	 		 //if (null==agilePartNumber) {
      //	 agilePartNumber = form.findLastPublishedPartNumber(source.getOrigin());
		 	// }
			 //if (sourceData is library component) return ctrlERR_EVENT_HANDLER_NOT_DEFINED;
       return ctrlEVENT_FIRED;
		 }
		 catch(Exception e) {
		   e.printStackTrace();
		   return ctrlERROR;
		 }
	}

	private String customCiscoRenumberItem(RoutedEventBase event) {
	  RenumberItem ev = (RenumberItem)event;
	  try {
      Origin lastOrigin= synkService.lastSynchronization(ev.getDomainName(), ev.getServerName(), ev.getDatasourceName(), ev.getNewName());
      if (null==lastOrigin) return ctrlEVENT_FIRED;
      Origin agileOrigin= synkService.findSynchronization(lastOrigin, ev.getAgileDomainName(), ev.getAgileServerName(), ev.getAgileDatasourceName());
      if (null==agileOrigin) return ctrlEVENT_FIRED;

      hiCiscoPublish2Agile form = new hiCiscoPublish2Agile();
      MetadataBase data = new MetadataBase();
      data.setName(ev.getNewName());
      String tempNoPrefix = form.getTempCiscoPartNumberPrefix();
	    String proposedPartNumber = form.proposeCiscoPartNumber(data, tempNoPrefix);
      {} //System.out.println("Updating with new temp name");
   	  CiscoMetadataAdapter source = null;
      {} //System.out.println("1");
      source = form.adaptSourceDesign(lastOrigin);
      data = source.getMetadataBase();
      {} //System.out.println("retrieved data: " + data);
      {} //System.out.println("2");
      data.set(form.ATT_AGILE_NUMBER, agileOrigin.getName());
      {} //System.out.println("3");
      Collection dataList = new Vector();
      {} //System.out.println("4");
      dataList.add(data);
      {} //System.out.println("5");
      {} //System.out.println("" + data);
      Collection mappedData = form.mapAttributes(dataList, form.AGILE_CLASS_CAD_PART);
      {} //System.out.println("attributes mapped: " + mappedData.iterator().next());
      {} //System.out.println("6");
      agile().update(mappedData);
      {} //System.out.println("7");
      OriginBase previousOrigin = (OriginBase)OriginMaker.materialize(lastOrigin.toString());
      {} //System.out.println("77");
      previousOrigin.setName(event.getName());
      {} //System.out.println("777");
      form.replaceURL(agileOrigin.getName(), previousOrigin, lastOrigin);
      {} //System.out.println("7777");
		  if (!proposedPartNumber.startsWith(tempNoPrefix)) {
	      {} //System.out.println("Converting to MWF_CPN");
	      {} //System.out.println("8");
	      agile().rename(agileOrigin.getName(), proposedPartNumber);
	      //synkService.rename(agileOrigin.getDomainName(), agileOrigin.getServerName(), agileOrigin.getDatasourceName(), agileOrigin.getName(), proposedPartNumber);
	      {} //System.out.println("9");
  	    ECO eco = agile().createECO("Intralink ECO - Cisco Standard", null, "Intralink ECO - Cisco Standard Auto Release", null);
	      {} //System.out.println("10");
	      Map validCiscoPartNumbers = new HashMap();
	      {} //System.out.println("11");
	      validCiscoPartNumbers.put(lastOrigin.getName(), proposedPartNumber);
	      {} //System.out.println("12");
	      form.bindCiscoEngineeringContainer(eco, validCiscoPartNumbers);
	      {} //System.out.println("13");
		    agile().moveToNextStatus(eco.getNumber(), getAuthentication());
	      {} //System.out.println("14");
	    }
	  }
	  catch(Exception e) { e.printStackTrace(); }
	  return ctrlEVENT_FIRED;
	}



	private String zwsPublishLibrary(RoutedEventBase ev) {
	 	 {} //System.out.println("Handler found!");
		 hiZWSPublish2Agile form = new hiZWSPublish2Agile ();

		 I2AMetadataAdapter source = null;
		 PublishLibraryItem event = (PublishLibraryItem) ev;
		 try {
	     source = form.adaptSourceDesign(event.getOrigin());
       //check to see if source matches policy:
	     String location = source.getMetadata().get("folder");
       {} //System.out.println(source.getName() + "["+location+"]");
	     if (null==location) return ctrlEVENT_FIRED;
	     if (location.toLowerCase().indexOf("cisco standard") < 0) {
	       {} //System.out.println(source.getName() + "["+location+"] not located in standard lib");
	       return ctrlEVENT_FIRED;
	     }
	     if (null==source) return this.ctrlERR_EVENT_NOT_RECOGNIZED;
		   form.getChosenItems().add(source);

	 		 //String aggilePartNumber = form.findLastPublishedPartNumber(source.getOrigin());
		   {} //System.out.println("Publishing rev update ");
		   form.createNewECO("Intralink ECO - Cisco Standard", "Intralink ECO - Cisco Standard Auto Release");
		   form.orderZWSRevUpdate();
	 		 //if (null==agilePartNumber) {
      //	 agilePartNumber = form.findLastPublishedPartNumber(source.getOrigin());
		 	// }
			 //if (sourceData is library component) return ctrlERR_EVENT_HANDLER_NOT_DEFINED;
       return ctrlEVENT_FIRED;
		 }
		 catch(Exception e) {
		   e.printStackTrace();
		   return ctrlERROR;
		 }
	}

	private String zwsRenumberItem(RoutedEventBase event) {
	  RenumberItem ev = (RenumberItem)event;
	  try {
      Origin lastOrigin= synkService.lastSynchronization(ev.getDomainName(), ev.getServerName(), ev.getDatasourceName(), ev.getNewName());
      if (null==lastOrigin) return ctrlEVENT_FIRED;
      Origin agileOrigin= synkService.findSynchronization(lastOrigin, ev.getAgileDomainName(), ev.getAgileServerName(), ev.getAgileDatasourceName());
      if (null==agileOrigin) return ctrlEVENT_FIRED;

      hiZWSPublish2Agile form = new hiZWSPublish2Agile();
      MetadataBase data = new MetadataBase();
      data.setName(ev.getNewName());
      String tempNoPrefix = form.getTempPartNumberPrefix();
	    String proposedPartNumber = form.proposePartNumber(data, tempNoPrefix);
      {} //System.out.println("Updating with new temp name");
   	  I2AMetadataAdapter source = null;
      {} //System.out.println("1");
      source = form.adaptSourceDesign(lastOrigin);
      data = source.getMetadataBase();
      {} //System.out.println("retrieved data: " + data);
      {} //System.out.println("2");
      data.set(form.ATT_AGILE_NUMBER, agileOrigin.getName());
      {} //System.out.println("3");
      Collection dataList = new Vector();
      {} //System.out.println("4");
      dataList.add(data);
      {} //System.out.println("5");
      {} //System.out.println("" + data);
      Collection mappedData = form.mapAttributes(dataList, form.AGILE_CLASS_CAD_PART);
      {} //System.out.println("attributes mapped: " + mappedData.iterator().next());
      {} //System.out.println("6");
      agile().update(mappedData);
      {} //System.out.println("7");
      OriginBase previousOrigin = (OriginBase)OriginMaker.materialize(lastOrigin.toString());
      {} //System.out.println("77");
      previousOrigin.setName(event.getName());
      {} //System.out.println("777");
      form.replaceURL(agileOrigin.getName(), previousOrigin, lastOrigin);
      {} //System.out.println("7777");
		  if (!proposedPartNumber.startsWith(tempNoPrefix)) {
	      {} //System.out.println("Converting to MWF_CPN");
	      {} //System.out.println("8");
	      agile().rename(agileOrigin.getName(), proposedPartNumber);
	      //synkService.rename(agileOrigin.getDomainName(), agileOrigin.getServerName(), agileOrigin.getDatasourceName(), agileOrigin.getName(), proposedPartNumber);
	      {} //System.out.println("9");
  	    ECO eco = agile().createECO("Intralink ECO - Cisco Standard", null, "Intralink ECO - Cisco Standard Auto Release", null);
	      {} //System.out.println("10");
	      Map validCiscoPartNumbers = new HashMap();
	      {} //System.out.println("11");
	      validCiscoPartNumbers.put(lastOrigin.getName(), proposedPartNumber);
	      {} //System.out.println("12");
	      //form.bindCiscoEngineeringContainer(eco, validCiscoPartNumbers);
	      {} //System.out.println("13");
		    agile().moveToNextStatus(eco.getNumber(), getAuthentication());
	      {} //System.out.println("14");
	    }
	  }
	  catch(Exception e) { e.printStackTrace(); }
	  return ctrlEVENT_FIRED;
	}


	private String inactivate(RoutedEventBase ev) {
	  return ctrlERR_EVENT_HANDLER_NOT_DEFINED;
  }

	private String update(RoutedEventBase ev) {
   return ctrlERR_EVENT_HANDLER_NOT_DEFINED;
  }


	private String renumberItem(RoutedEventBase ev) {
    return ctrlERR_EVENT_HANDLER_NOT_DEFINED;
	}

	private String updateWithNewPartNumber(RoutedEventBase ev) {
    return ctrlERR_EVENT_HANDLER_NOT_DEFINED;
	}

  private AgileAccess agile() throws Exception {
    return AgileAccess.getAccess("DesignState-node-0", Properties.get(Names.DEFAULT_AGILE_SOURCE), getAuthentication());
  }

	private Metadata findAgileCADModel(RoutedEvent ev) {
	  try {
	    Metadata data = agile().find(ev.getName());
	    {} //System.out.println("------------------------------");
	    {} //System.out.println(data);
	    {} //System.out.println("------------------------------");
	    return data;
	  }
	  catch(Exception e) { return null; }
	}

	private String attachFiles(RoutedEvent ev){ return attachFiles(findAgileCADModel(ev)); }

	private String attachFiles(Metadata agilePart){
    String attachments = agilePart.get("mcad attachments");
	  {} //System.out.println("MCAD Attachments: " + attachments);
	  PrintUtil.print(agilePart.getAttributes());
	  if (null==attachments || "".equals(attachments.trim())) return ctrlERR_MISSING_REQUIRED_ATTRIBUTE;
	  StringTokenizer tokens = new StringTokenizer(attachments, ";");
	  if (1==tokens.countTokens()) {
	    try { attachFiles(agilePart, attachments.trim()); }
	    catch (Exception e) { e.printStackTrace(); return ctrlERR_EVENT_ERROR; }
	    return ctrlEVENT_FIRED;
	  }
	  String r = ctrlEVENT_FIRED;
	  while (tokens.hasMoreTokens()) {
      try { attachFiles(agilePart, tokens.nextToken().trim()); }
      catch (Exception e) { e.printStackTrace(); r = ctrlERR_EVENT_ERROR; }
	  }
	  return r;
	}

	private static String URL="url|";
	private static String NATIVE="native|";
	private static String SNAPSHOT="snapshot|";
	private void attachFiles(Metadata agilePart, String attachmentType) throws Exception {
	  String prefix;
	  prefix = URL;
	  if (attachmentType.length() > prefix.length() && prefix.equalsIgnoreCase(attachmentType.substring(0, prefix.length()))) attachURL(agilePart,attachmentType);
	  prefix = NATIVE;
	  if (attachmentType.length() > prefix.length() && prefix.equalsIgnoreCase(attachmentType.substring(0, prefix.length()))) attachNative(agilePart,attachmentType);
	  prefix = SNAPSHOT;
	  if (attachmentType.length() > prefix.length() && prefix.equalsIgnoreCase(attachmentType.substring(0, prefix.length()))) attachSnapshot(agilePart,attachmentType);
	}

	private Synchronizer synk() { return Synchronizer.getClient(Properties.get(Names.CENTRAL_SERVER)); }

	private static String IGES="IGES";
	private static String STEP="STEP";
	private static String NEUTRAL="NEUTRAL";
	private static String DRAWING="DRAWING";
	private static String MODEL="MODEL";

	private Origin findSynchronization(Origin agileOrigin, String format) {
	  try {
      Origin synk=null;
	    Origin o = null;
	    Collection c = synk().findAllSynchronizationRecords(agileOrigin);
	    if (null==c) return null;
	    Iterator i = c.iterator();
	    String fileType;
	    while(i.hasNext()) {
 	      o = (Origin)i.next();
	      if(Origin.FROM_AGILE.equals(o.getDatasourceType())) continue;
	      fileType = FileNameUtil.getFileNameExtension(o.getName());
	      if (null==fileType) return null;
	      else if (IGES.equalsIgnoreCase(format) && fileType.equalsIgnoreCase("igs")) return o;
	      else if (IGES.equalsIgnoreCase(format) && fileType.equalsIgnoreCase("iges")) return o;
	      else if (STEP.equalsIgnoreCase(format) && fileType.equalsIgnoreCase("step")) return o;
	      else if (NEUTRAL.equalsIgnoreCase(format) && fileType.equalsIgnoreCase("neu")) return o;
	      else if (DRAWING.equalsIgnoreCase(format) &&
	              (fileType.equalsIgnoreCase("prt") ||
	               fileType.equalsIgnoreCase("asm")  ||
	               fileType.equalsIgnoreCase("drw")
	              )){
	        String name = FileNameUtil.getBaseName(o.getName()) + ".drw";
	        Origin drw = OriginMaker.materialize(o.getDomainName(), o.getServerName(), o.getDatasourceType(), o.getDatasourceName(), 0, o.getUniqueSequence(), name, "", "");
	        Metadata data = IntralinkAccess.getAccess().find(drw, getAuthentication());
	        if (null==data) return null;
	        return data.getOrigin();
	      }
	      else if (MODEL.equalsIgnoreCase(format) && fileType.equalsIgnoreCase("asm")) return o;
	      else if (MODEL.equalsIgnoreCase(format) && fileType.equalsIgnoreCase("prt")) return o;
	    }
      return null;
	  }
	  catch(Exception e) {
	    e.printStackTrace();
	    return null;
	  }
	}

  private void attachSnapshot(Metadata agilePart, String attachmentType) throws Exception {
    String format = attachmentType.substring(SNAPSHOT.length());
    {} //System.out.println("Attaching a Snapshot of a [" + format +"] format to " + agilePart.getName());
    Origin o = findSynchronization(agilePart.getOrigin(), format);
    if (null==o) snapshot(agilePart.getOrigin(), format);
    o = findSynchronization(agilePart.getOrigin(), format);
    if (null!=o) attachFile(agilePart, o);
  }

  private void attachNative(Metadata agilePart, String attachmentType) throws Exception {
    String format = attachmentType.substring(NATIVE.length());
    {} //System.out.println("Attaching a Native of a ["+format+ "] to "+ agilePart.getName());
    Origin o = findSynchronization(agilePart.getOrigin(), format);
    if (null!=o) attachFile(agilePart, o);
  }

  private void attachURL(Metadata agilePart, String attachmentType) throws Exception {
    String type = attachmentType.substring(URL.length());
	  String prefix;
	  prefix = NATIVE;
	  String format;
	  {} //System.out.println("urltype = " + type);
	  if (type.length() > prefix.length() && prefix.equalsIgnoreCase(type.substring(0, prefix.length()))) {
       format = type.substring(prefix.length());
       {} //System.out.println("Attaching a hyperlink to the Native " + format);
       Origin o = findSynchronization(agilePart.getOrigin(), format);
       if (null!=o) attachURL(agilePart, o);
	  }
	  prefix = SNAPSHOT;
	  if (type.length() > prefix.length() && prefix.equalsIgnoreCase(type.substring(0, prefix.length()))) {
      format = type.substring(prefix.length());
      {} //System.out.println("Attaching a hyperlink to the " + format + "Snapshot");
      Origin o = findSynchronization(agilePart.getOrigin(), format);
      if (null==o) snapshot(agilePart.getOrigin(), format);
      else attachURL(agilePart, o);
	  }
  }

	private void snapshot(Origin o, String format) throws Exception {
    IntralinkAccess access = IntralinkAccess.getAccess();
    String imageRepository= Properties.get(zws.application.server.webapp.Names.IMAGE_REPOSITORY);
    if (null==imageRepository|| "".equals(imageRepository.trim())) imageRepository=null;
    access.snapshotImage(o, format, getAuthentication());
	}

	private void attachFile(Metadata agilePart, Origin o) throws Exception {
    if (null==agilePart) return;
    if (null==o) return;
    IntralinkAccess access = IntralinkAccess.getAccess();
    String ws=agilePart.getName().replace('.', '_');
    String location = agilePart.getName();
    ilink.createWorkspace(o.getServerName(),o.getDatasourceName(), ws, getAuthentication());
    ilink.checkout(o, ws, getAuthentication());
    ilink.export(o, ws, location, getAuthentication());
    agile().attachFile(location, o.getName(), agilePart.getName());
	}

	private void attachURL(Metadata agilePart, Origin o) throws Exception {
   if (null==agilePart) return;
   if (null==o) return;
   URL url = new URL("http://"+Server.getHostName()+"/report/index.do?event=download&ID="+o.toString());
   agile().attachURL(url, o.getName(), agilePart.getName());
	}

	private String broadcastPartNumber(RoutedEventBase ev) {
    return ctrlERR_EVENT_HANDLER_NOT_DEFINED;
	}

	private String broadcastRevision(RoutedEventBase ev) {
    return ctrlERR_EVENT_HANDLER_NOT_DEFINED;
	}

	private String broadcastLifeCyclePhase(RoutedEventBase ev) {
    return ctrlERR_EVENT_HANDLER_NOT_DEFINED;
	}

	private String broadcastPLMStatus(RoutedEventBase ev) {
    return ctrlERR_EVENT_HANDLER_NOT_DEFINED;
	}

  static Synchronizer sync = Synchronizer.getClient("DesignState-node-0");
  static IntralinkAccess ilink = IntralinkAccess.getAccess();
  protected AgileAccess agileClient(RoutedEventBase ev) throws Exception {
	  return AgileAccess.getAccess(ev.getServerName(), ev.getDatasourceName(), getAuthentication());
	}

  public void updatePLMStatus(AffectedItem item, ECO eco, KLAMetadataAdapter source) {
    updatePLMStatus(item, eco, source.getOrigin());
  }

  public void clearPLMStatus(AffectedItem item, ECO eco, KLAMetadataAdapter source) {
    clearPLMStatus(item, eco, source.getOrigin());
  }

  public void updatePLMStatus(AffectedItem item, ECO eco, Origin source) {
	  String oldRev=null, newRev=null;
	  String oldLCP=null, newLCP=null;
	  oldRev = item.getOldRev();
	  newRev = item.getNewRev();
	  oldLCP = item.getOldLifeCyclePhase();
	  newLCP = item.getNewLifeCyclePhase();
	  String number = item.getItemNumber();
	  if (null==oldRev || "".equals(oldRev.trim())) oldRev =" ";
	  if (null==oldLCP || "".equals(oldLCP.trim())) oldLCP ="Preliminary";

	  if (null!=newRev && !"".equals(newRev.trim())) number +=".("+newRev+")";
	  else number +="."+oldRev;

	  if (null!=newLCP && !"".equals(newLCP.trim())) number +=".("+newLCP+")";
	  else number +="."+"Preliminary";

	  String ec = " ECO#" + eco.getNumber() +": "+eco.getStatus() ;
	  if (eco.getNumberOfAffectedItems() == 1) ec +=" ("+eco.getNumberOfAffectedItems()+" item)";
	  else if (eco.getNumberOfAffectedItems() > 1) ec +=" ("+eco.getNumberOfAffectedItems()+" items)";
	  String plmStatus = number+ " ["+ec+"]";
	  {} //System.out.println(plmStatus);
	  try {
	    IntralinkAccess ilink = IntralinkAccess.getAccess();
	    {} //System.out.println("Agile PLM Status="+plmStatus);
	    ilink.setLifeCycleAttribute(source, ATT_ILINK_AGILE_STATUS, plmStatus, getAuthentication());
	  }
	  catch(Exception e) { e.printStackTrace(); }
  }


  public void clearPLMStatus(AffectedItem item, ECO eco, Origin source) {
	  String number = item.getItemNumber();
	  try {
	    IntralinkAccess ilink = IntralinkAccess.getAccess();
	    {} //System.out.println("Agile PLM Status="+number);
	    ilink.setLifeCycleAttribute(source, ATT_ILINK_AGILE_STATUS, number, getAuthentication());
	  }
	  catch(Exception e) { e.printStackTrace(); }
  }

  private String approveECO(RoutedEventBase ev) {
 	  Metadata agileData = null;
	  ECO eco=null;
	  try { eco = agile().findECO(ev.getName()); }
	  catch (Exception e) {e.printStackTrace(); return this.ctrlERR_EVENT_ERROR; }
    hiKLAPublish2Agile form = new hiKLAPublish2Agile();
    KLAMetadataAdapter source = null;
	  AffectedItem item = null;
	  Iterator i = eco.getAffectedItems().iterator();
	  String oldRelease, newRelease;
    IntralinkAccess ilink = IntralinkAccess.getAccess();
    Metadata sourceData=null;
	  while (i.hasNext()) {
	    item = (AffectedItem) i.next();
	    try {
	 	   agileData = agileClient(ev).find(item.getItemNumber());
	 	   source = form.findSourceDesign(agileData);
	 	   sourceData = source.getMetadata();
		   {} //System.out.println(source);
	 	   oldRelease = sourceData.get("release-level");
	 	   newRelease = item.getNewLifeCyclePhase();
	 	   if (null==newRelease || "".equals(newRelease.trim())) newRelease = "Preliminary";
		   {} //System.out.println("Want to Promote "+ source.getName()+" from " + oldRelease + " to " + newRelease);
	 	   if (null!=oldRelease && !oldRelease.equalsIgnoreCase(newRelease)) {
  		   {} //System.out.println("Promoting "+ source.getName()+" from " + oldRelease + " to " + newRelease);
  		   ilink.demote(source.getOrigin(),newRelease, getAuthentication());
	 	   }
 		 }
 		 catch (Exception e) { {} //System.out.println(e.getMessage()); }
 		   
 		 }
 	   try {  ilink.unlock(source.getOrigin(), getAuthentication()); }
 	   catch (Exception e) {
 	     e.printStackTrace();
 	     {} //System.out.println("Could not unlock " + source.getName());
 	     {} //System.out.println(e.getMessage());
 	   }
 	 }

		try { source = form.findSourceDesign(agileData); }
		catch (Exception e) { {} //System.out.println(e.getMessage()); }
		  
		}
	  String release = eco.getStatus();
	  try { clearECOStatus(ev, eco); }
	  catch (Exception e) {e.printStackTrace(); return this.ctrlERR_EVENT_ERROR; }
	  return ctrlEVENT_FIRED;
  }

  private String rejectECO(RoutedEventBase ev) {
    return this.ctrlERR_EVENT_HANDLER_NOT_DEFINED;
  }

  private String updateECOStatus(RoutedEventBase ev) {
   ECO eco=null;
   try { eco = agile().findECO(ev.getName()); }
   catch (Exception e) {e.printStackTrace(); return this.ctrlERR_EVENT_ERROR; }
   try { updateECOStatus(ev, eco); }
   catch (Exception e) {e.printStackTrace(); return this.ctrlERR_EVENT_ERROR; }
   return ctrlEVENT_FIRED;
  }


  private void updateECOStatus(RoutedEventBase ev, ECO eco) {
	  Metadata agileData = null;
    hiKLAPublish2Agile form = new hiKLAPublish2Agile();
	  KLAMetadataAdapter source = null;
	  AffectedItem item = null;
	  Iterator i = eco.getAffectedItems().iterator();
	  while (i.hasNext()) {
	    item = (AffectedItem) i.next();
	    try {
	 	   agileData = agileClient(ev).find(item.getItemNumber());
	 	   source = form.findSourceDesign(agileData);
		   updatePLMStatus(item, eco, source);
 		 }
 		 catch (Exception e) { {} //System.out.println(e.getMessage()); }
 		   
 		 }
 	 }
  }

  private void clearECOStatus(RoutedEventBase ev, ECO eco) {
  	  Metadata agileData = null;
      hiKLAPublish2Agile form = new hiKLAPublish2Agile();
  	  KLAMetadataAdapter source = null;
  	  AffectedItem item = null;
  	  Iterator i = eco.getAffectedItems().iterator();
  	  while (i.hasNext()) {
  	    item = (AffectedItem) i.next();
  	    try {
  	 	   agileData = agileClient(ev).find(item.getItemNumber());
  	 	   source = form.findSourceDesign(agileData);
  		   clearPLMStatus(item, eco, source);
   		 }
   		 catch (Exception e) { {} //System.out.println(e.getMessage()); }
   		   
   		 }
   	 }
    }

  /*
  private String promote(RoutedEventBase ev) {

    for each promoted item {
      sourceData = next item
      agileOrigin = findIn synklog
      if(null==agileOrigin) continue;
      if item is ecopending continue;
      agileDsta = find agileitem
      Collection ilinks.add(sourceData)
      Collection agiles.add(agileData)
    }

    Iterator i=ilinks.iterator();
    while (i.hasNext() ) {
      sourceData = i.next();
      promote source("ECO Pending")
    }

    Iterator i=agils.iterator();
    eco = create eco;
    while (i.hasNext() ) {
      agileData = i.next();
      addto eco(agiledata);
    }
  }
  */
  private String promote(RoutedEventBase ev) {
	 {} //System.out.println("Handler found!");
	 hiKLAPublish2Agile form = new hiKLAPublish2Agile();
	 Metadata agileData = retrieveMetadata(ev);
	 KLAMetadataAdapter source = null;
	 Metadata sourceData = null;
	 try {
	   source = form.findSourceDesign(agileData);
	   sourceData=source.getMetadata();
	 }
	 catch (Exception e) { {} //System.out.println(e.getMessage()); }
	   
	 }
	 if (null==source) return ctrlERR_NOT_SYNCHRONIZED;
   form.getChosenItems().add(source);
	 form.createNewECO();
	 ECO eco=null;
   String release = sourceData.get("release-level");
   String nextRelease = form.getLCO().getPhaseAfter(release);
   {} //System.out.println("Promoting "+release+" to next release of "+nextRelease);
	 form.release2LifeCyclePhase(nextRelease);
	 try { eco = agile().findECO(form.getSelectedECO().getNumber()); }
	 catch (Exception e) {e.printStackTrace(); return this.ctrlERR_EVENT_ERROR; }
	 updateECOStatus(ev, eco);
   IntralinkAccess ilink = IntralinkAccess.getAccess();
   try {
 	   {} //System.out.println("Locking to ECO Pending");
     ilink.lock(source.getOrigin(), getAuthentication());
 	   {} //System.out.println("Locked");
     ilink.promote(source.getOrigin(), "ECO Pending", getAuthentication());
 	   {} //System.out.println("Promoted");
   }
   catch (Exception e) {
     e.printStackTrace();
     {} //System.out.println("Could not lock " + source.getName());
     {} //System.out.println(e.getMessage());
   }
	 return ctrlEVENT_FIRED;
	}

  protected Metadata retrieveMetadata(RoutedEventBase ev) {
    Metadata data=null;
    try { data = agileClient(ev).find(ev.getName()); }
    catch (Exception e) { e.printStackTrace(); }
    return data;
  }

	public String getEv() { return ev; }
	public void setEv(String s) { ev=s; }

  public String getUsername() { return username; }
  public void setUsername(String s) { username=s; }
  public String getPassword() { return password; }
  public void setPassword(String s) { password=s; }

  public String getErrorMessage() { return errorMessage; }
  public void setErrorMessage(String s) { errorMessage=s; }

  private String ev=null;
  private String username=null;
  private String password=null;
  private String errorMessage=null;
  private transient Synchronizer synkService = Synchronizer.getClient("DesignState-node-0");

  protected static String ATT_ILINK_AGILE_STATUS="Agile";
}
