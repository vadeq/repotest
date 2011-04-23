package zws.handler.datasource; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.IntralinkAccess;
import zws.util.RoutedEventBase;
import zws.event.design.Moved;
import zws.handler.*;
import zws.security.Authentication;


import java.io.Serializable;


public class testMoveHandler extends HandlerBase implements Serializable{
  public Class getEventClass() { return Moved.class; }

  public boolean handles(RoutedEventBase event) {
    Moved ev = (Moved)event;
    try {
	    if (getID().getUsername().equals(ev.getAuthor())) {
	      {} //System.out.println("Skipping move by author " + ev.getAuthor());
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
    Moved ev = (Moved)event;
    try {
      IntralinkAccess ilink = IntralinkAccess.getAccess();
      String node = ev.getServerName();
      String rep = ev.getDatasourceName();
      String name = ev.getName();
      String folder = "Root Folder/zwsData";
      ilink.move(node, rep, name, folder, getID());
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