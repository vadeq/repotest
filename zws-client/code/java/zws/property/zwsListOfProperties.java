package zws.property; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Nov 18, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.util.Namespace;

import zws.exception.InvalidType;
import zws.exception.CanNotMaterialize;

import java.util.*;
import java.io.Serializable;

public class zwsListOfProperties implements Serializable {
  public Namespace getNamespace() { return namespace; }


  public zwsProperty getProperty(String name) { 
	  zwsProperty p = (zwsProperty)propMap.get(name);
	  return p;
	}
  
  public void addProperty(zwsProperty prop) { 
    props.add(prop);
    propMap.put(prop.getName(), prop);
  }
  public void addProperty(String name, String value, String type) throws InvalidType, CanNotMaterialize {
     zwsProperty p = PropertyMaker.materialize(namespace, name, value, type);
     addProperty(p);
  }
  public void addProperties(Collection props) {
    if (null==props) return;
    Iterator i = props.iterator(); 
    while(i.hasNext())  addProperty((zwsProperty)i.next());
  }

  public zwsProperty removeProperty(int idx) {
    zwsProperty p=null;
    try { p=(zwsProperty)props.get(idx); }
    catch (Exception ignore) {}
    if (null==p) return null;
    props.remove(idx);
    propMap.remove(p.getName());
    return p;
  }
  
  public zwsProperty removeProperty(String name) {
    zwsProperty p = (zwsProperty)propMap.get(name);
    if (null==p) return null;
    props.remove(p);
    propMap.remove(name);
    return p;
  }
  
  public void removeProperty(zwsProperty prop) { removeProperty(prop.getName()); }

  public void moveUp(String name) {
    zwsProperty p = getProperty(name);
    int idx = props.indexOf(p);
    if (-1==idx) return; // not in list
    if (0==idx) return; // already top of list
    props.remove(idx);
    idx--;
    props.add(idx, p);
  }

  public void moveDown(String name) {
    zwsProperty p = getProperty(name);
	  int idx = props.indexOf(p);
	  if (-1==idx) return; // not in list
	  int idxLast=props.size()-1;
	  if (idxLast==idx) return; // already bottom of list
	  props.remove(idx);
	  idx++;
	  props.add(idx, p);
  }  
  
  private Namespace namespace=null;
  private ArrayList props = new ArrayList();
  private Map propMap = new HashMap();
}
