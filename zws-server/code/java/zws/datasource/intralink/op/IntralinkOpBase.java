package zws.datasource.intralink.op; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Properties;
import zws.application.server.datasource.Names;
import zws.datasource.intralink.IntralinkSource;
import zws.datasource.intralink.IlinkTKLicensePool;
import zws.datasource.intralink.xml.*;
import zws.op.ListOpBase;
import zws.security.Authentication;
import zws.util.*;

import java.io.*;
import java.util.Collection;

import javax.xml.parsers.*;
import org.xml.sax.*;

public abstract class IntralinkOpBase extends ListOpBase {
  protected String getOpType() { 
    String type = getClass().getName();
    type = type.substring(type.lastIndexOf('.')+1);
    return "ilink-" + type; 
  }

  protected String getEXEName() { return "QxService.bat"; }
  protected String getEXEPath() { return getConnectorPath() + Names.PATH_SEPARATOR + getEXEName(); }
  protected String getConnectorPath() { 
    return getEnvRoot().getAbsolutePath() + Names.PATH_SEPARATOR + "bin" + Names.PATH_SEPARATOR + "connector";
  }

  protected void initExecution() throws Exception { }
  protected void finishExecution() throws Exception { }
  protected void createInstructionFile(String filename) throws Exception { }
  public IntralinkResultHandler getXMLResultHandler() { return new IlinkQxResponseHandler(); }

  protected boolean requiresLicense() { return true; }
  public void execute() throws Exception {
    Object token = null;
    IlinkTKLicensePool tkPool = IlinkTKLicensePool.getLicensePool(datasource.getName());
    if (requiresLicense()) token = tkPool.takeToken(datasource.getTimeout());
    try {
	    getNewCount();
	    initExecution();
	    File executable = new File(getEXEPath());
	    if (!executable.exists()) throw new FileNotFoundException("executable file does not exist! [" + getOpType() + "] " +executable.getAbsolutePath());
	    workingDir = defineWorkingDirectory(executable);
	    instruction = getInstructionFileName(workingDir);
	    response = getOutputFileName(workingDir);
	    createInstructionFile(instruction);
	    ExecShell shell = new ExecShell();
	    shell.setExecutable(executable.getAbsolutePath());
	    shell.setWorkingDirectory(workingDir);
	    shell.addCommandLineArgument(getEnvRoot().getAbsolutePath());
	    shell.addCommandLineArgument(workingDir.getAbsolutePath());
	    setArguments(shell);
	    shell.execute();
	
	    exitCode = shell.waitFor();
	    handleResponse(response);
	    finishExecution();
	    if (zws.Server.debugMode()) return;
	    //cleanup
	    DeleteFile deleter = new DeleteFile();
	    deleter.setDeleteIfNotEmpty(true);
	    deleter.setFile(workingDir);
	    deleter.execute();
	    if (Boolean.FALSE == (Boolean)deleter.getResult()) {}{}//Logwriter.printOnConsole("Could not delete working dir: " + workingDir.getAbsolutePath());
    }
	  catch(Exception e) { throw e; }
	  finally { if (null!=token) tkPool.releaseToken(token); } }
 
  protected void setArguments(ExecShell shell) {} //default    

  private File defineWorkingDirectory(File f) {
    try { 
      File workingDir = new File (Properties.get(Names.WORKING_DIR) + Names.PATH_SEPARATOR + getOpType() + Names.PATH_SEPARATOR + "work"+getNewCount());
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

  public void setAuthentication(Authentication a) { authentication = a; }
  public Authentication getAuthentication() { return authentication; }
  public String getUsername() {
    if (null==authentication) return datasource.getDefaultUsername();
    return authentication.getUsername(); 
  }    
  public String getPassword() { 
    if (null==authentication) return datasource.getDefaultPassword();
    return authentication.getPassword(); 
  }

  public void setStorable(Storable s) { storable=s; }

  protected void handleResponse(String outputFilename) throws Exception {
    if (null==getOutputFileName(workingDir)) {
      {}//Logwriter.printOnConsole("No Output Results.");
      return;
    }
    IntralinkResultHandler handler = getXMLResultHandler();
    if (null==handler) {
      {}//Logwriter.printOnConsole("No Result Handler Defined.");
      return;
    }
    XMLReader xr = getParser(false).getXMLReader();
    if (null==storable) {
      initializeStorage();
      storable=this;
    }
    storable.throwOnFailure(false);
    handler.setStorable(storable);
    handler.setDatasource(datasource);
    xr.setContentHandler(handler);
    xr.setErrorHandler(handler);
    File xml = new File(outputFilename);
    FileReader r = new FileReader(xml);
    xr.parse(new InputSource(r));
    storeResult(handler);
    if (handler.hasError()) handler.throwError();
   }

  protected void storeResult(IntralinkResultHandler handler) throws Exception {
    Collection c = handler.getResults();
    resetStorage();
    initializeStorage(c);
  }

  protected static SAXParser getParser(boolean validate) throws SAXException, ParserConfigurationException {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setValidating(validate);
    return factory.newSAXParser();
  }

  protected void openTag(String tagName) throws IOException {
    instructionFile.write("<"+tagName);
  }
  protected void endUnaryTag() throws IOException {
    instructionFile.write(">" + endl);
  }
  protected void closeTag() throws IOException {
    instructionFile.write("/>" + endl);
  }
  protected void closeTag(String tagName) throws IOException {
    instructionFile.write("</"+tagName+">" + endl);
  }
  protected void writeParameter(String parameter, String value) throws IOException {
    instructionFile.write(" "+parameter+"='"+xmlValue(value)+"'");
  }
  protected void writeParameter(String parameter, int value) throws IOException {
    instructionFile.write(" "+parameter+"='"+value+"'");
  }
  protected void writeParameter(String parameter, long value) throws IOException {
    instructionFile.write(" "+parameter+"='"+value+"'");
  }
  protected void writeParameter(String parameter, boolean value) throws IOException {
    if (value) instructionFile.write(" "+parameter+"='true'");
    else instructionFile.write(" "+parameter+"='false'");    
  }
  protected void writeParameter(String parameter, double value) throws IOException {
    instructionFile.write(" "+parameter+"='"+value+"'");
  }

  protected String xmlValue(String s){
    if (null==s || "".equals(s.trim())) return "";
    StringBuffer x = new StringBuffer();
    int idx;
    char c;
    for (idx=0; idx<s.length(); idx++) {
      c = s.charAt(idx);
      switch (c) {
        case '&' : x.append("&amp;");  break;
        case '"' : x.append("&quot;"); break;
        case '\'': x.append("&apos;"); break;
        case '<' : x.append("&lt;");   break;
        case '>' : x.append("&gt;");   break;
        default  : x.append(c);
      }
    }
    return x.toString();
  }

  public void setDatasource(IntralinkSource source) { 
    datasource=source;
    envRoot = source.getEnvRoot();
  }
  public File getEnvRoot() { return envRoot; }
  public String getRepositoryName() { return getEnvRoot().getName(); }
  
  protected IntralinkSource datasource;
  private Storable storable=null;
  private Authentication authentication=null;
  private String instruction=null;
  private String response=null;
  private File envRoot=null;
  protected File workingDir=null;
  protected FileWriter instructionFile = null;
  protected String endl = Names.NEW_LINE;
  private int exitCode = -999;

  public static String AS_STORED="as-stored";
  public static String LATEST="latest";
  
  public static String NO_DEPENDENCIES = "none";
  public static String ALL_DEPENDENCIES = "all";
  public static String REQUIRED_DEPENDENCIES = "required";
}
