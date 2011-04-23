package com.agile.sdo.cif;

import zws.application.Names;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.exception.FailedToAuthenticate;
import zws.qx.QxContext;
import zws.repository.agile.plm.api.AgilePLMAPIConstants;
import zws.repository.agile.plm.api.AgilePLMAPIRepositoryBase;
import zws.security.Authentication;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import com.agile.plmapi.api.PlmAttribute;
import com.agile.plmapi.api.PlmData;
import com.agile.plmapi.api.PlmException;
import com.agile.plmapi.api.PlmFactory;
import com.agile.plmapi.api.PlmObject;
import com.agile.plmapi.api.PlmRelation;
import com.agile.plmapi.api.PlmRequest;
import com.agile.plmapi.api.PlmResponse;
import com.agile.plmapi.api.PlmSession;
import com.agile.plmapi.api.impl.PlmRequestHelper;
import com.agile.plmapi.api.impl.PlmResponseHelper;
import com.agile.share.xml.XmlDocument;
import com.agile.share.xml.XmlElement;
import com.agile.share.xml.XmlNode;

/**
 * The Class CifClientUtils.
 */
public class AgileWSXSvcBase extends AgilePLMAPIConstants {
  public AgileWSXSvcBase() {  }
  public AgileWSXSvcBase(String strURL, Authentication authID, AgilePLMAPIRepositoryBase rep, QxContext context) {
    url = strURL;
    id = authID;
    repository = rep;
    ctx = context;
  }
  public void loadCIFsvc(String strURL, Authentication authID, AgilePLMAPIRepositoryBase rep, QxContext context) {
    url = strURL;
    id = authID;
    repository = rep;
    ctx = context;
  }
  public PlmSession getSession() throws Exception {
    return login(url, id);
  }

  public PlmSession login(final String wsurl, Authentication id ) throws Exception {
    String[] s = split(wsurl, "/");
    String ahost = s[1];
    String aurl = "http://" + s[1] + "/" + s[2];

    Map map = new HashMap();
    map.put(PlmSession.KEY_SYSTEM, "agile.plmapi.sdk");
    map.put(PlmSession.KEY_USER, id.getUsername());
    map.put(PlmSession.KEY_PASSWORD, id.getPassword());
    map.put(PlmSession.KEY_RESOURCE, "19990");
    map.put(PlmSession.KEY_APPLICATION, aurl);
    map.put(PlmSession.KEY_HOST, ahost);
    map.put(PlmSession.KEY_URL, wsurl);
    map.put(PlmSession.KEY_OPTIONS, "");
    map.put(PlmSession.KEY_PROTOCOL, PlmSession.PROTOCOL_WEBSERVICE);
    PlmSession session = null;
    try {
      {} //System.out.println("opening session to " + wsurl); // debug
      session = PlmFactory.getSession();
      session.setOpenTimeout(10*1000);
      session.open(map);
      {} //System.out.println("session opened!"); // debug
      session.setExecuteTimeout(99999999);
      return session;
    } catch (PlmException e) {
      {} //System.out.println("could not open Session. Retrying!");
    }
    try {
      {} //System.out.println("Retrying session to " + wsurl); // debug
      session = PlmFactory.getSession();
      session.setOpenTimeout(15*1000);
      session.open(map);
      {} //System.out.println("session opened!"); // debug
      session.setExecuteTimeout(99999999);
      return session;
    } catch (PlmException e) {
      {} //System.out.println("Could not open Session. Retrying Again!");
    }
    try {
      {} //System.out.println("Retrying session again to " + wsurl); // debug
      session = PlmFactory.getSession();
      session.setOpenTimeout(30*1000);
      session.open(map);
      {} //System.out.println("session opened!"); // debug
      session.setExecuteTimeout(99999999);
      return session;
    } catch (PlmException e) {
      {} //System.out.println("Could not open Session. Giving up after 3 tries!");
      throw new FailedToAuthenticate(id.getUsername()+" could not login to Agile after 3 attempts: " + e.getMessage());
    }
  }

  protected PlmRequest createRequest(String username) throws Exception {
    String createdByUser          = id.getUsername();
    String createdDate            = new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(new java.util.Date());
    String createdByTool          = ctx.get(CREATED_BY_TOOL);
    String createdForProject      = ctx.get(CREATED_FOR_PROJECT);
    String createdByToolVersion   = ctx.get(CREATED_BY_TOOL_VERSION);

    if(null == createdByTool || null == createdForProject || null == createdByToolVersion)
      throw new Exception("WSX parameters are not set");

    PlmRequest request = PlmFactory.createRequest();
    setHeader(request, createdByUser, createdDate, createdByTool, createdByToolVersion, createdForProject);
    return request;
  }

