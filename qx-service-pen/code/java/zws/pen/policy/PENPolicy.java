package zws.pen.policy;

/*
 * DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0 Created on Apr 2, 2007 11:09:06 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */


import zws.application.Names;
import zws.context.Context;
import zws.exception.NameNotFound;
import zws.op.Op;
import zws.origin.Origin;
import zws.pen.policy.declaration.Sequence;
import zws.pen.policy.op.pendata.PENDataOp;
import zws.qx.QxContext;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.security.Authentication;
import zws.service.pen.PENData;
import zws.service.repository.RepositorySvc;
import zws.util.Prototype;
import zws.util.Named;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The Class PENPolicy.
 *
 * @author ptoleti
 */
public class PENPolicy implements Named, Serializable, Prototype {

  /**
   * Sets the update src op.
   * @param op retrieve src op
   */
  public void setRetrieveSrcOp(PENDataOp op) {
    retrieveSourceOp = op;
  }


  public void open(Authentication authID) throws Exception {
    Authentication id = authID;
    getSourceRepository().open(id);
    getTargetRepository().open(id);
  }
  

  public void close(Authentication authID) throws Exception {
    Authentication id = authID;
    getSourceRepository().close(id);
    getTargetRepository().close(id);
  }

  /**
   * Retrieve source.
   * @param policyObj the policy obj
   * @param authID the auth ID
   * @param qxctx the ctx
   * @param penDataObj the pen data
   * @param origin Origin
   * @throws Exception exception
   */
  public void getData(QxContext qxctx, PENData penDataObj, PENPolicy policyObj,
    Collection originsToPublish, Authentication authID) throws Exception {
    QxContext processCtx = RecorderUtil.createChildContext(qxctx, Names.PEN_GET_METADATA, Names.STATUS_PUBLISHING, Names.ACTIVITY_FINDING, displayOrigins(originsToPublish));
    executeOP(Names.PEN_GET_METADATA,retrieveSourceOp, processCtx, penDataObj, policyObj, originsToPublish, authID);
 }

  /**
   * Retrieve Binary.
   * @param policyObj the policy obj
   * @param authID the auth ID
   * @param qxctx the ctx
   * @param penDataObj the pen data
   * @param location the pen location
   * @param origin Origin
   * @throws Exception exception
   */
  public void retrieveBinarySource(QxContext qxctx, PENData penDataObj, PENPolicy policyObj,Collection originsToPublish, String location, Authentication authID) throws Exception {
    QxContext processCtx = RecorderUtil.createChildContext(qxctx, Names.PEN_GET_BINARY_DATA, Names.STATUS_PUBLISHING, Names.ACTIVITY_DOWNLOADING, displayOrigins(originsToPublish));
    executeOP(Names.PEN_GET_BINARY_DATA,retrieveSourceOp, processCtx, penDataObj, policyObj, originsToPublish, authID);
 }

  /**
   * Sets the update src op.
   * @param op the update src op
   */

  public void setTransformSrcOp(PENDataOp op) {
    transformSourceOp = op;
  }

  /**
   * transformsource.
   * @param policyObj the policy obj
   * @param authID the auth ID
   * @param qxctx the ctx
   * @param penDataObj the pen data
   * @param origin Origin
   * @throws Exception exception
   */

  public void transformSource(QxContext qxctx, PENData penDataObj, PENPolicy policyObj,Collection originsToPublish, Authentication authID) throws Exception {
    QxContext processCtx = RecorderUtil.createChildContext(qxctx, Names.PEN_TRANSFORM_DATA, Names.STATUS_PUBLISHING, Names.ACTIVITY_TRANSFORMING, displayOrigins(originsToPublish));
    executeOP(Names.PEN_TRANSFORM_DATA,transformSourceOp, processCtx, penDataObj, policyObj, originsToPublish, authID);
 }

  /**
   * Sets the publish target op.
   * @param op the update src op
   */
  public void setAnalyzeDataOp(PENDataOp op) {
    analyzeDataOp = op;
  }

  /**
   * Publish Target.
   * @param policyObj the policy obj
   * @param authID the auth ID
   * @param qxctx the ctx
   * @param penDataObj the pen data
   * @param origin Origin
   * @throws Exception exception
   */
  public void analyzeTxData(QxContext qxctx, PENData penDataObj, PENPolicy policyObj,Collection originsToPublish, Authentication authID) throws Exception {
    QxContext processCtx = RecorderUtil.createChildContext(qxctx, Names.PEN_ANALYZE_DATA, Names.STATUS_ANALYZING, Names.ACTIVITY_PROCESSING, displayOrigins(originsToPublish));
    executeOP(Names.PEN_ANALYZE_DATA,analyzeDataOp, processCtx, penDataObj, policyObj, originsToPublish, authID);
  }


