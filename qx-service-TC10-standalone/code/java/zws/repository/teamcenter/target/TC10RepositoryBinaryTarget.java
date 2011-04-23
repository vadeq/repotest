package zws.repository.teamcenter.target;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.InvalidName;
import zws.exception.InvalidOrigin;
import zws.origin.Origin;
import zws.origin.TC10Origin;
import zws.origin.tc10.TC10IMANFileOrigin;
import zws.qx.QxContext;
import zws.security.Authentication;
import zws.util.RemotePackage;
import zws.repository.target.RepositoryBinaryTarget;
import zws.repository.teamcenter.TC10Constants;
import zws.repository.teamcenter.TC10RepositoryBase;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;


import com.ugsolutions.iman.kernel.IMANComponent;
import com.ugsolutions.iman.kernel.IMANComponentDataset;
import com.ugsolutions.iman.kernel.IMANComponentDatasetType;
import com.ugsolutions.iman.kernel.IMANComponentImanFile;
import com.ugsolutions.iman.kernel.IMANComponentItem;
import com.ugsolutions.iman.kernel.IMANComponentItemRevision;
import com.ugsolutions.iman.kernel.IMANPreferenceService;
import com.ugsolutions.iman.kernel.IMANSession;


/**
 * The  TC10RepositoryBinaryTarget.
 */
