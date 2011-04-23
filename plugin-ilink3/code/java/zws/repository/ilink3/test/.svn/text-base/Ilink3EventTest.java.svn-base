/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Nov 20, 2007 6:41:50 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.repository.ilink3.test;

import zws.repository.ilink3.Ilink3EventWatcher;
import zws.repository.ilink3.event.watcher.client.ILink3EventWatcherClient;

public class Ilink3EventTest {
  public static void main(String[] args) {
    Ilink3EventTest t = new Ilink3EventTest();
    try {
      t.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void run() throws Exception{
    Ilink3EventWatcher eventSvc = ILink3EventWatcherClient.getClient("ilink");
    eventSvc.startEventListener();
    //eventSvc.setEventListenerRunPeriod("ilink", 10);
    //eventSvc.fireEventListenerEvents("ilink");
    //eventSvc.stopEventListener();
  }
}

