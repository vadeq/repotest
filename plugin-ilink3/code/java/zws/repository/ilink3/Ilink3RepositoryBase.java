package zws.repository.ilink3;/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.application.Names;
import zws.application.Properties;
import zws.exception.CanNotMaterialize;
import zws.exception.NotConnected;
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
import zws.search.SearchAgent;
import zws.security.Authentication;
//import zws.util.{}//Logwriter;

import java.security.NoSuchAlgorithmException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * Logical representation of a data repository.
 */
public class Ilink3RepositoryBase extends RepositoryBase  {
  public Ilink3RepositoryBase(){   }
  
  public void activate() throws Exception {  }

  /**
   * Returns the type of data repository (e.g. Intralink, TeamCenter, Agile, File System, etc.).
   *
   * @return The type of repository.
   */
  public String getRepositoryType() { return zws.origin.Origin.FROM_ILINK; }

  /**
   * Checks to see if a connection to the data repository can be established.
   *
   * @Throws NotConnected Exception is thrown only if a connection can not be established.
   */
  public void verifyConnection() throws NotConnected {
    /// +++ ToDo
  }

/*
  public QxXML toXMLOLD () {
    //<intralink name="ilink"  domain-name="domain-name"
    //  server-name="node-0" protocol="http"
    //  host-name="designstate-0" port="80" env-root="ilink-1"
    //  services-path="ZeroWait-State/services"/>
    StringBuffer xmlString = new StringBuffer();
    xmlString.append("<").append(this.getRepositoryType());
    xmlString.append(" name=\"").append(this.getRepositoryName()).append("\" ");
    xmlString.append(" domain-name=\"").append(this.getDomainName()).append("\" ");
    xmlString.append(" server-name=\"").append(this.getServerName()).append("\" ");
    xmlString.append(" protocol=\"").append(this.getProtocol()).append("\" ");
    xmlString.append(" host-name=\"").append(this.getHostName()).append("\" ");
    xmlString.append(" port=\"").append(this.getPort()).append("\" ");
    xmlString.append(" env-root=\"").append(this.getEnvRoot()).append("\" ");
    xmlString.append(" services-path=\"").append(this.getServicesPath()).append("\" ");
    xmlString.append(" description=\"").append(this.getDescription()).append("\" ");
    xmlString.append("/>");
    {} //System.out.println("-->>>toXML in RepositoryBase");
    {} //System.out.println(new QxXML(xmlString.toString()));
    return new QxXML(xmlString.toString());
  }
*/

  protected void prepareSpecificArgs(StringBuffer xmlString) {
    xmlString.append(prepareArg(Ilink3Constants.ENV_ROOT, getEnvRoot()));
  }
  
  public String getLDBPathBase() { return Properties.get(Names.LDB_PATH_BASE);  }
  
  public String getEnvRoot(){
    String envRoot = this.getContext().get(Ilink3Constants.ENV_ROOT);
    if (null==envRoot) return getName();
    return envRoot;
  }

  public boolean getLaunchEventListener(){
    boolean launchEventListener = getContext().getBool(Ilink3Constants.LAUNCH_EVENT_LISTENER);
    return launchEventListener ;
  }  
  

  public void setLaunchEventListener(boolean b){
    getContext().set(Ilink3Constants.LAUNCH_EVENT_LISTENER, String.valueOf(b));
  }
  
  public void setEnvRoot(String s){ getContext().set(Ilink3Constants.ENV_ROOT, s); }

  /**
   * Returns a basic search agent that can be used to query the repository.
   *
   * @return A Search Agent.
   */
  public SearchAgent materializeSearchAgent() throws CanNotMaterialize {
    Ilink3RepositorySearchAgent agent = new Ilink3RepositorySearchAgent();
    agent.setRepository(this);
    return agent;
  }

  /**
   * Returns a pre-configured search agent that reports only the latest data when queried.
   *
   * @return A Search Agent.
   */
  public SearchAgent materializeLatestSearchAgent() throws CanNotMaterialize {
    throw new CanNotMaterialize("LatestSearch Agent", "materialize method has not been implemented", this);
  }

