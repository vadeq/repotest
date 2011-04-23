package zws.qx.program;
/*

 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Apr 8, 2007 6:42:27 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
//import zws.util.{}//Logwriter;

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
    QxInstruction root = new QxInstruction();
    root.setName("root");
    root.set("parm", "val");
    root.set("weird", "val & > < ' \" trump");
    root.set("parm3", "val2");
    {}//Logwriter.printOnConsole(""+root);

    QxInstruction sub1= new QxInstruction();
    sub1.setName("sub1");
    sub1.set("subparm", "val");
    sub1.set("subweird", "val & > < ' \" trump");
    root.add(sub1);
    {}//Logwriter.printOnConsole(""+root);

    QxInstruction sub2= new QxInstruction();
    sub2.setName("sub2");
    sub2.set("subparm", "val");
    sub2.set("subweird", "val & > < ' \" trump");
    root.add(sub2);
    {}//Logwriter.printOnConsole(""+root);


    QxInstruction sub11= new QxInstruction();
    sub11.setName("sub11");
    sub11.set("subparm", "val");
    sub11.set("subweird", "val & > < ' \" trump");
    sub1.add(sub11);
    {}//Logwriter.printOnConsole(""+root);
  }
}
