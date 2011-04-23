package zws.qx.queue;

import java.util.Collection;
import java.util.Iterator;

import zws.qx.queue.persistence.QxQueueFactory;
import zws.queue.QxQueueRecordBase;

/*
 DesignState - Design Compression Technology
 @author: HP USER
 @version: 1.0
 Created on Apr 4, 2007 10:03:25 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

public class DaemonQueue {
  
  
  
  private static DaemonQueue _instance = null;
  
  private DaemonQueue(){
    
  }
  
  public static DaemonQueue getInstance(){
    if( _instance == null )
      _instance = new DaemonQueue();
    return _instance;
  }
  
  public void reInstantiate() throws Exception {
    Collection queues = QxQueueFactory.getQueues();
    if(null == queues || queues.size()<0) return;
    Iterator itr = queues.iterator();
      while(itr.hasNext()) {
        QxQueueRecordBase qRecord = (QxQueueRecordBase) itr.next();
        QxQueueService.reInstantiate(qRecord.getQ_name());
      }
  }
  public void stop() throws Exception {
    QxQueueService.shutdown();
    _instance = null;
  }
}
