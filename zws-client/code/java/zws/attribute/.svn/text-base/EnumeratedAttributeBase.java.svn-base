package zws.attribute;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 6, 2004, 4:58 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.*;
import zws.util.StringValue;

import java.util.Collection;
import java.util.Iterator;

public abstract class EnumeratedAttributeBase extends AttributeBase implements EnumeratedAttribute {
  public abstract String getType();
  protected abstract String createValue(String s) throws InvalidValue;
  
  public void setValue(String s) throws InvalidValue {
    String val = createValue(s);
    if (!isBounded) value=val;
    else if (enumeration.contains(val)) value=val;
    else throw new InvalidValue(s + " is not a valid enumerated value");
  }
  public boolean getIsEnumerated() { return true; }
  public boolean getValueIsBoundToEnumeration() { return isBounded; }
  public void setValueIsBoundToEnumeration(boolean b) { isBounded=b; }
  public void clearEnumeration() { enumeration.clear(); }
  public Enumeration getEnumeration() { return enumeration; }
  public void setEnumeration(Enumeration e) { enumeration = e;}
  public void addEnumeration(String s) throws InvalidValue { enumeration.add(createValue(s));  }
  public void addEnumeration(StringValue s) throws InvalidValue { enumeration.add(createValue(s.getValue()));  }
  
  protected int compareValueTo(String s) { 
    try { return enumeration.compare(getValue(), createValue(s));  }
    catch (Exception e) { e.printStackTrace();  return 1; }
  }
  
  public String enumBefore(String s) throws NameNotFound, StartOfList { return enumeration.itemBefore(s); }    
  public String enumAfter(String s) throws NameNotFound, EndOfList { return enumeration.itemAfter(s); }    
  public Iterator enumIterator() { return enumeration.iterator(); }
  public Collection enumValues() { return enumeration.getValues(); }

  private boolean isBounded=false;
  private Enumeration enumeration = new Enumeration();
}
