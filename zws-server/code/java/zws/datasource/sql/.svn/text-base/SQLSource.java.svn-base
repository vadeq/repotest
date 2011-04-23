package zws.datasource.sql;/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.data.Metadata;
import zws.datasource.DatasourceBase;
import zws.datasource.sql.finder.FTP;
import zws.exception.InvalidOrigin;
import zws.exception.UnsupportedOperation;
import zws.origin.Origin;
import zws.security.Authentication;
import zws.util.KeyValue;
//impoer zws.util.{}//Logwriter;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class SQLSource extends DatasourceBase {
  public String getType() { return Origin.FROM_SQL; }
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
    else {}{}//Logwriter.printOnConsole("SQL Driver: "+SQLDriver.getName() +" already Loaded");
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
  
  public Origin createOrigin(Metadata metadata){
    /*
    Origin origin = new Origin();
    origin.setServerName(Properties.get(Names.SERVER_NAME));
    origin.setDatasourceName(getName());
    origin.setUniqueID(getUniqueID(metadata));
    origin.setName(lookupName(metadata, getNameSpec()));
    return origin;
     **/
    return null;
  }
  private String lookupName(Metadata metadata, String nameSpec) { 
  	String name="";
	StringTokenizer tokens = new StringTokenizer(nameSpec, Names.ORIGIN_DELIMITER);
	while (tokens.hasMoreTokens()) 
	  name += parseNameSpecToken(metadata, tokens.nextToken().trim());
	return name;
  }
  
  private String parseNameSpecToken(Metadata metadata, String field) { 
  	String s = "";
	if (-1 <field.indexOf(LITERAL)) return field.substring(field.indexOf(LITERAL)+1, field.lastIndexOf(LITERAL));
	s += metadata.get(field);
	return s.trim();
  }
  
  
  public String getUniqueID(Metadata metadata) {
    String id=null;
    StringTokenizer tokens = new StringTokenizer(uniqueIDColumns, Names.ORIGIN_DELIMITER);
    if (tokens.hasMoreTokens()) id = metadata.get(tokens.nextToken().trim()).trim();
    while (tokens.hasMoreTokens()) id += Names.ORIGIN_DELIMITER + metadata.get(tokens.nextToken().trim()).trim();
    return id;
  }

  public String getValue(Origin origin, String fieldName){
    String field = uniqueIDColumns;  //initialize in case of only 1 unique ID column
    String value = origin.getUniqueID(); //initialize in case of only 1 unique ID column
    StringTokenizer ids = new StringTokenizer(origin.getUniqueID(), Names.ORIGIN_DELIMITER);
    StringTokenizer fields = new StringTokenizer(uniqueIDColumns, Names.ORIGIN_DELIMITER);
    if (fields.hasMoreTokens()) { 
      field=fields.nextToken().trim();
      value = ids.nextToken();
    }
    while (fields.hasMoreTokens() && !fieldName.trim().equals(field)){
      field=fields.nextToken().trim();
      value = ids.nextToken();
    }
    return value;    
  }
  
  public void addFieldType(KeyValue k) { fieldTypes.put(k.getKey(), k); }

 
  public boolean contains(Origin o, Authentication id) throws Exception {
    boolean exists;
    StringTokenizer originTokens = new StringTokenizer(o.getUniqueID(), Names.ORIGIN_DELIMITER);
    StringTokenizer uniqueIDFieldTokens = new StringTokenizer(uniqueIDColumns, Names.ORIGIN_DELIMITER);
    if (originTokens.countTokens()!= uniqueIDFieldTokens.countTokens()) throw new InvalidOrigin(o);
    String whereClause="";
    if (uniqueIDFieldTokens.hasMoreTokens())
      whereClause = uniqueIDFieldTokens.nextToken() +"="+ "'"+originTokens.nextToken()+"'";
    while (uniqueIDFieldTokens.hasMoreTokens())
      whereClause += " AND " + uniqueIDFieldTokens.nextToken() +"="+ "'"+originTokens.nextToken()+"'";
    Connection c = getConnection();
    PreparedStatement s = c.prepareStatement("SELECT "+getNameSpec()+" from "+getDatabaseName()+"."+getTableName()+" WHERE "+ whereClause);
    ResultSet r = s.executeQuery();
    exists=r.first();    
    r.close();
    s.close();
    release(c);
    return exists;
  }

  public File extractBinary(Origin o, Authentication id, File outputDir) throws Exception {
    String filename = o.getName();
    FTP finder = (FTP) getFinderPrototype().copy();
    finder.setOrigin(o);
    finder.setDatasource(this);
    URL url = finder.find();
    InputStream binary = url.openStream();
    OutputStream outFile = new FileOutputStream( outputDir + Names.PATH_SEPARATOR + filename );
    try {
      int b = binary.read();
      if (-1==b) outFile.close();
      while(b > -1) {
        outFile.write(b);
        b = binary.read();
      }
      File f = new File(outputDir, filename);
      while(!f.exists()) ; //hack: wait till os detects that the file is there.
      //++this prevents client from using the returned URL before the os is aware the new file in the download repository.
      //++this is dangerous, but resolves a timing issue.
      //++is there a better way to wait till the OS is aware that the download file has been created?
      return f;
    }
    catch(IOException io) { throw io; }
    finally { binary.close(); outFile.close(); }    
  }

  
  public Metadata find(Origin o, Authentication id) throws Exception {
          //find-query="SELECT * FROM CIDBP01.GMS01_DRP WHERE [criteria]"
    return null;
  }

  public InputStream findBinary( Origin o, Authentication id ) throws Exception {
    return null;
  }

  public Metadata findMetadataForPackage(Metadata data, boolean includeHistory, Authentication id) throws Exception { return data; }
  
  public String getDriverClassName() { return driverClassName; }
  public void setDriverClassName(String s) { driverClassName=s; }
  public String getConnectionURL() { return connectionURL; }
  public void setConnectionURL(String s) { connectionURL = s; }
  public String getDatabaseName() { return databaseName; }
  public void setDatabaseName(String s) { databaseName=s; }
  public String getUsername() { return username; }
  public void setUsername(String s) { username=s; }
  public String getPassword() { return password; }
  public void setPassword(String s) { password=s; }
  public String getContainsQuery(){ return queryContains; }
  public void setContainsQuery(String s) { queryContains=s; }
  public String getFindQuery() { return queryFind; }
  public void setFindQuery(String s) { queryFind=s; }
  public String getTableName(){ return tableName; }
  public void setTableName(String s) { tableName=s; }
  public String getUniqueIDColumns() { return uniqueIDColumns; }
  public void setUniqueIDColumns(String s) { uniqueIDColumns=s; }
  public String getNameSpec() { return nameSpec; }
  public void setNameSpec(String s) { nameSpec=s; }
  
  public void setFinderPrototype(FTP f) { finderPrototype=f; }
  public FTP getFinderPrototype() { return finderPrototype; } //+++ user finder interface instead of FTP
  
  private String connectionURL;
  private String databaseName;
  private String username; 
  private String password;
  private String queryContains;
  private String queryFind;
  private String uniqueIDColumns;
  private String tableName;
  private String nameSpec;
  private Map fieldTypes = new HashMap();  
  private String driverClassName;
  private transient Class SQLDriver;
  private transient List connectionPool = new ArrayList();
  private transient List activeConnections = new ArrayList();
  private FTP finderPrototype;  //+++ use Finder interface instead of FTP implementation
  
  private static String LITERAL = "'";
}
