package zws.repository.teamcenter.target;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.tc10.TC10MetadataBinary;
import zws.exception.InvalidOrigin;
import zws.origin.Origin;
import zws.origin.tc10.TC10IMANFileOrigin;
import zws.origin.tc10.TC10ItemRevOrigin;
import zws.qx.QxContext;
import zws.repository.target.RepositoryMetadataTarget;
import zws.repository.teamcenter.TC10Constants;
import zws.repository.teamcenter.TC10RepositoryBase;
import zws.repository.teamcenter.util.TC10Util;
import zws.security.Authentication;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import com.ugsolutions.iman.kernel.IMANComponentItemType;
import com.ugsolutions.iman.kernel.IMANComponentItem;
import com.ugsolutions.iman.kernel.IMANComponentItemRevision;
import com.ugsolutions.iman.kernel.IMANComponentDatasetType;
import com.ugsolutions.iman.kernel.IMANComponentDataset;
import com.ugsolutions.iman.kernel.IMANComponentUnitOfMeasure;
import com.ugsolutions.iman.kernel.IMANSession;
import com.ugsolutions.iman.kernel.IMANPreferenceService;

/**
 * The Interface RepositoryMetadataTarget.
 */
public class TC10RepositoryMetadataTarget extends TC10RepositoryBase
implements RepositoryMetadataTarget {

  /**
   * The Constructor.
   * @param parent parentContext
   */
  public TC10RepositoryMetadataTarget(QxContext parent) 
  {
    configureParentContext(parent);
  }

  /**
   * Create.
   *
   * @param data the data
   * @param runningCtx the running ctx
   * @param id the id
   * @param file file
   * @return the origin
   *
   * @throws Exception the exception
   */
  public Origin create(QxContext runningCtx, Metadata data, File file, Authentication id) throws Exception
  {    
    TC10ItemRevOrigin o = null;
    
    IMANComponentUnitOfMeasure uom = null;
    IMANComponentItem item = null;
    IMANComponentItemRevision rev = null;
    IMANComponentDataset dataset = null;
    
    String itemId = data.get(TC10Constants.ITEM_ID);
    String revId = data.get(TC10Constants.REVISION);
    String name = data.get(TC10Constants.NAME);
    String desc = data.get(TC10Constants.DESCRIPTION);
        
    HashMap tcSessions = (HashMap)getTeamcenterSessions();
    IMANSession imanSession = (IMANSession)tcSessions.get(id.getUsername());
        
    item = findItem(imanSession, itemId);
    if(item==null)
    {
      String type = data.get(TC10Constants.TYPE);
      String uomSymbol = data.get(TC10Constants.UNIT_OF_MEASURE);
      
      //TODO: Convert UOM Symbol to IMANComponentUnitOfMeasure
      if( itemId!=null || itemId!="" || revId!=null || revId!="" || name!=null || name!="")
      {
        IMANComponentItemType t = (IMANComponentItemType)imanSession.getTypeComponent(TC10Constants.CLASS_ITEM);
        item = t.create(itemId, revId, type, name, desc, uom);
      }
    }
    
    rev = findItemRevision(imanSession, itemId, revId);
    if(item!=null && rev==null)
      if( revId!=null || revId!="" || name!=null || name!="")
        rev = item.revise(revId, name, desc); 

    Collection c = data.getBinaries();
    if (null!=c && !c.isEmpty()) 
    {
      Iterator t = c.iterator();
      
      while(t.hasNext()) 
      {
        TC10MetadataBinary bm = (TC10MetadataBinary)t.next();        
                
        String[] filePathNames = {""};        
        if(file!=null)
          filePathNames[0] = file.getAbsolutePath();
        else
          filePathNames[0] = bm.get(TC10Constants.ORIGINAL_FILE_NAME);        
        String namedRefs[] = { bm.get(TC10Constants.NAMED_REF) };
                
        TC10IMANFileOrigin imanFileOrigin = (TC10IMANFileOrigin)bm.getOrigin();
//{} //System.out.println("imanFileOrigin: "+imanFileOrigin);         
        String datasetType = imanFileOrigin.getDatasetType();
        String datasetName = imanFileOrigin.getDatasetName();        
        dataset = findDataset(rev, datasetName, datasetType);        
                
        if(dataset==null)
        {
          IMANComponentDatasetType dst = (IMANComponentDatasetType)imanSession.getTypeComponent(datasetType);
          dataset = dst.create(datasetName, "", datasetType);
          dataset.setFiles(filePathNames, namedRefs);
          
          //Attach Dataset
          if( !datasetType.equals("DirectModel") )
          {
            IMANPreferenceService prefService = imanSession.getPreferenceService();
            String default_relation = prefService.getString( IMANPreferenceService.IMAN_preference_site,
                                                             "ItemRevision_default_relation");
            rev.add(default_relation, dataset);
          }
          else { rev.add("IMAN_Rending", dataset); }            
        }
        else { dataset.setFiles(filePathNames, namedRefs); }
      }
    }
    else
    {
      // Create TC10ItemRevOrigin
      //  Now materialize the Metadata with this origin and values from the search result
      MetadataBase m = getProperties(rev);
      //Materialize state for origin
      String state = TC10Util.materializeState(m);
      //Creation Date
      Date creationDate = rev.getDateProperty("creation_date");
      String uniqueId = TC10Util.materializeItemRevUniqueID(rev.getUid(), itemId, revId);
      o = new TC10ItemRevOrigin(getDomainName(), getServerName(), getRepositoryName(), uniqueId, creationDate.getTime(), state);
    }    
    return o;
  }
  
  /**
   * Create.
   *
   * @param data the data
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
  public Origin create(QxContext runningCtx, Metadata data, Authentication id) throws Exception
  {  return create(runningCtx, data, null, id); }

  /**
   * Update.
   *
   * @param data the data
   * @param binary the binary
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the metadata
   *
   * @throws Exception the exception
   */
  public Metadata update(QxContext runningCtx, Metadata data, File binary, Authentication id) throws Exception
  {return data;}

  /**
   * Move.
   *
   * @param o the o
   * @param newFolderPath the new folder path
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
  public Origin move(QxContext runningCtx, Origin o, String newFolderPath, Authentication id) throws Exception
  {return o;}

  /**
   * Rename.
   *
   * @param o the o
   * @param newName the new name
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
  public Origin rename(QxContext runningCtx, Origin o, String newName, Authentication id) throws Exception
  {
    TC10ItemRevOrigin oRev = null;    
    IMANComponentItem item = null;
    IMANComponentItemRevision rev = null;
        
    if( !(o instanceof TC10ItemRevOrigin) ) throw new InvalidOrigin(o);
    
    oRev = (TC10ItemRevOrigin)o;
    
    HashMap tcSessions = (HashMap)getTeamcenterSessions();
    IMANSession imanSession = (IMANSession)tcSessions.get(id.getUsername());  

    String itemId = oRev.getItemId();
    String revId = oRev.getRevision();
    item = findItem(imanSession, itemId);
    
    if(item==null)
      return null;
    
    item.setProperty("item_id", newName);
    
    rev = findItemRevision(imanSession, itemId, revId);
    if(rev==null)
      rev = item.getLatestItemRevision();
    
    MetadataBase m = getProperties(rev);
    //Materialize state for origin
    String state = TC10Util.materializeState(m);
    //Creation Date
    Date creationDate = rev.getDateProperty("creation_date");
    String uniqueId = TC10Util.materializeItemRevUniqueID(rev.getUid(), itemId, revId);
    oRev = new TC10ItemRevOrigin(getDomainName(), getServerName(), getRepositoryName(), uniqueId, creationDate.getTime(), state);
    
    return oRev;
  }

  /**
   * Renumber.
   *
   * @param o the o
   * @param newNumber the new number
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
  public Origin renumber(QxContext runningCtx, Origin o, String newNumber, Authentication id) throws Exception
  {return rename(runningCtx, o, newNumber, id);}

  /**
   * Delete minor version.
   *
   * @param o the o
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @throws Exception the exception
   */
  public void deleteMinorVersion(QxContext runningCtx, Origin o, Authentication id) throws Exception
  {}

  /**
   * Delete entire history.
   *
   * @param runningCtx the running ctx
   * @param name the name
   * @param id the id
   *
   * @throws Exception the exception
   */
  public void deleteEntireHistory(QxContext runningCtx, String name, Authentication id) throws Exception
  {}

  /**.
   * Link.
   *
   * @param target the target
   * @param runningCtx the running ctx
   * @param source the source
   * @param id the id
   *
   * @throws Exception the exception
   */
  public void link(QxContext runningCtx, Origin source, Origin target, Authentication id) throws Exception
  {}

  /**
   * Unlink.
   *
   * @param target the target
   * @param runningCtx the running ctx
   * @param source the source
   * @param id the id
   *
   * @throws Exception the exception
   */
  public void unlink(QxContext runningCtx, Origin source, Origin target, Authentication id) throws Exception
  {}
    
}