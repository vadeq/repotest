package zws.hi.intralink.publish.agile;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 20, 2004, 2:10 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.hi.report.MetadataAdapter;

import java.util.*;

public class AgilePOCMetadataAdapter extends MetadataAdapter{

  public AgilePOCMetadataAdapter () { init(); }

  public void init() {
    if (null!=conversionFormats) return;
    releaseLevels = new ArrayList();
    releaseLevels.add("In Progress");
    releaseLevels.add("In Approval");
    releaseLevels.add("Released");
    releaseLevels.add("Obsolete");
    conversionFormats = new HashMap();
    Collection c;
    c = new Vector();
    c.add("--");
    c.add("PDF");
    conversionFormats.put("doc", c);
    c = new Vector();
    c.add("--");
    c.add("PDF");
    conversionFormats.put("xls", c);
    c = new Vector();
    c.add("--");
    c.add("PDF");
    conversionFormats.put("ppt", c);
    c = new Vector();
    c.add("--");
    c.add("PDF");
    c.add("CGM");
    c.add("HPGL");
    conversionFormats.put("drw", c);
    c = new Vector();
    c.add("--");
    c.add("PDF");
    c.add("DWF");
    c.add("DXF");
    c.add("IGES");
    c.add("HPGL");
    conversionFormats.put("dwg", c);
  }

  public boolean getHasPromotionLevels() { 
    Collection c=getPromotionLevels();
    if (null==c) return false;
    if (0==c.size()) return false;
    return true;
  }
  public Collection getPromotionLevels(){
   int i = releaseLevels.indexOf(get("Release")); 
   if (i==-1) return null;
   if (i==releaseLevels.size()-1) return null;
   Collection c = new Vector();
   for (int j=i+1; j<releaseLevels.size(); c.add(releaseLevels.get(j++)));
   return c;
  }

  public boolean getHasViewableOptions() {
    String type = getName().substring(getName().lastIndexOf('.')+1).toLowerCase();
    Collection c = (Collection)conversionFormats.get(type);
    if (null==c) return false;
    return true;
  }
  
  public Collection getViewableOptions() {
    String type = getName().substring(getName().lastIndexOf('.')+1).toLowerCase();
    Collection c = (Collection)conversionFormats.get(type);
    if (null==c) {
      c = new Vector();
      c.add("----");
    }
    return c;
  }

  public String getPromoteTo() { return promoteTo; }
  public void setPromoteTo(String s) { promoteTo=s; }
  public String getViewableType() { return viewableType; }
  public void setViewableType(String s) { viewableType=s; }

  public boolean getBillIsAvailable() { if (getName().endsWith(".asm")) return true; return false; }
  public boolean getIncludeBill() { return includeBill; }
  public void setIncludeBill(boolean b) { includeBill=b; }
  public boolean getIncludeLinks() { return includeLinks; }
  public void setIncludeLinks(boolean b) { includeLinks=b; }

  private String promoteTo=null;
  private String viewableType=null;
  
  //private Collection promotionLevels=null;
  private boolean includeBill=true;
  private boolean includeLinks=false;

  private static Map conversionFormats=null;
  private static List releaseLevels=null;
}
