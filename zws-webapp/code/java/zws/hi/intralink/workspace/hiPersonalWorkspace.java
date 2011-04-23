package zws.hi.intralink.workspace; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Dec 12, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

import zws.Downloader;
import zws.IntralinkAccess;
import zws.data.workspace.*;
import zws.exception.DuplicateName;
import zws.folder.IntralinkFolder;
import zws.hi.treeview.folder.FolderView;
import zws.origin.Origin;
import zws.service.webapp.TransferService;

import com.zws.hi.hiList;

import java.net.URL;
import java.net.URLDecoder;
import java.util.Collection;

import org.apache.struts.upload.FormFile;

public class hiPersonalWorkspace extends hiList{
 
  private static synchronized int getNewCount() { return count++; }
  static int count=8;

	public String getFolderView() {
	 try {
     IntralinkFolder f = getIEE().getRootFolder();
     String s =f.toString();
     {} //System.out.println(".");
     {} //System.out.println(".");
     {} //System.out.println(".");
     {} //System.out.println(".");
	   {} //System.out.println(s);
 		 FolderView fView = new FolderView();
	   return fView.getTreeViewFromString(f.toString());
 		 /*
 		 File xml = new File ("C:\\f.xml");
 		 FileWriter w = new FileWriter(xml);
 		 w.write("<?xml version=\"1.0\" encoding=\"latin1\" ?>" + zws.application.Names.NEW_LINE);
 		 w.write(f.toString());
 		 w.flush();
 		 w.close();  		 		 
	   return fView.getTreeView("C:\\f.xml");
	   */
	 }
	 catch (Throwable e) { e.printStackTrace(); return e.getMessage(); }    
	}

  public boolean idChoosesItem(String id, Object o) {
    Workspace ws = (Workspace) o;
    if (id.equalsIgnoreCase(ws.getName())) {
      setParameter("workspace-name", ws.getName());
      return true;        
    }
    return false;
  }

  public String createWorkspace() {
    try {
	    String workspace=getWorkspaceName();
	    ilinkClient.personalCreateWorkspace(getIEE().getSelectedServer(), getIEE().getSelectedRepository(), workspace, getAuthentication());
	    logFormStatus("msg.item.created", "workspace " + workspaceName);
	    workspaceName=null;
	    refreshWorkspaces();
	    return ctrlOK;
    }
    catch (Exception e) {
      e.printStackTrace();
      return ctrlERROR;
    }
  }
  
  public String refreshWorkspaces() {
    //capture selected workspace
    String wsName=null;
    Workspace ws = getSelectedWorkspace();
    if (null!=ws) wsName=ws.getName();
    
    //clear all workspaces
    setItems(null);
    setChosenItem(null);
    setParameter("workspace-name", null);

    //reload all workspaces
    loadWorkspaces();
    
    //reselect captured workspace 
    if (null!=wsName) chooseItem(wsName);
    return ctrlOK; 
  }
  
  public void unselectWorkspace() {
    setChosenItem(null);
    setParameter("workspace-name", null);
  }

  public String deleteWorkspace() {
    try {
	    String workspace=getWorkspaceName();
	    ilinkClient.personalDestroyWorkspace(getIEE().getSelectedServer(), getIEE().getSelectedRepository(), workspace, getAuthentication());
	    logFormWarning("msg.item.deleted", "workspace " + workspaceName);
	    workspaceName=null;
	    refreshWorkspaces();
	    if (null!=getSelectedWorkspace()&& workspace.equals(getSelectedWorkspace().getName())) unselectWorkspace();
	    return ctrlOK;
    }
    catch (Exception e) {
      e.printStackTrace();
      return ctrlERROR;
    }
  }

  public String lockWorkspaceItem() {
   String item = getID();
	  try {
	    String workspace=getSelectedWorkspace().getName();
	    ilinkClient.lock(getIEE().getSelectedServer(), getIEE().getSelectedRepository(), item, getAuthentication());
	    logFormStatus("status.locked", item);
	    refreshWorkspaces();
	    return ctrlOK;
	  }
	  catch (Exception e) {
	    e.printStackTrace();
	    logFormError("err.locked", item);
	    return ctrlERROR;
	  }
	}  

