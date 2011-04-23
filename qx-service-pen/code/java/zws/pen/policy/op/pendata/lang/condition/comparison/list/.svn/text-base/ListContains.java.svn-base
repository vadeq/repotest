/* DesignState - Design Compression Technology
 * @author: Rodney
 * @version: 1.0
 * Created on Mar 20, 2008 8:21:53 AM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.lang.condition.comparison.list;

import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;
import zws.application.Properties;
import java.util.*;

public class ListContains extends ConditionOPBase {

  private boolean ignoreCase = false;
  
  public Boolean evaluateCondition() throws Exception{

    String needle = null;
    Collection haystack = null;
    Collection operations = doOps();
    Object opResult;

    Iterator opsIterator = operations.iterator();
    while ( opsIterator.hasNext() ) {
      opResult = opsIterator.next();
      if (opResult instanceof Collection) 
        haystack = (Collection) opResult;
      else if (opResult instanceof String)
        needle = (String) opResult;
    }
        
    // validate the needle and haystack before comparison
    // BTW, a null collection is different than a zero length collection as
    // a null collection is a configuration/policy problem!
    if (haystack == null) throw new Exception("A valid Collection is required for comparison.");
    if (needle == null) throw new Exception("A String is required for comparison.");

    Iterator iterator = haystack.iterator();
    boolean found = false;
    Object item;
    String value;

    if ( isIgnoreCase() ) needle = needle.toLowerCase();
    
    // For each item, see if we have something to work with.  If so:
    //  1. find out if we are to ignore case
    //  2. compare the item
    //  3. if we find the needle in the haystack, we're done!
    while(iterator.hasNext()) {        
      item = iterator.next();
      if (item == null) continue;
      if (isIgnoreCase()) value = item.toString().toLowerCase();
      else value = item.toString();
 
      if (value.equals(needle)) {
        found = true;
        break;
      }
    }
    return new Boolean(found);
  }
  
  public boolean isIgnoreCase() { return ignoreCase; }
  public void setIgnoreCase(boolean b) { ignoreCase = b; }  
  
  // whats this for?
  public static long getSerialVersionUID()  { return serialVersionUID; }
  private static final long serialVersionUID = 0;
}
