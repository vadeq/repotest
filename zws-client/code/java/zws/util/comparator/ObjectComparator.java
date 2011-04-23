package zws.util.comparator;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 7, 2004, 6:27 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.Serializable;
import java.util.Comparator;

public class ObjectComparator implements Serializable, Comparator {
  public int compare(Object x0, Object x1) { return x0.toString().compareTo(x1.toString()); }
}
