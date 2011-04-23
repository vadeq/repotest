package zws.repository.teamcenter;
/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on May 23, 2007 10:48:58 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import com.ugsolutions.aif.kernel.AIFComponentContext;
import com.ugsolutions.iman.kernel.IMANComponentBOMLine;
import com.ugsolutions.iman.kernel.IMANComponentBOMWindow;
import com.ugsolutions.iman.kernel.IMANComponentBOMWindowType;
import com.ugsolutions.iman.kernel.IMANComponentItemRevision;
import com.ugsolutions.iman.kernel.IMANComponentRevisionRule;
import com.ugsolutions.iman.kernel.IMANException;
import com.ugsolutions.iman.kernel.IMANSession;

import zws.action.datasource.Find;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.MetadataSubComponent;
import zws.data.MetadataSubComponentBase;
import zws.exception.InvalidOrigin;
import zws.origin.Origin;
import zws.origin.tc10.TC10ItemRevOrigin;
import zws.qx.QxContext;
import zws.security.Authentication;
import zws.repository.source.RepositoryStructureSource;
import zws.repository.teamcenter.util.TC10Util;


/**
 * The Class TC10RepositoryMetadataSource.
 */
public class TC10RepositoryStructureSource extends TC10RepositoryBase
    implements RepositoryStructureSource {

  /**
   * The Constructor.
   * @param parent parentContext
   */
  public TC10RepositoryStructureSource(QxContext parent) {
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
  public final boolean contains(QxContext runningCtx, Origin origin,
      Authentication id) {

	  if( !(origin instanceof TC10ItemRevOrigin) )
			return false;

	  HashMap tcSessions = (HashMap)getTeamcenterSessions();
	  IMANSession imanSession = (IMANSession)tcSessions.get(id.getUsername());

	  TC10ItemRevOrigin o = (TC10ItemRevOrigin)origin;
	  String itemId = o.getItemId();
	  String revision = o.getRevision();
	  IMANComponentItemRevision itemRevision = findItemRevision( imanSession, itemId, revision);
	  if(itemRevision==null)return false;
	  else return true;
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
  public final Metadata find(QxContext runningCtx, Origin origin,
      Authentication id) throws Exception {
    /*
     * IntralinkOrigin o = (IntralinkOrigin)origin; Find op = new Find();
     * op.setRepository(this); op.setAuthentication(id);
     * op.setComponentName(o.getName()); op.setBranch(o.getBranch());
     * op.setRevision(o.getRevision()); op.setVersion(o.getVersion());
     * op.execute(); Metadata m = (Metadata) op.getResult(); return m;
     */
	if( !(origin instanceof TC10ItemRevOrigin) )
		throw new InvalidOrigin(origin);
	TC10ItemRevOrigin o = (TC10ItemRevOrigin)origin;

	Find op = new Find();
	op.setAuthentication(id);
	// TODO: Add functions below to Find op
	//op.setRepository(this);
	//op.setRevision(o.getRevision());
	//op.setItemId(o.getRevision());
	op.execute();

    Metadata m = (Metadata)op.getResult();
    return m;

  }


  /** Report all dependencies.
   * @param runningCtx qxContext
   * @param o origin
   * @param structureConfiguration structure Configuration
   * @param id authentication
   * @return Metadata
   * @throws Exception exception
   */
  public Collection reportDependencies(QxContext runningCtx, Origin o,Authentication id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  public Metadata reportLatestBOM(QxContext runningCtx, String name, Authentication id)  throws Exception {
    return null;
  }

  /** report BOM.
   * @param runningCtx qxContext
   * @param o origin
   * @param structureConfiguration structure Configuration
   * @param id authentication
   * @return Metadata
   * @throws Exception exception
   */
  public Metadata reportBOM(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
    String structureConfiguration = TC10Constants.DEFAULT_CONFIGURATION;
    MetadataBase root = null;
    
    try
    {
	  HashMap tcSessions = (HashMap)getTeamcenterSessions();
	  IMANSession imanSession = (IMANSession)tcSessions.get(id.getUsername());

	  if( !(origin instanceof TC10ItemRevOrigin) )
		  throw new InvalidOrigin(origin);

	  TC10ItemRevOrigin o = (TC10ItemRevOrigin)origin;

	  String itemId = o.getItemId();
	  String revision = o.getRevision();

	  // Check if Item Revision exists
	  IMANComponentItemRevision itemRevision = findItemRevision( imanSession,
			  													 itemId,
			  													 revision);

	  IMANComponentRevisionRule revRule = getRevisionRule(imanSession, structureConfiguration);
	  IMANComponentBOMWindowType myBOMWindowType = (IMANComponentBOMWindowType) imanSession.getTypeComponent("BOMWindow");
	  IMANComponentBOMWindow myBOMWindow = myBOMWindowType.create(revRule);

	  IMANComponentBOMLine bl = myBOMWindow.setWindowTopLine(null, itemRevision, null, null);

	  root = getProperties(itemRevision);
	  root.merge(getProperties(bl));
	  root = addBinaries(itemRevision, root);
	  root.setOrigin(o);

	  if( bl.hasChildren() )
	  {
		  MetadataSubComponent[] children = getChildren(bl);
		  for(int i=0; i<children.length; i++)
			  root.addSubComponent(children[i]);
		  //Collection x = root.getSubComponents();
	  }

	  myBOMWindow.close();
    }
    catch(IMANException ex)
    {
      debug(this, ex);
    }
	  return root;
  }

  /** report Design Dependencies.
   * @param runningCtx qxContext
   * @param o origin
   * @param structureConfiguration structure Configuration
   * @param id authentication
   * @return Metadata
   * @throws Exception exception
   */
  public Collection reportLatestDependencies(QxContext runningCtx, Origin o,Authentication id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }


  /** report WhereUsed.
   * @param runningCtx qxContext
   * @param o origin
   * @param structureConfiguration structure Configuration
   * @param id authentication
   * @return Metadata
   * @throws Exception exception
   */
  public Metadata reportWhereUsed(QxContext runningCtx, Origin o,Authentication id) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  public MetadataSubComponentBase[] getChildren(IMANComponentBOMLine parent)
  {
	  Vector children = new Vector();

	  try
	  {
		  AIFComponentContext[] comps = parent.getChildren();

		  for(int i=0; i<comps.length; i++)
		  {

			  IMANComponentBOMLine bl = (IMANComponentBOMLine)comps[i].getComponent();
			  IMANComponentItemRevision itemRev = bl.getItemRevision();
			  MetadataBase m = getProperties(itemRev);
			  MetadataSubComponentBase subm = new MetadataSubComponentBase(getSubComponentProperties(bl, m));
			  subm.setComponent( addBinaries(itemRev, m.getMetadataBase()) );

			  // Materialize state for origin
			  String state = TC10Util.materializeState(m.getMetadataBase());
			  // Creation Date
			  Date t = itemRev.getDateProperty("creation_date");

			  // Materialize uniqueId for origin
			  String itemId = m.get(TC10Constants.ITEM_ID);
			  String revision = m.get(TC10Constants.REVISION);
        
			  String uniqueId = TC10Util.materializeItemRevUniqueID(itemRev.getUid(), itemId, revision);

			  //Now materialize the Metadata with this origin and values from the search result
			  TC10ItemRevOrigin tcOrig = new TC10ItemRevOrigin( getDomainName(),
												    		    getServerName(),
												    		    getRepositoryName(),
												    		    uniqueId,
												    		    t.getTime(),
												    			state);
			  tcOrig.setItemId(itemId);
			  tcOrig.setRevision(revision);

			  subm.setOrigin(tcOrig);

			  if( bl.hasChildren() )
			  {
				  MetadataSubComponentBase[] c = getChildren(bl);
				  for(int j=0; j<c.length; j++)
					  subm.addSubComponent(c[j]);
			  }

			  children.add(subm);
		  }
	  }
	  catch(IMANException ex)
	  {
		  {} //System.out.println("TC Error: "+ex.getMessage() );
	  }
	  catch(Exception ex)
	  {
		  {} //System.out.println(ex.getMessage() );
	  }

	  MetadataSubComponentBase[] c = new MetadataSubComponentBase[children.size()];

	  for(int i=0; i<children.size(); i++)
		  c[i] = (MetadataSubComponentBase)children.get(i);

	  return c;
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
  
  public void debug(Object o, Exception ex) {
    String msg = "[" + o.getClass().getName() + "] " + ex.getMessage();    
    try {
      File debug = new File("_debug.txt");
      
      OutputStream debugStream = new FileOutputStream(debug);
      PrintStream s = new PrintStream(debugStream);
      char[] m = msg.toCharArray();
      for (int i=0; i<m.length; s.print(m[i++]));
      ex.printStackTrace(s);
    }
    catch(Exception x) {
      x.printStackTrace();
    }
    
  }
}