  public String synchronizeWithCommonSpace() {
   String item = getID();
	  try {
	    String workspace=getSelectedWorkspace().getName();
	    ilinkClient.personalSynchronizeWithCommonSpace(getIEE().getSelectedServer(), getIEE().getSelectedRepository(), workspace, item, getAuthentication());
	    refreshWorkspaces();
	    return ctrlOK;
	  }
	  catch (Exception e) {
	    e.printStackTrace();
	    return ctrlERROR;
	  }
	}
  
  public String unlockWorkspaceItem() {
   String item = getID();
	  try {
	    String workspace=getSelectedWorkspace().getName();
	    ilinkClient.unlock(getIEE().getSelectedServer(), getIEE().getSelectedRepository(), item, getAuthentication());
	    logFormStatus("status.unlocked", item);
	    refreshWorkspaces();
	    return ctrlOK;
	  }
	  catch (Exception e) {
	    e.printStackTrace();
	    logFormError("err.unlocked", item);
	    return ctrlERROR;
	  }
	}

  public String deleteWorkspaceItem() {
	  try {
	    String workspace=getSelectedWorkspace().getName();
	    String item = getID();
	    ilinkClient.personalRemoveFromWorkspace(getIEE().getSelectedServer(), getIEE().getSelectedRepository(), workspace, item, getAuthentication());
	    logFormWarning("msg.item.deleted.from", item, workspace);
	    refreshWorkspaces();
	    return ctrlOK;
	  }
	  catch (Exception e) {
	    e.printStackTrace();
	    return ctrlERROR;
	  }
	}

  public String checkinSelectedWorkspace() {
	  try {
	    String workspace=getSelectedWorkspace().getName();
	    ilinkClient.personalCheckin(getIEE().getSelectedServer(), getIEE().getSelectedRepository(), workspace, getAuthentication());
	    refreshWorkspaces();
	    return ctrlOK;
	  }
	  catch (Exception e) {
	    e.printStackTrace();
	    return ctrlERROR;
	  }
	}

  public Collection getWorkspaces() {
    if (null!=getParameter("refresh-workspaces")) {
      removeParameter("refresh-workspaces");
      refreshWorkspaces();
    }
    else if (null==getItems() || 0== getItems().size()) loadWorkspaces();
    return getItems();
  }

  public void loadWorkspaces() {
    try {
      Collection workspaces=ilinkClient.personalReportAllWorkspaceStatus(getIEE().getSelectedServer(), getIEE().getSelectedRepository(), getAuthentication());
      setItems(workspaces);
    }
    catch (Exception e) { e.printStackTrace(); }
  }

  public Workspace getSelectedWorkspace() {
    Workspace ws=(Workspace)getChosenItem();
    return ws;  
 }

  public IlinkWorkspaceItemAdapter getSelectedItem() {
    Workspace ws=getSelectedWorkspace();
    IlinkWorkspaceItem item = (IlinkWorkspaceItem)ws.getItem(getID());
    IlinkWorkspaceItemAdapter adapter = new IlinkWorkspaceItemAdapter();
    adapter.adapt(item);
    adapter.configure(getIEE());
    return adapter;  
 }

  public String navToUpdateBinaryFile() {
    selectedItemName=getID();
    clearUploadedFiles();
    return ctrlUPDATE_EXISTING_BINARY;
	}

  public String navToImportToWorkspace() {
    setID(null);
    selectedItemName=null;
    clearUploadedFiles();
    return ctrlIMPORT_NEW_BINARY;
	}

  public void nameNewBinaryFile() throws Exception {
    if (this.getSelectedWorkspace().hasItem(getNewBinaryFileName())) {
      logFormError("err.duplicate.name.in.workspace", getNewBinaryFileName());
      throw new DuplicateName(getNewBinaryFileName());
    }
    if (ilinkClient.contains(getIEE().getSelectedServer(), getIEE().getSelectedRepository(), getNewBinaryFileName(), getAuthentication())) {
        logFormError("err.duplicate.name.in.repository", getNewBinaryFileName());
        throw new DuplicateName(getNewBinaryFileName());      
    }
  }
  
