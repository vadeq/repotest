package zws.service.replication;

/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 30, 2004, 7:23 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.Alert;
import zws.exception.UnsupportedOperation;
import zws.pkg.DataPackage;
import zws.pkg.DataPackageBase;
import zws.replication.policy.*;
import zws.replication.report.ConflictReport;
import zws.replication.report.ConflictReportBase;
import zws.service.space.DataSpaceService;
import zws.space.DataSpace;
import zws.util.DomainContext;
//impoer zws.util.Logwriter;


import java.util.*;

public class ReplicationSvc {

  public static void replicateDesign(ReplicationPolicy policy) throws Exception  {
    try {
      {}//Logwriter.printOnConsole("Starting design replication - " + policy.getName()+"...");
      if (policy instanceof zws.replication.policy.BroadcastPolicy) replicateDesign((BroadcastPolicy)policy);
      //else if (policy instanceof zws.replication.policy.MultiSynchPolicy) replicateDesign((MultiSynchPolicy)policy);
      {}//Logwriter.printOnConsole("...Design replication completed - " + policy.getName());
    }
    catch (Exception e) {
      {}//Logwriter.printOnConsole("!!!Replication ERROR - "+ policy.getName()+":");
      {}//Logwriter.printOnConsole(e.getMessage());
    }
  }

  public static void replicate(ReplicationPolicy policy) throws Exception  {
    try {
      {}//Logwriter.printOnConsole("Starting replication - " + policy.getName()+"...");
      if (policy instanceof zws.replication.policy.BroadcastPolicy) replicate((BroadcastPolicy)policy);
      else if (policy instanceof zws.replication.policy.MultiSynchPolicy) replicate((MultiSynchPolicy)policy);
      {}//Logwriter.printOnConsole("...Replication completed - " + policy.getName());
    }
    catch (Exception e) {
      {}//Logwriter.printOnConsole("!!!Replication ERROR - "+ policy.getName()+":");
      {}//Logwriter.printOnConsole(e.getMessage());
    }
  }

  public static void generateReplicationPackage(ReplicationPolicy policy, String tarballName) throws Exception  {
    try {
      {}//Logwriter.printOnConsole("Creating Package - " + policy.getName()+"...");
      if (policy instanceof zws.replication.policy.BroadcastPolicy) createPackages((BroadcastPolicy)policy, tarballName);
      else if (policy instanceof zws.replication.policy.MultiSynchPolicy) throw new UnsupportedOperation("generateReplicationPackage(MultiSynchPolicy)");//createPackages((MultiSynchPolicy)policy);
      {}//Logwriter.printOnConsole("...Packaging completed - " + policy.getName());
    }
    catch (Exception e) {
      {}//Logwriter.printOnConsole("!!!Packaging ERROR - "+ policy.getName()+":");
      {}//Logwriter.printOnConsole(e.getMessage());
    }
  }

  public static void importReplicationPackage(ReplicationPolicy  policy) throws Exception  {
    try {
      {}//Logwriter.printOnConsole("Importing package - " + policy.getName()+"...");
      if (policy instanceof zws.replication.policy.BroadcastPolicy) importPackages((BroadcastPolicy)policy);
      else if (policy instanceof zws.replication.policy.MultiSynchPolicy) importPackages((MultiSynchPolicy)policy);
      {}//Logwriter.printOnConsole("...Importing completed - " + policy.getName());
    }
    catch (Exception e) {
      {}//Logwriter.printOnConsole("!!!Import ERROR - "+ policy.getName()+":");
      {}//Logwriter.printOnConsole(e.getMessage());
    }
  }

  public static void synchronizeReplicationPackage(ReplicationPolicy  policy) throws Exception  {
    try {
      {}//Logwriter.printOnConsole("Synchronizing package: " + policy.getName()+"...");
      if (policy instanceof zws.replication.policy.BroadcastPolicy) synchronizePackages((BroadcastPolicy)policy);
      else if (policy instanceof zws.replication.policy.MultiSynchPolicy) synchronizePackages((MultiSynchPolicy)policy);
      {}//Logwriter.printOnConsole("...Completed synchronizing: " + policy.getName());
    }
    catch (Exception e) {
      {}//Logwriter.printOnConsole("Error while synchronizing "+ policy.getName()+":");
      {}//Logwriter.printOnConsole(e.getMessage());
    }
  }


