package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 6, 2004, 4:03 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.*;

import java.util.*;

public class StringSequence implements Comparator {
  
  public Iterator iterator() { return sequence.iterator(); }
  public void clear() { sequence.clear(); }
  public void add(String s) { sequence.add(s); }

  public boolean contains(String s) { return sequence.contains(s); }
  
  public String itemBefore(String s) throws NameNotFound, StartOfList {
    int idx = sequence.indexOf(s);
    if (-1==idx) throw new NameNotFound(s);
    try { return (String)sequence.get(idx-1); }
    catch (IndexOutOfBoundsException e) { throw new StartOfList(s);}
  }
  
  public String itemAfter(String s) throws NameNotFound, EndOfList {
    int idx = sequence.indexOf(s);
    if (-1==idx) throw new NameNotFound(s);
    try { return (String)sequence.get(idx+1); }
    catch (IndexOutOfBoundsException e) { throw new EndOfList(s);}
  }
  
  public int compare (Object s0, Object s1) {
    try { return compare((String)s0, (String)s1); }
    catch (NameNotFound e) { throw new RuntimeException(e.getMessage()); }
  }

  public int compare (String s0, String s1) throws NameNotFound {
    int idx0, idx1;
    idx0 = sequence.indexOf(s0);
    idx1 = sequence.indexOf(s1);
    if (-1==idx0) throw new NameNotFound(s0);
    if (-1==idx1) throw new NameNotFound(s0);
    if (idx0<idx1) return -1;
    if (idx0==idx1) return 0;
    return 1;
  }
  
  public Collection getValues() { return sequence; }
  
  LinkedList sequence = new LinkedList();
}
