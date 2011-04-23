package zws.datasource.intralink.op.workspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.datasource.intralink.IntralinkSource;
import zws.util.ExecShell;

import java.io.File;
import java.io.IOException;

public class Export extends IntralinkWorkspaceOp {
  protected String getInstructionFileName(File f) { return null; } //override
  protected String getOutputFileName(File f) { return null; } //override
  protected void writeWorkspaceInstruction() throws IOException {}

  public void setArguments(ExecShell shell) {
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
  private String dependencies=IntralinkSource.ALL_DEPENDENCIES;
}
