package zws.datasource.intralink.op.workspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.datasource.Names;
import zws.exception.NotADirectory;
import zws.util.CopyFile;
//import zws.util.{}//Logwriter;

import java.io.*;

public class CreateWorkspace extends IntralinkWorkspaceOp {

  protected void initExecution() throws Exception {
    if (usePersonalWorkspace()) return;
    resetLDB();
  }
      

  protected void createInstructionFile(String filename) throws Exception {
    /*
    CopyFile copier = new CopyFile(); 
    File dotProi = new File (getEnvRoot().getAbsolutePath() + Names.PATH_SEPARATOR + "LDB" + Names.PATH_SEPARATOR + ".proi");
    if (!dotProi.exists()) throw new NotADirectory(dotProi);
    copier.setSource(dotProi);
    copier.setToDir(getLDBLocation());
    copier.setCopyRecursively(true);
    copier.execute();
    */
    File f = new File(filename);
    if (f.exists()) f.delete();
    File ddb = new File(getLDBLocation().getAbsolutePath() + Names.PATH_SEPARATOR + ".proi", "Local.ddb");
    {}//Logwriter.printOnConsole("------------");
    {}//Logwriter.printOnConsole(ddb.getAbsolutePath());
    {}//Logwriter.printOnConsole("------------");
    
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
      instructionFile.write("    <create-workspace workspace='"+xmlValue(getWorkspaceName())+"'/>" + endl);
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

}
