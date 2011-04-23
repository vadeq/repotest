package zws.util; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class AutoIncrement {
  private static long uniqueVMID=0;
  public static long getNext() { return uniqueVMID++; }
}