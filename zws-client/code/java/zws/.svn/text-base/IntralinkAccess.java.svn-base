package zws;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 10, 2003, 12:07 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.bill.intralink.BillOfMaterials;
import zws.data.Metadata;
import zws.data.workspace.Workspace;
import zws.exception.NameNotFound;
import zws.folder.IntralinkFolder;
import zws.origin.IntralinkOrigin;
import zws.origin.Origin;
import zws.security.Authentication;
import zws.service.datasource.intralink.EJBLocator;
import zws.service.datasource.intralink.IntralinkAccessService;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Map;


//+++ unwrap remote excepton and throw cause.
//+++ make sure EJB always populates RemoteException with the cause;
public class IntralinkAccess extends DatasourceAccess {
  public static String AS_STORED_CONFIGURATION = "as-stored";
  public static String LATEST_CONFIGURATION = "latest";
  public static String NO_DEPENDENCIES = "none";
  public static String ALL_DEPENDENCIES = "all";
  public static String REQUIRED_DEPENDENCIES = "required";

  private static IntralinkAccess datasourceAccess=null;
  private IntralinkAccess() {}
/*
  public static Origin createOrigin(String serverName, String sourceName, String branch, String name, String revision, String version, String createdOn) throws Exception {
    String n = objectName(name);
    long dateCreated=0;    
    try {dateCreated = parseDate(createdOn); }
    catch (Exception e) {}
    String delim = Names.ORIGIN_DELIMITER;
    Origin o = new Origin(serverName, sourceName, Origin.ILINK, dateCreated, branch+delim+n+delim+revision+delim+version);
    o.setName(n);
    return o;
  }

  public static Origin createOrigin(String serverName, String sourceName, Metadata data) throws Exception {
    String branch, name, revision, version, createdOn;
    branch = data.get(BRANCH);
    name = objectName(data.getName());
    revision = data.get(REVISION);
    version = data.get(VERSION);
    createdOn = data.get(CREATED_ON);
    Origin o = createOrigin(serverName, sourceName, branch, name, revision, version, createdOn);
    o.setName(name);
    return o;
  }
*/
  /*
  public static String getBranchFromOrigin( Origin o ) throws InvalidOrigin {
    StringTokenizer tokens = new StringTokenizer(o.getUniqueID(), Names.ORIGIN_DELIMITER);
    if (tokens.hasMoreTokens()) return tokens.nextToken();
    throw new InvalidOrigin(o);
  }

  public static String getRevisionFromOrigin( Origin o ) throws InvalidOrigin {
    StringTokenizer tokens = new StringTokenizer(o.getUniqueID(), Names.ORIGIN_DELIMITER);
    if (tokens.hasMoreTokens()) tokens.nextToken();
    if (tokens.hasMoreTokens()) tokens.nextToken();
    if (tokens.hasMoreTokens()) return tokens.nextToken();
    throw new InvalidOrigin(o);
  }

  public static String getVersionFromOrigin( Origin o ) throws InvalidOrigin {
    StringTokenizer tokens = new StringTokenizer(o.getUniqueID(), Names.ORIGIN_DELIMITER);
    if (tokens.hasMoreTokens()) tokens.nextToken();
    if (tokens.hasMoreTokens()) tokens.nextToken();
    if (tokens.hasMoreTokens()) tokens.nextToken();
    if (tokens.hasMoreTokens()) return tokens.nextToken();
    throw new InvalidOrigin(o);
  }
*/
  public static IntralinkAccess getAccess() {
    if (null==datasourceAccess) datasourceAccess=new IntralinkAccess();
    return datasourceAccess;
  }

  
  public Collection listIntralinkRepositories(String serverName) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.listIntralinkRepositories();
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return null;
    }
  }

  public void pauseEventListener(String serverName, String datasourceName) throws Exception {
	  try {
	    IntralinkAccessService service = EJBLocator.findService(serverName);
	    service.pauseEventListener(datasourceName);
	  }
	  catch (RemoteException e) {
	    if (null!=e.getCause()) throw (Exception)e.getCause();
	    e.printStackTrace();
	  }
	}

  public void resumeEventListener(String serverName, String datasourceName)throws Exception {
	  try {
	    IntralinkAccessService service = EJBLocator.findService(serverName);
	    service.resumeEventListener(datasourceName);
	  }
	  catch (RemoteException e) {
	    if (null!=e.getCause()) throw (Exception)e.getCause();
	    e.printStackTrace();
	  }
	}
  public void stopEventListener(String serverName, String datasourceName)throws Exception {
	  try {
	    IntralinkAccessService service = EJBLocator.findService(serverName);
	    service.stopEventListener(datasourceName);
	  }
	  catch (RemoteException e) {
	    if (null!=e.getCause()) throw (Exception)e.getCause();
	    e.printStackTrace();
	  }
	}
  
  public void startEventListener(String serverName, String datasourceName) throws Exception {
	  try {
	    IntralinkAccessService service = EJBLocator.findService(serverName);
	    service.startEventListener(datasourceName);
	  }
	  catch (RemoteException e) {
	    if (null!=e.getCause()) throw (Exception)e.getCause();
	    e.printStackTrace();
	  }
	}
  
  public void ignoreEventListenerEvents(String serverName, String datasourceName) throws Exception {
    try {
	    IntralinkAccessService service = EJBLocator.findService(serverName);
	    service.ignoreEventListenerEvents(datasourceName);
	  }
	  catch (RemoteException e) {
	    if (null!=e.getCause()) throw (Exception)e.getCause();
	    e.printStackTrace();
	  }
	}
  
  public void fireEventListenerEvents(String serverName, String datasourceName) throws Exception {
    try {
	    IntralinkAccessService service = EJBLocator.findService(serverName);
	    service.fireEventListenerEvents(datasourceName);
	  }
	  catch (RemoteException e) {
	    if (null!=e.getCause()) throw (Exception)e.getCause();
	    e.printStackTrace();
	  }
	}
  
  public String getEventListenerEventFiringState(String serverName, String datasourceName)throws Exception {
	  try {
	    IntralinkAccessService service = EJBLocator.findService(serverName);
	    return service.getEventListenerEventFiringState(datasourceName);
	  }
	  catch (RemoteException e) {
	    if (null!=e.getCause()) throw (Exception)e.getCause();
	    e.printStackTrace();
	    return null;
	  }
	}
  
  public String getEventListenerRunningState(String serverName, String datasourceName)throws Exception {
	  try {
	    IntralinkAccessService service = EJBLocator.findService(serverName);
	    return service.getEventListenerRunningState(datasourceName);
	  }
	  catch (RemoteException e) {
	    if (null!=e.getCause()) throw (Exception)e.getCause();
	    e.printStackTrace();
	    return null;
	  }
	}
  public Collection getEventListenerHistoryLog(String serverName, String datasourceName) throws Exception {
    try {
	    IntralinkAccessService service = EJBLocator.findService(serverName);
	    return service.getEventListenerHistoryLog(datasourceName);
	  }
	  catch (RemoteException e) {
	    if (null!=e.getCause()) throw (Exception)e.getCause();
	    e.printStackTrace();
	    return null;
	  }
	}
  public int getEventListenerHistoryLogDuration(String serverName, String datasourceName) throws Exception {
    try {
	    IntralinkAccessService service = EJBLocator.findService(serverName);
	    return service.getEventListenerHistoryLogDuration(datasourceName);
	  }
	  catch (RemoteException e) {
	    if (null!=e.getCause()) throw (Exception)e.getCause();
	    e.printStackTrace();
	    return 0;
	  }
	}
  public void setEventListenerHistoryLogDuration(String serverName, String datasourceName, int hours) throws Exception {
    try {
	    IntralinkAccessService service = EJBLocator.findService(serverName);
	    service.setEventListenerHistoryLogDuration(datasourceName, hours);
	  }
	  catch (RemoteException e) {
	    if (null!=e.getCause()) throw (Exception)e.getCause();
	    e.printStackTrace();
	    return;
	  }
	}
  public int getEventListenerRunPeriod(String serverName, String datasourceName) throws Exception {
    try {
	    IntralinkAccessService service = EJBLocator.findService(serverName);
	    return service.getEventListenerRunPeriod(datasourceName);
	  }
	  catch (RemoteException e) {
	    if (null!=e.getCause()) throw (Exception)e.getCause();
	    e.printStackTrace();
	    return 0;
	  }
	}
  public void setEventListenerRunPeriod(String serverName, String datasourceName, int seconds) throws Exception {
    try {
	    IntralinkAccessService service = EJBLocator.findService(serverName);
	    service.setEventListenerRunPeriod(datasourceName, seconds);
	  }
	  catch (RemoteException e) {
	    if (null!=e.getCause()) throw (Exception)e.getCause();
	    e.printStackTrace();
	    return;
	  }
	}

  public Collection getEventListenerTargetQueueNodes(String serverName, String datasourceName) throws Exception {
  try {
	    IntralinkAccessService service = EJBLocator.findService(serverName);
	    return service.getEventListenerTargetQueueNodes(datasourceName);
	  }
	  catch (RemoteException e) {
	    if (null!=e.getCause()) throw (Exception)e.getCause();
	    e.printStackTrace();
	    return null;
	  }
	}
  public void addEventListenerTargetQueueNode(String serverName, String datasourceName, String serverNode) throws Exception {
    try {
	    IntralinkAccessService service = EJBLocator.findService(serverName);
	    service.addEventListenerTargetQueueNode(datasourceName, serverNode);
	  }
	  catch (RemoteException e) {
	    if (null!=e.getCause()) throw (Exception)e.getCause();
	    e.printStackTrace();
	  }
	}
  public void removeEventListenerTargetQueueNode(String serverName, String datasourceName, String serverNode) throws Exception {
    try {
	    IntralinkAccessService service = EJBLocator.findService(serverName);
	    service.removeEventListenerTargetQueueNode(datasourceName, serverNode);
	  }
	  catch (RemoteException e) {
	    if (null!=e.getCause()) throw (Exception)e.getCause();
	    e.printStackTrace();
	  }
	}
  
  
  public Collection listNames(String serverName, String datasourceName, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.listNames(datasourceName, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return null;
    }
  }
  
  public IntralinkFolder getRootFolder(String serverName, String datasourceName, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.getRootFolder(datasourceName, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return null;
    }
  }
  
  public BillOfMaterials reportBill(Origin origin, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
      return service.reportBill(origin, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return null;
    }
  }

  
  public BillOfMaterials reportLatestBill(Origin origin, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
      return service.reportLatestBill(origin, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return null;
    }
  }
