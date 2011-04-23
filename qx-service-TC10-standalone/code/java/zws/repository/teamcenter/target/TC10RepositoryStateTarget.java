package zws.repository.teamcenter.target;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;
import zws.origin.tc10.TC10IMANFileOrigin;
import zws.origin.tc10.TC10ItemRevOrigin;
import zws.qx.QxContext;
import zws.repository.target.RepositoryStateTarget;
import zws.repository.teamcenter.TC10Constants;
import zws.repository.teamcenter.TC10RepositoryBase;
import zws.security.Authentication;
import zws.exception.InvalidOrigin;
import zws.exception.InvalidValue;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.ugsolutions.iman.kernel.IMANComponent;
import com.ugsolutions.iman.kernel.IMANComponentDataset;
import com.ugsolutions.iman.kernel.IMANComponentItem;
import com.ugsolutions.iman.kernel.IMANComponentItemRevision;
import com.ugsolutions.iman.kernel.IMANComponentReleaseStatus;
import com.ugsolutions.iman.kernel.IMANComponentReleaseStatusType;
import com.ugsolutions.iman.kernel.IMANComponentUser;
import com.ugsolutions.iman.kernel.IMANProperty;
import com.ugsolutions.iman.kernel.IMANSession;


/**
 * The Interface RepositoryStateTarget.
 */
public class TC10RepositoryStateTarget extends TC10RepositoryBase
implements RepositoryStateTarget {

  /**
   * The Constructor.
   * @param parent parentContext
   */
  public TC10RepositoryStateTarget(QxContext parent) 
  {
    configureParentContext(parent);
  }
  
  public void setStateAttributes(QxContext runningCtx, Origin origin, Map attribValues, Authentication id) throws Exception
  {
    Set keys = attribValues.keySet();
    Iterator it = keys.iterator();
    while(it.hasNext ()) {
      String key = (String)it.next();
      setStateAttribute(runningCtx, origin, key, (String)attribValues.get(key), id);
    }
  }
  
  public void setStateAttribute(QxContext runningCtx, Origin origin, String attribute, String newValue, Authentication id) throws Exception
  {
    if(origin instanceof TC10ItemRevOrigin )
    {writeItemRevStateAttribute(runningCtx, (TC10ItemRevOrigin)origin, attribute, newValue, id);}  
    else if(origin instanceof TC10IMANFileOrigin )
    {writeDatasetStateAttribute(runningCtx, (TC10IMANFileOrigin)origin, attribute, newValue, id);}
    else {throw new InvalidOrigin(origin);}
  }
  
  public void lock(QxContext runningCtx, Origin origin, Authentication id) throws Exception
  {
    
  }
  
  public void unlock(QxContext runningCtx, Origin origin, Authentication id) throws Exception
  {
    
  }
  
  public void lock(QxContext runningCtx, Collection origins, Authentication id) throws Exception
  {
    
  }
  
  public void unlock(QxContext runningCtx, Collection origins, Authentication id) throws Exception
  {
    
  }
  
  public void updateRevision(QxContext runningCtx, Origin origin, String newRevision, Authentication id) throws Exception
  {
    
  }
  
  public void promoteLifeCycleReleaseState(QxContext runningCtx, Origin origin, String promoteTo, Authentication id) throws Exception
  {
    
  }
  
  public void demoteLifeCycleReleaseState(QxContext runningCtx, Origin origin, String promoteTo, Authentication id) throws Exception
  {
    
  }
    
  private void writeItemRevStateAttribute(QxContext runningCtx, TC10ItemRevOrigin origin, String attribute, String newValue, Authentication id) throws Exception
  {
    String itemId = origin.getItemId();
    String revId = origin.getRevision();
    
    HashMap tcSessions = (HashMap)getTeamcenterSessions();
    IMANSession imanSession = (IMANSession)tcSessions.get(id.getUsername());
    
    IMANComponentItem item = findItem(imanSession, itemId);
    if(item==null) return;
    
    IMANComponentItemRevision rev = findItemRevision(imanSession, itemId, revId);
    if(rev==null) return;
    
    if(attribute.equals(TC10Constants.RELEASE_LEVEL)){writeReleaseStatus(rev, newValue);}
    else if(attribute.equals(TC10Constants.LOCK_STATUS)){writeLockStatus(rev, newValue);}
    else if(attribute.equals(TC10Constants.OWNER)){writeOwner(imanSession, rev, newValue);}
    else{ throw new InvalidValue(attribute); }    
  }
  
  private void writeDatasetStateAttribute(QxContext runningCtx, TC10IMANFileOrigin origin, String attribute, String newValue, Authentication id) throws Exception
  {
    String itemId = origin.getItemId();
    String revId = origin.getRevision();
    String datasetType = origin.getDatasetType();
    String datasetName = origin.getDatasourceName();
    
    HashMap tcSessions = (HashMap)getTeamcenterSessions();
    IMANSession imanSession = (IMANSession)tcSessions.get(id.getUsername());
    
    IMANComponentItem item = findItem(imanSession, itemId);
    if(item==null) return;
    
    IMANComponentItemRevision rev = findItemRevision(imanSession, itemId, revId);
    if(rev==null) return;
    
    IMANComponentDataset dataset = findDataset(rev, datasetName, datasetType);
    if(dataset==null) return;
    
    if(attribute.equals(TC10Constants.RELEASE_LEVEL)){
      writeReleaseStatus(rev, newValue);
      writeReleaseStatus(dataset, newValue);
    }
    else if(attribute.equals(TC10Constants.LOCK_STATUS)){
      writeLockStatus(rev, newValue);
      writeLockStatus(dataset, newValue);
    }
    else if(attribute.equals(TC10Constants.OWNER)){
      writeLockStatus(rev, newValue);
      writeOwner(imanSession, dataset, newValue);
    }
    else{ throw new InvalidValue(attribute); }
  }
  
  private void writeReleaseStatus(IMANComponent comp, String value) throws Exception
  {
    IMANProperty prop = comp.getIMANProperty(TC10Constants.RELEASE_LEVEL);
    IMANComponent[] releaseStatusList = prop.getReferenceValueArray();
    IMANComponent[] newStatusList = new IMANComponent[releaseStatusList.length+1];
    
    IMANComponentReleaseStatusType relStatusType = new IMANComponentReleaseStatusType();
    IMANComponentReleaseStatus newStatus = (IMANComponentReleaseStatus)relStatusType.create(value);
    
    newStatusList[0] = newStatus;
    for(int i=0; i<releaseStatusList.length; i++)
      newStatusList[i+1] = releaseStatusList[0];
    
    prop.setReferenceValueArray(newStatusList);
    comp.setIMANProperty(prop);    
  }
  
  private void writeLockStatus(IMANComponent comp, String value) throws Exception
  {
    IMANProperty prop = comp.getIMANProperty(TC10Constants.LOCK_STATUS);    
    if( value.equals(TC10Constants.LOCKED) ) prop.setLogicalValue(true);
    else prop.setLogicalValue(false);    
    comp.setIMANProperty(prop);
  }
  
  private void writeOwner(IMANSession session, IMANComponent comp, String value) throws Exception
  {
    IMANComponentUser user = findUser(session, value);
    if( user!=null) comp.changeOwner(user, null);
    else throw new InvalidValue(value);
  }
  
}
