/**
 * 
 */
package zws.service.chrysalis;

import zws.qx.QxContext;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import zws.recorder.util.RecorderUtil;
import zws.util.ExecShell;
import java.io.File;
import java.util.Vector;

/*
 DesignState - Design Compression Technology
 @author: eankutse
 @version: 1.0
 Created on Aug 22, 2007 1:59:37 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

/**
 * @author eankutse
 *
 */
public class ChrysalisDesignConverter extends ChrysalisTranslationRequestProcessor {

  private static final String SOURCE_SECTION_NAME = "source"; 
  private static final String SOURCE_DESIGN_FORMAT_ITEM_NAME = "source.design.format"; 
 
  public ChrysalisDesignConverter(QxContext ctx, QxXML instr) throws Exception {
    super(ctx, instr);
  }
  
  
  public QxXML process() throws Exception {
    System.out.println("Chrysalis: Invoking Pro/E Translator...");
    {} //System.out.println("Enter ChrysalisDesignConverter.convertDesign");
    RecorderUtil.logActivity(ctx, "Invoking Pro/E Translator",  "Proe26.exe");
    String translatorToInvoke = null;
    String uniqueId = ChrysalisRequestProcessorResource.getUniqueDirName(instr);
    translatorToInvoke = determineTranslatorToInvoke(instr);
    
    if (null==translatorToInvoke) {
      resource.getStatusRegistry().updateStatusToException(uniqueId, new Exception("Translator to invoke was not specified"));
      throw new Exception("Translator to invoke was not specified");
    }
    if (!supportedTranslator(translatorToInvoke)) { 
      resource.getStatusRegistry().updateStatusToException(uniqueId, new Exception("Translator to invoke " + translatorToInvoke + " not supported"));    
      throw new Exception("Translator to invoke " + translatorToInvoke + " not supported");
    }

    File xlatorOutputDir = new File(resource.getOutputDirectoryPath()+File.separator+uniqueId);
    if (!xlatorOutputDir.exists()) xlatorOutputDir.mkdirs();   
    
    if (translatorToInvoke.equalsIgnoreCase("ProE"))
      invokeProETranslator(instr);
    else if (translatorToInvoke.equalsIgnoreCase("UGNX"))
      invokeUGNXTranslator(instr);
    
    {} //System.out.println("Exit ChrysalisDesignConverter.convertDesign"); 
    
    return new QxXML("<DesignConversionDONE/>");  
  }

  
  protected String determineTranslatorToInvoke(QxXML instrs) throws Exception
  {
    QxInstruction instr = null;
    String translatorName="unknown";
    Vector instrElems = (Vector)instrs.toQxProgram().getSubInstructions();
  
    for (int i=0; i<instrElems.size(); i++) {
      instr = (QxInstruction) instrElems.get(i); 
      if (SOURCE_SECTION_NAME.equalsIgnoreCase(instr.getName()))
      {
        translatorName = instr.get(SOURCE_DESIGN_FORMAT_ITEM_NAME);
        break;
      }
    }
    
    if (null==translatorName || "unknown".equalsIgnoreCase(translatorName))
      throw new Exception("ChrysalisDesignConverter: did not find a translator!");

    return translatorName;
  }
  
  protected  boolean supportedTranslator(String translator)
  {
    return true;
  }
  
  /**
   * Create the shell [functor object] and the command to invoke the ProE
   * Translator. Then pass the command to the shell to execute 
   * @throws Exception
   */
  protected  void invokeProETranslator(QxXML instr) throws Exception
  {
    int status = -1; //initialize to other than normal
    
    {} //System.out.println("Invoking ProE Translator...."); 
    
    String uniqueDirName = ChrysalisRequestProcessorResource.getUniqueDirName(instr);
    String paramsFile = resource.getInputDirectoryPath()+File.separator+uniqueDirName+File.separator+"xlator"+ChrysalisConstants.INI_FILE_EXT;
    File xlatorOutputDir = new File(resource.getOutputDirectoryPath()+File.separator+uniqueDirName);
    if (!xlatorOutputDir.exists()) xlatorOutputDir.mkdirs();
    
    String translatorExecutable = this.resource.getProperty("proe2pdfstp-xlator-command");
   
    ExecShell shell = new ExecShell();    
    shell.addCommandLineArgument(paramsFile, false); //paramsFile is the fullpathname of the parameters .ini file   
    shell.setExecutable(translatorExecutable);    
    shell.setWorkingDirectory(xlatorOutputDir);   
    shell.execute();
    status = shell.waitFor();
    {} //System.out.println("Status = " + status);
  }
  
  
  protected  void invokeUGNXTranslator(QxXML instrs) throws Exception
  {
    {} //System.out.println("UGNX Translator NOT YET AVAILABLE!!!");
    throw new Exception("UGNX Translator NOT YET AVAILABLE!!!");
  }
    

}
