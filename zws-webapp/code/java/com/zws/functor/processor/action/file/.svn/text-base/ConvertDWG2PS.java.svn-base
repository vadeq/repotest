package com.zws.functor.processor.action.file;

import com.zws.application.Constants;
import com.zws.functor.processor.action.Action;
import com.zws.functor.util.file.DWG2PS;

import java.io.File;
import java.io.FileNotFoundException;

public class ConvertDWG2PS extends Action  {

  public void execute () throws Exception {
    Exception ex=null;
    DWG2PS action = new DWG2PS();
    action.setInColor(inColor);
    String inputFilename = getFilename();
    if (null!=getFilenameLogProperty()) inputFilename = getActionLog().getProperty(getFilenameLogProperty());
    else if (null!=getFilenameMetadata()) inputFilename = getDocument().get(getFilenameMetadata());

    if (null!=getInputPath()) inputFilename = getInputPath() + Constants.FILE_SEPARATOR + inputFilename;
    else if (null!=getInputPathMetadata()) inputFilename = getDocument().get(getInputPathMetadata()) + Constants.FILE_SEPARATOR + inputFilename;
    //todo add inputPathMetadata and inputPathLogProperty

    action.setInputFileName(inputFilename);
    File f = new File(getOutputPath());
    if (!f.exists() || !f.isDirectory()) throw new FileNotFoundException("Output directory does not exist: " + getOutputPath());
    action.setOutputPath(getOutputPath());
    if (0<getTimeout()) action.setTimeout(getTimeout());
    try {
      action.execute();
      getActionLog().log("ok: converted "+inputFilename+" to postscript: "+getOutputPath());
      getActionLog().setProperty(postscriptFileName, action.getOutputFileName());
      {} //System.out.println("----------------------------------------------");
      {} //System.out.println(inputFilename);
      {} //System.out.println("----------------------------------------------");
      {} //System.out.println(action.getOutputFileName());
      {} //System.out.println("----------------------------------------------");
      if (getDeleteInputFile()) {
        File in = new File(inputFilename);
        in.delete();
      }
    }
    catch(Exception e) {
      getActionLog().log("Failed: converting "+inputFilename+" to postscript: "+getOutputPath());
      ex = e;
    }
    if (null!=ex) throw ex;
  }

  public String getFilename() { return filename; }
  public void setFilename(String s) { filename=s; }
  public String getFilenameMetadata() { return filenameMetadata; }
  public void setFilenameMetadata(String s) { filenameMetadata=s; }
  public String getFilenameLogProperty() { return filenameLogProperty; }
  public void setFilenamelogProperty(String s) { filenameLogProperty=s; }
  public String getInputPath() { return inputPath; }
  public void setInputPath(String s) { inputPath=s; }
  public String getInputPathMetadata() { return inputPathMetadata; }
  public void setInputPathMetadata(String s) { inputPathMetadata=s; }
  public String getOutputPath() { return outputPath; }
  public void setOutputPath(String s) { outputPath=s; }
  public boolean getDeleteInputFile() { return deleteInputFile; }
  public void setDeleteInputFile(boolean b) { deleteInputFile=b; }
  public long getTimeout() { return timeout; }
  public void setTimeout(long l) { timeout=l; }

  private boolean inColor=false;
  private boolean deleteInputFile=false;
  private String filename=null;
  private String filenameMetadata=null;
  private String filenameLogProperty=null;
  private String inputPath=null;
  private String inputPathMetadata=null;
  private String outputPath=null;
  private long timeout = 0;
}
