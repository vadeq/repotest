/**
 * 
 */
package zws.service.chrysalis;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Iterator;

import zws.application.Names;
import zws.qx.QxContext;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import zws.recorder.util.RecorderUtil;
import zws.service.file.depot.FileDepotClient;
import zws.util.RemotePackage;

/*
 DesignState - Design Compression Technology
 @author: eankutse
 @version: 1.0
 Created on Aug 21, 2007 1:15:13 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */


/**
 * The ChrysalisOutputManager Manages the translation results for the 
 * Chrysalis Service
 * @author eankutse
 *
 */
public class ChrysalisOutputManager extends ChrysalisTranslationRequestProcessor {

  public static final String CHRYSALIS_INFO_INSTR_NAME = "chrysalisinfo";

  
  /**
   * @param inputDirectory directory to store files for incoming requests
   */
  public ChrysalisOutputManager(QxContext ctx, QxXML instr) throws Exception {
    super(ctx, instr);
  }
  
  public String getInputDirectoryPath() throws Exception {
    return resource.getInputDirectoryPath();
  }
  
  public String getOutputDirectoryPath() throws Exception {
    return resource.getOutputDirectoryPath();
  }
  
  public String getProperty(String propertyName) {
    return resource.getProperty(propertyName);
  }

  /**
   * Upload the translation results associated with 'id' in dataInstructions
   * to the Document Repository Service
   * 
   * @param dataInstruction contains, among other things, the string id 
   * associated with the translation result. This id is the same as the 
   * id associated with the corresponding input request 
   */
  public QxXML process() throws ChrysalisTranslationResultsFileNotFoundException, Exception
  {//TODO: ?Should be in separate thread?    
    {} //System.out.println("Enter ChrysalisOutputManager.process()");
    String dirName = null;
    try {    
      dirName = ChrysalisRequestProcessorResource.getUniqueDirName(instr);
      String tgtDsgName = getTargetDesignName(instr);
      if (null == tgtDsgName) tgtDsgName = makeTargetDesignName(instr);
      File tgtFile = new File(resource.getOutputDirectoryPath()+"/"+dirName, tgtDsgName);
      
      //workaround for translation output files named "out"    
      if (!tgtFile.exists()) {
        doWorkAroundUntilBobsBugFix(tgtFile, dirName);
      }
      
      if (!tgtFile.exists()) {
        ChrysalisTranslationResultsFileNotFoundException e = new ChrysalisTranslationResultsFileNotFoundException("Could not find file " + tgtFile.getPath());
        resource.getStatusRegistry().updateStatusToException(dirName, e); 
        throw e;
      }
      String fileDepotClientTempDir = zws.application.Properties.get(Names.TEMP_DIR)+Names.PATH_SEPARATOR+"chrysalis"+Names.PATH_SEPARATOR+"file-depot";       

      FileDepotClient client = FileDepotClient.materializeClient(resource.getFileDepotHost(), fileDepotClientTempDir);
      {} //System.out.println("ChrysalisOutputManager: file to upload - " + tgtFile.getPath());      
      RemotePackage rf = client.storeFile(tgtFile, dirName);
  
        
      String resultsZipUrlStr = rf.getUrl().toString();
      String msg = "Chrysalis: Stored output ["+tgtFile.getPath()+"]"+ " As ["+resultsZipUrlStr+"]";
      System.out.println(msg);
      RecorderUtil.logActivity(ctx, "Stored output",  msg);
      
      //update the status registry with "Done" status and the results zip url      
      resource.getStatusRegistry().updateStatusToDone(dirName, resultsZipUrlStr, tgtDsgName);        
    } catch (MalformedURLException e) {
      e.printStackTrace();
      resource.getStatusRegistry().updateStatusToException(dirName, e);      
      throw new Exception(e);      
    } catch (URISyntaxException e) {
      e.printStackTrace();
      resource.getStatusRegistry().updateStatusToException(dirName, e);        
      throw new Exception(e);
    } catch (Exception e) {
      e.printStackTrace();
      resource.getStatusRegistry().updateStatusToException(dirName, e);        
      throw new Exception(e);
    }
   
    {} //System.out.println("Exit ChrysalisOutputManager.process()");
    return new QxXML("<OutputProcessingDone/>");
  }
  
  private void doWorkAroundUntilBobsBugFix(File targetFile, String uniqueid) {
    String dirName = targetFile.getParent();
    File outFile = new File(dirName, uniqueid+".stp");
    if (outFile.exists()) outFile.renameTo(targetFile);
    outFile = new File(dirName, uniqueid+".igs");
    if (outFile.exists()) outFile.renameTo(targetFile); 
    outFile = new File(dirName, uniqueid+".pdf");
    if (outFile.exists()) outFile.renameTo(targetFile);
  }
   

  private String getTargetDesignName(QxXML instr) throws Exception  {
    java.util.Vector instrElems = (java.util.Vector)instr.toQxProgram().getSubInstructions();
    String tgtDesignName = null;
    
    QxInstruction srcInstr = null;
    Iterator i = instrElems.iterator();
    while (i.hasNext()) {
      srcInstr = (QxInstruction) i.next();
      if (!("target".equalsIgnoreCase(srcInstr.getName())))
        continue;
      
      tgtDesignName = srcInstr.get("target.design.name");
      break;  
    }  
    if (null == tgtDesignName || "".equals(tgtDesignName)) {
      tgtDesignName = null;
    }
      
    return tgtDesignName;
  }
   
  private String makeTargetDesignName(QxXML instr) throws Exception  {
    String tgtDesignName =  null;
    
    java.util.Vector instrElems = (java.util.Vector)instr.toQxProgram().getSubInstructions();
    String srcDesignName = null;
    
    QxInstruction srcInstr = null;
    Iterator i = instrElems.iterator();
    while (i.hasNext()) {
      srcInstr = (QxInstruction) i.next();
      if (!("source".equalsIgnoreCase(srcInstr.getName())))
        continue;
      
      srcDesignName = srcInstr.get("source.design.name");
      break;  
    }  
    String srcDesignNameBase = srcDesignName.substring(0, srcDesignName.indexOf("."));
    tgtDesignName = srcDesignNameBase+getTargetDesignExt(instr);   
    
    return tgtDesignName;
  }
 
  
  private String getTargetDesignExt(QxXML instr) {
    java.util.Vector instrElems = (java.util.Vector)instr.toQxProgram().getSubInstructions();
    String tgtDesignFormat = null;
    
    QxInstruction srcInstr = null;
    Iterator i = instrElems.iterator();
    while (i.hasNext()) {
      srcInstr = (QxInstruction) i.next();
      if (!("target".equalsIgnoreCase(srcInstr.getName())))
        continue;
      
      tgtDesignFormat = srcInstr.get("target.design.format");
      break;  
    }  
    String tgtDesignXt=null;
    if (tgtDesignFormat.equalsIgnoreCase("IGES")) tgtDesignXt = ".igs"; 
    if (tgtDesignFormat.equalsIgnoreCase("PDF")) tgtDesignXt = ".pdf"; 
    if (tgtDesignFormat.equalsIgnoreCase("STEP")) tgtDesignXt = ".stp";    
   
    return tgtDesignXt;
  }
  
  
}
