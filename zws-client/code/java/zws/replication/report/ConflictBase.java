package zws.replication.report;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 16, 2005, 10:40 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.Serializable;
import java.util.*;

public class ConflictBase implements Conflict, Serializable {
  public void setType(String s) { type=s; }
  public String getType() { return type; }
  public void setMessageKey(String s) { messageKey=s; }
  public String getMessageKey() { return messageKey; }
  public void addParameter(String s) { 
    if (null==parameters) parameters = new Vector();
    parameters.add(s); 
  }
  public Collection getParameters() { return parameters; }
  
  public String toString() { 
    return toXML();
  }
  public String toXML() {
    String s = "<" + getType();
    if (null!=getMessageKey()) s += " message-key='"+getMessageKey()+"'";
    int x=0;
    if (null!=getParameters()) {
      Iterator i = parameters.iterator();
      s += " param-" + x++ + "='"+i.next().toString()+"'";
    }
    s+="/>";
    return s;
  }
  private String type="conflict";
  private String messageKey=null;
  private Collection parameters=null;
}
