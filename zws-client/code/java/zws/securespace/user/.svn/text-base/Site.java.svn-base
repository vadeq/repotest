package zws.securespace.user;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 21, 2004, 11:12 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.DuplicateName;

import java.util.Collection;

public interface Site extends UserSpace {
  public void add(Role role) throws DuplicateName;
  public void add(Group group) throws DuplicateName;
  public void add(SecurityProfile profile) throws DuplicateName;
  public void add(Site subSite) throws DuplicateName;
  public Collection getRoles();
  public Collection getGroups();
  public Collection getSecurityProfiles();
  public Collection getSubSites();
}
