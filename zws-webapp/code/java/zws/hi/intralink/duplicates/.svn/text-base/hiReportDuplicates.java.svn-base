package zws.hi.intralink.duplicates;/*
DesignState - Design Compression Technology
@author: dstewart
@version: 1.0
Created on February 22, 2004, 9:14 PM
Copywrite (c) 2004 Zero Wait-State Inc. All rights reserved */


import zws.IntralinkClient;
import zws.Synchronizer;
import zws.application.server.webapp.Names;
import zws.application.Properties;
import zws.data.Metadata;
import com.zws.hi.hiList;

import zws.origin.Origin;
import zws.util.PrintUtil;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class hiReportDuplicates extends hiList{
 
  public hiReportDuplicates() {
    try {
			repositories1.setSelectedRepository("zws-nt4");
    }
    catch(Exception e) { e.printStackTrace(); }
  }

  public String reportDuplicates() throws Exception {
    activeServer0 = getSelectedIEEServer0();
    activeServer1 = getSelectedIEEServer1();
    activeRepository0 = getSelectedIEERepository0();
    activeRepository1 = getSelectedIEERepository1();
    clearItems();
    
    Collection dups = new Vector();
    Collection names0 = repositories0.listNames();
    PrintUtil.print(names0);
    Collection names1 = repositories1.listNames();
    PrintUtil.print(names1);
    
    Map names0Map = mapNames(names0);
    Iterator i = names1.iterator();
    Metadata m0, m1;
    DuplicateNameAdapter dup;
    while (i.hasNext()) {
      m1 = (Metadata)i.next();
      m0 = (Metadata) names0Map.get(m1.getName());
      if(null!=m0) { // found duplicate
        try { if (isSynchronized(m0.getName())) continue; }
        catch(Exception ignore) {}
        dup = new DuplicateNameAdapter();
        dup.adapt(m0, m1);
        dups.add(dup);          
      }
    }
    if (0==dups.size()) setItems(null);
    else setItems(dups);
    return ctrlOK;
  }

  public int getDuplicateCount() {
    if (null==getItems()) return 0;
    return getItems().size();
  }

  private boolean isSynchronized(String name) throws Exception {
    Synchronizer s = Synchronizer.getClient(Properties.get(Names.CENTRAL_SERVER));
    Collection c = s.findAllSynchronizationRecords(name);
    Origin o, o0=null, o1=null;
    Iterator i = c.iterator();
    while (i.hasNext()) {
      o = (Origin)i.next();
      if (o.getServerName().equals(repositories0.getSelectedServer()) && o.getDatasourceName().equals(repositories0.getSelectedRepository())) {
        o0 = o;
      }
      if (o.getServerName().equals(repositories1.getSelectedServer()) && o.getDatasourceName().equals(repositories1.getSelectedRepository())) {
        o1 = o;
      }
    }
    if (null!=o0 && null!=o1) return true;
    return false;
  }

  private Map mapNames(Collection metadataList) {
    Map map = new HashMap();
    Iterator i = metadataList.iterator();
    Metadata m;
    while (i.hasNext()) {
      m = (Metadata) i.next();
      map.put(m.getName(), m);
    }
    return map;
  }

  public Collection getRepositoryList0 () throws Exception {
    return repositories0.getRepositoryList(); 
  }

  public Collection getRepositoryList1 () throws Exception { 
    return repositories1.getRepositoryList(); 
  }


  public String getActiveIEEServer0() throws Exception { return activeServer0; }
  public String getActiveIEEServer1() throws Exception { return activeServer1; }
  public String getActiveIEERepository0() throws Exception { return activeRepository0; }
  public String getActiveIEERepository1() throws Exception { return activeRepository1; }

  public void setSelectedIEEServer0(String s) throws Exception { repositories0.setSelectedServer(s); }
  public void setSelectedIEEServer1(String s) throws Exception { repositories1.setSelectedServer(s); }
  public void setSelectedIEERepository0(String s) throws Exception { repositories0.setSelectedRepository(s); }
  public void setSelectedIEERepository1(String s) throws Exception { repositories1.setSelectedRepository(s); }

  public String getSelectedIEEServer0() throws Exception { return repositories0.getSelectedServer(); }
  public String getSelectedIEEServer1() throws Exception { return repositories1.getSelectedServer(); }
  public String getSelectedIEERepository0() throws Exception { return repositories0.getSelectedRepository(); }
  public String getSelectedIEERepository1() throws Exception { return repositories1.getSelectedRepository(); }
  
  private IntralinkClient repositories0 = new IntralinkClient();
  private IntralinkClient repositories1 = new IntralinkClient();
  
  private String activeServer0=null;
  private String activeServer1=null;
  private String activeRepository0=null;
  private String activeRepository1=null;
}
