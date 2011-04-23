package zws.datasource.intralink.op.commonspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.datasource.Names;
import zws.datasource.intralink.op.IntralinkOpBase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class IntralinkRepositoryOp extends IntralinkOpBase {

  protected abstract void writeRepositoryInstruction() throws IOException;
  
  protected void createInstructionFile(String filename) throws Exception {
    File f = new File(filename);
    if (f.exists()) f.delete();
    try{
  		f.createNewFile();
  		instructionFile = new FileWriter(f);
      String endl = Names.NEW_LINE;
      instructionFile.write("<?xml version='1.0' encoding='latin1'?>" + endl);
      instructionFile.write("<qx output-encoding='LATIN1'>" + endl);
      instructionFile.write(" <ilink-qx>" + endl);
      instructionFile.write("  <open-repository username='"+xmlValue(getUsername())+"' password='"+xmlValue(getPassword())+"'>"+endl);
      writeRepositoryInstruction();
      instructionFile.write("  </open-repository>" + endl);
      instructionFile.write(" </ilink-qx>" + endl);
      instructionFile.write("</qx>");
      instructionFile.write(endl);
      instructionFile.close();
    }
    catch (Exception e) { e.printStackTrace(); throw e;}
  }    
  
}
