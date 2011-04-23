/**
 *
 */
package zws.repository.ilink3.qx.program;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Apr 8, 2007 6:42:27 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import zws.qx.program.QxInstruction;
//import zws.util.{}//Logwriter;

/**
 * @author arbind
 *
 */
public class TestQxInstruction {

  /**
   * @param args
   */
  public static void main(String[] args) {
  // TODO Auto-generated method stub
    TestQxInstruction t = new TestQxInstruction();
    t.run();
  }

  public void run() {
    QxInstruction qx= new QxProgram();
    QxInstruction ilinkQx= new IlinkQxProgram();
    QxInstruction openRep = new OpenRepository("user1", "psswd");
    QxInstruction openSandbox = new OpenSandbox("ilink-1");
    QxInstruction openWorkspace= new OpenWorkspace("fishing");
    QxInstruction checkoutLatest= new CheckoutLatest("line_roller.prt");
    QxInstruction checkoutLatest1= new CheckoutLatest("line_roller.prt");
    QxInstruction checkoutLatest2= new CheckoutLatest("line_roller.prt");
    QxInstruction checkoutLatest3= new CheckoutLatest("line_roller.prt");
    QxInstruction checkoutLatest4= new CheckoutLatest("line_roller.prt");
    QxInstruction checkoutLatest5= new CheckoutLatest("line_roller.prt");

    QxInstruction exportQx= new QxInstruction("ilink-export-qx");
    QxInstruction export= new QxInstruction("export");
    export.set("name", "line_roller.prt");
    export.set("to-location", "c:\\zws\\data\\export");

    exportQx.add(export);

    ilinkQx.add(openRep);
    openWorkspace.add(checkoutLatest);
    openWorkspace.add(checkoutLatest1);
    openWorkspace.add(checkoutLatest2);
    openWorkspace.add(checkoutLatest3);
    openWorkspace.add(checkoutLatest4);
    openWorkspace.add(checkoutLatest5);
    openRep.add(openSandbox);
    openSandbox.add(openWorkspace);

    qx.add(ilinkQx);
    qx.add(exportQx);

    {}//Logwriter.printOnConsole(""+qx);
  }
}
