package zws.service.synchronization;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 30, 2004, 7:23 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.datasource.Names;
import zws.data.Metadata;
import zws.database.DB;
import zws.database.Database;
import zws.datasource.Datasource;
import zws.exception.*;
import zws.origin.*;
import zws.replication.policy.ReplicationPolicy;
import zws.space.DataSpace;
import zws.synchronization.SynchronizationRecord;
import zws.synchronization.SynchronizationRecordBase;
//impoer zws.util.{}//Logwriter;
import zws.util.MapUtil;

import java.sql.*;
import java.util.*;

public class SynchronizationSvc {
  /*
  public static void synchronizeSpace(BroadcastPolicy p) throws Exception {
    Collection syncRecords = new Vector();
    DataSpace sourceSpace=p.getSourceSpace();
    DataSpace targetSpace;
    Collection updates,c;
    Iterator i = p.getTargetSpaces().iterator();
    while (i.hasNext()) {
      targetSpace = (DataSpace)i.next();
      updates = getAvailableUpdates(p, sourceSpace, targetSpace);
      c = synchronizeSpace(p, sourceSpace, updates, targetSpace);
      if (c!=null && c.size()>0) syncRecords.addAll(c);
    }
    //synchronizeAllSources(syncRecords);
  }
  public static void synchronizeSpace(MultiSynchPolicy p) throws Exception {
    Collection syncRecords,c;
    DataSpace sourceSpace,targetSpace;
    Collection updates;
    Map updateSet = new HashMap();
    Object[] sources = p.getSourceSpaces().toArray();
    for (int i=0; i< sources.length; i++) {
      sourceSpace = (DataSpace)sources[i];
      for (int j=0; j< sources.length; j++) {
        if (i!=j) { //dont synchronize to self!
          targetSpace = (DataSpace)sources[j];
          updateSet.put(p.key(sourceSpace)+"-2-"+p.key(targetSpace), getAvailableUpdates(p, sourceSpace, targetSpace));
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
          c = synchronizeSpace(p,sourceSpace,updates,targetSpace);
          if (c!=null && c.size()>0) syncRecords.addAll(c);
        }
      }
      synchronizeAllSources(syncRecords);
    }
  }

   private static Collection synchronizeSpace(ReplicationPolicy p, DataSpace sourceSpace, Collection updates, DataSpace targetSpace) throws Exception {
    if (null!=updates && updates.size()>0) {
      PackageSource pkg = sourceSpace.createPackage(p.key(sourceSpace)+"-2-"+p.key(targetSpace), updates, null);
      return targetSpace.synchronizePackage(pkg, null);
    }
    return null;
  }
   */
/*
  private static Collection getAvailableUpdates(ReplicationPolicy p, DataSpace sourceSpace, DataSpace targetSpace) throws Exception {
    Collection c = sourceSpace.listComponents(null, true, null);
    if (null==c || c.size()<1) return null;
    Collection updates = new Vector();
    Iterator i = c.iterator();
    Metadata m;
    while (i.hasNext()) {
      m = (Metadata)i.next();
      if (!SynchronizationSvc.isSynchronizedToDatasource(m.getOrigin(), targetSpace)) updates.add(m);
    }
    {}//Logwriter.printOnConsole("Space: "+sourceSpace.getName() +"2"+targetSpace.getName() +" has " +updates.size()+" updates");
    return updates;
  }
  */
  /*
  private static PackageSource createPackage(DataSpace srcSpace, String pkgName, Collection updates) throws Exception {
    PackageSourceBase pkg = new PackageSourceBase();
    pkg.setName(pkgName);
    if (null==updates || updates.size()<1) return pkg;
    Iterator i = updates.iterator();
    Metadata m;
    while (i.hasNext()) {
      m = (Metadata)i.next();
      pkg.addMetadata(m, null);
      pkg.storeMetadata(m, null);
      Origin o = m.getOrigin();
      InputStream stream = srcSpace.findBinary(m.getOrigin(),null);
      long len = srcSpace.getBinaryLength(m.getOrigin(), null);
      pkg.storeBinary(o,stream,len,null);
    }
    return pkg;
  }
  */
 //--------------------------------------------------------------





   /*
  public static void synchronize(BroadcastPolicy p) throws Exception {
    Collection syncRecords = new Vector();
    Datasource source=p.getSource();
    Datasource target;
    Collection updates,c;
    Iterator i = p.getTargets().iterator();
    while (i.hasNext()) {
      target = (Datasource)i.next();
      updates = getAvailableUpdates(p, source, target);
      c = synchronize(p,source,updates,target);
      if (c!=null && c.size()>0) syncRecords.addAll(c);
    }
    synchronizeAllSources(syncRecords);
  }
  public static void synchronize(MultiSynchPolicy p) throws Exception {
    Collection syncRecords,c;
    Datasource source,target;
    Collection updates;
    Map updateSet = new HashMap();
    Object[] sources = p.getSources().toArray();
    for (int i=0; i< sources.length; i++) {
      source = (Datasource)sources[i];
      for (int j=0; j< sources.length; j++) {
        if (i!=j) { //dont synchronize to self!
          target = (Datasource)sources[j];
          updateSet.put(p.key(source)+"-2-"+p.key(target), getAvailableUpdates(p, source, target));
        }
      }
    }
    for (int i=0; i< sources.length; i++) {
      source = (Datasource)sources[i];
      syncRecords = new Vector();
      for (int j=0; j< sources.length; j++) {
        if (i!=j) { //dont synchronize to self!
          target = (Datasource)sources[j];
          updates = (Collection)updateSet.get(p.key(source)+"-2-"+p.key(target));
          c = synchronize(p,source,updates,target);
          if (c!=null && c.size()>0) syncRecords.addAll(c);
        }
      }
      synchronizeAllSources(syncRecords);
    }
  }


  private static Collection synchronize(ReplicationPolicy p, Datasource source, Collection updates, Datasource target) throws Exception {
    if (null!=updates && updates.size()>0) {
      PackageSource pkg = source.createPackage(p.key(source)+"-2-"+p.key(target), updates, null);
      return target.synchronizePackage(pkg, null);
    }
    return null;
  }
  */


  private static void synchronizeAllSources(Collection syncRecords) throws Exception {
    Map m = new HashMap();
    Iterator i = syncRecords.iterator();
    SynchronizationRecord r;
    while (i.hasNext()) {
      r= (SynchronizationRecord) i.next();
      MapUtil.getCollectionFromMap(m, r.getOrigin0().toString()).add(r.getOriginA());
      MapUtil.getCollectionFromMap(m, r.getOriginA().toString()).add(r.getOrigin0());
    }
    i = m.values().iterator();
    while (i.hasNext()) {
      record((Collection)i.next());
    }
  }

