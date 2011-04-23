package zws.repository;
/*
* DesignState - Design Compression Technology
* @author: Arbind Thakur
* @version: 1.0
* Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
*/

import zws.application.s;
import zws.exception.NotConnected;
import zws.repository.source.RepositoryBinarySource;
import zws.repository.source.RepositoryConfigurationSource;
import zws.repository.source.RepositoryECOSource;
import zws.repository.source.RepositoryMetadataSource;
import zws.repository.source.RepositoryStateSource;
import zws.repository.source.RepositoryStructureSource;
import zws.repository.source.RepositoryTemplateSource;
import zws.repository.target.RepositoryBinaryTarget;
import zws.repository.target.RepositoryConfigurationTarget;
import zws.repository.target.RepositoryECOTarget;
import zws.repository.target.RepositoryMetadataTarget;
import zws.repository.target.RepositoryStateTarget;
import zws.repository.target.RepositoryStructureTarget;
import zws.repository.target.RepositoryTemplateTarget;
import zws.repository.target.RepositoryWorkflowTarget;
import zws.search.SearchAgent;
import zws.security.Authentication;
import zws.security.CryptoUtil;
import zws.qx.QxContext;
import zws.qx.xml.QxXML;


import java.util.Map;
import zws.exception.CanNotMaterialize;

// TODO: Auto-generated Javadoc
/**
 * Logical representation of a data repository.
 */
public abstract class RepositoryBase implements Repository {

  public void open(Authentication id) throws Exception { }
  public void close(Authentication id) throws Exception { }
  
  public QxXML toXML () {
    /*<intralink name="ilink"  domain-name="domain-name"
      server-name="node-0" protocol="http"
      host-name="designstate-0" port="80" env-root="ilink-1"
      services-path="ZeroWait-State/services"/>*/

    StringBuffer xmlString = new StringBuffer();
    xmlString.append(START_TAG).append(this.getRepositoryType());
    xmlString.append(prepareArg(QxContext.REPOSITORY_NAME, this.getRepositoryName()));
    xmlString.append(prepareArg(QxContext.DOMAIN_NAME, this.getDomainName()));
    xmlString.append(prepareArg(QxContext.SERVER_NAME, this.getServerName()));
    xmlString.append(prepareArg(QxContext.PROTOCOL, this.getProtocol()));
    xmlString.append(prepareArg(QxContext.HOST_NAME, this.getHostName()));
    xmlString.append(prepareArg(QxContext.PORT, this.getPort()));
    xmlString.append(prepareArg(QxContext.SERVICES_PATH, this.getServicesPath()));
    xmlString.append(prepareArg(QxContext.DESCRIPTION, this.getDescription()));
    xmlString.append(prepareArg(QxContext.SYSTEM_USERNAME, this.getSystemUsername()));
    xmlString.append(prepareArg(QxContext.SYSTEM_PASSWORD, this.getSystemPassword()));
    prepareSpecificArgs(xmlString);
    xmlString.append(END_TAG);


    {} //System.out.println("-->>>toXML in RepositoryBase");
    {} //System.out.println(new QxXML(xmlString.toString()));
    return new QxXML(xmlString.toString());
  }

  protected void prepareSpecificArgs(StringBuffer xmlString) {}

  /**
   * @param key key
   * @param value value
   * @return arg
   */
  public String prepareArg(String key, String value) {
    // prepare string like  key="value"
    StringBuffer argBuffer = new StringBuffer();
    argBuffer.append(SPACE).append(key).append(EQUALTO).append(QUOTE).append(value).append(QUOTE);
    return argBuffer.toString();
  }

  /**
   * Returns a QxContext that defines the configuration parameters specific for this repository.
   *
   * @param qxCtx qxCtx
   */
  public void setContext(QxContext qxCtx) {
    this.ctx = qxCtx;
  }

  /**
   * Push context.
   *
   * @param qxCtx the qx ctx
   */
  public void pushContext(QxContext qxCtx) {
    qxCtx.configureParent(ctx);
    ctx = qxCtx;
  }

  /**
   * Pop context.
   */
  public void popContext() { ctx = ctx.parent(); }

  /**
   * Returns a QxContext that defines the configuration parameters specific for
   * this repository.
   *
   * @return the context
   */
  public QxContext getContext() {
    return ctx;
  }

  /**
   * Defines configuration parameters specific for this repository.
   *
   * @param parent the parent
   */
  public void configureParentContext(QxContext parent) {
    ctx.configureParent(parent);
  }

