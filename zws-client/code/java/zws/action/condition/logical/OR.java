package zws.action.condition.logical;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 19, 2004, 1:54 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.action.condition.Conditional;

import java.util.Iterator;

public class OR extends Logical {
  public boolean isTrue() throws Exception {
    Iterator i = conditions.iterator();
    Conditional op;
    while (i.hasNext()) {
      op = (Conditional) i.next();
      op.setContext(getContext());
      if(op.isTrue()) return true;
    }
    return false;
  }
}
