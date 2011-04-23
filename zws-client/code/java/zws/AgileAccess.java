package zws; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Apr 11, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

import zws.bill.intralink.BillOfMaterials;
import zws.data.Metadata;
import zws.data.eco.AffectedItem;
import zws.data.eco.ECO;
import zws.lifecycle.LifeCycleOrder;
import zws.security.Authentication; 
import zws.service.datasource.agile.EJBLocator;
import zws.service.datasource.agile.AgileAccessService;
import java.util.*;

import java.net.URL;
import java.rmi.RemoteException;

import com.agile.api.APIException;

public class AgileAccess {
  public synchronized static AgileAccess getAccess(String serverName, String datasourceName, Authentication auth) throws Exception {
    {} //System.out.println("finding client for: " + serverName + "." + datasourceName);
    AgileAccess x = new AgileAccess(serverName, datasourceName, auth);
    x.activate();
    {} //System.out.println("got client for " + serverName + "." + datasourceName);
    //x.loginUser();
    return x;
  }

  public String getDomainName() { return domain; }
  public String getServerName() { return server; }
  public String getDatasourceName() { return datasource; }
  
  private AgileAccess(String serverName, String datasourceName, Authentication auth) throws Exception {
    id=auth;
    domain=Server.getDomainName();//paramaeterize at sometime
    server=serverName;
    datasource=datasourceName;
    service = EJBLocator.findService(serverName);
  }    

  public Metadata find(String partnumber) throws RemoteException{
    try {
      return service.find(datasource, partnumber, id);
    }
    catch (RemoteException e) { throw e; }
  }
  
  public boolean contains(String name) throws Exception {
    try {
      return service.contains(datasource, name, id);
    }
    catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
  }
  
  public boolean hasSubComponent(String parent, String subcomponent) throws RemoteException {
    try {
      return service.hasSubComponent(datasource, parent, subcomponent, id);
    }
    catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
  }

