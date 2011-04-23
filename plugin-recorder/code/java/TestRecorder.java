/* DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on May 30, 2007 12:05:16 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.application.Names;
import zws.application.Properties;
import zws.recorder.ExecutionRecord;
import zws.recorder.util.RecorderUtil;
import zws.service.recorder.qx.RecorderClient;
import zws.service.recorder.qx.RecorderService;
//import zws.util.{}//Logwriter;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author arbind
 *
 */
public class TestRecorder {

  /**
   * @param args args
   */
  public static void main(String[] args) {

    TestRecorder p = new TestRecorder();
    p.run();
    }

   /**   */
  private void run() {
    Properties.set(Names.SERVICE_FINDER_HOSTNAME, "designstate-0");
    RecorderService r = RecorderClient.getClient();

    Long startTime;

    try {
      {} //System.out.println("recordStartTime ");
     /* startTime = r.recordStartTime("test-name-space2", "recordStartTime", "test-status2", "test-description2");
      {}//Logwriter.printOnConsole(startTime.toString());

      r.recordActivity(startTime, "test-domain", "test-node", "test-type", "test message1");
      r.recordActivity(startTime, "test-domain", "test-node", "test-type", "test message2");
      r.recordActivity(startTime, "test-domain", "test-node", "test-type", "test message3");

      r.recordChildStartTime(startTime, "child-name-space", "child1");
      r.recordChildStartTime(startTime, "child-name-space", "child2");
      r.recordChildStartTime(startTime, "child-name-space", "child3");
      r.recordChildStartTime(startTime, "child-name-space", "child4");

      SortedSet s = r.getRecordings("test-name-space2", "recordStartTime");
      {} //System.out.println("result in TestRecorder " + s.toString());

      {} //System.out.println("getRecording");
      ExecutionRecord record = r.getRecording(startTime);
      {} //System.out.println(record.toString());

      /*System.out.println("recordStartTime 1");
      startTime = r.recordStartTime("test-name-space", "recordStartTime 1");
      {}//Logwriter.printOnConsole(startTime.toString());
      {} //System.out.println("recordStartTime 2");
      startTime = r.recordStartTime("test-name-space", "recordStartTime 2", "test-status");
      {}//Logwriter.printOnConsole(startTime.toString());
      {} //System.out.println("recordStartTime 3");
      startTime = r.recordStartTime("test-name-space", "recordStartTime 3", "test-status", "test-description");
      {}//Logwriter.printOnConsole(startTime.toString());

      {} //System.out.println("stampStartTime 1");
      startTime = r.stampStartTime("test-name-space", "stampStartTime 1", "2007.06.13.10.10.10");
      {}//Logwriter.printOnConsole(startTime.toString());

      {} //System.out.println("stampStartTime 2");
      startTime = r.stampStartTime("test-name-space", "stampStartTime 2", "test-status", "2007.06.13.10.10.10");
      {}//Logwriter.printOnConsole(startTime.toString());

      {} //System.out.println("stampStartTime 3");
      startTime = r.stampStartTime("test-name-space", "stampStartTime 3e", "test-status", "2007.06.13.10.10.10", "test-description");
      {}//Logwriter.printOnConsole(startTime.toString());


      {} //System.out.println("recordChildStartTime 1");
      Long startTime2 = r.recordChildStartTime(startTime, "test-name-space", "recordChildStartTime 1");
      {}//Logwriter.printOnConsole(startTime.toString());

      {} //System.out.println("recordChildStartTime 2");
      Long startTime3 = r.recordChildStartTime(startTime, "test-name-space", "recordChildStartTime 2", "test-status");
      {}//Logwriter.printOnConsole(startTime.toString());

      {} //System.out.println("recordChildStartTime 3");
      startTime = r.recordChildStartTime(startTime, "test-name-space", "recordChildStartTime 3", "test-status", "test-description");
      {}//Logwriter.printOnConsole(startTime.toString());


      {} //System.out.println("recordEndTime 1");
       r.recordEndTime(startTime2);
       {}//Logwriter.printOnConsole("recordEndTime 1 done");
      {} //System.out.println("recordEndTime 2");
      r.recordEndTime(startTime3, "test-status");
      {}//Logwriter.printOnConsole("recordEndTime 2 done");*/

      //String namespace=zws.application.Names.PEN_NAMESPACE;
      //String name = "publish";


      RecorderService recSvc = RecorderClient.getClient();
/*
      Collection pendingRecords  = recSvc.getRecordings(namespace,name);
      {}//Logwriter.printOnConsole("pendingRecords " + pendingRecords);

      namespace=Names.PEN_QUEUE_NAMESPACE;
      name = Names.PEN_PUBLISH_DATA;
      Collection records = recSvc.getRecordings(namespace,name);
*/
      {}//Logwriter.printOnConsole("processLogs " + records);
      {} //System.out.println("-------------------------------");
      //recSvc.recordActivity(new Long(2133), "test", "d-0", "test", "test message", "test notes");
      //Long id = recSvc.recordStartTime("test", "test name", "<test status/>", "test description");
      //recSvc.recordChildStartTime(Long.getLong(id.toString()), "c1", "child 1", "<ch\\ild st&a`t\"u<>s>", "child description");
      Collection c = r.getRecordingsByStatus(Names.PEN_QUEUE_NAMESPACE, Names.STATUS_PUBLISHING);
      System.out.println(c);
      Iterator itr = c.iterator();
		while(itr.hasNext()){
			ExecutionRecord record = (ExecutionRecord) itr.next();
			r.recordEndTime(new Long(record.getID()), Names.STATUS_STALE);
		}

      
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }
}
