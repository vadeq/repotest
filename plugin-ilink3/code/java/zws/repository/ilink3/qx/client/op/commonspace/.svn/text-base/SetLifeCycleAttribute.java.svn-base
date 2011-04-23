package zws.repository.ilink3.qx.client.op.commonspace; /*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 11, 2003, 9:45 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

//If there name null, all files in workspace will have their attributes set
public class SetLifeCycleAttribute extends IntralinkRepositoryOp {
	public void handleResponse(String outputFilename) throws Exception {}
	
  protected void createOpInstructionXML(){
  openTag("set-life-cycle-attribute");
    writeParameter("name", getComponentName());
    writeParameter("branch", getBranch());
    writeParameter("revision", getRevision());
    writeParameter("version", getVersion());
    writeParameter("attribute", parameter);
    if (null!=val) writeParameter("value", val); 
    closeTag();
  }
	
	public String getComponentName() { return componentName; }
	public void setComponentName(String s) { componentName=s; } 
  public String getBranch() { return branch; }
  public void setBranch(String s) { branch= s; }
  public String getRevision() { return revision; }
  public void setRevision(String s) { revision= s; }
  public long getVersion() { return version; }
  public void setVersion(long s) { version= s; }

  public void set(String field, String value) { 
    parameter=field;
    val = value;
    if (null==val) val="";
  }

	private String componentName=null;
  private String branch=null;
  private String revision=null;
  private long version=-1;
  
  private String parameter=null;
  private String val="";
}
