package zws.util.category;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 2, 2004, 3:54 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class ServerNode extends CategoryBase {
  public String getType() {
    if (null==type) type="Server node";
    return type; 
  }
  
}
