package zws.securespace.vade;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 21, 2004, 11:56 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.CanNotMaterialize;
import zws.util.Namespace;

public class WorkspaceBase extends VadeSpaceBase implements Workspace {
  protected Namespace defineNamespace() { 
    String space = getType() + DOT + STAR + DOT + getServerName() + DOT + STAR + DOT + getLocation();
    return new Namespace(space); 
  }

  public final String getServerName() { return serverName; }
  public final void setServerName(String s) { serverName=s; resetNamespace(); }
    
  public String getLocation() { return location; }
  public void setLocation(String s) { location=s; resetNamespace(); }
  public String getUsername() { return getName(); }
  public void setUsername(String s) { setName(s); }

  protected void materialize(String space) throws CanNotMaterialize {
    String[] spaceTokens = getSpaceTokens(space);
    if (4!=spaceTokens.length) throw new CanNotMaterialize(spaceTokens[0], spaceTokens);
    setType(spaceTokens[0]);
    setServerName(spaceTokens[1]);
    setLocation(spaceTokens[2]);
    setName(spaceTokens[3]);
  }    
  
  private String serverName=null; 
  private String location=null;
}
