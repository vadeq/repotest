package zws.repository.teamcenter.proxy.target;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.origin.Origin;
import zws.origin.TC10Origin;
import zws.qx.QxContext;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import zws.repository.target.RepositoryMetadataTarget;
import zws.repository.teamcenter.TC10Constants;
import zws.repository.teamcenter.proxy.TC10ProxyRepositoryBase;
import zws.repository.teamcenter.qx.program.TC10Poet;
import zws.security.Authentication;
import zws.security.CryptoUtil;

import java.io.File;


/**
 * The Interface RepositoryMetadataTarget.
 */
public class TC10ProxyRepositoryMetadataTarget extends TC10ProxyRepositoryBase
implements RepositoryMetadataTarget {

  /**
   * The Constructor.
   * @param parent parentContext
   */
  public TC10ProxyRepositoryMetadataTarget(QxContext parent) 
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
  public Origin create(QxContext runningCtx, Metadata data, File file, Authentication id)
  {
    TC10Origin o = new TC10Origin();
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
  public Origin create(QxContext ctx, Metadata data, Authentication id) throws Exception
  {
    Origin o = null;
    
    QxInstruction root = new QxInstruction(TC10Constants.TAG_QX);
    QxInstruction open = new QxInstruction(TC10Constants.TAG_OPEN_REPOSITORY);
    QxInstruction create = new QxInstruction(TC10Constants.TAG_CREATE);

    open.set(TC10Constants.ATT_REPOSITORY_NAME, getRepositoryName());
    open.set(TC10Constants.ATT_USER, id.getUsername());
    //open.set(TC10Constants.ATT_PASSWORD, id.getPassword());    
    CryptoUtil encryptutil = new CryptoUtil();
    String encryptedPassword = encryptutil.encrypt(id.getPassword());
    open.set(TC10Constants.ATT_ENCRYPTED_PASSWORD, encryptedPassword);
    
    //create.set(TC10Constants.ATT_ORIGIN, origin.toString());

    root.add(open);          
    open.add(create);
    QxXML dataInstruction = new QxXML(root.toString());
    TC10Poet poet = TC10Poet.materializePoet(this);
    QxXML result = poet.executeQx(ctx, dataInstruction);
        
    {} //System.out.println(">>>>>>Proxy Create<<<<<<<<");
    {} //System.out.println(result.toString());
    {} //System.out.println(">>>>>>Proxy Create<<<<<<<<");

    //TC10ResultHandler handler = new SearchResultHandler();
    //handleResponse(result, handler);
    //Collection c = handler.getResults();
    //if (null==c || c.isEmpty()) return null;
    //Metadata m = (Metadata)c.iterator().next();
    //{} //System.out.println("find results... " + m);    
    //return m;
        
    return o;
  }

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
  {return o;}

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
  {return o;}

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

  /**
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
