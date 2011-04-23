package zws.service.pen;


import zws.data.eco.ECO;
import zws.qx.QxContext;
/*
 DesignState - Design Compression Technology
 @author: ptoleti
 @version: 1.0
 Created on May 25, 2007 12:09:48 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class SourceDataElement.
 *
 * @author ptoleti
 */
public class ECOElement {
  public ECOElement(PENDataElement e) { penDataElement = e; }

  public String getTargetECO() { return targetECO; }
  public void setTargetECO(String ecoNumber) { targetECO = ecoNumber; }

  public QxContext getTargetECOAfftectedItemContext() { return targetECOAfftectedItemContext; }

  public String getTargetECOAfftectedItemStatus(String statusName) {
    return targetECOAfftectedItemContext.get(statusName);
  }

  public void settargetECOAfftectedItemStatus(String statusName, String statusValue) {
    targetECOAfftectedItemContext.set(statusName, statusValue);
  }

  public QxContext getTargetECORedlineContext(String subcomponent) {
    QxContext ctx = (QxContext) targetECORedlineContext.get(subcomponent);
    return ctx;
  }
  public String getTargetECORedlineStatus(String subcomponent, String statusName) {
    QxContext ctx = (QxContext) targetECORedlineContext.get(subcomponent);
    if (null==ctx) return null;
    String statusValue = ctx.get(statusName);
    return statusValue;
  }
  public void setTargetECORedlineStatus(String subcomponent, String statusName, String statusValue) {
    QxContext ctx = (QxContext) targetECORedlineContext.get(subcomponent);
    if (null==ctx) {
      ctx = new QxContext();
      targetECORedlineContext.put(subcomponent, ctx);
    }
    ctx.set(statusName, statusValue);
  }

  /*
  public QxContext getPendingECOContext(String pendingECO) {
    QxContext ctx = (QxContext) pendingECOContexts.get(pendingECO);
    return ctx;
  }

  public String getPendingECOStatus(String pendingECO, String statusName) {
    QxContext ctx = (QxContext) pendingECOContexts.get(pendingECO);
    if (null==ctx) return null;
    String statusValue = ctx.get(statusName);
    return statusValue;
  }
  public void setPendingECOStatus(String pendingECO, String statusName, String statusValue) {
    QxContext ctx = (QxContext) pendingECOContexts.get(pendingECO);
    if (null==ctx) {
      ctx = new QxContext();
      pendingECOContexts.put(pendingECO, ctx);
    }
    ctx.set(statusName, statusValue);
  }
*/
  public void addPendingECO(String ecoNumber) {
    pendingECOList.add(ecoNumber);
  }

  public Collection getPendingECOList () {
    return pendingECOList;
  }
  
  public ECO lookupFirstPendingECO() {
    if (pendingECOList.isEmpty()) return null;
    String ecoNumber = (String)pendingECOList.iterator().next();
    ECO eco = getPENData().getECO(ecoNumber);
    return eco;
  }

  public PENData getPENData() { return penDataElement.getPENData(); }
  public PENDataElement getPenDataElement() { return penDataElement; }

  private String targetECO = null; //target eco number
  private QxContext targetECOAfftectedItemContext = new QxContext(); // ctx.set(status-name, status-value)  - for affecte item on target-eco-#
  private Map targetECORedlineContext = new HashMap(); //map(subcomponent-name, context) - for redline bom attributes on target-eco-# for each subcomponent

  //private HashMap pendingECOContexts = new HashMap(); // map(pending-eco-#, context) - keep a context for each pending eco
  Collection pendingECOList = new ArrayList();
  private PENDataElement penDataElement = null;
}