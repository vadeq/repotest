package zws.repository.agile.sdk.svc;

import zws.data.Metadata;
import zws.data.MetadataSubComponent;
import zws.data.eco.AffectedItem;
import zws.exception.Duplicate;
import zws.exception.InvalidName;
import zws.exception.NameNotFound;
import zws.qx.QxContext;
import zws.repository.agile.sdk.AgileSDKConstants;
import zws.repository.agile.sdk.AgileSDKRepositoryBase;
import zws.security.Authentication;
import zws.util.Pair;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import com.agile.api.ChangeConstants;
import com.agile.api.ExceptionConstants;
import com.agile.api.FileFolderConstants;
import com.agile.api.IAgileClass;
import com.agile.api.IAgileSession;
import com.agile.api.IAttribute;
import com.agile.api.IChange;
import com.agile.api.IFileFolder;
import com.agile.api.IRedlinedRow;
import com.agile.api.IRow;
import com.agile.api.ITable;
import com.agile.api.ITwoWayIterator;
import com.agile.api.ItemConstants;
/*
 * DesignState - Design Compression Technology @author: ptoleti @version: 1.0
 * Created on Jul 12, 2007 10:49:11 AM Copywrite (c) 2007 Zero Wait-State Inc.
 * All rights reserved
 */
import com.agile.api.IItem;


/**
 * The Class AgileSDKAffected.
 *
 * @author ptoleti
 */
public class AgileSDKAffectedItemSvc extends AgileSDKItemSvc {


  /**
   * The Constructor.
   *
   * @param repository repository
   * @param configUtil the config util
   * @param sessionUtil the session util
   */
  protected AgileSDKAffectedItemSvc(AgileSDKSessionSvc sessionUtil,
                                    AgileSDKConfigurationSvc configUtil,
                                    AgileSDKRepositoryBase repository) {
    super(sessionUtil, configUtil, repository);
    ecoSvc = materializeECOSvc();
    bomSvc = materializeBOMSvc();
  }


