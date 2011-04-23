package zws.datasource.intralink.op.commonspace;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 14, 2005, 12:21 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.datasource.Names;
import zws.datasource.intralink.xml.IntralinkResultHandler;
import zws.datasource.intralink.xml.SearchResultHandler;

import java.io.*;
import java.io.File;
import java.io.FileWriter;

public class FindLatest extends IntralinkRepositoryOp {
  public IntralinkResultHandler getXMLResultHandler() { return new SearchResultHandler(); }
  protected void writeRepositoryInstruction() throws IOException {
    openTag("find-latest");
    writeParameter("name", getComponentName());
    closeTag();
  }

  public String getComponentName() { return componentName; }
  public void setComponentName(String s) { componentName=s; }
  private String componentName=null;
}
