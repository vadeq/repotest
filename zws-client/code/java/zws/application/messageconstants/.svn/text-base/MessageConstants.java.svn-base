/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Mar 7, 2008 8:17:17 AM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.application.messageconstants;

import java.util.Map;
import java.util.HashMap;

public class MessageConstants {


  public static void add(MessageConstant msg) {
    if (null==msg) return;
    messageMap.put(msg.getId(), msg);
  }
  
  public static void add(String id, String message, String desc) {
    messageMap.put(id.trim(), new MessageConstant(id.trim(), message, desc));
  }
  
  public static MessageConstant lookup(String id) {
    return (MessageConstant)messageMap.get(id.trim());
  }
  
  public static String resolve(String id) throws Exception {
    MessageConstant mc = (MessageConstant)messageMap.get(id.trim());
    if (null == mc) return null;    
    return mc.resolve();
  }
  public static String resolve(String id, Map msgParams) throws Exception {
    MessageConstant mc = (MessageConstant)messageMap.get(id.trim());
    if (null == mc) return null;
    
    return mc.resolve(msgParams);
  }
  
  static Map messageMap = new HashMap();  
}
