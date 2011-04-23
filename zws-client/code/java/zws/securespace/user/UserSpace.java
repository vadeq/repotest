package zws.securespace.user;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 21, 2004, 10:20 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.DuplicateName;
import zws.securespace.SecureSpace;

import java.util.Collection;

public interface UserSpace extends SecureSpace {
  public void add(UserSpace subSpace) throws DuplicateName;
  public Collection everyone();
  public Collection getUsers();
  public Collection getVadeSpaces();
  public Collection getSubSpaces();
}
