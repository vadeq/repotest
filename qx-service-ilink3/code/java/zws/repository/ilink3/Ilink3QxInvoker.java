package zws.repository.ilink3;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Mar 23, 2007 11:10:21 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.Server;
import zws.qx.xml.QxXML;
import zws.application.Names;
import zws.application.Properties;
import zws.repository.ilink3.license.IlinkTKLicensePool;
import zws.repository.ilink3.license.IlinkTKLicensePoolSvc;
import zws.util.DeleteFile;
import zws.util.ExecShell;
import zws.util.FileUtil;
//import zws.util.{}//Logwriter;

import java.io.*;
import java.util.*;

public class Ilink3QxInvoker {

  public static QxXML executeQx(String envRoot, String opType, QxXML instruction, boolean acquireLicense ) throws Exception {
    Object token = null;
    String path =   Properties.get(Names.ENV_DIR) + Names.PATH_SEPARATOR + "ptc" + Names.PATH_SEPARATOR + envRoot;
    File envRootPath = (File)env.get(path);
    if (null==envRootPath) {
      envRootPath = new File(path);
      env.put(path, envRootPath);
    }

    IlinkTKLicensePool tkPool = IlinkTKLicensePoolSvc.getLicensePool(envRoot);
    if (acquireLicense && null!=tkPool) token = tkPool.takeToken(60*1000);
    try {
      getNewCount();
      File executable = new File(getEXEPath(envRootPath));
      if (!executable.exists()) throw new FileNotFoundException("executable file does not exist! [" + opType + "] " +executable.getAbsolutePath());
      File workingDir = defineWorkingDirectory(opType);
      String instructionFilePath = getInstructionFileName(workingDir);
      String responseFilePath = getOutputFileName(workingDir);
      createInstructionFile(instructionFilePath, instruction);
      ExecShell shell = new ExecShell();
      shell.setExecutable(executable.getAbsolutePath());
      shell.setWorkingDirectory(workingDir);
      shell.addCommandLineArgument(envRootPath.getAbsolutePath());
      shell.addCommandLineArgument(workingDir.getAbsolutePath());
      //setArguments(shell);
      shell.execute();

      int exitCode = shell.waitFor();
      QxXML response = readResponseFile(responseFilePath);

      if (!Server.debugMode()) {
        //todo: queue directory for deletion and return immediately
        DeleteFile deleter = new DeleteFile();
        deleter.setDeleteIfNotEmpty(true);
        deleter.setFile(workingDir);
        deleter.execute();
        if (Boolean.FALSE == (Boolean)deleter.getResult()) {
            {}//Logwriter.printOnConsole("Could not delete working dir: " + workingDir.getAbsolutePath());
        }
      }
      return response;
    }
    catch(Exception e) { throw e; }
    finally { if (null!=token) tkPool.releaseToken(token); } }

  protected static String getEXEPath(File envRoot) {
    return
      envRoot.getAbsolutePath() + Names.PATH_SEPARATOR
      + "bin" + Names.PATH_SEPARATOR
      + "connector" + Names.PATH_SEPARATOR
      + EXEFileName;
  }

  protected static void createInstructionFile(String filename, QxXML instruction) throws Exception {
    File f = new File(filename);
    if (f.exists()) f.delete();
    try{
      f.createNewFile();
      FileWriter instructionFile = new FileWriter(f);
      instructionFile.write(instruction.toString());
      instructionFile.close();
    }
    catch (Exception e) { e.printStackTrace(); throw e;}
  }

  protected static QxXML readResponseFile(String filename) throws Exception {
    QxXML response = new QxXML();
    response.loadFile(filename);
    return response;
  }

  private static File defineWorkingDirectory(String opType) {
    try {
      File workingDir = new File (workingDirectoryPath + Names.PATH_SEPARATOR + opType + Names.PATH_SEPARATOR + "work"+getNewCount());
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

  private static synchronized int getNewCount() { return ++count; }
  protected static String getInstructionFileName(File f) { return f.getAbsolutePath()+Names.PATH_SEPARATOR+instructionFileName; }
  protected static String getOutputFileName(File f) { return f.getAbsolutePath()+Names.PATH_SEPARATOR+"response.xml"; }

  private static int count=0;
  private static Map env = new HashMap();

  private static String workingDirectoryPath= "/zws/log/work";
  private static String instructionFileName = "instruction.xml";
  private static String responseFileName = "response.xml";
  private static String EXEFileName = "QxService.bat";
  private static long CONNECTION_TIMEOUT = 30;
}
