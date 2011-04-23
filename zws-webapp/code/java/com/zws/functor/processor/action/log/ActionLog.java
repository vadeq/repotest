package com.zws.functor.processor.action.log;

import com.zws.domo.document.Document;

import java.util.*;

public class ActionLog {

  public Document getDocument() { return doc; }
  public void setDocument(Document d) { doc=d; }

  public void log(String s) { log.add(s); }
  public Collection getLog() { return log; }

  public String getProperty(String name) { return (String) properties.get(name); }
  public void setProperty(String name, String value){ properties.put(name, value); }
  public String removeProperty(String name) { return (String) properties.remove(name); }

  public Object getData(String name) { return data.get(name); }
  public void setData(String name, Object value){ data.put(name, value); }
  public Object removeData(String name) { return data.remove(name); }

  private Document doc;
  private Collection log = new Vector();
  private Map properties=new HashMap();
  private Map data=new HashMap();
}
