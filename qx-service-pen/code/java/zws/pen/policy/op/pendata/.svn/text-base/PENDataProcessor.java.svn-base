package zws.pen.policy.op.pendata;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/

import java.util.Iterator;


public abstract class PENDataProcessor extends PENDataOpBase {

  protected abstract void process () throws Exception;

  public void execute() throws Exception {
    String name = null;
    Iterator itr = getPenData().materializeIterator();
    setCurrentIterator(itr);
    while (itr.hasNext()) {
      name = (String) itr.next();
      {}//Logwriter.printOnConsole(this,"execute",name);
      setCurrentItem(name);
      process();
    }
  }

}
