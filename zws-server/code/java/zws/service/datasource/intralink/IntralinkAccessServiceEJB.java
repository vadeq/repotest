package zws.service.datasource.intralink; /*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.application.*;
import zws.report.ReportBase;
import zws.bill.intralink.BillOfMaterials;
import zws.data.Metadata;
import zws.data.workspace.Workspace;
import zws.datasource.Datasource;
import zws.datasource.intralink.IntralinkSource;
import zws.application.Properties;
import zws.exception.NameNotFound;
import zws.folder.IntralinkFolder;
import zws.origin.Origin;
import zws.security.Authentication;
import zws.service.datasource.DatasourceSvc;

import java.util.*;
import java.net.URL;
import java.rmi.RemoteException;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class IntralinkAccessServiceEJB implements SessionBean, IntralinkAccessService{
    
  public Collection listIntralinkRepositories() throws RemoteException {
    Collection repositories = new Vector();
    Collection c = DatasourceSvc.findAll();
    if (null==c) return repositories;
    Iterator i = c.iterator();
    Datasource d;
    while (i.hasNext()) {
      d = (Datasource)i.next();
      if (d.getType().startsWith(Origin.FROM_ILINK)) repositories.add(d.getName());
    }
    return repositories;
  }
  
  public void stopAllEventListeners() {      
	  try {
	    Collection c = listIntralinkRepositories();
	    if (null==c) return;
	    Iterator i = c.iterator();
	    String datasource;
	    while(i.hasNext()) {
	      datasource = (String)i.next();
	      stopEventListener(datasource);
	    }
	  }
	  catch(Exception e) { e.printStackTrace(); }
	}
  
  public void pauseEventListener(String datasourceName) throws RemoteException {
	  try {
	    IntralinkSource datasource = lookup(datasourceName);
	    datasource.pauseEventListener();
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  public void resumeEventListener(String datasourceName) throws RemoteException {
	  try {
	    IntralinkSource datasource = lookup(datasourceName);
	    datasource.resumeEventListener();
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  public void stopEventListener(String datasourceName) throws RemoteException {
	  try {
	    IntralinkSource datasource = lookup(datasourceName);
	    datasource.stopEventListener();
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  public void startEventListener(String datasourceName) throws RemoteException {
	  try {
	    IntralinkSource datasource = lookup(datasourceName);
	    datasource.startEventListener();
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  public void ignoreEventListenerEvents(String datasourceName) throws RemoteException {
    try {
	    IntralinkSource datasource = lookup(datasourceName);
	    datasource.ignoreEventListenerEvents();
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  public void fireEventListenerEvents(String datasourceName) throws RemoteException {
  try {
	    IntralinkSource datasource = lookup(datasourceName);
	    datasource.fireEventListenerEvents();
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  
  public String getEventListenerEventFiringState(String datasourceName) throws RemoteException {
	  try {
	    IntralinkSource datasource = lookup(datasourceName);
	    return datasource.getEventListenerEventFiringState();
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  
  public String getEventListenerRunningState(String datasourceName) throws RemoteException {
	  try {
	    IntralinkSource datasource = lookup(datasourceName);
	    return datasource.getEventListenerRunningState();
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  public Collection getEventListenerHistoryLog(String datasourceName) throws RemoteException {
    try {
	    IntralinkSource datasource = lookup(datasourceName);
	    return datasource.getEventListenerHistoryLog();
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  public int getEventListenerHistoryLogDuration(String datasourceName) throws RemoteException {
    try {
	    IntralinkSource datasource = lookup(datasourceName);
	    return datasource.getEventListenerHistoryLogDuration();
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  public void setEventListenerHistoryLogDuration(String datasourceName, int hours) throws RemoteException {
    try {
	    IntralinkSource datasource = lookup(datasourceName);
	    datasource.setEventListenerHistoryLogDuration(hours);
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  public int getEventListenerRunPeriod(String datasourceName) throws RemoteException {
    try {
	    IntralinkSource datasource = lookup(datasourceName);
	    return datasource.getEventListenerRunPeriod();
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  public void setEventListenerRunPeriod(String datasourceName, int seconds) throws RemoteException {
    try {
	    IntralinkSource datasource = lookup(datasourceName);
	    datasource.setEventListenerRunPeriod(seconds);
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  
  public Collection getEventListenerTargetQueueNodes(String datasourceName) throws RemoteException {
    try {
	    IntralinkSource datasource = lookup(datasourceName);
	    return datasource.getEventListenerTargetQueueNodes();
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  public void addEventListenerTargetQueueNode(String datasourceName, String serverNode) throws RemoteException {
    try {
	    IntralinkSource datasource = lookup(datasourceName);
	    datasource.addEventListenerTargetQueueNode(serverNode);
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  public void removeEventListenerTargetQueueNode(String datasourceName, String serverNode) throws RemoteException {
    try {
	    IntralinkSource datasource = lookup(datasourceName);
	    datasource.removeEventListenerTargetQueueNode(serverNode);
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  
  public Collection getAttributes(String datasourceName, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      return datasource.getAttributes(id);
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public Collection listNames(String datasourceName, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      return datasource.listNames(id);
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public Collection getUserDefinedAttributes(String datasourceName, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      return datasource.getUserDefinedAttributes(id);
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public IntralinkFolder getRootFolder(String datasourceName, Authentication id)throws RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      return datasource.getRootFolder(id);
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public void move(String datasourceName, String name, String path, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.move(name, path, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void rename(String datasourceName, String name, String newName, Authentication id) throws RemoteException { 
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.rename(name, newName, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  
  public void trash(String datasourceName, String name, Authentication id) throws RemoteException { 
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.trash(name, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public Metadata find(Origin origin, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(origin.getDatasourceName());
      return datasource.find(origin, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + origin.getDatasourceName(), e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public Metadata findLatest(String datasourceName, String name, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(datasourceName);
      return datasource.findLatest(name, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public BillOfMaterials reportBill(Origin origin, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(origin.getDatasourceName());
      return datasource.reportBill(origin, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + origin.getDatasourceName(), e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public BillOfMaterials reportLatestBill(Origin origin, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(origin.getDatasourceName());
      return datasource.reportLatestBill(origin.getName(), id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + origin.getDatasourceName(), e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  /*
  public BillOfMaterials getBill(Origin origin, boolean asStored) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(origin.getDatasourceName());
      return datasource.getBill(origin, asStored);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + origin.getDatasourceName(), e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public BillOfMaterials getBill(Origin origin, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(origin.getDatasourceName());
      return datasource.getBill(origin, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + origin.getDatasourceName(), e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  */
  
  public String publishToAgile(Collection origins, Authentication id) throws RemoteException {
    if (null==origins || origins.size()==0) return null;
    Origin o = (Origin)origins.toArray()[0];
    try {
      IntralinkSource datasource = lookup(o.getDatasourceName());
      return datasource.publishToAgile(origins, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + o.getDatasourceName(), e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Origin createNewProEModel(String datasourceName, Metadata data, Authentication id) throws RemoteException {
	  if (null==data) return null;
	  try {
	    IntralinkSource datasource = lookup(datasourceName);
	    return datasource.createNewProEModel(data, id);
	  }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  
  public String publishIDFToAgile(Collection origins, Authentication id) throws RemoteException {
    if (null==origins || origins.size()==0) return null;
    Origin o = (Origin)origins.toArray()[0];
    try {
      IntralinkSource datasource = lookup(o.getDatasourceName());
      return datasource.publishIDFToAgile(origins, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + o.getDatasourceName(), e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
    
  public Collection getQueuedSnapshots(String datasourceName) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      return datasource.getQueuedSnapshots();
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void snapshotImage(Origin origin, String outputType, String targetDatasource, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(origin.getDatasourceName());
      datasource.snapshotImage(origin, outputType, targetDatasource, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + origin.getDatasourceName(), e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
    
  /*
  public Metadata snapshotImage(Origin origin, String imageNamingConvention, String stampNamingConvention, boolean deleteOldImage, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(origin.getDatasourceName());
      datasource.snapshotImage(origin, imageNamingConvention, stampNamingConvention, deleteOldImage, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + origin.getDatasourceName(), e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
    */
  
  public void lock(String datasourceName, String name, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.lock(name, id);
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public void lock(String datasourceName, Collection names, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.lock(names, id);
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public void unlock(String datasourceName, String name, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.unlock(name, id);
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public void unlock(String datasourceName, Collection names, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.unlock(names, id);
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void lock(String datasourceName, String branch, String name, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.lock(branch, name, id);
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public void unlock(String datasourceName, String branch, String name, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.unlock(branch, name, id);
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  
  public void deleteFromRepository(Origin origin, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(origin.getDatasourceName());
      datasource.deleteFromRepository(origin.getName(), id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + origin.getDatasourceName(), e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public void deleteVersionFromRepository(Origin origin, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(origin.getDatasourceName());
      datasource.deleteVersionFromRepository(origin, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + origin.getDatasourceName(), e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public void personalRemoveFromWorkspace(String datasourceName, String workspaceName, String name, Authentication id) throws RemoteException {
	  try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.personalRemoveFromWorkspace(workspaceName, name, id);
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public void removeFromWorkspace(String datasourceName, String workspaceName, String name, Authentication id) throws RemoteException {
	  try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.removeFromWorkspace(workspaceName, name, id);
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  
  public void personalSynchronizeWithCommonSpace(String datasourceName, String workspaceName, String name, Authentication id) throws RemoteException {
	  try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.personalSynchronizeWithCommonSpace(workspaceName, name, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + name, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
 
  public void synchronizeWithCommonSpace(String datasourceName, String workspaceName, String name, Authentication id) throws RemoteException {
	  try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.synchronizeWithCommonSpace(workspaceName, name, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + name, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }


  public boolean contains(String datasourceName, String name, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
	    return datasource.contains(name, id);
	  }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  
  public void personalCheckoutLatest(String datasourceName, String workspaceName, String name, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.personalCheckoutLatest(workspaceName, name, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void checkoutLatest(String datasourceName, String workspaceName, String name, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.checkoutLatest(workspaceName, name, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void personalCheckout(Origin origin, String workspace, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(origin.getDatasourceName());
      datasource.personalCheckout(origin, workspace, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + origin.getDatasourceName(), e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  
  public void checkout(Origin origin, String workspace, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(origin.getDatasourceName());
      datasource.checkout(origin, workspace, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + origin.getDatasourceName(), e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void promote(Origin origin, String promotionLevel, Authentication id) throws RemoteException {
	  try {//+++ add required dependencies
	    IntralinkSource datasource = lookup(origin.getDatasourceName());
	    datasource.promote(origin, promotionLevel, id); 
	  }
	  catch (NameNotFound e) { throw new RemoteException("Name not found: " + origin.getDatasourceName(), e); }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
    
  public void promote(Origin origin, String promotionLevel, String dependencies, String configuration, Authentication id) throws RemoteException {
	  try {//+++ add required dependencies
	    IntralinkSource datasource = lookup(origin.getDatasourceName());
	    datasource.promote(origin, promotionLevel, dependencies, configuration, id); 
	  }
	  catch (NameNotFound e) { throw new RemoteException("Name not found: " + origin.getDatasourceName(), e); }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
    
  
  public void promoteInstance(Origin genericOrigin, String instanceName, String promotionLevel, Authentication id) throws RemoteException {
	  try {//+++ add required dependencies
	    IntralinkSource datasource = lookup(genericOrigin.getDatasourceName());
	    datasource.promoteInstance(genericOrigin, instanceName, promotionLevel, id); 
	  }
	  catch (NameNotFound e) { throw new RemoteException("Name not found: " + genericOrigin.getDatasourceName(), e); }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
    
  public void promoteInstance(Origin genericOrigin, String instanceName, String promotionLevel, String dependencies, String configuration, Authentication id) throws RemoteException {
	  try {//+++ add required dependencies
	    IntralinkSource datasource = lookup(genericOrigin.getDatasourceName());
	    datasource.promoteInstance(genericOrigin, instanceName, promotionLevel, dependencies, configuration, id); 
	  }
	  catch (NameNotFound e) { throw new RemoteException("Name not found: " + genericOrigin.getDatasourceName(), e); }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
    
  public void demote(Origin origin, String demotionLevel, Authentication id) throws RemoteException {
	  try {//+++ add required dependencies
	    IntralinkSource datasource = lookup(origin.getDatasourceName());
	    datasource.demote(origin, demotionLevel, id); 
	  }
	  catch (NameNotFound e) { throw new RemoteException("Name not found: " + origin.getDatasourceName(), e); }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
    
  public void demote(Origin origin, String demotionLevel, String dependencies, String configuration, Authentication id) throws RemoteException {
	  try {//+++ add required dependencies
	    IntralinkSource datasource = lookup(origin.getDatasourceName());
	    datasource.demote(origin, demotionLevel, dependencies, configuration, id); 
	  }
	  catch (NameNotFound e) { throw new RemoteException("Name not found: " + origin.getDatasourceName(), e); }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
  
  public void demoteInstance(Origin genericOrigin, String instanceName, String demotionLevel, Authentication id) throws RemoteException {
	  try {//+++ add required dependencies
	    IntralinkSource datasource = lookup(genericOrigin.getDatasourceName());
	    datasource.demoteInstance(genericOrigin, instanceName, demotionLevel, id); 
	  }
	  catch (NameNotFound e) { throw new RemoteException("Name not found: " + genericOrigin.getDatasourceName(), e); }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
    
  public void demoteInstance(Origin genericOrigin, String instanceName, String demotionLevel, String dependencies, String configuration, Authentication id) throws RemoteException {
	  try {//+++ add required dependencies
	    IntralinkSource datasource = lookup(genericOrigin.getDatasourceName());
	    datasource.demoteInstance(genericOrigin, instanceName, demotionLevel, dependencies, configuration, id); 
	  }
	  catch (NameNotFound e) { throw new RemoteException("Name not found: " + genericOrigin.getDatasourceName(), e); }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}
    
  public void modelCheck(Origin origin, Authentication id) throws RemoteException {
    try {//+++ add required dependencies
      IntralinkSource datasource = lookup(origin.getDatasourceName());
      datasource.modelCheck(origin, id); 
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + origin.getDatasourceName(), e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

	public void unsetLifeCycleAttributes(Collection metadataList, String attribute, Authentication id) throws RemoteException{
    if (null==metadataList || metadataList.isEmpty()) return;
    Origin o = ((Metadata)metadataList.iterator().next()).getOrigin();
    try {
      IntralinkSource datasource = lookup(o.getDatasourceName());
      datasource.unsetLifeCycleAttributes(metadataList, attribute, id);    
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + o.getDatasourceName(), e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }


	public void setLifeCycleAttributes(Collection metadataList, String metadataField, String attribute, Authentication id) throws RemoteException{
    if (null==metadataList || metadataList.isEmpty()) return;
    Origin o = ((Metadata)metadataList.iterator().next()).getOrigin();
    try {
      IntralinkSource datasource = lookup(o.getDatasourceName());
      datasource.setLifeCycleAttributes(metadataList, metadataField, attribute, id);    
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + o.getDatasourceName(), e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public void unsetLifeCycleAttribute(Origin o, String attribute, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(o.getDatasourceName());
      datasource.unsetLifeCycleAttribute(o, attribute, id);    
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + o.getDatasourceName(), e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  
  public void setLifeCycleAttribute(Origin o, String attribute, String value, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(o.getDatasourceName());
      datasource.setLifeCycleAttribute(o, attribute, value, id);    
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + o.getDatasourceName(), e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void personalSetAttribute(String datasourceName, String workspace, String name, String attribute, String value, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.personalSetAttribute(workspace, name, attribute, value, id);    
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public void personalSetAttributes(String datasourceName, String workspace, String name, Map attributes, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.personalSetAttributes(workspace, name, attributes, id);    
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void setAttribute(String datasourceName, String workspace, String name, String attribute, String value, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.setAttribute(workspace, name, attribute, value, id);    
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public void setAttributes(String datasourceName, String workspace, String name, Map attributes, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.setAttributes(workspace, name, attributes, id);    
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
/*
  public void setAttributeForAll(String datasourceName, String workspace, String attribute, String value, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.setAttributeForAll(workspace, attribute, value, id);    
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public void setAttributesForAll(String datasourceName, String workspace, Map attributes, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.setAttributesForAll(workspace, attributes, id);    
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
*/
  
  public void personalLink(String datasourceName, String workspace, String parent, String child, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.personalLink(workspace, parent, child, id);     
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void link(String datasourceName, String workspace, String parent, String child, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.link(workspace, parent, child, id);     
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
   
 public void personalCheckin(String datasourceName, String workspace, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.personalCheckin(workspace, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
 
 public void personalImportToWorkspace(String datasourceName, String workspace, String objectName, URL source, Authentication id) throws RemoteException {
   try {
     IntralinkSource datasource = lookup(datasourceName);
     datasource.personalImportToWorkspace(workspace, objectName, source, id);
   }
   catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
   catch (Exception e) { throw new RemoteException(Server.getName(), e); }
 }
 
 public void importToWorkspace(String datasourceName, String workspace, String objectName, URL source, Authentication id) throws RemoteException {
   try {
     IntralinkSource datasource = lookup(datasourceName);
     datasource.importToWorkspace(workspace, objectName, source, id);
   }
   catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
   catch (Exception e) { throw new RemoteException(Server.getName(), e); }
 }
 
  public void personalImportToWorkspace(String datasourceName, String workspace, String location, String filename, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.personalImportToWorkspace(workspace, findLocalPath(location, filename), id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public Collection personalListWorkspaces(String datasourceName, Authentication id) throws NameNotFound, RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      return datasource.personalListWorkspaces(id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public Workspace personalReportWorkspaceStatus(String datasourceName, String workspaceName, Authentication id) throws NameNotFound, RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      return datasource.personalReportWorkspaceStatus(workspaceName, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public Collection personalReportAllWorkspaceStatus(String datasourceName, Authentication id) throws NameNotFound, RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      return datasource.personalReportAllWorkspaceStatus(id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public Collection personalListWorkspaceContents(String datasourceName, String workspace, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      return datasource.personalListWorkspaceContents(workspace, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public void checkin(String datasourceName, String workspace, Authentication id) throws RemoteException{
   try {
     IntralinkSource datasource = lookup(datasourceName);
     datasource.checkin(workspace, id);
   }
   catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
   catch (Exception e) { throw new RemoteException(Server.getName(), e); }
 }
 
  public void importToWorkspace(String datasourceName, String workspace, String location, String filename, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.importToWorkspace(workspace, findLocalPath(location, filename), id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public Collection listWorkspaces(String datasourceName, String workspace, Authentication id) throws NameNotFound, RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      return datasource.listWorkspaces(workspace, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public Workspace reportWorkspaceStatus(String datasourceName, String workspaceName, Authentication id) throws NameNotFound, RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      return datasource.reportWorkspaceStatus(workspaceName, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public Collection reportAllWorkspaceStatus(String datasourceName, String workspace, Authentication id) throws NameNotFound, RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      return datasource.reportAllWorkspaceStatus(workspace, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public Collection listWorkspaceContents(String datasourceName, String workspace, boolean showInstances, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      return datasource.listWorkspaceContents(workspace, showInstances, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public String convertToIGES(Origin origin, String workspace, String location, Authentication id) throws RemoteException{
	  try {
	    IntralinkSource datasource = lookup(origin.getDatasourceName());
	    return datasource.convertToIGES(origin, workspace, findLocalPath(location), id);
	  }
	  catch (NameNotFound e) { throw new RemoteException("Name not found: " + origin.getDatasourceName(), e); }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}


  public String convertToSTEP(Origin origin, String workspace, String location, Authentication id) throws RemoteException{
	  try {
	    IntralinkSource datasource = lookup(origin.getDatasourceName());
	    return datasource.convertToSTEP(origin, workspace, findLocalPath(location), id);
	  }
	  catch (NameNotFound e) { throw new RemoteException("Name not found: " + origin.getDatasourceName(), e); }
	  catch (Exception e) { throw new RemoteException(Server.getName(), e); }
	}

  public Collection personalExport(String datasourceName, String workspace, String location, String name, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(datasourceName);
      return datasource.personalExport(workspace, findLocalPath(location), name, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public Collection personalExport(String datasourceName, String workspace, String location, String name, String dependencies, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(datasourceName);
      return datasource.personalExport(workspace, findLocalPath(location), name, dependencies, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public void personalCreateWorkspace(String datasourceName, String workspace, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.personalCreateWorkspace(workspace, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void personalDestroyWorkspace(String datasourceName, String workspace, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.personalDestroyWorkspace(workspace, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public Collection export(String datasourceName, String workspace, String location, String name, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(datasourceName);
      return datasource.export(workspace, findLocalPath(location), name, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public Collection export(String datasourceName, String workspace, String location, String name, String dependencies, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(datasourceName);
      return datasource.export(workspace, findLocalPath(location), name, dependencies, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public void createWorkspace(String datasourceName, String workspace, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.createWorkspace(workspace, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void destroyWorkspace(String datasourceName, String workspace, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.destroyWorkspace(workspace, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public Collection findRTPForms(String datasourceName, String datedAfter, String promoteTo, Authentication id) throws RemoteException{
    try {
      IntralinkSource datasource = lookup(datasourceName);
      return datasource.findRTPForms(datedAfter, promoteTo, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public void createBaseline(String datasourceName, String name, String folderLocation, String releaseLevel, Collection components, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.createBaseline(name, folderLocation, releaseLevel, components, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public void deleteBaseline(String datasourceName, String name, Authentication id) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      datasource.deleteBaseline(name, id);
    }
    catch (NameNotFound e) { throw new RemoteException("Name not found: " + datasourceName, e); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public String objectName(String datasourceName, String name) throws RemoteException {
    try {
      IntralinkSource datasource = lookup(datasourceName);
      return datasource.objectName(name);
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  private IntralinkSource lookup(String datasourceName) throws NameNotFound{ return (IntralinkSource) DatasourceSvc.find(datasourceName); }
  private static String findLocalPath(String location) { 
    String dir = Properties.get(Names.LOCAL_DIR) + Names.PATH_SEPARATOR + location;
    return dir;
  }    
  private static String findLocalPath(String location, String filename) {
    String dir = Properties.get(Names.LOCAL_DIR) + Names.PATH_SEPARATOR + location + Names.PATH_SEPARATOR + filename;
    return dir;
  }    
  
  public void ejbCreate () { try { Configurator.load(); } catch (Exception e) { e.printStackTrace(); }  }
  public void ejbRemove() {}
  public void ejbPassivate() {}
  public void ejbActivate() {}
  public void setSessionContext(SessionContext ctx) {}
  
  private static String LOCAL_DIR = Properties.get(Names.LOCAL_DIR);
}



/*      
public void ejbRemove() {
        String s;
        Datasource d;
        try {
          Collection c = listIntralinkRepositories();
          if (null==c) return;
          Iterator i = c.iterator();
          while (i.hasNext()) {
            s = (String)i.next();
            try { 
              d = lookup(s);
              d.inactivate();
            }
            catch(Exception ignore) {}
          }
        }
        catch (Exception e) { e.printStackTrace(); }
      }
     */
