package zws.action.condition.logical;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 19, 2004, 1:54 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.action.condition.Conditional;

import java.util.Iterator;

public class XNOR extends Logical {
  public boolean isTrue() throws Exception {
    Iterator i = conditions.iterator();
    Conditional op;
    boolean result;
    int count=0;
    while (i.hasNext()) {
      op = (Conditional) i.next();
      op.setContext(getContext());
      result = op.isTrue();
      if (result) count++;
      if (count>1) return true;
    }
    return !(1==count);
  }
}
