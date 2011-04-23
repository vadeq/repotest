package zws.agile.px;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur, Jason Brown @agile.com
Created on Mar 3, 2006
@version: 1.0
Copywrite (c) 2006 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.util.RoutedEventBase; 
import zws.event.*;
import zws.event.eco.*;
import zws.event.hi.i2a.*;
import zws.origin.Origin;

import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.Locale;
import java.util.HashMap;
import java.util.ResourceBundle;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

import com.agile.px.*;
import com.agile.api.*;

public abstract class pxActionBase implements ICustomAction {

	protected zws.util.RoutedEventBase materializeEvent(IAgileSession session, INode action, IDataObject data) throws Exception {
		RoutedEventBase ev=null;
	  if (null==data) ev = materializeEvent(action);
    else if (data instanceof IChange ) ev = materializeEvent(action, (IChange)data);
	  else if (data instanceof IItem ) ev = materializeEvent(action, (IItem)data);
	  else ev = new zws.util.RoutedEventBase();
	  loadRouting(ev);
    if (null!=session)  ev.set("agile-user", session.getCurrentUser().getName());
	  return ev;
	}


	protected void loadRouting(RoutedEventBase ev) throws Exception {
    ev.setDomainName(getProperty(DESIGNSTATE_DOMAIN_NAME));
    ev.setServerName(getProperty(DESIGNSTATE_SERVER_NAME));
    ev.setDatasourceName(getProperty(DESIGNSTATE_DATASOURCE_NAME));
    if (null==ev.getDatasourceType()) ev.setDatasourceType(Origin.FROM_AGILE);
	}

	private RoutedEventBase materializeEvent(INode action) throws APIException {
	  String hiAction = lookuphiAction(action.getName());
	  Invoke ev = new Invoke();
	  ev.setAction(hiAction);
	  return ev;
	}

	private RoutedEventBase materializeEvent(INode actionNode, IItem data) throws APIException {
		String name = data.getName();
	  String hiAction = lookuphiAction(actionNode.getName());
	  hiProcessItemBase ev = null;
	  if (hiAction.toLowerCase().indexOf("promote latest")>-1) ev = new PromoteLatestItem(name);
	  else if (hiAction.toLowerCase().indexOf("promote")>-1) ev = new PromoteItem(name);
	  else if (hiAction.toLowerCase().indexOf("attach")>-1 && 
	          hiAction.toLowerCase().indexOf("file")>-1) ev = new AttachFiles(name);
	  else if (hiAction.toLowerCase().indexOf("update")>-1 && 
	          hiAction.toLowerCase().indexOf("new")>-1 && 
	          hiAction.toLowerCase().indexOf("part")>-1) ev = new UpdateWithNewPartNumber(name);
	  else if (hiAction.toLowerCase().indexOf("update")>-1) ev = new UpdateItem(name);
	  else if (hiAction.toLowerCase().indexOf("inactivate")>-1) ev = new Inactivate(name);
	  else if (hiAction.toLowerCase().indexOf("broadcast")>-1) {
      if (hiAction.toLowerCase().indexOf("part#")>-1) ev = new BroadcastPartNumber(name);	      
      else if (hiAction.toLowerCase().indexOf("rev")>-1) ev = new BroadcastRevision(name);	      
      else if (hiAction.toLowerCase().indexOf("life cycle phase")>-1) ev = new BroadcastLifeCyclePhase(name);	      
      else if (hiAction.toLowerCase().indexOf("plm status")>-1) ev = new BroadcastPLMStatus(name);
	  }
	  if (null==ev) {
	    ev = new hiProcessItemBase();
		  ev.setAction(hiAction);	    
	  }
	  ev.set("agile-class", data.getAgileClass().getName());
	  return ev;
	}

	private RoutedEventBase materializeEvent(INode actionNode, IChange eco) throws APIException {
	  ECOEventBase ev=null;
	  if (wasApproved(eco)) ev = new Approved();
	  else if (wasRejected(eco)) ev = new Rejected();
	  else ev = new StatusChanged();
	  ev.setName(eco.getName());
    ev.setECOStatus(eco.getStatus().getName());
	  ev.set("agile-class", eco.getAgileClass().getName());
    ev.setDatasourceType(Origin.FROM_AGILE_ECO);
	  return ev;
  }

