package zws.data.filter.file;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.exception.InvalidSyntax;
import zws.search.criteria.Comparison;
import zws.search.criteria.Grouping;
import zws.util.TimeUtil;

import java.io.*;
import java.util.*;

public class SimpleFileFilter implements FileFilter, Serializable {
  public SimpleFileFilter(){}
  //public SimpleFileFilter(String criteria) { searchCriteria=criteria; }
  public boolean accept(File pathname) {
    initialize(pathname);
    if (!isInSizeRange()) return false;
    if (!isInTimeSpan()) return false;
    return match();
  }

  private void initialize(File pathname) {
    folder = pathname.getParentFile().getAbsolutePath().toLowerCase();
    fileName=pathname.getName().toLowerCase();
    int idx = fileName.lastIndexOf(DOT);
    if (idx<0 || idx==fileName.length())  fileType="null";
    else {
      fileType=fileName.substring(idx+1);
      fileName=fileName.substring(0,idx);
    }
    lastModified = pathname.lastModified();
    isReadOnly=pathname.canRead() && !pathname.canWrite();
    isReadWrite=pathname.canRead() && pathname.canWrite();
    isHidden=pathname.isHidden();
    length=pathname.length();
  }
  
  private boolean match() {
    if (ignore()) return false;
    if (include()) return true;
    return false;
  }

  private boolean ignore() {
    if(ignoreHiddenFiles && isHidden) return true;
    if(ignoreReadOnlyFiles && isReadOnly) return true;
    if(ignoreReadWriteFiles && isReadWrite) return true;
    if (isInIgnoredFolder() || isAnIgnoredFileType() || isAnIgnoredFilename()) return true;
    return false;
  }

  private boolean include() {
    if(includeOnlyHiddenFiles && !isHidden) return false;
    if(includeOnlyReadOnlyFiles && !isReadOnly) return false;
    if(includeOnlyReadWriteFiles && !isReadWrite) return false;
    if ( (isAnIncludedFileType() && isAnIncludedFilename())) return true;
    return false;
  }

  private boolean isInSizeRange() {
    if (minFileSize==0 && maxFileSize==0) return true;
    if (minFileSize>0 && maxFileSize>0){
      if (length >minFileSize && length<maxFileSize) return true;
      else return false;
    }
    if (minFileSize>0 && length>minFileSize) return true;
    if (maxFileSize>0 && length<maxFileSize) return true;
    return false;
  }

  private boolean isInTimeSpan() {
    if (earliestTime==0 && latestTime==0) return true;
    if (earliestTime>0 && latestTime>0){
      if (lastModified+1 >earliestTime && lastModified-1<latestTime) return true;
      return false;
    }
    if (earliestTime>0 && lastModified+1>earliestTime) return true;
    if (latestTime>0 && lastModified-1<latestTime ) return true;
    return false;
  }
  
  private boolean isInIgnoredFolder() {
    if (ignoredFolders!=null && ignoredFolders.containsKey(folder)) return true;
    if (ignoredRootFolders==null) return false;
    Iterator i = ignoredRootFolders.keySet().iterator();
    String root;
    while (i.hasNext()) {
      root = (String)i.next();
      if (folder.startsWith(root)) return true;
    }
    return false;
  }
  
  private boolean isAnIgnoredFileType() {
    if (null==ignoredFileTypes) return false;
    if (ignoredFileTypes.containsKey(fileType)) return true;
    return false;
  }
  private boolean isAnIncludedFileType() {
    if (null==includedFileTypes) return true;
    if (includedFileTypes.containsKey(fileType)) return true;
    return false;
  }
  
  private boolean isAnIgnoredFilename() {
   Iterator i;
   String key;
   if (null!=ignoredFileNamesNotContaining) {
     i = ignoredFileNamesNotContaining.keySet().iterator();
     while(i.hasNext()){
       key = (String)i.next();
       if (fileName.indexOf(key)>-1) return false;
     }
   }
   if (null!=ignoredFileNamesStartingWith) {
     i = ignoredFileNamesStartingWith.keySet().iterator();
     while(i.hasNext()){
       key = (String)i.next();
       if (fileName.startsWith(key)) return true;
     }
   }
   if (null!=ignoredFileNamesEndingIn) {
     i = ignoredFileNamesEndingIn.keySet().iterator();
     while(i.hasNext()){
       key = (String)i.next();
       if (fileName.endsWith(key)) return true;
     }
   }
   if (null!=ignoredFileNamesContaining) {
     i = ignoredFileNamesContaining.keySet().iterator();
     while(i.hasNext()){
       key = (String)i.next();
       if (fileName.indexOf(key)>-1) return true;
     }
   }
   return false;
  }
  
