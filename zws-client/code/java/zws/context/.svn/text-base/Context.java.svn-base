package zws.context;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.*;

import java.io.*;
import java.util.*;


public class Context implements Serializable {
  public String getString(String property) { return (String) get(property); }
  public Object get(String property) {
    Object value=null;
    Context ctx = this;
    while (null==value && null!=ctx && !ctx.properties.containsKey(property)) ctx = ctx.getParent();
    if (null==ctx) return null;
    return ctx.properties.get(property);
  }

  
  public String getString(String key, String defaultValue) {
    String value = getString(key);
    if (null==value) { value= defaultValue; set(key, defaultValue); }
    return value;
  }
  
	public void set(String property, Object value) {
	  properties.put(property, value);
	}
  
   
	public void set(Map parameters) throws NotSerializableException {
	  Iterator i = parameters.keySet().iterator();
	  String key;
	  Object o;
	  while (i.hasNext()) {
	    key = (String) i.next();
	    o = parameters.get(key);
	    if (null==key) continue;
		  if (o instanceof Serializable) set(key, ((Serializable)o) );
		  else throw new NotSerializableException(o.toString());	    
	  }
	}
	
	public void set(KeyValue pair) throws NotSerializableException {
	  Object o=null;
	  o = pair.getValue();
	  if (null==o) throw new NullPointerException();
	  if (o instanceof Serializable) set(pair.getKey(), ((Serializable)o) );
	  else throw new NotSerializableException(o.toString());
	}

	public boolean contains(String property){
		Context ctx=this;
    boolean containsProperty=false;
    while(!containsProperty && null!=ctx){
			containsProperty=ctx.properties.containsKey(property);
			ctx = ctx.getParent();
		}
		return containsProperty; 
	}

    
  public Context getParent() { return parentContext; }
  public void setParent(Context c) { parentContext = c; }

  public void dump(PrintStream stream){
    Iterator i = properties.keySet().iterator();
    String field;
    while (i.hasNext()) {
      field = (String) i.next();
      stream.println(field + "=" + properties.get(field));
    }
  }
  
  //does not return parent properties
  public Map getProperties() { return properties; }
  
  protected Map properties = new HashMap();
	private Context parentContext = null;
	
  public static String NAME="name";
  public static String DESCRIPTION="description";
  public static String SYSTEM_USERNAME="system-username";
  public static String SYSTEM_PASSWORD="system-password";
  public static String DOMAIN_NAME="domain-name";
  public static String SERVER_NAME="server-name";
  public static String REPOSITORY_TYPE="repository-type";
  public static String REPOSITORY_NAME="repository-name";
  public static String PROTOCOL="protocol";
  public static String HOST_NAME="host-name";
  public static String PORT="port";
  public static String SERVICES_PATH="services-path";
  public static String SERVICE_NAME="service-name";
  public static String METHOD_NAME="method-name";
}
