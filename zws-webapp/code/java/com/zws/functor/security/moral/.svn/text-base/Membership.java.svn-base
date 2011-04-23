package com.zws.functor.security.moral;

import com.zws.application.Constants;

import java.util.StringTokenizer;

public class Membership extends Moral {

  public Boolean check() {
    boolean grant = (checkUsernames() && checkRoles() && checkGroups());
    if (GRANT.equalsIgnoreCase(getMode())) return new Boolean(grant);
    else return new Boolean(!grant);
  }

  private boolean checkUsernames(){
    if (null==getUsernames()) return true;
    if (ALL.equalsIgnoreCase(getUsernames().trim())) return true;
    StringTokenizer tok = new StringTokenizer(getUsernames(), Constants.DELIMITER);
    if (!tok.hasMoreTokens()) return getUser().getUsername().equalsIgnoreCase(getUsernames().trim());
    while (tok.hasMoreTokens())
      if (getUser().getUsername().equalsIgnoreCase(tok.nextToken().trim())) return true;
    return false;
  }

  private boolean checkRoles(){
    if (null==getRoles()) return true;
    if (ALL.equalsIgnoreCase(getRoles().trim())) return true;
    StringTokenizer tok = new StringTokenizer(getRoles(), Constants.DELIMITER);
    if (!tok.hasMoreTokens()) return getUser().getRole().equalsIgnoreCase(getRoles().trim());
    while (tok.hasMoreTokens())
      if (getUser().getRole().equalsIgnoreCase(tok.nextToken().trim())) return true;
    return false;
  }

  private boolean checkGroups(){ return true; } //++todo: add groups


  public String getMode() { return mode; }
  public void setMode(String s) { mode=s; }

  public String getUsernames() { return usernames; }
  public void setUsernames(String s) { usernames=s; }
  public String getRoles() { return roles; }
  public void setRoles(String s) { roles=s; }
  public String getGroups() { return groups; }
  public void setGroups(String s) { groups=s; }

  private String mode=GRANT;
  private String usernames=null;
  private String roles=null;
  private String groups=null;

  private static String GRANT="grant";
  private static String DENY="deny";
  private static String ALL="*";
}
