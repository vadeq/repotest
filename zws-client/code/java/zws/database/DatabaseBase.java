package zws.database;/*
                       * DesignState - Design Compression Technology @author: athakur @version: 1.0 Created on February 22, 2004, 9:17 AM Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
                       */

import zws.Alert;
import zws.exception.DriverNotFound;
import zws.exception.InvalidConfiguration;
import zws.exception.InvalidSyntax;
import zws.exception.NameNotFound;
import zws.exception.NotConnected;
import zws.exception.WaitedTooLong;
import zws.security.CryptoUtil;
import zws.util.Namespace;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

public abstract class DatabaseBase implements Database {
  protected Properties dbProperties = new Properties();
  private int maxConnections = 10;

  private int activeConnCount;

  private Vector connPool = new Vector();

  protected synchronized void release(Connection con) {
    if (null == con) return;
    try {
      if (!con.isClosed()) {
        connPool.addElement(con);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      activeConnCount--;
      notify();
    }
  }

  protected synchronized Connection connect() throws NotConnected, DriverNotFound, SQLException {
    Connection con = null;
    if (connPool.size() > 0) {
      con = (Connection) connPool.firstElement();
      connPool.removeElementAt(0);
      try {
        if (con.isClosed()) {
          con = getConnection();
        }
      } catch (SQLException e) {
        e.printStackTrace();
        con = getConnection();
      }
    } else if (maxConnections == 0 || activeConnCount < maxConnections) {
      try {
        con = getConnection();
      } //all connections are taken allocate a new one
      catch (SQLException e) {
        e.printStackTrace();
        Alert.notify("DesignState: DB Connection Error to  " + getName(), e.getMessage());
        throw e;
      }
    }
    if (con != null) activeConnCount++;
    return con;
  }

  protected synchronized Connection connect(long timeout) throws NotConnected, DriverNotFound, WaitedTooLong, SQLException {
    long startTime = new Date().getTime();
    Connection con;
    long interval = 200;
    while ((con = getConnection()) == null) {
      try {
        wait(interval);
      } catch (InterruptedException e) {}
      if ((new Date().getTime() - startTime) >= timeout) {
        throw new WaitedTooLong("connect to DB: " + getName());
      }
    }
    return con;
  }

  protected Connection getConnection() throws NotConnected, DriverNotFound, SQLException {
    if (null == getDriver()) registerDriver();
    String url = null;
    try {
      url = getConnectionURL();
      if (username == null) return DriverManager.getConnection(url);
      else return DriverManager.getConnection(url, username, password);
    } catch (InvalidConfiguration e) {
      e.printStackTrace();
      throw new NotConnected("Invalid Configuration: " + e.getMessage());
    } catch (SQLException e) {
      e.printStackTrace();
      throw new NotConnected(url);
    }
  }

  public synchronized void closeAllConnections() {
    Iterator i = connPool.iterator();
    while (i.hasNext()) {
      Connection con = (Connection) i.next();
      try {
        con.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    connPool.removeAllElements();
    activeConnCount = 0;
  }

  public String limitResults(String sql, int rows) {

    int idxLimit = sql.toLowerCase().indexOf("limit");

    // this has already been done
    if (idxLimit >= 0) return sql;
    return sql + " limit " + Integer.toString(rows);
  }

  public abstract String getConnectionURL() throws InvalidConfiguration;

  public abstract String getDatabaseType();

  public abstract String getDriverFQCN();

  public abstract String getPort();

  public abstract String formatDate(Calendar c) throws InvalidSyntax;

  public abstract Calendar parseDate(String s) throws InvalidSyntax;

  public PreparedStatement prepareStatement(String sql) throws NameNotFound, NotConnected, DriverNotFound, SQLException {
    return connect().prepareStatement(sql);
  }

  public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws NameNotFound, NotConnected, DriverNotFound, SQLException {
    return connect().prepareStatement(sql, autoGeneratedKeys);
  }

  public boolean execute(PreparedStatement s) throws SQLException {
    boolean b = s.execute();
    release(s.getConnection());
    s.close();
    return b;
  }

  public ResultSet executeQuery(PreparedStatement s) throws SQLException {
    ResultSet r = s.executeQuery();
    release(s.getConnection());
    return r;
  }

  public void executeUpdate(PreparedStatement s) throws SQLException {
    s.executeUpdate();
    release(s.getConnection());
    s.close();
  }

  public ResultSet generateKeys(PreparedStatement s) throws SQLException {
    s.executeUpdate();
    ResultSet r = s.getGeneratedKeys();
    release(s.getConnection());
    return r;
  }

  public boolean insertText(String table, String c1, String c2) throws NameNotFound, NotConnected, DriverNotFound, SQLException {
    String sql = "INSERT into " + table + " VALUES(?,?)";
    PreparedStatement s = prepareStatement(sql);
    s.setString(1, c1);
    s.setString(2, c2);
    return execute(s);
  }

  public boolean insertText(String table, String c1, String c2, String c3) throws NameNotFound, NotConnected, DriverNotFound, SQLException {
    String sql = "INSERT into " + table + " VALUES(?,?,?)";
    PreparedStatement s = prepareStatement(sql);
    s.setString(1, c1);
    s.setString(2, c2);
    s.setString(3, c3);
    return execute(s);
  }

  public boolean insertText(String table, String c1, String c2, String c3, String c4) throws NameNotFound, NotConnected, DriverNotFound, SQLException {
    String sql = "INSERT into " + table + " VALUES(?,?,?,?)";
    PreparedStatement s = prepareStatement(sql);
    s.setString(1, c1);
    s.setString(2, c2);
    s.setString(3, c3);
    s.setString(4, c4);
    return execute(s);
  }

  public String getName() {
    return name;
  }

  public void setName(String s) {
    name = s;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String s) {
    description = s;
  }

  public void disconnect(Connection con) {
    release(con);
  }

  public void close(Connection con) {
    release(con);
  }

  protected void registerDriver() throws DriverNotFound {
    try {
      driver = Class.forName(getDriverFQCN());
    } catch (Exception e) {
      e.printStackTrace();
      throw new DriverNotFound(getDriverFQCN());
    }
  }

  public String getHost() {
    return host;
  }

  public void setHost(String s) {
    host = s;
  }

  public String getDatabaseName() {
    return databaseName;
  }

  public void setDatabaseName(String s) {
    databaseName = s;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String s) {
    username = s;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String s) {
    password = s;
  }

  /*public String getEncryptedSystemPassword() {
  return encryptedPassword;
}*/

public void setEncryptedPassword(String s) throws Exception{
  encryptedPassword = s;
  try {
    CryptoUtil cryptoUtil = new CryptoUtil();
    password = cryptoUtil.decrypt(encryptedPassword);
  } catch (Exception e) {
    System.out.println("Not able to decrypt the password " + s);
    throw e;
  }
}


  public long getConnectionTimeout() {
    return connectionTimeout;
  }

  public void setConnectionTimeout(long l) {
    connectionTimeout = l;
  }

  protected Class getDriver() {
    return driver;
  }

  private String name = null;

  private String description = null;

  private Namespace namespace = null;

  private Class driver = null;

  private String host = null;

  private String databaseName = null;

  private String username = null;

  private String password = null;
  private String encryptedPassword = null;

  long connectionTimeout = 180 * 60; //180 minutes

  public static char DASH = '-';

  public static char SPACE = ' ';

  public static char COLON = ':';

  public int getMaxConnections() {
    return maxConnections;
  }

  public void setMaxConnections(int c) {
    maxConnections = c;
  }
}