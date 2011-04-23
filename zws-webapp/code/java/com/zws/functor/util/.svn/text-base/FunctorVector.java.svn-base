package com.zws.functor.util;

import java.util.Collection;
import java.util.Vector;

public class FunctorVector extends Vector {
  public Collection copyCollection(){
    Collection c = new Vector();
    FunctorIterator i = copyIterator();
    while (i.hasNext()) c.add(i.copyNext());
    return c;
  }
  public FunctorIterator copyIterator() { return new FunctorIterator(this); }
}
