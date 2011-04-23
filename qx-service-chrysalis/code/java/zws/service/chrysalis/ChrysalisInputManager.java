/**
 * 
 */
package zws.service.chrysalis;

import zws.recorder.util.RecorderUtil;
import zws.service.file.depot.FileDepotClient;
import zws.service.chrysalis.ChrysalisConstants;
import zws.application.Names;
import zws.qx.QxContext;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
//import zws.util.{}//Logwriter;
import zws.util.RemotePackage;

import java.io.File;
import java.net.URL;
import java.util.Iterator;


/*
 DesignState - Design Compression Technology
 @author: eankutse
 @version: 1.0
 Created on Aug 21, 2007 1:14:25 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

//TODO: Further refactor of ChrysalisInputManager, ChrysalisDesignConverter and ChrysalisOutputManager 
//into ChrysalisTranslationRequestProcessor to initializes more the common stuff.

/**
 * The ChrysalisOutputManager Manages the input for translation requests for
 * the Chrysalis Service
 * @author eankutse
 *
 */
public class ChrysalisInputManager extends ChrysalisTranslationRequestProcessor {

  private ChrysalisInstructionGenerator iniFileGenerator;
  
  
  /**
   * @param inputDirectory directory to store files for incoming requests
   */
  public ChrysalisInputManager(QxContext ctx, QxXML instr) throws Exception {
    super(ctx, instr);
    this.iniFileGenerator = new ChrysalisInstructionGenerator(this);
  }
  
  

  public QxXML process() throws Exception {
    {}//Logwriter.printOnConsole("Enter ChrysalisInputManager.process()");    

    String uniqueDirName = ChrysalisRequestProcessorResource.getUniqueDirName(instr);     
    resource.getStatusRegistry().updateStatusToTranslationInProgress(uniqueDirName);
      
    String inputDirPathName = this.getInputDirectoryPath()+File.separator+uniqueDirName;  
    String fileURL = ChrysalisRequestProcessorResource.getFileURL(instr); 
    String srcDsgName = getSourceDesignName(instr);    
    File inputDir = new File(inputDirPathName);
    if (!inputDir.exists()) inputDir.mkdirs();
    String msg = "Chrysalis: Retrieving input ["+fileURL+"] To ["+inputDirPathName+"]";   
    System.out.println();
    System.out.println(msg);
    
    RecorderUtil.logActivity(ctx, "Retrieving input",  msg);    
    {}//Logwriter.printOnConsole("Downloading input file from File Depot to: " + inputDir.getPath());
    try {  
      String fileDepotClientTempDir = zws.application.Properties.get(Names.TEMP_DIR)+Names.PATH_SEPARATOR+"chrysalis"+Names.PATH_SEPARATOR+"file-depot";      

      //download file from File Depot
      RemotePackage rFile = FileDepotClient.materializeRemotePackage(new URL(fileURL), srcDsgName);
      FileDepotClient client = FileDepotClient.materializeClient(resource.getFileDepotHost(), fileDepotClientTempDir);
      File localfile = client.retrieve(rFile, inputDir);
   
      {}//Logwriter.printOnConsole("Downloaded Input file from File Depot to: " + localfile.getPath());  
      
      File iniFile = new File(inputDirPathName+File.separator+"xlator" + ChrysalisConstants.INI_FILE_EXT);
      //generate the INI file contents
      this.iniFileGenerator.generateINIfile(instr, iniFile);
      
      
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      resource.getStatusRegistry().updateStatusToException(uniqueDirName, e);      
      throw new Exception(e);
    }
    {}//Logwriter.printOnConsole("Exit ChrysalisInputManager.prepareInput");     
    return new QxXML("<InputProcessingDone/>");
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
 
  private String  getSourceDesignName(QxXML instr) throws Exception {
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
    
    if (null == srcDesignName || "".equals(srcDesignName))
      throw new Exception("Could not find \"source.design.name\" in translation request!");  
   
    return srcDesignName;
  }

}
