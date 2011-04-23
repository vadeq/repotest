/*
 * DesignState - Design Compression Technology
 * @author: ptoleti @version: 1.0
 * Created on Mar 29, 2007 10:23:29 AM
 * Copywrite (c) 2007 Zero Wait-State Inc.
 * All rights reserved
 */

package zws.service.pen;

import zws.application.Names;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.qx.program.QxInstruction;
import zws.qx.service.QxServiceFinder;
import zws.qx.xml.QxXML;
import zws.recorder.util.RecorderUtil;
import zws.security.Authentication;
import zws.exception.CanNotMaterialize;
import zws.exception.zwsException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/**
 * The Class PenQueuePlugin.
 */
public class PenQueuePlugin {

  /**
   * The Constructor.
   * @param hostname the hostname
   */
  public PenQueuePlugin() {  }

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
    ctx.set(QxContext.QUEUE_NAME, "pen-queue");
    QxWebClient webClient = (QxWebClient) finder.materializeClient();

    QxXML  responseXML = webClient.executeQx(ctx, cancelInstruction);
    return responseXML;
  }

  public QxXML  publish(Origin origin, String intent, String priority, String targetRepository, QxContext userCtx, Authentication id) throws Exception {
    Collection origins = new ArrayList();
    origins.add(origin);
    String recipients = userCtx.get(QxContext.RECIPIENTS);
    return publish(origins, intent, priority, targetRepository, recipients, userCtx, id);
  }

  public QxXML  publish(Collection origins, String intent, String priority, String targetRepository, QxContext userCtx, Authentication id) throws Exception {
    String recipients = userCtx.get(QxContext.RECIPIENTS);
    return publish(origins, intent, priority, targetRepository, recipients, userCtx, id);
  }
  
  public QxXML  publish(Collection originsToPublish, String intent, String priority, String targetRepository, String recipients, Authentication id) throws Exception {
    if(null == originsToPublish) throw new zwsException("No items to be published");
    QxServiceFinder finder = QxServiceFinder.materializeFinder("pen-queue");
    QxContext ctx = finder.prepareQxWebClientContext("zws.qx.queue","QxQueueService");
    return publish(originsToPublish, intent, priority, targetRepository, recipients, ctx, id);
  }



  public QxXML  publish(Collection originsToPublish, String intent, String priority, String targetRepository, Authentication id) throws Exception {
    String recipients = null;
    return publish(originsToPublish, intent, priority, targetRepository, recipients, id);
  }

  public QxXML  publish(Collection originsToPublish, String intent, String priority, String targetRepository, String recipients, QxContext userCtx, Authentication id) throws Exception {
    if(null == originsToPublish) throw new zwsException("No items to be published");
    QxServiceFinder finder = QxServiceFinder.materializeFinder("pen-queue");
    QxContext finderCtx = finder.prepareQxWebClientContext("zws.qx.queue","QxQueueService");
    userCtx.merge(finderCtx, true);
    prepareRecorderContext(userCtx, intent, targetRepository, recipients, id);
    QxWebClient webClient = (QxWebClient) finder.materializeClient();
    QxXML  responseXML = webClient.executeQx(userCtx, constructInstruction(originsToPublish));
    return responseXML;
  }

  private void prepareRecorderContext(QxContext ctx, String intent, String targetRepository, String recipients, Authentication id) {
    ctx.set(QxContext.PROCESS_NAME_SPACE, Names.PEN_NAMESPACE);
    ctx.set(QxContext.QUEUE_NAME, Names.PEN_QUEUE_NAME);
    ctx.set(QxContext.USERNAME, id.getUsername());
    ctx.set(QxContext.PASSWORD, id.getPassword());
    ctx.set(QxContext.INTENT, intent);
    ctx.set(QxContext.TARGET_REPOSITORY, targetRepository);
    if(null != recipients) ctx.set(QxContext.RECIPIENTS, recipients);
  }

    private QxXML constructInstruction (Collection origins) throws CanNotMaterialize {
      QxInstruction  publishInstr = new QxInstruction("publish");
      QxInstruction  originInstr = null;
      Iterator itr = origins.iterator();
      Origin o = null;
      while(itr.hasNext()) {
        o = (Origin) itr.next();
        originInstr = new QxInstruction("origin");
        originInstr.set("value", o.toString());
        publishInstr.add(originInstr);
      }
      return new QxXML(publishInstr.toString());
    }

  /** The stop instruction. */
  private static QxXML  stopInstruction = new QxXML("<stop/>");

  /** The start instruction. */
  private static QxXML  startInstruction = new QxXML("<start/>");

  /** The cancel instruction. */
  private static QxXML  cancelInstruction = new QxXML("<cancel/>");

}
