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
import zws.util.Prototype;

import java.util.Collection;

public interface Op extends Prototype {
  public void bind(Object o ) throws Exception;
  public Object getBinding();

  public void initialize() throws InitializationError; //some ops may need to be initialized with local data before serializing/transporting to another memory space or machine.
  public void execute() throws Exception;


  public void setResult(Object o);
  public Object getResult();

  public void throwOnFailure(boolean b);

  public void clearLogs();
  public void log(Status status);
  public void log(Warning warning);
  public void log(Failure failure) throws Exception;

  public Collection getStatusLog();
  public Collection getWarnings();
  public Collection getFailures();
  
  public DomainContext getContext();
  public void setContext(DomainContext c);
}