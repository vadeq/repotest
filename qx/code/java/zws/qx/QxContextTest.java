/**
 *
 */
package zws.qx;/*
 DesignState - Design Compression Technology
 @author: ptoleti
 @version: 1.0
 Created on Apr 18, 2007 7:52:56 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
//impoer zws.util.Logwriter;
/**
 * @author ptoleti
 *
 */
public class QxContextTest {


  public static void main(String[] args) {
    QxContextTest t = new QxContextTest();
    t.run();
  }

  public void run() {
    QxContext parent = new QxContext();
    QxContext child = new QxContext();

    parent.set("name", "parentName");
    parent.set("common", "NY");
    parent.set("pre-verwrite", "Parent Pre-Overwrite Value");
    parent.set("overwrite", "Parent Overwrite Value");
    child.set("name", "childName");
    child.set("pre-overwrite", "Child Pre-Overwrite Value");

    {}//Logwriter.printOnConsole("Before");
    {}//Logwriter.printOnConsole("Parent Name " + parent.get("name"));
    {}//Logwriter.printOnConsole("Parent Common " + parent.get("common"));
    {}//Logwriter.printOnConsole("Parent Pre-Overwrite " + parent.get("pre-overwrite"));
    {}//Logwriter.printOnConsole("Parent Overwrite " + parent.get("overwrite"));
    {}//Logwriter.printOnConsole("Child Name " + child.get("name"));
    {}//Logwriter.printOnConsole("Child Common " + child.get("common"));
    {}//Logwriter.printOnConsole("Child Pre-Overwrite " + child.get("pre-overwrite"));
    {}//Logwriter.printOnConsole("Child Overwrite" + child.get("overwrite"));

    {}//Logwriter.printOnConsole("Parent: " + parent);
    {}//Logwriter.printOnConsole("Child " + child);


    child.configureParent(parent);
    //child.set("overwrite", "Child Overwrite Value");

    {}//Logwriter.printOnConsole("After");
    {}//Logwriter.printOnConsole("After");
    {}//Logwriter.printOnConsole("After");
    {}//Logwriter.printOnConsole("After");
    {}//Logwriter.printOnConsole("Parent Name " + parent.get("name"));
    {}//Logwriter.printOnConsole("Parent Common " + parent.get("common"));
    {}//Logwriter.printOnConsole("Parent Pre-Overwrite " + parent.get("pre-overwrite"));
    {}//Logwriter.printOnConsole("Parent Overwrite " + parent.get("overwrite"));
    {}//Logwriter.printOnConsole("Child Name " + child.get("name"));
    {}//Logwriter.printOnConsole("Child Common " + child.get("common"));
    {}//Logwriter.printOnConsole("Child Pre-Overwrite " + child.get("pre-overwrite"));
    {}//Logwriter.printOnConsole("Child Overwrite" + child.get("overwrite"));

    {}//Logwriter.printOnConsole("Parent: " + parent);
    {}//Logwriter.printOnConsole("Child " + child);
  }
}
