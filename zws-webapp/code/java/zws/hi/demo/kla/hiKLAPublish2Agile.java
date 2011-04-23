package zws.hi.demo.kla; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Apr 12, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.AgileAccess;
import zws.IntralinkAccess;
import zws.Synchronizer;
import zws.application.Names;
import zws.application.Properties;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.eco.AffectedItem;
import zws.data.eco.ECO;
import zws.exception.Duplicate;
import zws.exception.NotSynchronized;
import zws.exception.NotUpToDate;
import zws.hi.event.service.hiEventService;
import zws.lifecycle.LifeCycleOrder;
import zws.origin.Origin;
import zws.util.FileNameUtil;
import zws.util.MapUtil;
import zws.util.Pair;
import zws.util.PrintUtil;
import zws.util.StringPair;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
public class hiKLAPublish2Agile  extends hiKLAReport {
  public String getSelectedReportName() { return Properties.get("demo-report-harris"); }

  //Super Demo Admin

  public String findLastPublishedPartNumber(Origin ilinkOrigin) throws Exception, NotSynchronized {
	  if (null==ilinkOrigin) return null;
	  Origin last = synkService.lastSynchronization(ilinkOrigin.getDomainName(), ilinkOrigin.getServerName(), ilinkOrigin.getDatasourceName(), ilinkOrigin.getName());
	  if (null==last) return null;
	  Collection c = synkService.findAllSynchronizationRecords(last);
	  if (null==c) return null;
	  Iterator i = c.iterator();
	  Origin o=null;
	  while (i.hasNext()) {
	    o = (Origin)i.next();
	    if (o.getDatasourceType().startsWith(Origin.FROM_AGILE))break;
	    o=null;
	  }
	  if (null==o) return null;
	  return o.getName();
	}
  
  public KLAMetadataAdapter findSourceDesign(Metadata agileData) throws Exception, NotSynchronized {
	  if (null==agileData) return null;
	  Origin agileOrigin = agileData.getOrigin();
	  Collection c = synkService .findAllSynchronizationRecords(agileData.getOrigin());
	  if (null==c) return null;
	  Iterator i = c.iterator();
	  Origin o=null;
	  while (i.hasNext()) {
	    o = (Origin)i.next();
	    if (o.getDatasourceType().equals(Origin.FROM_ILINK))break;
	    o=null;
	  }
	  if (null==o) throw new NotSynchronized(agileData.getName());
	  Metadata source = IntralinkAccess.getAccess().find(o, getAuthentication());
	  {} //System.out.println("Finding Source Design for: ");
	  {} //System.out.println(agileData);
	  System.out.print("Source =" + o);	  
	  KLAMetadataAdapter adapter = new KLAMetadataAdapter();
	  adapter.setMetadataFields (new String[] {"name", "branch", "rev", "ver"});
	  adapter.adapt(source);
	  return adapter;
	}

  public KLAMetadataAdapter adaptSourceDesign(Origin ilinkOrigin) throws Exception, NotSynchronized {
	  Metadata source = IntralinkAccess.getAccess().find(ilinkOrigin, getAuthentication());
	  KLAMetadataAdapter adapter = new KLAMetadataAdapter();
	  adapter.setMetadataFields (new String[] {"name", "branch", "rev", "ver"});
	  adapter.adapt(source);
	  return adapter;
	}

  public Origin findSourceOrigin(Metadata agileData) throws Exception, NotSynchronized {
	  if (null==agileData) return null;
	  Origin agileOrigin = agileData.getOrigin();
	  Collection c = synkService .findAllSynchronizationRecords(agileData.getOrigin());
	  if (null==c) return null;
	  Iterator i = c.iterator();
	  Origin o=null;
	  while (i.hasNext()) {
	    o = (Origin)i.next();
	    if (o.getDatasourceType().equals(Origin.FROM_ILINK))break;
	    o=null;
	  }
	  if (null==o) throw new NotSynchronized(agileData.getName());
	  return o;
	}

  
  public String purgeCADParts() {
    try { 
	    Collection c = agile().purgeItems(AGILE_CLASS_PART, AGILE_CLASS_CAD_PART);
	    {} //System.out.println("--------------------------------------------------------");
	    {} //System.out.println("--------------------------------------------------------");
	    {} //System.out.println("Coud not Delete These:");
	    {} //System.out.println("--------------------------------------------------------");
	    PrintUtil.print(c);
	    {} //System.out.println("--------------------------------------------------------");
	    {} //System.out.println("--------------------------------------------------------");
		}
	  catch(Exception e) { e.printStackTrace(); }
	  return ctrlOK;
	}  
  
  //CAD DOCUMENTS
  public String publishCADDocuments() {      
	  Map agileMappings = MapUtil.getMapFromMap(hiKLADemo.agileMappings, "CAD Document");
	  if (agileMappings.isEmpty()) {
      logFormWarning("err.mapping.is.undefined", "CAD Document");
	    {} //System.out.println("***********Mappings for CAD Document are not defined!");
	    return ctrlOK;
	  }
	  createCADDocuments();
	  storeCADDocumentBills();
	  return ctrlOK;
	}
  
