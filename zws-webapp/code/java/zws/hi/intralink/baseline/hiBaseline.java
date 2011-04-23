package zws.hi.intralink.baseline;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 6, 2004, 9:24 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Properties;
import zws.application.server.webapp.Names;
import zws.hi.report.MetadataAdapter;
import zws.hi.report.hiReport;
import zws.hi.treeview.fig.ConfigurationView;
import zws.hi.treeview.folder.FolderView;
import zws.origin.IntralinkOrigin;
import zws.origin.OriginMaker;
import zws.util.AdapterPrototype;

import com.zws.domo.baseline.Baseline;
import com.zws.domo.baseline.Fileentry;

import java.io.ByteArrayInputStream;
import java.util.*;

public class hiBaseline extends hiReport implements AdapterPrototype, Cloneable {
  
  public void bind() {
    if ("add".equalsIgnoreCase(getNav()) || "add".equalsIgnoreCase(getEvent())) 
        baseline = new Baseline();
  }

  public void render() { if (null==baseline) bind(); }
  public void adapt(Object baseln) { baseline=(Baseline)baseln; }

  public void registerRequiredFields() {
    require("next", "name");
    require("next", "location");
  }

  public String next() {
    try {
      com.zws.service.baseline.BaselineService.getService().find(getName()); 
      logFormError("err.duplicate.name", getName());
      return ctrlERROR;
    }
    catch (Exception e) { return ctrlEDIT; }
  }
  
  public boolean idChoosesItem(String id, Object item) {
    String o = ((MetadataAdapter)item).getOrigin().toString();
    return id.equals(o);
  }
  public String getName(){ return getBaseline().getName(); }
  public void setName(String name){ getBaseline().setName(name); }
  
	public String getFolderView() {
	 try {
				FolderView fView = new FolderView();
				return fView.getTreeView(getServlet().getServletContext().getRealPath("\\intralink\\baseline\\folder.xml"));	
	 }
	 catch (Throwable e) { e.printStackTrace(); return e.getMessage(); }    
	}
  
  
  public String save(){
    try {
      String serverName = "";
      String datasourceName= "";
      String name = baseline.getName();
      String folder = baseline.getLocation();
      String release = "";
      //Collection components = getComponents(baseline);
      //Authentication id = getAuthentication();
      //IntralinkAccess.getAccess().createBaseline(serverName, datasourceName, name, location, releaase, components, id);
//      com.zws.service.baseline.BaselineService.getService().update(baseline);
//      com.zws.service.baseline.BaselineService.getService().add(baseline);
      com.zws.service.baseline.BaselineService.getService().save(baseline, getAuthentication());
      return ctrlLIST;
    }
    catch (Exception e) { e.printStackTrace(); return ctrlSYSTEM_ERROR; } 
  }
  
  public String download() {
      
      //zws.Downloader.getURL().openStream();
return null;
  }
/*
  private Collection getOriginList() {
      if (null==baseline.getFiles()) return null;
      Iterator i = baseline.getFiles().iterator();
      Fileentry f;
      Origin o;
      while (i.hasNext()) {
          o = IntralinkAccess.getAccess().
  }
      */
      
  private Collection getComponents(Baseline b) {
   Collection c = new Vector();
   Iterator i = b.getFiles().iterator();
   Fileentry f;
   while(i.hasNext())c.add(((Fileentry)i.next()).getOrigin());
   return c;
  }
  
	public String getFigTree() {
     MetadataAdapter node = (MetadataAdapter) getChosenItem();
     {} //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^");
     {} //System.out.println(node.toString());
     {} //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^");
     ByteArrayInputStream stream = new ByteArrayInputStream(node.toString().getBytes());
	 try {
				{} //System.out.println("getting branch-tree");
				ConfigurationView cView = new ConfigurationView();
				//return cView.getTreeView(getServlet().getServletContext().getRealPath("\\prototype\\treeview\\fig-tree.xml"));	
				return cView.getTreeView(stream);	
	 }
	 catch (Throwable e) { e.printStackTrace(); return e.getMessage(); }    
	}
  
  //public String getCriteria() { return combineCriteriaValues(); }
  /*
  public String getNameCriteria() { return criteriaValue("Name"); }
  public void setNameCriteria(String s) { criteriaValue("Name", s); }
  public String getBranchCriteria(){ return criteriaValue("Branch"); }
  public void setBranchCriteria(String s) { criteriaValue("Branch", s); }
  public String getRevisionCriteria() { return criteriaValue("Revision"); }
  public void setRevisionCriteria(String s) { criteriaValue("Revision", s); }
  public String getVersionCriteria() { return criteriaValue("Version"); }
  public void setVersionCriteria(String s) { criteriaValue("Version", s); }
  public String getRelease_levelCriteria() { return criteriaValue("Release-Level"); }
  public void setRelease_levelCriteria(String s) { criteriaValue("Release-Level", s); }
  public String getCreated_byCriteria() { return criteriaValue("Created-By"); }
  public void setCreated_byCriteria(String s) { criteriaValue("Created-By", s); }
  public String getFolderCriteria() { return criteriaValue("Folder"); }
  public void setFolderCriteria(String s) { criteriaValue("Folder", s); }
  public String getTitleCriteria() { return criteriaValue("Title"); }
  public void setTitleCriteria(String s) { criteriaValue("Title", s); }
  public String getNomenclatureCriteria() { return criteriaValue("Nomenclature"); }
  public void setNomenclatureCriteria(String s) { criteriaValue("Nomenclature", s); }
*/
  public String getLocation(){ return getBaseline().getLocation(); }
  public void setLocation(String location){ getBaseline().setLocation(location); }
  public Collection getFiles(){ return getBaseline().getFiles(); }
  
  public String chooseFile() {
    choose();
    return addFile();
  }
  public String addFile() {
    try {
     IntralinkOrigin o = (IntralinkOrigin)OriginMaker.materialize(getID());
     Fileentry f = new Fileentry();
     f.setName(o.getName());
     f.setBranch(o.getBranch());
     f.setRevision(o.getRevision());
     f.setVersion(""+o.getVersion());
     getBaseline().addFile(f);
     return ctrlOK;
    }
    catch (Exception e) { e.printStackTrace(); return ctrlSYSTEM_ERROR; }
  }
  
  public void addFile(Fileentry fileEntry){ getBaseline().addFile(fileEntry); }
  public String removeFile(){ getBaseline().removeFile(getID()); return ctrlOK; }
  public void removeAllFiles(){ getBaseline().removeAllFiles(); }
  public Fileentry getFile(String name){ return getBaseline().getFile(name); }
  public void prune(){ getBaseline().prune(); }

  public String getSelectedReportName() { return Properties.get(Names.BASELINE_FILES_REPORT); }

  public Object copy() { if (supportsDeepCopy()) return deepCopy(); else return shallowCopy(); }
  public Object deepCopy() { throw new RuntimeException("Deep copy not supported"); }
  public Object shallowCopy() { try {return super.clone(); } catch (CloneNotSupportedException e) { throw new RuntimeException(e.getMessage()); } }
  public boolean supportsDeepCopy() { return false; }  

  public void inactivate() {}
  
  private Baseline getBaseline() { if (null==baseline) baseline=new Baseline(); return baseline; }
  private Baseline baseline=null;
}
