package com.zws.datasource;

import com.zws.domo.baseline.Folder;
import com.zws.domo.document.Document;
import com.zws.functor.intralink.GetFolders;

import java.io.InputStream;


public class IntralinkSource extends DataSource {
  public String getType() { return DATA_SOURCE_INTRALINK; }

  public boolean contains(String origin)throws Exception {return false;} //todo:
  public boolean hasChildren(String origin) { return false; } //todo;
  public Document find(String origin){return null;}
  public void add(Document d) throws Exception{return;} //todo:
  public void update(Document d) throws Exception{return;} //todo:
//  public void delete(Document d) throws Exception{return;} //todo:
  public void delete(String origin) throws Exception{return;} //todo:
  public void storeBinary(Document d, String path, String name, InputStream stream, int len) throws Exception { } //+++todo:


  public String getEXEToolkitEnv() { return exeToolkitEnv; }
  public void setEXEToolkitEnv(String s) { exeToolkitEnv=s; }

  public String getUsername() { return username; }
  public void setUsername(String s) { username=s; }
  public String getPassword() { return password; }
  public void setPassword(String s) { password=s; }
  
  public Folder getFolderTree(){
	try{

		GetFolders getFoldersFunctor = new GetFolders();
		
		//getFoldersFunctor.setStartFolder(topFolder);
		//get these from the datasource
		getFoldersFunctor.setUsername(getUsername());
		getFoldersFunctor.setPassword(getPassword());
		getFoldersFunctor.setEXEToolkitEnv(getEXEToolkitEnv());
		getFoldersFunctor.execute();
			
		return getFoldersFunctor.getFolderTree();
	}catch(Exception ex){
		ex.printStackTrace();
		return null; 
	}
  	
  }

  private String username=null, password=null, exeToolkitEnv=null, folders=null;
}
