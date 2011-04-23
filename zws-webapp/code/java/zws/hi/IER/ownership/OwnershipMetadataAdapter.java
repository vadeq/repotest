package zws.hi.IER.ownership;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 26, 2005, 12:58 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.DatasourceAccess;
import zws.Synchronizer;
import zws.application.Properties;
import zws.application.server.webapp.Names;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.hi.report.MetadataAdapter;
import zws.origin.Origin;
import zws.util.FileNameUtil;

import java.util.*;
import java.util.Collection;
import java.util.Iterator;
import zws.util.MapUtil;
import zws.util.comparator.AlphaNumericComparator;

public class OwnershipMetadataAdapter extends MetadataAdapter {

  public void adapt(Metadata data) {
    name = data.getName();   
    metadataMap.put(data.getOrigin().getDatasourceName(), data);
  }
  
  public String getName() { return name; }
  
  public String getSiteOwner() {
    return getOwnedMetadata().getOrigin().getDatasourceName();
  }

  public String getLockedBy() {
    try {
      return getOwnedMetadata().get(ATT_LOCKED_BY);
    }
    catch(Exception e) { e.printStackTrace(); }
    return "";
  }

  public String getLockTime() {
    return getOwnedMetadata().get(ATT_LOCK_TIME);    
  }

  public String getLockEmail() {
    return getOwnedMetadata().get(ATT_LOCK_EMAIL);      
  }

  private Metadata getOwnedMetadata() {
    Metadata data=null, m=null;
    Iterator i = metadataMap.values().iterator();
    String lockedBy;
    
    while(i.hasNext()) {
      data = (Metadata) i.next();
      lockedBy = data.get(ATT_LOCKED_BY);
      {} //System.out.println("locked by = " + lockedBy + " at " + data.getOrigin().getDatasourceName());
      if ("designstate".equalsIgnoreCase(lockedBy)) continue;
      else m=data;
    }
    if (null==m) {
      m = data;
      {} //System.out.println("owned metadata not found !!!!!!!!!!!");
    }
    return m;
  }

  public String getOwnedServer() {
    return getOwnedMetadata().getOrigin().getServerName();
  }

  public String getOwnedDatasource() {
    return getOwnedMetadata().getOrigin().getDatasourceName();
  }
  
  public Collection getOwnershipTransferSites() {
    Metadata data, m=null;
    Iterator i = metadataMap.values().iterator();
    String lockedBy;
    Collection sites = new TreeSet(new AlphaNumericComparator());

    while(i.hasNext()) {
      data = (Metadata) i.next();
      lockedBy = data.get(ATT_LOCKED_BY);
      if ("designstate".equalsIgnoreCase(lockedBy)) sites.add(data.getOrigin().getDatasourceName());
    }
    return sites;
  }


  public Collection getSynchronizedSites() {
	  Metadata data;
	  Iterator i = metadataMap.values().iterator();
	  Collection sources = new TreeSet(new AlphaNumericComparator());
	
	  while(i.hasNext()) {
	    data = (Metadata) i.next();
	    sources.add(data.getOrigin().getDatasourceName());
	  }
	  return sources;
  }
  

  public String findServerForSite(String site) {
	  Metadata data;
	  Iterator i = metadataMap.values().iterator();
	  while(i.hasNext()) {
	    data = (Metadata) i.next();
	    if (site.equals(data.getOrigin().getDatasourceName())) return data.getOrigin().getServerName();
	  }
	  return null;
  }
  
  public String getDataSources() {
	  Iterator i = metadataMap.values().iterator();
	  Collection sources = getSynchronizedSites();
	  String s="";
	  i = sources.iterator();
	  if (i.hasNext()) s = "["+ i.next() + "]" ;
	  while (i.hasNext()) s += " [" + i.next() + "]";
	  return s;
  }
  
  public void setTransferToSite(String s) { transferToSite = s; }
  public String getTransferToSite() { return transferToSite; }
  
  public int getSynchronizationCount() { return metadataMap.size(); }

  private Map metadataMap = new HashMap();
  private String transferToSite = null;
  private String name = null;

  private String ATT_LOCKED_BY = "Locked-By";
  private String ATT_LOCK_TIME = "Lock-Time";
  private String ATT_LOCK_EMAIL = "Lock-Email";
}
