/**
 *
 */
package zws.repository.agile.sdk.svc;
/*
 * DesignState - Design Compression Technology @author: ptoleti @version: 1.0
 * Created on Jul 12, 2007 10:48:20 AM Copywrite (c) 2007 Zero Wait-State Inc.
 * All rights reserved
 */


import zws.application.Names;
import zws.application.Properties;
import zws.data.Metadata;
import zws.exception.Duplicate;
import zws.exception.InvalidName;
import zws.exception.NameNotFound;
import zws.exception.NoSuchType;
import zws.exception.NotAFile;
import zws.qx.QxContext;
import zws.repository.agile.sdk.AgileSDKConstants;
import zws.repository.agile.sdk.AgileSDKRepositoryBase;
import zws.security.Authentication;
import zws.service.file.depot.FileDepotClient;
import zws.util.Counter;
import zws.util.RemotePackage;

import java.io.File;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import com.agile.api.APIException;
import com.agile.api.ExceptionConstants;
import com.agile.api.FileFolderConstants;
import com.agile.api.IAdmin;
import com.agile.api.IAgileClass;
import com.agile.api.IAgileObject;
import com.agile.api.IAgileSession;
import com.agile.api.IAttribute;
import com.agile.api.IChange;
import com.agile.api.IDataObject;
import com.agile.api.IFileFolder;
import com.agile.api.IItem;
import com.agile.api.IQuery;
import com.agile.api.IRow;
import com.agile.api.ITable;
import com.agile.api.ITwoWayIterator;
import com.agile.api.ItemConstants;


/**
 * The Class AgileSDKItemSvc.
 *
 * @author ptoleti
 */
public class AgileSDKItemSvc extends AgileSDKSvcBase {


  /**
   * The Constructor.
   *
   * @param repository the repository
   * @param configUtil the config util
   * @param sessionUtil the session util
   */
  public AgileSDKItemSvc(AgileSDKSessionSvc sessionUtil,
      AgileSDKConfigurationSvc configUtil, AgileSDKRepositoryBase repository) {
    super(sessionUtil, configUtil, repository);
    partNumberSvc = new AgileSDKPartNumberSvc(sessionUtil, configUtil, repository);
  }

