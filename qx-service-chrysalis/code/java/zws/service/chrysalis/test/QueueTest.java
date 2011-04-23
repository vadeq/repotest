/**
 * 
 */
package zws.service.chrysalis.test;/*
 DesignState - Design Compression Technology
 @author: eankutse
 @version: 1.0
 Created on Aug 22, 2007 7:06:40 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.application.Properties;
import zws.qx.QxContext;
import zws.qx.QxOp;
import zws.qx.queue.QxQueuePersistent;
import zws.qx.xml.QxXML;

import java.io.File;

/**
 * @author eankutse
 *
 */
public class QueueTest extends QxOp {

  public static void main(String[] args) {
    QueueTest t = new QueueTest();
    QxQueuePersistent f = null;
    QxQueuePersistent s = null;
    try {
      f = t.lookupFirstQueue();
      s = t.lookupSecondQueue();
    } catch (Exception e1) {
      e1.printStackTrace();
    }

    try {
      stop(f); //just for testing
      stop(s); //just for testing
      queueMessages(f);
      queueMessages(s);
      start(f); //just for testing
      start(s); //just for testing
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    
  }

  private static void stop(QxQueuePersistent q) throws Exception { q.stop(); }
  private static void start(QxQueuePersistent q) throws Exception { q.start(); }
  private static void queueMessages(QxQueuePersistent q) {
    QxContext ctx;
    QxXML xml;
    for (int i=0; i<8; i++) {
      ctx = new QxContext();
      xml = new QxXML("<hello-world-"+i+"/>");
      ctx.set("msg-"+i,"context");
      try {
        q.add(1, ctx, xml);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  
  public QxXML executeQx(QxContext ctx, QxXML data) {
    {} //System.out.println(ctx.toString());
    {} //System.out.println(data.toString());
    QxXML xml = new QxXML("<queue-test/>");
    return xml;
  }
  
  private QxQueuePersistent lookupFirstQueue() throws Exception {
    String qName = "firstQ";
    if(null==q1) {
      q1 = new QxQueuePersistent(getRootQueuePath()+Names.PATH_SEPARATOR+qName,this);
    }
    return q1;
  }

  private QxQueuePersistent lookupSecondQueue() throws Exception {
    String qName = "secondQ";
    if(null==q2) {
      q2 = new QxQueuePersistent(getRootQueuePath()+Names.PATH_SEPARATOR+qName,this);
    }
    return q2;
  }
  
  public static String getRootQueuePath(){
    String rootPath=null;
    if( rootPath == null ) rootPath = Properties.get(Names.DATA_DIR)+Names.PATH_SEPARATOR+"queue";
    File f = new File("/zws/data");
    if( rootPath == null ) rootPath = f.getAbsolutePath();
    return rootPath;
  }

  
  QxQueuePersistent q1=null, q2=null;  
  
}

