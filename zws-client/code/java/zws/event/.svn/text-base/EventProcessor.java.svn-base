package zws.event; /*
* DesignState - Design Compression
* Technology. @author: Arbind Thakur Created
* on Jan 26, 2006 @version: 1.0 Copywrite (c)
* 2002-2005 Zero Wait-State Inc. All rights
* reserved
*/
import zws.util.RoutedEventBase; 
import zws.op.PollingThreadOpBase;
import zws.handler.*;


import java.util.*;

public class EventProcessor extends PollingThreadOpBase {

	protected void executePolling() { /*ignored */ }

	public void executeRun() throws Exception {
	  while (getThread()==Thread.currentThread()) {
	    try { synchronized(this) { while (threadIsSuspended()) wait(); }}
	    catch (InterruptedException ignore) { }
      try { synchronized(this) { while (firedEvents.isEmpty() && null==activeEvent) wait(); } } 
      catch (InterruptedException ignore) { }
	    if (getThread()!=Thread.currentThread()) break;
	    processActiveEvent();
	  }
	}

  private void handle(RoutedEventBase event, Collection handlers) {
    Handler h;
    Iterator i = handlers.iterator();
  	 while(i.hasNext()) {
  	   h = (Handler)i.next();
  	   if (h.handles(event)) {
  	     {} //System.out.println(event.getEventName() + "["+event.getEventID()+"] will be handeled");
  	     h.handle(event);
  	   }
  	   else {
   	     {} //System.out.println(event.getEventName() + "["+event.getEventID()+"] skipped by handler");  	     
  	   }
  	 }
  }

	private void processActiveEvent() {
	  errorMessage =null;
    activeEvent = (RoutedEventBase)firedEvents.get(0);
 	  Collection handlers =  registry.getHandlers(activeEvent);
 	  handle(activeEvent, handlers);
    activeEvent=null;
    firedEvents.remove(0);
	}

/*
	  activeHandlers = new ArrayList();
 	  Iterator i = handlers.iterator();
 	  while(i.hasNext()) {
 	    h = (Handler) i.next();
 	    try {  if (h.handles(activeEvent)) activeHandlers.add(h); }
 	    catch (EventHandlingError e) { e.printStackTrace(); }
 	  }
 	  {} //System.out.println(handlers.size() + " handlers found for " + activeEvent.getEventType() +"["+activeHandlers.size()+"] active");
    while (idx < activeHandlers.size()) {
      h = (Handler)activeHandlers.get(idx);
      if (processEvents) { 
        try { h.handle(activeEvent); }
  	    catch (EventHandlingError e) { e.printStackTrace(); }
  	  }
      else {} //System.out.println("Events are being ignored!");
      idx++;
    }
    activeEvent=null;
    firedEvents.remove(0);
	}
*/
	
  public synchronized void fire(Collection events) {
	  {} //System.out.println("firing "+events.size()+" events");
	  firedEvents.addAll(events);
	  notify();
	}
  
  public synchronized void fire(RoutedEventBase ev) {
    {} //System.out.println("firing event: " + ev);
    firedEvents.add(ev);
    notify();
  }

  public void register(Handler h) { registry.register(h); }
  public void unregister(Handler h) { registry.unregister(h); }

  public String getEventHandlingState() { 
    if (processEvents) return PROCESSING_EVENTS;
    else return IGNORING_EVENTS;
  }
  public void ignoreEvents() { processEvents = false; }
  public void handleEvents() { processEvents = true; }
  
  private List firedEvents = Collections.synchronizedList(new Stack());
  private static HandlerRegistry registry= new HandlerRegistry();

  private RoutedEventBase activeEvent=null;
  private List activeHandlers=null;
  private String errorMessage = null;
  private boolean processEvents=true;
  
  private static String PROCESSING_EVENTS = "processing";
  private static String IGNORING_EVENTS = "ignored";
}
