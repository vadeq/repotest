package zws.hi.event.processor; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Dec 12, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

import zws.EventHandlingClient;

import java.util.Collection;
import java.util.Vector;

import com.zws.hi.hiList;

public class hiEventProcessor extends hiList{

	public String getRunningState() {
	  try { return getEventClient().getRunningState(); }
	  catch(Exception e) { e.printStackTrace(); return null; }
	}

	public String getHandlingState() {
	  try { return getEventClient().getEventHandlingState(); }
	  catch(Exception e) { e.printStackTrace(); return null; }
	}
	
	public String ignoreEvents() {
    try { 
      getEventClient().ignoreEvents();
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
	
	public String handleEvents() {
    try { 
      getEventClient().handleEvents();
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

	public String startProcessor() {
    try { 
      getEventClient().start();
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
	
	public String stopProcessor() {
    try { 
      getEventClient().stop();
      return ctrlOK;
    }
	  catch(Exception e) { e.printStackTrace(); return ctrlERROR; }
	}
	
	public String pauseProcessor() {
    try { 
      getEventClient().pause();
      return ctrlOK;
    }
	  catch(Exception e) { e.printStackTrace(); return ctrlERROR; }
	}
	
	public String resumeProcessor() {
    try { 
      getEventClient().resume();
      return ctrlOK;
    }
	  catch(Exception e) { e.printStackTrace(); return ctrlERROR; }
	}
  public Collection getHistoryLog() { 
    try { 
      return getEventClient().getHistoryLog();
    }
	  catch(Exception e) { e.printStackTrace(); return null; }
	}  
  public String getHistoryLogDuration() { 
    try { 
      return ""+getEventClient().getHistoryLogDuration();
    }
	  catch(Exception e) { e.printStackTrace(); return null; }
	}
  public void setHistoryLogDuration(String hours) { 
    try { 
      getEventClient().setHistoryLogDuration(Integer.parseInt(hours));
    }
	  catch(Exception e) { e.printStackTrace(); return; }
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

  public EventHandlingClient getEventClient() { //+++parameterize
     return EventHandlingClient.getClient(selectedServer);
  }

  public String getSelectedServer() { return selectedServer; }
  public void setSelectedServer(String s) { selectedServer=s; }

  private String selectedServer = null;

}
