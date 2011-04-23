package zws.xml;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.util.FileUtil;
import java.io.File;
import java.io.Serializable;

public class XML implements Serializable {
  public XML(){};
  public XML(String s) { xml.append(s); }

  public String getString() { return toString(); }
  public void setString(String s) { xml = new StringBuffer(s); }

  public String toString(){ return xml.toString(); }
  public void loadFile(String path) throws Exception { loadFile(new File(path)); }
  public void loadFile(File input) throws Exception { xml.append(FileUtil.read(input)); }
  public void write(String s) { xml.append(s); }
  
  private StringBuffer xml=new StringBuffer();
}