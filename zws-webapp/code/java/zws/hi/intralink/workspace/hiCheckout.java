package zws.hi.intralink.workspace;/*
DesignState - Design Compression Technology
@author: dstewart
@version: 1.0
Created on February 22, 2004, 9:14 PM
Copywrite (c) 2004 Zero Wait-State Inc. All rights reserved */

import zws.*;
import zws.application.server.webapp.Names;
import zws.application.Properties;
import zws.data.Metadata;
import zws.report.Report;
import zws.util.comparator.metadata.PartNumberOrder;
import zws.hi.report.MetadataAdapter;
import zws.hi.report.hiReport;
import zws.origin.Origin;

import java.util.*;

public class hiCheckout extends hiReport {

   public Comparator getComparator() {
     PartNumberOrder c = new PartNumberOrder();
     String sortKeyFields=Properties.get(Names.SORT_KEY_FIELDS);
     if (sortKeyFields==null || "".equals(sortKeyFields.trim())) {
      {} //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      {} //System.out.println(Names.SORT_KEY_FIELDS + " not configured!");
      {} //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      sortKeyFields="name";
     }
     c.setKeyFields(sortKeyFields);
     return c; 
   }
  /*
  public String getSelectedReportName() { 
    String r = Properties.get("ilink-checkout"); 
    if (null!=r && r.length()>0) return r;
    r = getIEE().getSelectedRepository();
    return r;
  }
*/
  public boolean idChoosesItem(String id, Object item) {
    String o = ((Metadata)item).getOrigin().toString();
    return id.equals(o);
  }
  
  public boolean isChosen(Object o) {
    Origin origin = ((Metadata)o).getOrigin();
    Iterator i = getChosenItems().iterator();
    //Origin x;
    while (i.hasNext()) if( ((Metadata)i.next()).getOrigin().isTheSameAs(origin)) return true;
    return false;
  }
  
  public String checkout() {
    IntralinkAccess access = IntralinkAccess.getAccess();
    Iterator i = getChosenItems().iterator();
    MetadataAdapter d;
    while (i.hasNext()) {
      d = (MetadataAdapter) i.next();
      try {
	      access.personalCheckout(d.getOrigin(), getWorkspaceName(), getAuthentication());
	    }
	    catch(Exception e) {
	      logFormError("err.checkout", d.getOrigin().toString(), e.getMessage());
	      continue;
	    }
	  }
    if (null!=getChosenItems()) getChosenItems().clear();
    if (null!=getItems()) getItems().clear();
    setParameter("refresh-workspaces", "true");
    return ctrlBACK;
	}

  public String getWorkspaceName() { return (String) getParameter("workspace-name"); }

  public Report getReport() {
    try { sourceReport = getIEE().getReport(); return sourceReport; }
	  catch( Exception e) { e.printStackTrace(); return null; }
	}

 private Report sourceReport = null; 
}
