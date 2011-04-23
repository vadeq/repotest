package zws.datasource.intralink.op.workspace; /*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 11, 2003, 9:45 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.application.server.datasource.Names;
import zws.application.Properties;
import zws.datasource.intralink.xml.IntralinkResultHandler;
import zws.util.ExecShell;

import java.io.*;
import java.io.File;
import java.io.FileWriter;
public class ModelCheck extends IntralinkWorkspaceOp {
  protected String getEXEPath() { return getConnectorPath() + Names.PATH_SEPARATOR + "ModelCheck.bat"; }
    
  
  protected void handleResponse(String outputFilename) throws Exception {
    throw  new Exception("ModelCheck Failure"); //hack need to write response handler
  }
  
  protected void createInstructionFile(String filename) throws Exception {
    File f = new File(filename);
    if (f.exists()) f.delete();
    try{
  		f.createNewFile();
  		instructionFile = new FileWriter(f);
      String endl = Names.NEW_LINE;
      /*
      instructionFile.write("<?xml version='1.0' encoding='latin1'?>" + endl);
      instructionFile.write("<qx output-encoding='LATIN1'>" + endl);
      instructionFile.write(" <ilink-qx>" + endl);
      instructionFile.write("  <open-repository username='"+xmlValue(getUsername())+"' password='"+xmlValue(getPassword())+"'>"+endl);
      instructionFile.write("   <open-sandbox ldb-path='"+xmlValue(getLDBLocation().getAbsolutePath())+"'>" + endl);
      instructionFile.write("    <open-workspace workspace='"+xmlValue(getWorkspaceName())+"'>" + endl);
      */
      writeWorkspaceInstruction();
      /*
      instructionFile.write("    </open-workspace>" + endl);
      instructionFile.write("   </open-sandbox>" + endl);
      instructionFile.write("  </open-repository>" + endl);
      instructionFile.write(" </ilink-qx>" + endl);
      instructionFile.write("</qx>");
      */
      instructionFile.write(endl);
      instructionFile.close();
    }
    catch (Exception e) { e.printStackTrace(); throw e;}
  }    
  
  public void setArguments(ExecShell shell) {
	  shell.addCommandLineArgument(getLDBLocation().getAbsolutePath());
	  shell.addCommandLineArgument(getWorkspaceName());	
	  shell.addCommandLineArgument(getComponentName());
  }
  
  protected void writeWorkspaceInstruction() throws IOException {
    //<mdlcheck name="pdm_test_p-001.prt" workspace="zws" output="C:\temp" start_pro="ProEWF2.bat"/>
    String proE = Properties.get("proe-exe");
    openTag("mdlcheck");
    writeParameter("name", getComponentName());
    writeParameter("workspace", getWorkspaceName());
    writeParameter("output", workingDir.getAbsolutePath());
    writeParameter("start_pro", proE);
    closeTag();
  }
    
		public String getComponentName() { return componentName; }
		public void setComponentName(String s) { componentName=s; } 
		
		private String componentName=null;
		private String outputDir=null;
		private String inputPath=null;
		private String startPro=null;
}
