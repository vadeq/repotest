package zws.hi.demo.harris; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Apr 12, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.data.*;
import zws.origin.*;
import zws.AgileAccess;
import zws.IntralinkAccess;
import zws.application.Names;
import zws.application.Properties;
import zws.hi.report.MetadataAdapter;
import zws.hi.report.hiReport;
import zws.util.comparator.metadata.PartNumberOrder;

import java.util.*;

import zws.bill.intralink.BillOfMaterials;

public class hiHarrisPublishBill extends hiHarrisReport {
  protected MetadataAdapter createNewMetadataAdapter() { return new HarrisMetadataAdapter(); }

  public String getSelectedReportName() { return Properties.get("demo-report-harris-all-assemblies"); }
  
  public String createCADParts() {
	  try { createChosenItems("CAD Part"); }
	  catch(Exception e) { e.printStackTrace(); }
	  return ctrlOK;
	}  
	
	public String deleteCADParts() {
	  try { deleteChosenItems("CAD Part"); }
	  catch(Exception e) { e.printStackTrace(); }
	  return ctrlOK;
	}
	
  public String publish() {
    createCADParts();
    return ctrlOK;
    /*
    BillOfMaterials bill;
    HarrisMetadataAdapter data;
    Iterator i = getChosenItems().iterator();
    try {
      IntralinkAccess y = IntralinkAccess.getAccess();
      AgileAccess x = zws.AgileAccess.getAccess("DesignState-node-0", "zero-888", getAuthentication());
      while (i.hasNext()) {
        data = (HarrisMetadataAdapter) i.next();
        bill = y.reportBill(data.getOrigin(), getAuthentication());
        //x.store(map(bill, "CAD PART"));
      }
    }
    catch(Exception e) {e.printStackTrace(); }
    return ctrlOK;
    */
  }
    
  private BillOfMaterials map(BillOfMaterials bill, String agileClassName) throws Exception {
    Metadata data = bill.getMetadata();
    Metadata m = map(data, agileClassName);
    BillOfMaterials b = new BillOfMaterials(m);
    try {
      {} //System.out.println(bill.structuredXML());
      {} //System.out.println("=================================");
      {} //System.out.println(b.structuredXML());
    }
    catch(Exception e) { e.printStackTrace(); }
    return b;
  }
  
  public String deleteBill() {
    deleteCADParts();
    /*
    BillOfMaterials bill;
    HarrisMetadataAdapter data;
    Iterator i = getChosenItems().iterator();
    try {
      IntralinkAccess y = IntralinkAccess.getAccess();
      AgileAccess x = zws.AgileAccess.getAccess("DesignState-node-0", "zero-888", getAuthentication());
      while (i.hasNext()) {
        data = (HarrisMetadataAdapter) i.next();
        bill = y.reportBill(data.getOrigin(), getAuthentication());
        //x.delete(map(bill));
      }
    }
    catch(Exception e) {e.printStackTrace(); }
    */
    return ctrlOK;
  }
}
