/*
 * DesignState - Design Compression Technology @author: ptoleti @version: 1.0
 * Created on Jul 12, 2007 10:50:04 AM Copywrite (c) 2007 Zero Wait-State Inc.
 * All rights reserved
 */
package zws.repository.agile.sdk.svc;

import zws.data.Metadata;
import zws.data.eco.AffectedItem;
import zws.data.eco.ECO;
import zws.exception.InitializationError;
import zws.exception.NameNotFound;
import zws.repository.agile.sdk.AgileSDKConstants;
import zws.repository.agile.sdk.AgileSDKRepositoryBase;
import zws.security.Authentication;
import zws.util.MapUtil;
import zws.util.PrintUtil;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;


import com.agile.api.APIException;
import com.agile.api.ChangeConstants;
import com.agile.api.ExceptionConstants;

import com.agile.api.IAdmin;
import com.agile.api.IAgileClass;
import com.agile.api.IAgileSession;
import com.agile.api.IAttribute;
import com.agile.api.IAutoNumber;
import com.agile.api.IChange;
import com.agile.api.IItem;
import com.agile.api.IQuery;
import com.agile.api.IRow;
import com.agile.api.IStatus;
import com.agile.api.ITable;
import com.agile.api.IUser;
import com.agile.api.IWorkflow;
import com.agile.api.ItemConstants;

/**
 * The Class AgileSDKECOSvc.
 *
 * @author ptoleti
 */
public class AgileSDKECOSvc extends AgileSDKSvcBase {

  /**
   * The Constructor.
   *
   * @param repository the repository
   * @param configUtil the config util
   * @param sessionUtil the session util
   */
  protected AgileSDKECOSvc(AgileSDKSessionSvc sessionUtil,
      AgileSDKConfigurationSvc configUtil, AgileSDKRepositoryBase repository) {
    super(sessionUtil, configUtil, repository);
  }


  /**
   * Find change.
   *
   * @param id the id
   * @param number the number
   *
   * @return the i change
   *
   * @throws Exception the exception
   */
  public IChange findChange(String number, Authentication id) throws Exception {
    IChange x = (IChange) sessions.login(id).getObject(IChange.OBJECT_TYPE, number);
    return x;
  }



  /**
   * Adds the change.
   *
   * @param id the id
   * @param changedItem the changed item
   * @param eco the eco
   *
   * @throws Exception the exception
   */
  public void addChange(IChange eco, AffectedItem changedItem, Authentication id)
      throws Exception {
    AgileSDKAffectedItemSvc affectedItemSvc = materializeAffectedItemSvc();
    IItem item = affectedItemSvc.findItem(changedItem.getItemNumber(), id);
    ITable affectedItems = eco.getTable(ChangeConstants.TABLE_AFFECTEDITEMS);
    if (affectedItemSvc.listedAsAffectedItem(changedItem.getItemNumber(),
        affectedItems)) { return; }
    IRow row;
    row = affectedItems.createRow(item);
    Map params = new HashMap();
    if (null != changedItem.getDescription()) {
      params.put(ChangeConstants.ATT_AFFECTED_ITEMS_ITEM_DESCRIPTION,
          changedItem.getDescription());
    }
    String nextRev = changedItem.getNewRev();
    /* +++ make configurable
    if (null == changedItem.getNewRev()) {
      nextRev = affectedItemSvc.findNextRevision(row.getValue(
          ChangeConstants.ATT_AFFECTED_ITEMS_OLD_REV).toString());
    }
    */
    if (null != nextRev && !"".equals(nextRev.trim())) {
      params.put(ChangeConstants.ATT_AFFECTED_ITEMS_NEW_REV, nextRev);
    }
    if (null != changedItem.getNewLifeCyclePhase()) {
      params.put(ChangeConstants.ATT_AFFECTED_ITEMS_LIFECYCLE_PHASE,
          changedItem.getNewLifeCyclePhase());
    }
    row.setValues(params);
    return;
    // row.setValue(ChangeConstants.ATT_AFFECTED_ITEMS_ITEM_DESCRIPTION,
    // changedItem.getDescription());
    // row.setValue(ChangeConstants.ATT_AFFECTED_ITEMS_LIFECYCLE_PHASE,
    // changedItem.getLifeCyclePhase());
    // IRow affectedItemRow = affectedItems.createRow(params);
  }

