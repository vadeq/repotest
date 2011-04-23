package zws.attribute;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 7, 2004, 10:49 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.*;

import java.util.Collection;
import java.util.Iterator;

public interface EnumeratedAttribute extends Attribute {
  public String getName();
  public void setName(String s);
  public String getType();
  public String getValue();
  public void setValue(String s) throws InvalidValue;
  public int compareTo(String s);
  public boolean getIsEnumerated();

  public boolean getValueIsBoundToEnumeration();
  public void setValueIsBoundToEnumeration(boolean b);
  
  public Enumeration getEnumeration();
  public void clearEnumeration();
  public void setEnumeration(Enumeration e);
  public void addEnumeration(String s) throws InvalidValue;  
  public String enumBefore(String s) throws NameNotFound, StartOfList;
  public String enumAfter(String s) throws NameNotFound, EndOfList;
  public Iterator enumIterator();
  public Collection enumValues();
}
