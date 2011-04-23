package zws.service.datasource; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.datasource.Datasource;
import zws.exception.*;
import zws.search.SearchAgent;
import zws.service.PrototypeSvc;
import zws.service.search.SearchAgentSvc;
import zws.util.Named;
import zws.util.PrototypeCollection;

import java.util.Collection;

public class DatasourceSvc {
  public static String NAMESPACE = "zws-datasource-service"; 
  public static String getNamespace() { return NAMESPACE; }
  public static Datasource find(String name) throws NameNotFound { return (Datasource)PrototypeSvc.lookup(NAMESPACE, name); }

  public static Collection getPrototypeNames() { return PrototypeSvc.getPrototypeNames(NAMESPACE); }
  public static PrototypeCollection findAll() { return PrototypeSvc.findAll(NAMESPACE); }
  public static void add(Datasource source) throws DuplicateName, InvalidConfiguration, NotConnected {
    try {
      Datasource old = (Datasource)PrototypeSvc.lookup(NAMESPACE, source.getName());
      throw new DuplicateName(NAMESPACE, source.getName());
    }
    catch (NameNotFound ignore) {}
    source.verifyConfiguration();
    source.verifyConnection();
    PrototypeSvc.add(NAMESPACE, source);
    source.configure();
  }

  public static void update(Datasource source) throws InvalidConfiguration, NotConnected { 
    remove(source.getName()); 
    try { add(source); }
    catch (DuplicateName e) {} //will never be thrown if source is first removed;
  }
  public static void remove(Named op) { remove(op.getName()); }
  public static void remove(String name) { PrototypeSvc.remove(NAMESPACE, name); }
  public static void unload() { PrototypeSvc.unload(NAMESPACE); }
}
