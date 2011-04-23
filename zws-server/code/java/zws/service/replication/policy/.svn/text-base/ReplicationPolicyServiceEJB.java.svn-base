package zws.service.replication.policy;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 14, 2004, 1:11 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Configurator;
import zws.replication.policy.ReplicationPolicy;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class ReplicationPolicyServiceEJB implements SessionBean, ReplicationPolicyService {
  
  public ReplicationPolicy getPolicy(String name) throws RemoteException {
    try{ return ReplicationPolicySvc.find(name); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }
  }
  public Collection getPolicies()throws RemoteException{ return ReplicationPolicySvc.findAll(); }

  public void ejbCreate () { try {Configurator.load();} catch (Exception e) { e.printStackTrace(); } }
  public void ejbRemove() {}
  public void ejbPassivate() {}
  public void ejbActivate() {}
  public void setSessionContext(SessionContext ctx) {}
}
