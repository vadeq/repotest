package zws.repository.teamcenter;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on May 23, 2007 10:48:58 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.ugsolutions.aif.kernel.AIFComponentContext;
import com.ugsolutions.aif.kernel.InterfaceAIFComponent;
import com.ugsolutions.iman.kernel.IMANComponent;
import com.ugsolutions.iman.kernel.IMANComponentDataset;
import com.ugsolutions.iman.kernel.IMANComponentItemRevision;
import com.ugsolutions.iman.kernel.IMANException;
import com.ugsolutions.iman.kernel.IMANSession;

import zws.origin.Origin;
import zws.origin.TC10Origin;
import zws.origin.tc10.TC10DatasetOrigin;
import zws.origin.tc10.TC10IMANFileOrigin;
import zws.origin.tc10.TC10ItemOrigin;
import zws.origin.tc10.TC10ItemRevOrigin;
import zws.repository.teamcenter.util.TC10Util;
import zws.repository.teamcenter.util.ZWS2TC10AttributeMapper;

import zws.repository.search.RepositorySearchAgentBase;
import zws.search.criteria.Comparison;
import zws.search.criteria.parser.CriteriaParser;
import zws.search.criteria.parser.CriteriaParserBase;
import zws.security.Authentication;
import zws.util.StringMapper;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.exception.InvalidSyntax;

public class TC10RepositorySearchAgent extends RepositorySearchAgentBase
{

	// search- return result set
	// find returns exactly one
	// find itemID - returns latest Rev of that item id
	// find (origin) returns exactly by oid

	// 12345 A - directmodel[12345.jt]
	// 12345 A - IDEAS[12345.prt]
	// 12345 B
	//  - [Item] uid for 12345
	//  - [ItemRev] uid for 12345/A
	//  - [Dataset] uid for 12345/A/directmodel[datasetname]
	//  - [IMAN File] uid for 12345/A/directmodel/12345.jt

	// Origin: domainname | servername | repositoryType | repositoryName | uniqueidentifer
	// +++ Create the following TCOrigin:
	//  - [TC10ItemOrigin]     unique identifiers: uid | itemID
	//  - [TC10ItemRevOrigin]  unique identifiers: uid | itemID | Rev
	//  - [TC10DatasetOrigin]  unique identifiers: uid | itemID | Rev | datasettype | dataset name
	//  - [TC10IMANFileOrigin] unique identifiers: uid | itemID | Rev | datasettype | dataset name | filename
	//  eg domainname | servername | repositoryType | repositoryName | itemID | Rev | datasettype | dataset name | filename | timestamp

	// ++search Specifications
	// input -
	//            criteria: see below for criteria support
	//            query-target - specifies object to execute query on
	//            select - specifies attributes to bring back (tbd -  bring back all atts for now)
	//            * datedAfter
	//            * datedAfter
	//
	// *return : metadata objecgt based on value of "query-on"
	//            default: query-target=item-revision
	//            query-target = itemid
	//            * query-target = item-revision
	//            x query-target = dataset (low-or-no priority)
	//            x query-target = iman-file (low-or-no priority)

	// definition of criteria grammar to support:
	// + Create a reference for default attributes that can be queried on
	//   define a map containing all possible attributes that can be queried on
	//
	// ItemID search - wildcard searche by name
	//                 (only support comparison op '=') (no 'or' or and support) ( no attributes except for itemID)
	//                 for modifiedafter modifiedbefore: use agent.getDatedAfter() & agent.getDatedBefore();
	// * simple search - wildcard searches by any number of fields anded together
	//                 (only support comparison op '=') (support for 'and' included) (no 'or' support)
	//                 for modifiedafter modifiedbefore: use agent.getDatedAfter() & agent.getDatedBefore();
	// complex search - wildcard searches by any number of fields groups contain only and expressions, grops can be or'd together
	//                 (only support comparison op '=') (support for 'and' and 'or' included)
	// complete search - support any combination of group, ands and ors and any appropriate comparison operator


	// search:
	// criteriea: simple search include dated after/before (only support and)
	// query-target: itemrevision

