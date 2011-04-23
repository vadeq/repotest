package zws.op.queue;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 26, 2005, 1:23 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.UnsupportedOperation;
import zws.op.OpBase;

public abstract class QueueOp extends OpBase {
  public void execute() { throw new UnsupportedOperation("QueueOp: implement processItem(object) instead of execute()"); }
  public abstract void processNext(Object nextQueueItem) throws Exception;
}
