package zws.qx.test;

import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.qx.xml.QxXML;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/*
 DesignState - Design Compression Technology
 @author: HP USER
 @version: 1.0
 Created on Apr 5, 2007 9:51:05 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

public class LoadTestQx implements JavaSamplerClient{
  public LoadTestQx() {
  }
  public void setupTest(JavaSamplerContext context) {
  }
  public Arguments getDefaultParameters() {
    Arguments params = new Arguments();
    params.addArgument("QueueName", "");
    return params;
  }
  public SampleResult runTest(JavaSamplerContext context) {
    SampleResult results = new SampleResult();
    try{  
      
      QxWebClient webClient = QxWebClient.materializeClient();
      QxContext ctx = new QxContext();
      ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.queue");
      ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "QxQueueService");
      ctx.set(QxContext.SOAP_HOSTNAME, "vm-ilink-1");
      ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
      ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
      ctx.set(QxContext.SOAP_SERVICE_OPERATION, "executeQx");
      
      String queueName = context.getParameter("QueueName");
      ctx.set(QxContext.QUEUE_NAME, queueName);
      
      QxXML startInstruction = new QxXML("<start />");
      QxXML stopInstruction = new QxXML("<stop />");
      
      
      {} //System.out.println("Start");
      
    // webClient.executeQx(ctx, startInstruction);
      
      {} //System.out.println("Stop");
      
   // webClient.executeQx(ctx, stopInstruction);
   
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
      xml = new QxXML("<data-instruction message='test3 instruction' />");
      webClient.executeQx(ctx, xml);
     
      ctx.set("PRIORITY", "40");
      xml = new QxXML("<data-instruction message='test4 instruction' />");
      webClient.executeQx(ctx, xml);
     
      webClient.executeQx(ctx, startInstruction);
        results.setSuccessful(true);
    }
    catch (Exception ex){
      ex.printStackTrace();
      results.setSuccessful(false);
    }
    return results;
  }
 
  public void teardownTest(JavaSamplerContext context) {
  }
  
  public static void main(String[] args){
    QxWebClient webClient = QxWebClient.materializeClient();
    QxContext ctx = new QxContext();
    ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.queue");
    ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "QxQueueService");
    ctx.set(QxContext.SOAP_HOSTNAME, "vm-ilink-1");
    ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
    ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
    ctx.set(QxContext.SOAP_SERVICE_OPERATION, "executeQx");
    
    
    ctx.set(QxContext.QUEUE_NAME, "TQ2");
    QxXML startInstruction = new QxXML("<start />");
    
    QxXML stopInstruction = new QxXML("<stop />");
    webClient.executeQx(ctx, stopInstruction);
   // webClient.executeQx(ctx, startInstruction);
  }

}
