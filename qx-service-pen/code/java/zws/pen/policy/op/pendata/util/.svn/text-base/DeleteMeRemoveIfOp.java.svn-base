package zws.pen.policy.op.pendata.util;
/*
* DesignState - Design Compression Technology
* @author: ptoleti
* @version: 1.0
* Created on Apr 27, 2007 9:58:02 AM
* Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
*/

import zws.pen.policy.op.pendata.PENDataProcessor;
import zws.pen.policy.op.pendata.lang.condition.ConditionOPBase;
import zws.service.pen.TxDataElement;
//import zws.util.{}//Logwriter;

import java.util.Collection;
import java.util.Iterator;

public class DeleteMeRemoveIfOp extends PENDataProcessor{

  public void process() throws Exception{
    {}//Logwriter.printOnConsole(this,"process", getCurrentItem());
     String name = getCurrentItem();
     {}//Logwriter.printOnConsole("checking for " + checkFor);
     ConditionOPBase op = null;
     op = getConditionOp();
     passConfiguration(op);
     op.execute();
     Boolean opResult = (Boolean)op.getResult();
     if (getCheckFor() == opResult.booleanValue()) {
       {}//Logwriter.printOnConsole("Item count before purge " + lookupPENDataElement(name).getReferenceCount());
       if(lookupPENDataElement(name).getReferenceCount() == 1) {
         removeFromSubcomponents(name);
         getCurrentIterator().remove();
         {}//Logwriter.printOnConsole("The item " + name + " purged successfully.");
      }
     }
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

  public void add(ConditionOPBase op) {conditionOp = op;}
  public ConditionOPBase getConditionOp() {return conditionOp;}
  public boolean getCheckFor() {return checkFor;}
  public void setCheckFor(boolean b) {checkFor = b;}

  private boolean checkFor = true;
  private ConditionOPBase conditionOp = null;
}
