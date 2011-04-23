package zws.data.configuration;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 3, 2004, 10:08 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.Collection;
import java.util.SortedSet;

public interface RevisionSet extends ConfigurationNode {
  public String getRevisionLevel();
  public SortedSet getVersionNodes();
  public Collection getVersions(); //returns collection of strings for versions available under this configuration
}
