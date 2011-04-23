package zws.hi.IER.ownership;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 21, 2004, 6:43 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Synchronizer;
import zws.application.Properties;
import zws.application.server.webapp.Names;
import zws.data.Metadata;
import zws.origin.Origin;
import zws.search.filter.KeyFilter;
import zws.util.MapUtil;

import java.util.*;

public class OwnershipFilter extends KeyFilter  {
  public void filter(Collection list) throws Exception {
    Map names = new HashMap();
    Iterator i = list.iterator();
    Metadata data;
    Collection c;
    String name;
    while (i.hasNext()) {
      data = (Metadata)i.next();
      name = data.getName();
      c = MapUtil.getCollectionFromMap(names, name);
      c.add(data);
    }
    Collection syncs = new Vector();
    i = names.keySet().iterator();
    OwnershipMetadataAdapter a;
    while(i.hasNext()) {
      name = (String) i.next();
      c = MapUtil.getCollectionFromMap(names, name);
      a = defineOwnership(c);
      if (null!=a) syncs.add(a);
    }
    list.clear();
    list.addAll(syncs);
    resetStorage();
    initializeStorage(list);
  }
  private OwnershipMetadataAdapter defineOwnership(Collection list) {
    Synchronizer s = Synchronizer.getClient(Properties.get(Names.CENTRAL_SERVER));
    Iterator i = list.iterator();
    Metadata data;
    OwnershipMetadataAdapter adapter = new OwnershipMetadataAdapter();
    Origin a;
    while (i.hasNext()) {
      data = (Metadata) i.next();
      try {
        Collection c = s.findAllSynchronizationRecords(data.getOrigin());
        if (c==null) continue;
        Iterator r = c.iterator();
        while (r.hasNext()) {
          a = (Origin)r.next();
          if (a.getDatasourceType().equals(a.FROM_ILINK)) {
            adapter.adapt(data);
            break;
          }
        }
      }
      catch(Exception e) { e.printStackTrace(); }
    }
    if (2>adapter.getSynchronizationCount()) return null;
    return adapter;
  }
}
