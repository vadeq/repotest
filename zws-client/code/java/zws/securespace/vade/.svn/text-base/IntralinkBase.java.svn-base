package zws.securespace.vade;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 21, 2004, 11:37 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.CanNotMaterialize;
import zws.util.Namespace;

public class IntralinkBase extends RepositoryBase implements Intralink {
  protected Namespace defineNamespace() { 
    return new Namespace(getType() + DOT + STAR + DOT + getServerName()); 
  }
  
  protected void materialize(String space) throws CanNotMaterialize {
    String[] spaceTokens = getSpaceTokens(space);
    if (3!=spaceTokens.length) throw new CanNotMaterialize(spaceTokens[0], spaceTokens);
    setType(spaceTokens[0]);
    setServerName(spaceTokens[1]);
    setName(spaceTokens[2]);
  }  
}
