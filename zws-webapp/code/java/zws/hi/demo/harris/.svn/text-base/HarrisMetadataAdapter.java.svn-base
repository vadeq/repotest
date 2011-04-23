package zws.hi.demo.harris;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 26, 2005, 12:58 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Synchronizer;
import zws.application.Properties;
import zws.application.server.webapp.Names;
import zws.data.Metadata;
import zws.hi.report.MetadataAdapter;
import zws.origin.*;
import zws.origin.Origin;
import zws.util.FileNameUtil;

import java.util.Collection;
import java.util.Iterator;
import zws.synchronization.SynchronizationRecord;

public class HarrisMetadataAdapter extends MetadataAdapter {
  public void adapt(Metadata data) {
    super.adapt(data);
    refresh();
  }
  private String getCADDocumentName(Metadata m) { return getCADDocumentName(m.get("number")); }
  private String getCADDocumentName(String name) { return name+"-doc"; }
  
  public void refresh() {
    clearSynchronization();
    findSynchronizations();
    checkIfRenamed();      
    checkIfRenumbered();      
  }
  
  public void checkIfRenamed() {
    isRenamed=false;
    try {
	    if (getCADDocumentOrigin()!=null) return;
	    if (getCADModelOrigin()!=null) return;
	    Origin origin = getOrigin();
	    Origin harrisOrigin = materializeHarrisOrigin();
	    if (null==harrisOrigin) return;
	    Collection c = sync.findMatches(harrisOrigin.getDomainName(), harrisOrigin.getServerName(), harrisOrigin.getDatasourceName(), harrisOrigin.getName());
	    if (null==c || c.size()==0) return;
	    Iterator i = c.iterator();
	    while(i.hasNext()) {
	      origin = (Origin)i.next();
	      if (!origin.getDatasourceType().equals(Origin.FROM_ILINK)) continue;
	      if (!origin.getName().equals(getName())) isRenamed=true;
	    }
	    return;
    }
    catch(Exception e) { e.printStackTrace(); }
  }
  
  public void checkIfRenumbered() {
    isRenumbered=false;
    try {
	    if (getCADDocumentOrigin()!=null) return;
	    if (getCADModelOrigin()!=null) return;
	    Origin o = getOrigin();
	    Collection c = sync.findMatches(o.getDomainName(), o.getServerName(), o.getDatasourceName(), o.getName());
	    if (null==c || c.size()==0) return;
	    Iterator i = c.iterator();
	    while(i.hasNext()) {
	      o = (Origin)i.next();
	      if (!o.getDatasourceType().equals(Origin.FROM_HARRIS_ILINK)) continue;
	      if (!o.getName().equals(getPartNumber())) {
	        isRenumbered=true;
	        oldPartNumber = o.getName();  
	      }
	    }
	    return;
    }
    catch(Exception e) { e.printStackTrace(); }
  }
  public void checkIfRenamed2() {
    String nameNow = getName();
    String numberNow = getPartNumber();
    String oldName = null;
    String oldNumber = null;
    SynchronizationRecord r;
    Origin a;
    if (null!=harrisOrigin && !harrisOrigin.getName().equals(numberNow)) oldNumber=harrisOrigin.getName();
    Collection c = findSynchronizationRecords(getName());
    if (null!=c) {
      Iterator i = c.iterator();
      while (i.hasNext()) {
        r = (SynchronizationRecord)i.next();
        a = r.getOrigin0();
        if (a.getDatasourceType().equals(Origin.FROM_HARRIS_ILINK) && !getPartNumber().equals(a.getName())) oldNumber = a.getName();
        a = r.getOriginA();
        if (a.getDatasourceType().equals(Origin.FROM_HARRIS_ILINK) && !getPartNumber().equals(a.getName())) oldNumber = a.getName();
      }
    }
    c = findSynchronizationRecords(getPartNumber());
    if (null!=c) {
      Iterator i = c.iterator();
      while (i.hasNext()) {
        r = (SynchronizationRecord)i.next();
        a = r.getOrigin0();
        if (a.getDatasourceType().equals(Origin.FROM_ILINK) && !getName().equals(a.getName())) oldName= a.getName();
        a = r.getOriginA();
        if (a.getDatasourceType().equals(Origin.FROM_ILINK) && !getName().equals(a.getName())) oldName= a.getName();
      }
    }
    if (oldName!=null) isRenamed = true;
    if (oldNumber!=null) isRenumbered=true;
    if (isRenamed) {}{} //System.out.println(oldName +" has been renamed to " + getName());
    if (isRenumbered) {}{} //System.out.println(oldNumber+" has been renumbered to " + getPartNumber());
  }

