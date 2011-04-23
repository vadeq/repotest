package zws.security; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

public class Authentication implements Serializable {
  public Authentication(){}
  public Authentication(String username, String psswd) throws NoSuchAlgorithmException {
    userID=username; pass=psswd; password = new Password(psswd); 
  }
  public Authentication(String username, Password psswd) { 
    userID=username; password = psswd; 
  }
  public String getUsername() { return userID; }
  public void setUsername(String s) { userID=s; }
  public String getPassword() { if (null!=pass) return pass; return password.getEncoding();}
  public void setPassword(String s) { pass=s; }
  public Password password() { return password; }
  private String userID=null;
  private String pass=null;
  private Password password=null;
}