  public Authentication getDefaultAuthentication(){
    Authentication defaultAuthentication= null;
    try {
      defaultAuthentication = new Authentication("admin", "admin");
    } catch(NoSuchAlgorithmException e){
      {}//Logwriter.printOnConsole("NoSuchAlgorithmException while creating default authentication.");
      e.printStackTrace();
    } catch(Exception e){
      {}//Logwriter.printOnConsole("Exception while creating default authentication.");
      e.printStackTrace();
    }
    return defaultAuthentication;
  }
  /**
   * Returns a pre-configured search agent that reports only the latest data for each revision when queried.
   *
   * @return A Search Agent.
   */
  public SearchAgent materializeLatestRevSearchAgent() throws CanNotMaterialize {
    throw new CanNotMaterialize("LatestRevSearch Agent", "materialize method has not been implemented", this);
  }

  public RepositoryMetadataSource materializeMetadataSource() throws CanNotMaterialize {
    Ilink3RepositoryMetadataSource source = new Ilink3RepositoryMetadataSource(getContext());
    return source;
  }

  public RepositoryBinarySource materializeBinarySource() throws CanNotMaterialize {
    Ilink3RepositoryBinarySource source = new Ilink3RepositoryBinarySource(getContext());
    return source;
  }

  public RepositoryStructureSource materializeStructureSource() throws CanNotMaterialize {
    Ilink3RepositoryStructureSource source = new Ilink3RepositoryStructureSource(getContext());
    return source;
    //throw new CanNotMaterialize("StructureSource", "materialize method has not been implemented", this);
  }
  public RepositoryStateSource materializeStateSource() throws CanNotMaterialize {
    throw new CanNotMaterialize("StateSource", "materialize method has not been implemented", this);
  }
  public RepositoryConfigurationSource materializeConfigurationSource() throws CanNotMaterialize {
    throw new CanNotMaterialize("ConfigurationSource", "materialize method has not been implemented", this);
  }
  public RepositoryTemplateSource materializeTemplateSource() throws CanNotMaterialize {
    throw new CanNotMaterialize("TemplateSource", "materialize method has not been implemented", this);
  }

  public RepositoryMetadataTarget materializeMetadataTarget() throws CanNotMaterialize {
    throw new CanNotMaterialize("MetadataTarget", "materialize method has not been implemented", this);
  }
  public RepositoryBinaryTarget materializeBinaryTarget() throws CanNotMaterialize {
    throw new CanNotMaterialize("BinaryTarget", "materialize method has not been implemented", this);
  }
  public RepositoryStructureTarget materializeStructureTarget() throws CanNotMaterialize {
    throw new CanNotMaterialize("StructureTarget", "materialize method has not been implemented", this);
  }
  public RepositoryStateTarget materializeStateTarget() throws CanNotMaterialize {
    Ilink3RepositoryStateTarget source = new Ilink3RepositoryStateTarget(getContext());
    return source;
  }
  public RepositoryConfigurationTarget materializeConfigurationTarget() throws CanNotMaterialize {
    throw new CanNotMaterialize("ConfigurationTarget", "materialize method has not been implemented", this);
  }
  public RepositoryTemplateTarget materializeTemplateTarget() throws CanNotMaterialize {
    throw new CanNotMaterialize("TemplateTarget", "materialize method has not been implemented", this);
  }
  
  public static final String ENV_ROOT = Ilink3Constants.ENV_ROOT;








  /*
  public Ilink3ClientCommonspace materializeCommonSpace() {
    Ilink3ClientCommonspace cs = new Ilink3ClientCommonspace(ctx);
    return cs;
  }

  public Ilink3ClientSandbox materializeSandbox() {
    Ilink3ClientSandbox sb = new Ilink3ClientSandbox(ctx);
    return sb;
  }

  public Ilink3ClientWorkspace materializeWorkSpace() {
    Ilink3ClientWorkspace ws = new Ilink3ClientWorkspace(ctx);
    return ws;
  }
*/

  public String objectName(String name) {
    if (getLowerCasedFilenames()) return name.toLowerCase();
    else if (getUpperCasedFilenames()) return name.toUpperCase();
    else return name;
  }

  public boolean getLowerCasedFilenames() {
    String s = getContext().get(Ilink3Constants.LOWER_CASED_FILENAMES, Boolean.valueOf(true).toString());
    return Boolean.valueOf(s).booleanValue();
  }

  public void setLowerCasedFilenames(boolean b) {
    getContext().set(Ilink3Constants.LOWER_CASED_FILENAMES, Boolean.valueOf(b).toString());
  }

  public boolean getUpperCasedFilenames() {
    String s = getContext().get(Ilink3Constants.UPPER_CASED_FILENAMES, Boolean.valueOf(false).toString());
    return Boolean.valueOf(s).booleanValue();
  }

