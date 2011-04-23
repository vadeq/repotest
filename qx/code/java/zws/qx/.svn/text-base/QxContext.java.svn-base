package zws.qx;
/*
DesignState - Design Compression Technology.
@author: Santhos Avunuri, Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */


import java.io.PrintStream;
import java.io.Serializable;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class QxContext implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public QxContext() {}
  public QxContext(String asXML) { setProperties(asXML); }

  public QxContext parent() { return parentContext; }
  public void configureParent(QxContext parent) { parentContext = parent; }

  public String get(String property) {
    String value = (String) properties.get(property);
    if (null==value && null!=parentContext) value = parentContext.get(property);
    return value;
  }

  public String get(String property, String defaultValue) {
    String value = get(property);
    if (null == value) {
      value = defaultValue;
    }
    return value;
  }

  public long getLong(String property) { return Long.valueOf(get(property)).longValue(); }
  public int getInt(String property) { return Integer.valueOf(get(property)).intValue(); }
  public boolean getBool(String property) { return Boolean.valueOf(get(property)).booleanValue(); }

  public void set(String property, String value) { properties.put(property, value); }

  public void merge(QxContext ctx, boolean overwrite) {
    if (null==ctx) return;
    Stack stack = new Stack();
    QxContext c = ctx;
    while (c!=null) {
      stack.push(c);
      c = c.parent();
    }
    while (!stack.isEmpty()) {
      c = (QxContext)stack.pop();
      setAll(c.properties, overwrite);
    }
  }

  public void setAll(Map parameters, boolean overwrite) {
    if (null == parameters || parameters.isEmpty()) return;
    Iterator i = parameters.keySet().iterator();
    String key;
    String o;
    while (i.hasNext()) {
      key = i.next().toString();
      o = parameters.get(key).toString();
      if (null==key) continue;
      if (!overwrite && contains(key)) continue;
      set(key, o);
    }
  }

  public void setAll(Map parameters) {
    if (null == parameters || parameters.isEmpty()) return;
    Iterator i = parameters.keySet().iterator();
    String key;
    String o;
    while (i.hasNext()) {
      key = i.next().toString();
      o = parameters.get(key).toString();
      if (null==key) continue;
      set(key, o);
    }
  }

  public boolean contains(String property){ return properties.containsKey(property); }

  public void dump(PrintStream stream){
    Iterator i = properties.keySet ().iterator();
    String field;
    while (i.hasNext()) {
      field = (String) i.next();
      stream.println(field + "=" + properties.get(field));
    }
  }

  public String toString(){
    Map fullMap = flattenKeys();
    Iterator i = fullMap.keySet().iterator();
    StringBuffer fieldBfr = new StringBuffer();
    fieldBfr.append("<string_context ");

    while (i.hasNext()) {
      String fld = (String) i.next();
      fieldBfr.append( fld )
      .append("=")
      .append("\"")

      .append(fullMap.get(fld))

      .append("\"")
      .append(" ");
    }

    fieldBfr.append("/>");
    return fieldBfr.toString();
  }

private Map flattenKeys() {
  Map finalMap = new HashMap();
  if (null != parentContext) addMapInto(finalMap, parentContext.flattenKeys());
  finalMap = addMapInto(finalMap, properties);
  return finalMap;
}

/** Add the contents of subMap into finalMap
 * @param finalMap
 * @param subMap
 */
