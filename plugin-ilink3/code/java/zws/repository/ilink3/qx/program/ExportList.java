package zws.repository.ilink3.qx.program;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.IntralinkOrigin;
import zws.qx.program.QxInstruction;
import zws.repository.ilink3.Ilink3Constants;

public class ExportList extends QxInstruction {
  
  public ExportList() {
    setName(Tags.EXPORT);
    set(Tags.DEPENDENCIES, Ilink3Constants.DEPENDENCIES_ALL);
    //set(Tags.ASSOCIATE_INSTANCES, Tags.TRUE);
    //set(Tags.OVERRIDE, Tags.TRUE);
    //set(Tags.METADATA_ONLY, Tags.TRUE);    
  }

  //public String getWorkspaceName() { return workspaceName; }
  //public void setWorkspaceName(String s) { workspaceName = s; }
  public void addComponent(String name) { 
    QxInstruction c = new QxInstruction();
    c.setName(Tags.METADATA );
    c.set(Tags.NAME, name);
    this.add(c);
  }
  public String getWorkspace() { return get(Tags.WORKSPACE); }
  public void setWorkspace(String s) { set(Tags.WORKSPACE,s); }
  public String getOutputPath() { return get(Tags.OUTPUT_PATH); }
  public void setOutputPath(String s) { set(Tags.OUTPUT_PATH,s); }
  public String getDependencies() { return get(Tags.DEPENDENCIES); }
  public void setDependencies(String s) { set(Tags.DEPENDENCIES,s); }
  public String getWorkspaceParent() { return get(Tags.WORKSPACE_PARENT); }
  public void setWorkspaceParent(String s) { set(Tags.WORKSPACE_PARENT,s); }  
}
