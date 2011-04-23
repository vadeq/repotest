package zws.datasource.intralink.op.workspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.datasource.Names;
import zws.datasource.intralink.IntralinkSource;

import java.io.*;
import java.io.File;
import java.io.FileWriter;

public class Import extends IntralinkWorkspaceOp {
  protected void writeWorkspaceInstruction() throws IOException {
    openTag("import");
    writeParameter("source-path", getComponentPath());
    writeParameter("lower-cased", nameIsLowerCased);
    writeParameter("upper-cased", nameIsUpperCased);
    closeTag();
  }

  public String getComponentPath() { return componentPath; }
  public void setComponentPath(String s) { componentPath= s; }
  //public String getWorkspaceName() { return workspaceName; }
  //public void setWorkspaceName(String s) { workspaceName = s; }
  public String getDependencies() { return dependencies; }
  public void setDependencies(String s) { dependencies=s; }
  public boolean getNameIsUpperCased() { return nameIsUpperCased; }
  public void setNameIsUpperCased(boolean b) { nameIsUpperCased=b; }
  public boolean getNameIsLowerCased() { return nameIsLowerCased; }
  public void setNameIsLowerCased(boolean b) { nameIsLowerCased=b; }

  private String componentPath=null;
  //private String workspaceName=null;
  private String dependencies=IntralinkSource.ALL_DEPENDENCIES;
  private boolean nameIsLowerCased = false;
  private boolean nameIsUpperCased = false;
}
