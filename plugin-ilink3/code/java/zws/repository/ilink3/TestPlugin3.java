package zws.repository.ilink3;
/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Mar 18, 2007 6:52:38 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.data.Metadata;
import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.qx.QxContext;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.target.RepositoryStateTarget;
import zws.security.Authentication;
import java.security.NoSuchAlgorithmException;


/**
 * The Class TestPlugin.
 */
public class TestPlugin3 {
  private static Ilink3RepositoryBase base = new Ilink3RepositoryBase();
  /**
   * The Constructor.
   */
  private TestPlugin3() { }

  /**
   * @param args
   */
  public static void main(String[] args) {
    Authentication id;
    try {
      
      /*<intralink-3 name="ilink" system-password="agile" system-username="admin"
        domain-name="zws" server-name="node-0" 
        protocol="http" host-name="designstate-0" port="80" 
        env-root="ilink-1"
        services-path="ZeroWait-State/services"/>*/
      {} //System.out.println("Start......");
      base.setDomainName("zws");
      base.setHostName("10.10.10.175");
      base.setName("ilink");
      base.setPort("80");
      base.setProtocol("http");
      base.setRepositoryName("ilink");
      base.setServerName("node-0");
      base.setServicesPath("ZeroWait-State/services");
      base.setSystemPassword("agile");
      base.setSystemUsername("admin");
      base.setEnvRoot("ilink-1");
      id = new Authentication("admin", "agile");
      run();
      {} //System.out.println("END......");
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
   }

  /**
   * Run as publishing engine.
   */
  public static void run() {
    String name = null;
    QxContext ctx = new QxContext();
    try {
      Authentication id = new Authentication("admin", "agile");
      //name = "200-1501.drw";
      name = "frame.asm";
      //name = "line_roller.prt";
      Origin o= OriginMaker.materialize("zws|node-0|ilink|ilink|1188231064000|main|1|0|000-100-01.prt|N/A|N/A|N/A|N/A");
      
      RepositoryMetadataSource metadataSrc = base.materializeMetadataSource();
      Metadata m = metadataSrc.findLatest(ctx, o.getName(), id);
      
      RepositoryStateTarget stateTarget = base.materializeStateTarget();
      //stateTarget.lock(ctx, m.getOrigin(), id);
      {}//Logwriter.printOnConsole("locked.........!");
      //stateTarget.unlock(ctx, m.getOrigin(), id);
      {}//Logwriter.printOnConsole("un locked.........!");
      stateTarget.promoteLifeCycleReleaseState(ctx, o, "Released", id);
     // stateTarget.demoteLifeCycleReleaseState(ctx, o, "WIP", id);
      
    } catch (Exception e) { e.printStackTrace(); }
  }

  /*
  IntralinkOrigin o;
  o = new IntralinkOrigin();
  o.setBranch("br");
  o.setReleaseLevel("Released");
  o.setDatasourceName("ilink-00");
  o.setDomainName("zws");
  o.setServerName("DesignState-node-0");
  o.setName("abc.prt");
  o.setRevision("G");
  o.setVersion(8);
*/
}
