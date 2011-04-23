package zws.logical; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.Collection;

//executes a boolean operation on all results of all logicals in a list
public interface ListLogicalOp extends LogicalOp {
  public boolean isTrue() throws Exception;
  public boolean isFalse() throws Exception;
  public void add(LogicalOp l);
  public Collection getLogicals();
  public void setLogicals(Collection c);
}