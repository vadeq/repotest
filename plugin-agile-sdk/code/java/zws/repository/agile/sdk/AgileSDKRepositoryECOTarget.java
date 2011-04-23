/*
 * DesignState - Design Compression Technology @author: Arbind Thakur @version:
 * 1.0 Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 */
package zws.repository.agile.sdk;


import zws.data.Metadata;
import zws.data.eco.AffectedItem;
import zws.data.eco.ECO;

import zws.qx.QxContext;
import zws.repository.agile.sdk.svc.AgileSDKSvcBase;
import zws.repository.target.RepositoryECOTarget;
import zws.security.Authentication;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;


/**
 * The Class Ilink3RepositoryMetadataSource.
 */
public class AgileSDKRepositoryECOTarget extends AgileSDKRepositoryBase
    implements RepositoryECOTarget {
  
	protected AgileSDKRepositoryECOTarget(QxContext parent, AgileSDKSvcBase sdkSvc) {
    configureParentContext(parent);
    configureSDKBase(sdkSvc);
  }

  public void removeAttachment(QxContext runningCtx, String ecoNumber, String attachmentName, String itemNumber, Authentication id) throws Exception {
	  if(!getAffectedItemSvc().isECOExists(ecoNumber, id)) {
		  System.out.println("ECO "+ ecoNumber + " does not exists in the system.");
		  return;
	  }	  
      getAffectedItemSvc().removeFileFromECOItem(runningCtx, itemNumber, ecoNumber, attachmentName, id);
  }
  
  public void removeAttachments(QxContext runningCtx, String ecoNumber, String itemNumber, Authentication id) throws Exception {
	  if(!getAffectedItemSvc().isECOExists(ecoNumber, id)) {
		  System.out.println("ECO "+ ecoNumber + " does not exists in the system");
		  return;
	  }
      getAffectedItemSvc().removeAllFilesFromECOItem(runningCtx, itemNumber, ecoNumber, id);
  }
  
  public void undoPriorRedlines(QxContext runningCtx, String ecoNumber, String itemNumber, Authentication id) throws Exception {
      AffectedItem aItem = new AffectedItem();
      aItem.setName(itemNumber);
      aItem.setItemNumber(itemNumber);
      getAffectedItemSvc().undoRedlines(ecoNumber, aItem, id);
  }
  
  
  public void addAffectedItem(QxContext runningCtx, String ecoNumber, Metadata item, Authentication id) throws Exception {
      AffectedItem aItem = new AffectedItem(item);
      aItem.setName(item.getName());
      aItem.setItemNumber(item.getName());
      getEcoSvc().addChange(ecoNumber, aItem, id);
  }

  public void addAffectedItem(QxContext runningCtx, String ecoNumber, String itemNumber, Authentication id) throws Exception {
    //Metadata data = getItemSvc().find(itemNumber, id);
    //if(null != data) {
      AffectedItem aItem = new AffectedItem();
      aItem.setName(itemNumber);
      aItem.setItemNumber(itemNumber);
      getEcoSvc().addChange(ecoNumber, aItem, id);
      //getAffectedItemSvc().update(data, id);
  }

  public void addAffectedItems(QxContext runningCtx, String ecoNumber, Collection itemNumbers, Authentication id) throws Exception {
    String itemNumber = null;
    Iterator itr = itemNumbers.iterator();
    while(itr.hasNext()) {
      itemNumber = (String) itr.next();
      addAffectedItem(runningCtx, ecoNumber, itemNumber, id);
    }
  }

  public void update(QxContext runningCtx, String ecoNumber, Metadata data, Authentication id) throws Exception {
    getAffectedItemSvc().update(data, ecoNumber, id);
  }

  public void setECOAttribute(QxContext runningCtx, String ecoNumber, String attribute, String value, Authentication id) throws Exception {
    getEcoSvc().setECOAttribute(ecoNumber, attribute, value, id);
  }
  
  public void setECOOriginator(QxContext runningCtx, String ecoNumber,String value, Authentication id) throws Exception {
    getEcoSvc().setECOOriginator(ecoNumber, value, id);
  }
  
  public void setECOChangeAnalyst(QxContext runningCtx, String ecoNumber,  String value, Authentication id) throws Exception {
    getEcoSvc().setECOChangeAnalyst(ecoNumber, value, id);
  }
  

  public void setBOMAttribute(QxContext runningCtx, String ecoNumber, String affectedItemNumber, String subcomponentNumber, String attribute, String value, Authentication id) throws Exception {
    getAffectedItemSvc().setBOMAttribute(ecoNumber, affectedItemNumber, subcomponentNumber, attribute, value, id);
  }

  

  public String createECO(QxContext runningCtx, String agileClass, Authentication id) throws Exception {
    ECO eco = getEcoSvc().createECO(agileClass, null, null, null, id);
    return eco.getNumber();
  }


  public String createECO(QxContext runningCtx, Map ecoAttributes, Authentication id) throws Exception {
    ECO eco = getEcoSvc().createECO(ecoAttributes, id);
    return eco.getNumber();
  }

  public void redlineAdd(QxContext runningCtx, String ecoNumber, String itemNumber, String subComponentNumber,
                         int qty, QxContext bomAttributes,  Authentication id) throws Exception {
    getAffectedItemSvc().redlineNewSubComponent(ecoNumber, itemNumber,subComponentNumber, qty, null, id);
    if (null==bomAttributes) return;
    Iterator i = bomAttributes.properties.keySet().iterator();
    String attribute, value;
    while(i.hasNext()) {
      attribute = (String)i.next();
      value = bomAttributes.get(attribute);
      if (null!= value && "quantity".equalsIgnoreCase(attribute)) {
        Integer intQty = Integer.valueOf(value);
        getAffectedItemSvc().redlineUpdateQuantity(ecoNumber, itemNumber, subComponentNumber, intQty.intValue(), id);
      } else {
        getAffectedItemSvc().setBOMAttribute(ecoNumber, itemNumber, subComponentNumber, attribute, value, id);
      }
    }
  }

  public void redlineDelete(QxContext runningCtx, String ecoNumber, String itemNumber, String subComponentNumber, Authentication id) throws Exception {
    getAffectedItemSvc().redlineDeleteSubComponent(ecoNumber, itemNumber, subComponentNumber, id);
  }

  public void redlineModify(QxContext runningCtx, String ecoNumber, String itemNumber, String subComponentNumber,
                            QxContext bomAttributes, Authentication id) throws Exception {
    String qty = bomAttributes.get(AgileSDKConstants.QUANTITY);
    if(null == qty ) qty ="0";
    getAffectedItemSvc().redlineUpdateQuantity(ecoNumber, itemNumber, subComponentNumber,Integer.valueOf(qty).intValue(), id);
    getAffectedItemSvc().setBOMAttributes(ecoNumber, itemNumber, subComponentNumber, bomAttributes.properties, id);
  }

  public void redlineQty(QxContext runningCtx, String ecoNumber, String itemNumber, String subComponentNumber, int qty, Authentication id) throws Exception {
    getAffectedItemSvc().redlineUpdateQuantity(ecoNumber, itemNumber, subComponentNumber, qty, id);
  }

public void deleteEmptyECO(QxContext runningCtx, String ecoNumber, boolean truncateFlag, Authentication id) throws Exception {
	getEcoSvc().deleteChange(ecoNumber, truncateFlag, id);
}
 }