  public void importNewBinaryFile() throws Exception {
    importState=null;
    importToSelectedWorkspace(newBinaryFileURL, getNewBinaryFileName());
    ilinkClient.personalSetAttribute(getIEE().getSelectedServer(), getIEE().getSelectedRepository(), getSelectedWorkspace().getName(), getNewBinaryFileName(), "folder", getNewBinaryFileFolder(), getAuthentication());
    refreshWorkspaces();
  }

  public String overwrite() {
    String wsName = getSelectedWorkspace().getName();
	  if (!getSelectedWorkspace().hasItem(getNewBinaryFileName())) {
	    try {
        ilinkClient.personalCheckoutLatest(getIEE().getSelectedServer(), getIEE().getSelectedRepository(), wsName, getNewBinaryFileName(), getAuthentication());
        refreshWorkspaces();
	    }
	    catch(Exception e) { e.printStackTrace(); }
  	  if (getSelectedWorkspace().hasItem(getNewBinaryFileName())) {
  	    logFormStatus("msg.item.checked.out", getNewBinaryFileName(), wsName);
  	  }
  	  else { 
  	    logFormError("err.not.checked.out", getNewBinaryFileName(), wsName);
  	    return ctrlNAME_CONFLICT;
  	  }
	  }
    importState=null;
    try {
      importToSelectedWorkspace(newBinaryFileURL, getNewBinaryFileName());
      refreshWorkspaces();
  	  logFormWarning("warn.updated", getNewBinaryFileName());
      return ctrlCOMPLETE;
    }
    catch (Exception e) {
      logFormError("err.uploading.file");
      e.printStackTrace();
      return ctrlCOMPLETE;
    }
  }

  public String next() {
    if (null==importState) importState=NAME;
    if (COMPLETE==importState) importState=NAME;
    
    if (NAME.equalsIgnoreCase(importState)) {
      try {
        nameNewBinaryFile();
        importState = PLACE;
      }
      catch (Exception e) {
        return ctrlNAME_CONFLICT;
      }
    } 
    else if (PLACE.equalsIgnoreCase(importState)) {
      try {
        importNewBinaryFile();
        logFormStatus("msg.item.imported", getNewBinaryFileName());
        importState = COMPLETE;
        //importState = SET_RELEASE;   
      }
      catch (Exception e) {
        logFormStatus("err.item.not.imported", getNewBinaryFileName());
        return ctrlCOMPLETE;
      }
    } 
    else if (SET_RELEASE.equalsIgnoreCase(importState)) {
      importState = SET_REVISION;           
    } 
    else if (SET_REVISION.equalsIgnoreCase(importState)) {
      importState = SET_ATTRIBUTES;           
    } 
    else if (SET_ATTRIBUTES.equalsIgnoreCase(importState)) {
      importState = COMPLETE;           
    } 
    return importState;
  }

  

  public String back() {
    if (PLACE.equalsIgnoreCase(importState)) {
      importState = NAME;
    } 
    else if (SET_RELEASE.equalsIgnoreCase(importState)) {
      importState = PLACE;   
    } 
    else if (SET_REVISION.equalsIgnoreCase(importState)) {
      importState = SET_RELEASE;           
    } 
    else if (SET_ATTRIBUTES.equalsIgnoreCase(importState)) {
      importState = SET_REVISION;           
    } 
    return importState;
  }
  
  
  private void importToSelectedWorkspace(URL url, String fileName) throws Exception {
    ilinkClient.personalImportToWorkspace(getIEE().getSelectedServer(), getIEE().getSelectedRepository(), getSelectedWorkspace().getName(), fileName, url, getAuthentication());
    TransferService.clearFile(url);
  }

