package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 6, 2004, 11:19 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.*;

public class TransformerTreeSet implements TransformerSortedSet {
  private SortedSet set;
  public TransformerTreeSet() {super(); set = new TreeSet(); }
  public TransformerTreeSet(Collection c) {super(); set = new TreeSet();  addAll(c); }
  public TransformerTreeSet(Comparator c) { super(); set = new TreeSet(c); }
  public TransformerTreeSet(SortedSet s) { super(); set = new TreeSet(); addAll(s); }
  
  public void bind(Collection s) { bind ((SortedSet)s); }
  public void bind(SortedSet s) { set=s; }
  public void clear() { clear(); }
  public Object clone() throws CloneNotSupportedException { return super.clone(); }
  public Comparator comparator() { return set.comparator(); }
  public boolean contains(Object o) { return set.contains(o); }
  public boolean containsAll(Collection c) { return set.containsAll(c); }
  public Object first() { return set.first(); }
  public SortedSet headSet(Object toElement) { 
    TransformerTreeSet tree = newSet(); 
    tree.addAll(set.headSet(toElement)); 
    return tree;
  }
  public boolean isEmpty() { return set.isEmpty(); }
  public Iterator iterator() { return set.iterator(); }
  public Object last() { return set.last(); }
  public boolean remove(Object o) { return set.remove(o); }
  public int size() { return set.size(); }
  public SortedSet subSet(Object fromElement, Object toElement) { 
    TransformerTreeSet tree = newSet(); 
    tree.addAll(set.subSet(fromElement, toElement)); 
    return tree;
  }
  public SortedSet tailSet(Object fromElement) { 
    TransformerTreeSet tree = newSet(); 
    tree.addAll(set.tailSet(fromElement)); 
    return tree;
  }
  private TransformerTreeSet newSet() {
    TransformerTreeSet tree;
    if (null!=comparator()) tree = new TransformerTreeSet(comparator());
    else tree = new TransformerTreeSet();
    tree.setTransformer(getTransformer());
    return tree;
  }
  public Object[] toArray() { return set.toArray(); }
  public Object[] toArray(Object[] a) { return set.toArray(a); }
  public boolean removeAll(Collection c) { return set.removeAll(c); }
  public boolean retainAll(Collection c) { return set.retainAll(c); }
  
  public boolean add(Object o) { return set.add(transform(o)); }
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
