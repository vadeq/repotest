package zws.service.policy.match.op;
/*
 * DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 26, 2007 12:09:08 PM
 * Copywrite (c) 2007 Zero Wait-State Inc All rights reserved
 */

import zws.qx.QxContext;
import zws.security.Authentication;
import zws.util.DomainContext;
import zws.op.Op;
import zws.origin.Origin;

/**
 * The Interface PolicyMatchOp.
 * @author ptoleti
 */
public interface PolicyMatchOp extends Op {

  /**
   * Gets the qx ctx.
   * @return the ctx
   */
  QxContext getQxCtx();

  /**
   * Sets the qx ctx.
   * @param ctx the ctx to set
   */
  void setQxCtx(QxContext ctx);

  /**
   * Sets the domain ctx.
   * @param ctx the domani context to set
   */
  void setContext(DomainContext context);
  
  /**
   * Gets the originObj.
   * @return the ctx
   */
  Origin getOrigin();

  /**
   * Sets the originObj.
   * @param originObj the ctx to set
   */
  void setOrigin(Origin originObj);

  /**
   * Add.
   * @param op the op
   */
   void add(PolicyMatchOp op);

   /**
    * @return Authentication
    */
   Authentication getAuthentication();

   /**
    * @param authId Authentication
    */
     void setAuthentication(Authentication authId);

public static String NO_DECISION ="noDecision";
public static String EXCLUDE_POLICY ="excludePolicy";
public static String INCLUDE_POLICY ="includePolicy";

}
