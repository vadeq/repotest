package zws.service.pen.policy;

/*
 * DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 4, 2007 10:39:59 AM Copywrite (c) 2007 Zero Wait-State Inc.
 * All rights reserved
 */

import zws.pen.policy.PENPolicy;
import zws.service.PrototypeSvc;
//impoer zws.util.Logwriter;
import zws.util.PrototypeCollection;
import zws.exception.NameNotFound;
import zws.exception.DuplicateName;
import zws.exception.InvalidConfiguration;
import zws.exception.NotConnected;
import zws.util.Named;

import java.util.Collection;

/**
 * The Class PENPolicySvc.
 *
 * @author ptoleti
 */
public final class PENPolicySvc {

 /**
  * Find.
  * @param name the name
  * @return the PEN policy
  * @throws NameNotFound the name not found
  */
 public static PENPolicy find(final String name) throws NameNotFound {
    return (PENPolicy) PrototypeSvc.lookup(NAMESPACE, name);
  }

  /**
   * Add.
   * @param penPolicy the pen policy
   * @throws NotConnected the not connected
   * @throws DuplicateName the duplicate name
   * @throws InvalidConfiguration the invalid configuration
   */
  public static void add(PENPolicy penPolicy)
      throws DuplicateName, InvalidConfiguration, NotConnected {
    try {
      PrototypeSvc.lookup(NAMESPACE, penPolicy.getName());
      // if Lookup successful, throw duplicate name exception
      throw new DuplicateName(NAMESPACE, penPolicy.getName());
    } catch (NameNotFound ignore) {
      {}//Logwriter.printOnConsole("");
      // No Policy object exixts with the name "penPolicy",
      //so add this Policy object
    }
    PrototypeSvc.add(NAMESPACE, penPolicy);
    {}//Logwriter.printOnConsole("Added " + penPolicy.getName());
  }

  /**
   * Update.
   * @param penPolicy the pen policy
   * @throws NotConnected the not connected
   * @throws InvalidConfiguration the invalid configuration
   */
  public static void update(final PENPolicy penPolicy)
                  throws InvalidConfiguration, NotConnected {
    try {
      remove(penPolicy.getName());
      add(penPolicy);
    } catch (final DuplicateName e) {
      e.printStackTrace();
      {}//Logwriter.printOnConsole("DuplicateName in PENPolicySvc update");
    }
  }

  /**
   * Gets the prototype names.
   * @return the prototype names
   */
  public static Collection getPrototypeNames() {
    return PrototypeSvc.getPrototypeNames(NAMESPACE);
  }

  /**
   * Find all.
   * @return the prototype collection
   */
  public static PrototypeCollection findAll() {
    return PrototypeSvc.findAll(NAMESPACE);
  }

  /**
   * Remove.
   * @param op the op
   */
  public static void remove(final Named op) {
    remove(op.getName());
  }

  /**
   * Remove.
   * @param name the name
   */
  public static void remove(final String name) {
    PrototypeSvc.remove(NAMESPACE, name);
  }

  /**
   * Unload.
   */
  public static void unload() {
    PrototypeSvc.unload(NAMESPACE);
  }

  /**
   * Gets the namespace.
   * @return the namespace
   */
  public static String getNamespace() {
    return NAMESPACE;
  }

  /** The NAMESPACE. */
  private static final String NAMESPACE = "zws-PENPolicy-service";
}
