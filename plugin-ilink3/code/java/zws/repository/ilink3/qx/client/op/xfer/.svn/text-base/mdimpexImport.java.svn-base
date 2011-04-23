package zws.repository.ilink3.qx.client.op.xfer;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.repository.ilink3.Ilink3Constants;
import zws.util.ExecShell;

public class mdimpexImport extends IntralinkXferOp {
  //protected boolean requiresLicense() { return false; }
  protected String getEXEName() { return "mdimpex-import.bat"; }

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
  private String dependencies=Ilink3Constants.DEPENDENCIES_NONE;
}
