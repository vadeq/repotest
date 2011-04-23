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

public class Find extends IntralinkRepositoryOp {
  public IntralinkResultHandler getXMLResultHandler() { return new SearchResultHandler(); }

  protected void writeRepositoryInstruction() throws IOException {
    openTag("find");
    writeParameter("name", getComponentName());
    writeParameter("branch", getBranch());
    writeParameter("revision", getRevision());
    writeParameter("version", getVersion());
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

  private String componentName=null;
  private String branch=null;
  private String revision=null;
  private int version=0;
}
