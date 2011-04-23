/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Oct 15, 2007 3:44:04 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved 
 */

package zws.util;

import java.io.File;
import java.net.URL;

/**
 * This object represents a file that is stored at a remote location.
 * 
 * This object is the only one that uses the File Depot client 
 * (the plugin to the File Depot service) to the File Depot service . 
 * All other clients else use this object to interact with the File Depot.
 */
 public interface RemoteFile {

  String getName();
  long length();   
  boolean exists();
  
  URL getURL();
  String getId();
  String getRemoteLocation(); //return the directory of the file
  
  RemoteFile store(File file) throws Exception;
  RemoteFile store(URL url)throws Exception;
  
  File retrieveFile();
  File retrieveFileInTo(String dir) throws Exception;
  String getBinaryFileName();
  void setBinaryFileName(String binaryFileName);
  
  boolean delete();  
}
