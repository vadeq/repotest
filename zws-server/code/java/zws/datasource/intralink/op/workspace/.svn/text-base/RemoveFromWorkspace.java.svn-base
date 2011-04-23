package zws.datasource.intralink.op.workspace;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 28, 2005, 1:14 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.datasource.Names;
import zws.datasource.intralink.xml.IntralinkResultHandler;
import zws.datasource.intralink.xml.WorkspaceHandler;

import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Collection;


public class RemoveFromWorkspace extends IntralinkWorkspaceOp {
  public IntralinkResultHandler getXMLResultHandler() { return new WorkspaceHandler(); }
  
  protected void writeWorkspaceInstruction() throws IOException {
    openTag("remove-item");
    writeParameter("name", getComponentName());
    closeTag();
  }
  
  public Object getResult() {
    Collection c = getResults();
    if (null==c) return null;
    if (c.size()==0) return null;
    return c.iterator().next();
  }
  
	public String getComponentName() { return componentName; }
	public void setComponentName(String s) { componentName=s; } 
	//public String getWorkspaceName() { return workspaceName; }
	//public void setWorkspaceName(String s) { workspaceName=s; } 
  public boolean getShowInstances() { return showInstances; }
  public void setShowInstances(boolean b) { showInstances=b; }

  //private String workspaceName=null;
  private String componentName=null;
  private boolean showInstances = true;
}
