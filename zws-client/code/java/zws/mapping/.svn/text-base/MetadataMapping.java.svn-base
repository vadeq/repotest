package zws.mapping;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 18, 2004, 11:18 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.exception.InvalidMapping;
import zws.op.Op;
import zws.util.RoutedData;

public interface MetadataMapping extends Op {
  void setSource(Metadata m);
  Metadata getTarget();
  void setTarget(Metadata m);
  
  RoutedData getSourceRouting();
  RoutedData getTargetRouting();

  void map() throws InvalidMapping; 
  //void map(String fieldName) throws UnsupportedOperation, InvalidMapping; 
}
