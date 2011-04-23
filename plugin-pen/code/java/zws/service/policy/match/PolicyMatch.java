package zws.service.policy.match;

/*
 * DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 2, 2007 11:09:06 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */

import zws.context.Context;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.security.Authentication;
import zws.service.policy.match.op.PolicyMatchOp;
import zws.util.DomainContext;
//impoer zws.util.Logwriter;
import zws.util.Prototype;
import zws.util.Named;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;



/**
 * The Class PolicyMatch.
 * @author ptoleti
 */
public class PolicyMatch implements Named, Serializable, Prototype {



  /**
   * Update source.
   * @param originObj the originObj
   * @param qxctx the ctx
   * @param authID Authentication
   * @return policyname
   */
  public boolean matchPolicyName(DomainContext context, QxContext qxctx, Origin originObj, Authentication authID) {
    String result = PolicyMatchOp.NO_DECISION;
    boolean isSelected = false;
    if(!isValidIntent(qxctx, getIntent())) {
      return false;
    }
    try {
      Iterator itr = opList.iterator();
      while (itr.hasNext() && PolicyMatchOp.NO_DECISION.equals(result)) {
        policyMatchOp = (PolicyMatchOp) itr.next();
        policyMatchOp.setQxCtx(qxctx);
        policyMatchOp.setOrigin(originObj);
        policyMatchOp.setAuthentication(authID);
        policyMatchOp.setContext(context);
        policyMatchOp.execute();
        result = (String) policyMatchOp.getResult();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    if(PolicyMatchOp.INCLUDE_POLICY.equals(result)) isSelected = true;
    return isSelected;
}

  /**
   * Add op.
   * @param op PolicyMatchOp
   * @throws Exception
   */
  public void add(PolicyMatchOp op) {
    opList.add(op);
  }


  /**
   * Defines configuration parameters specific for this PENPolicy.
   * @param parameters the parameters
   * @throws NotSerializableException the not serializable exception
   */

  public  void configureContext(Map parameters)
                                      throws NotSerializableException {
    ctx.set(parameters);
  }
  
  private boolean isValidIntent(QxContext qxctx, String intent) {
    boolean result = false;
    String ctxIntent = qxctx.get(QxContext.INTENT);
    if(null != ctxIntent) {
      String[] intents = null;
      if(intent.indexOf("|") != -1) {
        intent= intent.replace('|', '~');         // split will not work with "|"
        intents = intent.split("~");
        for(int idx =0; idx<intents.length ;idx++) {
          if(ctxIntent.equalsIgnoreCase(intents[idx])) {
            result = true;
            break;
          }
        }
      } else {
        if(ctxIntent.equalsIgnoreCase(intent)) {
          result = true;
        }
      }
    }
    return result;
  }

  /**
   * Get the copy og Policyobject.
   * @return Policy object
   * @see zws.util.Prototype#copy()
   */
  public  Object copy() {
    PolicyMatch penPolicyClone = null;
    try {
        Context ctxClone = new Context();
        ctxClone.set(ctx.getProperties());
        penPolicyClone = (PolicyMatch) clone();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return penPolicyClone;
  }

  /**
   * Sets the name.
   * @param srcPolicyName the name
   */
  public  void setName(String srcPolicyName) {
    ctx.set(QxContext.NAME, srcPolicyName);
  }

  /**
   * Get the policy object name.
   * @return Policy name
   * @see zws.util.Named#getName()
   */
  public  String getName() {
    return ctx.getString(QxContext.NAME);
  }

  /**
   * @return the priority
   */
  public String getPriority() {
    return ctx.getString(QxContext.PRIORITY);
  }

  /**
   * @param strPriority the priority to set
   */
  public void setPriority(String strPriority) {
    ctx.set(QxContext.PRIORITY, strPriority);
  }

  /**
   * @return the priority
   */
  public String getTargetName() {
   // return ctx.getString("targetName");
    return ctx.getString(QxContext.TARGET_REPOSITORY);
  }

  /**
   * @param target the target to set
   */
  public void setTargetName(String target) {
    ctx.set(QxContext.TARGET_REPOSITORY, target);
  }

  /**
   * @return the priority
   */
  public String getIntent() {
    return ctx.getString(QxContext.INTENT);
  }

  /**
   * @param intent the intent to set
   */
  public void setIntent(String strIntent) {
    ctx.set(QxContext.INTENT, strIntent);
  }

  /**
   * Get deep copy of Policy object.
   * @return deep copyof Policy object
   * @see zws.util.Prototype#deepCopy()
   */
  public  Object deepCopy() {
    return null;
  }

  /**
   * Inactivate the policy object.
   * @see zws.util.Prototype#inactivate()
   */
  public void inactivate() {
  }

  /**
   * Get the shallow copy of Policy object.
   * @return shallow copy of Policy object
   * @see zws.util.Prototype#shallowCopy()
   */
  public  Object shallowCopy() {
    return null;
  }

  /**
   * determines ability to get deep copy of policy object.
   * @return true or false
   * @see zws.util.Prototype#supportsDeepCopy()
   */
  public  boolean supportsDeepCopy() {
    return false;
  }
  /** The ctx. */
  private Context ctx = new Context();

  /** The NAME. */
  //private static String sNAME = "NAME";
  /**   */
  private PolicyMatchOp  policyMatchOp  = null;

  /** opList. */
  private Collection opList = new Vector();
 }
