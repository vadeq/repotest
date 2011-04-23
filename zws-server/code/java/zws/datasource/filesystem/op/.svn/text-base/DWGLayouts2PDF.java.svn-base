package zws.datasource.filesystem.op; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.*;
import zws.application.Properties;
import zws.application.server.datasource.Names;
import zws.datasource.filesystem.xml.FileSystemResultHandler;
import zws.data.filter.file.SimpleFileFilter;
import java.util.*;
import java.io.File;

public class DWGLayouts2PDF extends FileSystemOpBase {
  protected String getEXEName() { return "dwgLayouts2pdf.bat"; }
  protected String getConnectorPath() { return Properties.get(Names.ENV_DIR) + Names.PATH_SEPARATOR +"autocad" + Names.PATH_SEPARATOR + "bin" + Names.PATH_SEPARATOR + "connector"; }
  public FileSystemResultHandler getXMLResultHandler() { return null; }
  //protected String getOutputFileName(File f) { return f.getAbsolutePath()+Names.PATH_SEPARATOR+targetBaseName+".pdf"; }
  public void setSourceAutoCADFile(String path) { sourceAutoCADFile = new File(path); }
  public void setSourceAutoCADFile(File f) { sourceAutoCADFile = f; }
  public File getSourceAutoCADFile() { return sourceAutoCADFile; }

  //public void setOutputDirectory(String path) { outputDirectory = new File(path); }
  //public void setOutputDirectory(File f) { outputDirectory= f; }
  //public File getOutputDirectory() { return outputDirectory; }

  protected void setArguments(ExecShell shell) {
    //shell.addCommandLineArgument(workingDir.getAbsolutePath() + Names.PATH_SEPARATOR + targetAutoCADFile.getName(), false); 
    shell.addCommandLineArgument(workingDir.getAbsolutePath(), false); 
  }

  protected void initExecution() throws Exception {
    File f = new File(workingDir, sourceAutoCADFile.getName());
    FileUtil.copy(sourceAutoCADFile, f);
  }

  protected void finishExecution(){
    File f = new File(workingDir, sourceAutoCADFile.getName());
    if (f.exists()) f.delete();

    //find all pdfs in output directory
    SimpleFileFilter filter = new SimpleFileFilter();
    filter.includeFileType("pdf");
    filter.setIncludeAllFileNames(true);
    File[] pdfs = workingDir.listFiles(filter);
    resetStorage();
    try { initializeStorage(new Vector()); }
    catch (Exception ignore) {ignore.printStackTrace(); }
    for (int idx=0; idx< pdfs.length; idx++) {
      try {  store(pdfs[idx]); }
      catch (Exception ignore) {ignore.printStackTrace(); }
    }
    /*
    File pdf;
    String pdfName;
    String dwgName = FileNameUtil.getBaseName(sourceAutoCADFile.getName())+"_";
    for (int idx=0; idx< pdfs.length; idx++) {
      layoutPDFs.add(pdfs[idx]);
      pdfName = pdfs[idx].getName();
      if (pdfName.startsWith(dwgName)) {
        pdfName = pdfName.substring(dwgName.length());
        pdf = new File(pdfs[idx].getParentFile(), pdfName);
        pdfs[idx].renameTo(pdf);
        layouts.add(pdf);
      }   
    }
    */     
  }

  private File sourceAutoCADFile=null;
  //private File outputDirectory=null;
}
