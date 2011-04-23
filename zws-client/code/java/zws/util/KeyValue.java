package zws.util; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class KeyValue {
  private String key = null;
  private Object value = null;
  public KeyValue() {}
  public KeyValue(String k, Object v) {key=k; value=v;}

  public String getKey() {return key;}
  public void setKey(String x) {key = x;}

  public Object getValue() {return value;}
  public void setValue(Object x) {value=x;}

  public String toXML() {
    String v;
    if (null==value) v = "";
    else v = value.toString();
    return key.trim().replace(' ', '-')+"=\""+v+"\"";
  }
}