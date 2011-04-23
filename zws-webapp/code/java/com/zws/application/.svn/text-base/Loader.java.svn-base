package com.zws.application;

import com.zws.xml.parser.Parser;

import java.io.File;

public class Loader {
  public static void load(File xmlFile, File tagmap) throws Exception {
    Parser parser = new Parser();
    parser.load(xmlFile, tagmap);
  }

  public void load() throws Exception {
    Parser parser = new Parser();
    if ( null == getTagMap() || null == getXMLConfiguration() ) throw new IllegalStateException();
    String bp = ( (getBasePath() != null && getBasePath() !="") ? getBasePath() + "/" : "" );
    parser.load(new File( bp + getXMLConfiguration()), new File( bp + getTagMap()));
  }
  public String getTagMap(){ return tagMap; }
  public void setTagMap(String s){ tagMap = s; }
  public String getXMLConfiguration(){ return xmlConfiguration; }
  public void setXMLConfiguration(String s){ xmlConfiguration = s; }
  public String getBasePath(){ return basePath; }
  public void setBasePath(String basePath){ this.basePath = basePath; }

  private String tagMap;
  private String xmlConfiguration;
  private String basePath;
}

