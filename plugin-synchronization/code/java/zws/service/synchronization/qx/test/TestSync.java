package zws.service.synchronization.qx.test;
/* DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on May 30, 2007 12:05:16 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import zws.origin.IntralinkOrigin;
import zws.origin.Origin;
import zws.origin.OriginMaker;

import zws.service.synchronization.qx.SynchronizationClient;
import zws.service.synchronization.qx.SynchronizationService;

/**
 * The Class TestSync.
 *
 * @author arbind
 */
public class TestSync {

  /**
   * The main method.
   *
   * @param args args
   */
  public static void main(String[] args) {

    TestSync p = new TestSync();
    String origin = "";
    if (null != args && args.length > 0) {
      origin = args[0];
    }
    p.run(origin);
    }

   /**
    * Run.
    * @param origin origin
    */
  private void run(String origin) {
    SynchronizationService r = SynchronizationClient.getClient();

    Long startTime;

    try {
      /*{} //System.out.println("rename");
      r.rename(DOMAIN_NAME_0, SERVER_NAME, DATASOURCE_NAME_0, NAME, NEW_NAME);
      {} //System.out.println("isSynchronized");
      r.rename(DOMAIN_NAME_0, SERVER_NAME, DATASOURCE_NAME_0, NAME, NEW_NAME);
      //29-5002-01.prt main C 0
      boolean b = r.isSynchronized(materializeOrigin("del_00_bearing.prt", "main", "A", 0), materializeOrigin("impeller.prt", "main", "A", 0));
      {} //System.out.println("isSynchronized " + b);

      b = r.isSynchronized(materializeOrigin("del_00_bearing.prt", "main", "A", 0), materializeOrigin("del_00_bearing_new.prt", "main", "A", 0));
      {} //System.out.println("isSynchronized " + b);*/
      //r.findMatches("zwait", "DesignState-node-0", "ilink-1", "del_00_backhead.prt");
      /*Collection c = new ArrayList();
      c.add("zws|node0|ilink|ilink|8|branch1|rev1|0|name1|N/A|N/A|N/A|N/A");
      c.add("zws|node0|ilink|ilink|8|branch2|rev2|0|name2|N/A|N/A|N/A|N/A");
      c.add("zws|node0|ilink|ilink|8|branch3|rev2|0|name3|N/A|N/A|N/A|N/A");
      c.add("zws|node0|ilink|ilink|8|branch4|rev2|0|name4|N/A|N/A|N/A|N/A");
      c.add("zws|node0|ilink|ilink|8|branch5|rev2|0|name5|N/A|N/A|N/A|N/A");
      {} //System.out.println(c);
      r.record(c);*/
/*
      Origin originA = OriginMaker.materialize("zws|node0|ilink|ilink|8|branch1|rev1|0|nameA|N/A|N/A|N/A|N/A");
      Origin originB = OriginMaker.materialize("zws|node0|ilink|ilink|8|branch1|rev1|0|nameB|N/A|N/A|N/A|N/A");
      //r.record(originA, originB);

      boolean b = r.isSynchronizedToDatasource(originA, originA.getDomainName(), originA.getServerName(), originA.getDatasourceName());
      {} //System.out.println("-------------------b------------");
      {} //System.out.println(b);
      SynchronizationRecordBase sync = new SynchronizationRecordBase();
      sync.setData(originA, originB);
      //r.remove(sync);
*/

        /*Origin originB = OriginMaker.materialize("zws|node-0|ilink|ilink|1186694499000|main|1|0|77-8001.prt|N/A|N/A|N/A|N/A");
        Origin originA = OriginMaker.materialize("cisco|node-0|agile.CAD.Part|agile-sdk|1192042799704|6069321|MEP_77-8001_P");
        r.record(originA, originB);
        
        originB = OriginMaker.materialize("zws|node-0|ilink|ilink|1186694499000|main|1|0|77-8002.prt|N/A|N/A|N/A|N/A");
        originA = OriginMaker.materialize("cisco|node-0|agile.CAD.Part|agile-sdk|1192038904766|6069339|MEP_77-8002_P");
        r.record(originA, originB);        
        
        originB = OriginMaker.materialize("zws|node-0|ilink|ilink|1186694499000|main|1|0|77-1111.prt|N/A|N/A|N/A|N/A");
        originA = OriginMaker.materialize("cisco|node-0|agile.CAD.Part|agile-sdk|1192040212110|6069351|MEP_77-1111_P");   
        

        originB = OriginMaker.materialize("zws|node-0|ilink|ilink|1186694499000|main|1|0|29-5006-01.prt|N/A|N/A|N/A|N/A");
        originA = OriginMaker.materialize("cisco|node-0|agile.CAD.Part|agile-sdk|1192042968001|6068477|MEP_29-5006-01_P");   
        */

      Origin originB = OriginMaker.materialize("zws|node-0|ilink|ilink|1191625533000|main|A|0|500-100.asm|N/A|N/A|N/A|N/A");
      Origin originA = OriginMaker.materialize("cisco|node-0|agile.CAD.Part|agile-sdk|1192042799704|6069321|MEP_500-100_A");
      r.record(originA, originB);

        

      //{} //System.out.println(r.isSynchronizedToDatasource(originA, "zws", "node0", "ilink"));
      //{} //System.out.println(r.findSynchronization(originA, "zws", "node0", "ilink"));
     /* {} //System.out.println(originA.toString());
      String strOrigin = originA.toString();      
      r.record(originA, originB);
     
      
      int idx = -1;
      {}//Logwriter.printOnConsole("second origin " + strOrigin);


      {}//Logwriter.printOnConsole("second origin " + strOrigin);
      idx = -1;

      while ((idx = strOrigin.indexOf(".prt")) >= 0) {
        strOrigin = strOrigin.substring(0, idx) + "_P" + strOrigin.substring(idx + ".prt".length());
      }
      {}//Logwriter.printOnConsole("second origin " + strOrigin);
*/
      
    //<event datasource="ilink" datasource-name="ilink" time="2007.12.04.10.06.34" 
      //new-name="8888-0101.prt" domain="harris" server="node-0" event-id="7975" 
      //name="8888-01011.prt" event-type="event.design.renamed" author="intralink"/>
      
      r.rename("harris", "node-0", "ilink", "8888-0101.prt", "8888-01011.prt");



      {} //System.out.println("-------------------------------");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * Materialize origin.
   * @param rev the rev
   * @param name the name
   * @param branch the branch
   * @param ver the ver
   *
   * @return the origin
   */
  private Origin materializeOrigin(String name, String branch, String rev, int ver) {
    IntralinkOrigin o = new IntralinkOrigin();
    o.setDomainName("zws");
    o.setServerName("node0");
    o.setDatasourceName("ilink");
    o.setName(name);
    o.setBranch(branch);
    o.setRevision(rev);
    o.setVersion(ver);
    return o;
  }


  /** The SOURCE. */
  public static String DATASOURCE_NAME_0 = "ilink-1";

  /** The DATASOURC e_ NAME. */
  public static String DATASOURCE_NAME_A = "ilink-2";

  /** The DOMAIN. */
  public static String DOMAIN_NAME_0 = "zwait";

  /** The DOMAI n_ NAME. */
  public static String DOMAIN_NAME_A = "zwait";

  /** The SERVE r_ NAME. */
  public static String SERVER_NAME = "DesignState-node-0";

  /** The NAME. */
  public static String NAME = "main|A|0|del_00_backhead.prt";

  /** The NE w_ NAME. */
  public static String NEW_NAME = "main|A|0|del_00_backhead_new.prt";

  /** The OL d_ ORIGI n_0 s. */
  public static String OLD_ORIGIN_0S = "oldorigin0s";

  /** The OL d_ ORIGI n_ AS. */
  public static String OLD_ORIGIN_AS = "oldoriginas";

  /** The ORIGIN. */
  public static String ORIGIN = "origin";

  /** The ORIGI n_ a. */
  public static String ORIGIN_A = "main|A|0|del_00_bearing.prt";

  /** The ORIGI n_ b. */
  public static String ORIGIN_B = "main|A|0|del_00_bearing.prt";

  /** The ORIGINS. */
  public static String ORIGINS = "origins";


  /** The SYN c_ RECORD. */
  public static String SYNC_RECORD = "syncrecord";

  /** The TARGET. */
  public static String TARGET = "target";

  /** The TARGE t_ DATASOURCES. */
  public static String TARGET_DATASOURCES = "targetdatasources";

  /** The UID. */
  public static String UID = "uid";

}