  protected PlmObject createPLMObject(String name, String key,String source,
                                   String[] options, String[] attributes,
                                   boolean mappedflag, boolean dirtyflag) throws Exception {
    PlmObject obj = PlmFactory.createObject(name);
    obj.setDirty(dirtyflag);
    obj.setMapped(mappedflag);
    obj.setSource(source);
    obj.setKey(key);
    if (attributes != null) {
      for (int i = 0; i < attributes.length - 1; i = i + 2) {
        PlmAttribute attr = obj.setAttributeValue(attributes[i], attributes[i + 1]);
        attr.setSource(source);
        attr.setDirty(dirtyflag);
        attr.setMapped(mappedflag);
      }
    }
    if (options != null) {
      for (int i = 0; i < options.length - 1; i = i + 2) {
        obj.setOptionValue(options[i], options[i + 1]);
      }
    }
    return obj;
  }

  public void setHeader(PlmRequest request, String createdByUser, String createdDate,
                                            String createdByTool, String createdByToolVersion,
                                            String createdForProject) {
    request.getHeader().setParameter(CREATED_BY_USER, createdByUser);
    request.getHeader().setParameter(CREATED_DATE, createdDate);
    request.getHeader().setParameter(CREATED_BY_TOOL, createdByTool);
    request.getHeader().setParameter(CREATED_BY_TOOL_VERSION, createdByToolVersion);
    request.getHeader().setParameter(CREATED_FOR_PROJECT, createdForProject);
    request.getHeader().setParameter(DEBUG_FILTER, "note");
    request.getHeader().setParameter(LOGING_LEVELS, "note,warn");
  }

  public String[] getStringArray(Map attrMap) {
    String[] strArray = null;
    String key = null;
    String value = null;
    int idx = 0;
    if (null != attrMap) {
      strArray = new String[(attrMap.size() * 2)];
      Iterator itr = attrMap.keySet().iterator();
      while (itr.hasNext()) {
        key = (String) itr.next();
        value = (String) attrMap.get(key);
        strArray[idx++] = key;
        strArray[idx++] = value;
      }
    }
    return strArray;
  }

  public void printStringArray(String[] strArray) {
    {} //System.out.println("strArray " + strArray.length);
    int size = strArray.length;
    for (int idx = 0; idx < size; idx++) {
      {} //System.out.println(strArray[idx]);
    }
  }

  public PlmObject getPlmObject(PlmData data, String objectName) throws PlmException {
    PlmObject obj = null;
    PlmObject tempObj = null;
    if (null != objectName && null != data) {
      Collection objects = data.getObjects();
      Iterator iter = objects.iterator();
      while (iter.hasNext()) {
        tempObj = (PlmObject) iter.next();
        if(new Boolean(tempObj.getOptionValue("internal.found")).booleanValue()) {
          obj = tempObj;
          break;
        }
      }
    }
    return obj;
  }

  public static Collection getObjectsByName( PlmData data, String objectName ) throws PlmException{
    Collection collection = new Vector();
    if ( objectName != null ){
        Collection objects = data.getObjects();
        Iterator iter = objects.iterator();
        while ( iter.hasNext() ){
            PlmObject obj = (PlmObject)iter.next();
            {} //System.out.println("Obj.toString "+obj.toString());
            {} //System.out.println(obj.getAttributeValue("Number"));
            getObjectsByName( obj, objectName, collection );
        }
    }

    return collection;
}

private static void getObjectsByName( PlmObject object, String objectName, Collection collection ) {
  String name = object.getEntity().getName();
  Map attribs = object.getAttributes();
  Iterator itr = attribs.keySet().iterator();
  while(itr.hasNext()) {
    String key = (String)itr.next();
    {} //System.out.println("key "+ key);
    {} //System.out.println("value "+ attribs.get(key));
  }
  if ( objectName.equals( name ) ){
    collection.add( object );
  }
}

  public PlmObject getPlmObjectByType(PlmResponse plmResponse, String objectType) throws Exception {
    XmlDocument rpnsDoc1 = new XmlDocument(PlmResponseHelper.marshal(plmResponse));
    List l = rpnsDoc1.getNodes();
    if(null == l) return null;
    PlmObject obj = null;
    PlmObject tempObj = null;
    Iterator itr = l.iterator();
    System.out.println("XmlDocument " + rpnsDoc1);
    PlmData data = plmResponse.getData();
    System.out.println("data " + data);
    Collection c = getObjectsByName(data,"Eng Library Publish");
    System.out.println("object by name" + c);
    Iterator objItr = c.iterator();
    while(objItr.hasNext()) {
      obj = (PlmObject)objItr.next();
      System.out.println("obj "+ obj.toString());
    }
    System.out.println(" response status "+plmResponse.getStatus());
    while(itr.hasNext()) {
      XmlNode node = (XmlNode) itr.next();
      //printNodes(node);
    }
    //System.out.println(" " + xml.getElementText("status"));
    System.out.println(PlmResponseHelper.marshal(plmResponse).toString());

    /*
     *  XmlElement xmlElement =  rpnsDoc1.getElement("MEP_10-9918-81_P");
    System.out.println("count " + xmlElement);
    System.out.println("Number " + xmlElement.getAttributeValue("Number"));
    System.out.println("count " + rpnsDoc1.getElementText("MEP_10-9918-81_P"));
    System.out.println(PlmResponseHelper.marshal(plmResponse).toString());
    System.out.println("Number " + xmlElement.getAttributeValue("Number"));
     */

    /*if (null != objectType && null != data) {
      Collection objects = data.getObjects();
      Iterator iter = objects.iterator();
      while (iter.hasNext()) {
        tempObj = (PlmObject) iter.next();
        if(objectType.equals(tempObj.getOptionValue("type"))) {
          obj = tempObj;
          break;
        }
      }
    }*/
    return obj;
  }

