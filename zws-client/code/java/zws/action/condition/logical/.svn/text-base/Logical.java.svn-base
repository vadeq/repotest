package zws.action.condition.logical;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 19, 2004, 1:54 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.action.condition.Conditional;

import java.util.Collection;
import java.util.Vector;

public abstract class Logical extends Conditional {
  public abstract boolean isTrue() throws Exception;

  public void add(Conditional op) { conditions.add(op); }
  protected Collection conditions = new Vector();
}
