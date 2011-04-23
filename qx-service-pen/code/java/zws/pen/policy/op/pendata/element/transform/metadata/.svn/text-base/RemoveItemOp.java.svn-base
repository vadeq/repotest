/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Apr 27, 2007 9:58:02 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.pen.policy.op.pendata.element.transform.metadata;

import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.recorder.util.RecorderUtil;
import zws.service.pen.TxDataElement;
//impoer zws.util.Logwriter;

import java.util.Collection;
import java.util.Iterator;

public class RemoveItemOp extends PENDataOpBase{

  public void execute() throws Exception{
     String name = getCurrentItem();
     {}//Logwriter.printOnConsole("Item count before purge " + lookupPENDataElement(name).getReferenceCount());
     removeFromSubcomponents(name);
     getPenData().remove(name);
     RecorderUtil.logActivity(getQxCtx(), "remove Item" ,  name);
     {}//Logwriter.printOnConsole("The item " + name + " purged successfully.");
  }
  private void removeFromSubcomponents(String name) throws Exception{
    Collection subComponents= null;
    TxDataElement txDataElement = null;
    Iterator itr = getPenData().getReferenceTableCopy().keySet().iterator();
    while (itr.hasNext()) {
      String itemName = (String) itr.next();
      txDataElement = lookupTxDataElement(itemName);
      subComponents = txDataElement.getSubComponentRefNames();
      if(subComponents.contains(name)) {
        {}//Logwriter.printOnConsole("subComponents  for " + itemName);
        //zws.util.PrintUtil.print(subComponents);
        {}//Logwriter.printOnConsole("removing " + name + " from subcomponents of " + itemName);
        txDataElement.removeSubcomponent(name);
      }
    }
  }
}
