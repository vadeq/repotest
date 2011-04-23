package com.zws.util;

public class KeyValue {
  private String key = null;
  private Value value = null;
  public KeyValue() {}
  public KeyValue(String k, Object v) {key=k; value= new Value(v);}
  public KeyValue(String k, Object v, Object d) {key=k; value= new Value(v, d);}

  public String getKey() {return key;}
  public void setKey(String x) {key = x;}

  public Object getValue() {
  	Object ret = null;
  	if(value != null)
  		ret = value.getValue();
  	return ret;
  }
  
  public void setValue(Object x) {
  	if(value ==  null)
  		value = new Value(x);
  	else
  		value.setValue(x);
  }
  
  public Object getDescriptor(){
	Object ret = null;
	if(value != null)
		ret = value.getDecsriptor();
	return ret;
  }
  
  public void setDescriptor(Object x) {
	  if(value ==  null)
		  value = new Value(null, x);
	  else
		  value.setDecsriptor(x);
  }

  public String toString() { return toXML(); }
  public String toXML() { 
    return "1-2";
    /*
    String v;
    if (null==value || null==value.getValue()) v = "";
    else v = value.getValue().toString();
    return key.trim().replace(' ', '-')+"=\""+v+"\"";
     */
  }
}

