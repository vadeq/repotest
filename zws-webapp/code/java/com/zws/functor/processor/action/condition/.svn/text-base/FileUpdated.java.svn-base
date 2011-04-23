package com.zws.functor.processor.action.condition;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 30, 2003, 10:13 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import com.zws.application.Constants;
import com.zws.domo.document.Document;
import com.zws.util.FileNameUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileUpdated extends Conditional {


  //Daylight Saving Time begins for most of the United States at 2 a.m. on the first Sunday of April. 
  //Time reverts to standard time at 2 a.m. on the last Sunday of October. 
  //In the U.S., each time zone switches at a different time. 
  
  //Airborn fix: data entered into database has invalid daylight savings time.
  //Hack is in place to add hour for invlaid DST entry in DB.
  
  public boolean isTrue() {
    Document doc = getDocument();
    if (null!=getUseLogData()) {
      doc = (Document) getActionLog().getData(getUseLogData());
      {} //System.out.println("using document from log");
      {} //System.out.println("date=" + doc.get(getLastModifiedMetaData()));
    }
    Calendar priorModification=null;
    Calendar lastModification=null;
    String file;
    if (null!= fileNameMetaData) {
      if ("name".equalsIgnoreCase(fileNameMetaData)) file = doc.getName();
      else file= doc.get(fileNameMetaData);
    }
    else file=fileName;
    if (null!=type) file = FileNameUtil.convertType(file, type);
    if (null!= pathMetaData) file= doc.get(pathMetaData) + Constants.FILE_SEPARATOR + file;
    else if (null!= path) file= path + Constants.FILE_SEPARATOR + file;
    File f = new File (file);
    {} //System.out.println("comparing against binary: " + f.getAbsolutePath());
    priorModification = createDateTime(doc.get(getLastModifiedMetaData()));
    lastModification = createDateTime(f.lastModified());
    boolean isUpdated = lastModification.after(priorModification);
    if (isUpdated) { //Airborn hack to check for invalid DST in DB
      priorModification.add(Calendar.HOUR, 1);
      if (priorModification.equals(lastModification)) isUpdated=false;
    }
    return isUpdated;
    
  }
  
  private Calendar createDateTime(String dateTime) {  //+++ turn into functor
    String date, time, ampm;
    int month, day, year;
    int hour, minute, second;
    StringTokenizer tokens = new StringTokenizer(dateTime, " ");
    date = tokens.nextToken();
    time = tokens.nextToken();
    ampm = tokens.nextToken();
    
    StringTokenizer dateTokens = new StringTokenizer(date, "/");
    month = Integer.valueOf(dateTokens.nextToken()).intValue();
    day = Integer.valueOf(dateTokens.nextToken()).intValue();
    year = Integer.valueOf(dateTokens.nextToken()).intValue();
    
    StringTokenizer timeTokens = new StringTokenizer(time, ":");
    hour = Integer.valueOf(timeTokens.nextToken()).intValue();
    minute = Integer.valueOf(timeTokens.nextToken()).intValue();
    second = Integer.valueOf(timeTokens.nextToken()).intValue();
    if (ampm.equalsIgnoreCase("AM") && hour == 12) hour = 0;
    else if (ampm.equalsIgnoreCase("PM") && hour == 12) hour = 12;
    else if (ampm.equalsIgnoreCase("PM")) hour += 12;
    {} //System.out.println("string date = " + year + "." + month +"." + day +"." +hour +"." +minute +"."+second);
    return new GregorianCalendar(year, month, day, hour, minute, second);
  }
  
  private Calendar createDateTime(long dateTime) { //+++ turn into functor
    int year, month, day, hour,minute,second;
    SimpleDateFormat fyear =new SimpleDateFormat("yyyy");
    SimpleDateFormat fmonth =new SimpleDateFormat("MM");
    SimpleDateFormat fday =new SimpleDateFormat("dd");
    SimpleDateFormat fhour =new SimpleDateFormat("HH");
    SimpleDateFormat fmin =new SimpleDateFormat("mm");
    SimpleDateFormat fsec =new SimpleDateFormat("ss");
    Date d = new Date(dateTime);
    year = Integer.valueOf(fyear.format(d)).intValue();
    month = Integer.valueOf(fmonth.format(d)).intValue();
    day = Integer.valueOf(fday.format(d)).intValue();
//    hour = Integer.valueOf(fhour.format(d)).intValue() - 1; // hour is from 1 - 24
    hour = Integer.valueOf(fhour.format(d)).intValue();
    minute = Integer.valueOf(fmin.format(d)).intValue();
    second = Integer.valueOf(fsec.format(d)).intValue();    
    {} //System.out.println("long date = " + year + "." + month +"." + day +"." +hour +"." +minute +"."+second);
    return new GregorianCalendar(year, month, day, hour, minute, second);
  }

  public String getFileName() { return fileName; }
  public void setFileName(String s) { fileName=s; }
  public String getPath() { return path; }
  public void setPath(String s) { path=s; }
  public String getFileNameMetaData() { return fileNameMetaData; }
  public void setFileNameMetaData(String s) { fileNameMetaData=s; }
  public String getPathMetaData() { return pathMetaData; }
  public void setPathMetaData(String s) { pathMetaData=s; }
  public String getLastModifiedMetaData() { return lastModifiedMetaData;  }
  public void setLastModifiedMetaData(String s) { lastModifiedMetaData = s; }
  public String getUseLogData() { return useLogData; }
  public void setUseLogData(String s) { useLogData = s; }
  
  public String getType() { return type; }
  public void setType(String s) { type=s; }
  public int getMinimumSize() { return minimumSize; }
  public void setMinimumSize(int i) { minimumSize=i; }

  private String fileName=null;
  private String path=null;
  private String fileNameMetaData=null;
  private String pathMetaData=null;
  private String type=null;
  private String lastModifiedMetaData=Constants.METADATA_FILE_LAST_MODIFIED;
  private String useLogData=null;
  
  private int minimumSize=-1;
}