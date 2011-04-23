package com.zws.hi.account;
import com.zws.domo.account.User;
import com.zws.hi.hiItem;
import com.zws.service.account.AccountService;
import com.zws.service.account.DuplicateUsernameException;
//import zws.security.Password;

public class hiUser extends hiItem {
  private String username,password, role, newPassword, confirmationPassword, firstName, lastName, email, phone;
  private AccountService service;
  private boolean accountActivated;
  private User user;
  private static String keyMEMBER = "member";

  public void bind(){

    service = AccountService.getService();
    {} //System.out.println("Service value"+ service);

    if ("add".equalsIgnoreCase(getNav()) || "add".equalsIgnoreCase(getEvent())) {
      user = new User();
      return;
    }

    //if (null!=user) return;
    if (null==getID()) setID(keyMEMBER);
    if (keyMEMBER.equals(getID())) {
      if (null==getMember()) return;
      user = getMember().getUser();
    }
    else try{ user = service.find(getID()); } catch (Exception e){e.printStackTrace();}
  }

  public void refresh() {
    setUsername(null);
    setPassword(null);
    setNewPassword(null);
    setConfirmationPassword(null);
    setFirstName(null);
    setLastName(null);
    setEmail(null);
    setPhone(null);
    setRole(null);
    setAccountActivated(false);
  }

  public void render() {
    if (null==user) return;
    setUsername(user.getUsername());
    setFirstName(user.getFirstName());
    setLastName(user.getLastName());
    setEmail(user.getEmail());
    setPhone(user.getPhone());
    setRole(user.getRole());
    setAccountActivated(true);
  }

  public void registerRequiredFields(){
    require("changePassword", "password");
    require("changePassword", "newPassword");
    require("changePassword", "confirmationPassword");
    require("newPassword", "newPassword");
    require("newPassword", "confirmationPassword");
    require("add", "username");
    require("add", "newPassword");
    require("add", "confirmationPassword");
  }

  public void setUsername(String s) { username = s; }
  public String getUsername() { return username; }
  public void setPassword(String s) { password = s; }
  public String getPassword() { return password; }
  public void setNewPassword(String s) { newPassword = s; }
  public String getNewPassword() { return newPassword; }
  public void setConfirmationPassword(String s) { confirmationPassword = s; }
  public String getConfirmationPassword() { return confirmationPassword; }
  public String getRole() { return role; }
  public void setRole(String r) { role = r; }
  public void setFirstName(String s) { firstName = s; }
  public String getFirstName() { return firstName; }
  public void setLastName(String s) { lastName = s; }
  public String getLastName() { return lastName; }
  public void setEmail(String s) { email = s; }
  public String getEmail() { return email; }
  public void setPhone(String s) { phone = s; }
  public String getPhone() { return phone; }
  public void setAccountActivated(boolean x) { accountActivated = x; }
  public boolean getAccountActivated() { return accountActivated; }

  public String changePassword(){
    validateRequiredInputFields();
		String encodedPwd = null;
/*
    try {
			encodedPwd = new Password(getPassword()).getEncoding();

    } catch (Exception e) {
      logFormError("run.err.operation.failed");
      {} //System.out.println("Error: " + e.getMessage());
      return ctrlERROR;
    }
*/
		{} //System.out.println("Actual password: "+ encodedPwd);
		{} //System.out.println("User password: "+ user.getPassword());

		if (! getPassword().equals(user.getPassword())){
			logFormError("err.invalid.password");
			return ctrlERROR;
		}

		if (!getNewPassword().equals(getConfirmationPassword())){
			logFormError("err.mismatched.password");
			return ctrlERROR;
			}

		try {
      service.updatePassword(user.getUsername(), getNewPassword());
      getMember().getUser().setPassword(getNewPassword());
      logFormStatus("ev.password.updated");
      return ctrlOK;
    } catch (Exception e) {
      logFormError("run.err.operation.failed");
      {} //System.out.println("Error: " + e.getMessage());
      return ctrlERROR;
    }
  }

  public String setNewPassword(){

    validateRequiredInputFields();

    if (!getNewPassword().equals(getConfirmationPassword())){
      logFormError("err.mismatched.password");
      return ctrlERROR;
    }
    try {
      service.updatePassword(user.getUsername(), getNewPassword());
      logFormStatus("ev.password.updated");
      return ctrlOK;
    }
    catch (Exception e) {
      logFormError("run.err.operation.failed");
      return ctrlERROR;
    }
  }

  public boolean isBound() { return null!=user; }

  public String save(){
    user.setRole(getRole());
    user.setFirstName(getFirstName());
    user.setLastName(getLastName());
    user.setEmail(getEmail());
    user.setPhone(getPhone());
    try {
      service.update(user);
      if (null!= getID() && getID().equals(keyMEMBER)) getMember().setUser(user);
      logFormStatus("msg.item.saved", getFirstName() + " " + getLastName());
      setNav(null);
      return ctrlOK;
    }
    catch (Exception e) {
      e.printStackTrace();
      logFormError(keyRUNTIME_ERROR);
      return ctrlSYSTEM_ERROR;
    }
  }

  public void validateInputFields() {
    if ("add".equals(getEvent()) && !getNewPassword().equals(getConfirmationPassword()))
      logFormError("err.mismatched.password");
  }

  public String add(){
    user.setUsername(getUsername());
    user.setPassword(getNewPassword());
    user.setFirstName(getFirstName());
    user.setLastName(getLastName());
    user.setEmail(getEmail());
    user.setPhone(getPhone());
    user.setRole(getRole());
    try {
      service.add(user);
      logFormStatus("msg.item.added", getFirstName() + " " + getLastName());
      setNav(null);
      setEvent(null);
      return ctrlOK;
    }
    catch (DuplicateUsernameException ex) {
      logFormError("err.username.not.available", getUsername());
      return ctrlERROR;
    }
    catch (Exception e) {
      e.printStackTrace();
      logFormError(keyRUNTIME_ERROR);
      return ctrlSYSTEM_ERROR;
    }
  }

}
