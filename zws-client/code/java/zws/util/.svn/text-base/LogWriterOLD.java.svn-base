package zws.util;
/*
 DesignState - Design Compression Technology
 @author: ptoleti
 @version: 1.0
 Created on Apr 19, 2007 3:38:12 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

/**
 * @author ptoleti
 *
 */
import zws.Server;
import zws.application.Names;
import zws.application.Properties;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
public class LogWriterOLD {

  public static void printOnConsole(Object o, String method) {
    printOnConsole(o.getClass(), method);
  }
  public static void printOnConsole(Object o, String method, Object msg) {
    printOnConsole(o.getClass(), method, msg);
  }

  public static void printOnConsole(Class c, String method) {
    {} //System.out.println(c.getName()+"["+method+"]");
  }
  public static void printOnConsole(Class c, String method, Object msg) {
    {} //System.out.println(c.getName()+"["+method+"]: " + msg);
  }



  public static void printToFile(File file, String msg) {
    if(null == msg) return;
    try {
      FileOutputStream fos = new FileOutputStream(file, true);
      fos.write(Names.NEW_LINE.getBytes());
      fos.write(msg.getBytes());
      fos.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void printToFile(String fileName, String msg) {
    printToFile(new File(Properties.get(Names.TEMP_DIR)+Names.PATH_SEPARATOR+fileName), msg);
  }



  public static void printOnConsole(Object object) {
    {} //System.out.println(object);
  }

public static void printOnConsole(int message) {
  if (Server.debugMode()) {
    {} //System.out.println(message);
  }
}

public static void printOnConsole(long message) {
  if (Server.debugMode()) {
    {} //System.out.println(message);
  }
}

public static void printOnConsole(char message) {
  if (Server.debugMode()) {
    {} //System.out.println(message);
  }
}
}