  /**
   * Adds the change.
   *
   * @param affectedItems the affected items
   * @param item the item
   * @param changedItem the changed item
   * @param eco the eco
   *
   * @throws Exception the exception
   */
  protected void addChange(IChange eco, ITable affectedItems, IItem item,
      AffectedItem changedItem) throws Exception {
    
    IRow row = null;
    
    AgileSDKAffectedItemSvc affectedItemSvc = materializeAffectedItemSvc();
    
    if (affectedItemSvc.listedAsAffectedItem(changedItem.getItemNumber(),
        affectedItems)) {       
      row = affectedItemSvc.lookupAffectedItem(changedItem.getItemNumber(),
          affectedItems);  
    } else {
      row = affectedItems.createRow(item);       
    }

    Map params = new HashMap();
    if (null != changedItem.getDescription()) {
      params.put(ChangeConstants.ATT_AFFECTED_ITEMS_ITEM_DESCRIPTION,
          changedItem.getDescription());
    }
    String nextRev = changedItem.getNewRev();
    /* ++ make this configurable
     if (null == changedItem.getNewRev()) {
      nextRev = affectedItemSvc.findNextRevision(row.getValue(
          ChangeConstants.ATT_AFFECTED_ITEMS_OLD_REV).toString());
    }
    */
    if (null != nextRev && !"".equals(nextRev.trim())) {
      params.put(ChangeConstants.ATT_AFFECTED_ITEMS_NEW_REV, nextRev);
    }
    if (null != changedItem.getNewLifeCyclePhase()) {
      params.put(ChangeConstants.ATT_AFFECTED_ITEMS_LIFECYCLE_PHASE, changedItem.getNewLifeCyclePhase());
    }
    row.setValues(params);
    return;
    // row.setValue(ChangeConstants.ATT_AFFECTED_ITEMS_ITEM_DESCRIPTION,
    // changedItem.getDescription());
    // row.setValue(ChangeConstants.ATT_AFFECTED_ITEMS_LIFECYCLE_PHASE,
    // changedItem.getLifeCyclePhase());
    // IRow affectedItemRow = affectedItems.createRow(params);
  }

  
  
  /**
   * Adds the change.
   *
   * @param id the id
   * @param ecoNumber the eco number
   * @param changedItem the changed item
   *
   * @return the agile ECO
   *
   * @throws Exception the exception
   */
  public ECO addChange(String ecoNumber, AffectedItem changedItem,
      Authentication id) throws Exception {
    try {
      AgileSDKAffectedItemSvc affectedItemSvc = materializeAffectedItemSvc();
      sessions.login(id);
      sessions.login(id).disableWarning(ExceptionConstants.APDM_PENDINGCHANGE_ITEM_WARNING);
      //sessions.login(id).disableWarning(ExceptionConstants.APDM_CHANGE_ADD_AFFECTEDITEM);
      IChange eco = findChange(ecoNumber, id);
      if (null==eco) throw new NameNotFound(ecoNumber + " ECO does not exist in agile.");
      ITable affectedItems = eco.getTable(ChangeConstants.TABLE_AFFECTEDITEMS);
      IItem item = affectedItemSvc.findItem(changedItem.getItemNumber(), id);
      if (null==item) throw new NameNotFound(changedItem.getItemNumber() + " Item does not exist in agile. Can not add  "+changedItem.getItemNumber()+" to eco "+ecoNumber+".");
      addChange(eco, affectedItems, item, changedItem);
      return findECO(ecoNumber, id);
    } catch (Exception e) {
      throw e;
    }
  }

  
  
  
  public void deleteChange(String ecoNumber, boolean truncateFlag, Authentication id) throws Exception {
	    try {
	      AgileSDKAffectedItemSvc affectedItemSvc = materializeAffectedItemSvc();
	      sessions.login(id);
	      sessions.login(id).disableWarning(ExceptionConstants.APDM_PENDINGCHANGE_ITEM_WARNING);
	      IChange eco = findChange(ecoNumber, id);
	      //if (null==eco) throw new NameNotFound(ecoNumber + " ECO does not exist in agile.");
	      if (null==eco) {
	    	  System.out.println(" ECO does not exist in agile." + ecoNumber);
	    	  return;
	      }
	      ITable affectedItems = eco.getTable(ChangeConstants.TABLE_AFFECTEDITEMS);
	      if(null == affectedItems || affectedItems.size() > 0) return;
	      eco.delete();
	      if(truncateFlag) {
	    	  permanentDeleteChange(eco, id);
	      }
	    } catch (Exception e) {
	      throw e;
	    }
	  }

