package zws.datasource.intralink.op.commonspace;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 15, 2004, 7:44 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.datasource.Names;
import zws.datasource.intralink.xml.GetBillHandler;
import zws.datasource.intralink.xml.IntralinkResultHandler;
import zws.util.ExecShell;
import zws.bill.intralink.BillOfMaterials;

import java.io.*;
import java.io.File;
import java.io.FileWriter;

public class ReportBill extends IntralinkRepositoryOp {
    
  protected void writeRepositoryInstruction() throws IOException {
    openTag("report-bill");
    writeParameter("name", getComponentName());
    writeParameter("branch", getBranch());
    writeParameter("revision", getRevision());
    writeParameter("version", getVersion());
    closeTag();
  }
   
  //protected String getOutputFileName(File f) { return f.getAbsolutePath() + Names.PATH_SEPARATOR + "bill.xml"; } //override
  
  public IntralinkResultHandler getXMLResultHandler() { 
    xmlHandler = new GetBillHandler();
    return xmlHandler;
  }
  
  public String getComponentName() { return componentName; }
  public void setComponentName(String s) { componentName = s; }
  public String getBranch() { return branch; }
  public void setBranch(String s) { branch= s; }
  public String getRevision() { return revision; }
  public void setRevision(String s) { revision= s; }
  public String getVersion() { return version; }
  public void setVersion(String s) { version= s; }

  public BillOfMaterials getBillOfMaterials() { return xmlHandler.getBillOfMaterials(); }
  public Object getResult() { return xmlHandler.getBillOfMaterials(); }
  
  private String componentName=null;
  private String branch=null;
  private String revision=null;
  private String version=null;
  
  private GetBillHandler xmlHandler=null;
}
