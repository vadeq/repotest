package zws.service.datasource.agile; /*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.bill.intralink.BillOfMaterials;
import zws.data.Metadata;
import zws.data.eco.AffectedItem;
import zws.data.eco.ECO;
import zws.lifecycle.LifeCycleOrder;
import zws.security.Authentication;

import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Map;

public interface AgileAccessService extends Serializable {
  /*
  public Collection getAttributes(String datasourceName, Authentication id) throws RemoteException;
  public Collection storeCADDocuments(String datasourceName, Collection documents, Authentication id) throws RemoteException;
  public void store(String datasourceName, BillOfMaterials bill, Authentication id) throws RemoteException;
  public void delete(String datasourceName, String partNumber, Authentication id) throws RemoteException;
  public void delete(String datasourceName, BillOfMaterials bill, Authentication id) throws RemoteException;
  */
  /*
  public void loginUser(String datasourceName, Authentication id) throws RemoteException;
  public void logoutUser(String datasourceName, Authentication id) throws RemoteException;
  */
  public void activate(String datasourceName) throws RemoteException;
  public void refreshConfiguration(String datasourceName) throws RemoteException;
  public Metadata find(String datasourceName, String partnumber, Authentication id) throws RemoteException;
  public boolean contains(String datasourceName, String name, Authentication id) throws RemoteException;
  public boolean hasSubComponent(String datasourceName, String parent, String subcomponent, Authentication id) throws RemoteException;
  public Metadata renumber(String datasourceName, String partnumber, String newNumber, Authentication id) throws RemoteException;

  public void create(String datasourceName, Metadata data, Authentication id) throws RemoteException;
  public void attachFile(String datasourceName, String location, String filename, String partnumber, String ecoNumber, Authentication id) throws RemoteException;
  public void attachFile(String datasourceName, String location, String filename, String partnumber, Authentication id) throws RemoteException;
  public void attachURL(String datasourceName, URL url, String description, String partnumber, String ecoNumber, Authentication id) throws RemoteException;
  public void attachURL(String datasourceName, URL url, String description, String partnumber, Authentication id) throws RemoteException;
  public void replaceURL(String datasourceName, URL oldURL, URL newURL, String description, String partnumber, Authentication id) throws RemoteException;
	public void storeBill(String datasourceName, BillOfMaterials bill, Authentication id) throws RemoteException;
	public void update(String datasourceName, Metadata data, Authentication id) throws RemoteException;
	public void update(String datasourceName, Metadata data, String ecoNumber, Authentication id) throws RemoteException;
	public void delete(String datasourceName, Metadata m, Authentication id) throws RemoteException;
  public void delete(String datasourceName, String partNumber, Authentication id) throws RemoteException;
  public void rename(String datasourceName, String name, String newName, Authentication id) throws RemoteException;
  public void replace(String datasourceName, String partNumber, String newPartNumber, Authentication id) throws RemoteException;
  public void replace(String datasourceName, Collection replacementPairs, Authentication id) throws RemoteException;

  public void createItems(String datasourceName, Collection metadataList, Authentication id) throws RemoteException;
  public void createCADParts(String datasourceName, Collection metadataList, boolean createCADDocuments, Authentication id) throws RemoteException;
  public void create(String datasourceName, Collection dataList, Authentication id) throws RemoteException;
	public void replaceFirstLevelBOM(String datasourceName, Metadata data, Authentication id) throws RemoteException;
	public void replaceFirstLevelBOM(String datasourceName, Collection parents, Authentication id) throws RemoteException;
  public void structureBill(String datasourceName, Collection dataList, Authentication id) throws RemoteException;
 	public void structureBill(String datasourceName, Metadata data, Authentication id) throws RemoteException;
 	public void redlineStructure(String datasourceName, String ecoNumber, Collection dataList, Authentication id) throws RemoteException;
 	public void redlineStructure(String datasourceName, String ecoNumber, Metadata data, Authentication id) throws RemoteException;
	public void storeBill(String datasourceName, Collection dataList, Authentication id) throws RemoteException;
  public void update(String datasourceName, Collection dataList, Authentication id) throws RemoteException;
  public void update(String datasourceName, Collection dataList, String ecoNumber, Authentication id) throws RemoteException;
  public void delete(String datasourceName, Collection dataList, Authentication id) throws RemoteException;
  public Map detectPendingChanges(String datasourceName, Collection changedItems, Authentication id) throws RemoteException;

  public ECO createECO(String datasourceName, String ecoClass, String autoNumSource, String workflow, String changeAnalyst, Authentication id) throws RemoteException;
  public ECO createECO(String datasourceName, Authentication id) throws RemoteException;
  public ECO findECO(String datasourceName, String number, Authentication id) throws RemoteException;
  public void orderRelease(String datasourceName, String ecoNumber, Collection changedItems, String lifeCyclePhase, LifeCycleOrder lco, Authentication id) throws RemoteException;
  public void orderRevUpdate(String datasourceName, String ecoNumber, Collection changedItems, Authentication id) throws RemoteException;
  public ECO orderInitialRelease(String datasourceName, String ecoNumber, Collection changedItems, Authentication id) throws RemoteException;
  public ECO moveToNextStatus(String datasourceName, String ecoNumber, Authentication id) throws RemoteException;
	public ECO addChange(String datasourceName, String ecoNumber, AffectedItem changedItem, Authentication id) throws RemoteException;
  public Collection findECOs(String datasourceName, String nameCriteria, String descriptionCriteria, String statusCriteria, String workflowCriteria, Authentication id) throws RemoteException;
  public Collection findItems(String datasourceName, String nameCriteria, Authentication id) throws RemoteException;
  
  public Collection purgeItems(String datasourceName, String baseClassType, String subClassType) throws RemoteException;
  public Map establishNewPartNumbers(String datasourceName, Map proposedPartNumbers, String agileClass, String autoNumberSourceName) throws RemoteException;  
  public String generateNextPartNumber(String datasourceName, String agileClassName, String autoNumberSourceName) throws RemoteException;
  public Collection listAttributes(String datasourceName, String agileClassName) throws RemoteException;
  public Collection listClasses(String datasourceName) throws RemoteException;
  public Collection refreshAttributes(String datasourceName, String agileClassName) throws RemoteException;
}
