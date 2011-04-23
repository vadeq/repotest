package com.zws.util;


public class AutoIncrement {
  private static long uniqueVMID=0;
  public static long getNext() { return uniqueVMID++; }
}