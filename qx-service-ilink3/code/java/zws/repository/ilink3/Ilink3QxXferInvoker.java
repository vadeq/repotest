/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Mar 23, 2007 11:10:21 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.repository.ilink3;

import zws.Server;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;
import zws.repository.ilink3.qx.program.Tags;
import zws.application.Names;
import zws.util.DeleteFile;
import zws.util.ExecShell;
import zws.util.FileUtil;
//import zws.util.{}//Logwriter;
import zws.application.Properties;
import zws.data.Metadata;

import java.io.*;
import java.util.*;

public class Ilink3QxXferInvoker {

  private static long count = 0;
  private static synchronized long nextCount() { return count++; }

  public static QxXML executeQx(String envRoot, String opType, QxXML instruction) throws Exception {
    Object token = null;

    long unique = nextCount();

    String path = Properties.get(Names.ENV_DIR) + Names.PATH_SEPARATOR + "ptc" + Names.PATH_SEPARATOR + envRoot;
    File envRootPath = (File)env.get(path);
    if (null==envRootPath) {
      envRootPath = new File(path);
      env.put(path, envRootPath);
    }
    QxInstruction export = instruction.toQxProgram();
    if (!"export".equalsIgnoreCase(opType) && !"export-list".equalsIgnoreCase(opType)) {
      return new QxXML("<exception message='Unsupported instruction' instruction='"+export.getName()+"'");
    }

    //+++Check for export list, create file and call export accordingly

    File executable=null;
    File workingDir=null;
    QxXML exported=null;
    String workspace = export.get(Tags.WORKSPACE);
    String workspaceParent = export.get(Tags.WORKSPACE_PARENT);
    String outDirPath = export.get(Tags.OUTPUT_PATH);
    String dependencies = export.get(Tags.DEPENDENCIES);
    String componentName = null;
    
    if (null==outDirPath) outDirPath = Properties.get(Names.TEMP_DIR) + Names.PATH_SEPARATOR + "export-" + unique;
    File outDir = new File (outDirPath);
    if (!outDir.exists()) {
      {}//Logwriter.printOnConsole("creating " + outDir.getAbsolutePath());
      outDir.mkdirs();
    }
    if (null==dependencies) dependencies="all";
    try {      
      if ("export".equalsIgnoreCase(opType)) {
        componentName = export.get(Tags.COMPONENT_NAME);
        executable = new File(getExportEXEPath(envRootPath));
        if (!executable.exists()) throw new FileNotFoundException("executable file does not exist! [" + opType + "] " +executable.getAbsolutePath());
        workingDir = defineWorkingDirectory(opType);
        ExecShell shell = new ExecShell();
  
        File ldb = new File(Properties.get(Names.LDB_PATH_BASE), workspaceParent);
  
        shell.setExecutable(executable.getAbsolutePath());
        shell.setWorkingDirectory(workingDir);
        shell.addCommandLineArgument(envRootPath.getAbsolutePath());
        shell.addCommandLineArgument(workingDir.getAbsolutePath());
        shell.addCommandLineArgument(ldb.getAbsolutePath());
        shell.addCommandLineArgument(componentName);
        shell.addCommandLineArgument(workspace);
        shell.addCommandLineArgument(outDir.getAbsolutePath());
        shell.addCommandLineArgument(dependencies);
        shell.execute();
        //mdimpex-export.bat %env% %wdir% %ldb% %name% %ws% %out% %dep%
        //verify outputfile exists
        File outputFile = new File(outDir, componentName);
        int exitCode = shell.waitFor();
        exported = new QxXML("<exported file='"+outputFile.getAbsolutePath()+"'/>");
      }
      else { //export-list
        executable = new File(getExportListEXEPath(envRootPath));
        if (!executable.exists()) throw new FileNotFoundException("executable file does not exist! [" + opType + "] " +executable.getAbsolutePath());
        workingDir = defineWorkingDirectory(opType);
        String exportListFileName =  getInstructionFileName(workingDir);
        writeFileList(exportListFileName, extractNames(export.getSubInstructions()));
        ExecShell shell = new ExecShell();
  
        File ldb = new File(Properties.get(Names.LDB_PATH_BASE), workspaceParent);
  
        shell.setExecutable(executable.getAbsolutePath());
        shell.setWorkingDirectory(workingDir);
        
        shell.addCommandLineArgument(envRootPath.getAbsolutePath());
        shell.addCommandLineArgument(workingDir.getAbsolutePath());
        shell.addCommandLineArgument(ldb.getAbsolutePath());
        shell.addCommandLineArgument(exportListFileName);
        shell.addCommandLineArgument(workspace);
        shell.addCommandLineArgument(outDir.getAbsolutePath());
        shell.addCommandLineArgument(dependencies);
        shell.execute();
        //mdimpex-export.bat %env% %wdir% %ldb% %name% %ws% %out% %dep%
        //verify outputfile exists
        int exitCode = shell.waitFor();
        File[] files = outDir.listFiles();
        String fl=null;
        for (int idx=0; idx< files.length; idx++ ) {
          if (0==idx) fl = files[idx].getName();
          else fl += ", " + files[idx].getName();
        }
        exported = new QxXML("<exported files='"+fl+"'/>");
        
      }

      if (!Server.debugMode()) {
      //todo: queue directory for deletion and return immediately
      DeleteFile deleter = new DeleteFile();
      deleter.setDeleteIfNotEmpty(true);
      deleter.setFile(workingDir);
      deleter.execute();
      if (Boolean.FALSE == (Boolean)deleter.getResult()) {} {}//Logwriter.printOnConsole("Could not delete working dir: " + workingDir.getAbsolutePath());
      }
      return exported;
    }
    catch(Exception e) { throw e; }
  }

  
  private static Collection extractNames(Collection qxInstructions) {
    Collection names = new Vector();
    String n;
    QxInstruction qx;
    Iterator i = qxInstructions.iterator();
    while (i.hasNext()) {
      qx = (QxInstruction) i.next();
      n = qx.get(Tags.NAME);
      names.add(n);
    }
    return names;
  }
  
