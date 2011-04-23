/* DesignState - Design Compression Technology
 * @author: Rodney
 * @version: 1.0
 * Created on Mar 20, 2008 8:21:53 AM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.lang.condition.comparison.list;

import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;
import zws.application.Properties;
import java.util.*;

public class FolderListContains extends ConditionOPBase {

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

    boolean found = false;
    Object item;
    String value;

    needle = needle.trim();
    needle = needle.replaceAll("\\\\", "/"); //normalize the path separator 
    if (!needle.endsWith("/")) needle += "/"; //make sure all folder paths end with a slash
    // For each item, see if we have something to work with.  If so:
    //  1. find out if we are to ignore case
    //  2. compare the item
    //  3. if we find the needle in the haystack, we're done!
    Iterator iterator = haystack.iterator();
    while(!found && iterator.hasNext()) {        
      item = iterator.next();
      if (item == null) continue;
      value=item.toString().trim();
      if (needle.length()> value.length());
      value=value.replaceAll("\\\\", "/"); //normalize the path separator 

      if (value.endsWith("*")) { // treat this as a root folder 
        value = value.substring(0,value.length()-1); //remove the *
        if (!value.endsWith("/")) value += "/";
        if ( isIgnoreCase() && needle.toLowerCase().startsWith(value)) found=true;
        else if ( needle.startsWith(value)) found=true;        
      }
      else { // treat this as the exact folder path
        if (!value.endsWith("/")) value += "/";
        if ( isIgnoreCase() && value.equalsIgnoreCase(needle)) found=true;
        else if ( value.equals(needle)) found=true;
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
