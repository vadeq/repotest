package zws.exception;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 26, 2004, 1:47 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class BadPassword extends Exception {
  public BadPassword(String username) { super(username); }
  public BadPassword(String uSpace, String dSpace, String username) { super("UserSpace: "+uSpace+" DataSpace: "+dSpace+" username:"+username); }
}