	public void executeQuery() throws Exception
	{
    {} //System.out.println("TC10 execute query...");
		try
		{
			//routing info for each result is the same and can be retrieved fron the agents repository.
			//A search agent repository is configured when an agent is instantiated.
			String domainName = this.getRepository().getDomainName();
			String serverName = this.getRepository().getServerName();
			String repositoryName = this.getRepository().getRepositoryName();

			Authentication id = this.getAuthenticationToken();

			TC10RepositoryBase tc10 = (TC10RepositoryBase)this.getRepository();
      {} //System.out.println("getting session...");
			IMANSession imanSession = (IMANSession)tc10.getTeamcenterSessions().get(id.getUsername());//tc10.login(id);
      {} //System.out.println("session..." + imanSession);

			//parser.getORList(); - for now double check that this is null or empty (no or support) else throw unsuported operation
			//Collection c = getCriteria().getParser().getComparisons();
			//iterate on c
			//Comparison comp;
			//comp.getOperator() - check to see that this is an equal - else throw zws.exception.UnsupportedOperation
			//comp.getFieldName();
			//comp.getValue();

			if (null==this.getCriteria() || 0==getCriteria().toString().length()) return;

{} //System.out.println("Searching...");
{} //System.out.println("Criteria: " + getCriteria());

			//execute search and create result list: need an Origin and Metadata for each result.
			Collection c = getCriteria().getParser().getComparisons();
			String[] keys = new String[c.size()];
			String[] values = new String[c.size()];

			Iterator it = c.iterator();
			int inx = 0;
			while(it.hasNext())
			{
				Comparison comp = (Comparison)it.next();
//{} //System.out.println("Comp: "+comp.toString());
				comp.toString();
				keys[inx] = comp.getFieldNameMapper().getMappedValue();
//{} //System.out.println("Key: "+keys[inx]);
				values[inx] = comp.getValue();
//{} //System.out.println("Value: "+values[inx]);
				inx++;
			}

			InterfaceAIFComponent[] qryResults = queryTeamcenter(imanSession, keys, values);

			if (qryResults!=null)
			{
				for(int i=0; i<qryResults.length; i++)
				{
//{} //System.out.println(i+" : "+qryResults.length);
					//	Now materialize the Metadata with this origin and values from the search result
					IMANComponent comp = (IMANComponent)qryResults[i];
					MetadataBase m = tc10.getProperties(comp);

					//Materialize state for origin
					String state = TC10Util.materializeState(m);
					//Creation Date
					Date creationDate = comp.getDateProperty("creation_date");

					/*
					//construct a unique id to materialize an origin
					//unique id format: itemId | revision | datasetName | datasetType | fileName
					//String uniqueId = TC10RepositoryBase.materializeUniqueID(itemId, revision, datasetName, datasetType, fileName);
					TC10Origin tcOrig = (TC10Origin)OriginMaker.materialize( domainName,
							serverName,
							Origin.FROM_TEAMCENTER_10,
							repositoryName, t.getTime(),
							uniqueId+delim,
							null,
							state);
					*/

					TC10Origin tcOrig = null;
					if( getQueryTarget().equals(TC10Constants.CLASS_ITEM) )
					{
						// Materialize uniqueId for origin
						//String itemId = m.get("item_id");
						String itemId = m.get(TC10Constants.ITEM_ID);
						String uniqueId = TC10Util.materializeItemUniqueID(comp.getUid(), itemId);
						tcOrig = new TC10ItemOrigin(domainName, serverName, repositoryName, uniqueId, creationDate.getTime(), state);
					}
					else if( getQueryTarget().equals(TC10Constants.CLASS_DATASET) )
					{
						// Materialize uniqueId for origin
						//String itemId = m.get("item_id");
						String itemId = m.get(TC10Constants.ITEM_ID);
						String revision = m.get(TC10Constants.REVISION);//String revision = m.get("item_revision_id");
						String datasetName = m.get(TC10Constants.NAME);//String datasetName = m.get("object_name");
						String datasetType= m.get(TC10Constants.TYPE);//String datasetType= m.get("object_type");
						String uniqueId = TC10Util.materializeDatasetUniqueID(comp.getUid(), itemId, revision, datasetType, datasetName);
						tcOrig = new TC10DatasetOrigin(domainName, serverName, repositoryName, uniqueId, creationDate.getTime(), state);

						// Add Binaries
						IMANComponentDataset dsComp = (IMANComponentDataset)comp;
						m = tc10.addBinaries(dsComp, m);

					}
					else if( getQueryTarget().equals(TC10Constants.CLASS_IMANFILE) )
					{

						AIFComponentContext[] referencedComps = comp.whereReferenced();
						for(int j=0; j<referencedComps.length; j++)
						{
							IMANComponent refComp = (IMANComponent)referencedComps[j].getComponent();
							if( refComp.getProperty("item_id")!=null && !refComp.getProperty("item_id").equals("") &&
								refComp.getProperty("item_revision_id")!=null && !refComp.getProperty("item_revision_id").equals("") )
							{
								// Materialize uniqueId for origin
								String itemId = refComp.getProperty("item_id");
								String revision = refComp.getProperty("item_revision_id");
								String datasetName = refComp.getProperty("object_name");
								String datasetType= refComp.getProperty("object_type");
								String fileName = m.get("original_file_name");
								String uniqueId = TC10Util.materializeIMANFileUniqueID(comp.getUid(), itemId, revision, datasetType, datasetName, fileName);
								tcOrig = new TC10IMANFileOrigin(domainName, serverName, repositoryName, uniqueId, creationDate.getTime(), state);
								break;
							}
						}

					}
					else
					{
						// Materialize uniqueId for origin
						//String itemId = m.get("item_id");
						//Need to use
						//String itemId = m.get("item_id");
						String itemId = m.get(TC10Constants.ITEM_ID);
						String revision = m.get(TC10Constants.REVISION);
						String uniqueId = TC10Util.materializeItemRevUniqueID(comp.getUid(), itemId, revision);
						tcOrig = new TC10ItemRevOrigin(domainName, serverName, repositoryName, uniqueId, creationDate.getTime(), state);
						if(null == m.getName()) {
							{} //System.out.println("metadata " + m);
							throw new Exception("Name is not set in the metadata");
						}
						tcOrig.setName(m.getName());
						// Add Binaries
						IMANComponentItemRevision revComp = (IMANComponentItemRevision)comp;
						m = tc10.addBinaries(revComp, m);
					}

{} //System.out.println(tcOrig.getDatasourceType()+": " + tcOrig.toString() );
//{} //System.out.println(m);

					m.setOrigin(tcOrig);
//{} //System.out.println("before storing.. meatadata is " + m);
					store(m);
//{} //System.out.println("Now holding "+getResults().size()+" objects");
				}
			}
			//tc10.logout(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setDeleteOutput(boolean b) { deleteOutput=b; }
	public boolean getDeleteOutput() { return deleteOutput; }

	public void setQueryTarget(String queryTarget) {this.queryTarget = queryTarget;}
	public String getQueryTarget() {return queryTarget;}

	public void addSystemAttributes(Metadata data) {
		super.addSystemAttributes(data);
	}

	public void setCriteria(String s) throws InvalidSyntax
	{

		if (null==s) throw new InvalidSyntax("Criteria is null");

		CriteriaParser parser = new CriteriaParserBase();

		//parser.displayAsANDOperator("&");
		// parser.displayValueInQuotes(false);
		parser.displayValueInQuotes(false);
		StringMapper zws2Tc10 = new ZWS2TC10AttributeMapper();
		parser.setFieldNameMapper(zws2Tc10);
		setCriteria(parser.parse(s));

	}


	private InterfaceAIFComponent[] queryTeamcenter(IMANSession session, String[] keys, String[] values)
	{
		try
		{
			String[] entryNames = keys;
			String[] entryValues = values;
			InterfaceAIFComponent res[] = null;

			if( getQueryTarget().equals(TC10Constants.CLASS_ITEM) )
				res = session.search("Item...", entryNames, entryValues);
			else if( getQueryTarget().equals(TC10Constants.CLASS_DATASET) )
				res = session.search("Dataset...", entryNames, entryValues);
			else if( getQueryTarget().equals(TC10Constants.CLASS_IMANFILE) )
				res = session.search("ImanFile...", entryNames, entryValues);
			else
				res = session.search("Item Revision...", entryNames, entryValues);

			if ( res==null || res.length==0 ){return null;}
			else {return res;}
		}
		catch(IMANException iex)
		{
			{} //System.out.println( "TC ERROR: " + iex.toString() );
			iex.printStackTrace();
		}
		catch(Exception ex)
		{
			{} //System.out.println( ex.toString() );
			ex.printStackTrace();
		}

		return null;
	}

	private boolean deleteOutput = true;
	private String queryTarget = TC10Constants.CLASS_ITEM_REV;
	private static String delim = Origin.delim;

}
