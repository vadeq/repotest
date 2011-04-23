package zws.datasource.agile;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 15, 2004, 11:06 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.bill.intralink.BillOfMaterials;
import zws.application.Properties;
import zws.application.server.datasource.Names;
import zws.data.*;
import zws.data.eco.*;
import zws.lifecycle.LifeCycleOrder;
import zws.origin.*;
import zws.util.MapUtil;
import zws.datasource.DatasourceBase;
import zws.datasource.agile.op.PublishBill;
import zws.exception.*;
import zws.search.SearchAgent;
import zws.security.Authentication;
import zws.synchronization.SynchronizationRecord;
import zws.service.document.DocumentSvc;
import zws.service.synchronization.SynchronizationSvc;
import zws.util.*;
import zws.util.comparator.ObjectComparator;
import zws.xml.util.XMLString;
import zws.xml.xslt.Stylizer;

import java.rmi.RemoteException;
import java.util.*;
import java.io.ByteArrayInputStream;

import com.agile.api.*;
import com.agile.zws.connector.AgileConnectorPub;
import java.io.File;
import zws.datasource.agile.op.AttachFile;

import zws.data.eco.AffectedItem;
import zws.data.eco.ECO;
public class KLAAgileSource extends DatasourceBase {
  public String getType() { return "agile"; }

  public static void main(String[] args) {
    KLAAgileSource s = new KLAAgileSource();
    //s.run();
  }

  //Datasource interface
  public SearchAgent materializeSearchAgent() { return null; }
  public SearchAgent materializeLatestSearchAgent() { return null; }
  public SearchAgent materializeLatestRevSearchAgent() { return null; }
  public Metadata findMetadataForPackage(Metadata data, boolean includeHistory, Authentication id) throws Exception { return data; }

  public Metadata find(String partnumber, Authentication id) throws Exception {
    try {
      login(id);
      IDataObject x = findItem(partnumber, id);
      if (null==x) throw new NameNotFound(partnumber);
      return unmarshall(x);
    }
    catch(Exception e) { throw e;}
    finally { logout(id); }
  }

  public boolean contains(String name, Authentication id) throws Exception {
    IItem x;
    try {
      login(id);
      return null!=findItem(name, id);
    }
    catch(Exception e) { throw e;}
    finally { logout(id); }
  }

  public void create(Metadata data, Authentication id) throws Exception {
    try {
      login(id);
      Metadata d;
      Iterator i = mapAllComponents(data).values().iterator();
      while (i.hasNext()) {
        d = (Metadata)i.next();
        createItem(d, id);
      }
    }
    catch(Exception e) { throw e; }
    finally { logout(id); }
  }

  public void attachFile(File attachment, String partnumber, Authentication id) throws Exception {
    try {
      login(id);
      attachFileToItem(attachment, partnumber, id);
    }
    catch(Exception e) { throw e;}
    finally { logout(id); }
  }


  public void storeBill(BillOfMaterials bill, Authentication id) throws Exception {
    Metadata data = bill.getMetadata();
    structureBill(data, id);
  }

  public void structureBill(Metadata data, Authentication id) throws Exception {
    try {
      login(id);
      IItem root = findItem(data, id);
      if (root==null) throw new NameNotFound(data.getName());
      Map dataParentMap;
      Map itemParentMap;
      Pair itemP, dataP;
      Map parents;
      Metadata dataKid, dataParent;
      IItem itemKid, itemParent;
      Iterator i, j;

      //+++check to see that all modified are preliminary

      dataParentMap= mapParents(data);
      itemParentMap = mapParents(root);
      //remove items
      Map dels = findRemovedItems(root.getAgileClass().getName(), itemParentMap, dataParentMap);
      i = dels.values().iterator();
      while (i.hasNext()) {
        itemP = (Pair)i.next();
        itemKid = (IItem) itemP.getObject0();
        parents = (Map)itemP.getObject1();
        j = parents.values().iterator();
        while (j.hasNext()) {
          itemParent = (IItem) j.next();
          removeSubComponent(itemParent, itemKid.getName(), id);
        }
      }

      //add items
      itemParentMap = mapParents(root);
      Map adds = findAddedMetadata(itemParentMap, dataParentMap);
      i = adds.values().iterator();
      while (i.hasNext()) {
        dataP = (Pair)i.next();
        dataKid = (Metadata) dataP.getObject0();
        parents = (Map)dataP.getObject1();
        j = parents.values().iterator();
        while (j.hasNext()) {
          dataParent = (Metadata) j.next();
          addNewSubComponent(dataParent.get(ATT_PART_NUMBER), dataKid.get(ATT_PART_NUMBER), ((MetadataSubComponent)dataKid).getQuantity(), id);
        }
      }

      //update quantities
      //itemParentMap = mapParents(root);
      //Map mods = findModifiedMetadata(itemParentMap, dataParentMap);
    }
    catch(Exception e) { throw e;}
    finally { logout(id); }
  }


  public void update(Metadata data, Authentication id) throws Exception {
    try {
      login(id);
      String partnumber = data.get(ATT_PART_NUMBER);
      IDataObject x = findItem(partnumber, id);
      if (null==x) throw new NameNotFound(partnumber);
      updateItem(x, data);
    }
    catch(Exception e) { throw e;}
    finally { logout(id); }
  }

  public void delete(Metadata m, Authentication id) throws Exception {
    try {
      login(id);
      if (null==m) return;
      IItem root = findItem(m, id);
      if (null==root) return;
      String agileClassName = m.get(this.ATT_AGILE_CLASS_TYPE);
      if (null==agileClassName || "".equals(agileClassName)) deleteItem(root);
      else if (root.getAgileClass().getName().equalsIgnoreCase(agileClassName)) deleteItem(root);
      else {}//Logwriter.printOnConsole("Agile item's class type does not match metadata's class type - did not delete\n" + m.toString());
      if (!m.hasSubComponents())  return;
      Iterator i = m.getSubComponents().iterator();
      while(i.hasNext()) delete((Metadata)i.next(), id);
    }
    catch(Exception e) { throw e;}
    finally { logout(id); }
  }

  public void delete(String partNumber, Authentication id) throws Exception {
    try {
      login(id);
      IItem root = findItem(partNumber, id);
      deleteItem(root);
    }
    catch(Exception e) { throw e;}
    finally { logout(id); }
  }

  public void rename(String name, String newName, Authentication id) throws Exception {
    try {
      login(id);
      if (null==name) throw new NullPointerException("Name is null");
      if (null==newName) throw new NullPointerException("New Name is null");
      IItem newItem = findItem(newName, id);
      if (null!=newItem) throw new Duplicate(newName);
      IItem item = findItem(name, id);
      if (null==item) throw new NameNotFound(name);
      renameItem(item, newName);
      resynchronizeToNewItem(item, newItem);
    }
    catch(Exception e) { throw e;}
    finally { logout(id); }
  }

  public void replace(String partNumber, String newPartNumber, Authentication id) throws Exception {
    try {
      login(id);
      IDataObject part = findItem(partNumber, id);
      IDataObject newPart = findItem(newPartNumber, id);
      replaceItem(part, newPart, id);
    }
    catch(Exception e) { throw e;}
    finally { logout(id); }
  }

  public void replace(Collection replacementPairs, Authentication id) throws Exception {
    Pair p;
    String partNumber, newPartNumber;
    Iterator i = replacementPairs.iterator();
    try {
      login(id);
      while (i.hasNext()) {
        p = (Pair) i.next();
        partNumber = p.getObject0().toString();
        newPartNumber= p.getObject1().toString();
        IDataObject part = findItem(partNumber, id);
        IDataObject newPart = findItem(newPartNumber, id);
        replaceItem(part, newPart, id);
      }
    }
    catch(Exception e) { throw e;}
    finally { logout(id); }
  }

