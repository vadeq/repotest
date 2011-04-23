package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 24, 2004, 7:21 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class DatabaseError extends Exception {
  public DatabaseError(String db, String msg ) {
    super ("Error accessing Database: " + db + ": " + msg);
    database=db;
  }
  
  String database=null;
}
