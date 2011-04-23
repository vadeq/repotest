package zws.datasource.intralink.op.commonspace;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 30, 2004, 2:16 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.datasource.Names;
import zws.origin.IntralinkOrigin;
import zws.util.ExecShell;

import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Iterator;

public class CreateBaseline {
    /*
public class CreateBaseline extends IntralinkRepositoryOp {
  protected void createInstructionFile(String filename) {
		File input= new File(filename);
	  input.delete();
		try{
		  input.createNewFile();
		  FileWriter writer = new FileWriter(input);
      Iterator i = components.iterator();
      IntralinkOrigin origin;
      while (i.hasNext()) {
        origin = (IntralinkOrigin)i.next();
		    writer.write(origin.getName()+",");
		    writer.write(origin.getBranch()+",");
		    writer.write(origin.getRevision()+",");
		    writer.write(origin.getVersion()+Names.NEW_LINE);
      }
		  writer.close();
		}catch (Exception e) { e.printStackTrace(); }
  }
  
  public void setArguments(ExecShell shell) {
    shell.addCommandLineArgument(getName());
    shell.addCommandLineArgument(getFolderLocation());
    shell.addCommandLineArgument(getReleaseLevel());
    //shell.addCommandLineArgument(getInstructionFileName(workingDir));
    shell.addCommandLineArgument(getUsername());
    shell.addCommandLineArgument(getPassword());
    //to many inputs!!! - an only have total of 9!!
  }
  
  public String getName() { return name; }
  public void setName(String s) { name = s; }
  public String getFolderLocation() { return folderLocation; }
  public void setFolderLocation(String s) { folderLocation = s; }
  public String getReleaseLevel() { return releaseLevel; }
  public void setReleaseLevel(String s) { releaseLevel = s; }
  public Collection getComponents() { return components; }
  public void setComponents(Collection s) { components = s; }
  
  private String name=null;
  private String folderLocation=null;
  private String releaseLevel=null;
  private Collection components=null;
  
  private static String COMPONENT_INPUT_FILE="componentList.txt";
  */
}