/* DesignState - Design Compression Technology 
 * @author: ptoleti @version: 1.0
 * Created on Apr 26, 2007 12:03:51 PM Copywrite (c) 2007 Zero Wait-State Inc.
 * All rights reserved
 */
package zws.pen.policy.op.pendata;

import zws.data.Metadata;
import zws.exception.InvalidName;
import zws.op.OpBase;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.service.pen.DocumentElement;
import zws.service.pen.ECOElement;
import zws.service.pen.PENData;
import zws.service.pen.PENDataElement;
import zws.service.pen.RedlineElement;
import zws.service.pen.SourceDataElement;
import zws.service.pen.StatusElement;
import zws.service.pen.TargetDataElement;
import zws.service.pen.TxDataElement;
import zws.pen.policy.PENPolicy;
import zws.pen.policy.op.pendata.util.string.StringCollectionResult;
import zws.pen.policy.op.pendata.util.string.StringResult;
import zws.security.Authentication;
import zws.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import java.util.Vector;


/**
 * The Class PENDataOpBase.
 * @author ptoleti
 */
public abstract class PENDataOpBase extends OpBase implements PENDataOp {

  /**
   * set Context.
   * @param ctx QxContext
   * @see zws.pen.policy.old.op.PENDataOp#setCtx(zws.qx.QxContext)
   */
  public void setQxCtx(QxContext ctx) { this.qxContext = ctx; }

  /**
   * getContext.
   * @return context QxContext
   * @see zws.pen.policy.old.op.PENDataOp#getCtx()
   */
  public QxContext getQxCtx() {
    return this.qxContext;
  }
    
  
  public Object doOp(PENDataOp op) throws Exception {
    passConfiguration(op);
    op.execute();
    Object result = op.getResult();
    return result;
  }
  
  public Collection doOps() throws Exception {
    PENDataOp op = null;
    Iterator itr = getOps().iterator();
    Collection results = new Vector();
    Object result;
    boolean isContinue = !getPenData().isCancelled() || isContinueIfCancelled(); 
    while (itr.hasNext() && isContinue) {
      op = (PENDataOp) itr.next();
      result = doOp(op);
      results.add(result);
    }
    return results;
  }
  
  public Collection doCancelOps() throws Exception {
    PENDataOp op = null;
    Iterator itr = getOps().iterator();
    Collection results = new Vector();
    Object result;
    while (itr.hasNext()) {
      op = (PENDataOp) itr.next();
      result = doOp(op);
      results.add(result);
    }
    return results;
  }

  protected String concatenateDoOps() throws Exception {    
    Collection stringResults= doOps();
    if (null==stringResults) return "";

    String x = "";
    StringBuffer buf = new StringBuffer();

    buf.append(x);
    Iterator i = stringResults.iterator();
    while (i.hasNext()) {
      x = (String)i.next();
      if(null!=x) buf.append(x); 
    }
    x = buf.toString();
    return x;
  }

  protected Map doOpsAsAttributes() throws Exception {
    Map m = new HashMap();
    Collection propertyPairs = doOps();
    if (null==propertyPairs) return m;
    Iterator i = propertyPairs.iterator();
    Pair p;
    String attribute, value;
    while( i.hasNext()) {
      p = (Pair)i.next();
      attribute = (String)p.getObject0();
      value = (String)p.getObject1();
      if (null==attribute) throw new InvalidName("Attribute Name is Null");
      m.put(attribute, value);
    }
    return m;
  }
  
  /**
   * set PENData.
   * @param pData PENData
   * @see zws.pen.policy.old.op.PENDataOp#setPenData(zws.service.pen.PENData)
   */
  public void setPenData(PENData pData) {
    this.penData = pData;
  }

  /**
   * get PEN data.
   * @return penData PEMData
   * @see zws.pen.policy.old.op.PENDataOp#getPenData()
   */
  public PENData getPenData() {
    return this.penData;
  }

