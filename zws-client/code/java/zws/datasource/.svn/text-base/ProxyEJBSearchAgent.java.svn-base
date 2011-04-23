package zws.datasource; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.data.Metadata;
import zws.search.SearchAgentBase;
import zws.service.search.EJBLocator;
import zws.service.search.SearchAgentServiceRemote;

import java.io.Serializable;


public class ProxyEJBSearchAgent extends SearchAgentBase implements Serializable {

  public void initialize(){};
  public void executeQuery() throws Exception {
    storeAll(getService().search(getRemoteAgentName(), getCriteria().toString(), getSelect(), getOrderBy(), getAscending(), getSkipCount(), getMaxCount(), getAuthenticationToken()));
    {} //System.out.println("Proxy Agent["+getName()+"]: received  " + getResults().size());
  }

  private SearchAgentServiceRemote getService() throws Exception { return EJBLocator.findService(serverName); }

  public void addSystemAttributes(Metadata data){ data.set(Names.METADATA_SEARCH_AGENT_NAME, getName()); }

  public String getServerName() { return serverName; }
  public void setServerName(String s) { serverName=s; }
  public String getRemoteAgentName() { return remoteAgentName; }
  public void setRemoteAgentName(String s) { remoteAgentName=s; }
//  public void setResults(Collection c) { proxyResultList = c; }
//  public Collection getResults() { return proxyResultList; }

  private String name=null,serverName=null, remoteAgentName=null;;
  
//  private Collection proxyResultList=null;
}
