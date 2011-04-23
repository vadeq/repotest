package zws.repository.ilink3.qx.client.op.xml;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 6, 2004, 8:00 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.ownership.LockBase;
import zws.data.workspace.*;
import zws.security.Authentication;
import zws.log.failure.Failure;

import java.util.*;

import org.xml.sax.Attributes;

public class WorkspaceStatusHandler extends IntralinkResultHandler {

  public WorkspaceStatusHandler (String datasource, Authentication auth) {
    datasourceName = datasource;
    id=auth;
  }
    
  public void startElement (String uri, String name, String qName, Attributes atts) { 
    if ( qName.equalsIgnoreCase("workspace-status-report")) { pushWorkspace(atts); return; }
    else if ( qName.equalsIgnoreCase("metadata")) { pushItem(atts); return; }
    else if ( qName.equalsIgnoreCase("instance")) { pushItem(atts); return; }
    else if ( qName.equalsIgnoreCase("lock")) { setLock(atts); return; }
    else if ( qName.equalsIgnoreCase("intent-to-modify")) { setLock(atts); return; }
    else if ( qName.equalsIgnoreCase("conflict")) { addConflict(atts); return; }
    try {
      if ( qName.equalsIgnoreCase("failed-to-authenticate")) { getStorable().log(new Failure("err.invalid.authentication")); return; }
    }
    catch (Exception e) { e.printStackTrace(); }
  }
  
  public void endElement (String uri, String name, String qName) {
    if ( !qName.equalsIgnoreCase("workspace-status-report")) return;
    WorkspaceBase workspace = (WorkspaceBase)stack.pop();
    workspaces.add(workspace);
  }

  private void pushWorkspace(Attributes atts) {
    try {
      WorkspaceBase ws= unmarshallWorkspace(atts, datasourceName);
      ws.setAuthentication(id);
      stack.push(ws);
      itemMap.clear();
    }
    catch (Exception e) { e.printStackTrace(); /*++ log this error*/ }
  }
  
  private void pushItem(Attributes atts) {
    try {
      WorkspaceBase workspace = (WorkspaceBase) stack.peek();
      WorkspaceItemBase item= unmarshallWorkspaceItem(workspace, atts);
      workspace.add(item);
      itemMap.put(item.getName(), item);
    }
    catch (Exception e) { e.printStackTrace(); /*++ log this error*/ }
  }

  private void setLock(Attributes atts) {
   LockBase lock = unmarshallLock(atts);
   WorkspaceItemBase item  = (WorkspaceItemBase)itemMap.get(lock.getName());
   item.lock(lock.getOwner());
  }

  private void addConflict(Attributes atts) {
   WorkspaceConflictBase c = unmarshallWorkspaceConflict(atts);
   WorkspaceItemBase item  = (WorkspaceItemBase)itemMap.get(c.getName());
   item.add(c);
  }

  
  public Collection getResults() { return workspaces; }
  
  private Collection workspaces = new Vector();
  private Stack stack = new Stack();
  private Map itemMap = new HashMap();

  private String datasourceName=null;
  private Authentication id = null;
}
