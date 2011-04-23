package zws.util; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.log.failure.Failure;
import zws.log.status.Status;
import zws.log.warning.Warning;

import java.util.Collection;

public interface Storable {
  public void store(Object o) throws Exception;
  public void log(Failure f) throws Exception;
  public void log(Warning w);
  public void log(Status s);
  public void throwOnFailure(boolean b);
  public Collection getResults();
}