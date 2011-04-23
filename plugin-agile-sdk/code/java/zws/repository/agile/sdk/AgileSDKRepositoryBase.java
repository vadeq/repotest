package zws.repository.agile.sdk;

/*
 * DesignState - Design Compression Technology @author: Arbind Thakur @version:
 * 1.0 Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 */

import zws.exception.CanNotMaterialize;
import zws.exception.NotConnected;
import zws.repository.RepositoryBase;

import zws.repository.agile.sdk.svc.AgileSDKAffectedItemSvc;
import zws.repository.agile.sdk.svc.AgileSDKBOMSvc;
import zws.repository.agile.sdk.svc.AgileSDKConfigurationSvc;
import zws.repository.agile.sdk.svc.AgileSDKECOSvc;
import zws.repository.agile.sdk.svc.AgileSDKItemSvc;
import zws.repository.agile.sdk.svc.AgileSDKPartNumberSvc;
import zws.repository.agile.sdk.svc.AgileSDKRevisionControlSvc;
import zws.repository.agile.sdk.svc.AgileSDKSvcBase;
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


/**
 * Logical representation of a data repository.
 */
public class AgileSDKRepositoryBase extends RepositoryBase {

  /**
   * The Constructor.
   */
  public AgileSDKRepositoryBase() {

  }

  public void activate() {}
  
  public void open(Authentication id) throws Exception {
    getSDKSvcBase().open(getSystemAuthentication());
    if (!overrideCredentials) getSDKSvcBase().open(id);
  }

  public void close(Authentication id) throws Exception {
    getSDKSvcBase().close(getSystemAuthentication());
    if (!overrideCredentials) getSDKSvcBase().close(id);    
  }  

  protected void  configureSDKBase(AgileSDKSvcBase agileSDKSvc) {
    this.sdkSvc = agileSDKSvc;
  }

  /**
   * Returns the type of data repository (e.g. Intralink, TeamCenter, Agile,
   * File System, etc.).
   *
   * @return The type of repository.
   */
  public String getRepositoryType() {
    return zws.origin.Origin.FROM_AGILE_SDK;
  }

  /**
   * Materialize metadata source.
   *
   * @return RepositoryMetadataSource RepositoryMetadataSource
   *
   * @throws CanNotMaterialize exception
   *
   * @see zws.repository.RepositoryBase#materializeMetadataSource()
   */
  public RepositoryMetadataSource materializeMetadataSource() throws CanNotMaterialize {
    AgileSDKRepositoryMetadataSource source = new AgileSDKRepositoryMetadataSource(getContext(), getSDKSvcBase());
    return source;
  }


  /**
   * Materialize metadata target.
   *
   * @return RepositoryMetadataTarget RepositoryMetadataTarget
   *
   * @throws CanNotMaterialize exception
   *
   * @see zws.repository.RepositoryBase#materializeMetadataTarget()
   */
  public RepositoryMetadataTarget materializeMetadataTarget()
      throws CanNotMaterialize {
    AgileSDKRepositoryMetadataTarget target = new AgileSDKRepositoryMetadataTarget(getContext(), getSDKSvcBase());
    /*AgileSDKRepositoryMetadataTarget target = new AgileSDKRepositoryMetadataTarget();
    target.configureParentContext(getContext());*/
    return target;
  }

  /**
   * Materialize metadata source.
   *
   * @return RepositoryMetadataSource RepositoryMetadataSource
   *
   * @throws CanNotMaterialize exception
   *
   * @see zws.repository.RepositoryBase#materializeMetadataSource()
   */
  public RepositoryWorkflowTarget materializeWorkflowTarget() throws CanNotMaterialize {
    AgileSDKRepositoryWorkflowTarget source = new AgileSDKRepositoryWorkflowTarget(getContext(), getSDKSvcBase());
    return source;
  }


  /**
   * Materialize structure target.
   *
   * @return RepositoryStructureTarget RepositoryStructureTarget
   *
   * @throws CanNotMaterialize exception
   *
   * @see zws.repository.RepositoryBase#materializeStructureTarget()
   */
  public RepositoryStructureTarget materializeStructureTarget()
      throws CanNotMaterialize {
    AgileSDKRepositoryStructureTarget sTarget = new AgileSDKRepositoryStructureTarget(getContext(), getSDKSvcBase());
    return sTarget;
  }

  public RepositoryECOTarget materializeECOTarget() throws CanNotMaterialize {
    RepositoryECOTarget ecoTarget = new AgileSDKRepositoryECOTarget(getContext(), getSDKSvcBase());
    return ecoTarget;
  }
  public RepositoryECOSource materializeECOSource() throws CanNotMaterialize {
    RepositoryECOSource ecoSource = new AgileSDKRepositoryECOSource(getContext(), getSDKSvcBase());
    return ecoSource;
  }

