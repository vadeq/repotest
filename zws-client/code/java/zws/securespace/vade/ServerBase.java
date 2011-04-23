package zws.securespace.vade;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 25, 2004, 12:19 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.CanNotMaterialize;
import zws.util.Namespace;

public class ServerBase extends VadeSpaceBase implements Server {
  public boolean passwordProtected() { return false; }
  protected Namespace defineNamespace() { return new Namespace(getType()); }
  
  public final String getServerName() { return getName(); }
  public final void setServerName(String s) { setName(s); }
  
  protected void materialize(String space) throws CanNotMaterialize {
    String[] spaceTokens = getSpaceTokens(space);
    if (2!=spaceTokens.length) throw new CanNotMaterialize(spaceTokens[0], spaceTokens);
    setType(spaceTokens[0]);
    setName(spaceTokens[1]);
  }
}
