package zws.datasource.intralink.op.commonspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.*;

public class DeleteFolder extends IntralinkRepositoryOp {
  protected void writeRepositoryInstruction() throws IOException {
    openTag("delete-folder");
    writeParameter("path", getPath());
    closeTag();
  }
	
	public String getPath() { return path; }
	public void setPath(String s) { path= s; }

	private String path=null;
}
