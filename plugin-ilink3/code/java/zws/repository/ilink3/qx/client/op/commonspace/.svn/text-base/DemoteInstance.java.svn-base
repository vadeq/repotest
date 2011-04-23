package zws.repository.ilink3.qx.client.op.commonspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.repository.ilink3.Ilink3Constants;

public class DemoteInstance extends IntralinkRepositoryOp {

  protected void createOpInstructionXML(){
	  openTag("demote-instance");
	  writeParameter("binary", getGenericName());
	  writeParameter("binary-branch", getGenericBranch());
	  writeParameter("binary-rev", getGenericRevision());
	  writeParameter("binary-ver", getGenericVersion());
	  writeParameter("instance-name", getInstanceName());
	  writeParameter("demote-to", getDemoteTo());
	  if (null!=formLocation) writeParameter("folder", getFormLocation());
	  if (dependencies!=null) writeParameter("dependencies", getDependencies());
	  if (configuration!=null) writeParameter("configuration", getConfiguration());
	  closeTag();
	}

  public String getInstanceName() { return instanceName; }
  public void setInstanceName(String s) { instanceName=s; }
  public String getGenericName() { return genericName; }
  public void setGenericName(String s) { genericName=s; }
  public String getGenericBranch() { return genericBranch; }
  public void setGenericBranch(String s) { genericBranch=s; }
  public void setGenericRevision(String s) { genericRevision = s; }
  public String getGenericRevision() { return genericRevision; }
  public void setGenericVersion(String s) { genericVersion = s; }
  public String getGenericVersion() { return genericVersion; }
  
  public void setDemoteTo(String s) { demoteTo = s; }
  public String getDemoteTo() { return demoteTo; }
  public String getFormLocation() { return formLocation; }  
  public void setFormLocation(String s) { formLocation=s; }  
  public void setDependencies(String s) { dependencies = s; }
  public String getDependencies() { return dependencies; }
  public void setConfiguration(String s) { configuration= s; }
  public String getConfiguration() { return configuration; }
  
  private String genericName=null;
  private String instanceName=null;
  private String genericBranch=null;
  private String genericRevision = null;
  private String genericVersion = null;
  private String demoteTo; 
  private String formLocation=null;
  private String dependencies = Ilink3Constants.DEPENDENCIES_REQUIRED;    
  private String configuration = Ilink3Constants.AS_STORED;    
}
