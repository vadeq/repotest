package zws.hi.demo.kla; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Apr 12, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.AgileAccess;
import zws.application.Names;
import zws.application.Properties;
import zws.hi.report.MetadataAdapter;
import zws.bill.intralink.BillOfMaterials;
import zws.data.Metadata;

public class hiKLAPublishBill extends hiKLAReport {
  protected MetadataAdapter createNewMetadataAdapter() { return new KLAMetadataAdapter(); }

  public String getSelectedReportName() { return Properties.get("demo-report-harris-all-assemblies"); }
  
  public String createCADParts() {
	  try { createChosenItems("CAD Part"); }
	  catch(Exception e) { e.printStackTrace(); }
	  return ctrlOK;
	}  
	
	public String deleteCADParts() {
	  try { deleteChosenItems(); }
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
  private AgileAccess agile() throws Exception {
	  if (null==agileAccess) agileAccess = zws.AgileAccess.getAccess("DesignState-node-0", Properties.get(Names.DEFAULT_AGILE_SOURCE), getAuthentication());
	  return agileAccess;
	}

  AgileAccess agileAccess = null;
  
}
