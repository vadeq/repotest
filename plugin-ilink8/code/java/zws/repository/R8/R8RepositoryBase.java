package zws.repository.R8;/*
                           * DesignState - Design Compression Technology
                           * @author: Arbind Thakur @version: 1.0 Copywrite (c)
                           * 2003 Zero Wait-State Inc. All rights reserved
                           */

import java.io.StringReader;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.xerces.parsers.DOMParser;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.exception.CanNotMaterialize;
import zws.exception.NotConnected;
import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.qx.QxContext;
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

/**
 * import org.apache.xpath.XPathAPI; import org.w3c.dom.Document; import
 * org.w3c.dom.Node; import org.w3c.dom.NodeList; /** Logical representation of
 * an intralink 8+ repository.
 */
public class R8RepositoryBase extends RepositoryBase {

  public void activate() {};

  /**
   * Returns the type of data repository (e.g. Intralink, TeamCenter, Agile,
   * File System, etc.).
   *
   * @return The type of repository.
   */
	/*
  public static String materializeUiqueID(String name, String oid,
      String branch, String rev, long iteration) {
    return oid + d + branch + d + rev + d + iteration + d + name;
  }*/
  public static String materializeUiqueID(String name, String obid, String number, String CADName, String revision, long iteration) {
    return obid + d + number + d + CADName + d + revision + d + iteration + d + name;
  }

  /**
   * Returns the type of data repository (e.g. Intralink, TeamCenter, Agile,
   * File System, etc.).
   *
   * @return The type of repository.
   */
  public String getRepositoryType() {
    return Origin.FROM_ILINK_8;
  }

  protected String lookupLinkObid(Metadata m) {
    Collection fields = m.getFieldNames();
    if (null==fields) return null;
    Iterator i = fields.iterator();
    String field;
    String linkObid = null;
    while(null==linkObid && i.hasNext()) {
      field = (String) i.next();
      if (field.toLowerCase().endsWith(".defaultepmmemberlink.obid")) linkObid = m.get(field);
    }
    return linkObid;
  }


  public RepositoryMetadataSource materializeMetadataSource() throws CanNotMaterialize {
    R8RepositoryMetadataSource source = new R8RepositoryMetadataSource(getContext());
    return source;
  }

  public RepositoryBinarySource materializeBinarySource() throws CanNotMaterialize {
    R8RepositoryBinarySource source = new R8RepositoryBinarySource(getContext());
    return source;
  }

  public RepositoryStructureSource materializeStructureSource() throws CanNotMaterialize {
    R8RepositoryStructureSource source = new R8RepositoryStructureSource(getContext());
    return source;
  }

  public SearchAgent materializeSearchAgent() throws CanNotMaterialize {
    R8RepositorySearchAgent agent = new R8RepositorySearchAgent();
    agent.setRepository(this);
    return agent;
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
    throw new CanNotMaterialize("LatestSearch Agent", "materialize method has not been implemented", this);
  }

  /**
   * Returns a pre-configured search agent that reports only the latest data for
   * each revision when queried.
   *
   * @return A Search Agent.
   */
  public SearchAgent materializeLatestRevSearchAgent() throws CanNotMaterialize {
    throw new CanNotMaterialize("LatestRevSearch Agent", "materialize method has not been implemented", this);
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
    R8RepositoryMetadataStateTarget source = new R8RepositoryMetadataStateTarget(getContext());
    return source;
  }

  public RepositoryConfigurationTarget materializeConfigurationTarget() throws CanNotMaterialize {
    throw new CanNotMaterialize("ConfigurationTarget", "materialize method has not been implemented", this);
  }

  public RepositoryTemplateTarget materializeTemplateTarget() throws CanNotMaterialize {
    throw new CanNotMaterialize("TemplateTarget", "materialize method has not been implemented", this);
  }

  public String objectName(String name) {
    if (getLowerCasedFilenames()) {
      return name.toLowerCase();
    }
    else if (getUpperCasedFilenames()) {
      return name.toUpperCase();
    }
    else {
      return name;
    }
  }

  public boolean getLowerCasedFilenames() {
    String s = getContext().get(R8Constants.LOWER_CASED_FILENAMES, Boolean.valueOf(true).toString());
    return Boolean.valueOf(s).booleanValue();
  }

  public void setLowerCasedFilenames(boolean b) {
    getContext().set(R8Constants.LOWER_CASED_FILENAMES, Boolean.valueOf(b).toString());
  }

  public boolean getUpperCasedFilenames() {
    String s = getContext().get(R8Constants.UPPER_CASED_FILENAMES, Boolean.valueOf(false).toString());
    return Boolean.valueOf(s).booleanValue();
  }

