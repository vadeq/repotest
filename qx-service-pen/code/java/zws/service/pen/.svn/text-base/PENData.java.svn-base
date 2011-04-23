package zws.service.pen;

/*
 * DesignState - Design Compression Technology @author: ptoleti @version: 1.0
 * Created on Apr 24, 2007 4:32:37 PM Copywrite (c) 2007 Zero Wait-State Inc.
 * All rights reserved
 */

import zws.application.Names;
import zws.context.Context;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.MetadataSubComponentBase;
import zws.data.eco.ECO;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.util.Messages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

/**
 * The Class PENData.
 *
 * @author ptoleti
 */
public class PENData {

  PENData(Collection origins, Context ctx) {
    originsToPublish = origins;
    initializePulishingContext(ctx);
  }

  public PENData(Collection c) {
    originsToPublish = c;
    createPulishingContext();
  }

  public Collection getOriginsToPublish() {
    return originsToPublish;
  }

  public Collection getOriginsToRepublish() {
    return originsToRepublish;
  }  
  
  public Collection reportWhereUsedInSource(String name) {
    Collection parents = new Vector();
    Metadata data = null;
    String dataName=null;
    Iterator i = this.materializeIterator();//referenceTable.keySet().iterator();
    while (i.hasNext()) {
      data = lookupSrcMetaData(i.next().toString());
      dataName = data.getName();
      if(name.equalsIgnoreCase(dataName)) continue;
      if(data.hasSubComponent(name)) parents.add(dataName);
    }
    return parents;
  }

  public Collection reportWhereUsedInXfer(String name) {
    Collection parents = new Vector();
    TxDataElement data = null;
    String dataName=null;
    Iterator i = this.materializeIterator();//referenceTable.keySet().iterator();
    while (i.hasNext()) {
      dataName = (String)i.next();
      data = lookupPENDataElement(dataName).getTxDataElement();
      if(name.equalsIgnoreCase(dataName)) continue;
      if (data.containsSubcomponent(name)) parents.add(dataName);
    }
    return parents;
  }

  public Collection reportWhereUsedAsDocumentation(String name) {
    Collection parents = new Vector();
    DocumentElement data = null;
    String dataName=null;
    Iterator i = this.materializeIterator();//referenceTable.keySet().iterator();
    while (i.hasNext()) {
      dataName = (String)i.next();
      data = lookupPENDataElement(dataName).getDocumentElement();
      if(name.equalsIgnoreCase(dataName)) continue;
      if (data.containsDocument(name)) parents.add(dataName);
    }
    return parents;
  }

  public PENDataElement lookupPENDataElement(String name) {
    return (PENDataElement) referenceTable.get(name);
  }

  public PENDataElement lookupPENDataElementByTargetName(String targetName) {
    return (PENDataElement) targetReferenceTable.get(targetName);
  }
  public void setTargetReference(PENDataElement penElement) {
    targetReferenceTable.put(penElement.getTargetDataElement().getTargetData().getName(),penElement);
  }

  /*
   * public void recordErrorMessage() { } public void recordWarningMessage() { }
   * public void recordStatusMessage() { }
   */
  public void setCancelStatus(boolean status) {
    lookupStatusCtx().set("CANCEL_STATUS", String.valueOf(status));
  }

  public void setNotification(boolean value) {
    lookupStatusCtx().set("NOTIFY", String.valueOf(value));
  }
  
  public boolean doNotify() {
    String status = "true";
    if (lookupStatusCtx().contains("NOTIFY")) {
      status = lookupStatusCtx().get("NOTIFY");
    }
    return new Boolean(status).booleanValue();
  } 
 
  /*
   * public void unsetCancelStatus() { statusCtx.set("CANCEL_STATUS", "false"); }
   */

  public boolean isCancelled() {
    String status = "false";
    if (lookupStatusCtx().contains("CANCEL_STATUS")) {
      status = lookupStatusCtx().get("CANCEL_STATUS");
    }
    return new Boolean(status).booleanValue();
  }

