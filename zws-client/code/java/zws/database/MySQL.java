package zws.database;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 22, 2004, 9:25 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.*;
import zws.exception.InvalidConfiguration;
import zws.exception.InvalidSyntax;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MySQL extends DatabaseBase {
  public String getDatabaseType() { return "MySQL"; }

  public String getConnectionURL() throws InvalidConfiguration{
    String err="";
    if (null==getHost()) err="Host not specified ";
    if (null==getDatabaseName()) err+="Database name not specified";
    if (!"".equals(err)) throw new InvalidConfiguration(err);
    String url = "jdbc:mysql://"+getHost()+":"+getPort()+"/"+getDatabaseName();
    if (null==getUsername()) return url;
    url += "?user="+getUsername();
    if (null!=getPassword()) url += "&password="+getPassword();
    return url;
  }
  
	protected Connection getConnection() throws NotConnected, DriverNotFound {
	    if (null==getDriver()) registerDriver();
			String url=null;
			try {
		    url = getConnectionURL();
		    return DriverManager.getConnection(url);
			}
			catch (InvalidConfiguration e) {e.printStackTrace(); throw new NotConnected("Invalid Configuration: " + e.getMessage()); }
			catch (SQLException e) {e.printStackTrace(); throw new NotConnected(url); }
		}
  
  
  public String getDriverFQCN() { return driverFQCN; }
  public void setDriverFQCN(String fqcn) { driverFQCN = fqcn; }
  public String getPort() { return port; }
  public void setPort(String s) { port=s; }

  public String formatDate(Calendar c) throws InvalidSyntax { 
    StringBuffer b = new StringBuffer();
    return b.append(c.get(Calendar.YEAR)).append(DASH).append(c.get(Calendar.MONTH)).append(DASH).append(c.get(Calendar.DAY_OF_MONTH)).append(SPACE).append(c.get(Calendar.HOUR_OF_DAY)).append(COLON).append(c.get(Calendar.MINUTE)).append(COLON).append(c.get(Calendar.SECOND)).toString();
  }
  public Calendar parseDate(String s) throws InvalidSyntax { 
	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	 
   Calendar c = new GregorianCalendar();
   try{ c.setTime(formatter.parse(s)); }
   catch (java.text.ParseException e) { throw new InvalidSyntax(e.getMessage()); }
   return c;
  }
    
  private String driverFQCN="org.gjt.mm.mysql.Driver";
  private String port="3306";
}