  private static Collection getAvailableUpdates(ReplicationPolicy p, Datasource source, Datasource target) throws Exception {
    Collection c = source.listComponents(null, true, null);
    if (null==c || c.size()<1) return null;
    Collection updates = new Vector();
    Iterator i = c.iterator();
    Metadata m;
    while (i.hasNext()) {
      m = (Metadata)i.next();
      if (!SynchronizationSvc.isSynchronizedToDatasource(m.getOrigin(), target)) updates.add(m);
    }
    {}//Logwriter.printOnConsole(source.getName() +"2"+target.getName() +" has " +updates.size()+" updates");
    return updates;
  }
  /*
  private static PackageSource createPackage(Datasource src, String pkgName, Collection updates) throws Exception {
    PackageSourceBase pkg = new PackageSourceBase();
    pkg.setName(pkgName);
    if (null==updates || updates.size()<1) return pkg;
    Iterator i = updates.iterator();
    Metadata m;
    while (i.hasNext()) {
      m = (Metadata)i.next();
      pkg.addMetadata(m, null);
      pkg.storeMetadata(m, null);
      Origin o = m.getOrigin();
      InputStream stream = src.findBinary(m.getOrigin(),null);
      long len = src.getBinaryLength(m.getOrigin(), null);
      pkg.storeBinary(o,stream,len,null);
    }
    return pkg;
  }
  */

  public synchronized static Collection record(Collection origins) throws Exception {
    Collection syncRecords = new Vector();
    Origin a,b;
    Object[] records = origins.toArray();
    for (int i=0; i< records.length; i++) {
      a = (Origin)records[i];
      for (int j=0; j< records.length; j++) {
        if (i!=j) { //dont synchronize to self!
          b = (Origin)records[j];
          syncRecords.add(record(a,b));
        }
      }
    }
    return syncRecords;
  }

  public synchronized static SynchronizationRecord record(Origin a, Origin b) throws Exception {
    try {
      SynchronizationRecordBase r = new SynchronizationRecordBase(a,b);
      if (isSynchronized(a,b)) return r;
      record(a, findAllSynchronizationRecords(b));//if a is synked to b, then a should synkup with everything b is synked to
      record(b, findAllSynchronizationRecords(a));//if b is synked to a, then b should synkup with everything a is synked to
      record(r); //and, of course, synk a to b
      return r;
    }
    catch(Exception e) {e.printStackTrace(); throw e;}
  }

  public synchronized static Collection record(Origin a, Collection origins) throws Exception {
    if (null==origins) return null;
    Collection syncRecords = new Vector();
    Origin b;
    Iterator i = origins.iterator();
    SynchronizationRecordBase r;
    while(i.hasNext()) {
      b = (Origin)i.next();
      if (!b.isTheSameAs(a)) { //avoid circular deps
        r = new SynchronizationRecordBase(a,b);
        record (r);
        syncRecords.add(r);
      }
    }
    return syncRecords;
  }


