package zws.qx.service;


import zws.qx.Qx;
import zws.qx.QxContext;
import zws.qx.xml.QxXML;
import zws.util.FileUtil;
//import zws.util.{}//Logwriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class TC10QxInstructionFileService implements Qx {

  private TC10QxInstructionFileService() {};
  private static TC10QxInstructionFileService materializeService() { return new TC10QxInstructionFileService(); }
  
  public static QxXML runQx(File ctxFile, File instructionFile, File resultFile) throws Exception {
    String ctxString = null;
    String instructionString = null;    
    ctxString = FileUtil.read(ctxFile);
    instructionString = FileUtil.read(instructionFile);
    if (null==ctxString) ctxString=""; 
    if (null==instructionString) instructionString="";
    
    QxContext qxCtx = new QxContext(ctxString);
    QxXML qxXML = new QxXML(instructionString);
    
    TC10QxInstructionFileService service = TC10QxInstructionFileService.materializeService();
    /*
    {} //System.out.println("----context-----------------");
    {} //System.out.println(qxCtx.toString());
    {} //System.out.println("----instruction-------------");
    {} //System.out.println(qxXML.toString());
    {} //System.out.println("----------------------------");
    */
    QxXML respXML = service.executeQx(qxCtx, qxXML);
    return  respXML;
  }

  private static void showFileNotFound(File notThere) {
    {} //System.out.println("File Not Found: "+notThere.getAbsolutePath());
  }
  
  private static void showFileAlreadyFound(File alreadyThere) {
    {} //System.out.println("File already there: " + alreadyThere.getAbsolutePath());
  }

  private static void showFileError(File badFile, Exception e) {
    {} //System.out.println("Error in file: " + badFile.getAbsolutePath());
    {} //System.out.println("Error in File: " + e.getMessage());
  }
  
  private static void showUsage() {
    {} //System.out.println("zws.qx.QxInstructionFileService context-file-path instruction-file-path result-file-path bootstrap-config-file-path");
  }
  
  
  //LAUNCHED
  //read the instruction
  //read the context
  //create QxXML & QxContect
  //call executeQx
  
  public QxXML executeQx(QxContext ctx, QxXML dataInstruction){
    QxXML response = null;
    try {
      //String fqcn = ctx.lookupJavaFQCN();
      //Class c =  Class.forName(fqcn);
      //Qx qx = (Qx) c.newInstance();
      Qx qx = new TC10QxStandaloneService();
      response = qx.executeQx(ctx, dataInstruction);
      {}//Logwriter.printOnConsole("RESPONSE " + response);
    } catch(RuntimeException ex ){
      {}//Logwriter.printOnConsole("Runtime Exception! "+ex.getMessage());
      response = new QxXML("<exception message='"+ex.getMessage()+"' />");
      try {
        File except = new File("exception.err");
        {} //System.out.println("########################################"+except.getAbsolutePath() + "#################################################");
        OutputStream errStream = new FileOutputStream(except);
        PrintStream err = new PrintStream(errStream);
        ex.printStackTrace(err);
      }
      catch(Exception x) {
        ex.printStackTrace();
        x.printStackTrace();
      }
      ex.printStackTrace();
    }
    catch(Exception ex ){
      {}//Logwriter.printOnConsole("Exception "+ex.getMessage());
      response = new QxXML("<exception message='"+ex.getMessage()+"' />");
      try {
        File except = new File("exception.err");
        {} //System.out.println("########################################"+except.getAbsolutePath() + "#################################################");
        OutputStream errStream = new FileOutputStream(except);
        PrintStream err = new PrintStream(errStream);
        ex.printStackTrace(err);
      }
      catch(Exception x) {
        ex.printStackTrace();
        x.printStackTrace();
      }
      ex.printStackTrace();
    }
    {}//Logwriter.printOnConsole("RESPONSE " + response);
    return response;
  }
}
