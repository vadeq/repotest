package zws.service.repository;

import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.qx.program.QxInstruction;
import zws.qx.service.QxServiceFinder;
import zws.qx.xml.QxXML;
import zws.repository.RepositoryBase;


import java.util.ArrayList;
import java.util.Collection;


/**
 * The Class CommonClient.
 */
public class RepositoryClient implements RepositoryService {

  /**
   * The Constructor.
   */
  private RepositoryClient() { }

  /**
   * Gets the client.
   *
   * @param hostName the host name
   *
   * @return the client
   */
  public static RepositoryClient getClient() {
    return new RepositoryClient();
  }

  /**
   * findRepository.
   *
   * @param repositoryName the repository name
   *
   * @return the repository base
   */
  public final RepositoryBase findRepository(String repositoryName) {
    QxXML result = null;
    RepositoryBase repBase = null;
    try {
      /*QxContext ctx = new QxContext();
      this.prepareContext(ctx);
      // create XML data instruction and call execteQX
      QxWebClient webClient = QxWebClient.materializeClient();*/
      
      QxServiceFinder finder = QxServiceFinder.materializeFinder("repository-service");
      QxContext ctx = finder.prepareQxWebClientContext("zws.qx.service","RepositoryQxService");
      QxWebClient webClient = (QxWebClient) finder.materializeClient();    
      
      QxXML dataInstruction = new QxXML("<find-repository repositoryName=\"" + repositoryName + "\"/>");
      {}//Logwriter.printOnConsole("instruction from findRepository" + dataInstruction);
      result = webClient.executeQx(ctx, dataInstruction);
      {} //System.out.println("findRepository " + result);
      RepositoryMaker repMaker = new RepositoryMaker();
      repBase = repMaker.getRepository(result);
    } catch (Exception e) {
      e.printStackTrace();
      {}//Logwriter.printOnConsole("Error in FindRepository.findRepository");
    }
    return repBase;
  }

  /**
   * findRepository.
   *
   * @return the collection
   */
  public Collection listRepositories() {
    QxXML result = null;
    ArrayList rNames = null;
    try {
      /*QxContext ctx = new QxContext();
      prepareContext(ctx);
      // create XML data instruction and call execteQX
      QxWebClient webClient = QxWebClient.materializeClient();*/

      QxServiceFinder finder = QxServiceFinder.materializeFinder("repository-service");
      QxContext ctx = finder.prepareQxWebClientContext("zws.qx.service","RepositoryQxService");
      QxWebClient webClient = (QxWebClient) finder.materializeClient();    
      
      QxXML dataInstruction = new QxXML("<find-repository repositoryName=\"getList\"/>");
      {}//Logwriter.printOnConsole("instruction from listRepositories" + dataInstruction);
      result = webClient.executeQx(ctx, dataInstruction);
      {} //System.out.println("listRepositories " + result);
      // convert result to collection of strings
      QxInstruction insRepository = result.toQxProgram();
      String repositoryName = insRepository.get("repList");
      {} //System.out.println("from QxXML.... " + repositoryName);
      if (null != repositoryName) {
        String[] names = repositoryName.split(",");
        rNames = new ArrayList();
        for (int idx = 0; idx < names.length; idx++) {
          rNames.add(names[idx]);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      {}//Logwriter.printOnConsole("Error in FindRepository.listRepositories");
    }
    return rNames;
  }

  /**
   * Prepare context.
   *
   * @param ctx the ctx
   */
  /*private void prepareContext(QxContext ctx) {
    ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.service");
    ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "RepositoryQxService");
    ctx.set(QxContext.SOAP_HOSTNAME, host);
    ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
    ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
    ctx.set(QxContext.SOAP_SERVICE_OPERATION, "runQx");
  }*/

}


