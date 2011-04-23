package zws.hi.replication.ownership;/*
DesignState - Design Compression Technology
@author: dstewart
@version: 1.0
Created on February 22, 2004, 9:14 PM
Copywrite (c) 2004 Zero Wait-State Inc. All rights reserved */

import zws.IntralinkAccess;
import zws.application.server.webapp.Names;
import zws.report.Report;
import zws.util.comparator.metadata.PartNumberOrder;
import zws.hi.IER.ownership.OwnershipFilter;
import zws.hi.IER.ownership.OwnershipMetadataAdapter;
import zws.hi.report.MetadataAdapter;
import zws.hi.report.hiReport;
import zws.origin.Origin;

import java.util.Comparator;
import java.util.Iterator;


public class hiOwnership extends hiReport {

  protected String getZeroResultsKey() { return statFOUND_ZERO_OWNED_FILES; }
    
	public void startRequest() {
	  if (getChosenItems()==null) return;
	  Iterator i =null;
	  OwnershipMetadataAdapter data;
	  int idx;
	  if (selectedOwnershipSites!=null) {
	    i = getChosenItems().iterator();
	    idx=0;
	    String n,q;
	    while (i.hasNext() && idx < selectedOwnershipSites.length) {
	      data =(OwnershipMetadataAdapter)i.next();
	      n = selectedOwnershipSites[idx];
	      data.setTransferToSite(selectedOwnershipSites[idx]);
	      idx++;
	    }
	  }
	}

   public Comparator getComparator() {
     PartNumberOrder c = new PartNumberOrder();
     String sortKeyFields="Name | Rev | Ver | Datasource";
     c.setKeyFields(sortKeyFields);
     return c;
   }

   public String getSelectedReportName() { return getReport().getName(); }

   public Report getReport() {
	   if (null==ownershipReport) {
	     try {
        ownershipReport = getIEE().materializeOwnershipReport();
    		ownershipReport.add(new OwnershipFilter());
	     }
	     catch( Exception e) { e.printStackTrace(); return null; }
	   }
	   return ownershipReport;
	 }

   protected MetadataAdapter createNewMetadataAdapter() { return null; }
  //protected MetadataAdapter createNewMetadataAdapter() { return new OwnershipMetadataAdapter(); }

  public boolean idChoosesItem(String id, Object item) {
    String o = ((MetadataAdapter)item).getName();
    return id.equals(o);
  }

  public boolean isChosen(Object o) {
    Origin origin = ((MetadataAdapter)o).getOrigin();
    Iterator i = getChosenItems().iterator();
    //Origin x;
    while (i.hasNext()) if( ((MetadataAdapter)i.next()).getOrigin().isTheSameAs(origin)) return true;
    return false;
  }

  public String transferOwnership() {
    Iterator i = getChosenItems().iterator();
    OwnershipMetadataAdapter m;
    String c=null;
    while (i.hasNext()) {
      m = (OwnershipMetadataAdapter)i.next();
      if (m.getTransferToSite().equals("--------")) {
        logFormWarning("warning.select.site");
        return ctrlERROR;
      }
      transferOwnership(m);
      if (null==c) c = "";
      else c += " " + Names.CRITERIA_OR_OP + " ";
      c += "(Name='"+m.getName()+"')";
    }
    logFormStatus("status.ownership.transfered");
    setCriteria(c);
    {} //System.out.println("defining criteria as  " +c);
    search();
    getChosenItems().clear();
    return ctrlOK;
  }

  public void transferOwnership(OwnershipMetadataAdapter m) {
    IntralinkAccess access = IntralinkAccess.getAccess();
    
    Iterator i = m.getSynchronizedSites().iterator();
    //String servr = m.getOwnedServer(); //+++ look up server from a site mapping for the site's alias
    String site = null;
    while (i.hasNext()) {
      site=(String)i.next();
      if (m.getTransferToSite().equals(site)) {
        try {
           access.unlock(m.findServerForSite(site), site, m.getName(), null);
           {} //System.out.println(m.getName() + " unlocked at " + site);
        }
        catch(Exception e) {
          {} //System.out.println("could not unlock " + m.getName() + " at " + site);
          {} //System.out.println(e.getMessage());
        }
      }
    }
    
	      try {
          access.lock(m.getOwnedServer(), m.getOwnedDatasource(), m.getName(), null);
	        {} //System.out.println(m.getName() + " locked at " + site);
	      }
	      catch(Exception e) {
	        {} //System.out.println("could not lock " + m.getName() + " at " + site);
	        {} //System.out.println(e.getMessage());
	      }
    }

  public void render() { 
    //synchronizePDFs(getItems()); 
    //synchronizePDFs(getChosenItems()); 
    //synchronizePDFs(printLog); 
  }

  /*
  private void synchronizePDFs(Collection items) {
   if (null==items) return;
   OwnershipMetadataAdapter data;
   Iterator i = items.iterator();
   while (i.hasNext()) {
     data = (OwnershipMetadataAdapter)i.next();
     data.findSynchronizations();
   }
  }
  */
/*
  public Collection getSiteNames() {
    if (null==ownershipSites) {
      ownershipSites = new Vector();
      ownershipSites.add("ilink-0");
      ownershipSites.add("ilink-1");
      ownershipSites.add("ilink-2");
    }
    Collection c = new Vector();
    c.add("--------");
    c.addAll(ownershipSites);
    return c; 
  }
*/
  
  public String[] getSelectedOwnershipSites() { return selectedOwnershipSites; }
  public void setSelectedOwnershipSites(String[] sites) { selectedOwnershipSites=sites; }

  //private Collection ownershipSites=null;
  private String[] selectedOwnershipSites;

  //private Synchronizer synkService = Synchronizer.getClient(Properties.get(Names.CENTRAL_SERVER)); 
  private Report ownershipReport = null;    
}
