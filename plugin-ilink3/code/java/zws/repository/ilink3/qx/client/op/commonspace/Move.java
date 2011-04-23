package zws.repository.ilink3.qx.client.op.commonspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class Move extends IntralinkRepositoryOp {
  
  protected void createOpInstructionXML(){
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
