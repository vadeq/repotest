package zws.op;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 1, 2004, 8:32 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.log.failure.Failure;
import zws.log.status.Status;
import zws.log.warning.Warning;

import java.io.Serializable;
import java.util.Collection;

public interface OpResult extends Serializable {
  public void setResult(Object o);
  public Object getResult();

  public void throwOnFailure(boolean b);
  public void clearLogs();
  public void log(Status status);
  public void log(Warning warning);
  public void log(Failure failure) throws Exception;

  public void logStatus(Collection stats);
  public void logWarnings(Collection warnings);
  public void logFailures(Collection fails);
  
  public Collection getStatusLog();
  public Collection getWarnings();
  public Collection getFailures(); 
}
