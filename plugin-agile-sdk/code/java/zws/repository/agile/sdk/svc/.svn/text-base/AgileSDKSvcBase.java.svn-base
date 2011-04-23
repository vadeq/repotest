/*
 * DesignState - Design Compression Technology @author: ptoleti @version: 1.0
 * Created on Jul 10, 2007 9:53:56 AM Copywrite (c) 2007 Zero Wait-State Inc.
 * All rights reserved
 */
package zws.repository.agile.sdk.svc;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import com.agile.api.APIException;
import com.agile.api.ChangeConstants;
import com.agile.api.IAgileList;
import com.agile.api.IAttribute;
import com.agile.api.IChange;
import com.agile.api.IDataObject;
import com.agile.api.IItem;
import com.agile.api.IRow;
import com.agile.api.ITable;
import com.agile.api.ITwoWayIterator;

import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.eco.AffectedItem;
import zws.data.eco.ECO;
import zws.origin.AgileOrigin;
import zws.repository.agile.sdk.AgileSDKConstants;
import zws.repository.agile.sdk.AgileSDKRepositoryBase;
import zws.security.Authentication;


/**
 * The Class AgileSDKCommons.
 *
 * @author ptoleti
 */
public class AgileSDKSvcBase {

  /**
   * The Constructor.
   *
   * @param agileRepository agileRepository
   */
  public AgileSDKSvcBase(AgileSDKRepositoryBase agileRepository) {
    repository = agileRepository;
    sessions = new AgileSDKSessionSvc(repository);
    config = new AgileSDKConfigurationSvc(sessions);
  }

  /**
   * The Constructor.
   *
   * @param configUtil the config util
   * @param sessionUtil the session util
   * @param agileRepository agileRepository
   */
  protected AgileSDKSvcBase(AgileSDKSessionSvc sessionUtil, AgileSDKConfigurationSvc configUtil, AgileSDKRepositoryBase agileRepository) {
    sessions = sessionUtil;
    config = configUtil;
    repository = agileRepository;
  }

  
  public void open(Authentication id) throws Exception {
    sessions.login(id);
  }
  public void close(Authentication id) throws Exception {
    sessions.logout(id);    
  }
    
  /**
   * Materialize item utility.
   *
   * @return the item utility
   */
  public AgileSDKItemSvc materializeItemSvc() {
    AgileSDKItemSvc util = new AgileSDKItemSvc(sessions, config, repository);
    return util;
  }


  /**
   * Materialize AffectedItem utility.
   *
   * @return the ECO utility
   */
  public AgileSDKBOMSvc materializeBOMSvc() {
    AgileSDKBOMSvc util = new AgileSDKBOMSvc(sessions, config, repository);
    return util;
  }


  /**
   * Materialize ECO utility.
   *
   * @return the ECO utility
   */
  public AgileSDKECOSvc materializeECOSvc() {
    AgileSDKECOSvc util = new AgileSDKECOSvc(sessions, config, repository);
    return util;
  }


  /**
   * Materialize AffectedItem utility.
   *
   * @return the ECO utility
   */
  public AgileSDKAffectedItemSvc materializeAffectedItemSvc() {
    AgileSDKAffectedItemSvc util = new AgileSDKAffectedItemSvc(sessions, config, repository);
    return util;
  }

  /**
   * Materialize Revision Control utility.
   *
   * @return the ECO utility
   */
  public AgileSDKRevisionControlSvc materializeRevisionControlSvc() {
    AgileSDKRevisionControlSvc util = new AgileSDKRevisionControlSvc(sessions, config, repository);
    return util;
  }

  /**
   * Materialize Part Numberutility.
   *
   * @return the ECO utility
   */
  public AgileSDKPartNumberSvc materializePartNumberSvc() {
    AgileSDKPartNumberSvc util = new AgileSDKPartNumberSvc(sessions, config, repository);
    return util;
  }

  /**
   * Materialize config utility.
   *
   * @return the ECO utility
   */
  public AgileSDKConfigurationSvc materializeConfigurationSvc() {
        return config;
  }



  // Some Utilities
  /**
   * Prints the list.
   *
   * @param level the level
   * @param list the list
   *
   * @throws APIException the API exception
   */
  public void printList(IAgileList list, int level) throws APIException {
    if (list != null) {
      Object[] children = list.getChildren();
      if (children != null) {
        for (int i = 0; i < children.length; ++i) {
          printList((IAgileList) children[i], level + 1);
        }
      }
    }
  }

