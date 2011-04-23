package zws.service.synchronization;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 31, 2004, 10:34 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.log.failure.Failure;
import zws.op.OpBase;
import zws.origin.Origin;
import zws.util.Pair;

import java.util.*;

public class DatasourceSynchronizer extends OpBase {
  public void execute() throws Exception { synchronize(list0, list1); }

  public void synchronize(Collection metadataList0, Collection metadataList1) throws Exception {    
    if (null==metadataList0 || null==metadataList1 || metadataList0.size()<1 || metadataList1.size() <1) return;
    Map m0 = latestOrigins(metadataList0);
    Map m1 = latestOrigins(metadataList1);
    Origin o0, o1;
    Map match = new HashMap();
    Iterator i = m0.keySet().iterator();
    String key;
    while (i.hasNext()) {
      key = (String)i.next();
      o0 = (Origin)m0.get(key);
      o1 = (Origin)m1.get(key);
      if (null!=o0 && null !=o1) match.put(key, new Pair(o0, o1));
    }
    
    i = match.values().iterator();
    Pair p;
    throwOnFailure(false);
    while (i.hasNext()) {
      p = (Pair)i.next();
      try { SynchronizationSvc.record((Origin)p.getObject0(), (Origin)p.getObject1()); }
      catch (Exception e) { log(new Failure(e)); }
    }
  }
  
  public Map latestOrigins(Collection c) {
    Map origins = new HashMap();
    Metadata m;
    Origin o;
    Iterator i = c.iterator();
    while (i.hasNext()) {
      m = (Metadata)i.next();
      o = (Origin)origins.get(m.getName());
      if (null==o) origins.put(m.getName(), m.getOrigin());
      else {
        if (o.isEarlier(m.getOrigin())) origins.put(m.getName().toLowerCase(), m.getOrigin());
      }
    }
    return origins;
  }
  
  public Collection getMetadataList0(Collection c) { return list0; }
  public void setMetadataList0(Collection c) { list0=c; }
  public Collection getMetadataList1(Collection c) { return list1; }
  public void setMetadataList1(Collection c) { list1=c; }
  
  private transient Collection list0=null; 
  private transient Collection list1=null;
}
