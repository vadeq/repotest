/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Mar 23, 2007 11:10:21 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.qx.service;
import zws.qx.Qx;
import zws.qx.QxContext;
import zws.qx.xml.QxXML;
import zws.repository.ilink3.Ilink3Constants;
import zws.repository.ilink3.Ilink3QxFileDepotXferInvoker;
import zws.repository.ilink3.Ilink3QxInvoker;
import zws.repository.ilink3.Ilink3QxXferInvoker;
//import zws.util.{}//Logwriter;

public class Ilink3QxService implements Qx {

  public QxXML executeQx(QxContext ctx, QxXML instruction) {
    QxXML xml = emptyXML;
    try {
      String envRoot = lookupEnvRoot(ctx);
      {}//Logwriter.printOnConsole("Invoking QxService for: " + envRoot);
      String opType = ctx.get(ctx.OP_TYPE);
      if ("export".equalsIgnoreCase(opType) || "export-list".equalsIgnoreCase(opType)) {
        {}//Logwriter.printOnConsole("exporting...");
        xml = Ilink3QxXferInvoker.executeQx(envRoot, opType, instruction);
      }
      else if ("store".equalsIgnoreCase(opType)) {
        {}//Logwriter.printOnConsole("storing...");
        xml = Ilink3QxFileDepotXferInvoker.executeQx(instruction);
      }      
      else {
        {}//Logwriter.printOnConsole("invoking...");
        boolean acquireLicense = true;
        xml = Ilink3QxInvoker.executeQx(envRoot, opType, instruction, acquireLicense);
      }
    }
    catch(Exception e) {
      xml = new QxXML("<exception message='"+e.getMessage()+"'/>");
    }
    return xml;
  }
  
  private String lookupEnvRoot(QxContext ctx) {
    return ctx.get(Ilink3Constants.ENV_ROOT);
  }
  private static final QxXML emptyXML = new QxXML("<empty/>");
}
