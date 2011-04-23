/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

package zws.repository.R8;

import java.util.Collection;
import java.util.HashMap;

import zws.application.Names;
import zws.data.Metadata;
import zws.origin.Origin;
import zws.origin.R8Origin;
import zws.qx.QxContext;
import zws.repository.source.RepositoryMetadataSource;
import zws.security.Authentication;

public class R8RepositoryMetadataSource extends R8RepositoryBase implements RepositoryMetadataSource {

	/**
	 * The Constructor.
	 * @param parent parentContext
	 */
	public R8RepositoryMetadataSource (QxContext parent) {
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
	public final Metadata findLatest(final QxContext runtimeCtx, String name, Authentication id) throws Exception {
		{} //System.out.println("Inside Find Latest");

		String selectAttributes = getAttributes(runtimeCtx.get(Names.SELECT_ATTRIBUTES));

		HashMap dataHM = new HashMap();
		dataHM.put("method", 		"Query-Objects");
		dataHM.put("attribute", 	translateReturnAttributes(selectAttributes));
		dataHM.put("type", 		    "wt.epm.EPMDocument");
		dataHM.put("where", 		"name='" + name + "'");

		dataHM.put("filter", 		"latest");

		String xmlResult = R8Invoke.invokeMethod(makeOriginHM(id), dataHM);
		{} //System.out.println("The result is: " + xmlResult);
		//Document document = getDocumentByString(xmlResult);
		{} //System.out.println("The state is: " + getSingleValue(document, "/wc:COLLECTION/wt.epm.EPMDocument/wc:INSTANCE/state.state"));

		//throw new zws.exception.NameNotFound(name);

		Metadata m = (Metadata) materializeMetadata(xmlResult, selectAttributes, true);

		return m;

	}



	public final Collection searchLatest(final QxContext runtimeCtx, String name, Authentication id) throws Exception {
		{} //System.out.println("Inside Find Latest");
		String selectAttributes = getAttributes(runtimeCtx.get(Names.SELECT_ATTRIBUTES));
		HashMap dataHM = new HashMap();
		dataHM.put("method", 		"Query-Objects");  // Get it from QxContext -- Later I though it should not be the case
		dataHM.put("attribute", 	translateReturnAttributes(selectAttributes));
		dataHM.put("type", 		    "wt.epm.EPMDocument"); // Get it from QxContext -- Later I though it should not be the case
		dataHM.put("where", 		"name=" + name + ""); // This is custom
		dataHM.put("filter", 		"latest");

		String xmlResult = R8Invoke.invokeMethod(makeOriginHM(id), dataHM);
		{} //System.out.println("The result is: " + xmlResult);
		//Document document = getDocumentByString(xmlResult);
		{} //System.out.println("The state is: " + getSingleValue(document, "/wc:COLLECTION/wt.epm.EPMDocument/wc:INSTANCE/state.state"));

		//throw new zws.exception.NameNotFound(name);

		Collection m = (Collection) materializeMetadata(xmlResult, selectAttributes, false);

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
	public final Metadata find(QxContext runningCtx, Origin origin, Authentication id) throws Exception {
		{} //System.out.println("Inside Find Latest");

		String selectAttributes = getAttributes(runningCtx.get(Names.SELECT_ATTRIBUTES));
		HashMap dataHM = new HashMap();
		dataHM.put("method", 		"Query-Objects");
		dataHM.put("attribute", 	translateReturnAttributes(selectAttributes));
		dataHM.put("object_ref", 	((R8Origin)origin).getOBID());

		String xmlResult = R8Invoke.invokeMethod(makeOriginHM(id), dataHM);
		{} //System.out.println("The result is: " + xmlResult);
		Metadata m = (Metadata) materializeMetadata(xmlResult, selectAttributes, true);
		return m;
	}
}
