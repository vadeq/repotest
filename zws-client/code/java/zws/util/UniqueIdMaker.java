/**
 * 
 */
package zws.util;

/*
 DesignState - Design Compression Technology
 @author: eankutse
 @version: 1.0
 Created on Dec 11, 2007
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * UniqueIdMaker generates unique string ids of the form
 * <hostname>-<timestamp>-<counter>. These ids generated are 
 * guaranteed to be useable as valid directory and 
 * file names on Windows and UNIX systems. 
 * 
 * @author eankutse
 *
 */
public class UniqueIdMaker implements Serializable {
  
  private static final long serialVersionUID = -3081444883187241470L;
  private static String hostname;
  private static long id_counter = 0;
  private static String DASH = "-";
  
  
  static {
    hostname = "UnknownHost";
    try {
      InetAddress addr = InetAddress.getLocalHost();
      hostname = addr.getHostName();
     } catch (UnknownHostException e) {
       hostname = "UnknownHost";       
    }    
  }//static initialization
  
  /**
   * 
   * @return unique id: <hostname>-<timestamp>-<counter>. This id
   * is guaranteed to be useable as valid directory and file names 
   * on Windows and UNIX systems.
   * @throws Exception
   */
  public static String getId() throws Exception {

    Long timestamp_obj = new Long(System.currentTimeMillis());
    Long id_counter_obj = new Long(id_counter);
    
    //increment counter, resetting as necessary
    if (Long.MAX_VALUE == id_counter) id_counter = 0;
    else id_counter++;

    return hostname + DASH + timestamp_obj.toString() + DASH + id_counter_obj.toString();
  }

}
