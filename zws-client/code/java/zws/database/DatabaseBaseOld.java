package zws.database;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 22, 2004, 9:17 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.*;
import zws.util.Namespace;

import java.sql.*;
import java.util.*;
import java.util.Date;

public abstract class DatabaseBaseOld implements Database {
  public abstract String getConnectionURL() throws InvalidConfiguration;
  public abstract String getDatabaseType();
  public abstract String getDriverFQCN();
  public abstract String getPort();

  public abstract String formatDate(Calendar c) throws InvalidSyntax;
  public abstract Calendar parseDate(String s) throws InvalidSyntax;
  
  public PreparedStatement prepareStatement(String sql) throws NameNotFound, NotConnected, DriverNotFound, SQLException { return connect().prepareStatement(sql); }
    
  public boolean execute(PreparedStatement s) throws SQLException {
  //long duration = System.currentTimeMillis();
    boolean b = s.execute();
  //duration = System.currentTimeMillis() - duration;
  {} //System.out.println(duration + "ms: " + s.toString());
    release(s.getConnection());
    return b;
  }

  public ResultSet executeQuery(PreparedStatement s) throws SQLException {
  //long duration = System.currentTimeMillis();
    ResultSet r=s.executeQuery();
  //duration = System.currentTimeMillis() - duration;
  {} //System.out.println(duration + "ms: " + s.toString());
  {} //System.out.println(s.toString());
    release(s.getConnection());
    return r;
  }

  public boolean insertText(String table, String c1, String c2) throws NameNotFound, NotConnected, DriverNotFound, SQLException {
    String sql = "INSERT into "+table+" VALUES(?,?)";
    PreparedStatement s = prepareStatement(sql);
    s.setString(1,c1);
    s.setString(2,c2);
    return execute(s);
  }

  public boolean insertText(String table, String c1, String c2, String c3) throws NameNotFound, NotConnected, DriverNotFound, SQLException {
    String sql = "INSERT into "+table+" VALUES(?,?,?)";
    PreparedStatement s = prepareStatement(sql);
    s.setString(1,c1);
    s.setString(2,c2);
    s.setString(3,c3);
    return execute(s);
  }

  public boolean insertText(String table, String c1, String c2, String c3, String c4) throws NameNotFound, NotConnected, DriverNotFound, SQLException {
    String sql="INSERT into "+table+" VALUES(?,?,?,?)";
    PreparedStatement s = prepareStatement(sql);
    s.setString(1,c1);
    s.setString(2,c2);
    s.setString(3,c3);
    s.setString(4,c4);
    return execute(s);
  }

  protected Connection getConnection() throws NotConnected {
    String url=null;
    try  { 
      url = getConnectionURL();
      return DriverManager.getConnection(url);
    }
    catch (InvalidConfiguration e) {e.printStackTrace(); throw new NotConnected("Invalid Configuration: " + e.getMessage()); }
    catch (SQLException e) {e.printStackTrace(); throw new NotConnected(url); }
  }

  public String getName() { return name; }
  public void setName(String s) { name=s; }
  public String getDescription() { return description; }
  public void setDescription(String s) { description=s; }

  public Connection connect() throws NotConnected, DriverNotFound { return connect(getName()); } 
  public void disconnect(Connection con) { release(con); }
  public void close(Connection con) { release(con); }


 public void release (PreparedStatement s) {
    Connection con=null;
    try { s.close(); con=s.getConnection(); } 
    catch (SQLException e) { ; }
    release(con);
  }
  
  public void release (Connection con) { 
    if (null==con) return;
    try { 
      if (!con.isClosed()) recycle (getName(), con); 
      else { } {} //System.out.println("Throwing away closed connection."); }
    }
    catch (SQLException e) {
      try { con.close(); } catch (Exception x) {x.printStackTrace(); /* +++ log weird error */}
      getTakenConnectionsForSource(getName()).remove(con);
    }
  }

