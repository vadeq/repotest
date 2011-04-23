package zws.hi.demo.cisco.ec;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 26, 2005, 12:58 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Synchronizer;
import zws.application.Properties;
import zws.application.server.webapp.Names;
import zws.data.Metadata;
import zws.exception.InvalidName;
import zws.hi.report.MetadataAdapter;
import zws.origin.*;
import zws.origin.Origin;
import zws.util.FileNameUtil;

import java.util.Collection;
import java.util.Iterator;
import zws.synchronization.SynchronizationRecord;
import zws.util.Pair ;

public class CiscoMetadataAdapter extends MetadataAdapter {
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
    checkIfPreviouslyPublished();
  }
  
  public void checkIfRenamed() {
    isRenamed=false;
    try {
	    if (getCADDocumentOrigin()!=null) return;
	    if (getCADModelOrigin()!=null) return;
	    Origin origin = getOrigin();
	    Origin partNumberOrigin = materializeIlinkPartNumberOrigin();
	    if (null==partNumberOrigin) return;
	    Collection c = sync.findMatches(partNumberOrigin.getDomainName(), partNumberOrigin.getServerName(), partNumberOrigin.getDatasourceName(), partNumberOrigin.getName());
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
  
  public void checkIfRenumbered() { //?? may no longer work.
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
	      if (!o.getDatasourceType().equals(Origin.FROM_ILINK_PART_NUMBER)) continue;
	      if (!o.getName().equals(getPartNumberAttribute())) {
	        isRenumbered=true;
	        oldPartNumber = o.getName();  
	      }
	    }
	    return;
    }
    catch(Exception e) { e.printStackTrace(); }
  }
  
  /*
  public void checkIfRenamed2() {
    String nameNow = getName();
    String numberNow = getPartNumber();
    String oldName = null;
    String oldNumber = null;
    SynchronizationRecord r;
    Origin a;
    if (null!=partNumberOrigin && !partNumberOrigin.getName().equals(numberNow)) oldNumber=partNumberOrigin.getName();
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
    if (isRenamed) {} //System.out.println(oldName +" has been renamed to " + getName());
    if (isRenumbered) {} //System.out.println(oldNumber+" has been renumbered to " + getPartNumber());
  }

  public Collection findSynchronizationRecords(String name) {
    try {
      return sync.findAllSynchronizationRecords(name);
    }
    catch(Exception e) { e.printStackTrace(); }
    return null;
  }
  */
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
          else if (a.getDatasourceType().equals(Origin.FROM_ILINK_PART_NUMBER)) partNumberOrigin=a;
          else if ("pdf".equalsIgnoreCase(FileNameUtil.getFileNameExtension(a.getName()))) pdfOrigin = a;
          {} //System.out.println("----[" + a.getDatasourceType() + "]" + a.getName());
        }
        {} //System.out.println("-------------------------------------------");
        if (null!=pdfOrigin) {}{} //System.out.println(pdfOrigin);
      }
    }
    catch(Exception e) { e.printStackTrace(); }
    
    //hack - pick up model origin since agile is not yet versioned!
    if (partNumberOrigin==null) return;
    try {
			Collection c = sync.findAllSynchronizationRecords(partNumberOrigin);
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

  public void checkIfPreviouslyPublished() {
    isPublished = false;
    wasPreviouslyPublished = false;
    if (null!=modelOrigin) {
	    isPublished = true;
	    wasPreviouslyPublished = true;
	    previouslyPublishedOrigin = getOrigin();
	    loadBasePartNumber(modelOrigin.getName());
		  {} //System.out.println(getName() + "xxx " + basePartNumber);
		  return;
	  }
    if (null!=ecoOrigin) {
      isPublished = true;
	    wasPreviouslyPublished = true;  
	    previouslyPublishedOrigin = getOrigin();
	    loadBasePartNumber(ecoOrigin.getName());
		  {} //System.out.println(getName() + "xxx " + basePartNumber);
		  return;
	  }
	  try {
      Origin o = getOrigin();
      Origin lastModelPublish=null;
	    Origin lastPublish = sync.lastSynchronization(o.getDomainName(), o.getServerName(), o.getDatasourceName(), o.getName());
	    if (null==lastPublish) {
		    {} //System.out.println(getName() + "--- ");
		    return;
		  }
      Collection c = sync.findAllSynchronizationRecords(lastPublish);
      if (c!=null && c.size()>0) {
        Iterator i = c.iterator();
        Origin a;
        while (i.hasNext()) {
          a = (Origin)i.next();
          if (a.getDatasourceType().startsWith(Origin.FROM_AGILE)) { 
            if (!a.getName().endsWith("-doc")) lastModelPublish=a;
          }
        }
      }
	    if (null==lastModelPublish) {
	      {} //System.out.println(getName() + "--- ");
	      return;
	    }
	    wasPreviouslyPublished=true;
	    previouslyPublishedOrigin = lastPublish;
	    loadBasePartNumber(lastModelPublish.getName());
	    {} //System.out.println(getName() + "+++ " + basePartNumber);
	  }
	  catch(Exception e) { e.printStackTrace(); }
	}

  public String getPartNumberAttribute() { return get("Part_Number"); }

  public String getIntralinkName() { 
    if (null==ilinkOrigin) return ""; 
    return ilinkOrigin.getName();
  }

  public String getPartNumberName() { 
    if (null==partNumberOrigin) return ""; 
    return partNumberOrigin.getName();
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
  public Origin getHarrisOrigin() { return partNumberOrigin; }
  public void setHarrisOrigin(Origin o) { partNumberOrigin=o; }
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
  
  public Origin getPreviouslyPublishedOrigin() { return previouslyPublishedOrigin; }
  public void setPreviouslyPublishedOrigin(Origin o) { previouslyPublishedOrigin=o; }

  public boolean getIsRenamed() { return isRenamed; }
  public boolean getIsRenumbered() { return isRenumbered; }
  public boolean getIsPublished() { return isPublished; }
  public boolean getWasPreviouslyPublished() { return wasPreviouslyPublished; }
  
  public String getLatestBillOfMaterialsReport() { return ""; }
  public String getBillOfMaterialsReport() { return ""; }
  public String getFamilyTableReport() { return ""; }
  public String getHistoryReport() { return ""; }
  public String getDependencyReport() { return ""; }
  public String getWhereUsedReport() { return ""; }

  private void clearSynchronization() {
    ilinkOrigin=null;
    partNumberOrigin=null;
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
    wasPreviouslyPublished = false;  
  }

  public Origin materializeIlinkPartNumberOrigin() {
    if (null==getPartNumberAttribute() || "".equals(getPartNumberAttribute())) return null;
    IlinkPartNumberOrigin h = new IlinkPartNumberOrigin((IntralinkOrigin)getOrigin(), getPartNumberAttribute());
    return h;
  }

  public Pair incrementBasePartNumber() throws Exception {
	  if (null==basePartNumber) throw new InvalidName(null);
	  if (basePartNumber.length()!=11) throw new InvalidName(basePartNumber); //1234567-000.prt 1234567-000.asm
	  if ('-'!=basePartNumber.charAt(7)) throw new InvalidName(basePartNumber);
	  Pair pair = new Pair();
	  pair.setObject0(basePartNumber);
	  String base = basePartNumber.substring(0,7);
	  String suffix= basePartNumber.substring(8);
	  try { 
	    int s = Integer.parseInt(suffix);
	    s++;
	    String ss = "000" + s;
	    ss = ss.substring(ss.length()-3);
	    String number= base + "-" + ss;
	    {} //System.out.println(number + " Base Part Number incremented");
	    loadBasePartNumber(number);
		  pair.setObject1(basePartNumber);
	    return pair;
	  }
	  catch (NumberFormatException e) { return null; }
  }
  
  private void loadBasePartNumber(String number) {
   basePartNumber=number;
   getMetadataBase().set(ATT_AGILE_NUMBER, basePartNumber);
   getMetadataBase().set(ATT_ILINK_PART_NUMBER, basePartNumber);      
  }
  
  public String getBasePartNumber() { return basePartNumber; }
  public String getOldPartNumber() { return oldPartNumber; }
  
  private Origin ilinkOrigin=null;
  private Origin partNumberOrigin=null;
  private Origin modelOrigin=null;
  private Origin ecoOrigin=null;
  private Origin documentOrigin=null;
  private Origin previouslyPublishedOrigin=null;
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
  private boolean wasPreviouslyPublished= false;
  private boolean isPublished= false;
  private String basePartNumber=null;
  private String oldPartNumber=null;
  
  private static Synchronizer sync = Synchronizer.getClient(Properties.get(Names.CENTRAL_SERVER));
  private static String ATT_ILINK_PART_NUMBER="Part_Number";
  private static String ATT_AGILE_NUMBER = "number";
  private static String ATT_AGILE_CLASS_TYPE = "agile-class";
  private static String ATT_AGILE_FILE_TYPE = "CAD-File-Type";
  private static String CAD_PART = "CAD Part";
  private static String CAD_DOCUMENT = "CAD Document";
}
