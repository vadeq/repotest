package zws.datasource.intralink.op.workspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.datasource.Names;
import zws.datasource.intralink.xml.IntralinkResultHandler;
import zws.util.ExecShell;
import zws.util.FileNameUtil;
//impoer zws.util.Logwriter;

import java.io.File;
import java.io.IOException;

public abstract class ProEConverter extends IntralinkWorkspaceOp {
  protected String getEXEPath() { return getConnectorPath() + Names.PATH_SEPARATOR + "ProEConverter.bat"; }
  protected abstract String getTypeExtention();
  protected abstract String getTargetOutputType();
  protected String getInstructionFileName(File f) { return null; } //override
  protected String getOutputFileName(File f) { return null; } //override
  protected void writeWorkspaceInstruction() throws IOException {}
  protected void createInstructionFile(String filename) throws Exception {}

  public void setArguments(ExecShell shell) {
    shell.addCommandLineArgument(getLDBLocation().getAbsolutePath());
    shell.addCommandLineArgument(getWorkspaceName());	
    shell.addCommandLineArgument(getComponentName());
    shell.addCommandLineArgument(getTargetOutputType());
  }
  
  protected void finishExecution() throws Exception {
    File dumpFile = new File(workingDir, FileNameUtil.convertType(getComponentName(), getTypeExtention()));
    File outFile = new File(getBinaryOutputPath(), getBinaryFilename());
    if (dumpFile.exists()) {}//Logwriter.printOnConsole(dumpFile.getAbsolutePath()+ " created" );
    if (outFile.exists()) outFile.delete(); 
    {}//Logwriter.printOnConsole("renaming to " + outFile.getAbsolutePath() );
    {}//Logwriter.printOnConsole("copying " +dumpFile.getAbsolutePath()+ " to " + outFile.getAbsolutePath());
    if (!dumpFile.renameTo(outFile))
      throw new Exception("Conversion to " + getTypeExtention() + " Failed");
  }

  public String getComponentName() { return componentName; }
  public void setComponentName(String s) { componentName = s; }
  //public String getWorkspaceName() { return workspaceName; }
  //public void setWorkspaceName(String s) { workspaceName = s; }
  public String getBinaryFilename() { return binaryFilename; }
  public void setBinaryFilename(String s) { binaryFilename=s; }
  
  public String getBinaryOutputPath() { return binaryOutputPath; }
  public void setBinaryOutputPath(String s) { binaryOutputPath = s; }  

  //private String workspaceName=null;
  private String componentName=null;
  private String binaryFilename=null;
  private String binaryOutputPath=null;
}
