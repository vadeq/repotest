package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 30, 2004, 12:45 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.*;

public class MapUtil {

	public static Pair getPairFromMap(Map map, Object key) {
	  Pair p  = (Pair)map.get(key);
	  if (null==p) {
	    p = new Pair();
	    map.put(key, p);
	  }
	  return p;
	}
	
  public static Collection getCollectionFromMap(Map map, String key) { return getListFromMap(map, key); }

  public static List getListFromMap(Map map, Object key) {
    List l = (List)map.get(key);
    if (null==l) {
      l = new Vector();
      map.put(key, l);
    }
    return l;
  }
  
  public static Set getSetFromMap(Map map, Object key) {
    Set s = (Set)map.get(key);
    if (null==s) {
      s = new TreeSet();
      map.put(key, s);
    }
    return s;
  }

  public static SortedSet getSortedSetFromMap(Map map, Object key, Comparator comparator) {
	  SortedSet s = (SortedSet)map.get(key);
	  if (null==s) {
	    s = new TreeSet(comparator);
	    map.put(key, s);
	  }
	  return s;
	}
    
  
  public static Map getMapFromMap(Map map, String key) {
    Map m = (Map)map.get(key);
    if (null==m) {
      m = new HashMap();
      map.put(key, m);
    }
    return m;
  }
}
