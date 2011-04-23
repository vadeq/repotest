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

public class RemoveQuarantinedDuplicates extends IntralinkWorkspaceOp {

  public IntralinkResultHandler getXMLResultHandler() { return null; }

  protected void writeWorkspaceInstruction() throws IOException {
	  openTag("remove-quarantine-duplicates");
    writeParameter("quarantine-folder", getQuarantineFolder());	  
	  closeTag();
	}

	public String getQuarantineFolder() { return quarantineFolder; }
	public void setQuarantineFolder(String s) { quarantineFolder=s; } 

	private String quarantineFolder=null;  
}