  private boolean isAnIncludedFilename() {
   if (includeAllFileNames) return true;
   Iterator i;
   String key;
   if (null!=includedFileNamesNotContaining) {
     i = includedFileNamesNotContaining.keySet().iterator();
     while(i.hasNext()){
       key = (String)i.next();
       if (fileName.indexOf(key)>-1) return false;
     }
   }
   if (null!=includedFileNamesStartingWith) {
     i = includedFileNamesStartingWith.keySet().iterator();
     while(i.hasNext()){
       key = (String)i.next();
       if (fileName.startsWith(key)) return true;
     }
   }
   if (null!=includedFileNamesEndingIn) {
     i = includedFileNamesEndingIn.keySet().iterator();
     while(i.hasNext()){
       key = (String)i.next();
       if (fileName.endsWith(key)) return true;
     }
   }
   if (null!=includedFileNamesContaining) {
     i = includedFileNamesContaining.keySet().iterator();
     while(i.hasNext()){
       key = (String)i.next();
       if (fileName.indexOf(key)>-1) return true;
     }
   }
   return false;
  }
  
  public void define(Grouping g) throws InvalidSyntax, Exception {
    Comparison c;
    Iterator i = g.getComparisons().iterator();
    String field;
    String op;
    String value;
    while(i.hasNext()) {
      c = (Comparison)i.next();
      field = c.getFieldName();
      op = c.getOperator();
      value = c.getValue();
      if (ROOT_FOLDER.equalsIgnoreCase(field)){
        if (EQUALS.equals(op)) { rootFoldersToSearch.add(value); } //ignore
        else if (NOT_EQUAL.equals(op)) ignoreRootFolder(value);
        else throw new InvalidSyntax(g.toString());
      }
      else if (FOLDER.equalsIgnoreCase(field)){
        if (EQUALS.equals(op)) { absoluteFoldersToSearch.add(value); } //ignore
        else if (NOT_EQUAL.equals(op)) ignoreFolder(value);      
        else throw new InvalidSyntax(g.toString());
      }
      else if (NAME.equalsIgnoreCase(field)){
        if (EQUALS.equals(op)) {
          if (STAR.equals(value)) setIncludeAllFileNames(true);
          else includeFileNameWithSubstring(value);
        }
        else if (NOT_EQUAL.equals(op)) {
          if (STAR.equals(value)) setIncludeAllFileNames(false);
          else ignoreFileNameWithSubstring(value);
        }
        else throw new InvalidSyntax(g.toString());
      }
      else if (MISSING.equalsIgnoreCase(field)){
        if (EQUALS.equals(op)) includeFileNameWithoutSubstring(value);
        else if (NOT_EQUAL.equals(op)) ignoreFileNameWithoutSubstring(value);
        else throw new InvalidSyntax(g.toString());
      }
      else if (TYPE.equalsIgnoreCase(field)){
        if (EQUALS.equals(op)) includeFileType(value);
        else if (NOT_EQUAL.equals(op)) ignoreFileType(value);
        else throw new InvalidSyntax(g.toString());
      }
      else if (PREFIX.equalsIgnoreCase(field)){
        if (EQUALS.equals(op)) includeFileNameWithPrefix(value);
        else if (NOT_EQUAL.equals(op)) ignoreFileNameWithPrefix(value);
        else throw new InvalidSyntax(g.toString());
      }
      else if (SUFFIX.equalsIgnoreCase(field)){
        if (EQUALS.equals(op)) includeFileNameWithSuffix(value);
        else if (NOT_EQUAL.equals(op)) ignoreFileNameWithSuffix(value);
        else throw new InvalidSyntax(g.toString());
      }
      else if (DATE.equalsIgnoreCase(field)){
        if (GREATER_THAN.equals(op)) setEarliestModifiedTime(TimeUtil.getCalendar(value));
        else if (LESS_THAN.equals(op)) setLatestModifiedTime(TimeUtil.getCalendar(value));
        else throw new InvalidSyntax(g.toString());
      }
      else if (SIZE.equalsIgnoreCase(field)){
        if (GREATER_THAN.equals(op)) setMinFileSize(Long.valueOf(value).longValue());
        else if (LESS_THAN.equals(op)) setMaxFileSize(Long.valueOf(value).longValue());
        else throw new InvalidSyntax(g.toString());
      }
      else throw new InvalidSyntax(g.toString());
    }
  }
  
