package zws.qx.service;

/*
 * DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Jun 13, 2007 10:34:52 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */

import zws.qx.Qx;
import zws.qx.QxContext;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import zws.recorder.ActivityRecordBase;
import zws.recorder.ExecutionRecord;
import zws.recorder.ExecutionRecordBase;
import zws.service.recorder.qx.RecorderSvc;
import zws.service.recorder.qx.RecorderClient;
import zws.time.Duration;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.SortedSet;

/**
 * The Class RecorderQxService.
 *
 * @author ptoleti
 */
public class RecorderQxService implements Qx {

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
    String strResult = null;
    try {
      QxInstruction qxInst = dataInstruction.toQxProgram();
      {}//Logwriter.printOnConsole(" qxInst in RecorderQxService" + qxInst);
      String methodName   = qxInst.getName();
      String id           = qxInst.get(RecorderClient.sID);
      String msg          = qxInst.get(RecorderClient.sMSG);
      String name         = qxInst.get(RecorderClient.sNAME);
      String node         = qxInst.get(RecorderClient.sNODE);
      String notes        = qxInst.get(RecorderClient.sNOTES);      
      String status       = qxInst.get(RecorderClient.sSTATUS);
      String domain       = qxInst.get(RecorderClient.sDOMAIN);
      String childId      = qxInst.get(RecorderClient.sCHILDID);
      String msgType      = qxInst.get(RecorderClient.sMSGTYPE);
      String parentId     = qxInst.get(RecorderClient.sPARENTID);
      String namespace    = qxInst.get(RecorderClient.sNAMESPACE);
      String dotedDate    = qxInst.get(RecorderClient.sDOTEDDATE);
      String cutOffTime   = qxInst.get(RecorderClient.sCUTOFFTIME);
      String description  = qxInst.get(RecorderClient.sDESCRIPTION);

      long startTime = 0;
      ExecutionRecord record = null;
      SortedSet sortedSet = null;
      if (RecorderClient.sRECORD_START_TIME.equals(methodName)) {
        if (null == status && null == description&& null == notes) {
          startTime = RecorderSvc.recordStartTime(namespace, name);
        } else if (null == description && null == notes) {
          startTime = RecorderSvc.recordStartTime(namespace, name, status);
        } else if (null != description && null == notes) {
          startTime = RecorderSvc.recordStartTime(namespace, name, status, description);
        } else {
          startTime = RecorderSvc.recordStartTime(namespace, name, status, description, notes);
        }
        result = new QxXML("<result value=\"" + String.valueOf(startTime) + "\"/>");
      } else if (RecorderClient.sSTAMP_START_TIME.equals(methodName)) {
        if (null == status && null == description && null == notes) {
          startTime = RecorderSvc.stampStartTime(namespace, name, dotedDate);
        } else if (null == description && null == notes) {
          startTime = RecorderSvc.stampStartTime(namespace, name, status, dotedDate);
        } else if (null != description && null == notes) {
          startTime = RecorderSvc.stampStartTime(namespace, name, status, dotedDate, description);
        } else {
          startTime = RecorderSvc.stampStartTime(namespace, name, status, dotedDate, description, notes);
        }
        result = new QxXML("<result value=\"" + String.valueOf(startTime) + "\"/>");
      } else if (RecorderClient.sRECORD_CHILD_START_TIME.equals(methodName)) {
        if (null == status && null == description && null == notes) {
          startTime = RecorderSvc.recordChildStartTime(new Long(parentId).longValue(), namespace, name);
        } else if (null == description && null == notes) {
          startTime = RecorderSvc.recordChildStartTime(new Long(parentId).longValue(), namespace, name, status);
        } else if (null != description && null == notes) {
          startTime = RecorderSvc.recordChildStartTime(new Long(parentId).longValue(), namespace, name, status, description);
        } else {
          startTime = RecorderSvc.recordChildStartTime(new Long(parentId).longValue(), namespace, name, status, description, notes);
        }
        result = new QxXML("<result value=\"" + String.valueOf(startTime) + "\"/>");
      } else if (RecorderClient.sRECORD_END_TIME.equals(methodName)) {
        if (null == status) {
          RecorderSvc.recordEndTime(new Long(id).longValue());
        } else {
          RecorderSvc.recordEndTime(new Long(id).longValue(), status);
        }
        result = new QxXML("<result value=\"recordEndTime completed\"/>");
      } else if (RecorderClient.sGET_LAST_RECODRING.equals(methodName)) {
        if (null != name) {
          record =  RecorderSvc.getLastRecording(namespace, name);
        } else {
          record =  RecorderSvc.getLastRecording(namespace);
        }
        result = new QxXML("<result>" + toXML(record) + "</result>");
      } else if (RecorderClient.sRECORD_STATUS.equals(methodName)) {
        RecorderSvc.recordStatus(new Long(id).longValue(), status);
        result = new QxXML("<result value=\"recordStatus completed\"/>");
      } else if (RecorderClient.sRECORD_ACTIVITY.equals(methodName)) {
        RecorderSvc.recordActivity(new Long(id).longValue(), domain, node, msgType, msg, notes);
        result = new QxXML("<result value=\"recordActivity completed\"/>");
      } else if (RecorderClient.sDELETE_RECORD.equals(methodName)) {
        RecorderSvc.deleteRecord(new Long(id).longValue());
        result = new QxXML("<result value=\"deleteRecord completed\"/>");
      } else if (RecorderClient.sPURGE_RECORDS.equals(methodName)) {
        //RecorderSvc.purgeRecords(zws.util.TimeUtil.getCalendar(cutOffTime).getTime());
    	SimpleDateFormat sf = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy");    	  
        RecorderSvc.purgeRecords(sf.parse(cutOffTime));
        result = new QxXML("<result value=\"purgeRecords completed\"/>");
      } else if (RecorderClient.sGET_STATUS.equals(methodName)) {
        strResult = RecorderSvc.getStatus(new Long(id).longValue());
        result = new QxXML("<result value=\"" + strResult + "\"/>");
      } else if (RecorderClient.sGET_RECORDINGS.equals(methodName)) {
        sortedSet = RecorderSvc.getRecordings(namespace, name);
        result = new QxXML("<result>" + toXML(sortedSet) + "</result>");
      } else if (RecorderClient.sGET_RECORDINGS_BY_STATUS.equals(methodName)) {
          sortedSet = RecorderSvc.getRecordingsByStatus(namespace, status);
          result = new QxXML("<result>" + toXML(sortedSet) + "</result>");
      } else if (RecorderClient.sGET_CHILD_RECORDINGS.equals(methodName)) {
        sortedSet = RecorderSvc.getChildRecordings(new Long(parentId).longValue());
        result = new QxXML("<result>" + toXML(sortedSet) + "</result>");
      } else if (RecorderClient.sGET_PARENT_RECODRING.equals(methodName)) {
        record = RecorderSvc.getParentRecording(new Long(childId).longValue());
        result = new QxXML("<result>" + toXML(record) + "</result>");
      } else if (RecorderClient.sGET_ACTIVITY.equals(methodName)) {
        sortedSet = RecorderSvc.getActivity(new Long(id).longValue());
        result = new QxXML("<result>" + toXML(sortedSet) + "</result>");
      } else if (RecorderClient.sGET_NAMES.equals(methodName)) {
        sortedSet = RecorderSvc.getNames(namespace);
        result = new QxXML("<result>" + toXML(sortedSet) + "</result>");
      } else if (RecorderClient.sGET_NAMESPACES.equals(methodName)) {
        sortedSet = RecorderSvc.getNamespaces();
        result = new QxXML("<result>" + toXML(sortedSet) + "</result>");
      } else if (RecorderClient.sGET_RECODRING.equals(methodName)) {
        record =  RecorderSvc.getRecording(new Long(id).longValue());
        result = new QxXML("<result>" + toXML(record) + "</result>");
      }  else {
        result = new QxXML("Error:: Method name is not identified in RecorderQxService");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    {}//Logwriter.printOnConsole(" result in RecorderQxService.executeQx" + result);
    return result;
  }

  /**
   * To XML.
   *
   * @param sortedSet the sorted set
   *
   * @return the string
   */
  private String toXML(SortedSet sortedSet) {
    StringBuffer xmlRecord = new StringBuffer();
    String finalValue = null;
    ExecutionRecordBase record = null;
    {}//Logwriter.printOnConsole("^^^^^^^^^^^^^^^^^^^ Record ^^^^^^^^^^^^^^^^^^^^^^^^^");
    if (null != sortedSet) {
      Iterator itr = sortedSet.iterator();
      while (itr.hasNext()) {
        record = (ExecutionRecordBase) itr.next();
        xmlRecord.append(toXML(record));
      }
    }
    finalValue = xmlRecord.toString();
    {}//Logwriter.printOnConsole(finalValue);
    {}//Logwriter.printOnConsole(" --------------------------- ");
    return finalValue;
  }

  /**
   * To XML.
   *
   * @param record the record
   *
   * @return the string
   */
  private String toXML(ExecutionRecord record) {
    if(null == record) return "";
    StringBuffer xmlRecord = null;
    StringBuffer actBuffer = null;
    String finalValue = null;
    SortedSet activityList = null;
    SortedSet children = null;
    ActivityRecordBase  activity = null;
    {}//Logwriter.printOnConsole("^^^^^^^^^^^^^^^^^^^ toXML start ^^^^^^^^^^^^^^^^^^^^^^^^^ " + record.getID());
    xmlRecord = new StringBuffer();

    xmlRecord.append(START_TAG).append(RecorderClient.sEXECUTION_RECORD);
    xmlRecord.append(prepareArg(RecorderClient.sID, String.valueOf(record.getID())));
    xmlRecord.append(prepareArg(RecorderClient.sNAME, record.getName()));
    xmlRecord.append(prepareArg(RecorderClient.sNAMESPACE, record.getNamespace()));
    xmlRecord.append(prepareArg(RecorderClient.sDESCRIPTION, record.getDescription()));
    xmlRecord.append(prepareArg(RecorderClient.sNOTES, record.getNotes()));    
    xmlRecord.append(prepareArg(RecorderClient.sSTATUS, record.getStatus()));
    //xmlRecord.append(prepareArg(RecorderClient.sSTAMP_START_TIME, record.getStartTime().toString()));
    Duration duration = record.getDuration();
    if(null != duration) {
      if(null != duration.getStartTime())
        xmlRecord.append(prepareArg(RecorderClient.sSTAMP_START_TIME, duration.getStartTime().toString()));
      if(null != duration.getEndTime())
        xmlRecord.append(prepareArg(RecorderClient.sSTAMP_END_TIME, duration.getEndTime().toString()));
    }
    //xmlRecord.append(prepareArg("duration", record.getDuration().toString())); this will be acalculated field
    xmlRecord.append(CLOSE_TAG);

    activityList = record.getActivity();
    if (null != activityList) {
      actBuffer = new StringBuffer();
      Iterator actItr = activityList.iterator();
      while (actItr.hasNext()) {
        activity = (ActivityRecordBase) actItr.next();
        actBuffer = new StringBuffer();
        actBuffer.append(START_TAG).append(RecorderClient.sACTIVITY);
        actBuffer.append(prepareArg(RecorderClient.sMSGTYPE, activity.getType()));
        actBuffer.append(prepareArg(RecorderClient.sDOMAIN, activity.getDomain()));
        actBuffer.append(prepareArg(RecorderClient.sNODE, activity.getNodeName()));
        actBuffer.append(prepareArg(RecorderClient.sMSG, activity.getMessage()));
        actBuffer.append(prepareArg(RecorderClient.sNOTES, activity.getNotes()));
        actBuffer.append(prepareArg(RecorderClient.sTIME_STAMP, String.valueOf(activity.getTimestamp())));
        actBuffer.append(END_TAG);

        xmlRecord.append(actBuffer.toString());
      }
    }

    children = record.children();
    if (null != children) {
      Iterator childrenItr = children.iterator();
      while (childrenItr.hasNext()) {
        xmlRecord.append(toXML((ExecutionRecord) childrenItr.next()));
      }
    }
    //xmlRecord.append("</record>");
    xmlRecord.append(START_TAG).append(SLASH).append(RecorderClient.sEXECUTION_RECORD).append(CLOSE_TAG);

    finalValue = xmlRecord.toString();
    {}//Logwriter.printOnConsole(finalValue);
    {}//Logwriter.printOnConsole(" --------------------------- ");
    return finalValue;
  }


  /**
   * Prepare arg.
   *
   * @param value value
   * @param key key
   *
   * @return arg
   */
  private String prepareArg(String key, String value) {
    // prepare string like  key="value"
    StringBuffer argBuffer = new StringBuffer();
    argBuffer.append(SPACE).append(key).append(EQUALTO).append(QUOTE).append(value).append(QUOTE);
    return argBuffer.toString();
  }

  /** The QUOTE. */
  public static String QUOTE = "\"";

  /** The EQUALTO. */
  public static String EQUALTO = "=";

  /** The STAR t_ TAG. */
  public static String START_TAG = "<";

  /** The EN d_ TAG. */
  public static String END_TAG = "/>";

  /** The CLOS e_ TAG. */
  public static String CLOSE_TAG = ">";

  /** The SLASH. */
  public static String SLASH = "/";

  /** The SPACE. */
  public static String SPACE = " ";

}
