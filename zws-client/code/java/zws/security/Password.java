package zws.security;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 23, 2004, 11:30 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

public class Password implements Serializable {
  public Password(String s) throws NoSuchAlgorithmException { encoding = SHA.encode(s); }
  public boolean matches(Password p) { return encoding.equals(p.getEncoding()); }

  public String getEncoding() { return encoding; }
  public String toString() { return encoding; }
  private String encoding=null;
}
