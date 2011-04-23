package zws.hi.intralink.proconfly;/*
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
import zws.origin.Origin;
import zws.util.FileNameUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import zws.util.MapUtil;

public class PDFMetadataAdapter extends MetadataAdapter {
  public void adapt(Metadata data) {
    super.adapt(data);
    findSynchronizations();
  }
  
  public void readapt(Metadata data) {
    super.adapt(data);
    findSynchronizations();
  }

  public String getProEType() { 
    String fileType = lookupType(getName());
    if ("asm".equalsIgnoreCase(fileType)) return "ProE Model";
    if ("prt".equalsIgnoreCase(fileType)) return "ProE Model";
    if ("drw".equalsIgnoreCase(fileType)) return "ProE Drawing";
    return fileType;
  }
  public String getFileType() { return lookupType(getName()); }
  
  private String lookupType(Origin o) { return lookupType(o.getName()); }
  private String lookupType(String name) { return FileNameUtil.getFileNameExtension(name); }
  
  public void findSynchronizations() {
    if (isSynchronized) return;
    addBinaryOrigin(getOrigin());
    Synchronizer s = Synchronizer.getClient(Properties.get(Names.CENTRAL_SERVER));
    try {
      Collection c = s.findAllSynchronizationRecords(getOrigin());
      if (c==null) return;
      Iterator i = c.iterator();
      Origin a;
      while (i.hasNext()) {
        a = (Origin)i.next();
        addBinaryOrigin(a);
      }
    }
    catch(Exception e) { e.printStackTrace(); }
    isSynchronized = true;
  }
  
  public String getPrinterName() { return printerName; }
  public void setPrinterName(String s) { printerName=s; }

  public String getPrintQuantity() { return ""+printQuantity; }
  public void setPrintQuantity(String i) {  
    if (null==i || i.trim().equals("")) printQuantity=0;
    else printQuantity=Integer.valueOf(i).intValue(); 
  }

  public String getPromotedFrom() { return promotedFrom; }
  public void setPromotedFrom(String s) { promotedFrom=s; }
  public String getPromoteTo() { return promoteTo; }
  public void setPromoteTo(String s) { promoteTo=s; }
  public String getVerification() { return verification; }
  public void setVerification(String s) { verification=s; }

  public String getImageType() { return imageType; }
  public void setImageType(String s) { imageType=s; }
 
  public String getBinaryName(String type) { 
	  Origin origin = getBinaryOrigin(type);
	  if (null==origin) return ""; 
	  return origin.getName();
	}

  public String getPDFName() { return getBinaryName("pdf");}

  public String getPSName() { return getBinaryName("ps");}
  
  public Collection getPDFOrigins() { 
    Collection c = getBinaryOrigins(PDF);
    if (c.isEmpty()) return null;
    return c;
  }
  public Origin getAgileOrigin() { return getBinaryOrigin(AGILE); }

  public Origin getPDFOrigin() { return getBinaryOrigin(PDF); }
  public Origin getPSOrigin() { return getBinaryOrigin(PS); }
  public Origin getHPGOrigin() { return getBinaryOrigin(HPG); }
  public Origin getIGSOrigin() { return getBinaryOrigin(IGS); }
  public Origin getIDFOrigin() { return getBinaryOrigin(IDF); }
  public Origin getCGMOrigin() { return getBinaryOrigin(CGM); }
  public Origin getDXFOrigin() { return getBinaryOrigin(DXF); }
  public Origin getDWGOrigin() { return getBinaryOrigin(DWG); }
  public Origin getNEUOrigin() { return getBinaryOrigin(NEU); }
  public Origin getSTPOrigin() { return getBinaryOrigin(STP); }
  public Origin getSTLOrigin() { return getBinaryOrigin(STL); }

  public Collection getImageOrigins() {
    Collection c = new Vector();
    if (null!=getPDFOrigin() && !getOrigin().isExactlyTheSame(getPDFOrigin())) c.add(getPDFOrigin());
    if (null!=getPSOrigin() && !getOrigin().isExactlyTheSame(getPSOrigin())) c.add(getPSOrigin());
    if (null!=getHPGOrigin()&& !getOrigin().isExactlyTheSame(getHPGOrigin())) c.add(getHPGOrigin());
    if (null!=getIGSOrigin()&& !getOrigin().isExactlyTheSame(getIGSOrigin())) c.add(getIGSOrigin());
    if (null!=getIDFOrigin()&& !getOrigin().isExactlyTheSame(getIDFOrigin())) c.add(getIDFOrigin());
    if (null!=getCGMOrigin()&& !getOrigin().isExactlyTheSame(getCGMOrigin())) c.add(getCGMOrigin());
    if (null!=getDXFOrigin()&& !getOrigin().isExactlyTheSame(getDXFOrigin())) c.add(getDXFOrigin());
    if (null!=getDWGOrigin()&& !getOrigin().isExactlyTheSame(getDWGOrigin())) c.add(getDWGOrigin());
    if (null!=getNEUOrigin()&& !getOrigin().isExactlyTheSame(getNEUOrigin())) c.add(getNEUOrigin());
    if (null!=getSTPOrigin()&& !getOrigin().isExactlyTheSame(getSTPOrigin())) c.add(getSTPOrigin());
    if (null!=getSTLOrigin()&& !getOrigin().isExactlyTheSame(getSTLOrigin())) c.add(getSTLOrigin());
    if (0==c.size()) return null;
    return c;
  }
  
  public boolean hasBinaryOrigin(String type) {
    List c = getBinaryOrigins(type);
    return !c.isEmpty();  
  }
  
  
  public boolean hasMultiplePDFOrigins() { return hasMultipleBinaryOrigins(PDF); }
  public boolean hasMultipleBinaryOrigins(String type) {
    List c = getBinaryOrigins(type);
    return (c.size()>1); 
  }
  public Origin getBinaryOrigin(String type) {
    List c = getBinaryOrigins(type);
    if (c.isEmpty()) return null;
    return (Origin) c.get(0);
  }
  public List getBinaryOrigins(String type) {
    return MapUtil.getListFromMap(binaryOrigins, type.toLowerCase());      
  }
  public Collection getAllBinaryOrigins() {
    return binaryOriginsIndex.values();
	}
  public void addBinaryOrigin(Origin o) {
    if (binaryOriginsIndex.containsKey(o.toString())) return; //already been added
    binaryOriginsIndex.put(o.toString(), o); //index each item (prevents duplicates when resynching)
    String type = FileNameUtil.getFileNameExtension(o.getName());
    if (type.equalsIgnoreCase("zip") && o.getName().length() > 5)
      type = FileNameUtil.getFileNameExtension(o.getName().substring(0, o.getName().length()-4));
    Collection c = MapUtil.getListFromMap(binaryOrigins, type.toLowerCase());
    c.add(o);
  }

  public void resetSynchronizations() {
	  binaryOriginsIndex.clear();
	  binaryOrigins.clear();
    isSynchronized=false;
	}

  public String getLatestBillOfMaterialsReport() { return ""; }
  public String getBillOfMaterialsReport() { return ""; }
  public String getFamilyTableReport() { return ""; }
  public String getHistoryReport() { return ""; }
  public String getDependencyReport() { return ""; }
  public String getWhereUsedReport() { return ""; }

  
  public Collection getPromotionLevels() {
    String currentRelease = this.get("Release-Level"); //hack
    Collection c = new Vector();
    c.add("--------");
    Iterator i = getReleaseLevels().iterator();
    boolean isAnEarlierRelease=true;
    String release;
    while(isAnEarlierRelease && i.hasNext()) {
      release = i.next().toString();
      if (currentRelease.equals(release)) isAnEarlierRelease=false;
    }
    while(i.hasNext()) c.add(i.next());
    return c;
	}

  public Collection getVerificationOptions() {
    Collection c = new Vector();
    c.add("--------");
    c.add("Model Check");
    return c;
	}
  
  public Collection getReleaseLevels() {
	  if (null==releaseLevels) {
 	    releaseLevels= new Vector();
      String s = Properties.get("release-levels");
      StringTokenizer tokens = new StringTokenizer(s, Names.DELIMITER);
      while (tokens.hasMoreTokens()) releaseLevels.add(tokens.nextToken().trim());
	  }
	  return releaseLevels; 
	}
  
  public Collection getImageTypes() {
    Collection imageTypes = new Vector();
    imageTypes.add("--------");
	  String type =FileNameUtil.getFileNameExtension(getName());
    if ("dwg".equalsIgnoreCase(type)) {
      imageTypes.add("PDF");
    }
    else if ("drw".equalsIgnoreCase(type)) {
      imageTypes.add("PDF");
      imageTypes.add("CGM");
  	  imageTypes.add("HPGL");
//      if (pdfOrigin==null) imageTypes.add("PDF");
//      if (cgmOrigin==null) imageTypes.add("CGM");
//	    if (hpglOrigin==null) imageTypes.add("HPGL");
	  }
    else if ("asm".equalsIgnoreCase(type) || "prt".equalsIgnoreCase(type)) {
      //if (igesOrigin==null) imageTypes.add("IGES");
      //if (idfOrigin==null) imageTypes.add("IDF");
    }
	  return imageTypes; 
	}

  public String getVerificationStatus() { return verificationStatus; }
  public void setVerificationStatus(String s) { verificationStatus=s; }

  private static int getNewCount() { return count++; };
  private static int count=0;

  private int printQuantity=1;
  private String printerName="";

  private String verification="";
  private String verificationStatus="N/A";

  private Collection releaseLevels=null;
  private String promoteTo="";
  private String promotedFrom="";

  private String imageType="";

  private Map binaryOrigins = new HashMap();
  private Map binaryOriginsIndex = new HashMap();
  private boolean isSynchronized=false;
  
  //Drawing Formats
  private static String PDF ="pdf";
  private static String PS ="ps";
  private static String DXF ="dxf";
  private static String DWG ="dwg";
  private static String HPG ="hpg";
  private static String CGM ="cgm";

  //3D Model Formats
  private static String IGS ="igs";
  private static String STP ="stp";
  private static String NEU ="neu";
  private static String IDF ="idf";  //Electronic

  //Other formats?
  private static String STL ="stl";

  //Agile Formats
  private static String AGILE ="";
}
