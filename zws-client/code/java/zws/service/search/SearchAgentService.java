package zws.service.search; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.security.Authentication;

import java.rmi.RemoteException;
import java.util.Collection;

public interface SearchAgentService {
  
  /**
   * Returns the results from a search.
   * 
   * @param agentName name of searchAgent to launch
   * @param criteria search criteria used for the search
   * @param select list of fields to return in the results
   * @param orderBy name of the field to use for sorting (null if results should not be sorted)
   * @param ascending true is the results should be sorted in ascending order
   * @param skipCount an offset - the first skipCount results are skipped before returning results (ignored if < 0)
   * @param maxCount total number of results to return (ignored if < 1)
   * @param Authentication login credentials (if null, default logins are used)
   *
   * @return results from the search
   * @throws RemoteException on error
   */  
  public Collection search(String agentName, String criteria, String select, String orderBy, boolean ascending, int skipCount, int maxCount, Authentication token) throws RemoteException;
}