  /**
   * Sets the publish target op.
   * @param op the update src op
   */
  public void setCancelPublishOp(PENDataOp op) {
    cancelPublishOp = op;
  }

  /**
   * Cancel Publish.
   * @param policyObj the policy obj
   * @param authID the auth ID
   * @param qxctx the ctx
   * @param penDataObj the pen data
   * @param origin Origin
   * @throws Exception exception
   */
  public void cancelPublish(QxContext qxctx, PENData penDataObj, PENPolicy policyObj, Collection originsToPublish, Authentication authID) throws Exception {
    QxContext processCtx = RecorderUtil.createChildContext(qxctx, Names.PEN_CANCEL_PUBLISH, Names.STATUS_CANCELED, Names.ACTIVITY_PROCESSING, displayOrigins(originsToPublish));
    executeOP(Names.PEN_CANCEL_PUBLISH, cancelPublishOp, processCtx, penDataObj, policyObj, originsToPublish, authID);
  }

  /**
   * Sets the publish target op.
   * @param op the update src op
   */
  public void setPublishTargetOp(PENDataOp op) {
    publishTargetOp = op;
  }

  /**
   * Publish Target.
   * @param policyObj the policy obj
   * @param authID the auth ID
   * @param qxctx the ctx
   * @param penDataObj the pen data
   * @param origin Origin
   * @throws Exception exception
   */
  public void publishTargetData(QxContext qxctx, PENData penDataObj, PENPolicy policyObj, Collection originsToPublish, Authentication authID) throws Exception {
    QxContext processCtx = RecorderUtil.createChildContext(qxctx, Names.PEN_CHECKIN_DATA, Names.STATUS_PUBLISHING, Names.ACTIVITY_CHECK_IN, displayOrigins(originsToPublish));
    executeOP(Names.PEN_CHECKIN_DATA,publishTargetOp, processCtx, penDataObj, policyObj, originsToPublish, authID);
  }


  /**
   * Sets the update src op.
   * @param op the update src op
   */
  public void setUpdateSrcOp(PENDataOp op) {
    updateSourceOp = op;
  }

  /**
   * Update source.
   * @param policyObj the policy obj
   * @param authID the auth ID
   * @param qxctx the ctx
   * @param penDataObj the pen data
   * @param origin Origin
   * @throws Exception exception
   */
  public void updateSource(QxContext qxctx, PENData penDataObj, PENPolicy policyObj, Collection originsToPublish, Authentication authID) throws Exception {
    QxContext processCtx = RecorderUtil.createChildContext(qxctx, Names.PEN_UPDATE_SOURCE, Names.STATUS_PUBLISHING, Names.ACTIVITY_UPDATING, displayOrigins(originsToPublish));
    executeOP(Names.PEN_UPDATE_SOURCE,updateSourceOp, processCtx, penDataObj, policyObj, originsToPublish, authID);
  }

  /**
   * @param penDataOp penDataOP
   * @param processCtx qxcontext
   * @param penDataObj pendata
   * @param policyObj policy object
   * @param origin origin
   * @param authID authentication
   * @throws Exception exception
   */
  public void executeOP(String opName, PENDataOp penDataOp, QxContext processCtx, PENData penDataObj, PENPolicy policyObj, Collection originsToPublish, Authentication authID) throws Exception {
    String processStatus = null;
    try {
      if (null == penDataOp) {
        throw new Exception("penDataOp " + opName + "is not defined in the policy " + policyObj.getName());
        }
      penDataOp.initializeRuntime(processCtx, originsToPublish, policyObj, penDataObj, authID);
      penDataOp.execute();
      processStatus = Names.STATUS_COMPLETE;
    } catch (Exception e) {
      processStatus = Names.STATUS_ERROR;
      e.printStackTrace();
      throw e;
    } finally {
      RecorderUtil.removeChildContext(processCtx, processStatus);
    }
  }

  /**
   * Gets the source repository.
   * @return Repository
   *  @throws Exception the exception
   */
  public Repository getSourceRepository() throws Exception {
    try {
      if (srcRepository == null) {
        {}//Logwriter.printOnConsole("in getSourceRepository " + getSrcRepositoryName());
        srcRepository = RepositorySvc.find(getSrcRepositoryName());
      }
    } catch (NameNotFound exp) {
      exp.printStackTrace();
      {}//Logwriter.printOnConsole("NameNotFound exception in getSourceRepository()");
      throw exp;
    } catch (Exception exp) {
      exp.printStackTrace();
      {}//Logwriter.printOnConsole("Exception in PENPolicy.getSourceRepository()");
      throw exp;
    }
    return srcRepository;
  }