  // From Jason Brown
  /**
   * Attach.
   *
   * @param description the description
   * @param table the table
   * @param url the url
   *
   * @throws Exception the exception
   */
  public void attachURL(ITable table, String description, URL url)
      throws Exception {
    String value = null;
    Object o;
    IRow row = null;
    IRow returnRow = null;
    boolean replacedFile = false;
    String newURL = url.toString();
    int idx = newURL.toLowerCase().lastIndexOf("|");
    if (idx > -1) {
      newURL = newURL.substring(idx);
    }
    Iterator it = table.iterator();
    String type;
    while (it.hasNext()) {
      type = "";
      row = (IRow) it.next();
      o = row.getValue(ItemConstants.ATT_ATTACHMENTS_FILE_TYPE);
      if (null != o) {
        type = o.toString();
      }
      if (!"url".equalsIgnoreCase(type.trim())) {
        continue;
      }
      value = "";
      o = row.getValue(ItemConstants.ATT_ATTACHMENTS_FILE_NAME);
      if (null != o) {
        value = o.toString();
      }
      idx = value.toLowerCase().lastIndexOf("|");
      if (idx > -1) {
        value = value.substring(idx);
      }
      if (value.equalsIgnoreCase(newURL)) {
        returnRow = row;
        IFileFolder fileFolder = (IFileFolder) returnRow.getReferent();
        fileFolder.checkOutEx();
        ITable files = fileFolder.getTable(FileFolderConstants.TABLE_FILES);
        if (files != null) {
          IRow fileRow = (IRow) files.iterator().next();
          files.removeRow(fileRow);
          IRow replacement = files.createRow(url.toString());
          replacement.setValue(FileFolderConstants.ATT_FILES_FILE_DESCRIPTION, description);
          replacedFile = true;
        }
        if (replacedFile) {
          fileFolder.checkIn();
        } else {
          fileFolder.cancelCheckout();
        }
      }
    }
    if (null == returnRow) {
      Map values = new HashMap();
      values.put(ItemConstants.ATT_ATTACHMENTS_FILE_NAME, url.toString());
      values.put(ItemConstants.ATT_ATTACHMENTS_FILE_DESCRIPTION, description);
      values.put(ItemConstants.ATT_ATTACHMENTS_FILE_TYPE, "url");
      returnRow = table.createRow(values);
    }

    IFileFolder pubFileFolder = (IFileFolder) returnRow.getReferent();
    String pubFileFolderNum = pubFileFolder.getName();
    pubFileFolder = (IFileFolder) sessions.systemLogin().getObject(FileFolderConstants.CLASS_FILE_FOLDER_BASE_CLASS, pubFileFolderNum);
    Double currentFFVersion = (Double) pubFileFolder.getValue(FileFolderConstants.ATT_TITLE_BLOCK_VERSION);
    try {
      double d = currentFFVersion.doubleValue();
      int ffv = (int) d;
      returnRow.setValue(ItemConstants.ATT_ATTACHMENTS_FOLDER_VERSION, Integer
          .valueOf("" + ffv));
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }
  
  public void attachFileToItem(QxContext qxCtx, String partnumber, RemotePackage remotePackage , Authentication id) throws Exception {
    String tempDir = Properties.get(Names.TEMP_DIR) + Names.PATH_SEPARATOR + "agile-sdk-" + Counter.nextCount();
    String fetchDir = tempDir + Names.PATH_SEPARATOR + "fetch";
    FileDepotClient c = FileDepotClient.materializeClient(tempDir);
    File attachment = c.retrieve(remotePackage, new File(fetchDir));
    
    if (!attachment.exists()) { throw new NotAFile(attachment); }
    if (!attachment.isFile()) { throw new NotAFile(attachment); }
    if (null == partnumber) { throw new InvalidName("NULL"); }
    IAgileSession session = sessions.login(id);
    IItem item = (IItem)session.getObject(IItem.OBJECT_TYPE, partnumber);
    if (item == null) 
      throw new NameNotFound(partnumber);
    ITable table = item.getTable(ItemConstants.TABLE_ATTACHMENTS);
    attach(qxCtx, session, table, attachment, id); 
  }
  
 
  public void attachFileToECOItem(QxContext qxCtx, String partnumber, String ecoNumber, RemotePackage remotePackage , Authentication id) throws Exception {
    if (null == partnumber) { throw new InvalidName("NULL"); }

    String tempDir = Properties.get(Names.TEMP_DIR) + Names.PATH_SEPARATOR + "agile-sdk-" + Counter.nextCount();
    String fetchDir = tempDir + Names.PATH_SEPARATOR + "fetch";
    FileDepotClient c = FileDepotClient.materializeClient(tempDir);
    File attachment = c.retrieve(remotePackage, new File(fetchDir));
    if (!attachment.exists()) { throw new NotAFile(attachment); }
    if (!attachment.isFile()) { throw new NotAFile(attachment); }
    
    IAgileSession session = sessions.login(id);
    IItem item = (IItem)session.getObject(IItem.OBJECT_TYPE, partnumber);
    if (item == null) 
      throw new NameNotFound(partnumber);
    IChange eco = (IChange)session.getObject(IChange.OBJECT_TYPE, ecoNumber);
    if (eco== null) 
      throw new NameNotFound(ecoNumber);
    item.setRevision(eco);
    ITable table = item.getTable(ItemConstants.TABLE_ATTACHMENTS);
     attach(qxCtx, session, table, attachment, id); 
  }

  //From Jason Brown
  private void attach(QxContext qxCtx, IAgileSession session, ITable table, File attachment, Authentication id) throws Exception {
    String attachmentFolderDescription = qxCtx.get(QxContext.ATTACHMENT_FOLDER_DESCRIPTION);
    String attachmentFileDescription = qxCtx.get(QxContext.ATTACHMENT_FILE_DESCRIPTION);
    
    String value=null;
    Object o;
    IRow row=null;
    IRow returnRow = null;
    IFileFolder fileFolder = null;
    IRow fileRow = null;
    ITable files = null;
    boolean replacedFile = false;
    Iterator it = table.iterator();
    while (it.hasNext()) {
      row = ((IRow) it.next());
      o = row.getValue(ItemConstants.ATT_ATTACHMENTS_FILE_NAME);
      if (null!=o) value = o.toString();
      if (value.equalsIgnoreCase(attachment.getName())) {
        returnRow = row;
        fileFolder = (IFileFolder)returnRow.getReferent();
        fileFolder.checkOutEx();       
        files = fileFolder.getTable(FileFolderConstants.TABLE_FILES);
        
        if (files != null) {
          fileRow = (IRow)files.iterator().next();
          fileRow.setValue(FileFolderConstants.ATT_FILES_CONTENT, attachment);               
          replacedFile = true;
        }
        if (replacedFile) {
          fileFolder.checkIn();
        }
        else {
          fileFolder.cancelCheckout();
        }
      }
    }
    if (null==returnRow) returnRow=table.createRow(attachment);

    if (null != attachmentFolderDescription) returnRow.setValue(FileFolderConstants.ATT_ATTACHMENTS_FOLDER_DESCRIPTION, attachmentFolderDescription);
    if (null != attachmentFileDescription)returnRow.setValue(FileFolderConstants.ATT_ATTACHMENTS_FILE_DESCRIPTION, attachmentFileDescription);    
   
    IFileFolder pubFileFolder = (IFileFolder)returnRow.getReferent();
    String pubFileFolderNum = pubFileFolder.getName();
    pubFileFolder = (IFileFolder)session.getObject(FileFolderConstants.CLASS_FILE_FOLDER_BASE_CLASS, pubFileFolderNum);
    
    String currentFFVersion = ""+pubFileFolder.getValue(FileFolderConstants.ATT_TITLE_BLOCK_VERSION);
    try {
      double d = Double.valueOf(currentFFVersion).doubleValue();
      int ffv=(int)d;
      returnRow.setValue(ItemConstants.ATT_ATTACHMENTS_FOLDER_VERSION, new Integer(ffv));
    }
    catch (APIException e) {
      throw e;
    }
    catch (RuntimeException t) {
      throw t;
    }
  }
  /**
   * Attach URL.
   *
   * @param id the id
   * @param partnumber the partnumber
   * @param description the description
   * @param url the url
   *
   * @throws Exception the exception
   */
  public void attachURL(URL url, String description, String partnumber,
      Authentication id) throws Exception {
    try {
      sessions.login(id);
      attachURLToItem(url, description, partnumber, id);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Attach URL to item.
   *
   * @param id the id
   * @param partnumber the partnumber
   * @param description the description
   * @param url the url
   *
   * @throws Exception the exception
   */
  private void attachURLToItem(URL url, String description, String partnumber,
      Authentication id) throws Exception {
    if (null == partnumber) { throw new InvalidName("NULL"); }
    IItem object = findItem(partnumber, id);
    ITable table = object.getTable(ItemConstants.TABLE_ATTACHMENTS);
    attachURL(table, description, url);
  }

  /**
   * Change item number.
   *
   * @param id the id
   * @param item the item
   * @param newNumber the new number
   *
   * @throws Exception the exception
   */
  private void changeItemNumber(IItem item, String newNumber, Authentication id)
      throws Exception {
    sessions.login(id).disableWarning(
        ExceptionConstants.AGILE_OBJECT_ACCESSED_BY_OTHERS);
    try {
      item.setValue(ItemConstants.ATT_TITLE_BLOCK_NUMBER, newNumber);
    } catch (APIException ex) {
      if (ex.isWarning()) {
        sessions.login(id).disableWarning((Integer) ex.getErrorCode());
        changeItemNumber(item, newNumber, id);
      } else {
        throw ex;
      }
    }
  }

  /**
   * Contains.
   *
   * @param id the id
   * @param name the name
   *
   * @return true, if contains
   *
   * @throws Exception the exception
   */
  public boolean contains(String name, Authentication id) throws Exception {
    try {
      sessions.login(id);
      return null != findItem(name, id);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Contains item.
   *
   * @param id the id
   * @param name the name
   *
   * @return true, if contains item
   *
   * @throws Exception the exception
   */
  public boolean containsItem(String name, Authentication id) throws Exception {
    return null != findItem(name, id);
  }

  /**
   * Creates the agile object.
   *
   * @param id the id
   * @param attributes the attributes
   * @param agileClass the agile class
   *
   * @return the i agile object
   *
   * @throws Exception the exception
   */
  public IAgileObject createAgileObject(String agileClass, Map attributes,
      Authentication id) throws Exception {
    IAgileSession session = sessions.login(id);
    return session.createObject(config.findAgileClassID(agileClass), attributes);
  }

  // Fundamentals
  /**
   * Creates the agile object.
   *
   * @param id the id
   * @param partnumber the partnumber
   * @param agileClass the agile class
   *
   * @return the i agile object
   *
   * @throws Exception the exception
   */
  public IAgileObject createAgileObject(String agileClass, String partnumber,
      Authentication id) throws Exception {
    IAgileSession session = sessions.login(id);
    return session.createObject(config.findAgileClassID(agileClass), partnumber);
  }

  /*
   * DELETE BLOCK String num=""; for (int i = 5000; i < 5390; i++) { num =
   * "0000000"+i; num = num.substring(num.length()-7) + "-000"; num += ""; try {
   * delete(num,id); delete(num+"-doc",id); } catch(Exception e) {} } num += "";
   */

  /**
   * Creates the CAD parts.
   *
   * @param id the id
   * @param createCADDocuments the create CAD documents
   * @param metadataList the metadata list
   *
   * @throws Exception the exception
   */
  public void createCADParts(Collection metadataList,
      boolean createCADDocuments, Authentication id) throws Exception {

    IItem part, doc;
    Metadata data;
    String number;
    Iterator i = metadataList.iterator();
    try {
      sessions.login(id);
      IAgileSession session = sessions.login(id);
      Integer cadDocClassID = config.findAgileClassID(AgileSDKConstants.CLASS_TYPE_CAD_DOCUMENT);
      Integer cadPartClassID = config.findAgileClassID(AgileSDKConstants.CLASS_TYPE_CAD_PART);

      while (i.hasNext()) {
        data = (Metadata) i.next();
        number = data.get(AgileSDKConstants.ATT_PART_NUMBER);
        part = (IItem) sessions.login(id).getObject(IItem.OBJECT_TYPE, number);
        if (null == part) {
          part = (IItem) session.createObject(cadPartClassID, number);
        }
        // synchronizeItem(data, part);
        if (createCADDocuments) {
          String docNumber = number + "-doc";
          doc = (IItem) sessions.login(id).getObject(IItem.OBJECT_TYPE,
              docNumber);
          if (null == doc) {
            doc = (IItem) session.createObject(cadDocClassID, docNumber);
          }
          // synchronizeItem(data, doc);
        }
      }
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Creates the item.
   *
   * @param id the id
   * @param data the data
   *
   * @return the i item
   *
   * @throws Exception the exception
   */
  public Metadata createItem(Metadata data, Authentication id) throws Exception {
    String partnumber = data.get(AgileSDKConstants.ATT_PART_NUMBER);
    if (null == partnumber) { throw new InvalidName("NULL"); }
    String agileClass = data.get(AgileSDKConstants.ATT_AGILE_CLASS_TYPE);
    if (agileClass == null || "".equals(agileClass)) {
      throw new NoSuchType("NULL");
    }
    IItem x = findItem(partnumber, id);
    if (null == x) {
      x = (IItem) createAgileObject(agileClass, partnumber, id);
    }
    // synchronizeItem(data, x);

    updateItem(x, data);
    return unmarshall(x);
  }

  /*
   * DELETE BLOCK String num=""; for (int i = 5000; i < 5390; i++) { num =
   * "0000000"+i; num = num.substring(num.length()-7) + "-000"; num += ""; try {
   * delete(num,id); delete(num+"-doc",id); } catch(Exception e) {} } num += "";
   */

  /**
   * Creates the items.
   *
   * @param id the id
   * @param metadataList the metadata list
   *
   * @throws Exception the exception
   */
  public void createItems(Collection metadataList, Authentication id)
      throws Exception {
    IItem part;
    Metadata data;
    String number, agileClass;
    Iterator i = metadataList.iterator();
    try {
      sessions.login(id);
      IAgileSession session = sessions.login(id);
      Integer cadPartClassID = null;

      while (i.hasNext()) {
        data = (Metadata) i.next();
        agileClass = data.get(AgileSDKConstants.ATT_AGILE_CLASS_TYPE);
        cadPartClassID = config.findAgileClassID(agileClass);
        number = data.get(AgileSDKConstants.ATT_PART_NUMBER);
        part = (IItem) sessions.login(id).getObject(IItem.OBJECT_TYPE, number);
        if (null == part) {
          part = (IItem) session.createObject(cadPartClassID, number);
        }
        // synchronizeItem(data, part);
      }
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Delete.
   *
   * @param id the id
   * @param dataList the data list
   *
   * @throws Exception the exception
   */
  public void delete(Collection dataList, Authentication id) throws Exception {
    Metadata data;
    Iterator i = dataList.iterator();
    while (i.hasNext()) {
      data = (Metadata) i.next();
      delete(data, id);
    }
  }

  /**
   * Delete.
   *
   * @param id the id
   * @param m the m
   *
   * @throws Exception the exception
   */
  public void delete(Metadata m, Authentication id) throws Exception {
    try {
      sessions.login(id);
      if (null == m) { return; }
      IItem root = findItem(m, id);
      if (null == root) { return; }
      String agileClassName = m.get(AgileSDKConstants.ATT_AGILE_CLASS_TYPE);
      if (null == agileClassName || "".equals(agileClassName)) {
        deleteItem(root);
      } else if (root.getAgileClass().getName().equalsIgnoreCase(agileClassName)) {
        deleteItem(root);
      } 
      if (!m.hasSubComponents()) { return; }
      Iterator i = m.getSubComponents().iterator();
      while (i.hasNext()) {
        delete((Metadata) i.next(), id);
      }
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Delete.
   *
   * @param id the id
   * @param partNumber the part number
   *
   * @throws Exception the exception
   */
  public void delete(String partNumber, Authentication id) throws Exception {
    try {
      sessions.login(id);
      IItem root = findItem(partNumber, id);
      deleteItem(root);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Delete item.
   *
   * @param root the root
   *
   * @throws Exception the exception
   */
  private void deleteItem(IItem root) throws Exception {
    if (null == root) { return; }
    // ?????
    materializeOrigin(root);
    if (!root.isDeleted()) {
      root.delete();
    }
    if (root.isDeleted()) {
      root.delete();
    }
    // unsynchronizeItem(o);
  }

  /**
   * Establish new part numbers.
   *
   * @param proposedPartNumbers the proposed part numbers
   * @param autoNumberSourceName the auto number source name
   * @param agileClass the agile class
   *
   * @return the map
   *
   * @throws Exception the exception
   */
  public Map establishNewPartNumbers(Map proposedPartNumbers,
      String agileClass, String autoNumberSourceName) throws Exception {
    IAgileClass cls;
    IAgileSession session = sessions.systemLogin();
    session.getAdminInstance();
    cls = config.findAgileClass(agileClass);
    String name, number;
    Map partNumbers = new HashMap();
    Map givenNumbers = new HashMap();
    Iterator i = proposedPartNumbers.keySet().iterator();
    while (i.hasNext()) {
      name = i.next().toString();
      number = (String) proposedPartNumbers.get(name);
      if (null == number || "".equals(number.trim())) {
        number = partNumberSvc.generateNextPartNumber(cls, autoNumberSourceName);
      } else if (containsItem(number, sessions.getSystemAuthentication())) {
        number = partNumberSvc.generateNextPartNumber(cls, autoNumberSourceName);
      } else if (givenNumbers.containsKey(number)) {
        number = partNumberSvc.generateNextPartNumber(cls, autoNumberSourceName);
      }
      partNumbers.put(name, number);
      givenNumbers.put(number, number);
    }
    return partNumbers;
  }

  /**
   * Find item.
   *
   * @param id the id
   * @param m the m
   *
   * @return the i item
   *
   * @throws Exception the exception
   */
  public IItem findItem(Metadata m, Authentication id) throws Exception {
    if (null == m) { return null; }
    return findItem(m.get(AgileSDKConstants.ATT_PART_NUMBER), id);
  }

  /**
   * Find item.
   *
   * @param id the id
   * @param number the number
   *
   * @return the i item
   *
   * @throws Exception the exception
   */
  public IItem findItem(String number, Authentication id) throws Exception {
    // LogWriter.printOnConsole(id, "findItem " + number + "..");
    IItem x = (IItem) sessions.login(id).getObject(IItem.OBJECT_TYPE, number); // try
    // itemconstants.item
    // type
    // LogWriter.printOnConsole(id, "..findItem " + number);
    return x;
  }

  /**
   * Find items.
   *
   * @param id the id
   * @param nameCriteria the name criteria
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  public Collection findItems(String nameCriteria, Authentication id) throws Exception {
    Collection itemList = new Vector();
    try {
      IAgileSession session = sessions.login(id);
      IQuery query = (IQuery) session.createObject(IQuery.OBJECT_TYPE,
          ItemConstants.CLASS_ITEM_BASE_CLASS);
      query.setCaseSensitive(false);
      query.setCriteria("[Number] like '" + nameCriteria + "'");
      ITable queryResults = query.execute();
      Iterator i = queryResults.getReferentIterator();
      if (!i.hasNext()) {
        return itemList;
      }
      IDataObject item;
      while (i.hasNext()) {
        item = (IDataObject) i.next();
        itemList.add(unmarshall(item));
      }
    } catch (APIException ex) {
    }
    Iterator i = itemList.iterator();
    while (i.hasNext()) {
      Metadata c = (Metadata) i.next();
    }
    return itemList;
  }

  /**
   * Gets the life cycle phase.
   *
   * @param item the item
   *
   * @return the life cycle phase
   */
  public String getLifeCyclePhase(IItem item) {
    try {
      Object x = item.getValue(ItemConstants.ATT_TITLE_BLOCK_LIFECYCLE_PHASE);
      if (null == x) { return ""; }
      return x.toString();
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
  }

  /**
   * Checks for not changed.
   *
   * @param item the item
   * @param data the data
   *
   * @return true, if has not changed
   *
   * @throws Exception the exception
   */
  public boolean hasNotChanged(Metadata data, IItem item) throws Exception {
    // If this is from Intralink, really should check that Name, Branch,
    // Revision and Version have not changed
    if (item.getRevision().equalsIgnoreCase(
        data.get(AgileSDKConstants.ATT_REVISION))) { return false; }
    return true;
  }

  /**
   * Checks if is released.
   *
   * @param item the item
   *
   * @return true, if is released
   *
   * @throws APIException the API exception
   */
  public boolean isReleased(IItem item) throws APIException {
    Object o = item.getValue(ItemConstants.ATT_TITLE_BLOCK_REV_RELEASE_DATE);
    if (o == null) { return false; }
    if ("".equals(o.toString().trim())) { return false; }
    return true;
  }

  /*
   * public Collection purgeCADParts() throws Exception { Collection
   * canNotDelete = new Vector(); try {
   * canNotDelete.addAll(purgeSubClasses(CLASS_TYPE_PART, CLASS_TYPE_CAD_PART));
   * canNotDelete.addAll(purgeSubClasses(CLASS_TYPE_DOCUMENT,
   * AgileSDKConstants.CLASS_TYPE_CAD_DOCUMENT)); return canNotDelete; }
   * catch(Exception e) { throw e; } finally {
   */

  /**
   * Purge sub classes.
   *
   * @param subClassType the sub class type
   * @param classType the class type
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  private Collection purgeSubClasses(String classType, String subClassType)
      throws Exception {
    Map partList = new HashMap();
    Collection canNotDelete = new Vector();
    sessions.login(sessions.getSystemAuthentication());
    IAgileSession session = sessions.systemLogin();
    IAdmin admin = session.getAdminInstance();
    admin.getAgileClass(classType);

    IQuery query = (IQuery) session.createObject(IQuery.OBJECT_TYPE,
        ItemConstants.CLASS_ITEM_BASE_CLASS);
    query.setCaseSensitive(false);
    query.setCriteria("[Title Block.Item Type] == '" + subClassType + "'");
    String[] attrs = {"Title Block.Number", "Title Block.Item Type"};
    query.setResultAttributes(attrs);

    ITable results = query.execute();
    Iterator i = results.getReferentIterator();
    IItem item;
    String type, number;
    while (i.hasNext()) {
      item = (IItem) i.next();
      number = item.getName();
      type = item.getValue(ItemConstants.ATT_TITLE_BLOCK_ITEM_TYPE).toString();
      if (type.equals(subClassType)) {
        try {
          deleteItem(item);
          partList.put(number, type);
        } catch (Exception x) {
          canNotDelete.add(number);
        }
      }
    }
    return canNotDelete;
  }

  /**
   * Purge type.
   *
   * @param subClassType the sub class type
   * @param baseClassType the base class type
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  public Collection purgeType(String baseClassType, String subClassType)
      throws Exception {
    try {
      sessions.login(sessions.getSystemAuthentication());
      return purgeSubClasses(baseClassType, subClassType);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Removes the attachment.
   *
   * @param id the id
   * @param partnumber the partnumber
   * @param attachmentName the attachment name
   *
   * @throws Exception the exception
   */
  public void removeAttachment(String partnumber, String attachmentName, Authentication id) throws Exception {
    IItem item = findItem(partnumber, id);
    if (null == item) { throw new NameNotFound("partnumber"); }

    ITable table = item.getTable(ItemConstants.TABLE_ATTACHMENTS);
    boolean moreAttachments = true;
    while (moreAttachments) {
      moreAttachments = false;
      ITwoWayIterator i = table.getTableIterator();
      while (i.hasNext()) {
        IRow row = (IRow) i.next();
        Object o = row.getValue(ItemConstants.ATT_ATTACHMENTS_FILE_NAME);
        String name = o.toString();
        if (name.equals(attachmentName)) {
          table.removeRow(row);
          moreAttachments = true; // reset to remove other attachments by same filename
          break;
        }
      }
    }
  }

  

 
 
  
  /**
   * Rename.
   *
   * @param id the id
   * @param name the name
   * @param newName the new name
   *
   * @throws Exception the exception
   */
  public void rename(String name, String newName, Authentication id)
      throws Exception {
    try {
      sessions.login(id);
      if (null == name) { throw new NullPointerException("Name is null"); }
      if (null == newName) { throw new NullPointerException("New Name is null"); }
      IItem newItem = findItem(newName, id);
      if (null != newItem) { throw new Duplicate(newName); }
      IItem item = findItem(name, id);
      if (null == item) { throw new NameNotFound(name); }
      renameItem(item, newName);
      // resynchronizeToNewItem(name, newName);
    } catch (APIException ex) {
      // check to see if warning
      if (ex.isWarning()) {
        sessions.login(id).disableWarning((Integer) ex.getErrorCode());
        // try again
        rename(name, newName, id);
      } else {
        throw ex;
      }
    }
  }

  /**
   * Rename item.
   *
   * @param item the item
   * @param newName the new name
   *
   * @throws Exception the exception
   */
  private void renameItem(IItem item, String newName) throws Exception {
    if (null == item) { throw new NullPointerException("Item is null"); }
    if (null == newName) { throw new NullPointerException("New Name is null"); }
    IAttribute att = item.getAgileClass().getAttribute(ItemConstants.ATT_TITLE_BLOCK_NUMBER);
    item.setValue(att.getId(), newName);
  }

  /**
   * Renumber.
   *
   * @param id the id
   * @param partnumber the partnumber
   * @param newNumber the new number
   *
   * @return the metadata
   *
   * @throws Exception the exception
   */
  public Metadata renumber(String partnumber, String newNumber,
      Authentication id) throws Exception {
    try {
      sessions.login(id);
      IItem x = findItem(partnumber, id);
      if (null == x) { throw new NameNotFound(partnumber); }
      changeItemNumber(x, newNumber, id);
      return unmarshall(x);
    } catch (Exception e) {
      throw e;
    }
  }

  // From Jason Brown
  /**
   * Replace attachment.
   *
   * @param newURL the new URL
   * @param description the description
   * @param table the table
   * @param oldURL the old URL
   *
   * @throws Exception the exception
   */
  private void replaceAttachment(ITable table, String description, URL oldURL,
      URL newURL) throws Exception {
    String value = null;
    Object o;
    IRow row = null;
    IRow returnRow = null;
    boolean replacedFile = false;
    String urlKey = oldURL.toString();
    int idx = urlKey.toLowerCase().lastIndexOf("|");
    if (idx > -1) {
      urlKey = urlKey.substring(idx);
    }
    Iterator it = table.iterator();
    String type;
    while (it.hasNext()) {
      type = "";
      row = (IRow) it.next();
      o = row.getValue(ItemConstants.ATT_ATTACHMENTS_FILE_TYPE);
      if (null != o) {
        type = o.toString();
      }
      if (!"url".equalsIgnoreCase(type.trim())) {
        continue;
      }
      value = "";
      o = row.getValue(ItemConstants.ATT_ATTACHMENTS_FILE_NAME);
      if (null != o) {
        value = o.toString();
      }
      idx = value.toLowerCase().lastIndexOf("|");
      if (idx > -1) {
        value = value.substring(idx);
      }
      if (value.equalsIgnoreCase(urlKey)) {
        returnRow = row;
        IFileFolder fileFolder = (IFileFolder) returnRow.getReferent();
        fileFolder.checkOutEx();
        ITable files = fileFolder.getTable(FileFolderConstants.TABLE_FILES);
        if (files != null) {
          IRow fileRow = (IRow) files.iterator().next();
          files.removeRow(fileRow);
          IRow replacement = files.createRow(newURL.toString());
          replacement.setValue(FileFolderConstants.ATT_FILES_FILE_DESCRIPTION,
              description);
          replacedFile = true;
        }
        if (replacedFile) {
          fileFolder.checkIn();
        } else {
          fileFolder.cancelCheckout();
        }
      }
    }
    if (null == returnRow) {
      Map values = new HashMap();
      values.put(ItemConstants.ATT_ATTACHMENTS_FILE_NAME, newURL.toString());
      values.put(ItemConstants.ATT_ATTACHMENTS_FILE_DESCRIPTION, description);
      values.put(ItemConstants.ATT_ATTACHMENTS_FILE_TYPE, "url");
      returnRow = table.createRow(values);
    }

    IFileFolder pubFileFolder = (IFileFolder) returnRow.getReferent();
    String pubFileFolderNum = pubFileFolder.getName();
    pubFileFolder = (IFileFolder) sessions.systemLogin().getObject(
        FileFolderConstants.CLASS_FILE_FOLDER_BASE_CLASS, pubFileFolderNum);

    Double currentFFVersion = (Double) pubFileFolder
        .getValue(FileFolderConstants.ATT_TITLE_BLOCK_VERSION);
    try {
      double d = currentFFVersion.doubleValue();
      int ffv = (int) d;
      returnRow.setValue(ItemConstants.ATT_ATTACHMENTS_FOLDER_VERSION, Integer
          .valueOf("" + ffv));
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  /*
   * //returns the subset of metadataParentMap of items that have been added
   * relative to itemParentMap private Map findUpdatedQuantities(Map
   * itemParentMap, Map metadataParentMap) throws Exception { String name; Map m =
   * new HashMap(); Iterator i = metadataParentMap.keySet().iterator(); Pair p;
   * IItem itemKid, itemParent; Metadata dataParent; MetadataSubComponent
   * dataKid; int dataQuantity, itemQuantity; while (i.hasNext()) { name =
   * i.next().toString(); if (!itemParentMap.containsKey(name)) continue; p =
   * (Pair) itemParentMap.get(name); itemParent= (IItem)p.getObject1(); itemKid =
   * (IItem)p.getObject0(); p = (Pair) metadataParentMap.get(name); dataKid =
   * (MetadataSubComponent)p.getObject0(); itemQuantity =
   * findQuantity(itemParent, itemKid.getName()); dataQuantity =
   * dataKid.getQuantity(); if (itemQuantity==dataQuantity) continue;
   * m.put(name, p); } return m; }
   */

  /**
   * Replace URL.
   *
   * @param id the id
   * @param partnumber the partnumber
   * @param newURL the new URL
   * @param description the description
   * @param oldURL the old URL
   *
   * @throws Exception the exception
   */
  public void replaceURL(URL oldURL, URL newURL, String description,
      String partnumber, Authentication id) throws Exception {
    try {
      sessions.login(id);
      replaceURLInItem(oldURL, newURL, description, partnumber, id);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Replace URL in item.
   *
   * @param id the id
   * @param partnumber the partnumber
   * @param newURL the new URL
   * @param description the description
   * @param oldURL the old URL
   *
   * @throws Exception the exception
   */
  private void replaceURLInItem(URL oldURL, URL newURL, String description,
      String partnumber, Authentication id) throws Exception {
    if (null == partnumber) { throw new InvalidName("NULL"); }
    IItem object = findItem(partnumber, id);
    ITable table = object.getTable(ItemConstants.TABLE_ATTACHMENTS);
    replaceAttachment(table, description, oldURL, newURL);
  }

  /*
   * DELETE BLOCK String num=""; for (int i = 5000; i < 5390; i++) { num =
   * "0000000"+i; num = num.substring(num.length()-7) + "-000"; num += ""; try {
   * delete(num,id); delete(num+"-doc",id); } catch(Exception e) {} } num += "";
   */

  /**
   * Update.
   *
   * @param id the id
   * @param dataList the data list
   *
   * @throws Exception the exception
   */
  public void update(Collection dataList, Authentication id) throws Exception {
    Metadata data;
    Iterator i = dataList.iterator();
    while (i.hasNext()) {
      data = (Metadata) i.next();
      update(data, id);
    }
  }

  /**
   * Update.
   *
   * @param id the id
   * @param data the data
   *
   * @throws Exception the exception
   */
  public void update(Metadata data, Authentication id) throws Exception {
    try {
      sessions.login(id);
      String partnumber = data.get(AgileSDKConstants.ATT_PART_NUMBER);
      IDataObject x = findItem(partnumber, id);
      if (null == x) { throw new NameNotFound(partnumber); }
      updateItem(x, data);
    } catch (Exception e) {
      throw e;
    }
  }

  /*
   * //From Jason Brown private void attach(ITable table, String description,
   * URL url) throws Exception { LogWriter.printOnConsole("Attaching " + description + ": " +
   * url.toString()); String value=null; Object o; IRow row=null; IRow returnRow =
   * null; String newURL = url.toString(); int
   * idx=newURL.toLowerCase().indexOf("name="); if (idx>-1) newURL =
   * newURL.substring(idx+5); LogWriter.printOnConsole("looking for " + newURL); ITwoWayIterator it =
   * table.getTableIterator(); while (it.hasNext()) { value=""; row = ((IRow)
   * it.next()); o = row.getValue(ItemConstants.ATT_ATTACHMENTS_FILE_NAME); if
   * (null!=o) value = o.toString(); LogWriter.printOnConsole("found attachment value:" + value);
   * idx=value.toLowerCase().indexOf("name="); if (idx>-1) value =
   * value.substring(idx+5); LogWriter.printOnConsole("checking attachment value:" + value); if
   * (value.equalsIgnoreCase(newURL)) { returnRow = row; break; } } if
   * (null!=returnRow) table.removeRow(returnRow); Map values = new HashMap();
   * values.put(ItemConstants.ATT_ATTACHMENTS_FILE_NAME, url.toString());
   * values.put(ItemConstants.ATT_ATTACHMENTS_FILE_DESCRIPTION, description);
   * values.put(ItemConstants.ATT_ATTACHMENTS_FILE_TYPE, "url"); returnRow =
   * table.createRow(values); }
   */

  /**
   * Update item.
   *
   * @param data the data
   * @param o the o
   *
   * @throws Exception the exception
   */
  protected void updateItem(IDataObject o, Metadata data) throws Exception {
    Map definedAttributes = config.findAttributes(data.get(AgileSDKConstants.ATT_AGILE_CLASS_TYPE)); // +++really slow!!!!!!
    Map definedXMLAttributes = config.findXMLAttributes(data.get(AgileSDKConstants.ATT_AGILE_CLASS_TYPE)); // +++really slow!!!!!!
    Iterator i = data.getAttributes().keySet().iterator();
    String key;
    IAttribute att;
    Integer attID;
    String value;
    int len;
    Map attValues = new HashMap();
    while (i.hasNext()) {
      key = i.next().toString();
      if (key.equalsIgnoreCase(AgileSDKConstants.ATT_PART_NUMBER)) {
        continue;
      }
      if (key.equalsIgnoreCase(AgileSDKConstants.ATT_RELEASE_PHASE)) {
        continue;
      }
      if (key.equalsIgnoreCase(AgileSDKConstants.ATT_REVISION)) {
        continue;
      }
      if (key.equalsIgnoreCase("Lifecycle Phase")) {
        continue;
      }
      if (key.equalsIgnoreCase("CREATED_BY")) {
        continue;
      }

      att = (IAttribute) definedAttributes.get(key.toLowerCase());
      if (null == att) {
        att = (IAttribute) definedXMLAttributes.get(key.toLowerCase());
      }
      if (null == att) {
        continue;
      }
      attID = (Integer) att.getId();
      value = data.get(key);
      try {
        len = att.getMaxLength();
        if (null != value && value.length() > len) {
          value = value.substring(0, att.getMaxLength() - 4) + "...";
        }
      } catch (NoSuchMethodError ignore) { } // att does not have maxlength
      // property
      attValues.put(attID, value);
      o.setValue(attID, value);
      // o.setValue(attID, value);
    }
/*    if (!attValues.isEmpty()) {
      o.setValues(attValues);
    }*/
  }

  /**
   * Verify attachment.
   *
   * @param id the id
   * @param partnumber the partnumber
   * @param filename the filename
   *
   * @return true, if verify attachment
   *
   * @throws Exception the exception
   */
  public boolean verifyAttachment(String partnumber, String filename,
      Authentication id) throws Exception {
    IItem item = findItem(partnumber, id);
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

  /**
   * Find next revision.
   *
   * @param oldRev the old rev
   *
   * @return the string
   */
  public String findNextRevision(String oldRev) {
    String rev = oldRev;
    try {
      if (null == rev) { return "01"; }
      if ("".equals(rev.trim())) { return "01"; }
      if ("(?)".equals(rev)) { return "01"; }
      if ("Introductory".equals(rev)) { return "01"; }
      // check for minor version indicators / and strip off minor version
      int idx = rev.indexOf("(");
      if (idx > 0) {
        rev = rev.substring(0, idx);
      }
      idx = rev.indexOf("-");
      if (idx > 0) {
        rev = rev.substring(0, idx);
      }
      idx = rev.indexOf("_");
      if (idx > 0) {
        rev = rev.substring(0, idx);
      }
      idx = rev.indexOf(".");
      if (idx > 0) {
        rev = rev.substring(0, idx);
      }

      int nextRev = -1;
      try {
        nextRev = Integer.valueOf(rev).intValue();
      } catch (NumberFormatException e) {
        if (rev.length() != 1) { return "101"; }
        if ("Z".equals(rev)) { return "201"; }
        char r = rev.charAt(0);
        r += 1;
        return "" + r;
      }
      if (nextRev > -1) {
        nextRev++;
      }
      if (nextRev < 10) { return "0" + nextRev; }
      return "" + nextRev;
    } catch (Exception e) {
      e.printStackTrace();
      return "Q";
    }
  }


  /**
   * Find.
   *
   * @param id the id
   * @param partnumber the partnumber
   *
   * @return the metadata
   *
   * @throws Exception the exception
   */
  public Metadata find(String partnumber, Authentication id) throws Exception {
      sessions.login(id);
      IDataObject x = findItem(partnumber, id);
      //if (null == x) { throw new NameNotFound(partnumber); }
      return unmarshall(x);
  }

  
  
  /***** ATTACHMENT TEST SUPPORT - EKA 12/18/07 *****/
  
  public void TestAttachFileToItem(QxContext qxCtx, String partnumber, File attachment, Authentication id) throws Exception {    
    if (!attachment.exists()) { throw new NotAFile(attachment); }
    if (!attachment.isFile()) { throw new NotAFile(attachment); }
    if (null == partnumber) { throw new InvalidName("NULL"); }
    IAgileSession session = sessions.login(id);
    IItem item = (IItem)session.getObject(IItem.OBJECT_TYPE, partnumber);
    if (item == null) 
      throw new NameNotFound(partnumber);
    ITable table = item.getTable(ItemConstants.TABLE_ATTACHMENTS);
    attach(qxCtx, session, table, attachment, id); 
  }
  /***** END ATTACHMENT TEST SUPPORT - EKA 12/18/07 *****/
   
  
  /** The bom svc. */
  private AgileSDKPartNumberSvc partNumberSvc = null;
}
