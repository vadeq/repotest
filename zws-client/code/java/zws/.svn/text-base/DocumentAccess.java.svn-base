package zws;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 18, 2004, 9:37 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;
import zws.service.document.DocumentService;
import zws.service.document.EJBLocator;

public class DocumentAccess {

  public static void print(Origin o, String printerName, int quantity) throws Exception {
    DocumentService service = EJBLocator.findService(o.getServerName());
    service.print(o, printerName, quantity);
  }
  public static boolean fileExists(String serverName, String location, String filename) throws Exception {
    DocumentService service = EJBLocator.findService(serverName);
    return service.fileExists(location, filename);
  }
  public static boolean locationExists(String serverName, String location) throws Exception {
    DocumentService service = EJBLocator.findService(serverName);
    return service.locationExists(location);
  }
  public static boolean deleteFile(String serverName, String location, String filename) throws Exception {
    DocumentService service = EJBLocator.findService(serverName);
    return service.deleteFile(location, filename);
  }
  public static boolean removeLocation(String serverName, String location, boolean deleteIfNotEmpty) throws Exception {
    DocumentService service = EJBLocator.findService(serverName);
    return service.removeLocation(location, deleteIfNotEmpty);
  } 
}
