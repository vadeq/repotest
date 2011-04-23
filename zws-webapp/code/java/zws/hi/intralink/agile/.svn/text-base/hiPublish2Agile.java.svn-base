package zws.hi.intralink.agile;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 16, 2004, 12:03 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Emailer;
import zws.IntralinkAccess;
import zws.application.Properties;
import zws.application.server.webapp.Names;
import zws.data.Metadata;
import zws.hi.report.MetadataAdapter;
import zws.hi.report.hiReport;
import zws.hi.treeview.agile.AgilePublishResult;
import zws.origin.Origin;

import java.io.ByteArrayInputStream;
import java.util.*;

public class hiPublish2Agile extends hiReport {

  public boolean idChoosesItem(String id, Object item) {
    String o = ((MetadataAdapter)item).getOrigin().toString();
    {} //System.out.println("id="+id+" item="+item);
    return id.equals(o);
  }

  protected String getPresentationReportsProperty() {
    return Names.PUBLISH_TO_AGILE_PRESENTATION_REPORTS;
  }
  
  public boolean isChosen(Object o) {
    Origin origin = ((MetadataAdapter)o).getOrigin();
    Iterator i = getChosenItems().iterator();
    Origin x;
    while (i.hasNext()) if( ((MetadataAdapter)i.next()).getOrigin().isTheSameAs(origin)) return true;
    return false;
  }

  public String back(){ return ctrlLIST; }

  public String next(){
    if (null==getChosenItems() || 0==getChosenItems().size()){
      logFormWarning("warn.no.items.selected");
      return ctrlLIST;
    }
    return ctrlNEXT;
  }

  public String search() {
    publishResult=null;
    return super.search();
  }

  public String publishToAgile(){
    try {
      if (null==getChosenItems()) return null;
      Collection origins = new Vector();
      Iterator i = getChosenItems().iterator();
      while (i.hasNext()) origins.add(((Metadata)i.next()).getOrigin());
      publishResult = IntralinkAccess.getAccess().publishToAgile(origins, getAuthentication());
      //sendNotification("yo");
      return ctrlLIST;
    }
    catch (Exception e) { e.printStackTrace(); return ctrlERROR; }
  }

  public String generateIDF(){
    try {
      if (null==getChosenItems()) return null;
      Collection origins = new Vector();
      Iterator i = getChosenItems().iterator();
      Origin o;
      while (i.hasNext()) {
        o = ((Metadata)i.next()).getOrigin();
        if (o.getName().startsWith("28-") || o.getName().startsWith("73-") ) origins.add(o);
        else this.logFormWarning("warning.invalid.for.idf", o.getName());
      }
      if (origins.size()>0) publishResult = IntralinkAccess.getAccess().publishIDFToAgile(origins, getAuthentication());
      else publishResult=null;
      //sendNotification("yo");
      return ctrlLIST;
    }
    catch (Exception e) { e.printStackTrace(); return ctrlERROR; }
  }

  private void sendNotification(String msg) throws Exception {
    String from = Properties.get(Names.PUBLISH_TO_AGILE_NOTIFICATION_REPLY_TO);
    String to = Properties.get(Names.PUBLISH_TO_AGILE_NOTIFICATION_RECIPIENTS);
    String subject = Properties.get(Names.PUBLISH_TO_AGILE_NOTIFICATION_SUBJECT);
    String body = Properties.get(Names.PUBLISH_TO_AGILE_NOTIFICATION_HEADER);
    body += msg;
    body += Properties.get(Names.PUBLISH_TO_AGILE_NOTIFICATION_FOOTER);
    Emailer.send(from, to, subject, body);
  }

  public String getPublishResult() {
    if (null==publishResult) return null;
      String s;
      try{
				AgilePublishResult pubView = new AgilePublishResult();
				return pubView.getTreeView(new ByteArrayInputStream(publishResult.getBytes()));	
      }
      catch (Throwable t) { t.printStackTrace();  return null; }
 }

  private String publishResult=null;
}

