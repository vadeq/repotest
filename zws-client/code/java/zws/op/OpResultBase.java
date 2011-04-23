package zws.op;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 1, 2004, 8:27 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.log.failure.Failure;
import zws.log.status.Status;
import zws.log.warning.Warning;

import java.util.Collection;
import java.util.Vector;

public class OpResultBase {
  public void setResult(Object o) { result=o; }
  public Object getResult() { return result; }


  public void throwOnFailure(boolean b) { throwOnFailure=b; }

  public void clearLogs() {
    if (null!=statusLog) statusLog.clear();
    if (null!=warnings) warnings.clear();
    if (null!=failures) failures.clear();
    statusLog=null; 
    warnings=null; 
    failures=null; 
  }
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
  
  private Object result;
  private boolean throwOnFailure = false;
  private Collection statusLog=null;
  private Collection warnings=null;
  private Collection failures=null;
}