  public void createCADParts(Collection metadataList, boolean createCADDocuments, Authentication id) throws Exception {
    IItem part,doc;
    Metadata data;
    String number;
    Iterator i = metadataList.iterator();
    try {
      login(id);
      IAgileSession session = session(id);
      Integer CADDocClassID = findAgileClassID(CLASS_TYPE_CAD_DOCUMENT);
      Integer CADPartClassID = findAgileClassID(CLASS_TYPE_CAD_PART);

      while(i.hasNext()) {
        data = (Metadata)i.next();
        number = data.get(this.ATT_PART_NUMBER);
        part = (IItem)session(id).getObject(IItem.OBJECT_TYPE, number);
        if (null==part) {
          part=(IItem)session.createObject(CADPartClassID, number);
          synchronizeItem(data, part);
        }
        if (true==createCADDocuments) {
          String docNumber = number+"-doc";
          doc = (IItem)session(id).getObject(IItem.OBJECT_TYPE, docNumber);
          if (null==doc) {
            doc = (IItem) session.createObject(CADDocClassID, docNumber);
            synchronizeItem(data, doc);
          }
          try {
             addNewSubComponent(part, doc, 1);
          }
          catch(Exception ignore) {}
        }
      }
    }
    catch(Exception e) { throw e;}
    finally { logout(id); }
  }

  public void create(Collection dataList, Authentication id) throws Exception {
    Metadata data;
    Iterator i = dataList.iterator();
    while(i.hasNext()) {
      data = (Metadata)i.next();
      create(data, id);
    }
  }


  public void structureBill(Collection dataList, Authentication id) throws Exception {
    Metadata data ;
    Iterator i = dataList.iterator();
    while(i.hasNext()) {
      data= (Metadata)i.next();
      structureBill(data, id);
    }
  }

  public void storeBill(Collection dataList, Authentication id) throws Exception {
    BillOfMaterials bill;
    Iterator i = dataList.iterator();
    while(i.hasNext()) {
      bill = (BillOfMaterials)i.next();
      storeBill(bill, id);
    }
  }

  public void update(Collection dataList, Authentication id) throws Exception {
    Metadata data;
    Iterator i = dataList.iterator();
    while(i.hasNext()) {
      data = (Metadata)i.next();
      update(data, id);
    }
  }

  public void delete(Collection dataList, Authentication id) throws Exception {
    Metadata data;
    Iterator i = dataList.iterator();
    while(i.hasNext()) {
      data = (Metadata)i.next();
      delete(data, id);
    }
  }

  public ECO findECO(String number, Authentication id) throws Exception {
    try {
      login(id);
      IAgileSession session = session(id);
      IAdmin admin = session.getAdminInstance();
      IAgileClass cls = admin.getAgileClass("Change Orders");
      IQuery query = (IQuery)session.createObject(IQuery.OBJECT_TYPE, cls);
      query.setCaseSensitive(false);
      String criteria = "[Cover Page.Number] == '"+number+"' ";
      query.setCriteria(criteria);
      ITable queryResults = query.execute();
      if (1!=queryResults.size()) return null;
      Iterator i = queryResults.getReferentIterator();
      IChange changeOrder = (IChange)i.next();
      ECO eco = unmarshall(changeOrder);
      {}//Logwriter.printOnConsole(eco);
      return eco;
    }
    catch(Exception e) { throw e;}
    finally { logout(id); }
  }

  public ECO createECO(Authentication id) throws Exception {
    try {
      login(id);
      IAdmin admin = session(id).getAdminInstance();
      IAgileClass ecoClass = admin.getAgileClass(ChangeConstants.CLASS_ECO);
      IAutoNumber[] numMaker = ecoClass.getAutoNumberSources();
      IChange change = (IChange)session(id).createObject(ecoClass, numMaker[0]);
      ECO eco = unmarshall(change);
      return eco;
    }
    catch(Exception e) { throw e;}
    finally { logout(id); }
  }

  public Collection findECOs(String nameCriteria, String descriptionCriteria, String statusCriteria, String workflowCriteria,Authentication id) throws Exception {
    Collection ecoList = new Vector();
    try {
      login(id);
      IAgileSession session = session(id);
      IAdmin admin = session.getAdminInstance();
      IAgileClass cls = admin.getAgileClass("Change Orders");
      IQuery query = (IQuery)session.createObject(IQuery.OBJECT_TYPE, cls);
      query.setCaseSensitive(false);
      String cName=null, cDescription=null, cStatus=null, cWorkflow=null;
      if (nameCriteria!=null && !"".equals(nameCriteria.trim())) cName = nameCriteria;
      if (descriptionCriteria!=null && !"".equals(descriptionCriteria.trim())) cDescription = descriptionCriteria;
      if (statusCriteria!=null && !"".equals(statusCriteria.trim())) cStatus = statusCriteria;
      if (workflowCriteria!=null && !"".equals(workflowCriteria.trim())) cWorkflow = workflowCriteria;
      String criteria = "";
      if (null!=cName) criteria = "[Cover Page.Number] like '"+cName+"' ";
      if (null!=cDescription) {
        if (!"".equals(criteria)) criteria += " and ";
        criteria +="["+ChangeConstants.ATT_COVER_PAGE_DESCRIPTION+"] like '"+cDescription+"' ";
      }
      if (null!=cStatus) {
        if (!"".equals(criteria)) criteria += " and ";
        criteria +="["+ChangeConstants.ATT_COVER_PAGE_STATUS+"] == '"+cStatus+"' ";
      }
      if (null!=cWorkflow) {
        if (!"".equals(criteria)) criteria += " and ";
        criteria +="["+ChangeConstants.ATT_COVER_PAGE_WORKFLOW+"] like '"+cWorkflow+"' ";
      }
      {}//Logwriter.printOnConsole("ECO Criteria: " + criteria);
      query.setCriteria(criteria);
      ITable queryResults = query.execute();
      Iterator i = queryResults.getReferentIterator();
      if (!i.hasNext()) {
       {}//Logwriter.printOnConsole("No ECO's found for " + nameCriteria);
       return ecoList;
      }
      IChange changeOrder;
      while (i.hasNext()) {
        changeOrder = (IChange)i.next();
        ecoList.add(unmarshall(changeOrder));
      }
    }
    catch(Exception e) { throw e; }
    finally { logout(id); }
    Iterator i = ecoList.iterator();
    while (i.hasNext()) {
      ECO c = (ECO)i.next();
      {}//Logwriter.printOnConsole(c);
    }
    return ecoList;
  }

  private boolean isReleased(IItem item) throws APIException {
    Object o = item.getValue(ItemConstants.ATT_TITLE_BLOCK_REV_RELEASE_DATE);
    if (o==null) return false;
    if ("".equals(o.toString().trim())) return false;
    return true;
  }

  public ECO orderInitialRelease(String ecoNumber, Collection changedItems, Authentication id) throws Exception {
    IChange eco = findChange(ecoNumber, id);
    Iterator i = changedItems.iterator();
    AffectedItem affectedItem;
    ITable bomTable;
    IItem item,dep;
    Iterator t;
    IRow row;
    AffectedItem affectedDep;
    ITable affectedItems = eco.getTable(ChangeConstants.TABLE_AFFECTEDITEMS);
    while (i.hasNext()) {
      affectedItem = (AffectedItem)i.next();
      //  set property for initial release description and life cycle phases
      if (null==affectedItem.getDescription() || "".equals(affectedItem.getDescription().trim())) affectedItem.setDescription("Pilot Production Release");
      //if (null==affectedItem.getLifeCyclePhase() || "".equals(affectedItem.getLifeCyclePhase().trim())) affectedItem.setLifeCyclePhase("Pre-Release");
      if (null==affectedItem.getNewLifeCyclePhase() || "".equals(affectedItem.getNewLifeCyclePhase().trim())) affectedItem.setNewLifeCyclePhase("Pre-Release");
      addChange(eco, affectedItem, id);
      item = findItem(affectedItem.getItemNumber(), id);
      orderInitialReleaseOfBOM(eco, affectedItems, item);
    }
    return findECO(ecoNumber, id);
  }

  private void orderInitialReleaseOfBOM(IChange eco, ITable affectedItems, IItem item) throws Exception{
    ITable bomTable;
    IItem dep;
    bomTable = item.getTable(ItemConstants.TABLE_BOM);
    Iterator i = bomTable.getReferentIterator();
    IRow row;
    AffectedItem init = new AffectedItem();
    //  set property for initial release description and life cycle phases
    init.setDescription("Pilot Production Release");
    //init.setLifeCyclePhase("Pre-Release");
    init.setNewLifeCyclePhase("Pre-Release");
    while(i.hasNext()) {
      dep = (IItem)i.next();
      if (!isReleased(dep) && !listedAsAffectedItem(dep.getName(), affectedItems)) {
        addChange(eco, affectedItems, dep, init);
        //row = affectedItems.createRow(dep);
        orderInitialReleaseOfBOM(eco, affectedItems, dep);
      }
    }
  }

