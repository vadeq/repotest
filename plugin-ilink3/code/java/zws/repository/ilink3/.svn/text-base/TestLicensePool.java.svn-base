package zws.repository.ilink3;
/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Mar 18, 2007 6:52:38 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.application.Names;
import zws.application.Properties;
import zws.data.Metadata;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.source.RepositoryStructureSource;

import zws.search.SearchAgent;
import zws.security.Authentication;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;
//import zws.util.{}//Logwriter;
import zws.util.WaitForThreads;

import java.util.Collection;

public class TestLicensePool {

  private TestLicensePool() { }

  public static void main(String[] args) {
    TestLicensePool t = new TestLicensePool();
    t.run(5);
  }

  public void run(int numThreads) {
    try {
      //Properties.set(Names.SERVICE_FINDER_HOSTNAME, "10.10.10.151");
      Properties.set(Names.SERVICE_FINDER_HOSTNAME, "Designstate-0");
      RepositoryService c = RepositoryClient.getClient();
      Repository r = c.findRepository("ilink");
      Authentication id = new Authentication("admin", "agile");
      Metadata m = null;
      SearchAgent agents[] = new SearchAgent[numThreads];
      for (int idx=0; idx < numThreads; idx++) {
        agents[idx] = r.materializeSearchAgent();
        agents[idx].resetStorage();
        agents[idx].initializeStorage();
        agents[idx].setCriteria("(name='29-*.prt')");
        agents[idx].setAuthenticationToken(id);
      }
      WaitForThreads wait = new WaitForThreads();
      for (int idx=0; idx < numThreads; idx++) {
        agents[idx].execute(); // launches asynchrouns thread
        wait.addThread(agents[idx].getThread());
      }
      wait.execute();
      Collection results;
      System.out.println("");
      for (int idx=0; idx < numThreads; idx++) {
        results = agents[idx].getResults();
        //System.out.println(results.size() + " results Found by Agent-"+idx);
      }
    } 
    catch (Exception e) { e.printStackTrace(); }
  }
}
