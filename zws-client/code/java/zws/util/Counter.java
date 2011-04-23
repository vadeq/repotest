/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Dec 12, 2007 10:03:39 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.util;
public class Counter {
  public static synchronized long nextCount() { return count++; }  
  private static long count=1;
}
