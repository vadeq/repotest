package zws.service.transformer; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.DuplicateName;
import zws.exception.NameNotFound;
import zws.service.PrototypeSvc;
import zws.util.*;

import java.util.Collection;

public class TransformerSvc  {
  public static String NAMESPACE = "zws-transfomer-service";
  public static String getNamespace() { return NAMESPACE; }
  public static Transformer find(String name) throws NameNotFound { return (Transformer)PrototypeSvc.lookup(NAMESPACE, name); }

  public static Collection getPrototypeNames() { return PrototypeSvc.getPrototypeNames(NAMESPACE); }
  public static PrototypeCollection findAll() { return PrototypeSvc.findAll(NAMESPACE); }
  public static void add(Named op) throws DuplicateName{ PrototypeSvc.add(NAMESPACE, op); }
  public static void update(Named op) { remove(op.getName()); try{add(op);} catch (Exception a) {} }
  public static void remove(Named op) { remove(op.getName()); }
  public static void remove(String name) { PrototypeSvc.remove(NAMESPACE, name); }
  public static void unload() { PrototypeSvc.unload(NAMESPACE); }
}