package zws.securespace.vade;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 26, 2004, 2:30 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.CanNotMaterialize;
import zws.util.Namespace;

public class ApplicationBase extends VadeSpaceBase implements Application {
  protected Namespace defineNamespace() { return new Namespace(getType()); } 

  protected void materialize(String space) throws CanNotMaterialize {
    String[] spaceTokens = getSpaceTokens(space);
    if (2!=spaceTokens.length) throw new CanNotMaterialize(spaceTokens[0], spaceTokens);
    setType(spaceTokens[0]);
    setName(spaceTokens[1]);
  }    
}
