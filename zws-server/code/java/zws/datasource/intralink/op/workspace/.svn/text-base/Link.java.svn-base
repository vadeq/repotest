package zws.datasource.intralink.op.workspace;
import zws.application.server.datasource.Names;

import java.io.*;
import java.io.File;
import java.io.FileWriter;

public class Link extends IntralinkWorkspaceOp {
  protected void writeWorkspaceInstruction() throws IOException {
    openTag("link");
    writeParameter("parent", getParentName());
    writeParameter("child", getChildName());
    closeTag();
  }

	public String getParentName() { return parentName; }
	public void setParentName(String s) { parentName=s; } 
	public String getChildName() { return childName; }
	public void setChildName(String s) { childName = s; }
	
	private String parentName=null;
	private String childName=null;
}
