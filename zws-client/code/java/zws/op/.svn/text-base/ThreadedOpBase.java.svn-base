package zws.op;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.InvalidState;
import zws.log.failure.Failure;

public abstract class ThreadedOpBase extends ListOpBase implements ThreadedOp {
  public abstract void executeRun() throws Exception;

  public final void run() {
    try {executeRun();}  //++change to catch Throwable instead of Exception
    catch (Exception e) { 
      e.printStackTrace(); 
      try{ log(new Failure(e)); } 
      catch (Throwable a){} 
    }
    catch (Throwable t) { 
      t.printStackTrace(); 
    }
  }

  public final void execute() throws Exception { clearThread(); start(); }

  public synchronized void start() throws InvalidState {
    if (null!=thread) return; //already started;
    thread = new Thread(this);
    getThread().start();
  }

  public final synchronized void clearThread() throws Exception {
    if (null==thread) return; //already stopped;
    Thread t = thread;
    thread=null;
    t.interrupt();    
   }

  public final Thread getThread() { return thread; }

  protected volatile Thread thread;
}
