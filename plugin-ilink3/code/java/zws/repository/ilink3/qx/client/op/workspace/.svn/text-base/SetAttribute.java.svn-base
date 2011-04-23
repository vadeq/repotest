package zws.repository.ilink3.qx.client.op.workspace;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 11, 2003, 9:45 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

//If there name null, all files in workspace will have their attributes set
public class SetAttribute extends IntralinkWorkspaceOp {
	public void handleResponse(String outputFilename) throws Exception {}
	
  protected void createOpInstructionXML() {
    openTag("set-attribute");
    writeParameter("name", getComponentName());
    writeParameter("field", parameter);
    writeParameter("value", val);
    closeTag();
  }
	
	public String getComponentName() { return componentName; }
	public void setComponentName(String s) { componentName=s; } 
  public void set(String field, String value) { 
    parameter=field;
    val = value;
    if (null==val) val="";
  }

	private String componentName=null;
  private String parameter=null;
  private String val="";
}
