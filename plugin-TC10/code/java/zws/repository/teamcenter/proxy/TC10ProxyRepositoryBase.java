package zws.repository.teamcenter.proxy;
/*
 * DesignState - Design Compression Technology
 * @author: arbind @version: 1.0 Created on
 * May 23, 2007 10:48:58 AM Copywrite (c) 2007
 * Zero Wait-State Inc. All rights reserved
 */

import zws.data.MetadataBase;
import zws.exception.CanNotMaterialize;
import zws.exception.NotConnected;
import zws.qx.xml.QxXML;
import zws.repository.RepositoryBase;
import zws.repository.source.RepositoryBinarySource;
import zws.repository.source.RepositoryConfigurationSource;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.source.RepositoryStateSource;
import zws.repository.source.RepositoryStructureSource;
import zws.repository.source.RepositoryTemplateSource;
import zws.repository.target.RepositoryBinaryTarget;
import zws.repository.target.RepositoryConfigurationTarget;
import zws.repository.target.RepositoryMetadataTarget;
import zws.repository.target.RepositoryStateTarget;
import zws.repository.target.RepositoryStructureTarget;
import zws.repository.target.RepositoryTemplateTarget;
import zws.repository.teamcenter.TC10Constants;
import zws.repository.teamcenter.proxy.target.TC10ProxyRepositoryBinaryTarget;
import zws.repository.teamcenter.proxy.target.TC10ProxyRepositoryMetadataTarget;
import zws.repository.teamcenter.proxy.target.TC10ProxyRepositoryStateTarget;
import zws.repository.teamcenter.proxy.target.TC10ProxyRepositoryStructureTarget;
import zws.repository.teamcenter.proxy.xml.TC10ResultHandler;
import zws.search.SearchAgent;
//import zws.util.{}//Logwriter;

import java.io.StringReader;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * Logical representation of a data repository.
 */
public class TC10ProxyRepositoryBase extends RepositoryBase {
  public TC10ProxyRepositoryBase() {}

  public void activate () {}
  
  /**
   * Returns the type of data repository (e.g. Intralink, TeamCenter, Agile,
   * File System, etc.).
   *
   * @return The type of repository.
   */
  public String getRepositoryType() {
    return zws.origin.Origin.FROM_TEAMCENTER_10;
  }

  /**
   * Checks to see if a connection to the data repository can be established.
   *
   * @Throws NotConnected
   * Exception is thrown only if a connection can not be established.
   */

  /**
   * Returns the type of data repository (e.g. Intralink, TeamCenter, Agile,
   * File System, etc.).
   *
   * @return The type of repository.
   */
  public static String materializeItemUniqueID(String uid, String itemId) {
    return uid + d + itemId;
  }

  public static String materializeItemRevUniqueID(String uid, String itemId,
      String revision) {
    return uid + d + itemId + d + revision;
  }

  public static String materializeDatasetUniqueID(String uid, String itemId,
      String revision, String datasetType, String datasetName) {
    return uid + d + itemId + d + revision + d + datasetType + d + datasetName;
  }

  public static String materializeIMANFileUniqueID(String uid, String itemId,
      String revision, String datasetType, String datasetName, String fileName) {
    return uid + d + itemId + d + revision + d + datasetType + d + datasetName
        + d + fileName;
  }

  public static String materializeState(MetadataBase m) {

    String releaseStatus = "N/A";
    if (m.get("last_release_status") != null
        && !m.get("last_release_status").trim().equals("")) releaseStatus = m
        .get("last_release_status");

    String lockStatus = "N/A";
    if (m.get("checked_out") != null && !m.get("checked_out").trim().equals("")) lockStatus = TC10Constants.LOCKED;

    String owner = "N/A";
    if (m.get("owning_user") != null && !m.get("owning_user").trim().equals("")) owner = m
        .get("owning_user");

    return releaseStatus + d + lockStatus + d + owner;
  }

  public RepositoryMetadataSource materializeMetadataSource() throws CanNotMaterialize {
    TC10ProxyRepositoryMetadataSource source = new TC10ProxyRepositoryMetadataSource(getContext());
    return source;
  }