  public Collection findSynchronizationRecords(String name) {
    try {
      return sync.findAllSynchronizationRecords(name);
    }
    catch(Exception e) { e.printStackTrace(); }
    return null;
  }
  
  public void findSynchronizations() {
    ilinkOrigin = getOrigin();
    try {
      Collection c = sync.findAllSynchronizationRecords(getOrigin());
      if (c!=null && c.size()>0) {
        Iterator i = c.iterator();
        Origin a;
        {} //System.out.println("-------------------------------------------");
        {} //System.out.println("----[" +getOrigin().getDatasourceType() + "]" + getOrigin().getName());
        while (i.hasNext()) {
          a = (Origin)i.next();
          if (a.getDatasourceType().startsWith(Origin.FROM_AGILE)) { 
            if (a.getName().endsWith("-doc")) documentOrigin=a;
            else modelOrigin=a;
          }
          else if (a.getDatasourceType().equals(Origin.FROM_ILINK)) ilinkOrigin=a;
          else if (a.getDatasourceType().equals(Origin.FROM_HARRIS_ILINK)) harrisOrigin=a;
          else if ("pdf".equalsIgnoreCase(FileNameUtil.getFileNameExtension(a.getName()))) pdfOrigin = a;
          {} //System.out.println("----[" + a.getDatasourceType() + "]" + a.getName());
        }
        {} //System.out.println("-------------------------------------------");
        if (null!=pdfOrigin) {}{} //System.out.println(pdfOrigin);
      }
    }
    catch(Exception e) { e.printStackTrace(); }
    
    //hack - pick up model origin since agile is not yet versioned!
    if (harrisOrigin==null) return;
    try {
			Collection c = sync.findAllSynchronizationRecords(harrisOrigin);
			if (c!=null && c.size()>0) {
			  Iterator i = c.iterator();
			  Origin a;
			  while (i.hasNext()) {
			    a = (Origin)i.next();
			    if (a.getDatasourceType().startsWith(Origin.FROM_AGILE)) { 
			      if (!a.getName().endsWith("-doc")) modelOrigin=a;
	        }
	      }
	    }
    }
    catch(Exception e) { e.printStackTrace(); }
  }

  public String getPartNumber() { return get("Part_Number"); }

  public String getIntralinkName() { 
    if (null==ilinkOrigin) return ""; 
    return ilinkOrigin.getName();
  }

  public String getHarrisName() { 
    if (null==harrisOrigin) return ""; 
    return harrisOrigin.getName();
  }

  public String getCADDocumentName() { 
    if (null==documentOrigin) return ""; 
    return documentOrigin.getName();
  }

  public String getCADModelName() { 
    if (null==modelOrigin) return ""; 
    return modelOrigin.getName();
  }

  public String getHPGLName() { if (null==hpglOrigin) return ""; return hpglOrigin.getName(); }
  public String getIGSName() { if (null==igsOrigin) return ""; return igsOrigin.getName(); }
  public String getCGMName() { if (null==cgmOrigin) return ""; return cgmOrigin.getName(); }
  public String getDXFName() { if (null==dxfOrigin) return ""; return dxfOrigin.getName(); }
  public String getNeutralName() { if (null==neutralOrigin) return ""; return neutralOrigin.getName(); }
  public String getStepName() { if (null==stepOrigin) return ""; return stepOrigin.getName(); }
  public String getSTLName() { if (null==stlOrigin) return ""; return stlOrigin.getName(); }
  public Origin getPDFOrigin() { return pdfOrigin; }
  public void setPDFOrigin(Origin o) { pdfOrigin=o; }
  
