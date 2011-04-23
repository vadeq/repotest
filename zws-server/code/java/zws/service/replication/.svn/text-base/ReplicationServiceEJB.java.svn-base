package zws.service.replication; /*
DesignState - Design Compression Technology.
@author: athakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Configurator;
import zws.replication.policy.BroadcastPolicy;
import zws.replication.policy.ReplicationPolicy;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class ReplicationServiceEJB implements SessionBean, ReplicationService {


  public void replicateDesign(ReplicationPolicy p) throws RemoteException  {
    try{ ReplicationSvc.replicateDesign(p); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }
  }

	public void replicate(ReplicationPolicy p) throws RemoteException  {
    try{ ReplicationSvc.replicate(p); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }
  }

	public void replicate(BroadcastPolicy p, Collection updates) throws RemoteException  {
    try{ ReplicationSvc.replicate(p, updates); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }
  }

	public void replicateDesign(BroadcastPolicy p, Collection updates) throws RemoteException  {
    try{ ReplicationSvc.replicateDesign(p, updates); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }
  }

  public void generateReplicationPackage(ReplicationPolicy p) throws RemoteException  {
    try{ ReplicationSvc.generateReplicationPackage(p, null); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }
  }

  public void generateReplicationPackage(ReplicationPolicy p, String tarballName) throws RemoteException  {
    try{ ReplicationSvc.generateReplicationPackage(p, tarballName); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }
  }

  public void importReplicationPackage(ReplicationPolicy p) throws RemoteException  {
    try{ ReplicationSvc.importReplicationPackage(p); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }
  }

  public void synchronizeReplicationPackage(ReplicationPolicy p) throws RemoteException  {
    try{ ReplicationSvc.synchronizeReplicationPackage(p); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }
  }

  public Collection reportConflicts(ReplicationPolicy p) throws RemoteException  {
    try{ return ReplicationSvc.reportConflicts(p); }
    catch(Exception e){ throw new RemoteException(e.getClass().getName() + " : " + e.getMessage()); }
  }

  public void ejbCreate () { try {Configurator.load();} catch (Exception e) { e.printStackTrace(); } }
  public void ejbRemove() {}
  public void ejbPassivate() {}
  public void ejbActivate() {}
  public void setSessionContext(SessionContext ctx) {}
}
