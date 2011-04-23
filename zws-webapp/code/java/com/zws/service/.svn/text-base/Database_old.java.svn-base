package com.zws.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import org.apache.struts.action.ActionServlet;

public class Database_old {
  private static ActionServlet servlet=null;
//  private static Map dataSources = new HashMap();
  private static Map availableConnections=new HashMap();
  private static Map takenConnections=new HashMap();

  private static synchronized Collection getAvailableConnectionsForSource(String name) {
    if (!availableConnections.containsKey(name))
      availableConnections.put(name, new Vector());
    return (Collection) availableConnections.get(name);
  }
  private static synchronized Collection getTakenConnectionsForSource(String name) {
    if (!takenConnections.containsKey(name))
      takenConnections.put(name, new Vector());
    return (Collection) takenConnections.get(name);
  }

  public static synchronized Connection connect(String sourceName) throws SQLException{
    Connection con=null;
    {} //System.out.println("Datasource.connect()" + sourceName);
    Collection c = getAvailableConnectionsForSource(sourceName); 
    {} //System.out.println("available connections for source:" + c.size());
    if (c.isEmpty()) {
      javax.sql.DataSource source = servlet.findDataSource(sourceName); 
      {} //System.out.println("source=" + source);
      con = source.getConnection();
      {} //System.out.println("connection="+con);
      c.add(con);
//      c.add(servlet.findDataSource(sourceName).getConnection());
    }
    Iterator i = c.iterator();
    con = (Connection )i.next();
    i.remove();
    getTakenConnectionsForSource(sourceName).add(con);
    return con;
  }

  public static void bind(ActionServlet s) { servlet = s; }

/*
 public synchronized static Connection createConnection(String sourceName) throws SQLException {
    DataSource source = (DataSource)dataSources.get(sourceName);
    if (null==source) {
      source = servlet.findDataSource(name);
      dataSources.put(name, source);
    }
    return source.getConnection();
  }
 */

  public static synchronized void release(String sourceName, Connection con) throws SQLException {
    if (con.isClosed()) return; //discard closed connections. re-use open ones
    getTakenConnectionsForSource(sourceName).remove(con);
    getAvailableConnectionsForSource(sourceName).add(con);
  }

  public static synchronized void release(Connection con) throws SQLException {
    if (con.isClosed()) return; //discard closed connections. re-use open ones
    String sourceName=removeConnection(takenConnections, con);
    if (null!=sourceName) getAvailableConnectionsForSource(sourceName).add(con);
  }

  private static synchronized String removeConnection(Map pool, Connection con) {
    String sourceName = null;
    Collection c = null;
    Iterator i = pool.keySet().iterator();
    boolean removed = false;
    while (i.hasNext() && !removed) {
      sourceName = (String) i.next();
      c = (Collection) pool.get(sourceName);
      if (c.contains(con)) {
        c.remove(con);
        removed = true;
      }
    }
    if (removed) return sourceName; else return null;
  }
}