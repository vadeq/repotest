package zws.datasource.intralink.op.commonspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.*;

public class Demote extends IntralinkRepositoryOp {
  //public IntralinkResultHandler getXMLResultHandler() {  return new PromoteResultHandler(); }

  protected void writeRepositoryInstruction() throws IOException {
	  openTag("demote");
	  writeParameter("name", getComponentName());
	  writeParameter("branch", getBranch());
	  writeParameter("rev", getRevision());
	  writeParameter("ver", getVersion());
	  writeParameter("demote-to", getDemoteTo());
	  if (null!=formLocation) writeParameter("folder", getFormLocation());
	  if (dependencies!=null) writeParameter("dependencies", getDependencies());
	  if (configuration!=null) writeParameter("configuration", getConfiguration());
	  closeTag();
	}

  public String getComponentName() { return componentName; }
  public void setComponentName(String s) { componentName=s; }
  public String getBranch() { return branch; }
  public void setBranch(String s) { branch=s; }
  public void setRevision(String s) { revision = s; }
  public String getRevision() { return revision; }
  public void setVersion(String s) { version = s; }
  public String getVersion() { return version; }
  
  public void setDemoteTo(String s) { demoteTo = s; }
  public String getDemoteTo() { return demoteTo; }
  public String getFormLocation() { return formLocation; }  
  public void setFormLocation(String s) { formLocation=s; }  
  public void setDependencies(String s) { dependencies = s; }
  public String getDependencies() { return dependencies; }
  public void setConfiguration(String s) { configuration= s; }
  public String getConfiguration() { return configuration; }
  
  private String componentName=null;
  private String branch=null;
  private String revision = null;
  private String version = null;
  private String demoteTo;
  private String formLocation=null;
  private String dependencies = REQUIRED_DEPENDENCIES;    
  private String configuration = AS_STORED;    
}