  /**
   * Gets the target repository.
   * @return TargetRepository
   */
  public Repository getTargetRepository() {
    try {
      if (targetRepository == null) {
        {}//Logwriter.printOnConsole("In getTargetRepository() " + getTargetRepositoryName());
        targetRepository = RepositorySvc.find(getTargetRepositoryName());
      }
    } catch (NameNotFound exp) {
      exp.printStackTrace();
      {}//Logwriter.printOnConsole("NameNotFound exception in getTargetRepository()");
    } catch (Exception exp) {
      exp.printStackTrace();
      {}//Logwriter.printOnConsole("Exception in getTargetRepository()");
    }
    return targetRepository;
  }

  /**
   * Defines configuration parameters specific for this PENPolicy.
   * @param parameters the parameters
   * @throws NotSerializableException the not serializable exception
   */

  public void configureContext(Map parameters) throws NotSerializableException {
    ctx.set(parameters);
  }

  /**
   * Get the copy og Policyobject.
   * @return Policy object
   * @see zws.util.Prototype#copy()
   */
  public Object copy() {
    PENPolicy penPolicyClone = null;
    try {
      Context ctxClone = new Context();
      ctxClone.set(ctx.getProperties());
      penPolicyClone = (PENPolicy) clone();
    } catch (Exception e) {
      e.printStackTrace();
      {}//Logwriter.printOnConsole("Exception in PENPolicy copy()");
    }
    return penPolicyClone;
  }

  /**
   * Sets the name.
   * @param srcPolicyName the name
   */
  public void setName(String srcPolicyName) {
    ctx.set(sNAME, srcPolicyName);
  }

  /**
   * Get the policy object name.
   * @return Policy name
   *  @see zws.util.Named#getName()
   */
  public String getName() {
    return ctx.getString(sNAME);
  }

  /**
   * Sets the src repository name.
   * @param srcRepName the src repository name
   */
  public void setSrcRepositoryName(String srcRepName) {
    ctx.set(sSrcRRepositoryName, srcRepName);
  }

  /**
   * Gets the src repository name.
   * @return the src repository name
   */
  public String getSrcRepositoryName() {
    return ctx.getString(sSrcRRepositoryName);
  }

  /**
   * Sets the target repository name.
   * @param targetRepName the target repository name
   */
  public void setTargetRepositoryName(String targetRepName) {
    ctx.set(sTargetRepositoryName, targetRepName);
  }

  /**
   * Gets the target repository.
   * @return the target repository name
   */
  public String getTargetRepositoryName() {
    return ctx.getString(sTargetRepositoryName);
  }

  /**
   * Get deep copy of Policy object.
   * @return deep copyof Policy object
   * @see zws.util.Prototype#deepCopy()
   */
  public Object deepCopy() {
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
  public Object shallowCopy() {
    return null;
  }

  /**
   * determines ability to get deep copy of policy object.
   * @return true or false
   * @see zws.util.Prototype#supportsDeepCopy()
   */
  public boolean supportsDeepCopy() {
    return false;
  }

  private static String sequenceKey(String name) {
    String key = name.toLowerCase();
    return key;
  }

  public static void defineSequence(Sequence sequence) {
    sequences.put(sequenceKey(sequence.getName()), sequence);
  }

  public static Sequence lookupSequence(String name) {
    Sequence sequence = (Sequence) sequences.get(sequenceKey(name));
    return sequence;
  }

  private static String procedureKey(String name) {
    String key = name.toLowerCase();
    return key;
  }

  public static void defineProcedure(Named op) {
    procedures.put(procedureKey(op.getName()), op);
  }

  public static Op lookupProcedure(String name) {
    Op op = (Op) procedures.get(procedureKey(name));
    return op;
  }

  public static String displayOrigins(Collection origins) {
    StringBuffer list = new StringBuffer();
    Iterator itr = origins.iterator();
    Origin o = null;
    while(itr.hasNext()) {
      o = (Origin) itr.next();
      list.append(o.getName()).append(" ");
    }
    return list.toString();
  }
/**
  @return recorder
  */

  /** The ctx. */
  private Context ctx = new Context();

  /** The NAME. */
  private static String sNAME = "NAME";

  /** The SR c_ REPOSITOR y_ NAME. */
  private static String sSrcRRepositoryName = "SRC_REPOSITORY_NAME";

  /** The s target repository name. */
  private static String sTargetRepositoryName = "TARGET_REPOSITORY_NAME";

  /** The target repository. */
  private Repository targetRepository = null;

  /** The src repository. */
  private Repository srcRepository = null;

  /** The update source op. */
  private PENDataOp updateSourceOp = null;

  /** The transform source op. */
  private PENDataOp transformSourceOp = null;

  /** The retrieve source op. */
  private PENDataOp retrieveSourceOp = null;

  /** The retrieve source op. */
  private PENDataOp publishTargetOp = null;
  /** The analyze data op. */
  private PENDataOp analyzeDataOp = null;
  /** The cancel publish op. */
  private PENDataOp cancelPublishOp = null;

  private static Map sequences = new HashMap();
  private static Map procedures = new HashMap();
}

