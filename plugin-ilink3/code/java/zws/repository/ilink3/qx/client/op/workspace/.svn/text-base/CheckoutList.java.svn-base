package zws.repository.ilink3.qx.client.op.workspace;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.origin.IntralinkOrigin;
import zws.repository.ilink3.Ilink3Constants;
import zws.repository.ilink3.qx.client.op.xml.IntralinkResultHandler;
import zws.repository.ilink3.qx.client.op.xml.WorkspaceHandler;

import java.util.Collection;
import java.util.Iterator;

public class CheckoutList extends IntralinkWorkspaceOp {
  public IntralinkResultHandler getXMLResultHandler() { return new WorkspaceHandler(); }

  protected void createOpInstructionXML() {
    openTag("checkout-list");
    writeParameter("dependencies", getDependencies());
    writeParameter("get-family-tables", getAssociateInstances());
    writeParameter("get-binaries", !getMetadataOnly());
    writeParameter("override-conflicts", getOverride());
    //writeParameter("show-instances", getShowInstances());
    writeParameter("show-dependencies", getShowDependencies());
    if (getShowDependencies()) writeParameter("show-history", getShowHistory());
    endUnaryTag();
    
    Iterator j = getMetadataList().iterator();
    String name, branch, rev, ver;
    Metadata m;
    IntralinkOrigin o;
    while (j.hasNext()) {
      openTag("metadata");
      m= (Metadata)j.next();
      o = (IntralinkOrigin)m.getOrigin();
      writeParameter("name", o.getName());
      writeParameter("branch", o.getBranch());
      writeParameter("rev", o.getRevision());
      writeParameter("ver", o.getVersion());
      closeTag();
    }
    closeTag("checkout-list");
  }

  public Collection getMetadataList() { return metadataList; }
  public void setMetadataList(Collection c) { metadataList = c; }
  public String getDependencies() { return dependencies; }
  public void setDependencies(String s) { dependencies=s; }
  public boolean getAssociateInstances() { return associateInstances; }
  public void setAssociateInstances(boolean b) { associateInstances=b; }
  public boolean getOverride() { return override; }
  public void setOverride(boolean b) { override=b; }
  public boolean getMetadataOnly() { return metadataOnly; }
  public void setMetadataOnly(boolean b) { metadataOnly=b; }
  public boolean getShowHistory() { return showHistory; }
  public void setShowHistory(boolean b) { showHistory=b; }
  public boolean getShowDependencies() { return showDependencies; }
  public void setShowDependencies(boolean b) { showDependencies=b; }

  //private String workspaceName=null;
  private Collection metadataList=null;
  private String dependencies=Ilink3Constants.DEPENDENCIES_REQUIRED;
  private boolean associateInstances = true;
  private boolean override=true;
  private boolean metadataOnly = false;
  private boolean showDependencies=false;
  private boolean showHistory=false;
}