  public String createCADDocuments() {
    try { createChosenItems("CAD Document"); }
    catch(Exception e) { e.printStackTrace(); }
    return ctrlOK;
  }  

  public String storeCADDocumentBills() {
    try { 
		  Collection c = loadBills(getChosenItems(), "CAD Document");
		  c = mapBills(c, "CAD Document");
		  storeBills(c); 
		}
	  catch(Exception e) { e.printStackTrace(); }
	  return ctrlOK;
	}  

 
  public String delete() {
	  try { deleteChosenItems(); }
	  catch(Exception e) { e.printStackTrace(); }
	  return ctrlOK;
  }
  
  //CAD PARTS
  public String publishCADModels() {
    Map agileMappings = MapUtil.getMapFromMap(hiKLADemo.agileMappings, AGILE_CLASS_CAD_PART);
    if (agileMappings.isEmpty()) {
      this.logFormWarning("err.mapping.is.undefined", AGILE_CLASS_CAD_PART);
	    {} //System.out.println("***********Mappings for CAD Part are not defined!");
      return ctrlOK;
    }
	  createCADmodels();
	  storeCADModelBills(true);
	  return ctrlOK;
	}
  
  public String createCADmodels() {
    createCADDocuments();
	  try { createChosenItems(AGILE_CLASS_CAD_PART); }
	  catch(Exception e) { e.printStackTrace(); }
	  return ctrlOK;
	}  
	
  public String storeCADModelBills(boolean linkCADDocuments) {
	  storeCADDocumentBills();
	  try { 
		  Collection c = loadBills(getChosenItems(), AGILE_CLASS_CAD_PART);
		  c = mapBills(c, AGILE_CLASS_CAD_PART);
		  c = linkKLAModelToDocument(c);
		  storeBills(c); 
		}
	  catch(Exception e) { e.printStackTrace(); }
	  return ctrlOK;
	}  

  
  /*
	public String deleteCADModels() {
	  try { deleteChosenItems(AGILE_CLASS_CAD_PART); }
	  catch(Exception e) { e.printStackTrace(); }
	  return ctrlOK;
	}
	*/
  
  
  public String deleteBill() {
    delete();
    /*
    BillOfMaterials bill;
    KLAMetadataAdapter data;
    Iterator i = getChosenItems().iterator();
    try {
      IntralinkAccess y = IntralinkAccess.getAccess();
      while (i.hasNext()) {
        data = (KLAMetadataAdapter) i.next();
        bill = y.reportBill(data.getOrigin(), getAuthentication());
        //x.delete(map(bill));
      }
    }
    catch(Exception e) {e.printStackTrace(); }
    */
    return ctrlOK;
  }
  
  
  //*****************************************
  //ECO Stuff

	public final String chooseECO() { 
    if(chooseECOItem(getID())) return ctrlCHOOSE;
    logFormError("can.not.select.item", getID());
    return ctrlINDEX;
  }
      
	// identifies the chosen one
	protected boolean chooseECOItem(String s) {
	  if ("".equals(s)) return false;
	  Iterator i = getECOResults().iterator();
	  ECO item = null;
	  ECO eco;
	  while (null==item && i.hasNext()) {
	    eco = (ECO)i.next();
	    if(idChoosesECO(s, eco)) item=eco;
	  }
	  if (null==item) return false;
	  try {
	    selectedECO = agile().findECO(item.getNumber());
	    return true;
	  }
	  catch(Exception e) { e.printStackTrace(); return false; }
	}
	
  public boolean idChoosesECO(String id, ECO eco) {
    return (id.equals(eco.getNumber()));
  }

  public String ECOSearch() {
    try {
      getECOResults().clear();
      Collection c= agile().findECOs(getECONumberCriteria(), null,null,null);
      getECOResults().addAll(c);
    }
    catch (Exception e) { e.printStackTrace(); }
    return ctrlOK;
  }

  public String createNewECO() {
	  try {
	    ECO eco = agile().createECO();
	    selectedECO=eco;
	    {} //System.out.println("Created ECO: " + eco.getNumber());
	  }
	  catch (Exception e) { e.printStackTrace(); }
	  return ctrlOK;
	}


  public String createNewECO(String ecoClass, String workflow) {
	  try {
	    ECO eco = agile().createECO(ecoClass, null, workflow, null);
	    selectedECO=eco;
	    {} //System.out.println("Created ECO: " + eco.getNumber());
	  }
	  catch (Exception e) { e.printStackTrace(); }
	  return ctrlOK;
	}
  
  public ECO getSelectedECO() { return selectedECO; }
  public String getECONumberCriteria() { return ECONumberCriteria; }
  public void setECONumberCriteria(String s ) { ECONumberCriteria=s; }

  public Collection getECOResults() {
    if (null==ECOResults) ECOResults = new Vector();
    return ECOResults;
  }

