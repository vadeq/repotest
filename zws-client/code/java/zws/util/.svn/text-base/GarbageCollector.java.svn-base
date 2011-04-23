package zws.util;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.OpBase;

public class GarbageCollector extends OpBase {
  public void execute() { collect(); }

  public static void collect(){
    {} //System.out.println("zws: Finalizing unused references.");
    System.runFinalization();
    {} //System.out.println("zws: Garbage Collecting.");
    System.gc();
  }
}