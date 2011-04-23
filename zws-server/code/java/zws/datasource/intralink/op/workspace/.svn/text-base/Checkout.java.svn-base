package zws.datasource.intralink.op.workspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.datasource.intralink.xml.IlinkQxResponseHandler;
import zws.datasource.intralink.xml.IntralinkResultHandler;

import java.io.*;

public class Checkout extends IntralinkWorkspaceOp {
  public IntralinkResultHandler getXMLResultHandler() { return null; }
  protected void writeWorkspaceInstruction() throws IOException {
    openTag("checkout");
    writeParameter("name", getComponentName());
    writeParameter("branch", getBranch());
    writeParameter("revision", getRevision());
    writeParameter("version", getVersion());
    writeParameter("dependencies", getDependencies());
    writeParameter("get-family-tables", getAssociateInstances());
    writeParameter("get-binaries", !getMetadataOnly());
    writeParameter("override-conflicts", getOverride());    
    closeTag();
  }

  //public String getWorkspaceName() { return workspaceName; }
  //public void setWorkspaceName(String s) { workspaceName = s; }
  public String getComponentName() { return componentName; }
  public void setComponentName(String s) { componentName = s; }
  public String getBranch() { return branch; }
  public void setBranch(String s) { branch=s; }
  public String getRevision() { return revision; }
  public void setRevision(String s) { revision=s; }
  public String getVersion() { return version; }
  public void setVersion(String s) { version=s; }
  public String getDependencies() { return dependencies; }
  public void setDependencies(String s) { dependencies=s; }
  public boolean getAssociateInstances() { return associateInstances; }
  public void setAssociateInstances(boolean b) { associateInstances=b; }
  public boolean getOverride() { return override; }
  public void setOverride(boolean b) { override=b; }
  public boolean getMetadataOnly() { return metadataOnly; }
  public void setMetadataOnly(boolean b) { metadataOnly=b; }

  private String componentName=null;
  //private String workspaceName=null;
  private String branch=null;
  private String revision=null;
  private String version=null;
  private String dependencies=REQUIRED_DEPENDENCIES;
  private boolean associateInstances = true;
  private boolean override=true;
  private boolean metadataOnly = false;
}
