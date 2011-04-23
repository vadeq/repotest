package zws.qx;

import zws.qx.xml.QxXML;

import zws.security.Secret;


//impoer zws.util.Logwriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.PublicKey;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.OMText;
//import org.apache.axis.handlers.{}//Logwriter;
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.rpc.client.RPCServiceClient;


public class QxWebClient extends QxTransferBase implements Qx {
  private static QxWebClient qxClient = new QxWebClient();

  private QxWebClient() {}

  private static Map serverSecrets = new HashMap();

  public Secret lookupSecret(QxContext ctx) {
    if (!serverSecrets.containsKey(ctx.lookupEndPointReference())) exchangeKeys(ctx);
    Secret secret = (Secret) serverSecrets.get(ctx.lookupEndPointReference());
    return secret;
  }

  private static String clientID = null;

  private String getClientId() {
    if (null==clientID) {
      clientID = zws.Server.getIPAddress();
      clientID += Calendar.getInstance().getTimeInMillis();
    }
    //return clientID;
    return "CLEAR_TEXT";
  }

  private boolean enableCrypto(){
    return true; //TBD

  }

  public void clearKeys(QxContext ctx) {

    String clientId = getClientId();

    String endPointRef = ctx.lookupEndPointReference();

    if (serverSecrets.containsKey(endPointRef)) {
      serverSecrets.remove(endPointRef);
    }

    try {
      // Create an end point reference for the SOAP web service.
      EndpointReference targetEPR = new EndpointReference(endPointRef);
      // Create RPC Client to invoke the webservice webmethod.
      RPCServiceClient serviceClient = new RPCServiceClient();
      // Create Option to set for the service Client.
      Options options = serviceClient.getOptions();

      // Set the client options
      options.setTo(targetEPR);
      options.setTimeOutInMilliSeconds(timeoutInSeconds*1000);
      options.setAction("clearKeys");
      // Create operation (webmethod) ref.
      QName operation = new QName(ctx.lookupSOAPSchemaTargetNameSpace(),
          "clearKeys");

      // Initialize the argument objects to the webservice operation
      Object[] opArgs = new Object[] { clientId };
      // Initialize the return class type.
      Class[] returnTypes = new Class[] { String.class };
      // Invoke the operation on the WebService
      
      Object[] response = serviceClient.invokeBlocking(operation, opArgs,
          returnTypes);
      // Retrieve the response
      String result = (String) response[0];

      if (result == null) throw new Exception("The response result returned from webservice on clearKeys is null");
      
    } catch (Exception ex) {
      System.err.println("Exception message in QxWebClient.clearKeys: " + ex.getMessage());
      ex.printStackTrace();
    }

  }

  public void exchangeKeys(QxContext ctx) {

    try {

      Secret secret = new Secret();

      String key = new sun.misc.BASE64Encoder().encode(secret
          .getLocalPublicKey().getEncoded());

      String clientId = getClientId();

      String endPointRef = ctx.lookupEndPointReference();
      // Create an end point reference for the SOAP web service.
      EndpointReference targetEPR = new EndpointReference(endPointRef);
      // Create RPC Client to invoke the webservice webmethod.
      RPCServiceClient serviceClient = new RPCServiceClient();
      // Create Option to set for the service Client.
      Options options = serviceClient.getOptions();

      // Set the client options
      options.setTo(targetEPR);
      options.setTimeOutInMilliSeconds(timeoutInSeconds * 1000);
      options.setAction("exchangeKeys");
      // Create operation (webmethod) ref.
      QName operation = new QName(ctx.lookupSOAPSchemaTargetNameSpace(),
          "exchangeKeys");

      // Initialize the argument objects to the webservice operation
      Object[] opArgs = new Object[] { clientId, key };
      // Initialize the return class type.
      Class[] returnTypes = new Class[] { String.class };
      
      // Invoke the operation on the WebService
      Object[] response = serviceClient.invokeBlocking(operation, opArgs,
          returnTypes);
      // Retrieve the response
      String result = (String) response[0];

      if (result == null) throw new Exception(
          "The response result returned from webservice on exchangeKeys is null");

      // Generete remote public key.
      PublicKey remotePublic = Secret
          .constructPublicKey(new sun.misc.BASE64Decoder().decodeBuffer(result));

      // Configure secret with remote public key.
      secret.configure(remotePublic);

      // Store in hash map for later use
      serverSecrets.put(ctx.lookupEndPointReference(), secret);

    } catch (Exception ex) {
      System.err.println("Exception message in QxWebClient.exchangeKeys: " + ex.getMessage());
      ex.printStackTrace();
    }
  }


