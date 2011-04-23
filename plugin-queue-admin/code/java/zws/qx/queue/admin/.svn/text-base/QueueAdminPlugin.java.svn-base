/*
 * DesignState - Design Compression Technology
 * @author: ptoleti @version: 1.0
 * Created on Mar 29, 2007 10:23:29 AM
 * Copywrite (c) 2007 Zero Wait-State Inc.
 * All rights reserved
 */

package zws.qx.queue.admin;


import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.qx.service.QxServiceFinder;
import zws.qx.xml.QxXML;


/**
 * The Class PenQueuePlugin.
 */
public class QueueAdminPlugin {

  /**
   * The Constructor.
   * @param hostname the hostname
   */
  public QueueAdminPlugin() {  }

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
   * List the queue currently managed by the QxQueueService.
   * @return the qx XML
   * @throws Exception
   */
  public QxXML list() throws Exception {
    QxServiceFinder finder = QxServiceFinder.materializeFinder("pen-queue");
    QxContext ctx = finder.prepareQxWebClientContext("zws.qx.queue","QxQueueService");
    //ctx.set(QxContext.QUEUE_NAME, "pen-queue");
    QxWebClient webClient = (QxWebClient) finder.materializeClient();

    //    System.out.println("------------- MONITOR LIST -------------------");
    //    System.out.println(ctx.toString());
    QxXML  responseXML = webClient.executeQx(ctx, listInstruction);
    //    System.out.println("\n\n" + responseXML);
    //    System.out.println("------------- DONE-------------------");
    return responseXML;
  }

  /**
   * Cancel.
   * @return the qx XML
   * @throws Exception
   */
  public QxXML cancel(String queueName) throws Exception {
    QxServiceFinder finder = QxServiceFinder.materializeFinder("pen-queue");
    QxContext ctx = finder.prepareQxWebClientContext("zws.qx.queue","QxQueueService");
    ctx.set(QxContext.QUEUE_NAME, queueName);
    QxWebClient webClient = (QxWebClient) finder.materializeClient();

    QxXML  responseXML = webClient.executeQx(ctx, cancelInstruction);
    return responseXML;
  }

  public QxXML purge(String queueName) throws Exception {
    QxServiceFinder finder = QxServiceFinder.materializeFinder("pen-queue");
    QxContext ctx = finder.prepareQxWebClientContext("zws.qx.queue","QxQueueService");
    ctx.set(QxContext.QUEUE_NAME, queueName);
    QxWebClient webClient = (QxWebClient) finder.materializeClient();

    QxXML  responseXML = webClient.executeQx(ctx, purgeInstruction);
    return responseXML;
  }
  
  /** The stop instruction. */
  private static QxXML  stopInstruction = new QxXML("<stop/>");

  /** The start instruction. */
  private static QxXML  startInstruction = new QxXML("<start/>");

  /** The cancel instruction. */
  private static QxXML  cancelInstruction = new QxXML("<cancel/>");

  /** The list instruction. */
  private static QxXML  listInstruction = new QxXML("<list/>");

  /** The purge instruction. */
  private static QxXML  purgeInstruction = new QxXML("<purge/>");
}
