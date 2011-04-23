package zws.context;


import zws.Server;
import zws.application.Configurator;
import zws.application.Names;
import zws.qx.queue.DaemonQueue;
import zws.recorder.ExecutionRecord;
import zws.repository.ilink3.Ilink3EventListener;
import zws.service.event.watcher.EventWatcherSvc;
import zws.service.recorder.qx.RecorderSvc;

import java.rmi.RemoteException;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public final class BootstrapContextListener
    implements ServletContextListener {

  private ServletContext context = null;
  private ArrayList listeners = new ArrayList();
  
  public BootstrapContextListener() {}

  //This method is invoked when the Web Application
  //has been removed and is no longer able to accept
  //requests

  public void contextDestroyed(ServletContextEvent event)
  {
    try {
      DaemonQueue.getInstance().stop();
    
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    
    Ilink3EventListener listener;
    for (int i=0; i<listeners.size(); i++) {
      try {
        listener = (Ilink3EventListener) listeners.get(i);
        listener.shutdown();
        System.out.println("Event Listner " + listener.getName() + " set to stop.");
      } catch (Exception e) {
        System.out.println("error while stopping event listner");
      }
    }
    context = null;
  }

  //This method is invoked when the Web Application
  //is ready to service requests

  public void contextInitialized(ServletContextEvent event)
  {
    context = event.getServletContext();
    try { 
      Configurator.load();
      archiveStaleProcesses();
      DaemonQueue.getInstance().reInstantiate();
      }
    catch (Exception e) {
      e.printStackTrace();
    }

    // start event listeners
    Collection eventList = EventWatcherSvc.getPrototypeNames();
    Iterator itr = eventList.iterator();
    String listnerName = null;
    String state = null;
    while(itr.hasNext()) {
      listnerName = (String) itr.next();
      try {
      Ilink3EventListener l = EventWatcherSvc.find(listnerName);
      if(null == l) continue;
      state = l.getRunningState();
      if(!"running".equalsIgnoreCase(state)) {
        l.start();
        listeners.add(l);
        System.out.println("Event Listner " + l.getName() + " started.");
      } else {
        System.out.println("Event Listner " + l.getName() + " already started.");
      }
      } catch (Exception e) {
        System.out.println("error while starting up event listner");
      }
    }
    System.out.println("DesignState "+ Server.getName() + " Loaded: " + Server.getDescription());
  }
  
  private void archiveStaleProcesses() {
	  ExecutionRecord record = null;
	  try {
		Collection publishingRecords = RecorderSvc.getRecordingsByStatus(Names.PEN_QUEUE_NAMESPACE, Names.STATUS_PUBLISHING);
		if(null == publishingRecords || publishingRecords.size() <1) return;
		Iterator itr = publishingRecords.iterator();
		while(itr.hasNext()){
			record = (ExecutionRecord) itr.next();
			RecorderSvc.recordEndTime(record.getID(), Names.STATUS_STALE);
			System.out.println("Stale process [ "+ record.getID() + "] found.");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	  
  }
}

