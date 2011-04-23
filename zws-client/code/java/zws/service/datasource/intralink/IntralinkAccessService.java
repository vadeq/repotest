package zws.service.datasource.intralink; /*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.bill.intralink.BillOfMaterials;
import zws.data.Metadata;
import zws.data.workspace.Workspace;
import zws.exception.NameNotFound;
import zws.folder.IntralinkFolder;
import zws.origin.Origin;
import zws.security.Authentication;

import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Map;

public interface IntralinkAccessService extends Serializable{
  public Collection listIntralinkRepositories() throws RemoteException;
  public String getEventListenerRunningState(String datasourceName)throws RemoteException;
  public void startEventListener(String datasourceName) throws RemoteException;
  public void stopEventListener(String datasourceName)throws RemoteException;
  public void pauseEventListener(String datasourceName) throws RemoteException;
  public void resumeEventListener(String datasourceName)throws RemoteException ;
  public Collection getEventListenerHistoryLog(String datasourceName) throws RemoteException;
  public int getEventListenerHistoryLogDuration(String datasourceName) throws RemoteException;
  public void setEventListenerHistoryLogDuration(String datasourceName, int hours) throws RemoteException;
  public int getEventListenerRunPeriod(String datasourceName) throws RemoteException;
  public void setEventListenerRunPeriod(String datasourceName, int seconds) throws RemoteException;
  public String getEventListenerEventFiringState(String datasourceName) throws RemoteException;
  public void ignoreEventListenerEvents(String datasourceName) throws RemoteException;
  public void fireEventListenerEvents(String datasourceName) throws RemoteException;

  public Collection getEventListenerTargetQueueNodes(String datasourceName) throws RemoteException;
  public void addEventListenerTargetQueueNode(String datasourceName, String serverNode) throws RemoteException;
  public void removeEventListenerTargetQueueNode(String datasourceName, String serverNode) throws RemoteException;
  
  public Collection getAttributes(String datasourceName, Authentication id) throws RemoteException;
  public Collection getUserDefinedAttributes(String datasourceName, Authentication id) throws RemoteException;
  public IntralinkFolder getRootFolder(String datasourceName, Authentication id)throws RemoteException;
  public Collection listNames(String datasourceName, Authentication id) throws RemoteException;
  public Metadata find(Origin origin, Authentication id) throws RemoteException;
  public Metadata findLatest(String datasourceName, String name, Authentication id) throws RemoteException;
  public void move(String datasourceName, String name, String path, Authentication id) throws RemoteException;
  public void rename(String datasourceName, String name, String newName, Authentication id) throws RemoteException; 
  public void trash(String datasourceName, String name, Authentication id) throws RemoteException; 
  public BillOfMaterials reportBill(Origin origin, Authentication id) throws RemoteException;
  public BillOfMaterials reportLatestBill(Origin origin, Authentication id) throws RemoteException;
//  public BillOfMaterials getBill(Origin origin, boolean asStored) throws RemoteException;
//  public BillOfMaterials getBill(Origin origin, Authentication id) throws RemoteException;
  public String publishToAgile(Collection origins, Authentication id) throws RemoteException;
  public String publishIDFToAgile(Collection origins, Authentication id) throws RemoteException;
  public Origin createNewProEModel(String datasourceName, Metadata data, Authentication id) throws RemoteException;
  public Collection getQueuedSnapshots(String datasourceName) throws RemoteException;
  public void snapshotImage(Origin o, String outputType, String targetDatasource, Authentication id) throws RemoteException;
  //public Metadata snapshotImage(Origin o, String imageNamingConvention, String stampNamingConvention, boolean deleteOldImage, Authentication id) throws RemoteException;
  public void deleteFromRepository(Origin origin, Authentication id) throws RemoteException;
  public void deleteVersionFromRepository(Origin origin, Authentication id) throws RemoteException;
  public void lock(String datasourceName, String name, Authentication id) throws RemoteException;
  public void unlock(String datasourceName, String name, Authentication id) throws RemoteException;
  public void lock(String datasourceName, Collection names, Authentication id) throws RemoteException;
  public void unlock(String datasourceName, Collection names, Authentication id) throws RemoteException;
  public void lock(String datasourceName, String branch, String name, Authentication id) throws RemoteException;
  public void unlock(String datasourceName, String branch, String name, Authentication id) throws RemoteException;

  public boolean contains(String datasourceName, String name, Authentication id) throws RemoteException;
  public void personalCheckoutLatest(String datasourceName, String workspaceName, String name, Authentication id) throws RemoteException;
  public void checkoutLatest(String datasourceName, String workspaceName, String name, Authentication id) throws RemoteException;
  public void checkout(Origin origin, String workspace, Authentication id) throws RemoteException;
  public void checkin(String datasourceName, String workspace, Authentication id) throws RemoteException;
  
  public void personalCheckout(Origin origin, String workspace, Authentication id) throws RemoteException;
  public void personalCheckin(String datasourceName, String workspace, Authentication id) throws RemoteException;
  public void personalSetAttribute(String datasourceName, String workspace, String name, String attribute, String value, Authentication id) throws RemoteException;
  public void personalSetAttributes(String datasourceName, String workspace, String name, Map attributes, Authentication id) throws RemoteException;
  public void personalSynchronizeWithCommonSpace(String datasourceName, String workspaceName, String name, Authentication id) throws RemoteException;
  public void personalImportToWorkspace(String datasourceName, String workspace, String objectName, URL source, Authentication id) throws RemoteException;
  public void personalImportToWorkspace(String datasourceName, String workspace, String location, String filename, Authentication id) throws RemoteException;
  public Collection personalExport(String datasourceName, String workspace, String location, String name, Authentication id) throws RemoteException;
  public Collection personalExport(String datasourceName, String workspace, String location, String name, String Dependencies, Authentication id) throws RemoteException;
  public Collection personalListWorkspaces(String datasourceName, Authentication id) throws NameNotFound, RemoteException;
  public Workspace personalReportWorkspaceStatus(String datasourceName, String workspaceName, Authentication id) throws NameNotFound, RemoteException;
  public Collection personalReportAllWorkspaceStatus(String datasourceName, Authentication id) throws NameNotFound, RemoteException;
  public Collection personalListWorkspaceContents(String datasourceName, String workspace, Authentication id) throws RemoteException;
  public void personalCreateWorkspace(String datasourceName, String workspace, Authentication id) throws RemoteException;
  public void personalDestroyWorkspace(String datasourceName, String workspace, Authentication id) throws RemoteException;
  public void personalRemoveFromWorkspace(String datasourceName, String workspaceName, String name, Authentication id) throws RemoteException;
  public void personalLink(String datasourceName, String workspace, String parent, String child, Authentication id) throws RemoteException;

  public void synchronizeWithCommonSpace(String datasourceName, String workspaceName, String name, Authentication id) throws RemoteException;
  public void promote(Origin origin, String promotionLevel, Authentication id) throws RemoteException;  
  public void promote(Origin origin, String promotionLevel, String dependencies, String configuration, Authentication id) throws RemoteException;  
  public void promoteInstance(Origin genericOrigin, String instanceName, String promotionLevel, Authentication id) throws RemoteException;  
  public void promoteInstance(Origin genericOrigin, String instanceName, String promotionLevel, String dependencies, String configuration, Authentication id) throws RemoteException;  
  public void demote(Origin origin, String demotionLevel, Authentication id) throws RemoteException;  
  public void demote(Origin origin, String demotionLevel, String dependencies, String configuration, Authentication id) throws RemoteException;  
  public void demoteInstance(Origin genericOrigin, String instanceName, String demotionLevel, Authentication id) throws RemoteException;  
  public void demoteInstance(Origin genericOrigin, String instanceName, String demotionLevel, String dependencies, String configuration, Authentication id) throws RemoteException;  
  public void modelCheck(Origin origin, Authentication id) throws RemoteException;
  public void setLifeCycleAttribute(Origin o, String attribute, String value, Authentication id) throws RemoteException;
	public void setLifeCycleAttributes(Collection metadataList, String metadataField, String attribute, Authentication id) throws RemoteException;
  public void unsetLifeCycleAttribute(Origin o, String attribute, Authentication id) throws RemoteException;
	public void unsetLifeCycleAttributes(Collection metadataList,String attribute, Authentication id) throws RemoteException;
  public void setAttribute(String datasourceName, String workspace, String name, String attribute, String value, Authentication id) throws RemoteException;
  public void setAttributes(String datasourceName, String workspace, String name, Map attributes, Authentication id) throws RemoteException;
  /*
  public void setAttributeForAll(String datasourceName, String workspace, String attribute, String value, Authentication id) throws RemoteException;
  public void setAttributesForAll(String datasourceName, String workspace, Map attributes, Authentication id) throws RemoteException;
  */
  public void link(String datasourceName, String workspace, String parent, String child, Authentication id) throws RemoteException;
  public void importToWorkspace(String datasourceName, String workspace, String objectName, URL source, Authentication id) throws RemoteException;
  public void importToWorkspace(String datasourceName, String workspace, String location, String filename, Authentication id) throws RemoteException;
  public String convertToIGES(Origin o, String workspace, String location, Authentication id) throws RemoteException;
  public String convertToSTEP(Origin o, String workspace, String location, Authentication id) throws RemoteException;
  public Collection export(String datasourceName, String workspace, String location, String name, Authentication id) throws RemoteException;
  public Collection export(String datasourceName, String workspace, String location, String name, String Dependencies, Authentication id) throws RemoteException;
  public Collection listWorkspaces(String datasourceName, String workspace, Authentication id) throws NameNotFound, RemoteException;
  public Workspace reportWorkspaceStatus(String datasourceName, String workspaceName, Authentication id) throws NameNotFound, RemoteException;
  public Collection reportAllWorkspaceStatus(String datasourceName, String workspace, Authentication id) throws NameNotFound, RemoteException;
  public Collection listWorkspaceContents(String datasourceName, String workspace, boolean showInstances, Authentication id) throws RemoteException;
  public void createWorkspace(String datasourceName, String workspace, Authentication id) throws RemoteException;
  public void destroyWorkspace(String datasourceName, String workspace, Authentication id) throws RemoteException;
  public void removeFromWorkspace(String datasourceName, String workspaceName, String name, Authentication id) throws RemoteException;
  public Collection findRTPForms(String datasourceName, String datedAfter, String promoteTo, Authentication id) throws RemoteException;
  public void createBaseline(String datasourceName, String name, String folderLocation, String releaseLevel, Collection components, Authentication id) throws RemoteException;
  public void deleteBaseline(String datasourceName, String name, Authentication id) throws RemoteException;
  public String objectName(String datasourceName, String name) throws RemoteException;
}
