package com.zws.functor.java;

import com.zws.functor.Functor;

import java.util.Iterator;
import java.util.Map;
public class NewInstance extends Functor {
  public void execute() throws Exception {
    setResult(Class.forName(getClassName()).newInstance());
    if (null==properties) return;
    PropertySetter setter = new PropertySetter();
    setter.bind(getResult());
    String key;
    Iterator i = properties.keySet().iterator();
    while (i.hasNext()) {
      key = (String)i.next();
      setter.setPropertyName(key);
      setter.setValue(properties.get(key));
      setter.execute();
    }
  }

  public String getClassName(){ return className; }
  public void setClassName(String s){ className=s; }
  public Class getJavaClass() { return javaClass; }
  public void setJavaClass(Class c) { javaClass=c; setClassName(c.getName()); }
  public Map getProperties(){ return properties; }
  public void setProperties(Map m){ properties = m; }

  private String className=null;
  private Class javaClass=null;
  private Map properties=null;
}