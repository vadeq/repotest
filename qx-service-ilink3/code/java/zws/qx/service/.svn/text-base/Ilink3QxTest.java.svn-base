package zws.qx.service;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Mar 24, 2007 1:45:14 AM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.qx.xml.QxXML;
import zws.qx.QxContext;
//import zws.util.{}//Logwriter;

public class Ilink3QxTest {
  private Ilink3QxTest() {}

  public static void main(String[] args) {
    Ilink3QxTest test = new Ilink3QxTest();
    test.run();
  }

  private void run(){
    try { find(); } catch (Exception e) { {}//Logwriter.printOnConsole(e.getMessage()); }
    try { listFolders(); } catch (Exception ex) { 
      {}//Logwriter.printOnConsole(e.getMessage()); 
      }
    }
  }

  private static void find() throws Exception {
    String s =
      "<?xml version='1.0' encoding='latin1'?>" + nl +
      "<qx output-encoding='LATIN1'>" + nl +
      " <ilink-qx>"+ nl +
      " <open-repository username='designstate' password='zero0'>" + nl +
      "  <find name='1490_shield.prt' branch='main' revision='A' version='0'/>" + nl +
      " </open-repository>" + nl +
      " </ilink-qx>" + nl  +
      "</qx>" + nl ;
    QxXML instruction= new QxXML(s);
    QxContext ctx = new QxContext();
    ctx.set(ctx.OP_TYPE, "find");
    ctx.set(ctx.REPOSITORY_NAME, "ilink-1");
    // Ilink3QxService svc = new Ilink3QxService();
    // svc.executeQx(ctx, instruction);
  }

  private static void listFolders() throws Exception {
    String s =
      "<?xml version='1.0' encoding='latin1'?>" + nl +
      "<qx output-encoding='LATIN1'>" + nl +
      " <ilink-qx>"+ nl +
      " <open-repository username='designstate' password='zero0'>" + nl +
      "  <get-folder-tree/>" + nl +
      " </open-repository>" + nl +
      " </ilink-qx>" + nl  +
      "</qx>" + nl ;
    QxXML instruction= new QxXML(s);
    QxContext ctx = new QxContext();
    ctx.set(ctx.OP_TYPE, "folder-tree");
    ctx.set(ctx.REPOSITORY_NAME, "ilink-1");
    // Ilink3QxService svc = new Ilink3QxService();
    // svc.executeQx(ctx, instruction);
  }


  static String nl = zws.application.Names.NEW_LINE;
}
