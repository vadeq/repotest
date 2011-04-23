/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Jan 23, 2008 11:12:38 AM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.service.chrysalis.test;

import zws.qx.QxContext;
import zws.service.chrysalis.ChrysalisClient;
import zws.service.file.depot.FileDepotClient;
import zws.util.RemotePackage;

import java.io.File;

public class TestChrysalis {
  static final String PROE_FORMAT_VALUE = "ProE";
  static final String IGES_FORMAT_VALUE = "IGES";
  static final String PDF_FORMAT_VALUE = "PDF";
  static final String STEP_FORMAT_VALUE = "STEP";
  
  /**
   * Stores a specified file (drw, asm, prt) into file depot
   * Calls chrysalis to translate the file and gets back a
   * remote handle to the stored translated file. 
   */
  public static void main(String[] args) {
    if (2 > args.length) {
      usage();
      return;
    }
    
    {} //System.out.println("TestChrysalis " + args[0] + " " + args[1]);
    {} //System.out.println();
    FileDepotClient client;
    try {
      client = FileDepotClient.materializeClient(args[0].trim(), "C:\\temp\\file-depot");

      File localFile= new File(args[1].trim());
      RemotePackage rFile = client.storeFile(localFile);
      {} //System.out.println("Stored: " + rFile.getUrl()); 
     
      RemotePackage xlation = requestTranslation(rFile);
      {} //System.out.println("Translation results: " + xlation.toString());
      
    } catch (Exception e) {
      e.printStackTrace();
    }       
  }

    
  private static RemotePackage requestTranslation(RemotePackage rf) throws Exception {
   String srcDesignFileName = rf.getName();
    
    if (!srcDesignFileName.endsWith(".asm") && !srcDesignFileName.endsWith(".prt") && !srcDesignFileName.endsWith(".drw")) {      
      throw new Exception("Expecting .asm or .prt or .drw file to translate!");      
    }
   
    String srcDesignFormat = null;
    String tgtDesignFormat = null;
    String srcGenericDesignFileName = null;
    String tgtDesignFileName = srcDesignFileName;   
    if (tgtDesignFileName.endsWith(".prt")){
      tgtDesignFileName = tgtDesignFileName.replace(".prt", ".stp");
      srcDesignFormat = PROE_FORMAT_VALUE;
      tgtDesignFormat = STEP_FORMAT_VALUE;
    }
    else if (tgtDesignFileName.endsWith(".asm")){
      tgtDesignFileName = tgtDesignFileName.replace(".asm", ".stp");
      srcDesignFormat = PROE_FORMAT_VALUE;
      tgtDesignFormat = STEP_FORMAT_VALUE;     
    }
    else if (tgtDesignFileName.endsWith(".drw")) {
      tgtDesignFileName = tgtDesignFileName.replace(".drw", ".pdf");
      srcDesignFormat = PROE_FORMAT_VALUE;
      tgtDesignFormat = PDF_FORMAT_VALUE;     
    }

    RemotePackage xlation = null;
    ChrysalisClient client = ChrysalisClient.materializeClient(); 
    QxContext emptyCtx = new QxContext();    
    xlation = client.translate(emptyCtx, srcDesignFileName, srcGenericDesignFileName, rf, srcDesignFormat, tgtDesignFormat, tgtDesignFileName);
    return xlation;
  }
    
    
    
  private static void usage() {
    {} //System.out.println("Usage:");
    {} //System.out.println(" TestChrysalis <file-depot-host-used-by-Chrysalis-service> <file-to-translate>");
    {} //System.out.println(" Example: TestChrysalis vm-file-depot c:\\temp\\mr3524-0102.prt");
  }
  
}
