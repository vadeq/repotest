package zws.securespace.user;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 21, 2004, 10:20 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.CanNotMaterialize;
import zws.securespace.SecureSpaceBase;
import zws.securespace.User;
import zws.securespace.vade.VadeSpace;
import zws.util.Namespace;

import java.util.Collection;

public class UserSpaceBase extends SecureSpaceBase implements UserSpace {
  protected Namespace defineNamespace() { return new Namespace(getType()); }
 
  public Collection everyone() { return null; }
  public final void add(User user) {}
  public final void add(VadeSpace vade) {}
  public final void add(UserSpace subSpace) {}
  public final Collection getUsers() { return null; }
  public final Collection getVadeSpaces() { return null; }
  public final Collection getSubSpaces() { return null; }
 
  public static UserSpace materialize(String space, String description, String className) throws CanNotMaterialize {
    try {
      UserSpaceBase uSpace = (UserSpaceBase)Class.forName(className).newInstance();
      uSpace.materialize(space);
      uSpace.setDescription(description);
      return uSpace;
    }
    catch (Exception e) {e.printStackTrace(); throw new CanNotMaterialize(className, space); }
  }

  protected void materialize(String space) throws CanNotMaterialize {
    String[] spaceTokens = getSpaceTokens(space);
    if (2!=spaceTokens.length) throw new CanNotMaterialize(spaceTokens[0], spaceTokens);
    setType(spaceTokens[0]);
    setName(spaceTokens[spaceTokens.length-1]);
  }  
}
