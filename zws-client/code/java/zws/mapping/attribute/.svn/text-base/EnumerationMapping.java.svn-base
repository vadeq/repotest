package zws.mapping.attribute;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 3, 2004, 11:09 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.InvalidAttributeMapping;
import zws.util.StringPair;

import java.util.HashMap;
import java.util.Map;

public class EnumerationMapping extends MetadataAttributeMappingBase {
  
  protected boolean isMapped() { return getSource().hasFieldName(getFieldName()); }
  
  protected void mapSourceToTarget() throws InvalidAttributeMapping {
    String oldValue = getSource().get(getFieldName());
    String newValue = (String)enumMappings.get(oldValue);
    if (null==newValue && !getCopyIfUnmapped()) throw new InvalidAttributeMapping("No enumerated mapping defined for value: " + getFieldName()+"="+oldValue);
    if (null==newValue && getCopyIfUnmapped()) newValue=oldValue;
    getTarget().set(getNewFieldName(), newValue);
  }

  public void addValueMapping(String fromValue, String toValue) { enumMappings.put(fromValue.trim(), toValue.trim()); }
  public void addValueMapping(StringPair pair) { enumMappings.put(pair.getString0().trim(), pair.getString1().trim()); }

  public boolean getCopyIfUnmapped() { return copyIfUnmapped; }
  public void setCopyIfUnmapped(boolean b) { copyIfUnmapped=b; }
  
  private Map enumMappings= new HashMap();
  private boolean copyIfUnmapped=false;
}
