package zws.hi.replication;/*
DesignState - Design Compression Technology
@author: dstewart
@version: 1.0
Created on February 22, 2004, 9:14 PM
Copywrite (c) 2004 Zero Wait-State Inc. All rights reserved */

import zws.EventHandlingClient;
import zws.application.Names;
import zws.data.Metadata;
import zws.event.ier.ReplicateDesign;
import zws.exception.InitializationError;
import zws.replication.policy.BroadcastPolicy;
import zws.report.Report;
import zws.space.DataSpace;
import zws.hi.report.hiReport;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class hiReplication extends hiReport {
  
  public String getSelectedIERServer() { return getIER().getSelectedServer(); }
  public void setSelectedIERServer(String s) {
    try {
      clearAllItems();
      getIEE().setSelectedServer(s);  
      getIER().setSelectedServer(s);
      sourceReport=null;
    }
    catch(InitializationError e) { e.printStackTrace(); }
  }

  public BroadcastPolicy getSelectedIERPolicy() throws Exception { return getIER().getSelectedPolicy(); }

  public String getSelectedIERPolicyName() { return getIER().getSelectedPolicyName(); }
  public void setSelectedIERPolicyName(String s) throws Exception {
    clearAllItems();
    String server = (String)policyServers.get(s);
    getIER().setSelectedServer(server);
    getIER().setSelectedPolicyName(s);
    sourceReport=null;
  }

  public String getSelectedReportName() {
    return getReport().getName();
  }

  public Collection getPolicyNames() {
    if (policyNames.isEmpty()) loadPolicyNames();
    return policyNames;
  }
  
  private void loadPolicyNames() {
    Collection servers;
    try { servers = getIER().getServerList(); }
    catch (Exception e) { e.printStackTrace(); return ; }
    Iterator i = servers.iterator();
    String server,policyName;
    Collection names;
    Iterator n;
    while(i.hasNext()) {
      server = (String)i.next();
      try {
        getIER().setSelectedServer(server);
        names = getIER().getPolicyNames();
        n = names.iterator();
        while(n.hasNext()) {
          policyName= (String)n.next();
          policyServers.put(policyName, server);
        }
        policyNames.addAll(names);
      }
      catch (Exception e) {e.printStackTrace(); }
    }
  }

  public Collection getSelectedTargetSpaces() { 
    try { return getIER().getSelectedTargetSpaces(); }
    catch (Exception e) {e.printStackTrace(); return null; }
  }
  
  public String toggleOverwrite() {
    String target = getID();
    DataSpace x;
    Iterator i = getSelectedTargetSpaces().iterator();
    while (i.hasNext()) {
      x = (DataSpace)i.next();
      if(x.getName().equals(target)) x.setOverwriteConflicts(!x.getOverwriteConflicts()); 
    }
    return ctrlOK;
  }

  public DataSpace getSelectedSourceSpace() throws Exception { 
    try { return getIER().getSelectedSourceSpace(); }
    catch (Exception e) {e.printStackTrace(); return null; }
  }
  
  public Report getReport() {
    if (null==sourceReport) {
      try { sourceReport = getIEE().materializeLatestReport(getIER().getSelectedSourceSpace()); }
      catch( Exception e) { e.printStackTrace(); return null; }
    }
    return sourceReport;
  }

  public String replicate() {
    Metadata m;
    Iterator i = getChosenItems().iterator();
    ReplicateDesign ev;
    int count=0;
    while(i.hasNext()) {
      m = (Metadata)i.next();
      ev = new ReplicateDesign();
      try { ev.setPolicyName(getSelectedIERPolicy().getName()); }
      catch(Exception e) {
        logFormStatus("err.design.replication.initiated", m.getName(), e.getMessage());       
        continue;
      }
      ev.setPolicyServer(getIER().getSelectedServer());
      ev.setCriteria("( name='"+m.getName()+"' )");
      ev.setUsername(getMember().getUsername());
      ev.setUserEmail(getMember().getUser().getEmail());
      
      DataSpace x;
      Iterator d = getSelectedTargetSpaces().iterator();
      String overwrites = null;
      while (d.hasNext()) {
        x = (DataSpace)d.next();
        if (x.getOverwriteConflicts()) {
          if (null==overwrites) overwrites = "";
          else overwrites += Names.DELIMITER;
          overwrites += x.getName(); 
        }
      }
      if (null!=overwrites) ev.setConflictOverwrites(overwrites);      
      try {
        EventHandlingClient.getClient().fire(ev);
        logFormStatus("status.design.replication.initiated", m.getName());
        count++;
      }
      catch(Exception e) {
        logFormStatus("err.design.replication.initiated", m.getName(), e.getMessage());       
      }
    }
    getChosenItems().clear();
    //if (0<count) logFormWarning("status.progress.will.be.mailed", getMember().getUsername(), getMember().getUser().getEmail());
    return ctrlOK;
  }
  
  private void clearAllItems() {
    if (null!=getItems()) getItems().clear();
    if (null!=getChosenItems()) getChosenItems().clear();    
  }

	private Report sourceReport = null;
	private Map policyServers = new HashMap();
	private Collection policyNames = new Vector();
}
