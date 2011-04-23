package zws.data.configuration;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 3, 2004, 11:28 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.Collection;
import java.util.SortedSet;

public interface History extends ConfigurationNode {
  public SortedSet getRevisionSets();
  public Collection getRevisions(); //returns versions available under this History configuration
  public Collection getVersions(String revision); //returns versions available for the given revision under this configuration
}
