package zws.logical; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

//executes a logical operation on a boolean returned by its rightLogical
public abstract class UnaryLogicalOpBase extends LogicalOpBase implements UnaryLogicalOp {
  public abstract boolean isTrue() throws Exception;

  public void bind(Object logical) { bind((LogicalOp)logical); }
  public void bind(LogicalOp l) { setLogical(l); }
  public void add(LogicalOp l) { setLogical(l); }
  
  public LogicalOp getLogical() { return logical; }
  public void setLogical(LogicalOp l) { logical = l; }
  
  private LogicalOp logical = null;
}