package zws.util; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on May 4, 2004
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import java.io.Serializable;
import java.util.*;

public class AdapterTreeSet implements AdapterSortedSet, Serializable {
    public AdapterTreeSet(Comparator c) { super(); sortedSet = new TreeSet(c); }
    public AdapterTreeSet(AdapterPrototype adapter, Comparator c) { 
      super(); 
      sortedSet = new TreeSet(c);
      adapterPrototype=adapter; 
    }
    public void bind(Collection s) { bind((SortedSet)s); }
    public void bind(SortedSet s) { sortedSet=s; }

    public Comparator comparator() { return sortedSet.comparator(); }
    public Object first() { return sortedSet.first(); }
    public Object last() { return sortedSet.last(); }
    public 	SortedSet subSet(Object from, Object to) { return sortedSet.subSet(from, to); }
    public 	SortedSet headSet(Object o) { return sortedSet.headSet(o); }
    public 	SortedSet tailSet(Object o) { return sortedSet.tailSet(o); }
    
    public void clear() { sortedSet.clear(); }
    public int size() { return sortedSet.size(); }
    public boolean isEmpty() { return sortedSet.isEmpty(); }
    public boolean contains(Object o ) { return sortedSet.contains(o); }
    public boolean containsAll(Collection c) { return containsAll(c); }
    public Iterator iterator() { return sortedSet.iterator(); }
    public Object[] toArray() { return sortedSet.toArray(); }
    public Object[] toArray(Object[] a) { return sortedSet.toArray(a); }
    public boolean remove(Object o ) { return sortedSet.remove(o); }
    public boolean removeAll(Collection c) { return sortedSet.removeAll(c); }
    public boolean retainAll(Collection c) { return sortedSet.retainAll(c); }  
    public boolean add(Object o) { return sortedSet.add(adapt(o)); }

    public boolean addAll(Collection c) {
      if (null==c) return false;
      boolean adding=true;
      Iterator i = c.iterator();
      while (i.hasNext() && adding) adding = add(i.next());
      return adding;
    }
    
    private Adapter adapt(Object o) {
      Adapter a = (Adapter)adapterPrototype.copy();
      try { a.adapt(o); return a; }
      catch (Exception e) {
        throw new IllegalArgumentException("AdapterTreeSet: could not adapt object["+o.toString()+"] using adapter["+adapterPrototype.toString()+"]");
      }
    }
    public Adapter getAdapterPrototype() { return adapterPrototype; }
    public void setAdapterPrototype(AdapterPrototype p) {adapterPrototype=p;}
    
    private AdapterPrototype adapterPrototype=null;
    private SortedSet sortedSet=null;
}
