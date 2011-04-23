package zws.datasource.intralink.op.workspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.datasource.intralink.IntralinkSource;
import zws.util.ExecShell;

import java.io.File;
import java.io.IOException;

public class mdimpexImport extends IntralinkWorkspaceOp {
  //protected boolean requiresLicense() { return false; }
  protected String getEXEName() { return "mdimpex-import.bat"; }
  protected String getInstructionFileName(File f) { return null; } //override
  protected String getOutputFileName(File f) { return null; } //override
  protected void writeWorkspaceInstruction() throws IOException {}
  protected void createInstructionFile(String filename) throws Exception {}

  public void setArguments(ExecShell shell) {
    shell.addCommandLineArgument(getLDBLocation().getAbsolutePath());
    shell.addCommandLineArgument(getFilePath());
    shell.addCommandLineArgument(getWorkspaceName());
    shell.addCommandLineArgument(getDependencies());
  }

	public String getFilePath() { return filePath; }
	public void setFilePath(String s) { filePath=s; } 
  //public String getWorkspaceName() { return workspaceName; }
  //public void setWorkspaceName(String s) { workspaceName = s; }
  public String getDependencies() { return dependencies; }
  public void setDependencies(String s) { dependencies=s; }
  
	private String filePath=null;
	//private String workspaceName=null;
  private String dependencies=IntralinkSource.NO_DEPENDENCIES;
}




