package zws.database;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 22, 2004, 10:51 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.*;

import java.sql.*;
import java.util.Calendar;


public class MSSQLServer extends DatabaseBase { // !!!totest 
  
  public String getConnectionURL() throws InvalidConfiguration {
    if (null==getHost()) throw new InvalidConfiguration("Host not specified");
    String url = "jdbc:microsoft:sqlserver://"+getHost()+":"+getPort();
    if (null!=getDatabaseName()) url += ";DatabaseName="+getDatabaseName();
    return url;
  }

  protected Connection getConnection() throws NotConnected {
    String url=null;
    try  { 
      url=getConnectionURL();
      return DriverManager.getConnection(url,getUsername(),getPassword()); 
    }
    catch (InvalidConfiguration e) {e.printStackTrace(); throw new NotConnected("Invalid Configuration: " + e.getMessage()); }
    catch (SQLException e) {e.printStackTrace(); throw new NotConnected(url); }
  }
  
  public String getDatabaseType() { return "Microsoft SQLServer"; }
  public String getDriverFQCN() { return driverFQCN; }
  public void setDriverFQCN(String fqcn) { driverFQCN = fqcn; }
  public String getPort() { return port; }
  public void setPort(String s) { port=s; }

  
  public String formatDate(Calendar c) throws InvalidSyntax { return "+++todo"; }
  public Calendar parseDate(String s) throws InvalidSyntax { return null; }
  
  private String driverFQCN="com.microsoft.jdbc.sqlserver.SQLServerDriver";
  private String port="1433";

}
