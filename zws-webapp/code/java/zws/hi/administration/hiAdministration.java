package zws.hi.administration;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 3, 2003, 5:26 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.service.EJBLocator;
import zws.service.PrototypeServiceRemote;

import com.zws.hi.hiList;

public class hiAdministration extends hiList {

  public String load() { 
    PrototypeServiceRemote service;
    try { service = EJBLocator.findService(serverName); }
    catch (Exception e) { logFormError("err.server.not.found", serverName);  return ctrlERROR; }
    try { service.load() ; }
    catch (Exception e) { logFormError("err.loading.server"); return ctrlERROR; }
    logFormStatus("status.loaded.server.config", serverName);
    return ctrlOK;
  }

  public String reload() {
    PrototypeServiceRemote service;
    try { service = EJBLocator.findService(serverName); }
    catch (Exception e) { logFormError("err.server.not.found", serverName);  return ctrlERROR; }
    try { service.reload() ; }
    catch (Exception e) { logFormError("err.loading.server"); return ctrlERROR; }
    logFormStatus("status.reloaded.server.config", serverName);
    return ctrlOK;
  }

  public String getServerName() { return serverName; }
  public void setServerName(String s) { serverName=s; }

  private String serverName;
  private PrototypeServiceRemote service=null;
}
