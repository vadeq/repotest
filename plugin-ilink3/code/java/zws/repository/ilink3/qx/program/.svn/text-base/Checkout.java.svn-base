package zws.repository.ilink3.qx.program;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.qx.program.QxInstruction;
import zws.repository.ilink3.Ilink3Constants;

public class Checkout extends QxInstruction {
  
  public Checkout() {
    setName(Tags.CHECKOUT);
    set(Tags.DEPENDENCIES, Ilink3Constants.DEPENDENCIES_REQUIRED);
    set(Tags.GET_FAMILY_TABLES, Tags.TRUE);    
    //set(Tags.ASSOCIATE_INSTANCES, Tags.TRUE);
    //set(Tags.OVERRIDE, Tags.TRUE);
    //set(Tags.METADATA_ONLY, Tags.TRUE);    
  }

  //public String getWorkspaceName() { return workspaceName; }
  //public void setWorkspaceName(String s) { workspaceName = s; }
  public String getComponentName() { return get(Tags.COMPONENT_NAME); }
  public void setComponentName(String s) { set(Tags.COMPONENT_NAME, s); }
  public String getBranch() { return get(Tags.BRANCH); }
  public void setBranch(String s) { set(Tags.BRANCH,s); }
  public String getRevision() { return get(Tags.REVISION); }
  public void setRevision(String s) { set(Tags.REVISION,s); }
  public String getVersion() { return get(Tags.VERSION); }
  public void setVersion(String s) { set(Tags.VERSION,s); }
  public String getDependencies() { return get(Tags.DEPENDENCIES); }
  public void setDependencies(String s) { set(Tags.DEPENDENCIES,s); }
  public boolean getAssociateInstances() { return getBool(Tags.ASSOCIATE_INSTANCES); }
  public void setAssociateInstances(boolean b) { setBool(Tags.ASSOCIATE_INSTANCES,b); }
  public boolean getOverride() { return getBool(Tags.OVERRIDE); }
  public void setOverride(boolean b) { setBool(Tags.OVERRIDE,b); }
  public boolean getMetadataOnly() { return getBool(Tags.METADATA_ONLY); }
  public void setMetadataOnly(boolean b) { setBool(Tags.METADATA_ONLY,b); }
}