  private void permanentDeleteChange(IChange eco, Authentication id) throws Exception {
	  eco.delete();
  }
  
  /**
   * Change status.
   *
   * @param id the id
   * @param change the change
   * @param stat the stat
   *
   * @throws Exception the exception
   */
  private void changeStatus(IChange change, IStatus stat, Authentication id)
      throws Exception {
    sessions.login(id).disableWarning(
        ExceptionConstants.APDM_ITEMHAS_PENDINGCHANGES_WARNING);
    sessions.login(id).disableWarning(
        ExceptionConstants.APDM_UNRELEASEDCHILD_WARNING);
    sessions.login(id).disableWarning(
        ExceptionConstants.APDM_CS_RELREVCHECK_WARNING);
    sessions.login(id).disableWarning(
        ExceptionConstants.APDM_CAUSECONFLICTS_WARNING);
    try {
      Object[] noObjs=null;
      change.changeStatus(stat, true, "", true, false, noObjs, noObjs, noObjs, false);
    } catch (APIException ex) {
      // check to see if warning
      if (ex.isWarning()) {
        sessions.login(id).disableWarning((Integer) ex.getErrorCode());
        // try again
        changeStatus(change, stat, id);
      } else {
       	ex.printStackTrace();
        throw ex;
      }
    }
  }

  public void setECOAttribute(String ecoNumber, String attribute, String value, Authentication id) throws Exception {
    IChange changeOrder = this.findChange(ecoNumber, id);
    setECOAttribute(changeOrder, attribute, value);
  }
  
  public void setECOOriginator(String ecoNumber, String value, Authentication id) throws Exception {
    IChange changeOrder = this.findChange(ecoNumber, id);
    setECOUser(changeOrder, AgileSDKConstants.ORIGINATOR, value, id);
  }
  
  public void setECOOriginator(IChange changeOrder, String value, Authentication id) throws Exception {
    setECOUser(changeOrder, AgileSDKConstants.ORIGINATOR, value, id);
  }

  public void setECOChangeAnalyst(String ecoNumber, String value, Authentication id) throws Exception {
    IChange changeOrder = this.findChange(ecoNumber, id);
    setECOUser(changeOrder, AgileSDKConstants.CHANGE_ANALYST, value, id);
  }
  

  public void setECOChangeAnalyst(IChange changeOrder, String value, Authentication id) throws Exception {
    setECOUser(changeOrder, AgileSDKConstants.CHANGE_ANALYST, value, id);
  }
  
  public void setECOUser(IChange changeOrder, String userType, String userName, Authentication id) throws Exception {
  
    IUser agileUser = (IUser)sessions.login(id).getObject(IUser.OBJECT_TYPE, userName);
    if(null == agileUser) return;
    String className = changeOrder.getAgileClass().getName();    
    String att = userType;
    if (null==att) throw new zws.exception.InvalidName("eco attribute: " + att);

    Map definedAttributes = config.findAttributes(className);
    PrintUtil.print("definedAttributes" + definedAttributes);
    
    Map definedXMLAttributes = config.findXMLAttributes(className);
    att = att.trim().toLowerCase();

    IAttribute a = (IAttribute) definedAttributes.get(att);
    if (null == att)      a = (IAttribute) definedXMLAttributes.get(att);
    if (null == a) {
      return;
    }
    changeOrder.setValue(a, agileUser);
  }
  
