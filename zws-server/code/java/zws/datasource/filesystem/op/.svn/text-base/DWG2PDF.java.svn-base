package zws.datasource.filesystem.op; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Properties;
import zws.application.server.datasource.Names;
import zws.datasource.filesystem.xml.FileSystemResultHandler;
import zws.util.*;

import java.io.File;

public class DWG2PDF extends FileSystemOpBase {
   protected String getEXEName() { return "dwg2pdf.bat"; }
 	 protected String getConnectorPath() { return Properties.get(Names.ENV_DIR) + Names.PATH_SEPARATOR +"autocad" + Names.PATH_SEPARATOR + "bin" + Names.PATH_SEPARATOR + "connector"; }
   public FileSystemResultHandler getXMLResultHandler() { return null; }
   protected String getOutputFileName(File f) { return f.getAbsolutePath()+Names.PATH_SEPARATOR+targetBaseName+".pdf"; }
   public void setTargetAutoCADFile(String path) { targetAutoCADFile = new File(path); }
   public void setTargetAutoCADFile(File f) { targetAutoCADFile = f; }
   public File getTargetAutoCADFile() { return targetAutoCADFile; }

   public void setOutputDirectory(String path) { outputDirectory = new File(path); }
   public void setOutputDirectory(File f) { outputDirectory= f; }
   public File getOutputDirectory() { return outputDirectory; }
   
   protected void setArguments(ExecShell shell) {
     shell.addCommandLineArgument(workingDir.getAbsolutePath() + Names.PATH_SEPARATOR + targetAutoCADFile.getName(), false); 
   }    

   protected void initExecution() throws Exception {
     File f = new File(workingDir, targetAutoCADFile.getName());
     FileUtil.copy(targetAutoCADFile, f);
     targetBaseName = FileNameUtil.getBaseName(targetAutoCADFile.getName());
   }
   
   protected void finishExecution(){
     File f = new File(workingDir, targetAutoCADFile.getName());
     if (f.exists()) f.delete();
     f = new File(getOutputFileName(workingDir));
     File out = new File(outputDirectory, f.getName());
     f.renameTo(out);
   }

   private File targetAutoCADFile=null;
   private File outputDirectory=null;
   private String targetBaseName=null;
 }
