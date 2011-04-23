package zws.handler.datasource; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.IntralinkAccess;
import zws.util.RoutedEventBase;
import zws.event.design.Renamed;
import zws.handler.*;
import zws.security.Authentication;


import java.io.Serializable;


public class testRenameHandler extends HandlerBase implements Serializable{
  public Class getEventClass() { return Renamed.class; }

  public boolean handles(RoutedEventBase event) {
    Renamed ev = (Renamed)event;
    try {
	    if (getID().getUsername().equals(ev.getAuthor())) {
	      {} //System.out.println("Skipping rename by author " + ev.getAuthor());
	      return false;
	    }
	    return true;
    }
    catch(Exception e) {
      e.printStackTrace();
      return false; 
    }
  }

  public void handle(RoutedEventBase event) {
    Renamed ev = (Renamed)event;
    try {
      IntralinkAccess ilink = IntralinkAccess.getAccess();
      String node = ev.getServerName();
      String rep = ev.getDatasourceName();
      String oldName = ev.getOldName();
      String newName = ev.getNewName();
      ilink.rename(node, rep, newName, oldName, getID());
    }
    catch(Exception e) {
      e.printStackTrace();   
    }
  }
  
  private Authentication getID() throws Exception {
    if (id==null) id = new Authentication("designstate", "zero0");
    return id;
  }

  private Authentication id=null;
}