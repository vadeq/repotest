package zws.xml.op.create; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0s
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.xml.op.XMLOp;
import zws.xml.op.XMLOpException;

abstract public class CreateFunctor extends XMLOp {
  public Object process() throws XMLOpException { return create();}

  public abstract Object create() throws CreateException;
}