  public void orderRelease(String ecoNumber, Collection changedItems, String lifeCyclePhase, LifeCycleOrder lco, Authentication id) throws Exception {
    IChange eco = findChange(ecoNumber, id);
    Iterator i = changedItems.iterator();
    AffectedItem affectedItem;
    IItem item;
    ITable affectedItems = eco.getTable(ChangeConstants.TABLE_AFFECTEDITEMS);
    while (i.hasNext()) {
      affectedItem = (AffectedItem)i.next();
      //  set property for initial release description and life cycle phases
      if (null==affectedItem.getDescription() || "".equals(affectedItem.getDescription().trim()))
        affectedItem.setDescription(lifeCyclePhase + " Release");
      //affectedItem.setLifeCyclePhase(lifeCyclePhase);
      affectedItem.setNewLifeCyclePhase(lifeCyclePhase);
      addChange(eco, affectedItem, id);
      item = findItem(affectedItem.getItemNumber(), id);
      orderReleaseOfBOM(eco, affectedItems, item, lifeCyclePhase, lco);
    }
    //return findECO(ecoNumber, id);
  }

  public void orderReleaseOfBOM(IChange eco, ITable affectedItems, IItem item, String lifeCyclePhase, LifeCycleOrder lco) throws Exception{
    ITable bomTable;
    IItem dep;
    bomTable = item.getTable(ItemConstants.TABLE_BOM);
    Iterator i = bomTable.getReferentIterator();
    IRow row;
    AffectedItem change = new AffectedItem();
    //  set property for initial release description and life cycle phases
    if (null==change.getDescription() || "".equals(change.getDescription().trim()))
      change.setDescription(lifeCyclePhase + " Release");
    //change.setLifeCyclePhase(lifeCyclePhase);
    change.setNewLifeCyclePhase(lifeCyclePhase);
    while(i.hasNext()) {
      dep = (IItem)i.next();
      if(listedAsAffectedItem(dep.getName(), affectedItems)) continue;
      if (isReleased(dep)) {
        if (lco.comesAfter(lifeCyclePhase, getLifeCyclePhase(dep)))
          continue;
      }
      addChange(eco, affectedItems, dep, change);
      //row = affectedItems.createRow(dep);
      orderReleaseOfBOM(eco, affectedItems, dep, lifeCyclePhase, lco);
    }
  }

  private String getLifeCyclePhase(IItem item) {
    try {
      Object x = item.getValue(ItemConstants.ATT_TITLE_BLOCK_LIFECYCLE_PHASE);
      if (null==x) return "";
      return x.toString();
    }
    catch(Exception e) { e.printStackTrace(); return ""; }
  }

  public ECO addChange(String ecoNumber, AffectedItem changedItem, Authentication id) throws Exception {
    try {
      login(id);
      session(id).disableWarning(ExceptionConstants.APDM_PENDINGCHANGE_ITEM_WARNING);
      session(id).disableWarning(ExceptionConstants.APDM_CHANGE_ADD_AFFECTEDITEM);
      IChange eco = findChange(ecoNumber, id);
      ITable affectedItems = eco.getTable(ChangeConstants.TABLE_AFFECTEDITEMS);
      IItem item = findItem(changedItem.getItemNumber(), id);
      addChange(eco, affectedItems, item, changedItem);
      return findECO(ecoNumber, id);
    }
    catch(Exception e) { throw e;}
    finally { logout(id); }
  }

  private void addChange(IChange eco, ITable affectedItems, IItem item, AffectedItem changedItem) throws Exception {
    if (listedAsAffectedItem(changedItem.getItemNumber(), affectedItems)) return;

    IRow row = affectedItems.createRow(item);
    Map params = new HashMap();
    if (null!=changedItem.getDescription()) params.put(ChangeConstants.ATT_AFFECTED_ITEMS_ITEM_DESCRIPTION, changedItem.getDescription());
    String nextRev=changedItem.getNewRev();
    if (null==changedItem.getNewRev()) nextRev = findNextRevision(item);
    if (null!=nextRev && !"".equals(nextRev.trim())) params.put(ChangeConstants.ATT_AFFECTED_ITEMS_NEW_REV, nextRev);
    //if (null!=changedItem.getLifeCyclePhase()) params.put(ChangeConstants.ATT_AFFECTED_ITEMS_LIFECYCLE_PHASE, changedItem.getLifeCyclePhase());
    if (null!=changedItem.getNewLifeCyclePhase()) params.put(ChangeConstants.ATT_AFFECTED_ITEMS_LIFECYCLE_PHASE, changedItem.getNewLifeCyclePhase());
    row.setValues(params);
    return;
    //row.setValue(ChangeConstants.ATT_AFFECTED_ITEMS_ITEM_DESCRIPTION, changedItem.getDescription());
    //row.setValue(ChangeConstants.ATT_AFFECTED_ITEMS_LIFECYCLE_PHASE, changedItem.getLifeCyclePhase());
    //IRow affectedItemRow = affectedItems.createRow(params);
  }

  private void addChange(IChange eco, AffectedItem changedItem, Authentication id) throws Exception {
    IItem item = findItem(changedItem.getItemNumber(), id);
    ITable affectedItems = eco.getTable(ChangeConstants.TABLE_AFFECTEDITEMS);
    if (listedAsAffectedItem(changedItem.getItemNumber(), affectedItems)) return;

    IRow row;
    row = affectedItems.createRow(item);
    Map params = new HashMap();
    if (null!=changedItem.getDescription()) params.put(ChangeConstants.ATT_AFFECTED_ITEMS_ITEM_DESCRIPTION, changedItem.getDescription());
    String nextRev=changedItem.getNewRev();
    if (null==changedItem.getNewRev()) nextRev = findNextRevision(item);
    if (null!=nextRev && !"".equals(nextRev.trim())) params.put(ChangeConstants.ATT_AFFECTED_ITEMS_NEW_REV, nextRev);
    //if (null!=changedItem.getLifeCyclePhase()) params.put(ChangeConstants.ATT_AFFECTED_ITEMS_LIFECYCLE_PHASE, changedItem.getLifeCyclePhase());
    if (null!=changedItem.getNewLifeCyclePhase()) params.put(ChangeConstants.ATT_AFFECTED_ITEMS_LIFECYCLE_PHASE, changedItem.getNewLifeCyclePhase());
    row.setValues(params);
    return;
    //row.setValue(ChangeConstants.ATT_AFFECTED_ITEMS_ITEM_DESCRIPTION, changedItem.getDescription());
    //row.setValue(ChangeConstants.ATT_AFFECTED_ITEMS_LIFECYCLE_PHASE, changedItem.getLifeCyclePhase());
    //IRow affectedItemRow = affectedItems.createRow(params);
  }

  public Collection findItems(String nameCriteria, Authentication id) throws Exception {
    Collection itemList = new Vector();
    try {
      IAgileSession session = session(id);
      IQuery query = (IQuery)session.createObject(IQuery.OBJECT_TYPE, ItemConstants.CLASS_ITEM_BASE_CLASS);
      query.setCaseSensitive(false);
      query.setCriteria("[Number] like '"+nameCriteria+"'");
      ITable queryResults = query.execute();
      Iterator i = queryResults.getReferentIterator();
      if (!i.hasNext()) {
       {}//Logwriter.printOnConsole("No Items's found for " + nameCriteria);
       return itemList;
      }
      IDataObject item;
      while (i.hasNext()) {
        item = (IDataObject)i.next();
        itemList.add(unmarshall(item));
      }
    }
    catch (APIException ex) {
      {}//Logwriter.printOnConsole(ex);
    }
    Iterator i = itemList.iterator();
    while (i.hasNext()) {
      Metadata c = (Metadata)i.next();
      {}//Logwriter.printOnConsole(c);
    }
    return itemList;
  }


