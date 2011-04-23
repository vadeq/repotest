/* DesignState - Design Compression Technology
 * @author: eankutse
 * @version: 1.0
 * Created on Nov 16, 2007 5:21:31 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.pen.policy.op.pendata.element.transform.binary;

import zws.qx.QxContext;
import zws.service.chrysalis.ChrysalisClient;
import zws.service.pen.PENDataElement;
import zws.service.pen.TxDataElement;
//import zws.util.{}//Logwriter;
import zws.util.RemotePackage;


public class TranslateProeDesignToIgsOp extends TransformBinaryOpBase {
  
  private static final long serialVersionUID = 1L;

  public void transformBinary(PENDataElement penElement) throws Exception {
    
    String name = getCurrentItem();
    
    //get the current RemoteFile:
    //TODO: for now there is no reason why each element should have more than ONE RemoteFile in the 
    //binary files list at any given point in time. If that becomes necesary, we need to have a way 
    //to tell which of the RemoteFile objects we are looking for.
    int binCount = penElement.getSourceDataElement().getBinaryCollection().size();
    if(binCount == 0) {
      notificationSubject += " "+name;
      notificationMessage="WARNING: "+"SourceDataElement for item " + name + " does not have a RemoteFile object. Cannot perform binary transformation!";
      logAndNotify();
      return;      
    }
    if(binCount > 1) {
      notificationSubject += " "+name;      
      notificationMessage = "WARNING: "+"SourceDataElement for item " + name + " has more than one RemoteFile object. Cannot perform binary transformation!";
      logAndNotify();   
      return;      
    }
    
    RemotePackage rf = (RemotePackage) penElement.getSourceDataElement().getBinaryCollection().get(0);   
    String srcDesignFileName = rf.getName();
    
    if (!srcDesignFileName.endsWith(".asm") && !srcDesignFileName.endsWith(".prt")) {
      notificationSubject += " "+name;      
      notificationMessage = "WARNING: "+"ProE-to-igs transformation is UNexpected for item " + name;
      logAndNotify();  
      return;      
    }
    

    String tgtDesignFileName = lookupLogicalName();
    if (null==tgtDesignFileName || "".equals(tgtDesignFileName.trim())) {
      tgtDesignFileName = srcDesignFileName;   
      if (tgtDesignFileName.endsWith(".prt")) tgtDesignFileName = tgtDesignFileName.replace(".prt", ".igs");
      else if (tgtDesignFileName.endsWith(".asm")) tgtDesignFileName = tgtDesignFileName.replace(".asm", ".isg");         
    }
         
    try {      
      ChrysalisClient client = ChrysalisClient.materializeClient();
      RemotePackage xlation = client.translate(this.getQxCtx(), srcDesignFileName, null, rf, PROE_FORMAT_VALUE, IGES_FORMAT_VALUE, tgtDesignFileName);    
      
      //put the RemoteFile of the translation into the Tx
      penElement.getTxDataElement().addBinaryFile(xlation);
    } catch (Exception e) {
      notificationSubject += " "+srcDesignFileName;
      notificationMessage = "WARNING: "+e.toString();
      logAndNotify(); 
    }
  }

}
