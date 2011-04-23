package zws.application; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.xml.parser.Parser;

import java.io.File;
import java.io.InputStream;

public class Loader {
  public static void load(File xml, File directives) throws Exception {
    Parser parser = new Parser();
    parser.load(xml, directives);
  }

  public static void load(InputStream xml, InputStream directives) throws Exception {
    Parser parser = new Parser();
    parser.load(xml, directives);
  }

  public void load() throws Exception {
    Parser parser = new Parser();
    if ( null == getTagMap() || null == getXMLConfiguration() ) throw new IllegalStateException();
    String bp = ( (getBasePath() != null && getBasePath() !="") ? getBasePath() + Names.PATH_SEPARATOR : "" );
    parser.load(new File( bp + getXMLConfiguration()), new File( bp + getTagMap()));
  }

  public String getTagMap(){ return tagMap; }
  public void setTagMap(String s){ tagMap = s; }
  public String getXMLConfiguration(){ return Properties.expandReferences(xmlConfiguration); }
  public void setXMLConfiguration(String s){ xmlConfiguration = s; }
  public String getBasePath(){ return Properties.expandReferences(basePath); }
  public void setBasePath(String basePath){ this.basePath = basePath; }

  private String tagMap;
  private String xmlConfiguration;
  private String basePath;
}
