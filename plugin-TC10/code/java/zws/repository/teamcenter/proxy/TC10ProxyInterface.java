package zws.repository.teamcenter.proxy;

import zws.application.Names;
import zws.application.Properties;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.origin.Origin;
import zws.origin.tc10.TC10IMANFileOrigin;
import zws.origin.tc10.TC10ItemRevOrigin;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.repository.RepositoryBase;
import zws.repository.source.RepositoryBinarySource;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.source.RepositoryStructureSource;
import zws.repository.target.RepositoryMetadataTarget;
import zws.repository.teamcenter.TC10Constants;
import zws.search.SearchAgent;
import zws.security.Authentication;
import zws.service.file.depot.FileDepotClient;
import zws.util.PrintUtil;
import zws.util.RemotePackage;
import zws.util.WaitForThreads;

import java.io.File;
import java.util.Collection;

public class TC10ProxyInterface {

	private TC10ProxyInterface() {
	}

	public static TC10ProxyInterface getTC10ProxyInterface() {
		return ref;
	}

	public static void main(String[] args) {

		if (3 >= args.length) {
			{} //System.out.println("usage: find user pwd itemID rev ");
			{} //System.out.println("usage: search user pwd criteria");
			{} //System.out.println("usage: download user pwd itemID rev datasetName datasetType fileName");
      {} //System.out.println("usage: reportBOM user pwd itemID rev configuration");
      {} //System.out.println("usage: create user pwd itemID rev itemType datasetName datasetType fileName");
      {} //System.out.println("usage: update user pwd itemID rev datasetName datasetType fileName");
      {} //System.out.println("usage: rename user pwd oldItemID newItemID");
      {} //System.out.println("usage: add user pwd fileName ");
      {} //System.out.println("usage: update user pwd fileName ");
      {} //System.out.println("usage: AddAttachment user pwd itemID rev datasetName datasetType fileName");
      {} //System.out.println("usage: updateAttachment user pwd itemID rev datasetName datasetType fileName");
      
			System.exit(1);
		}
		configure();
		String command = args[0];
		String user = args[1];
		String password = args[2];
		Authentication id = null;
		try {
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		TC10ProxyInterface i = new TC10ProxyInterface();
		try {
      
			id = new Authentication(user, password);
      
      /* Source Module Test Cases */
			if (TC10Constants.TAG_FIND.equalsIgnoreCase(command)) {
				if (5 != args.length) {
					{} //System.out.println("usage: find user pwd itemID rev ");
					System.exit(1);
				}
				i.find(args[3], args[4], id);
			}
      if (TC10Constants.TAG_FIND_LATEST.equalsIgnoreCase(command)) {
        if (4 != args.length) {
          {} //System.out.println("usage: find-latest user pwd itemID ");
          System.exit(1);
        }
        i.findLatest(args[3], id);
      }
			if (TC10Constants.TAG_SEARCH.equalsIgnoreCase(command)) {
				if (4 != args.length) {
					{} //System.out.println("usage: search user pwd criteria");
					System.exit(1);
				}
				i.search(args[3], id);
			}
			if (TC10Constants.TAG_FETCH_NATIVE_FILE.equalsIgnoreCase(command)) {
				if (8 != args.length) {
					{} //System.out.println("usage: download user pwd itemID rev datasetType datasetName fileName");
					System.exit(1);
				}
				i.FetchNativeFile(args[3], args[4], args[5], args[6], args[7], id);
			}
      if (TC10Constants.TAG_FETCH_DESIGN_FILES.equalsIgnoreCase(command)) {
        if (8 != args.length) {
          {} //System.out.println("usage: download user pwd itemID rev datasetType datasetName fileName");
          System.exit(1);
        }
        i.fetchDesignFiles(args[3], args[4], args[5], args[6], args[7], id);
      }
			if (TC10Constants.TAG_REPORT_BOM.equalsIgnoreCase(command)) {
				if (6 != args.length) {
					{} //System.out.println("usage: reportBOM user pwd itemID rev configuration");
					System.exit(1);
				}
				i.reportBOM(args[3], args[4], args[5], id);
			}
      
      /* Target Module Test Cases */
      if (TC10Constants.TAG_CREATE.equalsIgnoreCase(command)) {
        if (5 > args.length) {
          {} //System.out.println("usage: create user pwd itemID rev itemType datasetType datasetName fileName");
          System.exit(1);
        }
        i.create(args[3], args[4], args[5], args[6], args[7], args[8], id);
      }
      if (TC10Constants.TAG_UPDATE.equalsIgnoreCase(command)) {
        if (5 > args.length) {
          {} //System.out.println("usage: update user pwd itemID rev datasetName datasetType fileName");
          System.exit(1);
        }
        i.update(args[3], args[4], args[5], args[6], args[7], id);
      }
      if (TC10Constants.TAG_RENAME.equalsIgnoreCase(command)) {
        if (5 != args.length) {
          {} //System.out.println("usage: rename user pwd oldItemID newItemID");
          System.exit(1);
        }
        i.rename(args[3], args[4], id);
      }if (TC10Constants.TAG_ADD_BINARY.equalsIgnoreCase(command)) {
        if (4 != args.length) {
          {} //System.out.println("usage: addBinary user pwd fileName ");
          System.exit(1);
        }
        i.addBinary(args[3], id);
      }if (TC10Constants.TAG_UPDATE_BINARY.equalsIgnoreCase(command)) {
        if (4 != args.length) {
          {} //System.out.println("usage: updateBinary user pwd fileName ");
          System.exit(1);
        }
        i.updateBinary(args[3], id);
      }if (TC10Constants.TAG_ADD_ATTACHMENT.equalsIgnoreCase(command)) {
        if (8 != args.length) {
          {} //System.out.println("usage: AddAttachment user pwd itemID rev datasetName datasetType fileName");
          System.exit(1);
        }
        i.addAttachment(args[3], args[4], args[5], args[6], args[7], id);
      }if (TC10Constants.TAG_UPDATE_ATTACHMENT.equalsIgnoreCase(command)) {
        if (8 != args.length) {
          {} //System.out.println("usage: updateAttachment user pwd itemID rev datasetName datasetType fileName");
          System.exit(1);
        }
        i.updateAttachment(args[3], args[4], args[5], args[6], args[7], id);
      }
		} catch (Exception e) {
			e.printStackTrace();
			{} //System.out.println("------------------------------------------------------");
			{} //System.out.println(e.getMessage());
			{} //System.out.println("------------------------------------------------------");
		}

	}
	private void find(String itemID, String rev, Authentication id) throws Exception {

		RepositoryBase rep = proxyRepository();
		RepositoryMetadataSource source = rep.materializeMetadataSource();
		TC10ItemRevOrigin tcOrig = revOrigin(source, itemID, rev);
		Metadata m = source.find(context(),tcOrig, id);

		{} //System.out.println("******TEST RESULTS*******");
		{} //System.out.println(m);
		{} //System.out.println("******TEST RESULTS*******");

	}

  private void findLatest(String itemId, Authentication id) throws Exception {

    RepositoryBase rep = proxyRepository();
    RepositoryMetadataSource source = rep.materializeMetadataSource();
    Metadata m = source.findLatest(context(),itemId, id);

    {} //System.out.println("******TEST RESULTS*******");
    {} //System.out.println(m);
    {} //System.out.println("******TEST RESULTS*******");

  }

	private void search(String criteria, Authentication id) throws Exception {

		RepositoryBase rep = proxyRepository();
		SearchAgent agent = rep.materializeSearchAgent();
		agent.setAuthenticationToken(id);
		agent.setCriteria(criteria);
		agent.initializeStorage();
		agent.execute();

		WaitForThreads waiter = new WaitForThreads();
		waiter.addThread(agent.getThread());
		waiter.run();

		Collection c = agent.getResults();
		PrintUtil.print("agentResults from TC10ProxyInterface.search " +c);

		{} //System.out.println("******TEST RESULTS*******");
		{} //System.out.println(c);
		{} //System.out.println("******TEST RESULTS*******");
	}

	private void FetchNativeFile(String itemID, String rev, String datasetName, String datasetType, String fileName, Authentication id) throws Exception {
		RepositoryBase rep = proxyRepository();
		RepositoryBinarySource source = rep.materializeBinarySource();
		TC10IMANFileOrigin tcOrig = fileOrigin(source, itemID, rev, datasetName, datasetType, fileName);
		RemotePackage bin = source.fetchNativeFile(context(),tcOrig, id);
    String localTempDir = Properties.get(Names.TEMP_DIR) + Names.PATH_SEPARATOR + "TC-10";
    String fetchDir = localTempDir + Names.PATH_SEPARATOR + "fetch";
    FileDepotClient c = FileDepotClient.materializeClient(localTempDir);
    c.retrieve(bin, new File(fetchDir));
	}

  private void fetchDesignFiles(String itemID, String rev, String datasetName, String datasetType, String fileName, Authentication id) throws Exception {
    RepositoryBase rep = proxyRepository();
    RepositoryBinarySource source = rep.materializeBinarySource();
    TC10IMANFileOrigin tcOrig = fileOrigin(source, itemID, rev, datasetName, datasetType, fileName);
    RemotePackage bin = source.fetchDesignFiles(context(),tcOrig, id);
    String localTempDir = Properties.get(Names.TEMP_DIR) + Names.PATH_SEPARATOR + "TC-10";
    String fetchDir = localTempDir + Names.PATH_SEPARATOR + "fetch";
    FileDepotClient c = FileDepotClient.materializeClient(localTempDir);
    c.retrieve(bin, new File(fetchDir));
  }

  
	private void reportBOM(String itemID, String rev, String configuration, Authentication id) throws Exception {
		RepositoryBase rep = proxyRepository();
		RepositoryStructureSource source = rep.materializeStructureSource();
		TC10ItemRevOrigin tcOrig = revOrigin((Repository)source, itemID, rev);
		Metadata m = source.reportBOM(context(), tcOrig, id);

		{} //System.out.println("******TEST RESULTS*******");
		{} //System.out.println(m);
		{} //System.out.println("******TEST RESULTS*******");
	}

	private static void configure() {
		Properties.set(Names.WORKING_DIR, Names.PATH_SEPARATOR + "zws" +Names.PATH_SEPARATOR + "tc10");
	}

  private void create( String itemID, String rev, String itemType, 
                       String datasetName, String datasetType, String fileName, 
                       Authentication id) throws Exception {

    RepositoryBase rep = proxyRepository();
    RepositoryMetadataTarget target = rep.materializeMetadataTarget();
    
    Metadata data = new MetadataBase();
    
    Origin o = target.create(context(), data, id);

    {} //System.out.println("******TEST RESULTS*******");
    {} //System.out.println(o);
    {} //System.out.println("******TEST RESULTS*******");

  } 
  
  private void update( String itemID, String rev, String datasetName, String datasetType, String fileName, Authentication id) throws Exception {

    RepositoryBase rep = proxyRepository();
    
    RepositoryMetadataTarget target = rep.materializeMetadataTarget();
    
    Metadata dataIn = new MetadataBase();
    Metadata dataOut = new MetadataBase();
    File f = new File(fileName);
    
    dataOut = target.update(context(), dataIn, f, id);    
    
    {} //System.out.println("******TEST RESULTS*******");
    {} //System.out.println(dataOut);
    {} //System.out.println("******TEST RESULTS*******");

  }
  
  private void rename( String olditemID, String newItemID, Authentication id) throws Exception {

    RepositoryBase rep = proxyRepository();
    
    {} //System.out.println("******TEST RESULTS*******");
    {} //System.out.println("******TEST RESULTS*******");
  } 
  
  private void addBinary( String filename, Authentication id) throws Exception {

    RepositoryBase rep = proxyRepository();
    
    {} //System.out.println("******TEST RESULTS*******");
    {} //System.out.println("******TEST RESULTS*******");
  }
  
  private void updateBinary( String filename, Authentication id) throws Exception {

    RepositoryBase rep = proxyRepository();
    
    {} //System.out.println("******TEST RESULTS*******");
    {} //System.out.println("******TEST RESULTS*******");
  }
  
  private void addAttachment( String itemID, String rev, String datasetName, String datasetType, String fileName, Authentication id) throws Exception {

    RepositoryBase rep = proxyRepository();
    
    {} //System.out.println("******TEST RESULTS*******");
    {} //System.out.println("******TEST RESULTS*******");

  }
  
  private void updateAttachment( String itemID, String rev, String datasetName, String datasetType, String fileName, Authentication id) throws Exception {

    RepositoryBase rep = proxyRepository();
    
    {} //System.out.println("******TEST RESULTS*******");
    {} //System.out.println("******TEST RESULTS*******");

  }
  
	private static RepositoryBase proxyRepository() {
/*
 *   <tc10-proxy name="TC-10" system-password="user31" system-username="user31"
    domain-name="zws" server-name="node-0"
    protocol="http" host-name="designstate-0" port="80"
    services-path="ZeroWait-State/services"/>
 */
		RepositoryBase rep = new TC10ProxyRepositoryBase();
		String repositoryName="TC-10";
    rep.setDomainName("cisco");
    rep.setHostName("plm-rtp-002-d");
    rep.setPort("80");
    rep.setProtocol("http");
    rep.setServerName("node-0");
    rep.setServicesPath("ZeroWait-State/services");
    rep.setSystemPassword("user31");
    rep.setSystemUsername("user31");
    rep.setRepositoryName(repositoryName);

		return rep;
	}

	private static TC10ItemRevOrigin revOrigin(Repository rep, String itemID, String rev) throws Exception {
		String uniqueId="AAAAAAAAAAAAAA|"+itemID+"|" + rev;
		long timeOfCreation=8888;
		String state="NA";
		TC10ItemRevOrigin tcOrig = new TC10ItemRevOrigin(rep.getDomainName(), rep.getServerName(), rep.getRepositoryName(), uniqueId,timeOfCreation, state);
		return tcOrig;
	}

	private static TC10IMANFileOrigin fileOrigin(Repository rep, String itemID, String rev, String datasetName, String datasetType, String fileName) throws Exception {
		String uniqueId="AAAAAAAAAAAAAA|"+itemID+"|" + rev+"|"+datasetName+"|"+datasetType+"|"+fileName+"|";
		long timeOfCreation=8888;
		String state="NA";
		TC10IMANFileOrigin tcOrig = new TC10IMANFileOrigin(rep.getDomainName(), rep.getServerName(), rep.getRepositoryName(), uniqueId,timeOfCreation, state);
		return tcOrig;
	}

	private QxContext context() {
		QxContext ctx = new QxContext();
		ctx.set("run-time", "variable");
		return ctx;
	}
	private static TC10ProxyInterface ref = new TC10ProxyInterface();

}
