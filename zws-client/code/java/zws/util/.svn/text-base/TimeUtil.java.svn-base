package zws.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 7, 2004, 2:22 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;

import java.util.*;

public class TimeUtil {
    
  public static String timeStamp() { return asString(new GregorianCalendar()); }
  
  public static Calendar getCalendar(String s){
    if (null==s) throw new NullPointerException();
    int year=0,month=0,date=0,hour=0,min=0,sec=0;
    StringTokenizer tokens = new StringTokenizer(s, Names.TIME_DELIMITER);
    if (tokens.hasMoreTokens()) year = Integer.valueOf(tokens.nextToken()).intValue();
    if (tokens.hasMoreTokens()) month = Integer.valueOf(tokens.nextToken()).intValue();
    if (tokens.hasMoreTokens()) date = Integer.valueOf(tokens.nextToken()).intValue();
    if (tokens.hasMoreTokens()) hour = Integer.valueOf(tokens.nextToken()).intValue();
    if (tokens.hasMoreTokens()) min = Integer.valueOf(tokens.nextToken()).intValue();
    if (tokens.hasMoreTokens()) sec = Integer.valueOf(tokens.nextToken()).intValue();
    return new GregorianCalendar(year,month-1,date,hour,min,sec);
  }
  
  public static String now() { return asString (new GregorianCalendar()); }
  
  public static String asString(Calendar c) {
    int y,m,d,h,min,sec;
    y = c.get(Calendar.YEAR);
    m = c.get(Calendar.MONTH)+1;
    d = c.get(Calendar.DAY_OF_MONTH);
    h = c.get(Calendar.HOUR_OF_DAY);
    min = c.get(Calendar.MINUTE);
    sec = c.get(Calendar.SECOND);
    String s = "" + y + DOT;
    if (m<10) s+=ZERO;
    s += m + DOT;
    if (d<10) s+=ZERO;
    s += d + DOT;
    if (h<10) s+=ZERO;
    s += h + DOT;
    if (min<10) s+=ZERO;
    s += min + DOT;
    if (sec<10) s+=ZERO;
    s += sec;
    return s;
  }
  
  private static String DOT=".";
  private static String ZERO = "0";
}
