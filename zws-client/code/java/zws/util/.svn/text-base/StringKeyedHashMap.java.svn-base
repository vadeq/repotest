package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 1, 2004, 10:29 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.*;

public class StringKeyedHashMap implements Map {
  public void clear() { map.clear(); caseSensitiveMap.clear(); }
  public boolean containsKey(Object key) { return containsKey((String)key); }
  public boolean containsKey(String key) { return map.containsKey(lookupCase(key)); }
  public boolean containsValue(Object value) { return map.containsValue(value); }
  public Set entrySet() { return map.entrySet(); }
  public Object get(Object key) { return get((String)key); }
  public Object get(String key) { return map.get(lookupCase(key)); }
  public int hashCode() { return map.hashCode(); }
  public boolean isEmpty() { return map.isEmpty(); }
  public Set keySet() { return map.keySet(); }
  public Object put(Object key, Object value) { return put((String)key, value); }
  public Object put(String key, Object value) {
    if (null!=key) caseSensitiveMap.put(key.toLowerCase(), key);
    return map.put(key, value);
  }
  public void putAll(Map t) {
    if (null==t) return;
    Iterator i = t.keySet().iterator();
    Object key;
    while (i.hasNext()) {
      key = i.next();
      put(key.toString(), t.get(key));
    }
  }
  public Object remove (Object key) { return remove((String)key); }
  public Object remove (String key) { return remove(lookupCase(key)); }
  public int size() { return map.size(); }
  public Collection values() { return map.values(); }
  
  private String lookupCase(String key) {
   if (key==null) return null;
   if (caseSensitive) return key;
   else return (String)caseSensitiveMap.get(key.toLowerCase());
  }
  
  public boolean getCaseSensitive() { return caseSensitive; }
  public void setCaseSensitive(boolean b) { caseSensitive=b; }
  
  private boolean caseSensitive = true;
  
  private Map caseSensitiveMap = new HashMap();
  private Map map = new HashMap();
}
