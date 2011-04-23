package zws.mapping.attribute.value;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 25, 2004, 7:52 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
//import zws.exception.UndefinedValueMapping;

public abstract class ValueMappingBase implements ValueMapping {
  /*
  protected boolean isMapped(String value);
  public void map() throws UndefinedValueMapping {
    if (!isMapped(getOldValue())) {
      if (!copyValueIfNotMapped()) throw new UndefinedMapping();
      else setNewValue(getOldValue());
      return;
    }
    mapOldValueToNew();    
  }
  
  protected abstract void mapOldValueToNew() throws UndefinedValueMapping;

  public String getValue() { return value; }
  public void setValue(String s) { value=s; }
  public String getNewValue() { return newValue; }
  protected void setNewValue(String s) { newValue=s; }
  
  public boolean getIsCaseSensitive() { return isCaseSensitive; }
  public void setIsCaseSensitive(boolean s) { isCaseSensitive=s; }

  private String value=null;
  private String newValue=null;
  private boolean isCaseSensitive=false;  
   */
}
