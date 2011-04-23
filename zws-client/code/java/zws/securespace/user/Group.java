package zws.securespace.user;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 21, 2004, 11:12 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.DuplicateName;

import java.util.Collection;

public interface Group extends UserSpace {
  public void add(Group subGroup) throws DuplicateName;
  public Collection getSubGroups();
}