  public void setRepublishing() {
    lookupStatusCtx().set("REPUBLISH_STATUS", "true");
  }

  public void unsetRepublishing() {
    lookupStatusCtx().set("REPUBLISH_STATUS", "false");
  }

  public boolean isMarkedForRepublishing() {
    String status = "false";
    if (lookupStatusCtx().contains("REPUBLISH_STATUS")) {
      status = lookupStatusCtx().get("REPUBLISH_STATUS");
    }
    return new Boolean(status).booleanValue();
  }

  /*
   * public void alert() { //construct subject and message and send alert
   * Alert.notify(subject, message) }
   */
  public void setStatus(String statusName, String statusValue) {
    String name = statusName;
    String value = statusValue;
    if(null==name) return;
    if (null==value) value="";
    name = name.trim().toLowerCase();
    value = value.trim();
    lookupStatusCtx().set(name, value);
  }

  public String getStatus(String statusName) {
    String name = statusName;
    if(null==name) return "";
    name = name.trim().toLowerCase();
    return lookupStatusCtx().get(name);
  }

  public QxContext getStatusContext() { return lookupStatusCtx(); }

  public Metadata lookupSrcMetaData(String name) {
    PENDataElement e = (PENDataElement) referenceTable.get(name);
    if (null == e) return null;
    Metadata m = e.getSourceDataElement().getSourceData();
    return m;
  }

  public Metadata lookupTxMetaData(String name) {
    PENDataElement e = (PENDataElement) referenceTable.get(name);
    if (null == e) return null;
    Metadata m = e.getTxDataElement().getTxData();
    return m;
  }

  public Metadata lookupTargetMetaData(String name) {
    PENDataElement e = (PENDataElement) referenceTable.get(name);
    if (null == e) return null;
    Metadata m = e.getTargetDataElement().getTargetData();
    return m;
  }

  public ECOElement lookupECOElement(String name) {
    PENDataElement penDataElemt = lookupPENDataElement(name);
    return penDataElemt.getECOElement();
  }

  public ECO lookupTargetECO(String name) {
    ECOElement ecoElement = this.lookupECOElement(name);
    String tatgetECOName = ecoElement.getTargetECO();
    ECO eco = getECO(tatgetECOName);
    return eco;
  }

  private String getElementMessages(String type) {
    Iterator itr = materializeIterator();
    StringBuffer finalMessage = new StringBuffer();
    Collection c = null;
    String message = null;
    while (itr.hasNext()) {
      String item = (String) itr.next();
      PENDataElement e = (PENDataElement) referenceTable.get(item);
      if (type.equalsIgnoreCase(Messages.ERROR)) {
        c = e.getErrorMessages();
      } else if (type.equalsIgnoreCase(Messages.WARNING)) {
        c = e.getWarnings();
      }
      if (type.equalsIgnoreCase(Messages.MESSAGE)) {
        c = e.getMessages();
      }
      if (null == c) continue;
      Iterator msgItr = c.iterator();
      while (msgItr.hasNext()) {
        message = (String) msgItr.next();
        finalMessage.append(Names.NEW_LINE).append(message);
      }
    }
    return finalMessage.toString();
  }

  public String getGlobalMessages(String type) {
    Iterator iterator;

    Collection collection = null;

    if (type.equals( Messages.MESSAGE ))
      collection = getMessages();
    else if (type.equals( Messages.ERROR ))
      collection = getErrorMessages();
    else if (type.equals( Messages.WARNING ))
      collection = getWarnings();
    else if (type.equals( Messages.DEBUG ))
      collection = getDebugMessages();

    StringBuffer buffer = new StringBuffer("");

    if (collection != null) {
      iterator = collection.iterator();
      while (iterator.hasNext())
         buffer.append(Names.NEW_LINE).append(iterator.next());
    }
    return buffer.toString();
  }
  
