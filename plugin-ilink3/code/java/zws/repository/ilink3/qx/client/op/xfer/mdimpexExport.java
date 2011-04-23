package zws.repository.ilink3.qx.client.op.xfer;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.repository.ilink3.Ilink3Constants;
import zws.util.ExecShell;

public class mdimpexExport extends IntralinkXferOp {
  //protected boolean requiresLicense() { return false; }
  protected String getEXEName() { return "mdimpex-export.bat"; }

  public void setArguments(ExecShell shell) {
    shell.addCommandLineArgument(getLDBLocation().getAbsolutePath());
    shell.addCommandLineArgument(getComponentName());
    shell.addCommandLineArgument(getWorkspaceName());
    shell.addCommandLineArgument(getOutputDirectory());
    shell.addCommandLineArgument(getDependencies());
  }

  public String getComponentName() { return componentName; }
  public void setComponentName(String s) { componentName = s; }
  //public String getWorkspaceName() { return workspaceName; }
  //public void setWorkspaceName(String s) { workspaceName = s; }
  public String getOutputDirectory() { return outputDirectory; }
  public void setOutputDirectory(String s) {  outputDirectory= s; }
  public String getDependencies() { return dependencies; }
  public void setDependencies(String s) { dependencies=s; }

  private String componentName=null;
  //private String workspaceName=null;
  private String outputDirectory=null;
  private String dependencies=Ilink3Constants.DEPENDENCIES_ALL;
}
