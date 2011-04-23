package zws.securespace.vade;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 24, 2004, 8:15 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.CanNotMaterialize;
import zws.util.Namespace;

public class DomainBase extends VadeSpaceBase implements Domain {
  public boolean passwordProtected() { return true; }
  protected Namespace defineNamespace() { return new Namespace(getType()); }
  
  protected void materialize(String space) throws CanNotMaterialize {
    String[] spaceTokens = getSpaceTokens(space);
    if (2!=spaceTokens.length) throw new CanNotMaterialize(spaceTokens[0], spaceTokens);
    setType(spaceTokens[0]);
    setName(spaceTokens[1]);
  }    
}
