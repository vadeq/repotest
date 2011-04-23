package zws.hi.intralink.promote;
/*
 * Created on Nov 10, 2003
 * @author jyelizarov
 */
import zws.IntralinkAccess;
import zws.application.Names;
import zws.application.Properties;
import zws.hi.report.MetadataAdapter;
import zws.hi.report.hiReport;
import zws.origin.Origin;
import zws.security.Authentication;

import java.util.Iterator;


public class hiPromote extends hiReport {
  
  public String getSelectedReportName() { return Properties.get(Names.DEFAULT_REPORT_NAME); }
  
  public boolean idChoosesItem(String id, Object item) {
    String o = ((MetadataAdapter)item).getOrigin().toString();
    {} //System.out.println("id="+id+" item="+item);
    return id.equals(o);
  }
  
  public boolean isChosen(Object o) {
    Origin origin = ((MetadataAdapter)o).getOrigin();
    Iterator i = getChosenItems().iterator();
    //Origin x;
    while (i.hasNext()) if( ((MetadataAdapter)i.next()).getOrigin().isTheSameAs(origin)) return true;
    return false;
  }

  public String status(){
    if (op.getThread().isAlive()) return ctrlRUNNING;
    Object[] generatedViewables = op.getGeneratedViewableNames().toArray();
    String criteria="";
    if (generatedViewables.length>0) criteria = Names.CRITERIA_GROUP_START_BLOCK + SPACE + "Name=" + (String)generatedViewables[0] + SPACE + Names.CRITERIA_GROUP_END_BLOCK;
    for (int idx=1; idx < generatedViewables.length; idx++) 
      criteria += SPACE + Names.CRITERIA_OR_OP+ SPACE + Names.CRITERIA_GROUP_START_BLOCK + SPACE + "Name=" + (String)generatedViewables[idx] + SPACE + Names.CRITERIA_GROUP_END_BLOCK;
    String c =getCriteria();
    setCriteria(criteria);
    setFiltersEnabled(false);
    setTransformersEnabled(false);
    setCriteriaModifiersEnabled(false);
    search();
    setFiltersEnabled(true);
    setTransformersEnabled(true);
    setCriteriaModifiersEnabled(true);
    setCriteria(c);
    getChosenItems().clear();
    return ctrlCOMPLETE;
  }
  
  public String modelCheck(){
    op = new ModelCheckOp();
    op.setItems(getChosenItems());
    op.setAuthentication(getAuthentication());
    op.setViewableType(getViewableType());
    op.setDependentValue(getDependentValue());
    //op.setPromotionLevel(getPromotionLevel());
    op.setEnablePromotion(true);
    op.setRecipients(getMember().getUser().getEmail());
    try {
      op.execute();
  	  return ctrlRUNNING;
    }
    catch(Exception e){
      e.printStackTrace();
      return ctrlERROR;
    }
  }

  public String deleteFromRepository(){
    {} //System.out.println("deleteFromRepository");
    if (null==getChosenItems()) return ctrlOK;
    try {
      Iterator i = getChosenItems().iterator();
      MetadataAdapter mData;
      IntralinkAccess access = zws.IntralinkAccess.getAccess();
      while (i.hasNext()) {
        mData=(MetadataAdapter)i.next();
        {} //System.out.println("deleting" + mData);
        access.deleteFromRepository(mData.getOrigin(), getAuthentication());
      }
      {} //System.out.println("done");
      getChosenItems().clear();
      return ctrlOK;
    }
    catch (Exception e) { e.printStackTrace(); return ctrlERROR; }
  }
  
  public String deleteVersionFromRepository(){
    {} //System.out.println("deleteVersionFromRepository");
    if (null==getChosenItems()) return ctrlOK;
    try {
      Iterator i = getChosenItems().iterator();
      MetadataAdapter mData;
      IntralinkAccess access = zws.IntralinkAccess.getAccess();
      while (i.hasNext()) {
        mData=(MetadataAdapter)i.next();
        {} //System.out.println("deleting" + mData);
        access.deleteVersionFromRepository(mData.getOrigin(), getAuthentication());
      }
      {} //System.out.println("done");
      getChosenItems().clear();
      return ctrlOK;
    }
    catch (Exception e) { e.printStackTrace(); return ctrlERROR; }
  }

  public String lock(){
    {} //System.out.println("lock");
    if (null==getChosenItems()) return ctrlOK;
    try {
      Iterator i = getChosenItems().iterator();
      MetadataAdapter mData;
      IntralinkAccess access = zws.IntralinkAccess.getAccess();
      while (i.hasNext()) {
        mData=(MetadataAdapter)i.next();
        {} //System.out.println("locking" + mData);
        access.lock(mData.getOrigin(), getAuthentication());
      }
      {} //System.out.println("done");
      getChosenItems().clear();
      return ctrlOK;
    }
    catch (Exception e) { e.printStackTrace(); return ctrlERROR; }
  }
  
  public String unlock(){
    {} //System.out.println("unlock");
    if (null==getChosenItems()) return ctrlOK;
    try {
      Iterator i = getChosenItems().iterator();
      MetadataAdapter mData;
      IntralinkAccess access = zws.IntralinkAccess.getAccess();
      while (i.hasNext()) {
        mData=(MetadataAdapter)i.next();
        {} //System.out.println("unlocking" + mData);
        access.unlock(mData.getOrigin(), getAuthentication());
      }
      {} //System.out.println("done");
      getChosenItems().clear();
      return ctrlOK;
    }
    catch (Exception e) { e.printStackTrace(); return ctrlERROR; }
  }
  

  public Authentication getAuthentication() {
    if (null==authentication) 
      try { authentication = new Authentication(getMember().getUser().getUsername(), getMember().getUser().getPassword()); }
      catch (java.security.NoSuchAlgorithmException e) { e.printStackTrace(); }
    return authentication;
  }

  public String getViewableType() { return viewableType; }
  public void setViewableType(String s) { viewableType=s; }
  
  private String viewableType;
  private Authentication authentication=null;
  
  public String getDependentValue() {return dependentValue;}
  public void setDependentValue(String s) {dependentValue=s;}
  
  ModelCheckOp op=null;
  private String dependentValue=IntralinkAccess.REQUIRED_DEPENDENCIES;
  private static String PDF = "pdf";
  private static String HPG = "hpg";
  private static String CGM = "cgm";
  private static String ZIP = "zip";
  private static String DRW = "drw";
  private static String NONE = "none";
  private static String SPACE = " ";
}
