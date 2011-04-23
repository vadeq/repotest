package zws.util;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.Iterator;

public interface PrototypeIterator extends Iterator {
  public boolean hasNext();
  public void remove();
  public Object copyNext();
  public Object shallowCopyNext();
  public Object deepCopyNext();
}
