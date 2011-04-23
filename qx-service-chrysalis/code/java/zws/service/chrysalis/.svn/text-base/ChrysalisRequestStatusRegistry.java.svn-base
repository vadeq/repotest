/**
 * 
 */
package zws.service.chrysalis;

//import zws.util.{}//Logwriter;

import java.util.HashMap;


/*

 import java.io.Serializable;
 DesignState - Design Compression Technology
 @author: eankutse
 @version: 1.0
 Created on Sep 5, 2007 3:41:19 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

/**
 * @author eankutse
 *
 */
public class ChrysalisRequestStatusRegistry {

  private HashMap registry;

  public ChrysalisRequestStatusRegistry() {
    //thread-safe access to registry
    //this.registry = (HashMap)Collections.synchronizedMap(new HashMap());
    this.registry = new HashMap();    
  }

  synchronized public void addStatusInfo(String uniqueId) {
    {}//Logwriter.printOnConsole("ChrysalisRequestStatusRegistry.addStatusInfo: " + uniqueId);    
    ChrysalisRequestStatusInfo statusInfo = new ChrysalisRequestStatusInfo();
    this.registry.put(uniqueId, statusInfo);    
  }
  
  synchronized public void updateStatusToDone(String uniqueId) {
     
    ChrysalisRequestStatusInfo statusInfo = (ChrysalisRequestStatusInfo) this.registry.get(uniqueId);
    if (null != statusInfo) {       
      statusInfo.setDoneStatus();     
    }    
  }
 
  synchronized public void updateStatusToDone(String uniqueId, String resultsZipUrlStr, String binaryFileName) {
    ChrysalisRequestStatusInfo statusInfo = (ChrysalisRequestStatusInfo) this.registry.get(uniqueId);
    if (null != statusInfo) {      
      statusInfo.setDoneStatus();
      statusInfo.setResultsUrl(resultsZipUrlStr);
      statusInfo.setBinaryFileName(binaryFileName);
    }     
  }  
  
  synchronized public void updateStatusToOnInputQueue(String uniqueId){
    ChrysalisRequestStatusInfo statusInfo = (ChrysalisRequestStatusInfo) this.registry.get(uniqueId);
    if (null != statusInfo) {       
      statusInfo.setOnInputQueueStatus();
    }     
  }
  
  
  synchronized public void updateStatusToTranslationInProgress(String uniqueId){
    ChrysalisRequestStatusInfo statusInfo = (ChrysalisRequestStatusInfo) this.registry.get(uniqueId);
    if (null != statusInfo) {
     statusInfo.setTranslationInProgressStatus();
    }    
  }

  synchronized public void updateStatusToException(String uniqueId, Exception e){
    ChrysalisRequestStatusInfo statusInfo = (ChrysalisRequestStatusInfo) this.registry.get(uniqueId);
    if (null != statusInfo) {
     statusInfo.setExceptionStatus(e);
    }    
  }
  
  synchronized public ChrysalisRequestStatusInfo removeStatusInfo(String uniqueId) {
    {}//Logwriter.printOnConsole("ChrysalisRequestStatusRegistry.removeStatusInfo: " + uniqueId);    
    return (ChrysalisRequestStatusInfo)this.registry.remove(uniqueId);
  }
 
  synchronized public ChrysalisRequestStatusInfo getStatusInfo(String uniqueId) {
    ChrysalisRequestStatusInfo status = (ChrysalisRequestStatusInfo)this.registry.get(uniqueId);  
    if (null==status) {
      status = new ChrysalisRequestStatusInfo();
      status.setStatus("Status Registry does not have Status Information for " + uniqueId);
    }
      
    return status;
  }
  
  private void printInfo() {
    {}//Logwriter.printOnConsole("*******  NUMBER OF ITEMS IN Registry="+ this.registry.size());
    
    java.util.Iterator iter = this.registry.keySet().iterator();
    {}//Logwriter.printOnConsole("*** Keys currently in Registry: ");
    while (iter.hasNext()) {}
    {}//Logwriter.printOnConsole("==> " + ((String)iter.next()));
  }
  
}
