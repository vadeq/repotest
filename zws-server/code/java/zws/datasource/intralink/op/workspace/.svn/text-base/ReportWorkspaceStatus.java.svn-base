package zws.datasource.intralink.op.workspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.workspace.*;
import zws.datasource.intralink.IntralinkSource;
import zws.datasource.intralink.xml.IntralinkResultHandler;
import zws.datasource.intralink.xml.WorkspaceStatusHandler;

import java.io.*;

public class ReportWorkspaceStatus extends IntralinkWorkspaceOp {

  public IntralinkResultHandler getXMLResultHandler() { 
    handler = new WorkspaceStatusHandler(datasource.getName(), getAuthentication());
    return handler;
  }

  protected void writeWorkspaceInstruction() throws IOException {
	  openTag("report-workspace-status");
	  closeTag();
	}

  public Workspace getWorkspace() {
    if (null==handler) return null;
    if (null==handler.getResults()) return null;
    if (0==handler.getResults().size()) return null;
    Workspace workspace = (Workspace) handler.getResults().iterator().next();
    return workspace;
  }
  
  WorkspaceStatusHandler handler = null;
}
