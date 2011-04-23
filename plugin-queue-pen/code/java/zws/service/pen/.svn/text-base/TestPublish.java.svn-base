package zws.service.pen;
/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Apr 5, 2007 2:28:32 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */
import zws.application.Names;
import zws.application.Properties;
import zws.data.Metadata;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.repository.source.RepositoryMetadataSource;
import zws.security.Authentication;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;

import java.util.ArrayList;
import java.util.Collection;

public class TestPublish {
  
  TestPublish() {
    Properties.set(Names.SERVICE_FINDER_HOSTNAME, "designstate-0");    
  }

  /**
   * The main method.
   * @param args the args
   */
  public static void main(String[] args) {
    TestPublish t = new TestPublish();
    try {
      t.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Run.
   * @throws Exception 
   */
  public void run() throws Exception {
    
    repSvc = RepositoryClient.getClient();
    r = repSvc.findRepository("ilink");
    
    Metadata mLatestData = null;
    Origin latestOrigin = null;
    String name = "123.asm";
    Authentication id = new Authentication("intralink", "zero0");
    QxContext ctx = new QxContext();
    ctx.set("mass-sync", "true");
    RepositoryMetadataSource source = r.materializeMetadataSource();
    mLatestData = source.findLatest(ctx, name, id);
    latestOrigin = mLatestData.getOrigin();
    PenQueuePlugin penQ = new PenQueuePlugin();
    Collection c = new ArrayList();
    c.add(latestOrigin);
    //latestOriginpenQ.publish(c,"publish","1","agile-sdk",id);
    penQ.publish(latestOrigin, "mass-sync", "1", "agile-sdk", ctx, id);
    
  }
  private  static RepositoryService repSvc = null;
  private  static Repository r = null;
}