  public HashMap getGlobalMessageMap() {
    HashMap messageMap = new HashMap();
    messageMap.put(Messages.MESSAGE,getMessages());
    messageMap.put(Messages.ERROR,getErrorMessages());
    messageMap.put(Messages.WARNING ,getWarnings());
    messageMap.put(Messages.DEBUG ,getDebugMessages());
    return messageMap;
  }
  
  public HashMap getElementMessageMap() {
    HashMap elementMessageMap = new HashMap();
    Collection errors = new ArrayList();
    Collection debugMsgs = new ArrayList();
    Collection warnings = new ArrayList();
    Collection messages = new ArrayList();
    PENDataElement e = null;
    String item = null;
    Iterator itr = materializeIterator();
    while (itr.hasNext()) {
      item = (String) itr.next();
      e = (PENDataElement) referenceTable.get(item);
      if (null != e.getErrorMessages()) getMessageColelction(errors, e.getErrorMessages());
      if (null != e.getDebugMessages()) getMessageColelction(debugMsgs,e.getDebugMessages());
      if(null != e.getWarnings())getMessageColelction(warnings,e.getWarnings());
      if(null != e.getMessages())getMessageColelction(messages, e.getMessages());
    }
    elementMessageMap.put(Messages.ERROR,errors);
    elementMessageMap.put(Messages.WARNING,warnings);
    elementMessageMap.put(Messages.DEBUG,debugMsgs);
    elementMessageMap.put(Messages.MESSAGE,messages);    
    return elementMessageMap;
  }
  
  private void getMessageColelction(Collection list, Collection messages) {
    if (null == messages) return;
    String message = null;
    Iterator msgItr = messages.iterator();
    while (msgItr.hasNext()) {
      message = (String) msgItr.next();
      list.add(message);
    }
  }

  public String getElementErrorMessages() {
    return getElementMessages(Messages.ERROR);
  }

  public String getElementWarnings() {
    return getElementMessages(Messages.WARNING);
  }

  public String getElementMessages() {
    return getElementMessages(Messages.MESSAGE);
  }

  public void recordMessage(String message) { messages.recordMessage(message); }
  public void recordWarningMessage(String message) { messages.recordWarningMessage(message); }
  public void recordErrorMessage(String message) { messages.recordErrorMessage(message); }
  public void recordDebugMessage(String message) { messages.recordDebugMessage(message); }
  public Collection getMessages() { return messages.getMessages(); }
  public Collection getWarnings() { return messages.getWarningMessages(); }
  public Collection getErrorMessages() { return messages.getErrorMessages(); }
  public Collection getDebugMessages() { return messages.getDebugMessages(); }

  // public HashMap getReferenceMap() { return referenceTable; }
  public boolean containsReference(String name) {
    return referenceTable.containsKey(name);
  }

  public boolean containsTargetReference(String targetName) {
    return targetReferenceTable.containsKey(targetName);
  }

  public Iterator materializeIterator() {
    //return referenceTable.keySet().iterator();
    //return a copy of the keys to workaround ConcurrentModificationException
    return new Vector(referenceTable.keySet()).iterator();
  }

  public void add(PENDataElement e) {
    add(e, false);
  }

  public void add(PENDataElement e, boolean overwrite) {
    if (!overwrite && containsReference(e.getItemName())) return;
    if (referenceTable.containsKey(e.getItemName())) referenceTable.remove(e.getItemName());
    e.setPENData(this);
    referenceTable.put(e.getItemName(), e);
  }

  public void addTarget(PENDataElement e) {
    Metadata m = e.getTargetDataElement().getTargetData();
    if (null==m) return;
    if (containsTargetReference(m.getName())) return;
    targetReferenceTable.put(m.getName(), e);
  }

  public void remove(String name) throws Exception {
    if (!this.containsReference(name)) return;
    // remove from ref table.
    referenceTable.remove(name);
    // remove from subcomponent list
    String item = null;
    PENDataElement e = null;
    TxDataElement txDataElement = null;
    DocumentElement docElement = null;
    Iterator itr = materializeIterator();
    while (itr.hasNext()) {
      item = (String) itr.next();
      e = (PENDataElement) referenceTable.get(item);
      txDataElement = e.getTxDataElement();
      docElement = e.getDocumentElement();
      txDataElement.removeSubcomponent(item);
      docElement.removeDocument(item);
    }
  }

