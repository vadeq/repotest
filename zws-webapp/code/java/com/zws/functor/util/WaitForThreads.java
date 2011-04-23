package com.zws.functor.util;

import com.zws.functor.RunnableFunctor;
import com.zws.util.stream.StreamableCollection;

import java.util.Collection;
import java.util.Iterator;

public class WaitForThreads extends RunnableFunctor {
  public WaitForThreads() {}

  public void bindThreads(Collection c) { threads = c; }
  public void bindStream(StreamableCollection s) { stream = s; }

  public void setSleepInterval(long l) { sleepInterval = l; }
  public long getSleepInterval() { return sleepInterval; }

  public Collection getThreads() { return threads; }
  public void setThreads(Collection c) { threads =c; }
  public void run() {
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
    stream.close();
// profiler.stop("search", "wait-for-agents");
  }

  private Collection threads=null; //collection of agent threads
  private StreamableCollection stream=null;
  private long sleepInterval=25;
}
