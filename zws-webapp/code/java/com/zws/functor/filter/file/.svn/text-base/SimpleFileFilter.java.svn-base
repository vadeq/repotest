package com.zws.functor.filter.file;

import java.io.File;
import java.io.FileFilter;
import java.util.StringTokenizer;

public class SimpleFileFilter implements FileFilter {
  public SimpleFileFilter(){}
  public SimpleFileFilter(String criteria) { searchCriteria=criteria; }
  public boolean accept(File pathname) {
    if (ignore(pathname)) return false;
    if (include(pathname)) return match(pathname);
    return false;
  }

  private boolean match(File pathname) {
    if ("*".equals(getSearchCriteria())) return true;
    String name = pathname.getName().toLowerCase();
    StringTokenizer tok = new StringTokenizer(getSearchCriteria(), "*");
    String crit;
    int count=0;
    boolean matched=true;
    while (tok.hasMoreTokens()){
      crit=tok.nextToken();
      if (0==count++ && !getSearchCriteria().startsWith("*") && !name.startsWith(crit)) return false;
      if (0>name.indexOf(crit)) matched=false;
    }
    return matched;
  }

  private boolean ignore(File pathname) {
    if (null==ignoredExtentions) return false;
    String ext = getExtention(pathname.getName());
    StringTokenizer ignoredTokens=new StringTokenizer(ignoredExtentions, ";");
    while (ignoredTokens.hasMoreTokens())
      if  (ignoredTokens.nextToken().equalsIgnoreCase(ext)) return true;
    return false;
  }

  private boolean include(File pathname) {
    if (null==includedExtentions) return true;
    String ext = getExtention(pathname.getName());
    StringTokenizer includedTokens= new StringTokenizer(includedExtentions, ";");
    while (includedTokens.hasMoreTokens())
      if  (includedTokens.nextToken().equalsIgnoreCase(ext)) return true;
    return false;
  }

  private String getExtention(String filename) {
    int dot = filename.lastIndexOf(".");
    if (0>dot) return null;
    return filename.toLowerCase().substring(dot+1);
  }

  public String getSearchCriteria() {return searchCriteria; }
  public void setSearchCriteria(String s) { searchCriteria = s.toLowerCase(); }
  public String getIncludedExtentions() { return includedExtentions; }
  public void setIncludedExtentions(String s) { includedExtentions=s; }
  public String getIgnoredExtentions() { return ignoredExtentions; }
  public void setIgnoredExtentions(String s) { ignoredExtentions=s; }

  private String searchCriteria=null;
  private String includedExtentions=null;
  private String ignoredExtentions=null;
}
