package com.zws.functor;

import com.zws.application.Config;
import com.zws.functor.util.ExecShell;

import java.io.File;


public class PDFGenerator extends Functor {
  public PDFGenerator() {}
  public void execute() throws Exception {
    ExecShell shell = new ExecShell();
    File f = new File(Config.getProperty(Config.EXE_PDF_GENERATOR));
    try {
      if (!f.exists()) throw new Exception("executable file does not exist! " + Config.getProperty(Config.EXE_PDF_GENERATOR));
      String baseName = objectName.trim().substring(0,objectName.lastIndexOf("."));
      shell.setExecutable(f.getAbsolutePath());
      shell.setWorkingDirectory(f.getParent());
      shell.addCommandLineArgument(baseName);
      shell.addCommandLineArgument(outputFileName);
      shell.addCommandLineArgument(username);
      shell.addCommandLineArgument(password);
      shell.addCommandLineArgument(proComm);
      shell.addCommandLineArgument(proiTkEnv);
      shell.execute();
      exitCode = shell.waitFor();
      setResult(new Integer(getExitCode()));
    }
    catch (Exception e) {e.printStackTrace(); }
   }

   public int getExitCode() { return exitCode; }

   public void setObjectName(String s){ objectName = s; }
   public void setOutputFileName(String outFileName) { outputFileName = outFileName; }
   public void setUsername(String  f) { username = f; }
   public void setPassword(String  f) { password = f; }
   public void setProCommMessageExecutable(String  f) { proComm = f; }
   public void setEXEToolkitEnv(String  f) { proiTkEnv = f; }

   private String objectName = null;
   private String outputFileName = null;
   private String username, password, proiTkEnv, proComm;
   private int exitCode = -999;
}
