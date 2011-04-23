package zws.repository.agile.px;
/*
  DesignState - Design Compression Technology.
  @author: Arbind Thakur, Jason Brown @agile.com
  Created on Mar 3, 2006
  @version: 1.0
  Copywrite (c) 2006 Zero Wait-State Inc. All rights reserved
*/

import zws.repository.agile.event.EventClient;
import zws.repository.agile.event.HTTPEventClient;
import zws.repository.agile.event.JMSEventClient;

import java.io.FileWriter;
import java.util.Map;
import java.util.Locale;
import java.util.ResourceBundle;
import java.text.SimpleDateFormat;

public abstract class PxActionBase{

	static SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss: ");

  public static EventClient materiazlizeEventClient() throws Exception {
    EventClient client = null;
    if(null != getProperty(DESIGNSTATE_EVENT_SERVICE_PROTOCOL)) {
      if("http".equalsIgnoreCase(getProperty(DESIGNSTATE_EVENT_SERVICE_PROTOCOL))) {
        client = new HTTPEventClient();
      } else if("jms".equalsIgnoreCase(getProperty(DESIGNSTATE_EVENT_SERVICE_PROTOCOL))) {
        client = new JMSEventClient();
      }
    }
    return client;
  }

  protected String getDefaultUsername() throws Exception { return getProperty(DESIGNSTATE_DEFAULT_USERNAME); }
  protected String getDefaultPassword() throws Exception { return getProperty(DESIGNSTATE_DEFAULT_PASSWORD); }

  protected static String getProperty(String key) {
    if (null==pxProperties) loadProperties();
    String value = pxProperties.getString(key);
    return value;
  }

  private static void loadProperties() {

    try {
      Locale defaultLocale = Locale.getDefault();
      pxProperties  = ResourceBundle.getBundle("ZWS_PX",defaultLocale);
      String debugMode = pxProperties.getString(DESIGNSTATE_PX_IN_DEBUG_MODE);

      if ("true".equalsIgnoreCase(debugMode)) setDebugModeOn();

    } catch (Exception e) {
      System.out.println("Error loading configuration, exiting..." + e.getMessage());
    }
  }


  public static void debug(Object o) {
   if (null==o) debug("Object is NULL!");
   else debug("Object: " + o.toString());
  }

  public static void debug(String s) {
    if (!inDebugMode) return;
    try { log(s); System.out.println(s); }
    catch (Exception e) { e.printStackTrace(); }
  }

  public static void debug(Exception x) {
    if (!inDebugMode) return;
    String classname = x.getClass().getName();
    String msg = classname + ": " + x.getMessage();
    try { log(msg); System.out.println(msg); }
    catch (Exception e) { e.printStackTrace(); }
  }

  public static void debug(Throwable x) {
    if (!inDebugMode) return;
    String classname = x.getClass().getName();
    String msg = classname + ":" + x.getMessage();
    try { log(msg); System.out.println(msg); }
    catch (Exception e) { e.printStackTrace(); }
  }

  public static void debugThrow(Exception x) throws Exception {
    if (!inDebugMode) throw x;
    try { log(x.getMessage()); System.out.println(x.getMessage()); }
    catch (Exception e) { e.printStackTrace(); }
    throw x;
  }

  public static void debugThrow(Throwable x) throws Throwable{
    if (!inDebugMode) throw x;
    try { log(x.getMessage()); System.out.println(x.getMessage()); }
    catch (Exception e) { e.printStackTrace(); }
    throw x;
  }

  public static synchronized void log(String s) {
		try {
			FileWriter logger = new FileWriter(PATH_SEPARATOR + getLogFilePath(), true);
			logger.write(sdf.format(new java.util.Date()) + s + NEW_LINE);
			logger.flush();
			logger.close();
    } catch (Exception e) { e.printStackTrace(); }
  }

  private static String getLogFilePath() {
    String s=null;
    try {s = getProperty(DESIGNSTATE_LOG_FILE); }
    catch(Exception e) { }
    if (null==s || "".equals(s.trim())) s = PATH_SEPARATOR + "ZWS_PX.log";
    return s;
  }

  protected static void setDebugModeOn() { inDebugMode=true; }

  //Properties: DesignState Service URLs
  protected static String DESIGNSTATE_APC_SERVICE_URL="designstate.apc.service.url";
  protected static String DESIGNSTATE_EVENT_SERVICE_URL="designstate.event.service.url";
  protected static String DESIGNSTATE_EVENT_SERVICE_PROTOCOL="designstate.event.service.protocol";

  //URL parameter names
  protected static String URL_PARAMETER_NUMBER = "number";
  protected static String URL_PARAMETER_AUTHOR= "author";

  //PRIVATE PROPERTIES - KEEP OFF!

  //Properties: DesignState Routing
  static String DESIGNSTATE_DOMAIN_NAME="designstate.domain.name";
  static String DESIGNSTATE_SERVER_NAME="designstate.server.name";
  static String DESIGNSTATE_REPOSITORY_NAME="designstate.repository.name";

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
  static ResourceBundle pxProperties = null;
  static boolean inDebugMode=true;
  //static Logger logger = Logger.getLogger(pxActionBase.class);

  static String Q = "?";
  static String EQ = "=";
  static String AND = "&";
  private static String PATH_SEPARATOR = System.getProperty("file.separator");
  private static String NEW_LINE = System.getProperty("line.separator");
  //private static int retryMinutes = 1;
}



