package zws.logical; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class Not extends UnaryLogicalOpBase {
  public boolean isTrue() throws Exception { return getLogical().isFalse(); }
}