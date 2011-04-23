package zws.repository.agile.plm.test;


import zws.application.Names;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.MetadataSubComponentBase;
import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.repository.agile.plm.api.AgilePLMAPIConstants;
import zws.repository.agile.plm.api.AgilePLMAPIRepositoryBase;
import zws.security.Authentication;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.agile.plmapi.api.PlmFactory;
import com.agile.plmapi.api.PlmObject;

import com.agile.plmapi.api.PlmAttribute;
import com.agile.plmapi.api.PlmAttributeCriteria;
import com.agile.plmapi.api.PlmData;
import com.agile.plmapi.api.PlmException;
import com.agile.plmapi.api.PlmExpressionCriteria;
import com.agile.plmapi.api.PlmQuery;
import com.agile.plmapi.api.PlmRelation;
import com.agile.plmapi.api.PlmRequest;
import com.agile.plmapi.api.PlmResponse;
import com.agile.plmapi.api.PlmSession;
import com.agile.plmapi.api.PlmStatus;
import com.agile.plmapi.api.impl.PlmResponseHelper;
import com.agile.plmapi.api.om.PlmActionConstants;
import com.agile.sdo.cif.AgileWSXItemSvc;
import com.agile.share.xml.XmlDocument;
import com.agile.share.xml.XmlNode;

/**
 *
 */
/*
 DesignState - Design Compression Technology
 @author: ptoleti
 @version: 1.0
 Created on Jul 18, 2007 12:37:50 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

/**
 * @author ptoleti
 *
 */
public class RunPLMAPI5 {