  public String orderInitialRelease() {
    AffectedItem changedItem = new AffectedItem();
    KLAMetadataAdapter m = (KLAMetadataAdapter)getChosenItems().iterator().next();
    changedItem.setItemNumber(m.getName());
    try {
	    Collection changedItems = new Vector();
	    changedItems.add(changedItem);
	    ECO eco = agile().orderInitialRelease(getSelectedECO().getNumber(), changedItems);
	    selectedECO=eco;
	  }
	  catch (Exception e) { e.printStackTrace(); }	  
    Iterator i = getChosenItems().iterator();
    KLAMetadataAdapter data;
    while (i.hasNext()) {
      data = (KLAMetadataAdapter)i.next();
      {} //System.out.println(data);
      {} //System.out.println("CAD Doc Name="+data.getCADDocumentName());
    }
    return ctrlOK;
  }
  protected Map formatNewPartNumbers(Map emptyPartNumbers, Map newPartNumbers) {
    String name, number;
    Map m = new HashMap();
    Iterator i = newPartNumbers.keySet().iterator();
    while (i.hasNext()) {
      name = i.next().toString();
      number = (String)newPartNumbers.get(name);
      if (number!=null && 7==number.length()) number += "-000";
      m.put(name, number);
    }
    return m;
  }
  
  protected void loadNewPartNumbers(Collection parts, Map newPartNumbers) {
    Metadata mData;
    MetadataBase data;
    String name, number;
    Map m = new HashMap();
    Iterator i = parts.iterator();
    while (i.hasNext()) {
      mData=(Metadata)i.next();
      data= mData.getMetadataBase();
      name = data.getName();
      number = (String)newPartNumbers.get(name);
      if (null!=number && !"".equals(number.trim())) {
       data.set(ATT_ILINK_PART_NUMBER, number);
       data.set(ATT_AGILE_NUMBER, number);
      }
    }
  }
/*
  private Collection numberAllParts(Collection allParts) throws Exception {
    String number;
    MetadataBase data;
    Collection c= new Vector();
    Iterator i = allParts.iterator();
    while (i.hasNext()) {
      data = ((Metadata)i.next()).getMetadataBase();
      number=data.get(ATT_AGILE_NUMBER);
      if (null==number || "".equals(number.trim())) {
        number = data.get(ATT_ILINK_PART_NUMBER);
        data.set(ATT_AGILE_NUMBER, number);
      }
      if (null==number || "".equals(number.trim())) throw new InvalidName(number);
      c.add(data);
    }
    return c;
  }
*/
  private String getFileType(String name) {
    String ext = FileNameUtil.getFileNameExtension(name);
    if ("prt".equalsIgnoreCase(ext)) return "PRT";
    if ("asm".equalsIgnoreCase(ext)) return "ASY";
    if ("drw".equalsIgnoreCase(ext)) return "DWG";
    return ext.toUpperCase();
  }
  
  protected Collection mapAttributes(Collection metadataList, String agileClassName) throws Exception {
    Metadata data;
    Collection c = new Vector();
    Iterator i = metadataList.iterator();
    while(i.hasNext()) { 
      data = (Metadata) i.next();
      c.add(mapAttributes(data.getMetadataBase(), agileClassName));
    }
    return c;
  }

  protected Metadata mapAttributes(MetadataBase data, String agileClassName) throws Exception {
	  String key;
	  StringPair mapping;
	  MetadataBase m = new MetadataBase();
	  m.setName(data.getName());
	  m.setOrigin(data.getOrigin());
	  Iterator i = data.getAttributes().keySet().iterator(); 
	  Map ilinkMappings = MapUtil.getMapFromMap(hiKLADemo.ilinkMappings, agileClassName);      
	  while (i.hasNext()) {
	    key = i.next().toString();
	    mapping = (StringPair) ilinkMappings.get(key.toLowerCase()); 
	    if (null==mapping) continue;
	    m.set(mapping.getString1(), data.get(key));
	  }
	  
	  mapping=(StringPair)ilinkMappings.get("name");
	  if (null!=mapping) m.set(mapping.getString1(), data.getName());
	  String number = data.get(ATT_AGILE_NUMBER);
	  m.set(ATT_AGILE_CLASS_TYPE, agileClassName);
	  m.set(ATT_AGILE_FILE_TYPE, getFileType(data.getName()));
	  if (AGILE_CLASS_CAD_DOCUMENT.equals(agileClassName)) number += "-doc";
	  m.set(ATT_AGILE_NUMBER, number);
	  return m;
  }

  public Collection findAllParts(Collection metadataList, Map newPartNumbers) throws Exception {
    Collection allParts = new Vector();
    Map partNames = new HashMap();
    Map partNumbers = new HashMap();
    findAllParts(partNames, partNumbers, metadataList, allParts, newPartNumbers);
    return allParts;
  }
  
