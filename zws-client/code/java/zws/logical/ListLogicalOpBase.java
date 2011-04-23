package zws.logical; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.Collection;
import java.util.Vector;

//executes a boolean operation on all results of all logicals in a list
public abstract class ListLogicalOpBase extends LogicalOpBase implements ListLogicalOp {
  public abstract boolean isTrue() throws Exception;
  public void add(LogicalOp l) { logicals.add(l); }
  public Collection getLogicals() { return logicals; }
  public void setLogicals(Collection c) { logicals=c; }

  private Collection logicals = new Vector();
}