  public List getRootFoldersToSearch() { return rootFoldersToSearch; }
  public List getAbsoluteFoldersToSearch() { return absoluteFoldersToSearch; }
  
  public void setIncludeAllFileNames(boolean b) { includeAllFileNames=b; }

  public void setMinFileSize(long size) { minFileSize=size; }
  public void setMaxFileSize(long size) { maxFileSize=size; }
  
  public void setEarliestModifiedTime(Calendar c) { earliestTime=c.getTimeInMillis(); }
  public void setLatestModifiedTime(Calendar c) { latestTime=c.getTimeInMillis(); }

  public void setIgnoreReadOnlyFiles(boolean b) { ignoreReadOnlyFiles=b; }
  public void setIncludeOnlyReadOnlyFiles(boolean b) { includeOnlyReadOnlyFiles=b; }
  public void setIgnoreReadWriteFiles(boolean b) { ignoreReadWriteFiles=b; }
  public void setIncludeOnlyReadWriteFiles(boolean b) { includeOnlyReadWriteFiles=b; }
  public void setIgnoreHiddenFiles(boolean b) { ignoreHiddenFiles=b; }
  public void setIncludeOnlyHiddenFiles(boolean b) { includeOnlyHiddenFiles=b; }
  
  public void ignoreFolder(String s) { if (null==ignoredFolders)ignoredFolders=new HashMap(); ignoredFolders.put(s.toLowerCase(),s); }
  public void ignoreRootFolder(String s) { if (null==ignoredRootFolders)ignoredRootFolders=new HashMap(); ignoredRootFolders.put(s.toLowerCase(),s); }
  public void includeFileType(String s) { if (null==includedFileTypes)includedFileTypes=new HashMap(); includedFileTypes.put(s.toLowerCase(),s); }
  public void ignoreFileType(String s) { if (null==ignoredFileTypes)ignoredFileTypes=new HashMap(); ignoredFileTypes.put(s.toLowerCase(),s); }
  public void includeFileNameWithPrefix(String s) { if (null==includedFileNamesStartingWith)includedFileNamesStartingWith=new HashMap(); includedFileNamesStartingWith.put(s,s); }
  public void ignoreFileNameWithPrefix(String s) { if (null==ignoredFileNamesStartingWith)ignoredFileNamesStartingWith=new HashMap(); ignoredFileNamesStartingWith.put(s,s); }
  public void includeFileNameWithSuffix(String s) { if (null==includedFileNamesEndingIn)includedFileNamesEndingIn=new HashMap(); includedFileNamesEndingIn.put(s,s); }
  public void ignoreFileNameWithSuffix(String s) { if (null==ignoredFileNamesEndingIn)ignoredFileNamesEndingIn=new HashMap(); ignoredFileNamesEndingIn.put(s,s); }
  public void includeFileNameWithSubstring(String s) { if (null==includedFileNamesContaining)includedFileNamesContaining=new HashMap(); includedFileNamesContaining.put(s,s); }
  public void ignoreFileNameWithSubstring(String s) { if (null==ignoredFileNamesContaining)ignoredFileNamesContaining=new HashMap(); ignoredFileNamesContaining.put(s,s); }
  public void includeFileNameWithoutSubstring(String s) { if (null==includedFileNamesNotContaining)includedFileNamesNotContaining=new HashMap(); includedFileNamesNotContaining.put(s,s); }
  public void ignoreFileNameWithoutSubstring(String s) { if (null==ignoredFileNamesNotContaining)ignoredFileNamesNotContaining=new HashMap(); ignoredFileNamesNotContaining.put(s,s); }
  
  private String folder=null;
  private String fileType=null;
  private String fileName=null;
  private long lastModified=0;
  private boolean isReadOnly=false;
  private boolean isReadWrite=false;
  private boolean isHidden=false;
  private long length=0;
  private static char DOT='.';
 
  private List absoluteFoldersToSearch = new Vector();
  private List rootFoldersToSearch = new Vector();
  