  private static void writeFileList(String filename, Collection names) {
    File instructionFile = new File(filename);
    instructionFile.delete();
    try{
      instructionFile.createNewFile();
      FileWriter outFile = new FileWriter(instructionFile);
      Iterator i = names.iterator();
      String n=null;
      while (i.hasNext()) {
        n = (String)i.next();
        outFile.write(n);
        outFile.write(Names.NEW_LINE);
      }
      outFile.close();
    }
    catch (Exception e) { e.printStackTrace(); }
  }
  
  private static String getInstructionFileName(File f) {
    String exportListFileName = f.getAbsolutePath()+Names.PATH_SEPARATOR+"exportList.txt";
    return exportListFileName;
  } //override
  
  private static String getExportEXEPath(File envRoot) {
    return
      envRoot.getAbsolutePath() + Names.PATH_SEPARATOR
      + "bin" + Names.PATH_SEPARATOR
      + "connector" + Names.PATH_SEPARATOR
      + ExportEXEFileName;
  }

  private static String getExportListEXEPath(File envRoot) {
    return
      envRoot.getAbsolutePath() + Names.PATH_SEPARATOR
      + "bin" + Names.PATH_SEPARATOR
      + "connector" + Names.PATH_SEPARATOR
      + ExportListEXEFileName;
  }

  private static File defineWorkingDirectory(String opType) {
    try {
      File workingDir = new File (Properties.get(Names.WORKING_DIR) + Names.PATH_SEPARATOR + opType + Names.PATH_SEPARATOR + "work"+nextCount());
      if (!workingDir.exists()) workingDir.mkdirs();
      FileUtil.deleteContents(workingDir);
      return workingDir;
    }
    catch (Exception ignore) {
       //++ notify of error - maybe out of disk space
      ignore.printStackTrace();
      return null;
    }
  }
  private static Map env = new HashMap();

  //private static String workingDirectoryPath= "/zws/log/work";
  private static String ExportEXEFileName = "mdimpex-export.bat";
  private static String ExportListEXEFileName = "mdimpex-export-list.bat";
  private static long CONNECTION_TIMEOUT = 30;
}