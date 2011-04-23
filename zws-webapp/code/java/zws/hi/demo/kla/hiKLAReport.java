package zws.hi.demo.kla; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Apr 12, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.Synchronizer;
import zws.data.*;
import zws.exception.*;
import zws.exception.InvalidName;
import zws.origin.*;
import zws.application.Properties;
import zws.AgileAccess;
import zws.IntralinkAccess;
import zws.application.server.webapp.Names; 
import zws.bill.intralink.BillOfMaterials;
import zws.hi.report.MetadataAdapter;
import zws.hi.report.hiReport;
import zws.synchronization.SynchronizationRecord;
import zws.util.MapUtil;
import zws.util.StringPair;
import zws.util.comparator.metadata.PartNumberOrder;

import java.util.*;

import zws.hi.demo.kla.choices.ChoiceValidator;

public class hiKLAReport extends hiReport {
  public String getSelectedReportName() { return Properties.get("demo-report-KLA"); }
  protected MetadataAdapter createNewMetadataAdapter() { return new KLAMetadataAdapter(); }

  public boolean removeItemWhenChosen() { return true; }

  public Comparator getComparator() {
	  PartNumberOrder c = new PartNumberOrder();
	  String sortKeyFields=Properties.get(Names.SORT_KEY_FIELDS);
	  if (sortKeyFields==null || "".equals(sortKeyFields.trim())) return null;
	  c.setKeyFields(sortKeyFields);
	  return c; 
	}
	    
  public boolean idChoosesItem(String id, Object item) {
    KLAMetadataAdapter adapter = (KLAMetadataAdapter) item;
    String o = adapter.getOrigin().toString();
    if(!id.equals(o)) return false;
    if (null==chosenItemValidator) return true;
    return chosenItemValidator.isValidChoice(adapter, this);
  }
    
  public boolean isChosen(Object o) {
    Origin origin = ((MetadataAdapter)o).getOrigin();
    Iterator i = getChosenItems().iterator();
    while (i.hasNext()) if( ((MetadataAdapter)i.next()).getOrigin().isTheSameAs(origin)) return true;
    return false;
  }
  
  public void createAgileItems(Collection newParts, boolean createCADDocuments) throws Exception {
    agile().createCADParts(newParts, createCADDocuments);
    Iterator i = newParts.iterator();
    IntralinkOrigin o,p;
    Metadata data;
    String partnumber;
    while (i.hasNext()) {
      data = (Metadata) i.next();
      o = (IntralinkOrigin)data.getOrigin();
      partnumber = data.getMetadataBase().get(ATT_AGILE_NUMBER);
      p = OriginMaker.materializeIntralinkPartNumberOrigin(o, partnumber);
      sync.record(o,p);
    }
  }
  
  public void replaceAgileItems(Collection replacements) throws Exception {
    agile().replace(replacements);
  }
  
  public void publishCADAttributes(Collection metadataList) throws Exception {
    agile().update(metadataList);
  }

  public void redlineCADStructure(Collection metadataList, boolean createCADDocuments) throws Exception {
    //+++++ ahhhh, todo.
  }
  
  public void publishCADStructure(Collection metadataList, boolean createCADDocuments) throws Exception {
    Collection allData = new Vector();
    Collection cadParts = createCADStructure(metadataList, false);
    allData.addAll(cadParts);
    if (createCADDocuments) {
      Collection cadDocuments = createCADStructure(metadataList, true);
      allData.addAll(cadDocuments);
    }
    agile().structureBill(allData);
  }

  
  public void redlineCADStructure(String ecoNumber, Collection metadataList, boolean createCADDocuments) throws Exception {
    Collection allData = new Vector();
    Collection cadParts = createCADStructure(metadataList, false);
    allData.addAll(cadParts);
    if (createCADDocuments) {
      Collection cadDocuments = createCADStructure(metadataList, true);
      allData.addAll(cadDocuments);
    }
    agile().redlineStructure(ecoNumber, allData);
  }

  private Collection createCADStructure(Collection c, boolean isCADDocument) {
    Collection parts = new Vector();
    Iterator i = c.iterator();
    Metadata parentData;
    MetadataBase parentPart;
    while(i.hasNext()) {
      parentData = (Metadata) i.next();
      parentPart = new MetadataBase();
      createCADStructure(parentData, parentPart, isCADDocument, enableCADDocuments);
      parts.add(parentPart);
    }
    return parts;
  }

