/* DesignState - Design Compression Technology
 * @author: ptoleti
 * @version: 1.0
 * Created on Oct 31, 2007 11:20:37 AM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.qx.service;

import zws.qx.Qx;
import zws.util.Named;
import zws.util.Prototype;
import zws.qx.QxWebClient;
import zws.qx.xml.QxXML;
import zws.exception.CanNotMaterialize;
import zws.exception.InvalidName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ServiceRecord implements Named, Serializable, Prototype {
  
  public String getName() {return getServiceName();}

  public void setServiceName(String sName) {set(SERVICE_NAME, sName);}
  public void setDescription(String desc) {set(DESCRIPTION, desc);}
  public void setProtocol(String protocol) throws Exception{  validateProtocol(protocol); set(PROTOCOL, protocol); }

  public void setHostName(String hostName) {set(HOSTNAME, hostName);}
  public void setPort(String port) {set(PORT, port);}
  public void setSoapServicePath(String servicePath) {set(SOAP_SERVICE_PATH, servicePath);}
  public void setSoapServiceMethod(String s) {    set(SOAP_SERVICE_METHOD,s);}
  public void setSoapServiceName(String s) {set(SOAP_SERVICE_NAME,s);}
  
  public String getServiceName() {return get(SERVICE_NAME);}
  public String getDescription() {return get(DESCRIPTION);}
  public String getProtocol() {return get(PROTOCOL);}
  public String getHostName() {return get(HOSTNAME);}
  public String getPort() {return get(PORT);}
  public String getSoapServiceName() {return get(SOAP_SERVICE_NAME);}
  public String getSoapServicePath() {return get(SOAP_SERVICE_PATH);}
  public String getSoapServiceMethod() { return get(SOAP_SERVICE_METHOD);}

  
  public Qx materializeServiceClient() throws CanNotMaterialize {
    String protocol = get(PROTOCOL);
    Qx client = null;
    if(QX_AXIS_2_WEB_SERVICE.equalsIgnoreCase(protocol)) {
      client = QxWebClient.materializeClient();
//    } else if("qx-local-instruction-file".equalsIgnoreCase(protocol)){
//      client = QxInstructionFileClient.materializeClient();
    } else if(QX_AXIS_1_WEB_SERVICE.equalsIgnoreCase(protocol)){
      client = null;
    }

    if (null==client) throw new CanNotMaterialize(protocol, "ServiceClient");
    return client;
  }
  
  
  public void set(String property, String value) { attribMap.put(property, value); }
  public String get(String property) { return (String)attribMap.get(property); }
  
  private void validateProtocol(String protocol) throws Exception{
    if(!(
        QX_AXIS_2_WEB_SERVICE.equalsIgnoreCase(protocol) || 
        QX_AXIS_1_WEB_SERVICE.equalsIgnoreCase(protocol) ||
        HTTP_SERVLET.equalsIgnoreCase(protocol))){
      throw new InvalidName(protocol);
    }
  }
  
  private void setProperties(Map attributes) { attribMap = attributes; }

  public Object copy() {
    ServiceRecord r = null;
    try {
      {} //System.out.println("original " + attribMap);
      r = (ServiceRecord) clone();
      r.setProperties(attribMap);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return r;
  }
  public Object deepCopy() {
    return null;
  }
  public void inactivate() {}
  public Object shallowCopy() {
    return null;
  }
  public boolean supportsDeepCopy() {
    return false;
  }
  
  public String toString() { return toXML ().toString(); }
  public QxXML toXML () {
   StringBuffer xmlString = new StringBuffer();
    xmlString.append(START_TAG).append("service");
    Iterator itr = attribMap.keySet().iterator();
    String key = null;
    String value = null;
    while(itr.hasNext()){
      key = (String) itr.next();
      value = (String) attribMap.get(key);
      if(null!=value) {
        xmlString.append(prepareArg(key, value));
      }
    }
    prepareSpecificArgs(xmlString);
    xmlString.append(END_TAG);
    {} //System.out.println("-->>>toXML in ServiceRecord");
    {} //System.out.println(new QxXML(xmlString.toString()));
    return new QxXML(xmlString.toString());
  }

  protected void prepareSpecificArgs(StringBuffer xmlString) {}

  public String prepareArg(String key, String value) {
    // prepare string like  key="value"
    StringBuffer argBuffer = new StringBuffer();
    argBuffer.append(SPACE).append(key).append(EQUALTO).append(QUOTE).append(value).append(QUOTE);
    return argBuffer.toString();
  }
  
  
  private Map attribMap = new HashMap();
  public static String QUOTE = "\"";
  public static String EQUALTO = "=";
  public static String START_TAG = "<";
  public static String END_TAG = "/>";
  public static String CLOSE_TAG = ">";
  public static String SLASH = "/";
  public static String SPACE = " ";

  public static String SERVICE_NAME = "service-name";
  public static String DESCRIPTION = "description";
  public static String PROTOCOL = "protocol";
  public static String HOSTNAME= "host-name";
  public static String PORT = "port";
  public static String SOAP_SERVICE_NAME= "soap-service-name";
  public static String SOAP_SERVICE_PATH= "soap-service-path";
  public static String SOAP_SERVICE_METHOD= "soap-service-method";
  
  //Valid Protocols
  public static String HTTP_SERVLET = "http-servlet";
  public static String QX_AXIS_2_WEB_SERVICE = "QxAxis2WebService";
  public static String QX_AXIS_1_WEB_SERVICE = "QxAxis1WebService";
    
 }
