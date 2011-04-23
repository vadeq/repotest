package zws.repository.ilink3.qx.client.op.commonspace;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 14, 2005, 12:21 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.repository.ilink3.qx.client.op.xml.*;


public class FindLatest extends IntralinkRepositoryOp {
  public IntralinkResultHandler getXMLResultHandler() { return new SearchResultHandler(); }
  
  protected void createOpInstructionXML() {
    openTag("find-latest");
    writeParameter("name", getComponentName());
    closeTag();
  }

  public String getComponentName() { return componentName; }
  public void setComponentName(String s) { componentName=s; }
  private String componentName=null;
}