  /**
   * execute.
   * @throws Exception exception
   * @see zws.op.OpBase#execute()
   */
  public abstract void execute() throws Exception;

  /**
   * Add op.
   * @param op PENData
   * @throws Exception
   */
  public void add(PENDataOp op) throws Exception{
    opList.add(op);
  }
  /**
   * Gets the ops.
   * @return the ops
   */
  public Collection getOps() {
    return opList;
  }

  /**
   * @return Authentication
   * @see zws.pen.policy.old.op.PENDataOp#getAuthentication()
   */
  public Authentication getAuthentication() {
    return authID;
  }

  /**
   * @param policyObj PENPolicy
   * @see zws.pen.policy.old.op.PENDataOp#setPenPolicyObj(zws.pen.policy.PENPolicy)
   */
  public void setPenPolicy(PENPolicy policy) {
    penPolicy = policy;
  }

  /**
   * @return PENPolicy
   * @see zws.pen.policy.old.op.PENDataOp#getPenPolicyObj()
   */
  public PENPolicy getPenPolicy() {
    return penPolicy;
  }

  /**
   * @param authId Authentication
   * @see zws.pen.policy.old.op.PENDataOp#setAuthentication(zws.security.Authentication)
   */
    public void setAuthentication(Authentication authId) {
    authID = authId;
  }


    /**
     * @return the sourceOrigin
     */
    public Collection getOriginsToPublish() {
      return origins;
    }

    /**
     * @param sourceOrigin the sourceOrigin to set
     */
    public void setOriginsToPublish(Collection c) {
      this.origins = c;
    }

    /**
     * @return the currentItem
     */
    public String getCurrentItem() {
      return currentItem;
    }

    /**
     * @param item the currentItem to set
     */
    public void setCurrentItem(String item) throws InvalidName {
      if (null==item) { currentItem = null; return; }
      boolean found = getPenData().containsReference(item);
      if (!found) throw new InvalidName(item+" Not found in PENData reference Table");
      this.currentItem = item;
    }
    public Iterator getCurrentIterator() {
      return currentIterator;
    }

    public void setCurrentIterator(Iterator itr) {
      this.currentIterator = itr;
    }
   public PENDataElement lookupPENDataElement(String name) {
     return (PENDataElement) getPenData().lookupPENDataElement(name);
   }
   public Metadata lookupSrcMetadata(String name) {
     PENDataElement penDataElemt = (PENDataElement) getPenData().lookupPENDataElement(name);
     SourceDataElement srcElement = penDataElemt.getSourceDataElement();
     return srcElement.getSourceData();
   }
   public Metadata lookupTxMetadata(String name) {
     PENDataElement penDataElemt = (PENDataElement) getPenData().lookupPENDataElement(name);
     TxDataElement txElement = penDataElemt.getTxDataElement();
     return txElement.getTxData();
   }

   public Metadata lookupTargetMetadata(String name) {
     PENDataElement penDataElemt = (PENDataElement) getPenData().lookupPENDataElement(name);
     TargetDataElement targetElement = penDataElemt.getTargetDataElement();
     return targetElement.getTargetData();
   }

   public SourceDataElement lookupSourceDataElement(String name) {
     PENDataElement penDataElemt = (PENDataElement) getPenData().lookupPENDataElement(name);
     return penDataElemt.getSourceDataElement();
   }

   public TxDataElement lookupTxDataElement(String name) {
     PENDataElement penDataElemt = (PENDataElement) getPenData().lookupPENDataElement(name);
     return penDataElemt.getTxDataElement();
   }
   public DocumentElement lookupDocumentElement(String name) {
     PENDataElement penDataElemt = (PENDataElement) getPenData().lookupPENDataElement(name);
     return penDataElemt.getDocumentElement();
   }

