package zws.op;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.InitializationError;
import zws.exception.UnsupportedOperation;
import zws.log.failure.Failure;
import zws.log.status.Status;
import zws.log.warning.Warning;

import zws.util.DomainContext;
import zws.util.JavaObjectUtil;


import java.io.Serializable;
import java.util.*;
import java.util.Collection;
import java.util.Vector;

public abstract class ListOpBase implements ListOp, Cloneable, Serializable {
  public abstract void execute() throws Exception;
  public void initialize() throws InitializationError {}

  public final void bind(Collection c ) throws Exception { bindingList = c; }
  public final Collection getBindingList() { return bindingList; }

  public final void resetStorage() { resultList=null; }
  public final void initializeStorage(Collection c) throws Exception {
    if (null!=resultList) throw new InitializationError("Storage is already initialized");
    resultList=c;
  }
  public void initializeStorage() {
    resultList=null;      
    resultList = new Vector();
  }

  public void store(Object o) throws Exception {
    if (null==resultList) throw new InitializationError("Storage has not been initialized");
    if (keep(o)) resultList.add(transform(o));
  }
  
  public void storeAll(Collection c) throws Exception {
    if (null==c) return;
    Iterator i = c.iterator();
    while (i.hasNext()) store(i.next());
  }

  protected boolean keep(Object o) throws Exception { return true; }
  protected Object transform(Object o) throws Exception { return o; }
  
  public final Collection getResults() { return resultList; }
  
  public final Object copy() {
    if (supportsDeepCopy()) return deepCopy();
    return shallowCopy();
  }

  public Object shallowCopy(){
    try {return clone(); }
    catch (CloneNotSupportedException e){throw new RuntimeException(e.getMessage()); }
  }

/*
  public Object deepCopy(){throw new UnsupportedOperation("deepCopy", "use shallowCopy() or an implementation that supports deepCopy()");}
  public boolean supportsDeepCopy() { return false; }
*/

  public boolean supportsDeepCopy() { return true; }

  public Object deepCopy() {
    try { return JavaObjectUtil.copy(this); }
    catch(Exception e) {
      e.printStackTrace();
      return null;
    }
	}
  public void throwOnFailure(boolean b) { throwOnFailure=b; }

  public void log(Status status) {
    if (null==statusLog) statusLog = new Vector();
    statusLog.add(status);
  }
  public void log(Warning warning) {
    if (null==warnings) warnings = new Vector();
    warnings.add(warning);
  }
  public void log(Failure failure) throws Exception {
    if (null==failures) failures = new Vector();
    failures.add(failure);
    if (throwOnFailure) {
      if (null!= failure.getException()) throw failure.getException();
      else throw new Exception(failure.getMessage());
    }
  }

  public void clearLogs() {statusLog=null; warnings=null; failures=null; }
  public void logStatus(Collection stats) {
    if (null==statusLog) statusLog = new Vector();
    statusLog.addAll(stats);
  }
  public void logWarnings(Collection warnings) {
    if (null==warnings) warnings = new Vector();
    warnings.addAll(warnings);
  }
  public void logFailures(Collection fails) {
    if (null==failures) failures = new Vector();
    failures.addAll(fails);
  }  
  
  public Collection getStatusLog() { return statusLog; }
  public Collection getWarnings() { return warnings; }
  public Collection getFailures() { return failures; }

  //Compatibility for Interface Interface Delegation
  public void bind(Object o) throws Exception { bind((Collection)o); }
  public Object getBinding() { return getBindingList(); }
  public Object getResult() { return getResults(); }
  public void setResult(Object o) { throw new UnsupportedOperation("setResult on an OpList: " + getClass().getName());}
  
  public DomainContext getContext(){ return context; }
  public void setContext(DomainContext c) { context=c; }

  public void inactivate() {}
  
  private boolean throwOnFailure = false;
  private Collection statusLog=null;
  private Collection warnings=null;
  private Collection failures=null;

  private Collection bindingList=null;
  private Collection resultList=null;
  private DomainContext context=null;
}
