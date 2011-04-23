package com.zws.functor.intralink;

import com.zws.application.Constants;
import com.zws.application.Properties;
import com.zws.datasource.IntralinkSource;
import com.zws.functor.ListFunctor;
import com.zws.functor.util.ExecShell;
import com.zws.functor.util.file.UTF8Tidy;
import com.zws.service.config.DataSourceService;
import com.zws.util.AutoIncrement;
import com.zws.util.Profiler;
import com.zws.xml.IntralinkResultHandler;

import java.io.*;
import java.util.StringTokenizer;

import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class Search extends ListFunctor  {
  public void execute() throws Exception {
    Profiler p = new Profiler();
    p.start("I-Link Search", "search: ");
    File f = new File(Properties.get(Properties.iLinkSearch));
    if (!f.exists()) throw new FileNotFoundException("executable file does not exist! " + f.getAbsoluteFile());
    String metaDataFileName = getMetaDataFileName(f);
    String outputFileName = getOutputFileName(f);
    createMetaDataSpec(metaDataFileName);
    //Profiler profiler = new Profiler();
    //profiler.start("exec-ilink-search " + xmlFileName, "exec-ilink-search " + xmlFileName);
    ExecShell shell = new ExecShell();
    shell.setExecutable(f.getAbsolutePath());
    shell.setWorkingDirectory(f.getParent());
    shell.addCommandLineArgument(criteria);
    shell.addCommandLineArgument(String.valueOf(maxCount));
    shell.addCommandLineArgument(releaseLevel);
    shell.addCommandLineArgument(metaDataFileName);
    shell.addCommandLineArgument(datasource.getUsername());
    shell.addCommandLineArgument(datasource.getPassword());
    shell.addCommandLineArgument(outputFileName);
    shell.addCommandLineArgument(datasource.getEXEToolkitEnv());
    shell.execute();
    //profiler.start("exec-ilink-search " + xmlFileName, "shell-wait-for");
    exitCode = shell.waitFor();
    p.stop("I-Link Search", "search: ");
    //profiler.stop("exec-ilink-search " + xmlFileName, "shell-wait-for");

    p.start("I-Link Search", "tidy: ");
    UTF8Tidy tidy = new UTF8Tidy();
    tidy.setFilename(outputFileName);
    tidy.execute();
    p.stop("I-Link Search", "tidy: ");

    XMLReader xr = new SAXParser();
    
    if (null==contentHandler){
      DefaultHandler h = new IntralinkResultHandler();
      contentHandler = h;
      errorHandler = h;
    }
    
//  handler.setDataSourceName(getDataSourceName());
    xr.setContentHandler(contentHandler);
    xr.setErrorHandler(errorHandler);
    File xml = new File(outputFileName);
    FileReader r = new FileReader(xml);
    p.start("I-Link Search", "parse: ");
    xr.parse(new InputSource(r));
    p.stop("I-Link Search", "parse: ");
    setResults(((IntralinkResultHandler)contentHandler).getResults());
    if (getDeleteOutputFile()) xml.delete();
    //profiler.stop("exec-ilink-search " + xmlFileName, "exec-ilink-search " + xmlFileName);
    //profiler.dump(System.out);
    p.dump(System.out);
  }

  private String getMetaDataFileName(File f) { return f.getParentFile()+Constants.FILE_SEPARATOR+outputName+".metadata"; };
  private String getOutputFileName(File f) { return Properties.get(Properties.tempDirectory)+Constants.FILE_SEPARATOR+outputName+"."+getMemberID()+"."+AutoIncrement.getNext()+".xml"; }

/*
  private void tidyUnicode(String filename) {
    FileInputStream in = null;
    FileOutputStream out = null;
    InputStreamReader reader = null;
    OutputStreamWriter writer = null;
    File f0 = new File(filename+"0");
    File f1 = new File(filename);
    f1.renameTo(f0);
    try{
      in = new FileInputStream(f0);
      reader = new InputStreamReader(in);
      out = new FileOutputStream(f1);
      writer = new OutputStreamWriter(out);
      UTF8Tidy u = new UTF8Tidy(reader, writer);
      u.execute();
    }
    catch( Exception e ){ {} //System.out.println("e: " + e ); }
    finally {
      try { reader.close(); writer.close(); }
      catch (Exception ex){ ex.printStackTrace(); }
      f0.delete();
    }
  }
*/
  private void createMetaDataSpec(String filename) {
    File configFile = new File(filename);
    configFile.delete();
    try{
      configFile.createNewFile();
      FileWriter outFile = new FileWriter(configFile);
      StringTokenizer tokens;
      if (null!=getSystemLevelAttributes()) {
        tokens = new StringTokenizer(getSystemLevelAttributes(), ";");
        while (tokens.hasMoreTokens()){
          outFile.write("sys."+tokens.nextToken().trim());
          if (tokens.hasMoreElements() || null!=getUserDefinedAttributes())
            outFile.write(Constants.LINE_SEPARATOR);
        }
      }

      if (null!=getUserDefinedAttributes()) {
        tokens = new StringTokenizer(getUserDefinedAttributes(), ";");
        while (tokens.hasMoreTokens()) {
          outFile.write("user."+tokens.nextToken().trim());
          if (tokens.hasMoreElements()) outFile.write(Constants.LINE_SEPARATOR);
        }
      }
      outFile.close();
    }
    catch (Exception e) { e.printStackTrace(); }
  }

  public String getMemberID() { return memberID; }
  public void setMemberID(String s) { memberID=s; }
  public void setOutputName(String  s) { outputName = s; }
  public void setMaxCount(long l) { maxCount = l; }
  public void setOffset(long l) { offset = l; }
//  public void setUsername(String  s) { username = s; }
//  public void setPassword(String  s) { password = s; }
  public void setCriteria(String  s) { criteria = s; }
  public void setReleaseLevel(String  s) { releaseLevel = s; }
  public String getSystemLevelAttributes() { return systemLevelAttributes; }
  public void setSystemLevelAttributes(String s) { systemLevelAttributes=s; }
  public String getUserDefinedAttributes() { return userDefinedAttributes; }
  public void setUserDefinedAttributes(String s) { userDefinedAttributes = s; }
//  public void setEXEToolkitEnv(String  s) { proiTkEnv = s; }
  public void setContentHandler(ContentHandler h) { contentHandler = h; }
  public void setErrorhandler(ErrorHandler h) { errorHandler = h; }
  public int getExitCode() { return exitCode; }

  public String getDataSourceName() { return datasourceName; }

  public void setDataSourceName(String s) {
    datasourceName=s;
    try { datasource = (IntralinkSource)DataSourceService.find(s); }
    catch (Exception e) { e.printStackTrace(); }
  }

  public void setDeleteOutputFile(boolean b) { deleteOutputFile=b; }
  public boolean getDeleteOutputFile() {return deleteOutputFile; }

  private String memberID, outputName, criteria, releaseLevel;
  private String datasourceName=null;
  private IntralinkSource datasource;
  private long maxCount, offset;
  private int exitCode = -999;
  private ContentHandler contentHandler;
  private ErrorHandler errorHandler;
  private String systemLevelAttributes;
  private String userDefinedAttributes;
  private  boolean deleteOutputFile=true;
}
