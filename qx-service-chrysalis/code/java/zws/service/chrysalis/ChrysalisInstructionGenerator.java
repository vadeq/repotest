/**
 * 
 */
package zws.service.chrysalis;

/*
 DesignState - Design Compression Technology
 @author: eankutse
 @version: 1.0
 Created on Aug 22, 2007 1:31:37 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.application.Names;
import zws.exception.zwsException;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;


import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

/**
 * @author eankutse
 *
 */
public class ChrysalisInstructionGenerator {

  /* TODO: move these constants into ChrysalisConstants.java */
  


  private static final String SOURCE_SECTION_NAME = "source"; 
  private static final String TARGET_SECTION_NAME = "target";
  private static final int  SOURCE_SECTION_POS = 0;
  private static final int  TARGET_SECTION_POS = 1;  
  private static final String NEWLINE = "\n";
  private static final String EQUALS = "=";
  private static final String TOOL_INSTRUCTION_CONVERT_VALUE = "convert";
  private static final String TRUE = "True";
  private static final String FALSE = "False";
  private static final String PROE_TO_PDF =  "ProE-to-PDF";  
  private static final String PROE_TO_PRINT = "proe-to-print";  
  private static final String PDF_GENERATION_APPROACH = "pdf-generation-approach";
  private static final String POSTSCRIPT_APPROACH = "PRINT";
  private static final String DIRECT_APPROACH = "DIRECT";  
  private ChrysalisInputManager owner;
  
  public ChrysalisInstructionGenerator (ChrysalisInputManager owner) {
    super();    
    this.owner = owner;
  }
  
  protected  void generateINIfile(QxXML instr, File iniFile) throws Exception
  {
  /**
   * The in-coming instruction object does not have information for the [Tool] 
   * and [Log Files] sections of the instruction file - these will be known to 
   * the server-side Translation Input Module. 
   * 
   * The instruction object DOES have some of the items for the [Source] and 
   * [Target] sections. Specifically, the instruction object does NOT have 
   * information that translates into "source.design.path" and 
   * "target.design.path" entries in the instruction file. Since these are 
   * internal to the Translation Service proper, these two will also be known 
   * to the server-side Translation Input Module. 
   * 
   * The instruction object has ALL of the information (and the only information) 
   * for the [Context] section of the instruction file.
   */    

    {}//Logwriter.printOnConsole("Enter ChrysalisInstructionGenerator.generateINIfile");    

    String uniqueId = getUniqueDirName(instr);
    String inputManagerDir = owner.getInputDirectoryPath();
    String outputManagerDir = owner.getOutputDirectoryPath(); 
    
    Vector ordered = null;
    Vector instrElems = (Vector)instr.toQxProgram().getSubInstructions();
    ordered = orderInstrElements(instrElems);
    StringBuffer iniInfo = new StringBuffer(); 
    QxInstruction sectionElement;  
 
    iniInfo.append(generateToolsSection() + NEWLINE);
    iniInfo.append(NEWLINE);
    
    sectionElement = (QxInstruction)ordered.elementAt(SOURCE_SECTION_POS);
    iniInfo.append(generateSourceSection(sectionElement, inputManagerDir, uniqueId) + NEWLINE);
    iniInfo.append(NEWLINE);
    
    sectionElement = (QxInstruction)ordered.elementAt(TARGET_SECTION_POS);    
    iniInfo.append(generateTargetSection(sectionElement, outputManagerDir, uniqueId) + NEWLINE);
    iniInfo.append(NEWLINE);

    iniInfo.append(generateOptionsSection(instr) + NEWLINE);
    iniInfo.append(NEWLINE);
    
    iniInfo.append(generateLogFilesSection(instr) + NEWLINE);
    
    writeOut(iniInfo, iniFile);
    
    {}//Logwriter.printOnConsole("Exit ChrysalisInstructionGenerator.generateINIfile");    
  }
  
  
  protected  Vector orderInstrElements(Vector unordered) throws Exception
  {//order is: Tools, Source, Target, Options, Log Files
    
    Vector ordered = new Vector();
    

    QxInstruction instr = null;
    Iterator i = unordered.iterator();
    while (i.hasNext()) {
      instr = (QxInstruction) i.next();
      if (SOURCE_SECTION_NAME.equalsIgnoreCase(instr.getName()))
          ordered.insertElementAt(instr, SOURCE_SECTION_POS);
      else if (TARGET_SECTION_NAME.equalsIgnoreCase(instr.getName()))
          ordered.insertElementAt(instr, TARGET_SECTION_POS);
//      else if (THIRD_INCOMING_SECTION_NAME.equalsIgnoreCase(instr.getName()))
//          ordered.insertElementAt(instr, THIRD_INCOMING_SECTION_POS);      
    }
    
    return ordered;
  }
  
