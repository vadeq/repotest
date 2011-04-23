/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Nov 30, 2007 3:52:31 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.service.chrysalis;

import zws.application.Names;
import zws.application.Properties;
import zws.exception.zwsException;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;

import java.io.File;
import java.util.Vector;

public class ChrysalisRequestProcessorResource {


  
  private static final String CHRYSALIS_INFO_SECTION_NAME = "chrysalisinfo";
  private static final String CHRYSALIS_INFO_DOC_UNIQUE_ID_NAME = "docUniqueId";
  private static final String CHRYSALIS_INFO_DOC_URL = "docURL";   
  private static final String CHRYSALIS_INPUT_DIR_PROP_NAME = "chrysalis-input-dir";
  private static final String CHRYSALIS_OUTPUT_DIR_PROP_NAME = "chrysalis-output-dir";
  private static final String FILE_DEPOT_HOST_PROP_NAME = "file-depot-hostname";
  

  private File ChrysalisInputDir = null;
  private File ChrysalisOutputDir = null;
 
  public ChrysalisRequestProcessorResource() throws zwsException {

    try {      
      ChrysalisInputDir = new File (Properties.get(CHRYSALIS_INPUT_DIR_PROP_NAME));
      ChrysalisOutputDir = new File (Properties.get(CHRYSALIS_OUTPUT_DIR_PROP_NAME));
  
      if (!ChrysalisInputDir.exists()) ChrysalisInputDir.mkdirs();      
      if (!ChrysalisOutputDir.exists()) ChrysalisOutputDir.mkdirs();
          
    } catch (Exception e) {
      e.printStackTrace();
      throw new zwsException(e.getMessage());
    }
  }
 
 
  
  public String getInputDirectoryPath() throws Exception {
    return ChrysalisInputDir.getPath();
  }
 
  public String getOutputDirectoryPath() {
    return ChrysalisOutputDir.getPath();
  }  
  
  
  public String getProperty(String propertyName) {
    return Properties.get(propertyName);
  }
  

  public ChrysalisRequestStatusRegistry getStatusRegistry() {
    return ChrysalisSvc.getStatusRegistry();
  }
  
  public static String getChrysalisConfigsDirPath() {
    
    String zwsConfigDir = Properties.get(Names.CONFIG_DIR);
    String organization = Properties.get(Names.ORGANIZATION);
    String project = Properties.get(Names.PROJECT);
    String SLASH = Names.PATH_SEPARATOR;

    return zwsConfigDir+SLASH+organization+SLASH+project+SLASH+"chrysalis";
  }
  
  public static String getUniqueDirName(QxXML instrs) {
    String uniqueDirName="unknown";
    Vector instrElems = (Vector)instrs.toQxProgram().getSubInstructions();

    QxInstruction instr = null;
    
    for (int i=0; i<instrElems.size(); i++) {
      instr = (QxInstruction) instrElems.get(i); 
      if (CHRYSALIS_INFO_SECTION_NAME.equalsIgnoreCase(instr.getName()))
      {
        uniqueDirName = instr.get(CHRYSALIS_INFO_DOC_UNIQUE_ID_NAME);
        break;
      }
    }
    
    {}//Logwriter.printOnConsole("ChrysalisInstructionGenerator.getUniqueId: " + CHRYSALIS_INFO_SECTION_NAME + " = " + uniqueDirName);            
    return uniqueDirName;
  }
  
  public static String getFileURL(QxXML instrs)
  {
    String docUrl="unknown";
    Vector instrElems = (Vector)instrs.toQxProgram().getSubInstructions();

    QxInstruction instr = null;
    
    for (int i=0; i<instrElems.size(); i++) {
      instr = (QxInstruction) instrElems.get(i); 
      if (CHRYSALIS_INFO_SECTION_NAME.equalsIgnoreCase(instr.getName()))
      {
        docUrl = instr.get(CHRYSALIS_INFO_DOC_URL);
        break;
      }
    }            
    return docUrl;    
  }
  
  public String getFileDepotHost() {
    String fileDepotHostName = Properties.get(FILE_DEPOT_HOST_PROP_NAME);
    return fileDepotHostName;    
  }  
  
}