  public Collection findAllParts(Map partNames, Map partNumbers, Collection metadataList, Collection allParts, Map newPartNumbers) throws Exception {
    String name, number, dupName, dupNumber;
    Metadata data, dupDataName, dupDataNumber;
    Iterator i = metadataList.iterator();    
    while (i.hasNext()) {
      data = (Metadata) i.next();
      name = data.getName();
      number = data.get(ATT_AGILE_NUMBER);
      data.getMetadataBase().set(ATT_ILINK_PART_NUMBER, number);
      /*
      if (null==number || "".equals(number.trim())) {
        number = (String)newPartNumbers.get(name);
        if (null==number || "".equals(number.trim())) {
          number = data.get(ATT_ILINK_PART_NUMBER);
          if (null==number || "".equals(number.trim())) throw new InvalidName(number);
          data.set(ATT_AGILE_NUMBER, number);
        }
        data.getMetadataBase().set(ATT_ILINK_PART_NUMBER, number);
        data.getMetadataBase().set(ATT_AGILE_NUMBER, number);
      }
      */
      dupDataName = (Metadata)partNames.get(name);
      dupDataNumber = (Metadata)partNumbers.get(number);
      if(dupDataName==null && dupDataNumber==null) {
        allParts.add(data);
        partNames.put(name, data);
        partNumbers.put(number, data);
        if (data.hasSubComponents()) findAllParts(partNames, partNumbers, data.getSubComponents(), allParts, newPartNumbers);
      }
      else if (dupDataName!=null && dupDataNumber!=null) {
        dupName = dupDataName.getName();
        dupNumber = dupDataNumber.get(ATT_AGILE_NUMBER);
        if (dupName.equals(name) && dupNumber.equals(number)) continue;
        throw new Duplicate("duplicate name[number] found for:" +name +"["+number+"] =!= " +dupName +"["+dupNumber+"]"); 
      }
      else if (dupDataName!=null ) {
          dupName = dupDataName.getName();
	      throw new Duplicate("duplicate name[number] found for:" +name +"["+number+"] =!= " + dupName +"["+dupDataName.get(ATT_AGILE_NUMBER)+"]"); 
	    }
      else if (dupDataNumber!=null) {
        dupNumber = dupDataNumber.get(ATT_AGILE_NUMBER);
	      throw new Duplicate("duplicate name[number] found for:" +name +"["+number+"] =!= " +dupDataNumber.getName()+"["+dupNumber+"]"); 
	    }
    }
    return allParts;
  }
  
  public String publishWIPold() {
    try {
      Collection c = loadDependencies(getChosenItems());
      Map emptyPartNumbers = findEmptyPartNumbers(c); //throw warning for multiple versions of same object
      Map newPartNumbers = new HashMap();
      Collection loadedPartNumbers=new Vector();
      if (null!=emptyPartNumbers && !emptyPartNumbers.isEmpty()) {
	      Map proposedPartNumbers = proposePartNumbers(emptyPartNumbers);
	      newPartNumbers = agile().establishNewPartNumbers(proposedPartNumbers, AGILE_CLASS_CAD_PART, null);
	      //add -000 to the end of the new partnumbers
	      newPartNumbers = formatNewPartNumbers(emptyPartNumbers, newPartNumbers);
	      PrintUtil.print(newPartNumbers);
	      //update AGILE_NUMBER and ILINK_PART_NUMBER metadata atts
	      loadNewPartNumbers(emptyPartNumbers.values(), newPartNumbers);
	      loadedPartNumbers = new Vector();
	      loadedPartNumbers.addAll(emptyPartNumbers.values());
	      createAgileItems(loadedPartNumbers, false);
      }
      //PrintUtil.print(loadedPartNumbers);
      Collection allParts = findAllParts(c, newPartNumbers);
      Collection agileCADObjects = mapAttributes(allParts, AGILE_CLASS_CAD_PART);
      agileCADObjects.addAll(mapAttributes(allParts, AGILE_CLASS_CAD_DOCUMENT));
      
      //PrintUtil.print(agileCADObjects);
      publishCADAttributes(agileCADObjects);
      PrintUtil.print(c);
      publishCADStructure(c, enableCADDocuments);
      if (null!=emptyPartNumbers && !emptyPartNumbers.isEmpty()) {
        bindIntralinkPartNumbers(loadedPartNumbers);
      }
      //PrintUtil.print(loadedPartNumbers);
      return "wip";
    }
    catch (Exception e) { e.printStackTrace(); return ctrlERROR; }
  }

  //Attachments
  
  public void publishViewableImages(Collection c) {
    Metadata data;
		Iterator i = c.iterator();
		String ws;
		String location;
		String partnumber;
		while(i.hasNext()) {
		  data = (Metadata)i.next();
		  ws=data.getName().replace('.', '_');
		  location = data.getName();
		  partnumber = data.get(ATT_AGILE_NUMBER);
		  if (null==partnumber) {
		      {} //System.out.println("Can not create image for " + data.getName() + ": part number is null");
		      return;
		  }
		  try {
		    IntralinkAccess y = IntralinkAccess.getAccess();
		    Origin o = data.getOrigin();
		    y.createWorkspace(o.getServerName(),o.getDatasourceName(), ws, getAuthentication());
		    y.checkout(o, ws, getAuthentication());	      
		    if ("YES".equalsIgnoreCase(getAttachNativeFile())) {
		     y.export(o, ws, location, getAuthentication());
		     agile().attachFile(location, data.getName(), partnumber);
		    }
		    if ("YES".equalsIgnoreCase(getAttachIGESImage())) {
			      String igesName = y.convertToIGES(o, ws, location, getAuthentication());
			      agile().attachFile(location, igesName, partnumber);
			    }
			    y.destroyWorkspace(o.getServerName(),o.getDatasourceName(), ws, getAuthentication());
			  }
			catch(Exception e) { e.printStackTrace(); }
		}
  }
	  
  
  
