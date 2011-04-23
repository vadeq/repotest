/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Dec 6, 2007 4:10:19 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.service.chrysalis.test;

import zws.service.chrysalis.ChrysalisClient;
import zws.service.file.depot.FileDepotClient;
import zws.util.FileUtil;
import zws.util.RemotePackage;

import java.io.File;
import java.net.URL;
import java.util.Calendar;

public class ProETracking {
  
  public static void main(String[] args) {
    ProETracking t = new ProETracking();
    try { t.run(); }
    catch (Exception e) { 
      e.printStackTrace();
    }
  }
  
  private void run () throws Exception {
    
    URL url= new URL("http://zero-big/FileDepot/Repository/zero-big-1196970910859-2/77-3703-030346082.zip");
   
    RemotePackage drawing = FileDepotClient.materializeRemotePackage(url, "77-3703-0303.drw");
    String srcDesignFileName = drawing.getName();    
    
    String tgtDesignFileName = srcDesignFileName;
    tgtDesignFileName = tgtDesignFileName.toLowerCase();    
    if (tgtDesignFileName.endsWith(".drw")) tgtDesignFileName = tgtDesignFileName.replace(".drw", ".pdf");
    else throw new Exception("ProE-to-pdf transformation is UNexpected for item " + drawing.getName());

    for (int i =0; i < 10; i++) { 
     String proeHangs = "C:\\temp\\proe\\run-"+Calendar.getInstance().getTimeInMillis()+ "-"+ i ;
     FileUtil.delete(proeHangs);
     ChrysalisClient client = ChrysalisClient.materializeClient();
     {} //System.out.println("TODO: fix client.translate() call in ProeTracking - curently commented out!");     
     //RemotePackage xlation = client.translate(srcDesignFileName, drawing, PROE_FORMAT_VALUE, PDF_FORMAT_VALUE, tgtDesignFileName);    

     File f = new File(proeHangs);
     f.mkdir();
     FileDepotClient fdclient = FileDepotClient.materializeClient("vm-file-depot", "C:\\temp\\file-depot");     
     //fdclient.retrieve(xlation, new File(proeHangs));
    }

  }
  
  static final String PROE_FORMAT_VALUE = "ProE";
  static final String IGES_FORMAT_VALUE = "IGES";
  static final String PDF_FORMAT_VALUE = "PDF";
  static final String STEP_FORMAT_VALUE = "STEP";

}
