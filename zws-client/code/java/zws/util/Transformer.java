package zws.util;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

//Transformer first becomes an adapter to an object.
//Transformation can then be completed whenever desired.
public interface Transformer extends Adapter {
  public void adapt(Object o) throws Exception;
  public Object transform() throws Exception;
}
