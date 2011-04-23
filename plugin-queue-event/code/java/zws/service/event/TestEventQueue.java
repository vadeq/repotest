package zws.service.event;

/*
 * DesignState - Design Compression Technology @author: arbind @version: 1.0
 * Created on Apr 5, 2007 2:28:32 PM Copywrite (c) 2007 Zero Wait-State Inc. All
 * rights reserved
 */
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.origin.IntralinkOrigin;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.qx.event.TestEvent;
import zws.security.Authentication;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @author arbind
 */
public class TestEventQueue {


  public static void main(String[] args) throws Exception {
      TestEventQueue t = new TestEventQueue();
      t.run();
  }

  public void run() throws Exception {
    QxContext ctx = new QxContext();
    EventQueuePlugin eventQ = new EventQueuePlugin();
    eventQ.execute("stop");
    fire(eventQ);
    eventQ.execute("start");

  }

  private void fire(EventQueuePlugin eventQ) {

    BufferedReader input = null;
    try {
      Authentication id = new Authentication("admin","agile");
      Metadata m = createMetadata("29-5001-01.prt");
      TestEvent testEvent = new TestEvent();
          testEvent.setEventType("ilink-promote");
          testEvent.setAuthor("pavan");
          testEvent.setEvent("testEvent");
          testEvent.setName("Event-1");
          testEvent.set("to", "pavan.toleti@gmail.com");
          testEvent.set("repository-name", "agile-sdk");
          testEvent.setDomainName("zws-domain");
          testEvent.setServerName("designstate-0");
          testEvent.add(m);
          m = createMetadata("9999-0101.prt");
          testEvent.add(m);
          {} //System.out.println("data origin " + m.getOrigin().getUniqueIDDisplay());
          {} //System.out.println(testEvent.toXML());
          {} //System.out.println("-------------------------------------------");
          eventQ.fire(id, testEvent);
          //String s = "<event to='pavan.toleti@gmail.com' time='2007.10.23.17.20.23' domain='zws-domain' server='designstate-0' name='Event-1' event-type='zws.qx.event.AgileCheckinHandler' author='pavan'/>";
         // eventQ.fire(id, new QxXML(s));
          {} //System.out.println("------------------END-------------------------");
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (input != null) {
          input.close();
        }
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }
  
  private static Metadata createMetadata(String name) {
    MetadataBase root = new MetadataBase();
    File f = new File("C:/test.txt");
    root.set("name", name);
    root.set("lib_x", "888.88");
    root.set("number", name);
    root.set("description", "Code Drop 3");
    root.set("attachment", f.getAbsolutePath());
    root.set("author", "Matt Mohr");
    root.set("version", "B.8");
    root.set("dirtyFlag", "true");
    //29-5001-01.prt main C 0
    Origin o1 = materializeOrigin(name, "main", "C", 0);
    root.setOrigin(o1);
    return root;
  }

  
  private static Origin materializeOrigin(String name, String branch, String rev, int ver) {
    IntralinkOrigin o = new IntralinkOrigin();
    o.setDomainName("zws");
    o.setServerName("node0");
    o.setDatasourceName("ilink");
    o.setName(name);
    o.setBranch(branch);
    o.setRevision(rev);
    o.setVersion(ver);
    {} //System.out.println(o);
    {} //System.out.println(o.getUniqueIDDisplay());
    return o;
  }
  
}
