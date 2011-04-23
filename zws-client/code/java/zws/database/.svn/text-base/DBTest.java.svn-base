package zws.database;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 29, 2005, 11:30 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */


import java.sql.*;

public class DBTest {
  public DBTest() { }
  public static void main(String[] args) {
    {} //System.out.println("starting");
    DBTest test = new DBTest();
    try { test.run(); }
    catch(Exception e) { e.printStackTrace(); }
    {} //System.out.println("done");
  }

  void run() throws Exception {
    MySQL db = new MySQL();
    db.setName("MySQL-Test");
    db.setHost("localhost");
    db.setDatabaseName("designstate");
    db.setUsername("DesignState");
    db.setPassword("zero0");
    Connection con1 = db.connect();
    Connection con2 = db.connect();
    db.release(con1);
    db.release(con2);
    Thread.sleep(2000); 
    con1 = db.connect();
    PreparedStatement s = con1.prepareStatement("show variables like 'wait_timeout'");
    ResultSet r = s.executeQuery();
    r.next();
    long wait = r.getInt("Value");
    {} //System.out.println(wait);
  }
}