  /**
   * Materialize binary target.
   *
   * @return RepositoryBinaryTarget RepositoryBinaryTarget
   *
   * @throws CanNotMaterialize exception
   *
   * @see zws.repository.RepositoryBase#materializeBinaryTarget()
   */
  public RepositoryBinaryTarget materializeBinaryTarget() throws CanNotMaterialize {
    AgileSDKRepositoryBinaryTarget binaryTarget = new AgileSDKRepositoryBinaryTarget(getContext(), getSDKSvcBase());
    return binaryTarget;
  }
  
  /**
   * Checks to see if a connection to the data repository can be established.
   *
   * @throws NotConnected the not connected
   *
   * @Throws NotConnected
   * Exception is thrown only if a connection can not be established.
   */
  public void verifyConnection() throws NotConnected {
    throw new NotConnected("verifyConnection() is not implemented");
  }

  /**
   * Returns a basic search agent that can be used to query the repository.
   *
   * @return A Search Agent.
   *
   * @throws CanNotMaterialize the can not materialize
   */
  public SearchAgent materializeSearchAgent() throws CanNotMaterialize {
    throw new CanNotMaterialize("Search Agent", "materialize method has not been implemented", this);
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
    throw new CanNotMaterialize("LatestSearch Agent", "materialize method has not been implemented", this);
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
    throw new CanNotMaterialize("LatestRevSearch Agent", "materialize method has not been implemented", this);
  }



