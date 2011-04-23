package zws.op;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.TimerTask;

//To create a timertask functor:
// extend this class.
// implement execute.
// getTask() returns a TimerTask (anonymous class) that can be scheduled
public abstract class TimerTaskOp extends ListOpBase {

  public final AnonymousTimerTask getTask() {
    if (null==task) task = new AnonymousTimerTask(this);
    return task;
  }

  public class AnonymousTimerTask extends TimerTask {
    public AnonymousTimerTask(TimerTaskOp f) { op = f; }
    public void run(){
      exception = null;
      try { op.execute();}
      catch (Exception e) { e.printStackTrace(); exception = e; }
    }
    public Exception getException() { return exception; }
    private TimerTaskOp op;
    private Exception exception;
  }

  AnonymousTimerTask task=null;
}
