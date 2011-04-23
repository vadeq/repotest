package zws.op;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.InvalidState;

import java.util.*;

public abstract class PollingThreadOpBase extends ThreadedOpBase implements ThreadedOp {
	//if the thread will be looping/polling infinitely
	//following implementation is necessary to properly implement stop and suspend
	//(override this function if thread is not a poller)
  
  // reordered things to clean up...
  public void executeRun() throws Exception {
	  initializeThreadExecution();
	  while (!inShutdown && getThread() == Thread.currentThread()) {
	    try {
	      synchronized(this) {
          startTimeOfLastRun = new GregorianCalendar();
          executePolling();
          
          if (RUNNING.equals(getRunningState())) Thread.sleep(getSleepInterval());
	        while (threadIsSuspended()) wait();
	      }
	    } catch (InterruptedException ignore) { }
	  }
    System.out.println("Listener is exiting run state");
	}

	protected boolean sleepConditionExists() { return false; }

  protected long getSleepInterval() {
    //run at every minute;
    Calendar c = getStartTimeOfLastRun();
    if (null==c) return 1000;
    Calendar now = new GregorianCalendar();
    while(now.after(c)) c.add(Calendar.SECOND, getRunPeriod());
    return c.getTimeInMillis() - now.getTimeInMillis();
  }

  //default implementation
	protected void initializeThreadExecution() {}
	protected abstract void executePolling() throws Exception;
	
  public synchronized void start() throws InvalidState {
    if (RUNNING.equals(getRunningState())) {
	    {} //System.out.println("Thread is already running.");
	    return;
    }
    if (PAUSED.equals(getRunningState())) stop();
    super.start();
    isSuspended=false;
    {} //System.out.println("Thread is " + getRunningState());
  }

  public synchronized final void stop() {
	  if (null==thread || STOPPED.equals(getRunningState())) {
      {} //System.out.println("Thread is already stopped.");
      return;
    }
    Thread runningThread = thread;
    thread=null;
    runningThread.interrupt();
    isSuspended=false;
    {} //System.out.println("Thread is " + getRunningState());
  }

  public void suspend() throws InvalidState { 
    if (null==thread  || STOPPED.equals(getRunningState())) throw new InvalidState("Thread is stopped and cannot be suspended.");
    if (PAUSED.equals(getRunningState())) {
      {} //System.out.println("Thread is already suspended.");
      return;
    }
    if (RUNNING.equals(getRunningState())) {
      isSuspended=true;
      {} //System.out.println("Thread is " + getRunningState());
    }
  }

  public void resume() throws InvalidState {
    if (RUNNING.equals(getRunningState())) {
      {} //System.out.println("Thread is already running - no need to resume");
      return;
    }
    if (null==thread || STOPPED.equals(getRunningState())) throw new InvalidState("Thread is stopped and cannot be resumed.");
    if (PAUSED.equals(getRunningState())) {
      isSuspended=false;
      getThread().interrupt();
      {} //System.out.println("Thread is " + getRunningState());
    }
  }

  public void shutdown() {
    inShutdown = true;
    getThread().interrupt();
  }
  
  public String getRunningState() {
    if (null==getThread()) return STOPPED;
    if (inShutdown) return STOPPED;
    if (threadIsSuspended() ) return PAUSED;
    return RUNNING;
  }

  public Calendar getStartTimeOfLastRun() { return startTimeOfLastRun; }
  public int getRunPeriod() { return period; }
  public void setRunPeriod(int seconds) { period= seconds; }

  
  public final boolean threadIsSuspended() { return isSuspended; }
  private boolean isSuspended=false;
  
  private static String RUNNING = "running";
  private static String PAUSED= "paused";
  private static String STOPPED = "stopped";
  private boolean inShutdown = false;
  protected Calendar startTimeOfLastRun=null;
  private int period = 25; //seconds
}