  public Map establishNewPartNumbers(Map proposedPartNumbers, String agileClass, String autoNumberSourceName) throws Exception {
    IAdmin admin;
    IAgileClass cls;
    IAutoNumber source;
    IAutoNumber[] sourceList;
    String nextAutoNumber;
    IAgileSession session = session(getDefaultAuthentication());
    admin = session.getAdminInstance();
    cls = findAgileClass(agileClass);
    String name, number;
    Map partNumbers = new HashMap();
    Iterator i = proposedPartNumbers.keySet().iterator();
    while (i.hasNext()) {
      name = i.next().toString();
      number = (String)proposedPartNumbers.get(name);
      if (null==number || "".equals(number.trim()))
        number = generateNextPartNumber(cls, autoNumberSourceName);
      else if (containsItem(name, getDefaultAuthentication()))
        number = generateNextPartNumber(cls, autoNumberSourceName);
      partNumbers.put(name, number);
    }
    return partNumbers;
  }

  public String generateNextPartNumber(String agileClass, String autoNumberSourceName) throws Exception {
    IAdmin admin;
    IAgileClass cls;
    IAutoNumber source;
    IAutoNumber[] sourceList;
    String nextAutoNumber;
    IAgileSession session = session(getDefaultAuthentication());
    admin = session.getAdminInstance();
    cls = findAgileClass(agileClass);
    return generateNextPartNumber(cls, autoNumberSourceName);
  }


  public String generateNextPartNumber(IAgileClass cls, String autoNumberSourceName) throws Exception {
    IAutoNumber source;
    IAutoNumber[] sourceList;
    String nextAutoNumber;
    sourceList = cls.getAutoNumberSources();
    source=null;
    if (sourceList.length>1 && (null==autoNumberSourceName || "".equals(autoNumberSourceName.trim()))) throw new InvalidName(autoNumberSourceName);
    if (sourceList.length==1 && (null==autoNumberSourceName || "".equals(autoNumberSourceName.trim()))) source = sourceList[0];
    else {
      for (int i = 0; i< sourceList.length; i++) {
        source = sourceList[i];
        if (autoNumberSourceName.equalsIgnoreCase(source.getName())) break;
      }
    }
    if (null==source) { throw new NameNotFound(autoNumberSourceName); }
    nextAutoNumber = source.getNextNumber();
    return nextAutoNumber;
  }



  public Collection listAttributes(String agileClassName) throws Exception {
    try {
      login(getDefaultAuthentication());
      return listAttributeNames(agileClassName);
    }
    catch(Exception e) { throw e;}
    finally { getDefaultAuthentication(); }
  }

  public Collection refreshAttributes(String agileClassName) throws Exception {
    try {
      login(getDefaultAuthentication());
      loadAttributes(agileClassName);
      return listAttributeNames(agileClassName);
    }
    catch(APIException e) {
      //e.getCause().printStackTrace();
      {}//Logwriter.printOnConsole(e.getRootCause().getMessage());
      throw e;
    }
    catch(Exception e) {
      e.printStackTrace();
      throw e;
    }
    finally { logout(getDefaultAuthentication()); }
  }

  public Collection listClasses() throws Exception {
    try {
      login(getDefaultAuthentication());
      return listClassNames();
    }
    catch(Exception e) { throw e;}
    finally { getDefaultAuthentication(); }
  }


  private boolean containsItem(String name, Authentication id) throws Exception { return null!=findItem(name, id); }
  private IItem findItem(Metadata m, Authentication id) throws Exception {
    if (null==m) return null;
    return findItem(m.get(ATT_PART_NUMBER), id);
  }

  private IItem findItem(String number, Authentication id) throws Exception {
    IItem x = (IItem)session(id).getObject(IItem.OBJECT_TYPE, number);
    return x;
  }

  private IChange findChange(String number, Authentication id) throws Exception {
    IChange x = (IChange)session(id).getObject(IChange.OBJECT_TYPE, number);
    return x;
  }


  private IItem createItem(Metadata data, Authentication id) throws Exception {
    String partnumber = data.get(ATT_PART_NUMBER);
    if (null==partnumber) throw new InvalidName("NULL");
    String agileClass = data.get(ATT_AGILE_CLASS_TYPE);
    if (agileClass==null || "".equals(agileClass)) agileClass = CLASS_TYPE_PART;
    IItem x = findItem(partnumber, id);
    if (null==x) x = (IItem)createAgileObject(agileClass, partnumber, id);
    synchronizeItem(data, x);
    updateItem(x, data);
    return x;
  }

  private void attachFileToItem(File attachment, String partnumber, Authentication id) throws Exception {
    if (!attachment.exists()) throw new NotAFile(attachment);
    if (!attachment.isFile()) throw new NotAFile(attachment);
    if (null==partnumber) throw new InvalidName("NULL");
    // --Axis client not loading properly
    // --IItem x = findItem(partnumber, id);
    // --attachFile(x, attachment);
    removeAttachment(partnumber, attachment.getName(), id);

    AttachFile attacher = new AttachFile();
    attacher.setAuthentication(id);
    attacher.setURL(getURL());
    attacher.setPartnumber(partnumber);
    attacher.setAttachment(attachment);
    attacher.execute();
    boolean verified = verifyAttachment(partnumber, attachment.getName(), id);
    if (!verified) throw new FailedToTransferFile(attachment, getURL());
    if(Server.productionMode()) attachment.delete();//create a flag for this.
  }

  private void removeAttachment(String partnumber, String filename, Authentication id) throws Exception {
    IItem item = findItem(partnumber, id);
    if (null==item) throw new NameNotFound("partnumber");

    ITable table = item.getTable(ItemConstants.TABLE_ATTACHMENTS);
    boolean moreAttachments = true;
    while (moreAttachments) {
      moreAttachments=false;
      ITwoWayIterator i = table.getTableIterator();
      while (i.hasNext()) {
        IRow row = (IRow)i.next();
        Object o = row.getValue(ItemConstants.ATT_ATTACHMENTS_FILE_NAME);
        String name = o.toString();
        if (name.equals(filename)) {
          table.removeRow(row);
          moreAttachments=true; // reset to remove other attachments by same filename
          break;
        }
      }
    }
  }

  private boolean verifyAttachment(String partnumber, String filename, Authentication id) throws Exception {
    boolean verified=false;
    IItem item = findItem(partnumber, id);
    ITable table = item.getTable(ItemConstants.TABLE_ATTACHMENTS);
    ITwoWayIterator i = table.getTableIterator();
    while (i.hasNext()) {
      IRow row = (IRow)i.next();
      Object o = row.getValue(ItemConstants.ATT_ATTACHMENTS_FILE_NAME);
      String name = o.toString();
      if (name.equals(filename)) verified = true;
    }
    return verified;
  }

  private void updateItem(IDataObject o, Metadata data) throws Exception {
    Map definedAttributes = findAttributes(data.get(ATT_AGILE_CLASS_TYPE)); //+++really slow!!!!!!
    Iterator i = data.getAttributes().keySet().iterator();
    String key;
    IAttribute att;
    Integer attID;
    String value;
    int len;
    Map attValues = new HashMap();
    while (i.hasNext()) {
      key =i.next().toString();
      if (key.equalsIgnoreCase(this.ATT_PART_NUMBER)) continue;
      if (key.equalsIgnoreCase(this.ATT_RELEASE_PHASE)) continue;
      if (key.equalsIgnoreCase(this.ATT_REVISION)) continue;
      att = (IAttribute) definedAttributes.get(key.toLowerCase());
      if (null== att) continue;
      attID = (Integer)att.getId();
      value = data.get(key);
      try {
        len = att.getMaxLength();
        if (null!=value && value.length() > len) value = value.substring(0,att.getMaxLength()-4)+"...";
      }
      catch(NoSuchMethodError ignore) { } //att does not have maxlength property
      attValues.put(attID, value);
      //o.setValue(attID, value);
    }
    if (!attValues.isEmpty()) o.setValues(attValues);
  }

  private void renameItem(IItem item, String newName) throws Exception {
    if (null==item) throw new NullPointerException("Item is null");
    if (null==newName) throw new NullPointerException("New Name is null");
    IAttribute att = item.getAgileClass().getAttribute(ItemConstants.ATT_TITLE_BLOCK_NUMBER);
    item.setValue(att.getId(), newName);
  }

  private void replaceItem(IDataObject part, IDataObject newPart, Authentication id) throws Exception {
    ITable whereUsedTable = part.getTable(ItemConstants.TABLE_WHEREUSED);
    IRow row;
    ITwoWayIterator i = whereUsedTable.getTableIterator();
    String partNumber;
    IItem root;
    while(i.hasNext()) {
      row = (IRow)i.next();
      partNumber = row.getValue(ItemConstants.ATT_WHERE_USED_ITEM_NUMBER).toString();
      root = findItem(partNumber, id);
      replaceReference(root, part, newPart);
    }
  }

