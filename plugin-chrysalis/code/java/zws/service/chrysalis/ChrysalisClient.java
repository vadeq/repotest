package zws.service.chrysalis;

import zws.recorder.util.RecorderUtil;
import zws.service.chrysalis.ChrysalisRequestStatusInfo;
import zws.service.file.depot.FileDepotClient;
import zws.util.RemotePackage;
import zws.application.Names;
import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.qx.program.QxInstruction;
import zws.qx.service.QxServiceFinder;
import zws.qx.service.ServiceRecord;
import zws.qx.xml.QxXML;

import java.net.URL;


/* DesignState - Design Compression Technology
 @author: eankutse
 @version: 1.0
 Created on Aug 8, 2007 10:32:28 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

/*
 * @author eankutse
 *
 */
public class ChrysalisClient {

  private static final long WAIT_FOR_RESULTS_SLEEP_TIME = 15*1000; //15 seconds
  
  
  public static ChrysalisClient materializeClient() throws Exception {
    ChrysalisClient c = new ChrysalisClient();
    return c;
  }
  
  
  
  /**
   * The Constructor.
   * @param hostName hostname
   */
  private ChrysalisClient() throws Exception { init(); }  

  
  private void init() throws Exception {
    QxServiceFinder finder = QxServiceFinder.materializeFinder("chrysalis");
    serviceContext = finder.prepareQxWebClientContext("zws.qx.service", "ChrysalisQxService");

    ServiceRecord r = finder.getServiceRecord();
    qxWebClient = (QxWebClient)r.materializeServiceClient();    
  }
 
  
/**
 * 
 * @param srcDesignFileName the name of the design artifact that is to be translated (e.g. machine.asm). 
 * The translator needs this
 * @param srcDesignFormat the format of the design artifact that need to be translated
 * @param targetDesignFormat the format to which the design artifact is to be translated
 * @param srcDesignZipFileFullPathName the zip file containing the design artifact named by srcDesignFileName
 * @param targetDesignZipFileFullPathName the name of the zip file that will contain the design
 * artifacts resulting from the translation
 * @return
 */
 public final RemotePackage translate(QxContext ctx, String srcDesignFileName, String srcGenericDesignFileName, RemotePackage src,
                                   String srcDesignFormat, String targetDesignFormat, String targetDesignName) throws Exception
 { 
   RemotePackage xlation = null;
   QxXML dataInstruction = null;
   String recStatus = null;
   QxContext newCtx = null;
   try {
     //build the translate instruction 
     dataInstruction = buildTranslationInstruction(srcDesignFileName, srcGenericDesignFileName, srcDesignFormat, 
                                                   targetDesignFormat, src, targetDesignName);
 
     {}//Logwriter.printOnConsole("instruction from test: " + dataInstruction);
     String msg = "source design file " + srcDesignFileName;
     StringBuffer notes = new StringBuffer("Source Design File: ").append(srcDesignFileName);
                          notes.append(" Source Generic Design FileName: ").append(srcGenericDesignFileName);
                          notes.append(" Source Design Format: ").append(srcDesignFormat);
                          notes.append(" Target Design Format: ").append(targetDesignFormat);
                          notes.append(" Remote Package: ").append(src.getName());
                          notes.append(" Target Design Name: ").append(targetDesignName);
     RecorderUtil.logActivity(ctx, "Binary Translation",  msg, notes.toString());
    
     serviceContext.configureParent(ctx);
     newCtx = RecorderUtil.startSubProcess(serviceContext, "Binary Translation", srcDesignFileName);
     RecorderUtil.setStatus(newCtx, Names.STATUS_STARTED);
     qxWebClient.executeQx(newCtx, dataInstruction);
     
     //TODO: what we get here is the URL of the output zip file of the translation
     //TODO: on the file depot. Return a RemoteFile encapsulating this url
     ChrysalisRequestStatusInfo result = waitForResults(src.getLocation(), qxWebClient, ctx);
     String status = result.getStatus();   
     RecorderUtil.logActivity(newCtx, "Binary Translation Status",  status);
     if (status.contains(ChrysalisRequestStatusInfo.EXCEPTION_STATUS)) throw new Exception(status);
     xlation = FileDepotClient.materializeRemotePackage(new URL(result.getResultsUrl()), result.getBinaryFileName());
     //xlation = new RemoteFileImpl(new URL(result.getResultsUrl()));
     //xlation.setBinaryFileName(result.getBinaryFileName());
     recStatus = Names.STATUS_COMPLETE;
     } catch (Exception e) {
       e.printStackTrace();
       RecorderUtil.logActivity(newCtx, Names.STATUS_ERROR, e.getMessage());
       recStatus = Names.STATUS_ERROR;
       throw e;
       //Logwriter.printOnConsole("Error in ChrysalisClient.publish");
     } finally {
     //Remove the status info object this request from the Registry
     removeStatusInfo(src.getLocation(), qxWebClient, ctx);
     RecorderUtil.endRecProcess(newCtx, recStatus);
     }
   
   return xlation;
 }
 
 
 private ChrysalisRequestStatusInfo waitForResults(String id, QxWebClient webClient, QxContext ctx) throws Exception
 {
   {}//Logwriter.printOnConsole("ChrysalisClient.waitForResults...");
   QxXML statusInstr = buildStatusInstruction(id); 
   ChrysalisRequestStatusInfo statusInfo = getRequestStatus(webClient, ctx, statusInstr);   
   while (!statusInfo.statusIsDone() && !statusInfo.statusIsException()) {
     //{}//Logwriter.printOnConsole("Current status of request: " + statusInfo.getStatus());
     try {
         Thread.sleep(WAIT_FOR_RESULTS_SLEEP_TIME);      
     } catch (InterruptedException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
       throw e;
     }
     statusInfo = getRequestStatus(webClient, ctx, statusInstr);      
   }//while
   {}//Logwriter.printOnConsole("ChrysalisClient.waitForResults DONE");
   return statusInfo;
 }  

