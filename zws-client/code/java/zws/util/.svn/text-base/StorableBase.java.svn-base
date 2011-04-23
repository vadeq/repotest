package zws.util; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.log.failure.Failure;
import zws.log.status.Status;
import zws.log.warning.Warning;

import java.util.Collection;
import java.util.Vector;

public class StorableBase implements Storable {
  
  public void store(Object o) throws Exception {
    if (null==storage) storage = new Vector();
    storage.add(o);
  }
  
  public void log(Failure f) throws Exception {
    
  }
  public void log(Warning w) {
    
  }
  public void log(Status s) {
    
  }
  public void throwOnFailure(boolean b) {
    
  }
  public Collection getResults() { return storage; }
  
  private Collection storage = null;
}