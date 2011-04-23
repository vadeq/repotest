/*
 * Created on Oct 15, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.zws.functor.processor;

/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import com.zws.application.Constants;
import com.zws.application.Log;
import com.zws.functor.processor.action.Action;
import com.zws.functor.processor.action.log.ActionLog;
import com.zws.functor.util.*;

import java.util.*;

public class Processor extends DataProcessor {

  private static int runCount=0;
	public void process() throws Exception {
    setLogFileName("process-" + getName()+"-"+runCount+++".log");
    logTime("starting process " + getName());
    long start = System.currentTimeMillis();
    {} //System.out.println("starting process " + getName()  + " at " + start);
		processActions();
    long finish = System.currentTimeMillis();
    long duration = finish - start;
    {} //System.out.println("finished process " + getName()  + " at " + finish + ". [duration = "+duration+" ms]");
    GarbageCollector gc = new GarbageCollector();
    gc.execute();
    logTime("finished process " + getName());
    dumpActionLog();
    Log.close(getLogFileName());
	}

  public void log(String s) throws Exception { Log.write(getLogFileName(), s); }	
  public void logTime(String msg) throws Exception { Log.writeTime(getLogFileName(), msg); }	

  public void dumpActionLog() throws Exception {
    Iterator i = getActionLog().iterator();
    while (i.hasNext()) Log.write(getLogFileName(), (String)i.next());
    //while (i.hasNext()) {} //System.out.println((String)i.next());
  }

	public void processActions(){
		Action action = null;
		ActionLog actionLog = new ActionLog();
		FunctorIterator a = getActions().copyIterator();
		boolean cancelActions=false;
		while(a.hasNext() && !cancelActions){
			action = (Action)a.copyNext();
			action.setActionLog(actionLog);
			try {
				action.execute();
				actionLog=action.getActionLog();
				if (Constants.PROCESS_TIMED_OUT==action.getExitCode()){
					{} //System.out.println("***Action timed out  :" + action.getClass().getName());
					if (getHaltOnTimeout())
						return;
					if (!getContinueOnTimeout())
						break;
					}
				} catch (Exception e) {e.printStackTrace(); actionLog.log("Exception["+e.getClass().getName()+"]: " + e.getMessage()); cancelActions=true;}
		}
		getActionLog().addAll(actionLog.getLog());
	}
	

	/* (non-Javadoc)
	 * @see com.zws.functor.Functor#execute()
	 */
	public void execute() throws Exception {
		try { process(); }
		catch (Exception e) {
		  setException(e);
		  e.printStackTrace();
		}
	}
	
	public boolean getHaltOnTimeout() { return haltOnTimeout; }
	public void setHaltOnTimeout(boolean b) { haltOnTimeout=b; }
	public boolean getContinueOnTimeout() { return continueOnTimeout; }
	public void setContinueOnTimeout(boolean b) { continueOnTimeout=b; }

	public Collection getActionLog() { return actionLog; }
	public FunctorVector getActions() { return actions; }
	public void addAction(Action a ) { getActions().add(a); }
  
  public String getLogFileName() { return logFileName; }
  public void setLogFileName(String s) { logFileName=s; }
  

	private FunctorVector actions=new FunctorVector();
	private Collection actionLog = new Vector();
	private boolean haltOnTimeout = false;
	private boolean continueOnTimeout = false;
  private String logFileName = "process.log";
}
