package zws.repository.ilink3.qx.client.op.commonspace; /*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 15, 2004, 7:44 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.bill.intralink.BillOfMaterials;
import zws.repository.ilink3.qx.client.op.xml.GetBillHandler;
import zws.repository.ilink3.qx.client.op.xml.IntralinkResultHandler;

public class ReportLatestBill extends IntralinkRepositoryOp {
  
  protected void createOpInstructionXML(){
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
