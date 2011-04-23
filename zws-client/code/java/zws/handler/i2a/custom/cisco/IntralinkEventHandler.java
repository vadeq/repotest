package zws.handler.i2a.custom.cisco; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.AgileAccess;
import zws.security.Authentication;
import zws.util.RoutedEventBase;
import zws.application.Names;

import zws.event.custom.cisco.CustomCiscoEventBase;
import zws.handler.i2a.BaseI2AHandler;

import java.io.*;
import java.io.DataInputStream;
import java.io.Serializable;
import java.net.*;
import java.util.*;

public abstract class IntralinkEventHandler extends BaseI2AHandler implements Serializable{
  public abstract Class getEventClass();
  public abstract CustomCiscoEventBase convertEvent(RoutedEventBase event);
  
  public abstract void handleForSource(RoutedEventBase event) throws Exception;

  protected void loadAgileClient(RoutedEventBase ev) throws Exception {
    setAgileClient(AgileAccess.getAccess(getAgileServerName(), getAgileDatasourceName(), getAuthentication()));
  }

  public String getAgileDomainName() { return agileDomainName; }
  public void setAgileDomainName(String s) { agileDomainName=s; }
  public String getAgileServerName() { return agileServerName; }
  public void setAgileServerName(String s) { agileServerName=s; }
  public String getAgileDatasourceName() { return agileDatasourceName; }
  public void setAgileDatasourceName(String s) { agileDatasourceName=s; }
  public String getAgileUserName() { return agileUserName; }
  public void setAgileUserName(String s) { agileUserName=s; }
  public String getAgilePassword() { return agilePassword; }
  public void setAgilePassword(String s) { agilePassword=s; }
  public Authentication getAuthentication() {
    try { if (null==id) id= new Authentication(agileUserName, agilePassword); }
    catch(Exception ignore) {}
    return id; 
 }
  
  private String agileDomainName=null;
  private String agileServerName=null;
  private String agileDatasourceName=null;
  private String agileUserName=null;
  private String agilePassword=null;
  private Authentication id=null; 
  
  public void handleEvent(RoutedEventBase event) {
    try {
	    handleForSource(event);
	    CustomCiscoEventBase ev=convertEvent(event);
	    ev.setAgileDomainName(getAgileDomainName());
	    ev.setAgileServerName(getAgileServerName());
	    ev.setAgileDatasourceName(getAgileDatasourceName());
	    ev.setDomainName(event.getDomainName());
	    ev.setServerName(event.getServerName());
	    ev.setDatasourceName(event.getDatasourceName());
	    fire(ev);
    }
    catch(Exception e) { e.printStackTrace(); }
  }
  
  public void preHandleEvent(RoutedEventBase event) throws Exception {}
  public void postHandleEvent(RoutedEventBase event) throws Exception {}
  public void reset() throws Exception{}
  
  // fire framework copy and pasted from zws.agile.px.pxActionBase
	protected String fire(RoutedEventBase ev) throws Exception {
	    if (null==ev) return null;
	    String urlPath = getServiceURLPath(DESIGNSTATE_EVENT_SERVICE_URL, URL_ACTION_FIRE_EVENT);
	    debug("firing event: " + ev);
	    urlPath = addURLParameter(urlPath, URL_PARAMETER_EVENT, ev.toString());
	    String response = invokeService(urlPath);
	    return response;
		}

	  protected String invokeService(String urlPath) throws Exception {
	    debug("invoking service " + urlPath);
	    String nextLine = "";
		  String response = "";
	    DataInputStream inStream;
	    URL url = new URL(urlPath);
			URLConnection urlConn = url.openConnection();
		  urlConn.setDoInput(true);
		  urlConn.setDoOutput(true);
		  urlConn.setUseCaches(false);
		  inStream = new DataInputStream(urlConn.getInputStream());
		  BufferedReader d = new BufferedReader(new InputStreamReader(inStream));
       
		  while((nextLine = d.readLine()) != null) { 
	      response += nextLine;
        inStream.close();
        d.close();
		  }
		  return response;
		}

	  protected URL materializeURL(String path) throws MalformedURLException {
	    return new URL(path);
	  }

	  protected String urlEncode(Object o) {
	    if (null==o) return "";
      try {
  	    String s = URLEncoder.encode(o.toString(), "UTF-8");
	      return s;
      }
      catch(UnsupportedEncodingException e) {
        e.printStackTrace();
        return "unsupported encoding exception!!";
      }
	  }
	  
	  protected String getServiceURLPath(String serviceProperty, String action) throws Exception {  
	    String urlPath = getProperty(serviceProperty);
	    urlPath = setURLAction(urlPath, action, getDefaultUsername(), getDefaultPassword());
	    return urlPath;
	  }
	  
	  protected String getServiceURLPath(String serviceProperty, String action, String username, String password) throws Exception {  
	    String urlPath = getProperty(serviceProperty);
	    urlPath = setURLAction(urlPath, action, username, password);
	    return urlPath;
	  }

	  protected String setURLAction(String urlPath, String action, String username, String password) {
	    String x = setURLAction(urlPath, action);
	    x = addURLParameter(x, URL_PARAMETER_USERNAME, username);
	    x = addURLParameter(x, URL_PARAMETER_PASSWORD, password);
	    return x;
	  }

	  protected String setURLAction(String urlPath, String action) {
	    return setFirstURLParameter(urlPath, URL_PARAMETER_ACTION, action); 
	  }

	  protected String addURLParameter(String urlPath, String param, String value) {
	    return setNextURLParameter(urlPath, param, value); 
	  }

