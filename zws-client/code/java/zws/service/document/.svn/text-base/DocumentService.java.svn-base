package zws.service.document; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;
import zws.security.Authentication;

import java.io.Serializable;
import java.net.URL;
import java.rmi.RemoteException;

public interface DocumentService extends Serializable {

  
  public void print(Origin o, String printerName, int quantity) throws RemoteException;
  
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
  public URL getURL(Origin origin) throws RemoteException;

/**
   * Downloads the binary corresponding to the origin to the local server.
   * The binary is stored into a logical set identified by key
   *  
   * @param  origin  an Origin specifying the binary to be  downloaded.
   * @param  skey identifier for the logical set.
   * @throws RemoteException if the servername specified in the origin is not found or if errors 
   *         occure durring file download.
   */  
  public void saveBinaryFile(Origin origin, String key) throws RemoteException ;

/**
   * Zips up the binaries associated with the logical set named by key and returns a URL to this binary.
   *  
   * @param  key identifier for the logical set.
   * @throws RemoteException if if errors occure durring the operation.
   */
  public URL getBinaryFiles(String key) throws RemoteException;

  public boolean fileExists(String location, String filename) throws RemoteException;
  public boolean locationExists(String location) throws RemoteException;
  public boolean deleteFile(String location, String filename) throws RemoteException;
  public boolean removeLocation(String location, boolean deleteIfNotEmpty) throws RemoteException;

  public void convertDRW2PDF(Origin origin, String workspace, String location, String outputName, Authentication id) throws RemoteException;
  public void convertDRW2HPGL(Origin origin, String workspace, String location, String outputName, Authentication id) throws RemoteException;
  public void convertDRW2CGM(Origin origin, String workspace, String location, String outputName, Authentication id) throws RemoteException;
  public void convertDWG2PDF(Origin origin, String workspace, String location, String outputName, Authentication id) throws RemoteException;
  public void convertPS2PDF(String name, String location, String outputName, String outputLocation, boolean deleteInput) throws RemoteException;
  public void stampPDF(String location, String pdfName, String text, int fontSize, String color, int xPosition, int yPosition, int angle, int opacity) throws RemoteException;
}
