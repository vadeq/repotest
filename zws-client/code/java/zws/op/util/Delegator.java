package zws.op.util;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 18, 2004, 6:27 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.op.Op;
import zws.op.OpBase;

public class Delegator extends OpBase {
  public void execute() throws Exception {
    targetOp.setContext(getContext());
    targetOp.execute();
    setResult(targetOp.getResult());
  }

  public void add(Op op) { setTarget(op); }
  public void setTarget(Op op) { targetOp=op; }
  public Op getTarget() { return targetOp; }

  private Op targetOp;
}
