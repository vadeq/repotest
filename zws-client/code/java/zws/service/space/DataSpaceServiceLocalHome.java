package zws.service.space; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface DataSpaceServiceLocalHome extends EJBLocalHome {
  public DataSpaceServiceLocalHome create() throws CreateException;
}