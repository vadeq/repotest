package zws.repository;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.NotConnected;
import zws.qx.QxContext;
import zws.qx.xml.QxXML;
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
import zws.util.Named;
import zws.util.Routed;
import zws.util.Prototype;

import java.util.Map;
import java.io.Serializable;
import zws.exception.CanNotMaterialize;


/**
 * Logical representation of a data repository.
 */
public interface Repository extends Prototype, Routed, Named, Serializable  {

    /**@return String */
    QxXML toXML();

    /**
     * opens the repository for the given user.
     *
     */
  void open(Authentication id) throws Exception;

    /**
     * closes the repository for the given user.
     *
     */
  void close(Authentication id) throws Exception;
 
  /**
   * Defines configuration parameters specific for this repository's context.
   *
   * @return the context
   */
  QxContext getContext();

  /**
   * Defines configuration parameters specific for this repository's context.
   *
   * @param parameters Dictionary specifying configuration properties as a set of key, value pairs.
   */
  void configureContext(Map parameters);

  /** Sets a configuration parameter specific to this repository.
   *
   * @param key Name of the configuration parameter to be set.
   * @param value The string value that the configuration parameter should be set to.
   */
  void configureContext(String key, String value);

  /**
   * Returns a configuration parameter from this repository's context.
   *
   * @param key Name of the configuration parameter.
   *
   * @return Value the configuration parameter.
   */
  String readContext(String key);

  /**
   * Returns the name of the domain where this data repository can be found.
   *
   * @return The domain-name portion of this repository's routing.
   */
  String getDomainName();

  /**
   * Returns the name of the local DesignState server connected to this data repository.
   *
   * @return The server-name portion of this repository's routing.
   */
  String getServerName();

  /**
   * Returns the type of data repository (e.g. Intralink, TeamCenter, Agile, File System, etc.).
   *
   * @return The type of repository.
   */
  String getRepositoryType();

  /**
   * Returns the logical name for this data repository.
   *
   * @return The logical-name portion of this repository's routing.
   */
  String getRepositoryName();

  /**
   * Returns the protocol for connecting to this repository.
   *
   * @return The protocol.
   */
  String getProtocol();

  /**
   * Returns the Hostname for connecting to this repository.
   *
   * @return The repository's hostname.
   */
  String getHostName();

  /**
   * Returns the port for connecting to this repository.
   *
   * @return The repository's port.
   */
  String getPort();

  /**
   * Returns the Hostname for connecting to this repository.
   *
   * @return The repository's hostname.
   */
  String getServicesPath();

  /**
   * Returns a brief description of this data repository.
   *
   * @return A description.
   */
  String getDescription();

  /**
   * Returns a sytem user account that is available in the repository.
   *
   * @return A system authentication.
   */
  Authentication getSystemAuthentication() throws Exception;
  
  /**
   * Returns a default user account that is available in the repository.
   *
   * @return A default username.
   */
  String getSystemUsername();

  /**
   * Returns the password for the default user account.
   *
   * @return A default password.
   */
  String getSystemPassword();

  /**
   * Checks to see if a connection to the data repository can be established.
   * @throws NotConnected Exception is thrown only if a connection can not be established.
   */
  void verifyConnection() throws NotConnected;

  /**
   * Returns a basic search agent that can be used to query the repository.
   *
   * @return A Search Agent.
   *
   * @throws CanNotMaterialize the can not materialize
   */
  SearchAgent materializeSearchAgent() throws CanNotMaterialize;

  /**
   * Returns a pre-configured search agent that reports only the latest data when queried.
   *
   * @return A Search Agent.
   *
   * @throws CanNotMaterialize the can not materialize
   */
  SearchAgent materializeLatestSearchAgent()throws CanNotMaterialize;

  /**
   * Returns a pre-configured search agent that reports only the latest data for each revision when queried.
   *
   * @return A Search Agent.
   *
   * @throws CanNotMaterialize the can not materialize
   */
  SearchAgent materializeLatestRevSearchAgent()throws CanNotMaterialize;

  /**
   * Materialize metadata source.
   *
   * @return the repository metadata source
   *
   * @throws CanNotMaterialize the can not materialize
   */
  RepositoryMetadataSource materializeMetadataSource() throws CanNotMaterialize;

  /**
   * Materialize binary source.
   *
   * @return the repository binary source
   *
   * @throws CanNotMaterialize the can not materialize
   */
  RepositoryBinarySource materializeBinarySource() throws CanNotMaterialize;

  /**
   * Materialize structure source.
   *
   * @return the repository structure source
   *
   * @throws CanNotMaterialize the can not materialize
   */
  RepositoryStructureSource materializeStructureSource() throws CanNotMaterialize;

  /**
   * Materialize state source.
   *
   * @return the repository state source
   *
   * @throws CanNotMaterialize the can not materialize
   */
  RepositoryStateSource materializeStateSource() throws CanNotMaterialize;

  /**
   * Materialize configuration source.
   *
   * @return the repository configuration source
   *
   * @throws CanNotMaterialize the can not materialize
   */
  RepositoryConfigurationSource materializeConfigurationSource() throws CanNotMaterialize;

  /**
   * Materialize template source.
   *
   * @return the repository template source
   *
   * @throws CanNotMaterialize the can not materialize
   */
  RepositoryTemplateSource materializeTemplateSource() throws CanNotMaterialize;

  /**
   * Materialize metadata target.
   *
   * @return the repository metadata target
   *
   * @throws CanNotMaterialize the can not materialize
   */
  RepositoryMetadataTarget materializeMetadataTarget() throws CanNotMaterialize;

  /**
   * Materialize binary target.
   *
   * @return the repository binary target
   *
   * @throws CanNotMaterialize the can not materialize
   */
  RepositoryBinaryTarget materializeBinaryTarget() throws CanNotMaterialize;

  /**
   * Materialize structure target.
   *
   * @return the repository structure target
   *
   * @throws CanNotMaterialize the can not materialize
   */
  RepositoryStructureTarget materializeStructureTarget() throws CanNotMaterialize;

  /**
   * Materialize state target.
   *
   * @return the repository state target
   *
   * @throws CanNotMaterialize the can not materialize
   */
  RepositoryStateTarget materializeStateTarget() throws CanNotMaterialize;

  /**
   * Materialize configuration target.
   *
   * @return the repository configuration target
   *
   * @throws CanNotMaterialize the can not materialize
   */
  RepositoryConfigurationTarget materializeConfigurationTarget() throws CanNotMaterialize;

  /**
   * Materialize template target.
   *
   * @return the repository template target
   *
   * @throws CanNotMaterialize the can not materialize
   */
  RepositoryTemplateTarget materializeTemplateTarget() throws CanNotMaterialize;

  /**
   * Materialize Repository WorkflowTarget.
   *
   * @return the repository template target
   *
   * @throws CanNotMaterialize the can not materialize
   */
  RepositoryWorkflowTarget materializeWorkflowTarget() throws CanNotMaterialize;

  RepositoryECOTarget materializeECOTarget() throws CanNotMaterialize;

  RepositoryECOSource materializeECOSource() throws CanNotMaterialize;

  /**
   * Sets the state of this repository to be active and usable.
   */
  void activate() throws Exception;

  /**
   * Sets the state of this repository to be inactive and unusable.
   */
  void inactivate();
}


