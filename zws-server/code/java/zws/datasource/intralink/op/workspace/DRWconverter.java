package zws.datasource.intralink.op.workspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Properties;
import zws.datasource.intralink.xml.IlinkQxResponseHandler;
import zws.datasource.intralink.xml.IntralinkResultHandler;
import zws.util.ExecShell;
import zws.util.FileNameUtil;

import java.io.File;
import java.io.IOException;

public abstract class DRWconverter extends IntralinkWorkspaceOp {

  //protected boolean requiresLicense() { return false; }
	protected abstract String getEXE();
  protected abstract String getTypeExtention();
  protected String getInstructionFileName(File f) { return null; } //override
  protected String getOutputFileName(File f) { return null; } //override
  protected void writeWorkspaceInstruction() throws IOException {}
  public IntralinkResultHandler getXMLResultHandler() { return null; }

  public void setArguments(ExecShell shell) {
    shell.addCommandLineArgument(getComponentName());
    shell.addCommandLineArgument(getWorkspaceName());	
    shell.addCommandLineArgument(Properties.get(Properties.exeProCommMsg));
    shell.addCommandLineArgument(Properties.get(Properties.exeProE));
  }
  
  protected void finishExecution() throws Exception {
    File dumpFile = new File(workingDir, FileNameUtil.convertType(getComponentName(), getTypeExtention()));
    File outFile = new File(getBinaryOutputPath(), getBinaryFilename());
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
