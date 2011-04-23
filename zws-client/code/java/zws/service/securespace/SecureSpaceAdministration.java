package zws.service.securespace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.securespace.User;
import zws.securespace.user.UserSpace;
import zws.securespace.vade.Domain;
import zws.securespace.vade.VadeSpace;
import zws.security.Authentication;
import zws.security.Password;

import java.io.Serializable;
import java.util.Collection;

public interface SecureSpaceAdministration extends Serializable {

  //REGISTRATION
  public void register(User u, Domain domain, Password password) throws Exception;
  public void registerGuest(VadeSpace vSpace) throws Exception;
  public void updatePassword(User user, VadeSpace dSpace, Password old, Password password) throws Exception;
  public void changePassword(User user, VadeSpace dSpace, Password password) throws Exception;
  
  public void register(UserSpace uSpace, VadeSpace dSpace, Authentication authentication) throws Exception;
  public Authentication findAuthentication(UserSpace uSpace, VadeSpace dSpace) throws Exception;  
  public void changeAuthentication(UserSpace uSpace, VadeSpace dSpace, Authentication authentication) throws Exception;  
  public void updateAuthentication(UserSpace uSpace, VadeSpace dSpace, Authentication old, Authentication authentication) throws Exception;  

  public void allow(User user, UserSpace uSpace) throws Exception;
  public void include(VadeSpace dSpace, User user) throws Exception;
  public void include(VadeSpace dSpace, UserSpace uSpace) throws Exception;
  
  public void lookupVadeSpaceTypes() throws Exception;
  public void lookupUserSpaceTypes() throws Exception;
  
  public void remove(User user) throws Exception;
  public User findUser(String username) throws Exception;
  public Collection findUsers() throws Exception;
  public Collection findUsers(VadeSpace dSpace) throws Exception;
  public Collection findUsers(UserSpace uSpace) throws Exception;
  
  public void create(UserSpace uSpace) throws Exception;
  public void remove(UserSpace uSpace) throws Exception;
  public UserSpace findUserSpace(String uSpace) throws Exception;
  public Collection findUserSpaces() throws Exception;
  public Collection findUserSpaces(String type) throws Exception;
  public Collection findUserSpaces(User user) throws Exception;
  public Collection findUserSpaces(User user, String type) throws Exception;
  public Collection findUserSpaces(VadeSpace dSpace) throws Exception;
  public Collection findUserSpaces(VadeSpace dSpace, String type) throws Exception;
  public Collection findRootUserSpaces() throws Exception;
  public Collection findRootUserSpaces(String type) throws Exception;
  public Collection findRootUserSpaces(User user) throws Exception;
  public Collection findRootUserSpaces(User user, String type) throws Exception;
  public Collection findRootUserSpaces(VadeSpace dSpace) throws Exception;
  public Collection findRootUserSpaces(VadeSpace dSpace, String type) throws Exception;
  public Collection findSubSpaces(UserSpace uSpace) throws Exception;
  public Collection findParentSpaces(UserSpace uSpace) throws Exception;

  public void linkSubSpace(UserSpace parent, UserSpace child) throws Exception;
  public void unlinkSubSpace(UserSpace parent, UserSpace child) throws Exception;
  
  public void create(VadeSpace uSpace) throws Exception;
  public void remove(VadeSpace uSpace) throws Exception;
  public VadeSpace findVadeSpace(String dSpace) throws Exception;
  public Collection findVadeSpaces() throws Exception;
  public Collection findVadeSpaces(String type) throws Exception;
  public Collection findVadeSpaces(User user) throws Exception;
  public Collection findVadeSpaces(User user, String type) throws Exception;
  public Collection findVadeSpaces(UserSpace uSpace) throws Exception;
  public Collection findVadeSpaces(UserSpace uSpace, String type) throws Exception;
  public Collection findRootVadeSpaces() throws Exception;
  public Collection findRootVadeSpaces(String type) throws Exception;
  public Collection findRootVadeSpaces(User user) throws Exception;
  public Collection findRootVadeSpaces(User user, String type) throws Exception;
  public Collection findRootVadeSpaces(UserSpace uSpace) throws Exception;
  public Collection findRootVadeSpaces(UserSpace uSpace, String type) throws Exception;
  
  public void linkSubVade(VadeSpace parent, VadeSpace child) throws Exception;
  public void unlinkSubVade(VadeSpace parent, VadeSpace child) throws Exception;
}

/*
  authenticate
  public void changePassword(UserSpace uSpace, VadeSpace dSpace, String username, Password old, Password password) throws Exception;
  public void changePassword(UserSpace uSpace, VadeSpace dSpace, String username, Password old, Password password) throws RemoteException {
    try { return SecureSpaceSvc.changePassword(uSpace, dSpace, username, old, password); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
 
  public void setPassword(User user, Domain domain, Password password) throws Exception;
  public void changePassword(User user, VadeSpace dSpace, Password password) throws Exception;  
  public void changePassword(User user, VadeSpace dSpace, Password old, Password password) throws Exception;

  public Role findRole(String uSpace) throws Exception;
  public Collection findRoles() throws Exception;
  public Collection findRoles(User user) throws Exception;
  public Collection findRoles(VadeSpace dSpace) throws Exception;
  public Collection findSubRoles(UserSpace uSpace) throws Exception;
  public void create(Role role) throws Exception;
  public void remove(Role role) throws Exception;

  public Group findGroup(String uSpace) throws Exception;
  public Collection findGroups() throws Exception;
  public Collection findGroups(User user) throws Exception;
  public Collection getgroups(VadeSpace dSpace) throws Exception;
  public Collection getSubGroups(UserSpace uSpace) throws Exception;
  public void register(Group group) throws Exception;
  public void remove(Group group) throws Exception;

  public Site findSite(String uSpace) throws Exception;
  public Collection getSites() throws Exception;
  public Collection getSites(User user) throws Exception;
  public Collection getSites(VadeSpace dSpace) throws Exception;
  public Collection getSubSites(UserSpace uSpace) throws Exception;
  public void register(Site site) throws Exception;
  public void remove(Site site) throws Exception;
  
  public SecurityProfile findSecurityProfile(String uSpace) throws Exception;
  public Collection getSecurityProfiles() throws Exception;
  public Collection getSecurityProfiles(User user) throws Exception;
  public Collection getSecurityProfiles(VadeSpace dSpace) throws Exception;
  public Collection getSubProfiles(UserSpace uSpace) throws Exception;
  public void register(SecurityProfile profile) throws Exception;
  public void remove(SecurityProfile profile) throws Exception;
  
  public void allow (User user, UserSpace uspace) throws Exception;
  public void allow (User user, Role role) throws Exception;
  public void allow (User user, Group group) throws Exception;
  public void allow (User user, Site site) throws Exception;
  public void allow (User user, SecurityProfile profile) throws Exception;

  public void include(VadeSpace dSpace, UserSpace uSpace) throws Exception;
  public void include(VadeSpace dSpace, Role role) throws Exception;
  public void include(VadeSpace dSpace, Group group) throws Exception;
  public void include(VadeSpace dSpace, Site site) throws Exception;
  public void include(VadeSpace dSpace, SecurityProfile profile) throws Exception;
 */