  public static QxWebClient materializeClient() { return qxClient; }

  public QxXML executeQx(QxContext ctx, QxXML dataInstruction) {

    Secret secret = null;


    QxXML result = null;
    String clientId = getClientId();
    if( clientId == null )
      clientId = "CLEAR_TEXT";

    boolean enableCrypto = enableCrypto();

    if( !enableCrypto )
      clientId = "CLEAR_TEXT";

    if( clientId.equalsIgnoreCase("CLEAR_TEXT") )
      enableCrypto = false;

    String context = null;
    String dataInstr = null;

    if( !enableCrypto ) {
      context = ctx.toString();
      dataInstr = dataInstruction.toString();
    } else {
      try {
        secret = lookupSecret(ctx);
        context = secret.encrypt(ctx.toString());
        dataInstr = secret.encrypt(dataInstruction.toString());
      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    }

    try {
      String endPointRef = ctx.lookupEndPointReference();
      {}//Logwriter.printOnConsole("endPointRef " + endPointRef );
      // Create an end point reference for the SOAP web service.
      EndpointReference targetEPR = new EndpointReference(endPointRef);
     {}//Logwriter.printOnConsole("targetEPR " + targetEPR.toString());
      // Create RPC Client to invoke the webservice webmethod.
      RPCServiceClient serviceClient = new RPCServiceClient();
     {}//Logwriter.printOnConsole("serviceClient " + targetEPR.toString());
      // Create Option to set for the service Client.
      Options options = serviceClient.getOptions();
     {}//Logwriter.printOnConsole("options " + options.toString());
      // Set the client options
      options.setTo(targetEPR);
      options.setTimeOutInMilliSeconds(timeoutInSeconds * 1000);
      
      // Create operation (webmethod) ref.

      //options.setAction(ctx.lookupSOAPServiceOperation());
      options.setAction("runQx");
      QName operation = new QName(ctx.lookupSOAPSchemaTargetNameSpace(), ctx.lookupSOAPServiceOperation());



      // Initialize the argument objects to the webservice operation
      Object[] opArgs = new Object[] { clientId, context, dataInstr };
      // Initialize the return class type.
      Class[] returnTypes = new Class[] { String.class };
      //print(ctx);
     {}//Logwriter.printOnConsole("operation in QX webclinet :");
      {}//Logwriter.printOnConsole(operation.getLocalPart());
     {}//Logwriter.printOnConsole(operation.getClass());
     {}//Logwriter.printOnConsole(operation.getNamespaceURI());
     {}//Logwriter.printOnConsole(operation.getPrefix());
     {}//Logwriter.printOnConsole("opArgs in QX webclinet :");
     {}//Logwriter.printOnConsole(opArgs.length);
     {}//Logwriter.printOnConsole(opArgs[0]);
     {}//Logwriter.printOnConsole(opArgs[1]);
     {}//Logwriter.printOnConsole("returnTypes in QX webclinet :");
     {}//Logwriter.printOnConsole(returnTypes.toString());

     
     // Invoke the operation on the WebService
      Object[] response = serviceClient.invokeBlocking(operation, opArgs, returnTypes);
      // Retrieve the response
      String resultStr = (String) response[0];
      if( enableCrypto )
        result = new QxXML(secret.decrypt(resultStr));
      else
        result = new QxXML(resultStr);
      {}//Logwriter.printOnConsole(result.toString());
    }
    catch (NullPointerException ex) {
      System.err.println("Null pointerException message: " + ex.getMessage());
      ex.printStackTrace();
    }
    catch (Exception ex) {
      System.err.println("Exception message: " + ex.getMessage());
      ex.printStackTrace();
    }
    return result;
  }

private void print(QxContext ctx) {
  {}//Logwriter.printOnConsole("########################################################3" );
  {}//Logwriter.printOnConsole("QxContext.DESCRIPTION"  + ctx.get(QxContext.DESCRIPTION));
  {}//Logwriter.printOnConsole("QxContext.USERNAME" + ctx.get(QxContext.USERNAME));
  {}//Logwriter.printOnConsole("QxContext.PASSWORD" + ctx.get(QxContext.PASSWORD));
  {}//Logwriter.printOnConsole("QxContext.DEFAULT_USERNAME" + ctx.get(QxContext.DEFAULT_USERNAME));
  {}//Logwriter.printOnConsole("QxContext.DEFAULT_PASSWORD" + ctx.get(QxContext.DEFAULT_PASSWORD));
  {}//Logwriter.printOnConsole("QxContext.DOMAIN_NAME" + ctx.get(QxContext.DOMAIN_NAME));
  {}//Logwriter.printOnConsole("QxContext.SERVER_NAME" + ctx.get(QxContext.SERVER_NAME));
  {}//Logwriter.printOnConsole("QxContext.REPOSITORY_TYPE" + ctx.get(QxContext.REPOSITORY_TYPE));
  {}//Logwriter.printOnConsole("QxContext.REPOSITORY_NAME" + ctx.get(QxContext.REPOSITORY_NAME));
  {}//Logwriter.printOnConsole("QxContext.SERVICE" + ctx.get(QxContext.SERVICE));
  {}//Logwriter.printOnConsole("QxContext.METHOD" + ctx.get(QxContext.METHOD));
  {}//Logwriter.printOnConsole("QxContext.OP_TYPE" + ctx.get(QxContext.OP_TYPE));
  {}//Logwriter.printOnConsole("QxContext.ACQUIRE_LICENSE" + ctx.get(QxContext.ACQUIRE_LICENSE));
  {}//Logwriter.printOnConsole("QxContext.SOAP_PROTOCOL " + ctx.get(QxContext.SOAP_PROTOCOL ));
  {}//Logwriter.printOnConsole("QxContext.SOAP_HOSTNAME " + ctx.get(QxContext.SOAP_HOSTNAME ));
  {}//Logwriter.printOnConsole("QxContext.SOAP_PORT " + ctx.get(QxContext.SOAP_PORT ));
  {}//Logwriter.printOnConsole("QxContext.SOAP_SERVICES_PATH " + ctx.get(QxContext.SOAP_SERVICES_PATH ));
  {}//Logwriter.printOnConsole("QxContext.SOAP_SERVICE_NAME " + ctx.get(QxContext.SOAP_SERVICE_NAME ));
  {}//Logwriter.printOnConsole("QxContext.SOAP_SERVICE_OPERATION " + ctx.get(QxContext.SOAP_SERVICE_OPERATION ));
  {}//Logwriter.printOnConsole("QxContext.SOAP_SERVICE_NAMESPACE " + ctx.get(QxContext.SOAP_SERVICE_NAMESPACE ));
  {}//Logwriter.printOnConsole("QxContext.SOAP_SERVICE_NAMESPACE_PREFIX " + ctx.get(QxContext.SOAP_SERVICE_NAMESPACE_PREFIX ));
  {}//Logwriter.printOnConsole("QxContext.PRIORITY " + ctx.get(QxContext.PRIORITY ));
  {}//Logwriter.printOnConsole("QxContext.ACTION " + ctx.get(QxContext.ACTION ));
  {}//Logwriter.printOnConsole("QxContext.SOURCE " + ctx.get(QxContext.SOURCE ));
  {}//Logwriter.printOnConsole("QxContext.TARGET " + ctx.get(QxContext.TARGET ));
  {}//Logwriter.printOnConsole("QxContext.JAVA_SERVICES_PACKAGE " + ctx.get(QxContext.JAVA_SERVICES_PACKAGE ));
  {}//Logwriter.printOnConsole("QxContext.JAVA_SERVICE_CLASSNAME " + ctx.get(QxContext.JAVA_SERVICE_CLASSNAME ));
  {}//Logwriter.printOnConsole("QxContext.QUEUE_NAME " + ctx.get(QxContext.QUEUE_NAME ));
  {}//Logwriter.printOnConsole("QxContext.NAME" + ctx.get(QxContext.NAME));
  {}//Logwriter.printOnConsole("QxContext.SYSTEM_USERNAME" + ctx.get(QxContext.SYSTEM_USERNAME));
  {}//Logwriter.printOnConsole("QxContext.SYSTEM_PASSWORD" + ctx.get(QxContext.SYSTEM_PASSWORD));
  {}//Logwriter.printOnConsole("QxContext.PROTOCOL" + ctx.get(QxContext.PROTOCOL));
  {}//Logwriter.printOnConsole("QxContext.HOST_NAME" + ctx.get(QxContext.HOST_NAME));
  {}//Logwriter.printOnConsole("QxContext.PORT" + ctx.get(QxContext.PORT));
  {}//Logwriter.printOnConsole("QxContext.SERVICES_PATH" + ctx.get(QxContext.SERVICES_PATH));
  {}//Logwriter.printOnConsole("QxContext.SERVICE_NAME" + ctx.get(QxContext.SERVICE_NAME));
  {}//Logwriter.printOnConsole("QxContext.METHOD_NAME" + ctx.get(QxContext.METHOD_NAME));
}

  public QxXML transferQx(QxContext ctx, QxXML dataInstruction,
      DataHandler binary) {
    QxXML xmlResponse = null;

    Secret secret = null;
    try {
      OMFactory fac = OMAbstractFactory.getOMFactory();
      OMNamespace omNs = fac.createOMNamespace(ctx
          .lookupSOAPSchemaTargetNameSpace(), ctx.lookupSOAPNameSpacePrefix());

      OMElement payload = fac.createOMElement("transferQx", omNs);

      OMElement binaryElement = fac.createOMElement("binary", omNs);

      String clientId = getClientId();
      if( clientId == null )
        clientId = "CLEAR_TEXT";

      boolean enableCrypto = enableCrypto();

      if( !enableCrypto )
        clientId = "CLEAR_TEXT";

      if( clientId.equalsIgnoreCase("CLEAR_TEXT") )
        enableCrypto = false;

      String context = null;
      String dataInstr = null;

      if( !enableCrypto ) {
        context = ctx.toString();
        dataInstr = dataInstruction.toString();
      } else {
        try {
          secret = lookupSecret(ctx);
          context = secret.encrypt(ctx.toString());
          dataInstr = secret.encrypt(dataInstruction.toString());

          InputStream is = binary.getDataSource().getInputStream();

          //OutputStream os = binary.getDataSource().getOutputStream();

          OutputStream os = new FileOutputStream("C:/enc_"+binary.getDataSource().getName());
          secret.encryptFile(is, os);

          DataHandler encBinary = new DataHandler(new FileDataSource("C:/enc_"+binary.getDataSource().getName()));
          binary = encBinary;


        } catch (Exception e) {
          e.printStackTrace();
          return null;
        }
      }




      OMText binaryData = fac.createOMText(binary, true);
      binaryData.setBinary(true);
      binaryElement.addChild(binaryData);
      {}//Logwriter.printOnConsole("Added BinaryData");

      OMElement contextElement = fac.createOMElement("ctx", omNs);
      contextElement.addChild(fac.createOMText(context));

      OMElement dataInstructionElement = fac.createOMElement("data-instruction", omNs);
      dataInstructionElement.addChild(fac.createOMText(dataInstr));

      OMElement clientIdElement = fac.createOMElement("Client-Id", omNs);
      clientIdElement.addChild(fac.createOMText(clientId));

      payload.addChild(contextElement);
      payload.addChild(dataInstructionElement);
      payload.addChild(clientIdElement);
      payload.addChild(binaryElement);

      String endPointRef = ctx.lookupEndPointReference();
      // Create an end point reference for the SOAP web service.
      EndpointReference targetEPR = new EndpointReference(endPointRef);
      // Create RPC Client to invoke the webservice webmethod.
      RPCServiceClient serviceClient = new RPCServiceClient();
      // Create Option to set for the service Client.
      Options options = serviceClient.getOptions();
      // Set the client options
      options.setTo(targetEPR);

      options.setProperty(Constants.Configuration.CACHE_ATTACHMENTS,Constants.VALUE_TRUE);
      options.setProperty(Constants.Configuration.ATTACHMENT_TEMP_DIR, "\\zws\\temp\\axis-cache");
      options.setProperty(Constants.Configuration.FILE_SIZE_THRESHOLD, "4000");

      options.setTimeOutInMilliSeconds(timeoutInSeconds * 1000);
      options.setProperty(Constants.Configuration.ENABLE_MTOM,Constants.VALUE_TRUE);
      options.setAction("runQx");
      OMElement attachResult = serviceClient.sendReceive(payload);
      OMElement ele = attachResult.getFirstElement();
      String xmlResponseStr = ele.getText();

      if( enableCrypto )
        xmlResponse = new QxXML(secret.decrypt(xmlResponseStr));
      else
        xmlResponse = new QxXML(xmlResponseStr);
    } catch (Exception ex) {
      {}//Logwriter.printOnConsole(ex.getMessage());
      ex.printStackTrace();
    }

    return xmlResponse;
  }

  public DataHandler transferQx(QxContext ctx, QxXML dataInstruction) {
    DataHandler dataResponse = null;
    Secret secret = null;
    try {
      OMFactory fac = OMAbstractFactory.getOMFactory();
      OMNamespace omNs = fac.createOMNamespace(ctx
          .lookupSOAPSchemaTargetNameSpace(), ctx.lookupSOAPNameSpacePrefix());

      OMElement payload = fac.createOMElement("runQx", omNs);



      String clientId = getClientId();
      if( clientId == null )
        clientId = "CLEAR_TEXT";

      boolean enableCrypto = enableCrypto();

      if( !enableCrypto )
        clientId = "CLEAR_TEXT";

      if( clientId.equalsIgnoreCase("CLEAR_TEXT") )
        enableCrypto = false;

      String context = null;
      String dataInstr = null;

      if( !enableCrypto ) {
        context = ctx.toString();
        dataInstr = dataInstruction.toString();
      } else {
        try {
          secret = lookupSecret(ctx);
          context = secret.encrypt(ctx.toString());
          dataInstr = secret.encrypt(dataInstruction.toString());

        } catch (Exception e) {
          e.printStackTrace();
          return null;
        }
      }

      OMElement contextElement = fac.createOMElement("ctx", omNs);
      contextElement.addChild(fac.createOMText(context));

      OMElement dataInstructionElement = fac.createOMElement("data-instruction", omNs);
      dataInstructionElement.addChild(fac.createOMText(dataInstr));

      OMElement clientIdElement = fac.createOMElement("Client-Id", omNs);
      clientIdElement.addChild(fac.createOMText(clientId));

      payload.addChild(contextElement);
      payload.addChild(dataInstructionElement);
      payload.addChild(clientIdElement);

      String endPointRef = ctx.lookupEndPointReference();
      // Create an end point reference for the SOAP web service.
      EndpointReference targetEPR = new EndpointReference(endPointRef);
      // Create RPC Client to invoke the webservice webmethod.
      RPCServiceClient serviceClient = new RPCServiceClient();
      // Create Option to set for the service Client.
      Options options = serviceClient.getOptions();

      // Set the client options
      options.setTo(targetEPR);
      options.setProperty(Constants.Configuration.ENABLE_MTOM,
          Constants.VALUE_TRUE);
      options.setTimeOutInMilliSeconds(timeoutInSeconds * 1000);

      options.setProperty(Constants.Configuration.CACHE_ATTACHMENTS,Constants.VALUE_TRUE);
      options.setProperty(Constants.Configuration.ATTACHMENT_TEMP_DIR, "/zws/temp/cache");
      options.setProperty(Constants.Configuration.FILE_SIZE_THRESHOLD, "4000");
      options.setAction("runQx");
      
      OMElement attachResult = serviceClient.sendReceive(payload);
      OMElement ele = attachResult.getFirstElement();
      OMText binaryNode = (OMText) ele.getFirstOMChild();
      binaryNode.setBinary(true);

      // Retrieving the DataHandler & then do whatever the processing to the
      // data
      dataResponse = (DataHandler) binaryNode.getDataHandler();


      InputStream is = dataResponse.getDataSource().getInputStream();
      //OutputStream os = binary.getDataSource().getOutputStream();
      OutputStream os = new FileOutputStream("C:/dec_"+dataResponse.getDataSource().getName());
      secret.decryptFile(is, os);

      DataHandler deccBinary = new DataHandler(new FileDataSource("C:/dec_"+dataResponse.getDataSource().getName()));
      dataResponse = deccBinary;


    } catch (Exception ex) {
      {}//Logwriter.printOnConsole(ex.getMessage());
      ex.printStackTrace();
    }
    return dataResponse;
  }

  /*
   * public static void testQxService() throws Exception { RPCServiceClient
   * serviceClient = new RPCServiceClient(); Options options =
   * serviceClient.getOptions(); EndpointReference targetEPR = new
   * EndpointReference(EPR); options.setTo(targetEPR); // options.setProperty(); //
   * Setting the weather QName operation = new QName("http://qx.zws/xsd",
   * "executeQx"); QxContext strContext = new QxContext();
   * strContext.set("domain", "spinsci"); strContext.set("host",
   * "DesignState-node-0"); strContext.set("node", "0");
   * strContext.set("qx-service", "Ilink3Qx"); strContext.set("method", "find");
   * strContext.set("process_id", "888"); strContext.set(QxContext.OP_TYPE,
   * "find-client"); strContext.set(QxContext.REPOSITORY_NAME, "ilink-1");
   * String xmlInstrStr = "<?xml version='1.0' encoding='latin1'?>" + nl + "<qx
   * output-encoding='LATIN1'>" + nl + " <ilink-qx>" + nl + " <open-repository
   * username='designstate' password='zero0'>" + nl + " <find
   * name='1490_shield.prt' branch='main' revision='A' version='0'/>" + nl + "
   * </open-repository>" + nl + " </ilink-qx>" + nl + "</qx>" + nl; QxXML
   * xmlInstr = new QxXML(xmlInstrStr); Object[] opArgs = new Object[] {
   * strContext, xmlInstr }; Class[] returnTypes = new Class[] { QxXML.class };
   * Object[] response = serviceClient.invokeBlocking(operation, opArgs,
   * returnTypes); QxXML result = (QxXML) response[0];
   * {}//Logwriter.printOnConsole(result.toString()); }
   */

  public static OMElement getAttachmentPayload(String symbol) {
    OMFactory fac = OMAbstractFactory.getOMFactory();
    OMNamespace omNs = fac.createOMNamespace(Qx.WS_NAMESPACE, "zns");

    OMElement method = fac.createOMElement("sendQxAttachment", omNs);
    OMElement value = fac.createOMElement("symbol", omNs);
    value.addChild(fac.createOMText(value, symbol));
    method.addChild(value);
    return method;
  }

  public static void testGetAttachment(String str) throws Exception {
    OMElement payload = getAttachmentPayload(str);
    ServiceClient serviceClient = new ServiceClient();
    Options opts = new Options();
    opts.setTo(targetEPR);
    opts.setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
    serviceClient.setOptions(opts);

    OMElement attachResult = serviceClient.sendReceive(payload);
    OMElement ele = attachResult.getFirstElement();
    OMText binaryNode = (OMText) ele.getFirstOMChild();
    binaryNode.setBinary(true);

    // Retrieving the DataHandler & then do whatever the processing to the data
    DataHandler actualDH = (DataHandler) binaryNode.getDataHandler();

    File file = new File("C:\\return.jpg");

    FileOutputStream fileOutputStream = new FileOutputStream(file);
    actualDH.writeTo(fileOutputStream);
    // fileOutputStream.flush();
    // fileOutputStream.close();

    {}//Logwriter.printOnConsole("Attachment downloaded successfully");

  }

  private static EndpointReference targetEPR = new EndpointReference(
      "http://localhost:8080/axis2/services/QxWebService");

  /**
   * @param args
   */
  public static void main(String[] args) throws Exception {
    // TODO Auto-generated method stub
    // testQxService();
    testGetAttachment("ilink-zws-agile.jpg");
  }
  /*
   * QxContext strContext = new QxContext(); strContext.set("domain",
   * "spinsci"); strContext.set("host", "DesignState-node-0");
   * strContext.set("node", "0"); strContext.set("qx-service", "Ilink3Qx");
   * strContext.set("method", "find"); strContext.set("process_id", "888");
   * strContext.set(QxContext.OP_TYPE, "find-client");
   * strContext.set(QxContext.REPOSITORY_NAME, "ilink-1"); String xmlInstrStr = "<?xml
   * version='1.0' encoding='latin1'?>" + nl + "<qx output-encoding='LATIN1'>" +
   * nl + " <ilink-qx>" + nl + " <open-repository username='designstate'
   * password='zero0'>" + nl + " <find name='1490_shield.prt' branch='main'
   * revision='A' version='0'/>" + nl + " </open-repository>" + nl + "
   * </ilink-qx>" + nl + "</qx>" + nl; QxXML xmlInstr = new QxXML(xmlInstrStr);
   */

  private long timeoutInSeconds = 60*60*100; //100hrs
}