private Map  addMapInto(Map finalMap, Map subMap) {
  Iterator itr = null;
  Object key = null;
  if(null != subMap) {
    itr = subMap.keySet().iterator();
    while(itr.hasNext()) {
      key = itr.next();
          finalMap.put(key, subMap.get(key));
    }
  }
  return finalMap;
}

  //some convenience lookups:
  public String lookupSOAPProtocol() {
    String lookupValue = null;
    lookupValue = get(SOAP_PROTOCOL);
    if (lookupValue == null) lookupValue = "http";
    return lookupValue;
  }

  public String lookupSOAPHostName() {
    String lookupValue = null;
    lookupValue = get(SOAP_HOSTNAME);
    if (lookupValue == null) lookupValue = "designstate-0";
    return lookupValue;
  }

  public String lookupSOAPPort() {
    String lookupValue = null;
    lookupValue = get(SOAP_PORT);
    if (lookupValue == null) lookupValue = "80";
    return lookupValue;
  }

  public String lookupSOAPServicesPath() {
    String lookupValue = null;
    lookupValue = get(SOAP_SERVICES_PATH);
    if (lookupValue == null) lookupValue = "ZeroWait-State/services";
    return lookupValue;
  }

  public String lookupSOAPServiceName() {
    String lookupValue = null;
    lookupValue = get(SOAP_SERVICE_NAME);
    if (lookupValue == null) lookupValue = "QxWebService";
    return lookupValue;
  }

  public String lookupSOAPServiceOperation() {
    String lookupValue = null;
    lookupValue = get(SOAP_SERVICE_OPERATION);
    //{}//Logwriter.printOnConsole("SOAP_SERVICE_OPERATION " + lookupValue);
    //if (lookupValue == null) lookupValue = "executeQx";
    if (lookupValue == null) lookupValue = "runQx";
    return lookupValue;
  }

  public String lookupEndPointReference() {
    String epr = "";
    epr = lookupSOAPProtocol() + "://" + lookupSOAPHostName()
        + ":" + lookupSOAPPort() + "/"
        + lookupSOAPServicesPath() + "/"
        + lookupSOAPServiceName();
    {}//Logwriter.printOnConsole("->>> EPR: " + epr);
    return epr;
  }

  public String lookupSOAPServiceNameSpace() {
    String lookupValue = null;
    lookupValue = get(SOAP_SERVICE_NAMESPACE);
    {}//Logwriter.printOnConsole("SOAP_SERVICE_NAMESPACE " + lookupValue);
    if (lookupValue == null) lookupValue = Qx.WS_NAMESPACE;
    return lookupValue;
  }

  public String lookupSOAPSchemaTargetNameSpace() {
    String targetNS = lookupSOAPServiceNameSpace();
    // if ( !targetNS.endsWith("/xsd") ) targetNS += "/xsd";
    return targetNS;
  }

  public String lookupSOAPNameSpacePrefix() {
    String lookupValue = get(SOAP_SERVICE_NAMESPACE_PREFIX);
    if (lookupValue == null) lookupValue = "zwsns";
    return lookupValue;
  }

  public String lookupJavaServicesPackage() {
    String packagePath = get(JAVA_SERVICES_PACKAGE);
    if (null==packagePath) packagePath = "zws.qx.service.";
    return packagePath;
  }

  public String lookupJavaServiceClassname() {
    String packagePath = get(JAVA_SERVICE_CLASSNAME);
    if (null==packagePath) packagePath = "QxService";
    return packagePath;
  }

  public String lookupJavaFQCN() {
    return lookupJavaServicesPackage() + dot + lookupJavaServiceClassname();
  }

  public int getPriority() {
    String p = get(PRIORITY);
    if (null==p) return 1;
    return Integer.valueOf(p).intValue();
  }
  public void setPriority(int priority) {
    set(PRIORITY, ""+priority);
  }

  public Object copy() {
    try {
      QxContext ctxClone = new QxContext();
      ctxClone.setProperties(getProperties());
      return ctxClone;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static final char dot ='.';
  public static final String DESCRIPTION="description";
  public static final String USERNAME="username";
  public static final String PASSWORD="password";
  public static final String RECIPIENTS="recipients";

  public static final String DEFAULT_USERNAME="default-username";
  public static final String DEFAULT_PASSWORD="default-password";
  public static final String DOMAIN_NAME="domain-name";
  public static final String SERVER_NAME="server-name";
  public static final String REPOSITORY_TYPE="repository-type";
  public static final String REPOSITORY_NAME="repository-name";
  public static final String SERVICE="service";
  public static final String METHOD="method";
  public static final String OP_TYPE="op-type";
  public static final String ACQUIRE_LICENSE="acquire-license";

  public static final String SOAP_PROTOCOL = "soap-protocol";
  public static final String SOAP_HOSTNAME = "soap-hostname";
  public static final String SOAP_PORT = "soap-port";
  public static final String SOAP_SERVICES_PATH = "soap-services-path";
  public static final String SOAP_SERVICE_NAME = "soap-service-name";
  public static final String SOAP_SERVICE_OPERATION = "soap-service-operation";
  public static final String SOAP_SERVICE_NAMESPACE = "soap-service-namespace";
  public static final String SOAP_SERVICE_NAMESPACE_PREFIX = "soap-service-namespace-prefix";

  public static final String PRIORITY = "priority";
  public static final String ACTION = "action";
  public static final String SOURCE = "source";
  public static final String TARGET = "target";

  public static final String JAVA_SERVICES_PACKAGE = "java-services-package";
  public static final String JAVA_SERVICE_CLASSNAME = "java-service-classname";

  public static final String INTENT = "intent";
  public static final String TARGET_REPOSITORY = "target-repository-name";

  public static final String QUEUE_NAME = "queue-name";

  public static final String FILE_NAME = "file-name";
  public static final String FILE_DESCRIPTION = "file-description";
  public static final String FOLDER_DESCRIPTION = "folder-description";
  public static final String ATTACHMENT_FILE_DESCRIPTION = "attachment-file-description";
  public static final String ATTACHMENT_FOLDER_DESCRIPTION = "attachment-folder-description";
  public static final String CHANGE_ANALYST= "change-analyst";
  public static final String AUTO_RELASE = "auto-release";
  public static final String ORIGINATING_USER= "originator";
  public static final String ORIGINATING_EVENT= "originating-event";
  public static final String ORIGIN= "origin";
  public static final String POLICY_NAME= "policy-name";
  public static final String PUBLISH_PENDING = "publish-pending";
  public static final String PUBLISH_ECPN = "publish-ECPN";

  public String getProperties() {
   // if (null!=secret) return secret.encrypt(toString());
    return toString();
  }

  public void setProperties(String props) {
    AttributesHandler attHandler = new AttributesHandler();
    try {
      XMLReader xr = XMLReaderFactory.createXMLReader();
      xr.setContentHandler (attHandler);
      StringReader reader = new StringReader(props);
      InputSource source = new InputSource(reader);
      xr.parse(source);
      this.properties = attHandler.getProperties();
    } catch(Exception ex){
      {} //System.out.println("Exception message: "+ex.getMessage() );
    }
  }

  public static final String NAME="name";

  public static final String SYSTEM_USERNAME="system-username";
  public static final String SYSTEM_PASSWORD="system-password";
  public static final String ENCRYPTED_SYSTEM_PASSWORD="encrypted-system-password";
  public static final String PROTOCOL="protocol";
  public static final String HOST_NAME="host-name";
  public static final String PORT="port";
  public static final String SERVICES_PATH="services-path";
  public static final String DOWNLOAD_SERVICES_PATH="download-services-path";
  public static final String ILINK_SERVER_DOMAIN="ilink-server-domain";
  public static final String SERVICE_NAME="service-name";
  public static final String METHOD_NAME="method-name";

  public static final String REMOTE_METHOD="remote-method";
  public static final String INSTANCE_NAME="instance-name";

  public static final String PROCESS_ID = "process-id";
  public static final String PROCESS_NAME_SPACE = "process-name-space";
  public static final String PROCESS_NAME = "process-name";
  public static final String PROCESS_DESCRIPTION = "process-description";

  public static final String SUMMARY = "summary";

  //public static final String SERVER_MARKER = "server-marker";
  //public static final String TC_SERVER_NAME = "tC-server-name";

  public Map properties = new HashMap();
  private QxContext parentContext = null;

}

class AttributesHandler extends DefaultHandler {
  // This method is called when an element is encountered
  public void startElement(String namespaceURI, String localName,
                           String qName, Attributes atts)  {
      // Get the number of attribute
      int length = atts.getLength();

      // Process each attribute
      for (int i=0; i<length; i++) {
          // Get names and values for each attribute
          String name = atts.getQName(i);
          String value = atts.getValue(i);
          mapProperties.put(name, value);
      }
  }

  private transient HashMap mapProperties = new HashMap();

  public HashMap getProperties(){
    return this.mapProperties;
  }


  public void setProperties(HashMap m){
    this.mapProperties = m;
  }
}
