package zws.repository.agile.plm.test;


import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.repository.agile.plm.api.AgilePLMAPIRepositoryBase;

import zws.security.Authentication;

import java.io.File;
import java.util.Iterator;
import java.util.Map;

import com.agile.plmapi.api.PlmAttribute;
import com.agile.plmapi.api.PlmData;
import com.agile.plmapi.api.PlmException;
import com.agile.plmapi.api.PlmFactory;
import com.agile.plmapi.api.PlmObject;
import com.agile.plmapi.api.PlmRequest;
import com.agile.plmapi.api.PlmResponse;
import com.agile.plmapi.api.PlmSession;
import com.agile.plmapi.api.PlmStatus;
import com.agile.plmapi.api.impl.PlmImplConstants;
import com.agile.plmapi.api.om.PlmActionConstants;
import com.agile.sdo.cif.AgileWSXItemSvc;

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
public class RunPLMAPI3 {

  public static void main(String[] args) {
    String url="http://pwebdev.cisco.com/pls/services";
    AgileWSXItemSvc itemSvc = new AgileWSXItemSvc();

    Metadata root = null;

    try {
      String key1="parent1.prt";
      String key2="child1.prt";
      File f = new File("C:/test.txt");
      Authentication id= new Authentication("user31", "user31");
      root = createMetadata(key1);
      //createRelation();
      PlmSession session = itemSvc.login(url, id);
      session.close();
      session = itemSvc.login(url, id);
      session = itemSvc.login(url, id);
      {} //System.out.println(session.getId());
      {} //System.out.println(session.isValid()+"");
      //get_affecteditems(session);
      part_getstructure(session);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
 /* private static void get_affecteditems(PlmSession session) {

      PlmObject plmChange;
      try {
      plmChange = PlmFactory.createObject("change");
//       Use the number value of a change that already exists on the PLM
//       server
      plmChange.setAttributeValue("number", "C012390");
      PlmRequest request = PlmFactory.createRequest();
      PlmData data = request.getData();
      data.addObject(plmChange);
      PlmActionConstants c ;
      PlmResponse response =

      session.execute(PlmActionConstants.GET_AFFECTED_ITEMS, request);
      PlmStatus status = response.getStatus();
      if (status != null && response.getStatus().isError())
      {} //System.out.println("get affected items failed. Error code = " +
      status.getCode() + " " + status.getMessage());
      data = response.getData();
//       The collection of returned affected items
      {} //System.out.println(data.getObjects());
      PlmObject obj = (PlmObject)data.getObjects().iterator().next();
      Map attrs = obj.getAttributes();
      Iterator itr = attrs.keySet().iterator();
      while(itr.hasNext()) {
        String key = (String) itr.next();
        PlmAttribute value = (PlmAttribute)attrs.get(key);
        {} //System.out.println("key " + key + " value " + value);
      }
      {} //System.out.println("objects" + obj.getAttributeValue("number"));
      } catch (PlmException e) {
      e.printStackTrace();
      }
  }*/

  private static PlmObject part_getstructure(PlmSession session) {
    PlmObject plmPart;
    try {
    plmPart = PlmFactory.createObject("Mechanical Assembly");
//     Use the number value of a part that already exists on the PLM
//     server
    plmPart.setAttributeValue("number", "MEP_25-0010-01_A");
    PlmRequest request = PlmFactory.createRequest();
    request.getHeader().setParameter("recurse", "true");
    request.getHeader().setParameter("load-option", "0");
    PlmData data = request.getData();
    data.addObject(plmPart);
    {} //System.out.println("request " +request);
    PlmResponse response = session.execute(PlmActionConstants.GET_STRUCTURE, request);
    PlmStatus status = response.getStatus();
    if (status != null && response.getStatus().hasErrors())
    {} //System.out.println("part.getstructure failed. Error code = ");
    data = response.getData();
//    The returned PlmObject with complete structure
    plmPart = (PlmObject) data.getObjects().iterator().next();
    {} //System.out.println("plmPart " + plmPart);
    Map attrs = plmPart.getAttributes();
    Iterator itr = attrs.keySet().iterator();
    while(itr.hasNext()) {
      String key = (String) itr.next();
      PlmAttribute value = (PlmAttribute)attrs.get(key);
      {} //System.out.println("key " + key + " value " + value);
    }
        return plmPart;
    } catch (PlmException e) {
    e.printStackTrace();
    }
    return null;
    }


  private static void change_changestatus(PlmSession session) {
    try {
    PlmObject plmChange = PlmFactory.createObject("change");
    plmChange.setAttributeValue("number", "C01223");
//     Change the status of this change.
    plmChange.setOptionValue(PlmImplConstants.INTERNAL_OPTION_NEXT_STATUS_ID,
    "14302");
    PlmRequest plmRequest = PlmFactory.createRequest();
//    Uncomment the follwoing line to give a list of warnings each
//    seperated by these 3 characters "|.|"
    plmRequest.getHeader().setParameter("disabled-warnings", "integerValue1 |.| integerValue2 ...." );
    PlmData plmData = plmRequest.getData();
    plmData.addObject(plmChange);
    //PlmResponse response = session.execute(PlmActionConstants.CHANGE_STATUS,plmRequest);
    PlmResponse response = session.execute("CHANGE_STATUS",plmRequest);
    PlmStatus status = response.getStatus();
    if (status != null && response.getStatus().hasErrors()) {
    {} //System.out.println("change-status failed. Error code ");
    }
    } catch (PlmException e) {
    e.printStackTrace();
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