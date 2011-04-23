package zws.data.filter.metadata;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 27, 2004, 12:54 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;

public abstract class MetadataValueBase extends MetadataUnitFilterBase {

  protected abstract boolean testValue(String metadataValue, String value) throws Exception;
  public boolean keep(Metadata data) throws Exception {
    if (null==data) return false;
    if (null==fieldname || null==value) return true;
    String metadataValue = data.get(fieldname);
    if (null==metadataValue) return false;
    if (ignoreCase) return testValue(metadataValue.toLowerCase(), value.toLowerCase());
    else return testValue(metadataValue, value);
  }

  public String getFieldname() { return fieldname; }
  public void setFieldname(String s) { fieldname=s; }
  public String getValue() { return value; }
  public void setValue(String s) { value=s; }
  public boolean getIgnoreCase() { return true; }
  public void setIgnoreCase(boolean b) { ignoreCase=b; }

  private String fieldname=null;
  private String value=null;
  private boolean ignoreCase=true;
}
