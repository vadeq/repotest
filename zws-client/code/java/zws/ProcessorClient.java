/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 12, 2004, 3:48 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
package zws;

import zws.exception.NameNotFound;
import zws.exception.ServerNotFound;
import zws.processor.Processor;
import zws.service.processor.EJBLocator;
import zws.service.processor.ProcessorServiceRemote;

import java.util.Collection;

public class ProcessorClient {
  public static Processor getProcessor(String serverName, String processorName) throws ServerNotFound, NameNotFound { 
    //Process process;
    ProcessorServiceRemote service;
    if (null==serverName) throw new ServerNotFound(serverName);
    if (null==processorName) throw new NameNotFound(processorName, "Processors");
    try { service = EJBLocator.findService(serverName); }
    catch (Exception e) { e.printStackTrace(); throw new ServerNotFound(serverName); }
    try { return service.getProcessor(processorName); }
    catch (Exception e) { e.printStackTrace(); throw new NameNotFound(processorName, "Processors"); }
  }

  public static Collection getAllProcessors(String serverName) throws Exception {
    ProcessorServiceRemote service;
    if (null==serverName) throw new ServerNotFound(serverName);
    try { service = EJBLocator.findService(serverName); }
    catch (Exception e) { e.printStackTrace(); throw new ServerNotFound(serverName); }
    return service.getProcessors();
  }
  
  public static void launch(String serverName, Processor p) throws Exception {
    ProcessorServiceRemote service;
    if (null==serverName) throw new ServerNotFound(serverName);
    if (null==p) throw new NullPointerException("Process is null");
    try { service = EJBLocator.findService(serverName); }
    catch (Exception e) { e.printStackTrace(); throw new ServerNotFound(serverName); }
    try { service.execute(p); }
    catch (Exception e) { e.printStackTrace(); throw e;}
  }
}
