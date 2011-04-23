package zws.securespace.vade;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 21, 2004, 10:47 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.DuplicateName;
import zws.securespace.SecureSpace;

import java.util.Map;

public interface VadeSpace extends SecureSpace {
  public boolean getPasswordProtected();
  public boolean getIsActive();
  public Map getActions();
  public void addAction(String name) throws DuplicateName;
  public void addAction(String name, String description, boolean passwordProtected) throws DuplicateName;
}