  private void replaceReference(IDataObject root, IDataObject ref, IDataObject newRef) throws Exception {
    Collection replacements = new Vector();
    ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
    IRow row;
    ITwoWayIterator i = bomTable.getTableIterator();
    String partNumber;
    while(i.hasNext()) {
      row = (IRow)i.next();
      partNumber = row.getValue(ItemConstants.ATT_BOM_ITEM_NUMBER).toString();
      if (partNumber.equals(ref.getName())) replacements.add(row);
    }
    Iterator j = replacements.iterator();
    String quantity;
    Map p = new HashMap();
    while(j.hasNext()) {
      row = (IRow)j.next();
      quantity = row.getValue(ItemConstants.ATT_BOM_QTY).toString();
      bomTable.removeRow(row);
      p.put(ItemConstants.ATT_BOM_ITEM_NUMBER, newRef.getName());
      p.put(ItemConstants.ATT_BOM_QTY, quantity);
      {}//Logwriter.printOnConsole(newRef.getName() + " ["+quantity+"] replaced ref");
      bomTable.createRow(p);
    }
  }

  private void deleteItem(IItem root) throws Exception {
    if (null==root) return;
    AgileOrigin o = materializeOrigin(root);
    if (!root.isDeleted()) root.delete();
    if (root.isDeleted()) root.delete();
    unsynchronizeItem(o);
  }

  private void addNewSubComponent(String root, String kid, int quantity, Authentication id) throws Exception {
    if (null==root) throw new NullPointerException("Root item is null");
    if (null==kid) throw new NullPointerException("Subcomponent name is null");
    IItem r = findItem(root, id);
    if (null==r) throw new NameNotFound(root);
    addNewSubComponent(r, kid, quantity, id);
  }

  private void addNewSubComponent(IItem root, String kid, int quantity, Authentication id) throws Exception {
    if (null==root) throw new NullPointerException("Root item is null");
    if (null==kid) throw new NullPointerException("Subcomponent name is null");
    IItem k = findItem(kid, id);
    if (null==k) throw new NameNotFound(kid);
    ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
    Map p = new HashMap();
    p.put(ItemConstants.ATT_BOM_ITEM_NUMBER, kid);
    p.put(ItemConstants.ATT_BOM_QTY, ""+quantity);
    {}//Logwriter.printOnConsole(kid + " ["+quantity+"] added");
    bomTable.createRow(p);
  }

  private void addNewSubComponent(IItem root, IItem kid, int quantity) throws Exception {
    if (null==root) throw new NullPointerException("Root item is null");
    if (null==kid) throw new NullPointerException("Subcomponent name is null");
    ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
    if (isInBOM(bomTable, kid.getName())) throw new Duplicate("BOM entry: " + kid.getName());
    Map p = new HashMap();
    p.put(ItemConstants.ATT_BOM_ITEM_NUMBER, kid.getName());
    p.put(ItemConstants.ATT_BOM_QTY, ""+quantity);
    bomTable.createRow(p);
  }

  private boolean isInBOM(ITable bomTable, String number) throws Exception {
    IRow row;
    String itemNumber;
    ITwoWayIterator i = bomTable.getTableIterator();
    while(i.hasNext()) {
      row = (IRow)i.next();
      itemNumber = row.getValue(ItemConstants.ATT_BOM_ITEM_NUMBER).toString();
      if (number.equals(itemNumber)) return true;
    }
    return false;
  }

  private void removeSubComponent(IItem root, String kid, Authentication id) throws Exception {
    Collection deletes = new Vector();
    ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
    if (bomTable==null) return;
    IRow row;
    IItem part;
    String partNumber;
    ITwoWayIterator i = bomTable.getTableIterator();
    while(i.hasNext()) {
      row = (IRow)i.next();
      partNumber = row.getValue(ItemConstants.ATT_BOM_ITEM_NUMBER).toString();
      if (kid.equals(partNumber)) {
        part = findItem(partNumber, id);
        if (null==part) continue;
        if (part.getAgileClass().getName().equalsIgnoreCase(root.getAgileClass().getName())) deletes.add(row);
      }
    }
    Iterator j = deletes.iterator();
    while(j.hasNext()) {
      row = (IRow) j.next();
      bomTable.removeRow(row);
    }
  }













  //Session management
  private IAgileSession login(Authentication id) throws Exception {
    logout(id);
    IAgileSession session;
    {}//Logwriter.printOnConsole("logging " + id.getUsername() +" into " + getURL() + " pw=" + id.getPassword());
    AgileSessionFactory factory = AgileSessionFactory.getInstance(getURL());
    HashMap params = new HashMap();
    params.put(AgileSessionFactory.USERNAME, id.getUsername());
    params.put(AgileSessionFactory.PASSWORD, id.getPassword());
    session = factory.createSession(params);
    //session.setTimeout(Integer.MAX_VALUE);
    sessions.put(id.getUsername(), session);
    return session;
  }

  private void logout(Authentication id) throws Exception {
    if (null!=agileClasses) agileClasses.clear();
    agileClasses=null;
    IAgileSession session = (IAgileSession)sessions.get(id.getUsername());
    if (null==session) return;
    session.close();
    sessions.remove(id.getUsername());
  }

  private IAgileSession session(Authentication id) throws Exception {
    IAgileSession session = (IAgileSession)sessions.get(id.getUsername());
    if (null==session) session = login(id);
    if (!session.isOpen()) {
      logout(id);
      session = login(id);
    }
    return session;
  }

  private void purgeClosedSessions() throws Exception {
    Iterator i = sessions.keySet().iterator();
    String username;
    IAgileSession session;
    while (i.hasNext()) {
      username = i.next().toString();
      session = (IAgileSession)sessions.get(username);
      if (null!=session && !session.isOpen()) {
        sessions.remove(username);
        session.close();
      }
    }
  }


  //Agile Configuration

  //Agile Classes
  private Collection listClassNames() throws Exception {
    if (null==agileClasses) loadAgileClasses();
    Collection c = new Vector();
    c.addAll(agileClasses.keySet());
    return c;
  }


  private IAgileClass findAgileClass(String agileClassName) throws Exception {
    if (null==agileClasses) loadAgileClasses();
    return (IAgileClass)agileClasses.get(agileClassName);
  }
  private Integer findAgileClassID(String agileClassName) throws Exception {
    if (null==agileClasses) loadAgileClasses();
    IAgileClass c = (IAgileClass)agileClasses.get(agileClassName);
    if (null==c) throw new NameNotFound("Class " + agileClassName);
    return (Integer)c.getId();
  }
  private void loadAgileClasses() throws Exception {
    if (null==agileClasses) agileClasses = new HashMap();
    agileClasses.clear();
    IAgileSession session = session(getDefaultAuthentication());
    IAgileClass[] classes = session.getAdminInstance().getAgileClasses(IAdmin.CONCRETE);
    for (int idx=0; idx<classes.length; idx++) agileClasses.put(classes[idx].getName(), classes[idx]);
  }
  private IAgileClass findAgileClass(Integer agileClassID) throws Exception {
    IAgileSession session = session(getDefaultAuthentication());
    IAdmin admin = session.getAdminInstance();
    return admin.getAgileClass(agileClassID);
  }


  //Agile Attributes
  private Collection listAttributeNames(String agilesClassName) throws Exception {
    Collection c = new TreeSet(new ObjectComparator());
    Iterator i = findAttributes(agilesClassName).keySet().iterator();
    while(i.hasNext()) c.add(i.next().toString());
    return c;
  }

  private Map findAttributes(String agileClassName) throws Exception {
    if (null==classAttributes.get(agileClassName)) loadAttributes(agileClassName);
    return (Map) classAttributes.get(agileClassName);
  }

  private void loadAttributes(String agileClassName) throws Exception {
    Map definedAttributes = MapUtil.getMapFromMap(classAttributes, agileClassName);
    definedAttributes.clear();
    Collection c=null;
    Collection atts = findVisibleAttributes(findAgileClass(agileClassName));
    c = new Vector();
    Iterator i = atts.iterator();
    IAttribute att=null;
    String key;
    while(i.hasNext()) {
      att = (IAttribute) i.next();
      c.add(att.getName());
      key = att.getName().toLowerCase();
      //if( key is ignored attribute) continue;
      definedAttributes.put(key, att);
      definedAttributes.put(XMLString.asXMLName(key), att);
    }
  }

