package zws.util;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.*;

public class TransformerVector implements TransformerCollection {
  public TransformerVector() { super(); }
  public TransformerVector(Transformer t) { super(); transformer=t; }
  public void bind(Collection c) { collection=c; }
  public void clear() { collection.clear(); }
  public int size() { return collection.size(); }
  public boolean isEmpty() { return collection.isEmpty(); }
  public boolean contains(Object o ) { return collection.contains(o); }
  public boolean containsAll(Collection c) { return collection.containsAll(c); }
  public Iterator iterator() { return collection.iterator(); }
  public Object[] toArray() { return collection.toArray(); }
  public Object[] toArray(Object[] a) { return collection.toArray(a); }
  public boolean remove(Object o ) { return collection.remove(o); }
  public boolean removeAll(Collection c) { return collection.removeAll(c); }
  public boolean retainAll(Collection c) { return collection.retainAll(c); }
  
  public boolean add(Object o) { return collection.add(transform(o)); }
  public boolean addAll(Collection c) { 
    boolean adding=true;
    Iterator i = c.iterator();
    while (i.hasNext() && adding) adding=add(i.next());
    return adding;
  }
  private Object transform(Object o) {
    if (null==transformer) return o;
    try { transformer.adapt(o); return transformer.transform(); }
    catch (Exception e) {
      throw new IllegalArgumentException("TransformerVector: could not transform object ["+o.toString()+"] using transformer ["+transformer.toString()+"]");
    }
  }
  public Transformer getTransformer() { return transformer; }
  public void setTransformer(Transformer t) { transformer=t; }
  
  private Transformer transformer=null;
  private Collection collection=new Vector();
}