  public RepositoryBinarySource materializeBinarySource()
      throws CanNotMaterialize {
      TC10ProxyRepositoryBinarySource source = new TC10ProxyRepositoryBinarySource(getContext());
      return source;
  }

  public RepositoryStructureSource materializeStructureSource()
      throws CanNotMaterialize {
      TC10ProxyRepositoryStructureSource source = new TC10ProxyRepositoryStructureSource(getContext());
      return source;
  }

  public SearchAgent materializeSearchAgent() throws CanNotMaterialize {
    TC10ProxyRepositorySearchAgent agent = new TC10ProxyRepositorySearchAgent();
    agent.setRepository(this);
    {} //System.out.println("Returning TC10 search agent " + agent);
    return agent;
  }

  public RepositoryConfigurationSource materializeConfigurationSource()
      throws CanNotMaterialize {
      TC10ProxyRepositoryConfigurationSource source = new TC10ProxyRepositoryConfigurationSource(getContext());
      return source;
  }

  public void verifyConnection() throws NotConnected {
  // / +++ ToDo
  }

  /**
   * Returns a pre-configured search agent that reports only the latest data
   * when queried.
   *
   * @return A Search Agent.
   */
  public SearchAgent materializeLatestSearchAgent() throws CanNotMaterialize {
    throw new CanNotMaterialize("LatestSearch Agent",
        "materialize method has not been implemented", this);
  }

  /**
   * Returns a pre-configured search agent that reports only the latest data for
   * each revision when queried.
   *
   * @return A Search Agent.
   */
  public SearchAgent materializeLatestRevSearchAgent() throws CanNotMaterialize {
    throw new CanNotMaterialize("LatestRevSearch Agent",
        "materialize method has not been implemented", this);
  }

