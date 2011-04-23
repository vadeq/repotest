package com.zws.functor;

import com.zws.application.Config;
import com.zws.functor.util.ExecShell;
import com.zws.functor.util.file.UTF8Tidy;
import com.zws.xml.DrawingHandler;

import java.io.*;
import java.util.Map;

import org.apache.xerces.parsers.SAXParser;
import org.xml.sax.*;


public class MetadataExtractor extends TimerTaskFunctor  {
  private String metadataSpec, outputFile, username, password, criteria, releaseLevel, proiTkEnv;
  private long maxCount, offset;
  private int exitCode = -999;
  private ContentHandler contentHandler;
  private ErrorHandler errorHandler;
  private Map results;

  public Map getResults(){ return results; }
  public void setMetadataFile(String s) { metadataSpec = s; }
  public void setOutputFile(String  s) { outputFile = s; }
  public void setMaxCount(long l) { maxCount = l; }
  public void setOffset(long l) { offset = l; }
  public void setUsername(String  s) { username = s; }
  public void setPassword(String  s) { password = s; }
  public void setCriteria(String  s) { criteria = s; }
  public void setReleaseLevel(String  s) { releaseLevel = s; }
  public void setEXEToolkitEnv(String  s) { proiTkEnv = s; }
  public void setContentHandler(ContentHandler h) { contentHandler = h; }
  public void setErrorhandler(ErrorHandler h) { errorHandler = h; }

  public boolean generatePDFs = false;
  public int getExitCode() { return exitCode; }

  public boolean getGeneratePDFs() {return generatePDFs; }
  public void setGeneratePDFs(boolean b) { generatePDFs = b; }

  private void execExtractor() throws Exception {
    ExecShell shell = new ExecShell();
    File f = new File(Config.getProperty(Config.EXE_ILINK_SEARCH));
    if (!f.exists()) throw new Exception("executable file does not exist! " + Config.getProperty(Config.EXE_ILINK_SEARCH));
    shell.setExecutable(f.getAbsolutePath());
    shell.setWorkingDirectory(f.getParent());
    shell.addCommandLineArgument(criteria);
    shell.addCommandLineArgument(String.valueOf(maxCount));
    shell.addCommandLineArgument(releaseLevel);
    shell.addCommandLineArgument(metadataSpec);
    shell.addCommandLineArgument(username);
    shell.addCommandLineArgument(password);
    shell.addCommandLineArgument(outputFile);
    shell.addCommandLineArgument(proiTkEnv);
    shell.execute();
    exitCode = shell.waitFor();
  }

  public void execute() {
    try {
      execExtractor();
      tidyUnicode(outputFile); //eliminate invalid unicode Chars

      XMLReader xr = new SAXParser();
      //todo: parameterize the handler
      DrawingHandler handler = new DrawingHandler();
      handler.setGeneratePDFs(getGeneratePDFs());
      xr.setContentHandler(handler);
      xr.setErrorHandler(handler);
      File xml = new File(outputFile);
      FileReader r = new FileReader(xml);
      xr.parse(new InputSource(r));
      results = handler.getResults();
    }
    catch (Exception e) { e.printStackTrace(); }
  }
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
      
    }
    finally {
      try { reader.close(); writer.close(); }
      catch (Exception ex){ ex.printStackTrace(); }
      f0.delete();
    }
  }
}
