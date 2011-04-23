package zws.replication.policy;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 2, 2004, 12:49 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.datasource.Datasource;
import zws.space.DataSpace;
import zws.util.*;

public interface ReplicationPolicy extends Named, Prototype {
  public String getName();
  public String key(Datasource source);
  public String key(DataSpace space);

  public String getSpaceCriteria(DataSpace space);
  public void setSpaceCriteria(DataSpace space, String criteria);
  
  public DomainContext getContext();
  public void setContext(DomainContext x);
}