  private void createCADStructure(Metadata data, MetadataBase parentPart, boolean isCADDocument, boolean linkCADDocument) {
    //parentPart.setOrigin(data.getOrigin());
    if  (isCADDocument) {
      parentPart.set(ATT_AGILE_CLASS_TYPE, AGILE_CLASS_CAD_DOCUMENT);
      parentPart.set(ATT_AGILE_NUMBER, data.get(ATT_AGILE_NUMBER)+"-doc");
    }
    else {
      parentPart.set(ATT_AGILE_CLASS_TYPE, AGILE_CLASS_CAD_PART);
      parentPart.set(ATT_AGILE_NUMBER, data.get(ATT_AGILE_NUMBER));
      MetadataBase docBase= new MetadataBase();
      //parentPart.setOrigin(data.getOrigin());
      if (linkCADDocument) {
        docBase.set(ATT_AGILE_CLASS_TYPE, AGILE_CLASS_CAD_DOCUMENT);
        docBase.set(ATT_AGILE_NUMBER, data.get(ATT_AGILE_NUMBER)+"-doc");
        MetadataSubComponentBase docSub = new MetadataSubComponentBase(docBase);
        parentPart.addSubComponent(docSub);
      }
    }
    if (!data.hasSubComponents()) return;
    Iterator i = data.getSubComponents().iterator();
    MetadataSubComponent kid;
    MetadataBase partBase;
    MetadataSubComponentBase sub;
    while (i.hasNext()) {
      kid = (MetadataSubComponent) i.next();
      partBase = new MetadataBase();
      createCADStructure(kid, partBase, isCADDocument, linkCADDocument);
      sub = new MetadataSubComponentBase(partBase);
      sub.setQuantity(kid.getQuantity());
      parentPart.addSubComponent(sub);
    }
  }
  
  public void bindIntralinkPartNumbers(Collection metadataList) throws Exception {
    IntralinkAccess y = IntralinkAccess.getAccess();
    y.setLifeCycleAttributes(metadataList, "number", "Agile_PLM_Status", getAuthentication());
  }

  public void unsetIntralinkPartNumbers(Collection metadataList) throws Exception {
    IntralinkAccess y = IntralinkAccess.getAccess();
    y.unsetLifeCycleAttributes(metadataList, "Agile_PLM_Status", getAuthentication());
  }

