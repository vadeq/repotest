package zws.qx;

import zws.application.Names;
import zws.application.Properties;
import zws.data.Metadata;
import zws.data.MetadataBinary;
import zws.origin.Origin;
import zws.origin.tc10.TC10IMANFileOrigin;
import zws.origin.tc10.TC10ItemRevOrigin;
import zws.repository.Repository;
import zws.repository.RepositoryBase;
import zws.repository.source.RepositoryBinarySource;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.source.RepositoryStructureSource;
import zws.repository.teamcenter.proxy.TC10ProxyRepositoryBase;
import zws.search.SearchAgent;
import zws.security.Authentication;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;
import zws.util.PrintUtil;
import zws.util.WaitForThreads;
import java.util.Collection;
import java.util.Iterator;

public class Test {
  private Test() { }
  //Authentication id = new Authentication("infodba45", "infodba78");
  //String uniqueId="112233|4545|A";
  public static void main(String[] args) {
   /* if (3>=args.length) {
      {} //System.out.println("usage: find user pwd itemID rev ");
      {} //System.out.println("usage: search user pwd criteria");
      {} //System.out.println("usage: download user pwd itemID rev datasetName datasetType fileName");
      System.exit(1);
    }*/
    configure();
    String command = null;
    String user=null;
    String password=null;
    Authentication id=null;
    try {
    if (args.length != 0) {
       command = args[0];
       user=args[1];
       password=args[2];
       id = new Authentication(user, password);
    }



    Test i = new Test();


      if ("find".equalsIgnoreCase(command)) {
        if (5!=args.length) {
          {} //System.out.println("usage: find user pwd itemID rev ");
        }
        i.find(args[3], args[4], id);
      } else if ("search".equalsIgnoreCase(command)) {
        if (4!=args.length) {
          {} //System.out.println("usage: search user pwd criteria");
        }
        i.search(args[3], id);
      } else if ("download".equalsIgnoreCase(command)) {
        if (8!=args.length) {
          {} //System.out.println("usage: download user pwd itemID rev datasetType datasetName fileName");
        }
        i.download(args[3], args[4], args[5], args[6], args[7], id);
      } else if ("reportBOM".equalsIgnoreCase(command)) {
          if (6!=args.length) {
            {} //System.out.println("usage: reportBOM user pwd itemID rev configuration");
          }
          i.reportBOM(args[3], args[4], args[5], id);
      } else {
        i.run();
      }
    }
    catch(Exception e) {
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
    PrintUtil.print(c);

    {} //System.out.println("******TEST RESULTS*******");
    {} //System.out.println(c);
    {} //System.out.println("******TEST RESULTS*******");
  }

  private void run() throws Exception {
    Properties.set(Names.SERVICE_FINDER_HOSTNAME, "plm-rtp-002-d");
    RepositoryService c = RepositoryClient.getClient();
    Repository rep = c.findRepository("TC-10");
    //RepositoryBase rep = proxyRepository();
    RepositoryMetadataSource source = rep.materializeMetadataSource();
    RepositoryBinarySource binSource = rep.materializeBinarySource();
    Authentication id = new Authentication("user31","user31");
    Metadata data = source.findLatest(context(), "000029", id);
    if(null == data.getBinaries() ) {
      {} //System.out.println("No binaries");
      return;
    }
    Iterator binItr = data.getBinaries().iterator();
    Metadata metadataBinary = (MetadataBinary) binItr.next();
    Origin fileOrigin = metadataBinary.getOrigin();
    /*RemotePackage remoteFile = binSource.fetchDesignFilesLocally(context(), fileOrigin, id);
    FileDepotClient depotClient = FileDepotClient.materializeClient(Properties.get(Names.FILE_DEPOT_HOSTNAME), remoteFile.getLocation());
    File file = depotClient.retrieve(remoteFile, new File("C:\\test"));*/

  }
  private void download(String itemID, String rev, String datasetName, String datasetType, String fileName, Authentication id) throws Exception {

    RepositoryBase rep = proxyRepository();
    RepositoryBinarySource source = rep.materializeBinarySource();
    TC10IMANFileOrigin tcOrig = fileOrigin(source, itemID, rev, datasetName, datasetType, fileName);
    //File bin = source.download(context(),tcOrig, id);

    {} //System.out.println("******TEST RESULTS*******");
    //{} //System.out.println("downloaded to: "+bin.getAbsolutePath());
    {} //System.out.println("******TEST RESULTS*******");
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
    Properties.set(Names.WORKING_DIR, "C:"+Names.PATH_SEPARATOR + "zws" +Names.PATH_SEPARATOR + "log" +Names.PATH_SEPARATOR + "work");
    Properties.set("tc10-qx-service-exe", "C:"+Names.PATH_SEPARATOR + "zws" +Names.PATH_SEPARATOR + "env" +Names.PATH_SEPARATOR + "ugs"+Names.PATH_SEPARATOR + "tc-10"+Names.PATH_SEPARATOR + "bin"+Names.PATH_SEPARATOR + "TC10QxService.bat");
  }

  private static RepositoryBase proxyRepository() {
    RepositoryBase rep = new TC10ProxyRepositoryBase();
    String domainName="cisco";
    String serverName="node-0";
    String repositoryName="TC-10";
    rep.setDomainName(domainName);
    rep.setServerName(serverName);
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

}
