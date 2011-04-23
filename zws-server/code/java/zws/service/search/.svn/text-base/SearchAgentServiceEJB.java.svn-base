package zws.service.search; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.application.Configurator;
import zws.log.failure.Failure;
import zws.search.SearchAgent;
import zws.security.Authentication;
//impoer zws.util.{}//Logwriter;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Vector;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class SearchAgentServiceEJB implements SessionBean {
 /** Returns the results from a search.
  *
  * @return results from the search
  * @param agentName name of searchAgent to launch
  * @param criteria search criteria used for the search
  * @param select list of fields to return in the results
  * @param orderBy name of the field to use for sorting (null if results should not be sorted)
  * @param ascending true if the results should be sorted in ascending order
  * @param skipCount an offset - the first skipCount results are skipped before returning results (ignored if < 0)
  * @param maxCount total number of results to return (ignored if < 1)
  * @param token
  * @throws RemoteException on error
  */
  public Collection search(String agentName, String criteria, String select, String orderBy, boolean ascending, int skipCount, int maxCount, Authentication token) throws RemoteException {
    SearchAgent agent=null;
    try{
      agent = SearchAgentSvc.find(agentName);
      agent.setCriteria(criteria);
      agent.setSelect(select);
      agent.setOrderBy(orderBy);
      agent.setAscending(ascending);
      agent.setSkipCount(skipCount);
      agent.setMaxCount(maxCount);
      agent.setAuthenticationToken(token);
      agent.throwOnFailure(false);

      agent.initializeStorage();

      agent.search();
      {}//Logwriter.printOnConsole("returning " + agent.getResults().size());
      if(null!=agent.getFailures() && 0<agent.getFailures().size()) {
        Failure f = (Failure)agent.getFailures().toArray()[0];
        {}//Logwriter.printOnConsole(f.getMessageKey());
        if (null!=f.getException()) f.getException().printStackTrace();
        throw new RemoteException(f.getMessageKey(), f.getException());
      }
      return agent.getResults();
    }
    catch (RemoteException a) { throw a; }
    catch (Exception a) { throw new RemoteException(Server.getName(), a); }
  }

  public void ejbCreate () { try {Configurator.load();} catch (Exception e) { e.printStackTrace(); } }
  public void ejbRemove() {}
  public void ejbPassivate() {}
  public void ejbActivate() {}
  public void setSessionContext(SessionContext ctx) {}
}
