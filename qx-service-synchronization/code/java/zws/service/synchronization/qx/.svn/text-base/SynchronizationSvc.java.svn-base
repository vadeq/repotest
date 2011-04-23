package zws.service.synchronization.qx;

/*
 * DesignState - Design Compression Technology
 * @author: ptoleti @version: 1.0
 * Created on Jun 13, 2007 10:36:09 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */


import zws.application.Names;
import zws.database.DB;
import zws.database.Database;
import zws.datasource.Datasource;
import zws.exception.CanNotMaterialize;
import zws.exception.CircularDependency;
import zws.exception.Duplicate;
import zws.exception.InvalidSyntax;
import zws.origin.Origin;
import zws.origin.OriginBase;
import zws.origin.OriginMaker;
import zws.space.DataSpace;
import zws.synchronization.SynchronizationRecord;
import zws.synchronization.SynchronizationRecordBase;
import zws.util.StringUtil;
//impoer zws.util.{}//Logwriter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;


/**
 * The Class SynchronizationSvc.
 */
public class SynchronizationSvc {

  /**
   * Record.
   *
   * @param origins the origins
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  public static synchronized Collection record(Collection origins)
      throws Exception {
    Collection syncRecords = new Vector();
    Origin a, b;
    Object[] records = origins.toArray();
    for (int i = 0; i < records.length; i++) {
      a = (Origin) records[i];
      for (int j = 0; j < records.length; j++) {
        if (i != j) { // dont synchronize to self!
          b = (Origin) records[j];
          syncRecords.add(record(a, b));
        }
      }
    }
    return syncRecords;
  }

  /**
   * Record.
   *
   * @param b the b
   * @param a the a
   *
   * @return the synchronization record
   *
   * @throws Exception the exception
   */
  public static synchronized  SynchronizationRecord record(Origin a, Origin b)
      throws Exception {
    try {
      SynchronizationRecordBase r = new SynchronizationRecordBase(a, b);
      if (isSynchronized(a, b)) {
        return r;
      }
      record(a, findAllSynchronizationOrigins(b)); // if a is synked to b, then a should synkup with everything b is synked to
      record(b, findAllSynchronizationOrigins(a)); // if b is synked to a, then b should synkup with everything a is synked to
      record(r); // and, of course, synk a to b
      return r;
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * Record.
   *
   * @param origins the origins
   * @param a the a
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  public static synchronized  Collection record(Origin a, Collection origins)
      throws Exception {
    if (null == origins) {
      return null;
    }
    Collection syncRecords = new Vector();
    Origin b;
    Iterator i = origins.iterator();
    SynchronizationRecordBase r;
    while (i.hasNext()) {
      b = (Origin) i.next();
      if (!b.isTheSameAs(a)) { // avoid circular deps
        r = new SynchronizationRecordBase(a, b);
        record(r);
        syncRecords.add(r);
      }
    }
    return syncRecords;
  }

  /**
   * Record.
   *
   * @param sync the sync
   *
   * @throws Exception the exception
   */
  public static synchronized  void record(SynchronizationRecord sync)
      throws Exception {
    if (isSynchronized(sync.getOrigin0(), sync.getOriginA())) {
      return;
    }
    try {
      /*
       *  SYNCHRONIZATIONLOG DOMAINNAME0        VARCHAR2
          SYNCHRONIZATIONLOG  SERVERNAME0       VARCHAR2
          SYNCHRONIZATIONLOG  DATASOURCETYPE0   VARCHAR2
          SYNCHRONIZATIONLOG  DATASOURCENAME0   VARCHAR2
          SYNCHRONIZATIONLOG  STAMP0            NUMBER
          SYNCHRONIZATIONLOG  UID0              VARCHAR2
          SYNCHRONIZATIONLOG  NAME0             VARCHAR2
          SYNCHRONIZATIONLOG  LOCATION0         VARCHAR2
          SYNCHRONIZATIONLOG  STATE0            VARCHAR2
          SYNCHRONIZATIONLOG  DOMAINNAMEA       VARCHAR2
          SYNCHRONIZATIONLOG  SERVERNAMEA       VARCHAR2
          SYNCHRONIZATIONLOG  DATASOURCETYPEA   VARCHAR2
          SYNCHRONIZATIONLOG  DATASOURCENAMEA   VARCHAR2
          SYNCHRONIZATIONLOG  STAMPA            NUMBER
          SYNCHRONIZATIONLOG  UIDA              VARCHAR2
          SYNCHRONIZATIONLOG  NAMEA             VARCHAR2
          SYNCHRONIZATIONLOG  LOCATIONA         VARCHAR2
          SYNCHRONIZATIONLOG  STATEA            VARCHAR2

       */
      PreparedStatement s = database().prepareStatement(
          "INSERT into " + SYNCHRONIZATION_TABLE + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
      // java.sql.Date d = new java.sql.Date(System.currentTimeMillis());
      s.setString(1, StringUtil.truncateWithIndicator(sync.getDomainName0(), 3999));
      s.setString(2, StringUtil.truncateWithIndicator(sync.getServerName0(), 3999));
      s.setString(3, StringUtil.truncateWithIndicator(sync.getDatasourceType0(), 3999));
      s.setString(4, StringUtil.truncateWithIndicator(sync.getDatasourceName0(), 3999));
      s.setLong(5, sync.getTimeOfCreation0InMillis());
      s.setString(6, StringUtil.truncateWithIndicator(sync.getUID0(), 3999));
      s.setString(7, StringUtil.truncateWithIndicator(sync.getName0(), 3999));
      s.setString(8, StringUtil.truncateWithIndicator(sync.getLocationA(), 3999));
      s.setString(9, StringUtil.truncateWithIndicator(sync.getStateA(), 3999));
      s.setString(10, StringUtil.truncateWithIndicator(sync.getDomainNameA(), 3999));
      s.setString(11, StringUtil.truncateWithIndicator(sync.getServerNameA(), 3999));
      s.setString(12, StringUtil.truncateWithIndicator(sync.getDatasourceTypeA(), 3999));
      s.setString(13, StringUtil.truncateWithIndicator(sync.getDatasourceNameA(), 3999));
      s.setLong(14, sync.getTimeOfCreationAInMillis());
      s.setString(15, StringUtil.truncateWithIndicator(sync.getUIDA(), 3999));
      s.setString(16, StringUtil.truncateWithIndicator(sync.getNameA(), 3999));
      s.setString(17, StringUtil.truncateWithIndicator(sync.getLocationA(), 3999));
      s.setString(18, StringUtil.truncateWithIndicator(sync.getStateA(), 3999));
      database().execute(s);
    } catch (SQLException e) {
      if (-1 != e.getMessage().indexOf("Duplicate entry")) {
        throw new Duplicate(sync.toString());
      } else {
        throw e;
      }
    }
  }

  /**
   * Rename.
   *
   * @param source the source
   * @param name the name
   * @param domainName the domain name
   * @param newName the new name
   * @param serverName the server name
   *
   * @throws Exception the exception
   */
  public static synchronized  void rename(String domainName, String serverName,
      String source, String name, String newName) throws Exception {
    Collection oldOrigin0s = findOrigin0s(domainName, serverName, source, name);
    Collection oldOriginAs = findOriginAs(domainName, serverName, source, name);
    rename(oldOrigin0s, oldOriginAs, newName);
  }

  /**
   * Rename.
   *
   * @param oldOrigin0s the old origin0s
   * @param newName the new name
   * @param oldOriginAs the old origin as
   *
   * @throws Exception the exception
   */
  public static synchronized  void rename(Collection oldOrigin0s,
      Collection oldOriginAs, String newName) throws Exception {
    OriginBase origin, newOrigin;
    Iterator i = oldOrigin0s.iterator();
    PreparedStatement s;
    while (i.hasNext()) {
      origin = (OriginBase) i.next();
      newOrigin = (OriginBase) origin.copy();
      newOrigin.setName(newName);
      s = database().prepareStatement(
          "UPDATE " + SYNCHRONIZATION_TABLE + " SET "
              + SYNCHRONIZATION_TABLE_uid0 + "=?, "
              + SYNCHRONIZATION_TABLE_name0 + "=? WHERE ("
              + SYNCHRONIZATION_TABLE_domainName0 + "=? AND "
              + SYNCHRONIZATION_TABLE_serverName0 + "=? AND "
              + SYNCHRONIZATION_TABLE_datasourceName0 + "=? AND "
              + SYNCHRONIZATION_TABLE_name0 + "=?)");
      s.setString(1, newOrigin.getUniqueID());
      s.setString(2, newOrigin.getName());
      s.setString(3, origin.getDomainName());
      s.setString(4, origin.getServerName());
      s.setString(5, origin.getDatasourceName());
      s.setString(6, origin.getName());
      database().execute(s);
    }
    i = oldOriginAs.iterator();
    while (i.hasNext()) {
      origin = (OriginBase) i.next();
      newOrigin = (OriginBase) origin.copy();
      newOrigin.setName(newName);
      s = database().prepareStatement(
          "UPDATE " + SYNCHRONIZATION_TABLE + " SET "
              + SYNCHRONIZATION_TABLE_uidA + "=?, "
              + SYNCHRONIZATION_TABLE_nameA + "=? WHERE ("
              + SYNCHRONIZATION_TABLE_domainNameA + "=? AND "
              + SYNCHRONIZATION_TABLE_serverNameA + "=? AND "
              + SYNCHRONIZATION_TABLE_datasourceNameA + "=? AND "
              + SYNCHRONIZATION_TABLE_nameA + "=?)");
      s.setString(1, newOrigin.getUniqueID());
      s.setString(2, newOrigin.getName());
      s.setString(3, origin.getDomainName());
      s.setString(4, origin.getServerName());
      s.setString(5, origin.getDatasourceName());
      s.setString(6, origin.getName());
      database().execute(s);
    }
  }

  /**
   * Remove.
   *
   * @param sync the sync
   *
   * @throws Exception the exception
   */
  public static synchronized  void remove(SynchronizationRecord sync)
      throws Exception {
    PreparedStatement s;
    s = database().prepareStatement(
        "DELETE FROM " + SYNCHRONIZATION_TABLE + " WHERE "
            + SYNCHRONIZATION_TABLE_domainName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_serverName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_datasourceName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_uid0 + "=? AND "
            + SYNCHRONIZATION_TABLE_domainNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_serverNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_datasourceNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_uidA + "=?");
    s.setString(1, sync.getDomainName0());
    s.setString(2, sync.getServerName0());
    s.setString(3, sync.getDatasourceName0());
    s.setString(4, sync.getUID0());
    s.setString(5, sync.getDomainNameA());
    s.setString(6, sync.getServerNameA());
    s.setString(7, sync.getDatasourceNameA());
    s.setString(8, sync.getUIDA());
    database().execute(s);
  }

  /**
   * Purge matches.
   *
   * @param origin the origin
   *
   * @throws Exception the exception
   */
  public static void purgeMatches(Origin origin) throws Exception {
    Collection cc = findAllSynchronizationOrigins(origin);
    Iterator i = cc.iterator();
    Origin o;
    while (i.hasNext()) {
      o = (Origin) i.next();
      purgeByUID(o.getDomainName(), o.getServerName(), o.getDatasourceName(), o
          .getUniqueID());
    }
    purgeByUID(origin.getDomainName(), origin.getServerName(), origin
        .getDatasourceName(), origin.getUniqueID());
  }

  /**
   * Purge by UID.
   *
   * @param uid the uid
   * @param source the source
   * @param domainName the domain name
   * @param serverName the server name
   *
   * @throws Exception the exception
   */
  public static synchronized  void purgeByUID(String domainName,
      String serverName, String source, String uid) throws Exception {
    PreparedStatement s = database().prepareStatement(
        "DELETE FROM " + SYNCHRONIZATION_TABLE + " WHERE ("
            + SYNCHRONIZATION_TABLE_domainName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_serverName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_datasourceName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_uid0 + "=?) OR ("
            + SYNCHRONIZATION_TABLE_domainNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_serverNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_datasourceNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_uidA + "=?)");
    s.setString(1, domainName);
    s.setString(2, serverName);
    s.setString(3, source);
    s.setString(4, uid);
    s.setString(5, domainName);
    s.setString(6, serverName);
    s.setString(7, source);
    s.setString(8, uid);
    database().execute(s);
  }

  /**
   * Purge by name.
   *
   * @param source the source
   * @param name the name
   * @param domainName the domain name
   * @param serverName the server name
   *
   * @throws Exception the exception
   */
  public static synchronized  void purgeByName(String domainName,
      String serverName, String source, String name) throws Exception {
    PreparedStatement s = database().prepareStatement(
        "DELETE FROM " + SYNCHRONIZATION_TABLE + " WHERE ("
            + SYNCHRONIZATION_TABLE_domainName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_serverName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_datasourceName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_name0 + "=?) OR ("
            + SYNCHRONIZATION_TABLE_domainNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_serverNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_datasourceNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_nameA + "=?)");
    s.setString(1, domainName);
    s.setString(2, serverName);
    s.setString(3, source);
    s.setString(4, name);
    s.setString(5, domainName);
    s.setString(6, serverName);
    s.setString(7, source);
    s.setString(8, name);
    database().execute(s);
  }

  /**
   * Purge datasource records.
   *
   * @param source the source
   * @param domainName the domain name
   * @param serverName the server name
   *
   * @throws Exception the exception
   */
  public static synchronized  void purgeDatasourceRecords(String domainName,
      String serverName, String source) throws Exception {
    PreparedStatement s = database().prepareStatement(
        "DELETE FROM " + SYNCHRONIZATION_TABLE + " WHERE ("
            + SYNCHRONIZATION_TABLE_domainName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_serverName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_datasourceName0 + "=?) OR ("
            + SYNCHRONIZATION_TABLE_domainNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_serverNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_datasourceNameA + "=?)");
    s.setString(1, domainName);
    s.setString(2, serverName);
    s.setString(3, source);
    s.setString(4, domainName);
    s.setString(5, serverName);
    s.setString(6, source);
    database().execute(s);
  }

  /**
   * Purge server records.
   *
   * @param domainName the domain name
   * @param serverName the server name
   *
   * @throws Exception the exception
   */
  public static synchronized  void purgeServerRecords(String domainName,
      String serverName) throws Exception {
    try {
      PreparedStatement s = database().prepareStatement(
          "DELETE FROM " + SYNCHRONIZATION_TABLE + " WHERE ("
              + SYNCHRONIZATION_TABLE_domainName0 + "=? AND "
              + SYNCHRONIZATION_TABLE_serverName0 + "=?) OR ("
              + SYNCHRONIZATION_TABLE_domainNameA + "=? AND "
              + SYNCHRONIZATION_TABLE_serverNameA + "=?)");
      s.setString(1, domainName);
      s.setString(2, serverName);
      s.setString(3, domainName);
      s.setString(4, serverName);
      database().execute(s);
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
    //zws.util.{}//Logwriter.printOnConsole("purgeServerRecords executed");
  }

  /**
   * Checks if is synchronized.
   *
   * @param origins the origins
   *
   * @return true, if is synchronized
   *
   * @throws Exception the exception
   */
  public static boolean isSynchronized(Collection origins) throws Exception {
    if (null == origins || origins.size() < 2) {
      return false;
    }
    // boolean synced = true;
    Origin a, b;
    Iterator i = origins.iterator();
    if (i.hasNext()) {
      a = (Origin) i.next();
    } else {
      return false;
    }
    while (i.hasNext()) {
      b = (Origin) i.next();
      if (!isSynchronized(a, b)) {
        return false;
      }
      a = b;
    }
    return true;
  }

  /**
   * Checks if is synchronized.
   *
   * @param b the b
   * @param a the a
   *
   * @return true, if is synchronized
   *
   * @throws Exception the exception
   */
  public static boolean isSynchronized(Origin a, Origin b) throws Exception {
    SynchronizationRecordBase sync = new SynchronizationRecordBase(a, b);
    PreparedStatement s = database().prepareStatement(
        "SELECT " + SYNCHRONIZATION_TABLE_uidA + " FROM "
            + SYNCHRONIZATION_TABLE + " WHERE "
            + SYNCHRONIZATION_TABLE_domainName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_serverName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_datasourceName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_uid0 + "=? AND "
            + SYNCHRONIZATION_TABLE_domainNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_serverNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_datasourceNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_uidA + "=?");
    s.setString(1, sync.getDomainName0());
    s.setString(2, sync.getServerName0());
    s.setString(3, sync.getDatasourceName0());
    s.setString(4, sync.getUID0());
    s.setString(5, sync.getDomainNameA());
    s.setString(6, sync.getServerNameA());
    s.setString(7, sync.getDatasourceNameA());
    s.setString(8, sync.getUIDA());
    ResultSet r = database().executeQuery(s);
    boolean x = false;
    if (r.next()) {
      x = true;
    }
    r.close();
    r = null;
    s.close();
    s = null;
    
    return x;
  }

  /**
   * Checks if is synchronized to datasource.
   *
   * @param o the o
   * @param targetDatasources the target datasources
   *
   * @return true, if is synchronized to datasource
   *
   * @throws Exception the exception
   */
  public static boolean isSynchronizedToDatasource(Origin o, Collection targetDatasources) throws Exception {
    if (null == targetDatasources || targetDatasources.size() < 1) {
      return false;
    }
    Iterator i = targetDatasources.iterator();
    Datasource d;
    while (i.hasNext()) {
      d = (Datasource) i.next();
      if (isSynchronizedToDatasource(o, d)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if is indirectly synchronized to datasource.
   *
   * @param target the target
   * @param o the o
   *
   * @return true, if is indirectly synchronized to datasource
   *
   * @throws Exception the exception
   */
  public static boolean isIndirectlySynchronizedToDatasource(Origin o, Datasource target) throws Exception {
    Collection c = findAllSynchronizationOrigins(o);
    if (null == c) {
      return false;
    }
    Collection origins = new Vector();
    origins.addAll(c);
    Iterator i = c.iterator();
    while (i.hasNext()) {
      origins.addAll(findAllSynchronizationOrigins((Origin) i.next()));
    }
      // go 2 levels deep to find all synchronizations
    i = origins.iterator();
    Origin x;
    while (i.hasNext()) {
      x = (Origin) i.next();
      if (x.getServerName().equals(target.getServerName())
          && x.getDatasourceName().equals(target.getName())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if is synchronized to datasource.
   *
   * @param target the target
   * @param o the o
   *
   * @return true, if is synchronized to datasource
   *
   * @throws Exception the exception
   */
  public static boolean isSynchronizedToDatasource(Origin o, DataSpace target)
      throws Exception {
    return isSynchronizedToDatasource(o, target.getDomainName(), target
        .getServerName(), target.getDatasourceName());
  }

  /**
   * Checks if is synchronized to datasource.
   *
   * @param target the target
   * @param o the o
   *
   * @return true, if is synchronized to datasource
   *
   * @throws Exception the exception
   */
  public static boolean isSynchronizedToDatasource(Origin o, Datasource target)
      throws Exception {
    return isSynchronizedToDatasource(o, target.getDomainName(), target
        .getServerName(), target.getName());
  }

  /**
   * Checks if is synchronized to datasource.
   *
   * @param datasourceName the datasource name
   * @param o the o
   * @param domainName the domain name
   * @param serverName the server name
   *
   * @return true, if is synchronized to datasource
   *
   * @throws Exception the exception
   */
  public static boolean isSynchronizedToDatasource(Origin o, String domainName,
      String serverName, String datasourceName) throws Exception {
    try {
      return null != findSynchronization(o, domainName, serverName, datasourceName);
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Find synchronization.
   *
   * @param datasourceName the datasource name
   * @param o the o
   * @param domainName the domain name
   * @param serverName the server name
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
  public static Origin findSynchronization(Origin o, String domainName, String serverName, String datasourceName) throws Exception {
    Origin lookup = null;
    OriginBase targetOrigin = (OriginBase) o.copy();
    targetOrigin.setDomainName(domainName);
    targetOrigin.setServerName(serverName);
    targetOrigin.setDatasourceName(datasourceName);

    // Origin targetOrigin = OriginMaker.materialize(domainName, serverName,
    // datasourceType, datasourceName, o.getTimeOfCreationInMillis(), "unused|no
    // name");
    SynchronizationRecord sync = new SynchronizationRecordBase(o, targetOrigin);
    PreparedStatement s;
    if (sync.getOrigin0().isExactlyTheSame(o)) {
      s = database().prepareStatement(
          "SELECT * FROM " + SYNCHRONIZATION_TABLE + " WHERE "
              + SYNCHRONIZATION_TABLE_domainName0 + "=? AND "
              + SYNCHRONIZATION_TABLE_serverName0 + "=? AND "
              + SYNCHRONIZATION_TABLE_datasourceName0 + "=? AND "
              + SYNCHRONIZATION_TABLE_uid0 + "=? AND "
              + SYNCHRONIZATION_TABLE_domainNameA + "=? AND "
              + SYNCHRONIZATION_TABLE_serverNameA + "=? AND "
              + SYNCHRONIZATION_TABLE_datasourceNameA + "=?");
    } else {
      s = database().prepareStatement(
          "SELECT * FROM " + SYNCHRONIZATION_TABLE + " WHERE "
              + SYNCHRONIZATION_TABLE_domainNameA + "=? AND "
              + SYNCHRONIZATION_TABLE_serverNameA + "=? AND "
              + SYNCHRONIZATION_TABLE_datasourceNameA + "=? AND "
              + SYNCHRONIZATION_TABLE_uidA + "=? AND "
              + SYNCHRONIZATION_TABLE_domainName0 + "=? AND "
              + SYNCHRONIZATION_TABLE_serverName0 + "=? AND "
              + SYNCHRONIZATION_TABLE_datasourceName0 + "=?");
    }
    s.setString(1, o.getDomainName());
    s.setString(2, o.getServerName());
    s.setString(3, o.getDatasourceName());
    s.setString(4, o.getUniqueID());
    s.setString(5, domainName);
    s.setString(6, serverName);
    s.setString(7, datasourceName);
    {}//Logwriter.printOnConsole("query : " + s.toString());
    ResultSet r = database().executeQuery(s);
    /*if (!r.next()) {
      throw new MatchNotFound(o.toString(), domainName + "." + serverName + "." + datasourceName);
    }*/
    if (r.next()) {
      SynchronizationRecord rec = unmarshallSynchronizationRecord(r);
      if (rec.getOrigin0().isTheSameAs(o)) {
        lookup = rec.getOriginA();
      } else {
        lookup = rec.getOrigin0();
      }

    } else {
      {}//Logwriter.printOnConsole("No sync record found for " + o);
    }
    
    r.close();
    r = null;
    s.close();
    s = null;
    return lookup;
  }

  /**
   * Find all synchronization records.
   *
   * @param a the a
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  public static Collection findAllSynchronizationOrigins(Origin a)
      throws Exception {
    PreparedStatement s = database().prepareStatement(
        "SELECT * FROM " + SYNCHRONIZATION_TABLE + " WHERE ("
            + SYNCHRONIZATION_TABLE_domainName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_serverName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_datasourceName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_uid0 + "=?) OR ("
            + SYNCHRONIZATION_TABLE_domainNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_serverNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_datasourceNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_uidA + "=?) ORDER BY "
            + SYNCHRONIZATION_TABLE_serverName0 + ", "
            + SYNCHRONIZATION_TABLE_datasourceName0 + ", "
            + SYNCHRONIZATION_TABLE_uid0 + ", "
            + SYNCHRONIZATION_TABLE_serverNameA + ", "
            + SYNCHRONIZATION_TABLE_datasourceNameA + ", "
            + SYNCHRONIZATION_TABLE_uidA);
    s.setString(1, a.getDomainName());
    s.setString(2, a.getServerName());
    s.setString(3, a.getDatasourceName());
    s.setString(4, a.getUniqueID());
    s.setString(5, a.getDomainName());
    s.setString(6, a.getServerName());
    s.setString(7, a.getDatasourceName());
    s.setString(8, a.getUniqueID());
    ResultSet r = database().executeQuery(s);
    Collection x = unmarshallSynchronizationRecords(r);
    r.close();
    r = null;
    s.close();
    s = null;
    
    Collection origins = new Vector();
    if (null != x) {
      SynchronizationRecord rec;
      // Origin o;
      Iterator i = x.iterator();
      while (i.hasNext()) {
        rec = (SynchronizationRecord) i.next();
        if (rec.getOrigin0().isTheSameAs(a)) {
          origins.add(rec.getOriginA());
        } else {
          origins.add(rec.getOrigin0());
        }
      }
    }
    return origins;
  }

  /**
   * Find all synchronization records.
   *
   * @param name the name
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  public static Collection findAllSynchronizationRecords(String name)
      throws Exception {
    PreparedStatement s = database().prepareStatement(
        "SELECT * FROM " + SYNCHRONIZATION_TABLE + " WHERE "
            + SYNCHRONIZATION_TABLE_name0 + "=? OR "
            + SYNCHRONIZATION_TABLE_nameA + "=? ORDER BY "
            + SYNCHRONIZATION_TABLE_domainName0 + ", "
            + SYNCHRONIZATION_TABLE_serverName0 + ", "
            + SYNCHRONIZATION_TABLE_datasourceName0 + ", "
            + SYNCHRONIZATION_TABLE_uid0 + ", "
            + SYNCHRONIZATION_TABLE_domainNameA + ", "
            + SYNCHRONIZATION_TABLE_serverNameA + ", "
            + SYNCHRONIZATION_TABLE_datasourceNameA + ", "
            + SYNCHRONIZATION_TABLE_uidA);
    s.setString(1, name);
    s.setString(2, name);
    ResultSet r = database().executeQuery(s);
    Collection x = unmarshallSynchronizationRecords(r);
    r.close();
    r = null;
    s.close();
    s = null;
    return x;
  }

  /**
   * Find name synchronization.
   *
   * @param source the source
   * @param name the name
   * @param domain the domain
   * @param serverName the server name
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  public static Collection findNameSynchronization(String domain,
      String serverName, String source, String name) throws Exception {
    PreparedStatement s = database().prepareStatement(
        "SELECT * FROM " + SYNCHRONIZATION_TABLE + " WHERE ("
            + SYNCHRONIZATION_TABLE_domainName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_serverName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_datasourceName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_name0 + "=?) OR ("
            + SYNCHRONIZATION_TABLE_domainNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_serverNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_datasourceNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_nameA + "=?) ORDER BY "
            + SYNCHRONIZATION_TABLE_domainName0 + ", "
            + SYNCHRONIZATION_TABLE_serverName0 + ", "
            + SYNCHRONIZATION_TABLE_datasourceName0 + ", "
            + SYNCHRONIZATION_TABLE_uid0 + ", "
            + SYNCHRONIZATION_TABLE_domainNameA + ", "
            + SYNCHRONIZATION_TABLE_serverNameA + ", "
            + SYNCHRONIZATION_TABLE_datasourceNameA + ", "
            + SYNCHRONIZATION_TABLE_uidA);
    s.setString(1, domain);
    s.setString(2, serverName);
    s.setString(3, source);
    s.setString(4, name);
    s.setString(5, domain);
    s.setString(6, serverName);
    s.setString(7, source);
    s.setString(8, name);
    ResultSet r = database().executeQuery(s);
    Collection x = unmarshallSynchronizationRecords(r);
    r.close();
    r = null;
    s.close();
    s = null;
    return x;
  }

  public static Collection searchSynchronization(String criteria) throws Exception {
    
    criteria = "%" + criteria + "%";
    String sql = "SELECT * FROM " + SYNCHRONIZATION_TABLE + " WHERE ("
            + SYNCHRONIZATION_TABLE_domainName0 + " like ? or "
            + SYNCHRONIZATION_TABLE_serverName0 + " like ? or "
            + SYNCHRONIZATION_TABLE_datasourceName0 + " like ? or "
            + SYNCHRONIZATION_TABLE_name0 + " like ? or "
            + SYNCHRONIZATION_TABLE_domainNameA + " like ? or "
            + SYNCHRONIZATION_TABLE_serverNameA + " like ? or "
            + SYNCHRONIZATION_TABLE_datasourceNameA + " like ? or "
            + SYNCHRONIZATION_TABLE_uidA + " like ? or "
            + SYNCHRONIZATION_TABLE_nameA + " like ?) and ("
            + SYNCHRONIZATION_TABLE_name0 + " != " + SYNCHRONIZATION_TABLE_nameA + " and "
            + SYNCHRONIZATION_TABLE_datasourceName0 + " != " + SYNCHRONIZATION_TABLE_datasourceNameA + ") ORDER BY "
            + SYNCHRONIZATION_TABLE_domainName0 + ", "
            + SYNCHRONIZATION_TABLE_serverName0 + ", "
            + SYNCHRONIZATION_TABLE_datasourceName0 + ", "
            + SYNCHRONIZATION_TABLE_uid0 + ", "
            + SYNCHRONIZATION_TABLE_domainNameA + ", "
            + SYNCHRONIZATION_TABLE_serverNameA + ", "
            + SYNCHRONIZATION_TABLE_datasourceNameA + ", "
            + SYNCHRONIZATION_TABLE_uidA;
    
    PreparedStatement s = database().prepareStatement(sql);
    s.setString(1, criteria);
    s.setString(2, criteria);
    s.setString(3, criteria);
    s.setString(4, criteria);
    s.setString(5, criteria);
    s.setString(6, criteria);
    s.setString(7, criteria);
    s.setString(8, criteria);
    s.setString(9, criteria);
    ResultSet r = database().executeQuery(s);
    Collection x = unmarshallSynchronizationRecords(r);
    r.close();
    r = null;
    s.close();
    s = null;
    return x;
  }
  
  /**
   * Find datasource synchronization.
   *
   * @param source the source
   * @param domain the domain
   * @param serverName the server name
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  public static Collection findDatasourceSynchronization(String domain,
      String serverName, String source) throws Exception {
    PreparedStatement s = database().prepareStatement(
        "SELECT * FROM " + SYNCHRONIZATION_TABLE + " WHERE ("
            + SYNCHRONIZATION_TABLE_domainName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_serverName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_datasourceName0 + "=?) OR ("
            + SYNCHRONIZATION_TABLE_domainNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_serverNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_datasourceNameA + "=?) ORDER BY "
            + SYNCHRONIZATION_TABLE_domainName0 + ", "
            + SYNCHRONIZATION_TABLE_serverName0 + ", "
            + SYNCHRONIZATION_TABLE_datasourceName0 + ", "
            + SYNCHRONIZATION_TABLE_uid0 + ", "
            + SYNCHRONIZATION_TABLE_domainNameA + ","
            + SYNCHRONIZATION_TABLE_serverNameA + ", "
            + SYNCHRONIZATION_TABLE_datasourceNameA + ", "
            + SYNCHRONIZATION_TABLE_uidA);
    s.setString(1, domain);
    s.setString(2, serverName);
    s.setString(3, source);
    s.setString(4, domain);
    s.setString(5, serverName);
    s.setString(6, source);
    ResultSet r = database().executeQuery(s);
    Collection x = unmarshallSynchronizationRecords(r);
    r.close();
    r = null;
    s.close();
    s = null;
    return x;
  }

  /**
   * Find server synchronization.
   *
   * @param domain the domain
   * @param serverName the server name
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  public static Collection findServerSynchronization(String domain,
      String serverName) throws Exception {
    PreparedStatement s = database().prepareStatement(
        "SELECT * FROM " + SYNCHRONIZATION_TABLE + " WHERE ("
            + SYNCHRONIZATION_TABLE_domainName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_serverName0 + "=?) OR ("
            + SYNCHRONIZATION_TABLE_domainNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_serverNameA + "=?) ORDER BY "
            + SYNCHRONIZATION_TABLE_domainName0 + ", "
            + SYNCHRONIZATION_TABLE_serverName0 + ", "
            + SYNCHRONIZATION_TABLE_datasourceName0 + ", "
            + SYNCHRONIZATION_TABLE_uid0 + ", "
            + SYNCHRONIZATION_TABLE_domainNameA + ", "
            + SYNCHRONIZATION_TABLE_serverNameA + ", "
            + SYNCHRONIZATION_TABLE_datasourceNameA + ", "
            + SYNCHRONIZATION_TABLE_uidA);
    s.setString(1, domain);
    s.setString(2, serverName);
    s.setString(3, domain);
    s.setString(4, serverName);
    ResultSet r = database().executeQuery(s);
    Collection x = unmarshallSynchronizationRecords(r);
    r.close();
    r = null;
    s.close();
    s = null;
    return x;
  }

  /**
   * Last name synchronization.
   *
   * @param name the name
   *
   * @return the calendar
   *
   * @throws Exception the exception
   */
  public static Calendar lastNameSynchronization(String name) throws Exception {
    String t0 = null, t1 = null;
    Calendar d0 = null, d1 = null;

  	// Rodney McCabe 11/26/2007, add Oracle Support
  	String sql = "SELECT " + SYNCHRONIZATION_TABLE_timeOfCreation0 + " FROM "
              + SYNCHRONIZATION_TABLE + " WHERE " + SYNCHRONIZATION_TABLE_name0
              + "=? OR " + SYNCHRONIZATION_TABLE_nameA + "=? ORDER BY "
              + SYNCHRONIZATION_TABLE_timeOfCreation0 + " DESC ";
  
    sql = database().limitResults(sql, 1);

    PreparedStatement s = database().prepareStatement(sql);
    s.setString(1, name);
    s.setString(2, name);
    ResultSet r = database().executeQuery(s);
    if (r.next()) {
      t0 = r.getString(SYNCHRONIZATION_TABLE_timeOfCreation0);
      try {
        d0 = database().parseDate(t0);
      } catch (InvalidSyntax e) {
        e.printStackTrace();
      }
    }
    r.close();
    r = null;
    s.close();
    s = null;
    
  	sql = "SELECT " + SYNCHRONIZATION_TABLE_timeOfCreationA + " FROM "
              + SYNCHRONIZATION_TABLE + " WHERE " + SYNCHRONIZATION_TABLE_name0
              + "=? OR " + SYNCHRONIZATION_TABLE_nameA + "=? ORDER BY "
              + SYNCHRONIZATION_TABLE_timeOfCreationA + " DESC ";
  
  	if ( database() instanceof zws.database.Oracle ) {
  		sql = sql + "rownum<=1";
  	} else {
  		sql=sql+"limit 1";
  	}

    s = database().prepareStatement(sql);
    s.setString(1, name);
    s.setString(2, name);
    r = database().executeQuery(s);
    if (r.next()) {
      t1 = r.getString(SYNCHRONIZATION_TABLE_timeOfCreationA);
      try {
        d1 = database().parseDate(t1);
      } catch (InvalidSyntax e) {
        e.printStackTrace();
      }
    }
    r.close();
    r = null;
    s.close();
    s = null;
    
    if (null == d0 && null == d1) {
      return null;
    }
    if (null == d0) {
      return d1;
    }
    if (null == d1) {
      return d0;
    }
    if (d0.after(d1)) {
      return d0;
    }
    return d1;
  }

  /**
   * Find matches.
   *
   * @param source the source
   * @param name the name
   * @param domain the domain
   * @param serverName the server name
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  public static Collection findMatches(String domain, String serverName,
      String source, String name) throws Exception {
    Collection matches = new Vector();
    Collection c = findNameSynchronization(domain, serverName, source, name);
    if (null == c || c.size() == 0) {
      return matches;
    }
    SynchronizationRecord r;
    Iterator i = c.iterator();
    while (i.hasNext()) {
      r = (SynchronizationRecord) i.next();
      matches.add(r.getOrigin0());
      matches.add(r.getOriginA());
    }
    return matches;
  }

  /**
   * Last synchronization.
   *
   * @param source the source
   * @param name the name
   * @param domain the domain
   * @param serverName the server name
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
  public static Origin lastSynchronization(String domain, String serverName,
      String source, String name) throws Exception {
    Collection origin0s = findOrigin0s(domain, serverName, source, name);
    Collection originAs = findOriginAs(domain, serverName, source, name);
    Origin origin, lastOrigin = null;
    Iterator i = origin0s.iterator();
    while (i.hasNext()) {
      origin = (Origin) i.next();
      if (null == lastOrigin || origin.isLater(lastOrigin)) {
        lastOrigin = origin;
      }
    }
    i = originAs.iterator();
    while (i.hasNext()) {
      origin = (Origin) i.next();
      if (null == lastOrigin || origin.isLater(lastOrigin)) {
        lastOrigin = origin;
      }
    }
    return lastOrigin;
  }

  /**
   * Last datasource synchronization.
   *
   * @param source the source
   * @param domain the domain
   * @param serverName the server name
   *
   * @return the calendar
   *
   * @throws Exception the exception
   */
  public static Calendar lastDatasourceSynchronization(String domain,
      String serverName, String source) throws Exception {
    String t0 = null, t1 = null;
    Calendar d0 = null, d1 = null;

	String sql = "SELECT " + SYNCHRONIZATION_TABLE_timeOfCreation0 + " FROM "
            + SYNCHRONIZATION_TABLE + " WHERE ("
            + SYNCHRONIZATION_TABLE_domainName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_serverName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_datasourceName0 + "=?) OR ("
            + SYNCHRONIZATION_TABLE_domainNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_serverNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_datasourceNameA + "=?)  ORDER BY "
            + SYNCHRONIZATION_TABLE_timeOfCreation0 + " DESC ";
    sql = database().limitResults(sql, 1);

    PreparedStatement s = database().prepareStatement(sql);
    s.setString(1, domain);
    s.setString(2, serverName);
    s.setString(3, source);
    s.setString(4, domain);
    s.setString(5, serverName);
    s.setString(6, source);
    ResultSet r = database().executeQuery(s);
    if (r.next()) {
      t0 = r.getString(SYNCHRONIZATION_TABLE_timeOfCreation0);
      try {
        d0 = database().parseDate(t0);
      } catch (InvalidSyntax e) {
        e.printStackTrace();
      }
    }
    r.close();
    r = null;
    s.close();
    s = null;
  	sql = "SELECT " + SYNCHRONIZATION_TABLE_timeOfCreationA + " FROM "
              + SYNCHRONIZATION_TABLE + " WHERE ("
              + SYNCHRONIZATION_TABLE_domainName0 + "=? AND "
              + SYNCHRONIZATION_TABLE_serverName0 + "=? AND "
              + SYNCHRONIZATION_TABLE_datasourceName0 + "=?) OR ("
              + SYNCHRONIZATION_TABLE_domainNameA + "=? AND "
              + SYNCHRONIZATION_TABLE_serverNameA + "=? AND "
              + SYNCHRONIZATION_TABLE_datasourceNameA + "=?)  ORDER BY "
              + SYNCHRONIZATION_TABLE_timeOfCreationA + " DESC ";

    sql = database().limitResults(sql, 1);

    s = database().prepareStatement(sql);
    s.setString(1, domain);
    s.setString(2, serverName);
    s.setString(3, source);
    s.setString(4, domain);
    s.setString(5, serverName);
    s.setString(6, source);
    r = database().executeQuery(s);
    if (r.next()) {
      t1 = r.getString(SYNCHRONIZATION_TABLE_timeOfCreationA);
      try {
        d1 = database().parseDate(t1);
      } catch (InvalidSyntax e) {
        e.printStackTrace();
      }
    }
    r.close();
    r = null;
    s.close();
    s = null;
    
    if (null == d0 && null == d1) {
      return null;
    }
    if (null == d0) {
      return d1;
    }
    if (null == d1) {
      return d0;
    }
    if (d0.after(d1)) {
      return d0;
    }
    return d1;
  }

  /**
   * Last server synchronization.
   *
   * @param domain the domain
   * @param serverName the server name
   *
   * @return the calendar
   *
   * @throws Exception the exception
   */
  public static Calendar lastServerSynchronization(String domain,
      String serverName) throws Exception {
    String t0 = null, t1 = null;
    Calendar d0 = null, d1 = null;

  	String sql = "SELECT " + SYNCHRONIZATION_TABLE_timeOfCreation0 + " FROM "
              + SYNCHRONIZATION_TABLE + " WHERE ("
              + SYNCHRONIZATION_TABLE_domainName0 + "=? AND "
              + SYNCHRONIZATION_TABLE_serverName0 + "=?) OR ("
              + SYNCHRONIZATION_TABLE_domainNameA + "=? AND "
              + SYNCHRONIZATION_TABLE_serverNameA + "=?) ORDER BY "
              + SYNCHRONIZATION_TABLE_timeOfCreation0 + " DESC ";
    sql = database().limitResults(sql, 1);

    PreparedStatement s = database().prepareStatement(sql);
    s.setString(1, domain);
    s.setString(2, serverName);
    s.setString(3, domain);
    s.setString(4, serverName);
    ResultSet r = database().executeQuery(s);
    if (r.next()) {
      t0 = r.getString(SYNCHRONIZATION_TABLE_timeOfCreation0);
      try {
        d0 = database().parseDate(t0);
      } catch (InvalidSyntax e) {
        e.printStackTrace();
      }
    }
    r.close();
    r = null;
    s.close();
    s = null;
    
  	sql = "SELECT " + SYNCHRONIZATION_TABLE_timeOfCreationA + " FROM "
              + SYNCHRONIZATION_TABLE + " WHERE ("
              + SYNCHRONIZATION_TABLE_domainName0 + "=? AND "
              + SYNCHRONIZATION_TABLE_serverName0 + "=?) OR ("
              + SYNCHRONIZATION_TABLE_domainNameA + "=? OR "
              + SYNCHRONIZATION_TABLE_serverNameA + "=?) ORDER BY "
              + SYNCHRONIZATION_TABLE_timeOfCreationA + " DESC ";

    sql = database().limitResults(sql, 1);

    s = database().prepareStatement(sql);
    s.setString(1, domain);
    s.setString(2, serverName);
    s.setString(3, domain);
    s.setString(4, serverName);
    r = database().executeQuery(s);
    if (r.next()) {
      t1 = r.getString(SYNCHRONIZATION_TABLE_timeOfCreationA);
      try {
        d1 = database().parseDate(t1);
      } catch (InvalidSyntax e) {
        e.printStackTrace();
      }
    }
    r.close();
    r = null;
    s.close();
    s = null;
    
    if (null == d0 && null == d1) {
      return null;
    }
    if (null == d0) {
      return d1;
    }
    if (null == d1) {
      return d0;
    }
    if (d0.after(d1)) {
      return d0;
    }
    return d1;
  }

  /**
   * Find origin0s.
   *
   * @param source the source
   * @param name the name
   * @param domainName the domain name
   * @param serverName the server name
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  private static Collection findOrigin0s(String domainName, String serverName,
      String source, String name) throws Exception {
    String domainNameX, serverNameX, datasourceTypeX, datasourceNameX, uidX, nameX, locationX, stateX;
    long timeOfCreationX;
    String m = ",";
    String select = SYNCHRONIZATION_TABLE_domainName0 + m
        + SYNCHRONIZATION_TABLE_serverName0 + m
        + SYNCHRONIZATION_TABLE_datasourceType0 + m
        + SYNCHRONIZATION_TABLE_datasourceName0 + m
        + SYNCHRONIZATION_TABLE_timeOfCreation0 + m
        + SYNCHRONIZATION_TABLE_uid0 + m + SYNCHRONIZATION_TABLE_name0 + m
        + SYNCHRONIZATION_TABLE_location0 + m + SYNCHRONIZATION_TABLE_state0;

    PreparedStatement s = database().prepareStatement(
        "SELECT " + select + " FROM  " + SYNCHRONIZATION_TABLE + "  WHERE ("
            + SYNCHRONIZATION_TABLE_domainName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_serverName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_datasourceName0 + "=? AND "
            + SYNCHRONIZATION_TABLE_name0 + "=?)");
    s.setString(1, domainName);
    s.setString(2, serverName);
    s.setString(3, source);
    s.setString(4, name);
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
      originX = (OriginBase) OriginMaker.materialize(domainNameX, serverNameX,
          datasourceTypeX, datasourceNameX, timeOfCreationX, uidX, locationX,
          stateX);
      originX.setName(nameX);
      origin0s.add(originX);
    }
    r.close();
    r = null;
    s.close();
    s = null;
    
    return origin0s;
  }

  /**
   * Find origin as.
   *
   * @param source the source
   * @param name the name
   * @param domainName the domain name
   * @param serverName the server name
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  private static Collection findOriginAs(String domainName, String serverName,
      String source, String name) throws Exception {
    String domainNameX, serverNameX, datasourceTypeX, datasourceNameX, uidX, nameX, locationX, stateX;
    long timeOfCreationX;
    String m = ",";

    String select = SYNCHRONIZATION_TABLE_domainNameA + m
        + SYNCHRONIZATION_TABLE_serverNameA + m
        + SYNCHRONIZATION_TABLE_datasourceTypeA + m
        + SYNCHRONIZATION_TABLE_datasourceNameA + m
        + SYNCHRONIZATION_TABLE_timeOfCreationA + m
        + SYNCHRONIZATION_TABLE_uidA + m + SYNCHRONIZATION_TABLE_nameA + m
        + SYNCHRONIZATION_TABLE_locationA + m + SYNCHRONIZATION_TABLE_stateA;
    PreparedStatement s = database().prepareStatement(
        "SELECT " + select + " FROM  " + SYNCHRONIZATION_TABLE + "  WHERE ("
            + SYNCHRONIZATION_TABLE_domainNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_serverNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_datasourceNameA + "=? AND "
            + SYNCHRONIZATION_TABLE_nameA + "=?)");
    s.setString(1, domainName);
    s.setString(2, serverName);
    s.setString(3, source);
    s.setString(4, name);
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
      originX = (OriginBase) OriginMaker.materialize(domainNameX, serverNameX,
          datasourceTypeX, datasourceNameX, timeOfCreationX, uidX, locationX,
          stateX);
      originX.setName(nameX);
      originAs.add(originX);
    }
    r.close();
    r = null;
    s.close();
    s = null;
    
    return originAs;
  }

  /**
   * Unmarshall synchronization record.
   *
   * @param r the r
   *
   * @return the synchronization record
   *
   * @throws SQLException the SQL exception
   * @throws CanNotMaterialize the can not materialize
   * @throws CircularDependency the circular dependency
   */
  private static SynchronizationRecord unmarshallSynchronizationRecord(
      ResultSet r) throws SQLException, CircularDependency, CanNotMaterialize {
    String domainName0, serverName0, datasourceType0, datasourceName0, uid0, location0, state0;
    String domainNameA, serverNameA, datasourceTypeA, datasourceNameA, uidA, locationA, stateA;

    long timeOfCreation0, timeOfCreationA;

    SynchronizationRecordBase sync = new SynchronizationRecordBase();
    domainName0 = r.getString(SYNCHRONIZATION_TABLE_domainName0);
    serverName0 = r.getString(SYNCHRONIZATION_TABLE_serverName0);
    datasourceType0 = r.getString(SYNCHRONIZATION_TABLE_datasourceType0);
    datasourceName0 = r.getString(SYNCHRONIZATION_TABLE_datasourceName0);
    timeOfCreation0 = r.getLong(SYNCHRONIZATION_TABLE_timeOfCreation0);
    uid0 = r.getString(SYNCHRONIZATION_TABLE_uid0);
    // name0 = r.getString(SYNCHRONIZATION_TABLE_name0);
    location0 = r.getString(SYNCHRONIZATION_TABLE_location0);
    state0 = r.getString(SYNCHRONIZATION_TABLE_state0);

    domainNameA = r.getString(SYNCHRONIZATION_TABLE_domainNameA);
    serverNameA = r.getString(SYNCHRONIZATION_TABLE_serverNameA);
    datasourceTypeA = r.getString(SYNCHRONIZATION_TABLE_datasourceTypeA);
    datasourceNameA = r.getString(SYNCHRONIZATION_TABLE_datasourceNameA);
    timeOfCreationA = r.getLong(SYNCHRONIZATION_TABLE_timeOfCreationA);
    uidA = r.getString(SYNCHRONIZATION_TABLE_uidA);
    // nameA = r.getString(SYNCHRONIZATION_TABLE_nameA);
    locationA = r.getString(SYNCHRONIZATION_TABLE_locationA);
    stateA = r.getString(SYNCHRONIZATION_TABLE_stateA);

    Origin origin0 = OriginMaker.materialize(domainName0, serverName0,
        datasourceType0, datasourceName0, timeOfCreation0, uid0, location0,
        state0);
    Origin originA = OriginMaker.materialize(domainNameA, serverNameA,
        datasourceTypeA, datasourceNameA, timeOfCreationA, uidA, locationA,
        stateA);

    sync.setData(origin0, originA);
    return sync;
  }

  /**
   * Unmarshall synchronization records.
   *
   * @param r the r
   *
   * @return the collection
   *
   * @throws SQLException the SQL exception
   * @throws CanNotMaterialize the can not materialize
   * @throws CircularDependency the circular dependency
   */
  private static Collection unmarshallSynchronizationRecords(ResultSet r)
      throws SQLException, CircularDependency, CanNotMaterialize {
    Collection c = new Vector();
    while (r.next()) {
      c.add(unmarshallSynchronizationRecord(r));
    }
    return c;
  }

  /**
   * Database.
   *
   * @return the database
   *
   * @throws Exception the exception
   */
  private static Database database() throws Exception {
    return DB.source(Names.SYNCHRONIZATION_DATABASE);
  }


  /** The SYNCHRONIZATIO n_ TABLE. */
  private static String SYNCHRONIZATION_TABLE = "synchronizationLog";

  /** The SYNCHRONIZATIO n_ TABL e_domain name0. */
  private static String SYNCHRONIZATION_TABLE_domainName0 = "domainName0";

  /** The SYNCHRONIZATIO n_ TABL e_server name0. */
  private static String SYNCHRONIZATION_TABLE_serverName0 = "serverName0";

  /** The SYNCHRONIZATIO n_ TABL e_datasource type0. */
  private static String SYNCHRONIZATION_TABLE_datasourceType0 = "datasourceType0";

  /** The SYNCHRONIZATIO n_ TABL e_datasource name0. */
  private static String SYNCHRONIZATION_TABLE_datasourceName0 = "datasourceName0";

  /** The SYNCHRONIZATIO n_ TABL e_time of creation0. */
  private static String SYNCHRONIZATION_TABLE_timeOfCreation0 = "stamp0";

  /** The SYNCHRONIZATIO n_ TABL e_uid0. */
  private static String SYNCHRONIZATION_TABLE_uid0 = "uid0";

  /** The SYNCHRONIZATIO n_ TABL e_name0. */
  private static String SYNCHRONIZATION_TABLE_name0 = "name0";

  /** The SYNCHRONIZATIO n_ TABL e_location0. */
  private static String SYNCHRONIZATION_TABLE_location0 = "location0";

  /** The SYNCHRONIZATIO n_ TABL e_state0. */
  private static String SYNCHRONIZATION_TABLE_state0 = "state0";

  /** The SYNCHRONIZATIO n_ TABL e_domain name a. */
  private static String SYNCHRONIZATION_TABLE_domainNameA = "domainNameA";

  /** The SYNCHRONIZATIO n_ TABL e_server name a. */
  private static String SYNCHRONIZATION_TABLE_serverNameA = "serverNameA";

  /** The SYNCHRONIZATIO n_ TABL e_datasource type a. */
  private static String SYNCHRONIZATION_TABLE_datasourceTypeA = "datasourceTypeA";

  /** The SYNCHRONIZATIO n_ TABL e_datasource name a. */
  private static String SYNCHRONIZATION_TABLE_datasourceNameA = "datasourceNameA";

  /** The SYNCHRONIZATIO n_ TABL e_time of creation a. */
  private static String SYNCHRONIZATION_TABLE_timeOfCreationA = "stampA";

  /** The SYNCHRONIZATIO n_ TABL e_uid a. */
  private static String SYNCHRONIZATION_TABLE_uidA = "uidA";

  /** The SYNCHRONIZATIO n_ TABL e_name a. */
  private static String SYNCHRONIZATION_TABLE_nameA = "nameA";

  /** The SYNCHRONIZATIO n_ TABL e_location a. */
  private static String SYNCHRONIZATION_TABLE_locationA = "locationA";

  /** The SYNCHRONIZATIO n_ TABL e_state a. */
  private static String SYNCHRONIZATION_TABLE_stateA = "stateA";

}
