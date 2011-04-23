package zws.service.synchronization; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.rmi.Remote;

import javax.ejb.EJBObject;

public interface SynchronizationServiceRemote extends EJBObject, Remote, SynchronizationService { }