  private void setECOAttribute(IChange changeOrder, String attribute, String value) throws Exception {
    String className = changeOrder.getAgileClass().getName();    
    //Page 2 Attributes
    String att = attribute;
    if (null==att) throw new zws.exception.InvalidName("eco attribute: " + attribute);

    Map definedAttributes = config.findAttributes(className);
    
    Map definedXMLAttributes = config.findXMLAttributes(className);
    att = att.trim().toLowerCase();
    IAttribute a = (IAttribute) definedAttributes.get(att);
    if (null == att) a = (IAttribute) definedXMLAttributes.get(att);
    if (null == a) {
      return;
    }
    changeOrder.setValue(a, value);
    Integer attID = (Integer) a.getId();
    try {
      int len = a.getMaxLength();
      if (null != value && value.length() > len) value = value.substring(0, len - 4) + "...";
    } catch (NoSuchMethodError ignore) { } // att does not have maxlength
    changeOrder.setValue(attID, value);
  }  

  
  private IChange createChange(Map ecoAttributes, Authentication id) throws Exception {
    String agileClass=null;
    String autoNumSource=null;
    String wflow=null;
    String analyst=null;
    Map userAtts= new HashMap();
    
    Iterator i = ecoAttributes.keySet().iterator();
    String att, value;
    while (i.hasNext() ) {
      att = (String)i.next();
      value = (String)ecoAttributes.get(att);
      if ("agile-class".equalsIgnoreCase(att)) { agileClass = value; continue; }
      else if ("auto-number-source".equalsIgnoreCase(att)) { autoNumSource= value; continue; }
      else if ("workflow".equalsIgnoreCase(att)) { wflow= value; continue; }
      else if ("analyst".equalsIgnoreCase(att)) { analyst= value; continue; }
      else if ("change analyst".equalsIgnoreCase(att)) {  continue; }
      else if ("originator".equalsIgnoreCase(att)) {  continue; }
      else userAtts.put(att, value);
    }
        
    if (null==agileClass) throw new InitializationError("agile-class not defined for ECO");
    IChange changeOrder = createChange(agileClass, autoNumSource, wflow, analyst, id);

    i = userAtts.keySet().iterator();
    while (i.hasNext() ) {
      att = (String)i.next();
      value = (String)ecoAttributes.get(att);
      setECOAttribute(changeOrder, att, value);
    }
    
    if(ecoAttributes.containsKey(AgileSDKConstants.ORIGINATOR)) {
      String originator = (String) ecoAttributes.get(AgileSDKConstants.ORIGINATOR);
      if(null != originator && originator.length() > 0) {
        setECOOriginator(changeOrder, originator, id);
      }
    }

    if(ecoAttributes.containsKey(AgileSDKConstants.CHANGE_ANALYST)) {
      String changeAnalyst= (String) ecoAttributes.get(AgileSDKConstants.CHANGE_ANALYST);
      if(null != changeAnalyst && changeAnalyst.length() > 0) {     
        setECOChangeAnalyst(changeOrder, changeAnalyst, id);
      }
    }
    return changeOrder;
  }
  
