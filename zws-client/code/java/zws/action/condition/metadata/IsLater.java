package zws.action.condition.metadata;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 8, 2004, 3:11 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;

public class IsLater extends Comparator {
  public boolean compare(Metadata data) throws Exception { 
    return grabOrigin().isLater(data.getOrigin());
  }
}
