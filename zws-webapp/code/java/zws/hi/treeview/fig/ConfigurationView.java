package zws.hi.treeview.fig;/*
@version 1.0
@author athakur
DesignState - Design Compression Technology: May 9, 2004.
Copywrite (c) 2004 Zero Wait-State Inc. All rights reserved world-wide */
import zws.application.Properties;
import zws.application.server.webapp.Names;
import zws.hi.treeview.TreeViewBase;
public class ConfigurationView extends TreeViewBase {
	protected void addProcessingInstructions() {
	 addProcessingInstruction(Properties.get(Names.xsltFIG_TREE));
	}
	
}