  /**
   * Creates the change.
   *
   * @param id the id
   * @param analyst the analyst
   * @param ecoClassName the eco class name
   * @param wflow the wflow
   * @param autoNumSource the auto num source
   *
   * @return the i change
   *
   * @throws Exception the exception
   */
  private IChange createChange(String ecoClassName, String autoNumSource,
      String wflow, String analyst, Authentication id) throws Exception {
    IAgileClass ecoClass = null;
    if (null != ecoClassName) {
      ecoClass = sessions.login(id).getAdminInstance().getAgileClass(
          ecoClassName);
    }
    if (null == ecoClassName) {
      ecoClass = sessions.login(id).getAdminInstance().getAgileClass(
          ChangeConstants.CLASS_ECO);
    }

    if (null == ecoClass) throw new zws.exception.NameNotFound("Agile ECO Subclass: " + ecoClassName);

    IAutoNumber[] ans = ecoClass.getAutoNumberSources();
    IAutoNumber an = null;
    if (null == autoNumSource) {
      an = ans[0];
    } else {
      for (int idx = 0; idx < ans.length; idx++) {
        if (ans[idx].getName().equalsIgnoreCase(autoNumSource)) {
          an = ans[idx];
        }
      }
      if (null == an) {
        an = ans[0];
      }
    }

    IChange change = (IChange) sessions.login(id).createObject(ecoClass, an);

    // set workflow
    IWorkflow[] wfs = change.getWorkflows();
    IWorkflow wf = null;
    if (null == wflow) {
      wf = wfs[0];
    } else {
      for (int idx = 0; idx < wfs.length; idx++) {
        if (wfs[idx].getName().equalsIgnoreCase(wflow)) {
          wf = wfs[idx];
        }
      }
      if (null == wf) {
        wf = wfs[0];
      }
    }
    change.setWorkflow(wf);

    // define a changeAnalyst
    IUser changeAnalyst = null;
    
    /*
     * if (null == analyst) { ; // find user }
     */

    //if (null == changeAnalyst) { changeAnalyst = sessions.login(id).getCurrentUser(); }
    if (null!=changeAnalyst) change.setValue(ChangeConstants.ATT_COVER_PAGE_CHANGE_ANALYST, changeAnalyst);
    return change;
  }

  
  
  
  