  public static Collection reportConflicts(ReplicationPolicy policy) throws Exception  {
    {}//Logwriter.printOnConsole("Reporting conflicts: " + policy.getName()+"...");
    if (policy instanceof zws.replication.policy.BroadcastPolicy) return reportConflicts((BroadcastPolicy)policy);
    else if (policy instanceof zws.replication.policy.MultiSynchPolicy) return reportConflicts((MultiSynchPolicy)policy);
    else return null;
  }

  public static void replicate(BroadcastPolicy p) throws Exception {
    Collection syncRecords = new Vector();
    DataSpace sourceSpace=p.getSourceSpace();

    DataPackage dPkg=null;
    DataSpace targetSpace;
    Collection updates,c;
    Iterator i = p.getTargetSpaces().iterator();
    while (i.hasNext()) {
      targetSpace = (DataSpace)i.next();
      //+++sourceSpace.setDatedAfter(lookupLastSynchronization(sourceSpace, targetSpace));
      updates = getAvailableUpdates(sourceSpace, targetSpace);
      if (null!=updates && updates.size()>0) {
        {}//Logwriter.printOnConsole(sourceSpace.getName() + " has "+ updates.size() + " update"+((updates.size()==1)? "":"s")+" for " + targetSpace.getName());
        dPkg = createPackage(p,sourceSpace,updates,targetSpace, null);
        c = loadPackage(p,sourceSpace,targetSpace, dPkg, false);
        if (c!=null && c.size()>0) syncRecords.addAll(c);
      }
      else {}{}//Logwriter.printOnConsole(sourceSpace.getName() + " has no updates for " + targetSpace.getName());
    }
  }

