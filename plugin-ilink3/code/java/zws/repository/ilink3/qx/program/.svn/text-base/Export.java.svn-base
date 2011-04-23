package zws.repository.ilink3.qx.program;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.qx.program.QxInstruction;
import zws.repository.ilink3.Ilink3Constants;

public class Export extends QxInstruction {
  
  public Export() {
    setName(Tags.EXPORT);
    set(Tags.DEPENDENCIES, Ilink3Constants.DEPENDENCIES_ALL);
    //set(Tags.ASSOCIATE_INSTANCES, Tags.TRUE);
    //set(Tags.OVERRIDE, Tags.TRUE);
    //set(Tags.METADATA_ONLY, Tags.TRUE);    
  }

  //public String getWorkspaceName() { return workspaceName; }
  //public void setWorkspaceName(String s) { workspaceName = s; }
  public String getComponentName() { return get(Tags.COMPONENT_NAME); }
  public void setComponentName(String s) { set(Tags.COMPONENT_NAME, s); }
  public String getWorkspace() { return get(Tags.WORKSPACE); }
  public void setWorkspace(String s) { set(Tags.WORKSPACE,s); }
  public String getOutputPath() { return get(Tags.OUTPUT_PATH); }
  public void setOutputPath(String s) { set(Tags.OUTPUT_PATH,s); }
  public String getDependencies() { return get(Tags.DEPENDENCIES); }
  public void setDependencies(String s) { set(Tags.DEPENDENCIES,s); }
}