  /**
   * Creates the ECO.
   *
   * @param id the id
   *
   * @return the agile ECO
   *
   * @throws Exception the exception
   */
  public ECO createECO(Authentication id) throws Exception {
    try {
      sessions.login(id);
      IChange change = createChange(null, null, null, null, id);
      ECO eco = unmarshall(change);
      return eco;
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Creates the ECO.
   *
   * @param id the id
   * @param data the data
   *
   * @throws Exception the exception
   */
/*  public void createECO(Metadata data, Authentication id) throws Exception {
    AgileSDKAffectedItemSvc affectedItemSvc = materializeAffectedItemSvc();
    IItem root = affectedItemSvc.findItem(data, id);
    if (root == null) { throw new NameNotFound(data.getName()); }
    String ecoNumber = data.getName() + AgileSDKConstants.DOT
        + data.get(AgileSDKConstants.ATT_BRANCH) + AgileSDKConstants.DOT
        + data.get(AgileSDKConstants.ATT_REVISION) + AgileSDKConstants.DOT
        + data.get(AgileSDKConstants.ATT_VERSION);
    // if (hasPendingChange(data)) throw new PendingChangeExists(data);

    IChange eco = createECO(ecoNumber, id);
    includeAffectedItem(data, eco, id);
    root.setRevision(eco);
    // addAffectedItems();
  }
*/
  /**
   * Creates the ECO.
   *
   * @param id the id
   * @param ecoNumber the eco number
   *
   * @return the i change
   *
   * @throws Exception the exception
   */
  private IChange createECO(String ecoNumber, Authentication id)
      throws Exception {
    IChange change = (IChange) sessions.login(id).createObject(
        ChangeConstants.CLASS_ECO, ecoNumber);
    change.setWorkflow(change.getWorkflows()[0]);
    return change;
  }

  /**
   * Creates the ECO.
   *
   * @param id the id
   * @param ecoClass the eco class
   * @param autoNumSource the auto num source
   * @param workflow the workflow
   * @param changeAnalyst the change analyst
   *
   * @return the agile ECO
   *
   * @throws Exception the exception
   */
  public ECO createECO(String ecoClass, String autoNumSource,
      String workflow, String changeAnalyst, Authentication id)
      throws Exception {
    try {
      sessions.login(id);
      IChange change = createChange(ecoClass, autoNumSource, workflow,
          changeAnalyst, id);
      ECO eco = unmarshall(change);
      return eco;
    } catch (Exception e) {
      throw e;
    }
  }  


  
  public ECO createECO(Map ecoAttributes, Authentication id)
      throws Exception {
    try {
      sessions.login(id);
      IChange change = createChange(ecoAttributes, id);
      ECO eco = unmarshall(change);
      return eco;
    } catch (Exception e) {
      throw e;
    }
  }  

  
  public Collection detectPendingChanges(AffectedItem affectedItem, Authentication id)
      throws Exception {
    Collection c = new ArrayList();
    c.add(affectedItem);
    Map map = detectPendingChanges(c, id);
    return map.keySet();
  }

  /**
   * Detect pending changes. Result will be key value pair with
   * key - ECO number
   * Value collection of affected Items.
   * @param id the id
   * @param changedItems the changed items
   * @return the map
   * @throws Exception the exception
   */
  public Map detectPendingChanges(Collection changedItems, Authentication id)
      throws Exception {
    AgileSDKAffectedItemSvc affectedItemSvc = materializeAffectedItemSvc();
    Iterator i = changedItems.iterator();
    AffectedItem affectedItem;
    IItem item;
    ITable pendingECOs;
    IChange change;
    Map pendingChanges = new HashMap();
    while (i.hasNext()) {
      affectedItem = (AffectedItem) i.next();
      item = affectedItemSvc.findItem(affectedItem.getItemNumber(), id);
      if (null == item) {
        continue;
      }
      pendingECOs = item.getTable(ItemConstants.TABLE_PENDINGCHANGES);
      if (null == pendingECOs) {
        continue;
      }
      Iterator t = pendingECOs.getReferentIterator();
      while (t.hasNext()) {
        change = (IChange) t.next();
        MapUtil.getCollectionFromMap(pendingChanges, change.getName()).add(affectedItem.getItemNumber());
      }
    }
    PrintUtil.print("pendingChanges " +pendingChanges);
    return pendingChanges;
  }

  /**
   * Find ECO.
   *
   * @param id the id
   * @param number the number
   *
   * @return the agile ECO
   *
   * @throws Exception the exception
   */
  public ECO findECO(String number, Authentication id) throws Exception {
    try {
      sessions.login(id);
      IAgileSession session = sessions.login(id);
      IAdmin admin = session.getAdminInstance();
      IAgileClass cls = admin.getAgileClass("Change Orders");
      IQuery query = (IQuery) session.createObject(IQuery.OBJECT_TYPE, cls);
      query.setCaseSensitive(false);
      String criteria = "[Cover Page.Number] == '" + number + "' ";
      query.setCriteria(criteria);
      ITable queryResults = query.execute();
      if (1 != queryResults.size()) { return null; }
      Iterator i = queryResults.getReferentIterator();
      IChange changeOrder = (IChange) i.next();
      ECO eco = unmarshall(changeOrder);
      return eco;
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Find EC os.
   *
   * @param id the id
   * @param descriptionCriteria the description criteria
   * @param nameCriteria the name criteria
   * @param workflowCriteria the workflow criteria
   * @param statusCriteria the status criteria
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  public Collection findECOs(String nameCriteria, String descriptionCriteria,
      String statusCriteria, String workflowCriteria, Authentication id)
      throws Exception {
    Collection ecoList = new Vector();
    try {
      sessions.login(id);
      IAgileSession session = sessions.login(id);
      IAdmin admin = session.getAdminInstance();
      IAgileClass cls = admin.getAgileClass("Change Orders");
      IQuery query = (IQuery) session.createObject(IQuery.OBJECT_TYPE, cls);
      query.setCaseSensitive(false);
      String cName = null, cDescription = null, cStatus = null, cWorkflow = null;
      if (nameCriteria != null && !"".equals(nameCriteria.trim())) {
        cName = nameCriteria;
      }
      if (descriptionCriteria != null && !"".equals(descriptionCriteria.trim())) {
        cDescription = descriptionCriteria;
      }
      if (statusCriteria != null && !"".equals(statusCriteria.trim())) {
        cStatus = statusCriteria;
      }
      if (workflowCriteria != null && !"".equals(workflowCriteria.trim())) {
        cWorkflow = workflowCriteria;
      }
      String criteria = "";
      if (null != cName) {
        criteria = "[Cover Page.Number] like '" + cName + "' ";
      }
      if (null != cDescription) {
        if (!"".equals(criteria)) {
          criteria += " and ";
        }
        criteria += "[" + ChangeConstants.ATT_COVER_PAGE_DESCRIPTION
            + "] like '" + cDescription + "' ";
      }
      if (null != cStatus) {
        if (!"".equals(criteria)) {
          criteria += " and ";
        }
        criteria += "[" + ChangeConstants.ATT_COVER_PAGE_STATUS + "] == '"
            + cStatus + "' ";
      }
      if (null != cWorkflow) {
        if (!"".equals(criteria)) {
          criteria += " and ";
        }
        criteria += "[" + ChangeConstants.ATT_COVER_PAGE_WORKFLOW + "] like '"
            + cWorkflow + "' ";
      }
      query.setCriteria(criteria);
      ITable queryResults = query.execute();
      Iterator i = queryResults.getReferentIterator();
      if (!i.hasNext()) {
        return ecoList;
      }
      IChange changeOrder;
      while (i.hasNext()) {
        changeOrder = (IChange) i.next();
        ecoList.add(unmarshall(changeOrder));
      }
    } catch (Exception e) {
      throw e;
    }
    Iterator i = ecoList.iterator();
    while (i.hasNext()) {
      ECO c = (ECO) i.next();
    }
    return ecoList;
  }

  /**
   * Move to next status.
   *
   * @param id the id
   * @param change the change
   *
   * @throws Exception the exception
   */
  private void moveToNextStatus(IChange change, Authentication id)
      throws Exception {
    // Check if the user has privileges to change to the next status
    IStatus nextStatus = change.getDefaultNextStatus();
    if (nextStatus == null) {
      return;
    }
    changeStatus(change, nextStatus, id);
  }

  /**
   * Move to next status.
   *
   * @param id the id
   * @param ecoNumber the eco number
   *
   * @return the agile ECO
   *
   * @throws Exception the exception
   */
  public ECO moveToNextStatus(String ecoNumber, Authentication id)
      throws Exception {
    try {
      sessions.login(id);
      IChange change = findChange(ecoNumber, id);
      moveToNextStatus(change, id);
      change = findChange(ecoNumber, id);
      ECO eco = unmarshall(change);
      return eco;
    } catch (Exception e) {
      throw e;
    }
  }



  /**
   * Include affected item.
   *
   * @param id the id
   * @param data the data
   * @param eco the eco
   *
   * @throws Exception the exception
   */

  private void includeAffectedItem(Metadata data, IChange eco, Authentication id) throws Exception {
    AgileSDKAffectedItemSvc affectedItemSvc = materializeAffectedItemSvc();
    IItem item = affectedItemSvc.findItem(data, id);
    if (affectedItemSvc.hasNotChanged(data, item)) { return; }
    ITable affectedItems = eco.getTable(ChangeConstants.TABLE_AFFECTEDITEMS);
    if (!affectedItemSvc.isNewAffectedItem(data, item, affectedItems)) { return; }

    Map params = new HashMap();
    params.put(ChangeConstants.ATT_AFFECTED_ITEMS_ITEM_NUMBER, item);
    params.put(ChangeConstants.ATT_AFFECTED_ITEMS_NEW_REV, affectedItemSvc.findNextRevision(item.getRevision()));
    affectedItems.createRow(params);
  }

  /** The affected item svc. */
  //private AgileSDKAffectedItemSvc affectedItemSvc = null;
}
