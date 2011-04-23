/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Sep 25, 2007 5:36:36 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.util;

import zws.application.Names;
import zws.data.Metadata;
import zws.data.eco.ECO;
import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.service.pen.ECOElement;
import zws.service.pen.PENData;
import zws.service.pen.PENDataElement;
import zws.service.pen.RedlineElement;
import zws.service.pen.SourceDataElement;
import zws.service.pen.StatusElement;
import zws.service.pen.TargetDataElement;
import zws.service.pen.TxDataElement;
import zws.util.Messages;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ShowPENData extends PENDataOpBase {
  
  PENDataElement element=null;
  String itemName = null;
  SourceDataElement sourceDataElement = null;
  TxDataElement txDataElement = null;
  TargetDataElement targetDataElement = null;
  StatusElement statusElement = null;
  ECOElement ecoElement = null;
  RedlineElement redlineElement = null;
  int referenceCount = 0;
  Messages messages = null;
  PENData penData = null;
  
  public void execute() {
    penData = getPenData();
    String x="";
    if (showElementNames) x+=showElementNames();
    if (showGlobalStatus) x+=showGlobalStatus(); 
    if (showECONames) x+=showECONames();
    if (showECOs) x+=showECOs();
    if (showSource) x+=showSource();
    if (showTarget) x+=showTarget();
    if (showXfer) x+=showXfer();
    if (showXferSubcomponents) x+=showXferSubcomponents();
    if (showDocumentSubcomponents) x+=showDocumentSubcomponents();
    if (showTargetECO) x+=showTargetECO();
    if (showPendingECO) x+=showPendingECO();
    if (showStatus) x+=showStatus();
    if (showSubcomponentStatus) x+=showSubcomponentStatus();
    if (showECOStatus) x+=showECOStatus();
    if (showRedlineStatus) x+=showRedlineStatus();
    
    {} //System.out.println(NL + "========================================");
    {} //System.out.println(x);
    {} //System.out.println("========================================");
  }

  private String showElementNames() {
    String x = "";
    x  = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + NL;
    x += "Element Names" + NL;
    x += "---------------------------------------------------" + NL;
    Iterator i = penData.getReferenceTableCopy().keySet().iterator();
    while (i.hasNext()) {
      itemName = (String)i.next();
      element = penData.lookupPENDataElement(itemName);
      x += itemName + " [refs:"+element.getReferenceCount()+"]" + NL;
    }
    x += "===================================================" + NL;
    return x;
  }
  
  private String showGlobalStatus() {
    String x = "";
    x  = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + NL;
    x += "Global Status" + NL;
    x += "---------------------------------------------------" + NL;
    Iterator entries = penData.getStatusContext().properties.entrySet().iterator();
    Map.Entry entry;
    String key,value;
    while (entries.hasNext()) {
      entry = (Map.Entry)entries.next();
      key = entry.getKey().toString();
      value = entry.getValue().toString();
      x += "("+key+"="+value+")" + NL;
    }
    x += "===================================================" + NL;
    return x;
  }
  
  private String showECONames() {
    String x = "";
    x  = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + NL;
    x += "ECO Names" + NL;
    x += "---------------------------------------------------" + NL;
    Iterator i = penData.getECOReferenceTableCopy().keySet().iterator();
    String ecoName;
    while (i.hasNext()) {
      ecoName = (String)i.next();
      x += ecoName + NL;
    }
    x += "===================================================" + NL;
    return x;
  }
  
  private String showECOs() {
    String x = "";
    x  = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + NL;
    x += "ECO Details" + NL;
    x += "---------------------------------------------------" + NL;
    Iterator i = penData.getECOReferenceTableCopy().keySet().iterator();
    String ecoName;
    ECO eco;
    while (i.hasNext()) {
      ecoName = (String)i.next();
      eco = penData.getECO(ecoName);
      x += ecoName +": " + eco + NL;
    }
    x += "===================================================" + NL;
    return x;
  }
  
  private String showSource() {
    String x = "";
    x  = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + NL;
    x += "Source Data Details" + NL;
    x += "---------------------------------------------------" + NL;
    Iterator i = penData.getReferenceTableCopy().keySet().iterator();
    Metadata m;
    while (i.hasNext()) {
      itemName = (String)i.next();
      element = penData.lookupPENDataElement(itemName);
      m = element.getSourceDataElement().getSourceData();
      if (null==m) continue; 
      x += itemName  + "::SourceElement::" + m.toString()  + NL;
    }
    x += "===================================================" + NL;
    return x;
  }
  private String showTarget() {
    String x = "";
    x  = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + NL;
    x += "Target Data Details" + NL;
    x += "---------------------------------------------------" + NL;
    Iterator i = penData.getReferenceTableCopy().keySet().iterator();
    Metadata target;
    while (i.hasNext()) {
      itemName = (String)i.next();
      element = penData.lookupPENDataElement(itemName);
      target = element.getTargetDataElement().getTargetData();
      x += itemName  + "::TargetElement:: ";
      if (null==target) x+= "[]" + NL;
      else x += target.toString()  + NL;
    }
    x += "===================================================" + NL;
    return x;
  }
  private String showXfer() {
    String x = "";
    x  = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + NL;
    x += "Xfer Data Details" + NL;
    x += "---------------------------------------------------" + NL;
    Iterator i = penData.getReferenceTableCopy().keySet().iterator();
    Metadata xfer;
    while (i.hasNext()) {
      itemName = (String)i.next();
      element = penData.lookupPENDataElement(itemName);
      xfer = element.getTxDataElement().getTxData();
      x += itemName  + "::XferElement::" + xfer.toString()  + NL;
    }
    x += "===================================================" + NL;
    return x;
  }
  private String showXferSubcomponents() {
    String x = "";
    x  = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + NL;
    x += "Xfer Subcomponents" + NL;
    x += "---------------------------------------------------" + NL;
    Iterator i = penData.getReferenceTableCopy().keySet().iterator();
    Collection names;
    Iterator n;
    String name;
    while (i.hasNext()) {;
      itemName = (String)i.next();
      element = penData.lookupPENDataElement(itemName);
      x += itemName + "::Subcomponents:: ";
      names = element.getTxDataElement().getSubComponentRefNames();
      if(null==names) continue;
      n = names.iterator();
      while (n.hasNext()) {
        name = (String)n.next();
        x += name + " | ";
      }
      x += NL;
    }
    //
    x += "===================================================" + NL;
    return x;
  }

  private String showDocumentSubcomponents() {
    String x = "";
    x  = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + NL;
    x += "Document Subcomponents" + NL;
    x += "---------------------------------------------------" + NL;
    Iterator i = penData.getReferenceTableCopy().keySet().iterator();
    Collection names;
    Iterator n;
    String name;
    while (i.hasNext()) {;
      itemName = (String)i.next();
      element = penData.lookupPENDataElement(itemName);
      x += itemName + "::Subcomponents:: ";
      names = element.getDocumentElement().getDocumentRefNames();
      if(null==names) continue;
      n = names.iterator();
      while (n.hasNext()) {
        name = (String)n.next();
        x += name + " | ";
      }
      x += NL;
    }
    //
    x += "===================================================" + NL;
    return x;
  }
  
  
  private String showTargetECO() {
    String x = "";
    x  = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + NL;
    x += "Target ECOs" + NL;
    x += "---------------------------------------------------" + NL;
    Iterator i = penData.getReferenceTableCopy().keySet().iterator();
    Collection names;
    Iterator n;
    String name, targetECO;
    while (i.hasNext()) {;
      itemName = (String)i.next();
      element = penData.lookupPENDataElement(itemName);
      targetECO= element.getECOElement().getTargetECO();
      x += itemName + "::Target-ECO:: "+targetECO + NL;
    }
    x += "===================================================" + NL;
    return x;
  }
  
  private String showPendingECO() {
    String x = "";
    x  = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + NL;
    x += "Pending ECOs" + NL;
    x += "---------------------------------------------------" + NL;
    Iterator i = penData.getReferenceTableCopy().keySet().iterator();
    Collection names;
    Iterator n;
    String name;
    while (i.hasNext()) {;
      itemName = (String)i.next();
      element = penData.lookupPENDataElement(itemName);
      names = element.getECOElement().getPendingECOList();
      x += itemName + "::Pending-ECOs:: ";
      if(null==names) continue;
      n = names.iterator();
      while (n.hasNext()) {
        name = (String)n.next();
        x += name + " | ";
      }
      x += NL;
    }
    x += "===================================================" + NL;
    return x;
  }
  
  private String showStatus() {
    String x = "";
    x  = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + NL;
    x += "Element Status" + NL;
    x += "---------------------------------------------------" + NL;
    Iterator i = penData.getReferenceTableCopy().keySet().iterator();
    Collection names;
    Iterator n;
    String key,value;
    while (i.hasNext()) {;
      itemName = (String)i.next();
      element = penData.lookupPENDataElement(itemName);
      x += itemName + "::Pending-ECOs:: ";
      Iterator entries = element.getStatusElement().getItemStatusContext().properties.entrySet().iterator();
      Map.Entry entry;
      while (entries.hasNext()) {
        entry = (Map.Entry)entries.next();
        key = entry.getKey().toString();
        value = entry.getValue().toString();
        x += "("+key+"="+value+")" + NL;
      }
    }
    x += "===================================================" + NL;
    return x;
  }
  
  private String showSubcomponentStatus() {
    String x = "";
    x  = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + NL;
    x += "SubComponent Status" + NL;
    x += "---------------------------------------------------" + NL;
    {} //System.out.println("+++todo");
    x += "===================================================" + NL;
    return x;
  }
  private String showECOStatus() {
    String x = "";
    x  = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + NL;
    x += "ECO Status" + NL;
    x += "---------------------------------------------------" + NL;
    {} //System.out.println("+++todo");
    x += "===================================================" + NL;
    return x;
  }
  private String showRedlineStatus() {
    String x = "";
    String name = "";
    StringBuffer temp = new StringBuffer(""); 
    x  = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + NL;
    x += "Xfer Redline status" + NL;
    x += "---------------------------------------------------" + NL;
    Iterator i = penData.getReferenceTableCopy().keySet().iterator();
    while (i.hasNext()) {
      itemName = (String)i.next();
      HashMap redlines  = penData.lookupPENDataElement(itemName).getRedlineElement().getRedlines();
      Iterator itr = redlines.keySet().iterator();
      while(itr.hasNext()) {
        name = (String) itr.next();
        x += itemName + " [" + name + " --> " + (String)redlines.get(name)+"]" + NL;
      }
    }
    x += "===================================================" + NL;    
    return x;
  }
  
  
  public boolean isShowECONames() {
    return showECONames;
  }
  public void setShowECONames(boolean showECONames) {
    this.showECONames = showECONames;
  }
  public boolean isShowECOs() {
    return showECOs;
  }
  public void setShowECOs(boolean showECOs) {
    this.showECOs = showECOs;
  }
  public boolean isShowECOStatus() {
    return showECOStatus;
  }
  public void setShowECOStatus(boolean showECOStatus) {
    this.showECOStatus = showECOStatus;
  }
  public boolean isShowElementNames() {
    return showElementNames;
  }
  public void setShowElementNames(boolean showElementNames) {
    this.showElementNames = showElementNames;
  }
  public boolean isShowGlobalStatus() {
    return showGlobalStatus;
  }
  public void setShowGlobalStatus(boolean showGlobalStatus) {
    this.showGlobalStatus = showGlobalStatus;
  }
  public boolean isShowPendingECO() {
    return showPendingECO;
  }
  public void setShowPendingECO(boolean showPendingECO) {
    this.showPendingECO = showPendingECO;
  }
  public boolean isShowSource() {
    return showSource;
  }
  public void setShowSource(boolean showSource) {
    this.showSource = showSource;
  }
  public boolean isShowStatus() {
    return showStatus;
  }
  public void setShowStatus(boolean showStatus) {
    this.showStatus = showStatus;
  }
  public boolean isShowSubcomponentStatus() {
    return showSubcomponentStatus;
  }
  public void setShowSubcomponentStatus(boolean showSubcomponentStatus) {
    this.showSubcomponentStatus = showSubcomponentStatus;
  }
  public boolean isShowTarget() {
    return showTarget;
  }
  public void setShowTarget(boolean showTarget) {
    this.showTarget = showTarget;
  }
  public boolean isShowRedlineStatus() {
    return showRedlineStatus;
  }

  public void setShowRedlineStatus(boolean showRedlineStatus) {
    this.showRedlineStatus = showRedlineStatus;
  }

  public boolean isShowTargetECO() {
    return showTargetECO;
  }

  public void setShowTargetECO(boolean showTargetECO) {
    this.showTargetECO = showTargetECO;
  }

  public boolean isShowXfer() {
    return showXfer;
  }
  public void setShowXfer(boolean showXfer) {
    this.showXfer = showXfer;
  }

  
  boolean showElementNames=true;
  boolean showGlobalStatus =true;
  boolean showECONames =true;
  boolean showECOs=true;
  boolean showSource =true;
  boolean showTarget=true;
  boolean showXfer =true;
  boolean showXferSubcomponents =true;
  boolean showDocumentSubcomponents =true;
  boolean showTargetECO =true;
  boolean showPendingECO =true;
  boolean showStatus =true;
  boolean showSubcomponentStatus =true;
  boolean showECOStatus =true;
  boolean showRedlineStatus =true;
  
  String NL = Names.NEW_LINE;

  public boolean isShowDocumentSubcomponents() {
    return showDocumentSubcomponents;
  }

  public void setShowDocumentSubcomponents(boolean showDocumentSubcomponents) {
    this.showDocumentSubcomponents = showDocumentSubcomponents;
  }

  public boolean isShowXferSubcomponents() {
    return showXferSubcomponents;
  }

  public void setShowXferSubcomponents(boolean showXferSubcomponents) {
    this.showXferSubcomponents = showXferSubcomponents;
  }
  }
