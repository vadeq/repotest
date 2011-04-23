package zws.repository.ilink3.qx.client.op.xml;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 6, 2004, 8:00 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.data.workspace.*;
import zws.log.failure.Failure;

import java.util.*;

import org.xml.sax.Attributes;


public class WorkspaceListHandler extends IntralinkResultHandler {
  public WorkspaceListHandler (String datasourceName) { datasource = datasourceName; }
  
  public void startElement (String uri, String name, String qName, Attributes atts) { 
    if ( qName.equalsIgnoreCase("workspace")) { pushWorkspace(atts); return; }
    try {
      if ( qName.equalsIgnoreCase("failed-to-authenticate")) { getStorable().log(new Failure("err.invalid.authentication")); return; }
    }
    catch (Exception e) { e.printStackTrace(); }
  }
  
  public void endElement (String uri, String name, String qName) {
    if ( !qName.equalsIgnoreCase("workspace")) return; 
    Workspace ws = (Workspace)stack.pop();
    workspaces.add(ws);
  }

  private void pushWorkspace(Attributes atts) {
    try {
      WorkspaceBase ws = unmarshallWorkspace(atts, datasource);
      stack.push(ws);
    }
    catch (Exception e) { e.printStackTrace(); /*++ log this error*/ }
  }
  
  public Collection getResults() { return workspaces; }
  
  private Collection workspaces= new Vector();
  private Stack stack = new Stack();
  private String datasource=null;
}
