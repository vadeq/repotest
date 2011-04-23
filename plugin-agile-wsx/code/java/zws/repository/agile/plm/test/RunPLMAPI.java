package zws.repository.agile.plm.test;


import zws.application.Names;
import zws.application.Properties;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.data.MetadataSubComponentBase;
import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.recorder.util.RecorderUtil;
import zws.repository.Repository;
import zws.repository.agile.plm.api.AgilePLMAPIConstants;
import zws.repository.agile.plm.api.AgilePLMAPIRepositoryBase;
import zws.security.Authentication;
import zws.service.repository.RepositoryClient;
import zws.service.repository.RepositoryService;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.agile.plmapi.api.PlmFactory;
import com.agile.plmapi.api.PlmObject;

import com.agile.plmapi.api.PlmAttributeCriteria;
import com.agile.plmapi.api.PlmData;
import com.agile.plmapi.api.PlmException;
import com.agile.plmapi.api.PlmExpressionCriteria;
import com.agile.plmapi.api.PlmQuery;
import com.agile.plmapi.api.PlmRequest;
import com.agile.plmapi.api.PlmResponse;
import com.agile.plmapi.api.PlmSession;
import com.agile.plmapi.api.impl.PlmResponseHelper;
import com.agile.sdo.cif.AgileWSXItemSvc;
import com.agile.sdo.cif.AgileWSXStructureSvc;
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
public class RunPLMAPI {

