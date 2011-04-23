package com.zws.functor.processor.action.file;

import com.zws.application.Constants;
import com.zws.functor.processor.action.Action;
import com.zws.functor.util.file.PS2PDF;
import com.zws.util.FileNameUtil;

import java.io.File;
import java.io.FileNotFoundException;

public class ConvertPS2PDF extends Action  {

  public void execute () throws Exception {
    Exception ex=null;
    PS2PDF action = new PS2PDF();
    action.setInColor(inColor);
    String inputFile = getActionLog().getProperty(postscriptFileName);
    action.setInputFileName(inputFile);
    File f = new File(getOutputPath());
    if (!f.exists() || !f.isDirectory()) throw new FileNotFoundException("Output directory does not exist: " + getOutputPath());
    action.setOutputFileName(getOutputPath()+Constants.FILE_SEPARATOR+FileNameUtil.getBaseName(action.getInputFileName())+".pdf");
    try {
      action.execute();
      getActionLog().log("ok: converted "+inputFile+" to PDF: "+getOutputPath());
      getActionLog().setProperty(pdfFileName, action.getOutputFileName());
      if (getDeleteInputFile()) {
        File in = new File(inputFile);
        in.delete();
        getActionLog().removeProperty(postscriptFileName);
      }
    }
    catch(Exception e) {
      getActionLog().log("Failed: converting "+inputFile+" to PDF: "+getOutputPath());
      ex = e;
    }
    if (null!=ex) throw ex;
  }

  //todo: add inputFileNameLogProperty so input filename is looked up from the log property
  public boolean getInColor() { return inColor; }
  public void setInColor(boolean b) { inColor=b; }
  public String getOutputPath() { return outputPath; }
  public void setOutputPath(String s) { outputPath=s; }
  public boolean getDeleteInputFile() { return deleteInputFile; }
  public void setDeleteInputFile(boolean b) { deleteInputFile=b; }

  private boolean inColor=false;
  private boolean deleteInputFile=false;
  private String outputPath=null;
}
