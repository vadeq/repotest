/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Dec 11, 2007 11:41:05 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.util;

import java.net.URL;
import java.util.Collection;

public interface RemotePackage {
  String getName(); //returns name of primary file
  Collection getFileNames();
  String getLocation();
  URL getUrl();
  
  public static final String URL = "url";
  public static String LOCATION = "loation";
  public static String PRIMARY_NAME = "primary-name";
  public static String DELIMITER = ",";
  public static String TAG_NAME = "remote-package";
  public static String FILE_LIST  = "file-list"; 
}
