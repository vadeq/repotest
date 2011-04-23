package zws.replication.report;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 16, 2005, 10:37 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;

import java.io.Serializable;
import java.util.*;

public class ConflictReportBase implements Serializable, ConflictReport {
  public String getPolicyName() { return policyName; }
  public void setPolicyName(String s) { policyName=s; }
  public String getSourceRoute(){ return sourceRoute; }
  public void setSourceRoute(String s){ sourceRoute=s; }
  public String getTargetRoute(){ return targetRoute; }
  public void setTargetRoute(String s){targetRoute = s; }
  
  public void add(Conflict c) { conflicts.add(c); }
  public void addAll(Collection c) { conflicts.addAll(c); }
  public Collection getConflicts() { return conflicts; }

  public String toString () { return toXML(); } 
  public String toXML(){
    StringBuffer s = new StringBuffer();
    s.append("<conflict-report policy='"+policyName+"' source='"+sourceRoute+"' target='"+targetRoute+"'>" + Names.NEW_LINE);
    s.append(" <conflicts>" + Names.NEW_LINE);
    Iterator i = conflicts.iterator();
    while ( i.hasNext()) s.append("  ").append(i.next().toString() + Names.NEW_LINE);
    s.append(" </conflicts>" + Names.NEW_LINE);
    s.append("</conflict-report>");
    return s.toString();
  }

  String policyName=null;
  String sourceRoute=null;
  String targetRoute=null;
  
  private Collection conflicts = new Vector();
}