  public void handleUploadedFile(FormFile uploadedFile, int idx) {
    if (null!=getSelectedItemName()) {
      try {
        URL url = TransferService.storeFileForDownload(uploadedFile.getInputStream());
        {} //System.out.println("#####################"+getSelectedItemName()+" should be replaced with " + getUploadedFile00().getFileName() + ".");
        clearUploadedFiles();
        importToSelectedWorkspace(url, getSelectedItemName());
    	  logFormWarning("warn.updated", getSelectedItemName());        
        refreshWorkspaces();
        return;
      }
      catch (Exception e) { e.printStackTrace(); return; }
    }
    try {
      newBinaryFileURL = TransferService.storeFileForDownload(getUploadedFile00().getInputStream());
      newBinaryFileName = getUploadedFile00().getFileName();      
      {} //System.out.println("##################### A new file should be imported using " + getUploadedFile00().getFileName() + ".");
      clearUploadedFiles();
      return;
    }
    catch (Exception e) { e.printStackTrace(); return; }
  }
  
  
  public String nameBinary() {
    return ctrlNEXT;
  }
  
  public String locateBinary() {
    return ctrlNEXT;
  }
  
  public String cancelImport() {
    importState=null;
    clearUploadedFiles();      
    return ctrlCANCEL;
  }

  public String download() {
    try {
      Origin origin = getSelectedWorkspace().getItem(getID()).getOrigin();
      URL url = Downloader.getURL(origin);
      //+++ check for null url
      String urlString = url.toString();
      {} //System.out.println(urlString);
      String name = URLDecoder.decode(urlString.substring(urlString.lastIndexOf("/")+1), "UTF-8");
      {} //System.out.println(name);
      String contents="application/octet-stream"; 
      String disposition = "attachment; filename="+name;
      getHttpResponse().setContentType(contents);
      getHttpResponse().setHeader("Content-Disposition", disposition);
      int dataLen = 0;
      if (0<dataLen) getHttpResponse().setContentLength(dataLen);
      streamDataToResponse(url.openStream());
      return ctrlLIST;
    }
    catch (Exception e) { e.printStackTrace(); return ctrlSYSTEM_ERROR; }
  }

  public String getNewBinaryFileName() { return newBinaryFileName; }
  public void setNewBinaryFileName(String s) { newBinaryFileName=s; }

  public String getNewBinaryFileFolder() { return newBinaryFileFolder; }
  public void setNewBinaryFileFolder(String s) { newBinaryFileFolder=s; }

  public String getNewBinaryFileRelease() { return newBinaryFileRelease; }
  public void setNewBinaryFileRelease(String s) { newBinaryFileRelease=s; }

  public String getNewBinaryFileRevision() { return newBinaryFileRevision; }
  public void setNewBinaryFileRevision(String s) { newBinaryFileRevision=s; }
  
  public String getWorkspaceName() { return workspaceName; }
  public void setWorkspaceName(String s) { workspaceName=s; }
  
  public String getSelectedItemName() { return selectedItemName; }
  public void setSelectedItemName(String s) { selectedItemName=s; }
  
  public void setSelectedIEEServer(String s) throws Exception {
    getIEE().setSelectedServer(s);
    refreshWorkspaces();
  }
  public String getSelectedIEEServer() throws Exception { return getIEE().getSelectedServer(); }

  public Collection getIEERepositoryList () throws Exception { return getIEE().getRepositoryList(); }
  public void setSelectedIEERepository(String s) throws Exception { 
    getIEE().setSelectedRepository(s);
    refreshWorkspaces();
  }
  public String getSelectedIEERepository() throws Exception { return getIEE().getSelectedRepository();}

  private String workspaceName=null;
  private String selectedItemName=null;
  
  //import wizard parameters
  private URL newBinaryFileURL=null;
  private String newBinaryFileName;
  private String newBinaryFileFolder;
  private String newBinaryFileRelease;
  private String newBinaryFileRevision;
  
  private static String NAME = "name-binary";
  private static String PLACE = "place-binary";
  private static String SET_RELEASE = "set-release";
  private static String SET_REVISION = "set-revision";
  private static String SET_ATTRIBUTES = "set-attributes";
  private static String COMPLETE = ctrlCOMPLETE;
  
  private String importState=null;
  
  private IntralinkAccess ilinkClient = IntralinkAccess.getAccess();

}
