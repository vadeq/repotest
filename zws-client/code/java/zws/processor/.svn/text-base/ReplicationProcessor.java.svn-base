package zws.processor;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 15, 2004, 9:25 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Replicator;
import zws.replication.policy.BroadcastPolicy;
import zws.replication.policy.ReplicationPolicy;
import zws.service.replication.policy.EJBLocator;
import zws.service.replication.policy.ReplicationPolicyService;
import zws.util.DomainContext;

public class ReplicationProcessor extends ProcessorBase {
  protected void process(DomainContext ctx) throws Exception{ replicate(); }
  
  public ReplicationPolicy getPolicy(int node, String name) {
    try {
      Replicator rep = Replicator.getClient("DesignState-node-"+node);
      ReplicationPolicyService service = EJBLocator.findService("DesignState-node-"+node);
      ReplicationPolicy p = service.getPolicy(name);
      return p;
    }
    catch(Exception e) {
      e.printStackTrace();
      {} //System.out.println("Failed to retrieve policy " + name+ " from node " + node);
      return null;
    }
  }

  public void replicate() {
    try {
      ReplicationPolicy policy = getPolicy(node, policyName);
      if (null==policy) { {} //System.out.println("Could not find policy!"); 
        return; 
      }
      {} //System.out.println("Starting replication for "+ policy.getName()); 

      Replicator rep = Replicator.getClient("DesignState-node-"+node);
      rep.replicate(policy);
    }
    catch(Exception e) { e.printStackTrace(); }
  }

  public void exportReplicationPackage() {
    try {
      ReplicationPolicy policy = getPolicy(node, policyName);
      if (null==policy) { {} //System.out.println("Could not find policy!"); 
        return; 
      }
      {} //System.out.println("Starting replication for "+ policy.getName());

      Replicator rep = Replicator.getClient("DesignState-node-0");
      rep.generateReplicationPackage(policy, null);
    }
    catch(Exception e) { e.printStackTrace(); }
  }

  public void importReplicationPackage() {
    try {
      ReplicationPolicy policy = (BroadcastPolicy)getPolicy(node, policyName);
      if (null==policy) { {} //System.out.println("Could not find policy!"); 
        return; 
      }
      {} //System.out.println("Starting replication for "+ policy.getName()); 

      Replicator rep = Replicator.getClient("DesignState-node-0");
      rep.importReplicationPackage(policy);
    }
    catch(Exception e) { e.printStackTrace(); }
  }

  public int getPolicyNode() { return node; }
  public void setPolicyNode(int n) { node=n; }
  public String getPolicyName() { return policyName; }
  public void setPolicyName(String s) { policyName=s; }

  int node=0;
  String policyName=null;
}
