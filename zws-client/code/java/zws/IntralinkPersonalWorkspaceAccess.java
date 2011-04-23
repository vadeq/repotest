package zws;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 10, 2003, 12:07 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.workspace.Workspace;
import zws.exception.NameNotFound;
import zws.origin.Origin;
import zws.security.Authentication;
import java.util.Collection;
import java.util.Map;

//+++ unwrap remote excepton and throw cause.
//+++ make sure EJB always populates RemoteException with the cause;
public class IntralinkPersonalWorkspaceAccess {

	private IntralinkPersonalWorkspaceAccess(String serverName, String datasourceName, Authentication id) {
    server=serverName;
    datasource=datasourceName;
    auth = id;
    ilinkAccess=IntralinkAccess.getAccess();
  }

  public static IntralinkPersonalWorkspaceAccess getAccess(String serverName, String datasourceName, Authentication id) {
    return new IntralinkPersonalWorkspaceAccess(serverName, datasourceName, id);
  }

  public void personalCheckout(Origin origin, String workspace) throws Exception{
    ilinkAccess.personalCheckout(origin, workspace, auth);
  }

  public void personalCheckin(Origin origin, String workspace) throws Exception {
    ilinkAccess.personalCheckin(origin, workspace, auth);
  }

  public void personalCheckin(String workspace) throws Exception {
    ilinkAccess.personalCheckin(server, datasource, workspace, auth);
  }
  
  public void personalSetAttribute(Origin origin, String workspace, String attribute, String value) throws Exception {
    ilinkAccess.personalSetAttribute(origin, workspace, attribute, value, auth);
  }

  public void personalSetAttribute(String workspace, String name, String attribute, String value) throws Exception {
    ilinkAccess.personalSetAttribute(server, datasource, workspace, name, attribute, value, auth);
  }

  public void personalSetAttributes(Origin origin, String workspace, Map attributes) throws Exception {
    ilinkAccess.personalSetAttributes(origin, workspace, attributes, auth);
  }

  public void personalSetAttributes(String workspace, String name, Map attributes) throws Exception {
    ilinkAccess.personalSetAttributes(server, datasource, workspace, name, attributes, auth);
  }

  public void personalLink(String workspace, String parent, String child) throws Exception {
    ilinkAccess.personalLink(server, datasource, workspace, parent, child, auth);
  }    
  public Collection personalExport(Origin origin, String workspace, String location) throws Exception {
    return ilinkAccess.personalExport(origin, workspace, location, auth);
  }
  public Collection personalExport(String workspace, String location, String name) throws Exception {
    return ilinkAccess.personalExport(server, datasource, workspace, location, name, auth);
  }
  public Collection personalExport(String workspace, String location, String name, String dependencies) throws Exception {
    return ilinkAccess.personalExport(server, datasource, workspace, location, name, dependencies, auth);
  }

  public void personalImportToWorkspace(Origin o, String workspace, String location, String filename) throws Exception {
    ilinkAccess.personalImportToWorkspace(o, workspace, location, filename, auth); 
  }

  public void personalImportToWorkspace(String workspace, String location, String filename) throws Exception {
    ilinkAccess.personalImportToWorkspace(server, datasource, workspace, location, filename, auth);
  }

  public Collection personalListWorkspaces(String serverName, String datasourceName, Authentication id) throws NameNotFound, Exception {
    return ilinkAccess.personalListWorkspaces(server, datasource, auth); 
  }

  public Workspace personalReportWorkspaceStatus(String serverName, String datasourceName, String workspaceName, Authentication id) throws NameNotFound, Exception {
    return ilinkAccess.personalReportWorkspaceStatus(server, datasource, workspaceName, auth); 
  }

  public Collection personalListWorkspaceContents(String workspace) throws Exception {
    return ilinkAccess.personalListWorkspaceContents(server, datasource, workspace, auth); 
  }
  
  public void personalCreateWorkspace(String workspace) throws Exception {
    ilinkAccess.personalCreateWorkspace(server, datasource, workspace, auth);
  }

  public void personalDestroyWorkspace(Origin o, String workspace) throws Exception {
    ilinkAccess.personalDestroyWorkspace(o, workspace, auth);
  }

  public void personalDestroyWorkspace(String workspace) throws Exception {
    ilinkAccess.personalDestroyWorkspace(server, datasource, workspace, auth);
  }

  private String server;
  private String datasource;
  private Authentication auth;
  private static IntralinkAccess ilinkAccess=null;
}
