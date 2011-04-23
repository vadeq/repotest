package com.zws.hi.account;
import com.zws.domo.account.User;
import com.zws.hi.hiList;
import com.zws.service.account.AccountService;
import com.zws.service.account.UserNotFoundException;

public class hiUserList extends hiList {
  public static String ROLE_GUEST = "guest";
  public static String ROLE_USER = "user";
  public static String ROLE_ENGINEER = "engineer";
  public static String ROLE_ADMIN = "admin";


  private AccountService service;
  public void bind() throws Exception { service = AccountService.getService(); }
  public void render () throws Exception { setItems(service.findAll()); }

  public String delete() {
    if (this.getMember().getUser().getUsername().equals(getID())){
      logFormError("err.can.not.delete.self", getID());
      return ctrlERROR;
    }
    try {
      service.delete(getID());
      logFormStatus("msg.item.deleted", getID());
      return ctrlLIST;
    }
    catch (UserNotFoundException e) {
      logFormError("err.user.not.found", getID());
      return ctrlERROR;
    }
    catch (Exception e) {
      logFormError("system.err.event", "delete", e.getMessage(), "");
      return ctrlSYSTEM_ERROR;
    }
  }

  public String changeRole() {
    if (this.getMember().getUser().getUsername().equals(getID())){
      logFormError("err.can.not.change.own.role", getID());
      return ctrlERROR;
    }
    try {
      String role;
      User u = service.find(getID());
      if (u.getRole().equals(ROLE_ADMIN)) role = ROLE_USER;
      else if (u.getRole().equals(ROLE_USER)) role = ROLE_ENGINEER;
      else role = ROLE_ADMIN;
      service.updateRole(getID(), role);
      logFormStatus("msg.role.changed", getID());
      return ctrlLIST;
    }
    catch (Exception e) {
      logFormError("system.err.event", "delete", e.getMessage(), "");
      return ctrlSYSTEM_ERROR;
    }
  }

}

