package com.zws.functor;

public abstract class RunnableFunctor extends Functor implements Runnable {

  public void execute() { if (null!= thread) thread.start(); else run(); }

  public void setThread(Thread t) { thread = t; }
  public Thread getThread() { return thread; }

  private Thread thread;

}