  /**
   * Defines configuration parameters specific for this repository.
   *
   * @param parameters Dictionary specifying configuration properties as a set of key, value
   * pairs.
   */
  public void configureContext(Map parameters) {
    ctx.setAll(parameters);
  }

  /**
   * Sets a configuration parameter specific to this repository.
   *
   * @param key Name of the configuration parameter to be set.
   * @param value Value the configuration parameter should be set to.
   */
  public void configureContext(String key, String value) {
    ctx.set(key, value);
  }

  /**
   * Returns a configuration parameter specific to this repository.
   *
   * @param key Name of the configuration parameter.
   *
   * @return Value the configuration parameter.
   */
  public String readContext(String key) {
    return ctx.get(key);
  }

  /**
   * Returns routing path to repository as String.
   *
   * @return the repository route
   */
  public String getRepositoryRoute() {
    return getDomainName() + s.delim + getServerName() + s.delim
        + getRepositoryName();
  }

  /**
   * Returns the name of the domain where this data repository can be found.
   *
   * @return The domain-name portion of this repository's routing.
   */
  public String getDomainName() {
    return ctx.get(QxContext.DOMAIN_NAME);
  }

  /**
   * Sets the name of the domain where this data repository can be found.
   *
   * @param s The domain-name portion of this repository's routing.
   */
  public void setDomainName(String s) {
    ctx.set(QxContext.DOMAIN_NAME, s);
  }

  /**
   * Returns the name of the local DesignState server connected to this data
   * repository.
   *
   * @return The server-name portion of this repository's routing.
   */
  public String getServerName() {
    return ctx.get(QxContext.SERVER_NAME);
  }

  /**
   * Sets the name of the local DesignState server connected to this data
   * repository.
   *
   * @param s The server-name portion of this repository's routing.
   */
  public void setServerName(String s) {
    ctx.set(QxContext.SERVER_NAME, s);
  }

  /**
   * Returns the type of data repository (e.g. Intralink, TeamCenter, Agile,
   * File System, etc.).
   *
   * @return The type of repository.
   */
  public abstract String getRepositoryType();

  /**
   * Equivilent to getRepositoryType.
   *
   * @return The type of repository.
   */
  public String getType() {
    return getRepositoryType();
  }

  /**
   * Returns the logical name for this data repository.
   *
   * @return The logical-name portion of this repository's routing.
   */
  public String getRepositoryName() {
    return ctx.get(QxContext.REPOSITORY_NAME);
  }

  /**
   * Returns the logical name for this data repository.
   *
   * @return The logical-name portion of this repository's routing.
   */
  public String getName() {
    return getRepositoryName();
  }

  /**
   * Returns the logical name for this data repository.
   *
   * @param RepositoryName the name
   *
   * @return The logical-name portion of this repository's routing.
   */
  public void setName(String RepositoryName) {
    setRepositoryName(RepositoryName);
  }

  /**
   * Gets the service URL.
   *
   * @return the service URL
   */
  public String getServiceURL() {
    String url = getProtocol() + "://" + getHostName() + ":" + getPort() + "/"
        + getServicesPath();
    return url;
  }

  /**
   * Sets the logical name for this data repository.
   *
   * @param s The logical-name portion of this repository's routing.
   */
  public void setRepositoryName(String s) {
    ctx.set(QxContext.REPOSITORY_NAME, s);
  }

  /**
   * Returns the protocol for connecting to this repository.
   *
   * @return The protocol.
   */
  public String getProtocol() {
    return ctx.get(QxContext.PROTOCOL);
  }

  /**
   * Sets the logical name for this data repository.
   *
   * @param s The logical-name portion of this repository's routing.
   */
  public void setProtocol(String s) {
    ctx.set(QxContext.PROTOCOL, s);
  }

  /**
   * Returns the Hostname for connecting to this repository.
   *
   * @return The repository's hostname.
   */
  public String getHostName() {
    return ctx.get(QxContext.HOST_NAME);
  }

  /**
   * Sets the logical name for this data repository.
   *
   * @param s The logical-name portion of this repository's routing.
   */
  public void setHostName(String s) {
    ctx.set(QxContext.HOST_NAME, s);
  }

  /**
   * Returns the port for connecting to this repository.
   *
   * @return The repository's port.
   */
  public String getPort() {
    return ctx.get(QxContext.PORT);
  }

  /**
   * Sets the logical name for this data repository.
   *
   * @param s The logical-name portion of this repository's routing.
   */
  public void setPort(String s) {
    ctx.set(QxContext.PORT, s);
  }

