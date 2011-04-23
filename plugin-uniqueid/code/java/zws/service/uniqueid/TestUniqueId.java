/**
 * 
 */
package zws.service.uniqueid;

/*
 DesignState - Design Compression Technology
 @author: eankutse
 @version: 1.0
 Created on Aug 17, 2007 9:06:13 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.qx.QxContext;

/**
 * @author eankutse
 *
 */
public class TestUniqueId {
  public static void main(String[] args) {
    
    TestUniqueId p = new TestUniqueId();
    try {
      p.run();
    } catch (Exception e) {
      e.printStackTrace();
    }
    }
  
  private void run() throws Exception {
    {} //System.out.println("Contacting UniqueID Service on DesignState-EKA");
    UniqueIdClient client = UniqueIdClient.materializeClient();
    QxContext ctx = new QxContext();
    String id = client.getId(ctx);
    {} //System.out.println("Assigned Id : " + id);
  }
}
