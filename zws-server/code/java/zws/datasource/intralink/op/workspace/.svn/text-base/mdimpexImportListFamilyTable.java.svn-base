package zws.datasource.intralink.op.workspace;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on November 23, 2004, 12:17 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.datasource.Names;
import zws.datasource.intralink.IntralinkSource;
import zws.util.ExecShell;

import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Iterator;

public class mdimpexImportListFamilyTable extends IntralinkWorkspaceOp {
  //protected boolean requiresLicense() { return false; }
  protected String getEXEName() { return "mdimpex-import-list-ft.bat"; }
  protected String getInstructionFileName(File f) {
    importListFileName = f.getAbsolutePath()+Names.PATH_SEPARATOR+"importList.txt";
    return importListFileName;
    
  } //override
  protected String getOutputFileName(File f) { return null; } //override

  public void setArguments(ExecShell shell) {
    shell.addCommandLineArgument(getLDBLocation().getAbsolutePath());
    shell.addCommandLineArgument(importListFileName);
    shell.addCommandLineArgument(getWorkspaceName());
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
      File f=null;
      while (i.hasNext()) {
        f = (File)i.next();
        outFile.write(f.getAbsolutePath());
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
  
  private String importListFileName=null;
  private Collection fileList=null;
  //private String workspaceName=null;
  private String dependencies=IntralinkSource.NO_DEPENDENCIES;
}