  public static void main(String[] args) {
    String url="http://pwebdev.cisco.com/pls/services";
    AgileWSXItemSvc itemSvc = new AgileWSXItemSvc();

    Metadata root = null;

    try {
      String key1="parent1.prt";
      String key2="child1.prt";
      File f = new File("C:/test.txt");
      Authentication id= new Authentication("user81", "user81");
      root = createMetadata(key1);
      createRelation();
      /*PlmSession session = itemSvc.login(url, id);
      session.close();
      session = itemSvc.login(url, id);
      session = itemSvc.login(url, id);
      {} //System.out.println(session.getId());
      {} //System.out.println(session.isValid()+"");
      searchDocument(session);*/
      //getObjectDetail(session);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static PlmResponse searchDocument(PlmSession session) {
    PlmResponse response = null;
    try {
       PlmQuery documentQuery = PlmFactory.createQuery("Mechanical Library Part");
       PlmAttributeCriteria attributeCriteriaPart =
         PlmFactory.createQueryAttributeCriteria("Mechanical Library Part", "Number",
                                                 PlmAttributeCriteria.OP_STARTS_WITH, "MEP_25-");
       PlmExpressionCriteria criteriaPart = PlmFactory.createQueryExpression("Mechanical Library Part", attributeCriteriaPart);
       documentQuery.setExpression(criteriaPart);
       PlmRequest request = PlmFactory.createRequest();
       request.addQuery(documentQuery);
       {} //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );
       response = session.execute("search", request);
       {} //System.out.println("response " + response.getStatus().getFatals());
       {} //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );

       try {
         XmlDocument xml = PlmResponseHelper.marshal(response);
         {} //System.out.println("XmlDocument " + xml);
         PlmData data = response.getData();
         {} //System.out.println("data " + data);
         Collection c = getObjectsByName(data,"Mechanical Library Part");
         {} //System.out.println("object by name" + c);
         Iterator itr = c.iterator();
         while(itr.hasNext()) {
           PlmObject obj = (PlmObject)itr.next();
           {} //System.out.println("obj "+ obj.toString());
         }
         {} //System.out.println(" response status "+response.getStatus());

         /*XmlElement xmlElement =  xml.getElement("MEP_10-9918-81_P");
         {} //System.out.println("count " + xmlElement);
         {} //System.out.println("Number " + xmlElement.getAttributeValue("Number"));
         {} //System.out.println("count " + xml.getElementText("MEP_10-9918-81_P"));
         {} //System.out.println(PlmResponseHelper.marshal(response).toString());
         {} //System.out.println("Number " + xmlElement.getAttributeValue("Number"));*/
         /*List l = xml.getNodes();
         Iterator itr = l.iterator();
         while(itr.hasNext()) {
           XmlNode node = (XmlNode) itr.next();
           printNodes(node);
         }*/
         {} //System.out.println(" " + xml.getElementText("status"));
         {} //System.out.println(PlmResponseHelper.marshal(response).toString());
       }
       catch (Exception ex) {
          throw new RuntimeException(ex);
       }
    }
    catch (PlmException ex) {
       ex.printStackTrace();
    }
    return response;
 }


  public static PlmResponse getObjectDetail(PlmSession session) throws Exception {
    //PlmObject object = PlmFactory.createObject("document");
    PlmObject object = PlmFactory.createObject("Mechanical Library Part");
    object.setAttributeValue("Number", "MEP_25-0010-02_P");
    //object.setAttributeValue("Rev", "LATESTPENDING");
    PlmRequest request = PlmFactory.createRequest();
    request.getData().addObject(object);
    PlmResponse response = session.execute("get-object-detail", request);
    {} //System.out.println("response " + response);
    XmlDocument xml = PlmResponseHelper.marshal(response);
    {} //System.out.println("result " + xml.toString());
    return response;
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

  private static void createRelation() throws Exception{
    String url="http://pwebdev.cisco.com/pls/services";
    Authentication id= new Authentication("user81", "user81");
    File f = new File("C:/test.txt");
    AgileWSXStructureSvc structureSvc = new AgileWSXStructureSvc(url,id, (AgilePLMAPIRepositoryBase)getRepository(),materializeQxContext());
    Metadata parent = null;
    //String strParent = "MEP_TEST_PARENT2_A";
    //String strChild  = "MEP_TEST_CHILD1_P";
    MetadataSubComponentBase child1 = null;
    MetadataSubComponentBase child11 = null;
    MetadataSubComponentBase child2 = null;
    MetadataSubComponentBase child21 = null;
    MetadataSubComponentBase child22 = null;
    MetadataSubComponentBase child211 = null;
    String strParent = "PARENT82.asm";
    String strChild1  = "CHILD182.prt";
    String strChild11  = "CHILD1-12.prt";
    String strChild2  = "CHILD222.prt";
    String strChild21  = "CHILD2-12.prt";
    String strChild211  = "CHILD2-1-12.prt";
    String strChild22  = "CHILD2-22.prt";


    parent = createMetadata(strParent);
    child1 = new MetadataSubComponentBase(createMetadata(strChild1));
    child11 = new MetadataSubComponentBase(createMetadata(strChild11));
    child2 = new MetadataSubComponentBase(createMetadata(strChild2));
    child21 = new MetadataSubComponentBase(createMetadata(strChild21));
    child211 = new MetadataSubComponentBase(createMetadata(strChild211));
    child22 = new MetadataSubComponentBase(createMetadata(strChild22));

    parent.addSubComponent(child1);
    parent.addSubComponent(child2);

    child1.addSubComponent(child11);

    child2.addSubComponent(child21);
    child2.addSubComponent(child22);

    child21.addSubComponent(child211);

    {} //System.out.println("structure " + parent);
    //Metadata c = structureSvc.createStructure(parent, "zws", null, null, null, false);
    structureSvc.createStructure(parent, "zws", null, null, null, false,true, RecorderUtil.startNewProcess("Test", "test", "test class"));
    {} //System.out.println("Metadata  " + c);

    //RepositoryMetadataTarget target = getRepository().materializeMetadataTarget();
    //target.update(materializeQxContext(), child, f, id);

    //itemSvc.createObject(child, f.getAbsolutePath(), "zws", null, false, true);
    //createRelation("object-object-relation", o1, o2, key1, key2, "source", null, null,false,true);
  }

  private static Repository getRepository() throws Exception{
    Properties.set(Names.SERVICE_FINDER_HOSTNAME, "plm-rtp-002-d");
    Properties.set(Names.DOMAIN_NAME, "cisco");
    RepositoryService c = RepositoryClient.getClient();
    return c.findRepository("agile-wsx");
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
  // test check-in
}