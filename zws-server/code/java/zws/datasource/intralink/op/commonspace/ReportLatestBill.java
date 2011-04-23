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

public class ReportLatestBill extends IntralinkRepositoryOp {
  protected void writeRepositoryInstruction() throws IOException {
    openTag("report-latest-bill");
    writeParameter("name", getComponentName());
    closeTag();
  }
 
  public IntralinkResultHandler getXMLResultHandler() { 
    xmlHandler = new GetBillHandler();
    return xmlHandler;
  }

  public String getComponentName() { return componentName; }
  public void setComponentName(String s) { componentName = s; }

  public BillOfMaterials getBillOfMaterials() { return xmlHandler.getBillOfMaterials(); }
  public Object getResult() { return xmlHandler.getBillOfMaterials(); }
  
  private String componentName=null;
  
  private GetBillHandler xmlHandler=null;
}
