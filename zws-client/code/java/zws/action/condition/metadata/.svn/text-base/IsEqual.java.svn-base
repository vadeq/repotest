package zws.action.condition.metadata;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 8, 2004, 3:13 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;

import java.util.Iterator;

public class IsEqual extends Comparator {
  public boolean compare(Metadata data) throws Exception {
    Metadata meta = grabMetadata();
    if (!meta.getOrigin().isTheSameAs(data.getOrigin())) return false;
    boolean isEqual = true;
    Iterator i = meta.getFieldNames().iterator();
    String fieldname=null;
    while (isEqual && i.hasNext()){
      fieldname = (String)i.next();
      if (!meta.get(fieldname).equals(data.get(fieldname))) isEqual=false;
    }
    return isEqual;
  } 
}
