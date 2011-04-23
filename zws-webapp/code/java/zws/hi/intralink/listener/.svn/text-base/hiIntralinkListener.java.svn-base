package zws.hi.intralink.listener; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Dec 12, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

import com.zws.hi.hiList;

import java.util.Collection;
import java.util.Vector;

public class hiIntralinkListener extends hiList{

	public String getRunningState() {
	  try { return getIEE().getEventListenerRunningState(); }
	  catch(Exception e) { e.printStackTrace(); return null; }
	}

	public String getFiringState() {
	  try { return getIEE().getEventListenerEventFiringState(); }
	  catch(Exception e) { e.printStackTrace(); return null; }
	}
	
	
	public String ignoreEvents() {
    try { 
      getIEE().ignoreEventListenerEvents();
      return ctrlOK;
    }
	  catch(zws.exception.InvalidState e) {
	    logFormError("err.invalid.state", e.getMessage());
	    return ctrlERROR; 
	  }
	  catch(Exception e) {
	    logFormError("system.err", e.getMessage());
	    e.printStackTrace();
	    return ctrlERROR; 
	  }
	}
	
	public String fireEvents() {
    try { 
      getIEE().fireEventListenerEvents();
      return ctrlOK;
    }
	  catch(zws.exception.InvalidState e) {
	    logFormError("err.invalid.state", e.getMessage());
	    return ctrlERROR; 
	  }
	  catch(Exception e) {
	    logFormError("system.err", e.getMessage());
	    e.printStackTrace();
	    return ctrlERROR; 
	  }
	}

	public String startListener() {
    try { 
      getIEE().startEventListener();
      return ctrlOK;
    }
	  catch(zws.exception.InvalidState e) {
	    logFormError("err.invalid.state", e.getMessage());
	    return ctrlERROR; 
	  }
	  catch(Exception e) {
	    logFormError("system.err", e.getMessage());
	    e.printStackTrace();
	    return ctrlERROR; 
	  }
	}
	
	public String stopListener() {
    try { 
      getIEE().stopEventListener();
      return ctrlOK;
    }
	  catch(Exception e) { e.printStackTrace(); return ctrlERROR; }
	}
	
	public String pauseListener() {
    try { 
      getIEE().pauseEventListener();
      return ctrlOK;
    }
	  catch(Exception e) { e.printStackTrace(); return ctrlERROR; }
	}
	
	public String resumeListener() {
    try { 
      getIEE().resumeEventListener();
      return ctrlOK;
    }
	  catch(Exception e) { e.printStackTrace(); return ctrlERROR; }
	}
  public Collection getHistoryLog() { 
    try { 
      return getIEE().getEventListenerHistoryLog();
    }
	  catch(Exception e) { e.printStackTrace(); return null; }
	}  
  public String getHistoryLogDuration() { 
    try { 
      return ""+getIEE().getEventListenerHistoryLogDuration();
    }
	  catch(Exception e) { e.printStackTrace(); return null; }
	}
  public void setHistoryLogDuration(String hours) { 
    try { 
      getIEE().setEventListenerHistoryLogDuration(Integer.parseInt(hours));
    }
	  catch(Exception e) { e.printStackTrace(); return; }
	}
  
  public String getRunPeriod() { 
    try { 
      return ""+getIEE().getEventListenerRunPeriod();
    }
	  catch(Exception e) { e.printStackTrace(); return null; }
	}
  
  public void setRunPeriod(String seconds) {
    try { 
      getIEE().setEventListenerRunPeriod(Integer.parseInt(seconds));
    }
	  catch(Exception e) { e.printStackTrace(); return; }
	}
  
  public Collection getTargetQueueNodes() {
    try { 
      return getIEE().getEventListenerTargetQueueNodes();
    }
	  catch(Exception e) { e.printStackTrace(); return null; }
  }

  public String addTargetQueueNode() {
    String serverNode = getID();
    try {
      getIEE().addEventListenerTargetQueueNode(serverNode);
      return ctrlOK;
    }
	  catch(Exception e) { e.printStackTrace(); return ctrlERROR; }
  }

  public String removeTargetQueueNode() {
    String serverNode = getID();
    try {
      getIEE().removeEventListenerTargetQueueNode(serverNode);
      return ctrlOK;
    }
	  catch(Exception e) { e.printStackTrace(); return ctrlERROR; }
  }

  public Collection getRunPeriodOptions() {
	  Collection c= new Vector();
	  c.add("10");
	  c.add("20");
	  c.add("25");
	  c.add("30");
	  c.add("60");
	  c.add("300");
	  c.add("600");
	  return c;
	}

  public Collection getHistoryLogDurationOptions() {
    Collection c= new Vector();
    c.add("12");
    c.add("24");
    c.add("48");
    c.add("72");
    c.add("96");
    c.add("120");
    c.add("144");
    c.add("168");
    c.add("240");
    c.add("720");
    return c;
  }

  public void setSelectedIEEServer(String s) throws Exception { getIEE().setSelectedServer(s); }
  public String getSelectedIEEServer() throws Exception { return getIEE().getSelectedServer(); }

  public Collection getIEERepositoryList () throws Exception { return getIEE().getRepositoryList(); }
  public void setSelectedIEERepository(String s) throws Exception { getIEE().setSelectedRepository(s); }
  public String getSelectedIEERepository() throws Exception { return getIEE().getSelectedRepository();}
}
