package zws.service; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Configurator;
import zws.exception.DuplicateName;
import zws.exception.NameNotFound;
import zws.util.*;

import java.util.*;

public class PrototypeSvc {
  public static Collection getPrototypeNames(String namespace) { 
    Collection c = new Vector(); //keySet is not serializable - create a vector
    c.addAll(getPrototypes(namespace).keySet());
    return c;
  }
  
  public static Object lookup(String namespace, String name) throws NameNotFound  {
    Prototype op = (Prototype)getPrototypes(namespace).get(name);
    if (null==op) throw new NameNotFound(name, namespace);
    Object x = op.copy();
    return x;
  }

  public static PrototypeCollection findAll(String namespace) {
    PrototypeCollection c = new PrototypeVector();
    Iterator i = getPrototypes(namespace).values().iterator();
    while (i.hasNext()) c.add(i.next());
    return c;
  }

  public static void clear(String namespace) { getPrototypes(namespace).clear(); }

  public static void add(Namespaced op) throws DuplicateName{
    if (null==getPrototypes(op.getNamespace().asString()).get(op.getName())){
      {} //System.out.println("Adding prototype: "+op.getNamespace()+"."+op.getName()); 
      getPrototypes(op.getNamespace().asString()).put(op.getName(), op);
    }
    else throw new DuplicateName(op.getName(), op.getNamespace().asString());
  }

  public static void add(String namespace, Named op) throws DuplicateName{
    if (null==getPrototypes(namespace).get(op.getName())){
      {} //System.out.println("Adding prototype: "+namespace+"."+op.getName()); 
      getPrototypes(namespace).put(op.getName(), op);
    }
    else throw new DuplicateName(op.getName(), namespace);
  }
  public static void update(String namespace, Named op) { remove(namespace, op); try{add(namespace, op);} catch (Exception a) {} }
  
  public static void load() throws Exception { Configurator.load(); }

  public static void reload() throws Exception {
    Configurator.reinitialize();
    unloadAll();
    load();
  }
  public static void remove(String namespace, Named op) { remove(namespace, op.getName()); }
  public static void remove(String namespace, String name) {
    Map space = (Map)getPrototypes(namespace);
    Prototype op = (Prototype)space.get(name);
    space.remove(name);
    op.inactivate();
  }
  public static void unload(String namespace) { prototypes.remove(namespace); }
  public static void unloadAll() {
    {} //System.out.println("*****Unloading All Prototypes!!");
    //try { throw new Exception("Unloading All"); } catch (Exception e) {e.printStackTrace(); }
    Map space=null;
    Prototype p = null;
    Iterator m = prototypes.values().iterator();
    while (m.hasNext()) {
      space = (Map) m.next();
      Iterator i = space.values().iterator();
      while (i.hasNext()) {
        p = (Prototype) i.next();
        p.inactivate();
      }
      space.clear();
    }
    prototypes.clear();
  }

  private static Map getPrototypes(String namespace) {
    if (!prototypes.containsKey(namespace)) prototypes.put(namespace, new HashMap());
    return (Map)prototypes.get(namespace);
  }
  
  private static Map prototypes = new HashMap();
}