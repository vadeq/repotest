/**
 * 
 */
package zws.util;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Jul 12, 2007 11:17:40 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import zws.op.Op;

/**
 * @author arbind
 *
 */
public interface StringMapper extends Op {
  public void setValue(String s);
  public String getMappedValue();
}
