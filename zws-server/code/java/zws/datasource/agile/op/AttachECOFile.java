package zws.datasource.agile.op;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 23, 2004, 7:29 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.ExecShell;
import zws.application.server.datasource.Names;

import java.io.*;

public class AttachECOFile extends AgileOpBase {
  public String getEXEName(){ return "AttachFile.bat"; }
  public void createInstructionFile(String filename) { return; }

  protected void handleResponse(String outputFilename) throws Exception { }
  
  public String getPartnumber() { return partnumber; }
  public void setPartnumber(String s) { partnumber=s; }
  public String getECOnumber() { return ecoNumber; }
  public void setECOnumber(String s) { ecoNumber=s; }
  public String getURL() { return url; }
  public void setURL(String s) { url=s; }
  
  protected void setArguments(ExecShell shell) {
	  shell.addCommandLineArgument(getURL());
	  shell.addCommandLineArgument(getUsername());
	  shell.addCommandLineArgument(getPassword());
	  shell.addCommandLineArgument(getPartnumber());
	  shell.addCommandLineArgument(getAttachment().getAbsolutePath());
	  shell.addCommandLineArgument(getECOnumber());
	}    

  public File getAttachment() { return attachment; }
  public void setAttachment(File f) { attachment = f; }
  
  private String partnumber = null;
  private String ecoNumber = null;
  private String url = null;
  private File attachment = null;
}
