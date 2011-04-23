/*
 * DesignState - Design Compression Technology 
 * @author: ptoleti 
 * @version: 1.0 
 * Created on Oct 31, 2007 9:09:51 AM 
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved
 */

package zws.qx.service;


import zws.application.Names;
import zws.application.Properties;
import zws.exception.NameNotFound;
import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.qx.xml.QxXML;
//impoer zws.util.Logwriter;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class QxServiceFinder {
  public static QxServiceFinder materializeFinder(String serviceName) throws Exception {
    return new QxServiceFinder(serviceName);
  }

  private QxServiceFinder(String serviceName) throws Exception {
    retrieveServiceRecord(serviceName);
  }  

  public ServiceRecord getServiceRecord() { return svcRecord; }
  
  
  public QxContext prepareQxWebClientContext(String svcPackage, String svcClassName) {
    QxContext ctx = new QxContext();
    ctx.set(QxContext.JAVA_SERVICES_PACKAGE, svcPackage);
    ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, svcClassName);
    ctx.set(QxContext.SOAP_HOSTNAME, svcRecord.getHostName());
    ctx.set(QxContext.SOAP_PORT, svcRecord.getPort());    
    ctx.set(QxContext.SOAP_SERVICES_PATH, svcRecord.getSoapServicePath());
    ctx.set(QxContext.SOAP_SERVICE_NAME, svcRecord.getSoapServiceName());
    ctx.set(QxContext.SOAP_SERVICE_OPERATION, svcRecord.getSoapServiceMethod());
    return ctx;
  }

  public Object materializeClient() throws Exception{
    Object qxClient = svcRecord.materializeServiceClient();
    return qxClient;
  }
  
  private void retrieveServiceRecord(String serviceName) throws Exception{
    if(serviceRecordsCache.containsKey(key(serviceName))) {
      svcRecord = (ServiceRecord)serviceRecordsCache.get(key(serviceName));
      return;
    }

    QxContext ctx = prepareFinderContext();
    QxXML dataInstruction = new QxXML("<find-service service=\"" + serviceName + "\"/>");
    {}//Logwriter.printOnConsole("instruction from findService" + dataInstruction);

    QxWebClient webClient = QxWebClient.materializeClient();
    QxXML result = webClient.executeQx(ctx, dataInstruction);
    {} //System.out.println("findService " + result.toString());
    svcRecord = unmarshallServiceRecord(result.toString());
    if(null==svcRecord) throw new NameNotFound("No service record defined for: "+serviceName);
    serviceRecordsCache.put(key(serviceName),svcRecord);
  }
  
  private QxContext  prepareFinderContext() throws Exception {
    QxContext ctx = new QxContext();
    ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.service");
    ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "ServiceRecordQxService");
    ctx.set(QxContext.SOAP_HOSTNAME, lookupFinderHost());
    ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
    ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
    ctx.set(QxContext.SOAP_SERVICE_OPERATION, "runQx");
    return ctx;
  }
  
  private ServiceRecord  unmarshallServiceRecord(String result) throws Exception {
    ServiceParser insParser = new ServiceParser();
    XMLReader rdr = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");
    rdr.setContentHandler(insParser);
    rdr.setFeature("http://xml.org/sax/features/validation", false);
    rdr.parse(new InputSource(new StringReader(result)));
    return insParser.getServiceRecord();
  }
  
  private String lookupFinderHost() throws Exception{
    if(null ==Properties.get(Names.SERVICE_FINDER_HOSTNAME)) {
      throw new Exception(Names.SERVICE_FINDER_HOSTNAME + " is not set.");
    }
    return Properties.get(Names.SERVICE_FINDER_HOSTNAME);
    //return "DesignState-0"; // +++lookup finder host from properties
  }
  
  private String key(String s) {return s.toLowerCase(); }

  private ServiceRecord svcRecord = null;
  private static Map serviceRecordsCache = new HashMap();
}