  public Map getECOReferenceTableCopy() {
    HashMap copy = new HashMap();
    copy.putAll(lookupECOMap());
    return copy;
  }

  public boolean containsECO(String key) {
    return lookupECOMap().containsKey(key);
  }

  public ECO getECO(String key) {
    return (ECO) lookupECOMap().get(key);
  }

  public void addECO(String key, ECO eco) {
    if (lookupECOMap().containsKey(key)) return;
    lookupECOMap().put(key, eco);
  }

  public HashMap getReferenceTableCopy() {
    HashMap copy = new HashMap();
    copy.putAll(referenceTable);
    return copy;
  }

  public QxContext lookupStatusCtx() {
    Context ctx = lookupPublishingContext();

    QxContext q = (QxContext)ctx.get("status-context");
    if (null==q) {
      q = new QxContext();
      ctx.set("status-context", q);
    }
    return q;
  }

  public Map lookupECOMap() {
    Context ctx = lookupPublishingContext();
    Map m = (Map)ctx.get("eco-map");
    if (null==m) {
      m = new HashMap();
      ctx.set("eco-map", m);
    }
    return m;
  }

  public Context lookupPublishingContext() {
    Iterator i = getOriginsToPublish().iterator();
    Origin o = (Origin)i.next();
    Context ctx = (Context)publishContexts.get(o.getUniqueID());
    return ctx;
  }

  public void createPulishingContext() {
    Context ctx = null;
    Iterator i = getOriginsToPublish().iterator();
    Origin o;
    while (null==ctx && i.hasNext()) {
      o = (Origin)i.next();
      ctx = (Context) publishContexts.get(o.getUniqueID());
    }
    if (null==ctx) ctx = new Context();
    initializePulishingContext(ctx);
  }


  private void initializePulishingContext(Context ctx) {
    Iterator i = getOriginsToPublish().iterator();
    Origin o;
    while (i.hasNext()) {
      o = (Origin)i.next();
      publishContexts.put(o.getUniqueID(), ctx);
    }
  }

  public void bindPulishingContext(Origin o) {
    Context ctx = lookupPublishingContext();
    publishContexts.put(o.getUniqueID(), ctx);
  }

  public void destroyPulishingContext() {
    Iterator i = getOriginsToPublish().iterator();
    Origin o;
    while (i.hasNext()) {
      o = (Origin)i.next();
      if (publishContexts.containsKey(o.getUniqueID()))
        publishContexts.remove(o.getUniqueID());
    }
  }

  public Metadata retrieveTxStructure(String srcParent) throws Exception {
    Metadata srcData = lookupTxMetaData(srcParent);
    MetadataBase txData = new MetadataBase(srcData);
    txData.setName(srcData.getName());
    loadTxSubStructure(srcParent, txData);
    return txData;
  }

  private void loadTxSubStructure(String srcParent, Metadata txParent) throws Exception {
    PENDataElement penElement = lookupPENDataElement(srcParent);
    TxDataElement txDataElement = penElement.getTxDataElement();
    Collection subs = txDataElement.getSubComponentRefNames();
    Iterator itr = subs.iterator();
    while(itr.hasNext()) {
      String sub = (String) itr.next();
      MetadataSubComponentBase subMetadata = (MetadataSubComponentBase)txDataElement.getSubComponent(sub);
      loadTxSubStructure(sub, subMetadata);
      txParent.addSubComponent(subMetadata);
    }
  }


  private static Map publishContexts = new HashMap();
  private Collection originsToPublish = null;
  private Collection originsToRepublish = new Vector();  
  private HashMap referenceTable = new HashMap();
  private HashMap targetReferenceTable = new HashMap();
  
  //QxContext statusCtx = new QxContext();

  // +++Need to add a new reference table to store ECOs (and maybe other
  // objecgts) separately
  private Messages messages = new Messages();

}