  /**
   * Update.
   *
   * @param id the id
   * @param dataList the data list
   * @param ecoNumber the eco number
   *
   * @throws Exception the exception
   */
  public void update(Collection dataList, String ecoNumber, Authentication id)
      throws Exception {
    Metadata data = null;
    Iterator i = dataList.iterator();
    try {
      sessions.login(id);
      IChange eco = ecoSvc.findChange(ecoNumber, id);
      while (i.hasNext()) {
        data = (Metadata) i.next();
        update(data, eco, id);
      }
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Update.
   *
   * @param id the id
   * @param ecoNumber the eco number
   * @param data the data
   *
   * @throws Exception the exception
   */
  public void update(Metadata data, String ecoNumber, Authentication id)
      throws Exception {
    try {
      sessions.login(id);
      String partnumber = data.get(AgileSDKConstants.ATT_PART_NUMBER);
      IItem x = findItem(partnumber, id);
      if (null == x) { throw new NameNotFound(partnumber); }
      IChange eco = ecoSvc.findChange(ecoNumber, id);
      if (null == eco) { throw new NameNotFound(ecoNumber); }
      x.setRevision(eco);
      updateItem(x, data);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Update.
   *
   * @param id the id
   * @param data the data
   * @param eco the eco
   *
   * @throws Exception the exception
   */
  private void update(Metadata data, IChange eco, Authentication id)
      throws Exception {
    try {
      sessions.login(id);
      String partnumber = data.get(AgileSDKConstants.ATT_PART_NUMBER);
      IItem x = findItem(partnumber, id);
      if (null == x) { throw new NameNotFound(partnumber); }
      x.setRevision(eco);
      updateItem(x, data);
    } catch (Exception e) {
      throw e;
    }
  }



  public void removeFileFromECOItem(QxContext qxCtx, String partnumber, String ecoNumber, String attachment, Authentication id) throws Exception {
	    if (null == partnumber) { throw new InvalidName("NULL"); }
	    IAgileSession session = sessions.login(id);
	    IItem item = (IItem)session.getObject(IItem.OBJECT_TYPE, partnumber);
	    IChange eco = (IChange)session.getObject(IChange.OBJECT_TYPE, ecoNumber);    
	    if (item == null){throw new NameNotFound(partnumber);}
	    if (eco== null)  {throw new NameNotFound(ecoNumber); }
	    item.setRevision(eco);
	    ITable table = item.getTable(ItemConstants.TABLE_ATTACHMENTS);
	    boolean moreAttachments = true;
	    while (moreAttachments) {
	      moreAttachments = false;
	      ITwoWayIterator i = table.getTableIterator();
	      while (i.hasNext()) {
	        IRow row = (IRow) i.next();
	        Object o = row.getValue(ItemConstants.ATT_ATTACHMENTS_FILE_NAME);
	        String name = o.toString();
	        if (name.equals(attachment)) {
	          table.removeRow(row);
	          System.out.println("Attachment " + attachment  +" is removed from Item " + partnumber + " with ECO " + ecoNumber);
	          moreAttachments = true; // reset to remove other attachments by same filename
	          break;
	        }
	      }
	    } 
	  }
  
  public void removeAllFilesFromECOItem(QxContext qxCtx, String partnumber, String ecoNumber, Authentication id) throws Exception {
	  try {
			if (null == partnumber) { throw new InvalidName("partnumber is null"); }
			// check for item and eco in agile
			IAgileSession session = sessions.login(id);
			IItem item = (IItem)session.getObject(IItem.OBJECT_TYPE, partnumber);
			IChange eco = (IChange)session.getObject(IChange.OBJECT_TYPE, ecoNumber);    
			if (item == null){throw new NameNotFound(partnumber);}
			if (eco== null)  {throw new NameNotFound(ecoNumber); }
			System.out.println("Deleting Attachments for item " + item.getName()+ " ECO " + eco.getName());
			// check for partNumber in the affected item list.
			ITable affectedItems = eco.getTable(ChangeConstants.TABLE_AFFECTEDITEMS);
			if(null == affectedItems || affectedItems.size() <1 ) return;
			IItem affectedItem = null;
			boolean itemFound = false;
			ITwoWayIterator itr = affectedItems.getTableIterator();
			while (itr.hasNext()) {
	        IRow row = (IRow) itr.next();
	        Object o = row.getValue(ChangeConstants.ATT_AFFECTED_ITEMS_ITEM_NUMBER);
	        String name = o.toString();
	        if (item.getName().equals(name)) {
	        	itemFound = true;
	        	break;
	        }
	      }
			if(!itemFound) {
				System.out.println(partnumber + " is not on the affected Item list of " + eco.getName());
				return;
			}
			// delete all the attachments
			item.setRevision(eco);
			ITable table = item.getTable(ItemConstants.TABLE_ATTACHMENTS);
			ITwoWayIterator i = table.getTableIterator();
			ITwoWayIterator tempItr = i;
			while (tempItr.hasNext()) {
			    IRow row = (IRow) tempItr.next();
			    Object o = row.getValue(ItemConstants.ATT_ATTACHMENTS_FILE_NAME);
			    String name = o.toString();
			    removeFileFromECOItem(qxCtx, partnumber, ecoNumber, name, id);	    
			}
	  } catch(Exception e) {
		  System.out.println("ERROR: Not able to delete the attachments for item " + partnumber + " ECO " + ecoNumber);
	  }

  }
  
  public boolean isECOExists(String ecoNumber, Authentication id) throws Exception{
	  boolean result = false;
	  if(null == ecoNumber || ecoNumber.length() <1 ) {
		  return result;
	  }
	  IAgileSession session = sessions.login(id);
	  IChange eco = (IChange)session.getObject(IChange.OBJECT_TYPE, ecoNumber);    
	  if (eco != null){
		  result = true;
	  }
	  return result;
  }
  
  /**
   * Attach file.
   *
   * @param id the id
   * @param partnumber the partnumber
   * @param ecoNumber the eco number
   * @param attachment the attachment
   *
   * @throws Exception the exception
   */
  public void attachFile(File attachment, String partnumber, String ecoNumber,
      Authentication id) throws Exception {
    try {
      sessions.login(id);
      //attachFileToECOItem(attachment, partnumber, ecoNumber, id);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Attach file to ECO item.
   *
   * @param id the id
   * @param partnumber the partnumber
   * @param ecoNumber the eco number
   * @param attachment the attachment
   *
   * @throws Exception the exception
   */
  /*
  private void attachFileToECOItem(File attachment, String partnumber,
      String ecoNumber, Authentication id) throws Exception {
    if (!attachment.exists()) { throw new NotAFile(attachment); }
    if (!attachment.isFile()) { throw new NotAFile(attachment); }
    if (null == partnumber) { throw new InvalidName("NULL"); }
    if (null == ecoNumber) { throw new InvalidName("NULL"); }

     // Java version must match to attach files - shell attachment to run in
    //  correct version of vm. IItem item =
    //  (IItem)SessionUtility.getAgileSession(id).getObject(IItem.OBJECT_TYPE,
    //  partnumber); if (item == null) throw new NameNotFound(partnumber);
    //  IChange eco =
    //  (IChange)SessionUtility.getAgileSession(id).getObject(IChange.OBJECT_TYPE,
    //  ecoNumber); if (eco== null) throw new NameNotFound(ecoNumber);
    //  item.setRevision(eco); ITable table =
    //  item.getTable(ItemConstants.TABLE_ATTACHMENTS); attach(table,
    //  attachment);

    // Java version must match to attach files - shell attachment to run in
    // correct version of vm.
    AttachECOFile attacher = new AttachECOFile();
    attacher.setAuthentication(id);
    attacher.setURL(sessions.getURL());
    attacher.setPartnumber(partnumber);
    attacher.setECOnumber(ecoNumber);
    attacher.setAttachment(attachment);
    attacher.execute();

    boolean verified = verifyAttachment(partnumber, ecoNumber, attachment
        .getName(), id);
    if (!verified) { throw new FailedToTransferFile(attachment, sessions.getURL()); }
    if (Server.productionMode()) {
      attachment.delete(); // create a flag for this.
    }
    updateFolderVersion(partnumber, ecoNumber, attachment, id);
  }
*/
  /**
   * Attach URL.
   *
   * @param id the id
   * @param partnumber the partnumber
   * @param ecoNumber the eco number
   * @param description the description
   * @param url the url
   *
   * @throws Exception the exception
   */
  public void attachURL(URL url, String description, String partnumber,
      String ecoNumber, Authentication id) throws Exception {
    try {
      sessions.login(id);
      attachURLToECOItem(url, description, partnumber, ecoNumber, id);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Attach URL to ECO item.
   *
   * @param id the id
   * @param partnumber the partnumber
   * @param ecoNumber the eco number
   * @param description the description
   * @param url the url
   *
   * @throws Exception the exception
   */
  private void attachURLToECOItem(URL url, String description,
      String partnumber, String ecoNumber, Authentication id) throws Exception {
    if (null == partnumber) { throw new InvalidName("NULL"); }
    IItem object = findItem(partnumber, id);
    IChange eco = ecoSvc.findChange(ecoNumber, id);
    object.setRevision(eco);
    ITable table = object.getTable(ItemConstants.TABLE_ATTACHMENTS);
    attachURL(table, description, url);
  }


  /**
   * Verify attachment.
   *
   * @param id the id
   * @param partnumber the partnumber
   * @param ecoNumber the eco number
   * @param filename the filename
   *
   * @return true, if verify attachment
   *
   * @throws Exception the exception
   */
  public boolean verifyAttachment(String partnumber, String ecoNumber,
      String filename, Authentication id) throws Exception {
    IItem item = findItem(partnumber, id);
    if (null == item) { return false; }
    IChange eco = ecoSvc.findChange(ecoNumber, id);
    if (null == eco) { return false; }
    item.setRevision(eco);
    ITable table = item.getTable(ItemConstants.TABLE_ATTACHMENTS);
    ITwoWayIterator i = table.getTableIterator();
    while (i.hasNext()) {
      IRow row = (IRow) i.next();
      Object o = row.getValue(ItemConstants.ATT_ATTACHMENTS_FILE_NAME);
      String name = o.toString();
      if (name.equals(filename)) { return true; }
    }
    return false;
  }



  // From Jason Brown
  /**
   * Update folder version.
   *
   * @param id the id
   * @param partnumber the partnumber
   * @param ecoNumber the eco number
   * @param attachment the attachment
   *
   * @throws Exception the exception
   */
  public void updateFolderVersion(String partnumber, String ecoNumber,
      File attachment, Authentication id) throws Exception {
    IItem item = (IItem) sessions.login(id).getObject(IItem.OBJECT_TYPE,
        partnumber);
    if (item == null) { throw new NameNotFound(partnumber); }
    IChange eco = (IChange) sessions.login(id).getObject(IChange.OBJECT_TYPE,
        ecoNumber);
    if (eco == null) { throw new NameNotFound(ecoNumber); }
    item.setRevision(eco);
    ITable table = item.getTable(ItemConstants.TABLE_ATTACHMENTS);
    String value = null;
    Object o;
    IRow row = null;
    IRow returnRow = null;
    Iterator it = table.iterator();
    while (it.hasNext()) {
      row = (IRow) it.next();
      o = row.getValue(ItemConstants.ATT_ATTACHMENTS_FILE_NAME);
      if (null != o) {
        value = o.toString();
      }
      if (value.equalsIgnoreCase(attachment.getName())) {
        returnRow = row;
      }
    }
    if (null == returnRow) { return; }

    IFileFolder pubFileFolder = (IFileFolder) returnRow.getReferent();
    String pubFileFolderNum = pubFileFolder.getName();
    pubFileFolder = (IFileFolder) sessions.login(id).getObject(
        FileFolderConstants.CLASS_FILE_FOLDER_BASE_CLASS, pubFileFolderNum);

    String currentFFVersion = ""
        + pubFileFolder.getValue(FileFolderConstants.ATT_TITLE_BLOCK_VERSION);
    try {
      double d = Double.valueOf(currentFFVersion).doubleValue();
      int ffv = (int) d;
      returnRow.setValue(ItemConstants.ATT_ATTACHMENTS_FOLDER_VERSION, Integer
          .valueOf("" + ffv));
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  public void setBOMAttribute(String ecoNumber, String affectedItemNumber, String subcomponentNumber, String attribute, String value, Authentication id) throws Exception {
    IChange changeOrder = materializeECOSvc().findChange(ecoNumber, id);
    IItem parent = findItem(affectedItemNumber, id);    
    if (null == parent) { throw new NameNotFound(affectedItemNumber); }
    IItem subComponent= findItem(subcomponentNumber, id);    
    if (null == subComponent) { throw new NameNotFound(subcomponentNumber); }
    if (attribute.equalsIgnoreCase(""+ItemConstants.ATT_BOM_ITEM_NUMBER)) return;
    setBOMAttribute(changeOrder, parent, subComponent, attribute, value, id);
  }

  public void setBOMAttribute(IChange eco, IItem parent, IItem subcomponent, String attribute, String value, Authentication id) throws Exception {
    ITable affectedItems = eco.getTable(ChangeConstants.TABLE_AFFECTEDITEMS);
    if (!listedAsAffectedItem(parent.getName(), affectedItems)) { throw new NameNotFound(subcomponent + " is not an affected item on eco " + eco.getName()); }
    parent.setRevision(eco);
    ITable redlineTable = lookupRedlineTable(eco, parent);
    if (null == redlineTable) { throw new zws.exception.InvalidState( parent.getName() + " on eco "+eco.getName()+"has no redline table to set bom attributes on!"); }
    IRow r=lookupSubcomponent(subcomponent.getName(), redlineTable );
    if (attribute.equalsIgnoreCase(""+ItemConstants.ATT_BOM_ITEM_NUMBER)) return;
    r.setValue(attribute, value);
  }

  
  public void setBOMAttributes(String ecoNumber, String affectedItemNumber, String subcomponentNumber, Map atts, Authentication id) throws Exception {
    if (null==atts) return;
    Iterator i = atts.keySet().iterator();
    Object att, val;
    while (i.hasNext()) {
      att = i.next();
      val = atts.get(att);
      if(null== att || val == null) continue;
      try {            
        setBOMAttribute(ecoNumber, affectedItemNumber, subcomponentNumber, att.toString(), val.toString(), id);
      } catch(Exception ignore) { 
      }        
    }
  }

  public void setBOMAttributes(IChange eco, IItem parent, IItem subcomponent, Map atts, Authentication id) throws Exception {
    if (null==atts) return;
    Iterator i = atts.keySet().iterator();
    Object att, val;
    while (i.hasNext()) {
      att = i.next();
      val = atts.get(att);
      if(null== att || val == null) continue;
      try {      
        setBOMAttribute(eco, parent, subcomponent, att.toString(), val.toString(), id);
      } catch(Exception ignore) { 
      }
    }
  }
  
  /**
   * Redline new sub component.
   *
   * @param root the root
   * @param quantity the quantity
   * @param eco the eco
   * @param kid the kid
   *
   * @throws Exception the exception
   */
  private void redlineNewSubComponent(IChange eco, IItem root, IItem kid,
      int quantity) throws Exception {
    if (null == root) { throw new NullPointerException("Root item is null"); }
    if (null == kid) {
      throw new NullPointerException("Subcomponent name is null");
    }
    ITable bomTable = root.getTable(ItemConstants.TABLE_BOM);
    if (bomSvc.isInBOM(bomTable, kid.getName())) {
      throw new Duplicate("BOM entry: " + kid.getName());
    }
    Map p = new HashMap();
    p.put(ItemConstants.ATT_BOM_ITEM_NUMBER, kid.getName());
    p.put(ItemConstants.ATT_BOM_QTY, "" + quantity);
    bomTable.createRow(p);
  }


  /**
   * Redline new sub component.
   *
   * @param id the id
   * @param root the root
   * @param quantity the quantity
   * @param eco the eco
   * @param kid the kid
   *
   * @throws Exception the exception
   */
  private void redlineNewSubComponent(IChange eco, IItem root, String kid,
      int quantity, QxContext bomAttributes, Authentication id) throws Exception {
    if (null == eco) { throw new NullPointerException("ECO item is null"); }
    if (null == root) { throw new NullPointerException("Root item is null"); }
    if (null == kid) { throw new NullPointerException("Subcomponent name is null"); }
    try { root.setRevision(eco); }
    catch (Exception e) {
      System.out.println("Could not set "+root.getName() + " revision to " + eco.getName());
      throw e;
    }
    IItem k = findItem(kid, id);
    if (null == k) { throw new NameNotFound(kid); }
    ITable redlineTable = lookupRedlineTable(eco, root);
    if (null == redlineTable) { return; }
    Map p = new HashMap();
    p.put(ItemConstants.ATT_BOM_ITEM_NUMBER, kid);
    p.put(ItemConstants.ATT_BOM_QTY, "" + quantity);
    if (null!=bomAttributes) {
      Iterator i = bomAttributes.properties.keySet().iterator();
      String key=null;
      while(i.hasNext()) {
        key = (String)i.next();
        if ("quantity".equalsIgnoreCase(key)) continue;
        p.put(key, bomAttributes.get(key));
      }
    }
    try {
      if (!bomSvc.isInBOM(redlineTable, kid))  redlineTable.createRow(p);
        }
    catch(Exception e) {
      String msg = e.getMessage();
      if (null!= msg && !msg.contains("is a duplicate item")) throw e;
    }
  }

  /**
   * Redline new sub component.
   *
   * @param id the id
   * @param root the root
   * @param ecoNumber the eco number
   * @param quantity the quantity
   * @param kid the kid
   *
   * @throws Exception the exception
   */
  public void redlineNewSubComponent(String ecoNumber, String root,
      String kid, int quantity, QxContext bomAttributes, Authentication id) throws Exception {
    if (null == ecoNumber) { throw new NullPointerException("ECO Number is null"); }
    if (null == root) { throw new NullPointerException("Root item is null"); }
    if (null == kid) { throw new NullPointerException("Subcomponent name is null"); }
    IChange eco = ecoSvc.findChange(ecoNumber, id);
    IItem r = findItem(root, id);
    if (null == r) { throw new NameNotFound(root); }
    redlineNewSubComponent(eco, r, kid, quantity, bomAttributes, id);
  }


  public void redlineDeleteSubComponent(String ecoNumber, String root,
      String kid, Authentication id) throws Exception {
    if (null == ecoNumber) { throw new NullPointerException("ECO Number is null"); }
    if (null == root) { throw new NullPointerException("Root item is null"); }
    if (null == kid) { throw new NullPointerException("Subcomponent name is null"); }
    IChange eco = ecoSvc.findChange(ecoNumber, id);
    IItem r = findItem(root, id);
    if (null == r) { throw new NameNotFound(root); }
    redlineDeleteSubComponent(eco, r, kid, id);
  }


  public void redlineDeleteSubComponent(String ecoNumber, IItem root,
      String kid, Authentication id) throws Exception {
    if (null == ecoNumber) { throw new NullPointerException("ECO Number is null"); }
    IChange eco = ecoSvc.findChange(ecoNumber, id);
    redlineDeleteSubComponent(eco, root, kid, id);
  }


  public void undoRedlines(String ecoNumber, String root, String kid, Authentication id) throws Exception {
    if (null == ecoNumber) { throw new NullPointerException("ECO Number is null"); }
    if (null == root) { throw new NullPointerException("Root item is null"); }
    if (null == kid) { throw new NullPointerException("Subcomponent name is null"); }
    IChange eco = ecoSvc.findChange(ecoNumber, id);
    IItem r = findItem(root, id);
    if (null == r) { throw new NameNotFound(root); }
    undoRedlines(eco, r, id);
  }

  public void undoRedlines(String ecoNumber, IItem root, String kid, Authentication id) throws Exception {
    if (null == ecoNumber) { throw new NullPointerException("ECO Number is null"); }
    IChange eco = ecoSvc.findChange(ecoNumber, id);
    undoRedlines(eco, root, id);
  }
  
  /**
   * Redline remove sub component.
   *
   * @param id the id
   * @param root the root
   * @param ecoNumber the eco number
   * @param kid the kid
   *
   * @throws Exception the exception
   */
  private void redlineDeleteSubComponent(IChange eco, IItem root, String kid, Authentication  id) throws Exception {
    Collection deletes = new Vector();
    ITable redlineBOMTable = lookupRedlineTable(eco, root);
    if (redlineBOMTable == null) { return; }
    String ownershipBOMAttribute = getRepository().getOwnershipBomAttribute();
    String ownershipBOMAttributeValue = getRepository().getOwnershipBomAttributeValue();
    IRow row;
    IItem part;
    String partNumber;
    String cadSource = null;
    ITwoWayIterator i = redlineBOMTable.getTableIterator();
    while (i.hasNext()) {
      cadSource = null;
      row = (IRow) i.next();
      partNumber = row.getValue(ItemConstants.ATT_BOM_ITEM_NUMBER).toString();
      IAttribute attr = getTableAttribute(root.getAgileClass(), ItemConstants.TABLE_BOM, ownershipBOMAttribute);
      cadSource = getTableAttributeValue(attr, row);
      if(null== cadSource || !cadSource.startsWith(ownershipBOMAttributeValue)) continue; 
      if (kid.equals(partNumber)) {
        part = findItem(partNumber, id);
        if (null == part) continue;
        if (part.getAgileClass().getName().equalsIgnoreCase(
            root.getAgileClass().getName())) {
          deletes.add(row);
        }
      }
    }
    Iterator j = deletes.iterator();
    while (j.hasNext()) {
      row = (IRow) j.next();
      redlineBOMTable.removeRow(row);
    }
  }


  private IAttribute getTableAttribute(IAgileClass rootClass, Integer tableConstant, String attName) throws Exception{
    //ItemConstants.TABLE_BOM
    if(null == attName) return null;
    IAttribute[] attList = rootClass.getTableAttributes(tableConstant);
    if(null == attList) return null;
    IAttribute att;
    for(int idx =0;idx<attList.length;idx++) {
      att = attList[idx];
      if(attName.equalsIgnoreCase(att.getName()))
        return att;
    }
    return null;
  }
  
  private String getTableAttributeValue(IAttribute attr, IRow row) throws Exception{
    if(null == attr) return null;
    Object value = null;
    value = row.getValue(attr);
    if(null == value) return null;
    return  value.toString();
  }
    
  

  /**
   * undo any existing redlines.
   *
   * @param id the id
   * @param ecoNumber the eco number
   * @param changedItem the changed item
   *
   * @return the agile ECO
   *
   * @throws Exception the exception
   */
  public void undoRedlines(String ecoNumber, AffectedItem changedItem, Authentication id) throws Exception {
    try {
      AgileSDKECOSvc ecoSvc = materializeECOSvc();
      sessions.login(id);
      sessions.login(id).disableWarning(ExceptionConstants.APDM_PENDINGCHANGE_ITEM_WARNING);
      IChange eco = ecoSvc.findChange(ecoNumber, id);
      if (null==eco) return;
      ITable affectedItems = eco.getTable(ChangeConstants.TABLE_AFFECTEDITEMS);
      if(!listedAsAffectedItem(changedItem.getItemNumber(),
        affectedItems)) return;
      IItem item = findItem(changedItem.getItemNumber(), id);
      if (null==item) return;
      undoRedlines(eco, item, id);
      return;
    } catch (Exception e) {
      throw e;
    }
  }
  
  
  private void undoRedlines(IChange eco, IItem root, Authentication id) throws Exception {
    ITable redlineBOMTable = lookupRedlineTable(eco, root);
    Iterator it = redlineBOMTable.iterator();
    IRow row;
    IRedlinedRow redlineRow;
    IItem redlinedSubcomponent;
    while (it.hasNext()) {
      row = (IRow)it.next();
      redlinedSubcomponent = (IItem)row.getReferent();
      redlineRow = (IRedlinedRow)row;
      if (wasRedlinedByZWS(root,row)) redlineRow.undoRedline();
    }
  }

  private boolean wasRedlinedByZWS(IItem parent, IRow row) throws Exception{
    String ownershipBOMAttribute = getRepository().getOwnershipBomAttribute();
    String ownershipBOMAttributeValue = getRepository().getOwnershipBomAttributeValue();    
    IAttribute IAttr = getTableAttribute(parent.getAgileClass(), ItemConstants.TABLE_BOM, ownershipBOMAttribute);
    String ownership = getTableAttributeValue(IAttr, row);
    if (null != ownership && ownership.startsWith(ownershipBOMAttributeValue)) return true;
     return false;
  }

  /**
   * Redline structure.
   *
   * @param id the id
   * @param dataList the data list
   * @param ecoNumber the eco number
   *
   * @throws Exception the exception
   */
  public void redlineStructure(String ecoNumber, Collection dataList,
      Authentication id) throws Exception {
    Metadata data;
    Iterator i = dataList.iterator();
    while (i.hasNext()) {
      data = (Metadata) i.next();
      redlineStructure(ecoNumber, data, id);
    }
  }


  /**
   * Redline structure.
   *
   * @param id the id
   * @param ecoNumber the eco number
   * @param data the data
   *
   * @throws Exception the exception
   */
  public void redlineStructure(String ecoNumber, Metadata data,
      Authentication id) throws Exception {
    // LogWriter.printOnConsole("REDLINING STRUCTURE " +
    // data.get(ATT_PART_NUMBER) + " on " + ecoNumber +
    // "------------------------");
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

      dataParentMap = bomSvc.mapParents(data);
      // itemParentMap = mapParentss(root);
      itemParentMap = bomSvc.mapAllParents(root, data, id);

      // remove items
      Map dels = bomSvc.findRemovedItems(root.getAgileClass().getName(),
          itemParentMap, dataParentMap);
      i = dels.values().iterator();
      while (i.hasNext()) {
        itemP = (Pair) i.next();
        itemKid = (IItem) itemP.getObject0();
        parents = (Map) itemP.getObject1();
        j = parents.values().iterator();
        while (j.hasNext()) {
          itemParent = (IItem) j.next();
          // LogWriter.printOnConsole("REDLINING "+itemParent.getName()+":
          // Removing Subcomponent" + itemKid.getName() + " on " + ecoNumber);
          redlineDeleteSubComponent(ecoNumber, itemParent, itemKid.getName(), id);
        }
      }

      // add items
      // itemParentMap = mapParents(root);
      itemParentMap = bomSvc.mapAllParents(root, data, id);
      Map adds = bomSvc.findAddedMetadata(itemParentMap, dataParentMap);
      i = adds.values().iterator();
      ITable redlineTable;
      IChange eco = ecoSvc.findChange(ecoNumber, id);
      while (i.hasNext()) {
        dataP = (Pair) i.next();
        dataKid = (Metadata) dataP.getObject0();
        parents = (Map) dataP.getObject1();
        j = parents.values().iterator();
        while (j.hasNext()) {
          dataParent = (Metadata) j.next();
          // LogWriter.printOnConsole("REDLINING "
          // +dataParent.get(ATT_PART_NUMBER)+ ": Adding Subcomponent" +
          // dataKid.get(ATT_PART_NUMBER) + " on " + ecoNumber);
          redlineNewSubComponent(ecoNumber, dataParent
              .get(AgileSDKConstants.ATT_PART_NUMBER), dataKid
              .get(AgileSDKConstants.ATT_PART_NUMBER),
              ((MetadataSubComponent) dataKid).getQuantity(), null, id);
        }
      }

      // update quantities and other bom attributes
      // itemParentMap = mapParents(root);
      itemParentMap = bomSvc.mapAllParents(root, data, id);

      Map dataKidLinks = bomSvc.linkKids(data);
      Map itemKidLinks = bomSvc.linkKids(root);

      MetadataSubComponent dataSubComponent = null;
      IItem itemSubComponent = null;
      i = dataKidLinks.keySet().iterator();
      String linkKey;
      int oldQuantity, newQuantity;
      while (i.hasNext()) {
        linkKey = (String) i.next();
        if (!itemKidLinks.containsKey(linkKey)) {
          continue;
        }
        dataP = (Pair) dataKidLinks.get(linkKey);
        itemP = (Pair) itemKidLinks.get(linkKey);
        dataSubComponent = (MetadataSubComponent) dataP.getObject1();
        itemParent = (IItem) itemP.getObject0();
        itemSubComponent = (IItem) itemP.getObject1();
        oldQuantity = bomSvc.findQuantity(itemParent, itemSubComponent
            .getName(), linkKey);
        newQuantity = dataSubComponent.getQuantity();
        if (newQuantity != oldQuantity) {
          redlineTable = lookupRedlineTable(eco, itemParent);
          // LogWriter.printOnConsole("REDLINING " + itemParent.getName()+ ":
          // Updating Quantity of " + itemSubComponent.getName() + " to " +
          // newQuantity + " on " + eco.getName());
          if (null != redlineTable) {
            bomSvc.updateQuantity(redlineTable, itemSubComponent.getName(),
                newQuantity);
          }
        }
      }
    } catch (Exception e) {
      throw e;
    }
    // LogWriter.printOnConsole("REDLINING STRUCTURE " +
    // data.get(ATT_PART_NUMBER) + " on " + ecoNumber +
    // "------------------------");
  }


  /**
   * Redline update quantity.
   *
   * @param id the id
   * @param root the root
   * @param quantity the quantity
   * @param eco the eco
   * @param kid the kid
   *
   * @throws Exception the exception
   */
  private void redlineUpdateQuantity(IChange eco, IItem root, String kid,
      int quantity, Authentication id) throws Exception {
    if (null == eco) { throw new NullPointerException("ECO item is null"); }
    if (null == root) { throw new NullPointerException("Root item is null"); }
    if (null == kid) { throw new NullPointerException("Subcomponent name is null"); }
    IItem k = findItem(kid, id);
    if (null == k) { throw new NameNotFound(kid); }
    ITable redlineTable = lookupRedlineTable(eco, root);
    if (null != redlineTable) {
      bomSvc.updateQuantity(redlineTable, kid, quantity);
    }
  }


  /**
   * Redline update quantity.
   *
   * @param id the id
   * @param root the root
   * @param ecoNumber the eco number
   * @param quantity the quantity
   * @param kid the kid
   *
   * @throws Exception the exception
   */
  public void redlineUpdateQuantity(String ecoNumber, String root, String kid,
      int quantity, Authentication id) throws Exception {
    if (null == ecoNumber) { throw new NullPointerException("ECO Number is null"); }
    if (null == root) { throw new NullPointerException("Root item is null"); }
    if (null == kid) { throw new NullPointerException("Subcomponent name is null"); }
    IChange eco = ecoSvc.findChange(ecoNumber, id);
    IItem r = findItem(root, id);
    if (null == r) { throw new NameNotFound(root); }
    redlineUpdateQuantity(eco, r, kid, quantity, id);
  }

  /**
   * Lookup redline table.
   *
   * @param affectedItem the affected item
   * @param eco the eco
   *
   * @return the i table
   *
   * @throws Exception the exception
   */
  private ITable lookupRedlineTable(IChange eco, IItem affectedItem)
      throws Exception {
    if (null == eco) { return null; }
    ITable affectedItems = eco.getTable(ChangeConstants.TABLE_AFFECTEDITEMS);
    if (null == affectedItems) { return null; }
    Iterator i = affectedItems.getReferentIterator();
    IItem item = null;
    while (i.hasNext()) {
      item = (IItem) i.next();
      if (item.getName().equals(affectedItem.getName())) {
        break;
      }
      item = null;
    }
    if (null == item) { return null; }
    ITable redlineTable = item.getTable(ItemConstants.TABLE_REDLINEBOM);
    return redlineTable;
  }

  /**
   * Checks if is new affected item.
   *
   * @param affectedItems the affected items
   * @param item the item
   * @param data the data
   *
   * @return true, if is new affected item
   *
   * @throws Exception the exception
   */
  public boolean isNewAffectedItem(Metadata data, IItem item,
      ITable affectedItems) throws Exception {
    if (hasNotChanged(data, item)) { return false; }
    // item has changed - so check if it has already been added
    IItem affectedItem;
    Iterator i = affectedItems.getReferentIterator();
    while (i.hasNext()) {
      affectedItem = (IItem) i.next();
      if (affectedItem.getName().equals(item.getName())) { return false; }
    }
    return true;
  }


  /**
   * Listed as affected item.
   *
   * @param affectedItems the affected items
   * @param itemNumber the item number
   *
   * @return true, if listed as affected item
   *
   * @throws Exception the exception
   */
  public boolean listedAsAffectedItem(String itemNumber, ITable affectedItems)
      throws Exception {
    ITwoWayIterator i = affectedItems.getTableIterator();
    String number;
    while (i.hasNext()) {
      IRow row = (IRow) i.next();
      number = row.getValue(ChangeConstants.ATT_AFFECTED_ITEMS_ITEM_NUMBER)
          .toString();
      if (number.equals(itemNumber)) { return true; }
    }
    return false;
  }

  public IRow lookupAffectedItem(String itemNumber, ITable affectedItems)
    throws Exception {

    ITwoWayIterator i = affectedItems.getTableIterator();
    String number;
    while (i.hasNext()) {
      IRow row = (IRow) i.next();
      number = row.getValue(ChangeConstants.ATT_AFFECTED_ITEMS_ITEM_NUMBER)
          .toString();
      if (number.equals(itemNumber)) { return row; }
    }
    return null;
  }
  
  public boolean listedAsSubcomponent(String subcomponentNumber, ITable subcomponents) throws Exception {
    IRow r = lookupSubcomponent(subcomponentNumber, subcomponents);
    return (null!=r);
  }
  
  public IRow lookupSubcomponent(String subcomponentNumber, ITable subcomponents) throws Exception {
    ITwoWayIterator i = subcomponents.getTableIterator();
    String number;
    while (i.hasNext()) {
      IRow row = (IRow) i.next();
      number = row.getValue(ItemConstants.ATT_BOM_ITEM_NUMBER).toString();
      if (number.equals(subcomponentNumber)) return row;
    }
    return null;
  }

  
  
  /** The eco svc. */
  private AgileSDKECOSvc ecoSvc = null;

  /** The bom svc. */
  private AgileSDKBOMSvc bomSvc = null;
}

