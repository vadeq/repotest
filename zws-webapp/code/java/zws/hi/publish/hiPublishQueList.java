package zws.hi.publish;

import com.zws.hi.hiList;
import com.zws.util.StringUtil;

import zws.application.Names;
import zws.recorder.ExecutionRecord;
import zws.recorder.util.RecorderUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

/*
 * DesignState - Design Compression Technology. @author: Rahul Deshmukh Created
 * on Mar 6, 2007 @version: 1.0 Copywrite (c) 2002-2005 Zero Wait-State Inc. All
 * rights reserved
 */

public class hiPublishQueList extends hiList {
    public void bind() {
      try {
        this.publish();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }


    public String publish() throws Exception
    {
      ExecutionRecord exeRecord = null;
      pendingRecords = new ArrayList();
      currentPublishingRecords = new ArrayList();
      String namespace=Names.PEN_NAMESPACE;
      String name = Names.PEN_PUBLISH;
      Collection  records  = RecorderUtil.getRecordings(namespace,name);
      setDurationScripts(new Vector(records ));
      Iterator itr = records .iterator();
      while(itr.hasNext()) {
        exeRecord = (ExecutionRecord) itr.next();
        if((exeRecord.getDuration().getDays() <= 1) &&
           (!Names.STATUS_COMPLETE.equalsIgnoreCase(exeRecord.getStatus()))) {
          pendingRecords.add(exeRecord);
        }
      }

      namespace=Names.PEN_QUEUE_NAMESPACE;
      name = Names.PEN_PUBLISH_DATA;
      Collection processLogs = RecorderUtil.getRecordings(namespace,name);

      Object arrayLogs[] = processLogs.toArray();
      Vector allPublishingRecords=new Vector();
      exeRecord = null;
      String status = null;

       int errorCount=0;
       int complCount=0;
       int publishCount=0;
       int cancelCount= 0;
      for (int i = 0; i < arrayLogs.length; i++) {
          exeRecord = (ExecutionRecord) arrayLogs[i];
          if(!StringUtil.isEmptyNullString(exeRecord.getStatus())) {
            status = exeRecord.getStatus();
              if (status.equals(Names.STATUS_PUBLISHING)) {
                  publishCount++;
                  currentPublishingRecords.add(exeRecord);
                  getDurationScripts().add(exeRecord);
              } else if (status.equals(Names.STATUS_COMPLETE)) {
                  complCount++;
                  allPublishingRecords.add(exeRecord);
              } else if (status.equals(Names.STATUS_CANCELED)) {
                cancelCount++;
                allPublishingRecords.add(exeRecord);
              } else if (status.equals(Names.STATUS_ERROR)) {
                  errorCount++;
                  allPublishingRecords.add(exeRecord);
              }
          }
      }
      //statusCount="Publising("+publishCount+") Error("+errorCount+") Complete("+complCount+")";
      currentCount  = "Publishing("+publishCount+") ";

      statusCount = " Complete("+complCount+")";
      statusCount += " Cancel("+cancelCount+")";
      statusCount += " Error("+errorCount+") ";

      setPendingRecords(pendingRecords);
      setCurrentPublishingRecords(currentPublishingRecords);
      setAllPublishingRecords(allPublishingRecords);

      return "index";
    }


    Collection pendingRecords = null;

    Collection currentPublishingRecords = null;

    Vector completedRecords = null;

    Vector errorRecords = null;

    Vector allPublishingRecords=null;

    Vector durationScripts= null;

    String statusCount= "";
    String currentCount= "";

    Names names= null;

    public void setCurrentCount(String s) {currentCount = s;}
    public String getCurrentCount() {return currentCount;}

    public void setStatusCount(String s) {statusCount = s;}
    public String getStatusCount() {return statusCount;}

    public Vector getErrorRecords() {return errorRecords;}
    public void setErrorRecords(Vector v) {errorRecords = v;}

    public Vector getCompletedRecords() { return completedRecords;}
    public void setCompletedRecords(Vector v) {completedRecords = v;}

    public Collection getPendingRecords() {return pendingRecords; }
    public void setPendingRecords(Collection c) {pendingRecords = c;}

    public Collection getCurrentPublishingRecords() {return currentPublishingRecords;}
    public void setCurrentPublishingRecords(Collection v) {currentPublishingRecords = v;}

    public Vector getAllPublishingRecords() {return allPublishingRecords;}
    public void setAllPublishingRecords(Vector v) {allPublishingRecords = v;}

    public Vector getDurationScripts() {return durationScripts;}
    public void setDurationScripts(Vector v) {this.durationScripts = v;}

    public Names getNames() {return new Names(); }
    public void setNames(Names n) {names = n;}
}