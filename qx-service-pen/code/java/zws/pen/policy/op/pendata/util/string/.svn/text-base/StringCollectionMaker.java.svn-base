/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Feb 15, 2008 11:09:32 AM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.util.string;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class StringCollectionMaker extends StringCollectionResult {
  
  protected Collection makeStringCollection(Collection c) throws Exception {
    return c;
  }
  protected Collection makeOnEachStringCollection(Collection c) throws Exception {
    return c;
  }

  public void execute() throws Exception {
    Collection strs = getStringCollection();
    if (null==strs || strs.isEmpty()) strs = new Vector();
    
    Collection c = doOps(); //doOps returns a Collection of Collections of names.
                            //In this case, should be a Collection of ONE collection of names
    Iterator i = c.iterator();
    Object o;
    Collection strColl;

    while(i.hasNext()) {
      o = i.next();
      if (null==o) continue;
      
      if (o instanceof Collection) {
        strColl=(Collection)o;
        strColl=makeOnEachStringCollection(strColl);
        strs.addAll(strColl);
      }
    }
 
    strs = makeStringCollection(strs);
    setStringCollectionResult(strs);
  } 

  public Collection getStringCollection() { return value; }
  public void setStringCollection(Collection c) { value = c; }

  private Collection value = null;
}
