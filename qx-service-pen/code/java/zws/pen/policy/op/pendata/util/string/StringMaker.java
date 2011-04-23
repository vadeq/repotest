/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.util.string;

import java.util.Collection;
import java.util.Iterator;

public class StringMaker extends StringResult {
  
  protected String makeString(String s) throws Exception {
    return s;
  }
  protected String makeOnEachString(String s) throws Exception {
    return s;
  }

  public void execute() throws Exception {
    String x = getString();
    if (null==x) x = "";
    
    Collection c = doOps();
    Iterator i = c.iterator();
    Object o;
    String s;
    while(i.hasNext()) {
      o = i.next();
      if (null==o) continue;
      s=o.toString();
      x += makeOnEachString(s);
    }
 
    x = makeString(x);
    setStringResult(x);
  } 

  public String getString() { return value; }
  public void setString(String s) { value = s; }

  private String value = null;
}
