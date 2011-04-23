package zws.op;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.InitializationError;
import zws.log.failure.Failure;
import zws.log.status.Status;
import zws.log.warning.Warning;
import zws.util.Storable;

import java.util.Collection;

public interface ListOp extends Op, Storable {
  public void bind(Collection c ) throws Exception;
  public Collection getBindingList();

  public void initialize() throws InitializationError; //some ops may need to be initialized with local data before serializing/transporting to another memory space or machine.
  public void execute() throws Exception;

  public void store(Object o) throws Exception;
  public void storeAll(Collection c) throws Exception;
  public void initializeStorage();
  public void initializeStorage(Collection c) throws Exception;
  public void resetStorage();

  public Collection getResults();

  public void log(Status status);
  public void log(Warning warning);
  public void log(Failure failure) throws Exception;
  
  public void throwOnFailure(boolean b);

  public void clearLogs();
  public Collection getStatusLog();
  public Collection getWarnings();
  public Collection getFailures();
}
