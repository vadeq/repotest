package zws.repository.teamcenter.proxy;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on May 23, 2007 10:48:58 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.action.datasource.Find;
import zws.data.Metadata;
import zws.exception.InvalidOrigin;
import zws.origin.Origin;
import zws.origin.tc10.TC10ItemRevOrigin;
import zws.qx.QxContext;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import zws.repository.source.RepositoryStructureSource;
import zws.repository.teamcenter.TC10Constants;
import zws.repository.teamcenter.proxy.xml.BomHandler;
import zws.repository.teamcenter.proxy.xml.TC10ResultHandler;
import zws.repository.teamcenter.qx.program.TC10Poet;
import zws.security.Authentication;
import zws.security.CryptoUtil;

import java.util.Collection;


/**
 * The Class TC10RepositoryMetadataSource.
 */
public class TC10ProxyRepositoryStructureSource extends TC10ProxyRepositoryBase
    implements RepositoryStructureSource {

  /**
   * The Constructor.
   * @param parent parentContext
   */
  public TC10ProxyRepositoryStructureSource(QxContext parent) {
    configureParentContext(parent);
  }

  /**
   * contains.
   * @param id authentication
   * @param runningCtx the running ctx
   * @param origin origin
   * @return boolean
   * @see zws.repository.source.RepositoryMetadataSource#contains(zws.origin.Origin,
   * zws.security.Authentication)
   */
  public final boolean contains(QxContext runningCtx, Origin origin, Authentication id) {
   return false;
  }


  /**
   * find object(s) laterst revision.
   * @param id authentication
   * @param runningCtx the running ctx
   * @param origin origin object
   * @return Metadata result data
   * @throws Exception exception
   * @see zws.repository.source.RepositoryMetadataSource#find(zws.origin.Origin,
   * zws.security.Authentication)
   */
  public final Metadata find(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
	if( !(origin instanceof TC10ItemRevOrigin) )
		throw new InvalidOrigin(origin);
	 TC10ItemRevOrigin o = (TC10ItemRevOrigin)origin;

	 Find op = new Find();
	 op.setAuthentication(id);
	 op.execute();
    Metadata m = (Metadata)op.getResult();
    {} //System.out.println("find results... " + m);
    return m;
  }



  /** report BOM.
   * @param runningCtx qxContext
   * @param o origin
   * @param structureConfiguration structure Configuration
   * @param id authentication
   * @return Metadata
   * @throws Exception exception
   */
  public Metadata reportBOM(QxContext ctx, Origin origin, Authentication id) throws Exception {

	    QxInstruction root = new QxInstruction(TC10Constants.TAG_QX);
	    QxInstruction open = new QxInstruction(TC10Constants.TAG_OPEN_REPOSITORY);
	    QxInstruction reportBOM = new QxInstruction(TC10Constants.TAG_REPORT_BOM);

	    open.set(TC10Constants.ATT_REPOSITORY_NAME, getRepositoryName());
	    reportBOM.set(TC10Constants.ATT_ORIGIN, origin.toString());
	    reportBOM.set(TC10Constants.ATT_CONFIGURATION, TC10Constants.DEFAULT_CONFIGURATION);	    
	    open.set(TC10Constants.ATT_USER, id.getUsername());
	    //open.set(TC10Constants.ATT_PASSWORD, id.getPassword());
	    CryptoUtil encryptutil = new CryptoUtil();
	    String encryptedPassword = encryptutil.encrypt(id.getPassword());
	    open.set(TC10Constants.ATT_ENCRYPTED_PASSWORD, encryptedPassword);    	    


	    root.add(open);
	    open.add(reportBOM);
	    QxXML dataInstruction = new QxXML(root.toString());


      TC10Poet poet = TC10Poet.materializePoet(this);
      QxXML result = poet.executeQx(ctx, dataInstruction);

      //QxInstructionFileClient qxClient = QxInstructionFileClient.materializeClient();
	    //QxXML result = qxClient.executeQx(runningCtx, dataInstruction);
	    //{} //System.out.println(">>>>>>Proxy Find<<<<<<<<");
	    //{} //System.out.println(result.toString());
	    //{} //System.out.println(">>>>>>Proxy Find<<<<<<<<");

	    TC10ResultHandler handler = new BomHandler();
	    handleResponse(result, handler);
	    Collection c = handler.getResults();
	    if (null==c || c.isEmpty()) return null;
	    Metadata m = (Metadata)c.iterator().next();
	    {} //System.out.println("report bom results... " + m);	    
	    return m;
  }


  /* (non-Javadoc)
   * @see zws.repository.source.RepositoryStructureSource#reportDependencies(zws.qx.QxContext, zws.origin.Origin, zws.security.Authentication)
   */
  public Collection reportDependencies(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see zws.repository.source.RepositoryStructureSource#reportLatestBOM(zws.qx.QxContext, java.lang.String, zws.security.Authentication)
   */
  public Metadata reportLatestBOM(QxContext runningCtx, String name, Authentication id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see zws.repository.source.RepositoryStructureSource#reportLatestDependencies(zws.qx.QxContext, zws.origin.Origin, zws.security.Authentication)
   */
  public Collection reportLatestDependencies(QxContext runningCtx, Origin o, Authentication id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see zws.repository.source.RepositoryStructureSource#reportWhereUsed(zws.qx.QxContext, zws.origin.Origin, zws.security.Authentication)
   */
  public Metadata reportWhereUsed(QxContext runningCtx, Origin o, Authentication id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see zws.repository.source.RepositoryStructureSource#reportFirstLevelDependencies(zws.qx.QxContext, zws.origin.Origin, zws.security.Authentication)
   */
  public Collection reportFirstLevelDependencies(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see zws.repository.source.RepositoryStructureSource#reportFirstLevelLatestDependencies(zws.qx.QxContext, zws.origin.Origin, zws.security.Authentication)
   */
  public Collection reportFirstLevelLatestDependencies(QxContext runningCtx, Origin o, Authentication id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  public Metadata reportBOMPlusDependencies(QxContext runningCtx, Origin o, Authentication id) throws Exception {
    return null;
  }

  public Metadata reportLatestBOMPlusDependencies(QxContext runningCtx, Origin o, Authentication id) throws Exception {
    return null;
  }

}
