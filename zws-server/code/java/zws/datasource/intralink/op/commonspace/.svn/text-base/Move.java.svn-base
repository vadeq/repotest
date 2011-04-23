package zws.datasource.intralink.op.commonspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.*;
import java.io.File;
import java.io.FileWriter;

public class Move extends IntralinkRepositoryOp {
  protected void writeRepositoryInstruction() throws IOException {
    openTag("move");
    writeParameter("name", getComponentName());
    writeParameter("new-folder", getPath());
    closeTag();
  }
	 
	public String getComponentName() { return componentName; }
	public void setComponentName(String s) { componentName=s; } 
	public String getPath() { return path; }
	public void setPath(String s) { path= s; }
	
	private String componentName=null;
	private String path=null;
}
