package com.zws.functor.processor.action.tejas;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on November 13, 2003, 1:37 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import com.zws.datasource.SQLServerSource;
import com.zws.functor.processor.action.Action;
import com.zws.service.config.DataSourceService;

import java.util.Collection;
import java.util.Iterator;

public class LoadMissingSubComponents extends Action {
  
  public void execute() throws Exception  {
    SQLServerSource datasource = (SQLServerSource) DataSourceService.find(getDatasourceName());
    Collection missingComponents = datasource.findMissingSubComponents();
    Iterator i = missingComponents.iterator();
    while (i.hasNext()) {} //System.out.println((String) i.next());
    i = missingComponents.iterator();
    String name=null;
    while(i.hasNext()) {
      name = (String)i.next();
      if (null!=name) {
        getActionLog().log("loading missing component:" + name);
        datasource.removeComponent(name);
        getBulkCopier().setActionLog(getActionLog());
        getBulkCopier().setCriteria(name);
        getBulkCopier().execute();
      }
    }
  }

  public String getDatasourceName() { return datasourceName; }
  public void setDatasourceName(String s) { datasourceName = s; }
  
  public TejasSearch getBulkCopier() { return bulkCopier; }
  public void setBulkCopier(TejasSearch s) { bulkCopier=s; }
  
  public void addAction(TejasSearch a) { setBulkCopier(a); }  

  private String datasourceName = null;
  private TejasSearch bulkCopier = null;
}
/*
Incremental update
c = tblILinkComponent
a = tblILinkAttribute
s = tblILinkSubComponent
_s = staging table
_p = production table

1) truncate c_s & a_s
2) bcp released asm, prt & drw -> c_s & a_s
G3) sql: copy from c_p -> c_s where not exists in c_s
4) get BOM for released asm -> s_s
A5) find missing components: s_s(childID) where not exists c_s(ID)
A6) for each missing component
     delete from c_s & a_s matching on ID = %{component name}%
     bcp for component name -> c_s & a_s
G7) sql: insert c_p from c_s where not exists in c_p
         insert a_p from a_s where not exists in a_p (ID, name)
         insert s_p from s_s where not exists in s_p (parentID, childID)
G3) stored proc: spzwsSynchronizeComponent_Stg
G7) stored proc: spzwsIncrementalUpdate
 */