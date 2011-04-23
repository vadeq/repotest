/**
 * 
 */
package zws.repository.R8.util;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Jul 12, 2007 11:20:57 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import zws.op.OpBase;
import zws.repository.R8.R8RepositoryBase;
import zws.util.StringMapper;

/**
 * @author arbind
 *
 */
public class ZWS2PDMLinkAttributeMapper extends OpBase implements StringMapper {

  public String getValue() { return value; }
  public void setValue(String s) { value = s; }
  
  public String getMappedValue() { return (String)getResult(); }
  
  public void execute() {
    String mappedValue = null;
    if (null==value) return;
    mappedValue = (String) R8RepositoryBase.fromZwsToILinkMap.get(getValue());
    setResult(mappedValue);
  }
  private String value=null;
}
