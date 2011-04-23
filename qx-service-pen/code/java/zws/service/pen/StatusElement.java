package zws.service.pen;

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

/**
 * The Class SourceDataElement.
 *
 * @author ptoleti
 */
public class StatusElement {
  public StatusElement(PENDataElement e) { penDataElement = e; }

  public QxContext getItemStatusContext() { return itemStatus; }

  public String getItemStatus(String statusName) {
    String name = statusName;
    if(null==name) return "";
    name = name.trim().toLowerCase();
    return itemStatus.get(name);
  }

  public void setItemStatus(String statusName, String statusValue) {
    String name = statusName;
    String value = statusValue;
    if(null==name) return;
    if (null==value) value="";
    name = name.trim().toLowerCase();
    value = value.trim();    
    itemStatus.set(name, value);
  }

  public QxContext getSubcomponentStatusContext(String subcomponent) {
    QxContext ctx = (QxContext) subcomponentStatusContexts.get(subcomponent);
    return ctx;
  }
  public String getSubcomponentStatus(String subcomponent, String statusName) {
    QxContext ctx = (QxContext) subcomponentStatusContexts.get(subcomponent);
    if (null==ctx) return null;
    String statusValue = ctx.get(statusName);
    return statusValue;
  }
  public void setSubcomponentStatus(String subcomponent, String statusName, String statusValue) {
    QxContext ctx = (QxContext) subcomponentStatusContexts.get(subcomponent);
    if (null==ctx) {
      ctx = new QxContext();
      subcomponentStatusContexts.put(subcomponent, ctx);
    }
    ctx.set(statusName, statusValue);
  }

  public PENData getPENData() { return penDataElement.getPENData(); }
  public PENDataElement getPenDataElement() { return penDataElement; }


  private QxContext itemStatus = new QxContext(); //map(status-name, status-value) - general
  private HashMap subcomponentStatusContexts= new HashMap();

  private PENDataElement penDataElement = null;
}
