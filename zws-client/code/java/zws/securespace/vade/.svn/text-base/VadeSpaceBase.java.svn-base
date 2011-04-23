package zws.securespace.vade;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 21, 2004, 10:56 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.CanNotMaterialize;
import zws.exception.DuplicateName;
import zws.securespace.SecureSpaceBase;
import zws.util.Namespace;

import java.util.HashMap;
import java.util.Map;

public class VadeSpaceBase extends SecureSpaceBase implements VadeSpace {
  public boolean getPasswordProtected() { return passwordProtected; }
  public void setPasswordProtected(boolean b) { passwordProtected=b; }
  public boolean getIsActive() { return isActive; }
  public void setIsActive(boolean b) { isActive=b; }

  protected Namespace defineNamespace() { return new Namespace(getType()); }
 
  public final Map getActions() { return actions; }
  
  public final void addAction(String name) throws DuplicateName { addAction(name, "Action on " + getName(), false); }
  public final void addAction(String name, String description, boolean passwordProtected) throws DuplicateName {
    if (null==actions) actions=new HashMap();
    if (actions.containsKey(name)) throw new DuplicateName(name);
    VadeSpaceActionBase action= new VadeSpaceActionBase();
    action.setVadeSpace(this);
    action.setName(name);
    action.setDescription(description);
    action.setPasswordProtected(passwordProtected);
    actions.put(name, action);
  }
    
  public static VadeSpace materialize(String space, String description, boolean passworded, boolean active, String className) throws CanNotMaterialize {
    String[] spaceTokens = getSpaceTokens(space);
    try {
      VadeSpaceBase vSpace = (VadeSpaceBase)Class.forName(className).newInstance();
      vSpace.materialize(space);
      vSpace.setDescription(description);
      vSpace.setPasswordProtected(passworded);
      vSpace.setIsActive(active);
      return vSpace;
    }
    catch (Exception e) {e.printStackTrace(); throw new CanNotMaterialize(spaceTokens[0], spaceTokens); }
  }

  protected void materialize(String space) throws CanNotMaterialize {
    String[] spaceTokens = getSpaceTokens(space);
    if (2!=spaceTokens.length) throw new CanNotMaterialize(spaceTokens[0], spaceTokens.toString());
    setType(spaceTokens[0]);
    setName(spaceTokens[1]);
  }
  
  private boolean passwordProtected=false;
  private boolean isActive=true;
  private Map actions=null;
}
