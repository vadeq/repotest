package com.zws.functor.processor.action.file;

import com.zws.application.Constants;
import com.zws.functor.processor.action.Action;
import com.zws.functor.util.file.Office2PDF;
import com.zws.util.FileNameUtil;

import java.io.File;
import java.io.FileNotFoundException;

public class ConvertOffice2PDF extends Action  {

  public void execute () throws Exception {
    Exception ex=null;
    Office2PDF action = new Office2PDF();
    action.setInColor(inColor);
    String inputFilename = getFilename();
    if (null!=getFilenameLogProperty()) inputFilename = getActionLog().getProperty(getFilenameLogProperty());
    else if (null!=getFilenameMetadata()) inputFilename = getDocument().get(getFilenameMetadata());

    if (null!=getInputPath()) inputFilename = getInputPath() + Constants.FILE_SEPARATOR + inputFilename;
    //todo add inputPathMetadata and inputPathLogProperty

    action.setInputFileName(inputFilename);
    File f = new File(getOutputPath());
    if (!f.exists() || !f.isDirectory()) throw new FileNotFoundException("Output directory does not exist: " + getOutputPath());
    action.setOutputPath(getOutputPath());
    try {
      action.execute();
      getActionLog().log("ok: converted "+inputFilename+" to PDF: "+getOutputPath());
      getActionLog().setProperty(pdfFileName, action.getOutputPath()+Constants.FILE_SEPARATOR+FileNameUtil.convertType(inputFilename, "pdf"));
      if (getDeleteInputFile()) {
        File in = new File(inputFilename);
        in.delete();
      }
    }
    catch(Exception e) {
      getActionLog().log("Failed: converting "+inputFilename+" to PDF: "+getOutputPath());
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
  public String getOutputPath() { return outputPath; }
  public void setOutputPath(String s) { outputPath=s; }
  public boolean getDeleteInputFile() { return deleteInputFile; }
  public void setDeleteInputFile(boolean b) { deleteInputFile=b; }
  public boolean getInColor() { return inColor; }
  public void setInColor(boolean b) { inColor=b; }

  private boolean inColor=false;
  private boolean deleteInputFile=false;
  private String filename=null;
  private String filenameMetadata=null;
  private String filenameLogProperty=null;
  private String inputPath=null;
  private String outputPath=null;
}
