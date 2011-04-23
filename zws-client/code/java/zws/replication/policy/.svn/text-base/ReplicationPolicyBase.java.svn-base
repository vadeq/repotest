package zws.replication.policy;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 2, 2004, 12:49 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.datasource.Datasource;
import zws.space.DataSpace;
import zws.util.DomainContext;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class ReplicationPolicyBase implements ReplicationPolicy, Serializable, Cloneable {  
  public String getName() { return name; }
  public void setName(String s) { name=s; }
  public String key(Datasource repository) { return repository.getServerName()+"."+repository.getName(); }
  public String key(DataSpace space) { return space.getServerName()+"."+space.getName(); }
  public DomainContext getContext() { return ctx; }
  public void setContext(DomainContext x) { ctx = x; }

  public String getSpaceCriteria(DataSpace space){ 
    if (null==space) return null;
    String criteria = (String)sourceCriteria.get(key(space));
    return criteria;
  }
  public void setSpaceCriteria(DataSpace space, String criteria){ sourceCriteria.put(key(space), criteria); }

  public boolean getLockAtTarget() { return lockAtTarget; }
  public void setLockAtTarget(boolean b) { lockAtTarget=b; }
  
  public boolean supportsDeepCopy() { return true; }
  
  public final Object copy() {
    if (supportsDeepCopy()) return deepCopy();
    return shallowCopy();
  }

  public Object shallowCopy(){
    try {return clone(); }
    catch (CloneNotSupportedException e){throw new RuntimeException(e.getMessage()); }
  }

  public Object deepCopy(){
    return shallowCopy(); //+++ todo
  }
  
  public void inactivate() {}
  
  private String name=null;
  private DomainContext ctx;
  
  private Map sourceCriteria = new HashMap();
  private boolean lockAtTarget=true;
}
