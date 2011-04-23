package zws.service.repository; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.DuplicateName;
import zws.exception.InvalidConfiguration;
import zws.exception.NameNotFound;
import zws.exception.NotConnected;
import zws.repository.Repository;
import zws.service.PrototypeSvc;
import zws.util.Named;
import zws.util.PrototypeCollection;

import java.util.Collection;

public class RepositorySvc {
  public static String NAMESPACE = "zws-repository-service"; 
  public static String getNamespace() { return NAMESPACE; }
  
  public static Repository find(String name) throws NameNotFound { 
    return (Repository)PrototypeSvc.lookup(NAMESPACE, name);
  }

  public static Collection getPrototypeNames() { return PrototypeSvc.getPrototypeNames(NAMESPACE); }
  
  public static PrototypeCollection findAll() { return PrototypeSvc.findAll(NAMESPACE); }
  
  public static void add(Repository repository) throws DuplicateName, InvalidConfiguration, NotConnected, Exception  {
    try {
      Repository old = (Repository)PrototypeSvc.lookup(NAMESPACE, repository.getName());
      throw new DuplicateName(NAMESPACE, repository.getName());
    }
    catch (NameNotFound ignore) { }
    catch (Exception exp) {
      exp.printStackTrace();
      {} //System.out.println("Exception " + exp.getMessage());
    }
    repository.activate();
    PrototypeSvc.add(NAMESPACE, repository);
    {} //System.out.println("Added " + repository.getName());
  }

  public static void update(Repository repository) throws InvalidConfiguration, NotConnected, Exception { 
    remove(repository.getName()); 
    try { add(repository); }
    catch (DuplicateName e) {} //will never be thrown if source is first removed;
  }

  public static void remove(Named op) { remove(op.getName()); }
  public static void remove(String name) { PrototypeSvc.remove(NAMESPACE, name); }
  public static void unload() { PrototypeSvc.unload(NAMESPACE); }
}