  private Collection findVisibleAttributes(IAgileClass c) throws Exception {
    ArrayList visibleAtts = new ArrayList();
    //if (c.isAbstract()) return visibleAtts;
    visibleAtts.addAll(findVisibleAttributes(c.getTableDescriptor(TableTypeConstants.TYPE_PAGE_ONE)));
    visibleAtts.addAll(findVisibleAttributes(c.getTableDescriptor(TableTypeConstants.TYPE_PAGE_TWO)));
    visibleAtts.addAll(findVisibleAttributes(c.getTableDescriptor(TableTypeConstants.TYPE_PAGE_THREE)));
    return visibleAtts;
  }

  private Collection findVisibleAttributes(ITableDesc table) throws Exception {
    Collection visibleAtts = new ArrayList();
    IAttribute[] atts = null;
    if (null==table) return visibleAtts;
    atts = table.getAttributes();
    for (int idx=0; idx < atts.length; idx++) {
      if (isVisible(atts[idx])) visibleAtts.add(atts[idx]);
    }
    return visibleAtts;
  }

  private boolean isVisible(IAttribute att) throws APIException {
    IProperty visible = att.getProperty(PropertyConstants.PROP_VISIBLE);
    if (null==visible) return false;
    Object v = visible.getValue();
    if (null==v) return false;
    return v.toString().equalsIgnoreCase("Yes");
  }

  private boolean isRequired(IAttribute att) throws APIException {
    if(!isVisible(att)) return false;
    IProperty required = att.getProperty(PropertyConstants.PROP_REQUIRED);
    if (null==required) return false;
    Object v = required.getValue();
    if (null==v) return false;
    return v.toString().equalsIgnoreCase("Yes");
  }

  //Fundamentals
  private IAgileObject createAgileObject(String agileClass, String partnumber, Authentication id) throws Exception {
    IAgileSession session = session(id);
    return session.createObject(findAgileClassID(agileClass), partnumber);
  }

  private IAgileObject createAgileObject(String agileClass, Map attributes, Authentication id) throws Exception {
    IAgileSession session = session(id);
    return session.createObject(findAgileClassID(agileClass), attributes);
  }

  /*
  //axis client not loading properly
  private void attachFile(IItem item, File f) throws Exception {
    ITable table = item.getTable(ItemConstants.TABLE_ATTACHMENTS);
    IRow row = table.createRow(f);
  }
  */

  private boolean hasNotChanged(Metadata data, IItem item) throws Exception {
   //If this is from Intralink, really should check that Name, Branch, Revision and Version have not changed
   if (item.getRevision().equalsIgnoreCase(data.get(ATT_REVISION))) return false;
   return true;
  }

  private String findNextRevision(IItem item) {
    try {
      String rev = item.getRevision();
      if (null==rev);
      if ("(?)".equals(rev)) return "A";
      if ("Introductory".equals(rev)) return "B";
      if (rev.length()!=1) return "";
      if ("Z".equals(rev)) return "";
      char r = rev.charAt(0);
      r+=1;
      return ""+r;
    }
    catch(Exception e) { e.printStackTrace(); return ""; }
  }

  public void createECO(Metadata data, Authentication id) throws Exception {
    IItem root = findItem(data, id);
    if (root==null) throw new NameNotFound(data.getName());
    String ecoNumber = data.getName()+dot+data.get(ATT_BRANCH)+dot+data.get(ATT_REVISION)+dot+data.get(ATT_VERSION);
    //if (hasPendingChange(data)) throw new PendingChangeExists(data);

    IChange eco = createECO(ecoNumber, id);
    includeAffectedItem(data, eco, id);
    root.setRevision(eco);
    //addAffectedItems();
  }

  private IChange createECO(String ecoNumber, Authentication id) throws Exception {
    IChange change = (IChange)session(id).createObject(ChangeConstants.CLASS_ECO, ecoNumber);
    change.setWorkflow(change.getWorkflows()[0]);
    return change;
  }

  private void includeAffectedItem(Metadata data, IChange eco, Authentication id) throws Exception {
    IItem item = findItem(data, id);
    if (hasNotChanged(data, item)) return;
    ITable affectedItems = eco.getTable(ChangeConstants.TABLE_AFFECTEDITEMS);
    if (!isNewAffectedItem(data, item, affectedItems)) return;

    Map params = new HashMap();
    params.put(ChangeConstants.ATT_AFFECTED_ITEMS_ITEM_NUMBER, item);
    params.put(ChangeConstants.ATT_AFFECTED_ITEMS_NEW_REV, findNextRevision(item));
    IRow affectedItemRow = affectedItems.createRow(params);
  }

  private boolean listedAsAffectedItem(String itemNumber, ITable affectedItems) throws Exception {
    ITwoWayIterator i = affectedItems.getTableIterator();
    String number;
    while (i.hasNext()) {
      IRow row = (IRow)i.next();
      number = row.getValue(ChangeConstants.ATT_AFFECTED_ITEMS_ITEM_NUMBER).toString();
      if (number.equals(itemNumber)) return true;
    }
    return false;
  }

  private boolean isNewAffectedItem(Metadata data, IItem item, ITable affectedItems) throws Exception {
    if (hasNotChanged(data, item)) return false;
    //item has changed - so check if it has already been added
    IItem affectedItem;
    Iterator i = affectedItems.getReferentIterator();
    while(i.hasNext()) {
      affectedItem = (IItem)i.next();
      if (affectedItem.getName().equals(item.getName())) return false;
    }
    return true;
  }

  private void synchronizeItem(Metadata data, IItem agileItem) throws Exception {
    Origin a  = data.getOrigin();
    AgileOrigin b = materializeOrigin(agileItem);
    if (null==a) ((MetadataBase)data).setOrigin(b);
    else SynchronizationSvc.record(a, b);
  }

  private void unsynchronizeItem(AgileOrigin o) throws Exception {
    SynchronizationSvc.purgeByUID(o.getDomainName(), o.getServerName(), o.getDatasourceName(), o.getUniqueID());
  }


  private void resynchronizeToNewItem(IItem item, IItem newItem) throws Exception {
    throw new UnsupportedOperation("resynchronize");
  }


  //returns the subset of itemParentMap of items that are deleted relative to metadataParentMap
  private Map findRemovedItems(String agileClassName, Map itemParentMap, Map metadataParentMap) throws Exception {
    String name;
    Map m = new HashMap();
    Iterator i = itemParentMap.keySet().iterator();
    Pair p;
    IItem kid;
    while (i.hasNext()) {
      name = i.next().toString();
      if (name.length()>4 && "-doc".equals(name.substring(name.length()-4))) continue; //do not delete docs
      if (metadataParentMap.containsKey(name)) continue; //item has not been deleted
      //item is in itemParentMap, but not in metadataParentMap - it may have been deleted
      p = (Pair) itemParentMap.get(name);
      kid = (IItem) p.getObject0();
      if (agileClassName.equals(kid.getAgileClass().getName())) m.put(name, p);
      //preserve items that are not the type as the root
    }
    return m;
  }

  //returns the subset of metadataParentMap of items that have been added relative to itemParentMap
  private Map findAddedMetadata(Map itemParentMap, Map metadataParentMap) {
    String name;
    Map m = new HashMap();
    Iterator i = metadataParentMap.keySet().iterator();
    Pair p;
    while (i.hasNext()) {
      name = i.next().toString();
      if (itemParentMap.containsKey(name)) continue; //item has not been deleted
      p = (Pair) metadataParentMap.get(name);
      m.put(name, p);
    }
    return m;
  }

  //returns the subset of metadataParentMap of items that have been added relative to itemParentMap
  private Map findModifiedMetadata(Map itemParentMap, Map metadataParentMap) throws Exception {
    String name;
    Map m = new HashMap();
    Iterator i = metadataParentMap.keySet().iterator();
    Pair p;
    IItem itemKid;
    Metadata dataKid;
    while (i.hasNext()) {
      name = i.next().toString();
      if (!itemParentMap.containsKey(name)) continue; //item has not been deleted
      p = (Pair) itemParentMap.get(name);
      itemKid = (IItem)p.getObject0();
      p = (Pair) metadataParentMap.get(name);
      dataKid = (Metadata)p.getObject0();
      if (hasNotChanged(dataKid, itemKid)) continue;
      m.put(name, p);
    }
    return m;
  }

