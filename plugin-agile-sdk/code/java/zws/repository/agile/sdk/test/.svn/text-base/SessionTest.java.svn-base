/* DesignState - Design Compression Technology
 * @author: arbind
 * @version: 1.0
 * Created on Oct 18, 2007 10:42:21 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.repository.agile.sdk.test;

import zws.repository.agile.sdk.AgileSDKRepositoryBase;
import zws.repository.agile.sdk.svc.AgileSDKSessionSvc; 
import zws.security.Authentication;

import java.util.Calendar;
import java.util.HashMap;

import com.agile.api.AgileSessionFactory;
import com.agile.api.IAdmin;
import com.agile.api.IAgileClass;
import com.agile.api.IAgileSession;

public class SessionTest {

  public static void main(String[] args) {
    SessionTest test = new SessionTest();
    //test.pooledTest();
    //test.straightTest();
    //test.timeoutTest();
    //test.loginForeverTest();
    test.threadedLoginTest();
  }  
  
  private void loginUsingNewThread(int sessionsPerThread) throws Exception  {
    ThreadedLogin t = new ThreadedLogin(this, sessionsPerThread);
    t.execute();
  }
  
  public void threadedLoginTest() {
    try {
      int threadCount = 3;
      int sessionsPerThread= 1;
      for (int i = 0; i<threadCount; i++) {
        System.out.print (i + " Thread ");
        loginUsingNewThread(sessionsPerThread);
        //sessions.logout(id);
      }
    }
    catch (Exception e) { 
      e.printStackTrace();
      {} //System.out.println("could not start Session: " + e.getMessage());
    }
  }
  
  public static void pooledTest() {
    String username = "admin";
    String password = "agile";
    Authentication id;
    try {
      id = new Authentication(username, password);
      AgileSDKSessionSvc sessions = initialize();
      int count = 200;
      for (int i = 0; i<count; i++) {
        System.out.print (i + "  ");
        sessions.login(id);
        //sessions.logout(id);
      }
    }
    catch (Exception e) { 
      e.printStackTrace();
      {} //System.out.println("could not start Session: " + e.getMessage());
    }
  }
  
  /*
   *       {} //System.out.println("wait for 0 minutes to logout");
      session = startSession(serverURL, username, password);
      stopSession(session);

      {} //System.out.println("wait for 1 minutes to logout");
      session = startSession(serverURL, username, password);
      Thread.sleep(1000*60*33);
      stopSession(session);

      
      {} //System.out.println("wait for 33 minutes to logout");
      session = startSession(serverURL, username, password);
      Thread.sleep(1000*60*33);
      stopSession(session);

   * 
   */

  private static void waitFor(int minutes) throws Exception {
    Thread.sleep(1000*60*minutes);
  }

  public static IAgileSession initSession() throws Exception {
    //String username = "admin";
    //String password = "agile";
    String username = "intralink";
    String password = "zero0";
    String serverURL;
    serverURL= "http://vm-agile-9-2:7778/Agile";
    //serverURL="http://vm-agile-oas-9213.dev.zerowait-state.com/Agile";   
    IAgileSession session = startSession(serverURL, username, password);
    return session;
  }

  private static String timeNow() {
    int hour, min=0, sec;
    Calendar n = Calendar.getInstance();
    hour = n.get(Calendar.HOUR_OF_DAY);
    min = n.get(Calendar.MINUTE);
    sec = n.get(Calendar.SECOND);
    String time = hour +":"+min+":"+sec;
    return time;
  }
  
  public static void timeoutTest() {
    try {
      IAgileSession session = initSession();
      int wait;

      showSession(session);
      doSomething(session);

      wait = 35;
      {} //System.out.println(timeNow() + " - Waiting for " + wait + "minutes...");
      waitFor(wait);
      {} //System.out.println(timeNow() +" - Done waiting for " + wait + "minutes.");

      showSession(session);
      doSomething(session);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }  

  public static void loginForeverTest() {
    IAgileSession session1, session2;
    
    try {
      for (int i=0;i<2;i++) {
        session1= initSession();
        showSession(session1);
        doSomething(session1);
      }
      //stopSession(session1);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }  

  
  public static void straightTest() {
    IAgileSession session;
    String username = "admin";
    String password = "agile";
    String serverURL;
    serverURL= "http://vm-agile-9-2:7778/Agile";
    serverURL="http://vm-agile-oas-9213.dev.zerowait-state.com/Agile";   
    try {
      int count = 2;
      for (int i = 0; i<count; i++) {
        System.out.print (i + "  ");
        session = startSession(serverURL, username, password);
        stopSession(session);
        //sessions.logout(id);
      }
    }
    catch (Exception e) { 
      e.printStackTrace();
      {} //System.out.println("could not start Session: " + e.getMessage());
    }    
  }

  private static void showSession(IAgileSession session) throws Exception {
    System.out.print(" Session ID: " + session.toString());
    System.out.print(" timeout: " + session.getTimeout());
    System.out.print(" is open: " + session.isOpen());
    {} //System.out.println();
    //System.out.print(" Note* session.getCurrentUser() throws error: java.lang.NoSuchFieldError: TABLE_RELATIONSHIPSAFFECTEDBY");
    //{} //System.out.println(" current User: " + session.getCurrentUser());
    {} //System.out.println();
  }

  private static void doSomething(IAgileSession session) throws Exception {
    try {
      long start, end;
      start = Calendar.getInstance().getTimeInMillis();      
      IAgileClass[] classes = session.getAdminInstance().getAgileClasses(IAdmin.CONCRETE);
      end = Calendar.getInstance().getTimeInMillis();
      {} //System.out.println("doing Something => Found " +classes.length+ " Agile Classes [" + (end-start) + "ms]!");
    }
    catch(Exception e) {
      throw e;
    }
  }

  
  private static synchronized IAgileSession startSession(String serverURL, String username, String password) throws Exception {
    IAgileSession session = null;
    {} //System.out.println("Starting session " + username +":"+password+ " into " + serverURL);

    HashMap params = new HashMap();
    params.put(AgileSessionFactory.USERNAME, username);
    params.put(AgileSessionFactory.PASSWORD, password);
    params.put(AgileSessionFactory.URL, serverURL);
    try {
    session = AgileSessionFactory.createSessionEx(params);
    }
    catch(Exception e) {
      //if (e.getMessage().toLowerCase().contains("the number of available sessions for the given server is exceeded"))
        //return retrySession(serverURL, username, password);
      //else throw e;
      throw e;
    }
    return session;
  }

  
  private static synchronized IAgileSession retrySession(String serverURL, String username, String password) throws Exception {
    IAgileSession session = null;
    {} //System.out.println("Retrying session " + username +":"+password+ " into " + serverURL);

    HashMap params = new HashMap();
    params.put(AgileSessionFactory.USERNAME, username);
    params.put(AgileSessionFactory.PASSWORD, password);
    params.put(AgileSessionFactory.URL, serverURL);
    session = AgileSessionFactory.createSessionEx(params);
    {} //System.out.println("Session Retried: ");
    {} //System.out.println("             ID: " + " " + session.toString());
    //{} //System.out.println("           User: " + session.getCurrentUser());
    {} //System.out.println("    Timeout    : " + session.getTimeout());
    {} //System.out.println("    Is Open    : " + session.isOpen());
    return session;
  }
  
  
  private static synchronized void stopSession(IAgileSession session) throws Exception {
    {} //System.out.println("Stopping Session: ");
    if (null==session) return;
    {} //System.out.println("             ID: " + " " + session.toString());
    //{} //System.out.println("           User: " + session.getCurrentUser());
    {} //System.out.println("     Is Open    : " + session.isOpen());
    if (null != session && session.isOpen()) {
      try { 
        session.close(); 
        {} //System.out.println("Session Stopped");
        }
      catch (Exception ignore) {
        ignore.printStackTrace();
        {} //System.out.println("Error closing session: " + ignore.getMessage()); 
        }
    }
  }

  
  public static AgileSDKSessionSvc initialize() throws Exception {
    String username = "admin";
    String password = "agile";
    String serverHost;

    serverHost= "vm-agile-9-2:7778";
    serverHost="vm-agile-oas-9213.dev.zerowait-state.com";   

    Authentication id;
    AgileSDKRepositoryBase base = new AgileSDKRepositoryBase();

    id = new Authentication(username, password);
    base.setDomainName("harris");
    base.setHostName(serverHost);
    base.setName("agile-sdk");
    base.setPort("80");
    base.setProtocol("http");
    base.setRepositoryName("agile-sdk");
    base.setServerName("node-0");
    base.setServicesPath("Agile");
    base.setSystemPassword("agile");
    base.setSystemUsername(username);
    AgileSDKSessionSvc sdkSVC = new AgileSDKSessionSvc(base);
    return sdkSVC;
  }

  public class ThreadedLogin extends zws.op.ThreadedOpBase {
    public ThreadedLogin(SessionTest t) { test = t; }
    public ThreadedLogin(SessionTest t, int sessions) { test = t; sessionsToMake=sessions; }
    public void executeRun() throws Exception {
      login();
    }
    
    public synchronized void login() throws Exception{
      String username = "admin";
      String password = "agile";
      Authentication id;
      id = new Authentication(username, password);
      {} //System.out.println(".");
      {} //System.out.println(".");
      try {
        for (int i = 0; i < sessionsToMake; i++) {
          System.out.print("Thread ID: " + this.getThread().getId() + "  ");
          System.out.print("Session...");
          IAgileSession session = SessionTest.initSession();      
          {} //System.out.println("Initialized! session ID=" + session);
        }
      }
      catch(Exception e) {
        {} //System.out.println("Failed: " + e.getMessage());        
      }
      {} //System.out.println(".");
      {} //System.out.println(".");
    }
    
    SessionTest test = null;
    int sessionsToMake=1;
  }


}

/*
ID:  com.agile.api.pc.Session@fa7e74
ID:  com.agile.api.pc.Session@fa7e74
ID:  com.agile.api.pc.Session@80f4cb
ID:  com.agile.api.pc.Session@80f4cb
 */