 private void removeStatusInfo(String id, QxWebClient webClient, QxContext ctx)
 {
   {}//Logwriter.printOnConsole("ChrysalisClient.waitForResults...");
   
   QxXML statusInstr = new QxXML("<removeStatus uniqueId ='"+id+"' />");
   ChrysalisRequestStatusInfo statusInfo = removeRequestStatus(webClient, ctx, statusInstr);
   {} //System.out.println("Removed Status Info: "+ statusInfo.toString());
 }  
 
 
 private QxXML buildStatusInstruction(String uniqueId)
 {
   return new QxXML("<requestStatus uniqueId ='"+uniqueId+"' />");
 }
 
 
 private ChrysalisRequestStatusInfo removeRequestStatus(QxWebClient webClient, QxContext ctx, QxXML statusInstr) {
   ChrysalisRequestStatusInfo statusInfo = new ChrysalisRequestStatusInfo();
   QxXML result = null;
   serviceContext.configureParent(ctx);
   result = webClient.executeQx(serviceContext, statusInstr);
   QxInstruction r = result.toQxProgram();
   String status = r.get("statusStr");  
   String zipUrl = r.get("urlStr");
   statusInfo.setStatus(status);
   statusInfo.setUrlStr(zipUrl);
   
   return statusInfo;
 }
 
 private ChrysalisRequestStatusInfo getRequestStatus(QxWebClient webClient, QxContext ctx, QxXML statusInstr) {
   ChrysalisRequestStatusInfo statusInfo = new ChrysalisRequestStatusInfo();
   QxXML result = null;
   serviceContext.configureParent(ctx);
   result = webClient.executeQx(serviceContext, statusInstr);
   QxInstruction r = result.toQxProgram();
  
   String status = r.get("statusStr");  
   String zipUrl = r.get("urlStr");
   String binaryFileName = r.get("binaryFileName");
   
   {}//Logwriter.printOnConsole("ChrysalisClient.getRequestStatus received: " + "statusStr="+status + " " + "url="+zipUrl);
   
   statusInfo.setStatus(status);
   statusInfo.setUrlStr(zipUrl);
   statusInfo.setBinaryFileName(binaryFileName);

   return statusInfo;
 }
 

 
/**
 * Construct the entire instruction
 * 
 * @param srcDesignFileName
 * @param srcDesignFormat
 * @param targetDesignFormat
 * @param srcDesignDir
 * @param ctx
 * @return
 */ 
 protected QxXML buildTranslationInstruction(String srcDesignFileName, String srcGenericDesignFileName, String srcDesignFormat, 
                                             String targetDesignFormat, RemotePackage src, String targetDesignName) {
   
   String docUniqueId = src.getLocation();
   String docUrl = src.getUrl().toString();
   
   //create the elements of the instruction xml
   QxInstruction translate = new QxInstruction("translate");  
   QxInstruction source= new QxInstruction("source");
   QxInstruction target = new QxInstruction("target");
   QxInstruction chrysalisinfo = new QxInstruction("chrysalisinfo");   

   //set attributes on the elements - the names used here would 
   //come from the one Enum type
   source.set("source.design.name", srcDesignFileName);
   source.set("source.design.format", srcDesignFormat); 
   if (null != srcGenericDesignFileName)  source.set("source.generic.design.name", srcGenericDesignFileName);
   target.set("target.design.name", targetDesignName);   
   target.set("target.design.format", targetDesignFormat); 
   chrysalisinfo.set("docUniqueId", docUniqueId);
   chrysalisinfo.set("docURL", docUrl);   
   
   //now assemble the entire xml instruction
   translate.add(source);
   translate.add(target);   
   translate.add(chrysalisinfo);   
   
   return new QxXML(translate.toString());
 }
 

private QxContext serviceContext= null;
 private QxWebClient qxWebClient = null;  
}