  private long minFileSize=0;
  private long maxFileSize=0;
  
  private long earliestTime=0;
  private long latestTime=0;

  private boolean ignoreReadOnlyFiles=false;
  private boolean includeOnlyReadOnlyFiles=false;
  private boolean ignoreReadWriteFiles=false;
  private boolean includeOnlyReadWriteFiles=false;
  private boolean includeOnlyHiddenFiles=false;
  private boolean ignoreHiddenFiles=false;

  private boolean includeAllFileNames=false;  //Name=* & name!=*-flat;
  
  private Map ignoredFolders=null;     //assume that search is conducted only on included folders - this filter is invoked only for included folders
  private Map ignoredRootFolders=null; //assume that search is conducted only on included folders
  private Map includedFileTypes=null;
  private Map ignoredFileTypes=null;
  private Map includedFileNamesStartingWith=null;
  private Map ignoredFileNamesStartingWith=null;
  private Map includedFileNamesEndingIn=null;
  private Map ignoredFileNamesEndingIn=null;
  private Map includedFileNamesContaining=null;
  private Map ignoredFileNamesContaining=null;
  private Map includedFileNamesNotContaining=null;
  private Map ignoredFileNamesNotContaining=null;
  
 public static String ROOT_FOLDER="root";
 public static String FOLDER="folder";
 public static String NAME="name";
 public static String TYPE="type";
 public static String PREFIX="prefix";
 public static String SUFFIX="suffix";
 public static String MISSING="missing";
 public static String DATE="date";
 public static String SIZE="size";
  
 public static String STAR="*";
 public static String EQUALS=Names.CRITERIA_EQUALS;
 public static String NOT_EQUAL=Names.CRITERIA_NOT_EQUAL;
 public static String LESS_THAN=Names.CRITERIA_LESS_THAN;
 public static String NOT_LESS_THAN=Names.CRITERIA_NOT_LESS_THAN;
 public static String LESS_THAN_OR_EQUAL=Names.CRITERIA_LESS_THAN_OR_EQUAL;
 public static String GREATER_THAN=Names.CRITERIA_GREATER_THAN;
 public static String NOT_GREATER_THAN=Names.CRITERIA_NOT_GREATER_THAN;
 public static String GREATER_THAN_OR_EQUAL=Names.CRITERIA_GREATER_THAN_OR_EQUAL;
}

  /*
//  private long ignoreFileSizeLargerThan=0;
//  private long ignoreFileSizeSmallerThan=0;
//  private long includeFileSizeLargerThan=0;
//  private long includeFileSizeSmallerThan=0;
//  private long ignoreFileearliestTime=0;
//  private long ignoreFilelatestTime=0;
//public void setIncludeOnlyFileChangedAfter(Calendar c) { includeOnlyFileearliestTime=c.getTimeInMillis(); }
//public void setIncludeOnlyFileChangedBefore(Calendar c) { includeOnlyFilelatestTime=c.getTimeInMillis(); }
//public void setIgnoreFileLargerThan(long size) { ignoreFileSizeLargerThan=size; }
//public void setIgnoreFileSmallerThan(long size) { ignoreFileSizeSmallerThan=size; }
  private boolean isInIgnoredTimeSpan() {
    if (ignoreFileearliestTime==0 && ignoreFilelatestTime==0) return false;
    if (ignoreFileearliestTime>0 && ignoreFilelatestTime>0){
      if (lastModified>ignoreFileearliestTime || lastModified<ignoreFilelatestTime) return true;
      return false;
    }
    if (ignoreFileearliestTime>0 && lastModified>ignoreFileearliestTime) return true;
    if (ignoreFilelatestTime>0 && lastModified<ignoreFilelatestTime ) return true;
    return false;
  }
    
    private boolean isIgnoredFileSize() {
    if (ignoreFileSizeLargerThan==0 && ignoreFileSizeSmallerThan==0) return false;
    if (ignoreFileSizeLargerThan>0 && ignoreFileSizeSmallerThan>0){
      if (length >ignoreFileSizeLargerThan && length<ignoreFileSizeSmallerThan ) return true;
      return false;
    }
    if (ignoreFileSizeLargerThan>0 && length >ignoreFileSizeLargerThan) return true;
    if (ignoreFileSizeSmallerThan>0 && length<ignoreFileSizeSmallerThan ) return true;
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
  */