  public RepositoryStateSource materializeStateSource()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("StateSource",
        "materialize method has not been implemented", this);
  }

  public RepositoryTemplateSource materializeTemplateSource()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("TemplateSource",
        "materialize method has not been implemented", this);
  }

  public RepositoryMetadataTarget materializeMetadataTarget()
      throws CanNotMaterialize {
      TC10ProxyRepositoryMetadataTarget target = new TC10ProxyRepositoryMetadataTarget(getContext());
      return target;
  }

  public RepositoryBinaryTarget materializeBinaryTarget()
      throws CanNotMaterialize {
      TC10ProxyRepositoryBinaryTarget target = new TC10ProxyRepositoryBinaryTarget(getContext());
      return target;
  }

  public RepositoryStructureTarget materializeStructureTarget()
      throws CanNotMaterialize {
      TC10ProxyRepositoryStructureTarget target = new TC10ProxyRepositoryStructureTarget(getContext());
      return target;
  }

  public RepositoryStateTarget materializeStateTarget()
      throws CanNotMaterialize {
      TC10ProxyRepositoryStateTarget target = new TC10ProxyRepositoryStateTarget(getContext());
      return target;
  }

  public RepositoryConfigurationTarget materializeConfigurationTarget()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("ConfigurationTarget",
        "materialize method has not been implemented", this);
  }

  public RepositoryTemplateTarget materializeTemplateTarget()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("TemplateTarget",
        "materialize method has not been implemented", this);
  }

  public String objectName(String name) {
    if (getLowerCasedFilenames()) return name.toLowerCase();
    else if (getUpperCasedFilenames()) return name.toUpperCase();
    else return name;
  }


  public long parseDate(String formattedDate) {
    long d = 0;
    if (null == formattedDate || "".equals(formattedDate)) return 0;
    if (formattedDate.indexOf(".") > 0) d = parseDottedDate(formattedDate);
    else d = parseSimpleDate(formattedDate);
    return d;
  }

  private long parseDottedDate(String dottedDate) {
    int year = 0, month = 0, day = 0, hour = 0, min = 0, sec = 0;
    StringTokenizer tokens = new StringTokenizer(dottedDate, ".");
    if (tokens.hasMoreTokens()) year = Integer.valueOf(tokens.nextToken())
        .intValue();
    if (tokens.hasMoreTokens()) month = Integer.valueOf(tokens.nextToken())
        .intValue();
    if (tokens.hasMoreTokens()) day = Integer.valueOf(tokens.nextToken())
        .intValue();
    if (tokens.hasMoreTokens()) hour = Integer.valueOf(tokens.nextToken())
        .intValue();
    if (tokens.hasMoreTokens()) min = Integer.valueOf(tokens.nextToken())
        .intValue();
    if (tokens.hasMoreTokens()) sec = Integer.valueOf(tokens.nextToken())
        .intValue();
    Calendar c = new GregorianCalendar(year, month - 1, day, hour, min, sec);
    {}//Logwriter.printOnConsole("Dotted Date: " + c.getTimeInMillis());
    return c.getTimeInMillis();
  }

  private long parseSimpleDate(String formattedDate) {
    if (null == formattedDate || "".equals(formattedDate)) return 0;
    SimpleDateFormat dateFormatter = new SimpleDateFormat(getDateFormat());
    String d = formattedDate.replace(',', ' ');
    return dateFormatter.parse(d, new ParsePosition(0)).getTime();
  }  

  public boolean getLowerCasedFilenames() {
    String s = getContext().get(TC10Constants.LOWER_CASED_FILENAMES,
        Boolean.valueOf(true).toString());
    return Boolean.valueOf(s).booleanValue();
  }

  public void setLowerCasedFilenames(boolean b) {
    getContext().set(TC10Constants.LOWER_CASED_FILENAMES,
        Boolean.valueOf(b).toString());
  }

  public boolean getUpperCasedFilenames() {
    String s = getContext().get(TC10Constants.UPPER_CASED_FILENAMES,
        Boolean.valueOf(false).toString());
    return Boolean.valueOf(s).booleanValue();
  }

  public void setUpperCasedFilenames(boolean b) {
    getContext().set(TC10Constants.UPPER_CASED_FILENAMES,
        Boolean.valueOf(b).toString());
  }

  public long getConnectionTimeout() {
    String s = getContext().get(TC10Constants.CONNECTION_TIMEOUT, "30");
    return Long.valueOf(s).longValue();
  }

  public void setConnectionTimeout(long l) {
    getContext().set(TC10Constants.CONNECTION_TIMEOUT, "" + l);
  }

  public String getDateFormat() {
    return getContext().get(TC10Constants.DATE_FORMAT, TC10Constants.DEFAULT_DATE_FORMAT);
  }

  public void setDateFormat(String s) {
    getContext().set(TC10Constants.DATE_FORMAT, s);
  }
/*
  public String getServerMarker() {
    return getContext().get(QxContext.SERVER_MARKER);
  }
  
  public String getTCServerName() {
    return getContext().get(QxContext.TC_SERVER_NAME);
  }

  public void setServerMarker(String s) {
    getContext().set(QxContext.SERVER_MARKER, s);
  }
  public void setTCServerName(String s) {
    getContext().set(QxContext.TC_SERVER_NAME, s);
  }
  */
  
  protected TC10ProxyInterface getTC10Proxy() {
	  return TC10Proxy;
  }
  
  
  protected static SAXParser getParser(boolean validate) throws SAXException, ParserConfigurationException {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setValidating(validate);
    return factory.newSAXParser();
  }  
  
  protected void handleResponse(QxXML response, TC10ResultHandler handler) throws Exception {
    if (null==response) {
      {}//Logwriter.printOnConsole("No Output Results.");
      return;
    }
    if (null==handler) {
      {}//Logwriter.printOnConsole("No Result Handler Defined.");
      return;
    }
    XMLReader xr = getParser(false).getXMLReader();
    handler.setTC10Repository(this);
    xr.setContentHandler(handler);
    xr.setErrorHandler(handler);

    xr.parse(new InputSource(new StringReader(response.toString())));
    if (handler.hasError()) handler.throwError();
   }
  

  // default configurations
  private static String d = TC10Constants.delim;
  private static final String crlf = TC10Constants.crlf;

  //private Map teamcenterSessions = new HashMap();
  //private static AIFPortal portal;
  private TC10ProxyInterface TC10Proxy = TC10ProxyInterface.getTC10ProxyInterface();


}
