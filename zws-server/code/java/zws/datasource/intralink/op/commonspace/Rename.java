package zws.datasource.intralink.op.commonspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.FileNameUtil;
//impoer zws.util.Logwriter;

import java.io.*;

public class Rename extends IntralinkRepositoryOp {
  protected void writeRepositoryInstruction() throws IOException {
    openTag("rename");
    writeParameter("name", getComponentName());
    writeParameter("new-name", getNewName());
    closeTag();
  }

	public String getComponentName() { return componentName; }
	public void setComponentName(String s) {
	  componentName=s;
	  if (31 < FileNameUtil.getBaseName(s).length()) {
	    componentName = FileNameUtil.getBaseName(s).substring(0, 30) + "." + FileNameUtil.getFileNameExtension(s);
	    {}//Logwriter.printOnConsole("Filename truncated to 31 chars");
	  }
	}

	public String getNewName() { return newName; }
	public void setNewName(String s) { newName = s; }
	
	private String componentName=null;
	private String newName=null;
}
