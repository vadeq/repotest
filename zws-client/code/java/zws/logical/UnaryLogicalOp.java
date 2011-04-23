package zws.logical; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

//executes a unary logical operation on results of a logical
public interface UnaryLogicalOp extends LogicalOp {
  public void bind(LogicalOp l);
  public void add(LogicalOp l);
  public boolean isTrue() throws Exception;
  public boolean isFalse() throws Exception;
  public LogicalOp getLogical();
  public void setLogical(LogicalOp l);
}