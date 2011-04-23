
/* DesignState - Design Compression Technology
 * @author: ptoleti, arbind
 * @version: 1.0
 * Created on May 15, 2007 11:53:44 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.service.pen;

import zws.util.Messages;
import java.util.Collection;

public class PENDataElement {

  public String getItemName() { return itemName; }
  public void setItemName(String strItemName) { this.itemName = strItemName; }

  public SourceDataElement getSourceDataElement() { return sourceDataElement; }
  public TargetDataElement getTargetDataElement() { return targetDataElement; }
  public TxDataElement getTxDataElement() { return txDataElement; }
  public DocumentElement getDocumentElement() { return documentElement; }
  public StatusElement getStatusElement() { return statusElement; }
  public ECOElement getECOElement() { return ecoElement; }
  public RedlineElement getRedlineElement() { return redlineElement; }

  
  public void redlineAdd(String targetSubComponetName) throws Exception {
    redlineElement.redlineAdd(targetSubComponetName);
  }
  
  public void redlineModify(String targetSubComponetName) throws Exception {
    redlineElement.redlineModify(targetSubComponetName);
  }
  
  public void redlineDelete(String targetSubComponetName) throws Exception {
    redlineElement.redlineDelete(targetSubComponetName);
  }    

  
  public int getReferenceCount() { return referenceCount; }
  public void increaseReferenceCount() { referenceCount += 1; }

  public void decreaseReferenceCount() throws Exception{
    if(referenceCount >0) {
      referenceCount -= 1;
    } else {
      throw new Exception("Object count is 0 and can't be decreased.");
    }
  }

  /*
  public String lookupSrcBinding(String targetName) {
    String srcName = null;
    if (srcTargetBindings.containsValue(targetName)) {
      Iterator itr = srcTargetBindings.keySet().iterator();
      while (itr.hasNext()) {
        srcName = (String) itr.next();
        if (targetName.equalsIgnoreCase((String) srcTargetBindings.get(srcName))) {
          return srcName;
        }
      }
    }
    return null;
  }

  public String lookupTargetBinding(String srcName) {
    String targetName = (String) srcTargetBindings.get(srcName);
    return targetName;
  }

  public void defineSrcTargetBinding(String srcName, String targetName) {
    if (!srcTargetBindings.containsKey(srcName)) {
      srcTargetBindings.put(srcName, targetName);
    }
  }

  public Map getSrcTargetBindings() {
    return srcTargetBindings;
  }*/
  
  public void recordMessage(String message) { messages.recordMessage(message); }
  public void recordWarningMessage(String message) { messages.recordWarningMessage(message); }
  public void recordErrorMessage(String message) { messages.recordErrorMessage(message); }
  public void recordDebugMessage(String message) { messages.recordDebugMessage(message); }
  public Collection getMessages() { return messages.getMessages(); }
  public Collection getWarnings() { return messages.getWarningMessages(); }
  public Collection getErrorMessages() { return messages.getErrorMessages(); }
  public Collection getDebugMessages() { return messages.getDebugMessages(); }

  public void setPENData(PENData table) { penData=table; }
  public PENData getPENData() { return penData; }

  private String itemName = null;
  private SourceDataElement sourceDataElement = new SourceDataElement(this);
  private TxDataElement txDataElement = new TxDataElement(this);
  private DocumentElement documentElement = new DocumentElement(this);
  private TargetDataElement targetDataElement = new TargetDataElement(this);
  private StatusElement statusElement = new StatusElement(this);
  private ECOElement ecoElement = new ECOElement(this);
  private RedlineElement redlineElement = new RedlineElement(this);
  //private HashMap srcTargetBindings = new HashMap();
  
  private int referenceCount = 0;
  private Messages messages = new Messages();
  private PENData penData = null;

}
