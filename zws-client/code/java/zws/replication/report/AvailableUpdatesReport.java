package zws.replication.report;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 16, 2005, 10:32 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.Collection;

public interface AvailableUpdatesReport {
  //this may not be necessary when moving to an event based replication
  public Collection getNewDesigns();
  public Collection getModifiedDesigns();
  public Collection getMovedDesigns();
  public Collection getRenamedItems();
  //..
}
