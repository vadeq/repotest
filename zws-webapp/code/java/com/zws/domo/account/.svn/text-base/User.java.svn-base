package com.zws.domo.account;
import com.zws.domo.Domo;

public class User extends Domo {
  public User(){}
  public User(String userName){ username=userName;}
  public String getID(){ return username; } //override!

  public boolean isValid(){
    if (null==username || username.length()<4) return false;
    if (null==password || password.length()<4) return false;
    return true;
  }

  public void setUsername(String s) { if(s==null) username=""; else username = s; }
  public String getUsername() { return username; }
  public void setPassword(String s) { if(s==null) password=""; else password = s; }
  public String getPassword() { return password; }
  public void setFirstName(String s) { if(s==null) firstName=""; else firstName = s; }
  public String getFirstName() { return firstName; }
  public void setLastName(String s) { if(s==null) lastName=""; else lastName = s; }
  public String getLastName() { return lastName; }
  public void setEmail(String s) { if(s==null) email=""; else email = s; }
  public String getEmail() { return email; }
  public void setPhone(String s) { if(s==null) phone=""; else phone = s; }
  public String getPhone() { return phone; }
  public void setRole(String s) { if(s==null) role=""; else role = s; }
  public String getRole() { return role; }
  public void setAccountIsActive(boolean x) { accountIsActive = x; }
  public boolean getAccountIsActive() { return accountIsActive; }

  private String username, password, firstName, lastName, email, phone, role;
  private boolean accountIsActive;
}