/*
  public BillOfMaterials getBill(Origin origin, boolean asStored) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
      return service.reportBill(origin, asStored);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      else e.printStackTrace();
      return null;
    }
  }

  public BillOfMaterials getBill(Origin origin, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
      return service.getBill(origin, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      else e.printStackTrace();
      return null;
    }
  }
*/
  public String publishToAgile(Collection origins, Authentication id) throws Exception {
    if (origins==null || origins.size()==0) return null;
    Origin o = (Origin)origins.toArray()[0];
    try {
      IntralinkAccessService service = EJBLocator.findService(o.getServerName());
      return service.publishToAgile(origins, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return null;
    }
  }

  public String publishIDFToAgile(Collection origins, Authentication id) throws Exception {
    if (origins==null || origins.size()==0) return null;
    Origin o = (Origin)origins.toArray()[0];
    try {
      IntralinkAccessService service = EJBLocator.findService(o.getServerName());
      return service.publishIDFToAgile(origins, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return null;
    }
  }
  
  public Origin createNewProEModel(String serverName, String datasourceName, Metadata data, Authentication id) throws Exception {
    if (data==null) return null;
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.createNewProEModel(datasourceName, data, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return null;
    }
  }

  public void snapshotImage(Origin origin, String outputType, Authentication id) throws Exception{
	  try {
	    IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
	    service.snapshotImage(origin, outputType, null, id);
	  }
	  catch (RemoteException e) {
	    if (null!=e.getCause()) throw (Exception)e.getCause();
	    e.printStackTrace();
	  }
	}
  
  public void snapshotImage(Origin origin, String outputType, String targetDatasource, Authentication id) throws Exception{
    try {
      IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
      service.snapshotImage(origin, outputType, targetDatasource, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public Collection getQueuedSnapshots(String serverName, String datasourceName) throws Exception{
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.getQueuedSnapshots(datasourceName);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return null;
    }
  }

  /*
  public void snapshotImage(Origin origin, String imageNamingConvention, String stampNamingConvention, boolean deleteOldImage, Authentication id) throws Exception{
    try {
      IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
      service.snapshotImage(origin, imageNamingConvention, stampNamingConvention, deleteOldImage, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      else e.printStackTrace();
      return null;
    }
  }
  */
  public void move(String serverName, String datasourceName, String name, String path, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.move(datasourceName, name, path, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return;
    }
  }
  
  public void rename(String serverName, String datasourceName, String name, String newName, Authentication id) throws Exception { 
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.rename(datasourceName, name, newName, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return;
    }
  }

  
  public void trash(String serverName, String datasourceName, String name, Authentication id) throws Exception { 
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.trash(datasourceName, name, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return;
    }
  }
  
  public Metadata find(Origin origin, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
      return service.find(origin, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return null;
    }
  }
  
  public Metadata findLatest(String serverName, String datasourceName, String name, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.findLatest(datasourceName, name, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return null;
    }
  }

  public void deleteFromRepository(Origin o, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(o.getServerName());
      service.deleteFromRepository(o, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void deleteVersionFromRepository(Origin o, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(o.getServerName());
      service.deleteVersionFromRepository(o, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void personalSynchronizeWithCommonSpace(String serverName, String datasourceName, String workspaceName, String name, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.personalSynchronizeWithCommonSpace(datasourceName, workspaceName, name, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void synchronizeWithCommonSpace(String serverName, String datasourceName, String workspaceName, String name, Authentication id) throws Exception {
	  try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.synchronizeWithCommonSpace(datasourceName, workspaceName, name, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void personalRemoveFromWorkspace(String serverName, String datasourceName, String workspaceName, String name, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.personalRemoveFromWorkspace(datasourceName, workspaceName, name, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void removeFromWorkspace(String serverName, String datasourceName, String workspaceName, String name, Authentication id) throws Exception {
	  try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.removeFromWorkspace(datasourceName, workspaceName, name, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }
  public void personalCheckin(Origin origin, String workspace, Authentication id) throws Exception {
    try {
        personalCheckin(origin.getServerName(), origin.getDatasourceName(), workspace, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void personalCheckin(String serverName, String datasourceName, String workspace, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.personalCheckin(datasourceName, workspace, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }


  public boolean contains(String serverName, String datasourceName, String name, Authentication id) throws Exception {
	  try {
	    IntralinkAccessService service = EJBLocator.findService(serverName);
	    return service.contains(datasourceName, name, id);
	  }
	  catch (RemoteException e) {
	    if (null!=e.getCause()) throw (Exception)e.getCause();
	    e.printStackTrace();
	    throw e;
	  }
	}
	  
  
  public void personalCheckoutLatest(String serverName, String datasourceName, String workspaceName, String name, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.personalCheckoutLatest(datasourceName, workspaceName, name, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void checkoutLatest(String serverName, String datasourceName, String workspaceName, String name, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.checkoutLatest(datasourceName, workspaceName, name, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void personalCheckout(Origin origin, String workspace, Authentication id) throws Exception{
    try {
      IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
      service.personalCheckout(origin, workspace, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void checkout(Origin origin, String workspace, Authentication id) throws Exception{
    try {
      IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
      service.checkout(origin, workspace, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void checkin(Origin origin, String workspace, Authentication id) throws Exception {
    try {
      checkin(origin.getServerName(), origin.getDatasourceName(), workspace, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void checkin(String serverName, String datasourceName, String workspace, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.checkin(datasourceName, workspace, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void lock(Origin o, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(o.getServerName());
      service.lock(o.getDatasourceName(), ((IntralinkOrigin)o).getBranch(), o.getName(), id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }
  public void unlock(Origin o, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(o.getServerName());
      service.unlock(o.getDatasourceName(), ((IntralinkOrigin)o).getBranch(), o.getName(), id);
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void lock(String serverName, String datasourceName, String name, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.lock(datasourceName, name, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }
  
  public void lock(String serverName, String datasourceName, Collection names, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.lock(datasourceName, names, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }
  
  public void unlock(String serverName, String datasourceName, String name, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.unlock(datasourceName, name, id);
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void unlock(String serverName, String datasourceName, Collection names, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.unlock(datasourceName, names, id);
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public void lock(String serverName, String datasourceName, String branch, String name, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.lock(datasourceName, branch, name, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }
  
  public void unlock(String serverName, String datasourceName, String branch, String name, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.unlock(datasourceName, branch, name, id);
    }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void promote(Origin origin, String promotionLevel, Authentication id) throws Exception {
	  try{
	    IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
	    service.promote(origin, promotionLevel, id);
	  }
	  catch (RemoteException e) {
	    Exception x = e;
	    while(null!=x.getCause()) x = (Exception)x.getCause();
	    throw x;
	  }
	}
    
  public void promote(Origin origin, String promotionLevel, String dependencies, String configuration, Authentication id) throws Exception {
	  try{
	    IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
	    service.promote(origin, promotionLevel, dependencies, configuration, id);
	  }
	  catch (RemoteException e) {
	    Exception x = e;
	    while(null!=x.getCause()) x = (Exception)x.getCause();
	    throw x;
	  }
	}
  
  public void promoteInstance(Origin genericOrigin, String instancName, String promotionLevel, Authentication id) throws Exception {
	  try{
	    IntralinkAccessService service = EJBLocator.findService(genericOrigin.getServerName());
	    service.promoteInstance(genericOrigin, instancName, promotionLevel, id);
	  }
	  catch (RemoteException e) {
	    Exception x = e;
	    while(null!=x.getCause()) x = (Exception)x.getCause();
	    throw x;
	  }
	}
    
  public void promoteInstance(Origin genericOrigin, String instanceName, String promotionLevel, String dependencies, String configuration, Authentication id) throws Exception {
	  try{
	    IntralinkAccessService service = EJBLocator.findService(genericOrigin.getServerName());
	    service.promoteInstance(genericOrigin, instanceName, promotionLevel, dependencies, configuration, id);
	  }
	  catch (RemoteException e) {
	    Exception x = e;
	    while(null!=x.getCause()) x = (Exception)x.getCause();
	    throw x;
	  }
	}
  
  public void demote(Origin origin, String demotionLevel, Authentication id) throws Exception {
	  try{
	    IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
	    service.demote(origin, demotionLevel, id);
	  }
	  catch (RemoteException e) {
	    Exception x = e;
	    while(null!=x.getCause()) x = (Exception)x.getCause();
	    throw x;
	  }
	}
    
  public void demote(Origin origin, String demotionLevel, String dependencies, String configuration, Authentication id) throws Exception {
	  try{
	    IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
	    service.demote(origin, demotionLevel, dependencies, configuration, id);
	  }
	  catch (RemoteException e) {
	    Exception x = e;
	    while(null!=x.getCause()) x = (Exception)x.getCause();
	    throw x;
	  }
	}
  
  public void demoteInstance(Origin genericOrigin, String instancName, String demotionLevel, Authentication id) throws Exception {
	  try{
	    IntralinkAccessService service = EJBLocator.findService(genericOrigin.getServerName());
	    service.demoteInstance(genericOrigin, instancName, demotionLevel, id);
	  }
	  catch (RemoteException e) {
	    Exception x = e;
	    while(null!=x.getCause()) x = (Exception)x.getCause();
	    throw x;
	  }
	}
    
  public void demoteInstance(Origin genericOrigin, String instanceName, String demotionLevel, String dependencies, String configuration, Authentication id) throws Exception {
	  try{
	    IntralinkAccessService service = EJBLocator.findService(genericOrigin.getServerName());
	    service.demoteInstance(genericOrigin, instanceName, demotionLevel, dependencies, configuration, id);
	  }
	  catch (RemoteException e) {
	    Exception x = e;
	    while(null!=x.getCause()) x = (Exception)x.getCause();
	    throw x;
	  }
	}
    
  public void modelCheck(Origin origin, Authentication id) throws Exception {
    try{
      IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
      service.modelCheck(origin, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }
  
  public Collection getAttributes(String serverName, String datasourceName, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.getAttributes(datasourceName, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      throw e;
    }
  }
  
  public Collection getUserDefinedAttributes(String serverName, String datasourceName, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.getUserDefinedAttributes(datasourceName, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      throw e;
    }
  }
  
	public void setLifeCycleAttributes(Collection metadataList, String metadataField, String attribute, Authentication id) throws Exception {
   if (null==metadataList || metadataList.isEmpty()) return;
   Origin origin = ((Metadata)metadataList.iterator().next()).getOrigin();
    try {
      IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
      service.setLifeCycleAttributes(metadataList, metadataField, attribute, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }
  

  public void setLifeCycleAttribute(Origin origin, String attribute, String value, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
      service.setLifeCycleAttribute(origin, attribute, value, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  
	public void unsetLifeCycleAttributes(Collection metadataList, String attribute, Authentication id) throws Exception {
    if (null==metadataList || metadataList.isEmpty()) return;
    Origin origin = ((Metadata)metadataList.iterator().next()).getOrigin();
    try {
      IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
      service.unsetLifeCycleAttributes(metadataList, attribute, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }


  public void unsetLifeCycleAttribute(Origin origin, String attribute, Authentication id) throws Exception {
	  try {
      IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
      service.unsetLifeCycleAttribute(origin, attribute, id);
	  }
	  catch (RemoteException e) {
	    if (null!=e.getCause()) throw (Exception)e.getCause();
	    e.printStackTrace();
	  }
	}

  public void personalSetAttribute(Origin origin, String workspace, String attribute, String value, Authentication id) throws Exception {
    try {
        personalSetAttribute(origin.getServerName(), origin.getDatasourceName(), origin.getName(), workspace, attribute, value, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void personalSetAttribute(String serverName, String datasourceName, String workspace, String name, String attribute, String value, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.personalSetAttribute(datasourceName, workspace, name, attribute, value, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void personalSetAttributes(Origin origin, String workspace, Map attributes, Authentication id) throws Exception {
    try {
      personalSetAttributes(origin.getServerName(), origin.getDatasourceName(), workspace, origin.getName(), attributes, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void personalSetAttributes(String serverName, String datasourceName, String workspace, String name, Map attributes, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.personalSetAttributes(datasourceName, workspace, name, attributes, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }
  
  public void setAttribute(Origin origin, String workspace, String attribute, String value, Authentication id) throws Exception {
    try {
      setAttribute(origin.getServerName(), origin.getDatasourceName(), origin.getName(), workspace, attribute, value, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void setAttribute(String serverName, String datasourceName, String workspace, String name, String attribute, String value, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.setAttribute(datasourceName, workspace, name, attribute, value, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void setAttributes(Origin origin, String workspace, Map attributes, Authentication id) throws Exception {
    try {
      setAttributes(origin.getServerName(), origin.getDatasourceName(), workspace, origin.getName(), attributes, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void setAttributes(String serverName, String datasourceName, String workspace, String name, Map attributes, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.setAttributes(datasourceName, workspace, name, attributes, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }
  
  /*
  public void setAttributeForAll(String serverName, String datasourceName, String workspace, String attribute, String value, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.setAttributeForAll(datasourceName, workspace, attribute, value, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      else e.printStackTrace();
    }
  }

  public void setAttributesForAll(String serverName, String datasourceName, String workspace, Map attributes, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.setAttributesForAll(datasourceName, workspace, attributes, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      else e.printStackTrace();
    }
  }
*/
  
  public void personalLink(String serverName, String datasourceName, String workspace, String parent, String child, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.personalLink(datasourceName, workspace, parent, child, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }    

  
  public void link(String serverName, String datasourceName, String workspace, String parent, String child, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.link(datasourceName, workspace, parent, child, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }    

  public String convertToIGES(Origin origin, String workspace, String location, Authentication id) throws Exception {
	  try {
	    IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
	    return service.convertToIGES(origin, workspace, location, id);
	  }
	  catch (RemoteException e) {
	    if (null!=e.getCause()) throw (Exception)e.getCause();
	    e.printStackTrace();
	    return null;
	  }
	}

  public String convertToSTEP(Origin origin, String workspace, String location, Authentication id) throws Exception {
	  try {
	    IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
	    return service.convertToSTEP(origin, workspace, location, id);
	  }
	  catch (RemoteException e) {
	    if (null!=e.getCause()) throw (Exception)e.getCause();
	    e.printStackTrace();
	    return null;
	  }
	}
  
  public Collection personalExport(Origin origin, String workspace, String location, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
      return service.personalExport(origin.getDatasourceName(), workspace, location, origin.getName(), id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return null;
    }
  }
  public Collection personalExport(String serverName, String datasourceName, String workspace, String location, String name, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.personalExport(datasourceName, workspace, location, name, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return null;
    }
  }

  public Collection personalExport(String serverName, String datasourceName, String workspace, String location, String name, String dependencies, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.personalExport(datasourceName, workspace, location, name, dependencies, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return null;
    }
  }

  public void personalImportToWorkspace(String serverName, String datasourceName, String workspace, String objectName, URL source, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.personalImportToWorkspace(datasourceName, workspace, objectName, source, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void importToWorkspace(String serverName, String datasourceName, String workspace, String objectName, URL source, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.personalImportToWorkspace(datasourceName, workspace, objectName, source, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void personalImportToWorkspace(Origin o, String workspace, String location, String filename, Authentication id) throws Exception {
    try { personalImportToWorkspace(o.getServerName(), o.getDatasourceName(), workspace, location, filename, id); }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void personalImportToWorkspace(String serverName, String datasourceName, String workspace, String location, String filename, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.personalImportToWorkspace(datasourceName, workspace, location, filename, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }
    
  public Collection personalListWorkspaces(String serverName, String datasourceName, Authentication id) throws NameNotFound, Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.personalListWorkspaces(datasourceName, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      throw e;
    }
  }

  public Workspace personalReportWorkspaceStatus(String serverName, String datasourceName, String workspaceName, Authentication id) throws NameNotFound, Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.personalReportWorkspaceStatus(datasourceName, workspaceName, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      throw e;
    }
  }


  public Collection personalReportAllWorkspaceStatus(String serverName, String datasourceName, Authentication id) throws NameNotFound, Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.personalReportAllWorkspaceStatus(datasourceName, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      throw e;
    }
  }

  public Collection personalListWorkspaceContents(String serverName, String datasourceName, String workspace, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.personalListWorkspaceContents(datasourceName, workspace, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      throw e;
    }
  }
  
  
  public void personalCreateWorkspace(String serverName, String datasourceName, String workspace, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.personalCreateWorkspace(datasourceName, workspace, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void personalDestroyWorkspace(Origin o, String workspace, Authentication id) throws Exception {
    try { personalDestroyWorkspace(o.getServerName(), o.getDatasourceName(), workspace, id); }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void personalDestroyWorkspace(String serverName, String datasourceName, String workspace, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.personalDestroyWorkspace(datasourceName, workspace, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }


  
  
  
  
  
  
  public Collection export(Origin origin, String workspace, String location, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(origin.getServerName());
      return service.export(origin.getDatasourceName(), workspace, location, origin.getName(), id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return null;
    }
  }
  public Collection export(String serverName, String datasourceName, String workspace, String location, String name, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.export(datasourceName, workspace, location, name, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return null;
    }
  }

  public Collection export(String serverName, String datasourceName, String workspace, String location, String name, String dependencies, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.export(datasourceName, workspace, location, name, dependencies, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return null;
    }
  }

  public void importToWorkspace(Origin o, String workspace, String location, String filename, Authentication id) throws Exception {
    try { importToWorkspace(o.getServerName(), o.getDatasourceName(), workspace, location, filename, id); }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void importToWorkspace(String serverName, String datasourceName, String workspace, String location, String filename, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.importToWorkspace(datasourceName, workspace, location, filename, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public Collection listWorkspaces(String serverName, String workspace, String datasourceName, Authentication id) throws NameNotFound, Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.listWorkspaces(datasourceName, workspace, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      throw e;
    }
  }
  

  public Workspace reportWorkspaceStatus(String serverName, String datasourceName, String workspaceName, Authentication id) throws NameNotFound, Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.reportWorkspaceStatus(datasourceName, workspaceName, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      throw e;
    }
  }

  public Collection reportAllWorkspaceStatus(String serverName, String workspace, String datasourceName, Authentication id) throws NameNotFound, Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.reportAllWorkspaceStatus(datasourceName, workspace, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      throw e;
    }
  }
  
  public Collection listWorkspaceContents(String serverName, String datasourceName, String workspace, boolean getInstances, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.listWorkspaceContents(datasourceName, workspace, getInstances, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      throw e;
    }
  }
  
  
  public void createWorkspace(String serverName, String datasourceName, String workspace, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.createWorkspace(datasourceName, workspace, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void destroyWorkspace(Origin o, String workspace, Authentication id) throws Exception {
    try { destroyWorkspace(o.getServerName(), o.getDatasourceName(), workspace, id); }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void destroyWorkspace(String serverName, String datasourceName, String workspace, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.destroyWorkspace(datasourceName, workspace, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public Collection findRTPForms(String serverName, String datasourceName, String datedAfter, String promoteTo, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.findRTPForms(datasourceName, datedAfter, promoteTo, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return null;
    }
  }

  public void createBaseline(String serverName, String datasourceName, String name, String folderLocation, String releaseLevel, Collection components, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.createBaseline(datasourceName, name, folderLocation, releaseLevel, components, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public void deleteBaseline(String serverName, String datasourceName, String name, Authentication id) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      service.deleteBaseline(datasourceName, name, id);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
    }
  }

  public String objectName(String serverName, String datasourceName, String name) throws Exception {
    try {
      IntralinkAccessService service = EJBLocator.findService(serverName);
      return service.objectName(datasourceName, name);
    }
    catch (RemoteException e) {
      if (null!=e.getCause()) throw (Exception)e.getCause();
      e.printStackTrace();
      return null;
    }
  }

  public static String RELEASE_LEVEL = "Release-Level";
}
