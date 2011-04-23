package zws.qx.event.handler.ilink;

/*
 DesignState - Design Compression Technology
 @author: ptoleti
 @version: 1.0
 Created on Apr 9, 2007 3:17:33 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.application.Names;
import zws.data.Metadata;
import zws.exception.InvalidConfiguration;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.qx.event.handler.EventHandlerBase;
import zws.qx.event.handler.QxHandler;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.source.RepositoryMetadataSource;
import zws.service.pen.PenQueuePlugin;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;
import zws.util.RoutedEventBase;

import java.util.Collection;
import java.util.Iterator;

public class Intralink8CheckinHandler extends EventHandlerBase implements QxHandler{
  public void execute(QxContext eventCtx, RoutedEventBase event) {
    try {
      Thread.sleep(90000);
      Collection dataList = event.getMetadataList();
      if(null==dataList) return;

      Metadata data = null;
      PenQueuePlugin penQ = new PenQueuePlugin();
      String targetDataSource = "agile-wsx";
      //if(null == targetDataSource)
      RepositoryService r = RepositoryClient.getClient();
      String sourceRepositoryName = (String) event.get("repositry-name");
      Repository sourceRepository = r.findRepository(sourceRepositoryName );
      if (null==sourceRepository) {
        throw new InvalidConfiguration("Event Error: can not find source Repository: " + sourceRepositoryName);
      }
      RepositoryMetadataSource source = sourceRepository.materializeMetadataSource();
      if (null==source) {
        throw new InvalidConfiguration("Event Error: can not materialize RepositoryMetadatasource: " + sourceRepositoryName);
      }
      Repository targetRepository = r.findRepository(targetDataSource);
      if (null==targetRepository) {
        throw new InvalidConfiguration("Event Error: can not find Target Repository: " + targetDataSource);
      }
      Iterator itr = dataList.iterator();
      Origin o;
      String name;
      Metadata m;
      while(itr.hasNext()) {
        data = (Metadata) itr.next();
        o = data.getOrigin();
        name = o.getName();
        m = source.findLatest(eventCtx, name, sourceRepository.getSystemAuthentication());
        String location = m.get("container");
        {} //System.out.println(m.getName() + " is in " + location);
        if ("CiscoStandard".equalsIgnoreCase(location)) {
          {} //System.out.println("publishing " + m.getName());
          RecorderUtil.logActivity(eventCtx, data.getOrigin().getName(), Names.STATUS_STARTED);
          penQ.publish(data.getOrigin(),"publish","1",targetDataSource,eventCtx, targetRepository.getSystemAuthentication());
        }
        {} //System.out.println(m.getName() + " is not in CiscoStandard Library - publishing skipped");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    {} //System.out.println(eventType + " executed successfully...");
  }

  public boolean handles(QxContext ctx, RoutedEventBase event) {
    if(!eventType.equals(event.getEventType())) return false;
    return true;
  }
}
