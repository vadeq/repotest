package zws.qx.test;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Apr 5, 2007 1:18:21 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import zws.qx.Qx;
import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.qx.xml.QxXML;

/**
 * @author arbind
 *
 */
public class NestedPingQxService implements Qx {

  public QxXML executeQx(QxContext ctx, QxXML dataInstruction) {
    {} //System.out.println("Nested starting ping()");
     ping();
     {} //System.out.println("Nested starting ping2()");
     ping2();
     {} //System.out.println("Nested complete");

    return new QxXML("<Nested-ping/>");
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

    QxXML nestedPingResult = webClient.executeQx(ctx, new QxXML("<client-ping/>"));

    {} //System.out.println(" nestedPingResult " +nestedPingResult);
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

    QxXML nestedPing2Result = webClient.executeQx(ctx, new QxXML("<client-ping/>"));
    {} //System.out.println(" nestedPing2Result " +nestedPing2Result);

  }
}
