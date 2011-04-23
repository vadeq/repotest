package zws.repository.teamcenter.proxy;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on May 23, 2007 10:48:58 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */


import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.teamcenter.TC10Constants;
import zws.repository.teamcenter.proxy.xml.SearchResultHandler;
import zws.repository.teamcenter.proxy.xml.TC10ResultHandler;
import zws.repository.teamcenter.qx.program.TC10Poet;
import zws.search.SearchAgent;
import zws.security.Authentication;
import zws.security.CryptoUtil;

import java.util.Collection;
import java.util.Vector;

/**
 * The Class TC10RepositoryMetadataSource.
 */
public class TC10ProxyRepositoryMetadataSource extends TC10ProxyRepositoryBase
implements RepositoryMetadataSource {

	/**
	 * The Constructor.
	 * @param parent parentContext
	 */
	public TC10ProxyRepositoryMetadataSource(QxContext parent) 
	{
		configureParentContext(parent);
	}

  private Metadata stubbedOutMetadata() {
    MetadataBase m = new MetadataBase();
    m.setName("stubed-metadata");
    return m;
  }
  
	/**
	 * contains.
	 * @param runningCtx QxContext
	 * @param origin origin
	 * @param id authentication
	 * @return boolean
	 * @see zws.repository.source.RepositoryMetadataSource#contains(zws.origin.Origin,
	 * zws.security.Authentication)
	 */
	public final boolean contains(QxContext ctx, Origin origin, Authentication id) {
		QxInstruction root = new QxInstruction("tc10-qx");
	    QxInstruction open = new QxInstruction("open-repository");
	    QxInstruction contains = new QxInstruction("contains");

	    open.set("username",  id.getUsername());
	    open.set("password",  id.getPassword());
	    contains.set("origin", origin.toString());

	    root.add(open);          
	    open.add(contains);
	    QxXML dataInstruction = new QxXML(root.toString());
      
      TC10Poet poet = TC10Poet.materializePoet(this);
      QxXML result = poet.executeQx(ctx, dataInstruction);

      //QxInstructionFileClient qxClient = QxInstructionFileClient.materializeClient();
	    //QxXML result = qxClient.executeQx(runningCtx, dataInstruction);      

      //parse result using TC10 handler
	    //return something
	    return false;
	}

	// each method here will make a WEBService call (Client implementation)
	/**
	 * find object(s) laterst revision.
	 * @param runtimeCtx QxContext
	 * @param name object name
	 * @param id authentication
	 * @throws Exception exception
	 * @return Metadata result data
	 * @see zws.repository.source.RepositoryMetadataSource#findLatest(java.lang.String,
	 * zws.security.Authentication)
	 */
	public final Metadata findLatest(final QxContext ctx, String itemId, Authentication id) throws Exception {
	    
      QxInstruction root = new QxInstruction(TC10Constants.TAG_QX);
	    QxInstruction open = new QxInstruction(TC10Constants.TAG_OPEN_REPOSITORY);
	    QxInstruction find_latest = new QxInstruction(TC10Constants.TAG_FIND_LATEST);

      open.set(TC10Constants.ATT_REPOSITORY_NAME, getRepositoryName());
      open.set(TC10Constants.ATT_USER,  id.getUsername());
      //open.set(TC10Constants.ATT_PASSWORD,  id.getPassword());
      CryptoUtil encryptutil = new CryptoUtil();
      String encryptedPassword = encryptutil.encrypt(id.getPassword());
      open.set(TC10Constants.ATT_ENCRYPTED_PASSWORD, encryptedPassword);
      
	  find_latest.set(TC10Constants.ATT_ITEM_ID, itemId);

	    root.add(open);          
	    open.add(find_latest);

      QxXML dataInstruction = new QxXML(root.toString());

      TC10Poet poet = TC10Poet.materializePoet(this);
      QxXML result = poet.executeQx(ctx, dataInstruction);
      
	    //QxInstructionFileClient qxClient = QxInstructionFileClient.materializeClient();
	    //QxXML result = qxClient.executeQx(runningCtx, dataInstruction);
      
      TC10ResultHandler handler = new SearchResultHandler();
      handleResponse(result, handler);
      Collection c = handler.getResults();
      if (null==c || c.isEmpty()) return null;
      Metadata m = (Metadata)c.iterator().next();
      {} //System.out.println("find latest results... " + m);
      return m;
	}


	/**
	 * find object(s) laterst revision.
	 * @param runningCtx QxContext
	 * @param origin origin object
	 * @param id authentication
	 * @throws Exception exception
	 * @return Metadata result data
	 * @see zws.repository.source.RepositoryMetadataSource#find(zws.origin.Origin, zws.security.Authentication)
	 */
	public final Metadata find(QxContext ctx, Origin origin, Authentication id) throws Exception 
	{
    QxInstruction root = new QxInstruction(TC10Constants.TAG_QX);
    QxInstruction open = new QxInstruction(TC10Constants.TAG_OPEN_REPOSITORY);
    QxInstruction find = new QxInstruction(TC10Constants.TAG_FIND);

    open.set(TC10Constants.ATT_REPOSITORY_NAME, getRepositoryName());
    find.set(TC10Constants.ATT_ORIGIN, origin.toString());    
    open.set(TC10Constants.ATT_USER, id.getUsername());
    //open.set(TC10Constants.ATT_PASSWORD, id.getPassword());
    CryptoUtil encryptutil = new CryptoUtil();
    String encryptedPassword = encryptutil.encrypt(id.getPassword());
    open.set(TC10Constants.ATT_ENCRYPTED_PASSWORD, encryptedPassword);    


    root.add(open);          
    open.add(find);
    QxXML dataInstruction = new QxXML(root.toString());
    TC10Poet poet = TC10Poet.materializePoet(this);
    QxXML result = poet.executeQx(ctx, dataInstruction);
    
    //QxInstructionFileClient qxClient = QxInstructionFileClient.materializeClient();
    //QxXML result = qxClient.executeQx(runningCtx, dataInstruction);
    
    //{} //System.out.println(">>>>>>Proxy Find<<<<<<<<");
    //{} //System.out.println(result.toString());
    //{} //System.out.println(">>>>>>Proxy Find<<<<<<<<");

    TC10ResultHandler handler = new SearchResultHandler();
    handleResponse(result, handler);
    Collection c = handler.getResults();
    if (null==c || c.isEmpty()) return null;
    Metadata m = (Metadata)c.iterator().next();
    {} //System.out.println("find results... " + m);    
    return m;
	}

	public Collection searchLatest(QxContext runtimeCtx, String name, Authentication id) throws Exception {		
		SearchAgent agent = materializeSearchAgent();
		TC10ProxyRepositorySearchAgent tc_agent = (TC10ProxyRepositorySearchAgent)agent;
		tc_agent.setAuthenticationToken(id);
		String c = "( ItemID=" + name + " )";
		tc_agent.initializeStorage(new Vector());
		tc_agent.setCriteria(c);
		tc_agent.search();
		return agent.getResults();
	}
}
