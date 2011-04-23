package zws.database;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 22, 2004, 9:17 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.DuplicateName;
import zws.exception.NameNotFound;
import zws.util.Namespace;

import java.util.HashMap;
import java.util.Map;

public class DB {
  
  public static Database source(String name) throws NameNotFound {
    if (!sources.containsKey(name)) throw new NameNotFound(name, "database");
    return (Database) sources.get(name);
  }
  
  public static void add(Database db) throws DuplicateName {
    if (sources.containsKey(db.getName())) throw new DuplicateName(db.getName(), namespace.asString());
    sources.put(db.getName(), db);
  }
  
  public static void clear() { sources.clear(); }
  
  public static void remove(Database db) throws NameNotFound { remove(db.getName()); }
  public static Database remove(String sourceName) throws NameNotFound { 
    if (!sources.containsKey(sourceName)) throw new NameNotFound(sourceName, namespace.asString());
    return (Database) sources.remove(sourceName); 
  }
  
  private static Map sources=new HashMap();
  private static Namespace namespace = null;
}
