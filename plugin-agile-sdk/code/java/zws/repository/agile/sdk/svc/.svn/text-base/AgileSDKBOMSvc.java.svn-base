/*
 * DesignState - Design Compression Technology @author: ptoleti @version: 1.0
 * Created on Jul 10, 2007 9:55:03 AM Copywrite (c) 2007 Zero Wait-State Inc.
 * All rights reserved
 */
package zws.repository.agile.sdk.svc;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;

import zws.repository.agile.sdk.AgileSDKConstants;
import zws.repository.agile.sdk.AgileSDKRepositoryBase;
import zws.security.Authentication;
import zws.data.Metadata;
import zws.data.MetadataSubComponent;
import zws.data.MetadataSubComponentBase;
//import zws.datasource.agile.op.PublishBill;
import zws.exception.Duplicate;
import zws.exception.NameNotFound;
import zws.util.MapUtil;
import zws.util.Pair;
import zws.application.Names;
import zws.bill.intralink.BillOfMaterials;
import com.agile.api.IItem;
import com.agile.api.IRow;
import com.agile.api.ITable;
import com.agile.api.ITwoWayIterator;
import com.agile.api.ItemConstants;
import com.agile.api.IDataObject;
//import com.agile.zws.connector.AgileConnectorPub;


/**
 * The Class BOMUtility.
 *
 * @author ptoleti
 */
public class AgileSDKBOMSvc extends AgileSDKItemSvc {

  /**
   * The Constructor.
   *
   * @param repository repository
   * @param configUtil the config util
   * @param sessionUtil the session util
   */
  protected AgileSDKBOMSvc(AgileSDKSessionSvc sessionUtil,
      AgileSDKConfigurationSvc configUtil, AgileSDKRepositoryBase repository) {
    super(sessionUtil, configUtil, repository);
  }

  /**
   * Adds the new sub component.
   *
   * @param root the root
   * @param quantity the quantity
   * @param kid the kid
   *
   * @throws Exception the exception
   */
  public void addNewSubComponent(IItem root, IItem kid, int quantity)
      throws Exception {
    checkArgs(root, kid);
    ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
    if (isInBOM(bomTable, kid.getName())) {
      throw new Duplicate("BOM entry: " + kid.getName());
    }
    Map p = new HashMap();
    p.put(ItemConstants.ATT_BOM_ITEM_NUMBER, kid.getName());
    p.put(ItemConstants.ATT_BOM_QTY, "" + quantity);
    bomTable.createRow(p);
  }

  /**
   * Adds the new sub component.
   *
   * @param id the id
   * @param root the root
   * @param quantity the quantity
   * @param kid the kid
   *
   * @throws Exception the exception
   */
  private void addNewSubComponent(IItem root, String kid, int quantity,
      Authentication id) throws Exception {
    checkArgs(root, kid);
    IItem k = findItem(kid, id);
    if (null == k) { throw new NameNotFound(kid); }
    ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
    Map p = new HashMap();
    p.put(ItemConstants.ATT_BOM_ITEM_NUMBER, kid);
    p.put(ItemConstants.ATT_BOM_QTY, "" + quantity);
    bomTable.createRow(p);
  }

  /**
   * Adds the new sub component.
   *
   * @param id the id
   * @param root the root
   * @param quantity the quantity
   * @param kid the kid
   *
   * @throws Exception the exception
   */
  public void addNewSubComponent(String root, String kid, int quantity,
      Authentication id) throws Exception {
    checkArgs(root, kid);
    IItem r = findItem(root, id);
    if (null == r) { throw new NameNotFound(root); }
    addNewSubComponent(r, kid, quantity, id);
  }

  /**
   * Checks if is in BOM.
   *
   * @param bomTable the bom table
   * @param number the number
   *
   * @return true, if is in BOM
   *
   * @throws Exception the exception
   */
  public boolean isInBOM(ITable bomTable, String number) throws Exception {
    IRow row;
    String itemNumber;
    ITwoWayIterator i = bomTable.getTableIterator();
    while (i.hasNext()) {
      row = (IRow) i.next();
      itemNumber = row.getValue(ItemConstants.ATT_BOM_ITEM_NUMBER).toString();
      if (number.equals(itemNumber)) { return true; }
    }
    return false;
  }

  /**
   * Replace first level BOM.
   *
   * @param id the id
   * @param parents the parents
   *
   * @throws Exception the exception
   */
  public void replaceFirstLevelBOM(Collection parents, Authentication id)
      throws Exception {
    if (null == parents) { return; }
    Iterator i = parents.iterator();
    while (i.hasNext()) {
      replaceFirstLevelBOM((Metadata) i.next(), id);
    }
  }

