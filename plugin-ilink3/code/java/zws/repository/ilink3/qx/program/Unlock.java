/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 15, 2004, 7:44 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

package zws.repository.ilink3.qx.program;

import zws.origin.IntralinkOrigin;
import zws.qx.program.QxInstruction;

import java.util.Collection;
import java.util.Iterator;

/**
 * The Class ReportBill.
 */
public class Unlock extends QxInstruction {

  public Unlock(Collection origins) {
    setName("unlock");
    Iterator i = origins.iterator();
    IntralinkOrigin ilinkOrigin  = null;
    while (i.hasNext()) {
      ilinkOrigin  = (IntralinkOrigin) i.next();
      addItemToLock(ilinkOrigin);
    }
  }

  private void addItemToLock(  IntralinkOrigin ilinkOrigin) {
    QxInstruction item = new QxInstruction("metadata");
    item.set("name", ilinkOrigin.getName());
    item.set("branch", ilinkOrigin.getBranch());
    item.set("table", "true");
    add(item);
  }
   
}