package zws.hi.prototype.treeview;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 1, 2004, 9:47 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.configuration.BranchBase;
import zws.hi.treeview.fig.*;
import zws.hi.treeview.folder.FolderView;
import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.util.tree.TreeNode;

import org.apache.struts.action.ActionForm;

public class hiTreeview extends ActionForm {
	public void adapt(Object o) { }
	public Object getModelObject() { return null; }
	public String alias() { return "tree"; }
	
	
	public String getTree() {
	 try {
				FolderView fView = new FolderView();
				return fView.getTreeView(getServlet().getServletContext().getRealPath("\\prototype\\treeview\\folder.xml"));	
	 }
	 catch (Throwable e) { e.printStackTrace(); return e.getMessage(); }    
	}




  
	public String getFigTree() {
	 try {
				{} //System.out.println("getting branch-tree");
				ConfigurationView cView = new ConfigurationView();
				return cView.getTreeView(getServlet().getServletContext().getRealPath("\\prototype\\treeview\\fig-tree.xml"));	
	 }
	 catch (Throwable e) { e.printStackTrace(); return e.getMessage(); }    
	}
  
	public String getBranchTree() {
	 try {
				{} //System.out.println("getting branch-tree");
				BranchView view = new BranchView();
				return view.getTreeView(getServlet().getServletContext().getRealPath("\\prototype\\treeview\\fig-tree.xml"));	
	 }
	 catch (Throwable e) { e.printStackTrace(); return e.getMessage(); }    
	}
  
	public String getHistoryTree() {
	 try {
				{} //System.out.println("getting branch-tree");
				HistoryView view = new HistoryView();
				return view.getTreeView(getServlet().getServletContext().getRealPath("\\prototype\\treeview\\fig-tree.xml"));	
	 }
	 catch (Throwable e) { e.printStackTrace(); return e.getMessage(); }    
	}
  
  
  
	public TreeNode makeTree() throws Exception {
		Metadata d0,d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11;
		d0=c("data", "main", "C");
		d1=c("data", "main", "B");
		d2=c("data", "main", "A");
		d3=c("data", "main", "C");
		d4=c("data", "main", "B");
		d5=c("data", "main", "A");
		d6=c("data", "main/abc", "D");
		d7=c("data", "main/abc", "A");
		d8=c("data", "main/abc", "C");
		d9=c("data", "main/main2", "B");
		d10=c("data", "main/main2/main3", "A");
		d11=c("data", "main/main2/main3", "B");

		BranchBase tree = new BranchBase();
		tree.defineKeyFields("Name", "Branch", "Revision");

		//History tree = new HistoryBase();
		//tree.defineKeyFields("Name", "Branch", "Revision");
    
		//RevisionTree tree=new RevisionTreeBase();
		//tree.defineKeyFields("Name|Branch", "Revision", "Version");
    
		tree.defineKey(d0);
		try {tree.place(d10);} catch (Exception e) {e.printStackTrace();} 
		try {tree.place(d0);} catch (Exception e) {e.printStackTrace();} 
		try {tree.place(d1);} catch (Exception e) {e.printStackTrace();} 
		try {tree.place(d2);} catch (Exception e) {e.printStackTrace();} 
		try {tree.place(d3);} catch (Exception e) {e.printStackTrace();} 
		try {tree.place(d4);} catch (Exception e) {e.printStackTrace();} 
		try {tree.place(d5);} catch (Exception e) {e.printStackTrace();} 
		try {tree.place(d6);} catch (Exception e) {e.printStackTrace();} 
		try {tree.place(d7);} catch (Exception e) {e.printStackTrace();} 
		try {tree.place(d8);} catch (Exception e) {e.printStackTrace();} 
		try {tree.place(d9);} catch (Exception e) {e.printStackTrace();} 
		try {tree.place(d11);} catch (Exception e) {e.printStackTrace();} 
	 // {} //System.out.println(tree);
		tree.selectDefault();
		MetadataBase m = new MetadataBase();
		m.merge(tree);
		{} //System.out.println("------------------");
		{} //System.out.println(m);
		print(tree);
  return tree;
 }

  
	protected void print(Object o) {
		{} //System.out.println("-------------------------------------------------------");
		{} //System.out.println(o);
		{} //System.out.println("-------------------------------------------------------");
	}
  
	public Metadata c(String name, String branch, String revision) {
    try {
      String uid = getUID();
      MetadataBase m=new MetadataBase();
      Origin o = OriginMaker.materialize(zws.Server.getDomainName(), "DesignState-node-0", "none", "type", 0, uid, name, null, null);
      //o.setName(name);
      m.setOrigin(o);
      m.setName(name);
      m.set("Branch", branch);
      m.set("Revision", revision);
      m.set("Version", uid);
      return m;
    }
    catch(Exception e) { e.printStackTrace(); return null; }
	}
	private static int i=0;
	private static String getUID() { return "id-" + i++; }
  
}