  /**
   * Unmarshall.
   *
   * @param data data
   *
   * @return metadata
   *
   * @throws Exception exception
   */
  public Metadata unmarshall(IDataObject data) throws Exception {
    if(null == data) {
      return null;
    }
    AgileOrigin o = null;
    MetadataBase d = null;
    try {
      /*o = new AgileOrigin(repository.getDomainName(), repository.getServerName(), repository.getName(),
          data.getObjectId().toString(), data.getAgileClass().getName(), data.getName());*/
      o = materializeOrigin((IItem) data);
      d = new MetadataBase();
      d.setOrigin(o);
      d.setName(data.getName());
      d.set(AgileSDKConstants.ATT_AGILE_CLASS_TYPE, data.getAgileClass().getName());
      d.set(AgileSDKConstants.ATT_PART_NUMBER, data.getName());
      Map definedAttributes = config.findAttributes(d.get(AgileSDKConstants.ATT_AGILE_CLASS_TYPE));
      Iterator i = definedAttributes.keySet().iterator();
      String att, value;
      IAttribute a;
      Object val;
      while (i.hasNext()) {
        val = null;
        att = (String) i.next();
        a = (IAttribute) definedAttributes.get(att);
        try {
          val = data.getValue(a);
        } catch (Exception ignore) {
        } // +++modify to precheck - instead of catching exception
        if (null == val) {
          continue;
        }
        value = val.toString();
        if ("".equals(value.trim())) {
          continue;
        }
        d.set(att, value);
      }
    } catch (Exception exp) {
      exp.printStackTrace();
    }
    return d;
  }

  /**
   * Clear table.
   *
   * @param table the table
   *
   * @throws Exception the exception
   */
  public void clearTable(ITable table) throws Exception {
    Collection rows = new Vector();
    ITwoWayIterator i = table.getTableIterator();
    while (i.hasNext()) {
      rows.add(i.next());
    }
    table.removeAll(rows);
  }

  /**
   * Unmarshall.
   *
   * @param changeOrder the change order
   *
   * @return the agile ECO
   *
   * @throws Exception the exception
   */
  public ECO unmarshall(IChange changeOrder) throws Exception {
    String number = null, type = null, description = null, status = null, defaultNextStatus = null, workflow = null;
    number = changeOrder.getName();
    //Title Page Attributes
    if (null != changeOrder.getStatus()) {
      status = changeOrder.getStatus().toString();
    } else {
      status = "Unassigned";
    }
    if (null != changeOrder.getDefaultNextStatus()) {
      defaultNextStatus = changeOrder.getDefaultNextStatus().toString();
    }
    if (null != changeOrder.getWorkflow()) {
      workflow = changeOrder.getWorkflow().getName();
    }
    description = changeOrder.getValue(ChangeConstants.ATT_COVER_PAGE_DESCRIPTION).toString();
    type = changeOrder.getValue(ChangeConstants.ATT_COVER_PAGE_CHANGE_TYPE).toString();
    // analyst=
    // changeOrder.getValue(ChangeConstants.ATT_COVER_PAGE_CHANGE_ANALYST).toString();
    // =
    // changeOrder.getValue(ChangeConstants.ATT_COVER_PAGE_CHANGE_CATEGORY).toString();
    // =
    // changeOrder.getValue(ChangeConstants.ATT_COVER_PAGE_DATE_ORIGINATED_).toString();
    if ("".equals(description)) {
      description = null;
    }

    ECO eco = new ECO(number, type, description, status, workflow, defaultNextStatus); 
    String className = changeOrder.getAgileClass().getName();    
    //Page 2 Attributes
    Map definedAttributes = config.findAttributes(className);
    Iterator atts = definedAttributes.keySet().iterator();
    String attKey, attributeName, value;
    IAttribute a;
    Object val;
    while (atts.hasNext()) {
      val = null;
      attKey = (String) atts.next();
      a = (IAttribute) definedAttributes.get(attKey);
      attributeName= a.getName();
      try {
        val = changeOrder.getValue(a);
      } catch (Exception ignore){
      } // +++modify to precheck - instead of catching exception
      if (null == val) {
        continue;
      } 
      value = val.toString();
      if ("".equals(value.trim())) {
        continue;
      }
      eco.set(attributeName, value);
    }
    
    ITable affectedItems = changeOrder
        .getTable(ChangeConstants.TABLE_AFFECTEDITEMS);
    ITwoWayIterator i = affectedItems.getTableIterator();
    AffectedItem item;
    IRow row = null;
    IItem agileItem = null;
    Metadata m = null;

    while (i.hasNext()) {
      row = (IRow) i.next();
      agileItem = (IItem) row.getReferent();
      m = unmarshall(agileItem);
      item = new AffectedItem(m);
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


  /**
   * Materialize origin.
   *
   * @param item the item
   *
   * @return the agile origin
   *
   * @throws Exception the exception
   */
  public AgileOrigin materializeOrigin(IItem item) throws Exception {
    AgileOrigin o = new AgileOrigin(repository.getDomainName(), repository.getServerName(),
        repository.getName(), item.getObjectId().toString(),
        item.getAgileClass().getName(), item.getName());
    o.setTimeOfCreationInMillis(System.currentTimeMillis());
    return o;
  }

  /** The config. */
  protected AgileSDKConfigurationSvc config = null;

  /** The sessions. */
  protected AgileSDKSessionSvc sessions = null;

  /** The repository. */
  protected AgileSDKRepositoryBase repository = null;

  /**
   * Gets the repository.
   *
   * @return the repository
   */
  public AgileSDKRepositoryBase getRepository() {
    return repository;
  }
}