  /**
   * Replace first level BOM.
   *
   * @param id the id
   * @param data the data
   *
   * @throws Exception the exception
   */
  public void replaceFirstLevelBOM(Metadata data, Authentication id)
      throws Exception {
    try {
      //sessions.login(id);
      IItem root = findItem(data, id);
      if (root == null) { throw new NameNotFound(data.getName()); }
      ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
      try {
        clearTable(bomTable);
      } catch (Exception e) {
        System.out.println("!!ERROR: " + e.getMessage());
      }
      Metadata kid;
      // add items
      Iterator i = data.getSubComponents().iterator();
      while (i.hasNext()) {
        kid = (Metadata) i.next();
        addNewSubComponent(data.get(AgileSDKConstants.ATT_PART_NUMBER), kid
            .get(AgileSDKConstants.ATT_PART_NUMBER),
            ((MetadataSubComponent) kid).getQuantity(), id);
      }
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Link kids.
   *
   * @param item the item
   *
   * @return the map
   *
   * @throws Exception the exception
   */
  public Map linkKids(IItem item) throws Exception {
    Map links = new HashMap();
    linkKids(links, item);
    return links;
  }

  /**
   * Link kids.
   *
   * @param parent the parent
   * @param links the links
   *
   * @throws Exception the exception
   */
  private void linkKids(Map links, IItem parent) throws Exception {
    IItem kid;
    ITable bomTable = parent.getTable(ItemConstants.TABLE_BOM);
    Iterator i = bomTable.getReferentIterator();
    Pair p;
    String linkKey = null;
    while (i.hasNext()) {
      kid = (IItem) i.next();
      linkKey = materializeLinkKey(parent, kid);
      if (links.containsKey(linkKey)) {
        continue;
      }
      p = new Pair(parent, kid);
      links.put(linkKey, p);
      linkKids(links, kid);
    }
  }

  /**
   * Link kids.
   *
   * @param parent the parent
   * @param links the links
   */
  private void linkKids(Map links, Metadata parent) {
    MetadataSubComponent kid;
    if (!parent.hasSubComponents()) { return; }

    Iterator i = parent.getSubComponents().iterator();
    Pair p;
    String linkKey = null;
    while (i.hasNext()) {
      kid = (MetadataSubComponent) i.next();
      linkKey = materializeLinkKey(parent, kid);
      if (links.containsKey(linkKey)) {
        continue;
      }
      p = new Pair(parent, kid);
      links.put(linkKey, p);
      if (kid.hasSubComponents()) {
        linkKids(links, kid);
      }
    }
  }

  // parent-subcompnent links can be defined by different attributes
  // same subcomponent can be found more than once under a parent
  // due to different layout or find number or other specific relationship
  // attrbiute
  // linkParents returns a map with unique relationships
  // key = link-key
  // value = pair(parent, child)
  /**
   * Link kids.
   *
   * @param data the data
   *
   * @return the map
   */
  public Map linkKids(Metadata data) {
    Map links = new HashMap();
    if (!data.hasSubComponents()) { return links; }

    linkKids(links, data);
    return links;
  }

  /**
   * Store bill.
   *
   * @param id the id
   * @param bill the bill
   *
   * @throws Exception the exception
   */
  public void storeBill(BillOfMaterials bill, Authentication id)
      throws Exception {
    Metadata data = bill.getMetadata();
    structureBill(data, id);
  }

  /**
   * Store bill.
   *
   * @param dataList the data list
   * @param id the id
   *
   * @throws Exception the exception
   */
  public void storeBill(Collection dataList, Authentication id)
      throws Exception {
    BillOfMaterials bill;
    Iterator i = dataList.iterator();
    while (i.hasNext()) {
      bill = (BillOfMaterials) i.next();
      storeBill(bill, id);
    }
  }

  /*
   * DELETE BLOCK String num=""; for (int i = 5000; i < 5390; i++) { num =
   * "0000000"+i; num = num.substring(num.length()-7) + "-000"; num += ""; try {
   * delete(num,id); delete(num+"-doc",id); } catch(Exception e) {} } num += "";
   */

  /**
   * Structure bill.
   *
   * @param dataList the data list
   * @param id the id
   *
   * @throws Exception the exception
   */
  public void structureBill(Collection dataList, Authentication id)
      throws Exception {
    Metadata data;
    Iterator i = dataList.iterator();
    while (i.hasNext()) {
      data = (Metadata) i.next();
      structureBill(data, id);
    }
  }

  /**
   * Structure bill.
   *
   * @param id the id
   * @param data the data
   *
   * @throws Exception the exception
   */
  public void structureBill(Metadata data, Authentication id) throws Exception {
    try {
      sessions.login(id);
      IItem root = findItem(data, id);
      if (root == null) { throw new NameNotFound(data.getName()); }
      Map dataParentMap;
      Map itemParentMap;
      Pair itemP, dataP;
      Map parents;
      Metadata dataKid, dataParent;
      IItem itemKid, itemParent;
      Iterator i, j;

      // +++check to see that all modified are preliminary

      dataParentMap = mapParents(data);
      // itemParentMap = mapParents(root);
      itemParentMap = mapAllParents(root, data, id);

      // remove items
      Map dels = findRemovedItems(root.getAgileClass().getName(),
          itemParentMap, dataParentMap);
      i = dels.values().iterator();
      while (i.hasNext()) {
        itemP = (Pair) i.next();
        itemKid = (IItem) itemP.getObject0();
        parents = (Map) itemP.getObject1();
        j = parents.values().iterator();
        while (j.hasNext()) {
          itemParent = (IItem) j.next();
          removeSubComponent(itemParent, itemKid.getName(), id);
        }
      }

      // add items
      // itemParentMap = mapParents(root);
      itemParentMap = mapAllParents(root, data, id);

      Map adds = findAddedMetadata(itemParentMap, dataParentMap);
      i = adds.values().iterator();
      while (i.hasNext()) {
        dataP = (Pair) i.next();
        dataKid = (Metadata) dataP.getObject0();
        parents = (Map) dataP.getObject1();
        j = parents.values().iterator();
        while (j.hasNext()) {
          dataParent = (Metadata) j.next();
          addNewSubComponent(dataParent.get(AgileSDKConstants.ATT_PART_NUMBER),
              dataKid.get(AgileSDKConstants.ATT_PART_NUMBER),
              ((MetadataSubComponent) dataKid).getQuantity(), id);
        }
      }

      // update quantities
      // itemParentMap = mapParents(root);
      // Map mods = findModifiedMetadata(itemParentMap, dataParentMap);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Update quantity.
   *
   * @param redlineORbomTable the redline O rbom table
   * @param quantity the quantity
   * @param kid the kid
   *
   * @throws Exception the exception
   */
  public void updateQuantity(ITable redlineORbomTable, String kid, int quantity)
      throws Exception {
    IRow row;
    String partNumber;
    String q;
    ITwoWayIterator i = redlineORbomTable.getTableIterator();
    while (i.hasNext()) {
      row = (IRow) i.next();
      partNumber = row.getValue(ItemConstants.ATT_BOM_ITEM_NUMBER).toString();
      if (kid.equals(partNumber)) {
        q = row.getValue(ItemConstants.ATT_BOM_QTY).toString();
      
        int currentAgileQty = -12344321; //initialize to some improbable Agile Qty value 
        try {currentAgileQty = Integer.parseInt(q);} 
        catch (NumberFormatException e) {}
        if (quantity == currentAgileQty) return;
   
        row.setValue(ItemConstants.ATT_BOM_QTY, "" + quantity);        
      }
    }
  }

  /**
   * Lookup parent map.
   *
   * @param kidName the kid name
   * @param m the m
   * @param kid the kid
   *
   * @return the map
   */
  private Map lookupParentMap(Map m, String kidName, Object kid) {
    Pair p;
    Map parentMap;
    p = MapUtil.getPairFromMap(m, kidName);
    p.setObject0(kid);
    parentMap = (Map) p.getObject1();
    if (null == parentMap) {
      parentMap = new HashMap();
      p.setObject1(parentMap);
    }
    return parentMap;
  }

  /*
   * private Map mapParents(IItem item) throws Exception {
   * LogWriter.printOnConsole("IITEM MAPPING PARENTS FOR " + item.getName());
   * Map m = new HashMap(); //add item into m with no parents Map parentMap =
   * lookupParentMap(m, item.getName(), item); mapParents(m, item); return m; }
   * private void mapParents(Map m, IItem parent) throws Exception { IItem kid;
   * Map parentMap; ITable bomTable = parent.getTable(ItemConstants.TABLE_BOM);
   * Iterator i = bomTable.getReferentIterator(); while(i.hasNext()) { kid =
   * (IItem) i.next(); parentMap = lookupParentMap(m, kid.getName(), kid);
   * parentMap.put(parent.getName(), parent); mapParents(m, kid);
   * LogWriter.printOnConsole("IITEM MAPPED " + parent.getName() + " as parent
   * of " + kid.getName()); } }
   */

  // mapParents returns a nested map structure of:
  // Map <kidName, Pair(kid, ParentMap)>
  // where structure for ParentMap is:
  // Map <parentName, parent>
  // Same structure is used for kid and parent types for both metadata and
  // IItems
  /**
   * Map all parents.
   *
   * @param id the id
   * @param item the item
   * @param data the data
   *
   * @return the map
   *
   * @throws Exception the exception
   */
  public Map mapAllParents(IItem item, Metadata data, Authentication id)
      throws Exception {
    // LogWriter.printOnConsole("IITEM MAPPING PARENTS FOR " + item.getName());
    Map m = new HashMap();
    lookupParentMap(m, item.getName(), item);
    mapAllParents(m, item, data, id);
    return m;
  }

  /**
   * Map all parents.
   *
   * @param id the id
   * @param item the item
   * @param data the data
   * @param m the m
   *
   * @throws Exception the exception
   */
  private void mapAllParents(Map m, IItem item, Metadata data, Authentication id)
      throws Exception {
    // LogWriter.printOnConsole("IITEM MAPPING PARENTS FOR " + item.getName());
    if (null != item) {
      mapFirstLevelParents(m, item);
    }
    if (!data.hasSubComponents()) { return; }
    Iterator i = data.getSubComponents().iterator();
    Metadata kidMetadata;
    IItem kidItem;
    while (i.hasNext()) {
      kidMetadata = (Metadata) i.next();
      kidItem = null;
      try {
        kidItem = findItem(kidMetadata.get(AgileSDKConstants.ATT_PART_NUMBER),
            id);
      } catch (Exception ignore) {
      }
      mapAllParents(m, kidItem, kidMetadata, id);
    }
  }

  /**
   * Map first level parents.
   *
   * @param parent the parent
   * @param m the m
   *
   * @throws Exception the exception
   */
  private void mapFirstLevelParents(Map m, IItem parent) throws Exception {
    IItem kid;
    Map parentMap;
    ITable bomTable = parent.getTable(ItemConstants.TABLE_BOM);
    Iterator i = bomTable.getReferentIterator();
    while (i.hasNext()) {
      kid = (IItem) i.next();
      parentMap = lookupParentMap(m, kid.getName(), kid);
      parentMap.put(parent.getName(), parent);
      // LogWriter.printOnConsole("IITEM~ MAPPED " + parent.getName() + " as
      // parent of " + kid.getName());
    }
  }

  /**
   * Map parents.
   *
   * @param parent the parent
   * @param m the m
   */
  private void mapParents(Map m, Metadata parent) {
    Metadata kid;
    if (!parent.hasSubComponents()) { return; }
    Iterator i = parent.getSubComponents().iterator();
    Map parentMap;
    while (i.hasNext()) {
      kid = (Metadata) i.next();
      parentMap = lookupParentMap(m,
          kid.get(AgileSDKConstants.ATT_PART_NUMBER), kid);
      parentMap.put(parent.get(AgileSDKConstants.ATT_PART_NUMBER), parent);
      if (kid.hasSubComponents()) {
        mapParents(m, kid);
      }
    }
  }

  // mapParents returns a nested map structure of:
  // Map <kidName, Pair(kid, ParentMap)>
  // where structure for ParentMap is:
  // Map <parentName, parent>
  // Same structure is used for kid and parent types for both metadata and
  // IItems
  /**
   * Map parents.
   *
   * @param data the data
   *
   * @return the map
   */
  public Map mapParents(Metadata data) {
    Map m = new HashMap();
    Map parentMap = lookupParentMap(m, data
        .get(AgileSDKConstants.ATT_PART_NUMBER), data);
    parentMap.put(data.get(AgileSDKConstants.ATT_PART_NUMBER), null); // no
                                                                      // parent
                                                                      // for
                                                                      // root
                                                                      // object
    mapParents(m, data);
    return m;
  }

  /**
   * Map all components.
   *
   * @param subComponents the sub components
   * @param m the m
   */
  private void mapAllComponents(Map m, Collection subComponents) {
    Metadata data;
    Iterator i = subComponents.iterator();
    while (i.hasNext()) {
      data = (Metadata) i.next();
      m.put(data.get(AgileSDKConstants.ATT_PART_NUMBER), data);
      if (data.hasSubComponents()) {
        mapAllComponents(m, data.getSubComponents());
      }
    }
  }

  // simple map of components
  /**
   * Map all components.
   *
   * @param data the data
   *
   * @return the map
   */
  public Map mapAllComponents(Metadata data) {
    Map m = new HashMap();
    m.put(data.get(AgileSDKConstants.ATT_PART_NUMBER), data);
    if (data.hasSubComponents()) {
      mapAllComponents(m, data.getSubComponents());
    }
    return m;
  }

  /**
   * Checks for sub component.
   *
   * @param id the id
   * @param subcomponent the subcomponent
   * @param parent the parent
   *
   * @return true, if has sub component
   *
   * @throws Exception the exception
   */
  public boolean hasSubComponent(String parent, String subcomponent,
      Authentication id) throws Exception {
    try {
      sessions.login(id);
      IDataObject p = findItem(parent, id);
      if (null == p) { throw new NameNotFound(parent); }
      ITable bomTable;
      bomTable = p.getTable(ItemConstants.TABLE_BOM);
      return isInBOM(bomTable, subcomponent);
    } catch (Exception e) {
      throw e;
    }
  }

  /*
   * DELETE BLOCK String num=""; for (int i = 5000; i < 5390; i++) { num =
   * "0000000"+i; num = num.substring(num.length()-7) + "-000"; num += ""; try {
   * delete(num,id); delete(num+"-doc",id); } catch(Exception e) {} } num += "";
   */

  /**
   * Materialize link key.
   *
   * @param parent the parent
   * @param kid the kid
   *
   * @return the string
   *
   * @throws Exception the exception
   */
  public String materializeLinkKey(IItem parent, IItem kid) throws Exception {
    return parent.getName() + AgileSDKConstants.DOT + kid.getName();
  }

  /**
   * Materialize link key.
   *
   * @param parent the parent
   * @param kid the kid
   *
   * @return the string
   */
  private String materializeLinkKey(Metadata parent, MetadataSubComponent kid) {
    return parent.get(AgileSDKConstants.ATT_PART_NUMBER)
        + AgileSDKConstants.DOT + kid.get(AgileSDKConstants.ATT_PART_NUMBER);
  }

  /*
   * //From Jason Brown private void attach(ITable table, String description,
   * URL url) throws Exception { System.out.println("Attaching " + description + ": " +
   * url.toString()); String value=null; Object o; IRow row=null; IRow returnRow =
   * null; String newURL = url.toString(); int
   * idx=newURL.toLowerCase().indexOf("name="); if (idx>-1) newURL =
   * newURL.substring(idx+5); System.out.println("looking for " + newURL);
   * ITwoWayIterator it = table.getTableIterator(); while (it.hasNext()) {
   * value=""; row = ((IRow) it.next()); o =
   * row.getValue(ItemConstants.ATT_ATTACHMENTS_FILE_NAME); if (null!=o) value =
   * o.toString(); System.out.println("found attachment value:" + value);
   * idx=value.toLowerCase().indexOf("name="); if (idx>-1) value =
   * value.substring(idx+5); System.out.println("checking attachment value:" +
   * value); if (value.equalsIgnoreCase(newURL)) { returnRow = row; break; } }
   * if (null!=returnRow) table.removeRow(returnRow); Map values = new
   * HashMap(); values.put(ItemConstants.ATT_ATTACHMENTS_FILE_NAME,
   * url.toString()); values.put(ItemConstants.ATT_ATTACHMENTS_FILE_DESCRIPTION,
   * description); values.put(ItemConstants.ATT_ATTACHMENTS_FILE_TYPE, "url");
   * returnRow = table.createRow(values); }
   */

  /**
   * Replace item.
   *
   * @param id the id
   * @param newPart the new part
   * @param part the part
   *
   * @throws Exception the exception
   */
  public void replaceItem(IDataObject part, IDataObject newPart,
      Authentication id) throws Exception {
    ITable whereUsedTable = part.getTable(ItemConstants.TABLE_WHEREUSED);
    IRow row;
    ITwoWayIterator i = whereUsedTable.getTableIterator();
    String partNumber;
    IItem root;
    while (i.hasNext()) {
      row = (IRow) i.next();
      partNumber = row.getValue(ItemConstants.ATT_WHERE_USED_ITEM_NUMBER)
          .toString();
      root = findItem(partNumber, id);
      replaceReference(root, part, newPart);
    }
  }

  /**
   * Replace reference.
   *
   * @param ref the ref
   * @param root the root
   * @param newRef the new ref
   *
   * @throws Exception the exception
   */
  private void replaceReference(IDataObject root, IDataObject ref,
      IDataObject newRef) throws Exception {
    Collection replacements = new Vector();
    ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
    IRow row;
    ITwoWayIterator i = bomTable.getTableIterator();
    String partNumber;
    while (i.hasNext()) {
      row = (IRow) i.next();
      partNumber = row.getValue(ItemConstants.ATT_BOM_ITEM_NUMBER).toString();
      if (partNumber.equals(ref.getName())) {
        replacements.add(row);
      }
    }
    Iterator j = replacements.iterator();
    String quantity;
    Map p = new HashMap();
    while (j.hasNext()) {
      row = (IRow) j.next();
      quantity = row.getValue(ItemConstants.ATT_BOM_QTY).toString();
      bomTable.removeRow(row);
      p.put(ItemConstants.ATT_BOM_ITEM_NUMBER, newRef.getName());
      p.put(ItemConstants.ATT_BOM_QTY, quantity);
      bomTable.createRow(p);
    }
  }

  /**
   * Removes the sub component.
   *
   * @param id the id
   * @param root the root
   * @param kid the kid
   *
   * @throws Exception the exception
   */
  private void removeSubComponent(IItem root, String kid, Authentication id)
      throws Exception {
    Collection deletes = new Vector();
    ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
    if (bomTable == null) { return; }
    IRow row;
    IItem part;
    String partNumber;
    ITwoWayIterator i = bomTable.getTableIterator();
    while (i.hasNext()) {
      row = (IRow) i.next();
      partNumber = row.getValue(ItemConstants.ATT_BOM_ITEM_NUMBER).toString();
      if (kid.equals(partNumber)) {
        part = findItem(partNumber, id);
        if (null == part) {
          continue;
        }
        if (part.getAgileClass().getName().equalsIgnoreCase(
            root.getAgileClass().getName())) {
          deletes.add(row);
        }
      }
    }
    Iterator j = deletes.iterator();
    while (j.hasNext()) {
      row = (IRow) j.next();
      bomTable.removeRow(row);
    }
  }

  /**
   * Find quantity.
   *
   * @param root the root
   * @param kid the kid
   *
   * @return the int
   *
   * @throws Exception the exception
   */
  public int findQuantity(IItem root, String kid) throws Exception {
    ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
    if (bomTable == null) { return 0; }
    IRow row;
    String partNumber;
    String quantity;
    ITwoWayIterator i = bomTable.getTableIterator();
    while (i.hasNext()) {
      row = (IRow) i.next();
      partNumber = row.getValue(ItemConstants.ATT_BOM_ITEM_NUMBER).toString();
      if (kid.equals(partNumber)) {
        quantity = row.getValue(ItemConstants.ATT_BOM_QTY).toString();
        return Integer.valueOf(quantity).intValue();
      }
    }
    return 0;
  }

  /**
   * Find quantity.
   *
   * @param root the root
   * @param linkKey the link key
   * @param kid the kid
   *
   * @return the int
   *
   * @throws Exception the exception
   */
  public int findQuantity(IItem root, String kid, String linkKey)
      throws Exception {
    ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
    if (bomTable == null) { return 0; }
    IRow row;
    String partNumber;
    String quantity;
    ITwoWayIterator i = bomTable.getTableIterator();
    String key = null;
    IItem itemKid;
    while (i.hasNext()) {
      row = (IRow) i.next();
      partNumber = row.getValue(ItemConstants.ATT_BOM_ITEM_NUMBER).toString();
      if (kid.equals(partNumber)) {
        itemKid = (IItem) row.getReferent();
        key = materializeLinkKey(root, itemKid);
        if (key.equals(linkKey)) {
          quantity = row.getValue(ItemConstants.ATT_BOM_QTY).toString();
          return Integer.valueOf(quantity).intValue();
        }
      }
    }
    return 0;
  }

  // add linkkey as parameter to properly identify the right instance to
  // retrieve
  /**
   * Find quantity.
   *
   * @param redlineORbomTable the redline O rbom table
   * @param kid the kid
   *
   * @return the int
   *
   * @throws Exception the exception
   */
  public int findQuantity(ITable redlineORbomTable, String kid)
      throws Exception {
    IRow row;
    String partNumber;
    String q;
    ITwoWayIterator i = redlineORbomTable.getTableIterator();
    while (i.hasNext()) {
      row = (IRow) i.next();
      partNumber = row.getValue(ItemConstants.ATT_BOM_ITEM_NUMBER).toString();
      if (kid.equals(partNumber)) {
        q = row.getValue(ItemConstants.ATT_BOM_QTY).toString();
        return Integer.valueOf(q).intValue();
      }
    }
    return 0;
  }

  /**
   * Check args.
   *
   * @param root the root
   * @param kid the kid
   */
  private void checkArgs(Object root, Object kid) {
    if (null == root) { throw new NullPointerException("Root item is null"); }
    if (null == kid) { throw new NullPointerException("Subcomponent name is null"); }
  }

  /**
   * Publish.
   *
   * @param id the id
   * @param metadataList the metadata list
   *
   * @return the string
   *
   * @throws Exception the exception
   */
  /*
   // old prototype function?
  public String publish(Collection metadataList, Authentication id)
      throws Exception {
    if (null == metadataList || 0 == metadataList.size()) { return null; }
    zws.util.PrintUtil.println("##################");
    zws.util.PrintUtil.println(id.getUsername());
    zws.util.PrintUtil.println("##################");
    zws.util.PrintUtil.println(id.getPassword());
    zws.util.PrintUtil.println("##################");
    // zws.util.PrintUtil.println(metadataList);
    Iterator i = metadataList.iterator();
    Metadata m;
    String s = null;
    while (i.hasNext()) {
      m = (Metadata) i.next();
      s = publish(m, id);
      zws.util.PrintUtil.println("-----------");
      zws.util.PrintUtil.println(s);
    }
    return s;
  }
*/

  /**
   * Publish.
   *
   * @param id the id
   * @param m the m
   *
   * @return the string
   *
   * @throws Exception the exception
   */
  /*
   // old prototype function?
  public String publish(Metadata m, Authentication id) throws Exception {
    // LogWriter.printOnConsole(m);
    String xmlIn = m.toString();
    LogWriter.printOnConsole("INPUT------------------------");
    LogWriter.printOnConsole(xmlIn);
    LogWriter.printOnConsole("INPUT------------------------");
    Stylizer styler = new Stylizer();
    styler.addProcessingInstruction(Properties.get(Names.xsltAGILE_DATA));
    ByteArrayInputStream inStream = new ByteArrayInputStream(xmlIn
        .getBytes("UTF-8"));
    String agileData = styler.styleXML(inStream).toString();
    PrintUtil.println("Publishing BOM into agile: ---------------");
    PrintUtil.println(agileData);
    PrintUtil.println("------------------------------------------");
    if ("true".equalsIgnoreCase(Properties.get(Names.PUBLISH_TO_AGILE_ENABLED))) {
      ByteArrayInputStream agileDataStream = new ByteArrayInputStream(agileData
          .getBytes("UTF-8"));
      AgileConnectorPub ac = new AgileConnectorPub();
      String errorMessage = null;
      StringBuffer result = null;
      boolean connected = ac.connect(id.getUsername(), id.getPassword());
      if (connected) {
        result = ac.publish(agileDataStream);
        if (result == null) {
          errorMessage = ac.getErrorMessage();
        }
      } else {
        errorMessage = ac.getErrorMessage();
      }
      if (null != errorMessage || null == result) {
        LogWriter.printOnConsole("Error Message: " + errorMessage);
      }
      return result.toString();
    } else if ("exec".equalsIgnoreCase(Properties
        .get(Names.PUBLISH_TO_AGILE_ENABLED))) {
      PublishBill publisher = new PublishBill();
      publisher.setBillXML(agileData);
      publisher.setAuthentication(id);
      publisher.execute();
      return publisher.getResponseXML();
    } else {
      LogWriter.printOnConsole("-->Publishing to Agile is Disabled!");
      return "<agile-data><metadata number=\"Publishing\" revision=\"XX\" message=\"Disabled\" message-type=\"error\"><metadata number=\"th125-04\" revision=\"XX\" message=\"th125-04 published.\" message-type=\"information\"/><metadata number=\"pwr-plate-191-04\" revision=\"XX\" message=\"pwr-plate-191-04 published.\" message-type=\"information\"/><metadata number=\"471-00013-04\" revision=\"XX\" message=\"471-00013-04 published.\" message-type=\"information\"/><metadata number=\"28-7305-04\" revision=\"XX\" message=\"28-7305-04 published.\" message-type=\"information\"/><metadata number=\"29-1564-04\" revision=\"XX\" message=\"29-1564-04 published.\" message-type=\"information\"/></metadata></agile-data>";
    }
  }
*/
  // returns the subset of metadataParentMap of items that have been added
  // relative to itemParentMap
  /**
   * Find added metadata.
   *
   * @param itemParentMap the item parent map
   * @param metadataParentMap the metadata parent map
   *
   * @return the map
   */
  public Map findAddedMetadata(Map itemParentMap, Map metadataParentMap) {
    String name;
    Map m = new HashMap();
    Iterator i = metadataParentMap.keySet().iterator();
    Pair p;
    while (i.hasNext()) {
      name = i.next().toString();
      metadataParentMap.get(name);
      itemParentMap.get(name);
      // if (null!=o) System.out.print("---metadata has " + name + " " );
      // else System.out.print("---metadata does not have " + name + " " );
      // if (null!=oo) System.out.print("item has " + name + " " );
      // else System.out.print("item does not have " + name + " " );

      if (itemParentMap.containsKey(name)) {
        // LogWriter.printOnConsole("------------"+ name + " skipped");
        continue; // item was already there addedd
      }
      p = (Pair) metadataParentMap.get(name);
      m.put(name, p);
    }
    return m;
  }

  // returns the subset of itemParentMap of items that are deleted relative to
  // metadataParentMap
  /**
   * Find removed items.
   *
   * @param itemParentMap the item parent map
   * @param metadataParentMap the metadata parent map
   * @param agileClassName the agile class name
   *
   * @return the map
   *
   * @throws Exception the exception
   */
  public Map findRemovedItems(String agileClassName, Map itemParentMap,
      Map metadataParentMap) throws Exception {
    String name;
    Map m = new HashMap();
    Iterator i = itemParentMap.keySet().iterator();
    Pair p;
    IItem kid;
    while (i.hasNext()) {
      name = i.next().toString();
      if (name.length() > 4 && "-doc".equals(name.substring(name.length() - 4))) {
        continue; // do
      }
      // not
      // delete
      // docs
      if (metadataParentMap.containsKey(name)) {
        continue; // item has not been
        // deleted
        // item is in itemParentMap, but not in metadataParentMap - it may have
        // been deleted
      }

      p = (Pair) itemParentMap.get(name);
      kid = (IItem) p.getObject0();
      if (agileClassName.equals(kid.getAgileClass().getName())) {
        m.put(name, p);
      }
      // preserve items that are not the type as the root
    }
    return m;
  }

  /**
   * Create.
   *
   * @param id the id
   * @param data the data
   *
   * @throws Exception the exception
   */
  public void create(Metadata data, Authentication id) throws Exception {
    try {
      sessions.login(id);
      Metadata d;
      Iterator i = mapAllComponents(data).values().iterator();
      while (i.hasNext()) {
        d = (Metadata) i.next();
        createItem(d, id);
      }
    } catch (Exception e) {
      throw e;
    }
  }


  /**
   * Create.
   *
   * @param dataList the data list
   * @param id the id
   *
   * @throws Exception the exception
   */
  public void create(Collection dataList, Authentication id) throws Exception {
    Metadata data;
    Iterator i = dataList.iterator();
    while (i.hasNext()) {
      data = (Metadata) i.next();
      create(data, id);
    }
  }


  /**
   * Replace.
   *
   * @param id the id
   * @param replacementPairs the replacement pairs
   *
   * @throws Exception the exception
   */
  public void replace(Collection replacementPairs, Authentication id)
      throws Exception {
    Pair p;
    String partNumber, newPartNumber;
    Iterator i = replacementPairs.iterator();
    try {
      sessions.login(id);
      while (i.hasNext()) {
        p = (Pair) i.next();
        partNumber = p.getObject0().toString();
        newPartNumber = p.getObject1().toString();
        IDataObject part = findItem(partNumber, id);
        IDataObject newPart = findItem(newPartNumber, id);
        replaceItem(part, newPart, id);
      }
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Replace.
   *
   * @param id the id
   * @param newPartNumber the new part number
   * @param partNumber the part number
   *
   * @throws Exception the exception
   */
  public void replace(String partNumber, String newPartNumber, Authentication id)
      throws Exception {
    try {
      sessions.login(id);
      IDataObject part = findItem(partNumber, id);
      IDataObject newPart = findItem(newPartNumber, id);
      replaceItem(part, newPart, id);
    } catch (Exception e) {
      throw e;
    }
  }

  public Metadata retrieveBOM(String partnumber, Authentication id) throws Exception {
    IItem parentItem = null;
    Metadata parentData = null;
    parentItem = findItem(partnumber, id);
    if (null != parentItem) {
      parentData = this.unmarshall(parentItem);
      retrieveBOM(parentData, id);
    }
    return parentData;
  }
  
  /*
  public Collection retrieveFirstLevelBOM(String partnumber, Authentication id) throws Exception {
    Collection items= new ArrayList();
    Collection bomList = new ArrayList();
    Metadata parentData = this.retrieveBOM(partnumber, id);
    if(null == parentData) return null;
    getItemList(parentData, items);
    zws.util.PrintUtil.print(items);
    Iterator itr = items.iterator();
    while(itr.hasNext()) {
      String itemName = (String) itr.next();
      getFirstLevelItems(bomList, itemName, id);
    }
    return bomList;
  }
  */
  /*
  private void getItemList(Metadata parentData, Collection items) {
    items.add(parentData.getName());
    if(parentData.hasSubComponents()) {
      Collection subItems = parentData.getSubComponents();
      Iterator itr = subItems.iterator();
      MetadataSubComponent child = null;
      while(itr.hasNext()) {
        child = (MetadataSubComponent) itr.next();
        getItemList(child, items);
      }
    }
  }
  */
  
  public Collection listFirstLevelItems(String itemNumber, Authentication id) throws Exception {
    Collection items = new Vector();
    IItem parentItem = null;
    IItem kid = null;
    ITable bomTable = null;
    MetadataSubComponent childData = null;
    parentItem = findItem(itemNumber, id);
    if (null != parentItem) {
      bomTable = parentItem.getTable(ItemConstants.TABLE_BOM);
      if (null != bomTable) {
        //Iterator i = bomTable.getReferentIterator();
        ITwoWayIterator i = bomTable.getTableIterator();
        IRow row = null;
        Object findNumber=null;
        Object quantity=null;
        while (i.hasNext()) {
          row = (IRow) i.next();
          kid = (IItem)row.getReferent();
          childData = new MetadataSubComponentBase(unmarshall(kid));
          findNumber = row.getValue(ItemConstants.ATT_BOM_FIND_NUM);
          if (null!=findNumber) {
            childData.set(Names.METADATA_FIND_NUMBER, findNumber.toString());
          }
          quantity = row.getValue(ItemConstants.ATT_BOM_QTY);
          if (null!=quantity) {
            childData.set(MetadataSubComponentBase.QUANTITY, quantity.toString());
          }
          items.add(childData);
        }
      }
    }
   
    return items;
  }
  
  private void retrieveBOM(Metadata parentData, Authentication id) throws Exception {
    IItem parentItem = null;
    IItem kid = null;
    ITable bomTable = null;
    MetadataSubComponent childData = null;
    parentItem = findItem(parentData.getName(), id);
    if (null != parentItem) {
      bomTable = parentItem.getTable(ItemConstants.TABLE_BOM);
      if (null != bomTable) {
        Iterator i = bomTable.getReferentIterator();
        while (i.hasNext()) {
          kid = (IItem) i.next();
          childData = new MetadataSubComponentBase(unmarshall(kid));
          retrieveBOM(childData, id);
          parentData.addSubComponent(childData);
        }
      }
    }
  }

}
