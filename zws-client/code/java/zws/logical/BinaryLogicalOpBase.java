package zws.logical; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public abstract class BinaryLogicalOpBase extends LogicalOpBase implements BinaryLogicalOp {
  public abstract boolean isTrue() throws Exception;

  public void bind(LogicalOp leftLogical, LogicalOp rightLogical) { setLeftLogical(leftLogical);  setRightLogical(rightLogical); }
  public void bind(Object leftLogical) { setLeftLogical((LogicalOp)leftLogical); }
  public void add(LogicalOp l) { 
    if (null==getLeftLogical()) setLeftLogical(l);
    else setRightLogical(l);
  }
  
  public LogicalOp getLeftLogical() { return leftLogical; }
  public void setLeftLogical(LogicalOp l) { leftLogical = l; }
  public LogicalOp getRightLogical() { return rightLogical; }
  public void setRightLogical(LogicalOp l ) { rightLogical = l; }
  
  private LogicalOp leftLogical = null, rightLogical=null;
}