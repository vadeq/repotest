package zws.search.criteria;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on June 28, 2004, 4:24 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.Serializable;
import java.util.List;

public interface Expression extends Serializable, Cloneable {
  public boolean isComparison();
  public abstract Expression copy() throws Exception;
  public List getComparisons();
}

