package zws.service.securespace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.application.Configurator;
import zws.securespace.User;
import zws.securespace.user.UserSpace;
import zws.securespace.vade.Domain;
import zws.securespace.vade.VadeSpace;
import zws.security.Authentication;
import zws.security.Password;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class SecureSpaceAdministrationEJB implements SessionBean, SecureSpaceAdministration  {
  //USER REGISTRATION SERVICES
  public void register(User user, Domain domain, Password password) throws RemoteException {
    try { SecureSpaceSvc.register(user, domain, password); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public void registerGuest(VadeSpace vSpace) throws RemoteException {
     //+++ todo
  }
  public void updatePassword(User user, VadeSpace dSpace, Password old, Password password) throws RemoteException {  
    try { SecureSpaceSvc.updatePassword(user, dSpace, old, password); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public void changePassword(User user, VadeSpace dSpace, Password password) throws RemoteException {  
    try { SecureSpaceSvc.changePassword(user, dSpace, password); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  //ADMINISTRATIVE REGISTRATION SERVICES
  public void register(UserSpace uSpace, VadeSpace dSpace, Authentication authentication) throws RemoteException {
    try { SecureSpaceSvc.register(uSpace, dSpace, authentication); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Authentication findAuthentication(UserSpace uSpace, VadeSpace dSpace) throws RemoteException {  
    try { return SecureSpaceSvc.findAuthentication(uSpace, dSpace); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public void changeAuthentication(UserSpace uSpace, VadeSpace dSpace, Authentication authentication) throws RemoteException {  
    try { SecureSpaceSvc.changeAuthentication(uSpace, dSpace, authentication); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public void updateAuthentication(UserSpace uSpace, VadeSpace dSpace, Authentication old, Authentication authentication) throws RemoteException {  
    try { SecureSpaceSvc.updateAuthentication(uSpace, dSpace, old, authentication); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public void allow(User user, UserSpace uSpace) throws RemoteException {
    try { SecureSpaceSvc.allow(user, uSpace); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public void include(VadeSpace dSpace, User user) throws RemoteException {
    try { SecureSpaceSvc.include(dSpace, user); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
    
  public void include(VadeSpace dSpace, UserSpace uSpace) throws RemoteException {
    try { SecureSpaceSvc.include(dSpace, uSpace); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  // TYPES of VADE AND USER SPACES
  public void lookupVadeSpaceTypes() throws RemoteException {
    try { SecureSpaceSvc.lookupVadeSpaceTypes(); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
    
  public void lookupUserSpaceTypes() throws RemoteException {
    try { SecureSpaceSvc.lookupUserSpaceTypes(); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
 
  
  //USERS
  public void create(User user) throws RemoteException {
    try { SecureSpaceSvc.create(user); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public void remove(User user) throws RemoteException {
    try { SecureSpaceSvc.remove(user); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public User findUser(String username) throws RemoteException {
    try { return SecureSpaceSvc.findUser(username); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findUsers() throws RemoteException {
    try {return SecureSpaceSvc.findUsers(); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findUsers(VadeSpace dSpace) throws RemoteException {
    try { return SecureSpaceSvc.findUsers(dSpace); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findUsers(UserSpace uSpace) throws RemoteException {
    try { return SecureSpaceSvc.findUsers(uSpace); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  //USER SPACES: ROLES, GROUPS, SITES & SECURITY PROFILES
  public void create(UserSpace uSpace) throws RemoteException {
    try { SecureSpaceSvc.create(uSpace); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public void remove(UserSpace uSpace) throws RemoteException {
    try { SecureSpaceSvc.remove(uSpace); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public UserSpace findUserSpace(String uSpace) throws RemoteException {
    try { return SecureSpaceSvc.findUserSpace(uSpace); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findUserSpaces() throws RemoteException {
    try { return SecureSpaceSvc.findUserSpaces(); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findUserSpaces(String type) throws RemoteException {
    try { return SecureSpaceSvc.findUserSpaces(type); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findUserSpaces(User user) throws RemoteException {
    try { return SecureSpaceSvc.findUserSpaces(user); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findUserSpaces(User user, String type) throws RemoteException {
    try { return SecureSpaceSvc.findUserSpaces(user, type); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findUserSpaces(VadeSpace dSpace) throws RemoteException {
    try { return SecureSpaceSvc.findUserSpaces(dSpace); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findUserSpaces(VadeSpace dSpace, String type) throws RemoteException {
    try { return SecureSpaceSvc.findUserSpaces(dSpace, type); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public Collection findRootUserSpaces() throws RemoteException {
    try { return SecureSpaceSvc.findRootUserSpaces(); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findRootUserSpaces(String type) throws RemoteException {
    try { return SecureSpaceSvc.findRootUserSpaces(type); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findRootUserSpaces(User user) throws RemoteException {
    try { return SecureSpaceSvc.findRootUserSpaces(user); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findRootUserSpaces(User user, String type) throws RemoteException {
    try { return SecureSpaceSvc.findRootUserSpaces(user, type); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findRootUserSpaces(VadeSpace dSpace) throws RemoteException {
    try { return SecureSpaceSvc.findRootUserSpaces(dSpace); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findRootUserSpaces(VadeSpace dSpace, String type) throws RemoteException {
    try { return SecureSpaceSvc.findRootUserSpaces(dSpace, type); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  
  // USER SPACE FAMILIES (allows multiple parents)
  public void linkSubSpace(UserSpace parent, UserSpace child) throws RemoteException {
    try { SecureSpaceSvc.linkSubSpace(parent, child); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public void unlinkSubSpace(UserSpace parent, UserSpace child) throws RemoteException {
    try { SecureSpaceSvc.linkSubSpace(parent, child); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public Collection findSubSpaces(UserSpace uSpace) throws RemoteException {
    try { return SecureSpaceSvc.findSubSpaces(uSpace); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findParentSpaces(UserSpace uSpace) throws RemoteException {
    try { return SecureSpaceSvc.findParentSpaces(uSpace); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  //DATA SPACES: DOMAINS, SERVERS, REPOSITORIES (INTRALINK), DRIVE LOCATIONS, WORKSPACES
  public void create(VadeSpace dSpace) throws RemoteException {
    try { SecureSpaceSvc.create(dSpace); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public void remove(VadeSpace dSpace) throws RemoteException {
    try { SecureSpaceSvc.remove(dSpace); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public VadeSpace findVadeSpace(String dSpace) throws RemoteException {
    try { return SecureSpaceSvc.findVadeSpace(dSpace); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findVadeSpaces() throws RemoteException {
    try { return SecureSpaceSvc.findVadeSpaces(); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findVadeSpaces(String type) throws RemoteException {
    try { return SecureSpaceSvc.findVadeSpaces(type); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findVadeSpaces(User user) throws RemoteException {
    try { return SecureSpaceSvc.findVadeSpaces(user); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findVadeSpaces(User user, String type) throws RemoteException {
    try { return SecureSpaceSvc.findVadeSpaces(user, type); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findVadeSpaces(UserSpace uSpace) throws RemoteException {
    try { return SecureSpaceSvc.findVadeSpaces(uSpace); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findVadeSpaces(UserSpace uSpace, String type) throws RemoteException {
    try { return SecureSpaceSvc.findVadeSpaces(uSpace, type); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public Collection findRootVadeSpaces() throws RemoteException {
    try { return SecureSpaceSvc.findRootVadeSpaces(); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findRootVadeSpaces(String type) throws RemoteException {
    try { return SecureSpaceSvc.findRootVadeSpaces(type); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findRootVadeSpaces(User user) throws RemoteException {
    try { return SecureSpaceSvc.findRootVadeSpaces(user); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findRootVadeSpaces(User user, String type) throws RemoteException {
    try { return SecureSpaceSvc.findRootVadeSpaces(user, type); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findRootVadeSpaces(UserSpace uSpace) throws RemoteException {
    try { return SecureSpaceSvc.findRootVadeSpaces(uSpace); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findRootVadeSpaces(UserSpace uSpace, String type) throws RemoteException {
    try { return SecureSpaceSvc.findRootVadeSpaces(uSpace, type); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  // VADE SPACE FAMILIES (allows multiple parents)
  public void linkSubVade(VadeSpace parent, VadeSpace child) throws RemoteException {
    try { SecureSpaceSvc.linkSubVade(parent, child); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public void unlinkSubVade(VadeSpace parent, VadeSpace child) throws RemoteException {
    try { SecureSpaceSvc.linkSubVade(parent, child); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }

  public Collection findSubVades(VadeSpace vSpace) throws RemoteException {
    try { return SecureSpaceSvc.findSubVades(vSpace); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  public Collection findParentVades(VadeSpace vSpace) throws RemoteException {
    try { return SecureSpaceSvc.findParentVades(vSpace); } catch (Exception e) { throw new RemoteException(Server.getName(), e); }
  }
  
  public void ejbCreate () { try {Configurator.load();} catch (Exception e) { e.printStackTrace(); } }
  public void ejbRemove() {}
  public void ejbPassivate() {}
  public void ejbActivate() {}
  public void setSessionContext(SessionContext ctx) {}
}