  public static void replicateDesign(BroadcastPolicy p) throws Exception {
    Collection syncRecords = new Vector();
    DataSpace sourceSpace=p.getSourceSpace();

    DataPackage dPkg=null;
    DataSpace targetSpace;
    Collection updates,c;
    Iterator i = p.getTargetSpaces().iterator();
    while (i.hasNext()) {
      targetSpace = (DataSpace)i.next();
      //+++sourceSpace.setDatedAfter(lookupLastSynchronization(sourceSpace, targetSpace));
      updates = getAvailableUpdates(sourceSpace, targetSpace);
      try {
        if (null!=updates && updates.size()>0) {
          {}//Logwriter.printOnConsole(sourceSpace.getName() + " has "+ updates.size() + " update"+((updates.size()==1)? "":"s")+" for " + targetSpace.getName());
          //dPkg = createPackage(p,sourceSpace,updates,targetSpace, null);
          dPkg = createDesignPackage(p,sourceSpace,updates,targetSpace, null);
          c = loadDesignPackage(p, sourceSpace, targetSpace, dPkg, false);
          if (c!=null && c.size()>0) syncRecords.addAll(c);
        }
        else {}{}//Logwriter.printOnConsole(sourceSpace.getName() + " has no updates for " + targetSpace.getName());
      }
      catch (Exception e) {
        //e.printStackTrace();
        //send notification.
        Alert.notify("IER Error!!", "Error Replicating from " + sourceSpace.getName()+" to " + targetSpace.getName() +": "+ e.getMessage());
        {}//Logwriter.printOnConsole("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        {}//Logwriter.printOnConsole("ERROR replicating to " + targetSpace.getName() + ": " + e.getMessage());
        {}//Logwriter.printOnConsole("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      }
    }
  }


  public static void replicate(BroadcastPolicy p, Collection updates) throws Exception {
    Collection syncRecords = new Vector();
    DataSpace sourceSpace=p.getSourceSpace();

    DataPackage dPkg=null;
    DataSpace targetSpace;
    Collection c;
    Iterator i = p.getTargetSpaces().iterator();
    while (i.hasNext()) {
      targetSpace = (DataSpace)i.next();
      //+++sourceSpace.setDatedAfter(lookupLastSynchronization(sourceSpace, targetSpace));
      if (null!=updates && updates.size()>0) {
        {}//Logwriter.printOnConsole(sourceSpace.getName() + " has "+ updates.size() + " update"+((updates.size()==1)? "":"s")+" for " + targetSpace.getName());
        dPkg = createPackage(p,sourceSpace,updates,targetSpace, null);
        c = loadPackage(p,sourceSpace,targetSpace, dPkg, false);
        if (c!=null && c.size()>0) syncRecords.addAll(c);
      }
      else {}{}//Logwriter.printOnConsole(sourceSpace.getName() + " has no updates for " + targetSpace.getName());
    }
  }

  public static void replicateDesign(BroadcastPolicy p, Collection updates) throws Exception {
    Collection syncRecords = new Vector();
    DataSpace sourceSpace=p.getSourceSpace();

    DataPackage dPkg=null;
    DataSpace targetSpace;
    Collection c;
    Iterator i = p.getTargetSpaces().iterator();
    while (i.hasNext()) {
      targetSpace = (DataSpace)i.next();
      //+++sourceSpace.setDatedAfter(lookupLastSynchronization(sourceSpace, targetSpace));
      if (null!=updates && updates.size()>0) {
        {}//Logwriter.printOnConsole(sourceSpace.getName() + " has "+ updates.size() + " update"+((updates.size()==1)? "":"s")+" for " + targetSpace.getName());
        dPkg = createPackage(p,sourceSpace,updates,targetSpace, null);
        c = loadDesignPackage(p,sourceSpace,targetSpace, dPkg, false);
        if (c!=null && c.size()>0) syncRecords.addAll(c);
      }
      else {}{}//Logwriter.printOnConsole(sourceSpace.getName() + " has no updates for " + targetSpace.getName());
    }
  }

  public static void createPackages(BroadcastPolicy p, String tarballName) throws Exception {
    Collection syncRecords = new Vector();
    DataSpace sourceSpace=p.getSourceSpace();

    DataPackage dPkg=null;
    DataSpace targetSpace;
    Collection updates,c;
    Iterator i = p.getTargetSpaces().iterator();
    while (i.hasNext()) {
      targetSpace = (DataSpace)i.next();
      //+++sourceSpace.setDatedAfter(lookupLastSynchronization(sourceSpace, targetSpace));
      updates = getAvailableUpdates(sourceSpace, targetSpace);
      if (null!=updates && updates.size()>0) {
        {}//Logwriter.printOnConsole(sourceSpace.getName() + " has "+ updates.size() + " update"+((updates.size()==1)? "":"s")+" for " + targetSpace.getName());
        dPkg = createPackage(p,sourceSpace,updates,targetSpace, tarballName);
        //c = importPackage(p,sourceSpace,targetSpace, dPkg);
        //if (c!=null && c.size()>0) syncRecords.addAll(c);
      }
      else {}{}//Logwriter.printOnConsole(sourceSpace.getName() + " has no updates for " + targetSpace.getName());
    }
  }
  public static void importPackages(BroadcastPolicy p) throws Exception {
    loadPackages(p, false);
  }
  public static void synchronizePackages(BroadcastPolicy p) throws Exception {
    loadPackages(p, true);
  }
  public static void loadPackages(BroadcastPolicy p, boolean justSynchronize) throws Exception {
    Collection syncRecords = new Vector();
    DataSpace sourceSpace=p.getSourceSpace();
    DataPackage dPkg=null;
    DataSpace targetSpace;
    Collection c;
    String packageName=null;
    Iterator i = p.getTargetSpaces().iterator();
    while (i.hasNext()) {
      targetSpace = (DataSpace)i.next();
      packageName = getPackageName(p,sourceSpace,targetSpace);
      dPkg = createDataPackage(sourceSpace, packageName);
      c = loadPackage(p, sourceSpace, targetSpace, dPkg, justSynchronize);
      if (c!=null && c.size()>0) syncRecords.addAll(c);
    }
  }

  public static Collection reportConflicts(BroadcastPolicy p) throws Exception {
    DataSpace sourceSpace=p.getSourceSpace();
    DataPackage dPkg=null;
    DataSpace targetSpace;
    Collection c = new Vector();
    ConflictReport report;
    String packageName=null;
    Iterator i = p.getTargetSpaces().iterator();
    while (i.hasNext()) {
      targetSpace = (DataSpace)i.next();
      packageName = getPackageName(p,sourceSpace,targetSpace);
      dPkg = createDataPackage(sourceSpace, packageName);
      report = reportConflicts(p, sourceSpace, targetSpace, dPkg);
      c.add(report);
    }
    return c;
  }


  public static void replicate(MultiSynchPolicy p) throws Exception {
    Collection syncRecords,c;
    DataPackage dPkg=null;
    DataSpace sourceSpace,targetSpace;
    Collection updates;
    Map updateSet = new HashMap();
    Object[] sources = p.getSourceSpaces().toArray();
    for (int i=0; i< sources.length; i++) {
      sourceSpace = (DataSpace)sources[i];
      for (int j=0; j< sources.length; j++) {
        if (i!=j) { //dont synchronize to self!
          targetSpace = (DataSpace)sources[j];
          //sourceSpace.setDatedAfter(lookupLastSynchronization(sourceSpace, targetSpace));
          updates = getAvailableUpdates(sourceSpace, targetSpace);
          updateSet.put(p.key(sourceSpace)+"-2-"+p.key(targetSpace), targetSpace);
        }
      }
    }
    for (int i=0; i< sources.length; i++) {
      sourceSpace = (DataSpace)sources[i];
      syncRecords = new Vector();
      for (int j=0; j< sources.length; j++) {
        if (i!=j) { //dont synchronize to self!
          targetSpace = (DataSpace)sources[j];
          updates = (Collection)updateSet.get(p.key(sourceSpace)+"-2-"+p.key(targetSpace));
          dPkg = createPackage(p,sourceSpace,updates,targetSpace, null);
          c = loadPackage(p,sourceSpace,targetSpace, dPkg, false);
          if (c!=null && c.size()>0) syncRecords.addAll(c);
        }
      }
    }
  }

  public static void importPackages(MultiSynchPolicy p) throws Exception {
    loadPackages(p, false);
  }
  public static void synchronizePackages(MultiSynchPolicy p) throws Exception {
    loadPackages(p, true);
  }
  public static void loadPackages(MultiSynchPolicy p, boolean justSynchronize) throws Exception {
    DataPackage dPkg=null;
    Collection syncRecords,c;
    String packageName=null;
    DataSpace sourceSpace,targetSpace;
    Object[] sources = p.getSourceSpaces().toArray();
    for (int i=0; i< sources.length; i++) {
      sourceSpace = (DataSpace)sources[i];
      syncRecords = new Vector();
      for (int j=0; j< sources.length; j++) {
        if (i!=j) { //dont synchronize to self!
          targetSpace = (DataSpace)sources[j];
          packageName = getPackageName(p,sourceSpace,targetSpace);
          dPkg = createDataPackage(sourceSpace, packageName);
          c = loadPackage(p,sourceSpace,targetSpace, dPkg, justSynchronize);
          if (c!=null && c.size()>0) syncRecords.addAll(c);
        }
      }
    }
  }

  public static Collection reportConflicts(MultiSynchPolicy p) throws Exception {
    DataPackage dPkg=null;
    Collection c = new Vector();
    ConflictReport report;
    String packageName=null;
    DataSpace sourceSpace,targetSpace;
    Object[] sources = p.getSourceSpaces().toArray();
    for (int i=0; i< sources.length; i++) {
      sourceSpace = (DataSpace)sources[i];
      for (int j=0; j< sources.length; j++) {
        if (i!=j) { //dont report conflicts to self!
          targetSpace = (DataSpace)sources[j];
          packageName = getPackageName(p,sourceSpace,targetSpace);
          dPkg = createDataPackage(sourceSpace, packageName);
          report = reportConflicts(p,sourceSpace,targetSpace, dPkg);
          c.add(report);
        }
      }
    }
    return c;
  }

  private static Collection getAvailableUpdates(DataSpace sourceSpace, DataSpace targetSpace) throws Exception {
   DataSpaceService service = zws.service.space.EJBLocator.findService(sourceSpace.getServerName());
   return service.getAvailableUpdates(sourceSpace, targetSpace);
  }

  private static DataPackage createDesignPackage(ReplicationPolicy p, DataSpace sourceSpace, Collection updates, DataSpace targetSpace, String tarballName) throws Exception {
    if (null==updates || updates.size()==0) return null;
    String packageName = getPackageName(p,sourceSpace,targetSpace);
    DataSpaceService service = zws.service.space.EJBLocator.findService(sourceSpace.getServerName());
    //return service.createPackage(sourceSpace, packageName, updates, tarballName, null);
    return service.createDesignPackage(sourceSpace, packageName, updates, tarballName, null);
  }

  private static DataPackage createPackage(ReplicationPolicy p, DataSpace sourceSpace, Collection updates, DataSpace targetSpace, String tarballName) throws Exception {
    if (null==updates || updates.size()==0) return null;
    String packageName = getPackageName(p,sourceSpace,targetSpace);
    DataSpaceService service = zws.service.space.EJBLocator.findService(sourceSpace.getServerName());
    return service.createPackage(sourceSpace, packageName, updates, tarballName, null);
  }

  private static Collection loadDesignPackage(ReplicationPolicy p, DataSpace sourceSpace, DataSpace targetSpace, DataPackage dPkg, boolean justSynchronize) throws Exception {
    if (null==targetSpace.getContext()) targetSpace.setContext(new DomainContext());
    DataSpaceService service = zws.service.space.EJBLocator.findService(targetSpace.getServerName());
    if (justSynchronize) return service.synchronizeFromPackage(targetSpace,dPkg,null);
    return service.importDesignPackage(targetSpace,dPkg, null);
  }

  private static Collection loadPackage(ReplicationPolicy p, DataSpace sourceSpace, DataSpace targetSpace, DataPackage dPkg, boolean justSynchronize) throws Exception {
    if (null==targetSpace.getContext()) targetSpace.setContext(new DomainContext());
    DataSpaceService service = zws.service.space.EJBLocator.findService(targetSpace.getServerName());
    if (justSynchronize) return service.synchronizeFromPackage(targetSpace,dPkg,null);
    return service.importPackage(targetSpace,dPkg,null);
  }

  private static ConflictReport reportConflicts(ReplicationPolicy p, DataSpace sourceSpace, DataSpace targetSpace, DataPackage dPkg) throws Exception {
    if (null==targetSpace.getContext()) targetSpace.setContext(new DomainContext());
    DataSpaceService service = zws.service.space.EJBLocator.findService(targetSpace.getServerName());
    ConflictReportBase report = (ConflictReportBase) service.reportConflicts(targetSpace,dPkg,null);
    report.setPolicyName(p.getName());
    report.setSourceRoute(sourceSpace.getRoute());
    report.setTargetRoute(targetSpace.getRoute());
    return report;
  }

  private static String getPackageName(ReplicationPolicy p, DataSpace sourceSpace, DataSpace targetSpace) {
    return p.key(sourceSpace)+"-2-"+p.key(targetSpace);
  }

  private static DataPackage createDataPackage(DataSpace sourceSpace, String packageName) {
    DataPackageBase dPkg = new DataPackageBase();
    dPkg.setDomainName(sourceSpace.getDomainName());
    dPkg.setServerName(sourceSpace.getServerName());
    dPkg.setName(packageName);
    return dPkg;
  }
 }