/*
 * Created on Oct 16, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package zws.service.processor;

/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import zws.exception.DuplicateName;
import zws.exception.NameNotFound;
import zws.processor.Processor;
import zws.service.PrototypeSvc;
import zws.util.Named;
import zws.util.PrototypeCollection;

import java.util.Collection;

public class ProcessorSvc {

  public static String NAMESPACE = "zws-process-service";
  public static String getNamespace() { return NAMESPACE; }
  public static Processor find(String name) throws NameNotFound { return (Processor)PrototypeSvc.lookup(NAMESPACE, name); }

  public static Collection getPrototypeNames() { return PrototypeSvc.getPrototypeNames(NAMESPACE); }
  public static PrototypeCollection findAll() { return PrototypeSvc.findAll(NAMESPACE); }
  public static void add(Named op) throws DuplicateName{ PrototypeSvc.add(NAMESPACE, op); }
  public static void update(Named op) { remove(op.getName()); try{add(op);} catch (Exception a) {} }
  public static void remove(Named op) { remove(op.getName()); }
  public static void remove(String name) { PrototypeSvc.remove(NAMESPACE, name); }
  public static void unload() { PrototypeSvc.unload(NAMESPACE); }
}