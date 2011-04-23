package zws.util.stream;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.AdapterPrototype;

import java.util.*;

public class StreamableCollection implements Collection {
  public StreamableCollection() {}

  public void setAdapter(AdapterPrototype a) { adapterPrototype=a; }

  //stream state info for interprocess-communication
  public void open() { open=true; }
  public void close() { open=false; }
  public boolean isOpen() { return open; }
  public synchronized int addItem(Object o) {
    int i = idx;
    if (add(o)) return i;
    return -1;
  }

  //StreamableIterator utility
  public Object item(int index) { return m.get(""+index); }

  //collection interface
  public synchronized boolean add(Object o) {
    Object item=o;
    if (null!=adapterPrototype) item=adapterPrototype.copy();
    boolean b = c.add(item);
    if (b) m.put(""+(idx++), item);
    notifyAll();
    return b;
  }
  public synchronized boolean addAll (Collection c) {
    boolean added=true;
    Iterator i = c.iterator();
    while (i.hasNext())
      if (added) added=add(i.next()); else return false;
    return added;
  }
  public synchronized void clear() { idx=0; open(); m.clear(); c.clear(); notifyAll(); }
  public synchronized boolean contains (Object o ) { return c.contains(o); }
  public synchronized boolean containsAll (Collection c ) { return c.containsAll(c);}
  public boolean equals(Object o) { return this.equals(o); }
  public synchronized int hashCode() { return c.hashCode(); }
  public synchronized boolean isEmpty() { return c.isEmpty(); }
  public Iterator iterator() { return new StreamableIterator(this); }
  public synchronized boolean remove(Object o) {
    boolean b = c.remove(o);
    if (b) reIndex();
    notifyAll();
    return b;
  }
  public synchronized boolean removeAll(Collection c) {
    boolean b = c.removeAll(c);
    if (b) reIndex();
    return b;
  }
  private void reIndex() {
    Object[] list = c.toArray();
    m.clear();
    for (idx=0; idx<c.size(); m.put(""+(idx),list[idx++]));
  }
  public synchronized boolean retainAll(Collection c) {
    boolean b = c.retainAll(c);
    reIndex();
    notifyAll();
    return b;
  }
  public synchronized int size() { return idx; }
  public synchronized Object[] toArray() { return c.toArray(); }
  public synchronized Object[] toArray(Object[] a) { return c.toArray(a); }

  private boolean open = false;
  private Collection c = new Vector();
  private Map m = new HashMap();
  private int idx=0;
  private AdapterPrototype adapterPrototype;
}
