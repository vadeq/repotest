package zws.property; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Nov 18, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.util.Namespace;

import zws.exception.InvalidType;
import zws.property.enums.zwsEnumeration;

public interface zwsProperty {
  public Namespace getNamespace();
  public String getName();
  public String getValue();
	public String getCategory();

  public void setValue(String s) throws InvalidType;
  public String getPrimitiveType();
  public boolean hasEnumeration();
  public zwsEnumeration getEnumeration();
  
  public static String STRING = "string";
  public static String INTEGER= "integer";
  public static String NUMBER= "number";  
  public static String BOOLEAN= "boolean";  
  public static String PATH= "path";  
  public static String FILE= "file";  
  public static String DIRECTORY= "directory";  
  public static String URLPATH= "url";
}