   public TargetDataElement lookupTargetDataElement(String name) {
     PENDataElement penDataElemt = (PENDataElement) getPenData().lookupPENDataElement(name);
     return penDataElemt.getTargetDataElement();
   }
   public StatusElement lookupStatusElement(String name) {
     PENDataElement penDataElemt = (PENDataElement) getPenData().lookupPENDataElement(name);
     return penDataElemt.getStatusElement();
   }
   public ECOElement lookupECOElement(String name) {
     PENDataElement penDataElemt = (PENDataElement) getPenData().lookupPENDataElement(name);
     return penDataElemt.getECOElement();
   }
   public RedlineElement lookupRedlineElement(String name) {
     PENDataElement penDataElemt = (PENDataElement) getPenData().lookupPENDataElement(name);
     return penDataElemt.getRedlineElement();
   }


    /**
     * @param op opobj
     */
    public void passConfiguration(PENDataOp op) {
      if (null==op) return;
      op.setQxCtx(getQxCtx());
      op.setPenData(getPenData());
      op.setOriginsToPublish(getOriginsToPublish());
      op.setAuthentication(getAuthentication());
      op.setContext(getContext());
      op.setPenPolicy(getPenPolicy());
      try {op.setCurrentItem(getCurrentItem()); }
      catch(InvalidName ignore) { ignore.printStackTrace(); }
      op.setCurrentIterator(getCurrentIterator());
  }

    /**
     * @param qxctx context
     * @param originObj origin
     * @param penPolicy policy obj
     * @param penDataObj pen data
     * @param id authentication
     */
    public void initializeRuntime(QxContext qxctx, Collection origins, PENPolicy penPolicy, PENData penData, Authentication id) {
      this.setQxCtx(qxctx);
      this.setPenData(penData);
      this.setOriginsToPublish(origins);
      this.setAuthentication(id);
      this.setContext(getContext());
      this.setPenPolicy(penPolicy);
      try {setCurrentItem(getCurrentItem()); }
      catch(InvalidName ignore) { ignore.printStackTrace(); }
      this.setCurrentIterator(getCurrentIterator());
    }

    protected String lookupLogicalName()throws Exception
    {
      String refName = getLogicalName();
      if (null==logicalNameOp) return refName;

      PENDataOp op = getLogicalNameOp();
      doOp(op);
      refName = (String)op.getResult();
      return refName;
    }

    protected Collection lookupLogicalNames()throws Exception
    {
      Collection refNames = getLogicalNames();      
      if (null==logicalNamesOp) return refNames;

      PENDataOp op = getLogicalNamesOp();
      doOp(op);
      refNames = (Collection)op.getResult();
      return refNames;
    }    
    
    
    public String getLogicalName() { return logicalName; }  
    public void setLogicalName(String s) { logicalName = s; }
    public Collection getLogicalNames() { return logicalNames; }  
    public void setLogicalNames(Collection s) { logicalNames = s; }    
    public StringResult getLogicalNameOp() { return logicalNameOp; }
    public void setLogicalNameOp(StringResult op) { logicalNameOp = op; }
    public StringCollectionResult getLogicalNamesOp() {return logicalNamesOp;}
    public void setLogicalNamesOp(StringCollectionResult op) {logicalNamesOp = op;}    
    
    protected static synchronized long nextCount() {
      return count++;
    }
    public boolean isContinueIfCancelled() { return continueIfCancelled;  }
    public void setContinueIfCancelled(boolean b) {continueIfCancelled = b; }
    
 private static long count = 0;    
    
  /** The pen policy obj. */
  private PENPolicy penPolicy= null;

  /** The auth ID. */
  private Authentication authID = null;

  /** opList. */
  private Collection opList = new Vector();

  /** context. */
  private QxContext qxContext = null;

  /** penData. */
  private PENData penData = null;

  private Collection origins = null;
  private String currentItem = null;
  private Iterator currentIterator = null;

  //logicalName for convenience 
  private StringResult logicalNameOp = null;
  private String logicalName = null;      
  private boolean continueIfCancelled = false;
  
  private Collection logicalNames = null;
  private StringCollectionResult logicalNamesOp = null;


}
