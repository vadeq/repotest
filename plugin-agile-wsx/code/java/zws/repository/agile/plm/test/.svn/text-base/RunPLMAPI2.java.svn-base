package zws.repository.agile.plm.test;


import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.security.Authentication;

import java.io.File;
import com.agile.plmapi.api.PlmRequest;
import com.agile.plmapi.api.PlmResponse;
import com.agile.plmapi.api.PlmSession;
import com.agile.plmapi.api.impl.PlmRequestHelper;

import com.agile.sdo.cif.AgileWSXItemSvc;
import com.agile.sdo.cif.AgileWSXSearchSvc;
import com.agile.share.xml.XmlDocument;

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
public class RunPLMAPI2 {

  public static void main(String[] args) {
    //String url="http://linuxwas.agilesoft.com/Agile/services";
    String url="http://pwebdev.cisco.com/pls/services";
    AgileWSXItemSvc itemSvc = new AgileWSXItemSvc();
    AgileWSXSearchSvc searchSvc = new AgileWSXSearchSvc();
    Metadata root = null;
    try {
      String key1="do_test2.prt";
      String key2="do_test12.prt";
      File f = new File("C:/test.txt");
      //Authentication id= new Authentication("suchaudh", "Agile123");
      Authentication id= new Authentication("user31", "user31");
      String createdByUser = "Admin";
      String createdDate="10/10/07";
      String createdByTool="MCAD Libraby";
      String createdByToolVersion="PLS 1.3";
      String createdForProject="project PLS 1.3";

      root = new MetadataBase();
      root.set("name", key1);
      root.set("lib_x", "888.88");
      root.set("number", key1);
      root.set("description", "Code Drop 3");
      root.set("attachment", f.getAbsolutePath());
      root.set("author", "Matt Mohr");
      root.set("version", "B.8");
      PlmResponse plmResponse = null;
      //PlmSession session = cifClientSvc.login(url, id.getUsername(), id.getPassword());
      PlmSession session = itemSvc.login(url, id);
      //PlmRequest req = PlmFactory.createRequest();
      //cifClientSvc.setHeader(req, createdByUser, createdDate,  createdByTool, createdByToolVersion, createdForProject);
      /*PlmObject o1=cifClientSvc.createObject(
          req, "", key1,"zws",null,
          new String[]{"number",key1,"attachment","c:/test.txt","description","test part","lib_x","22"},
          false,true);*/
      String[] attributes = itemSvc.getStringArray(root.getAttributes());

      //PlmObject o1=cifClientSvc.createObject(req, "", key1,"zws",null,attributes,false,true);

      // -- cifClientSvc.createObject(root, f.getAbsolutePath(), "zws", null, false, true, url, id);

      //PlmRequest request,String name,String key,String source,String[] options,String[] attributes,boolean mappedflag,boolean dirtyflag
      //o1=cifClientSvc.createObject(req, metadata,"zws",null,false,false);
      //PlmObject o2=cifClientSvc.createObject(req, "", key2,"zws",null,new String[]{"number",key2,"attachment","c:/test.txt","description","test part","lib_x","33"},false,true);
      //PlmRelation oor=cifClientSvc.createRelation("object-object-relation", o1, o2, key1, key2, "source", null, null,false,true);

      /*{} //System.out.println("mapping...");
      {} //System.out.println(System.currentTimeMillis()/1000);
      dump(req);
      plmResponse=session.execute("mapping",req);
      if(!plmResponse.getStatus().isSuccess()) cifClientSvc.errorout(session,plmResponse);

      {} //System.out.println("checkstatus...");
      {} //System.out.println(System.currentTimeMillis()/1000);
      PlmRequest mappedrequest=PlmFactory.createRequest(plmResponse,"checkstatus");
      dump(mappedrequest);
      plmResponse=session.execute("checkstatus",mappedrequest);
      if(plmResponse.getStatus().hasErrors() || plmResponse.getStatus().hasFatals())
        cifClientSvc.errorout(session,plmResponse);


      {} //System.out.println("reserve...");
      {} //System.out.println(System.currentTimeMillis()/1000);
      mappedrequest=PlmFactory.createRequest(plmResponse,"reserve");
      dump(mappedrequest);
      plmResponse=session.execute("reserve",mappedrequest);
      if(plmResponse.getStatus().hasErrors() || plmResponse.getStatus().hasFatals())
        cifClientSvc.errorout(session,plmResponse);

  /*    {} //System.out.println("validate...");
      mappedrequest=PlmFactory.createRequest(plmResponse,"validate");
      dump(mappedrequest);
      plmResponse=session.execute("validate",mappedrequest);
     if(plmResponse.getStatus().hasErrors() || plmResponse.getStatus().hasFatals()) cifClientSvc.errorout(session,plmResponse);

     {} //System.out.println("upload-files...");
     {} //System.out.println(System.currentTimeMillis()/1000);
     mappedrequest=PlmFactory.createRequest(plmResponse,"upload-files");
     mappedrequest.getHeader().setParameter("recurse", "true"); // set to scan the whole request structure
     dump(mappedrequest);
     plmResponse = session.execute("upload-files", mappedrequest);
     if(plmResponse.getStatus().hasErrors() || plmResponse.getStatus().hasFatals())
       cifClientSvc.errorout(session,plmResponse);

     {} //System.out.println("update...");
     {} //System.out.println(System.currentTimeMillis()/1000);
     mappedrequest=PlmFactory.createRequest(plmResponse,"update");
     dump(mappedrequest);
     plmResponse=session.execute("update",mappedrequest);
     if(plmResponse.getStatus().hasErrors() || plmResponse.getStatus().hasFatals())
       cifClientSvc.errorout(session,plmResponse);

     {} //System.out.println("release... ");
     {} //System.out.println(System.currentTimeMillis()/1000);
     mappedrequest=PlmFactory.createRequest(plmResponse,"release");
     dump(mappedrequest);
     plmResponse=session.execute("release",mappedrequest);
     if(plmResponse.getStatus().hasErrors() || plmResponse.getStatus().hasFatals())
       cifClientSvc.errorout(session,plmResponse);
      {} //System.out.println(PlmResponseHelper.marshal(plmResponse).toFormattedString() );*/
      {} //System.out.println(System.currentTimeMillis()/1000);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void dump(PlmRequest r) {
    XmlDocument rqd = new XmlDocument( PlmRequestHelper.marshal( r ) );
    {} //System.out.println( rqd.toFormattedString() );
  }
 }
