package zws.op;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 1, 2004, 8:38 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.Collection;

public interface OpResults extends OpResult {
  public void setResults(Collection o);
  public Collection getResults();
}
