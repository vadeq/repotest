package zws.logical; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.Iterator;

public class And extends ListLogicalOpBase {
  public boolean isTrue() throws Exception{
    if (2>getLogicals().size()) throw new LogicException("Tried to AND "+getLogicals().size()+"elements together. Need at least 2.");
    boolean keepAnding;
    LogicalOp logical;
    Iterator i = getLogicals().iterator();
    logical = (LogicalOp)i.next();
    keepAnding = logical.isTrue();
    while (keepAnding && i.hasNext()) keepAnding = ((LogicalOp)i.next()).isTrue();
    return keepAnding;
  }
}