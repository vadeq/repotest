package zws.application.graphics; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Feb 23, 2006
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

import zws.util.*;
import zws.application.*;

import java.util.*;

  
public class GraphicsTheme {

  public String getName() { return name; }
  public void setName(String s) { name=s; }

  public String getRootImagePath() { return rootImagePath; }
  public void setRootImagePath(String s) { rootImagePath=s; }    

  public String getValue(String name) { return (String) values.get(name); }
  public String getImagePath(String name) { return imageThemePath()+ Names.PATH_SEPARATOR  + images.get(name); }
  
  public void add(StringPair pair) { values.put(pair.getString0(), pair.getString1()); }
  public void addImage(StringPair pair) { images.put(pair.getString0(), pair.getString1()); }
  
  protected String imageThemePath() { return rootImagePath + Names.PATH_SEPARATOR + name; } 
  
  private String name=null;
  private String rootImagePath=null;

  private Map values = new HashMap();
  private Map images = new HashMap();
}
