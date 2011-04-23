/*
 * DesignState - Design Compression Technology
 * @author: ptoleti @version: 1.0
 * Created on Mar 29, 2007 10:23:29 AM
 * Copywrite (c) 2007 Zero Wait-State Inc.
 * All rights reserved
 */

package zws.qx.queue.admin.test;

import zws.application.Names;
import zws.application.Properties;
import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.qx.program.QxInstruction;
import zws.qx.service.QxServiceFinder;
import zws.qx.xml.QxXML;
import zws.security.Authentication;
import zws.exception.CanNotMaterialize;
import zws.exception.zwsException;

import java.util.Collection;


/**
 * The Class PenQueuePlugin.
 */
public class TestQueuePlugin {

  /**
   * The Constructor.
   * @param hostname the hostname
   */
  public TestQueuePlugin() { 
    Properties.set(Names.SERVICE_FINDER_HOSTNAME, "designstate-0");
  }

  public static void main(String[] args) {
    TestQueuePlugin p = new TestQueuePlugin();
    try {
      for(int i =16161;i<100000;i++) {
        p.publish("test " + i);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * Stop.
   * @return the qx XML
   * @throws Exception
   */
  public QxXML stop(String queueName) throws Exception {
    QxServiceFinder finder = QxServiceFinder.materializeFinder("pen-queue");
    QxContext ctx = finder.prepareQxWebClientContext("zws.qx.queue","QxQueueService");
    ctx.set(QxContext.QUEUE_NAME, queueName);
    QxWebClient webClient = (QxWebClient) finder.materializeClient();
    QxXML  responseXML = webClient.executeQx(ctx, stopInstruction);
    return responseXML;
  }

  /**
   * Start.
   * @return the qx XML
   * @throws Exception
   */
  public QxXML start(String queueName) throws Exception {
    QxServiceFinder finder = QxServiceFinder.materializeFinder("pen-queue");
    QxContext ctx = finder.prepareQxWebClientContext("zws.qx.queue","QxQueueService");
    ctx.set(QxContext.QUEUE_NAME, queueName);
    QxWebClient webClient = (QxWebClient) finder.materializeClient();
    QxXML  responseXML = webClient.executeQx(ctx, startInstruction);
    return responseXML;
  }

  /**
   * Cancel.
   * @return the qx XML
   * @throws Exception
   */
  public QxXML cancel() throws Exception {
    QxServiceFinder finder = QxServiceFinder.materializeFinder("pen-queue");
    QxContext ctx = finder.prepareQxWebClientContext("zws.qx.queue","QxQueueService");
    ctx.set(QxContext.QUEUE_NAME, queueName);
    QxWebClient webClient = (QxWebClient) finder.materializeClient();

    QxXML  responseXML = webClient.executeQx(ctx, cancelInstruction);
    return responseXML;
  }


  public QxXML  publish(String value) throws Exception {
    if(null == value) throw new zwsException("No items to be published");
    QxServiceFinder finder = QxServiceFinder.materializeFinder("pen-queue");
    QxContext finderCtx = finder.prepareQxWebClientContext("zws.qx.queue","QxQueueService");
    finderCtx.set(QxContext.QUEUE_NAME, queueName);
    QxWebClient webClient = (QxWebClient) finder.materializeClient();
    QxXML  responseXML = webClient.executeQx(finderCtx, constructInstruction(value));
    return responseXML;
  }


    private QxXML constructInstruction (String value) throws CanNotMaterialize {
      QxInstruction instr = new QxInstruction("test");
      instr.set("testParam", value);
      instr.set("wait-time", waitTime);
      instr.set("file-name", fileName);
      return new QxXML(instr.toString());
    }

  /** The stop instruction. */
  private static QxXML  stopInstruction = new QxXML("<stop/>");

  /** The start instruction. */
  private static QxXML  startInstruction = new QxXML("<start/>");

  /** The cancel instruction. */
  private static QxXML  cancelInstruction = new QxXML("<cancel/>");
  
  private String queueName = "test-queue";
  
  private String waitTime = "2";
  private String testNum= "reliability-test3";
  private String fileName = "C:\\queue-test\\"+testNum+"\\result.txt";

}
