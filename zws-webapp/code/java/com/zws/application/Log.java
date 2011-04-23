package com.zws.application;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on November 5, 2003, 10:58 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class Log {
  private static FileOutputStream getLogFile(String key) throws Exception {
    if (!logFiles.containsKey(key)) {
      FileOutputStream out = new FileOutputStream(logDirectory + Constants.FILE_SEPARATOR + key);
      logFiles.put(key, out);
    }
    return (FileOutputStream)logFiles.get(key);
  }
  
  public void purge() {
    //Remove log files older than D days
    
  }
  
  public static void write(String key, String log) throws Exception { write(getLogFile(key), log + Constants.LINE_SEPARATOR); }
  
  public static void writeTime(String key, String log) throws Exception { 
    SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd G 'at' hh:mm:ss a zzz");
    Date d = new Date();
    write(getLogFile(key), log + ": " + formatter.format(d) + Constants.LINE_SEPARATOR ); 
  }
  
  private static void write(OutputStream stream, String s) throws Exception { 
    char[] chars = s.toCharArray();
    for (int idx=0; idx < chars.length; stream.write(chars[idx++]));
  }
  
  public static void close(String key) throws Exception {
    if (!logFiles.containsKey(key)) return;
    FileOutputStream out = (FileOutputStream) logFiles.remove(key);
    if (null!=out) out.close();
  }

  private static Map logFiles = new HashMap();
  private static String logDirectory = Properties.get(Properties.logDirectory);
}