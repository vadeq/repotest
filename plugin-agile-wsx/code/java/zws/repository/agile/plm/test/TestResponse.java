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
import java.io.FileInputStream;
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
public class TestResponse {

  public static void main(String[] args) {
    String url="http://pwebdev.cisco.com/pls/services";
    AgileWSXItemSvc itemSvc = new AgileWSXItemSvc();

    try {
      Authentication id= new Authentication("user31", "user31");
      FileInputStream fis = new FileInputStream("c:\\t.txt");
      int x= fis.available();
      byte b[]= new byte[x];
      fis.read(b);
      String content = new String(b);
      XmlDocument doc = new XmlDocument(content);
      PlmResponse response = PlmResponseHelper.unmarshal(doc);
      processResponse(response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


private static void processResponse(PlmResponse plmResponse) throws Exception{
  XmlDocument rpnsDoc1 = new XmlDocument(PlmResponseHelper.marshal(plmResponse));
  List l = rpnsDoc1.getNodes();
  if(null == l) return;
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
    printNodes(node);
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

}

private static void printAttributes(PlmObject obj) throws Exception{
  Map attrs = obj.getAttributes();
  Iterator itr = attrs.keySet().iterator();
  while(itr.hasNext()) {
   String key = (String) itr.next();
   String value = obj.getAttributeValue(key);
     if(null != value && value.length()>0) {}
       System.out.println(key + " "  + obj.getAttributeValue(key));
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
    System.out.println("name " + node.getName());
    System.out.println("path " + node.getPath());
    System.out.println("text " + node.getText());
    System.out.println("string " + node.toString());
    //PlmObject pp = new PlmChange(node.toString());



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
              System.out.println("Obj.toString "+obj.toString());
              System.out.println(obj.getAttributeValue("Number"));
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
      System.out.println("key "+ key);
      System.out.println("value "+ attribs.get(key));
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