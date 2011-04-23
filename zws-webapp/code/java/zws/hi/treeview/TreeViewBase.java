package zws.hi.treeview;/*
@version 1.0
@author athakur
DesignState - Design Compression Technology: May 9, 2004.
Copywrite (c) 2004 Zero Wait-State Inc. All rights reserved world-wide */
import zws.application.Properties;
import zws.application.server.webapp.Names;
import zws.op.OpBase;
import zws.xml.xslt.Stylizer;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.ByteArrayInputStream;

public abstract class TreeViewBase extends OpBase {

	public void execute() throws Exception { setResult(getTreeView()); }

	public void setXMLSource(String s) throws Exception { xmlSource = new FileInputStream(s); }
	public void setXMLSource(InputStream s) { xmlSource=s; }

	public String getTreeView(String xmlPath) throws Exception {
		{} //System.out.println("reading :" + xmlPath);
		setXMLSource(xmlPath);
		return getTreeView();
	}
	
	
	public String getTreeViewFromString(String xml) throws Exception {
	  return getTreeView(new ByteArrayInputStream(xml.getBytes()));
	}
	
	public String getTreeView(InputStream xml) throws Exception {
		setXMLSource(xml);
		return getTreeView();
	}
	
	protected abstract void addProcessingInstructions();

	private String getTreeView() throws Exception {
		addProcessingInstructions();
		stylizer.addProcessingInstruction(Properties.get(Names.xsltTREEVIEW));
		{} //System.out.println("generated Tree from source");
		return stylizer.styleXML(xmlSource).toString();		
	}
	
	protected void addProcessingInstruction(String xsltPath){	stylizer.addProcessingInstruction(xsltPath); }
	protected void addProcessingInstruction(InputStream xslt){	stylizer.addProcessingInstruction(xslt); }
	
	private InputStream xmlSource = null;
	private Stylizer stylizer = new Stylizer();
}
