/**
 *
 */
package zws.qx.service;

/*
 * DesignState - Design Compression Technology @author: ptoleti @version: 1.0
 * Created on Jun 13, 2007 10:35:56 AM Copywrite (c) 2007 Zero Wait-State Inc.
 * All rights reserved
 */

import zws.origin.Origin;
import zws.origin.OriginBase;
import zws.origin.OriginMaker;
import zws.qx.Qx;
import zws.qx.QxContext;
import zws.qx.xml.QxXML;
import zws.service.synchronization.qx.SynchronizationClient;
import zws.service.synchronization.qx.SynchronizationParser;
import zws.service.synchronization.qx.SynchronizationSvc;
import zws.synchronization.SynchronizationRecord;
import zws.synchronization.SynchronizationRecordBase;
import zws.util.StringUtil;
//impoer zws.util.{}//Logwriter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.io.StringReader;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * The Class SynchronizationQxService.
 *
 * @author ptoleti
 */
public class SynchronizationQxService implements Qx {

  /**
   * executeQx.
   *
   * @param dataInstruction the data instruction
   * @param ctx the ctx
   *
   * @return the qx XML
   */
  public QxXML executeQx(QxContext ctx, QxXML dataInstruction) {
    QxXML result = null;
    try {
      SynchronizationParser insParser = new SynchronizationParser();
      XMLReader rdr = XMLReaderFactory
          .createXMLReader("org.apache.xerces.parsers.SAXParser");
      rdr.setFeature("http://xml.org/sax/features/validation", false);
      rdr.setContentHandler(insParser);
      rdr.parse(new InputSource(new StringReader(dataInstruction.toString())));
      HashMap dataMap = insParser.getDataMap();
      {} //System.out.println("-------Sync data-------");
      {} //System.out.println(dataMap.toString());
      String methodName             = (String) dataMap.get(SynchronizationClient.METHOD);
      String uid                    = (String) dataMap.get(SynchronizationClient.UID);
      String name                   = (String) dataMap.get(SynchronizationClient.NAME);
      String target                 = (String) dataMap.get(SynchronizationClient.TARGET);
      String origin                 = (String) dataMap.get(SynchronizationClient.ORIGIN);
      String originA                = (String) dataMap.get(SynchronizationClient.ORIGIN_A);
      String originB                = (String) dataMap.get(SynchronizationClient.ORIGIN_B);
      Collection originList         = (Collection) dataMap.get(SynchronizationClient.ORIGIN_LIST);
      String newName                = (String) dataMap.get(SynchronizationClient.NEW_NAME);
      String serverName             = (String) dataMap.get(SynchronizationClient.SERVER_NAME);
      String syncRecord             = (String) dataMap.get(SynchronizationClient.SYNC_RECORD);
      String domainNameA            = (String) dataMap.get(SynchronizationClient.DOMAIN_NAME_A);
      String domainName0            = (String) dataMap.get(SynchronizationClient.DOMAIN_NAME_0);
      Collection oldOriginAs        = (Collection) dataMap.get(SynchronizationClient.OLD_ORIGIN_AS);
      Collection oldOrigin0s        = (Collection) dataMap.get(SynchronizationClient.OLD_ORIGIN_0S);
      String datasourceNameA        = (String) dataMap.get(SynchronizationClient.DATASOURCE_NAME_A);
      String datasourceName0        = (String) dataMap.get(SynchronizationClient.DATASOURCE_NAME_0);
      Collection targetDatasources  = (Collection) dataMap.get(SynchronizationClient.TARGET_DATASOURCES);
      Calendar calendar = null;
      long time = 0;
      boolean isSync = false;
      Collection syncRecords = null;
      Origin resultOrigin = null;
      if (SynchronizationClient.PURGE_SERVER_RECORDS.equals(methodName)) {
        SynchronizationSvc.purgeServerRecords(domainName0, serverName);
        result = new QxXML("<result value=\" purgeServerRecords completed\"/>");
      } else if (SynchronizationClient.PURGE_BY_NAME.equals(methodName)) {
        SynchronizationSvc.purgeByName(domainName0, serverName, datasourceName0, name);
        result = new QxXML("<result value=\" purgeByName completed\"/>");
      } else if (SynchronizationClient.PURGE_BY_UID.equals(methodName)) {
        SynchronizationSvc.purgeByUID(domainName0, serverName, datasourceName0, uid);
        result = new QxXML("<result value=\" purgeByUID completed\"/>");
      } else if (SynchronizationClient.PURGE_DATA_SOURCE_RECORDS.equals(methodName)) {
        SynchronizationSvc.purgeDatasourceRecords(domainName0, serverName, datasourceName0);
        result = new QxXML(
            "<result value=\" purgeDatasourceRecords completed\"/>");
      } else if (SynchronizationClient.PURGE_MATCHES.equals(methodName)) {
        SynchronizationSvc.purgeMatches(OriginMaker.materialize(origin));
        result = new QxXML("<result value=\" purgeMatches completed\"/>");
      } else if (SynchronizationClient.PURGE_MATCHES.equals(methodName)) {
        SynchronizationSvc.purgeMatches(OriginMaker.materialize(origin));
        result = new QxXML("<result value=\" purgeMatches completed\"/>");
      } else if (SynchronizationClient.RENAME.equals(methodName)) {
        if (null != oldOriginAs && null != oldOrigin0s) {
          SynchronizationSvc.rename(convertToOriginList(oldOrigin0s), convertToOriginList(oldOriginAs), newName);
        } else {
          SynchronizationSvc.rename(domainName0, serverName, datasourceName0, name, newName);
        }
        result = new QxXML("<result value=\"rename completed.\"/>");
      } else if (SynchronizationClient.IS_SYNCHRONIZED.equals(methodName)) {
        if (null != originList) {
          isSync = SynchronizationSvc.isSynchronized(convertToOriginList(originList));
        } else {
          isSync = SynchronizationSvc.isSynchronized(OriginMaker.materialize(originA), OriginMaker.materialize(originB));
        }
        result = new QxXML("<result value=\"" + String.valueOf(isSync) + "\"/>");
      } else if (SynchronizationClient.IS_SYNCHRONIZED_TO_DATASOURCE
          .equals(methodName)) {
        if (null != targetDatasources) {
          isSync = SynchronizationSvc.isSynchronizedToDatasource(OriginMaker.materialize(origin), convertToOriginList(targetDatasources));
        } else {
          isSync = SynchronizationSvc.isSynchronizedToDatasource(OriginMaker.materialize(origin), domainName0, serverName, datasourceName0);
        }
        result = new QxXML("<result value=\"" + String.valueOf(isSync) + "\"/>");
      } else if (SynchronizationClient.LAST_DATASOURCE_SYNCHRONIZATION.equals(methodName)) {
        calendar = SynchronizationSvc.lastDatasourceSynchronization(domainName0, serverName, datasourceName0);
        time = calendar.getTimeInMillis();
        result = new QxXML("<result value=\"" + String.valueOf(time) + "\"/>");
      } else if (SynchronizationClient.LAST_SERVER_SYNCHRONIZATION.equals(methodName)) {
        calendar = SynchronizationSvc.lastServerSynchronization(domainName0,
            serverName);
        time = calendar.getTimeInMillis();
        result = new QxXML("<result value=\"" + String.valueOf(time) + "\"/>");
      } else if (SynchronizationClient.LAST_NAME_SYNCHRONIZATION.equals(methodName)) {
        calendar = SynchronizationSvc.lastNameSynchronization(name);
        time = calendar.getTimeInMillis();
        result = new QxXML("<result value=\"" + String.valueOf(time) + "\"/>");
      } else if (SynchronizationClient.FIND_MATCHES.equals(methodName)) {
        Collection matches = SynchronizationSvc.findMatches(domainName0, serverName, datasourceName0, name);
        {} //System.out.println(matches.toString());
        result = new QxXML("<result>" + toXML(matches) + "</result>");
      } else if (SynchronizationClient.REMOVE.equals(methodName)) {
        SynchronizationRecordBase sync = new SynchronizationRecordBase();
        sync.setData(OriginMaker.materialize(originA), OriginMaker.materialize(originB));
        SynchronizationSvc.remove(sync);
        result = new QxXML("<result value=\" remove completed\"/>");
      } else if (SynchronizationClient.FIND_NAME_SYNCHRONIZATION.equals(methodName)) {
        syncRecords = SynchronizationSvc.findNameSynchronization(domainName0, serverName, datasourceName0, name);
        result = new QxXML("<result>" + syncRecordsToXML(syncRecords) + "</result>");
      } else if (SynchronizationClient.FIND_DATA_SOURCE_SYNCHRONIZATION.equals(methodName)) {
        syncRecords = SynchronizationSvc.findDatasourceSynchronization(domainName0, serverName, datasourceName0);
        result = new QxXML("<result>" + syncRecordsToXML(syncRecords) + "</result>");
      } else if (SynchronizationClient.FIND_SERVER_SYNCHRONIZATION .equals(methodName)) {
        syncRecords = SynchronizationSvc.findServerSynchronization(domainName0, serverName);
        result = new QxXML("<result>" + syncRecordsToXML(syncRecords) + "</result>");
      } else if (SynchronizationClient.FIND_SYNCHRONIZATION .equals(methodName)) {
        resultOrigin = SynchronizationSvc.findSynchronization(OriginMaker.materialize(origin), domainName0, serverName, datasourceName0);
        String originStr = "";
        if(null != resultOrigin) { 
          originStr = resultOrigin.toString();
        }
        result = new QxXML("<result value=\"" + originStr + "\"/>");
      } else if (SynchronizationClient.SEARCH_SYNCHRONIZATION .equals(methodName)) {
        syncRecords = SynchronizationSvc.searchSynchronization(name);
        result = new QxXML("<result>" + syncRecordsToXML(syncRecords) + "</result>");       
      } else if (SynchronizationClient.LAST_SYNCHRONIZATION .equals(methodName)) {
          resultOrigin = SynchronizationSvc.lastSynchronization(domainName0, serverName, datasourceName0, name);
          String originStr = "";
          if(null != resultOrigin) { 
            originStr = resultOrigin.toString();
          }
          result = new QxXML("<result value=\"" + originStr + "\"/>");          
      } else if (SynchronizationClient.FIND_ALL_SYNCHRONIZATION_ORIGINS .equals(methodName)) {
        if (null != origin) {
          syncRecords = SynchronizationSvc.findAllSynchronizationOrigins(OriginMaker.materialize(origin));
          {} //System.out.println("------------------syncOriginRecords-------------------------");
          {} //System.out.println(syncRecords);
          result = new QxXML("<result>" + toXML(syncRecords) + "</result>");
        } else {
          syncRecords = SynchronizationSvc.findAllSynchronizationRecords(name);
          {} //System.out.println("------------------syncRecords-------------------------");
          {} //System.out.println(syncRecords);
          result = new QxXML("<result>" + syncRecordsToXML(syncRecords) + "</result>");
        }
      } else if (SynchronizationClient.RECORD.equals(methodName)) {
        if (null != originA && null != originB) {
          SynchronizationRecord sRecord = SynchronizationSvc.record(OriginMaker.materialize(originA), OriginMaker.materialize(originB));
          {} //System.out.println(sRecord.toString());
          syncRecords = new ArrayList();
          syncRecords.add(sRecord);
          result = new QxXML("<result>" + syncRecordsToXML(syncRecords) + "</result>");
        } else {
          syncRecords = SynchronizationSvc.record(convertToOriginList(originList));
          {} //System.out.println(syncRecords.toString());
          result = new QxXML("<result>" + syncRecordsToXML(syncRecords) + "</result>");
        }
      } else {
        result = new QxXML("Error:: Method " + methodName + " is not identified in SynchronizationQxService");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    {}//Logwriter.printOnConsole(" result in SynchronizationQxService.executeQx"
//        + result);
    return result;
  }

  /**
   * Sync records to XML.
   *
   * @param syncRecords the sync records
   *
   * @return the string
   */
  private String syncRecordsToXML(Collection syncRecords) {
    StringBuffer finalValue = new StringBuffer();
    SynchronizationRecord syncRecord = null;
    Iterator itr = syncRecords.iterator();
    while (itr.hasNext()) {
      syncRecord = (SynchronizationRecord) itr.next();
      finalValue.append("<syncRecord ");
      finalValue.append("origin0=\"" + StringUtil.getXMLValue(syncRecord.getOrigin0().toString()) + "\" ");
      finalValue.append("originA=\"" + StringUtil.getXMLValue(syncRecord.getOriginA().toString()) + "\" ");
      finalValue.append("/>");
    }
    return finalValue.toString();
  }

  /**
   * To XML.
   *
   * @param matches matches
   *
   * @return xml
   */
  private String toXML(Collection matches) {
    StringBuffer finalValue = new StringBuffer();
    OriginBase origin = null;
    Iterator itr = matches.iterator();
    while (itr.hasNext()) {
      origin = (OriginBase) itr.next();
      {} //System.out.println(origin);
      finalValue.append("<origin value=\"").append(StringUtil.getXMLValue(origin.toString())).append("\"/>");
    }
    return finalValue.toString();
  }

  /**
   * Convert to origin list.
   * @param originList the origin list
   * @return the collection
   */
  private Collection convertToOriginList(Collection originList) {
    Collection origins = new ArrayList();
    Iterator itr = originList.iterator();
    try {
      while (itr.hasNext()) {
        origins.add(OriginMaker.materialize((String) itr.next()));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return origins;
  }
}
