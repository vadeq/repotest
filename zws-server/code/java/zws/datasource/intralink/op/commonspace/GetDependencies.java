package zws.datasource.intralink.op.commonspace;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on October 11, 2004, 11:41 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.datasource.Names;
import zws.datasource.intralink.xml.GetDependenciesHandler;
import zws.datasource.intralink.xml.IntralinkResultHandler;

import java.io.*;
import java.io.File;
import java.io.FileWriter;

public class GetDependencies extends IntralinkRepositoryOp {
  public IntralinkResultHandler getXMLResultHandler() { return new GetDependenciesHandler(); }
  
  protected void writeRepositoryInstruction() throws IOException {
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
  private String associations=ALL_DEPENDENCIES;
  private String configuration=AS_STORED;
}
