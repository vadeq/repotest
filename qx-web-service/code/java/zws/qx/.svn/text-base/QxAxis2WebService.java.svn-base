package zws.qx;

import zws.qx.xml.QxXML;
import zws.security.Secret;
//impoer zws.util.Logwriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.stream.XMLStreamException;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axiom.om.OMText;

public class QxAxis2WebService extends QxWebService {

  private static Map clientSecrets = new HashMap();

  public String runQx(String clientID, String ctx, String dataInstruction) {
    {} //System.out.println("In WebService runQx method: ctx-> "+ ctx );
    {} //System.out.println("dataIns-> "+dataInstruction);
    {} //System.out.println("Receiving ClientID: "+ clientID);
   /*
    try { Configurator.load(); }
    catch (Exception e) {
      e.printStackTrace();
      response = new QxXML("<error reason='Could not load configuration' message='"+e.getMessage()+"'/>").toString();
      return response;
    }
    */
    if (clientID != null && clientID.equalsIgnoreCase("CLEAR_TEXT")) {
      QxContext qxCtx = new QxContext(ctx);
      QxXML qxXML = new QxXML(dataInstruction);
      QxXML respXML = executeQx(qxCtx, qxXML);
      return respXML.toString();
    } else {
      Secret secret = (Secret) clientSecrets.get(clientID);
      {} //System.out.println(" Client Secrets: "+clientSecrets);
      String context = secret.decrypt(ctx).trim();
      {} //System.out.println("Decrypted Context: "+ context);
      QxContext qxCtx = new QxContext(context);
      String dataInstr = secret.decrypt(dataInstruction).trim();
      {} //System.out.println("Decrypted dataInstr: "+ dataInstr);
      QxXML qxXML = new QxXML(dataInstr);
      QxXML respXML = executeQx(qxCtx, qxXML);
      return secret.encrypt(respXML.toString().trim());
    }
  }

  /**
   * Delivers an instruction and its execution context to a remote server.
   * @param ctx
   * An execution context.
   * @param XMLInstruction
   * The instruction to be run(represented in QxXML).
   * @return Response from the execution (also in QxXML).
   */

  public OMElement transferQx(OMElement reqElement) {
    {}//Logwriter.printOnConsole("start-> transferQx");
    QxContext ctx = null;
    QxXML dataInstruction = null;
    DataHandler binary = null;
    OMElement omResponse = null;
    QxTransfer qxTransferService = null;
    Secret secret = null;
    try {
      reqElement.build();
      reqElement.detach();

      String ctxStr = null;
      Iterator childIterator = reqElement.getChildElements();
      if (childIterator.hasNext()) {
        OMElement ctxElement = (OMElement) childIterator.next();
         ctxStr = ctxElement.getText();

      }
      String dataInstructionStr = null;
      if (childIterator.hasNext()) {
        OMElement dataInstrElement = (OMElement) childIterator.next();
        dataInstructionStr = dataInstrElement.getText();

      }
      String clientID = null;
      if( childIterator.hasNext() ) {
        OMElement clientIdElement = (OMElement) childIterator.next();
        clientID = clientIdElement.getText();
      }
      boolean encrypted = false;
      if (clientID != null && clientID.equalsIgnoreCase("CLEAR_TEXT")) {
        ctx = new QxContext(ctxStr);
        dataInstruction = new QxXML(dataInstructionStr);
      } else {
        encrypted = true;
        secret = (Secret) clientSecrets.get(clientID);
        {} //System.out.println(" Client Secrets: "+clientSecrets);
        String context = secret.decrypt(ctxStr).trim();
        {} //System.out.println("Decrypted Context: "+ context);
        ctx = new QxContext(context);
        String dataInstr = secret.decrypt(dataInstructionStr).trim();
        {} //System.out.println("Decrypted dataInstr: "+ dataInstr);
        dataInstruction = new QxXML(dataInstr);
      }
      OMFactory fac = OMAbstractFactory.getOMFactory();
      OMNamespace omNs = fac.createOMNamespace(ctx
          .lookupSOAPSchemaTargetNameSpace(), ctx.lookupSOAPNameSpacePrefix());
      omResponse = fac.createOMElement("transferQxResponse", omNs);

      {}//Logwriter.printOnConsole(""+ctx.toString());
      {}//Logwriter.printOnConsole(""+dataInstruction);

      {}//Logwriter.printOnConsole("Key Value "+ (String)ctx.get("domain"));

      String fqcn = ctx.lookupJavaFQCN();

      Class c =  Class.forName(fqcn);

      qxTransferService = (QxTransfer) c.newInstance();

      if (childIterator.hasNext()) {

        OMElement binaryElement = (OMElement) childIterator.next();
        OMText binaryNode = (OMText) binaryElement.getFirstOMChild();
        binaryNode.setBinary(true);

        // Retrieving the DataHandler & then do whatever the processing to the data
        if( encrypted ) {
          binary = (DataHandler) binaryNode.getDataHandler();
          InputStream is = binary.getDataSource().getInputStream();
          File decFile = new File("C:/dec_"+binary.getDataSource().getName());
          OutputStream os = new FileOutputStream(decFile);
          secret.decryptFile(is, os);

          DataHandler decBinary = new DataHandler(new FileDataSource(decFile));
          binary = decBinary;
          decFile.deleteOnExit();

        } else {
          binary = (DataHandler) binaryNode.getDataHandler();
        }
        QxXML xmlResponse = qxTransferService.transferQx(ctx, dataInstruction,
            binary);
        OMElement xmlInstrElement = fac.createOMElement("xml-response", omNs);
        if( encrypted ) {
          xmlInstrElement.addChild(fac.createOMText(secret.encrypt(xmlResponse.toString())));
        } else {
          xmlInstrElement.addChild(fac.createOMText(xmlResponse.toString()));
        }


        omResponse.addChild(xmlInstrElement);
      } else {
        // return sendBinary(new QxContext(ctxStr), new
        // QxXML(dataInstructionStr));
        DataHandler binaryResponse = null;
        binaryResponse = qxTransferService.transferQx(ctx, dataInstruction);

        OMElement respBinaryElement = fac.createOMElement("binary-response", omNs);

        if( encrypted ) {
          InputStream is = binaryResponse.getDataSource().getInputStream();
          //OutputStream os = binary.getDataSource().getOutputStream();
          OutputStream os = new FileOutputStream("C:/enc_"+binaryResponse.getDataSource().getName());
          secret.encryptFile(is, os);

          DataHandler encBinary = new DataHandler(new FileDataSource("C:/enc_"+binaryResponse.getDataSource().getName()));
          binaryResponse = encBinary;
        }


        OMText textData = fac.createOMText(binaryResponse, true);
        respBinaryElement.addChild(textData);

        omResponse.addChild(respBinaryElement);
      }
    } catch(RuntimeException ex ){
      {}//Logwriter.printOnConsole("RuntimeException"+ex.getMessage());
      ex.printStackTrace();
      // TODO set omResponse to display error messages.
    } catch(Exception ex ){
      {}//Logwriter.printOnConsole("Exception"+ex.getMessage());
      ex.printStackTrace();
      // TODO set omResponse to display error messages.
    }
    return omResponse;
  }