  //mapParents returns a nested map structure of:
  // Map <kidName, Pair(kid, ParentMap)>
  // where structure for ParentMap is:
  // Map <parentName, parent>
  // Same structure is used for kid and parent types for both metadata and IItems
  private Map mapParents(Metadata data) {
    Map m = new HashMap();
    Map parentMap = lookupParentMap(m, data.get(ATT_PART_NUMBER), data);
    parentMap.put(data.get(ATT_PART_NUMBER), null); //no parent for root object
    mapParents(m, data);
    return m;
  }

  private void mapParents(Map m, Metadata parent) {
    Metadata kid;
    if (!parent.hasSubComponents()) return;
    Iterator i = parent.getSubComponents().iterator();
    Map parentMap;
    while(i.hasNext()) {
      kid = (Metadata) i.next();
      parentMap = lookupParentMap(m, kid.get(ATT_PART_NUMBER), kid);
      parentMap.put(parent.get(ATT_PART_NUMBER), parent);
      if (kid.hasSubComponents()) mapParents(m, kid);
    }
  }


  private Map mapParents(IItem item) throws Exception {
    Map m = new HashMap();
   //add item into m with no parents
    Map parentMap = lookupParentMap(m, item.getName(), item);
    mapParents(m, item);
    return m;
  }

  private void mapParents(Map m, IItem parent) throws Exception {
    IItem kid;
    Map parentMap;
    ITable bomTable = parent.getTable(ItemConstants.TABLE_BOM);
    Iterator i = bomTable.getReferentIterator();
    while(i.hasNext()) {
      kid = (IItem) i.next();
      parentMap = lookupParentMap(m, kid.getName(), kid);
      parentMap.put(parent.getName(), parent);
      mapParents(m, kid);
    }
  }

  private Map lookupParentMap(Map m, String kidName, Object kid) {
    Pair p;
    Map parentMap;
    p = MapUtil.getPairFromMap(m, kidName);
    p.setObject0(kid);
    parentMap = (Map)p.getObject1();
    if (null==parentMap) {
      parentMap = new HashMap();
      p.setObject1(parentMap);
    }
    return parentMap;
  }


  //simple map of components
  private Map mapAllComponents(Metadata data) {
    Map m = new HashMap();
    m.put(data.get(ATT_PART_NUMBER), data);
    if (data.hasSubComponents()) mapAllComponents(m, data.getSubComponents());
    return m;
  }

  private void mapAllComponents(Map m, Collection subComponents) {
    Metadata data;
    Iterator i = subComponents.iterator();
    while(i.hasNext()) {
      data = (Metadata) i.next();
      m.put(data.get(ATT_PART_NUMBER), data);
      if (data.hasSubComponents()) mapAllComponents(m, data.getSubComponents());
    }
  }


  //Some Utilities
  private void printList(IAgileList list, int level) throws APIException {
    if (list != null ) {
      {}//Logwriter.printOnConsole(list.getLevelName() + ":" + list.getValue() + ":" + list.getId());
      Object[] children = list.getChildren();
      if (children != null) {
        for (int i = 0; i < children.length; ++i) {
          printList((IAgileList)children[i], level + 1);
        }
      }
    }
  }

  public String getURL() { return uri+":"+port+"/" + getWebPath(); }
  public String getWebPath() { return webPath; }
  public void setWebPath(String s) { webPath=s; }
  public String getURI() { return uri; }
  public void setURI(String s) { uri=s; }
  public int getPort() { return port; }
  public void setPort(int i) { port=i; }

  private Authentication getDefaultAuthentication() {
    try {
     Authentication id = new Authentication(defaultUsername, defaultPassword);
     return id;
    }
    catch (Exception e) {
     e.printStackTrace();
     return null;
    }
  }

  public String getDefaultUsername() { return defaultUsername; }
  public void setDefaultUsername(String s) { defaultUsername=s; }
  public String getDefaultPassword() { return defaultPassword; }
  public void setDefaultPassword(String s) { defaultPassword=s; }







  public String publish(Collection metadataList, Authentication id) throws Exception {
    if (null==metadataList || 0==metadataList.size()) return null;
    zws.util.PrintUtil.println("##################");
    zws.util.PrintUtil.println(id.getUsername());
    zws.util.PrintUtil.println("##################");
    zws.util.PrintUtil.println(id.getPassword());
    zws.util.PrintUtil.println("##################");
    //zws.util.PrintUtil.println(metadataList);
    Iterator i = metadataList.iterator();
    Metadata m;
    String s=null;
    while (i.hasNext()) {
      m = (Metadata) i.next();
      s = publish(m, id);
      zws.util.PrintUtil.println("-----------");
      zws.util.PrintUtil.println(s);
    }
    return s;
  }

  public String publish(Metadata m, Authentication id) throws Exception {
    {}//Logwriter.printOnConsole(m);
    String xmlIn = m.toString();
    {}//Logwriter.printOnConsole("INPUT------------------------");
    {}//Logwriter.printOnConsole(xmlIn);
    {}//Logwriter.printOnConsole("INPUT------------------------");
    Stylizer styler = new Stylizer();
    styler.addProcessingInstruction(Properties.get(Names.xsltAGILE_DATA));
    ByteArrayInputStream inStream = new ByteArrayInputStream(xmlIn.getBytes("UTF-8"));
    String agileData = styler.styleXML(inStream).toString();
    PrintUtil.println("Publishing BOM into agile: ---------------");
    PrintUtil.println(agileData);
    PrintUtil.println("------------------------------------------");
    if ("true".equalsIgnoreCase(Properties.get(Names.PUBLISH_TO_AGILE_ENABLED))) {
      ByteArrayInputStream agileDataStream= new ByteArrayInputStream(agileData.getBytes("UTF-8"));
      AgileConnectorPub ac = new AgileConnectorPub();
      String errorMessage=null;
      StringBuffer result=null;
      boolean connected = ac.connect(id.getUsername(), id.getPassword());
      if (connected) {
        result = ac.publish(agileDataStream);
        if (result==null) errorMessage=ac.getErrorMessage();
      }
      else {
        errorMessage = ac.getErrorMessage();
      }
      if (null!=errorMessage || null==result) {} {}//Logwriter.printOnConsole("Error Message: " + errorMessage);
      return result.toString();
    }
    else if ("exec".equalsIgnoreCase(Properties.get(Names.PUBLISH_TO_AGILE_ENABLED))) {
      PublishBill publisher = new PublishBill();
      publisher.setBillXML(agileData);
      publisher.setAuthentication(id);
      publisher.execute();
      return publisher.getResponseXML();
    }
    else {
      {}//Logwriter.printOnConsole("-->Publishing to Agile is Disabled!");
      return  "<agile-data><metadata number=\"Publishing\" revision=\"XX\" message=\"Disabled\" message-type=\"error\"><metadata number=\"th125-04\" revision=\"XX\" message=\"th125-04 published.\" message-type=\"information\"/><metadata number=\"pwr-plate-191-04\" revision=\"XX\" message=\"pwr-plate-191-04 published.\" message-type=\"information\"/><metadata number=\"471-00013-04\" revision=\"XX\" message=\"471-00013-04 published.\" message-type=\"information\"/><metadata number=\"28-7305-04\" revision=\"XX\" message=\"28-7305-04 published.\" message-type=\"information\"/><metadata number=\"29-1564-04\" revision=\"XX\" message=\"29-1564-04 published.\" message-type=\"information\"/></metadata></agile-data>";
    }    
  }

  private AgileOrigin materializeOrigin(IItem item) throws Exception {
    return new AgileOrigin(getName(), item.getObjectId().toString(), item.getAgileClass().getName(), item.getName());
  }

