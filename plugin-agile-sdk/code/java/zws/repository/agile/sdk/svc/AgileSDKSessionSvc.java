/*
 * DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Jul 10, 2007 11:29:59 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */
package zws.repository.agile.sdk.svc;

import zws.repository.Repository;
import zws.security.Authentication;

import java.util.Calendar;
import java.util.HashMap;

import com.agile.api.IAgileSession;
import zws.repository.agile.sdk.AgileSDKRepositoryBase;

/**
 * The Class SessionUtility.
 *
 * @author ptoleti
 */
public class AgileSDKSessionSvc {

  /**
   * The Constructor.
   *
   * @param repository repository
   */
  public AgileSDKSessionSvc(Repository repository) {
    uri = repository.getHostName();
    port = repository.getPort();
    agilePath = repository.getServicesPath();
    systemUserName = repository.getSystemUsername();
    systemPassword = repository.getSystemPassword();
    if (repository instanceof AgileSDKRepositoryBase) {
      overrideCredentials = ((AgileSDKRepositoryBase) repository).getOverrideCredentials();
    }
  }

  /**
   * Gets the URL.
   *
   * @return the URL
   */
  public String getURL() {
    return "http://" + uri + ":" + port + "/" + agilePath;
  }

  private String key(Authentication id) {
    String key = getURL() + DOT + id.getUsername();
    return key;
  }
  
  private String timeNow() {
    Calendar c = Calendar.getInstance();
    int h = c.get(Calendar.HOUR_OF_DAY);
    int m = c.get(Calendar.MINUTE);
    int s = c.get(Calendar.SECOND);
    String t = h + ":" + s + ":" + m;
    return t;
  }
  
  /**
   * Login.
   *
   * @param id the id
   *
   * @return the i agile session
   *
   * @throws Exception the exception
   */
  public synchronized IAgileSession login(Authentication id) throws Exception {
    IAgileSession session = null;
    
    if (overrideCredentials) id = getSystemAuthentication();
    
    try {
      if (usrSessionMap.containsKey(key(id))) {
        session = (IAgileSession) usrSessionMap.get(key(id));
        if (!session.isOpen()) {
          stopSession(id);
          session = startSession(id);
       }
      } 
      else {
        session = startSession(id);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
      throw ex;
    }
    return session;
  }

  /**
   * Logout.
   *
   * @param id the id
   *
   * @throws Exception the exception
   */
  public synchronized void logout(Authentication id) throws Exception {
    
    if (overrideCredentials) id = getSystemAuthentication();

    stopSession(id);
    return;
  }

  /**
   * Gets the agile session.
   *
   * @return the agile session
   *
   * @throws Exception the exception
   */
  protected synchronized IAgileSession systemLogin() throws Exception {
    return login(getSystemAuthentication());
  }

  private synchronized IAgileSession startSession(Authentication id)
      throws Exception {
    IAgileSession session = null;
    
    if (usrSessionMap.containsKey(key(id))) {
      session = (IAgileSession) usrSessionMap.get(key(id));
    }

    if (null == session) {
      AgileLoginThread loginThread = new AgileLoginThread(id, getURL());
      loginThread.execute();
      while(!loginThread.isCompleted()) {
        Thread.sleep(100);
      }
      session = (IAgileSession)loginThread.getResult();
      loginThread.clearThread();
      System.gc();      
      if(null!= session) {
        usrSessionMap.put(key(id), session);
      }
    }
    return session;
  }

 
  private synchronized void stopSession(Authentication id) throws Exception {
    if (!usrSessionMap.containsKey(key(id))) {
      return;
    }
    IAgileSession session = (IAgileSession) usrSessionMap.remove(key(id));
    if (null != session) {
      try { 
        session.close(); 
      }
      catch (Exception ignore) { 
         
        }
    }
  }

  /**
   * Gets the default authentication.
   *
   * @return Authentication
   */
  protected Authentication getSystemAuthentication() {
    Authentication id = null;
    try {
      id = new Authentication(systemUserName, systemPassword);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return id;
  }

  // session

  /** usrSessionMap. */
  private static HashMap usrSessionMap = new HashMap();

  /** The agile path. */
  private String agilePath = null; // "Agile";

  /** The port. */
  private String port = null; // "7778";

  /** The uri. */
  private String uri = null; // "vm-agile-9-2";

  /** The system user name. */
  private String systemUserName = null;

  /** The system password. */
  private String systemPassword = null;
  
  private static String DOT = ".";
  
  private boolean overrideCredentials = false;
}