	  private String setFirstURLParameter(String urlPath, String key, String value) {
	    return urlPath + Q + key + EQ + value; 
	  }

	  private String setNextURLParameter(String urlPath, String key, String value) {
	    String v;
      try {
	      v = URLEncoder.encode(value, "UTF-8");
	      return urlPath + AND + key + EQ + v;
      }
      catch (UnsupportedEncodingException e) {
        e.printStackTrace();
        return "unsupported-encoding-exception!!";
      }
	  }

	  protected String getDefaultUsername() throws Exception { return getProperty(DESIGNSTATE_DEFAULT_USERNAME); }
	  protected String getDefaultPassword() throws Exception { return getProperty(DESIGNSTATE_DEFAULT_PASSWORD); }

	  protected String getProperty(String key) throws Exception {
		  if (null==apcResource) loadProperties();
		  //log("looking up " + key);
		  String value = apcResource.getString(key);
		  //log(key + "=" + value);
		  return value;
		}
	  private static void loadProperties() throws Exception {
      Locale defaultLocale = Locale.getDefault();
      apcResource  = ResourceBundle.getBundle("ZWS_PX",defaultLocale);
      String debugMode = apcResource.getString(DESIGNSTATE_PX_IN_DEBUG_MODE);
      if ("true".equalsIgnoreCase(debugMode)) setDebugModeOn();
      //debug("ZWS_PX loaded");
  	}

	  
	  protected void debug(Object o) {
      if (null==o) debug("Object is NULL!"); 
      else debug("Object: " + o.toString());
     }
     
     protected void debug(String s) {
       if (!inDebugMode) return;
       try { log(s); {} //System.out.println(s); }
       
       }
       catch (Exception e) { e.printStackTrace(); }
     }
   	
     protected void debug(Exception x) {
   	  if (!inDebugMode) return;
   	  String classname = x.getClass().getName();
   	  String msg = classname + ": " + x.getMessage();
   	  try { log(msg); {} //System.out.println(msg); }
   	  
   	  }
   	  catch (Exception e) { e.printStackTrace(); }
   	}

     protected void debug(Throwable x) {
   	  if (!inDebugMode) return;
   	  String classname = x.getClass().getName();
   	  String msg = classname + ":" + x.getMessage();
   	  try { log(msg); {} //System.out.println(msg); }
   	  
   	  }
   	  catch (Exception e) { e.printStackTrace(); }
   	}
   	
     protected void debugThrow(Exception x) throws Exception {
   	  if (!inDebugMode) throw x;
   	  try { log(x.getMessage()); {} //System.out.println(x.getMessage()); }
   	  
   	  }
   	  catch (Exception e) { e.printStackTrace(); }
   	  throw x;
   	}

     protected void debugThrow(Throwable x) throws Throwable{
   	  if (!inDebugMode) throw x;
   	  try { log(x.getMessage()); {} //System.out.println(x.getMessage()); }
   	  
   	  }
   	  catch (Exception e) { e.printStackTrace(); }
   	  throw x;
   	}
     private synchronized void log(String s) throws Exception {
   	  FileWriter logger = new FileWriter(getLogFilePath(), true);
   	  logger.write(s + zws.application.Names.NEW_LINE);
       logger.flush();
       logger.close();
   	}
     
   private String getLogFilePath() {
     String s=null;
     s = zws.application.Properties.get(Names.ZWS_ROOT_DIR) + Names.PATH_SEPARATOR + "ZWS_PX.log";
     return s;
   }

  protected static void setDebugModeOn() { inDebugMode=true; }


  //Properties: DesignState Service URLs
  protected static String DESIGNSTATE_APC_SERVICE_URL="designstate.apc.service.url";
  protected static String DESIGNSTATE_EVENT_SERVICE_URL="designstate.event.service.url";

  //URL parameter names
  protected static String URL_PARAMETER_NUMBER = "number";
  protected static String URL_PARAMETER_AUTHOR= "author";

  //PRIVATE PROPERTIES - KEEP OFF!
  
  //Properties: DesignState Routing
  static String DESIGNSTATE_DOMAIN_NAME="designstate.domain.name";
  static String DESIGNSTATE_SERVER_NAME="designstate.server.name";
  static String DESIGNSTATE_DATASOURCE_NAME="designstate.datasource.name";

  //Properties: DesignState Default Authentication 
  static String DESIGNSTATE_DEFAULT_USERNAME="designstate.default.username";
  static String DESIGNSTATE_DEFAULT_PASSWORD="designstate.default.password";

  //URL parameter names 
  static String URL_PARAMETER_ACTION ="event";
  static String URL_PARAMETER_EVENT ="ev";
  static String URL_PARAMETER_USERNAME = "username";
  static String URL_PARAMETER_PASSWORD = "password"; 

  //URL actions (private)
  static String URL_ACTION_FIRE_EVENT = "fire";   
  
  //Properties: Agile Command Line Execution 
  static String AGILE_CMDLN_URL="agile.cmdln.url";
  static String AGILE_CMDLN_USERNAME="agile.cmdln.username";
  static String AGILE_CMDLN_PASSWORD="agile.cmdln.password";

  //Properties: Debug Mode 
  static String DESIGNSTATE_PX_IN_DEBUG_MODE="designstate.px.in.debug.mode";
  static String DESIGNSTATE_LOG_FILE="designstate.log.file";
  
  static Map properties = null;
  static ResourceBundle apcResource = null;
  static boolean inDebugMode=false;
 	//static Logger logger = Logger.getLogger(pxActionBase.class);

  static String Q = "?";
	static String EQ = "=";
	static String AND = "&";
}
