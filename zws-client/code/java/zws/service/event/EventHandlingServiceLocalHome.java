package zws.service.event; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface EventHandlingServiceLocalHome extends EJBLocalHome {
  public EventHandlingServiceLocalHome create() throws CreateException;
}