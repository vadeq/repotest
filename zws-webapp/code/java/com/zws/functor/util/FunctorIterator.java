package com.zws.functor.util;

import com.zws.functor.Functor;

import java.util.*;

public class FunctorIterator implements Iterator {
  public FunctorIterator (Collection c) { iterator = c.iterator(); }

  public boolean hasNext() { return iterator.hasNext(); }
  public Object next() { throw new NoSuchElementException("When using FunctorIterator, call copyNext() method instead of next"); }
  public void remove() { iterator.remove(); }
  public Functor copyNext() { Functor f = (Functor)iterator.next(); return f.copy(); }

  private Iterator iterator;
}