  public Origin getIntralinkOrigin() { return ilinkOrigin; }
  public void setIntralinkOrigin(Origin o) { ilinkOrigin=o; }
  public Origin getHarrisOrigin() { return harrisOrigin; }
  public void setHarrisOrigin(Origin o) { harrisOrigin=o; }
  public Origin getCADModelOrigin() { return modelOrigin; }
  public void setCADModelOrigin(Origin o) { modelOrigin=o; }
  public Origin getCADDocumentOrigin() { return documentOrigin; }
  public void setCADDocumentOrigin(Origin o) { documentOrigin=o; }
  public Origin getHPGLOrigin() { return hpglOrigin; }
  public void  setHPGLOrigin(Origin o) { hpglOrigin=o; }
  public Origin getIGSOrigin() { return igsOrigin; }
  public void  setIGSOrigin(Origin o) { igsOrigin=o; }
  public Origin getCGMOrigin() { return cgmOrigin; }
  public void setCGMOrigin(Origin o) { cgmOrigin=o; }
  public Origin getDXFOrigin() { return dxfOrigin; }
  public void setDXFOrigin(Origin o) { dxfOrigin=o; }
  public Origin getNeutralOrigin() { return neutralOrigin; }
  public void setNeutralOrigin(Origin o) { neutralOrigin=o; }
  public Origin getStepOrigin() { return stepOrigin; }
  public void setStepOrigin(Origin o) { stepOrigin=o; }
  public Origin getSTLOrigin() { return stlOrigin; }

  public void setSTLOrigin(Origin o) { stlOrigin=o; }

  public boolean getIsRenamed() { return isRenamed; }
  public boolean getIsRenumbered() { return isRenumbered; }
  
  public String getLatestBillOfMaterialsReport() { return ""; }
  public String getBillOfMaterialsReport() { return ""; }
  public String getFamilyTableReport() { return ""; }
  public String getHistoryReport() { return ""; }
  public String getDependencyReport() { return ""; }
  public String getWhereUsedReport() { return ""; }

  private void clearSynchronization() {
    ilinkOrigin=null;
    harrisOrigin=null;
    modelOrigin=null;
    documentOrigin=null;
    pdfOrigin=null;  
    hpglOrigin=null;
    igsOrigin=null;
    cgmOrigin=null;
    dxfOrigin=null;
    neutralOrigin=null;
    stepOrigin=null;
    stlOrigin=null;
    isRenamed = false;
    isRenumbered = false;  
  }

  public Origin materializeHarrisOrigin() {
    if (null==getPartNumber() || "".equals(getPartNumber())) return null;
    HarrisOrigin h = new HarrisOrigin((IntralinkOrigin)getOrigin(), getPartNumber());
    return h;
  }
  
  public String getOldPartNumber() { return oldPartNumber; }
  
  private Origin ilinkOrigin=null;
  private Origin harrisOrigin=null;
  private Origin modelOrigin=null;
  private Origin documentOrigin=null;
  private Origin pdfOrigin=null;  
  private Origin hpglOrigin=null;
  private Origin igsOrigin=null;
  private Origin cgmOrigin=null;
  private Origin dxfOrigin=null;
  private Origin neutralOrigin=null;
  private Origin stepOrigin=null;
  private Origin stlOrigin=null;
  private boolean isRenamed = false;
  private boolean isRenumbered = false;
  private String oldPartNumber=null;
  
  private static Synchronizer sync = Synchronizer.getClient(Properties.get(Names.CENTRAL_SERVER));
}
