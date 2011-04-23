package zws.attribute;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 6, 2004, 5:02 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.*;
import zws.util.StringSequence;
import zws.util.StringValue;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

public class Enumeration implements Serializable {
  public void clear() { enumerations.clear(); }
  public void add(String s) { enumerations.add(s); }
  public void add(StringValue s) { enumerations.add(s.getValue()); }
  public boolean contains(String s) { return enumerations.contains(s); }
  public int compare(String s0, String s1) throws NameNotFound { return enumerations.compare(s0, s1); }    
  public String itemBefore(String s) throws NameNotFound, StartOfList { return enumerations.itemBefore(s); }    
  public String itemAfter(String s) throws NameNotFound, EndOfList { return enumerations.itemAfter(s); }    
  public Iterator iterator() { return enumerations.iterator(); }
  public Collection getValues() { return enumerations.getValues(); }

  private StringSequence enumerations = new StringSequence();
}
