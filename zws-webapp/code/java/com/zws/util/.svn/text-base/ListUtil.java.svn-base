package com.zws.util;

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