  public void setUpperCasedFilenames(boolean b) {
    getContext().set(R8Constants.UPPER_CASED_FILENAMES, Boolean.valueOf(b).toString());
  }

  public long getConnectionTimeout() {
    String s = getContext().get(R8Constants.CONNECTION_TIMEOUT, "30");
    return Long.valueOf(s).longValue();
  }

  public void setConnectionTimeout(long l) {
    getContext().set(R8Constants.CONNECTION_TIMEOUT, "" + l);
  }

  public String getDateFormat() {
    return getContext().get(R8Constants.DATE_FORMAT, R8Constants.DEFAULT_DATE_FORMAT);
  }

  public void setDateFormat(String s) {
    getContext().set(R8Constants.DATE_FORMAT, s);
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
    if (tokens.hasMoreTokens()) year = Integer.valueOf(tokens.nextToken()).intValue();
    if (tokens.hasMoreTokens()) month = Integer.valueOf(tokens.nextToken()).intValue();
    if (tokens.hasMoreTokens()) day = Integer.valueOf(tokens.nextToken()).intValue();
    if (tokens.hasMoreTokens()) hour = Integer.valueOf(tokens.nextToken()).intValue();
    if (tokens.hasMoreTokens()) min = Integer.valueOf(tokens.nextToken()).intValue();
    if (tokens.hasMoreTokens()) sec = Integer.valueOf(tokens.nextToken()).intValue();
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

  public HashMap makeOriginHM(Authentication id) {
    HashMap originHM = new HashMap();
    originHM.put("R8_WBSERVICE_URL", getSOAPServiceURL());
    originHM.put("R8_USERNAME", id.getUsername());
    originHM.put("R8_PASSWORD", id.getPassword());
    originHM.put("R8_OPERATION", getRemoteMethod());

    return originHM;
  }

  public String getAttributes(String selectAttributes) throws Exception {
    // merge requiredAttributes and selectAttributes in to one string
    StringBuffer finalAttributes = new StringBuffer();
    String requiredAttributes = "name,number,version,iteration,state," +
                                "CADName,container,quantity,unit," +
                                "authoring-application," +
                                "creator, created-on," +
                                "modified-by, modified-on";
    String[] requiredAttributeList = requiredAttributes.split(",");
    HashMap attributeMap = new HashMap();
    String att=null;
    int idx = 0;
    while(idx<requiredAttributeList.length) {
      att = requiredAttributeList[idx].trim();
      if (null==att) att="";
      att = att.trim();
      attributeMap.put(att, att);
      idx++;
    }
    if(null != selectAttributes) {
      String[] selectAttributesList = selectAttributes.trim().split(",");
      if (0==selectAttributesList.length) attributeMap.put(selectAttributes.trim(), selectAttributes.trim());
      idx = 0;
      while(idx<selectAttributesList.length) {
        att = selectAttributesList[idx];
        if (null==att) att="";
        att = att.trim();
        attributeMap.put(att, att);
        idx++;
      }
    }

    Iterator itr = attributeMap.keySet().iterator();
    while(itr.hasNext()) {
      finalAttributes.append(itr.next()).append(",");
    }
    String s =  finalAttributes.toString();
    s = s.substring(0, s.lastIndexOf(','));
    return s;
  }


  public static HashMap fromILinkToZwsMap = new HashMap();
  public static HashMap fromZwsToILinkMap = new HashMap();
  private void loadMappings(String ilinkDomainName) throws Exception{
    if(null == ilinkDomainName ||ilinkDomainName.length() < 1) {
      throw new zws.exception.zwsException("ilink Domain name is not set");
    }
	  //String ilinkDomainName = "com.najanaja.plm";
    fromILinkToZwsMap.put("name", "name");
    fromILinkToZwsMap.put("number", "number");
    fromILinkToZwsMap.put("versionInfo.identifier.versionId", "version");
    fromILinkToZwsMap.put("iterationInfo.identifier.iterationId", "iteration");
    fromILinkToZwsMap.put("CADName", "CADName");
    fromILinkToZwsMap.put("state.state", "state");
	  fromILinkToZwsMap.put("containerName", "container");
	  fromILinkToZwsMap.put("WCTYPE|wt.epm.structure.EPMMemberLink|" + ilinkDomainName + ".DefaultEPMMemberLink.quantity.amount", "quantity");
	  fromILinkToZwsMap.put("WCTYPE|wt.epm.structure.EPMMemberLink|" + ilinkDomainName + ".DefaultEPMMemberLink.quantity.unit", "unit");
	  fromILinkToZwsMap.put("WCTYPE|wt.epm.structure.EPMMemberLink|" + ilinkDomainName + ".DefaultEPMMemberLink.suppressed", "issuppressed");
	  fromILinkToZwsMap.put("WCTYPE|wt.epm.structure.EPMMemberLink|" + ilinkDomainName + ".DefaultEPMMemberLink.depType", "dependencytype");
    fromILinkToZwsMap.put("creator", "creator");
    fromILinkToZwsMap.put("thePersistInfo.createStamp", "created-on");
    fromILinkToZwsMap.put("obid", "obid");
    fromILinkToZwsMap.put("Description", "Description");
    fromILinkToZwsMap.put("authoringApplication", "authoring-application");
    fromILinkToZwsMap.put("modifier", "modified-by");
    fromILinkToZwsMap.put("thePersistInfo.modifyStamp", "modified-on");

    fromZwsToILinkMap.put("name", "name");
    fromZwsToILinkMap.put("number", "number");
    fromZwsToILinkMap.put("version", "versionInfo.identifier.versionId");
    fromZwsToILinkMap.put("iteration", "iterationInfo.identifier.iterationId");
    fromZwsToILinkMap.put("Description", "Description");
    fromZwsToILinkMap.put("CADName", "CADName");
	  fromZwsToILinkMap.put("state", "state.state");
    fromZwsToILinkMap.put("container", "containerName");
	  fromZwsToILinkMap.put("quantity", "quantity.amount");
	  fromZwsToILinkMap.put("unit", "quantity.unit");
    fromZwsToILinkMap.put("issuppressed", "suppressed");
    fromZwsToILinkMap.put("dependencytype", "depType");
    fromZwsToILinkMap.put("creator", "creator");
    fromZwsToILinkMap.put("created-on", "thePersistInfo.createStamp");
    fromZwsToILinkMap.put("obid", "obid");
    fromZwsToILinkMap.put("authoring-application", "authoringApplication");
    fromZwsToILinkMap.put("modified-by", "modifier");
    fromZwsToILinkMap.put("modified-on", "thePersistInfo.modifyStamp");
  }


  public String translateReturnAttributes(String returnAttributes) throws Exception{
	  StringTokenizer tokens = new StringTokenizer(returnAttributes, ",");
	  returnAttributes = "";
	  String delim = "";
	  while(tokens.hasMoreTokens()){
		  String token = tokens.nextToken();
		  returnAttributes = returnAttributes + delim + fromZwsToILinkAttributeTranslation(token);
		  delim = ",";
	  }
	  return returnAttributes;
  }

  public String fromILinkToZwsAttributeTranslation(String attributeName) throws Exception {
    loadMappings(this.getIlinkServerDomain());
	  String result = (String) fromILinkToZwsMap.get(attributeName);
	  if(result == null){
		  result = attributeName.substring(4);
	  }
	  return result;
  }

  public String fromZwsToILinkAttributeTranslation(String attributeName) throws Exception {
    loadMappings(this.getIlinkServerDomain());
	  String result = (String) fromZwsToILinkMap.get(attributeName);
	  if(result == null){
		  result = "IBA|" + attributeName;
	  }
	  return result;
  }
  /*
  public String fromZwsToILinkAttributeTranslation(String attributeName){
	  String result = null;
	  Iterator iterator = attributeMap.keySet().iterator();
	  while(iterator.hasNext()){
		  String key = (String) iterator.next();
		  String value = (String) attributeMap.get(key);
		  if(value.equalsIgnoreCase(attributeName)){
			  result = key;
			  break;
		  }
	  }
	  if(result == null){
		  result = attributeName;
	  }
	  return result;
  }*/

  public Vector materializeMetadata(String xmlResult, String attributes)
      throws Exception {
    return (Vector) materializeMetadata(xmlResult, attributes, false);
  }

  public Object materializeMetadata(String xmlResult, String attributes,
      boolean returnSingle) throws Exception {
    Document document = getDocumentByString(xmlResult);
    NodeList nodeList = XPathAPI.selectNodeList(document,"/wc:COLLECTION//wc:INSTANCE");

    HashMap hashMap = new HashMap();
    Vector collection = new Vector();

    MetadataBase m = null;
    Node node = null;
    NodeList childNodeList = null;
    String key = null;
    String value = null;
    String name = null;
	String number =  null;
	String CADName = null;
	String obid = null;
	String revision = null;
	String iteration = null;
	String timeOfCreationString =  null;
	String uniqueID = null;
	Origin o = null;
	long createdTimeStamp = -1;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    java.util.Date date = null;

    for (int i = 0; i < nodeList.getLength(); i++) {
      node = nodeList.item(i);
      childNodeList = node.getChildNodes();
      Node childNode = null;
      for (int j = 0; j < childNodeList.getLength(); j++) {
        childNode = childNodeList.item(j);
        if (childNode.getNodeType() == Node.TEXT_NODE) {
          continue;
        }
        if (childNode.getFirstChild() != null) {
        	Node firstChild = childNode.getFirstChild();
        	if(childNode.getNodeName().equalsIgnoreCase("wc:Attribute")){

        		hashMap.put(childNode.getAttributes().getNamedItem("NAME").getNodeValue(), firstChild.getNodeValue());
        	} else {
        		hashMap.put(childNode.getNodeName(), firstChild.getNodeValue());
        	}
        }
      }

      m = new MetadataBase();
      Iterator iterator = hashMap.keySet().iterator();

      while (iterator.hasNext()) {
        key = (String) iterator.next();
        value = (String) hashMap.get(key);
        m.set(fromILinkToZwsAttributeTranslation(key), value);
      }

      name = (String) m.get("name");
      number = (String) m.get("number");
      CADName = (String) m.get("CADName");
      obid = (String) m.get("obid");
      revision = (String) m.get("version");
      iteration = (String) m.get("iteration");
      timeOfCreationString = (String) m.get("created-on");

      createdTimeStamp = -1;
      if (timeOfCreationString != null) {
        date = sdf.parse(timeOfCreationString);
        createdTimeStamp = date.getTime();
      }
      long iterationLong = Long.valueOf(iteration).longValue();

      uniqueID = R8RepositoryBase.materializeUiqueID(name, obid, number, CADName, revision, iterationLong);
      o = OriginMaker.materialize(getDomainName(), getServerName(), Origin.FROM_ILINK_8, getName(), createdTimeStamp, uniqueID, null, null);
      m.setOrigin(o);

      if (returnSingle) { break; }
      collection.addElement(m);
    }
    if (returnSingle) { return m; }


    return collection;
  }

  public static Document getDocumentByString(String xmlString) throws Exception {
    if(null == xmlString || "".equals(xmlString)) return null;
    DOMParser parser = new DOMParser();
    StringReader reader = new StringReader(xmlString);
    InputSource source = new InputSource(reader);
    parser.parse(source);
    Document document = parser.getDocument();
    return document;
  }

  public void setInstanceName(String s) {
    getContext().set(QxContext.INSTANCE_NAME, s);
  }
  public void setRemoteMethod(String s) {
    getContext().set(QxContext.REMOTE_METHOD, s);
  }
  public void setDownloadServicesPath(String s) {
    getContext().set(QxContext.DOWNLOAD_SERVICES_PATH, s);
  }
  public void setIlinkServerDomain(String s) {
    getContext().set(QxContext.ILINK_SERVER_DOMAIN, s);
  }

  public String getInstanceName() {
    return getContext().get(QxContext.INSTANCE_NAME);
  }
  public String getRemoteMethod() {
    return getContext().get(QxContext.REMOTE_METHOD);
  }
  public String getDownloadServicesPath() {
    return getContext().get(QxContext.DOWNLOAD_SERVICES_PATH);
  }

  public String getIlinkServerDomain() {
    return getContext().get(QxContext.ILINK_SERVER_DOMAIN);
  }

  protected void prepareSpecificArgs(StringBuffer xmlString) {
    xmlString.append(prepareArg(QxContext.INSTANCE_NAME, this.getInstanceName()));
    xmlString.append(prepareArg(QxContext.REMOTE_METHOD, this.getRemoteMethod()));
    xmlString.append(prepareArg(QxContext.DOWNLOAD_SERVICES_PATH, this.getDownloadServicesPath()));
    xmlString.append(prepareArg(QxContext.ILINK_SERVER_DOMAIN, this.getIlinkServerDomain()));
  }


  public String getSOAPServiceURL() {
    return "http://" + this.getHostName() + "/" + this.getServicesPath();
    //return "http://10.10.10.208/Windchill/servlet/RPC?CLASS=com.infoengine.soap";
  }


  public String getDownloadServiceURL() {
    return "http://" + this.getHostName() + "/" + this.getDownloadServicesPath();
    //return "http://10.10.10.208/Windchill/netmarkets/jsp/zws/downloadpdmcontent.jsp";
  }

  // default configurations
  public static String AS_STORED_CONFIGURATION = "as-stored";

  public static String LATEST_CONFIGURATION = "latest";

  public static String NO_DEPENDENCIES = "none";

  public static String ALL_DEPENDENCIES = "all";

  public static String ILINK_ALL_DEPENDENCIES = "ilink-all";

  public static String REQUIRED_DEPENDENCIES = "required";

  public static String ILINK_REQUIRED_DEPENDENCIES = "ilink-required";

  private static String d = Origin.delim;

}