  public Metadata getObjectByAttribute(PlmData data, String attributeName, String attributeValue) throws Exception {
    Metadata metadata = null;
    PlmObject obj = null;
    if (attributeName != null && attributeValue != null) {
      Collection objects = data.getObjects();
      Iterator iter = objects.iterator();
      while (iter.hasNext() && obj == null) {
        obj = (PlmObject) iter.next();
        obj = getObjectByAttribute(obj, attributeName, attributeValue);
      }
    }
    metadata = prepareMetadata(obj);
    return metadata;
  }

  private PlmObject getObjectByAttribute(PlmObject object, String attributeName, String attributeValue) throws Exception {
    String value = object.getAttributeValue(attributeName);
    if (attributeValue.equals(value)) { return object; }
    Collection relations = object.getRelations();
    Iterator iter = relations.iterator();
    PlmObject obj = null;
    while (iter.hasNext() && obj == null) {
      PlmRelation reln = (PlmRelation) iter.next();
      obj = reln.getChild();
      obj = getObjectByAttribute(obj, attributeName, attributeValue);
    }
    return obj;
  }

  public Metadata prepareMetadata(PlmObject plmObject) {
    if(null == plmObject) return null;
    Map attrMap = plmObject.getAttributes();
    Metadata data = new MetadataBase();
    Iterator itr = attrMap.keySet().iterator();
    while (itr.hasNext()) {
      String key = (String) itr.next();
      PlmAttribute plmAttribute = (PlmAttribute) attrMap.get(key);
      if (null != plmAttribute.getValue() && plmAttribute.getValue().length() > 0) {
        data.set(plmAttribute.getName(), plmAttribute.getValue());
      }
    }
    data.set(Names.METADATA_NAME, data.get("Number"));
    return data;
  }

  /*public PlmObject preparePlmObject(Metadata metadata) throws Exception{
    if(null == metadata) return null;
    Map attrMap = metadata.getAttributes();
    PlmObject plmObject = PlmFactory.createObject(metadata.getName());
    plmObject.setDirty(true);
    plmObject.setMapped(false);
    plmObject.setSource("zws");
    plmObject.setKey(metadata.getName());
    Iterator itr = attrMap.keySet().iterator();
    PlmAttribute plmAttribute = null;
    while (itr.hasNext()) {
      String key = (String) itr.next();
      plmAttribute = plmObject.setAttributeValue(key, metadata.get(key));
      plmAttribute.setSource("zws");
      plmAttribute.setDirty(true);
      plmAttribute.setMapped(false);
    }
    return plmObject;
  }*/

  private String[] split(String str, String delim) {
    if (str == null || delim == null) { return null; }
    StringTokenizer t = new StringTokenizer(str, delim);
    String[] values = new String[t.countTokens()];
    for (int i = 0; t.hasMoreTokens(); i++) {
      values[i] = t.nextToken();
    }
    return values;
  }

  public void dump(String action, PlmRequest r) {
    XmlDocument rqd = new XmlDocument(PlmRequestHelper.marshal(r));
    {} //System.out.println("request for : " + action);
    {} //System.out.println(rqd.toFormattedString());
  }

  public String getString(PlmRequest r) throws Exception {
    XmlDocument rqd = new XmlDocument(PlmRequestHelper.marshal(r));
    return rqd.toFormattedString();
  }
  public String getString(PlmResponse r) throws Exception {
    XmlDocument rqd = new XmlDocument(PlmResponseHelper.marshal(r));
    return rqd.toFormattedString();
  }

  // for async calls
  protected PlmResponse getResponse() {return plmReponse;}
  protected void setResponse(PlmResponse response) {plmReponse = response;}
  protected void setException(PlmException e) {mException = e;}
  protected boolean hasException() {
    /*synchronized (mException) {
       return null != mException;
    }*/
    return null != mException;
 }

  protected PlmException getException() {return mException;}
  protected static long getDefaultWait() {return DEFAULT_WAIT; }
  protected String url = null;
  protected Authentication id = null;
  protected AgilePLMAPIRepositoryBase repository = null;
  protected QxContext  ctx = null;
  private PlmResponse plmReponse = null;
  private PlmException mException = null;
  private static final long DEFAULT_WAIT = 60000;
  protected static final String WARNING = "WARNING";
  protected static final String ERROR = "ERROR";
  protected static final String FATAL = "FATAL";
  protected static final String REQUEST = "REQUEST";
  protected static final String RESPONSE = "RESPONSE";
  protected static final String MAPPING = "mapping";
  protected static final String CHECKSTATUS = "checkstatus";
  protected static final String RESERVE = "reserve";
  protected static final String VALIDATE  = "validate";
  protected static final String UPLOAD_FILES = "upload-files";
  protected static final String UPDATE = "update";
  protected static final String RELEASE = "release";

}
