package zws.application; 
/*
* DesignState - Design Compression Technology. 
* @author: Arbind Thakur 
* @version: 1.0 Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
*/

import zws.database.DB;

import java.io.File;
import java.net.URL;

public class Configurator {

  public static void reinitialize() {
    loaded = false;
    e = null;
  }

  public static void load() throws Exception {
    if (null != e) {
        System.out.println("Configuration already loaded.");
        System.out.println("Configuration error: " + e.getClass().getName());
        System.out.println(e.getMessage());
        return;
      }
      if (loaded) return;
      DB.clear();
      loaded = true;
      //loadResource("/server-bootstrap.xml", "/bootstrap.zws");
      //loadResource("/web-bootstrap.xml", "/bootstrap.zws");
      loadResource("/bootstrap.xml", "/bootstrap.zws");
    if (null != e) {
      System.out.println("ERROR: ERROR IN LOADING CONFIGURATION: bootstrap.xml!");
    } else {
      System.out.println("CONFIGURATION LOADED OK.");
    }
  }

  private static void loadResource(String configName, String tagmapName) {
    try {
      System.out.println("loading " + configName + " with " + tagmapName);      
      URL tagmap = loadURL(tagmapName);
      URL config = loadURL(configName);
      if(null == tagmap || null == config) return;
      Loader.load(new File(config.getPath()), new File(tagmap.getPath()));
    } catch (Exception x) {
      System.out.println("ERROR: while loading " + configName + " with " + tagmapName);
      Throwable t = x;
      while (null != t) {
        System.out.println(t.getClass().getName() + ": " + t.getMessage());
        t = t.getCause();
      }
      e = x;
    }
  }
  
  private static URL loadURL(String name) {
    Class c = (new Configurator()).getClass();
    URL url = c.getResource(name);
    if(null == url) System.out.println("ERROR: not able to load " + name); 
    return url;
  }
  public void load(Loader loader) {
    if (null != e) return;
    loader.setBasePath(getBasePath());
    try {
      loader.load();
    } catch (Exception x) {
      Throwable t = x;
      while (null != t) {
        System.out.println(t.getClass().getName() + ": " + t.getMessage());
        t = t.getCause();
      }
      e = x;
      return;
    }
  }

  public String getBasePath() {return basePath;}
  public void setBasePath(String s) {basePath = s;}

  private String basePath;
  private static Exception e;
  private static boolean loaded = false;
}
