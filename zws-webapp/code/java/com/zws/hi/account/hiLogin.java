package com.zws.hi.account;

import com.zws.domo.account.User;
import com.zws.hi.hiItem;
import com.zws.service.account.*;
import com.zws.session.Member;
import com.zws.session.SessionData;

import zws.Server;
import zws.application.Names;
import zws.exception.NotAFile;
import zws.hi.application.panelbar.hiPanelBar;
import zws.hi.application.panelbar.xml.PanelBarLoader;

import com.zws.application.Properties;

import java.io.File;
import java.io.FileReader;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public class hiLogin extends hiItem {
  private String username, password;
  private AccountService service;
  private static String FLAG_AUTHENTICATING = "athenticating";

  public void bind() { service = AccountService.getService(); }
  public void registerRequiredFields(){
    require("authenticate", "username");
    require("authenticate", "password");
  }
  public void registerEventHandlers(){
    //To:Do EventNames should be looked up from resources file
    //registerHandler("Login", "authenticate");
  }
  public void setUsername(String s) {
    username = s;
    username = username.replaceAll("<", "-");
    username = username.replaceAll(">", "-");
  }
  public String getUsername() { return username; }
  public void setPassword(String s) {
    password = s;
    password = password.replaceAll("<", "-");
    password= password.replaceAll(">", "-");
  }
  public String getPassword() { return password; }

  public boolean sessionIsExpired(){
    if ("authenticate".equals(getEvent())) return false;
    return super.sessionIsExpired();
  }

  public String authenticate() {
    if (null==startPage) {
	    try {
	      startPage = Properties.get(Properties.startPage);
	      if (null==startPage || "".equals(startPage)) throw new NoSuchFieldError(Properties.startPage);
	    }
	    catch (NoSuchFieldError e) {
	      logFormError("cfg.start.page.not.defined");
	      return ctrlERROR;
	    }
    }
    try {
      if (Server.debugMode()) {}{} //System.out.println("Authenticating " + getUsername());
      User user = service.authenticate(getUsername(), getPassword());
      if (Server.debugMode()) {}{} //System.out.println("Authenticated " + getUsername());
      Member member = new Member(user);
      if (Server.debugMode()) {}{} //System.out.println("Storing to session: " + getUsername());
      getSession().setAttribute(SessionData.keyMEMBER, member);
      getSession().setAttribute("authenticating", new Object());
      if (Server.debugMode()) {} //System.out.println("session: " + getSession().getId());
      logFormStatus("msg.welcome", user.getFirstName());
      //return ctrlOK;
      loadPanelBar(member);
      return startPage;
    }
    catch (UserNotFoundException e) {
      if (Server.debugMode()) {} //System.out.println("User not found: " + getUsername());
      logFormError("err.user.not.found", username);
      return ctrlERROR;
    }
    catch (InvalidPasswordException e) {
      if (Server.debugMode()) {} //System.out.println("Invalid password for " + getUsername());
      logFormError("err.invalid.password", username);
      return ctrlERROR;
    }
    catch (Exception e) {
      {} //System.out.println("Unknown error - check database!");
      logFormError("err.unknown");
      e.printStackTrace();
      {} //System.out.println("Unknown error - check database!");
      return ctrlERROR;
    }
  }

  public String logout() {
    if (null == getSession()) return ctrlOK;
    getSession().setAttribute(SessionData.keyMEMBER, null);
    getSession().invalidate();
    return ctrlOK;
  }

  private void loadPanelBar(Member member) throws Exception {
    String userAgent = getHttpRequest().getHeader("User-Agent");
    PanelBarLoader loader = new PanelBarLoader(member, getSession(), userAgent);
    String panelBarXML = Properties.get(Names.WEB_APP_CONFIG_DIR) + Names.PATH_SEPARATOR + "panel-bar.xml";
    XMLReader xr = getParser(false).getXMLReader();
    xr.setContentHandler(loader);
    xr.setErrorHandler(loader);
    File xml = new File(panelBarXML);
    if (!xml.exists()) throw new NotAFile(xml);
    FileReader r = new FileReader(xml);
    xr.parse(new InputSource(r));
  }

  protected static SAXParser getParser(boolean validate) throws SAXException, ParserConfigurationException {
	  SAXParserFactory factory = SAXParserFactory.newInstance();
	  factory.setValidating(validate);
	  return factory.newSAXParser();
	}

  public void setStartPage(String s) { startPage=s; }
  public String getStartPage() { return startPage; }
  public String startPage=null;
}

