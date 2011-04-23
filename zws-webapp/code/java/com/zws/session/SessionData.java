package com.zws.session;

import javax.servlet.http.HttpSession;

public class SessionData {
  private SessionData(){}

  public static void setSession(Object session){ sessionData.set((HttpSession)session); }
  public static HttpSession getSession() { return (HttpSession)sessionData.get(); }

  public static Member getMember() {
    return (Member)getSession().getAttribute(keyMEMBER);
  }

  private static ThreadLocal sessionData = new ThreadLocal();

  public static final String keyMEMBER = "__member";
}
