package zws.service.document; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.application.*;
import zws.origin.Origin;
import zws.util.DeleteFile;
import zws.security.Authentication;
import java.io.File;
import java.net.URL;
import java.rmi.RemoteException;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class DocumentServiceEJB implements SessionBean, DocumentService  {

  public void print(Origin o, String printerName, int quantity) throws RemoteException {
    try { DocumentSvc.print(o, printerName, quantity); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

 /**
   * Returns a {@link URL} to a binary file corresponding to a file or 
   * set of files specifed by the origin argument.
   * If more than one file is associated with the given origin, the corresponding 
   * fileset is zipped into a single file.
   * <p>
   * The file or set of files may have to be extracted from a data repository
   * before it is available. The function waits until the file(s) are extracted.
   * @param  origin  an Origin that refers to the exact location and name of the file or file set
   * @return a URL to the binary 
   * @throws RemoteException if the servername specified in the origin is not found or if errors 
   *         occure durring file extraction
   */  
  public URL getURL(Origin origin) throws RemoteException {
    try { return DocumentSvc.getURL(origin); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

/**
   * Downloads the binary corresponding to the origin to the local server.
   * The binary is stored into a logical set identified by key
   * 
   * @param  origin  an Origin specifying the binary to be  downloaded.
   * @param  skey identifier for the logical set.
   * @throws RemoteException if the servername specified in the origin is not found or if errors 
   *         occure durring file download.
   */
  public void saveBinaryFile(Origin origin, String key) throws RemoteException {  //+++change key to use logical location
    try { DocumentSvc.saveBinaryFile(origin, key); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }


/**
   * Zips up the binaries associated with the logical set named by key and returns a URL to this binary.
   *  
   * @param  key identifier for the logical set.
   * @throws RemoteException if if errors occure durring the operation.
   */
  public URL getBinaryFiles(String key) throws RemoteException { //+++change key to use logical location
    try { return DocumentSvc.getBinaryFiles(key); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void convertDRW2PDF(Origin origin, String workspace, String location, String outputName, Authentication id) throws RemoteException {
    try { 
      File dir = new File(findLocalPath(location));
      if (!dir.exists()) dir.mkdirs();
      DocumentSvc.convertDRW2PDF(origin, workspace, dir.getAbsolutePath(), outputName, id); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void convertDRW2HPGL(Origin origin, String workspace, String location, String outputName, Authentication id) throws RemoteException {
    try { DocumentSvc.convertDRW2HPGL(origin, workspace, findLocalPath(location), outputName, id); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void convertDRW2CGM(Origin origin, String workspace, String location, String outputName, Authentication id) throws RemoteException {
    try { DocumentSvc.convertDRW2CGM(origin, workspace, findLocalPath(location), outputName, id); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void convertDWG2PDF(Origin origin, String workspace, String location, String outputName, Authentication id) throws RemoteException {
    try { 
      File dir = new File(findLocalPath(location));
      if (!dir.exists()) dir.mkdirs();
      DocumentSvc.convertDWG2PDF(origin, workspace, dir.getAbsolutePath(), outputName, id); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void convertPS2PDF(String location, String name, String outputName, String outputLocation, boolean deleteInput) throws RemoteException {
    try { DocumentSvc.convertPS2PDF(findLocalPath(location), name, outputName, findLocalPath(outputLocation), deleteInput); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void stampPDF(String location, String pdfName, String text, int fontSize, String color, int xPosition, int yPosition, int angle, int opacity) throws RemoteException  {
    try { DocumentSvc.stampPDF(findLocalPath(location), pdfName, text, fontSize, color, xPosition, yPosition, angle, opacity); }
    catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public boolean fileExists(String location, String filename) throws RemoteException {
    File f = new File(findLocalPath(location), filename);
    if (f.exists() && !f.isFile()) throw new RemoteException(filename + " at location: " + location + " is not a file");
    return f.exists();
  }
  
  public boolean locationExists(String location) throws RemoteException {
    File f = new File(findLocalPath(location));
    if (f.exists() && !f.isDirectory()) throw new RemoteException(location + " is not a valid location");
    return f.exists();
  }
  
  public boolean deleteFile(String location, String filename) throws RemoteException {
    File f = new File(findLocalPath(location), filename);
    if (!f.exists()) throw new RemoteException("File " + filename + " does not exist in location: " + location);
    if (!f.isFile()) throw new RemoteException(filename + " at location: " + location + " is not a file");
    return f.delete();
  }
  
  public boolean removeLocation(String location, boolean deleteIfNotEmpty) throws RemoteException {
    File f = new File(findLocalPath(location));
    if (!f.exists()) throw new RemoteException("The location "+ location + " does not exist");
    if (!f.isDirectory()) throw new RemoteException(location + " is not considered a valid location");
    DeleteFile deleter = new DeleteFile();
    deleter.setFile(f);
    deleter.setDeleteIfNotEmpty(deleteIfNotEmpty);
    try { deleter.execute(); return ((Boolean)deleter.getResult()).booleanValue(); }
    catch (Exception e) { throw new RemoteException(e.getMessage()); } 
  }
  
  private static String findLocalPath(String location) { return Properties.get(Names.LOCAL_DIR) + Names.PATH_SEPARATOR + location; }    
  private static String findLocalPath(String location, String filename) { return Properties.get(Names.LOCAL_DIR) + Names.PATH_SEPARATOR + location + Names.PATH_SEPARATOR + filename; }    

  public void ejbCreate () { try {Configurator.load();} catch (Exception e) { e.printStackTrace(); } }
  public void ejbRemove() {}
  public void ejbPassivate() {}
  public void ejbActivate() {}
  public void setSessionContext(SessionContext ctx) {}
}