  //Standard Library
  //-------------------------------------------
  public String initPublishLibrary() {
    return "wip";
  }
  
  public String publishLibrary() {
    return "wip";
  }

  
  //Work In Progress
  //-------------------------------------------
  public String initPublishWIP() {
    //ChoiceValidator v = new PublishWIPChoiceValidator();
    //setChoiceValidator(v);
    return "wip";
  }
  
  public String initUpdateWIP() {
	  //ChoiceValidator v = new UpdateWIPChoiceValidator();
	  //setChoiceValidator(v);
	  return "wip";
  }
  
  public String initReplaceWIP() {
	  //ChoiceValidator v = new ReplaceWIPChoiceValidator();
	  //setChoiceValidator(v);
	  return "wip";
  }

  public String publishWIP() {
	  /*
	  1) find new items x
	  2) create new items x
	  3) find updated items
	  4) publish new and updated items
	  5) remove latest sync for updated items.
	  6) synchronize updated items with cad part,doc and part number
	  7)publish structure.
	  */
	  try {
	    //load assembly structures
	    
	    Collection c = loadDependencies(getChosenItems());
	    Map parts = new HashMap();
	    Map unchangedParts = new HashMap();
	    Map newParts = new HashMap();
	    Map renamedParts= new HashMap();
	    Map updatedParts = new HashMap();
	    Map lastPublishedOrigins = new HashMap();
	    Map lastPublishedModelOrigins= new HashMap();
	    Map lastPublishedDocumentOrigins= new HashMap();
	    Map lastPublishedPartNumberOrigins= new HashMap();
	    Map undeterminedParts= new HashMap();
	    //walk each structure and categorize all parts  
	    //check for multiple versions of same name in different places in the structure
	    try {
	  	  categorizeChanges(c, parts, unchangedParts, newParts, renamedParts, updatedParts, lastPublishedOrigins, lastPublishedModelOrigins, lastPublishedDocumentOrigins, lastPublishedPartNumberOrigins, undeterminedParts);
	    }
	    catch (NotUpToDate e) {
	      logFormError("err.out.of.date", e.getOldVersion().getUniqueID(), e.getNewVersion().getUniqueID());
	      return ctrlERROR;
	    }
	    if (!undeterminedParts.isEmpty()) {
        {} //System.out.println("Some parts could not be categorized as new, updated, renamed, or unchanged.");
        {} //System.out.println("These will be considered as new parts");
        PrintUtil.print(undeterminedParts);
	    }
	    Map newPartNumbers = new HashMap();
	    Collection loadedPartNumbers=new Vector();
	    if (null!=newParts && !newParts.isEmpty()) {
	      Map proposedPartNumbers = proposePartNumbers(newParts);
	      newPartNumbers = agile().establishNewPartNumbers(proposedPartNumbers, AGILE_CLASS_CAD_PART, null);
	      //add -000 to the end of the new partnumbers
	      newPartNumbers = formatNewPartNumbers(newParts, newPartNumbers);
	      PrintUtil.print(newPartNumbers);
	      //update AGILE_NUMBER and ILINK_PART_NUMBER metadata atts
	      loadNewPartNumbers(newParts.values(), newPartNumbers);
	      loadedPartNumbers = new Vector();
	      loadedPartNumbers.addAll(newParts.values());
	      //create agile objects
	      createAgileItems(loadedPartNumbers, enableCADDocuments);
	    }
	    //PrintUtil.print(loadedPartNumbers);
	    Collection newAndUpdatedParts = new Vector();
	    newAndUpdatedParts.addAll(updatedParts.values());
	    newAndUpdatedParts.addAll(renamedParts.values());
	    newAndUpdatedParts.addAll(loadedPartNumbers);
	    //Map attributes
	    Collection newUpdatedAndRenamedAgileCADObjects = mapAttributes(newAndUpdatedParts, AGILE_CLASS_CAD_PART);
	    if (enableCADDocuments) newUpdatedAndRenamedAgileCADObjects.addAll(mapAttributes(newAndUpdatedParts, AGILE_CLASS_CAD_DOCUMENT));
	    //PrintUtil.print(agileCADObjects);
	    //publish attributes
	    publishCADAttributes(newUpdatedAndRenamedAgileCADObjects);
	    //for updated parts 
	    // - break bindings to previously published ilink origins 
	    // - rebind partnumber, CADPart, CADDoc Origins to the updated ilink origin.  
	    resynchronize(updatedParts, lastPublishedOrigins, lastPublishedModelOrigins, lastPublishedDocumentOrigins, lastPublishedPartNumberOrigins);
	    
	    PrintUtil.print(c);
	    //update all structures
	    
	    numberParts(c, parts);
	    publishCADStructure(c, enableCADDocuments);  //update to link
	    if ("YES".equalsIgnoreCase(getAttachNativeFile()) || "YES".equalsIgnoreCase(getAttachIGESImage())) publishViewableImages(c);	  
	
	    if (null!=newParts && !newParts.isEmpty()) {
	      bindIntralinkPartNumbers(loadedPartNumbers); 
	    }
	    //PrintUtil.print(loadedPartNumbers);
	    return "wip";
	  }
	  catch (Exception e) { e.printStackTrace(); return ctrlERROR; }
  }
  
