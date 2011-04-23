package zws.hi.treeview.agile;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 22, 2004, 7:36 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Properties;
import zws.application.server.webapp.Names;
import zws.hi.treeview.TreeViewBase;

public class AgilePublishResult extends TreeViewBase {
	protected void addProcessingInstructions() {
	 addProcessingInstruction(Properties.get(Names.xsltAGILE_PUBLISH_RESULT_TREE));
	}
}
