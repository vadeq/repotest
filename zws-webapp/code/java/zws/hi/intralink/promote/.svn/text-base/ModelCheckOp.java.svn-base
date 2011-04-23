package zws.hi.intralink.promote;/*
DesignState - Design Compression Technology
@author: dstewart
@version: 1.0
Created on February 23, 2004, 11:52 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.IntralinkAccess;
import zws.data.Metadata;
import zws.hi.report.MetadataAdapter;
import zws.op.ThreadedOpBase;
import zws.origin.IntralinkOrigin;
import zws.origin.Origin;
import zws.report.Report;
import zws.security.Authentication;
import zws.util.FileNameUtil;

import java.util.*;

public class ModelCheckOp extends ThreadedOpBase {
  public void executeRun (){
    //String body,header,footer;
  	Report r = createReport(getItems());
    Iterator k = getItems().iterator();
    MetadataAdapter adapter;
    //Map attributes;
    boolean promotedOK=true;
    //String field;
    //Iterator j;
    IntralinkAccess access = IntralinkAccess.getAccess();
    String log="";
    String promotionLevel = com.zws.application.Properties.get(com.zws.application.Properties.MODEL_CHECK_PROMOTION_LEVEL);
    while (k.hasNext()) {
      log += newline + newline + "********Start Set********" + newline;
      adapter = ((MetadataAdapter)k.next());
      
      if (getEnablePromotion()){
        try {
            promote(adapter, promotionLevel);
            promotedOK=true;
            log +=  "Promoted " + adapter.getName() + " Revision:" + adapter.get("Revision") +" Version:" + adapter.get("Version") + " to " + promotionLevel + newline;
        }
        catch (Exception e1) {
          promotedOK=false;
          log += "Error promoting " + adapter.getName() + " Revision:" + adapter.get("Revision") +" Version:" + adapter.get("Version") + " - " + e1.getMessage() + newline; 
        }
      }
      
      if (promotedOK || !getEnablePromotion()){
        try {             
          if (FileNameUtil.getFileNameExtension(adapter.getName()).equalsIgnoreCase(DRW)) {
            if (getViewableType().equals(PDF)) generatePDF(adapter);
            else if (getViewableType().equals(CGM)) generateCGM(adapter);
            else if (getViewableType().equals(HPG)) generateHPG(adapter);
            else if (getViewableType().equals(NONE));
            log += newline + "Generated Viewable Type " + getViewableType() + " for " + adapter.getName() + " Revision:" + adapter.get("Revision") +" Version:" + adapter.get("Version") + newline;
          }
        }
        catch (Exception e) { e.printStackTrace(); log += "Error generating " + getViewableType() + " for " + adapter.getName() + " Revision:" + adapter.get("Revision") +" Version:" + adapter.get("Version") + " - " + e.getMessage() + newline; }
      }
    }
//    getChosenItems().clear();
    try {  notify(log); }
    catch (Exception e) { e.printStackTrace(); }  
  }

  private String generateName(Origin org){  //generates the promote form name
    IntralinkOrigin o = (IntralinkOrigin) org;
    String nameCore = FileNameUtil.getBaseName(org.getName());
    try{ nameCore = "zws-"+ nameCore + "_" + o.getRevision() + "_" + o.getVersion(); }
    catch(Exception e){ e.printStackTrace(); }
    {} //System.out.println("promo form name : " + nameCore);
    return nameCore;
  }
  
  private Report createReport(Collection adapters){ //this modifies temp report to include all metadata to be processed
    //---Report rpt = new ReportBase();
    Vector results = new Vector();
    Iterator itpI = adapters.iterator();
    while(itpI.hasNext()){
      Metadata md = ((MetadataAdapter)itpI.next()).getMetadata();
      results.add(md);
    }
    //---rpt.setResults(results);
    //---return rpt;
    return null;
  }
  
    public void promote(MetadataAdapter adapter, String promotionLevel) throws Exception {
    IntralinkAccess access = IntralinkAccess.getAccess();
    String promotionRequestFolder = com.zws.application.Properties.get(com.zws.application.Properties.MODEL_CHECK_PROMOTION_FOLDER);
    String dependencies = dependentValue;
    //promotionRequestFolder, 
    access.promote(adapter.getOrigin(), promotionLevel, dependencies, "as-stored", getAuthentication());
  }

  private String generateViewableName(Origin org){  //generates the promote form name
    IntralinkOrigin o = (IntralinkOrigin) org;
    String nameCore = FileNameUtil.getBaseName(org.getName());
    try{ nameCore = "zws-"+ nameCore + "_" + o.getRevision() + "_" + o.getVersion(); }
    catch(Exception e){ e.printStackTrace(); }
    {} //System.out.println("promo form name : " + nameCore);
    return nameCore;
  }

  public void generateViewable(MetadataAdapter adapter, String fileType, String fileExtention) throws Exception {
    IntralinkAccess access = IntralinkAccess.getAccess();
    Origin o = adapter.getOrigin();
    /*
    String viewable = FileNameUtil.convertType(o.getName(), fileExtention);
    Collection identifiers = new Vector();
    identifiers.add(adapter.get("Rev"));
    identifiers.add(adapter.get("Ver"));
    if (fileType.equals(CGM)) identifiers.add("cgm");
    viewable = FileNameUtil.addIdentifiers(viewable,identifiers,"_");
     **/
    access.snapshotImage(o, "pdf", getAuthentication());
    //Metadata image = access.snapshotImage(o, getAuthentication());
    //getGeneratedImages().add(image);
  }

  public void generatePDF(MetadataAdapter adapter) throws Exception { generateViewable(adapter, PDF, PDF); }
  public void generateHPG(MetadataAdapter adapter) throws Exception { generateViewable(adapter, HPG, HPG); }
  public void generateCGM(MetadataAdapter adapter) throws Exception { generateViewable(adapter, CGM, ZIP); }
  
  public void notify(String body) throws Exception {
    /*
    String recipients = com.zws.application.Properties.get(com.zws.application.Properties.MODEL_CHECK_NOTIFICATION_RECIPIENTS);
    recipients += ";" +getRecipients();
    EmailMessage message = new EmailMessage();
    message.setFrom( com.zws.application.Properties.get(com.zws.application.Properties.MODEL_CHECK_NOTIFICATION_REPLY_TO));
    message.setSubject( com.zws.application.Properties.get(com.zws.application.Properties.MODEL_CHECK_NOTIFICATION_SUBJECT));
    StringTokenizer tokens = new StringTokenizer(recipients, ";");
    if (!tokens.hasMoreTokens()) message.addRecipient(recipients.trim());
    else 
      while (tokens.hasMoreTokens()) message.addRecipient(tokens.nextToken().trim());
    Iterator i = getItems().iterator();
    String header = com.zws.application.Properties.get(com.zws.application.Properties.MODEL_CHECK_NOTIFICATION_HEADER);
    String footer = com.zws.application.Properties.get(com.zws.application.Properties.MODEL_CHECK_NOTIFICATION_FOOTER);
    message.setBody(header + newline + body + newline + newline+footer);
    Emailer.send(Properties.get(Names.CENTRAL_SERVER), message);
     */
  }
  
  
  public String getViewableType() { return viewableType; }
  public void setViewableType(String s) { viewableType=s; }
  
  public Collection getItems(){return items;}
  public void setItems(Collection c) {items=c;}
  public Collection getGeneratedImages(){return generatedImages;}
  public void setGeneratedImages(Collection c) {generatedImages=c;}
  
  
  public String getRecipients (){return recipients;}
  public void setRecipients(String s){recipients=s;}
  
  public Authentication getAuthentication(){return authentication;} 
  public void setAuthentication(Authentication a){ authentication=a;}
  
  private Collection items=null;
  private Collection generatedImages=null;
  private Authentication authentication=null;
  private String viewableType=null;
  
  public boolean getEnablePromotion (){return enablePromotion;}
  public void setEnablePromotion(boolean b){enablePromotion=b;}
  public Collection getGeneratedViewableNames() { return generatedViewableNames; }
  
  public String getDependentValue (){return dependentValue;}
  public void setDependentValue(String s){dependentValue=s;}

  private String dependentValue=IntralinkAccess.REQUIRED_DEPENDENCIES;
  private String recipients=null;
  private boolean enablePromotion=true;
  private Collection generatedViewableNames=new Vector();
  private static String PDF = "pdf";
  private static String HPG = "hpg";
  private static String CGM = "cgm";
  private static String ZIP = "zip";
  private static String DRW = "drw";
  private static String NONE = "none";
  private static String newline = "\n";
}