  public synchronized static void record(SynchronizationRecord sync) throws Exception {
    if (isSynchronized(sync.getOrigin0(), sync.getOriginA())) return;
    try{
      PreparedStatement s = database().prepareStatement("INSERT into "+SYNCHRONIZATION_TABLE+" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
      java.sql.Date d = new java.sql.Date(System.currentTimeMillis());
      s.setString(1,sync.getDomainName0());
      s.setString(2,sync.getServerName0());
      s.setString(3,sync.getDatasourceType0());
      s.setString(4,sync.getDatasourceName0());
      s.setLong(5,sync.getTimeOfCreation0InMillis());
      s.setString(6,sync.getUID0());
      s.setString(7,sync.getName0());
      s.setString(8,sync.getLocationA());
      s.setString(9,sync.getStateA());
      s.setString(10,sync.getDomainNameA());
      s.setString(11,sync.getServerNameA());
      s.setString(12,sync.getDatasourceTypeA());
      s.setString(13,sync.getDatasourceNameA());
      s.setLong(14,sync.getTimeOfCreationAInMillis());
      s.setString(15,sync.getUIDA());
      s.setString(16,sync.getNameA());
      s.setString(17,sync.getLocationA());
      s.setString(18,sync.getStateA());
      database().execute(s);
    }
    catch (SQLException e) {
      if (-1 != e.getMessage().indexOf("Duplicate entry")) throw new Duplicate(sync.toString());
      else throw e;
    }
  }

  public synchronized static void rename(String domainName, String serverName, String source, String name, String newName) throws Exception {
    Collection oldOrigin0s = findOrigin0s(domainName, serverName, source, name);
    Collection oldOriginAs = findOriginAs(domainName, serverName, source, name);
    rename(oldOrigin0s, oldOriginAs, newName);
  }

  public synchronized static void rename(Collection oldOrigin0s, Collection oldOriginAs, String newName) throws Exception {
    OriginBase origin, newOrigin;
    Iterator i = oldOrigin0s.iterator();
    PreparedStatement s;
    while(i.hasNext()) {
      origin = (OriginBase) i.next();
      newOrigin = (OriginBase)origin.copy();
      newOrigin.setName(newName);
      s = database().prepareStatement("UPDATE "+SYNCHRONIZATION_TABLE+" SET "+SYNCHRONIZATION_TABLE_uid0+"=?, "+SYNCHRONIZATION_TABLE_name0+"=? WHERE ("+SYNCHRONIZATION_TABLE_domainName0+"=? AND "+SYNCHRONIZATION_TABLE_serverName0+"=? AND "+SYNCHRONIZATION_TABLE_datasourceName0+"=? AND "+SYNCHRONIZATION_TABLE_name0+"=?)");
      s.setString(1,newOrigin.getUniqueID());
      s.setString(2,newOrigin.getName());
      s.setString(3,origin.getDomainName());
      s.setString(4,origin.getServerName());
      s.setString(5,origin.getDatasourceName());
      s.setString(6,origin.getName());
      database().execute(s);
    }
    i = oldOriginAs.iterator();
    while(i.hasNext()) {
      origin = (OriginBase) i.next();
      newOrigin = (OriginBase)origin.copy();
      newOrigin.setName(newName);
      s = database().prepareStatement("UPDATE "+SYNCHRONIZATION_TABLE+" SET "+SYNCHRONIZATION_TABLE_uidA+"=?, "+SYNCHRONIZATION_TABLE_nameA+"=? WHERE ("+SYNCHRONIZATION_TABLE_domainNameA+"=? AND "+SYNCHRONIZATION_TABLE_serverNameA+"=? AND "+SYNCHRONIZATION_TABLE_datasourceNameA+"=? AND "+SYNCHRONIZATION_TABLE_nameA+"=?)");
      s.setString(1,newOrigin.getUniqueID());
      s.setString(2,newOrigin.getName());
      s.setString(3,origin.getDomainName());
      s.setString(4,origin.getServerName());
      s.setString(5,origin.getDatasourceName());
      s.setString(6,origin.getName());
      database().execute(s);
    }
  }

  public synchronized static void remove(SynchronizationRecord sync) throws Exception {
    PreparedStatement s;
    s = database().prepareStatement("DELETE FROM "+SYNCHRONIZATION_TABLE+" WHERE "+SYNCHRONIZATION_TABLE_domainName0+"=? AND "+SYNCHRONIZATION_TABLE_serverName0+"=? AND "+SYNCHRONIZATION_TABLE_datasourceName0+"=? AND "+SYNCHRONIZATION_TABLE_uid0+"=? AND "+SYNCHRONIZATION_TABLE_domainNameA+"=? AND "+SYNCHRONIZATION_TABLE_serverNameA+"=? AND "+SYNCHRONIZATION_TABLE_datasourceNameA+"=? AND "+SYNCHRONIZATION_TABLE_uidA+"=?");
    s.setString(1,sync.getDomainName0());
    s.setString(2,sync.getServerName0());
    s.setString(3,sync.getDatasourceName0());
    s.setString(4,sync.getUID0());
    s.setString(5,sync.getDomainNameA());
    s.setString(6,sync.getServerNameA());
    s.setString(7,sync.getDatasourceNameA());
    s.setString(8,sync.getUIDA());
    database().execute(s);
  }

  public static void purgeMatches(Origin origin) throws Exception {
    Collection cc = findAllSynchronizationRecords(origin);
    Iterator i = cc.iterator();
    Origin o;
    while(i.hasNext()) {
      o = (Origin) i.next();
      purgeByUID(o.getDomainName(), o.getServerName(), o.getDatasourceName(), o.getUniqueID());
    }
    purgeByUID(origin.getDomainName(), origin.getServerName(), origin.getDatasourceName(), origin.getUniqueID());
  }

  public synchronized static void purgeByUID(String domainName, String serverName, String source, String uid) throws Exception {
    PreparedStatement s = database().prepareStatement("DELETE FROM "+SYNCHRONIZATION_TABLE+" WHERE ("+SYNCHRONIZATION_TABLE_domainName0+"=? AND "+SYNCHRONIZATION_TABLE_serverName0+"=? AND "+SYNCHRONIZATION_TABLE_datasourceName0+"=? AND "+SYNCHRONIZATION_TABLE_uid0+"=?) OR ("+SYNCHRONIZATION_TABLE_domainNameA+"=? AND "+SYNCHRONIZATION_TABLE_serverNameA+"=? AND "+SYNCHRONIZATION_TABLE_datasourceNameA+"=? AND "+SYNCHRONIZATION_TABLE_uidA+"=?)");
    s.setString(1,domainName);
    s.setString(2,serverName);
    s.setString(3,source);
    s.setString(4,uid);
    s.setString(5,domainName);
    s.setString(6,serverName);
    s.setString(7,source);
    s.setString(8,uid);
    database().execute(s);
  }

  public synchronized static void purgeByName(String domainName, String serverName, String source, String name) throws Exception {
    PreparedStatement s = database().prepareStatement("DELETE FROM "+SYNCHRONIZATION_TABLE+" WHERE ("+SYNCHRONIZATION_TABLE_domainName0+"=? AND "+SYNCHRONIZATION_TABLE_serverName0+"=? AND "+SYNCHRONIZATION_TABLE_datasourceName0+"=? AND "+SYNCHRONIZATION_TABLE_name0+"=?) OR ("+SYNCHRONIZATION_TABLE_domainNameA+"=? AND "+SYNCHRONIZATION_TABLE_serverNameA+"=? AND "+SYNCHRONIZATION_TABLE_datasourceNameA+"=? AND "+SYNCHRONIZATION_TABLE_nameA+"=?)");
    s.setString(1,domainName);
    s.setString(2,serverName);
    s.setString(3,source);
    s.setString(4,name);
    s.setString(5,domainName);
    s.setString(6,serverName);
    s.setString(7,source);
    s.setString(8,name);
    database().execute(s);
  }

  public synchronized static void purgeDatasourceRecords(String domainName, String serverName, String source) throws Exception {
    PreparedStatement s = database().prepareStatement("DELETE FROM "+SYNCHRONIZATION_TABLE+" WHERE ("+SYNCHRONIZATION_TABLE_domainName0+"=? AND "+SYNCHRONIZATION_TABLE_serverName0+"=? AND "+SYNCHRONIZATION_TABLE_datasourceName0+"=?) OR ("+SYNCHRONIZATION_TABLE_domainNameA+"=? AND "+SYNCHRONIZATION_TABLE_serverNameA+"=? AND "+SYNCHRONIZATION_TABLE_datasourceNameA+"=?)");
    s.setString(1,domainName);
    s.setString(2,serverName);
    s.setString(3,source);
    s.setString(4,domainName);
    s.setString(5,serverName);
    s.setString(6,source);
    database().execute(s);
  }

  public synchronized static void purgeServerRecords(String domainName, String serverName) throws Exception {
    PreparedStatement s = database().prepareStatement("DELETE FROM "+SYNCHRONIZATION_TABLE+" WHERE ("+SYNCHRONIZATION_TABLE_domainName0+"=? AND "+SYNCHRONIZATION_TABLE_serverName0+"=?) OR ("+SYNCHRONIZATION_TABLE_domainNameA+"=? AND "+SYNCHRONIZATION_TABLE_serverNameA+"=?)");
    s.setString(1,domainName);
    s.setString(2,serverName);
    s.setString(3,domainName);
    s.setString(4,serverName);
    database().execute(s);
  }

  public static boolean isSynchronized(Collection origins) throws Exception {
    if (null==origins || origins.size() <2) return false;
    boolean synced = true;
    Origin a,b;
    Iterator i = origins.iterator();
    if (i.hasNext()) a = (Origin)i.next();
    else return false;
    while(i.hasNext()) {
      b = (Origin)i.next();
      if(!isSynchronized(a,b)) return false;
      a=b;
    }
    return true;
  }

  public static boolean isSynchronized(Origin a, Origin b) throws Exception {
    SynchronizationRecordBase sync = new SynchronizationRecordBase(a, b);
    PreparedStatement s = database().prepareStatement("SELECT "+SYNCHRONIZATION_TABLE_uidA+" FROM "+SYNCHRONIZATION_TABLE+" WHERE "+SYNCHRONIZATION_TABLE_domainName0+"=? AND "+SYNCHRONIZATION_TABLE_serverName0+"=? AND "+SYNCHRONIZATION_TABLE_datasourceName0+"=? AND "+SYNCHRONIZATION_TABLE_uid0+"=? AND "+SYNCHRONIZATION_TABLE_domainNameA+"=? AND "+SYNCHRONIZATION_TABLE_serverNameA+"=? AND "+SYNCHRONIZATION_TABLE_datasourceNameA+"=? AND "+SYNCHRONIZATION_TABLE_uidA+"=?");
    s.setString(1,sync.getDomainName0());
    s.setString(2,sync.getServerName0());
    s.setString(3,sync.getDatasourceName0());
    s.setString(4,sync.getUID0());
    s.setString(5,sync.getDomainNameA());
    s.setString(6,sync.getServerNameA());
    s.setString(7,sync.getDatasourceNameA());
    s.setString(8,sync.getUIDA());
    ResultSet r = database().executeQuery(s);
    boolean x = false;
    if (r.next()) x = true;
    r.close(); r=null;
    return x;
  }

  public static boolean isSynchronizedToDatasource(Origin o, Collection targetDatasources) throws Exception {
    if (null==targetDatasources || targetDatasources.size()<1) return false;
    Iterator i = targetDatasources.iterator();
    Datasource d;
    while (i.hasNext()) {
      d = (Datasource)i.next();
      if (isSynchronizedToDatasource(o, d)) return true;
    }
    return false;
  }

  public static boolean isIndirectlySynchronizedToDatasource(Origin o, Datasource target) throws Exception {
    Collection c = findAllSynchronizationRecords(o);
    if (null==c) return false;
    Collection origins = new Vector();
    origins.addAll(c);
    Iterator i = c.iterator();
    while (i.hasNext()) origins.addAll(findAllSynchronizationRecords((Origin)i.next())); //go 2 levels deep to find all synchronizations
    i = origins.iterator();
    Origin x;
    while (i.hasNext()) {
      x = (Origin)i.next();
      if (x.getServerName().equals(target.getServerName()) && x.getDatasourceName().equals(target.getName())) return true;
    }
    return false;
  }

  public static boolean isSynchronizedToDatasource(Origin o, DataSpace target) throws Exception {
    return isSynchronizedToDatasource(o, target.getDomainName(), target.getServerName(), target.getDatasourceName());
  }
  public static boolean isSynchronizedToDatasource(Origin o, Datasource target) throws Exception {
    return isSynchronizedToDatasource(o, target.getDomainName(), target.getServerName(), target.getName());
  }
  public static boolean isSynchronizedToDatasource(Origin o, String domainName, String serverName, String datasourceName) throws Exception {
    try  { return (null!=findSynchronization(o, domainName, serverName, datasourceName)); }
    catch (Exception e) { return false; }
  }

  public static Origin findSynchronization(Origin o, String domainName, String serverName, String datasourceName) throws Exception {
    Origin lookup=null;
    OriginBase targetOrigin = (OriginBase)o.copy();
    targetOrigin.setDomainName(domainName);
    targetOrigin.setServerName(serverName);
    targetOrigin.setDatasourceName(datasourceName);

    //Origin targetOrigin = OriginMaker.materialize(domainName, serverName, datasourceType, datasourceName, o.getTimeOfCreationInMillis(), "unused|no name");
    SynchronizationRecord sync = new SynchronizationRecordBase(o, targetOrigin);
    PreparedStatement s;
    if (sync.getOrigin0().isExactlyTheSame(o))
      s = database().prepareStatement("SELECT * FROM "+SYNCHRONIZATION_TABLE+" WHERE "+SYNCHRONIZATION_TABLE_domainName0+"=? AND "+SYNCHRONIZATION_TABLE_serverName0+"=? AND "+SYNCHRONIZATION_TABLE_datasourceName0+"=? AND "+SYNCHRONIZATION_TABLE_uid0+"=? AND "+SYNCHRONIZATION_TABLE_domainNameA+"=? AND "+SYNCHRONIZATION_TABLE_serverNameA+"=? AND "+SYNCHRONIZATION_TABLE_datasourceNameA+"=?");
    else
      s = database().prepareStatement("SELECT * FROM "+SYNCHRONIZATION_TABLE+" WHERE "+SYNCHRONIZATION_TABLE_domainNameA+"=? AND "+SYNCHRONIZATION_TABLE_serverNameA+"=? AND "+SYNCHRONIZATION_TABLE_datasourceNameA+"=? AND "+SYNCHRONIZATION_TABLE_uidA+"=? AND "+SYNCHRONIZATION_TABLE_domainName0+"=? AND "+SYNCHRONIZATION_TABLE_serverName0+"=? AND "+SYNCHRONIZATION_TABLE_datasourceName0+"=?");
    s.setString(1, o.getDomainName());
    s.setString(2, o.getServerName());
    s.setString(3, o.getDatasourceName());
    s.setString(4, o.getUniqueID());
    s.setString(5, domainName);
    s.setString(6, serverName);
    s.setString(7, datasourceName);
    ResultSet r = database().executeQuery(s);
    if (!r.next()) throw new MatchNotFound(o.toString(), domainName+"."+serverName+"."+datasourceName);
    SynchronizationRecord rec = unmarshallSynchronizationRecord(r);
    if (rec.getOrigin0().isTheSameAs(o)) lookup = rec.getOriginA();
    else lookup=rec.getOrigin0();
    r.close(); r=null;
    return lookup;
  }

  public static Collection findAllSynchronizationRecords(Origin a) throws Exception {
    PreparedStatement s = database().prepareStatement("SELECT * FROM "+SYNCHRONIZATION_TABLE+" WHERE ("+SYNCHRONIZATION_TABLE_domainName0+"=? AND "+SYNCHRONIZATION_TABLE_serverName0+"=? AND "+SYNCHRONIZATION_TABLE_datasourceName0+"=? AND "+SYNCHRONIZATION_TABLE_uid0+"=?) OR ("+SYNCHRONIZATION_TABLE_domainNameA+"=? AND "+SYNCHRONIZATION_TABLE_serverNameA+"=? AND "+SYNCHRONIZATION_TABLE_datasourceNameA+"=? AND "+SYNCHRONIZATION_TABLE_uidA+"=?) ORDER BY "+SYNCHRONIZATION_TABLE_serverName0+", "+SYNCHRONIZATION_TABLE_datasourceName0+", "+SYNCHRONIZATION_TABLE_uid0+", "+SYNCHRONIZATION_TABLE_serverNameA+", "+SYNCHRONIZATION_TABLE_datasourceNameA+", "+SYNCHRONIZATION_TABLE_uidA);
    s.setString(1,a.getDomainName());
    s.setString(2,a.getServerName());
    s.setString(3,a.getDatasourceName());
    s.setString(4,a.getUniqueID());
    s.setString(5,a.getDomainName());
    s.setString(6,a.getServerName());
    s.setString(7,a.getDatasourceName());
    s.setString(8,a.getUniqueID());
    ResultSet r = database().executeQuery(s);
    Collection x = unmarshallSynchronizationRecords(r);
    r.close(); r=null;
    Collection origins = new Vector();
    if (null!=x) {
      SynchronizationRecord rec;
      Origin o;
      Iterator i = x.iterator();
      while (i.hasNext()) {
        rec = (SynchronizationRecord) i.next();
        if (rec.getOrigin0().isTheSameAs(a))
          origins.add(rec.getOriginA());
        else
          origins.add(rec.getOrigin0());
      }
    }
    return origins;
  }

  public static Collection findAllSynchronizationRecords(String name) throws Exception {
    PreparedStatement s = database().prepareStatement("SELECT * FROM "+SYNCHRONIZATION_TABLE+" WHERE "+SYNCHRONIZATION_TABLE_name0+"=? OR "+SYNCHRONIZATION_TABLE_nameA+"=? ORDER BY "+SYNCHRONIZATION_TABLE_domainName0+", "+SYNCHRONIZATION_TABLE_serverName0+", "+SYNCHRONIZATION_TABLE_datasourceName0+", "+SYNCHRONIZATION_TABLE_uid0+", "+SYNCHRONIZATION_TABLE_domainNameA+", "+SYNCHRONIZATION_TABLE_serverNameA+", "+SYNCHRONIZATION_TABLE_datasourceNameA+", "+SYNCHRONIZATION_TABLE_uidA);
    s.setString(1,name);
    s.setString(2,name);
    ResultSet r = database().executeQuery(s);
    Collection x = unmarshallSynchronizationRecords(r);
    r.close(); r=null;
    return x;
  }

  public static Collection findNameSynchronization(String domain, String serverName, String source, String name) throws Exception {
    PreparedStatement s = database().prepareStatement("SELECT * FROM "+SYNCHRONIZATION_TABLE+" WHERE ("+SYNCHRONIZATION_TABLE_domainName0+"=? AND "+SYNCHRONIZATION_TABLE_serverName0+"=? AND "+SYNCHRONIZATION_TABLE_datasourceName0+"=? AND "+SYNCHRONIZATION_TABLE_name0+"=?) OR ("+SYNCHRONIZATION_TABLE_domainNameA+"=? AND "+SYNCHRONIZATION_TABLE_serverNameA+"=? AND "+SYNCHRONIZATION_TABLE_datasourceNameA+"=? AND "+SYNCHRONIZATION_TABLE_nameA+"=?) ORDER BY "+SYNCHRONIZATION_TABLE_domainName0+", "+SYNCHRONIZATION_TABLE_serverName0+", "+SYNCHRONIZATION_TABLE_datasourceName0+", "+SYNCHRONIZATION_TABLE_uid0+", "+SYNCHRONIZATION_TABLE_domainNameA+", "+SYNCHRONIZATION_TABLE_serverNameA+", "+SYNCHRONIZATION_TABLE_datasourceNameA+", "+SYNCHRONIZATION_TABLE_uidA);
    s.setString(1,domain);
    s.setString(2,serverName);
    s.setString(3,source);
    s.setString(4,name);
    s.setString(5,domain);
    s.setString(6,serverName);
    s.setString(7,source);
    s.setString(8,name);
    ResultSet r = database().executeQuery(s);
    Collection x = unmarshallSynchronizationRecords(r);
    r.close(); r=null;
    return x;
  }




  public static Collection findDatasourceSynchronization(String domain, String serverName, String source) throws Exception {
    PreparedStatement s = database().prepareStatement("SELECT * FROM "+SYNCHRONIZATION_TABLE+" WHERE ("+SYNCHRONIZATION_TABLE_domainName0+"=? AND "+SYNCHRONIZATION_TABLE_serverName0+"=? AND "+SYNCHRONIZATION_TABLE_datasourceName0+"=?) OR ("+SYNCHRONIZATION_TABLE_domainNameA+"=? AND "+SYNCHRONIZATION_TABLE_serverNameA+"=? AND "+SYNCHRONIZATION_TABLE_datasourceNameA+"=?) ORDER BY "+SYNCHRONIZATION_TABLE_domainName0+", "+SYNCHRONIZATION_TABLE_serverName0+", "+SYNCHRONIZATION_TABLE_datasourceName0+", "+SYNCHRONIZATION_TABLE_uid0+", "+SYNCHRONIZATION_TABLE_domainNameA+","+SYNCHRONIZATION_TABLE_serverNameA+", "+SYNCHRONIZATION_TABLE_datasourceNameA+", "+SYNCHRONIZATION_TABLE_uidA);
    s.setString(1,domain);
    s.setString(2,serverName);
    s.setString(3,source);
    s.setString(4,domain);
    s.setString(5,serverName);
    s.setString(6,source);
    ResultSet r = database().executeQuery(s);
    Collection x = unmarshallSynchronizationRecords(r);
    r.close(); r=null;
    return x;
  }

  public static Collection findServerSynchronization(String domain, String serverName) throws Exception {
    PreparedStatement s = database().prepareStatement("SELECT * FROM "+SYNCHRONIZATION_TABLE+" WHERE ("+SYNCHRONIZATION_TABLE_domainName0+"=? AND "+SYNCHRONIZATION_TABLE_serverName0+"=?) OR ("+SYNCHRONIZATION_TABLE_domainNameA+"=? AND "+SYNCHRONIZATION_TABLE_serverNameA+"=?) ORDER BY "+SYNCHRONIZATION_TABLE_domainName0+", "+SYNCHRONIZATION_TABLE_serverName0+", "+SYNCHRONIZATION_TABLE_datasourceName0+", "+SYNCHRONIZATION_TABLE_uid0+", "+SYNCHRONIZATION_TABLE_domainNameA+", "+SYNCHRONIZATION_TABLE_serverNameA+", "+SYNCHRONIZATION_TABLE_datasourceNameA+", "+SYNCHRONIZATION_TABLE_uidA);
    s.setString(1,domain);
    s.setString(2,serverName);
    s.setString(3,domain);
    s.setString(4,serverName);
    ResultSet r = database().executeQuery(s);
    Collection x = unmarshallSynchronizationRecords(r);
    r.close(); r=null;
    return x;
  }

  public static Calendar lastNameSynchronization(String name) throws Exception {
    String t0=null, t1=null;
    Calendar d0=null, d1=null;


	// Rodney McCabe 11/26/2007, add Oracle Support
    String sql = "SELECT "+SYNCHRONIZATION_TABLE_timeOfCreation0+" FROM "+SYNCHRONIZATION_TABLE+" WHERE "+SYNCHRONIZATION_TABLE_name0+"=? OR "+SYNCHRONIZATION_TABLE_nameA+"=? ORDER BY "+SYNCHRONIZATION_TABLE_timeOfCreation0+" DESC ";
    sql = database().limitResults(sql, 1);

    PreparedStatement s = database().prepareStatement(sql);
    s.setString(1,name);
    s.setString(2,name);
    ResultSet r = database().executeQuery(s);
    if (r.next()) {
      t0 = r.getString(SYNCHRONIZATION_TABLE_timeOfCreation0);
      try { d0 = database().parseDate(t0); }
      catch (InvalidSyntax e) { e.printStackTrace(); }
    }
    r.close(); r=null;

    sql = "SELECT "+SYNCHRONIZATION_TABLE_timeOfCreationA+" FROM "+SYNCHRONIZATION_TABLE+" WHERE "+SYNCHRONIZATION_TABLE_name0+"=? OR "+SYNCHRONIZATION_TABLE_nameA+"=? ORDER BY "+SYNCHRONIZATION_TABLE_timeOfCreationA+" DESC ";
	if ( database() instanceof zws.database.Oracle ) sql = sql + "rownum<=1";
		else sql=sql+"limit 1";

    s = database().prepareStatement(sql);
    s.setString(1,name);
    s.setString(2,name);
    r = database().executeQuery(s);
    if (r.next()) {
      t1 = r.getString(SYNCHRONIZATION_TABLE_timeOfCreationA);
      try { d1 = database().parseDate(t1); }
      catch (InvalidSyntax e) { e.printStackTrace(); }
    }
    r.close(); r=null;

    if (null==d0 && null==d1) return null;
    if (null==d0) return d1;
    if (null==d1) return d0;
    if (d0.after(d1)) return d0;
    return d1;
  }

  public static Collection findMatches(String domain, String serverName, String source, String name) throws Exception {
    Collection matches = new Vector();
    Collection c = findNameSynchronization(domain, serverName, source, name);
    if (null==c || c.size()==0) return matches;
    SynchronizationRecord r;
    Iterator i = c.iterator();
    while(i.hasNext()) {
      r = (SynchronizationRecord)i.next();
      matches.add(r.getOrigin0());
      matches.add(r.getOriginA());
    }
    return matches;
  }

  public static Origin lastSynchronization(String domain, String serverName, String source, String name) throws Exception {
    Collection origin0s = findOrigin0s(domain, serverName, source, name);
    Collection originAs = findOriginAs(domain, serverName, source, name);
    Origin origin, lastOrigin=null;
    Iterator i = origin0s.iterator();
    while(i.hasNext()) {
      origin = (Origin)i.next();
      if (null==lastOrigin || origin.isLater(lastOrigin)) lastOrigin=origin;
    }
    i = originAs.iterator();
    while(i.hasNext()) {
      origin = (Origin)i.next();
      if (null==lastOrigin || origin.isLater(lastOrigin)) lastOrigin=origin;
    }
    return lastOrigin;
  }

  public static Calendar lastDatasourceSynchronization(String domain, String serverName, String source) throws Exception {
    String t0=null, t1=null;
    Calendar d0=null, d1=null;

	// Rodney McCabe 11/26/2007, add Oracle Support
    String sql = "SELECT "+SYNCHRONIZATION_TABLE_timeOfCreation0+" FROM "+SYNCHRONIZATION_TABLE+" WHERE ("+SYNCHRONIZATION_TABLE_domainName0+"=? AND "+SYNCHRONIZATION_TABLE_serverName0+"=? AND "+SYNCHRONIZATION_TABLE_datasourceName0+"=?) OR ("+SYNCHRONIZATION_TABLE_domainNameA+"=? AND "+SYNCHRONIZATION_TABLE_serverNameA+"=? AND "+SYNCHRONIZATION_TABLE_datasourceNameA+"=?)  ORDER BY "+SYNCHRONIZATION_TABLE_timeOfCreation0+" DESC ";
    sql = database().limitResults(sql, 1);

    PreparedStatement s = database().prepareStatement(sql);
    s.setString(1,domain);
    s.setString(2,serverName);
    s.setString(3,source);
    s.setString(4,domain);
    s.setString(5,serverName);
    s.setString(6,source);
    ResultSet r = database().executeQuery(s);
    if (r.next()) {
      t0 = r.getString(SYNCHRONIZATION_TABLE_timeOfCreation0);
      try { d0 = database().parseDate(t0); }
      catch (InvalidSyntax e) { e.printStackTrace(); }
    }
    r.close(); r=null;

	// Rodney McCabe 11/26/2007, add Oracle Support
    sql = "SELECT "+SYNCHRONIZATION_TABLE_timeOfCreationA+" FROM "+SYNCHRONIZATION_TABLE+" WHERE ("+SYNCHRONIZATION_TABLE_domainName0+"=? AND "+SYNCHRONIZATION_TABLE_serverName0+"=? AND "+SYNCHRONIZATION_TABLE_datasourceName0+"=?) OR ("+SYNCHRONIZATION_TABLE_domainNameA+"=? AND "+SYNCHRONIZATION_TABLE_serverNameA+"=? AND "+SYNCHRONIZATION_TABLE_datasourceNameA+"=?)  ORDER BY "+SYNCHRONIZATION_TABLE_timeOfCreationA+" DESC ";
    sql = database().limitResults(sql, 1);

    s = database().prepareStatement(sql);
    s.setString(1,domain);
    s.setString(2,serverName);
    s.setString(3,source);
    s.setString(4,domain);
    s.setString(5,serverName);
    s.setString(6,source);
    r = database().executeQuery(s);
    if (r.next()) {
      t1 = r.getString(SYNCHRONIZATION_TABLE_timeOfCreationA);
      try { d1 = database().parseDate(t1); }
      catch (InvalidSyntax e) { e.printStackTrace(); }
    }
    r.close(); r=null;

    if (null==d0 && null==d1) return null;
    if (null==d0) return d1;
    if (null==d1) return d0;
    if (d0.after(d1)) return d0;
    return d1;
  }

  public static Calendar lastServerSynchronization(String domain, String serverName) throws Exception {
    String t0=null, t1=null;
    Calendar d0=null, d1=null;

	// Rodney McCabe 11/26/2007, add Oracle Support
    String sql = "SELECT "+SYNCHRONIZATION_TABLE_timeOfCreation0+" FROM "+SYNCHRONIZATION_TABLE+" WHERE ("+SYNCHRONIZATION_TABLE_domainName0+"=? AND "+SYNCHRONIZATION_TABLE_serverName0+"=?) OR ("+SYNCHRONIZATION_TABLE_domainNameA+"=? AND "+SYNCHRONIZATION_TABLE_serverNameA+"=?) ORDER BY "+SYNCHRONIZATION_TABLE_timeOfCreation0+" DESC ";
    sql = database().limitResults(sql, 1);

    PreparedStatement s = database().prepareStatement(sql);
    s.setString(1,domain);
    s.setString(2,serverName);
    s.setString(3,domain);
    s.setString(4,serverName);
    ResultSet r = database().executeQuery(s);
    if (r.next()) {
      t0 = r.getString(SYNCHRONIZATION_TABLE_timeOfCreation0);
      try { d0 = database().parseDate(t0); }
      catch (InvalidSyntax e) { e.printStackTrace(); }
    }
    r.close(); r=null;

	// Rodney McCabe 11/26/2007, add Oracle Support
    sql = "SELECT "+SYNCHRONIZATION_TABLE_timeOfCreationA+" FROM "+SYNCHRONIZATION_TABLE+" WHERE ("+SYNCHRONIZATION_TABLE_domainName0+"=? AND "+SYNCHRONIZATION_TABLE_serverName0+"=?) OR ("+SYNCHRONIZATION_TABLE_domainNameA+"=? OR "+SYNCHRONIZATION_TABLE_serverNameA+"=?) ORDER BY "+SYNCHRONIZATION_TABLE_timeOfCreationA+" DESC ";
	if ( database() instanceof zws.database.Oracle ) sql = sql + "rownum<=1";
		else sql=sql+"limit 1";

    s = database().prepareStatement(sql);
    s.setString(1,domain);
    s.setString(2,serverName);
    s.setString(3,domain);
    s.setString(4,serverName);
    r = database().executeQuery(s);
    if (r.next()) {
      t1 = r.getString(SYNCHRONIZATION_TABLE_timeOfCreationA);
      try { d1 = database().parseDate(t1); }
      catch (InvalidSyntax e) { e.printStackTrace(); }
    }
    r.close(); r=null;

    if (null==d0 && null==d1) return null;
    if (null==d0) return d1;
    if (null==d1) return d0;
    if (d0.after(d1)) return d0;
    return d1;
  }

  private static Collection findOrigin0s(String domainName, String serverName, String source, String name) throws Exception {
    String domainNameX, serverNameX, datasourceTypeX, datasourceNameX, uidX, nameX, locationX, stateX;
    long timeOfCreationX;
    String m=",";
    String select = SYNCHRONIZATION_TABLE_domainName0+m+SYNCHRONIZATION_TABLE_serverName0+m+SYNCHRONIZATION_TABLE_datasourceType0+m+SYNCHRONIZATION_TABLE_datasourceName0+m+SYNCHRONIZATION_TABLE_timeOfCreation0+m+SYNCHRONIZATION_TABLE_uid0+m+SYNCHRONIZATION_TABLE_name0+m+SYNCHRONIZATION_TABLE_location0+m+SYNCHRONIZATION_TABLE_state0;

    PreparedStatement s = database().prepareStatement("SELECT "+select+" FROM  "+SYNCHRONIZATION_TABLE+"  WHERE ("+SYNCHRONIZATION_TABLE_domainName0+"=? AND "+SYNCHRONIZATION_TABLE_serverName0+"=? AND "+SYNCHRONIZATION_TABLE_datasourceName0+"=? AND "+SYNCHRONIZATION_TABLE_name0+"=?)");
    s.setString(1,domainName);
    s.setString(2,serverName);
    s.setString(3,source);
    s.setString(4,name);
    ResultSet r = database().executeQuery(s);
    OriginBase originX;
    Collection origin0s = new Vector();
    while (r.next()) {
      domainNameX = r.getString(SYNCHRONIZATION_TABLE_domainName0);
      serverNameX = r.getString(SYNCHRONIZATION_TABLE_serverName0);
      datasourceTypeX = r.getString(SYNCHRONIZATION_TABLE_datasourceType0);
      datasourceNameX = r.getString(SYNCHRONIZATION_TABLE_datasourceName0);
      timeOfCreationX = r.getLong(SYNCHRONIZATION_TABLE_timeOfCreation0);
      uidX = r.getString(SYNCHRONIZATION_TABLE_uid0);
      nameX = r.getString(SYNCHRONIZATION_TABLE_name0);
      locationX = r.getString(SYNCHRONIZATION_TABLE_location0);
      stateX = r.getString(SYNCHRONIZATION_TABLE_state0);
      originX = (OriginBase)OriginMaker.materialize(domainNameX, serverNameX, datasourceTypeX, datasourceNameX, timeOfCreationX, uidX, locationX, stateX);
      originX.setName(nameX);
      origin0s.add(originX);
    }
    r.close(); r=null;
    return origin0s;
  }

  private static Collection findOriginAs(String domainName, String serverName, String source, String name) throws Exception {
    String domainNameX, serverNameX, datasourceTypeX, datasourceNameX, uidX, nameX, locationX, stateX;
    long timeOfCreationX;
    String m=",";

    String select = SYNCHRONIZATION_TABLE_domainNameA+m+SYNCHRONIZATION_TABLE_serverNameA+m+SYNCHRONIZATION_TABLE_datasourceTypeA+m+SYNCHRONIZATION_TABLE_datasourceNameA+m+SYNCHRONIZATION_TABLE_timeOfCreationA+m+SYNCHRONIZATION_TABLE_uidA+m+SYNCHRONIZATION_TABLE_nameA+m+SYNCHRONIZATION_TABLE_locationA+m+SYNCHRONIZATION_TABLE_stateA;
    PreparedStatement s = database().prepareStatement("SELECT "+select+" FROM  "+SYNCHRONIZATION_TABLE+"  WHERE ("+SYNCHRONIZATION_TABLE_domainNameA+"=? AND "+SYNCHRONIZATION_TABLE_serverNameA+"=? AND "+SYNCHRONIZATION_TABLE_datasourceNameA+"=? AND "+SYNCHRONIZATION_TABLE_nameA+"=?)");
    s.setString(1,domainName);
    s.setString(2,serverName);
    s.setString(3,source);
    s.setString(4,name);
    ResultSet r = database().executeQuery(s);
    OriginBase originX;
    Collection originAs = new Vector();
    while (r.next()) {
      domainNameX = r.getString(SYNCHRONIZATION_TABLE_domainNameA);
      serverNameX = r.getString(SYNCHRONIZATION_TABLE_serverNameA);
      datasourceTypeX = r.getString(SYNCHRONIZATION_TABLE_datasourceTypeA);
      datasourceNameX = r.getString(SYNCHRONIZATION_TABLE_datasourceNameA);
      timeOfCreationX = r.getLong(SYNCHRONIZATION_TABLE_timeOfCreationA);
      uidX = r.getString(SYNCHRONIZATION_TABLE_uidA);
      nameX = r.getString(SYNCHRONIZATION_TABLE_nameA);
      locationX = r.getString(SYNCHRONIZATION_TABLE_locationA);
      stateX = r.getString(SYNCHRONIZATION_TABLE_stateA);
      originX = (OriginBase)OriginMaker.materialize(domainNameX, serverNameX, datasourceTypeX, datasourceNameX, timeOfCreationX, uidX, locationX, stateX);
      originX.setName(nameX);
      originAs.add(originX);
    }
    r.close(); r=null;
    return originAs;
  }

  private static SynchronizationRecord unmarshallSynchronizationRecord(ResultSet r) throws SQLException, CircularDependency, CanNotMaterialize {
    String domainName0, serverName0, datasourceType0, datasourceName0, uid0, name0, location0, state0;
    String domainNameA, serverNameA, datasourceTypeA, datasourceNameA, uidA, nameA, locationA, stateA;

    long timeOfCreation0, timeOfCreationA;

    SynchronizationRecordBase sync = new SynchronizationRecordBase();
    domainName0 = r.getString(SYNCHRONIZATION_TABLE_domainName0);
    serverName0 = r.getString(SYNCHRONIZATION_TABLE_serverName0);
    datasourceType0 = r.getString(SYNCHRONIZATION_TABLE_datasourceType0);
    datasourceName0 = r.getString(SYNCHRONIZATION_TABLE_datasourceName0);
    timeOfCreation0 = r.getLong(SYNCHRONIZATION_TABLE_timeOfCreation0);
    uid0 = r.getString(SYNCHRONIZATION_TABLE_uid0);
    name0 = r.getString(SYNCHRONIZATION_TABLE_name0);
    location0 = r.getString(SYNCHRONIZATION_TABLE_location0);
    state0 = r.getString(SYNCHRONIZATION_TABLE_state0);

    domainNameA = r.getString(SYNCHRONIZATION_TABLE_domainNameA);
    serverNameA = r.getString(SYNCHRONIZATION_TABLE_serverNameA);
    datasourceTypeA = r.getString(SYNCHRONIZATION_TABLE_datasourceTypeA);
    datasourceNameA = r.getString(SYNCHRONIZATION_TABLE_datasourceNameA);
    timeOfCreationA = r.getLong(SYNCHRONIZATION_TABLE_timeOfCreationA);
    uidA = r.getString(SYNCHRONIZATION_TABLE_uidA);
    nameA = r.getString(SYNCHRONIZATION_TABLE_nameA);
    locationA = r.getString(SYNCHRONIZATION_TABLE_locationA);
    stateA = r.getString(SYNCHRONIZATION_TABLE_stateA);

    Origin origin0 = OriginMaker.materialize(domainName0, serverName0, datasourceType0, datasourceName0, timeOfCreation0, uid0, location0, state0);
    Origin originA = OriginMaker.materialize(domainNameA, serverNameA, datasourceTypeA, datasourceNameA, timeOfCreationA, uidA, locationA, stateA);

    sync.setData(origin0, originA);
    return sync;
  }

  private static Collection unmarshallSynchronizationRecords(ResultSet r) throws SQLException, CircularDependency, CanNotMaterialize {
    Collection c = new Vector();
    while (r.next()) c.add(unmarshallSynchronizationRecord(r));
    return c;
  }

  private static Database database()  throws Exception { return DB.source(Names.SYNCHRONIZATION_DATABASE); }
  //private static String databaseName() { return Properties.get(Names.SYNCHRONIZATION_DATABASE); }

  private static String SYNCHRONIZATION_TABLE = "synchronizationLog";

  private static String SYNCHRONIZATION_TABLE_domainName0 = "domainName0";
  private static String SYNCHRONIZATION_TABLE_serverName0 = "serverName0";
  private static String SYNCHRONIZATION_TABLE_datasourceType0 = "datasourceType0";
  private static String SYNCHRONIZATION_TABLE_datasourceName0 = "datasourceName0";
  private static String SYNCHRONIZATION_TABLE_timeOfCreation0 = "stamp0";
  private static String SYNCHRONIZATION_TABLE_uid0 = "uid0";
  private static String SYNCHRONIZATION_TABLE_name0 = "name0";
  private static String SYNCHRONIZATION_TABLE_location0 = "location0";
  private static String SYNCHRONIZATION_TABLE_state0 = "state0";
  private static String SYNCHRONIZATION_TABLE_domainNameA = "domainNameA";
  private static String SYNCHRONIZATION_TABLE_serverNameA = "serverNameA";
  private static String SYNCHRONIZATION_TABLE_datasourceTypeA = "datasourceTypeA";
  private static String SYNCHRONIZATION_TABLE_datasourceNameA = "datasourceNameA";
  private static String SYNCHRONIZATION_TABLE_timeOfCreationA = "stampA";
  private static String SYNCHRONIZATION_TABLE_uidA = "uidA";
  private static String SYNCHRONIZATION_TABLE_nameA = "nameA";
  private static String SYNCHRONIZATION_TABLE_locationA = "locationA";
  private static String SYNCHRONIZATION_TABLE_stateA = "stateA";

  private static String DOT=".";
  private static String delim = Names.ORIGIN_DELIMITER;
}