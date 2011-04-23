package zws.database;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 14, 2004, 10:49 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.*;

import java.sql.*;
import java.util.Calendar;

public class Oracle8i extends DatabaseBase {
  public String getDatabaseType() { return "Oracle 8i"; }

  public String getConnectionURL() throws InvalidConfiguration {
    String err="";
    if (null==getHost()) err="Host not specified ";
    if (null==getSID()) err+="SID not specified";
    if (!"".equals(err)) throw new InvalidConfiguration(err);
    return "jdbc:oracle:thin:@"+getHost()+":"+getPort()+":"+getSID(); 
  }

  protected Connection getConnection() throws NotConnected {
    String url=null;
    try  { 
      url=getConnectionURL();
      return DriverManager.getConnection(url, getUsername(), getPassword()); 
    }
    catch (InvalidConfiguration e) {e.printStackTrace(); throw new NotConnected("Invalid Configuration: " + e.getMessage()); }
    catch (SQLException e) {e.printStackTrace(); throw new NotConnected(url); }
  }  

  public String getDriverFQCN() { return driverFQCN; }
  public void setDriverFQCN(String fqcn) { driverFQCN = fqcn; }
  public String getPort() { return port; }
  public void setPort(String s) { port=s; }   
  public String getSID() { return sid; }
  public void setSID(String s) { sid=s; }

  public String formatDate(Calendar c) throws InvalidSyntax { return "+++todo"; }
  public Calendar parseDate(String s) throws InvalidSyntax { return null; }
    
  private String driverFQCN="oracle.jdbc.driver.OracleDriver";
  private String port="3306";
  private String sid=null;
}
