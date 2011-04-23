package zws.service.securespace; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.securespace.User;
import zws.securespace.vade.Domain;
import zws.securespace.vade.VadeSpace;
import zws.security.Password;

import java.io.Serializable;

public interface SecureSpaceRegistration extends Serializable {
  public void register(User u, Domain domain, Password password) throws Exception;
  public User authenticate(String username, VadeSpace vSpace, Password password) throws Exception;
  public User authenticateGuest(VadeSpace vSpace) throws Exception;
  public void updatePassword(User user, VadeSpace dSpace, Password old, Password password) throws Exception;
  public void changePassword(User user, VadeSpace dSpace, Password password) throws Exception;
}