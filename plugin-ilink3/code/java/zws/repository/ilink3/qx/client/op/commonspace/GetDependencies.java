package zws.repository.ilink3.qx.client.op.commonspace; /*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 11, 2004, 11:41 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.repository.ilink3.Ilink3Constants;
import zws.repository.ilink3.qx.client.op.xml.GetDependenciesHandler;
import zws.repository.ilink3.qx.client.op.xml.IntralinkResultHandler;

public class GetDependencies extends IntralinkRepositoryOp {
  public IntralinkResultHandler getXMLResultHandler() { return new GetDependenciesHandler(); }
  
  protected void createOpInstructionXML(){
    openTag("report-dependencies");
    writeParameter("name", getComponentName());
    writeParameter("branch", getBranch());
    writeParameter("rev", getRevision());
    writeParameter("ver", getVersion());
    if (null!=getConfiguration()) writeParameter("configuration", getConfiguration());
    if (null!=getAssociations())writeParameter("associations", getAssociations());
    closeTag();
  }

  public String getComponentName() { return componentName; }
  public void setComponentName(String s) { componentName=s; }
  public String getBranch() { return branch; }
  public void setBranch(String s) { branch=s; }
  public String getRevision() { return revision; }
  public void setRevision(String s) { revision=s; }
  public int getVersion() { return version; }
  public void setVersion(int i) { version=i; }
  public String getAssociations() { return associations; }
  public void setAssociations(String s) { associations=s; }
  public String getConfiguration() { return configuration; }
  public void setConfiguration(String s) { configuration=s; }

  private String componentName=null;
  private String branch=null;
  private String revision=null;
  private int version=0;
  private String associations=Ilink3Constants.DEPENDENCIES_ALL;
  private String configuration=Ilink3Constants.AS_STORED;
}
