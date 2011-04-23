package zws.securespace;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 24, 2004, 2:56 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.CanNotMaterialize;
import zws.util.Namespace;
import zws.util.comparator.AlphaNumericComparator;

import java.util.StringTokenizer;

public abstract class SecureSpaceBase implements SecureSpace {
  
  protected String defineType(){
    String fqcn = getClass().getName();
    int idx = fqcn.lastIndexOf(DOT);
    if (-1!=idx) fqcn = fqcn.substring(idx+1);
    idx = fqcn.lastIndexOf("Base");
    if (-1!=idx) fqcn = fqcn.substring(0,idx);
    char[] chars = fqcn.toCharArray();
    chars[0] = fqcn.toLowerCase().charAt(0);
    String t="";
    char c;
    for (idx=0; idx<chars.length; idx++) {
      c = chars[idx];
      if (AlphaNumericComparator.isUpperCase(c)) t += DOT + fqcn.toLowerCase().charAt(idx);
      else t += c;
    }
    return t;
  }
  
  protected abstract Namespace defineNamespace();
  
  public final String getType() { if (null==type) type=defineType(); return type; }
  public void setType(String s) { type=s; }
  public final Namespace getNamespace() {
    if (null==namespace) namespace = defineNamespace();
    return namespace;
  }
  protected void resetNamespace() { namespace=null; }
  public final void setNamespace(String s) { namespace = new Namespace(s);}
  public final void setNamespace(Namespace n) { namespace=n; }
  
  public final String getSpace() { return getNamespace() + DOT + STAR + DOT +name; }
  public final String getName() { return name; }
  public final void setName(String s) { name=s; }
  public final String getDescription() { return description; }
  public final void setDescription(String s) { description=s; }
  
  protected abstract void materialize(String space) throws CanNotMaterialize;;
  
  protected static String[] getSpaceTokens(String space) {
    StringTokenizer spaces = new StringTokenizer(space, STAR);
    String[] spaceTokens = new String[spaces.countTokens()];
    String s = spaces.nextToken();
    s = s.substring(0, s.lastIndexOf(DOT));
    spaceTokens[0] = s;
    int idx=1;
    while (spaces.hasMoreTokens()) {
      s = spaces.nextToken();
      if (s.endsWith(DOT)) s = s.substring(1, s.lastIndexOf(DOT));
      else s = s.substring(1);
      spaceTokens[idx++] = s;
    }
    return spaceTokens;
  }
  
  public final boolean equals(SecureSpace space) { return getSpace().equals(space.getSpace()); }  
  public final String toString() { return getSpace(); }
 
  private String name=null;
  private String description=null;
  private Namespace namespace=null;

  private String type=null;
  
  public static String DOT=".";
  public static String STAR="*";

  /*
  public static String USPACE="user.space";
  public static String USPACE_ROLE="role";
  public static String USPACE_GROUP="group";
  public static String USPACE_SITE="site";
  public static String USPACE_SECURITY_PROFILE="security.profile";
  
  public static String VSPACE="vade.space";
  public static String VSPACE_APPLICATION="application";
  public static String VSPACE_DOMAIN="domain";
  public static String VSPACE_SERVER="server";
  public static String VSPACE_DRIVE_LOCATION="drive.location";
  public static String VSPACE_INTRALINK="intralink";
  public static String VSPACE_WORKSPACE="workspace";
  public static String VSPACE_ACTION="vade.space.action";
   */
}
