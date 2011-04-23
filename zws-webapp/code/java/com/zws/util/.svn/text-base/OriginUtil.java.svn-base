package com.zws.util;

import com.zws.application.Constants;

import java.util.StringTokenizer;

public class OriginUtil {
  public OriginUtil(){}

  public static String getServiceName(String origin){
    StringTokenizer toks = new StringTokenizer(origin, Constants.ORIGIN_DELIMITER);
    if (!toks.hasMoreTokens()) return null;
    return toks.nextToken();
  }

  public static String updateServiceName(String origin, String serviceName) {
    String newOrigin = serviceName;
    StringTokenizer toks = new StringTokenizer(origin, Constants.ORIGIN_DELIMITER);
    if (!toks.hasMoreTokens()) return null;
    toks.nextToken();
    if (!toks.hasMoreTokens()) return null;
    while (toks.hasMoreElements())
      newOrigin += Constants.ORIGIN_DELIMITER + toks.nextToken();
    return newOrigin;
  }

  public static String getDatasourceName(String origin){
    StringTokenizer toks = new StringTokenizer(origin, Constants.ORIGIN_DELIMITER);
    if (!toks.hasMoreTokens()) return null;
    toks.nextToken();
    if (!toks.hasMoreTokens()) return null;
    return toks.nextToken();
  }

  public static String getName(String origin){
    StringTokenizer toks = new StringTokenizer(origin, Constants.ORIGIN_DELIMITER);
    if (!toks.hasMoreTokens()) return null;
    toks.nextToken();
    if (!toks.hasMoreTokens()) return null;
    toks.nextToken();
    if (!toks.hasMoreTokens()) return null;
    return toks.nextToken();
  }

  public static String getIdentification(String origin){
    StringTokenizer toks = new StringTokenizer(origin, Constants.ORIGIN_DELIMITER);
    if (!toks.hasMoreTokens()) return null;
    toks.nextToken();
    if (!toks.hasMoreTokens()) return null;
    toks.nextToken();
    if (!toks.hasMoreTokens()) return null;
    String id="";
    while (toks.hasMoreTokens())
      id += toks.nextToken();
    return id;
  }

  public static String updateDatasourceName(String origin, String datasourceName){
    String newOrigin = null;
    StringTokenizer toks = new StringTokenizer(origin, Constants.ORIGIN_DELIMITER);
    if (!toks.hasMoreTokens()) return null;
    newOrigin = toks.nextToken();
    if (!toks.hasMoreTokens()) return null;
    toks.nextToken();
    newOrigin += Constants.ORIGIN_DELIMITER + datasourceName;
    if (!toks.hasMoreTokens()) return null;
    while (toks.hasMoreElements())
      newOrigin += Constants.ORIGIN_DELIMITER + toks.nextToken();
    return newOrigin;
  }

  public static String updateSources(String origin, String serviceName, String datasourceName){
    String newOrigin = null;
    StringTokenizer toks = new StringTokenizer(origin, Constants.ORIGIN_DELIMITER);
    if (!toks.hasMoreTokens()) return null;
    toks.nextToken();
    if (!toks.hasMoreTokens()) return null;
    toks.nextToken();
    if (!toks.hasMoreTokens()) return null;
    newOrigin = serviceName + Constants.ORIGIN_DELIMITER + datasourceName;
    while (toks.hasMoreElements())
      newOrigin += Constants.ORIGIN_DELIMITER + toks.nextToken();
    return newOrigin;
  }
}
