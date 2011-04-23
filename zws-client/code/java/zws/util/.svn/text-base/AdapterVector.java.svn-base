package zws.util;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.Serializable;
import java.util.*;

public class AdapterVector implements AdapterCollection, Serializable {
  public AdapterVector() { super(); }
  public AdapterVector(AdapterPrototype adapter) { super(); adapterPrototype=adapter; }
  public void bind(Collection c) { collection=c; }
  public void clear() { collection.clear(); }
  public int size() { return collection.size(); }
  public boolean isEmpty() { return collection.isEmpty(); }
  public boolean contains(Object o ) { return collection.contains(o); }
  public boolean containsAll(Collection c) { return containsAll(c); }
  public Iterator iterator() { return collection.iterator(); }
  public Object[] toArray() { return collection.toArray(); }
  public Object[] toArray(Object[] a) { return collection.toArray(a); }
  public boolean remove(Object o ) { return collection.remove(o); }
  public boolean removeAll(Collection c) { return collection.removeAll(c); }
  public boolean retainAll(Collection c) { return collection.retainAll(c); }  
  public boolean add(Object o) { return collection.add(adapt(o)); }

  public boolean addAll(Collection c) {
    if (null==c) return false;
    boolean adding=true;
    Iterator i = c.iterator();
    while (i.hasNext() && adding) adding = add(i.next());
    return adding;
  }
  private Adapter adapt(Object o) {
    if (null==o) throw new IllegalArgumentException("AdapterVector: could not adapt object[null] using adapter["+adapterPrototype.toString()+"]");
    Adapter a = (Adapter)adapterPrototype.copy();
    try { a.adapt(o); return a; }
    catch (Exception e) {
      throw new IllegalArgumentException("AdapterVector: could not adapt object["+o.toString()+"] using adapter["+adapterPrototype.toString()+"]");
    }
  }
  public Adapter getAdapterPrototype() { return adapterPrototype; }
  public void setAdapterPrototype(AdapterPrototype p) {adapterPrototype=p;}
  
  private AdapterPrototype adapterPrototype=null;
  private Collection collection = new Vector();
}