  private synchronized Connection connect(String sourceName) throws NotConnected, DriverNotFound {
    Connection con=null;
    Collection c = getAvailableConnectionsForSource(sourceName); 
    {} //System.out.println("available connections for " +sourceName + ":" + c.size());
    if (c.isEmpty()) {
      if (null== driver) registerDriver();
      con = getConnection();
      getTakenConnectionsForSource(sourceName).add(con);
      return con;     
    }
    Iterator i = c.iterator();
    con = (Connection)i.next();
    i.remove(); //remove idle connection
    if (idleTimes.containsKey(con)) { 
      {} //System.out.println(sourceName + ": Connection is available" );
      Date d = (Date)idleTimes.get(con);
      idleTimes.remove(con);
      if ((System.currentTimeMillis() - d.getTime()) > (getConnectionTimeout()*1000)){ //close connections that have been idle for too long
        try { con.close(); }
        catch(Exception e) {} //non critical error
        {} //System.out.println(sourceName + ": Connection has been idle for too long. Regenerating...");
        return connect(sourceName);
      }
      getTakenConnectionsForSource(sourceName).add(con);
      return con;     
    }
    getAvailableConnectionsForSource(sourceName).clear();
    con = getConnection();
    getTakenConnectionsForSource(sourceName).add(con);
    return con;     
  }

  private static synchronized void recycle(String sourceName, Connection con) throws SQLException {
    if (con.isClosed()) { //discard closed connections. re-use open ones
        if (idleTimes.containsKey(con)) idleTimes.remove(con); 
        return;
    }
    getTakenConnectionsForSource(sourceName).remove(con);
    getAvailableConnectionsForSource(sourceName).add(con);
    Date d = new Date();
    {} //System.out.println(sourceName + ": Recycling connection at " + d.getTime());
    idleTimes.put(con, d);
  }
  
  public void closeAllConnections() { 
    Iterator i = getTakenConnectionsForSource(getName()).iterator();
    Connection con;
    while (i.hasNext()) {
      con = (Connection)i.next();
      try { con.close(); } catch(SQLException e){ e.printStackTrace(); } //non critical error;
      i.remove();
      if (idleTimes.containsKey(con)) idleTimes.remove(con); 
    }
    i = getAvailableConnectionsForSource(getName()).iterator();
    while (i.hasNext()) {
      con = (Connection)i.next();
      try { con.close(); } catch(SQLException e){ e.printStackTrace(); } //non critical error;
      i.remove();
      if (idleTimes.containsKey(con)) idleTimes.remove(con); 
    }
  }

  private void registerDriver() throws DriverNotFound {
    try { driver = Class.forName(getDriverFQCN()); }
    catch (Exception e) { e.printStackTrace(); throw new DriverNotFound(getDriverFQCN()); }
  }
  
  private static synchronized Collection getAvailableConnectionsForSource(String name) {
    if (!availableConnections.containsKey(name)) availableConnections.put(name, new Vector());
    return (Collection) availableConnections.get(name);
  }

  private static synchronized Collection getTakenConnectionsForSource(String name) {
    if (!takenConnections.containsKey(name)) takenConnections.put(name, new Vector());
    return (Collection) takenConnections.get(name);
  }

  public String getHost() { return host; }
  public void setHost(String s) { host=s; }
  public String getDatabaseName() { return databaseName; }
  public void setDatabaseName(String s) { databaseName=s; }
  public String getUsername() { return username; }
  public void setUsername(String s) { username=s; }
  public String getPassword() { return password; }
  public void setPassword(String s) { password=s; }
  public long getConnectionTimeout() { return connectionTimeout; }
  public void setConnectionTimeout(long l) { connectionTimeout=l; }

  private String name=null;
  private String description=null;
  private Namespace namespace=null;
  private Class driver=null;
  private String host=null;
  private String databaseName=null;
  private String username=null;
  private String password=null;
  long connectionTimeout = 180*60; //180 minutes 
  private static Map availableConnections=new HashMap();
  private static Map takenConnections=new HashMap();
  private static Map idleTimes=new HashMap();
  
  public static char DASH='-';
  public static char SPACE=' ';
  public static char COLON=':';
}
