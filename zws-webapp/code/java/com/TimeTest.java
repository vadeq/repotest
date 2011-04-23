package com;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on November 8, 2004, 5:09 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.text.SimpleDateFormat;
import java.util.*;

public class TimeTest {
  public TimeTest() { }
  public static void main(String[] args) {
    TimeTest t= new TimeTest();
    t.run();
  }
  
  
  //Daylight Saving Time begins for most of the United States at 2 a.m. on the first Sunday of April. 
  //Time reverts to standard time at 2 a.m. on the last Sunday of October. 
  //In the U.S., each time zone switches at a different time. 
  public void run(){
      Date q = new Date();
      long dateTime = q.getTime();
    
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
    GregorianCalendar c = new GregorianCalendar(year, month, day, hour, minute, second);
    SimpleTimeZone tz = new SimpleTimeZone(c.getTimeZone().getRawOffset(), c.getTimeZone().getID());
    tz.setStartRule(Calendar.APRIL, 1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
    tz.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
    c.setTimeZone(tz);


    c = new GregorianCalendar();
    Calendar c2 = new GregorianCalendar();
    c.set(c.MILLISECOND, 0);
    c.set(c.SECOND, 0);
    c2.set(c.MILLISECOND, 0);
    c2.set(c.SECOND, 0);
    c.add(Calendar.HOUR, 1);
    
    {} //System.out.println("c  date = " + c.get(c.YEAR)+ "." + (1 + c.get(c.MONTH))+"." + c.get(c.DATE)+"." +c.get(c.HOUR_OF_DAY)+"." +c.get(c.MINUTE)+"."+c.get(c.SECOND));
    {} //System.out.println("c2 date = " + c2.get(c.YEAR)+ "." + (1 + c2.get(c.MONTH))+"." + c2.get(c.DATE)+"." +c2.get(c.HOUR_OF_DAY)+"." +c2.get(c.MINUTE)+"."+c2.get(c.SECOND));

    boolean isUpdated = c.after(c2);
    if (isUpdated) {
      c2.add(Calendar.HOUR, 1);
      if (c.equals(c2)) isUpdated=false;
    }
    if (isUpdated) {} //System.out.println("file has been updated.");
    else {} //System.out.println("file is not updated.");
  }
}
