package zws.repository.agile.plm.api;


/*
 * DesignState - Design Compression Technology @author: Arbind Thakur @version:
 * 1.0 Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 */

import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.exception.CanNotMaterialize;
import zws.exception.NotConnected;
import zws.origin.AgileOrigin;
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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.agile.plmapi.api.PlmData;
import com.agile.plmapi.api.PlmObject;
import com.agile.plmapi.api.PlmResponse;


/**
 * Logical representation of a data repository.
 */
public class AgilePLMAPIRepositoryBase extends RepositoryBase {

  /**
   * The Constructor.
   */
  public AgilePLMAPIRepositoryBase() {

  }

  public void activate () {}

  /**
   * Returns the type of data repository (e.g. Intralink, TeamCenter, Agile,
   * File System, etc.).
   *
   * @return The type of repository.
   */
  public String getRepositoryType() {
    return zws.origin.Origin.FROM_AGILE;
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
   * Materialize metadata source.
   *
   * @return RepositoryMetadataSource RepositoryMetadataSource
   *
   * @throws CanNotMaterialize exception
   *
   * @see zws.repository.RepositoryBase#materializeMetadataSource()
   */
  public RepositoryMetadataSource materializeMetadataSource() throws CanNotMaterialize {
    AgilePLMAPIRepositoryMetadataSource source = new AgilePLMAPIRepositoryMetadataSource(getContext());
    return source;
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
  public RepositoryBinarySource materializeBinarySource() throws CanNotMaterialize {
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
    throw new CanNotMaterialize("StructureSource", "materialize method has not been implemented", this);
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
  public RepositoryStateSource materializeStateSource() throws CanNotMaterialize {
    throw new CanNotMaterialize("StateSource", "materialize method has not been implemented", this);
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
  public RepositoryConfigurationSource materializeConfigurationSource() throws CanNotMaterialize {
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
  public RepositoryTemplateSource materializeTemplateSource() throws CanNotMaterialize {
    throw new CanNotMaterialize("TemplateSource", "materialize method has not been implemented", this);
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
  public RepositoryMetadataTarget materializeMetadataTarget() throws CanNotMaterialize {
    AgilePLMAPIRepositoryMetadataTarget target = new AgilePLMAPIRepositoryMetadataTarget(getContext());
    return target;
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
    throw new CanNotMaterialize("BinaryTarget", "materialize method has not been implemented", this);
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
  public RepositoryStructureTarget materializeStructureTarget() throws CanNotMaterialize {
    AgilePLMAPIRepositoryStructureTarget target = new AgilePLMAPIRepositoryStructureTarget(getContext());
    return target;
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
  public RepositoryStateTarget materializeStateTarget() throws CanNotMaterialize {
    throw new CanNotMaterialize("StateTarget", "materialize method has not been implemented", this);
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
  public RepositoryConfigurationTarget materializeConfigurationTarget() throws CanNotMaterialize {
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
  public RepositoryTemplateTarget materializeTemplateTarget() throws CanNotMaterialize {
    throw new CanNotMaterialize("TemplateTarget", "materialize method has not been implemented", this);
  }

  public String getURL() {
    String url=getProtocol() + "://" + getHostName()+ ":" +getPort() + "/" + getServicesPath();
    return url;
  }

  private static void validate(PlmResponse response) throws Exception {
    if (null==response) throw new Exception ("Agile Response is null");
    if (null==response.getStatus()) throw new Exception ("Agile Response has no status.");
    List errors = response.getStatus().getErrors();
    List fatals = response.getStatus().getFatals();
    Collection k = new Vector();
    if (null!=fatals) k.addAll(fatals);
    if (null!=errors) k.addAll(errors);
    if (k.isEmpty()) return; //no errors, response is valid

    Iterator i = k.iterator();
    Object err;
    String msg = "ERRORS: ";
    while(i.hasNext()) {
      err = i.next();
      msg += " " + err.toString();
    }
    throw new Exception (msg);
  }

  protected Collection extractMetadataList(PlmResponse response) throws Exception {
    Collection c = extractMetadataResults(response);
    return c;
  }

  protected Metadata extractMetadataResult(PlmResponse response) throws Exception {
    Metadata m=null;
    Collection c = extractMetadataResults(response);
    if (c.isEmpty()) return m;
    m = (Metadata)c.iterator().next();
    return m;
  }

  protected Collection extractMetadataResults(PlmResponse response) throws Exception {
    Collection results = new Vector();
    {} //System.out.println("status "  + response.getStatus());
    validate(response);
    PlmData data = response.getData();
    if (null==data || null==data.getObjects()) return results;
    Iterator dataItr = data.getObjects().iterator();
    Metadata mData=null;
    while(dataItr.hasNext()) {
      PlmObject obj = (PlmObject)dataItr.next();
      mData = unmarshall(obj);
      results.add(mData);
    }
    return results;
  }

  public Metadata unmarshall(PlmObject obj) throws Exception {
    MetadataBase m = null;
    if (null==obj) return m;
    Map attrs = obj.getAttributes();
    if (null==attrs) return m;
    m = new MetadataBase();
    Iterator itr = attrs.keySet().iterator();
    String key;
    String value;
    while(itr.hasNext()) {
      key = (String) itr.next();
      value = obj.getAttributeValue(key);
      if(null != key && key.length()>0 &&
          null != value && value.length()>0) {
       //System.out.println(key + " -- " + value);
        m.set(key, value);
      }
      if ("number".equalsIgnoreCase(key)) {
        m.setName(value);
        // number is coming as Number
        m.set("number", value);
      }
    }
    AgileOrigin o = new AgileOrigin(getDomainName(),getServerName(),getName(),
        obj.getId(),obj.getAttributeValue(com.agile.plmapi.api.PlmClassConstants.ATTRIB_CLASS_NAME),
        m.getName());
      o.setTimeOfCreationInMillis(System.currentTimeMillis());
      m.setOrigin(o);
    return m;
  }
}
