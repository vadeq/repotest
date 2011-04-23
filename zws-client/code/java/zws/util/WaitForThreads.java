package zws.util;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.ThreadedOpBase;

import java.util.*;

public class WaitForThreads extends ThreadedOpBase {
  public WaitForThreads() {}

  public void bindThreads(Collection c) { threads = c; }

  public void setSleepInterval(long l) { sleepInterval = l; }
  public long getSleepInterval() { return sleepInterval; }

  public Collection getThreads() { return threads; }
  public void addThread(Thread t) { threads.add(t); }
  public void addThreads(Collection c) { threads.addAll(c); }
  public void executeRun() {
//Profiler profiler = new Profiler();
    boolean threadsFinished=false;
    boolean stillAlive = false;
    Thread thread;
//profiler.start("search", "wait-for-agents");
    while (!threadsFinished) {
      try { Thread.sleep(sleepInterval); }
      catch (Exception e) {}
      stillAlive = false;
      Iterator i = threads.iterator();
      while (i.hasNext()) {
        thread = (Thread)i.next();
        if (thread.isAlive()) { stillAlive = true; break; }
      }
      if (!stillAlive) threadsFinished = true;
    }
// profiler.stop("search", "wait-for-agents");
  }
  public DomainContext getContext(){ return context; }
  public void setContext(DomainContext c) { context=c; } 
  
  private DomainContext context=null;
  private Collection threads=new Vector(); //collection of threads
  private long sleepInterval=25;
}
