package zws.action.condition.metadata;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 8, 2004, 1:33 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.action.condition.Conditional;
import zws.data.Metadata;

public class HasChildren extends Conditional {
  public boolean isTrue() {
    Metadata data = grabMetadata();
    return null!=data.getSubComponents() && 0 < data.getSubComponents().size();
  }
}
