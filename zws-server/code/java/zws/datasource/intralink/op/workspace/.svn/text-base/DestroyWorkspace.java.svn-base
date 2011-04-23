package zws.datasource.intralink.op.workspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.datasource.Names;
import zws.datasource.intralink.xml.IntralinkResultHandler;
import zws.util.DeleteFile;
//import zws.util.{}//Logwriter;

import java.io.*;

public class DestroyWorkspace extends IntralinkWorkspaceOp {
  public IntralinkResultHandler getXMLResultHandler() { return null; }

  protected void createInstructionFile(String filename) throws Exception {
    File f = new File(filename);
    if (f.exists()) f.delete();
    File ddb = new File(getLDBLocation().getAbsolutePath() + Names.PATH_SEPARATOR + ".proi", "Local.ddb");
    
    boolean needToCreateLDB = !ddb.exists();
    if (needToCreateLDB) {}//Logwriter.printOnConsole("Creating LDB");
    else {}//Logwriter.printOnConsole("LDB already exists");
    try{
  		f.createNewFile();
  		instructionFile = new FileWriter(f);
      String endl = Names.NEW_LINE;
      instructionFile.write("<?xml version='1.0' encoding='latin1'?>" + endl);
      instructionFile.write("<qx output-encoding='LATIN1'>" + endl);
      instructionFile.write(" <ilink-qx>" + endl);
      instructionFile.write("  <open-repository username='"+xmlValue(getUsername())+"' password='"+xmlValue(getPassword())+"'>" + endl);
      if (needToCreateLDB) instructionFile.write("   <create-sandbox ldb-path='"+xmlValue(getLDBLocation().getAbsolutePath())+"'/>" + endl);
      instructionFile.write("   <open-sandbox ldb-path='"+xmlValue(getLDBLocation().getAbsolutePath())+"'>" + endl);
      instructionFile.write("    <remove-workspace workspace='"+xmlValue(getWorkspaceName())+"'/>" + endl);
      instructionFile.write("   </open-sandbox>" + endl);
      instructionFile.write("  </open-repository>" + endl);
      instructionFile.write(" </ilink-qx>" + endl);
      instructionFile.write("</qx>");
      instructionFile.write(endl);
      instructionFile.close();
    }
    catch (Exception e) { e.printStackTrace(); throw e;}
  }    
  
  protected void writeWorkspaceInstruction() throws IOException {}
  
  protected void finishExecution() throws Exception {
    if (zws.Server.debugMode()) return;
    //cleanup
    DeleteFile deleter = new DeleteFile();
    deleter.setDeleteIfNotEmpty(true);
    deleter.setFile(getLDBLocation());
    deleter.execute();
    if (Boolean.FALSE == (Boolean)deleter.getResult()) {} {}//Logwriter.printOnConsole("Could not delete LDB dir: " + getLDBLocation().getAbsolutePath());   
  }
}
