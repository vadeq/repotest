package zws.hi.demo.harris; /*
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

public class hiHarrisPublishCADDocument  extends hiHarrisReport {
  public String getSelectedReportName() { return Properties.get("demo-report-harris"); }
  
  
  public String generateNextPartNumberBase() {
    try {
      AgileAccess x = zws.AgileAccess.getAccess("DesignState-node-0", Properties.get(zws.application.Names.DEFAULT_AGILE_SOURCE), getAuthentication());
      x.generateNextPartNumber("CAD Part", null);
      return ctrlOK;
    }
    catch (Exception e) { 
      e.printStackTrace();
      return ctrlERROR;
    }
    
  }
  
  //CAD DOCUMENTS
  public String publishCADDocuments() {      
	  Map agileMappings = MapUtil.getMapFromMap(hiHarrisDemo.agileMappings, "CAD Document");
	  if (agileMappings.isEmpty()) {
      logFormWarning("err.mapping.is.undefined", "CAD Document");
	    {} //System.out.println("***********Mappings for CAD Document are not defined!");
	    return ctrlOK;
	  }
	  createCADDocuments();
	  storeCADDocumentBills();
	  return ctrlOK;
	}
  
  public String createCADDocuments() {
    try { createChosenItems("CAD Document"); }
    catch(Exception e) { e.printStackTrace(); }
    return ctrlOK;
  }  

  public String storeCADDocumentBills() {
    try { 
		  Collection c = loadBills(getChosenItems(), "CAD Document");
		  c = mapBills(c, "CAD Document");
		  storeBills(c); 
		}
	  catch(Exception e) { e.printStackTrace(); }
	  return ctrlOK;
	}  

 
  public String deleteCADDocuments() {
	  Map agileMappings = MapUtil.getMapFromMap(hiHarrisDemo.agileMappings, "CAD Document");
	  if (agileMappings.isEmpty()) {
	    logFormWarning("err.mapping.is.undefined", "CAD Document");
	    {} //System.out.println("***********Mappings for CAD Document are not defined!");
	    return ctrlOK;
	  }
	  try { deleteChosenItems("CAD Document"); }
	  catch(Exception e) { e.printStackTrace(); }
	  return ctrlOK;
  }
  
  //CAD PARTS
  public String publishCADModels() {
    Map agileMappings = MapUtil.getMapFromMap(hiHarrisDemo.agileMappings, "CAD Part");
    if (agileMappings.isEmpty()) {
      this.logFormWarning("err.mapping.is.undefined", "CAD Part");
	    {} //System.out.println("***********Mappings for CAD Part are not defined!");
      return ctrlOK;
    }
	  createCADmodels();
	  storeCADModelBills();
	  return ctrlOK;
	}
  
  public String createCADmodels() {
    createCADDocuments();
	  try { createChosenItems("CAD Part"); }
	  catch(Exception e) { e.printStackTrace(); }
	  return ctrlOK;
	}  
	
  public String storeCADModelBills() {
	  storeCADDocumentBills();
	  try { 
		  Collection c = loadBills(getChosenItems(), "CAD Part");
		  c = mapBills(c, "CAD Part");
		  c = linkHarrisModelToDocument(c);
		  storeBills(c); 
		}
	  catch(Exception e) { e.printStackTrace(); }
	  return ctrlOK;
	}  

	public String deleteCADModels() {      
	  Map agileMappings = MapUtil.getMapFromMap(hiHarrisDemo.agileMappings, "CAD Part");
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
