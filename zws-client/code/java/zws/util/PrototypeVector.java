package zws.util;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.UnsupportedOperation;

import java.util.*;

public class PrototypeVector extends Vector implements PrototypeCollection {

  public Collection copyPrototypes(){
    Collection c = new Vector();
    PrototypeIterator i = prototypeIterator();
    while (i.hasNext()) c.add(i.copyNext());
    return c;
  }
  public Collection shallowCopyPrototypes(){
    Collection c = new Vector();
    PrototypeIterator i = prototypeIterator();
    while (i.hasNext()) c.add(i.shallowCopyNext());
    return c;
  }
  public Collection deepCopyPrototypes(){
    Collection c = new Vector();
    PrototypeIterator i = prototypeIterator();
    while (i.hasNext()) c.add(i.deepCopyNext());
    return c;
  }
  public PrototypeIterator prototypeIterator() { return new PrototypeVectorIterator(this); }

  private class PrototypeVectorIterator implements PrototypeIterator {
    public PrototypeVectorIterator (PrototypeCollection c) { iterator = c.iterator(); }
    public boolean hasNext() { return iterator.hasNext(); }
    public Object next() { throw new UnsupportedOperation("next()", "use copyNext()"); }
    public void remove() { iterator.remove(); }
    public Object copyNext() { Prototype f = (Prototype)iterator.next(); return f.copy(); }
    public Object shallowCopyNext() { Prototype f = (Prototype)iterator.next(); return f.shallowCopy(); }
    public Object deepCopyNext() { Prototype f = (Prototype)iterator.next(); return f.deepCopy(); }

    private Iterator iterator;
  }
}
