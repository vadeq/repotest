package zws.logical; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.Iterator;

public class Or extends ListLogicalOpBase {
  public boolean isTrue() throws Exception{
    if (2>getLogicals().size()) throw new LogicException("Tried to OR "+getLogicals().size()+"elements together. Need at least 2.");
    boolean orMatched;
    LogicalOp logical;
    Iterator i = getLogicals().iterator();
    logical = (LogicalOp)i.next();
    orMatched = logical.isTrue();
    while (!orMatched && i.hasNext()) orMatched = ((LogicalOp)i.next()).isTrue();
    return orMatched;
  }
}