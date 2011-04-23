package zws.service.synchronization.qx;

import java.util.Calendar;
import java.util.Collection;
import zws.datasource.Datasource;
import zws.origin.Origin;
import zws.space.DataSpace;
import zws.synchronization.SynchronizationRecord;



/**
 DesignState - Design Compression Technology
 @author: ptoleti
 @version: 1.0
 Created on Jun 13, 2007 10:32:00 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */


/**
 * The Interface SynchronizationClient.
 *
 * @author ptoleti
 */
public interface SynchronizationService {

  /**
   * Record.
   *
   * @param origins the origins
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  Collection record(Collection origins) throws Exception;

  /**
   * Record.
   *
   * @param originB the originB
   * @param originA the originA
   *
   * @return the synchronization record
   *
   * @throws Exception the exception
   */
  SynchronizationRecord record(Origin originA, Origin originB) throws Exception;

  /**
   * Record.
   *
   * @param syncRecord the syncRecord
   *
   * @throws Exception the exception
   */
  void record(SynchronizationRecord syncRecord) throws Exception;

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
  void rename(String domainName, String serverName, String source, String name, String newName) throws Exception;

  /**
   * Rename.
   *
   * @param oldOrigin0s the old origin0s
   * @param newName the new name
   * @param oldOriginAs the old origin as
   *
   * @throws Exception the exception
   */
  void rename(Collection oldOrigin0s, Collection oldOriginAs, String newName) throws Exception;

  /**
   * Remove.
   *
   * @param syncRecord the syncRecord
   *
   * @throws Exception the exception
   */
  void remove(SynchronizationRecord syncRecord) throws Exception;

  /**
   * Purge matches.
   *
   * @param origin the origin
   *
   * @throws Exception the exception
   */
  void purgeMatches(Origin origin) throws Exception;

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
  void purgeByUID(String domainName, String serverName, String source, String uid) throws Exception;

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
  void purgeByName(String domainName, String serverName, String source, String name) throws Exception;

  /**
   * Purge datasource records.
   *
   * @param source the source
   * @param domainName the domain name
   * @param serverName the server name
   *
   * @throws Exception the exception
   */
  void purgeDatasourceRecords(String domainName, String serverName, String source) throws Exception;

  /**
   * Purge server records.
   *
   * @param domainName the domain name
   * @param serverName the server name
   *
   * @throws Exception the exception
   */
  void purgeServerRecords(String domainName, String serverName) throws Exception;

  /**
   * Checks if is synchronized.
   *
   * @param origins the origins
   *
   * @return true, if is synchronized
   *
   * @throws Exception the exception
   */
  boolean isSynchronized(Collection origins) throws Exception;

  /**
   * Checks if is synchronized.
   *
   * @param originB the originB
   * @param originA the originA
   *
   * @return true, if is synchronized
   *
   * @throws Exception the exception
   */
  boolean isSynchronized(Origin originA, Origin originB) throws Exception;

  /**
   * Checks if is synchronized to datasource.
   *
   * @param origin the origin
   * @param targetDatasources the target datasources
   *
   * @return true, if is synchronized to datasource
   *
   * @throws Exception the exception
   */
  boolean isSynchronizedToDatasource(Origin origin, Collection targetDatasources) throws Exception;

  /**
   * Checks if is indirectly synchronized to datasource.
   *
   * @param target the target
   * @param origin the origin
   *
   * @return true, if is indirectly synchronized to datasource
   *
   * @throws Exception the exception
   */
  boolean isIndirectlySynchronizedToDatasource(Origin origin, Datasource target) throws Exception;

  /**
   * Checks if is synchronized to datasource.
   *
   * @param target the target
   * @param origin the origin
   *
   * @return true, if is synchronized to datasource
   *
   * @throws Exception the exception
   */
  boolean isSynchronizedToDatasource(Origin origin, DataSpace target) throws Exception;

  /**
   * Checks if is synchronized to datasource.
   *
   * @param target the target
   * @param origin the origin
   *
   * @return true, if is synchronized to datasource
   *
   * @throws Exception the exception
   */
  boolean isSynchronizedToDatasource(Origin origin, Datasource target) throws Exception;

  /**
   * Checks if is synchronized to datasource.
   *
   * @param datasourceName the datasource name
   * @param origin the origin
   * @param domainName the domain name
   * @param serverName the server name
   *
   * @return true, if is synchronized to datasource
   *
   * @throws Exception the exception
   */
  boolean isSynchronizedToDatasource(Origin origin, String domainName, String serverName, String datasourceName) throws Exception;

  /**
   * Find synchronization.
   *
   * @param datasourceName the datasource name
   * @param origin the origin
   * @param domainName the domain name
   * @param serverName the server name
   *
   * @return the origin
   *
   * @throws Exception the exception
   */
  Origin findSynchronization(Origin origin, String domainName, String serverName, String datasourceName) throws Exception;

  /**
   * Find all synchronization records.
   *
   * @param origin the origin
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  Collection findAllSynchronizationOrigins(Origin origin) throws Exception;

  /**
   * Find all synchronization records.
   *
   * @param name the name
   *
   * @return the collection
   *
   * @throws Exception the exception
   */
  Collection findAllSynchronizationRecords(String name) throws Exception;

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
  Collection findNameSynchronization(String domain, String serverName, String source, String name) throws Exception;

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
  Collection findDatasourceSynchronization(String domain, String serverName, String source) throws Exception;

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
  Collection findServerSynchronization(String domain, String serverName) throws Exception;

  /**
   * Last name synchronization.
   *
   * @param name the name
   *
   * @return the calendar
   *
   * @throws Exception the exception
   */
  Calendar lastNameSynchronization(String name) throws Exception;

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
  Collection findMatches(String domain, String serverName, String source, String name) throws Exception;

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
  Origin lastSynchronization(String domain, String serverName, String source, String name) throws Exception;

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
  Calendar lastDatasourceSynchronization(String domain, String serverName, String source) throws Exception;

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
  Calendar lastServerSynchronization(String domain, String serverName) throws Exception;
}