  /**
   * Returns the Hostname for connecting to this repository.
   *
   * @return The repository's hostname.
   */
  public String getServicesPath() {
    return ctx.get(QxContext.SERVICES_PATH);
  }

  /**
   * Sets the logical name for this data repository.
   *
   * @param s The logical-name portion of this repository's routing.
   */
  public void setServicesPath(String s) {
    ctx.set(QxContext.SERVICES_PATH, s);
  }

  /**
   * Returns a brief description of this data repository.
   *
   * @return A description.
   */
  public String getDescription() {
    return ctx.get(QxContext.DESCRIPTION);
  }

  /**
   * Sets a brief description for this data repository.
   *
   * @param s The description.
   */
  public void setDescription(String s) {
    ctx.set(QxContext.DESCRIPTION, s);
  }

  /**
   * Returns a default user account that is available in the repository.
   *
   * @return A default username.
   */
  public String getSystemUsername() {
    return ctx.get(QxContext.SYSTEM_USERNAME);
  }

  /**
   * Sets a default user account that is available in the repository.
   *
   * @param s Default username.
   */
  public void setSystemUsername(String s) {
    ctx.set(QxContext.SYSTEM_USERNAME, s);
  }

  /**
   * Returns the password for the default user account.
   *
   * @return A default password.
   */
  public String getSystemPassword() {
    return ctx.get(QxContext.SYSTEM_PASSWORD);
  }

  /**
   * Sets the password for the default user account.
   *
   * @param s The default password.
   */
  public void setSystemPassword(String s) {
    ctx.set(QxContext.SYSTEM_PASSWORD, s);
  }

  /*public String getEncryptedSystemPassword() {
    return ctx.get(QxContext.ENCRYPTED_SYSTEM_PASSWORD);
  }*/

  public void setEncryptedSystemPassword(String s) throws Exception{
    ctx.set(QxContext.ENCRYPTED_SYSTEM_PASSWORD, s);
    String password = null;
    try {
      CryptoUtil cryptoUtil = new CryptoUtil();
      password = cryptoUtil.decrypt(s);
    } catch (Exception e) {
      System.out.println("Not able to decrypt the password " + s);
      throw e;
    } 
    ctx.set(QxContext.SYSTEM_PASSWORD, password);
  }

  
  public Authentication getSystemAuthentication() throws Exception {
    Authentication id = new Authentication(getSystemUsername(), getSystemPassword());
    return id;
  }

  /**
   * Checks to see if a connection to the data repository can be established.
   *
   * @throws NotConnected the not connected
   *
   * @Throws NotConnected
   * Exception is thrown only if a connection can not be established.
   */
  public abstract void verifyConnection() throws NotConnected;

  /**
   * Returns a basic search agent that can be used to query the repository.
   *
   * @return A Search Agent.
   *
   * @throws CanNotMaterialize the can not materialize
   */
  public SearchAgent materializeSearchAgent() throws CanNotMaterialize {
    throw new CanNotMaterialize("Search Agent",
        "materialize method has not been implemented", this);
  }

  /**
   * Returns a pre-configured search agent that reports only the latest data
   * when queried.
   *
   * @return A Search Agent.
   *
   * @throws CanNotMaterialize the can not materialize
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
   *
   * @throws CanNotMaterialize the can not materialize
   */
  public SearchAgent materializeLatestRevSearchAgent() throws CanNotMaterialize {
    throw new CanNotMaterialize("LatestRevSearch Agent",
        "materialize method has not been implemented", this);
  }

