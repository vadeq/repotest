/*
 * DesignState - Design Compression Technology @author: ptoleti @version: 1.0 Created on Oct 30, 2007 4:19:17 PM Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */
package zws.service.finder.qx;

import zws.exception.DuplicateName;
import zws.exception.InvalidConfiguration;
import zws.exception.NameNotFound;
import zws.exception.NotConnected;
import zws.qx.service.ServiceRecord;
import zws.service.PrototypeSvc;
import zws.util.Named;
import zws.util.PrototypeCollection;

import java.util.Collection;

public class ServiceRecordSvc {
  
  public static String NAMESPACE = "zws-service-record"; 
  public static String getNamespace() { return NAMESPACE; }
  
  public static ServiceRecord find(String name) throws NameNotFound { 
    {} //System.out.println("class name" + name);
    {} //System.out.println("class name" + PrototypeSvc.lookup(NAMESPACE, name));
    {} //System.out.println("class name" + PrototypeSvc.lookup(NAMESPACE, name).getClass().getName());
    return (ServiceRecord)PrototypeSvc.lookup(NAMESPACE, name);
  }

  public static Collection getPrototypeNames() { return PrototypeSvc.getPrototypeNames(NAMESPACE); }
  
  public static PrototypeCollection findAll() { return PrototypeSvc.findAll(NAMESPACE); }
  
  public static void add(ServiceRecord serviceRecord) throws DuplicateName, InvalidConfiguration, NotConnected {
    try {
      ServiceRecord old = (ServiceRecord)PrototypeSvc.lookup(NAMESPACE, serviceRecord.getName());
      throw new DuplicateName(NAMESPACE, serviceRecord.getName());
    }
    catch (NameNotFound ignore) { }
    catch (Exception exp) {
      exp.printStackTrace();
      {} //System.out.println("Exception " + exp.getMessage());
    }
    PrototypeSvc.add(NAMESPACE, serviceRecord);
    {} //System.out.println("Added " + serviceRecord.getName());
  }

  public static void update(ServiceRecord serviceRecord) throws InvalidConfiguration, NotConnected { 
    remove(serviceRecord.getName()); 
    try { add(serviceRecord); }
    catch (DuplicateName e) {} //will never be thrown if source is first removed;
  }
  public static void remove(Named op) { remove(op.getName()); }
  public static void remove(String name) { PrototypeSvc.remove(NAMESPACE, name); }
  public static void unload() { PrototypeSvc.unload(NAMESPACE); }

  
  
}
