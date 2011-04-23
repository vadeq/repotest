/* file name  : zws\service\recorder\RecorderService.java
 * authors    : Sulakshan Shetty
 * created    : 02/13/2006 11:23:12
 * copyright  : Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 *
 * modifications:
 *
 */
package zws.service.recorder.qx;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.SortedSet;

import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.qx.program.QxInstruction;
import zws.qx.service.QxServiceFinder;
import zws.qx.xml.QxXML;
import zws.recorder.ExecutionRecord;
import zws.util.RoutedEventBase;
import zws.util.StringUtil;

import java.io.StringReader;
import java.rmi.RemoteException;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

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
public class RecorderClient implements RecorderService {

  /**
   * @param strHostName HostName
   */
  private RecorderClient() { }

  /**
   * Prepare context.
   *
   * @param ctx the ctx
   */
  /*private void prepareContext(QxContext ctx) {
        ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.service");
        ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "RecorderQxService");
        ctx.set(QxContext.SOAP_HOSTNAME, hostName);
        ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
        ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
        ctx.set(QxContext.SOAP_SERVICE_OPERATION, "runQx");
  }*/

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
    instBuffer.append("<").append(argMap.get("method")).append(" ");
    argMap.remove("method");
    Iterator itr = argMap.keySet().iterator();
    while (itr.hasNext()) {
      key = (String) itr.next();
      value = (String) argMap.get(key);
      //instBuffer.append(key).append("=\"").append(value).append("\" ");
      if(null == value) continue;
      instBuffer.append(" ").append(key).append("=");
      StringUtil.appendXMLValue(instBuffer, value);
      
    }
    instBuffer.append("/>");
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

    QxServiceFinder finder = QxServiceFinder.materializeFinder("recorder-service");
    QxContext ctx = finder.prepareQxWebClientContext("zws.qx.service","RecorderQxService");
    QxWebClient webClient = (QxWebClient) finder.materializeClient();    
    
