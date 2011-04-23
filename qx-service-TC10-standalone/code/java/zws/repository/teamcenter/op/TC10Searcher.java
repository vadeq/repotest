/**
 *
 */
package zws.repository.teamcenter.op;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Aug 31, 2007 3:28:35 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import zws.repository.teamcenter.TC10RepositoryBase;
import zws.search.SearchAgent;
import zws.util.PrintUtil;
import zws.util.WaitForThreads;

import java.util.Collection;

public class TC10Searcher extends TC10OpBase {

  public void execute() throws Exception {
    //zws.util.Logwriter.printOnConsole(this,"execute",getCriteria());
    TC10RepositoryBase rep = getRepository();
    SearchAgent agent = rep.materializeSearchAgent();
    agent.setAuthenticationToken(getAuthentication());
    agent.setCriteria(getCriteria());
    agent.initializeStorage();
    agent.execute();

    WaitForThreads waiter = new WaitForThreads();
    waiter.addThread(agent.getThread());
    waiter.run();

    Collection c = agent.getResults();
    storeAll(c);
    PrintUtil.print("agentResults from TC10Searcher.execute "+c);
    //{} //System.out.println("**********TC10Searcher.exexute********");
    //{} //System.out.println(c.toString());
    //{} //System.out.println("**********TC10Searcher.exexute********");
  }

  public String getCriteria() { return criteria; }
  public void setCriteria(String s) { criteria = s; }

  private String criteria = null;
}
