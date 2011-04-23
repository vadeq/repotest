package com.zws.service.repository;

import com.zws.application.Config;
import com.zws.application.Constants;
//import com.zws.service.Database;
import com.zws.util.KeyValue;

import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.util.*;


public class MetadataConfigurator {
/*
  private static MetadataConfigurator service;
  private static String dataSourceName = Config.getProperty(Config.DATA_SOURCE_APPLICATION_DATABASE);

  private MetadataConfigurator(){}
  public static MetadataConfigurator getService(){ if (null==service) service = new MetadataConfigurator(); return service; }

  private static void dumpConfigFile() throws Exception {
    File configFile = new File(Config.getProperty(Config.CFG_METADATA));
    configFile.delete();
    configFile.createNewFile();
    FileWriter outFile = new FileWriter(configFile);
    Collection atts = getConfiguration();
    Iterator i = atts.iterator();
    KeyValue pair;
    while (i.hasNext()) {
      pair = (KeyValue) i.next();
      outFile.write(pair.getKey());
      if (i.hasNext()) outFile.write(Constants.LINE_SEPARATOR);
    }
    outFile.close();
  }
  public static Collection getConfiguration() throws SQLException{
    Connection connection = Database.connect(dataSourceName);
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM attribute ORDER BY ID");
    Collection c = unmarshallConfiguration(resultSet);
//    resultSet.close();
    Database.release(connection);
    return c;
  }

  public static Collection getVisibleConfiguration() throws SQLException {
    Connection connection = Database.connect(dataSourceName);
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM attribute WHERE visible=1 ORDER BY ID");
    Collection c = unmarshallConfiguration(resultSet);
//    resultSet.close();
    Database.release(connection);
    return c;
  }

  public static void removeAttribute(String name) throws Exception{
    Connection connection = Database.connect(dataSourceName);
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("DELETE FROM attribute WHERE name='"+name+"'");
//    resultSet.close();
    Database.release(connection);
    dumpConfigFile();
  }

  public static void addAttribute(String name) throws Exception{
    Connection connection = Database.connect(dataSourceName);
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("DELETE FROM attribute WHERE name='"+name+"'");
//    resultSet.close();
    resultSet = statement.executeQuery("INSERT into attribute (name) VALUES('"+name+"')");
//    resultSet.close();
    Database.release(connection);
    dumpConfigFile();
  }

  public static String[] getVisibleAttributes() throws SQLException{
    int idx=0;
    KeyValue pair;
    Collection c = getVisibleConfiguration();
    Object[] a = getVisibleConfiguration().toArray();
    String[] atts = new String[c.size()];
    Iterator i = c.iterator();
    while (i.hasNext()) {
      pair = (KeyValue)i.next();
      atts[idx++] = pair.getKey();
    }
    return atts;
  }

  public static void setVisibleAttributes(String[] atts) throws SQLException {
    int i;
    Connection connection = Database.connect(dataSourceName);
    Statement statement = connection.createStatement();
    String sql = "UPDATE attribute SET visible=0";
    ResultSet resultSet = statement.executeQuery(sql);
//    resultSet.close();
    sql = "UPDATE attribute SET visible=1 ";
    for (i=0; i<atts.length; i++)
      resultSet = statement.executeQuery(sql + "WHERE name='"+atts[i]+"'");
//    resultSet.close();
    Database.release(connection);
  }

  private static Collection unmarshallConfiguration(ResultSet r) throws SQLException{
    Collection c = new Vector();
    String attributeName;
    KeyValue pair;
    while (r.next()){
      attributeName = r.getString("name");
      pair = new KeyValue(attributeName, attributeName);
      c.add(pair);
    }
    return c;
  }

  public static void setProcessTime(int id, Calendar c) throws SQLException{
    Time t = new Time(c.getTime().getTime());
    Connection connection = Database.connect(dataSourceName);
    Statement statement = connection.createStatement();
    String sql = "UPDATE processingTime SET startTime='"+t.toString()+"' WHERE ID="+id;
    ResultSet resultSet = statement.executeQuery(sql);
//    resultSet.close();
    Database.release(connection);
  }
  public static Calendar getProcessTime(int id) throws SQLException{
    int h, m;
    Calendar c;
    Time t=null;
    Connection connection = Database.connect(dataSourceName);
    Statement statement = connection.createStatement();
    String sql = "SELECT startTime from processingTime WHERE ID="+id;
    ResultSet resultSet = statement.executeQuery(sql);
    while (resultSet.next()) t = resultSet.getTime("startTime");
//    resultSet.close();
    Database.release(connection);
    if (null==t) throw new SQLException("No processing time found for ID="+id);
    c = new GregorianCalendar();
    c.setTime(new java.util.Date(t.getTime()));
    return c;
  }

  //temporary persistence
  private static Collection configuration = new Vector();
  private static Map configurationMap = new HashMap();
  private static Map visibleAttributes = new HashMap();
  private static String keySHOW_ATTRIBUTES = "on";

  private static String processHour1="04";
  private static String processHour2="13";
  private static String processMinute1="25";
  private static String processMinute2="15";
  */
}
