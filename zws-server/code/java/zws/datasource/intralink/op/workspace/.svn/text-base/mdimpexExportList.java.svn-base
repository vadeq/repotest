package zws.datasource.intralink.op.workspace;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on November 23, 2004, 12:17 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.DatasourceAccess;
import zws.application.server.datasource.Names;
import zws.data.Metadata;
import zws.datasource.intralink.IntralinkSource;
import zws.util.ExecShell;

import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Iterator;

public class mdimpexExportList extends IntralinkWorkspaceOp {
  //protected boolean requiresLicense() { return false; }
  protected String getEXEName() { return "mdimpex-export-list.bat"; }
  protected String getInstructionFileName(File f) {
    exportListFileName = f.getAbsolutePath()+Names.PATH_SEPARATOR+"exportList.txt";
    return exportListFileName;
  } //override
  
  protected String getOutputFileName(File f) { return null; } //override

  public void setArguments(ExecShell shell) {
    shell.addCommandLineArgument(getLDBLocation().getAbsolutePath());
    shell.addCommandLineArgument(exportListFileName);
    shell.addCommandLineArgument(getWorkspaceName());
    shell.addCommandLineArgument(getExportDir().getAbsolutePath());
    shell.addCommandLineArgument(getDependencies());
  }

  protected void writeWorkspaceInstruction() throws IOException {}
  protected void createInstructionFile(String filename) {
    File instructionFile = new File(filename);
    instructionFile.delete();
    try{
      instructionFile.createNewFile();
      FileWriter outFile = new FileWriter(instructionFile);
      Iterator i = getFileList().iterator();
      Metadata m=null;
      while (i.hasNext()) {
        m = (Metadata)i.next();
        outFile.write(m.getName());
        outFile.write(Names.NEW_LINE);
      }
      outFile.close();
      
    }
    catch (Exception e) { e.printStackTrace(); }
  }
  
  public Collection getFileList() { return fileList; }
  public void setFileList(Collection c) { fileList=c; }
  //public String getWorkspaceName() { return workspaceName; }
  //public void setWorkspaceName(String s) { workspaceName = s; }
  public String getDependencies() { return dependencies; }
  public void setDependencies(String s) { dependencies=s; }
  public File getExportDir() { return exportDir; }
  public void setExportDir(File f) { exportDir = f; }

  private String exportListFileName=null;
  private Collection fileList=null;
  private String dependencies=IntralinkSource.NO_DEPENDENCIES;
  private File exportDir=null;
}