  protected  void writeOut(StringBuffer iniInfo, File iniFile)
  {
    if (null == iniFile) return;

    /*
    {}//Logwriter.printOnConsole("------------------------------");    
    {}//Logwriter.printOnConsole(iniInfo);    
    {}//Logwriter.printOnConsole("------------------------------");    
    */
    
    try {
    iniFile.createNewFile();
    
    FileOutputStream fos = null;
    fos = new FileOutputStream(iniFile);
    BufferedOutputStream bos = new BufferedOutputStream(fos, iniInfo.length());
    DataOutputStream dos = new DataOutputStream(bos);
    dos.writeBytes(iniInfo.toString());
    
    dos.close();
    } 
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  
  protected String getUniqueDirName(QxXML instrs) {
    return ChrysalisRequestProcessorResource.getUniqueDirName(instrs);
  }


  
  protected QxInstruction getSourceSection(QxXML instrs)
  {
    Vector instrElems = (Vector)instrs.toQxProgram().getSubInstructions();

    QxInstruction instr = null;
    
    for (int i=0; i<instrElems.size(); i++) {
      instr = (QxInstruction) instrElems.get(i); 
      if (SOURCE_SECTION_NAME.equalsIgnoreCase(instr.getName()))
      break;
    }            
    return instr;    
  }  
 
  protected QxInstruction getTargetSection(QxXML instrs)
  {
    Vector instrElems = (Vector)instrs.toQxProgram().getSubInstructions();

    QxInstruction instr = null;
    
    for (int i=0; i<instrElems.size(); i++) {
      instr = (QxInstruction) instrElems.get(i); 
      if (TARGET_SECTION_NAME.equalsIgnoreCase(instr.getName()))
      break;
    }            
    return instr;    
  }  
  
  protected  StringBuffer generateToolsSection() throws Exception {
    StringBuffer buf = new StringBuffer();
    buf.append("[Tool]" + NEWLINE);
  
    String toolCommand = getExecutableConfigProperty();
    String toolConfigPro = getToolConfigProperty();
    String toolInstruction = getToolInstructionConfig();
    String toolGraphicMode = toolGraphicModeProperty();
    String toolExitOnCompletion = gettoolExitOnCompletionProperty();

    buf.append("tool.command" + EQUALS + toolCommand + NEWLINE);
    buf.append("tool.config.pro" + EQUALS + toolConfigPro + NEWLINE);
    buf.append("tool.instruction" + EQUALS +  toolInstruction + NEWLINE);
    buf.append("tool.in.graphic.mode" + EQUALS +  toolGraphicMode + NEWLINE);  
    buf.append("tool.exit.on.completion" + EQUALS +  toolExitOnCompletion + NEWLINE);    
    
    return buf;
  }
  
  
  protected String getProperty(String propertyName) throws Exception {
    return owner.getProperty(propertyName);
  }
    
  //gets info from property file generated from client settings
//  protected String getTranslatorParam(String configName){
//    return this.owner.getParams().getProperty(configName);
//  }
  
  protected String getExecutableConfigProperty() throws Exception {
    String toolCommand = getProperty("proe-tool-command");
    return toolCommand;
  }
  
  protected String getToolConfigProperty() throws Exception {
    String toolConfigPro = getProperty("proe-tool-config-pro");
    return toolConfigPro;
  }
  
  

  /**
   * Tool Instruction should be coming from requester (PEN, Connector,...)
   */
  protected String getToolInstructionConfig() {
    String toolInstruction=null;
   
    if (null==toolInstruction) toolInstruction = TOOL_INSTRUCTION_CONVERT_VALUE;
    return toolInstruction;
  }
  
  protected String toolGraphicModeProperty() throws Exception {

    String toolGraphicMode = getProperty("proe2pdfstp-graphic-mode");
    if (null==toolGraphicMode) toolGraphicMode=FALSE;
    return toolGraphicMode;
  }
  
  protected String gettoolExitOnCompletionProperty() throws Exception {

    String toolExitOnCompletion = getProperty("proe2pdfstp-exit-on-completion");  
    if (null==toolExitOnCompletion) toolExitOnCompletion=TRUE;
    return toolExitOnCompletion;
  }
  
  
  protected  StringBuffer generateSourceSection(QxInstruction instr, String inputMgrDir, String uniqueDirName) {
    StringBuffer buf = new StringBuffer();
    
    String srcGenericDesignName = instr.get("source.generic.design.name");
    if (null != srcGenericDesignName) {
      srcGenericDesignName = srcGenericDesignName.trim();
      String srcDesignName = instr.get("source.design.name").trim();
      String[] strArr1 = srcGenericDesignName.split("\\."); //split around the period; escape for regexp 
      String[] strArr2 = srcDesignName.split("\\.");
      srcDesignName = strArr2[0]+"<"+strArr1[0]+">."+strArr2[1];
      
      instr.set("source.design.name", srcDesignName);
    }
    
    buf.append("[Source]" + NEWLINE);    
    buf.append(generateEntries(instr));
    
    //add what is only known to the document converter but not the reqestor
    String srcDesignPath = inputMgrDir+File.separator+uniqueDirName+File.separator;
    buf.append("source.design.path" +  EQUALS +  srcDesignPath + NEWLINE);
    
    return buf;
  }
  
  protected  StringBuffer generateTargetSection(QxInstruction instr, String outputMgrDir, String uniqueDirName) {
    StringBuffer buf = new StringBuffer();
    
    //accommodate pdf generation via postscript
    String pdf_generation_approach = getPDFGenerationApproachProperty();
    String targetDesignFormat = instr.get("target.design.format").trim(); 
    String targetDesignName = instr.get("target.design.name").trim(); 
    
    if (targetDesignFormat.equalsIgnoreCase("PDF") && pdf_generation_approach.equalsIgnoreCase(POSTSCRIPT_APPROACH)) {
      instr.set("target.design.format", "PRINT");
      instr.set("target.design.name", targetDesignName.replace(".pdf", ".plt"));
    }
    
    //generate the Target section entries
    buf.append("[Target]" + NEWLINE);
    buf.append(generateEntries(instr)); 

    //add what is only known to the document converter but not the reqestor
    String tgtDesignPath = outputMgrDir+File.separator+uniqueDirName+File.separator;    
    buf.append("target.design.path" +  EQUALS +  tgtDesignPath + NEWLINE);
    
    return buf;
  }
  

  protected  StringBuffer generateOptionsSection(QxXML instr) throws Exception {
    StringBuffer buf = new StringBuffer();
    
    buf.append("[Options]" + NEWLINE);
    String xlationType = determineXlationType(instr);
    buf.append(generateOptionsEntries(xlationType) + NEWLINE);    
    
    return buf;
  }
  
  
  private String determineXlationType(QxXML instr) {
      QxInstruction sectionInstr = null;
      
      sectionInstr = getSourceSection(instr);
      String srcDesignFormat = sectionInstr.get("source.design.format");

      sectionInstr = getTargetSection(instr);
      String targetDesignFormat = sectionInstr.get("target.design.format"); 
      
      String xlationType = srcDesignFormat.trim()+"-to-"+targetDesignFormat.trim();
     
      //accommodate pdf generation via postscript
      if (xlationType.equalsIgnoreCase(PROE_TO_PDF)) {
        String pdf_generation_approach=getPDFGenerationApproachProperty();        
        if (pdf_generation_approach.equalsIgnoreCase(POSTSCRIPT_APPROACH))
          xlationType = PROE_TO_PRINT;          
      }
      
      return xlationType;
  }

  private String getPDFGenerationApproachProperty() {
    String pdf_generation_approach=DIRECT_APPROACH;        
    try {
      pdf_generation_approach = getProperty(PDF_GENERATION_APPROACH);
      if (null==pdf_generation_approach || pdf_generation_approach.equalsIgnoreCase("")) pdf_generation_approach=DIRECT_APPROACH;
    } catch (Exception e) {
      //assume proe-to-pdf (DIRECT approach)
      pdf_generation_approach=DIRECT_APPROACH;      
    }   
    return pdf_generation_approach.trim();
  }
  
  
  protected  StringBuffer generateLogFilesSection(QxXML instrs) throws Exception {
    String chrysalisOutputDir = getProperty("chrysalis-output-dir");    
    StringBuffer buf = new StringBuffer();
    String uniqueDirName = this.getUniqueDirName(instrs);
    String uniqueDirSegment="";
    if (null != uniqueDirName) uniqueDirSegment = uniqueDirName+File.separator;

    buf.append("[Log Files]" + NEWLINE);  
    buf.append("log.activity.file" + EQUALS + chrysalisOutputDir+File.separator+uniqueDirSegment+ "activity.log" + NEWLINE); 
    buf.append("log.status.directory" + EQUALS + chrysalisOutputDir+File.separator+uniqueDirSegment + NEWLINE);
    
    return buf;
  }
  
  
  protected  StringBuffer generateEntries(QxInstruction instr) {
    Map props = instr.getProperties();
    StringBuffer buf = new StringBuffer();
    
    String nextEntry = null;
    String nextEntryVal = null;    
    Iterator keyset = props.keySet().iterator();
    while (keyset.hasNext()) {
      nextEntry = (String)keyset.next();  
      nextEntryVal = (String)props.get(nextEntry);
      buf.append(nextEntry + EQUALS + nextEntryVal  + NEWLINE);
    }
    return buf;
  }   
  
  protected  StringBuffer generateOptionsEntries(String xlationType) throws Exception {
    java.util.Properties options = null;
    
    options = getXlationOptions(xlationType);
       
    StringBuffer buf = new StringBuffer();
    
    String nextEntry = null;
    String nextEntryVal = null;    
    Iterator keyset = options.keySet().iterator();
    while (keyset.hasNext()) {
      nextEntry = (String)keyset.next();  
      nextEntryVal = (String)options.get(nextEntry);
      buf.append(nextEntry + EQUALS + nextEntryVal  + NEWLINE);
    }
    return buf;
  }

  private java.util.Properties getXlationOptions(String xlationType) throws Exception {
    java.util.Properties options = null;
    
    if (xlationType.equalsIgnoreCase(PROE_TO_PRINT)) {
      options = new java.util.Properties();
      options.put("print.pcf.file", getProperty("print-pcf-file"));
    }
    else {
      File optionsFile = getPropertiesFile(xlationType);
      PropertiesReader preader = createPropertiesReader(optionsFile);
      options = preader.fetchProperties();
    }

    return options;
  }
 
  
  private PropertiesReader createPropertiesReader(File pFile) throws zwsException {

    if (!pFile.canRead()) {
      {} //System.out.println("Chrysalis Document Converter: Either properties file " + pFile.getPath() + " does not exist or cannot be read!!");
      throw new zwsException("Chrysalis Document Converter: Either properties file " + pFile.getPath() + " does not exist or cannot be read!!");      
    }
    
    PropertiesReader preader = new PropertiesReader(pFile);
    
    return preader;       
  }
  
  
  
  private File getPropertiesFile(String translationType) throws Exception {
    String s=null;

    s = ChrysalisRequestProcessorResource.getChrysalisConfigsDirPath()+Names.PATH_SEPARATOR+translationType+".cfg";
    File optionsFile = new File(s);    
    if (!optionsFile.exists()) throw new Exception("Chrysalis translation options file " + optionsFile.getPath());    
    
    return optionsFile; 
  }
   
}
