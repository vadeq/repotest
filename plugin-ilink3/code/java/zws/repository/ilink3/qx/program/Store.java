package zws.repository.ilink3.qx.program;/*
DesignState - Design Compression Technology.
@author: Emmanuel Ankutse
@version: 1.0
Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import zws.qx.program.QxInstruction;
import zws.repository.ilink3.Ilink3Constants;

public class Store extends QxInstruction {
  
  public Store() {
    setName(Tags.STORE);
    set(Tags.DEPENDENCIES, Ilink3Constants.DEPENDENCIES_ALL);
    //set(Tags.ASSOCIATE_INSTANCES, Tags.TRUE);
    //set(Tags.OVERRIDE, Tags.TRUE);
    //set(Tags.METADATA_ONLY, Tags.TRUE);    
  }

  public String getComponentName() { return get(Tags.COMPONENT_NAME); }
  public void setComponentName(String s) { set(Tags.COMPONENT_NAME, s); }
  public String getInputPath() { return get(Tags.INPUT_PATH); }
  public void setInputPath(String s) { set(Tags.INPUT_PATH,s); }
  public String getDependencies() { return get(Tags.DEPENDENCIES); }
  public void setDependencies(String s) { set(Tags.DEPENDENCIES,s); }
}