  public String updateWIP() {
    return publishWIP();
  }
  
  public String replaceWIP() {
    try {
	    KLAMetadataAdapter a; 
	    Iterator i = getChosenItems().iterator();
	    Collection replacements = new Vector();
	    Pair replacementPair;
	    while (i.hasNext()) {
	      a = (KLAMetadataAdapter)i.next();
	      replacementPair = a.incrementBasePartNumber();
	      replacements.add(replacementPair);
	    }
	    Collection metadataList = new Vector();
	    i = getChosenItems().iterator();
	    Metadata data;
	    while(i.hasNext()) {
	      data = (Metadata) i.next();
	      metadataList.add(data.getMetadataBase());
	    }
      createAgileItems(metadataList, enableCADDocuments);
	    publishWIP();
	    //!!!Intralink Bug !!!!!!!!
	    //!!!Updating Life Cycle Attribute in commonspace for second time on the later versin of a part 
	    //!!!will delete that version of the part in intralink!!!!!!!!!!!!!!!!!!!!!!! 
      //!!!bindIntralinkPartNumbers(metadataList); 
	    PrintUtil.print(replacements);
      replaceAgileItems(replacements);
	    return "wip";
    }
    catch(Exception e) { e.printStackTrace(); return ctrlERROR; }
  }
  
  public String replaceWIPold() {
    try {
	    KLAMetadataAdapter a; 
	    Iterator i = getChosenItems().iterator();
	    Collection replacements = new Vector();
	    Pair replacementPair;
	    while (i.hasNext()) {
	      a = (KLAMetadataAdapter)i.next();
	      replacementPair = a.incrementBasePartNumber();
	      replacements.add(replacementPair);
	    }
	    Collection metadataList = new Vector();
	    i = getChosenItems().iterator();
	    Metadata data;
	    while(i.hasNext()) {
	      data = (Metadata) i.next();
	      metadataList.add(data.getMetadataBase());
	    }
      createAgileItems(metadataList, false);
	    publishWIP();
      //bindIntralinkPartNumbers(metadataList); //!!!!!!!!this seems to delete the updated verion in intralink!!!!
	    PrintUtil.print(replacements);
      replaceAgileItems(replacements);
	    return "wip";
    }
    catch(Exception e) { e.printStackTrace(); return ctrlERROR; }
  }

  public String initRelease2Prototype() {
    return "eco";
  }

  
  
  
  
  
  
  
  
  public String revUpdate() {
    //+++for initial items, just publishWIP
    createNewECO("Intralink ECO", "Intralink ECO Auto Release");
    return orderRevUpdate();
  }
    
