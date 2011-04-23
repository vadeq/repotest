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
import zws.origin.IntralinkOrigin;
import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.security.Authentication;
//import zws.util.{}//Logwriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;

// TODO: Auto-generated Javadoc
/**
 * The Class TestPenQueue.
 *
 * @author arbind
 */
public class TestPenQueue {

  /**
   * The main method.
   * @param args the args
   */
  public static void main(String[] args) {
    Properties.set(Names.SERVICE_FINDER_HOSTNAME, "designstate-0");
    if (args.length < 4) {
      {}//Logwriter.printOnConsole("Usage TestPenQueue HostName UserID password Filename ");
      {}//Logwriter.printOnConsole("Filename is name of file that contains list of part names to publish.");
    } else {
    hostName = args[0];
    userName = args[1];
    password = args[2];
    fileName = args[3];
    TestPenQueue t = new TestPenQueue();
    try {
      t.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
    }
  }

  /**
   * Run.
   * @throws Exception 
   */
  public void run() throws Exception {
    //QxContext ctx = new QxContext();
    QxContext ctx = materializeQxContext();
    //PenQueuePlugin penQ = new PenQueuePlugin("DesignState-0");
    PenQueuePlugin penQ = new PenQueuePlugin();
    QxWebClient webClient =  QxWebClient.materializeClient();
    webClient.clearKeys(ctx);
   // penQ.stop();
    publish(penQ);
    //penQ.list();
    //penQ.start();
    //penQ.list();

  }

  /**
   * @return qxcontext
   */
  private QxContext materializeQxContext() {
    QxWebClient webClient = QxWebClient.materializeClient();
    QxContext ctx = new QxContext();
    ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.queue");
    ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "QxQueueService");
    ctx.set(QxContext.SOAP_HOSTNAME, hostName);
    ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
    ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
    //ctx.set(QxContext.SOAP_SERVICE_OPERATION, "runQx");
    return ctx;
  }
  /**
   * Publish.
   * @param penQ the pen q
   */
  private void publish(PenQueuePlugin penQ) {

    BufferedReader input = null;
    try {
      Authentication id = new Authentication(userName, password);
      input = new BufferedReader(new FileReader(new File(fileName)));
      String line = null;
     /* while ((line = input.readLine()) != null) {
        if (line.startsWith("#") || line.startsWith("-")) {
              {}//Logwriter.printOnConsole(" Object " + line + " is not published.");
        } else {
            //Origin o1 = materializeOrigin(line, "main", "C", 0);
            // changed for assembly search...
            String[] result = line.split("\\s");
            if (result.length == 4) {
              //29-5001-01.prt main C 0
              Origin o1 = materializeOrigin(result[0], result[1], result[2], Integer.valueOf(result[3]).intValue());
              //penQ.publish(o1,"publish","agile-sdk",id);
              
              penQ.publish(o1,"publish","1","agile-sdk",id);

              
              {}//Logwriter.printOnConsole("object : " +  line + " published.");
            } else {
              {}//Logwriter.printOnConsole("object : " +  line + " is not published.");
            }
        }
      }*/
      /*<publish >
      <origin value='zws|node-0|ilink|ilink|1190917824000|main|A|1|2100-1101.prt|N/A|N/A|N/A|N/A' />
      <origin value='zws|node-0|ilink|ilink|1190917824000|main|A|1|2100-1200.prt|N/A|N/A|N/A|N/A' />
      </publish>
      */
      Collection c = new ArrayList();
      c.add(OriginMaker.materialize("zws|node-0|ilink|ilink|1190917824000|main|A|1|9999-0101.prt|N/A|N/A|N/A|N/A"));
      c.add(OriginMaker.materialize("zws|node-0|ilink|ilink|1190917824000|main|A|1|999-0102.prt|N/A|N/A|N/A|N/A"));
     penQ.publish(c,"publish","1","agile-sdk",id);
    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (input != null) {
          input.close();
        }
      } catch (IOException ex) {
        ex.printStackTrace();
      }
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

  /** The host name. */
  private static String hostName = null;

  /** The user name. */
  private static String userName = null;

  /** The password. */
  private static String password = null;

  /** The file name. */
  private static String fileName = null;
}
