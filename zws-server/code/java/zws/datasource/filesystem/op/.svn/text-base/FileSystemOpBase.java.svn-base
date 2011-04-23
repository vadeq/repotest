package zws.datasource.filesystem.op; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Properties;
import zws.application.server.datasource.Names;
import zws.datasource.filesystem.FileSystemSource;
import zws.datasource.filesystem.xml.FileSystemResultHandler;
import zws.op.ListOpBase;
import zws.security.Authentication;
import zws.util.*;
import zws.util.ExecShell;
import zws.util.Storable;

import java.io.*;

import javax.xml.parsers.*;

import org.xml.sax.*;

public abstract class FileSystemOpBase extends ListOpBase {
  protected String getOpType() { 
    String type = getClass().getName();
    type = type.substring(type.lastIndexOf('.')+1);
    return "disk-" + type; 
  }
  
  protected String getConnectorPath() { return Properties.get(Names.ENV_DIR) + Names.PATH_SEPARATOR +"bin" + Names.PATH_SEPARATOR + "connector"; }
  protected String getEXEPath() { return getConnectorPath() + Names.PATH_SEPARATOR + getEXEName(); }
  protected abstract String getEXEName();
  protected void initExecution() throws Exception { }
  protected void finishExecution() throws Exception { }
  protected void createInstructionFile(String filename) { }
  public FileSystemResultHandler getXMLResultHandler() { return null; }
  public void execute() throws Exception {
    getNewCount();
    File executable = new File(getEXEPath());
    if (!executable.exists()) throw new FileNotFoundException("executable file does not exist! " + executable);
    workingDir = getWorkingDirectory(executable);
    initExecution();
    
    instruction = getInstructionFileName(workingDir);
    output = getOutputFileName(workingDir);
    createInstructionFile(instruction);
    //Profiler pofiler = new Profiler();
    //profiler.start("exec-ilink-search " + xmlFileName, "exec-ilink-search " + xmlFileName);
    ExecShell shell = new ExecShell();
    shell.setExecutable(executable.getAbsolutePath());
    shell.setWorkingDirectory(workingDir);
    setArguments(shell);
    shell.addCommandLineArgument(executable.getParent());
    shell.execute();

    //profiler.start("exec-ilink-search " + xmlFileName, "shell-wait-for");
    exitCode = shell.timeout(timeout);
    //profiler.stop("exec-ilink-search " + xmlFileName, "shell-wait-for");
    //instructionFile.delete();
    handleResponse(output);
    finishExecution();
    if (zws.Server.debugMode()) return;
    //cleanup
    File[] contents = workingDir.listFiles();
    for (int idx=0; idx<contents.length; contents[idx++].delete())
      ;
    workingDir.delete();
   }
  protected void setArguments(ExecShell shell) {
    shell.addCommandLineArgument(instruction); 
    shell.addCommandLineArgument(output);
  }    

  private File getWorkingDirectory(File f) {
    try { 
	    File workingDir = new File (Properties.get(Names.WORKING_DIR) + Names.PATH_SEPARATOR + getOpType() + Names.PATH_SEPARATOR+"work"+getNewCount());
	    if (!workingDir.exists()) workingDir.mkdirs();
	    FileUtil.deleteContents(workingDir);
	    return workingDir;
    }
    catch (Exception ignore) {
       //++ notify of error - maybe out of disk space
			ignore.printStackTrace();
			return null;
    }    
  }

  private static int count=0;
  private static synchronized int getNewCount() { return ++count; }
  protected String getInstructionFileName(File f) { return f.getAbsolutePath()+Names.PATH_SEPARATOR+"instruction.xml"; }
  protected String getOutputFileName(File f) { return f.getAbsolutePath()+Names.PATH_SEPARATOR+"response.xml"; }
  public int getExitCode() { return exitCode; }

  public FileSystemSource getDatasource() { return datasource; }
  public void setDatasource(FileSystemSource s) { datasource=s; }
  
  public void setAuthentication(Authentication a) { authentication = a; }
  public Authentication getAuthentication() { return authentication; }
  public String getUsername() {
    if (null!= authentication) return authentication.getUsername();
    return datasource.getDefaultUsername();
  }    
  public String getPassword() {
    if (null!= authentication) return authentication.getPassword();
    return datasource.getDefaultPassword();
  }    
  public void setDeleteOutput(boolean b) { deleteOutput=b; }
  public boolean getDeleteOutput() {return deleteOutput; }

  public void setStorable(Storable s) { storable=s; }

  protected void handleResponse(String outputFilename) throws Exception {
    FileSystemResultHandler handler = getXMLResultHandler();
//    if (null==handler) throw new Exception("XML Result Handler not specified for " + getClass().getName());
    if (null==handler) {
      {}//Logwriter.printOnConsole("+++todo - XML Result Handler not specified for " + getClass().getName());
      return;
    }
    /*
    UTF8Tidy tidy = new UTF8Tidy();
    tidy.setFilename(outputFilename);
    tidy.execute();
   */    
    XMLReader xr = getParser(false).getXMLReader();
    if (null==storable) storable=this;
    storable.throwOnFailure(false);
    handler.setStorable(storable);
    handler.setDatasource(getDatasource());
    xr.setContentHandler(handler);
    xr.setErrorHandler(handler);
    File xml = new File(outputFilename);
    FileReader r = new FileReader(xml);
    xr.parse(new InputSource(r));
    resetStorage();
    initializeStorage(handler.getResults());
   }

  protected static SAXParser getParser(boolean validate) throws SAXException, ParserConfigurationException {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setValidating(validate);
    return factory.newSAXParser();
  }

  private Storable storable=null;
  private Authentication authentication=null;
  private FileSystemSource datasource;
  private int exitCode = -999;
  private  boolean deleteOutput=!Boolean.valueOf(Properties.get(Names.DEBUG_MODE)).booleanValue();
  private String instruction=null;
  private String output=null;
  private long timeout = 600000; //10 minutes 
  protected File workingDir=null;
}
