package zws.repository.teamcenter;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on May 23, 2007 10:48:58 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */


import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.origin.Origin;
import zws.origin.tc10.TC10DatasetOrigin;
import zws.origin.tc10.TC10IMANFileOrigin;
import zws.origin.tc10.TC10ItemOrigin;
import zws.origin.tc10.TC10ItemRevOrigin;
import zws.qx.QxContext;
import zws.search.SearchAgent;
import zws.security.Authentication;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.teamcenter.util.TC10Util;


import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import com.ugsolutions.iman.kernel.IMANComponentDataset;
import com.ugsolutions.iman.kernel.IMANComponentImanFile;
import com.ugsolutions.iman.kernel.IMANComponentItem;
import com.ugsolutions.iman.kernel.IMANComponentItemRevision;
import com.ugsolutions.iman.kernel.IMANException;
import com.ugsolutions.iman.kernel.IMANSession;



/**
 * The Class TC10RepositoryMetadataSource.
 */
public class TC10RepositoryMetadataSource extends TC10RepositoryBase
implements RepositoryMetadataSource {

	/**
	 * The Constructor.
	 * @param parent parentContext
	 */
	public TC10RepositoryMetadataSource(QxContext parent)
	{
		configureParentContext(parent);
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
	public final boolean contains(QxContext runningCtx, Origin origin, Authentication id) {

		HashMap tcSessions = (HashMap)getTeamcenterSessions();
		IMANSession imanSession = (IMANSession)tcSessions.get(id.getUsername());

		String itemId = "";
		String revision = "";
		String datasetName = "";
		String datasetType = "";
		String fileName = "";

		if(origin instanceof TC10ItemOrigin)
		{
			TC10ItemOrigin o = (TC10ItemOrigin)origin;
			itemId = o.getItemId();
			IMANComponentItem item = findItem(imanSession, itemId);
			if(item==null) return false;
			else return true;
		}

		if(origin instanceof TC10ItemRevOrigin)
		{
			TC10ItemRevOrigin o = (TC10ItemRevOrigin)origin;
			itemId = o.getItemId();
			revision = o.getRevision();
			IMANComponentItemRevision itemRevision = findItemRevision( imanSession, itemId, revision);
			if(itemRevision==null)return false;
			else return true;
		}

		if(origin instanceof TC10DatasetOrigin)
		{
			TC10DatasetOrigin o = (TC10DatasetOrigin)origin;

			itemId = o.getItemId();
			revision = o.getRevision();
			datasetName = o.getDatasetName();
			datasetType = o.getDatasetType();

			//	Check if Item Revision exists
			IMANComponentItemRevision itemRevision = findItemRevision( imanSession, itemId, revision);
			if(itemRevision==null)
			{
				return false;
			}
			else
			{
				// Check if Dataset exists
				IMANComponentDataset ds = findDataset(itemRevision, datasetName, datasetType);
				if(ds==null)return false;
				else return true;
			}
		}

		if(origin instanceof TC10IMANFileOrigin)
		{
			TC10IMANFileOrigin o = (TC10IMANFileOrigin)origin;

			itemId = o.getItemId();
			revision = o.getRevision();
			datasetName = o.getDatasetName();
			datasetType = o.getDatasetType();
			fileName = o.getFileName();

			// Check if Item Revision exists
			IMANComponentItemRevision itemRevision = findItemRevision( imanSession,
					itemId,
					revision);
			if(itemRevision==null)
			{
				return false;
			}
			else
			{
				// Check if Dataset exists
				IMANComponentDataset ds = findDataset(itemRevision, datasetName, datasetType);
				if(ds==null)
				{
					return false;
				}
				else
				{
					// Check if Dataset contains file
					try
					{
						IMANComponentImanFile[] files = ds.getImanFiles();
						for(int i=0; i<files.length; i++)
						{
							String originalName = files[i].getProperty("original_file_name");
							if(originalName.equalsIgnoreCase(fileName))
								return true;
						}
					}
					catch ( IMANException e )
					{
						{} //System.out.println( "Unexpected exception during logout." );
						{} //System.out.println( e.toString() );
						e.printStackTrace();
					}

				}
			}
		}
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
	public final Metadata findLatest(final QxContext runtimeCtx, String itemId, Authentication id) throws Exception {

		try
		{
			HashMap tcSessions = (HashMap)getTeamcenterSessions();
			IMANSession imanSession = (IMANSession)tcSessions.get(id.getUsername());

			IMANComponentItem item = findItem(imanSession, itemId);
			if(item==null) return null;

			IMANComponentItemRevision itemRev = item.getLatestItemRevision();

			MetadataBase m = getProperties(itemRev);
			m = addBinaries(itemRev, m);

			// Create Origin
			String revision = m.get(TC10Constants.REVISION);
			Date creationDate = itemRev.getDateProperty("creation_date");
			String state = TC10Util.materializeState(m);
			String uniqueId = TC10Util.materializeItemRevUniqueID(itemRev.getUid(), itemId, revision);
			TC10ItemRevOrigin tcOrig = new TC10ItemRevOrigin(getDomainName(), getServerName(), getRepositoryName(), uniqueId, creationDate.getTime(), state);

			m.setOrigin(tcOrig);
			return m;
		}
		catch(Exception ex)
		{
			{} //System.out.println( ex.toString() );
			ex.printStackTrace();
		}
		return null;
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
	public final Metadata find(QxContext runningCtx, Origin origin, Authentication id) throws Exception
	{
    {} //System.out.println("Looking for: " +origin.getUniqueIDDisplay());

		HashMap tcSessions = (HashMap)getTeamcenterSessions();
		IMANSession imanSession = (IMANSession)tcSessions.get(id.getUsername());

		if(contains(runningCtx, origin, id))
		{
			if(origin instanceof TC10ItemOrigin)
			{
				TC10ItemOrigin o = (TC10ItemOrigin)origin;
				String itemId = o.getItemId();
				IMANComponentItem item = findItem(imanSession, itemId);
				MetadataBase m = this.getProperties(item);
				m.setOrigin(o);
				return m;
			}

			if(origin instanceof TC10ItemRevOrigin)
			{
				TC10ItemRevOrigin o = (TC10ItemRevOrigin)origin;
				String itemId = o.getItemId();
				String revision = o.getRevision();
				IMANComponentItemRevision itemRevision = findItemRevision( imanSession, itemId, revision);
				MetadataBase m = this.getProperties(itemRevision);
				m = addBinaries(itemRevision, m);
				m.setOrigin(o);
				return m;
			}

			if(origin instanceof TC10DatasetOrigin)
			{
				TC10DatasetOrigin o = (TC10DatasetOrigin)origin;
				String itemId = o.getItemId();
				String revision = o.getRevision();
				String datasetName = o.getDatasetName();
				String datasetType = o.getDatasetType();
				IMANComponentItemRevision itemRevision = findItemRevision( imanSession, itemId, revision);
				IMANComponentDataset dset = findDataset( itemRevision, datasetName, datasetType );
				MetadataBase m = this.getProperties(dset);
				m = addBinaries(dset, m);
				m.setOrigin(o);
				return m;
			}

			if(origin instanceof TC10IMANFileOrigin)
			{
				TC10IMANFileOrigin o = (TC10IMANFileOrigin)origin;
				String itemId = o.getItemId();
				String revision = o.getRevision();
				String datasetName = o.getDatasetName();
				String datasetType = o.getDatasetType();
				String fileName = o.getFileName();
				IMANComponentItemRevision itemRevision = findItemRevision( imanSession, itemId, revision);
				IMANComponentDataset dset = findDataset( itemRevision, datasetName, datasetType );
				IMANComponentImanFile[] files = dset.getImanFiles();
				for(int i=0; i<files.length; i++)
				{
					if( files[i].getProperty("original_file_name").equals(fileName) )
					{
						MetadataBase m = this.getProperties(files[i]);
						m.setOrigin(o);
						return m;
					}
				}
			}
		}
		return null;
	}

	public Collection searchLatest(QxContext runtimeCtx, String name, Authentication id) throws Exception {

		SearchAgent agent = materializeSearchAgent();
		TC10RepositorySearchAgent tc_agent = (TC10RepositorySearchAgent)agent;
		tc_agent.setAuthenticationToken(id);

		String c = "( ItemID=" + name + " )";
		tc_agent.initializeStorage(new Vector());
		tc_agent.setCriteria(c);
		tc_agent.search();

		return agent.getResults();
	}
}
