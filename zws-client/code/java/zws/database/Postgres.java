package zws.database;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 22, 2004, 10:09 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.InvalidConfiguration;
import zws.exception.InvalidSyntax;

import java.util.Calendar;

public class Postgres extends DatabaseBase { //!!! totest 

  public String getConnectionURL() throws InvalidConfiguration { 
    //jdbc:postgresql://host/database
    //jdbc:postgresql://host:port/database
    return null;
  }

  public String getDatabaseType() { return "Postgres"; }
  public String getDriverFQCN() { return driverFQCN; }
  public void setDriverFQCN(String fqcn) { driverFQCN = fqcn; }
  public String getPort() { return port; }
  public void setPort(String s) { port=s; }
   
  public String formatDate(Calendar c) throws InvalidSyntax { return "+++todo"; }
  public Calendar parseDate(String s) throws InvalidSyntax { return null; }
  
  private String driverFQCN="org.postgresql.Driver";
  private String port="5432";

}