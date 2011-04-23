package zws.datasource.intralinkSQL;/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.bill.intralink.BillOfMaterials;
import zws.data.Metadata;
import zws.datasource.DatasourceBase;
import zws.datasource.intralink.IntralinkSource;
import zws.exception.NameNotFound;
import zws.exception.UnsupportedOperation;
import zws.origin.IntralinkOrigin;
import zws.security.Authentication;
import zws.service.datasource.DatasourceSvc;
//impoer zws.util.Logwriter;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ProiOracleSource extends DatasourceBase {
  public String getType() { return IntralinkOrigin.FROM_ORACLE8i; }
  public zws.search.SearchAgent materializeSearchAgent() { 
    SearchAgent agent = new SearchAgent();
    agent.setDatasource(this);
    return agent;
  }
  public zws.search.SearchAgent materializeLatestSearchAgent() { return materializeSearchAgent(); }
  public zws.search.SearchAgent materializeLatestRevSearchAgent() { return materializeSearchAgent(); }

  public boolean contains(String name, Authentication id) throws Exception {
    throw new UnsupportedOperation(getServerName() + "|"+getName()+".contains(origin, id)");
  }
  public void inactivate() {} 

  private void registerDriver() throws ClassNotFoundException {
    {}//Logwriter.printOnConsole("SQL Driver: Loading : " + driverClassName);
    if (null==SQLDriver) {
      SQLDriver = Class.forName(driverClassName);
      {}//Logwriter.printOnConsole("SQL Driver: Loaded");
    }
    else {} {}//Logwriter.printOnConsole("SQL Driver: "+SQLDriver.getName() +" already Loaded");
  }
  public synchronized Connection getConnection() throws Exception {
    {}//Logwriter.printOnConsole("SQL Connection");
    Connection con;
    try { registerDriver(); }
    catch (Exception e) { e.printStackTrace(); throw e; }
    if (connectionPool.isEmpty()) {
      try {
        {}//Logwriter.printOnConsole("SQL Connection: connecting using URL=" + getConnectionURL());
        con = DriverManager.getConnection( getConnectionURL()); 
      }
      catch (Exception e) {
        try { 
          {}//Logwriter.printOnConsole("SQL Connection: connecting using URL=" + getConnectionURL() + " user =  "+ getUsername() +" password =" + getPassword());
          con = DriverManager.getConnection( getConnectionURL(), getUsername(), getPassword()); 
        }
        catch (Exception e2) {
          try {
            java.util.Properties properties = new java.util.Properties();
            properties.setProperty("user", getUsername());
            properties.setProperty("password", getPassword());
            {}//Logwriter.printOnConsole("SQL Connection: connecting using URL=" + getConnectionURL() + "properties = " + properties );
            con = DriverManager.getConnection( getConnectionURL(), getUsername(), getPassword()); 
          }
          catch (Exception e3) {
            e.printStackTrace();
            throw new Exception ("could not connect to Database: " + getName());
          }
        }
      }
    }
    else con = (Connection)connectionPool.remove(connectionPool.size()-1);
    if (null!= con) activeConnections.add(con);
    return con;
    //example connection string: getConnectionURL()+";DatabaseName="+ getDatabaseName(),getUsername(),getPassword()
  }

  public synchronized void release(Connection con) throws Exception {
    if (activeConnections.remove(con)) connectionPool.add(con); ///return the connection back to the pool
    else throw new Exception("Can nt release a connection - not created by datasource: " + getName());
  }
 
  public String objectName(String name) { return getIntralinkSource().objectName(name); }
 /*   
  public Origin createOrigin(String branch, String name, String revision, String version, String createdOn) throws Exception {
    return getIntralinkSource().createOrigin(branch, name, revision, version, createdOn);
  }
public Origin createOrigin(Metadata data) throws Exception {
	//return getIntralinkSource().createOrigin(data);
	String branch, name, revision, version, createdOn;
	branch = data.get(BRANCH);
	name = objectName(data.getName());
	revision = data.get(REVISION);
	version = data.get(VERSION);
  createdOn = data.get(CREATED_ON);
	Origin o = createOrigin(branch, name, revision, version, createdOn);
	o.setName(name);
	return o;
}

  
public String getBranchFromOrigin( Origin o ) throws InvalidValue {
	return getIntralinkSource().getBranchFromOrigin(o);
}
public String getRevisionFromOrigin( Origin o ) throws InvalidValue {
	return getIntralinkSource().getRevisionFromOrigin(o);
}
public String getVersionFromOrigin( Origin o ) throws InvalidValue {
	return getIntralinkSource().getVersionFromOrigin(o);
}
*/
  public Metadata findMetadataForPackage(Metadata data, boolean includeHistory, Authentication id) throws Exception { return data; }

  public File extractBinary(IntralinkOrigin o, Authentication id, File outputDir) throws Exception {
    return getIntralinkSource().exportBinary(o, outputDir, id);
  }
  
  public Metadata find(IntralinkOrigin o, Authentication id) throws Exception {
    return getIntralinkSource().find(o, id); //+++ convert to sql
  }
  
  public BillOfMaterials getAsStoredBill(IntralinkOrigin origin) throws Exception {
    return getIntralinkSource().getAsStoredBill(origin); //+++ convert to sql
  }
  public BillOfMaterials getLatestBill(IntralinkOrigin origin) throws Exception {
    return getIntralinkSource().getLatestBill(origin); //+++ convert to sql
  }
  public BillOfMaterials getBill(IntralinkOrigin origin, boolean asStored) throws Exception {
    return getIntralinkSource().getBill(origin, asStored); //+++ convert to sql
  }

  public String getDriverClassName() { return driverClassName; }
  public void setDriverClassName(String s) { driverClassName=s; }
  public String getConnectionURL() { return connectionURL; }
  public void setConnectionURL(String s) { connectionURL = s; }
  public String getUsername() { return username; }
  public void setUsername(String s) { username=s; }
  public String getPassword() { return password; }
  public void setPassword(String s) { password=s; }
  public String getIntralinkSourceName() { return intralinkSourceName; }
  private IntralinkSource getIntralinkSource() { return intralinkSource; }
  public void setIntralinkSourceName(String s) throws NameNotFound {  
  	intralinkSource = (IntralinkSource) DatasourceSvc.find(s); 
  	intralinkSourceName=s; 
  }
  
  private IntralinkSource intralinkSource;
  private String connectionURL;
  private String username; 
  private String password;
  private String intralinkSourceName;
  private String tableNames;

  private String driverClassName;
  private transient Class SQLDriver;
  private transient List connectionPool = new ArrayList();
  private transient List activeConnections = new ArrayList();
  
  public static String PINAME = "PINAME";
  public static String BRANCH = "BRPATH";
  public static String REVISION = "PIVREV";
  public static String VERSION = "PIVVER";
  public static String CREATED_ON = "CREATEDON";  //+++ find actual Oracle field name
  private static String LITERAL = "'";

}
