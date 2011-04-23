package zws.util; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.Collection;
import java.util.Vector;

public class ListUtil {

  public static Collection reverse(Collection c) {
    if (null==c) return null;
    Object [] list = c.toArray();
    Collection r = new Vector();
    for (int idx=list.length; 0<idx; r.add(list[--idx]));
    return r;
  }

}