  public Metadata renumber(String partnumber, String newNumber) throws RemoteException {
    try {
      return service.renumber(datasource, partnumber, newNumber, id);
    }
    catch (RemoteException e) { throw e; }
  }
  
  
  public void createItems(Collection metadataList) throws Exception {
    try {
	    service.createItems(datasource, metadataList, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
  
  public void createCADParts(Collection metadataList, boolean createCADDocuments) throws Exception {
    try {
	    service.createCADParts(datasource, metadataList, createCADDocuments, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
  
  public void create(Metadata data) throws Exception {
    try {
	    service.create(datasource, data, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
  
  public void attachFile(String location, String filename, String partnumber) throws Exception {
    try {
      service.attachFile(datasource, location, filename, partnumber, id);
    }
    catch (RemoteException e) {
      Throwable x = e;
      if (null!=x.getCause()) x = x.getCause();
      if (x instanceof APIException ) {
       {} //System.out.println(((APIException)x).getRootCause());
      }
      else if (null!=x.getCause()) x = x.getCause();
      if (x instanceof APIException ) {
        {} //System.out.println(((APIException)x).getRootCause());
       }
      else e.printStackTrace();  throw e; 
    }
  }
  
  public void attachFile(String location, String filename, String partnumber, String ecoNumber) throws Exception {
    try {
      service.attachFile(datasource, location, filename, partnumber, ecoNumber, id);
    }
    catch (RemoteException e) {
      Throwable x = e;
      if (null!=x.getCause()) x = x.getCause();
      if (x instanceof APIException ) {
       {} //System.out.println(((APIException)x).getRootCause());
      }
      else if (null!=x.getCause()) x = x.getCause();
      if (x instanceof APIException ) {
        {} //System.out.println(((APIException)x).getRootCause());
       }
      else e.printStackTrace();  throw e; 
    }
  }
  
  public void attachURL(URL url, String description, String partnumber) throws Exception {
    try {
      service.attachURL(datasource, url, description, partnumber, id);
    }
    catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
  }
  
  public void attachURL(URL url, String description, String partnumber, String ecoNumber) throws Exception {
    try {
      service.attachURL(datasource, url, description, partnumber, ecoNumber, id);
    }
    catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
  }
  
  public void replaceURL(URL oldURL, URL newURL, String description, String partnumber) throws RemoteException {
    try {
      service.replaceURL(datasource, oldURL, newURL, description, partnumber, id);
    }
    catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
  }
  
  
  
	public void replaceFirstLevelBOM(Metadata data, Authentication id) throws RemoteException {
	  try {
	 	  service.replaceFirstLevelBOM(datasource, data, id);
	 	}
	 	catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
  
	public void replaceFirstLevelBOM(Collection parents, Authentication id) throws RemoteException {
	  try {
	 	  service.replaceFirstLevelBOM(datasource, parents, id);
	 	}
	 	catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
	
 	public void structureBill(Collection dataList) throws RemoteException {
    try {
	 	  service.structureBill(datasource, dataList, id);
	 	 }
	 	 catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	 }
 	 	
 	public void structureBill(Metadata data) throws RemoteException {
 	  try {
 	 	  service.structureBill(datasource, data, id);
 	 	}
 	 	catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
 	}
  
  
 	public void redlineStructure(String ecoNumber, Collection dataList) throws RemoteException {
    try {
	 	  service.redlineStructure(datasource, ecoNumber, dataList, id);
	 	 }
	 	 catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	 }
 	 	
 	public void redlineS(String ecoNumber, Metadata data) throws RemoteException {
 	  try {
 	 	  service.redlineStructure(datasource, ecoNumber, data, id);
 	 	}
 	 	catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
 	}
  
	public void storeBill(BillOfMaterials bill) throws Exception {
    try {
	    service.storeBill(datasource, bill, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
	
  public void update(Metadata data) throws Exception {
    try {
	    service.update(datasource, data, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
	
  public void update(Metadata data, String ecoNumber) throws Exception {
    try {
	    service.update(datasource, data, ecoNumber, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}

	public void delete(Metadata m) throws Exception {
    try {
	    service.delete(datasource, m, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
  public void delete(String partNumber) throws Exception {
		try {
	    service.delete(datasource, partNumber, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
  public void rename(String name, String newName) throws Exception {
    try {
	    service.rename(datasource, name, newName, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}


  public void replace(Collection replacementPairs) throws Exception {
	try {
	    service.replace(datasource, replacementPairs, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
  
  public void replace(String partNumber, String newPartNumber) throws Exception {
    try {
	    service.replace(datasource, partNumber, newPartNumber, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
  public void create(Collection dataList) throws Exception {
    try {
	    service.create(datasource, dataList, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
	public void storeBill(Collection dataList) throws Exception {
    try {
	    service.storeBill(datasource, dataList, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}

  public void update(Collection dataList) throws Exception {
	  try {
	    service.update(datasource, dataList, id);
	  }
    catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
	
  public void update(Collection dataList, String ecoNumber) throws Exception {
	  try {
	    service.update(datasource, dataList, ecoNumber, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}

  public void delete(Collection dataList) throws Exception {
    try {
	    service.delete(datasource, dataList, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
  
  public ECO findECO(String number) throws RemoteException {
	  try {
		    return service.findECO(datasource, number, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
  public ECO createECO(String ecoClass, String autoNumSource, String workflow, String changeAnalyst) throws RemoteException {
	  try {
		   return service.createECO(datasource, ecoClass, autoNumSource, workflow, changeAnalyst, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
  
  public ECO createECO() throws RemoteException {
	  try {
		  return service.createECO(datasource, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}

  public Collection findECOs(String nameCriteria) throws Exception {
	  return findECOs(nameCriteria, null, null, null);
	}

  public Collection findECOs(String nameCriteria, String descriptionCriteria, String statusCriteria, String workflowCriteria) throws Exception {
	  try {
	    return service.findECOs(datasource, nameCriteria, descriptionCriteria, statusCriteria, workflowCriteria, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}

  public Map detectPendingChanges(Collection changedItems, Authentication id) throws RemoteException {
    try {
      return service.detectPendingChanges(datasource, changedItems, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}


  public void orderRevUpdate(String ecoNumber, Collection changedItems, Authentication id) throws RemoteException {
    try {
      service.orderRevUpdate(datasource, ecoNumber, changedItems, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}

  public void orderRelease(String ecoNumber, Collection changedItems, String lifeCyclePhase, LifeCycleOrder lco) throws RemoteException {
    try {
      service.orderRelease(datasource, ecoNumber, changedItems, lifeCyclePhase, lco, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}

	public ECO orderInitialRelease(String ecoNumber, Collection affectedItems) throws RemoteException {
	  try {
		    return service.orderInitialRelease(datasource, ecoNumber, affectedItems, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}


  public ECO moveToNextStatus(String ecoNumber, Authentication id) throws RemoteException{
	  try {
      return service.moveToNextStatus(datasource, ecoNumber, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
	
	public ECO addChange(String ecoNumber, AffectedItem changedItem) throws Exception {
	  try {
	    return service.addChange(datasource, ecoNumber, changedItem, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
  
  public Collection findItems(String nameCriteria) throws Exception {
	  try {
	    return service.findItems(datasource, nameCriteria, id);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
  
  public Map establishNewPartNumbers(Map proposedPartNumbers, String agileClass, String autoNumberSourceName) throws RemoteException {  
		try {
	    return service.establishNewPartNumbers(datasource, proposedPartNumbers, agileClass, autoNumberSourceName);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}


  public String generateNextPartNumber(String agileClassName) throws Exception { return generateNextPartNumber(agileClassName, null); }
  public String generateNextPartNumber(String agileClassName, String autoNumberSourceName) throws Exception {
		try {
	    return service.generateNextPartNumber(datasource, agileClassName, autoNumberSourceName);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
    
    
  public Collection listAttributes(String agileClassName) throws Exception {
    try {
      return service.listAttributes(datasource, agileClassName);
    }
    catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
  }
  public Collection refreshAttributes(String agileClassName) throws Exception {
    try {
      return service.refreshAttributes(datasource, agileClassName);
    }
    catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
  }
  public Collection listClasses() throws Exception {
	  try {
	    return service.listClasses(datasource);
	  }
	  catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}

/*  
  public Collection getVisibleAttributes() throws Exception {
    try { return service.getAttributes(datasource,id); }
    catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
  }

  public Collection storeCADDocuments(Collection documents) throws Exception {
    try { return service.storeCADDocuments(datasource, documents, id); }
    catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
  }

  public void store(BillOfMaterials bill) throws Exception { 
    try { service.store(datasource, bill, id); }
    catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
  }

  public void delete(BillOfMaterials bill) throws Exception { 
    try { service.delete(datasource, bill, id); }
    catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
  }

  public void delete(String partNumber) throws Exception { 
    try { service.delete(datasource, partNumber, id); }
    catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
  }
*/

  public Collection purgeItems(String baseClassType, String subClassType) throws Exception {
	  try { return service.purgeItems(datasource, baseClassType, subClassType); }
		catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
  
  public void activate() throws RemoteException{
	  try { service.activate(datasource); }
		catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}

  public void refreshConfiguration() throws RemoteException{
	  try { service.activate(datasource); }
		catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}

  /*
  public void loginUser() throws RemoteException{
    Object auth = userAuthentications.get(id.getUsername());
    if (null!=auth) return;
    try {
	    service.loginUser(datasource, id);
		  userAuthentications.put(id.getUsername(), id);
		}
		catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
  }
  
  public void logoutUser() throws RemoteException{
	  Object auth = userAuthentications.get(id.getUsername());
	  if (null==auth) return;
    try {
	    service.logoutUser(datasource, id);
		  userAuthentications.put(id.getUsername(), id);
		}
		catch (RemoteException e) { e.getCause().printStackTrace();  throw e; }
	}
  */
  private Authentication id=null;
  private String domain=null;
  private String server=null;
  private String datasource=null;
  private AgileAccessService service=null;

  private static Map userAuthentications = new HashMap();
}
