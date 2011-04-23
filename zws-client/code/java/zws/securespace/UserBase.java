package zws.securespace;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 24, 2004, 12:02 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.securespace.user.UserSpace;
import zws.securespace.user.UserSpaceBase;
import zws.util.Namespace;

public class UserBase extends SecureSpaceBase implements User {
  public UserBase() { super(); }
  public UserBase(String login, String alias, String first, String last,  String mail, String ph, boolean active){
    super();
    setUsername(login); setFirstName(first); setLastName(last); setAlias(alias);
    setEmail(mail); setPhone(ph); setIsActive(active);
  }
  
  public Namespace defineNamespace() { 
    Namespace ns = new Namespace();
    ns.setValue(getType());
    return ns;
  }

  public String getUsername() { return getName(); }
  public void setUsername(String s) { setName(s); }
  
  public String getAlias() { return alias; }
  public void setAlias(String s) { alias = s; }

  public String getFirstName() { return firstName; }
  public void setFirstName(String s) { firstName = s; }
  public String getLastName() { return lastName; }
  public void setLastName(String s) { lastName = s; }
  public String getEmail() { return email; }
  public void setEmail(String s) { email = s; }
  public String getPhone() { return phone; }
  public void setPhone(String s) { phone = s; }
  public boolean getIsActive() { return isActive; }
  public void setIsActive(boolean x) { isActive = x; }

  public UserSpace getUserSpace() { 
    if (null==uSpace) {
      uSpace = new UserSpaceBase();
      uSpace.setType(getType());
      uSpace.setName(getName());
      uSpace.setDescription(getUsername() + "'s User Space");
    }
    return uSpace;
  }

//  public void setPassword(dpsace, Password) { password = s; }
//  public String getPassword() { return password; }
//  public void setRole(String s) { role = s; }
//  public String getRole() { return role; }

  public boolean isValid(){
    if (null==getUsername() || getUsername().length()<4) return false;
    return true;
  }
  protected void materialize(String space) {}
  
  private String alias, firstName, lastName, email, phone;
  private boolean isActive=true;
  private UserSpaceBase uSpace=null;  
  private static String DESIGNSTATE_USER="DesignState-user";
}
