package zws.data.configuration;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 3, 2004, 11:33 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;

import java.util.Collection;
import java.util.SortedSet;

public interface Branch extends ConfigurationNode {
  public String getBranchName();
  public boolean keyMatchesChild(Metadata data);
  public boolean keyMatchesAncestor(Metadata data);
  public History getHistory();
  public Collection getRevisions(); //returns versions available under this Branch configuration
  public Collection getVersions(String revision); //returns versions available for the given revision under this configuration
  public SortedSet getBranches(); //returns all trees branching of this one
}
