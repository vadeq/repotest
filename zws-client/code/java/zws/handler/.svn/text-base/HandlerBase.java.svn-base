package zws.handler; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Alert;
import zws.util.StringUtil;
import zws.util.RoutedEventBase;

import java.io.Serializable;

import javax.mail.MessagingException;

abstract public class HandlerBase implements Serializable, Handler {
  public abstract Class getEventClass();
  public String getEventType() { return getEventType(getEventClass()); }
  public abstract boolean handles(RoutedEventBase event);
  public abstract void handle(RoutedEventBase event);
 
  public void initRegistration(HandlerRegistry r) { setRegistry(r); }
  public void closeRegistration() { }
  
  public final void setRegistry(HandlerRegistry r) { registry=r; }
  public final HandlerRegistry getRegistry() { return registry; }

  public final void unregister() {
    if (null==getRegistry()) return;
    getRegistry().unregister(this);
  }

  protected String getEventType(Class eventClass) {
	  String s = eventClass.getName();;
	  if (s.startsWith("zws.")) s = s.substring(4);
	  String name = s.substring(s.lastIndexOf('.')+1);
	  s = s.substring(0, s.lastIndexOf('.')+1);
	  name = StringUtil.javaName2xmlAttribute(name);
	  s += name;
	  return s;
	}

  public void alert(String subject, String message) throws MessagingException  {
    Alert.notify(subject, message);
  }

  private HandlerRegistry registry=null;
  private String fromEmail = null;
  private String hostServer = null;
  private String[] alertRecipients;
  
  //public boolean isPermanentHandler() { return true; }
  //public boolean ignoreFurthurHandlers() { return false; }
}
