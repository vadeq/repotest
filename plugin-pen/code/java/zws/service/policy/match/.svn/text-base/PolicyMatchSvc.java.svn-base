package zws.service.policy.match;

/*
 * DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 4, 2007 10:39:59 AM Copywrite (c) 2007 Zero Wait-State Inc.
 * All rights reserved
 */

import zws.origin.Origin;
import zws.qx.QxContext;
import zws.security.Authentication;
import zws.service.PrototypeSvc;
import zws.util.DomainContext;
//impoer zws.util.Logwriter;
import zws.util.PrototypeCollection;
import zws.exception.NameNotFound;
import zws.exception.DuplicateName;
import zws.exception.InvalidConfiguration;
import zws.exception.NotConnected;
import zws.exception.zwsException;
import zws.util.Named;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * The Class PENPolicySvc.
 *
 * @author ptoleti
 */
public final class PolicyMatchSvc {

 /**
  * Find.
  * @param name the name
  * @return the PEN policy
  * @throws NameNotFound the name not found
  */
 public static PolicyMatch find(final String name) throws NameNotFound {
    return (PolicyMatch) PrototypeSvc.lookup(NAMESPACE, name);
  }

  /**
   * Add.
   * @param policyMatch the pen policy
   * @throws NotConnected the not connected
   * @throws DuplicateName the duplicate name
   * @throws InvalidConfiguration the invalid configuration
   */
  public static void add(PolicyMatch policyMatch)
      throws DuplicateName, InvalidConfiguration, NotConnected {
    try {
      PrototypeSvc.lookup(NAMESPACE, policyMatch.getName());
      // if Lookup successful, throw duplicate name exception
      throw new DuplicateName(NAMESPACE, policyMatch.getName());
    } catch (NameNotFound ignore) {}
    PrototypeSvc.add(NAMESPACE, policyMatch);
  }

  /**
   * @param qxctx QxContext
   * @param originObj origin
   * @param authID authentication
   * @return policyname
   * @throws zwsException 
   */
  public static String matchPolicy(QxContext qxctx, Origin originObj, Authentication authID) throws zwsException {
    String policyName = null;
    String priorityKey = null;
    String targetName = null;
    String intent = null;
    PolicyMatch policyMatch = null;
    HashMap policyOpMap = new HashMap();
    // get all the existing policyMatch objects.
    Collection policyMatches = PrototypeSvc.findAll(getNamespace());
    if(policyMatches.size() == 0) throw new zwsException("None of the policies are loaded in to the system.");
    // prepare a HashMap with priority-> PolicyMatchOP combination
    Iterator itr = policyMatches.iterator();
    while (itr.hasNext()) {
      policyMatch = (PolicyMatch) itr.next();
      policyOpMap.put(policyMatch.getPriority(), policyMatch);
    }

    // sort the priorities and find the matched policy name
    List mapKeys = new ArrayList(policyOpMap.keySet());
    Collections.sort(mapKeys);
    Iterator opItr = mapKeys.iterator();
    DomainContext context = new DomainContext();
    while (opItr.hasNext()) {
      priorityKey = (String) opItr.next();
      policyMatch = (PolicyMatch) policyOpMap.get(priorityKey);
      targetName = qxctx.get(QxContext.TARGET_REPOSITORY);
      intent = qxctx.get(QxContext.INTENT);
      if (policyMatch.matchPolicyName(context, qxctx, originObj, authID)) {
          policyName = policyMatch.getName();
          break;
      } 
    }
    return policyName;
  }

  public static String getPripority(String policyName) throws Exception {
    String priority = null;
    PolicyMatch policyMatch = null;
    Collection policyMatches = PrototypeSvc.findAll(getNamespace());
    if(policyMatches.size() == 0) throw new zwsException("None of the policies are loaded in to the system.");
    // prepare a HashMap with priority-> PolicyMatchOP combination
    Iterator itr = policyMatches.iterator();
    while (itr.hasNext()) {
      policyMatch = (PolicyMatch) itr.next();
      if(policyName.equalsIgnoreCase(policyMatch.getName())) {
        priority = policyMatch.getPriority();
        break;
      }
    } 
    return priority;
  }
  /**
   * Update.
   * @param policyMatch the pen policy
   * @throws NotConnected the not connected
   * @throws InvalidConfiguration the invalid configuration
   */
  public static void update(final PolicyMatch policyMatch)
                  throws InvalidConfiguration, NotConnected {
    try {
      remove(policyMatch.getName());
      add(policyMatch);
    } catch (final DuplicateName e) {
      e.printStackTrace();
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
  private static final String NAMESPACE = "zws-PolicyMatch-service";
}