    return executeQx(webClient, ctx, prepareInstruction(argMap));
  }

  private synchronized QxXML executeQx(QxWebClient webClient, QxContext ctx, QxXML dataInstruction) throws Exception {
    //+++ queue this request for asynch execution and return immediately
    //Queue doesn't have to be persistent queue! (don't know where the client will be running....
    //If client is shut down abruptly, queue elements may be lost - 
    // that is ok - no need to make the client reliable at this time.
    return webClient.executeQx(ctx, dataInstruction);
  }

  /**
   * @param result result
   * @return recordings
   * @throws RemoteException exception
   */
  private SortedSet parseRecordings(String result) throws RemoteException {
    RecordParser insParser = null;
        insParser = new RecordParser();
    try {
      XMLReader rdr = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
      rdr.setContentHandler(insParser);
      rdr.setFeature("http://xml.org/sax/features/validation", false);
      rdr.parse(new InputSource(new StringReader(result)));
    } catch (Exception e) {
      e.printStackTrace();
      throw new RemoteException("Error in parseRecordings");
    }
    return insParser.getRecordings();
  }

  /**
   * @param result result
   * @return recordings
   * @throws RemoteException exception
   */
  private ExecutionRecord parseRecording(String result) throws RemoteException {
    SortedSet c = parseRecordings(result);
    if(c.size() ==0) return null;
    else return (ExecutionRecord) c.first();
  }
  /**
   * @return RecorderClient
   */
  public static RecorderClient getClient() {
    return new RecorderClient();
  }

  public void recordFiredEvent(RoutedEventBase firedEvent) throws Exception {
    Long id = stampStartTime(firedEvent.getNamespace().asString(), firedEvent.getEventName(), RoutedEventBase.STATUS_FIRED, firedEvent.getEventTime());
    firedEvent.set(RoutedEventBase.EVENT_ID, ""+id);
  }  
  

  /**
   * Record process start.
   * @param namespace Process namespace
   * @param name Process name
   * @param dotedDate timestamp to record
   * @return Unique process ID
   * @throws RemoteException On error or invalid argument is specified
   */
  public Long stampStartTime(String namespace, String name, String dotedDate) throws RemoteException {
    return stampStartTime(namespace, name, null, dotedDate, null, null);
  }

  /**
   * Record process start.
   * @param namespace Process namespace
   * @param name Process name
   * @param status Process status
   * @param dotedDate timestamp to record
   * @return Unique process ID
   * @throws RemoteException On error or invalid argument is specified
   */
  public Long stampStartTime(String namespace, String name, String status, String dotedDate) throws RemoteException {
    return stampStartTime(namespace, name, status, dotedDate, null, null);
  }


  /**
   * Record process start.
   * @param namespace Process namespace
   * @param name Process name
   * @param status Process status
   * @param dotedDate timestamp to record
   * @param description description
   * @return Unique process ID
   * @throws RemoteException On error or invalid argument is specified
   */
  public Long stampStartTime(String namespace, String name, String status, String dotedDate, String description) throws RemoteException {
    return stampStartTime(namespace, name, status, dotedDate, description, null);
  }

  /**
   * Record process start.
   * @param namespace Process namespace
   * @param name Process name
   * @param status Process status
   * @param dotedDate timestamp to record
   * @param description description
   * @return Unique process ID
   * @throws RemoteException On error or invalid argument is specified
   */
  public Long stampStartTime(String namespace, String name, String status, String dotedDate, String description, String notes) throws RemoteException {
    QxXML result = null;
    Long startTime = null;
    HashMap argMap = new HashMap();
    try {
      argMap.put(sMETHOD, sSTAMP_START_TIME);
      argMap.put(sNAMESPACE, namespace);
      argMap.put(sNAME, name);
      argMap.put(sSTATUS, status);
      argMap.put(sDOTEDDATE, dotedDate);
      argMap.put(sDESCRIPTION, description);
      argMap.put(sNOTES, notes);
      result = execute(argMap);
      String sTime = result.toQxProgram().get("value");
      if (null != sTime) {
        startTime = new Long(sTime);
      }
    } catch (Exception e) {
        e.printStackTrace();
        throw new RemoteException("Error in stampStartTime(namespace,name,status,dotedDate,description)");
    }
    return startTime;
  }


 /**
   * Record process start.
   * @param namespace Process namespace
   * @param name Process name
   * @return Unique process ID
   * @throws RemoteException On error or invalid argument is specified
   */
  public Long recordStartTime(String namespace, String name) throws RemoteException {
    return recordStartTime(namespace, name, null, null, null);
  }

  /**
   * Record process start.
   * @param namespace Process namespace
   * @param name Process name
   * @param status Process status
   * @return Unique process ID
   * @throws RemoteException On error or invalid argument is specified
   */
  public Long recordStartTime(String namespace, String name, String status) throws RemoteException {
    return recordStartTime(namespace, name, status, null, null);
  }


  /**
   * Record process start.
   * @param namespace Process namespace
   * @param name Process name
   * @param status Process status
   * @param description description
   * @return Unique process ID
   * @throws RemoteException On error or invalid argument is specified
   */
  public Long recordStartTime(String namespace, String name, String status, String description) throws RemoteException {
    return recordStartTime(namespace, name, status, description, null);
  }

    
  /**
   * Record process start.
   * @param namespace Process namespace
   * @param name Process name
   * @param status Process status
   * @param description description
   * @param notes notes
   * @return Unique process ID
   * @throws RemoteException On error or invalid argument is specified
   */
  public Long recordStartTime(String namespace, String name, String status, String description, String notes) throws RemoteException {
    QxXML result = null;
    Long startTime = null;
    HashMap argMap = new HashMap();
    try {
      argMap.put(sMETHOD, sRECORD_START_TIME);
      argMap.put(sNAMESPACE, namespace);
      argMap.put(sNAME, name);
      argMap.put(sSTATUS, status);
      argMap.put(sDESCRIPTION, description);
      argMap.put(sNOTES, notes);
      result = execute(argMap);
      {} //System.out.println("recordStartTime " + result);
      QxInstruction resultInst = result.toQxProgram();
      String sTime = resultInst.get("value");
      if (null != sTime) {
        startTime = new Long(sTime);
      }
    } catch (Exception e) {
        e.printStackTrace();
        throw new RemoteException("Error in recordStartTime(namespace,name,status,description)");
    }
    return startTime;
  }

  /**
   * Record process child start.
   * @param parentId Parent process ID
   * @param namespace Child process namespace
   * @param name Child process name
   * @return Unique child process ID
   * @throws RemoteException On error or invalid argument is specified
   */
  public Long recordChildStartTime(Long parentId, String namespace, String name) throws RemoteException {
    return recordChildStartTime(parentId, namespace, name, null, null, null);
  }

  /**
   * Record process child start.
   * @param parentId Parent process ID
   * @param namespace Child process namespace
   * @param name Child process name
   * @param status status
   * @return Unique child process ID
   * @throws RemoteException On error or invalid argument is specified
   */
  public Long recordChildStartTime(Long parentId, String namespace, String name, String status) throws RemoteException {
    return recordChildStartTime(parentId, namespace, name, status, null, null);
  }


  /**
   * Record process child start.
   * @param parentId Parent process ID
   * @param namespace Child process namespace
   * @param name Child process name
   * @param status status
   * @param description description
   * @return Unique child process ID
   * @throws RemoteException On error or invalid argument is specified
   */
  public Long recordChildStartTime(Long parentId, String namespace, String name, String status, String description) throws RemoteException {
    return recordChildStartTime(parentId, namespace, name, status, description, null);
  }

    
  /**
   * Record process child start.
   * @param parentId Parent process ID
   * @param namespace Child process namespace
   * @param name Child process name
   * @param status status
   * @param description description
   * @return Unique child process ID
   * @throws RemoteException On error or invalid argument is specified
   */
  public Long recordChildStartTime(Long parentId, String namespace, String name, String status, String description, String notes) throws RemoteException {
    QxXML result = null;
    Long startTime = null;
    HashMap argMap = new HashMap();
    try {
      argMap.put(sMETHOD, sRECORD_CHILD_START_TIME);
      argMap.put(sPARENTID, parentId.toString());
      argMap.put(sNAMESPACE, namespace);
      argMap.put(sNAME, name);
      argMap.put(sSTATUS, status);
      argMap.put(sDESCRIPTION, description);
      argMap.put(sNOTES, notes);
      result = execute(argMap);
      String sTime = result.toQxProgram().get("value");
      if (null != sTime) {
        startTime = new Long(sTime);
      }
    } catch (Exception e) {
        e.printStackTrace();
        throw new RemoteException("Error in recordChildStartTime(parentId,namespace, name,status,description))");
    }
    return startTime;
  }


  /**
   * Record process end.
   * @param id Process ID
   * @throws RemoteException On error or invalid argument is specified
   */
  public void recordEndTime(Long id) throws RemoteException {
    recordEndTime(id, null); 
  }

  /**
   * Record process end.
   * @param id Process ID
   * @param status Process status
   * @throws RemoteException On error or invalid argument is specified
   */
  public void recordEndTime(Long id, String status) throws RemoteException {
    QxXML result = null;
    HashMap argMap = new HashMap();
    try {
      argMap.put(sMETHOD, sRECORD_END_TIME);
      argMap.put(sID, id.toString());
      argMap.put(sSTATUS, status);
      result = execute(argMap);
    } catch (Exception e) {
        e.printStackTrace();
        throw new RemoteException("Error in recordEndTime(id,status)");
    }
  }

  /**
   * Record process status.
   * @param id Process ID
   * @param status Process status
   * @throws RemoteException On error or invalid argument is specified
   */
  public void recordStatus(Long id, String status) throws RemoteException {
    QxXML result = null;
    HashMap argMap = new HashMap();
    try {
      argMap.put(sMETHOD, sRECORD_STATUS);
      argMap.put(sID, id.toString());
      argMap.put(sSTATUS, status);
      result = execute(argMap);
    } catch (Exception e) {
        e.printStackTrace();
        throw new RemoteException("Error in recordStatus(id,status)");
    }
  }
  public void recordActivity(Long id, String domain, String node, String msgType, String msg) throws RemoteException {
    recordActivity(id, domain, node, msgType, msg, null); 
  }
  /**
   * Record process activity.
   * @param id Process ID
   * @param domain Activity domain
   * @param node Activity node
   * @param msgType Activity message
   * @param msg Activity message
   * @throws RemoteException On error or invalid argument is specified
   */
  public void recordActivity(Long id, String domain, String node, String msgType, String msg, String notes) throws RemoteException {
    QxXML result = null;
    HashMap argMap = new HashMap();
    try {
      argMap.put(sMETHOD, sRECORD_ACTIVITY);
      argMap.put(sID, id.toString());
      argMap.put(sDOMAIN, domain);
      argMap.put(sNODE, node);
      argMap.put(sMSGTYPE, msgType);
      argMap.put(sMSG, msg);
      argMap.put(sNOTES, notes);
      result = execute(argMap);
    } catch (Exception e) {
        e.printStackTrace();
        throw new RemoteException("Error in recordActivity(id,domain,node,msgType,msg)");
    }
  }

  /**
   * Delete process recording.
   * @param id Process ID
   * @throws RemoteException On error or invalid argument is specified
   */
  public void deleteRecord(Long id) throws RemoteException {
    QxXML result = null;
    HashMap argMap = new HashMap();
    try {
      argMap.put(sMETHOD, sDELETE_RECORD);
      argMap.put(sID, id.toString());
      result = execute(argMap);
    } catch (Exception e) {
        e.printStackTrace();
        throw new RemoteException("Error in deleteRecord(id)");
    }
  }

  /**
   * Delete all recordings that ended prior to specified date.
   * @param cutOffTime Delete recordings prior to cut off time
   * @throws RemoteException On error or invalid argument is specified
   */
  public void purgeRecords(Date cutOffTime) throws RemoteException {
    QxXML result = null;
    HashMap argMap = new HashMap();
    try {
      argMap.put(sMETHOD, sPURGE_RECORDS);
      argMap.put(sCUTOFFTIME, cutOffTime.toString());
      result = execute(argMap);
    } catch (Exception e) {
        e.printStackTrace();
        throw new RemoteException("Error in purgeRecords(cutOffTime)");
    }
  }

  /**
   * Get entire process recording (includes any children recordings also).
   * @param id Process ID
   * @return Entire process recording
   * @throws RemoteException On error or invalid argument is specified
   */
  public  ExecutionRecord getRecording(Long id) throws RemoteException {
    QxXML result = null;
    HashMap argMap = new HashMap();
    ExecutionRecord record = null;
    try {
      argMap.put(sMETHOD, sGET_RECODRING);
      argMap.put(sID, id.toString());
      result = execute(argMap);
      record = parseRecording(result.toString());
    } catch (Exception e) {
        e.printStackTrace();
        throw new RemoteException("Error in getRecording(id)");
    }
    return record;
  }

  /**
   * Get process status.
   * @param id Process ID
   * @return Process status
   * @throws RemoteException On error or invalid argument is specified
   */
  public  String getStatus(Long id) throws RemoteException {
    QxXML result = null;
    HashMap argMap = new HashMap();
    String status = null;
    try {
      argMap.put(sMETHOD, sGET_STATUS);
      argMap.put(sID, id.toString());
      result = execute(argMap);
      status = result.toQxProgram().get("value");
    } catch (Exception e) {
        e.printStackTrace();
        throw new RemoteException("Error in getStatus(id)");
    }
    return status;
  }

  /**
   * Get process statuses.
   * @param namespace Process namespace
   * @param name Process name
   * @return List of statuses
   * @throws RemoteException On error or invalid argument is specified
   */
  public SortedSet getRecordings(String namespace, String name) throws RemoteException {
    QxXML result = null;
    HashMap argMap = new HashMap();
    SortedSet recordings  = null;
    try {
      argMap.put(sMETHOD, sGET_RECORDINGS);
      argMap.put(sNAMESPACE, namespace);
      argMap.put(sNAME, name);
      result = execute(argMap);
      recordings = this.parseRecordings(result.toString());
    } catch (Exception e) {
        e.printStackTrace();
        throw new RemoteException("Error in getRecordings(namespace, name)");
    }
    return recordings;
  }
  
  public SortedSet getRecordingsByStatus(String namespace, String status) throws RemoteException {
	    QxXML result = null;
	    HashMap argMap = new HashMap();
	    SortedSet recordings  = null;
	    try {
	      argMap.put(sMETHOD, sGET_RECORDINGS_BY_STATUS);
	      argMap.put(sNAMESPACE, namespace);
	      argMap.put(sSTATUS, status);
	      result = execute(argMap);
	      recordings = this.parseRecordings(result.toString());
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RemoteException("Error in getRecordings(namespace, name)");
	    }
	    return recordings;
	  }

  /**
   * Get process statuses.
   * @param namespace Process namespace
   * @return Last execution record
   * @throws RemoteException On error or invalid argument is specified
   */
  public ExecutionRecord getLastRecording(String namespace) throws RemoteException {
    QxXML result = null;
    HashMap argMap = new HashMap();
    ExecutionRecord record = null;
    try {
      argMap.put(sMETHOD, sGET_LAST_RECODRING);
      argMap.put(sNAMESPACE, namespace);
      result = execute(argMap);
      if(null == result) return null;
      record = parseRecording(result.toString());
    } catch (Exception e) {
        e.printStackTrace();
        throw new RemoteException("Error in getLastRecording(namespace)");
    }
    return record;
  }

  /**
   * Get process statuses.
   * @param namespace Process namespace
   * @param name Process name
   * @return Last execution record for named process
   * @throws RemoteException On error or invalid argument is specified
   */
  public ExecutionRecord getLastRecording(String namespace, String name) throws RemoteException {
    QxXML result = null;
    HashMap argMap = new HashMap();
    ExecutionRecord record = null;
    try {
      argMap.put(sMETHOD, sGET_LAST_RECODRING);
      argMap.put(sNAMESPACE, namespace);
      argMap.put(sNAME, name);
      result = execute(argMap);
      record = parseRecording(result.toString());
    } catch (Exception e) {
        e.printStackTrace();
        throw new RemoteException("Error in getLastRecording(namespace, name)");
    }
    return record;
  }

  /**
   * Get process statuses.
   * @param parentId Process id
   * @return All child recordings
   * @throws RemoteException On error or invalid argument is specified
   */
  public SortedSet getChildRecordings(Long parentId) throws RemoteException {
    QxXML result = null;
    HashMap argMap = new HashMap();
    SortedSet recordings  = null;
    try {
      argMap.put(sMETHOD, sGET_CHILD_RECORDINGS);
      argMap.put(sPARENTID, parentId.toString());
      result = execute(argMap);
      recordings = this.parseRecordings(result.toString());
    } catch (Exception e) {
        e.printStackTrace();
        throw new RemoteException("Error in getChildRecordings(parent)");
    }
    return recordings;
  }

  /**
   * Get Parent process statuses.
   * @param childId Process id
   * @return Parent execution record
   * @throws RemoteException On error or invalid argument is specified
   */
  public ExecutionRecord getParentRecording(Long childId) throws RemoteException {
    QxXML result = null;
    HashMap argMap = new HashMap();
    ExecutionRecord record = null;
    try {
      argMap.put(sMETHOD, sGET_PARENT_RECODRING);
      argMap.put(sCHILDID, childId.toString());
      result = execute(argMap);
      record = parseRecording(result.toString());
    } catch (Exception e) {
        e.printStackTrace();
        throw new RemoteException("Error in getParentRecording(childId)");
    }
    return record;
  }

  /**
   * Get process activity list.
   * @param id Process ID
   * @return Process activity list
   * @throws RemoteException On error or invalid argument is specified
   */
  public SortedSet getActivity(Long id) throws RemoteException {
    QxXML result = null;
    HashMap argMap = new HashMap();
    SortedSet activities  = null;
    try {
      argMap.put(sMETHOD, sGET_ACTIVITY);
      argMap.put(id, id.toString());
      result = execute(argMap);
      activities = this.parseRecordings(result.toString());
    } catch (Exception e) {
        e.printStackTrace();
        throw new RemoteException("Error in getActivity(id)");
    }
    return activities;
  }


  /**
   * @param namespace namespace
   * @return Names
   * @throws RemoteException RemoteException
   */
  public SortedSet getNames(String namespace) throws RemoteException {
    QxXML result = null;
    HashMap argMap = new HashMap();
    SortedSet names  = null;
    try {
      argMap.put(sMETHOD, sGET_NAMES);
      argMap.put(namespace, sNAMESPACE);
      result = execute(argMap);
      names = this.parseRecordings(result.toString());
    } catch (Exception e) {
        e.printStackTrace();
        throw new RemoteException("Error in getNames(namespace)");
    }
    return names;
  }

  /**
   * @return  Namespaces
   * @throws RemoteException On error or invalid argument is specified
   */
  public SortedSet getNamespaces() throws RemoteException {
    QxXML result = null;
    HashMap argMap = new HashMap();
    SortedSet namespaces  = null;
    try {
      argMap.put(sMETHOD, sGET_NAMESPACES);
      result = execute(argMap);
      namespaces = this.parseRecordings(result.toString());
    } catch (Exception e) {
        e.printStackTrace();
        throw new RemoteException("Error in getNamespaces()");
    }
    return namespaces;
  }

  /**  methodname. */
  public static String sMETHOD = "method";
  /**  namespace. */
  public static String sNAMESPACE = "namespace";
  /** name.  */
  public static String sNAME = "name";
  /**  status. */
  public static String sSTATUS = "status";
  /** dotedDate.  */
  public static String sDOTEDDATE = "dotedDate";
  /** dotedDate.  */
  public static String sDESCRIPTION = "description";
  /** parentId.   */
  public static String sPARENTID = "parentId";
  /** childId.   */
  public static String sCHILDID = "childId";
  /**  id. */
  public static String sID = "id";
 /**  domain. */
  public static String sDOMAIN = "domain";
  /** node.  */
  public static String sNODE = "node";
  /**  msgType. */
  public static String sMSGTYPE = "msgType";
  /**  msg. */
  public static String sMSG = "msg";
  public static String sNOTES = "notes";
  /**  time stamp. */
  public static String sTIME_STAMP = "timeStamp";
  /**cutOffTime.   */
  public static String sCUTOFFTIME = "cutOffTime";
  /**  recordStartTime. */
  public static String sRECORD_START_TIME =  "recordStartTime";
  /**  stampStartTime. */
  public static String sSTAMP_START_TIME =  "stampStartTime";
  public static String sSTAMP_END_TIME =  "stampEndTime";
  /**  recordChildStartTime. */
  public static String sRECORD_CHILD_START_TIME =  "recordChildStartTime";
  /**  recordEndTime. */
  public static String sRECORD_END_TIME =  "recordEndTime";
  /**  recordStatus. */
  public static String sRECORD_STATUS =  "recordStatus";
  /**  recordActivity. */
  public static String sRECORD_ACTIVITY =  "recordActivity";
  /**  deleteRecord. */
  public static String sDELETE_RECORD =  "deleteRecord";
  /**  purgeRecords. */
  public static String sPURGE_RECORDS =  "purgeRecords";
  /**  getStatus. */
  public static String sGET_STATUS =  "getStatus";
  /**  getRecordings. */
  public static String sGET_RECORDINGS =  "getRecordings";
  /**  getRecordings. */
  public static String sGET_RECORDINGS_BY_STATUS =  "getRecordingsByStatus";
  /**  getChildRecordings. */
  public static String sGET_CHILD_RECORDINGS =  "getChildRecordings";
  /**  getActivity. */
  public static String sGET_ACTIVITY =  "getActivity";
  /**  getNames. */
  public static String sGET_NAMES =  "getNames";
  /**  getNamespaces. */
  public static String sGET_NAMESPACES =  "getNamespaces";
  /**  getRecording. */
  public static String sGET_RECODRING =  "getRecording";
  /**  getLastRecording. */
  public static String sGET_LAST_RECODRING =  "getLastRecording";
  /**  getParentRecording. */
  public static String sGET_PARENT_RECODRING =  "getParentRecording";
  /**  ExecutionRecord. */
  public static String sEXECUTION_RECORD =  "ExecutionRecord";
  /**  activity. */
  public static String sACTIVITY =  "Executionactivity";
}

