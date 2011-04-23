package zws;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 10, 2003, 5:55 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;
import zws.security.Authentication;
import zws.service.document.DocumentService;
import zws.service.document.EJBLocator;


public class DocumentConverter {
  public static void convertDRW2PDF(Origin origin, String workspace, String location, String outputName, Authentication id) throws Exception {
    DocumentService service = EJBLocator.findService(origin.getServerName());
    service.convertDRW2PDF(origin, workspace, location, outputName, id);
  }
  
  public static void convertDRW2HPGL(Origin origin, String workspace, String location, String outputName, Authentication id) throws Exception {
    DocumentService service = EJBLocator.findService(origin.getServerName());
    service.convertDRW2HPGL(origin, workspace, location, outputName, id);
  }
  
  public static void convertDRW2CGM(Origin origin, String workspace, String location, String outputName, Authentication id) throws Exception {
    DocumentService service = EJBLocator.findService(origin.getServerName());
    service.convertDRW2CGM(origin, workspace, location, outputName, id);
  }

  //should make a version of this that is independent of workspace
  public static void convertDWG2PDF(Origin origin, String workspace, String location, String outputName, Authentication id) throws Exception {
    DocumentService service = EJBLocator.findService(origin.getServerName());
    service.convertDWG2PDF(origin, workspace, location, outputName, id);
  }
  
  public static void convertPS2PDF(String serverName, String name, String location, String outputName, String outputLocation, boolean deleteInput) throws Exception {
    DocumentService service = EJBLocator.findService(serverName);
    service.convertPS2PDF(name, location, outputName, outputLocation, deleteInput);
  }
  
  public static void stampPDF(String serverName, String location, String name, String text, int fontSize, String color, int xPosition, int yPosition, int angle, int opacity) throws Exception  {
    DocumentService service = EJBLocator.findService(serverName);
    service.stampPDF(location, name, text, fontSize, color, xPosition, yPosition, angle, opacity);
  }
}
