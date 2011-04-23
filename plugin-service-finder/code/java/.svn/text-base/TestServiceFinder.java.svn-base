
import zws.application.Names;
import zws.application.Properties;
import zws.qx.QxWebClient;
import zws.qx.service.QxServiceFinder;
import zws.qx.service.ServiceRecord;

/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Nov 1, 2007 11:53:46 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

public class TestServiceFinder {
  public static void main(String[] args) {

    TestServiceFinder p = new TestServiceFinder();
    try {
      p.run(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Run.
   *
   * @param repName the rep name
   * @throws Exception 
   */
  private void run(String repName) throws Exception {
    Properties.set(Names.SERVICE_FINDER_HOSTNAME,"Designstate-0");
    QxServiceFinder c = QxServiceFinder.materializeFinder("synchronization");
    ServiceRecord s = c.getServiceRecord();
    {} //System.out.println("service found... " + s.toString());
    QxWebClient result = (QxWebClient) c.materializeClient();
    {} //System.out.println(result.toString());
  }
}


