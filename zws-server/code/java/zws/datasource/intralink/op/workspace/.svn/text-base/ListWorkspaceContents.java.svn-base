package zws.datasource.intralink.op.workspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.datasource.Names;
import zws.datasource.intralink.xml.IntralinkResultHandler;
import zws.datasource.intralink.xml.WorkspaceHandler;

import java.io.*;
import java.io.File;
import java.io.FileWriter;

public class ListWorkspaceContents extends IntralinkWorkspaceOp {
  public IntralinkResultHandler getXMLResultHandler() { return new WorkspaceHandler(); }
  
  protected void writeWorkspaceInstruction() throws IOException {
    openTag("list-all-in-workspace");
    writeParameter("show-instances", showInstances);
    closeTag();
  }
	 
  public boolean getShowInstances() { return showInstances; }
  public void setShowInstances(boolean b) { showInstances=b; }

  private boolean showInstances = true;
}
