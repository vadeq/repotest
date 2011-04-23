package com.zws.domo.document;
import com.zws.domo.Domo;
import com.zws.util.KeyValue;

import java.util.*;

public class Metadata extends Domo {
  public Metadata(){}
  public void set(String name, String value) {
    String val=value;
    KeyValue pair = new KeyValue(name, val);
    attributes.put(name.toLowerCase(), pair);
	//attributes.put(name, pair);
  }
  
  public void set(String name, String value, Object descriptor) {
	String val=value;
	KeyValue pair = new KeyValue(name, val, descriptor);
	attributes.put(name.toLowerCase(), pair);
	//attributes.put(name, pair);
  }


  public KeyValue lookupKeyValue(String name) { 
  	return (KeyValue)attributes.get(name.toLowerCase()); 
	//return (KeyValue)attributes.get(name); 
  }
  
  public String get(String name) {
    KeyValue pair = (KeyValue)attributes.get(name.toLowerCase());
	//KeyValue pair = (KeyValue)attributes.get(name);
    if (null==pair) return null;
    return (String)pair.getValue();
  }
  
  public Object getDescriptor(String name) {
  	KeyValue pair = (KeyValue)attributes.get(name.toLowerCase());
	//KeyValue pair = (KeyValue)attributes.get(name);
  	if (null==pair) return null;
  	return pair.getDescriptor();
}
  
  public String remove(String name) { 
  	return ((KeyValue)attributes.remove(name.toLowerCase())).getValue().toString(); 
	//return ((KeyValue)attributes.remove(name)).getValue().toString(); 
 
  }
  
  public Collection getAttributes() { return attributes.values(); }
  public Set getAttributeNames() { return attributes.keySet(); }
  public boolean matches(String criteria) {
    KeyValue pair;
    Collection values = attributes.values();
    Iterator i = values.iterator();
    while (i.hasNext()){
      pair = (KeyValue)i.next();
      if (null!= pair.getValue() && 0 <= pair.getValue().toString().toLowerCase().indexOf(criteria.toLowerCase())) return true;
	  //if (null!= pair.getValue() && 0 <= pair.getValue().toString().indexOf(criteria)) return true;
    }
    return false;
  }

  public void insert(Metadata m) {
    Iterator i = m.attributes.values().iterator();
    while (i.hasNext()) {
      KeyValue pair = (KeyValue)i.next();
      set(pair.getKey().toLowerCase(), (String)pair.getValue());
	  //set(pair.getKey(), (String)pair.getValue());
    }
  }

  public String toXML(){
    String xml = "";
    Iterator i = attributes.values().iterator();
    while (i.hasNext()) xml += " " + ((KeyValue)i.next()).toXML();
    return xml;
  }

  protected Map attributes = new HashMap();
}
