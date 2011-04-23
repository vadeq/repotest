package zws.search.filter;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 21, 2004, 6:43 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;

import java.util.*;

public class LatestByKey extends KeyFilter  {
  public void filter(Collection list) throws Exception {
    Map latest = new HashMap();
    Iterator i = list.iterator();
    Metadata data, stored;
    String key;
    while (i.hasNext()) {
      data = (Metadata)i.next();
      key = makeKey(data);
      stored = (Metadata) latest.get(key);
      if (null==stored || data.isLater(stored)) latest.put(key, data);
    }
    list.clear();
    list.addAll(latest.values());
    resetStorage();
    initializeStorage(list);
  }
}
