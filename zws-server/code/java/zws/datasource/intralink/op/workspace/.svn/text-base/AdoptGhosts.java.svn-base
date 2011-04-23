package zws.datasource.intralink.op.workspace;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 1, 2004, 3:40 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.datasource.Names;

import java.io.*;
import java.io.File;
import java.io.FileWriter;

public class AdoptGhosts extends IntralinkWorkspaceOp {
  protected void writeWorkspaceInstruction() throws IOException {
    openTag("adopt-ghosts");
    writeParameter("quarantine-folder", quarantineFolder);
    closeTag();
  }

	//public String getWorkspaceName() { return workspaceName; }
	//public void setWorkspaceName(String s) { workspaceName = s; }
	public String getQuarantineFolder() { return quarantineFolder; }
	public void setQuarantineFolder(String s) { quarantineFolder = s; }
	
	//private String workspaceName=null;
  private String quarantineFolder=null;
}
