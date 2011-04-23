package zws.repository.teamcenter;
 
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;

import zws.qx.service.TC10QxInstructionFileService;
import zws.qx.xml.QxXML;
import zws.util.FileUtil;
import zws.application.Loader;
import zws.application.Configurator;


public class TC10Main {

  private TC10Main() {};
  private static TC10Main materializeService() { return new TC10Main(); }
  
  public static void main(String[] args) {
    if (1!= args.length) showUsage();
    String workingDirectoryPath = args[0];
    File wd = new File(workingDirectoryPath);

    String ctxFileName= "context.xml";
    String instructionFileName= "instruction.xml";
    String resultFileName = "result.xml";
    String bootstrapConfigFileName= "tc-10-bootstrap-config.xml";

    File ctxFile = new File(wd, ctxFileName);
    File instructionFile =new File(wd, instructionFileName);
    File resultFile = new File(wd, resultFileName);
    
    if(!ctxFile.exists()) { showFileNotFound(ctxFile); }
    if(!instructionFile.exists()) { showFileNotFound(instructionFile);}
    if(resultFile.exists()) { showFileAlreadyFound(resultFile);  }

    try { loadConfiguration(bootstrapConfigFileName); }
    catch(Exception e) {
      {} //System.out.println("Configuration Error!");
      System.exit(1);
    }   
    try { 
      QxXML respXML = TC10QxInstructionFileService.runQx(ctxFile, instructionFile, resultFile);
      if (null==respXML) respXML = new QxXML("<null/>");
      try { FileUtil.save(respXML.toString(),resultFile, true); }
      catch (Exception e) { showFileError(resultFile, e); } 
      System.exit(0);
    }
    catch (Exception e) {
      try {
        File except = new File("exception.err");
        {} //System.out.println("########################################"+except.getAbsolutePath() + "#################################################");
        OutputStream errStream = new FileOutputStream(except);
        PrintStream err = new PrintStream(errStream);
        e.printStackTrace(err);
      }
      catch(Exception x) {
        e.printStackTrace();
        x.printStackTrace();
      }
      e.printStackTrace();
      System.exit(1);      
    }
  }

  private static void showFileNotFound(File notThere) {
    {} //System.out.println("File Not Found: "+notThere.getAbsolutePath());
    System.exit(1);
  }
  
  private static void showFileAlreadyFound(File alreadyThere) {
    {} //System.out.println("File already there: " + alreadyThere.getAbsolutePath());
    System.exit(1);
  }

  private static void showFileError(File badFile, Exception e) {
    {} //System.out.println("Error in file: " + badFile.getAbsolutePath());
    {} //System.out.println("Error in File: " + e.getMessage());
    System.exit(1);
  }
  
  private static void showUsage() {
    {} //System.out.println("zws.qx.QxInstructionFileService context-file-path instruction-file-path result-file-path bootstrap-config-file-path");
    System.exit(1);
  }

  public static void loadConfiguration (String bootstrapConfigFileName) throws Exception {
    Class c= (new Configurator()).getClass();
    {} //System.out.println("Looking for bootstrap.zws in classpath");
    URL tagmap = c.getResource("/bootstrap.zws");
    if (null==tagmap) { 
      {} //System.out.println("ERROR! bootstrap.zws not found in classpath");
      return;
    }
    {} //System.out.println("Looking for "+bootstrapConfigFileName+" in classpath");
    URL config = c.getResource("/"+bootstrapConfigFileName);
    if (null==config) {
      {} //System.out.println(bootstrapConfigFileName+" not found in classpath");
      return;
    }
    try { 
      Loader.load(new File(config.getPath()), new File(tagmap.getPath())); 
      {} //System.out.println("Configuration loaded.");
      {} //System.out.println("_______________________________________________________________________________________");
      {} //System.out.println("CONFIGURATION LOADED OK.");
    }
    catch (Exception x) {
     {} //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
     Throwable t = x;
     while (null!=t) {
       {} //System.out.println( t.getClass().getName() + ": " + t.getMessage());         
       t = t.getCause();
     }
     {} //System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
     {} //System.out.println("ERROR");
    }
  }
}
