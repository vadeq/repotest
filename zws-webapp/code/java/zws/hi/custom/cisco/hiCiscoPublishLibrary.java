package zws.hi.custom.cisco; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Apr 12, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.Synchronizer;
import zws.data.*;
import zws.origin.*;
import zws.AgileAccess;
import zws.IntralinkAccess;
import zws.application.Names;
import zws.application.Properties;
import zws.bill.intralink.BillOfMaterials;
import zws.hi.intralink.proconfly.PDFMetadataAdapter;
import zws.hi.report.MetadataAdapter;
import zws.hi.report.hiReport;
import zws.util.comparator.metadata.PartNumberOrder;

import java.util.*;

import zws.util.MapUtil;
import zws.util.StringPair;
import java.util.*;
import java.util.*;

import zws.synchronization.SynchronizationRecord;

public class hiCiscoPublishLibrary  extends hiCiscoReport {
  public String getSelectedReportName() { return Properties.get("custom-report-cisco-library"); }
  
  
  //CAD PARTS
  public String publishCADModels() {
	  createCADmodels();
	  storeCADModelBills(); //*************************
	  return ctrlOK;
	}
  
  
  
  public String createCADmodels() {
	  try { createChosenItems(); }
	  catch(Exception e) { e.printStackTrace(); }
	  return ctrlOK;
	}  
	
  public String storeCADModelBills() {
	  try { 
		  Collection c = loadBills(getChosenItems());
		  c = mapBills(c);
		  storeBills(c); 
		}
	  catch(Exception e) { e.printStackTrace(); }
	  return ctrlOK;
	}  

	public String deleteCADModels() {      
	  Map agileMappings = getCiscoLibraryAttributeMappping();
	  if (agileMappings.isEmpty()) {
      logFormWarning("err.mapping.is.undefined", "CAD Part");
	    {} //System.out.println("***********Mappings for CAD Part are not defined!");
	    return ctrlOK;
	  }
	  try { deleteChosenItems("CAD Part"); }
	  catch(Exception e) { e.printStackTrace(); }
	  return ctrlOK;
	}
	
  public String deleteBill() {
    deleteCADModels();
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
  
  public void render() { 
    synchronizeMetadata(getItems()); 
    synchronizeMetadata(getChosenItems()); 
  }
  
  private Synchronizer synkService = Synchronizer.getClient("DesignState-node-0"); 
}
