package zws.replication.report;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 16, 2005, 10:38 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.Collection;

public interface Conflict {
  public String getType();
  public String getMessageKey();
  public Collection getParameters();
  public String toXML();
}
