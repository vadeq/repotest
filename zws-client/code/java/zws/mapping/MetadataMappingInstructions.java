package zws.mapping;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 4, 2004, 2:24 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.InvalidMapping;
import zws.mapping.attribute.MetadataAttributeMapping;

import java.util.*;

public class MetadataMappingInstructions extends MetadataMappingBase {
  
  protected boolean isMapped() { return (null!=getSource()); }
  
  public final void map(String fieldName) throws InvalidMapping {
	  mapSourceToTarget(fieldName);
	}

  protected  void mapSourceToTarget() throws InvalidMapping { //continue if exception?
    if (null==mappings) return;
    Iterator i = mappings.iterator();
    MetadataMapping mapping;
    while (i.hasNext()) {
      mapping = (MetadataMapping) i.next();
      mapping.setSource(getSource());
      mapping.setTarget(getTarget());
      mapping.map();
      setTarget(mapping.getTarget());
    }
  }
  
  protected  void mapSourceToTarget(String fieldName) throws InvalidMapping { //continue if exception?
    if (null==mappings) return;
    Iterator i = mappings.iterator();
    MetadataMapping mapping;
    MetadataAttributeMapping attMapping;
    while (i.hasNext()) {
      mapping = (MetadataMapping) i.next();
      if (!(mapping instanceof MetadataAttributeMapping)) continue;
      attMapping = (MetadataAttributeMapping)mapping;
      if (!attMapping.getFieldName().equalsIgnoreCase(fieldName)) continue;
      mapping.setSource(getSource());
      mapping.setTarget(getTarget());
      attMapping.map();      
      setTarget(mapping.getTarget());
    }
  }
  
  public void add(MetadataMapping mapping) {
    if (null==mappings) mappings = new Vector();
    mappings.add(mapping);
  }
  
  private List mappings = null;
}
