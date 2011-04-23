/*
 * Created on Oct 9, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package zws.service;

import java.rmi.Remote;

import javax.ejb.EJBHome;
/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface Locator {
  public String getServiceName();
  public Remote createService(EJBHome serviceHome);
}