  /*
   * public OMElement sendQxAttachment(OMElement element) throws
   * XMLStreamException, IOException { element.build(); element.detach();
   * OMElement symbolElement = element.getFirstElement(); String symbol =
   * symbolElement.getText(); String returnFile = "test.jpg"; if(symbol !=
   * null){ returnFile = "C:\\"+symbol; } OMFactory fac =
   * OMAbstractFactory.getOMFactory(); OMNamespace omNs =
   * fac.createOMNamespace("http://qx.zws/xsd", "zns"); OMElement method =
   * fac.createOMElement("sendQxAttachmentResponse", omNs); OMElement
   * imageElement = fac.createOMElement("image", omNs); DataHandler dataHandler =
   * new DataHandler(new FileDataSource(returnFile)); // create an OMText node
   * with the above DataHandler and set optimized to true OMText textData =
   * fac.createOMText(dataHandler, true); //MTOM is enabled now.
   * imageElement.addChild(textData); method.addChild(imageElement); //we can
   * set optimized to false by using the following //textData.doOptimize(false);
   * return method; }
   */

  public OMElement sendQxAttachment(OMElement element)
  throws XMLStreamException, IOException {
     element.build(); element.detach();
    OMElement symbolElement = element.getFirstElement();
    String symbol = symbolElement.getText();

    String returnFile = "test.jpg";
    if(symbol != null){
        returnFile  = "C:\\"+symbol;
    }
    OMFactory fac = OMAbstractFactory.getOMFactory();
    OMNamespace omNs =
        fac.createOMNamespace(Qx.WS_NAMESPACE, "zns");
    OMElement method = fac.createOMElement("sendQxAttachmentResponse", omNs);

    OMElement imageElement = fac.createOMElement("image", omNs);


    DataHandler dataHandler = new DataHandler(new FileDataSource(returnFile));
//  create an OMText node with the above DataHandler and set optimized to true
    OMText textData = fac.createOMText(dataHandler, true); //MTOM is enabled now.


    imageElement.addChild(textData);
    method.addChild(imageElement);

    //we can set optimized to false by using the following
    //textData.doOptimize(false);
    return method;
  }

  public static void main(String[] a) throws Exception{

    QxAxis2WebService ws = new QxAxis2WebService();
    QxContext strContext = new QxContext();
    strContext.set("domain", "spinsci");
    strContext.set("host", "DesignState-node-0");
    strContext.set("node", "0");

    strContext.set("qx-service", "Ilink3Qx");
    strContext.set("method", "find");
    strContext.set("process_id", "888");

    strContext.set(QxContext.OP_TYPE, "find-client");
    strContext.set(QxContext.REPOSITORY_NAME, "ilink-1");

    String xmlInstrStr =
      "<?xml version='1.0' encoding='latin1'?>" +
      "<qx output-encoding='LATIN1'>"+
      " <ilink-qx>"+
      " <open-repository username='designstate' password='zero0'>" +
      "  <find name='1490_shield.prt' branch='main' revision='A' version='0'/>" +
      " </open-repository>" +
      " </ilink-qx>" +
      "</qx>" ;

    QxXML xmlInstr = new QxXML(xmlInstrStr);
    ws.executeQx(strContext, xmlInstr);
  }
}
