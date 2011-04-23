package zws.property; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Nov 18, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

import zws.util.Namespace;

import zws.exception.CanNotMaterialize;
import zws.exception.InvalidType;

public class PropertyMaker {

  public static zwsProperty materialize(Namespace namespace, String name, String value, String type) throws InvalidType, CanNotMaterialize {
    zwsPropertyBase p=null;
    if (zwsProperty.STRING.equals(type)) p = new zwsPropertyString();
    else if (zwsProperty.INTEGER.equals(type)) p = new zwsPropertyInteger();
    else if (zwsProperty.NUMBER.equals(type)) p = new zwsPropertyNumber();
    else if (zwsProperty.BOOLEAN.equals(type)) p = new zwsPropertyBoolean();
    else if (zwsProperty.PATH.equals(type)) p = new zwsPropertyPath();
    else if (zwsProperty.FILE.equals(type)) p = new zwsPropertyFile();
    else if (zwsProperty.DIRECTORY.equals(type)) p = new zwsPropertyDirectory();
    else if (zwsProperty.URLPATH.equals(type)) p = new zwsPropertyURL();
    else throw new CanNotMaterialize(namespace.asString()+DOT+name + "="+value, "Unknown Type",type);
    p.setNamespace(namespace);
    p.setName(name);
    p.setValue(value);
    return p;
  }
  
  private static String DOT=".";
}
