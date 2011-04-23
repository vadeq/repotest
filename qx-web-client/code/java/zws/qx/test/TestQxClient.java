package zws.qx.test;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Mar 25, 2007 11:06:50 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.qx.xml.QxXML;
//import zws.util.{}//Logwriter;
import java.io.File;
import java.io.FileOutputStream;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

public class TestQxClient {

  private QxContext createDefaultContext() {
    QxContext ctx = new QxContext();
    //set all the parameters
    //set java service package to zws.qx.test
    //set java service classname to QxQueueService
    //set all the other stuff

    //comfigure host file on client and server:
    // designstate-node0 ip address points to axis server

    //configure axis2
    //set webapp to zerowait-state:
    //http://designstate-node-0/zerowait-state
    //enable MTOM

    return ctx;
  }



  private void executeQxTest() {
    QxWebClient webClient = QxWebClient.materializeClient();
    QxContext ctx = new QxContext();
    ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.test");
    ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "QxQueueService");
    ctx.set(QxContext.SOAP_HOSTNAME, "designstate-0");
    ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
    ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
    ctx.set(QxContext.SOAP_SERVICE_OPERATION, "executeQx");

    QxXML dataInstruction = new QxXML();
    {}//Logwriter.printOnConsole("HERE");
    webClient.executeQx(ctx, dataInstruction);
  }

  private void executeQxQueueTest() {
    String q = "Test3 Queue";
    clearKeys();
    stop(q);
    queue(q);
    //queueLots(q, 500);
    start(q);
  }
  
  private void clearKeys(){
    QxWebClient webClient = QxWebClient.materializeClient();
    QxContext ctx = new QxContext();
    ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.queue");
    ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "QxQueueService");
    ctx.set(QxContext.SOAP_HOSTNAME, "designstate-0");
    ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
    ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
    ctx.set(QxContext.SOAP_SERVICE_OPERATION, "clearKeys");

    

    {}//Logwriter.printOnConsole("clear Keys");
    webClient.clearKeys(ctx);
  }

  private void start(String q){
    QxWebClient webClient = QxWebClient.materializeClient();
    QxContext ctx = new QxContext();
    ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.queue");
    ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "QxQueueService");
    ctx.set(QxContext.SOAP_HOSTNAME, "designstate-0");
    ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
    ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
    ctx.set(QxContext.SOAP_SERVICE_OPERATION, "runQx");

    ctx.set(QxContext.QUEUE_NAME, q);
    QxXML startInstruction = new QxXML("<start />");

    {}//Logwriter.printOnConsole("Start");
    webClient.executeQx(ctx, startInstruction);
  }

  private void stop(String q){
    QxWebClient webClient = QxWebClient.materializeClient();
    QxContext ctx = new QxContext();
    ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.queue");
    ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "QxQueueService");
    ctx.set(QxContext.SOAP_HOSTNAME, "designstate-0");
    ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
    ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
    ctx.set(QxContext.SOAP_SERVICE_OPERATION, "runQx");

    ctx.set(QxContext.QUEUE_NAME, q);
    QxXML stopInstruction = new QxXML("<stop />");

    {}//Logwriter.printOnConsole("Stop");
    webClient.executeQx(ctx, stopInstruction);
  }

  private void queue(String q) {
    QxWebClient webClient = QxWebClient.materializeClient();
    QxContext ctx = new QxContext();
    ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.queue");
    ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "QxQueueService");
    ctx.set(QxContext.SOAP_HOSTNAME, "designstate-0");
    ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
    ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
    ctx.set(QxContext.SOAP_SERVICE_OPERATION, "runQx");

    ctx.set(QxContext.QUEUE_NAME, q);

    ctx.set("PRIORITY", "1");
    QxXML xml = new QxXML("<data-instruction message='test1 instruction' />");
    webClient.executeQx(ctx, xml);

    ctx.set("PRIORITY", "10");

    xml = new QxXML("<data-instruction message='test10 instruction' />");
    webClient.executeQx(ctx, xml);


    ctx.set("PRIORITY", "2");

    xml = new QxXML("<data-instruction message='test2 instruction' />");
    webClient.executeQx(ctx, xml);

    ctx.set("PRIORITY", "20");
    xml = new QxXML("<data-instruction message='test20 instruction' />");
    webClient.executeQx(ctx, xml);

    ctx.set("PRIORITY", "30");
    xml = new QxXML("<data-instruction message='test30 instruction' />");
    webClient.executeQx(ctx, xml);

    ctx.set("PRIORITY", "40");
    xml = new QxXML("<data-instruction message='test40 instruction' />");
    webClient.executeQx(ctx, xml);

    ctx.set("PRIORITY", "1");
    xml = new QxXML("<data-instruction message='test1.1 instruction' />");
    webClient.executeQx(ctx, xml);
    ctx.set("PRIORITY", "1");
    xml = new QxXML("<data-instruction message='test1.2 instruction' />");
    webClient.executeQx(ctx, xml);

  }

  private void queueLots(String q, int count) {
    QxWebClient webClient = QxWebClient.materializeClient();
    QxContext ctx = new QxContext();
    ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.queue");
    ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "QxQueueService");
    ctx.set(QxContext.SOAP_HOSTNAME, "designstate-0");
    ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
    ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
    ctx.set(QxContext.SOAP_SERVICE_OPERATION, "runQx");

    ctx.set(QxContext.QUEUE_NAME, q);

    ctx.set("PRIORITY", "1");
    QxXML  xml=null;
    for (int idx = 0; idx < count; idx++) {
      xml = new QxXML("<data-instruction message='test-lots-"+idx+" instruction' />");
      webClient.executeQx(ctx, xml);
    }
  }

  private void executeQxSquareTest() {
    QxWebClient webClient = QxWebClient.materializeClient();
    QxContext ctx = new QxContext();
    ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.test");
    ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "QxQueueService");
    ctx.set(QxContext.SOAP_HOSTNAME, "designstate-0");
    ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
    ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
    ctx.set(QxContext.SOAP_SERVICE_OPERATION, "runQx");

    ctx.set(QxContext.QUEUE_NAME, "Test2 Queue");

   // EncryptDecrypt encrypter = new EncryptDecrypt();

    // Encrypt
  //  String encrypted = encrypter.encrypt("Test password");

    ctx.set(QxContext.PASSWORD, "HELLO");
   // {} //System.out.println("Encrypted password:"+ encrypted);
   // Secret secret2 = Secret.getInstance();
    // secret2.configure("1024", prime1024, base1024, randomExp1024);
   //  secret2.configure1024("1024");
   //  secret2.encrypt("1024", "<string_context password=\"HELLO\" queue-name=\"Test2 Queue\" soap-hostname=\"designstate-0\" soap-service-operation=\"executeQx\" java-services-package=\"zws.qx.queue\" soap-service-name=\"QxWebService\" soap-services-path=\"ZeroWait-State/services\" java-service-classname=\"QxQueueService\" />");
    QxXML startInstruction = new QxXML("<start />");
    QxXML stopInstruction = new QxXML("<stop />");


    {} //System.out.println("Start");

  //webClient.executeQx(ctx, startInstruction);

    {} //System.out.println("Stop");

 // webClient.executeQx(ctx, stopInstruction);

 // webClient.executeQx(ctx, startInstruction);

    ctx.set("PRIORITY", "1");
    QxXML xml = new QxXML("<square number='"+83+"'/>");
    webClient.executeQx(ctx, xml);

    ctx.set("PRIORITY", "10");
    xml = new QxXML("<square number='"+55+"'/>");
    webClient.executeQx(ctx, xml);

    ctx.set("PRIORITY", "2");
    xml = new QxXML("<square number='"+50+"'/>");
    webClient.executeQx(ctx, xml);

    ctx.set("PRIORITY", "20");
    xml = new QxXML("<square number='"+20+"'/>");
    webClient.executeQx(ctx, xml);

    ctx.set("PRIORITY", "30");
    xml = new QxXML("<square number='"+86+"'/>");
    webClient.executeQx(ctx, xml);

    ctx.set("PRIORITY", "40");
    xml = new QxXML("<square number='"+14+"'/>");
    webClient.executeQx(ctx, xml);


   // webClient.executeQx(ctx, startInstruction);


  }

  private void TransferQxTest() {
    //create a file  (so we can test different sizes)
    // upload it -> should save
    // download it to filename.00 ->  have a new file
    // upload it again -> should give exception
    // executeQx to delete original upload
    // download it to filename.01 -> should give exception
    // upload it again -> should save again
    // download it to filename.02 -> should have a new file
    QxWebClient webClient = QxWebClient.materializeClient();
    QxContext ctx = new QxContext();
    ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.test");
    ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "TestQxService");
    ctx.set(QxContext.SOAP_HOSTNAME, "designstate-0");
    ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
    ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
    ctx.set(QxContext.SOAP_SERVICE_OPERATION, "transferQx");


    QxXML dataInstruction = new QxXML();

    File inFile = new File("C:/ilink-zws-agile.jpg");
    DataHandler inDataHandler = new DataHandler(new FileDataSource(inFile));
    {}//Logwriter.printOnConsole("In TransferQxTest");
    QxXML response = webClient.transferQx(ctx, dataInstruction, inDataHandler);
    {}//Logwriter.printOnConsole("Response is " + response.toString());
    /*try{
      inDataHandler.getOutputStream().close();
    } catch(Exception ex){
      ex.printStackTrace();
    }*/

    FileOutputStream outStream = null;
    try {
      DataHandler downloadHandler = webClient.transferQx(ctx, dataInstruction);
      File outFile = new File("C:/zws/responseAttachment.dat");
      outStream =  new FileOutputStream(outFile);
      downloadHandler.writeTo(outStream);
    } catch(Exception ex){
      {}//Logwriter.printOnConsole("Exception "+ex.getMessage());
    }
    finally {
      if( outStream != null ) {
        try {
          outStream.close();
        } catch (Exception ex){
          {}//Logwriter.printOnConsole(ex.getMessage());
        }
      }
    }
  }

  private void createFile(String path, long size) {
  }

  private void uploadTransferQxTest(String localPath, String remotePath) {
  }


  private void downloadTransferQxTest(String localPath, String remotePath) {

  }

  public static void main(String[] a) throws Exception {
    TestQxClient client = new TestQxClient();
   // client.executeQxTest();
   // client.TransferQxTest();
    client.executeQxQueueTest();
    //client.ping();
    //client.ping2();
    //client.ping3();
   // client.executeQxSquareTest();
  // client.NestedPing();
  }



  private void ping() {
    QxWebClient webClient = QxWebClient.materializeClient();
    QxContext ctx = new QxContext();
    ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.service");
    ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "PingQxService");
    ctx.set(QxContext.SOAP_HOSTNAME, "designstate-0");
    ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
    ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
    ctx.set(QxContext.SOAP_SERVICE_OPERATION, "runQx");

    webClient.executeQx(ctx, new QxXML("<client-ping/>"));
  }

  private void NestedPing() {
    QxWebClient webClient = QxWebClient.materializeClient();
    QxContext ctx = new QxContext();
    ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.test");
    ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "NestedPingQxService");
    ctx.set(QxContext.SOAP_HOSTNAME, "designstate-0");
    ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
    ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
    ctx.set(QxContext.SOAP_SERVICE_OPERATION, "runQx");

    webClient.executeQx(ctx, new QxXML("<client-ping/>"));
  }



  private void ping2() {
    QxWebClient webClient = QxWebClient.materializeClient();
    QxContext ctx = new QxContext();
    ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.service");
    ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "Ping2QxService");
    ctx.set(QxContext.SOAP_HOSTNAME, "designstate-0");
    ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
    ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
    ctx.set(QxContext.SOAP_SERVICE_OPERATION, "runQx");

    webClient.executeQx(ctx, new QxXML("<client-ping/>"));
  }

  private void ping3() {
    QxWebClient webClient = QxWebClient.materializeClient();
    QxContext ctx = new QxContext();
    ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.service");
    ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "Ping3QxService");
    ctx.set(QxContext.SOAP_HOSTNAME, "designstate-0");
    ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
    ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
    ctx.set(QxContext.SOAP_SERVICE_OPERATION, "runQx");

    webClient.executeQx(ctx, new QxXML("<client-ping/>"));
  }

}