  /**
   * Materialize binary source.
   *
   * @return RepositoryMetadataSource RepositoryMetadataSource
   *
   * @throws CanNotMaterialize exception
   *
   * @see zws.repository.RepositoryBase#materializeBinarySource()
   */
  public RepositoryBinarySource materializeBinarySource()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("BinarySource", "materialize method has not been implemented", this);
  }

  /**
   * Materialize structure source.
   *
   * @return RepositoryStructureSource RepositoryStructureSource
   *
   * @throws CanNotMaterialize exception
   *
   * @see zws.repository.RepositoryBase#materializeStructureSource()
   */
  public RepositoryStructureSource materializeStructureSource() throws CanNotMaterialize {
    AgileSDKRepositoryStructureSource structureSource = new AgileSDKRepositoryStructureSource(getContext(), getSDKSvcBase());
    return structureSource;
  }

  /**
   * Materialize state source.
   *
   * @return RepositoryStateSource RepositoryStateSource
   *
   * @throws CanNotMaterialize exception
   *
   * @see zws.repository.RepositoryBase#materializeStateSource()
   */
  public RepositoryStateSource materializeStateSource()
      throws CanNotMaterialize {
    AgileSDKRepositoryStateSource agileSource = new AgileSDKRepositoryStateSource(getContext());
    return agileSource;
  }

  /**
   * Materialize configuration source.
   *
   * @return RepositoryConfigurationSource RepositoryConfigurationSource
   *
   * @throws CanNotMaterialize exception
   *
   * @see zws.repository.RepositoryBase#materializeConfigurationSource()
   */
  public RepositoryConfigurationSource materializeConfigurationSource()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("ConfigurationSource", "materialize method has not been implemented", this);
  }

  /**
   * Materialize template source.
   *
   * @return RepositoryTemplateSource RepositoryTemplateSource
   *
   * @throws CanNotMaterialize exception
   *
   * @see zws.repository.RepositoryBase#materializeTemplateSource()
   */
  public RepositoryTemplateSource materializeTemplateSource()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("TemplateSource", "materialize method has not been implemented", this);
  }


  /**
   * Materialize state target.
   *
   * @return RepositoryStateTarget RepositoryStateTarget
   *
   * @throws CanNotMaterialize exception
   *
   * @see zws.repository.RepositoryBase#materializeStateTarget()
   */
  public RepositoryStateTarget materializeStateTarget()
      throws CanNotMaterialize {
    AgileSDKRepositoryStateTarget agileTarget = new AgileSDKRepositoryStateTarget(getContext());
    return agileTarget;
  }

  /**
   * Materialize configuration target.
   *
   * @return RepositoryConfigurationTarget RepositoryConfigurationTarget
   *
   * @throws CanNotMaterialize exception
   *
   * @see zws.repository.RepositoryBase#materializeConfigurationTarget()
   */
  public RepositoryConfigurationTarget materializeConfigurationTarget()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("ConfigurationTarget", "materialize method has not been implemented", this);
  }

  /**
   * Materialize template target.
   *
   * @return RepositoryTemplateTarget RepositoryTemplateTarget
   *
   * @throws CanNotMaterialize exception
   *
   * @see zws.repository.RepositoryBase#materializeTemplateTarget()
   */
  public RepositoryTemplateTarget materializeTemplateTarget()
      throws CanNotMaterialize {
    throw new CanNotMaterialize("TemplateTarget", "materialize method has not been implemented", this);
  }


  protected void prepareSpecificArgs(StringBuffer xmlString) {
    xmlString.append(prepareArg(AgileSDKConstants.OWNERSHIP_BOM_ATTRIBUTE, this.getOwnershipBomAttribute()));
    xmlString.append(prepareArg(AgileSDKConstants.OWNERSHIP_BOM_ATTRIBUTE_VALUE, this.getOwnershipBomAttributeValue()));
    xmlString.append(prepareArg(AgileSDKConstants.OVERRIDE_CREDENTIALS, Boolean.toString(this.getOverrideCredentials())));
  }
  
  
  /**
   * Gets the SDK svc base.
   *
   * @return the affectedItemSvc
   */
  public AgileSDKSvcBase getSDKSvcBase() {
    if (null == sdkSvc) {
      sdkSvc = new AgileSDKSvcBase(this);
    }
    return sdkSvc;
  }

  /**
   * Gets the affected item svc.
   *
   * @return the affectedItemSvc
   */
  public AgileSDKAffectedItemSvc getAffectedItemSvc() {
    if (null == affectedItemSvc) {
      affectedItemSvc = getSDKSvcBase().materializeAffectedItemSvc();
    }
    return affectedItemSvc;
  }

  /**
   * Gets the bom svc.
   *
   * @return the bomSvc
   */
  public AgileSDKBOMSvc getBomSvc() {
    if (null == bomSvc) {
      bomSvc = getSDKSvcBase().materializeBOMSvc();
    }
    return bomSvc;
  }

  /**
   * Gets the eco svc.
   *
   * @return the ecoSvc
   */
  public AgileSDKECOSvc getEcoSvc() {
    if (null == ecoSvc) {
      ecoSvc = getSDKSvcBase().materializeECOSvc();
    }
    return ecoSvc;
  }

  /**
   * Gets the item svc.
   *
   * @return the itemSvc
   */
  public AgileSDKItemSvc getItemSvc() {
    if (null == itemSvc) {
      itemSvc = getSDKSvcBase().materializeItemSvc();
    }
    return itemSvc;
  }

  /**
   * Gets the part number svc.
   *
   * @return the partNumberSvc
   */
  public AgileSDKPartNumberSvc getPartNumberSvc() {
    if (null == partNumberSvc) {
      partNumberSvc = getSDKSvcBase().materializePartNumberSvc();
    }
    return partNumberSvc;

  }

  /**
   * Gets the revision control svc.
   *
   * @return the revisionControlSvc
   */
  public AgileSDKRevisionControlSvc getRevisionControlSvc() {
    if (null == revisionControlSvc) {
      revisionControlSvc = getSDKSvcBase().materializeRevisionControlSvc();
    }
    return revisionControlSvc;
  }

  /**
   * Gets the config svc.
   *
   * @return the configSvc
   */
  public AgileSDKConfigurationSvc getConfigSvc() {
    if (null == configSvc) {
      configSvc = getSDKSvcBase().materializeConfigurationSvc();
    }
    return configSvc;
  }

  public boolean getOverrideCredentials() {
    return overrideCredentials;
  }
  
  public void setOverrideCredentials(boolean value) {
    overrideCredentials = value;
  }
  
  public void setOverrideCredentials(String value) {
    
    // verify we have a legit value
    if (value != null && "true".equals(value.toLowerCase()))
      overrideCredentials = true;
    else
      overrideCredentials = false;
  }
  
  public String getOwnershipBomAttribute() {
    return getContext().get(AgileSDKConstants.OWNERSHIP_BOM_ATTRIBUTE);
  }

  public void setOwnershipBomAttribute(String s) {
    getContext().set(AgileSDKConstants.OWNERSHIP_BOM_ATTRIBUTE, s);
  }
  public String getOwnershipBomAttributeValue() {
    return getContext().get(AgileSDKConstants.OWNERSHIP_BOM_ATTRIBUTE_VALUE);
  }

  public void setOwnershipBomAttributeValue(String s) {
    getContext().set(AgileSDKConstants.OWNERSHIP_BOM_ATTRIBUTE_VALUE, s);
  }
  
  /** The session svc. */
  //protected AgileSDKSessionSvc sessionSvc = null;

  /** The config svc. */
  protected AgileSDKConfigurationSvc configSvc = null; 

  /** The sdk svc. */
  private AgileSDKSvcBase sdkSvc = null;

  /** The item svc. */
  private AgileSDKItemSvc itemSvc = null;

  /** The affected item svc. */
  private AgileSDKAffectedItemSvc affectedItemSvc = null;

  /** The bom svc. */
  private AgileSDKBOMSvc bomSvc = null;

  /** The eco svc. */
  private AgileSDKECOSvc ecoSvc = null;

  /** The part number svc. */
  private AgileSDKPartNumberSvc partNumberSvc = null;

  /** The revision control svc. */
  private AgileSDKRevisionControlSvc revisionControlSvc = null;
  
  private boolean overrideCredentials = false;
}
