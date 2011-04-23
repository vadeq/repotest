package zws.qx.event.custom.harris.handlers;

/*
 DesignState - Design Compression Technology
 @author: ptoleti
 @version: 1.0
 Created on Apr 9, 2007 3:17:33 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */



import zws.qx.QxContext;
import zws.qx.event.handler.EventHandlerBase;
import zws.qx.event.handler.QxHandler;
import zws.service.synchronization.qx.SynchronizationClient;
import zws.service.synchronization.qx.SynchronizationService;
import zws.util.RoutedEventBase;


public class RenameSyncLogOnIntralinkRename extends EventHandlerBase implements QxHandler{
  public void execute(QxContext eventContext, RoutedEventBase event) {
    //collection of metadata...
    // publish them as collection...
    try {
      /*<event datasource="ilink" datasource-name="ilink" time="2007.12.04.10.14.01"
       * new-name="8888-01011.prt" domain="harris" server="node-0"
       * event-id="7976" name="8888-0101.prt"
       * event-type="event.design.renamed"
       * author="intralink"/>*/
      String user = (String)event.get("author");
      String name = (String)event.get("name");
      String newName = (String)event.get("new-name");
      String domainName = (String)event.get("domain");
      String repositoryName = (String)event.get("datasource-name");
      String serverName = (String)event.get("server");
      SynchronizationService r = SynchronizationClient.getClient();
      r.rename(domainName, serverName, repositoryName, name, newName);
      {} //System.out.println(eventType + " executed successfully...");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public boolean handles(QxContext ctx, RoutedEventBase event) {
    if(eventType.equals(event.getEventType()))
      return true;
    else
      return false;
  }
}
