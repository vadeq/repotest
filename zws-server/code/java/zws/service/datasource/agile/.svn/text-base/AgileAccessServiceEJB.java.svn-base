package zws.service.datasource.agile; /*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.application.*;
import zws.bill.intralink.BillOfMaterials;
import zws.data.Metadata;
import zws.datasource.agile.AgileSource;
import zws.exception.AgileException;
import zws.exception.NameNotFound;
import zws.lifecycle.LifeCycleOrder;
import zws.origin.Origin;
import zws.security.Authentication;
import zws.service.datasource.DatasourceSvc;
import zws.util.FileNameUtil;

import java.io.File;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Map;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import zws.data.eco.AffectedItem;
import zws.data.eco.ECO;

import com.agile.api.APIException;

public class AgileAccessServiceEJB implements SessionBean, AgileAccessService{

	public void activate(String datasourceName) throws RemoteException{
	  try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.activate();
	  }
	  catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}


	public void refreshConfiguration(String datasourceName) throws RemoteException{
	  try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.refreshConfiguration();
	  }
	  catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}


  /*
  public void loginUser(String datasourceName, Authentication id) throws RemoteException{
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.loginUser(id);
	  }
	  catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}

  public void logoutUser(String datasourceName, Authentication id) throws RemoteException{
	  try {
      AgileSource datasource = lookup(datasourceName);
      datasource.logoutUser(id);
    }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
*/
    
  private AgileSource lookup(String datasourceName) throws NameNotFound{ return (AgileSource) DatasourceSvc.find(datasourceName); }

  public Metadata find(String datasourceName, String partnumber, Authentication id) throws RemoteException {
    try {
      AgileSource datasource = lookup(datasourceName);
      return datasource.find(partnumber, id);
    }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public boolean contains(String datasourceName, String name, Authentication id) throws RemoteException {
    try {
      AgileSource datasource = lookup(datasourceName);
      return datasource.contains(name, id);
    }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  

  public boolean hasSubComponent(String datasourceName, String parent, String subcomponent, Authentication id) throws RemoteException {
    try {
      AgileSource datasource = lookup(datasourceName);
      return datasource.hasSubComponent(parent, subcomponent, id);
    }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public Metadata renumber(String datasourceName, String partnumber, String newNumber, Authentication id) throws RemoteException {
    try {
      AgileSource datasource = lookup(datasourceName);
      return datasource.renumber(partnumber, newNumber, id);
    }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public void createItems(String datasourceName, Collection metadataList, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.createItems(metadataList, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}

  public void createCADParts(String datasourceName, Collection metadataList, boolean createCADDocuments, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.createCADParts(metadataList, createCADDocuments, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}

  public void create(String datasourceName, Metadata data, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.create(data, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}

  public void attachFile(String datasourceName, String location, String filename, String partnumber, String ecoNumber, Authentication id) throws RemoteException {
    try {
      File f = new File (findLocalPath(location, filename));
	    AgileSource datasource = lookup(datasourceName);
	    datasource.attachFile(f, partnumber, ecoNumber, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}

  public void attachFile(String datasourceName, String location, String filename, String partnumber, Authentication id) throws RemoteException {
    try {
      File f = new File (findLocalPath(location, filename));
	    AgileSource datasource = lookup(datasourceName);
	    datasource.attachFile(f, partnumber, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  
  public void attachURL(String datasourceName, URL url, String description, String partnumber, String ecoNumber, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.attachURL(url, description, partnumber, ecoNumber, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  
  public void attachURL(String datasourceName, URL url, String description, String partnumber, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.attachURL(url, description, partnumber, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  
  public void replaceURL(String datasourceName, URL oldURL, URL newURL, String description, String partnumber, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.replaceURL(oldURL, newURL, description, partnumber, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  
	public void replaceFirstLevelBOM(String datasourceName, Metadata data, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.replaceFirstLevelBOM(data, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  
	public void replaceFirstLevelBOM(String datasourceName, Collection parents, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.replaceFirstLevelBOM(parents, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
	
 	public void structureBill(String datasourceName, Collection dataList, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.structureBill(dataList, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
 	
 	public void structureBill(String datasourceName, Metadata data, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.structureBill(data, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}

  
 	public void redlineStructure(String datasourceName, String ecoNumber, Collection dataList, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.redlineStructure(ecoNumber, dataList, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
 	
 	public void redlineStructure(String datasourceName, String ecoNumber, Metadata data, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.redlineStructure(ecoNumber, data, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
 	
	public void storeBill(String datasourceName, BillOfMaterials bill, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.storeBill(bill, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
	
  public Map detectPendingChanges(String datasourceName, Collection changedItems, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    return datasource.detectPendingChanges(changedItems, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
	
  public void update(String datasourceName, Metadata data, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.update(data, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
	
  public void update(String datasourceName, Metadata data, String ecoNumber, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.update(data, ecoNumber, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
	public void delete(String datasourceName, Metadata m, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.delete(m, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  public void delete(String datasourceName, String partNumber, Authentication id) throws RemoteException {
		try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.delete(partNumber, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  public void rename(String datasourceName, String name, String newName, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.rename(name, newName, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}

  public void replace(String datasourceName, Collection replacementPairs, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.replace(replacementPairs, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  
  public void replace(String datasourceName, String partNumber, String newPartNumber, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.replace(partNumber, newPartNumber, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  public void create(String datasourceName, Collection dataList, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.create(dataList, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
	public void storeBill(String datasourceName, Collection dataList, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.storeBill(dataList, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
	 
  public void update(String datasourceName, Collection dataList, Authentication id) throws RemoteException {
	try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.update(dataList, id);
	  }
    catch (APIException e) {
      AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
     throw new RemoteException(Server.getName(), x); 
    }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
	
  public void update(String datasourceName, Collection dataList, String ecoNumber, Authentication id) throws RemoteException {
	try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.update(dataList, ecoNumber, id);
	  }
    catch (APIException e) {
      AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
     throw new RemoteException(Server.getName(), x); 
    }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  public void delete(String datasourceName, Collection dataList, Authentication id) throws RemoteException {
    try {
	    AgileSource datasource = lookup(datasourceName);
	    datasource.delete(dataList, id);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  
  public ECO findECO(String datasourceName, String number, Authentication id) throws RemoteException {
	  try {
      AgileSource datasource = lookup(datasourceName);
      return datasource.findECO(number,id);
    }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
 
  public ECO createECO(String datasourceName, String ecoClass, String autoNumSource, String workflow, String changeAnalyst, Authentication id) throws RemoteException {
	  try {
      AgileSource datasource = lookup(datasourceName);
      return datasource.createECO(ecoClass, autoNumSource, workflow, changeAnalyst, id);
    }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public ECO createECO(String datasourceName, Authentication id) throws RemoteException {
	  try {
      AgileSource datasource = lookup(datasourceName);
      return datasource.createECO(id);
    }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void orderRevUpdate(String datasourceName, String ecoNumber, Collection changedItems, Authentication id) throws RemoteException {
	  try {
      AgileSource datasource = lookup(datasourceName);
      datasource.orderRevUpdate(ecoNumber, changedItems, id);
    }
    catch (APIException e) {
      AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
      throw new RemoteException(Server.getName(), x); 
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void orderRelease(String datasourceName, String ecoNumber, Collection changedItems, String lifeCyclePhase, LifeCycleOrder lco, Authentication id) throws RemoteException {
	  try {
      AgileSource datasource = lookup(datasourceName);
      datasource.orderRelease(ecoNumber, changedItems, lifeCyclePhase, lco, id);
    }
    catch (APIException e) {
      AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
      throw new RemoteException(Server.getName(), x); 
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  

	public ECO orderInitialRelease(String datasourceName, String ecoNumber, Collection affectedItems, Authentication id) throws RemoteException {
	  try {
      AgileSource datasource = lookup(datasourceName);
      return datasource.orderInitialRelease(ecoNumber, affectedItems, id);
    }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public ECO moveToNextStatus(String datasourceName, String ecoNumber, Authentication id) throws RemoteException{
	  try {
      AgileSource datasource = lookup(datasourceName);
      return datasource.moveToNextStatus(ecoNumber, id);
    }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public ECO addChange(String datasourceName, String ecoNumber, AffectedItem changedItem, Authentication id) throws RemoteException {
	  try {
      AgileSource datasource = lookup(datasourceName);
      return datasource.addChange(ecoNumber, changedItem, id);
    }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public Collection findECOs(String datasourceName, String nameCriteria, String descriptionCriteria, String statusCriteria, String workflowCriteria, Authentication id) throws RemoteException {
    try {
      AgileSource datasource = lookup(datasourceName);
      return datasource.findECOs(nameCriteria, descriptionCriteria, statusCriteria, workflowCriteria, id);
    }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public Collection findItems(String datasourceName, String nameCriteria, Authentication id) throws RemoteException {
    try {
      AgileSource datasource = lookup(datasourceName);
      return datasource.findItems(nameCriteria, id);
    }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public Map establishNewPartNumbers(String datasourceName, Map proposedPartNumbers, String agileClass, String autoNumberSourceName) throws RemoteException {  
	  try {
	      AgileSource datasource = lookup(datasourceName);
	      return datasource.establishNewPartNumbers(proposedPartNumbers, agileClass, autoNumberSourceName);
	    }
	  catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  
  public String generateNextPartNumber(String datasourceName, String agileClassName, String autoNumberSourceName) throws RemoteException {
	  try {
	      AgileSource datasource = lookup(datasourceName);
	      return datasource.generateNextPartNumber(agileClassName, autoNumberSourceName);
	    }
	  catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public Collection listAttributes(String datasourceName, String agileClassName) throws RemoteException {
    try {
      AgileSource datasource = lookup(datasourceName);
      return datasource.listAttributes(agileClassName);
    }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection refreshAttributes(String datasourceName, String agileClassName) throws RemoteException {
	  try {
	    AgileSource datasource = lookup(datasourceName);
	    return datasource.refreshAttributes(agileClassName);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  
  public Collection listClasses(String datasourceName) throws RemoteException {
	  try {
	    AgileSource datasource = lookup(datasourceName);
	    return datasource.listClasses();
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x); 
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}

  
  public Collection purgeItems(String datasourceName, String baseClassType, String subClassType) throws RemoteException {
  try {
	    AgileSource datasource = lookup(datasourceName);
	    return datasource.purgeType(baseClassType, subClassType);
	  }
    catch (APIException e) {
	    AgileException x = new AgileException(e.getErrorCode().toString(), e.getMessage());
	    throw new RemoteException(Server.getName(), x);
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}

  
/*
  public Collection getAttributes(String datasourceName, Authentication id) throws RemoteException {
    try {
      AgileSource datasource = lookup(datasourceName);
      return datasource.getAttributes(id);
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public Collection storeCADDocuments(String datasourceName, Collection documents, Authentication id) throws RemoteException {
    try {
      AgileSource datasource = lookup(datasourceName);
      return datasource.storeCADDocuments(documents, id);
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public void store(String datasourceName, BillOfMaterials bill, Authentication id) throws RemoteException {
    try {
      AgileSource datasource = lookup(datasourceName);
      datasource.store(bill, id);
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void delete(String datasourceName, BillOfMaterials bill, Authentication id) throws RemoteException {
      try {
        AgileSource datasource = lookup(datasourceName);
        datasource.delete(bill, id);
      }
      catch (Exception e) { throw new RemoteException(Server.getName(), e); }
    }
    
  public void delete(String datasourceName, String partNumber, Authentication id) throws RemoteException {
      try {
        AgileSource datasource = lookup(datasourceName);
        datasource.delete(partNumber, id);
      }
      catch (Exception e) { throw new RemoteException(Server.getName(), e); }
    }
*/    
  
  private static String findLocalPath(String location) { return getLocalDirPath() + Names.PATH_SEPARATOR + location; }    
  private static String findLocalPath(String location, String filename) { return getLocalDirPath()+ Names.PATH_SEPARATOR + location + Names.PATH_SEPARATOR + filename; }      
  private static String getLocalDirPath() {return Properties.get(Names.LOCAL_DIR); }
  
  public void ejbCreate () { try {Configurator.load();} catch (Exception e) { e.printStackTrace(); } }
  public void ejbRemove() {}
  public void ejbPassivate() {}
  public void ejbActivate() {}
  public void setSessionContext(SessionContext ctx) {}
}
