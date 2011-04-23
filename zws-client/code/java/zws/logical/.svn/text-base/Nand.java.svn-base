package zws.logical; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.Iterator;

public class Nand extends ListLogicalOpBase {
  public boolean isTrue() throws Exception{
    if (2>getLogicals().size()) throw new LogicException("Tried to NAND "+getLogicals().size()+"elements together. Need at least 2.");
    boolean keepNanding;
    LogicalOp logical;
    Iterator i = getLogicals().iterator();
    logical = (LogicalOp)i.next();
    keepNanding = logical.isFalse();
    while (keepNanding && i.hasNext()) keepNanding = ((LogicalOp)i.next()).isFalse();
    return !keepNanding;
  }
}