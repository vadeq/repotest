package zws.datasource.agile.op;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 23, 2004, 7:29 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.datasource.Names;

import java.io.*;

public class PublishBill extends AgileOpBase {
  public String getEXEName(){ return "publishBill.bat"; }
  public void createInstructionFile(String filename) {
    File instructionFile = new File(filename);
    instructionFile.delete();
    try{
      instructionFile.createNewFile();
      FileWriter outFile = new FileWriter(instructionFile);
      outFile.write(getBillXML());
      outFile.write(Names.NEW_LINE);
      outFile.close();
    }
    catch (Exception e) { e.printStackTrace();}
  }

  protected void handleResponse(String outputFilename) throws Exception {
    byte[] bytes;
    File outFile = new File(outputFilename);
    FileInputStream inStream = new FileInputStream(outFile);
    bytes = new byte[(int)outFile.length()];
    inStream.read(bytes);
    setResponseXML(new String(bytes));
  }
  
  public String getBillXML() { return billXML; }
  public void setBillXML(String s) { billXML=s; }
  public String getResponseXML() { return responseXML; }
  public void setResponseXML(String s) { responseXML=s; }

  private String billXML=null;
  private String responseXML=null;
}
