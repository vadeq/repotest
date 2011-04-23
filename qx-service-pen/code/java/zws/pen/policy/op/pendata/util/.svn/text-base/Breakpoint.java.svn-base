/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */
package zws.pen.policy.op.pendata.util;

import zws.pen.policy.op.pendata.PENDataOpBase;

public class Breakpoint extends PENDataOpBase {

  public void execute() throws Exception {
    String x = "Breakpoint Reached";
    if (null!=spot) x+= ": " + spot;
    x += concatenateDoOps();
    {} //System.out.println(x);  //place breakpoint on this line to trace through policy at runtime :)
  }
  
  public String getSpot() { return spot; }
  public void setSpot(String s) { spot=s; }

  private String spot=null;
 }
