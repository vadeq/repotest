package zws.replication.report;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 16, 2005, 10:32 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.Collection;

public interface ConflictReport {
  public String getPolicyName();
  public String getSourceRoute();
  public String getTargetRoute();
  public void add(Conflict c);
  public void addAll(Collection c);
  public Collection getConflicts();
  public String toXML();
}
