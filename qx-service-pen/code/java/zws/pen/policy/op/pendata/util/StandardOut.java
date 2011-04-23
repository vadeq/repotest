/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */
package zws.pen.policy.op.pendata.util;

//impoer zws.util.{}//Logwriter;

public class StandardOut extends RecordMessageOpBase {
  protected void recordMessage(String s) {
    {}//Logwriter.printOnConsole(s);
    System.out.println(s);
  }
 }
