package zws.property; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Nov 18, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

import zws.util.Namespace;
import zws.property.enums.zwsEnumeration;

import zws.exception.InvalidType;
import java.io.Serializable;

public abstract class zwsPropertyBase implements zwsProperty, Serializable {
  public Namespace getNamespace() { return namespace; }
  public void setNamespace(Namespace n) { namespace=n; }
  public void setNamespace(String s) { namespace=new Namespace(s); }
	public String getName() { return name; }
	public void setName(String s) { name=s; }
	public String getCategory() { return category; }
	public void setCategory(String s) { category=s; }
	public String getValue() { return value; }
	public void setValue(String s) throws InvalidType { 
	  convertType(s, getPrimitiveType()); 
	  value=s; 
	}
	public boolean hasEnumeration() { return null!=zwsEnum; }
	public zwsEnumeration getEnumeration() { return zwsEnum; }

  public abstract String getPrimitiveType(); 

  public static void convertType(String value, String toType) throws InvalidType {
    try {
      if (STRING.equals(toType)) return;
	    if (INTEGER.equals(toType)) Integer.valueOf(value);
	    if (NUMBER.equals(toType)) Double.valueOf(value);
	    if (PATH.equals(toType)) return;
	    if (FILE.equals(toType)) return;
	    if (DIRECTORY.equals(toType)) return;
	    if (URLPATH.equals(toType)) return;
    }
	  catch(NumberFormatException e) { throw new InvalidType(value, toType); }
	  catch(Exception e) { throw new InvalidType(value, toType); } 
	}

  private Namespace namespace=null;
  private String category=null;
  private String name=null;
  private String value=null;
  private String primitive=STRING;
  private int spot=-1;
  private zwsEnumeration zwsEnum=null;
}
