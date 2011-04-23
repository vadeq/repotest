/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Dec 14, 2007 7:26:10 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.qx.test;

import zws.qx.QxContext;

public class TestQxContext {

  public static void main(String[] args) {
    TestQxContext t = new TestQxContext();
    t.run();
  }
  
  private void run() {
    QxContext c = new QxContext();
    QxContext b = new QxContext();
    QxContext d = new QxContext();
    c.set("c", "c-context");
    b.set("b", "b-context");
    c.configureParent(b);
    d.set("d", "d-context");
    d.merge(c, false);
    {} //System.out.println(d);
  }
}