  public String orderRevUpdate() {
    ECO eco=getSelectedECO();
    {} //System.out.println("Updating to ECO: " + eco.getNumber());
	  /*
	  1) find new items x
	  2) create new items x
	  3) find updated items
	  4) create eco
	  5) mark new and updated items as affected items on eco 
	  6) publish new and updated items (updates attributes)
	  7) remove latest sync for updated items.
	  8) synchronize updated items with cad part,doc and part number
	  9) redline structure on the eco
	  */
	  
	  try {
	    //load assembly structures
	    
	    Collection c = loadDependencies(getChosenItems());
	    Map parts = new HashMap();
	    Map unchangedParts = new HashMap();
	    Map newParts = new HashMap();
	    Map renamedParts= new HashMap();
	    Map updatedParts = new HashMap();
	    Map lastPublishedOrigins = new HashMap();
	    Map lastPublishedModelOrigins= new HashMap();
	    Map lastPublishedDocumentOrigins= new HashMap();
	    Map lastPublishedPartNumberOrigins= new HashMap();
	    Map undeterminedParts= new HashMap();
	    //walk each structure and categorize all parts  
	    //check for multiple versions of same name in different places in the structure
	    try {
	  	  categorizeChanges(c, parts, unchangedParts, newParts, renamedParts, updatedParts, lastPublishedOrigins, lastPublishedModelOrigins, lastPublishedDocumentOrigins, lastPublishedPartNumberOrigins, undeterminedParts);
	    }
	    catch (NotUpToDate e) {
	      logFormError("err.out.of.date", e.getOldVersion().getUniqueID(), e.getNewVersion().getUniqueID());
	      return ctrlERROR;
	    }
	    if (!undeterminedParts.isEmpty()) {
        {} //System.out.println("Some parts could not be categorized as new, updated, renamed, or unchanged.");
        {} //System.out.println("These will be considered as new parts");
        PrintUtil.print(undeterminedParts);
	    }
	    Map newPartNumbers = new HashMap();
	    Collection loadedPartNumbers=new Vector();
	    if (null!=newParts && !newParts.isEmpty()) {
	      Map proposedPartNumbers = proposePartNumbers(newParts);
	      newPartNumbers = agile().establishNewPartNumbers(proposedPartNumbers, AGILE_CLASS_CAD_PART, null);
	      //add -000 to the end of the new partnumbers
	      newPartNumbers = formatNewPartNumbers(newParts, newPartNumbers);
	      PrintUtil.print(newPartNumbers);
	      //update AGILE_NUMBER and ILINK_PART_NUMBER metadata atts
	      loadNewPartNumbers(newParts.values(), newPartNumbers);
	      loadedPartNumbers = new Vector();
	      loadedPartNumbers.addAll(newParts.values());
	      //create agile objects
	      createAgileItems(loadedPartNumbers, enableCADDocuments);
	    }
	    //PrintUtil.print(loadedPartNumbers);
	    Collection newAndUpdatedParts = new Vector();
	    newAndUpdatedParts.addAll(updatedParts.values());
	    newAndUpdatedParts.addAll(renamedParts.values());
	    newAndUpdatedParts.addAll(loadedPartNumbers);
	    //Map attributes
	    Collection newUpdatedAndRenamedAgileCADObjects = mapAttributes(newAndUpdatedParts, AGILE_CLASS_CAD_PART);
	    if (enableCADDocuments) newUpdatedAndRenamedAgileCADObjects.addAll(mapAttributes(newAndUpdatedParts, AGILE_CLASS_CAD_DOCUMENT));
	    //PrintUtil.print(agileCADObjects);
	    //publish attributes

	    Collection changedItems = new Vector();
		  AffectedItem changedItem = null;
	    Iterator a = newUpdatedAndRenamedAgileCADObjects.iterator();
		  Metadata m;
	    while (a.hasNext()) {
		    m = (Metadata) a.next();
			  {} //System.out.println(m.getMetadataBase().get(ATT_AGILE_NUMBER));
		    changedItem = new AffectedItem();
			  changedItem.setItemNumber(m.getMetadataBase().get(ATT_AGILE_NUMBER));
	      changedItems.add(changedItem);
	    }
	    
	    agile().orderRevUpdate(eco.getNumber(), changedItems, getAuthentication());
	    selectedECO=agile().findECO(getSelectedECO().getNumber());

	    publishCADAttributes(newUpdatedAndRenamedAgileCADObjects);
	    //for updated parts
	    // - break bindings to previously published ilink origins
	    // - rebind partnumber, CADPart, CADDoc Origins to the updated ilink origin.
	    resynchronize(updatedParts, lastPublishedOrigins, lastPublishedModelOrigins, lastPublishedDocumentOrigins, lastPublishedPartNumberOrigins);

	    PrintUtil.print(c);
	    //update all structures
	    
	    numberParts(c, parts);
	    redlineCADStructure(eco.getNumber(), c, enableCADDocuments);  //update to link
	    
	    if ("YES".equalsIgnoreCase(getAttachNativeFile()) || "YES".equalsIgnoreCase(getAttachIGESImage())) publishViewableImages(c);	  
	    agile().moveToNextStatus(eco.getNumber(), getAuthentication());
	    if (null!=newParts && !newParts.isEmpty()) {
	      bindIntralinkPartNumbers(loadedPartNumbers); 
	    }
	    //PrintUtil.print(loadedPartNumbers);
	  }
	  catch (Exception e) { e.printStackTrace(); return ctrlERROR; }
    return ctrlOK;
  }

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  public void release2LifeCyclePhase(String lifeCyclePhase) {
	  AffectedItem changedItem = new AffectedItem();
	  KLAMetadataAdapter m = (KLAMetadataAdapter)getChosenItems().iterator().next();
	  changedItem.setItemNumber(m.getMetadataBase().get(ATT_AGILE_NUMBER));
	  try {
	    Collection changedItems = new Vector();
	    changedItems.add(changedItem);
	    agile().orderRelease(getSelectedECO().getNumber(), changedItems, lifeCyclePhase, getLCO());
	    selectedECO=agile().findECO(getSelectedECO().getNumber());
	    broadcastPromote(getSelectedECO().getNumber());
	  }
	  catch (Exception e) { e.printStackTrace(); }	  
	  Iterator i = getChosenItems().iterator();
	  KLAMetadataAdapter data;
	  Collection c = new Vector();
	  while (i.hasNext()) {
	    data = (KLAMetadataAdapter)i.next();
	    {} //System.out.println(data);
	    {} //System.out.println("CAD Doc Name="+data.getCADDocumentName());
	  }
  }

  private String broadcastPromote(String ecoNumber) {
	 ECO eco;
	 try { eco = agile().findECO(ecoNumber); }
	 catch (Exception e) {e.printStackTrace(); return this.ctrlERR_EVENT_ERROR; }
	 updateECOStatus(eco);
	 return ctrlEVENT_FIRED;
	}
  
