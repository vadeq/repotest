package zws.origin;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 10, 2004, 1:09 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.exception.InvalidComparison;
import zws.util.RoutedData;

import java.io.*;
import java.util.Calendar;
import java.util.List;

public interface Origin extends RoutedData, Serializable {

  public InputStream getBinary();
  public File exportBinary(File directory);

  public String getDomainName();
  public String getServerName();
  public String getDatasourceName() ;
  public String getRepositoryName() ;
  public String getDatasourceType();
  public Calendar getTimeOfCreation();
  public long getTimeOfCreationInMillis();
  public String getName();
  public String getLocation();
  public String getState();    //state of data is derived information

  public String getFileType();
  public String getUniqueID();
  public String getUniqueIDDisplay();
  public String getUniqueSequence(); //unique sequence should never include the name
  public List getStateChangeEvents(Origin newOrigin) throws InvalidComparison;

  //public void load(String originAsString) throws CanNotMaterialize;
  //public void loadUniqueSequence(String sequence) throws CanNotMaterialize;
  //public void loadUniqueID(String uid) throws CanNotMaterialize;
  //public void loadState(String state);

  public boolean equals(Object o);
  public boolean isRenamed(Origin o);
  public boolean isTheSameAs(Origin o);
  public boolean isExactlyTheSame(Origin o);
  public boolean isFromSameDomain(Origin o);
  public boolean isFromSameServer(Origin o);
  public boolean isFromThisServer(Origin o);
  public boolean isFromSameDatasource(Origin o);
  public boolean isFromSameDatasourceType(Origin o);
  public boolean hasSameUniqueID(Origin o);
  public boolean hasSameName(Origin o);
  public boolean isEarlier(Origin o);
  public boolean isLater(Origin o);

  public Origin copy();
  public String toString();
  public String toXML();

  public static String NA=Names.NA;
  public static String delim = Names.ORIGIN_DELIMITER;
  public static String FROM_SQL = "sql";
  public static String FROM_WORKSPACE= "workspace";
  public static String FROM_AGILE = "agile";
  public static String FROM_AGILE_ECO = "agile-eco";
  public static String FROM_AGILE_SDK = "agile-sdk";
  public static String FROM_TEAMCENTER_10 = "TC-10";
  public static String FROM_TEAMCENTER_10_ITEM = "tc-10-item";
  public static String FROM_TEAMCENTER_10_REV = "tc-10-rev";
  public static String FROM_TEAMCENTER_10_DATASET = "tc-10-dataset";
  public static String FROM_TEAMCENTER_10_IMANFILE = "tc-10-imanfile";
  public static String FROM_ILINK = "ilink";
  public static String FROM_ILINK30 = "ilink-3.0";
  public static String FROM_ILINK31 = "ilink-3.1";
  public static String FROM_ILINK32 = "ilink-3.2";
  public static String FROM_ILINK33 = "ilink-3.3";
  public static String FROM_ILINK34 = "ilink-3.4";
  public static String FROM_ILINK_8 = "ilink-8";
  public static String FROM_ILINK_PART_NUMBER = "part-number-ilink";
  public static String FROM_PACKAGE = "package";
  public static String FROM_DISK = "disk";
  public static String FROM_FTP_SITE = "ftp";
  public static String FROM_URL_LOCATION = "url";
  public static String FROM_ORACLE8i = "Oracle-8i";
  public static String FROM_HARRIS_ILINK = "harris-ilink";

  //Demo stuff
  public static String FROM_AGILE_CAD_MODEL = "agile-cad-model";
  public static String FROM_AGILE_CAD_DOCUMENT = "agile-cad-document";
}