public class TC10RepositoryBinaryTarget extends TC10RepositoryBase
implements RepositoryBinaryTarget  {

  /**
   * The Constructor.
   * @param parent parentContext
   */
  public TC10RepositoryBinaryTarget(QxContext parent) 
  {
    configureParentContext(parent);
  }
  
  //Files
  /**
   * Add.
   *
   * @param file the file
   * @param runningCtx the running ctx
   * @param id the id
   * @return the origin
   * @throws Exception the exception
   */
   public Origin add(QxContext runningCtx, File file, Authentication id) throws Exception
   {
     TC10Origin o = new TC10Origin();
     return o;
   }

  /**
   * Update.
   * @param file the file
   * @param runningCtx the running ctx
   * @param id the id
   * @return the origin
   * @throws Exception the exception
   */
   public Origin update(QxContext runningCtx, File file, Authentication id)
   {
     TC10Origin o = new TC10Origin();
     return o;
   }

  /**
   * Store.
   *
   * @param file the file
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
   public Origin store(QxContext runningCtx, File file, Authentication id)
   {
     TC10Origin o = new TC10Origin();
     return o;
   }

  /**
   * Adds the attachment.
   *
   * @param o the o
   * @param file the file
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @throws Exception the exception
   */
   public void addAttachment(QxContext runningCtx, Origin o, File file, Authentication id) throws Exception
   {
     IMANComponentItem item = null;
     IMANComponentItemRevision rev = null;
     IMANComponentDataset dataset = null;
     TC10IMANFileOrigin oFile = null;
     
     if( !(o instanceof TC10IMANFileOrigin) ) throw new InvalidOrigin(o);
     
     oFile = (TC10IMANFileOrigin)o;     
     String itemId = oFile.getItemId();
     String revId = oFile.getRevision();
     String datasetType = oFile.getDatasetType();
     String datasetName = oFile.getDatasetName();
     String namedRefs[] = { oFile.getNamedRef() };
     String filePathNames[] = { file.getAbsolutePath() };        
          
     HashMap tcSessions = (HashMap)getTeamcenterSessions();
     IMANSession imanSession = (IMANSession)tcSessions.get(id.getUsername());
         
     item = findItem(imanSession, itemId);
     if(item==null)
       throw new InvalidName(itemId);
     
     rev = findItemRevision(imanSession, itemId, revId);
     if(rev==null)
       throw new InvalidName(itemId+"/"+revId);
     
     dataset = findDataset(rev, oFile.getDatasetName(), oFile.getDatasetType());        
                 
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

  /**
   * Update attachment.
   *
   * @param o the o
   * @param file the file
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @throws Exception the exception
   */
   public void updateAttachment(QxContext runningCtx, Origin o, File file, Authentication id) throws Exception
   {
     IMANComponentItem item = null;
     IMANComponentItemRevision rev = null;
     IMANComponentDataset dataset = null;
     TC10IMANFileOrigin oFile = null;
     
     if( !(o instanceof TC10IMANFileOrigin) ) throw new InvalidOrigin(o);
     
     String itemId = oFile.getItemId();
     String revId = oFile.getRevision();
     String datasetType = oFile.getDatasetType();
     String datasetName = oFile.getDatasetName();
          
     HashMap tcSessions = (HashMap)getTeamcenterSessions();
     IMANSession imanSession = (IMANSession)tcSessions.get(id.getUsername());
         
     item = findItem(imanSession, itemId);
     if(item==null)
       throw new InvalidName(itemId);
     
     rev = findItemRevision(imanSession, itemId, revId);
     if(rev==null)
       throw new InvalidName(itemId+"/"+revId);
     
     dataset = findDataset(rev, oFile.getDatasetName(), oFile.getDatasetType());
                 
     if(dataset!=null)
     { 
       String namedRefs[] = { oFile.getNamedRef() };
       String filePathNames[] = { file.getAbsolutePath() };
       
       IMANComponent[] comps = dataset.getNamedRefComponents(oFile.getNamedRef());
       for(int i=0; i<comps.length; i++)
       {
         IMANComponentImanFile f = (IMANComponentImanFile)comps[i];
         String originalFileName = f.getProperty(TC10Constants.ORIGINAL_FILE_NAME);
         if ( file.getName().equals(originalFileName) )
         {
           f.delete();
           dataset.setFiles(filePathNames, namedRefs);
         }
       }        
     }
     else { throw new InvalidOrigin(o); }
     
   }

  /**
   * Store attachment.
   *
   * @param o the o
   * @param file the file
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @throws Exception the exception
   */
   public void storeAttachment(QxContext runningCtx, Origin o, File file, Authentication id) throws Exception
   {addAttachment(runningCtx, o, file, id);}

  //InputStreams
  /**
   * Add.
   *
   * @param iStream the iStream
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
   public Origin add(QxContext runningCtx, InputStream iStream, Authentication id)
   {
     TC10Origin o = new TC10Origin();
     return o;
   }

  /**
   * Update.
   *
   * @param iStream the iStream
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
   public Origin update(QxContext runningCtx, InputStream iStream, Authentication id)
   {
     TC10Origin o = new TC10Origin();
     return o;
   }

  /**
   * Store.
   *
   * @param iStream the iStream
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
   public Origin store(QxContext runningCtx, InputStream iStream, Authentication id)
   {
     TC10Origin o = new TC10Origin();
     return o;
   }

  /**
   * Adds the attachment.
   *
   * @param o the o
   * @param iStream the iStream
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @throws Exception the exception
   */
   public void addAttachment(QxContext runningCtx, Origin o, InputStream  iStream, Authentication id)
   {}

  /**
   * Update attachment.
   *
   * @param o the o
   * @param iStream the iStream
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @throws Exception the exception
   */
   public void updateAttachment(QxContext runningCtx, Origin o, InputStream  iStream, Authentication id)
   {}

  /**
   * Store attachment.
   *
   * @param o the o
   * @param iStream the iStream
   * @param runningCtx the running ctx
   * @param id the id
   *
   * @throws Exception the exception
   */
   public void storeAttachment(QxContext runningCtx, Origin o, InputStream  iStream, Authentication id)
   {}
   
   /**
    * Store attachment.
    *
    * @param o the o
    * @param file the file
    * @param runningCtx the running ctx
    * @param id the id
    *
    * @throws Exception the exception
    */
   public void storeAttachment(QxContext runningCtx, String name, RemotePackage remotePackage, Authentication id) throws Exception
   {}
   
   /**
    * Store attachment.
    *
    * @param o the o
    * @param file the file
    * @param runningCtx the running ctx
    * @param id the id
    *
    * @throws Exception the exception
    */
   public void storeAttachmentForECOItem(QxContext runningCtx, String name, String ecoNumber, RemotePackage remotePackage, Authentication id) throws Exception
   {}
}
