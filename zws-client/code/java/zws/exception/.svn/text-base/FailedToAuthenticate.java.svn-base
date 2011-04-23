package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 26, 2004, 1:55 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class FailedToAuthenticate extends Exception {
  public FailedToAuthenticate(String uSpace, String dSpace, String username) {
    super("UserSpace: "+uSpace+" DataSpace: "+dSpace+" username:"+username);
    u=uSpace;d=dSpace;name=username;
  }
  public FailedToAuthenticate(String message) {
    super(message);
  }

  public String getUserSpace() { return u; }
  public String getDataSpace() { return d; }
  public String getUsername() { return name; }
  private String u,d,name;
}
