package com.zws.functor.search.filter;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on April 6, 2004, 1:42 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import com.zws.domo.document.Document;
import com.zws.functor.filter.DocumentUnitFilter;

public class DeleteFromCache extends DocumentUnitFilter {
  
  public boolean keep(Document doc){
    return true;
//    if (fileExists(path, name)) return true;
//    deleteFromCache(getDocument().getOrigin());
//    return false;
  }

  String path=null;
  String filename=null;
  String metadataPath=null;
  String metadataFilename=null;
}
