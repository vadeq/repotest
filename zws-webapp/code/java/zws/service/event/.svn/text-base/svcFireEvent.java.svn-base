package zws.service.event; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Dec 12, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */


import zws.event.EventMaker;
import zws.util.RoutedEventBase;

import com.zws.hi.hiItem;
import com.zws.service.account.InvalidPasswordException;
import com.zws.service.account.UserNotFoundException;


public class svcFireEvent extends hiItem {

	public String fire() {
    try { login(getUsername(), getPassword()); }
    catch(UserNotFoundException e) {return ctrlERR_EVENT_NO_USER;}
    catch (InvalidPasswordException e ) {return ctrlERR_EVENT_BAD_LOGIN;}
    catch (Exception e) { setErrorMessage(e.getMessage()); return ctrlERR_EVENT_ERROR;}
	  {} //System.out.println(getEventFired());
	  RoutedEventBase ev = null;
	  try { ev = EventMaker.materializeXML(getEventFired()); }
	  catch (Exception e) {
			{} //System.out.println(e.getMessage());
			return ctrlERR_EVENT_NOT_RECOGNIZED;
		}
		//try { ev.record(); }
		//catch (Exception e) { {} //System.out.println("could not record event to log" + e.getMessage()); }
    fire(ev);
	  logout(getUsername());
	  return ctrlOK;
	}

	private void fire(RoutedEventBase ev) {
	  {} //System.out.println("Event CLASSNAME=" + ev.getClass().getName());
    //queue event..
	}
 
	public String getEventFired() { return eventFired; }
	public void setEventFired(String s) { eventFired=s; }

  public String getUsername() { return username; }
  public void setUsername(String s) { username=s; }
  public String getPassword() { return password; }
  public void setPassword(String s) { password=s; }

  public String getErrorMessage() { return errorMessage; }
  public void setErrorMessage(String s) { errorMessage=s; }

  private String eventFired=null;
  private String username=null;
  private String password=null;
  private String errorMessage=null;
}
