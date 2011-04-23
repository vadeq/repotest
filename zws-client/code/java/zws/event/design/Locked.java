package zws.event.design;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 28, 2004, 1:11 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.event.*;

public class Locked extends DesignEventBase  {
  public String getOwner() { return (String) getProperties().get(OWNER); }
  public void setOwner(String s) { getProperties().put(OWNER, s); }
  
  private static String OWNER="owner";
}
