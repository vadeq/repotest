package zws.mapping.attribute;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 3, 2004, 11:48 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.InvalidAttributeMapping;

public class RenameAttribute extends MetadataAttributeMappingBase {

  protected boolean isMapped() {
/*
  if (getSource().hasFieldName(getFieldName())) {
    {} //System.out.println("Found " + getFieldName());
  }
  else {
    {} //System.out.println("Source does not have field " + getFieldName());
    {} //System.out.println(getSource());
  }
*/
      return getSource().hasFieldName(getFieldName()); 
  }
  
  protected  void mapSourceToTarget() throws InvalidAttributeMapping {
    String value = getSource().get(getFieldName());
    getTarget().set(getNewFieldName(), value);
    {} //System.out.println("mapped " + getFieldName() + " to " + getNewFieldName() + "="+value);
  }
}