  public static void main(String[] args) {
    String url="http://pwebdev.cisco.com/pls/services";
    AgileWSXItemSvc itemSvc = new AgileWSXItemSvc();


    try {
      HashMap structure = new HashMap();
      HashMap relMap = new HashMap();
      HashMap itemList = new HashMap();
      itemList.put("MEP_DEMUX24_SLIM_A","MEP_DEMUX24_SLIM_A");
      itemList.put("MEP_DEMUX24_SLIM_BOTTOM_P","MEP_DEMUX24_SLIM_BOTTOM_P");
      itemList.put("MEP_DEMUX24_SLIM_TOP_P","MEP_DEMUX24_SLIM_TOP_P");
      String itemNumber = null;
      itemNumber = "ENG_DEMUX24_SLIM";
      itemNumber = "MEP_DEMUX24_SLIM_A";
      //itemNumber = "ENG_DEMUX24_SLIM_BOTTOM";
      //itemNumber = "MEP_DEMUX24_SLIM_BOTTOM_P";
     Authentication id= new Authentication("user31", "user31");
     PlmSession session = null;
     PlmResponse response = null;
     session = itemSvc.login(url, id);
      session.close();
      session = itemSvc.login(url, id);
      {} //System.out.println(session.getId());
      {} //System.out.println(session.isValid()+"");
      //part_getcompletestructure(session);
      //response = searchDocument(session);
      response = getObjectDetail(itemNumber, session);
      Metadata data = processResponse(itemNumber, structure,response);
      {} //System.out.println("data " + data);
      //testResponse();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }



  public static PlmResponse searchDocument(PlmSession session) throws Exception {
    PlmResponse response = null;
       PlmQuery documentQuery = PlmFactory.createQuery("Mechanical Library Part");
       PlmAttributeCriteria attributeCriteriaPart =
         PlmFactory.createQueryAttributeCriteria("Mechanical Library Part", "Number",
                                                 PlmAttributeCriteria.OP_STARTS_WITH, "MEP_2");
       PlmExpressionCriteria criteriaPart = PlmFactory.createQueryExpression("Mechanical Library Part", attributeCriteriaPart);
       documentQuery.setExpression(criteriaPart);
       PlmRequest request = PlmFactory.createRequest();
       request.addQuery(documentQuery);
       response = session.execute("search", request);
       XmlDocument xml = PlmResponseHelper.marshal(response);
       {} //System.out.println("result " + xml.toString());
       return response;
 }


    public static PlmResponse getObjectDetail(String itemNumber, PlmSession session) throws Exception {
      {} //System.out.println("getObjectDetail " );
      PlmObject object =null;
      //object = PlmFactory.createObject("part");
      object = PlmFactory.createObject("Mechanical Library Part");
      object.setAttributeValue("Number", itemNumber);
      object.setAttributeValue("Rev", "12");
      PlmRequest request = PlmFactory.createRequest();
      request.getData().addObject(object);
      PlmResponse response = session.execute("get-object-detail", request);
      {} //System.out.println("response " + response);
      XmlDocument xml = PlmResponseHelper.marshal(response);
      {} //System.out.println("result " + xml.toString());
      return response;
 }

    public static PlmObject part_getcompletestructure(PlmSession session) {
      PlmObject plmPart;
      try {
      plmPart = PlmFactory.createObject("part");
//       Use the number value of a part that already exists on the PLM
//       server
      plmPart.setAttributeValue("number", "MEP_25-0010-02_P");
      PlmRequest request = PlmFactory.createRequest();
      request.getHeader().setParameter("load-option", "2");
      PlmData data = request.getData();
      data.addObject(plmPart);
      PlmResponse response = session.execute(
      PlmActionConstants.GET_COMPLETE_STRUCTURE, request);
      PlmStatus status = response.getStatus();
      if (status != null && response.getStatus().hasErrors())
      {} //System.out.println("part.getcompletestructure failed. Error code = ");
      data = response.getData();
//       The returned PlmObject with complete structure
      plmPart = (PlmObject) data.getObjects().iterator().next();
      return plmPart;
      } catch (PlmException e) {
      e.printStackTrace();
      }
      return null;
      }


private static Metadata processResponse(String ItemNumber, HashMap structure, PlmResponse response) throws Exception{
    {} //System.out.println("status "  + response.getStatus());
    {} //System.out.println("response"  + PlmResponseHelper.marshal(response).toString());
    Metadata metadata = null;
    PlmData data = response.getData();
    Iterator dataItr = data.getObjects().iterator();
    PlmObject obj = (PlmObject)dataItr.next();
    getObjects(null, obj,structure);
    //getObjects(obj);

    {} //System.out.println("structure " + structure.get(ItemNumber));
    metadata = (Metadata) structure.get(ItemNumber);
    return metadata;
}

private static void getObjects(PlmObject object) {
  Metadata dataObj = null;
  Metadata parentMetadata = null;
  Metadata childMetadata = null;
  PlmRelation plmRelation = null;
  PlmObject parentObj = null;
  PlmObject childObj = null;
  MetadataSubComponentBase sub = null;
  HashMap objectMap = new HashMap();
  HashMap relMap = new HashMap();
  dataObj = prepareMetadata(object);
  if(!objectMap.containsKey(dataObj.getName())) {
    objectMap.put(dataObj.getName(),dataObj);
  }
  Collection rels = object.getRelations();
  {} //System.out.println("rels " + rels);
  if(null == rels) return;
  Iterator relItr = rels.iterator();
  while(relItr.hasNext()) {
    plmRelation = (PlmRelation) relItr.next();
    parentObj = plmRelation.getParent();
    childObj = plmRelation.getChild();
    parentMetadata = prepareMetadata(parentObj);
    childMetadata = prepareMetadata(childObj);
    {} //System.out.println("plmRelation " + plmRelation.toString());
    {} //System.out.println("parentMetadata " + parentMetadata.getName());
    {} //System.out.println("childMetadata " + childMetadata.getName());

  }
}


private static void getObjects(String parent, PlmObject object, HashMap objectMap) {
  Metadata dataObj = null;
  Metadata parentMetadata = null;
  Metadata childMetadata = null;
  PlmRelation plmRelation = null;
  PlmObject parentObj = null;
  PlmObject childObj = null;
  MetadataSubComponentBase sub = null;
  dataObj = prepareMetadata(object);
  if(!objectMap.containsKey(dataObj.getName())) {
    objectMap.put(dataObj.getName(),dataObj);
  }
  Collection rels = object.getRelations();

  Iterator relItr = rels.iterator();
  while(relItr.hasNext()) {
    plmRelation = (PlmRelation) relItr.next();
    parentObj = plmRelation.getParent();
    parentMetadata = prepareMetadata(parentObj);

    if(objectMap.containsKey(parentMetadata.getName())) {
      parentMetadata = (Metadata) objectMap.get(parentMetadata.getName());
    }

    childObj = plmRelation.getChild();
    if(null == childObj) continue;
    childMetadata = prepareMetadata(childObj);
    if(null == childMetadata.getName()) continue;
    if(childMetadata.getName().equalsIgnoreCase(parent)) return;
    if(objectMap.containsKey(childMetadata.getName())) {
      childMetadata = (Metadata)objectMap.get(childMetadata.getName());
    }
    if(childMetadata.getName().endsWith("_P") ||
       childMetadata.getName().endsWith("_A")) {
      parentMetadata.addSubComponent(new MetadataSubComponentBase(childMetadata));
    }
    objectMap.put(parentMetadata.getName(),parentMetadata);
    getObjects(parentMetadata.getName(), childObj, objectMap);
  }
}



private static void printAttributes(PlmObject obj) throws Exception{
  Map attrs = obj.getAttributes();
  Iterator itr = attrs.keySet().iterator();
  while(itr.hasNext()) {
   String key = (String) itr.next();
   String value = obj.getAttributeValue(key);
     if(null != value && value.length()>0) {}
   {} //System.out.println(key + " "  + obj.getAttributeValue(key));
    }
}


private static Metadata prepareMetadata(PlmObject plmObject) {
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



















  public static void printNodes(XmlNode node) throws Exception{
    {} //System.out.println("name " + node.getName());
    {} //System.out.println("path " + node.getPath());
    if(node.hasNodes()) {
      List l = node.getNodes();
      Iterator itr = l.iterator();
      while(itr.hasNext()) {
        XmlNode childNode = (XmlNode) itr.next();
        printNodes(childNode);
      }
    }
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



  private static AgilePLMAPIRepositoryBase getRepository() throws Exception{
    AgilePLMAPIRepositoryBase r = new AgilePLMAPIRepositoryBase();
    /*
        <agile-wsx name="agile-wsx" system-password="agile" system-username="zws"
    domain-name="cisco" server-name="node-zero" protocol="http"
    host-name="linuxwas.agilesoft.com" port="19990" services-path="Agile/services"/>
     */
    r.setDomainName("cisco");
    r.setHostName("pwebdev.cisco.com");
    r.setPort("80");
    r.setProtocol("http");
    r.setServerName("node-zero");
    r.setServicesPath("pls/services");
    r.setSystemPassword("user31");
    r.setSystemUsername("user31");
    return r;
  }
  private static QxContext materializeQxContext() {
    QxWebClient webClient = QxWebClient.materializeClient();
    QxContext ctx = new QxContext();
    ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.queue");
    ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "QxQueueService");
    ctx.set(QxContext.SOAP_HOSTNAME, "designstate-0");
    ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
    ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
    ctx.set(AgilePLMAPIConstants.CREATED_BY_TOOL,"MCAD Library");
    ctx.set(AgilePLMAPIConstants.CREATED_FOR_PROJECT,"project PLS 1.17");
    ctx.set(AgilePLMAPIConstants.CREATED_BY_TOOL_VERSION, "PLS 1.17");
    //ctx.set(QxContext.SOAP_SERVICE_OPERATION, "runQx");
    return ctx;
  }
  private static Metadata createMetadata(String name) {
    Metadata root = new MetadataBase();
    File f = new File("C:/test.txt");
    root.set("name", name);
    root.set("lib_x", "888.88");
    root.set("number", name);
    root.set("description", "Code Drop 3");
    root.set("attachment", f.getAbsolutePath());
    root.set("author", "Matt Mohr");
    root.set("version", "B.8");
    root.set("dirtyFlag", "true");
    return root;
  }

}