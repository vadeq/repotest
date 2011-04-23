package zws.search.criteria.modifier;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 1, 2004, 9:57 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.search.criteria.Comparison;
import zws.search.criteria.Criteria;
import zws.util.StringKeyedHashMap;

import java.util.Map;

public class RemapFieldNames extends CriteriaModifierBase {
  
  protected void modifyExpression(Criteria newCriteria, Comparison exp) throws Exception {
    if (null==mapping) return;
    String newFieldName = (String)mapping.get(exp.getFieldName());
    if(null!=newFieldName) exp.setFieldName(newFieldName);
    else 
      if (removeIfUnmapped) exp.clear();  // nullify this comparison
  }
  
  public Map getMapping() { return mapping; }
  public void setMapping(StringKeyedHashMap m) { mapping=m; }
  public boolean getRemoveIfUnmapped() { return removeIfUnmapped; }
  public void setRemoveIfUnmapped(boolean b) { removeIfUnmapped=b; }

  private StringKeyedHashMap mapping=null;
  private boolean removeIfUnmapped=true;
}