  /** materialize MetadataSource.
   * @throws CanNotMaterialize exception
   * @return RepositoryMetadataSource RepositoryMetadataSource
   * @see zws.repository.Repository#materializeMetadataSource()
   */
  public RepositoryMetadataSource materializeMetadataSource()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("MetadataSource",
        "materialize method has not been implemented", this);
  }

  /** materialize BinarySource.
   * @throws CanNotMaterialize exception
   * @return RepositoryMetadataSource RepositoryMetadataSource
   * @see zws.repository.Repository#materializeBinarySource()
   */
  public RepositoryBinarySource materializeBinarySource()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("BinarySource",
        "materialize method has not been implemented", this);
  }

  /* (non-Javadoc)
   * @see zws.repository.Repository#materializeStructureSource()
   */
  public RepositoryStructureSource materializeStructureSource()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("StructureSource",
        "materialize method has not been implemented", this);
  }

  /* (non-Javadoc)
   * @see zws.repository.Repository#materializeStateSource()
   */
  public RepositoryStateSource materializeStateSource()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("StateSource",
        "materialize method has not been implemented", this);
  }

  /* (non-Javadoc)
   * @see zws.repository.Repository#materializeConfigurationSource()
   */
  public RepositoryConfigurationSource materializeConfigurationSource()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("ConfigurationSource",
        "materialize method has not been implemented", this);
  }

  /* (non-Javadoc)
   * @see zws.repository.Repository#materializeTemplateSource()
   */
  public RepositoryTemplateSource materializeTemplateSource()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("TemplateSource",
        "materialize method has not been implemented", this);
  }

  /* (non-Javadoc)
   * @see zws.repository.Repository#materializeMetadataTarget()
   */
  public RepositoryMetadataTarget materializeMetadataTarget()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("MetadataTarget",
        "materialize method has not been implemented", this);
  }

  /* (non-Javadoc)
   * @see zws.repository.Repository#materializeBinaryTarget()
   */
  public RepositoryBinaryTarget materializeBinaryTarget()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("BinaryTarget",
        "materialize method has not been implemented", this);
  }

  /* (non-Javadoc)
   * @see zws.repository.Repository#materializeStructureTarget()
   */
  public RepositoryStructureTarget materializeStructureTarget()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("StructureTarget",
        "materialize method has not been implemented", this);
  }

  /* (non-Javadoc)
   * @see zws.repository.Repository#materializeStateTarget()
   */
  public RepositoryStateTarget materializeStateTarget()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("StateTarget",
        "materialize method has not been implemented", this);
  }

  /* (non-Javadoc)
   * @see zws.repository.Repository#materializeConfigurationTarget()
   */
  public RepositoryConfigurationTarget materializeConfigurationTarget()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("ConfigurationTarget",
        "materialize method has not been implemented", this);
  }

  /* (non-Javadoc)
   * @see zws.repository.Repository#materializeTemplateTarget()
   */
  public RepositoryTemplateTarget materializeTemplateTarget()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("TemplateTarget",
        "materialize method has not been implemented", this);
  }

  public RepositoryWorkflowTarget materializeWorkflowTarget() throws CanNotMaterialize {
    throw new CanNotMaterialize("materializeRepositoryWorkflowTarget",
        "materialize method has not been implemented", this);
  }
  public RepositoryECOTarget materializeECOTarget() throws CanNotMaterialize {
    throw new CanNotMaterialize("materializeECOTarget", "materialize method has not been implemented", this);
  }
  public RepositoryECOSource materializeECOSource() throws CanNotMaterialize {
    throw new CanNotMaterialize("materializeECOSource", "materialize method has not been implemented", this);
  }
  /**
   * Sets the state of this repository to be active and usable.
   */
  public abstract void activate() throws Exception;

  /**
   * Sets the state of this repository to be inactive and unusable.
   */
  public void inactivate() {
    active = false;
  }

  /* (non-Javadoc)
   * @see zws.util.Prototype#deepCopy()
   */
  public Object deepCopy() {
    // +++Todo
    return null;
  }

  /* (non-Javadoc)
   * @see zws.util.Prototype#copy()
   */
  public Object copy() {
    // +++Todo
    try {
      {} //System.out.println("original " + ctx);
      QxContext ctxClone = new QxContext();
      ctxClone.setProperties(ctx.getProperties());
      RepositoryBase r = (RepositoryBase) clone();
      r.setContext(ctxClone);
      {} //System.out.println("clone  " + ctxClone);
      return r;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  /* (non-Javadoc)
   * @see zws.util.Routed#getRoute()
   */
  public String getRoute() {

    return getDomainName() + "." + getServerName() + "." + getName();

  }

  /* (non-Javadoc)
   * @see zws.util.Prototype#shallowCopy()
   */
  public Object shallowCopy() {
    // +++Todo
    return null;
  }

  /* (non-Javadoc)
   * @see zws.util.Prototype#supportsDeepCopy()
   */
  public boolean supportsDeepCopy() {
    // +++Todo
    return false;
  }

  /** The ctx. */
  private QxContext ctx = new QxContext();

  /** The active. */
  private boolean active = true;

  public static String QUOTE = "\"";
  public static String EQUALTO = "=";
  public static String START_TAG = "<";
  public static String END_TAG = "/>";
  public static String CLOSE_TAG = ">";
  public static String SLASH = "/";
  public static String SPACE = " ";

}
