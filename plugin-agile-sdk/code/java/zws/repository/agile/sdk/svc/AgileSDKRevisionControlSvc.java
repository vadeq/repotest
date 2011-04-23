/**
 *
 */
package zws.repository.agile.sdk.svc;

/*
DesignState - Design Compression Technology
@author: ptoleti
@version: 1.0
Created on Jul 12, 2007 1:01:09 PM
Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/
import zws.data.eco.AffectedItem;
import zws.data.eco.ECO;
import zws.lifecycle.LifeCycleOrder;
import zws.repository.agile.sdk.AgileSDKRepositoryBase;
import zws.security.Authentication;

import java.util.Collection;
import java.util.Iterator;


import com.agile.api.ChangeConstants;
import com.agile.api.IChange;
import com.agile.api.IItem;
import com.agile.api.ITable;
import com.agile.api.ItemConstants;


/**
 * The Class AgileSDKRevisionControlSvc.
 *
 * @author ptoleti
 */
public class AgileSDKRevisionControlSvc extends AgileSDKSvcBase {

  /**
   * The Constructor.
   *
   * @param repository the repository
   * @param configUtil the config util
   * @param sessionUtil the session util
   */
  protected AgileSDKRevisionControlSvc(AgileSDKSessionSvc sessionUtil,
                                       AgileSDKConfigurationSvc configUtil,
                                       AgileSDKRepositoryBase repository) {
    super(sessionUtil, configUtil, repository);
    itemSvc = materializeItemSvc();
    affectedItemSvc = materializeAffectedItemSvc();
    ecoSvc = materializeECOSvc();
  }

  /**
   * Order initial release.
   *
   * @param id the id
   * @param ecoNumber the eco number
   * @param changedItems the changed items
   *
   * @return the agile ECO
   *
   * @throws Exception the exception
   */
  public ECO orderInitialRelease(String ecoNumber,
      Collection changedItems, Authentication id) throws Exception {
    IChange eco = ecoSvc.findChange(ecoNumber, id);
    Iterator i = changedItems.iterator();
    AffectedItem affectedItem;
    IItem item;
    ITable affectedItems = eco.getTable(ChangeConstants.TABLE_AFFECTEDITEMS);
    while (i.hasNext()) {
      affectedItem = (AffectedItem) i.next();
      // set property for initial release description and life cycle phases
      if (null == affectedItem.getDescription()
          || "".equals(affectedItem.getDescription().trim())) {
        affectedItem.setDescription("Pilot Production Release");
      }
      if (null == affectedItem.getNewLifeCyclePhase()
          || "".equals(affectedItem.getNewLifeCyclePhase().trim())) {
        affectedItem.setNewLifeCyclePhase("Pre-Release");
      }
      ecoSvc.addChange(eco, affectedItem, id);
      item = affectedItemSvc.findItem(affectedItem.getItemNumber(), id);
      orderInitialReleaseOfBOM(eco, affectedItems, item);
    }
    return ecoSvc.findECO(ecoNumber, id);
  }

  /**
   * Order initial release of BOM.
   *
   * @param affectedItems the affected items
   * @param item the item
   * @param eco the eco
   *
   * @throws Exception the exception
   */
  private void orderInitialReleaseOfBOM(IChange eco, ITable affectedItems,
      IItem item) throws Exception {
    ITable bomTable;
    IItem dep;
    bomTable = item.getTable(ItemConstants.TABLE_BOM);
    Iterator i = bomTable.getReferentIterator();
    AffectedItem init = new AffectedItem();
    // set property for initial release description and life cycle phases
    init.setDescription("Pilot Production Release");
    init.setNewLifeCyclePhase("Pre-Release");
    while (i.hasNext()) {
      dep = (IItem) i.next();
      if (!itemSvc.isReleased(dep)
          && !affectedItemSvc.listedAsAffectedItem(dep.getName(), affectedItems)) {
        ecoSvc.addChange(eco, affectedItems, dep, init);
        // row = affectedItems.createRow(dep);
        orderInitialReleaseOfBOM(eco, affectedItems, dep);
      }
    }
  }

