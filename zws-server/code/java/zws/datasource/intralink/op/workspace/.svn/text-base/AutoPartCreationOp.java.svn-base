package zws.datasource.intralink.op.workspace;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 1, 2004, 3:40 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.datasource.Names;
import zws.util.ExecShell;

import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Map;

public class AutoPartCreationOp extends IntralinkWorkspaceOp {
  protected String getEXEPath() { return getConnectorPath() + Names.PATH_SEPARATOR + "ProQxService.bat"; }

	public void setArguments(ExecShell shell) {
    shell.addCommandLineArgument(getLDBLocation().getAbsolutePath());
    shell.addCommandLineArgument(getWorkspaceName());	
    shell.addCommandLineArgument(startPart);
  }

  protected void writeWorkspaceInstruction() throws IOException {
    /*
	  openTag("checkout-latest");
	  writeParameter("name", startPart);
	  closeTag();
    */
      
    openTag("start-proe");
	  writeParameter("command", proCommand);
	  writeParameter("hidden", true);
    endUnaryTag();
	  openTag("load-proe-model");
	  writeParameter("name", startPart+".drw");
	  closeTag();
	  openTag("rename-drawing-and-model");
	  writeParameter("new-name", newPartName);
	  closeTag();

	  openTag("load-proe-model");
	  writeParameter("name", newPartName+".prt");
	  closeTag();
	  Iterator i = proParameters.keySet().iterator();
	  String parameter, value;
	  while (i.hasNext()) {
	    parameter= (String)i.next();
	    value = (String)proParameters.get(parameter);
		  openTag("set-proe-parameter");
		  writeParameter("parameter", parameter);
		  writeParameter("value", value);
		  closeTag();	    
	  }
 
	  openTag("regenerate-proe-solid");
	  closeTag();
	  openTag("save-proe-model");
	  closeTag();
	  
    closeTag("start-proe");
  }
  
  public void setStartPart(String s) { startPart=s; }
  public void setNewPartName(String s) { newPartName=s; }
  public void setProParameters(Map m) { proParameters =m; }
  
  private String proCommand = "ProE-TK.bat";
	private String startPart = null;
	private String newPartName = null;
	private Map proParameters = null;
	
}