  private void updateECOStatus(ECO eco) {
	  Metadata agileData = null;
	  hiEventService form = new hiEventService();
	  Origin source = null;
	  AffectedItem item = null;
	  Iterator i = eco.getAffectedItems().iterator();
	  try {
	 	  IntralinkAccess ilink = IntralinkAccess.getAccess();
		  AgileAccess agileClient = AgileAccess.getAccess("DesignState-node-0", "agile", getAuthentication());
		  while (i.hasNext()) {
		    item = (AffectedItem) i.next();
		    try { 
		 	   agileData = agileClient.find(item.getItemNumber());
		 	   source = findSourceOrigin(agileData);
			   form.updatePLMStatus(item, eco, source);
			   {} //System.out.println("Locking to ECO Pending");
			   ilink.lock(source, getAuthentication());
			   {} //System.out.println("Locked");
			   ilink.promote(source, "ECO Pending", getAuthentication());
			   {} //System.out.println("Promoted");
			 }
			 catch (Exception e) { {} //System.out.println(e.getMessage()); }
			   
			 }
		 }
	  }
	  catch(Exception e) { e.printStackTrace(); }
	}

  
  
  
  
  
  
  
  
  
  
  public String promote() {
    if (null==getChosenItems()) return ctrlOK;
    Iterator i = getChosenItems().iterator();
    if (!i.hasNext()) return ctrlOK;
    Metadata m = (Metadata)i.next();    
	  String lifeCyclePhase = m.get("Release-Level");
	  System.out.print("Promoting from " + lifeCyclePhase);	  
	  lifeCyclePhase = getLCO().getPhaseAfter(lifeCyclePhase);
	  System.out.print("Promoting to " + lifeCyclePhase);
	  if (null==lifeCyclePhase) return ctrlOK;
	  release2LifeCyclePhase(lifeCyclePhase);
	  return ctrlOK;
	}
  
  public String release2Prototype() {
    String lifeCyclePhase = "Prototype";
    release2LifeCyclePhase(lifeCyclePhase);
	  return ctrlOK;
  }

  public String initPrototypeChange() {
	  return "eco";
	}
	
	public String prototypeChange() {
	  return "eco";
	}
	
	public String initPrototypeReplace() {
	  return "eco";
	}
	
	public String prototypeReplace() {
	  return "eco";
	}
	
  public String initRelease2Pilot() {
    return "eco";
  }
  
  public String release2Pilot() {
    return "eco";
  }
  
  public String initPilotChange() {
	  return "eco";
	}
	
	public String pilotChange() {
	  return "eco";
	}
	
	public String initPilotReplace() {
	  return "eco";
	}
	
	public String pilotReplace() {
	  return "eco";
	}
	
  public String initRelease2Production() {
    return "eco";
  }
  
  public String release2Production() {
    return "eco";
  }
  
  public String initProductionChange() {
    return "eco";
  }
  
  public String productionChange() {
    return "eco";
  }
  
  public String initProductionReplace() {
    return "eco";
  }
  
  public String productionReplace() {
    return "eco";
  }
  
  public String initDeactivate() {
    return "eco";
  }
  
  public String deactivate() {
    return "eco";
  }
  
  public String initMarkObsolete() {
    return "eco";
  }
  
  public String markObsolete() {
    return "eco";
  }

  public void render() { 
    synchronizeMetadata(getItems()); 
    synchronizeMetadata(getChosenItems()); 
  }

  public LifeCycleOrder getLCO() {
    if (null==lifeCycleOder) {
      lifeCycleOder = new LifeCycleOrder();
      lifeCycleOder.add("Preliminary");
      lifeCycleOder.add("Prototype");
      lifeCycleOder.add("Pilot");
      lifeCycleOder.add("Production");
      lifeCycleOder.add("Obsolete");
      lifeCycleOder.add("Inactive");
    }
    return lifeCycleOder;
  }

  private AgileAccess agile() throws Exception {
	  if (null==agileAccess) agileAccess = zws.AgileAccess.getAccess("DesignState-node-0", Properties.get(Names.DEFAULT_AGILE_SOURCE), getAuthentication());
	  return agileAccess;
	}
  
  private String ECONumberCriteria= null;
  private ECO selectedECO=null;
  private Collection ECOResults;
  
  private static LifeCycleOrder lifeCycleOder = null;
  
  private int viewState=STATE_ECO;
  private static int STATE_ECO=0;
  private static int STATE_ADD_CHANGES=1;
  
  private transient Synchronizer synkService = Synchronizer.getClient("DesignState-node-0");
  
  //private static String ATT_ILINK_PART_NUMBER="Part_Number";
  //private static String ATT_AGILE_NUMBER = "number";
  //private static String ATT_AGILE_CLASS_TYPE = "agile-class";
  //private static String ATT_AGILE_FILE_TYPE = "CAD-File-Type";
  //private static String AGILE_CLASS_CAD_PART = "CAD Part";
  //private static String AGILE_CLASS_CAD_DOCUMENT = "CAD Document";
  AgileAccess agileAccess = null;
}
