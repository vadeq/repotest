/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Nov 30, 2007 5:22:54 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.service.chrysalis;

import zws.qx.QxContext;
import zws.qx.xml.QxXML;
//import zws.util.{}//Logwriter;

public class ChrysalisStatusRequestProcessor {

  public ChrysalisStatusRequestProcessor() {}

  public static QxXML requestStatus(QxContext ctx, QxXML instr) throws Exception {  
    
    String uniqueId = instr.toQxProgram().get("uniqueId");
    QxXML statusResult = null;
    {}//Logwriter.printOnConsole("ChrysalisSvc.requesStatus info for: " + uniqueId);    
    ChrysalisRequestStatusInfo statusInfo = ChrysalisSvc.getStatusRegistry().getStatusInfo(uniqueId);
    
    if (null != statusInfo)
      {}//Logwriter.printOnConsole("Recieved status info: " + statusInfo.getStatus() + " " + statusInfo.getResultsUrl());

    statusResult = buildStatusRequestResult(statusInfo);
    return statusResult; 
  }
  
  public static QxXML removeStatus(QxContext ctx, QxXML instr) throws Exception {  
    
    String uniqueId = instr.toQxProgram().get("uniqueId");
    QxXML statusResult = null;
    {}//Logwriter.printOnConsole("ChrysalisSvc.removeStatus info for: " + uniqueId);    
    ChrysalisRequestStatusInfo statusInfo = ChrysalisSvc.getStatusRegistry().removeStatusInfo(uniqueId);
    
    if (null != statusInfo)
      {}//Logwriter.printOnConsole("Remove returned status info: " + statusInfo.getStatus() + " " + statusInfo.getResultsUrl());

    statusResult = buildStatusRemoveResult(statusInfo);
    return statusResult; 
  } 
  
  
  private static QxXML buildStatusRemoveResult(ChrysalisRequestStatusInfo statusInfo)
  {
    String status="";
    String url="";
    String resultxml = null;
    
    if (null==statusInfo)
    {
      resultxml = "<status statusStr='"+"StatusNotAvailable"+"'" + " urlStr='"+url+"' />";
    }
    else if (null==statusInfo.getResultsUrl())
    {
      status = statusInfo.getStatus();
      resultxml = "<status statusStr='"+status+"'" + " urlStr='"+url+"' />";
    }
    else {
      status = statusInfo.getStatus();
      url = statusInfo.getResultsUrl();
      resultxml =  "<status statusStr='"+status+"'" + " urlStr='"+url+"' />";     
    }
    return new QxXML(resultxml);
  }
  
  
  private static QxXML buildStatusRequestResult(ChrysalisRequestStatusInfo statusInfo)
  {
    String status="";
    String url="";
    String binFile = "";
    String resultxml = null;
    
    if (null==statusInfo)
    {
      resultxml = "<status statusStr='"+"StatusNotYetAvailable"+"'" + " urlStr='"+url+"' />";
    }
    else if (null==statusInfo.getResultsUrl())
    {
      status = statusInfo.getStatus();
      resultxml = "<status statusStr='"+status+"'" + " urlStr='"+url+"' />";
    }
    else {
      status = statusInfo.getStatus();
      url = statusInfo.getResultsUrl();
      binFile = statusInfo.getBinaryFileName();
      resultxml =  "<status statusStr='"+status+"'" + " binaryFileName='"+binFile+"'" + " urlStr='"+url+"'" + " />";     
    }
    return new QxXML(resultxml);
  }
    
}
