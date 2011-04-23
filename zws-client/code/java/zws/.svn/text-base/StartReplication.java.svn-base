package zws;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 27, 2004, 5:36 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.replication.policy.BroadcastPolicy;
import zws.replication.policy.ReplicationPolicy;
import zws.service.replication.policy.EJBLocator;
import zws.service.replication.policy.ReplicationPolicyService;
import zws.util.StringUtil;

import java.util.Collection;
import java.util.Iterator;

public class StartReplication {
  
  public static void main(String[] args) {
    //String name, folder;
    if (args.length > 3) showUsage();
    if (0==args.length) replicate(null);
    if (1==args.length) {
      if (args[0].equalsIgnoreCase("replicating")) replicate(null);
      else if (args[0].equalsIgnoreCase("importing")) importReplicationPackage();
      else if (args[0].equalsIgnoreCase("synchronizing")) snchronizeReplicationPackage();
      else if (args[0].equalsIgnoreCase("packaging")) exportReplicationPackage(null, null);
      else if (args[0].equalsIgnoreCase("conflict-report")) reportConflicts();
      else showUsage();
    }
    else if (args.length==2) {
      String criteria = StringUtil.trimQuotes(args[1]);
      if (args[0].equalsIgnoreCase("packaging")) exportReplicationPackage(criteria, "tarball");
      else if (args[0].equalsIgnoreCase("replicating")) replicate(criteria);
      else showUsage();
    }
    else if (args.length==3) {
      String criteria = StringUtil.trimQuotes(args[1]);
      String tarballName= StringUtil.trimQuotes(args[2]);
      if (args[0].equalsIgnoreCase("packaging")) exportReplicationPackage(criteria, tarballName);
      else if (args[0].equalsIgnoreCase("replicating")) replicate(criteria);
      else showUsage();
    }
    else showUsage();
  }

  public static void showUsage(){
    {} //System.out.println("usage:");
    {} //System.out.println("import package:  java -cp %classPath% zws.StartReplication importing");
    {} //System.out.println("export package:  java -cp %classPath% zws.StartReplication packaging [\"criteria\"]");
    {} //System.out.println("full replicate:  java -cp %classPath% zws.StartReplication replicating [\"criteria\"]");
    {} //System.out.println("conflict report: java -cp %classPath% zws.StartReplication conflict-report");
  }

  public static ReplicationPolicy getPolicy(int idx){
    try {
      Replicator rep = Replicator.getClient("DesignState-node-0");
      ReplicationPolicyService service = EJBLocator.findService("DesignState-node-0");
      Collection c =service.getPolicies();
      if (c==null || c.size()==0) { {} //System.out.println("no policies defined"); 
          return null; 
      }
      if (c.size()>idx) {
        {} //System.out.println("Policy: "+ ((ReplicationPolicy)c.toArray()[idx]).getName() ); 
        return (ReplicationPolicy)c.toArray()[idx];
      }
      else return null;
    }
    catch(Exception e) { e.printStackTrace(); }
    return null;
  }

  public static void replicate(String criteria) {
    try {
      Replicator rep = Replicator.getClient("DesignState-node-0");
      BroadcastPolicy policy = (BroadcastPolicy)getPolicy(0);
      if (null==policy) { {} //System.out.println("Could not find policy!"); 
        return; 
      }
      {} //System.out.println("Replicating: "+ policy.getName());
      if (null!=criteria) policy.setSpaceCriteria(criteria);
      {} //System.out.println("criteria=\""+criteria+"\"");
      rep.replicate(policy);
    }
    catch(Exception e) { e.printStackTrace(); }
  }

  public static void exportReplicationPackage(String criteria, String tarballName) {
    try {
      Replicator rep = Replicator.getClient("DesignState-node-0");
      BroadcastPolicy policy = (BroadcastPolicy)getPolicy(0);
      if (null==policy) { {} //System.out.println("Could not find policy!"); 
        return; 
      }
      {} //System.out.println("Creating Package: "+ policy.getName());
      if (null!=criteria) policy.setSpaceCriteria(criteria);
      {} //System.out.println("criteria=\""+criteria+"\"");
      rep.generateReplicationPackage(policy, tarballName);
    }
    catch(Exception e) { e.printStackTrace(); }  
  }

  public static void importReplicationPackage() {
    try {
      Replicator rep = Replicator.getClient("DesignState-node-0");
      BroadcastPolicy policy = (BroadcastPolicy)getPolicy(0);
      if (null==policy) { {} //System.out.println("Could not find policy!"); 
        return; 
      }
      {} //System.out.println("Importing Package.."+ policy.getName()); 
      rep.importReplicationPackage(policy);
    }
    catch(Exception e) { e.printStackTrace(); }
  }

  public static void snchronizeReplicationPackage() {
    try {
      Replicator rep = Replicator.getClient("DesignState-node-0");
      BroadcastPolicy policy = (BroadcastPolicy)getPolicy(0);
      if (null==policy) { {} //System.out.println("Could not find policy!"); 
        return; 
      }
      {} //System.out.println("Synchronizing Package.."+ policy.getName()); 
      rep.synchronizeReplicationPackage(policy);
    }
    catch(Exception e) { e.printStackTrace(); }
  }
  public static void reportConflicts() {
    try {
      Replicator rep = Replicator.getClient("DesignState-node-0");
      BroadcastPolicy policy = (BroadcastPolicy)getPolicy(0);
      if (null==policy) { {} //System.out.println("Could not find policy!"); 
        return; 
      }
      //{} //System.out.println("Generating Conflict Report for "+ policy.getName()); 
      Collection c = rep.reportConflicts(policy);
      Iterator i = c.iterator();
      {} //System.out.println("<conflicts>");
      while (i.hasNext()) {}{} //System.out.println(i.next().toString());
      {} //System.out.println("</conflicts>");
    }
    catch(Exception e) { e.printStackTrace(); }
  }
}
