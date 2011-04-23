package zws.logical; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;

public class MetadataFieldEquals extends LogicalOpBase {
  public void bind(Metadata data) { setMetadata(data); }
  public void bind(Object data) { bind((Metadata)data); }
  public boolean isTrue() throws Exception{
    if (null==metadata) throw new LogicException("Metadata not set for field comparison.");
    if (null==fieldName) throw new LogicException("Metadata field name not set for comparison.");
    if (ignoreCase) return metadata.get(fieldName).trim().equalsIgnoreCase(value);
    return metadata.get(fieldName).trim().equals(value);
    //++add type information to metadata fields
  }
 
  public Metadata getMetadata() { return metadata; }
  public void setMetadata(Metadata data) { metadata=data; }
  public String getFieldName() { return fieldName; }
  public void setFieldName(String s) { fieldName=s; }
  public String getValue() { return value; }
  public void setValue(String s) { value=s.trim(); }
  public boolean getIgnoreCase() { return ignoreCase; }
  public void setIgnoreCase(boolean b) { ignoreCase = b; }

  private Metadata metadata=null;
  private String fieldName = null;
  private String value=null;
  private boolean ignoreCase = true;
}