/*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Feb 22, 2007
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
package zws.repository.R8;


import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.origin.R8Origin;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.source.RepositoryStructureSource;
import zws.security.Authentication;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;
import zws.application.Names;
import zws.application.Properties;
import zws.data.Metadata;

import java.util.Collection;

public class R8LocalTest {
	public R8LocalTest() {
		repositoryClient = RepositoryClient.getClient();
	}
//	private static String hostName = "ilinkuser-training.ptc.com"; 
//	windchill -classpath=C:\zws-dojo\Workspace\plugin-ilink8\out;C:\zws-dojo\Workspace\zws-client\out;C:\zws-dojo\Workspace\plugin-zws-interface zws.repository.R8.R8LocalTest

	public static void main(String[] args) throws Exception {
		R8LocalTest x = new R8LocalTest();
		//x.testSearching();
		//x.testBinaryDownload();
		//x.generateBom();
		//x.testFind();
		x.generateBom();
		x.reportReferences();
	}


	private Authentication auth() throws Exception {
		Authentication auth = new Authentication("mmanager", "mmanager");
		return auth;
	}
	private Repository getRepository() throws Exception { 
		String hostname = "ilinkuser-training.ptc.com";
		Properties.set(Names.SERVICE_FINDER_HOSTNAME, hostname);
		RepositoryService c = RepositoryClient.getClient();
		Repository r = c.findRepository(hostname);
		return r;
	}

	private Repository getRepositoryBase() throws Exception { 
		String ilinkServerDomain="com.ptc";//serverside domain that shows up in attribute field names 
		String hostname = "ilinkuser-training.ptc.com";
		String systemPassword="mmanager";
		String systemUsername="mmanager";

		R8RepositoryBase r = new R8RepositoryBase();
		r.setIlinkServerDomain(ilinkServerDomain); 
		r.setHostName(hostname); 
		r.setSystemPassword(systemPassword);
		r.setSystemUsername(systemUsername);
		r.setName("ilink-8");
		r.setDomainName("zws"); 
		r.setServerName("node-0");
		r.setProtocol("http");
		r.setPort("80");
		r.setRemoteMethod("zws-ilink-interface"); 
		r.setDownloadServicesPath("ilinkuser/netmarkets/jsp/zws/downloadpdmcontent.jsp"); 
		r.setServicesPath("ilinkuser/servlet/RPC?CLASS=com.infoengine.soap");
		return r;

	}

	public void generateBom() throws Exception{
		Repository r = getRepositoryBase();
		QxContext runningCtx = new QxContext();
		RepositoryStructureSource structure = r.materializeStructureSource();
		RepositoryMetadataSource source = r.materializeMetadataSource();
		Metadata md = source.findLatest(runningCtx, "LOWER_SUPPORT.ASM", auth());
		runningCtx.set("depth", "999999");
		Metadata bom = structure.reportBOM(runningCtx, md.getOrigin(), auth());
		{} //System.out.println("bom " + bom);
	}

	public void reportReferences() throws Exception{
		Repository r = getRepositoryBase();
		QxContext runningCtx = new QxContext();
		RepositoryStructureSource structure = r.materializeStructureSource();
		RepositoryMetadataSource source = r.materializeMetadataSource();
		String returnAttributes = "name,number,state.state,versionInfo.identifier.versionId,containerName,iterationInfo.identifier.iterationId,thePersistInfo.modifyStamp,wt.epm.EPMDocument,depType";
		runningCtx.set("return_attributes", returnAttributes);
		R8Origin o = (R8Origin) OriginMaker.materialize("zws|node-0|ilink-8|PDMLink|-1|VR:wt.epm.EPMDocument:78299|0000000001.ASM|null|A|4|0000000001.asm|N/A|N/A|N/A|N/A");
		Collection references = structure.reportDependencies(runningCtx, o, auth());
		{} //System.out.println("References " + references);
	}


	public static long oid = 88888888;
	private static String delim = Origin.delim;
	RepositoryService repositoryClient = null;
	Repository r8 = null;
}
