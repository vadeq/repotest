package zws.repository.ilink3.qx.client.op.commonspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class DeleteFolder extends IntralinkRepositoryOp {
  protected void createOpInstructionXML(){
    openTag("delete-folder");
    writeParameter("path", getPath());
    closeTag();
  }
	
	public String getPath() { return path; }
	public void setPath(String s) { path= s; }

	private String path=null;
}
