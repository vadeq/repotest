package zws; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Feb 2, 2006
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.space.DataSpace;
import zws.util.*;
import zws.util.comparator.*;
import zws.exception.InitializationError;
import zws.replication.policy.BroadcastPolicy;

import java.util.*;

import javax.naming.NameNotFoundException;

public class ReplicationClient {

  public String getSelectedServer() { return selectedServer; }
  public void setSelectedServer(String s) throws InitializationError {
    if (!getServerList().contains(s)) throw new InitializationError(s + " is not one of the available servers");      
    selectedServer = s;
    selectedPolicy=null;
  }

  public BroadcastPolicy getSelectedPolicy() throws Exception {
    return getPolicy(getSelectedServer(), getSelectedPolicyName());
  }

  public DataSpace getSelectedSourceSpace() throws Exception {
	  BroadcastPolicy p = getSelectedPolicy();
	  if (null==p) return null;
	  return p.getSourceSpace();
	}
  
  public Collection getSelectedTargetSpaces() throws Exception {
	  BroadcastPolicy p = getSelectedPolicy();
	  if (null==p) return null;
	  return p.getTargetSpaces();
	}
    
  public String getSelectedPolicyName() { return selectedPolicy; }
  public void setSelectedPolicyName(String s) throws Exception {
    if (getPolicies(getSelectedServer()).containsKey(s)) selectedPolicy = s; 
  }

  public Collection getPolicyNames() throws Exception {
   if (null==getSelectedServer()) return null;
   return getPolicyNames(getSelectedServer());
  }

  private Collection getPolicyNames(String server) throws Exception {
    Map policyMap = getPolicies(server);
    if(policyMap.isEmpty()) return null;
    SortedSet c = new TreeSet(new AlphaNumericComparator());
    c.addAll(policyMap.keySet());
    return c;
  }

  private BroadcastPolicy getPolicy(String server, String policyName) throws Exception {
    if (null==server) return null;
    if (null==policyName) return null;
    if (policies.isEmpty()) { loadPolicies(); }
    Map m = MapUtil.getMapFromMap(policies, server);
    return (BroadcastPolicy)m.get(policyName);
  }
  
  private Map getPolicies(String server) throws Exception {
    if (policies.isEmpty()) { loadPolicies(); }
    return MapUtil.getMapFromMap(policies, server);
  }

  public Collection getServerList() { return Server.getServerList(); }

  private void loadPolicies() {
    Collection servers = getServerList();
    Iterator i,s;
    s = servers.iterator();
    String server;
    while(s.hasNext()) {
      server = (String)s.next();
      try {
        Map policyMap = MapUtil.getMapFromMap(policies, server);
	      Collection c = Replicator.getClient(server).getPolicies();
	      if (null==c) continue;
	      i = c.iterator();
	      BroadcastPolicy p;
	      while (i.hasNext()) {
          p = (BroadcastPolicy) i.next();
	        policyMap.put(p.getName(), p);
	      }
      }
      catch(NameNotFoundException e) { {} //System.out.println(e.getMessage()); }
        
      }
      catch(Exception e) { e.printStackTrace(); }
    }
  }
 
  private String selectedServer=null;
  private String selectedPolicy=null;
  private Map policies=new HashMap();
}
