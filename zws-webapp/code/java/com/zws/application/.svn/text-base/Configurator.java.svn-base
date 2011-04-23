package com.zws.application;

import com.zws.service.config.*;

import java.io.File;
import java.net.URL;

public class Configurator {

  public static void load () throws Exception {
    if (loaded) return;
    Class c= (new Configurator()).getClass();
    URL tagmap = c.getResource("/config.tagmap");
    URL config = c.getResource("/config.xml");
    Loader.load(new File(config.getPath()), new File(tagmap.getPath()));
    loaded=true;
  }

  public static void unload(){
    loaded=false;
    DataSourceService.unload();
    SearchAgentService.unload();
    DataReportService.unload();
    //DataProcessorService.unload();
  }
  public void load(Loader loader) throws Exception{ loader.setBasePath(getBasePath()); loader.load(); }
  public String getBasePath(){ return basePath; }
  public void setBasePath(String basePath){ this.basePath = basePath; }
  private String basePath;
  private static boolean loaded=false;
}