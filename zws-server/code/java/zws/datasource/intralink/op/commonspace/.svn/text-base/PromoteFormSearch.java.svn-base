package zws.datasource.intralink.op.commonspace;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 21, 2004, 11:37 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.datasource.Names;
import zws.datasource.intralink.xml.IntralinkResultHandler;
import zws.datasource.intralink.xml.PromoteFormSearchResultHandler;

import java.io.*;
import java.io.File;
import java.io.FileWriter;

public class PromoteFormSearch extends IntralinkRepositoryOp {
  public IntralinkResultHandler getXMLResultHandler() {return new PromoteFormSearchResultHandler(); }
  protected void writeRepositoryInstruction() throws IOException {
  }

  /*
  protected void createInstructionFile(String filename) {
    File instructionFile = new File(filename);
    instructionFile.delete();
    String username = getDatasource().getDefaultUsername();
    String password = getDatasource().getDefaultPassword();
    if (null!= getAuthentication()) {
      username = getAuthentication().getUsername();
      password = getAuthentication().getPassword();
    }
    try{
      instructionFile.createNewFile();
      FileWriter outFile = new FileWriter(instructionFile);
      outFile.write("<find-rtp-forms ");
      if (null!=getDatedAfter()) outFile.write("date=\"" + getDatedAfter() + "\" ");
      if (null!=getPromoteToLevel()) outFile.write("promote-to=\"" + getPromoteToLevel()+"\" ");
      outFile.write("user=\"" + username +"\" ");
      outFile.write("password=\"" +  password +"\" ");
      outFile.write("/>");
      outFile.write(Names.NEW_LINE);
      outFile.close();
    }
    catch (Exception e) { e.printStackTrace(); }
  }
  */
  public String getDatedAfter() { return datedAfter; }
  public void setDatedAfter(String s) { datedAfter=s; }
  public String getPromoteToLevel() { return promoteToLevel; }
  public void setPromoteToLevel(String s) { promoteToLevel=s; }
  
  private String datedAfter=null;
  private String promoteToLevel=null;
}
