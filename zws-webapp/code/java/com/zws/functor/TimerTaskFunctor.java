package com.zws.functor;

import java.util.TimerTask;
//To create a timertask functor:
// extend this class.
// implement execute.
// getTask() returns a TimerTask (anonymous class) that can be scheduled
public abstract class TimerTaskFunctor extends Functor implements Cloneable {
  public final AnonymousTimerTask getTask() {
    if (null==task) task = new AnonymousTimerTask(this);
    return task;
  }

  public class AnonymousTimerTask extends TimerTask {
    public AnonymousTimerTask(TimerTaskFunctor f) { functor = f; }
    public void run(){
      exception = null;
      try { functor.execute();}
      catch (Exception e) { exception = e; }
    }
    public Exception getException() { return exception; }
    private TimerTaskFunctor functor;
    private Exception exception;
  }

  AnonymousTimerTask task=null;
}
