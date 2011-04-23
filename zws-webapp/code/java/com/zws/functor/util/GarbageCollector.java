package com.zws.functor.util;

import com.zws.functor.Functor;

public class GarbageCollector extends Functor {
  public void execute() {
    {} //System.out.println("Garbage Collecting...");
    System.runFinalization();
    System.gc();
    {} //System.out.println("..Done Garbage Collecting...");
  }
}