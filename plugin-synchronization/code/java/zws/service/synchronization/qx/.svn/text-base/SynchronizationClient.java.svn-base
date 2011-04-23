package zws.service.synchronization.qx;

import java.io.StringReader;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import zws.datasource.Datasource;
import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.qx.service.QxServiceFinder;
import zws.qx.xml.QxXML;
import zws.space.DataSpace;
import zws.synchronization.SynchronizationRecord;
//impoer zws.util.{}//Logwriter;
import zws.util.StringUtil;

/**
 * RecorderService interface.
 * @author Sulakshan Shetty
 */
/**
 * @author ptoleti
 *
 */
/**
 * @author ptoleti
 *
 */
public class SynchronizationClient implements SynchronizationService {

  /**
   * @param strHostName HostName
   */
  /*private SynchronizationClient(String strHostName) {
      hostName = strHostName;
  }*/

  private SynchronizationClient() {}

  /**
   * Prepare context.
   *
   * @param ctx the ctx
   */
  /*private void prepareContext(QxContext ctx) {
        ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.service");
        ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "SynchronizationQxService");
        ctx.set(QxContext.SOAP_HOSTNAME, hostName);
        ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
        ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
        ctx.set(QxContext.SOAP_SERVICE_OPERATION, "runQx");
  }
*/
  /**
   * @param argMap argMap
   * @return QxXML
   * @throws Exception exception
   */
  private QxXML prepareInstruction(HashMap argMap) throws Exception {
    String key = null;
    String value = null;
    StringBuffer instBuffer = new StringBuffer();
    try {
      // <methodData name=\"test\"
    instBuffer.append("<methodData name=\"").append(argMap.get("method")).append("\" ");
    argMap.remove("method");
    Iterator itr = argMap.keySet().iterator();
    while (itr.hasNext()) {
      key = (String) itr.next();
      value = (String) argMap.get(key);
      //instBuffer.append(key).append("=\"").append(value).append("\" ");
      instBuffer.append(key);
      instBuffer.append("=");
      instBuffer.append("\"").append(StringUtil.getXMLValue(value)).append("\" ");
    }
    instBuffer.append("/>");
    {}//Logwriter.printOnConsole("instruction to be sent" + instBuffer.toString());
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
    return new QxXML(instBuffer.toString());
  }


  /**
   * @param argMap argMap
   * @return QxXML
   * @throws Exception exception
   */
  private QxXML execute(HashMap argMap) throws Exception {
    QxServiceFinder finder = QxServiceFinder.materializeFinder("synchronization");
    QxContext ctx = finder.prepareQxWebClientContext("zws.qx.service","SynchronizationQxService");
    QxWebClient webClient = (QxWebClient) finder.materializeClient();
    return webClient.executeQx(ctx, prepareInstruction(argMap));
  }

  /**
   * @param name name
   * @param originList list
   * @return string
   */
  private String prepareDataElement(String name, Collection originList) {
  StringBuffer element = new StringBuffer();
  Iterator itr = originList.iterator();
  element.append("<data elementName=\"").append(name).append("\">");
  while (itr.hasNext()) {
    element.append("<origin value=\"").append(itr.next()).append("\"/>");
  }
  element.append("</data>");
    return element.toString();
  }

  /**
   * @param result result
   * @return collection
   * @throws Exception exception
   */
  private Collection parseSyncRecords(QxXML result) throws Exception {
    SynchronizationParser insParser = new SynchronizationParser();
    XMLReader rdr = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
    rdr.setFeature("http://xml.org/sax/features/validation", false);
    rdr.setContentHandler(insParser);
    rdr.parse(new InputSource(new StringReader(result.toString())));
    Collection dataMap = insParser.getCollection();
    {} //System.out.println("-------parseSyncRecords-------");
    {} //System.out.println(dataMap.toString());
    {} //System.out.println("--------------0-----------");
    return dataMap;
  }

  /**
   * @return RecorderClient
   */
 /* public static SynchronizationClient getClient() {
    return new SynchronizationClient("designstate-0");
  }*/

  /**
   * @param hostName HostName
   * @return RecorderClient
   */
  /*public static SynchronizationClient getClient(String hostName) {
    return new SynchronizationClient(hostName);
  }*/

  public static SynchronizationClient getClient() {
    return new SynchronizationClient();
  }

  /** HostName.   */
//  private String hostName = null;
/**
  * Record.
  * @param originList the origins
  * @return the collection
  * @throws Exception the exception
  */
 public Collection record(Collection originList) throws Exception {
   QxXML result = null;
   StringBuffer dataBuffer = new StringBuffer();
   Collection c = null;
   try {
     dataBuffer.append("<syncService>");
     dataBuffer.append("<methodData name =\"").append(RECORD).append("\"/>");
     dataBuffer.append(prepareDataElement("originList", originList));
     dataBuffer.append("</syncService>");
     /*QxContext ctx = new QxContext();
     this.prepareContext(ctx);
     QxWebClient webClient = QxWebClient.materializeClient();
     */
     QxServiceFinder finder = QxServiceFinder.materializeFinder("synchronization");
     QxContext ctx = finder.prepareQxWebClientContext("zws.qx.service","SynchronizationQxService");
     QxWebClient webClient = (QxWebClient) finder.materializeClient();

     result = webClient.executeQx(ctx, new QxXML(dataBuffer.toString()));
     c = parseSyncRecords(result);
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in rename(domainName,serverName,source,name,newName)");
   }
   {}//Logwriter.printOnConsole("rename completed ");
   return c;
 }

 /**
  * Record.
  *
  * @param originA the originA
  * @param originB the originB
  *
  * @return the synchronization record
  *
  * @throws Exception the exception
  */
 public SynchronizationRecord record(Origin originA, Origin originB) throws Exception {
   QxXML result = null;
   HashMap argMap = new HashMap();
   ArrayList list = new ArrayList();
   try {
     argMap.put(METHOD, RECORD);
     argMap.put(ORIGIN_A, originA.toString());
     argMap.put(ORIGIN_B, originB.toString());
     result = execute(argMap);
     {} //System.out.println("record " + result);
     list.addAll(parseSyncRecords(result));
     {}//Logwriter.printOnConsole("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& ");
     {}//Logwriter.printOnConsole((SynchronizationRecord) list.get(0));
     {}//Logwriter.printOnConsole("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& ");

   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in isSynchronized(originA, originB)");
   }
   {}//Logwriter.printOnConsole("record completed ");

   return (SynchronizationRecord) list.get(0);
 }

 /**
  * Record.
  *
  * @param syncRecord the syncRecord
  *
  * @throws Exception the exception
  */
 public void record(SynchronizationRecord syncRecord) throws Exception {
   try {
     Origin originA = syncRecord.getOriginA();
     Origin originB = syncRecord.getOrigin0();
     record(originB, originA);
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("record(syncRecord)");
   }
 }

 /**
  * Rename.
  *
  * @param datasourceName the datasource
  * @param name the name
  * @param domainName the domain name
  * @param newName the new name
  * @param serverName the server name
  *
  * @throws Exception the exception
  */
 public void rename(String domainName, String serverName, String datasourceName, String name, String newName) throws Exception {
   QxXML result = null;
   HashMap argMap = new HashMap();
   try {
     argMap.put(METHOD, RENAME);
     argMap.put(DOMAIN_NAME_0, domainName);
     argMap.put(SERVER_NAME, serverName);
     argMap.put(DATASOURCE_NAME_0, datasourceName);
     argMap.put(NAME, name);
     argMap.put(NEW_NAME, newName);
     result = execute(argMap);
     {} //System.out.println("rename " + result);
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in rename(domainName,serverName,source,name,newName)");
   }
   {}//Logwriter.printOnConsole("rename completed ");
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
 public void rename(Collection oldOrigin0s, Collection oldOriginAs, String newName) throws Exception {
   QxXML result = null;
   StringBuffer dataBuffer = new StringBuffer();
   try {
     dataBuffer.append("<syncService>");
     dataBuffer.append("<methodData name =\"").append(RENAME).append("\"");
     dataBuffer.append("newName=\"").append(newName).append("\"");
     dataBuffer.append("\"/>");
     dataBuffer.append(prepareDataElement("oldOrigin0s", oldOrigin0s));
     dataBuffer.append(prepareDataElement("oldOriginAs", oldOriginAs));
     dataBuffer.append("</syncService>");
/*     QxContext ctx = new QxContext();
     this.prepareContext(ctx);
     QxWebClient webClient = QxWebClient.materializeClient();*/

     QxServiceFinder finder = QxServiceFinder.materializeFinder("synchronization");
     QxContext ctx = finder.prepareQxWebClientContext("zws.qx.service","SynchronizationQxService");
     QxWebClient webClient = (QxWebClient) finder.materializeClient();

     result = webClient.executeQx(ctx, new QxXML(dataBuffer.toString()));
     parseSyncRecords(result);
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in rename(domainName,serverName,source,name,newName)");
   }
   {}//Logwriter.printOnConsole("rename completed ");
 }

 /**
  * Remove.
  *
  * @param syncRecord the syncRecord
  *
  * @throws Exception the exception
  */
 public void remove(SynchronizationRecord syncRecord) throws Exception {
   QxXML result = null;
   HashMap argMap = new HashMap();
   try {
     argMap.put(METHOD, REMOVE);
     argMap.put(ORIGIN_A, syncRecord.getOrigin0().toString());
     argMap.put(ORIGIN_B, syncRecord.getOriginA().toString());
     result = execute(argMap);
     {}//Logwriter.printOnConsole("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& ");
     {} //System.out.println("record " + result);
     {}//Logwriter.printOnConsole("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& ");
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in remove(syncRecord)");
   }
   {}//Logwriter.printOnConsole("remove completed ");
 }

 /**
  * Purge matches.
  *
  * @param origin the origin
  *
  * @throws Exception the exception
  */
 public void purgeMatches(Origin origin) throws Exception {
   QxXML result = null;
   HashMap argMap = new HashMap();
   try {
     argMap.put(METHOD, PURGE_MATCHES);
     argMap.put(origin, origin.toString());
     result = execute(argMap);
     {} //System.out.println("purgeByUID " + result);
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in purgeMatches(origin)");
   }
   {}//Logwriter.printOnConsole("purgeMatches completed ");
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
 public void purgeByUID(String domainName, String serverName, String source, String uid) throws Exception {
   QxXML result = null;
   HashMap argMap = new HashMap();
   try {
     argMap.put(METHOD, PURGE_BY_UID);
     argMap.put(DOMAIN_NAME_0, domainName);
     argMap.put(SERVER_NAME, serverName);
     argMap.put(DATASOURCE_NAME_0, source);
     argMap.put(UID, uid);
     result = execute(argMap);
     {} //System.out.println("purgeByUID " + result);
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in purgeByUID(domainName,serverName,source,uid)");
   }
   {}//Logwriter.printOnConsole("purgeByUID completed ");
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
 public void purgeByName(String domainName, String serverName, String source, String name) throws Exception {
   QxXML result = null;
   HashMap argMap = new HashMap();
   try {
     argMap.put(METHOD, PURGE_BY_NAME);
     argMap.put(DOMAIN_NAME_0, domainName);
     argMap.put(SERVER_NAME, serverName);
     argMap.put(DATASOURCE_NAME_0, source);
     argMap.put(NAME, name);
     result = execute(argMap);
     {} //System.out.println("purgeByName " + result);
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in purgeByName(domainName,serverName,source,name)");
   }
   {}//Logwriter.printOnConsole("purgeByName completed ");
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
 public void purgeDatasourceRecords(String domainName, String serverName, String source) throws Exception {
   QxXML result = null;
   HashMap argMap = new HashMap();
   try {
     argMap.put(METHOD, PURGE_DATA_SOURCE_RECORDS);
     argMap.put(DOMAIN_NAME_0, domainName);
     argMap.put(SERVER_NAME, serverName);
     argMap.put(DATASOURCE_NAME_0, source);
     result = execute(argMap);
     {} //System.out.println("purgeDatasourceRecords " + result);
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in purgeDatasourceRecords(domainName,serverName,source)");
   }
   {}//Logwriter.printOnConsole("purgeDatasourceRecords completed ");
 }

 /**
  * Purge server records.
  *
  * @param domainName the domain name
  * @param serverName the server name
  *
  * @throws Exception the exception
  */
 public void purgeServerRecords(String domainName, String serverName) throws Exception {
   QxXML result = null;
   HashMap argMap = new HashMap();
   try {
     argMap.put(METHOD, PURGE_SERVER_RECORDS);
     argMap.put(DOMAIN_NAME_0, domainName);
     argMap.put(SERVER_NAME, serverName);
     result = execute(argMap);
     {} //System.out.println("purgeServerRecords " + result);
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in purgeServerRecords(domainName, serverName)");
   }
   {}//Logwriter.printOnConsole("purgeServerRecords completed ");
 }

 /**
  * Checks if is synchronized.
  *
  * @param originList the origins
  *
  * @return true, if is synchronized
  *
  * @throws Exception the exception
  */
 public boolean isSynchronized(Collection originList) throws Exception {
   QxXML result = null;
   StringBuffer dataBuffer = new StringBuffer();
   boolean syncValue = false;
   try {
     dataBuffer.append("<syncService>");
     dataBuffer.append("<methodData name =\"").append(IS_SYNCHRONIZED).append("\"/>");
     dataBuffer.append(prepareDataElement("originList", originList));
     dataBuffer.append("</syncService>");
     /*QxContext ctx = new QxContext();
     this.prepareContext(ctx);
     QxWebClient webClient = QxWebClient.materializeClient();
     */

     QxServiceFinder finder = QxServiceFinder.materializeFinder("synchronization");
     QxContext ctx = finder.prepareQxWebClientContext("zws.qx.service","SynchronizationQxService");
     QxWebClient webClient = (QxWebClient) finder.materializeClient();

     result = webClient.executeQx(ctx, new QxXML(dataBuffer.toString()));
     {} //System.out.println("isSynchronized " + result);
     String value = result.toQxProgram().get("value");
     if (null != value) {
       syncValue = new Boolean(value).booleanValue();
     }
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in isSynchronized(origins)");
   }
   {}//Logwriter.printOnConsole("isSynchronized completed ");
   return syncValue;
 }

 /**
  * Checks if is synchronized.
  *
  * @param originA the originAb
  * @param originB the originB
  *
  * @return true, if is synchronized
  *
  * @throws Exception the exception
  */
 public boolean isSynchronized(Origin originA, Origin originB) throws Exception {
   QxXML result = null;
   HashMap argMap = new HashMap();
   boolean syncValue = false;
   try {
     argMap.put(METHOD, IS_SYNCHRONIZED);
     argMap.put(ORIGIN_A, originA.toString());
     argMap.put(ORIGIN_B, originB.toString());
     result = execute(argMap);
     {} //System.out.println("isSynchronized " + result);
     String value = result.toQxProgram().get("value");
     if (null != value) {
       syncValue = new Boolean(value).booleanValue();
     }
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in isSynchronized(riginA, originB)");
   }
   {}//Logwriter.printOnConsole("isSynchronized completed ");
   return syncValue;
 }

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
 public boolean isSynchronizedToDatasource(Origin origin, Collection targetDatasources) throws Exception {
   return false;
 }

 /**
  * Checks if is indirectly synchronized to datasource.
  * @param target the target
  * @param origin the origin
  * @return true, if is indirectly synchronized to datasource
  * @throws Exception the exception
  */

 public boolean isIndirectlySynchronizedToDatasource(Origin origin, Datasource target) throws Exception {
   return false;
 }

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
 public boolean isSynchronizedToDatasource(Origin origin, DataSpace target) throws Exception {
   return false;
 }

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
 public boolean isSynchronizedToDatasource(Origin origin, Datasource target) throws Exception {
   return false;
 }

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
 public boolean isSynchronizedToDatasource(Origin origin, String domainName, String serverName, String datasourceName) throws Exception {
   QxXML result = null;
   HashMap argMap = new HashMap();
   boolean syncValue = false;
   try {
     argMap.put(METHOD, IS_SYNCHRONIZED_TO_DATASOURCE);
     argMap.put(ORIGIN, origin.toString());
     argMap.put(DOMAIN_NAME_0, domainName);
     argMap.put(SERVER_NAME, serverName);
     argMap.put(DATASOURCE_NAME_0, datasourceName);
     result = execute(argMap);
     {} //System.out.println("isSynchronizedToDatasource " + result);
     String value = result.toQxProgram().get("value");
     if (null != value) {
       syncValue = new Boolean(value).booleanValue();
     }
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in isSynchronizedToDatasource(origin, domainName,serverName,datasourceName)");
   }
   {}//Logwriter.printOnConsole("isSynchronizedToDatasource completed ");
   return syncValue;
 }

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
 public Origin findSynchronization(Origin origin, String domainName, String serverName, String datasourceName) throws Exception {
   QxXML result = null;
   HashMap argMap = new HashMap();
   Origin syncOrigin = null;
   try {
     argMap.put(METHOD, FIND_SYNCHRONIZATION);
     argMap.put(ORIGIN, origin.toString());
     argMap.put(DOMAIN_NAME_0, domainName);
     argMap.put(SERVER_NAME, serverName);
     argMap.put(DATASOURCE_NAME_0, datasourceName);
     result = execute(argMap);
     {} //System.out.println("isSynchronizedToDatasource " + result);
     String value = result.toQxProgram().get("value");
     if (null != value && value.length() >0) {
       syncOrigin = OriginMaker.materialize(value);
     }
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in findSynchronization(origin, domainName,serverName,datasourceName)");
   }
   {}//Logwriter.printOnConsole("findSynchronization completed ");
   return syncOrigin;
 }

 /**
  * Find all synchronization records.
  *
  * @param origin the origin
  *
  * @return the collection
  *
  * @throws Exception the exception
  */
 public Collection findAllSynchronizationOrigins(Origin origin) throws Exception {
   if(null == origin) return null;
   QxXML result = null;
   HashMap argMap = new HashMap();
   Collection list = null;
   try {
     argMap.put(METHOD, FIND_ALL_SYNCHRONIZATION_ORIGINS);
     argMap.put(ORIGIN, origin.toString());
     result = execute(argMap);
     {} //System.out.println("record " + result);
     list = parseSyncRecords(result);
     {}//Logwriter.printOnConsole("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& ");
     {}//Logwriter.printOnConsole(list);
     {}//Logwriter.printOnConsole("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& ");
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in ifindAllSynchronizationRecords(origin)");
   }
   {}//Logwriter.printOnConsole("findAllSynchronizationRecords completed ");
   return list;
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
 public Collection findAllSynchronizationRecords(String name) throws Exception {
   QxXML result = null;
   HashMap argMap = new HashMap();
   Collection list = null;
   try {
     argMap.put(METHOD, FIND_ALL_SYNCHRONIZATION_ORIGINS);
     argMap.put(NAME, name);
     result = execute(argMap);
     {} //System.out.println("record " + result);
     list = parseSyncRecords(result);
     {}//Logwriter.printOnConsole("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& ");
     {}//Logwriter.printOnConsole(list);
     {}//Logwriter.printOnConsole("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& ");
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in ifindAllSynchronizationRecords(name)");
   }
   {}//Logwriter.printOnConsole("findAllSynchronizationRecords completed ");
   return list;
 }

 public Collection searchSynchronizationRecords(String criteria) throws Exception {
   QxXML result = null;
   HashMap argMap = new HashMap();
   Collection list = null;
   try {
     argMap.put(METHOD, SEARCH_SYNCHRONIZATION);
     argMap.put(NAME, criteria);
     result = execute(argMap);
     list = parseSyncRecords(result);
    } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in searchSynchronizationRecords(criteria)");
   }
   return list;
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
 public Collection findNameSynchronization(String domain, String serverName, String source, String name) throws Exception {
   QxXML result = null;
   HashMap argMap = new HashMap();
   Collection list = null;
   try {
     argMap.put(METHOD, FIND_NAME_SYNCHRONIZATION);
     argMap.put(DOMAIN_NAME_0, domain);
     argMap.put(SERVER_NAME, serverName);
     argMap.put(DATASOURCE_NAME_0, source);
     argMap.put(NAME, name);
     result = execute(argMap);
     {} //System.out.println("record " + result);
     list = parseSyncRecords(result);
     {}//Logwriter.printOnConsole("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& ");
     {}//Logwriter.printOnConsole(list);
     {}//Logwriter.printOnConsole("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& ");
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in findNameSynchronization(domain, serverName, source, name)");
   }
   {}//Logwriter.printOnConsole("findSynchronization completed ");
   return list;

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
 public Collection findDatasourceSynchronization(String domain, String serverName, String source) throws Exception {
   QxXML result = null;
   HashMap argMap = new HashMap();
   Collection list = null;
   try {
     argMap.put(METHOD, FIND_DATA_SOURCE_SYNCHRONIZATION);
     argMap.put(DOMAIN_NAME_0, domain);
     argMap.put(SERVER_NAME, serverName);
     argMap.put(DATASOURCE_NAME_0, source);
     result = execute(argMap);
     {} //System.out.println("record " + result);
     list = parseSyncRecords(result);
     {}//Logwriter.printOnConsole("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& ");
     {}//Logwriter.printOnConsole(list);
     {}//Logwriter.printOnConsole("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& ");
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in findDatasourceSynchronization(domain, serverName, source)");
   }
   {}//Logwriter.printOnConsole("findSynchronization completed ");
   return list;

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
 public Collection findServerSynchronization(String domain, String serverName) throws Exception {
   QxXML result = null;
   HashMap argMap = new HashMap();
   Collection list = new ArrayList();
   try {
     argMap.put(METHOD, FIND_SERVER_SYNCHRONIZATION);
     argMap.put(DOMAIN_NAME_0, domain);
     argMap.put(SERVER_NAME, serverName);
     result = execute(argMap);
     {} //System.out.println("record " + result);
     list = parseSyncRecords(result);
     {}//Logwriter.printOnConsole("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& ");
     {}//Logwriter.printOnConsole(list);
     {}//Logwriter.printOnConsole("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& ");
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in findServerSynchronization(domain, serverName)");
   }
   {}//Logwriter.printOnConsole("findSynchronization completed ");
   return list;

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
 public Calendar lastNameSynchronization(String name) throws Exception {
   QxXML result = null;
   HashMap argMap = new HashMap();
   Calendar calendar = new GregorianCalendar();
   try {
     argMap.put(METHOD, LAST_NAME_SYNCHRONIZATION);
     argMap.put(NAME, name);
     result = execute(argMap);
     {} //System.out.println("lastNameSynchronization " + result);
     String value = result.toQxProgram().get("value");
     if (null != value) {
       calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value));
     }
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in lastNameSynchronization(name)");
   }
   {}//Logwriter.printOnConsole("lastNameSynchronization completed ");
   return calendar;
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
 public Collection findMatches(String domain, String serverName, String source, String name) throws Exception {
   QxXML result = null;
   HashMap argMap = new HashMap();
   Collection matches = null;
   try {
     argMap.put(METHOD, FIND_MATCHES);
     argMap.put(DOMAIN_NAME_0, domain);
     argMap.put(SERVER_NAME, serverName);
     argMap.put(DATASOURCE_NAME_0, source);
     argMap.put(NAME, name);
     result = execute(argMap);
     {} //System.out.println("findMatches " + result);
     matches = parseSyncRecords(result);
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in findMatches(domain,serverName,source,name)");
   }
   {}//Logwriter.printOnConsole("findMatches completed ");
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
 public Origin lastSynchronization(String domain, String serverName, String source, String name) throws Exception {
   QxXML result = null;
   HashMap argMap = new HashMap();
   Origin syncOrigin = null;
   try {
     argMap.put(METHOD, LAST_SYNCHRONIZATION);
     argMap.put(DOMAIN_NAME_0, domain);
     argMap.put(SERVER_NAME, serverName);
     argMap.put(DATASOURCE_NAME_0, source);
     argMap.put(NAME, name);
     result = execute(argMap);
     {} //System.out.println("lastSynchronization " + result);
     String value = result.toQxProgram().get("value");
     if (null != value && !"".equals(value.trim())) {
       syncOrigin = OriginMaker.materialize(value);
     }
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in lastSynchronization(domain,serverName,source,name)");
   }
   {}//Logwriter.printOnConsole("lastSynchronization completed ");
   return syncOrigin;
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
 public Calendar lastDatasourceSynchronization(String domain, String serverName, String source) throws Exception {
   QxXML result = null;
   HashMap argMap = new HashMap();
   Calendar calendar = new GregorianCalendar();
   try {
     argMap.put(METHOD, LAST_DATASOURCE_SYNCHRONIZATION);
     argMap.put(DOMAIN_NAME_0, domain);
     argMap.put(SERVER_NAME, serverName);
     argMap.put(DATASOURCE_NAME_0, source);
     result = execute(argMap);
     {} //System.out.println("lastDatasourceSynchronization " + result);
     String value = result.toQxProgram().get("value");
     if (null != value) {
       calendar.setTimeInMillis(new Long(value).longValue());
     }
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in lastDatasourceSynchronization(domain,serverName,source)");
   }
   {}//Logwriter.printOnConsole("lastDatasourceSynchronization completed ");
   return calendar;
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
 public Calendar lastServerSynchronization(String domain, String serverName) throws Exception {
   QxXML result = null;
   HashMap argMap = new HashMap();
   Calendar calendar = new GregorianCalendar();
   try {
     argMap.put(METHOD, LAST_SERVER_SYNCHRONIZATION);
     argMap.put(DOMAIN_NAME_0, domain);
     argMap.put(SERVER_NAME, serverName);
     result = execute(argMap);
     {} //System.out.println("lastDatasourceSynchronization " + result);
     String value = result.toQxProgram().get("value");
     if (null != value) {
       calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value));
     }
   } catch (Exception e) {
       e.printStackTrace();
       throw new RemoteException("Error in lastServerSynchronization(domain,serverName)");
   }
   {}//Logwriter.printOnConsole("lastServerSynchronization completed ");
   return calendar;
 }

 /** The SOURCE. */
 public static String DATASOURCE_NAME_0 = "datasourceName0";

 /** The DATASOURC e_ NAME. */
 public static String DATASOURCE_NAME_A = "datasourcenameA";

 /** The DOMAIN. */
 public static String DOMAIN_NAME_0 = "domain_name0";

 /** The DOMAI n_ NAME. */
 public static String DOMAIN_NAME_A = "domain_nameA";

 /** The SERVE r_ NAME. */
 public static String SERVER_NAME = "servername";

 /** The NAME. */
 public static String NAME = "ObjName";

 /** The NE w_ NAME. */
 public static String NEW_NAME = "newname";

 /** The OL d_ ORIGI n_0 s. */
 public static String OLD_ORIGIN_0S = "oldorigin0s";

 /** The OL d_ ORIGI n_ AS. */
 public static String OLD_ORIGIN_AS = "oldoriginas";

 /** The ORIGIN. */
 public static String ORIGIN = "origin";

 /** The ORIGI n_ a. */
 public static String ORIGIN_A = "origina";

 /** The ORIGI n_ b. */
 public static String ORIGIN_B = "originb";

 /** The ORIGINS. */
 public static String ORIGIN_LIST = "originList";

 /** The SYN c_ RECORD. */
 public static String SYNC_RECORD = "syncrecord";

 /** The TARGET. */
 public static String TARGET = "target";

 /** The TARGE t_ DATASOURCES. */
 public static String TARGET_DATASOURCES = "targetdatasources";

 /** The UID. */
 public static String UID = "uid";

 /** The method. */
 public static String METHOD = "method";

 /** The FIN d_ AL l_ SYNCHRONIZATIO n_ RECORDS. */
 public static String FIND_ALL_SYNCHRONIZATION_ORIGINS = "findAllSynchronizationRecords";

 /** The FIN d_ DAT a_ SOURC e_ SYNCHRONIZATION. */
 public static String FIND_DATA_SOURCE_SYNCHRONIZATION =  "findDatasourceSynchronization";

 /** The FIN d_ MATCHES. */
 public static String FIND_MATCHES = "findMatches";

 /** The FIN d_ NAM e_ SYNCHRONIZATION. */
 public static String FIND_NAME_SYNCHRONIZATION = "findNameSynchronization";

 /** The FIN d_ SERVE r_ SYNCHRONIZATION. */
 public static String FIND_SERVER_SYNCHRONIZATION = "findServerSynchronization";

 /** The FINDSYNCHRONIZATION. */
 public static String FIND_SYNCHRONIZATION = "findSynchronization";

 /** The SEARCHSYNCHRONIZATION. */
 public static String SEARCH_SYNCHRONIZATION = "searchSynchronization";
 
 /** The I s_ INDIRECTL y_ SYNCHRONIZE d_ T o_ DATASOURCE. */
 public static String IS_INDIRECTLY_SYNCHRONIZED_TO_DATASOURCE = "isIndirectlySynchronizedToDatasource";

 /** The I s_ SYNCHRONIZED. */
 public static String IS_SYNCHRONIZED = "isSynchronized";

 /** The I s_ SYNCHRONIZE d_ T o_ DATASOURCE. */
 public static String IS_SYNCHRONIZED_TO_DATASOURCE = "isSynchronizedToDatasource";

 /** The LAS t_ DATASOURC e_ SYNCHRONIZATION. */
 public static String LAST_DATASOURCE_SYNCHRONIZATION = "lastDatasourceSynchronization";

 /** The LAS t_ NAM e_ SYNCHRONIZATION. */
 public static String LAST_NAME_SYNCHRONIZATION = "lastNameSynchronization";

 /** The LAS t_ SERVE r_ SYNCHRONIZATION. */
 public static String LAST_SERVER_SYNCHRONIZATION = "lastServerSynchronization";

 /** The LAS t_ SYNCHRONIZATION. */
 public static String LAST_SYNCHRONIZATION = "lastSynchronization";

 /** The PURG e_ B y_ NAME. */
 public static String PURGE_BY_NAME = "purgeByName";

 /** The PURG e_ B y_ UID. */
 public static String PURGE_BY_UID = "purgeByUID";

 /** The PURG e_ DAT a_ SOURC e_ RECORDS. */
 public static String PURGE_DATA_SOURCE_RECORDS = "purgedatasourcerecords";

 /** The PURG e_ MATCHES. */
 public static String PURGE_MATCHES = "purgeMatches";

 /** The PURG e_ SERVE r_ RECORDS. */
 public static String PURGE_SERVER_RECORDS = "purgeServerRecords";

 /** The RECORD. */
 public static String RECORD = "record";

 /** The REMOVE. */
 public static String REMOVE =  "remove";

 /** The RENAME. */
 public static String RENAME = "rename";

}
