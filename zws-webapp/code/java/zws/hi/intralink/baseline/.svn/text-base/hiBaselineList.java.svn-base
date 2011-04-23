package zws.hi.intralink.baseline;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 7, 2004, 6:22 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.AdapterPrototype;
import zws.util.AdapterVector;

import com.zws.exception.EntryNotFound;
import com.zws.hi.hiList;
import com.zws.service.baseline.BaselineService;

import java.io.FileInputStream;

import org.rollerjm.util.treeview.TreeviewXML;

public class hiBaselineList extends hiList {
  public void bind() throws Exception { service = BaselineService.getService(); }

  public void render () throws Exception { 
    AdapterPrototype adapter = new hiBaseline();
    AdapterVector c = new AdapterVector(adapter);
    c.addAll(service.findAll());
    setItems(c); 
  }

  public boolean idChoosesItem(String id, Object o) { return id.equals(((hiBaseline)o).getName());}

  public String clear() { setChosenItem(null); return ctrlOK; }

  public String delete() {
    try {
      service.delete(getID(), getAuthentication());
      setChosenItem(null);
      logFormStatus("msg.item.deleted", getID());
      return ctrlLIST;
    }
    catch (EntryNotFound e) {
      logFormError("err.baseline.not.found", getID());
      return ctrlERROR;
    }
    catch (Exception e) {
      logFormError("system.err.event", "delete", e.getMessage(), "");
      return ctrlSYSTEM_ERROR;
    }
  }

    public String getBranchTree() {
      String s=null;
      try{
       TreeviewXML treeview = new TreeviewXML(new FileInputStream("c:/zws/tomcat/webapps/XTreeview/WEB-INF/xml/test.xml"), "ISO-8859-1");
       s =  treeview.getHTML();
      }
      catch (Throwable t) { t.printStackTrace();  return "Bad Tree"; }
      return s;
    }
    
    public String getRevisionTree() {
      String s;
      try{
       TreeviewXML treeview = new TreeviewXML(new FileInputStream("c:/zws/tomcat/webapps/XTreeview/WEB-INF/xml/treeview.xml"), "ISO-8859-1");
       s = treeview.getHTML();
      }
      catch (Throwable t) { t.printStackTrace();  return "Bad Tree"; }
      return s;
    }

  private BaselineService service;
}
