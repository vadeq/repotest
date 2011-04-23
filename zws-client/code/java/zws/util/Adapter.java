package zws.util;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.Serializable;

//An adapter encapsulates an object and presents different access to the data.
public interface Adapter extends Serializable {
  public void adapt(Object o) throws Exception;
}
