package zws.hi.IER; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Dec 27, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.ReplicationClient;

public class hiIER {
  public static ReplicationClient getReplicationClient() {
    ReplicationClient client = (ReplicationClient)replicator.get();
    return client;
  }
  
  private static ThreadLocal replicator = new ThreadLocal() {
    protected synchronized Object initialValue() {
      return new ReplicationClient();
    }
  };
}
