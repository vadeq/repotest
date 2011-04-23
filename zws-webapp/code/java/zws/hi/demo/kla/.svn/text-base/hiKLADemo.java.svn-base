package zws.hi.demo.kla; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Apr 12, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import java.util.*;

public class hiKLADemo {
  
  public static boolean isExcluded(String name) {
    Metadata data;
    Iterator i = excludeList.iterator();
    while(i.hasNext()) {
      data = (Metadata) i.next();
      if (data.getName().equals(name)) return true;
    }
    return false;
  }

  public static Collection excludeList=new Vector();

  public static Map mappings = new HashMap();
  public static Map ilinkMappings = new HashMap();
  public static Map agileMappings = new HashMap();
  
}
