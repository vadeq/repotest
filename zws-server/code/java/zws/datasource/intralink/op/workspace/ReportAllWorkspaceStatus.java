package zws.datasource.intralink.op.workspace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.datasource.Names;
import zws.data.workspace.Workspace;
import zws.datasource.intralink.xml.*;
import zws.datasource.intralink.xml.IntralinkResultHandler;
import zws.datasource.intralink.xml.WorkspaceListHandler;

import java.io.*;

public class ReportAllWorkspaceStatus extends IntralinkWorkspaceOp {
  public IntralinkResultHandler getXMLResultHandler() { 
    handler = new WorkspaceStatusHandler(datasource.getName(), getAuthentication());
    return handler;
  }

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
      instructionFile.write("  <open-repository username='"+xmlValue(getUsername())+"' password='"+xmlValue(getPassword())+"'>" + endl);
      instructionFile.write("   <open-sandbox ldb-path='"+xmlValue(getLDBLocation().getAbsolutePath())+"'>" + endl);
      instructionFile.write("    <report-all-workspace-status/>" + endl);
      instructionFile.write("   </open-sandbox>" + endl);
      instructionFile.write("  </open-repository>" + endl);
      instructionFile.write(" </ilink-qx>" + endl);
      instructionFile.write("</qx>");
      instructionFile.write(endl);
      instructionFile.close();
    }
    catch (Exception e) { e.printStackTrace(); throw e;}
  }

  private WorkspaceStatusHandler handler = null;
  protected void writeWorkspaceInstruction() throws IOException { }
}
