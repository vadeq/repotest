package zws.op;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.InitializationError;
import zws.log.failure.Failure;
import zws.log.status.Status;
import zws.log.warning.Warning;
import zws.util.DomainContext;
import zws.util.JavaObjectUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.Vector;

public abstract class OpBase implements Op, Cloneable, Serializable {
  public void bind(Object o )throws Exception {binding=o;}
  public Object getBinding() { return binding; }
  public void initialize() throws InitializationError {}
  public void setResult(Object o) { result=o; }
  public Object getResult() { return result; }
  public Collection getStatusLog() { return statusLog; }
  public Collection getWarnings() { return warnings; }
  public Collection getFailures() { return failures; }
  public DomainContext getContext(){ return context; }
  public void setContext(DomainContext c) { context=c; }
  public void inactivate() {}
  public boolean supportsDeepCopy() { return true; }
  public abstract void execute() throws Exception;
  
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
  public void log(Failure failure) throws Exception  {
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
  
 
//  public Object clone() throws CloneNotSupportedException { return super.clone(); }

  private boolean throwOnFailure = false;
  private Object binding=null;
  private Object result=null;
  private Collection statusLog=null;
  private Collection warnings=null;
  private Collection failures=null;
  private DomainContext context=null;
}
