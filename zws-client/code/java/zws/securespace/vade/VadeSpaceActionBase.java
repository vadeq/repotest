package zws.securespace.vade;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 21, 2004, 10:50 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.CanNotMaterialize;
import zws.securespace.SecureSpaceBase;
import zws.util.Namespace;

public class VadeSpaceActionBase extends SecureSpaceBase implements VadeSpaceAction {
  public Namespace defineNamespace() { 
    return new Namespace(vadespace.getNamespace().toString() + DOT + vadespace.getName()); 
  }

  public final VadeSpace getVadeSpace() { return vadespace; }
  public final void setVadeSpace(VadeSpace vSpace) { vadespace=vSpace; }
  public boolean getPasswordProtected() { return passwordProtected; }
  public void setPasswordProtected(boolean b) { passwordProtected=b; }

  protected void materialize(String space) throws CanNotMaterialize {
    String[] spaceTokens = getSpaceTokens(space);
    if (3!=spaceTokens.length) throw new CanNotMaterialize(spaceTokens[0], spaceTokens);
    setType(spaceTokens[0]);
    //setName(spaceTokens[1]);
  }

  private Namespace namespace=null;
  private VadeSpace vadespace=null;
  private boolean passwordProtected=false;
}
