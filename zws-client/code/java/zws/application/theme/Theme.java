package zws.application.theme; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Feb 23, 2006
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import java.util.*;
import java.io.Serializable;

public class Theme implements Serializable {

  public String getName() { return name;  }
  public void setName(String s) { name=s; } 
    
  public String getColor(String name) { return (String)colors.get(name); }

  public String getTextColor(String name) {
    Text txt =  (Text) fonts.get(name);
    if (null==txt) return null;
    return txt.getColor();
  }

  public String getTextFont(String name) {
    Text txt =  (Text) fonts.get(name);
    if (null==txt) return null;
    return txt.getFont();
  }

  public String getTextWeight(String name) {
    Text txt =  (Text) fonts.get(name);
    if (null==txt) return null;
    return txt.getWeight();
  }

  public String getTextDecoration(String name) {
    Text txt =  (Text) fonts.get(name);
    if (null==txt) return null;
    return txt.getDecoration();
  }

  public String getTextStyle(String name) {
    Text txt =  (Text) fonts.get(name);
    if (null==txt) return null;
    return txt.getStyle();
  }

  public String getTextSize(String name) {
    Text txt =  (Text) fonts.get(name);
    if (null==txt) return null;
    return txt.getSize();
  }
  public String getTextAlignment(String name) { 
	  Text txt =  (Text) fonts.get(name);
	  if (null==txt) return null;
	  return txt.getAlignment();
  }
  public String getTextLeftPadding(String name) { 
	  Text txt =  (Text) fonts.get(name);
	  if (null==txt) return null;
	  return txt.getLeftPadding();
  }
  public String getTextRightPadding(String name) { 
	  Text txt =  (Text) fonts.get(name);
	  if (null==txt) return null;
	  return txt.getRightPadding();
  }

  public void add(Color c) { colors.put(c.getName(), c.getValue()); }
  public void add(Text t) { fonts.put(t.getName(), t); }
  
  private String name;
  private Map colors = new HashMap();
  private Map fonts = new HashMap();
}