	private boolean wasApproved(IChange eco) throws APIException {
	  if ("released".equalsIgnoreCase(eco.getStatus().getName())) return true;
	  return false;
	}

	private boolean wasRejected(IChange eco) throws APIException {
	  if ("canceled".equalsIgnoreCase(eco.getStatus().getName())) return true;
	  return false;
	}

	private String lookuphiAction(String actionLabel) {
	  return actionLabel;
	}
	
	protected String fire(RoutedEventBase ev) throws Exception {
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
      return "unsupported-encoding-exception";
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

  protected void initializePX() throws Exception {
    String file = getProperty(DESIGNSTATE_LOG_FILE);
  }
	
  private static void loadProperties() throws Exception {
    Locale defaultLocale = Locale.getDefault();
    apcResource  = ResourceBundle.getBundle("ZWS_PX",defaultLocale);
    String debugMode = apcResource.getString(DESIGNSTATE_PX_IN_DEBUG_MODE);
    if ("true".equalsIgnoreCase(debugMode)) setDebugModeOn();
    //debug("ZWS_PX loaded");
	}

	public IAgileSession startAgileSession() throws Exception {
 	  IAgileSession session=null;
	  String agileURL = getProperty(AGILE_CMDLN_URL);
	  String agileUsername= getProperty(AGILE_CMDLN_USERNAME);
 	  String agilePassword= getProperty(AGILE_CMDLN_PASSWORD);
    session = connect(agileURL, agileUsername, agilePassword);
    return session;
	}

  protected IAgileSession connect(String url, String user, String pass) throws APIException {
	  IAgileSession session=null; 	    
		debug(user + ":"+pass + " login to: " +url);
		AgileSessionFactory factory = AgileSessionFactory.getInstance(url);
		HashMap params = new HashMap();
		params.put(AgileSessionFactory.USERNAME, user);
		params.put(AgileSessionFactory.PASSWORD, pass);
		session = factory.createSession(params);
		debug(session.getCurrentUser().getName() + " loged in");
		return session; 
	}
	
  protected void disconnect(IAgileSession session)  {
		if ( session == null ) return;
		try {
      debug("logout" + session.getCurrentUser().getName());
		  session.close();
		  session=null;
		}
		catch (Exception e) { debug(e.getMessage()); }
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
	  FileWriter logger = new FileWriter(Names.PATH_SEPARATOR + getLogFilePath(), true);
	  logger.write(s + zws.application.Names.NEW_LINE);
    logger.flush();
    logger.close();
	}
  
  private String getLogFilePath() {
    String s=null;
    try {s = getProperty(DESIGNSTATE_LOG_FILE); }
    catch(Exception e) { }
    if (null==s || "".equals(s.trim())) s = Names.PATH_SEPARATOR + "ZWS_PX.log";
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

/*
protected void loadEvent(zws.event.Event ev, INode actionNode) throws APIException {
  if (null==actionNode) return;
  ev.set("agile-action", actionNode.getValue("type")); 
  ev.set("action-invoked", actionNode.getName());
  ev.set("action-description", actionNode.getValue("description"));
  //loadEvent(ev, "node-", actionNode.getProperties());
}

protected void loadEvent(zws.event.Event ev, IDataObject affectedObject) throws APIException {
	if (null==affectedObject) return;
  ev.set("agile-class", affectedObject.getAgileClass().getName());
  ev.set("agile-number", affectedObject.getName());
}

protected void loadEvent(zws.event.Event ev, String keyPrefix, IProperty[] props) throws APIException {
  int idx;
  Object val;
  String key;
  for (idx=0; idx < props.length; idx++) {
    key=null;
    val=null;
    key = keyPrefix + props[idx].getName();
    if (null==key) continue;
    while (ev.getProperties().containsKey(key)) key = "agile." + key; 
    val = props[idx].getValue();
    ev.set(key, val);
  }
}
*/