  private ECO unmarshall(IChange changeOrder) throws Exception {
    String number=null, description=null, status=null, defaultNextStatus=null, workflow=null;
    number = changeOrder.getName();
    if (null!= changeOrder.getStatus()) status = changeOrder.getStatus().toString();
    if (null!=changeOrder.getDefaultNextStatus()) defaultNextStatus= changeOrder.getDefaultNextStatus().toString();
    if (null!=changeOrder.getWorkflow())workflow = changeOrder.getWorkflow().getName();
    description = changeOrder.getValue(ChangeConstants.ATT_COVER_PAGE_DESCRIPTION).toString();
    //analyst= changeOrder.getValue(ChangeConstants.ATT_COVER_PAGE_CHANGE_ANALYST).toString();
    //= changeOrder.getValue(ChangeConstants.ATT_COVER_PAGE_CHANGE_CATEGORY).toString();
    //= changeOrder.getValue(ChangeConstants.ATT_COVER_PAGE_DATE_ORIGINATED_).toString();
    if ("".equals(description)) description=null;
    ECO eco = new ECO(number, description, status, workflow, defaultNextStatus);
    ITable affectedItems = changeOrder.getTable(ChangeConstants.TABLE_AFFECTEDITEMS);
    ITwoWayIterator i = affectedItems.getTableIterator();
    AffectedItem item;
    while (i.hasNext()) {
      IRow row = (IRow)i.next();
      item = new AffectedItem();
      item.setItemNumber(row.getValue(ChangeConstants.ATT_AFFECTED_ITEMS_ITEM_NUMBER).toString());
      item.setDescription(row.getValue(ChangeConstants.ATT_AFFECTED_ITEMS_ITEM_DESCRIPTION).toString());
      item.setOldRev(row.getValue(ChangeConstants.ATT_AFFECTED_ITEMS_OLD_REV).toString());
      item.setNewRev(row.getValue(ChangeConstants.ATT_AFFECTED_ITEMS_NEW_REV).toString());
      item.setOldLifeCyclePhase(row.getValue(ChangeConstants.ATT_AFFECTED_ITEMS_OLD_LIFECYCLE_PHASE).toString());
      item.setNewLifeCyclePhase(row.getValue(ChangeConstants.ATT_AFFECTED_ITEMS_LIFECYCLE_PHASE).toString());
      eco.add(item);
    }
    return eco;
   }

  private Metadata unmarshall(IDataObject data) throws Exception {
    AgileOrigin o = materializeOrigin((IItem)data);
    MetadataBase d = new MetadataBase();
    d.setOrigin(o);
    d.setName(data.getName());
    d.set(ATT_AGILE_CLASS_TYPE, data.getAgileClass().getName());
    d.set(ATT_PART_NUMBER, data.getName());

    Map definedAttributes = findAttributes(d.get(ATT_AGILE_CLASS_TYPE));

    Iterator i = definedAttributes.keySet().iterator();
    String att, value;
    IAttribute a;
    Object val;
    while (i.hasNext()){
      att = (String)i.next();
      a = (IAttribute)definedAttributes.get(att);
      val = data.getValue(a);
      if (null==val) continue;
      value = val.toString();
      if ("".equals(value.trim())) continue;
      d.set(att, value);
      {}//Logwriter.printOnConsole(att + "::=" + value);
    }
    {}//Logwriter.printOnConsole(d);
    return d;
  }


  //network location1
  private String webPath="Agile";
  //private String webPath="Everest_ZeroWait-State/HcmServlet";
  private String uri=null;
  private int port=8888;

  //session
  private Map sessions = new HashMap();
  private String defaultPassword=null;
  private String defaultUsername=null;

  //configuration
  private Map agileClasses=null;
  private Map classAttributes = new HashMap();

  //Static Definitions

  private static String dot = ".";

  //Metadata ATTRIBUTES
  public static String ATT_AGILE_CLASS_TYPE = "agile-class";
  public static String ATT_PART_NUMBER = "number";
  public static String ATT_DESCRIPTION= "description";
  public static String ATT_BRANCH = "branch";
  public static String ATT_REVISION = "rev";
  public static String ATT_VERSION = "ver";
  public static String ATT_RELEASE_PHASE= "life cycle phase";

  //CLASS TYPES
  public static String CLASS_TYPE_ALL = "all";
  //Documents
  public static String CLASS_TYPE_DOCUMENT = "Document";
  public static String CLASS_TYPE_CAD_DOCUMENT = "CAD Document";
  //Parts
  public static String CLASS_TYPE_PART = "Part";
  public static String CLASS_TYPE_ASSEMBLY = "Assembly";
  public static String CLASS_TYPE_CAD_PART = "CAD Part";

}













/*
private void storeItem(Metadata data, Authentication id) throws Exception {
  IItem root = findItem(data, id);
  if (null==root) root = createItem(data, id);
  else if (data.getOrigin()==null)
      else if (!SynchronizationSvc.isSynchronizedToDatasource(data.getOrigin(), this) {

    updateItem(root, data);
  }
  storeItemSubComponents(root, data.getSubComponents(), id);
}

private void storeItemSubComponents(IItem root, Collection c, Authentication id) throws Exception {
  clearSubComponents(root);
  if (null==c || c.size()==0) return;
  Iterator i = c.iterator();
  MetadataSubComponent k;
  IItem kid;
  while (i.hasNext()) {
    k = (MetadataSubComponent) i.next();
    kid = findItem(k.get(ATT_PART_NUMBER));
    storeBill(kid, k.getSubComponents());
  }
}

private void storeSubComponent(IItem root, MetadataSubComponent kid, Authentication id) throws Exception {
  if (null==root) throw new InvalidName("Parent name is null!");
  IDataObject k = (IDataObject)findItem(kid.get(ATT_PART_NUMBER), id);
  if (null==k) {
      k = (IDataObject)create(kid.get(ATT_AGILE_CLASS_TYPE), kid.get(ATT_PART_NUMBER), id);
  }
  update(k, kid);
  ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
  storeSubComponent(root, kid.get(ATT_PART_NUMBER), kid.getQuantity(), id);
}

private void storeSubComponent(String root, String kid, int quantity, Authentication id) throws Exception {
  if (null==root) throw new InvalidName("Parent name is null!");
  IItem r = findItem(root, id);
  if (null==r) throw new NameNotFound(root);
  storeSubComponent(r, kid, quantity, id);
}

private void storeSubComponent(IItem root, String kid, int quantity, Authentication id) throws Exception {
  if (null==root) throw new NullPointerException("Parent is null");
  if (null==kid) throw new InvalidName("Subcomponent name is null!");
  IItem k = findItem(kid, id);
  if (null==k) throw new NameNotFound(kid);
  ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
  IRow row;
  ITwoWayIterator i = bomTable.getTableIterator();
  String partNumber;
  IItem part;
  boolean alreadyHasKid = false;
  while(i.hasNext()) {
    row = (IRow)i.next();
    partNumber = row.getValue(ItemConstants.ATT_BOM_ITEM_NUMBER).toString();
    if (kid.equals(partNumber)) { //kid is already a subcomponent - update its quantity
      alreadyHasKid = true;
      row.setValue(ItemConstants.ATT_BOM_QTY, ""+quantity);
    }
  }
  if (alreadyHasKid) return;
  //kid was not already a subcomponent, add it
  Map p = new HashMap();
  p.put(ItemConstants.ATT_BOM_ITEM_NUMBER, kid);
  p.put(ItemConstants.ATT_BOM_QTY, ""+quantity);
  bomTable.createRow(p);
}

private void addNewSubComponents(IItem root, Collection metadataSubComponents, Authentication id) throws Exception {
  if (null==root) throw new NullPointerException("Root item is null");
  if (null==metadataSubComponents || metadataSubComponents.size()==0) return;
  ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
  Map p;
  MetadataSubComponent kid;
  IDataObject k;
  Iterator i = metadataSubComponents.iterator();
  String partnumber, agileClassName;
  while (i.hasNext()) {
    kid = (MetadataSubComponent) i.next();
    partnumber = kid.get(ATT_PART_NUMBER);
    k = (IDataObject)findItem(partnumber, id);
    if (null==k) {
      agileClassName= kid.get(ATT_AGILE_CLASS_TYPE);
      k = (IDataObject)createItem(agileClassName, partnumber, id);
    }
    update(k, kid);
    p = new HashMap();
    p.put(ItemConstants.ATT_BOM_ITEM_NUMBER, kid.get(ATT_PART_NUMBER));
    p.put(ItemConstants.ATT_BOM_QTY, ""+kid.getQuantity());
    bomTable.createRow(p);
  }
}
*/
