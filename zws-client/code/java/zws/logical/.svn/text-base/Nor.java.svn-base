package zws.logical; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.Iterator;

public class Nor extends ListLogicalOpBase {
  public boolean isTrue() throws Exception{
    if (2>getLogicals().size()) throw new LogicException("Tried to NOR "+getLogicals().size()+"elements together. Need at least 2.");
    boolean norMatched;
    LogicalOp logical;
    Iterator i = getLogicals().iterator();
    logical = (LogicalOp)i.next();
    norMatched = logical.isFalse();
    while (!norMatched && i.hasNext()) norMatched = ((LogicalOp)i.next()).isFalse();
    return !norMatched;
  }
}