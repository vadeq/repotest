package zws.logical; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

//executes a boolean operation on results of leftLogical and rightLogical operators
public interface BinaryLogicalOp extends LogicalOp {
  public void bind(LogicalOp leftLogical, LogicalOp rightLogical);
  public void add(LogicalOp l);
  public boolean isTrue() throws Exception;
  public boolean isFalse() throws Exception;
  public LogicalOp getLeftLogical();
  public void setLeftLogical(LogicalOp l);
  public LogicalOp getRightLogical();
  public void setRightLogical(LogicalOp l);
}