  /**
   * Order release.
   *
   * @param id the id
   * @param lifeCyclePhase the life cycle phase
   * @param ecoNumber the eco number
   * @param changedItems the changed items
   * @param lco the lco
   *
   * @throws Exception the exception
   */
  public void orderRelease(String ecoNumber, Collection changedItems,
      String lifeCyclePhase, LifeCycleOrder lco, Authentication id)
      throws Exception {
    IChange eco = ecoSvc.findChange(ecoNumber, id);
    Iterator i = changedItems.iterator();
    AffectedItem affectedItem;
    IItem item;
    ITable affectedItems = eco.getTable(ChangeConstants.TABLE_AFFECTEDITEMS);
    while (i.hasNext()) {
      affectedItem = (AffectedItem) i.next();
      // set property for initial release description and life cycle phases
      if (null == affectedItem.getDescription()
          || "".equals(affectedItem.getDescription().trim())) {
        affectedItem.setDescription("Promote to " + lifeCyclePhase);
      }
      affectedItem.setNewLifeCyclePhase(lifeCyclePhase);
      ecoSvc.addChange(eco, affectedItem, id);
      item = itemSvc.findItem(affectedItem.getItemNumber(), id);
      orderReleaseOfBOM(eco, affectedItems, item, lifeCyclePhase, lco);
    }
    // return findECO(ecoNumber, id);
  }

  /**
   * Order release of BOM.
   *
   * @param lifeCyclePhase the life cycle phase
   * @param affectedItems the affected items
   * @param item the item
   * @param lco the lco
   * @param eco the eco
   *
   * @throws Exception the exception
   */
  public void orderReleaseOfBOM(IChange eco, ITable affectedItems, IItem item,
      String lifeCyclePhase, LifeCycleOrder lco) throws Exception {
    ITable bomTable;
    IItem dep;
    bomTable = item.getTable(ItemConstants.TABLE_BOM);
    Iterator i = bomTable.getReferentIterator();
    AffectedItem change = new AffectedItem();
    // set property for initial release description and life cycle phases
    if (null == change.getDescription()
        || "".equals(change.getDescription().trim())) {
      change.setDescription("Promote to  " + lifeCyclePhase);
    }
    change.setNewLifeCyclePhase(lifeCyclePhase);
    while (i.hasNext()) {
      dep = (IItem) i.next();
      if (affectedItemSvc.listedAsAffectedItem(dep.getName(), affectedItems)) {
        continue;
      }
      if (itemSvc.isReleased(dep)) {
        if (lco.comesAfter(lifeCyclePhase, itemSvc.getLifeCyclePhase(dep))) {
          continue;
        }
      }
      ecoSvc.addChange(eco, affectedItems, dep, change);
      // row = affectedItems.createRow(dep);
      orderReleaseOfBOM(eco, affectedItems, dep, lifeCyclePhase, lco);
    }
  }

  /**
   * Order rev update.
   *
   * @param id the id
   * @param ecoNumber the eco number
   * @param changedItems the changed items
   *
   * @throws Exception the exception
   */
  public void orderRevUpdate(String ecoNumber, Collection changedItems,
      Authentication id) throws Exception {
    IChange eco = ecoSvc.findChange(ecoNumber, id);
    Iterator i = changedItems.iterator();
    AffectedItem affectedItem;
    while (i.hasNext()) {
      affectedItem = (AffectedItem) i.next();
      ecoSvc.addChange(eco, affectedItem, id);
    }
  }

  /** The item svc. */
  private AgileSDKItemSvc itemSvc = null;

  /** The affected item svc. */
  private AgileSDKAffectedItemSvc affectedItemSvc = null;

  /** The affected item svc. */
  private AgileSDKECOSvc ecoSvc = null;

}