  public void createChosenItems(String agileClassName) throws Exception {
    Collection c = new Vector();
    Iterator i = getChosenItems().iterator();
    KLAMetadataAdapter data;
    Collection renumberedData = new Vector();
    //do not create items that are already synchronized
    while(i.hasNext()) {
      data = (KLAMetadataAdapter) i.next();
      if(data.getIsRenamed()) { renameInSyncLog(data); }
      if(data.getIsRenumbered()) { renumberedData.add(data); }
      c.add(data);
      //if (agileClassName.equals("CAD Document") && null == data.getCADDocumentOrigin()) c.add(data);
      //else if (agileClassName.equals("CAD Part") && null == data.getCADModelOrigin()) c.add(data);
    }
    //--x.create(map(c, agileClassName));
    //--renumberInAgile(x, renumberedData, agileClassName);
    Collection agileItems = map(c, agileClassName); 
    if ("YES".equalsIgnoreCase(createNonExistingParts)) agile().create(agileItems);
    else agile().update(agileItems);

    if (agileClassName.equals("CAD Part") && ("YES".equalsIgnoreCase(getAttachNativeFile()) || "YES".equalsIgnoreCase(getAttachIGESImage()))) {    
      Map agileMappings = MapUtil.getMapFromMap(hiKLADemo.agileMappings, agileClassName);
      StringPair mapping=(StringPair)agileMappings.get("number");
      String pnAttribute = "name";
      if (null!=mapping) pnAttribute = mapping.getString0();
        
	    i = getChosenItems().iterator();
	    String ws;
	    String location;
	    String partnumber;
	    while(i.hasNext()) {
        data = (KLAMetadataAdapter)i.next();
        partnumber = data.get(pnAttribute);
	      ws=data.getName().replace('.', '_');
	      location = data.getName();
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
	}

  public void renameInSyncLog(KLAMetadataAdapter data) throws Exception {
    String newName;
    Origin old = null;// lookup old origin from synclog using KLA origin
    Origin h = data.getOrigin();
    Collection c = sync.findMatches(h.getDomainName(), h.getServerName(), h.getDatasourceName(), h.getName());
    if (null==c) return;
    Iterator i = c.iterator();
    Origin o;
    while(old==null && i.hasNext()) {
      o = (Origin)i.next();
      if (o.getDatasourceType().equals(Origin.FROM_ILINK) && !o.getName().equals(data.getOrigin().getName())) old = o;
    }
    if (null==old) return;
    newName = data.getName();    
    sync.rename(old.getDomainName(), old.getServerName(), old.getDatasourceName(), old.getName(), newName);
  }

  public void renumberInAgile(AgileAccess x, Collection renumberedData, String agileClassName) throws Exception{
    Iterator i = renumberedData.iterator();
    KLAMetadataAdapter data;
    String oldNumber=null;
    String newNumber=null;
    while(i.hasNext()) {
      data = (KLAMetadataAdapter)i.next();
      oldNumber = data.getOldPartNumber(); //;find old number using last ilink origin->last KLA origin
      newNumber = data.getPartNumberAttribute();
      if (agileClassName.equals("CAD Document")) {
        oldNumber += "-doc";
        newNumber += "-doc";
      }
      x.replace(oldNumber, newNumber);
    }
  }

  public Collection loadBills(Collection metadataList, String agileClassName) throws Exception {
    BillOfMaterials bill=null;
    Collection bills = new Vector();
    IntralinkAccess y = IntralinkAccess.getAccess();
    Metadata data;
    Iterator i = metadataList.iterator();
    while (i.hasNext()) {
      data = (Metadata) i.next();
      if (data.hasSubComponents()) {
          bill = new BillOfMaterials(data);
      }
      else if (isAssembly(data)) {
        bill = y.reportBill(data.getOrigin(), getAuthentication());
      }
      else if (isDrawing(data)) {
          continue;
          //bill = y.reportDrawingModels(data.getOrigin(), getAuthentication());
      }
      else if (agileClassName.equals("CAD Document")) {
        continue;
      }
      else if (agileClassName.equals("CAD Part")) {
        bill = new BillOfMaterials(data);
      }
      bills.add(bill);
    }
    return bills;
  }

  public Collection linkKLAModelToDocument(Collection bills) throws Exception {
	  BillOfMaterials bill;
	  Collection linkedBills = new Vector();
	  Iterator i = bills.iterator();
	  while (i.hasNext()) {
	    bill = (BillOfMaterials) i.next();
	    linkKLAModelToDocument(bill);
	    linkedBills.add(bill);
	  }
	  return linkedBills;
  }
  
  public void linkKLAModelToDocument(BillOfMaterials bill) throws Exception {
    linkKLAModelToDocument(bill.getMetadata());
  }
  
  public void linkKLAModelToDocument(Metadata data) throws Exception {
    MetadataBase doc = new MetadataBase();
    doc.set("number", data.get("number")+"-doc");
    doc.setName(doc.get("number"));
    doc.set(ATT_AGILE_CLASS_TYPE, "");
    MetadataSubComponentBase sub = new MetadataSubComponentBase(doc);
    if (data.hasSubComponents()) {
      Iterator i = data.getSubComponents().iterator();
      Metadata kid;
      while (i.hasNext()) {
      	kid = (Metadata) i.next();
      	linkKLAModelToDocument(kid);
      }
    }
    data.addSubComponent(sub);
  }
  
  public Collection mapBills(Collection bills, String agileClassName) throws Exception {
	  BillOfMaterials bill;
	  Collection mappedBills = new Vector();
	  Iterator i = bills.iterator();
	  while (i.hasNext()) {
	    bill = (BillOfMaterials) i.next();
	    bill = map(bill, agileClassName);
	    mappedBills.add(bill);
	  }
	  return mappedBills;
	}    
  
  public void storeBills(Collection bills) throws Exception {
    if (null==bills || bills.size()==0) return;
    agile().storeBill(bills);
	}
  
  public void deleteChosenItems() throws Exception{
	  Collection c;
	  Iterator j;
	  SynchronizationRecord r;
    Iterator i = getChosenItems().iterator();
    Origin o;
    KLAMetadataAdapter data;
    MetadataBase mappedData;
    while (i.hasNext()) {
      data = (KLAMetadataAdapter) i.next();
      if (data.getIsRenamed() || data.getIsRenumbered()) continue;
      //if (data.getCADDocumentOrigin()!=null && data.getCADModelOrigin()!=null) {
        mappedData = new MetadataBase();
        mappedData.set(ATT_AGILE_NUMBER, data.getCADModelName());
        agile().delete(mappedData);
        if (enableCADDocuments) {
          mappedData.set(ATT_AGILE_NUMBER, data.getCADModelName()+"-doc");
          agile().delete(mappedData);
        }
        o = data.getOrigin();
        sync.purgeMatches(data.getOrigin());
        /*
        sync.purgeByUID(o.getDomainName(), o.getServerName(), o.getDatasourceName(), o.getUniqueID());
        o = data.getCADModelOrigin();
        sync.purgeByUID(o.getDomainName(), o.getServerName(), o.getDatasourceName(), o.getUniqueID());
        o = data.getCADDocumentOrigin();
        sync.purgeByUID(o.getDomainName(), o.getServerName(), o.getDatasourceName(), o.getUniqueID());
        */
    }
    IntralinkAccess y = IntralinkAccess.getAccess();
    y.unsetLifeCycleAttributes(getChosenItems(), ATT_ILINK_PART_NUMBER, getAuthentication());    
	}  

  protected Metadata map(Metadata data, String agileClassName) throws Exception {
    MetadataBase m = mapMetadata(data, agileClassName);
    m.setOrigin(data.getOrigin());
    if (!data.hasSubComponents()) return m;

    MetadataSubComponent kid;
    Iterator i = data.getSubComponents().iterator();
    while (i.hasNext()) {
      kid = (MetadataSubComponent) i.next();
      //+++Hack - exclude list is used only for parts (need to make an exclude list for each doc type
      if (agileClassName.equals("CAD Part") && hiKLADemo.isExcluded(kid.getName())) continue;
      {} //System.out.println("Mapping subcomponent: " + kid.getName() + "["+kid.getQuantity()+"]");
      m.addSubComponent(mapSubComponent(kid, agileClassName));
    }
    return m;
  }

  private MetadataSubComponent mapSubComponent(MetadataSubComponent kid, String agileClassName) throws Exception {
    Metadata m = map(kid, agileClassName);
    MetadataSubComponentBase sub = new MetadataSubComponentBase(m);
    sub.setQuantity(kid.getQuantity());
    return sub;
  }
  
  protected MetadataBase mapMetadata(Metadata data, String agileClassName) throws Exception {
      MetadataBase m = new MetadataBase();
      m.set(ATT_AGILE_CLASS_TYPE, agileClassName);
      //synchronizeKLAOrigin(data);
      Iterator i = data.getAttributes().keySet().iterator(); 
      String key;
      StringPair mapping;
      Map ilinkMappings = MapUtil.getMapFromMap(hiKLADemo.ilinkMappings, agileClassName);      
      mapping=(StringPair)ilinkMappings.get("name");
      if (null!=mapping) m.set(mapping.getString1(), data.getName());
      while (i.hasNext()) {
        key = i.next().toString();
        mapping = (StringPair) ilinkMappings.get(key.toLowerCase()); 
        if (null==mapping) continue;
        m.set(mapping.getString1(), data.get(key));
      }
      if (null==m.get("number") || "".equals(m.get("number").trim())) {
        autoNumber(data.getOrigin(), m);
      }
      if (agileClassName.toLowerCase().indexOf("document") >=0) {
          m.set("number", m.get("number")+"-doc");
          m.setName(m.get("number"));
      }
      return m;
    }

  
  private static Map autoNumberedItems = new HashMap();
  
  public void autoNumber(Origin source, MetadataBase data) throws Exception {
    String number = (String) autoNumberedItems.get(source.toString());
    if (null==number || "".equals(number.trim())) {
      number = generateAutoNumber(data);
      number += "-000";
      autoNumberedItems.put(source.toString(), number);
      IntralinkAccess y = IntralinkAccess.getAccess();
      y.setLifeCycleAttribute(source, "KLA_Number", number, getAuthentication());
    }
    else {} //System.out.println("Auto number "+number+" already generated for " + source.toString());
    data.set("number", number);
  }
  
  public String generateAutoNumber(Metadata data) throws Exception {
	  String number= generateNextPartNumberBase();
	  {} //System.out.println("Generated auto number " + number + " for " + data.getOrigin());
	  return number;
	}
    
  public String generateNextPartNumberBase() throws Exception {
		String number = agile().generateNextPartNumber("CAD Part", null);
		return number;
	}
  
	protected Collection loadDependencies(Collection metadataList) throws Exception {
    Collection c;
	  Metadata data;
    MetadataAdapter adapter;
	  c = new Vector();
	  Iterator i = metadataList.iterator();
	  while (i.hasNext()) {
	    adapter = (MetadataAdapter)i.next();
	    data=adapter.getMetadataBase();
	    if (!hiKLADemo.isExcluded(adapter.getName())) {
	      if (isAssembly(adapter)) data = loadSubComponents(adapter); 
	      c.add(data);
	    }
	  }
	  return c;
	}
	
	
	
  protected void resynchronize(Map updatedParts, Map lastPublishedOrigins, Map lastPublishedModelOrigins, Map lastPublishedDocumentOrigins, Map lastPublishedPartNumberOrigins) throws Exception {
    String name, partnumber;
    Metadata data;
    IntralinkOrigin o;
    Origin modelOrigin, docOrigin, partnumberOrigin, lastPublish;
    Iterator i = updatedParts.values().iterator();
    while (i.hasNext()) {
      data = (Metadata) i.next();
      name = data.getName();
      o = (IntralinkOrigin)data.getOrigin();
      modelOrigin = (Origin)lastPublishedModelOrigins.get(name);
      docOrigin = (Origin)lastPublishedDocumentOrigins.get(name);
      partnumberOrigin = (Origin)lastPublishedPartNumberOrigins.get(name);
      partnumber = partnumberOrigin.getName();
      lastPublish = (Origin)lastPublishedOrigins.get(name);
      if (null!=lastPublish)sync.purgeByUID(lastPublish.getDomainName(), lastPublish.getServerName(), lastPublish.getDatasourceName(), lastPublish.getUniqueID());
      if (null!=modelOrigin)sync.purgeByUID(modelOrigin.getDomainName(), modelOrigin.getServerName(), modelOrigin.getDatasourceName(), modelOrigin.getUniqueID());
      if (null!=docOrigin && enableCADDocuments) sync.purgeByUID(docOrigin.getDomainName(), docOrigin.getServerName(), docOrigin.getDatasourceName(), docOrigin.getUniqueID());
      if (null!=partnumberOrigin) sync.purgeByUID(partnumberOrigin.getDomainName(), partnumberOrigin.getServerName(), partnumberOrigin.getDatasourceName(), partnumberOrigin.getUniqueID());
      if (null!=modelOrigin)sync.record(o, modelOrigin);
      if (null!=docOrigin && enableCADDocuments) sync.record(o, docOrigin);
      partnumberOrigin = OriginMaker.materializeIntralinkPartNumberOrigin(o, partnumber);
      sync.record(o, partnumberOrigin);
    }
    
  }

  protected void numberPart(Map parts, Metadata data) throws Exception{
    if (hiKLADemo.isExcluded(data.getName())) return;
    String number;
	  Metadata part = (Metadata)parts.get(data.getName());
	  if (null==part) throw new InvalidName(data.getName());
	  number = part.getMetadataBase().get(ATT_AGILE_NUMBER);
	  if (null==number) throw new NameNotFound(data.getName());
	  data.getMetadataBase().set(ATT_AGILE_NUMBER, number);
	  if (!data.hasSubComponents()) return;
	  MetadataSubComponent kid;
	  Iterator i = data.getSubComponents().iterator();
	  while (i.hasNext()) {
	    kid = (MetadataSubComponent) i.next();
	    numberPart(parts, kid);
	  }
	}

  protected void numberParts(Collection metadataList, Map parts) throws Exception {
	  Iterator i=metadataList.iterator();
	  Metadata data;
	  while (i.hasNext()) {
	    data = (Metadata)i.next();
	    numberPart(parts, data);
	  }
  }

  protected void mapPartNames(Map parts, Metadata data) throws Exception{
    if (hiKLADemo.isExcluded(data.getName())) return;
    Metadata dup = (Metadata)parts.get(data.getName());
    if (null==dup) parts.put(data.getName(), data);
    else if (!data.getOrigin().getUniqueID().equals(dup.getOrigin().getUniqueID())) throw new Exception("Multiple Versions of same object: " + data.getOrigin() + " <<!!>> " + dup.getOrigin());
    if (!data.hasSubComponents()) return;
    MetadataSubComponent kid;
    Iterator i = data.getSubComponents().iterator();
    while (i.hasNext()) {
      kid = (MetadataSubComponent) i.next();
      mapPartNames(parts, kid);
    }
  }
  
  
  public IntralinkOrigin findPastNamedOrigin(IntralinkOrigin o, String partnumber){
    Origin x=null;
    IntralinkOrigin pastName=null;
    try {
      IlinkPartNumberOrigin partnumberOrigin = (IlinkPartNumberOrigin) sync.lastSynchronization(o.getDomainName(),o.getServerName(), o.getDatasourceName(), partnumber);
      if (null==partnumberOrigin) return null;
	    Collection c = sync.findMatches(partnumberOrigin.getDomainName(), partnumberOrigin.getServerName(), partnumberOrigin.getDatasourceName(), partnumberOrigin.getName());
	    if (null==c || c.size()==0) return null;
	    Iterator i = c.iterator();
	    while(i.hasNext()) {
	      x = (Origin)i.next();
	      if (x.getDatasourceType().equals(Origin.FROM_ILINK)) pastName = (IntralinkOrigin)x;
	    }
	    return pastName;
    }
    catch(Exception e) { e.printStackTrace(); return null; }
  }
  
  
  
	protected void categorizeChanges(Collection metadataList, Map parts, Map unchangedParts, Map newParts, Map renamedParts, Map updatedParts, Map lastPublishedOrigins, Map lastPublishedModelOrigins, Map lastPublishedDocumentOrigins, Map lastPublishedPartNumberOrigins, Map undeterminedParts) throws Exception {
	  Iterator i=metadataList.iterator();
	  Metadata data, part;
	  while (i.hasNext()) {
	    data = (Metadata)i.next();
	    mapPartNames(parts, data.getMetadataBase());
	  }
	  
    Origin o;
    Origin lastPublish=null;
    String ilinkPartNumber;
	  i = parts.values().iterator();
	  while (i.hasNext()) {
	    data = (Metadata) i.next();
      o = data.getOrigin();
      ilinkPartNumber = data.get(ATT_ILINK_PART_NUMBER);
      if (null==ilinkPartNumber || "".equals(ilinkPartNumber.trim())) ilinkPartNumber=null;
      lastPublish = sync.lastSynchronization(o.getDomainName(), o.getServerName(), o.getDatasourceName(), o.getName());
      if (null==lastPublish && null==ilinkPartNumber) {
        newParts.put(data.getName(), data);
        continue;
      }
      if (null==lastPublish && null!=ilinkPartNumber) {
        lastPublish = findPastNamedOrigin((IntralinkOrigin)o, ilinkPartNumber); //look for a renamed part
        if (null==lastPublish) {
          //This part has a part number but neither name nor part number can be found in sync log
          //consider it as a new part
          undeterminedParts.put(data.getName(), data);  
          newParts.put(data.getName(), data);
          continue;
        }
        //name was not found in sync log, but part number was. this parts looks to have been renamed
        sync.rename(lastPublish.getDomainName(), lastPublish.getServerName(), lastPublish.getDatasourceName(), lastPublish.getName(), data.getName());
        parts.remove(lastPublish.getName());
        parts.put(data.getName(), data);
        lastPublish = sync.lastSynchronization(o.getDomainName(), o.getServerName(), o.getDatasourceName(), o.getName());
        //rename in sync log and just continue on categorizing this new name publishing ...
        /*
        renamedParts.put(data.getName(), data);
        data.getMetadataBase().set(ATT_AGILE_NUMBER, ilinkPartNumber);
        part = (Metadata)parts.get(data.getName());
        part.getMetadataBase().set(ATT_AGILE_NUMBER, data.getMetadataBase().get(ATT_AGILE_NUMBER));
        continue;
        */
      }
      
      if (lastPublish.isLater(o)) throw new NotUpToDate(o, lastPublish);
      Collection syncRecords = sync.findAllSynchronizationRecords(lastPublish);
      if (syncRecords==null || syncRecords.size()==0) {
        //This part has been published, but doesn't seem to have been synchronized with the full set of data
        //consider it as new part - really want to throw an error here
        //throw incomplete synchronization errors
        undeterminedParts.put(data.getName(), data);
        newParts.put(data.getName(), data);
        continue;        
      }
      Iterator j = syncRecords.iterator();
      Origin a;
      Origin modelOrigin=null;
      Origin docOrigin=null;
      Origin partNumberOrigin=null;
      while (j.hasNext()) {
        a = (Origin)j.next();
	      if (a.getDatasourceType().equals("agile.CAD.Part")) modelOrigin=a;
 	      if (a.getDatasourceType().equals("agile.CAD.Document")) docOrigin=a;
 	      if (a.getDatasourceType().equals("part-number-ilink")) partNumberOrigin=a;
		  }
      if (lastPublish.getUniqueID().equals(data.getOrigin().getUniqueID())) {
         //this part has been previously published
        unchangedParts.put(data.getName(), data);
        if (null!=partNumberOrigin) {
          data.getMetadataBase().set(ATT_AGILE_NUMBER, partNumberOrigin.getName());
          part = (Metadata)parts.get(data.getName());
          part.getMetadataBase().set(ATT_AGILE_NUMBER, data.getMetadataBase().get(ATT_AGILE_NUMBER));          
        }
        continue;
      }      
      if (null!=modelOrigin && null!= partNumberOrigin) {
        if (!enableCADDocuments || (enableCADDocuments && null!=docOrigin)) {
	        //A prior version of this part has been published
	        //This part is an update
	        updatedParts.put(data.getName(), data);
	        lastPublishedOrigins.put(data.getName(), lastPublish);
	        lastPublishedModelOrigins.put(data.getName(), modelOrigin);
	        lastPublishedDocumentOrigins.put(data.getName(), docOrigin);
	        lastPublishedPartNumberOrigins.put(data.getName(), partNumberOrigin);
	        data.getMetadataBase().set(ATT_AGILE_NUMBER, partNumberOrigin.getName());
	        part = (Metadata)parts.get(data.getName());
	        part.getMetadataBase().set(ATT_AGILE_NUMBER, data.getMetadataBase().get(ATT_AGILE_NUMBER));
        }
      }
      else {
	      //This part has been published, but doesn't seem to have been synchronized with the full set of data
	      //consider it as new part - really want to throw an error here
	      //throw incomplete synchronization errors
	      undeterminedParts.put(data.getName(), data);
	      newParts.put(data.getName(), data);
	      continue;          
      }
	  }
	}
  
	protected Map findEmptyPartNumbers(Collection metadataList) throws Exception {
    Metadata data;
    //MetadataAdapter adapter;
    Map emptyPartNumbers = new HashMap();
	  Iterator i = metadataList.iterator();
	  while (i.hasNext()) {
	    data= (Metadata)i.next();
	    if (!hiKLADemo.isExcluded(data.getName())) {
	      findUnpublishedPartNumbers(emptyPartNumbers, data);
	    }
	  }
	  //look for multiple versions of same name
	  Map namesForEmptyPartNumbers = new HashMap();
	  i=emptyPartNumbers.values().iterator();
	  Metadata d, dup;
	  while (i.hasNext()) {
	    d = (Metadata)i.next();
	    dup = (Metadata)namesForEmptyPartNumbers.get(d.getName());
	    if (null==dup) namesForEmptyPartNumbers.put(d.getName(), d);
	    else {
        if (!((IntralinkOrigin)d.getOrigin()).getBranch().equals(((IntralinkOrigin)dup.getOrigin()).getBranch())) throw new Exception("Multiple Versions of same object: " + d.getOrigin() + " <<!!>> " + dup.getOrigin());
        else if (!((IntralinkOrigin)d.getOrigin()).getRevision().equals(((IntralinkOrigin)dup.getOrigin()).getRevision())) throw new Exception("Multiple Versions of same object: " + d.getOrigin() + " <<!!>> " + dup.getOrigin());
	      else if (!(((IntralinkOrigin)d.getOrigin()).getVersion() == ((IntralinkOrigin)dup.getOrigin()).getVersion())) throw new Exception("Multiple Versions of same object: " + d.getOrigin() + " <<!!>> " + dup.getOrigin());
	      // else duplicate is same version - ok 
	    }
	  }
	  return emptyPartNumbers;
	}

  protected void findUnpublishedPartNumbers(Map emptyPartNumbers, Metadata data) {
    if (partIsUnpublished(data) && null==emptyPartNumbers.get(data.getOrigin().toString()))
      emptyPartNumbers.put(data.getOrigin().toString(), data);
    if (!data.hasSubComponents()) return;

    MetadataSubComponent kid;
    Iterator i = data.getSubComponents().iterator();
    while (i.hasNext()) {
      kid = (MetadataSubComponent) i.next();
      if (hiKLADemo.isExcluded(kid.getName())) continue;
      findUnpublishedPartNumbers(emptyPartNumbers,kid);
    }
  }

  protected boolean partIsUnpublished(Metadata data) {      
    String partnumber=data.get(ATT_AGILE_NUMBER);
    if (null!=partnumber && !"".equals(partnumber.trim())) return false;
    try {
      Origin o = data.getOrigin();
      Origin lastModelPublish=null;
	    Origin lastPublish = sync.lastSynchronization(o.getDomainName(), o.getServerName(), o.getDatasourceName(), o.getName());
	    if (null==lastPublish) return true;
      Collection c = sync.findAllSynchronizationRecords(lastPublish);
	    if (c!=null && c.size()>0) {
	      Iterator i = c.iterator();
	      Origin a;
	      while (i.hasNext()) {
	        a = (Origin)i.next();
	        if (a.getDatasourceType().startsWith(Origin.FROM_AGILE)) { 
	          if (!a.getName().endsWith("-doc")) lastModelPublish=a;
	        }
	      }
	    }
	    if (null==lastModelPublish) return true;
	    partnumber =lastModelPublish.getName();
      data.getMetadataBase().set(ATT_AGILE_NUMBER, partnumber);
 	    data.getMetadataBase().set(ATT_ILINK_PART_NUMBER, partnumber);
 	    return false;
    }
  	catch(Exception e) { e.printStackTrace(); }
    return false;
  }

  protected Map proposePartNumbers(Map emptyPartNumbers) {
    Map proposedPartNumbers = new HashMap();
    Iterator i = emptyPartNumbers.keySet().iterator();
    Origin o;
    Metadata data;
    String key, name, proposedPartNumber;
    while (i.hasNext()) {
      key = i.next().toString();
      data = (Metadata)emptyPartNumbers.get(key);
      name = data.getName();
      if (null!=proposedPartNumbers.get(name)) continue;
      proposedPartNumber = proposePartNumber(data);
      proposedPartNumbers.put(name, proposedPartNumber);
    }
    return proposedPartNumbers;
  }
  
  protected String parseNumberingConvention(String text) {
    if (null==text) return null;
    if (11!=text.length() && 15!=text.length()) return null; 

    String base=null;
    String suffix=null;
    base = text.substring(0,7);
    if (11==text.length()) suffix=text.substring(8);
    if (15==text.length()) suffix= text.substring(8,11);
    try { 
      int b = Integer.parseInt(base);
      int s = Integer.parseInt(suffix);
      String bb = "0000000"+b;
      String ss = "000" + s;
      bb = bb.substring(bb.length()-7);
      ss = ss.substring(ss.length()-3);
      String number= bb + "-" + ss;
      {} //System.out.println("["+number+"]" + " proposed as part number for "+text);
      return number;
    }
    catch (NumberFormatException e) { return null; }    
  }
  
  protected String proposePartNumber(Metadata data) {
    String name = data.getName();
    String proposal = parseNumberingConvention(name);
    if (null!=proposal) return proposal;
    
    String partnumberAtt=data.get(ATT_ILINK_PART_NUMBER);
    proposal = parseNumberingConvention(partnumberAtt);
    if (null!=proposal) return proposal;
    return null;
  }

	protected Collection map(Collection metadataList, String agileClassName) throws Exception {
	  Collection c = new Vector();
	  Iterator i = metadataList.iterator();
	  MetadataAdapter adapter;
	  Metadata data;
	  while (i.hasNext()) {
	    adapter = (MetadataAdapter)i.next();
	    if (!hiKLADemo.isExcluded(adapter.getName())) {
	      data = adapter;
	      if (isAssembly(adapter)) data = loadSubComponents(adapter); 
	      c.add(map(data, agileClassName));
	    }
	  }
	  return c;
	}
  
  private BillOfMaterials map(BillOfMaterials bill, String agileClassName) throws Exception {
    Metadata data = bill.getMetadata();
    Metadata m = map(data, agileClassName);
    return new BillOfMaterials(m);
  }
  
  private boolean isAssembly(Metadata data) {
    return data.getName().toLowerCase().endsWith(".asm");
  }
  
  private boolean isDrawing(Metadata data) {
    return data.getName().toLowerCase().endsWith(".drw");
  }

  private Metadata loadSubComponents (Metadata data) throws Exception {
	  Metadata structure=null;
	  BillOfMaterials bill;
	  IntralinkAccess y = IntralinkAccess.getAccess();
	  bill = y.reportBill(data.getOrigin(), getAuthentication());
	  return bill.getMetadata();
  }
  
/*
  
   private void synchronizeKLAOrigin(Metadata data) throws Exception {
	  Origin o, KLAOrigin;
	  o = data.getOrigin();
	  //KLAOrigin = data.getOrigin();
	  //if (null!=KLAOrigin) sync.record(o, KLAOrigin);
	  
	  if (!data.hasSubComponents()) return;
	  Iterator i = data.getSubComponents().iterator();
	  while(i.hasNext()) {
	    data = (Metadata)i.next();
	    synchronizeKLAOrigins(sync, data);
	  }
	  
	}
  */
  public void synchronizeMetadata(Collection items) {
	  if (null==items) return;
	  KLAMetadataAdapter data;
	  Iterator i = items.iterator();
	  while (i.hasNext()) {
	    data = (KLAMetadataAdapter)i.next();
	    data.refresh();
	  }
	}
  
	public void render() { 
	  synchronizeMetadata(getItems()); 
	  synchronizeMetadata(getChosenItems()); 
	}
  
  //public String getCreateCADDocuments() { if (createCADDocuments) return "YES"; else return "NO"; }
  //public void setCreateCDDocuments(String s) { if ("yes".equalsIgnoreCase(s)) createCADDocuments=true; else createCADDocuments=false; }

  public String getCreateCADDocuments() { 
    if (enableCADDocuments) return "YES"; 
    return "NO"; 
  }
  public void setCreateCADDocuments(String s) { if ("yes".equalsIgnoreCase(s)) enableCADDocuments=true; else enableCADDocuments=false; }
  
  private AgileAccess agile() throws Exception {
	  if (null==agileAccess) agileAccess = zws.AgileAccess.getAccess("DesignState-node-0", Properties.get(Names.DEFAULT_AGILE_SOURCE), getAuthentication());
	  return agileAccess;
	}

	public String getAttachNativeFile() { return attachNativeFile; }
	public void setAttachNativeFile(String s) { attachNativeFile=s; }
	public String getAttachIGESImage() { return attachIGESImage; }
	public void setAttachIGESImage(String s) { attachIGESImage = s; }
	public String getCreateNonExistingParts() { return createNonExistingParts; }
	public void setCreateNonExistingParts(String s) { createNonExistingParts=s; }

	protected void setChoiceValidator(ChoiceValidator c) { chosenItemValidator=c; }
  private static Synchronizer sync = Synchronizer.getClient("DesignState-node-0");

  protected boolean enableCADDocuments = false;
  private String attachNativeFile = "NO";
  private String attachIGESImage= "NO";
  private String createNonExistingParts = "YES";
  
  private ChoiceValidator chosenItemValidator=null;

  protected static String ATT_ILINK_PART_NUMBER="Part_Number";
  protected static String ATT_AGILE_NUMBER = "number";
  protected static String ATT_AGILE_CLASS_TYPE = "agile-class";
  protected static String ATT_AGILE_FILE_TYPE = "CAD-File-Type";
  protected static String AGILE_CLASS_PART = "Part";
  protected static String AGILE_CLASS_DOCUMENT = "Document";
  protected static String AGILE_CLASS_CAD_PART = "CAD Part";
  protected static String AGILE_CLASS_CAD_DOCUMENT = "CAD Document";

  AgileAccess agileAccess = null;
}