  public void setUpperCasedFilenames(boolean b) {
    getContext().set(Ilink3Constants.UPPER_CASED_FILENAMES, Boolean.valueOf(b).toString());
  }

  public long getConnectionTimeout() {
    String s = getContext().get(Ilink3Constants.CONNECTION_TIMEOUT, "30");
    return Long.valueOf(s).longValue();
  }

  public void setConnectionTimeout(long l) {
    getContext().set(Ilink3Constants.CONNECTION_TIMEOUT, "" + l);
  }

  public String getDateFormat() {
    return getContext().get(Ilink3Constants.DATE_FORMAT, Ilink3Constants.DEFAULT_DATE_FORMAT);
  }

  public void setDateFormat(String s) { getContext().set(Ilink3Constants.DATE_FORMAT, s); }

  public long parseDate(String formattedDate) {
    long d=0;
    if (null==formattedDate || "".equals(formattedDate)) return 0;
    if (formattedDate.indexOf(".")>0) d = parseDottedDate(formattedDate);
    else d = parseSimpleDate(formattedDate);
    return d;
  }

  private long parseDottedDate(String dottedDate) {
    int year=0, month=0, day=0, hour=0, min=0, sec=0;
    StringTokenizer tokens = new StringTokenizer(dottedDate, ".");
    if (tokens.hasMoreTokens()) year = Integer.valueOf(tokens.nextToken()).intValue();
    if (tokens.hasMoreTokens()) month = Integer.valueOf(tokens.nextToken()).intValue();
    if (tokens.hasMoreTokens()) day = Integer.valueOf(tokens.nextToken()).intValue();
    if (tokens.hasMoreTokens()) hour = Integer.valueOf(tokens.nextToken()).intValue();
    if (tokens.hasMoreTokens()) min = Integer.valueOf(tokens.nextToken()).intValue();
    if (tokens.hasMoreTokens()) sec = Integer.valueOf(tokens.nextToken()).intValue();
    Calendar c = new GregorianCalendar(year, month-1, day, hour, min, sec);
    {}//Logwriter.printOnConsole("Dotted Date: " + c.getTimeInMillis());
    return c.getTimeInMillis();
  }

  private long parseSimpleDate(String formattedDate) {
    if (null==formattedDate || "".equals(formattedDate)) return 0;
    SimpleDateFormat dateFormatter = new SimpleDateFormat(getDateFormat());
    String d = formattedDate.replace(',', ' ');
    return dateFormatter.parse(d, new ParsePosition(0)).getTime();
  }

  //default configurations
  public static String AS_STORED_CONFIGURATION = "as-stored";
  public static String LATEST_CONFIGURATION = "latest";

  public static String NO_DEPENDENCIES = "none";
  public static String ALL_DEPENDENCIES = "all";
  public static String ILINK_ALL_DEPENDENCIES = "ilink-all";
  public static String REQUIRED_DEPENDENCIES = "required";
  public static String ILINK_REQUIRED_DEPENDENCIES = "ilink-required";

  //Some system attributes
  public static String NAME = "name";
  public static String BRANCH = "branch";
  public static String REVISION = "rev";
  public static String VERSION = "ver";
  public static String CREATED_ON = "created-on";
  public static String CREATED_BY = "created-by";
  public static String RELEASE_LEVEL = "release-level";
  public static String FOLDER = "folder";
  public static String GENERIC = "is-generic";
  public static String INSTANCE = "is-instance";
  public static String DESCRIPTION = "description";
  public static String LOCKED = "locked";
  public static String RELEASE_SCHEMA = "release-schema";
  public static String SCOPE = "scope";
  public static String STATUS_DESCRIPTION = "status-description";
  public static String RULE = "Rule";
  public static String ROLE = "Role";
  public static String RELEASE_SCHEME = "release-scheme";
  public static String RELEASE_PROCEDURE = "release-procedure";
  public static String QUANTITY = "quantity";
  public static String PROMOTE_TO = "promote-to";
  public static String DEMOTE_TO = "demote-to";
  public static String OBJECT = "Object";
  public static String MEMBER_TYPE = "Member-Type";
  public static String DEPENDENCIES_ARE_COMPLETE = "Dependencies-Complete";
  public static String BRANCH_DOMAIN = "Branch-Domain";
  public static String ATTENTION = "Attention";
  public static String RELATIONSHIP_TYPES = "Relationship-Types";
  public static String ROW_NUMBER = "Row-Number";
  public static String SAME_FILE = "Same-File";
  public static String TYPE_NAME = "Type-Name";
}
