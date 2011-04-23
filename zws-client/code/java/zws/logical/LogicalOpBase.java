package zws.logical; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.OpBase;

public abstract class LogicalOpBase extends OpBase implements LogicalOp {
  public abstract boolean isTrue() throws Exception;
  public void execute() throws Exception { initialize(); setResult(new Boolean(isTrue())); }
  public boolean isFalse() throws Exception { 
    initialize();
    boolean isFalse = !isTrue();
    setResult(new Boolean(isFalse));
    return isFalse;
  } 
}