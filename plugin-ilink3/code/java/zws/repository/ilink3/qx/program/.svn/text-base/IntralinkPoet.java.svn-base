package zws.repository.ilink3.qx.program; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.op.ListOpBase;
import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.repository.ilink3.Ilink3RepositoryBase;
import zws.repository.ilink3.qx.client.op.xml.IlinkQxResponseHandler;
import zws.repository.ilink3.qx.client.op.xml.IntralinkResultHandler;
import zws.security.Authentication;
//import zws.util.{}//Logwriter;
import zws.util.Storable;
import zws.qx.program.QxInstruction;
import zws.qx.xml.QxXML;

import java.io.StringReader;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class IntralinkPoet extends ListOpBase {

  private String opType = "ilink-Qx";

  public void setOpType(String s) { opType=s; }

  protected String getOpType() {
    return opType;
  }

  public String getLDBPath() { return "C:\\zws\\env\\ptc\\" + getRepository().getEnvRoot() + "\\LDB";  }

  public String getUserLDBPath(String username) { return "/zws/data/space/workspace/"+username+"/" + getRepository().getEnvRoot() + "/LDB";  }

  protected void initExecution() throws Exception { }
  protected void finishExecution() throws Exception { }
  protected void createInstructionXML() { }

  IntralinkResultHandler xmlResultHandler = new IlinkQxResponseHandler();
  public IntralinkResultHandler getXMLResultHandler() { return  xmlResultHandler; }
  public void setXMLResultHandler(IntralinkResultHandler handler) { xmlResultHandler = handler; }

  protected boolean requiresLicense() { return true; }

  private QxInstruction programTree = null;

  public void setProgram(QxInstruction program) {
    programTree = program;
  }


  public void execute() throws Exception {
    try {
	    initExecution();
	    createInstructionXML();

      QxContext ctx = new QxContext();

      getRepository().getContext();
      //retrieve these variables from the parent context...
      ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.service");
      ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "Ilink3QxService");
      ctx.set(QxContext.SOAP_HOSTNAME, getRepository().getHostName());
      ctx.set(QxContext.SOAP_SERVICES_PATH, getRepository().getServicesPath());
      ctx.set(Ilink3RepositoryBase.ENV_ROOT, getRepository().getEnvRoot());

      /*
      ctx.set(QxContext.SOAP_HOSTNAME, "DesignState-0");
      ctx.set(QxContext.SOAP_SERVICES_PATH, "ZeroWait-State/services");
      */

      ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
      //ctx.set(QxContext.SOAP_SERVICE_OPERATION, "executeQx");
      ctx.set(QxContext.SOAP_SERVICE_OPERATION, "runQx");

      ctx.set(QxContext.REPOSITORY_NAME, getRepository().getRepositoryName());
      ctx.set(QxContext.OP_TYPE, getOpType());
      ctx.set(QxContext.ACQUIRE_LICENSE, "TRUE");

      ctx.set(Names.DOMAIN_NAME, getRepository().getDomainName());
      ctx.set(Names.SERVER_NAME, getRepository().getServerName());

      QxWebClient client = QxWebClient.materializeClient();
      {}//Logwriter.printOnConsole("sending instruction....");
      {}//Logwriter.printOnConsole(instruction);
	    QxXML response = client.executeQx(ctx, new QxXML(programTree));
      {}//Logwriter.printOnConsole("------------Got the following response...." + this.getClass().getName());
      {}//Logwriter.printOnConsole(""+response);
      {}//Logwriter.printOnConsole("------------Got the following response...." + this.getClass().getName());

	    handleResponse(response);
	    finishExecution();
    }
	  catch(Exception e) { throw e; }
  }

  public void setAuthentication(Authentication a) { authentication = a; }
  public Authentication getAuthentication() { return authentication; }
  public String getUsername() { return authentication.getUsername(); }
  public String getPassword() { return authentication.getPassword(); }

  public void setStorable(Storable s) { storable=s; }

  protected void handleResponse(QxXML response) throws Exception {
    if (null==response) {
      {}//Logwriter.printOnConsole("No Output Results.");
      return;
    }
    IntralinkResultHandler handler = getXMLResultHandler();
    if (null==handler) {
      {}//Logwriter.printOnConsole("No Result Handler Defined.");
      return;
    }
    XMLReader xr = getParser(false).getXMLReader();
    if (null==storable) {
      initializeStorage();
      storable=this;
    }
    storable.throwOnFailure(false);
    handler.setStorable(storable);
    handler.setRepository(ilink3);
    xr.setContentHandler(handler);
    xr.setErrorHandler(handler);

    xr.parse(new InputSource(new StringReader(response.toString())));
    storeResult(handler);
    if (handler.hasError()) handler.throwError();
   }

  protected void storeResult(IntralinkResultHandler handler) throws Exception {
    Collection c = handler.getResults();
    resetStorage();
    initializeStorage(c);
  }

  protected static SAXParser getParser(boolean validate) throws SAXException, ParserConfigurationException {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    factory.setValidating(validate);
    return factory.newSAXParser();
  }

  public Ilink3RepositoryBase getRepository() { return ilink3; }
  public void setRepository(Ilink3RepositoryBase client) { ilink3=client; }

  //protected QxXML instruction = new QxXML();
  private Ilink3RepositoryBase ilink3;
  private Storable storable=null;
  private Authentication authentication=null;
  //private String response=null;
  protected String endl = Names.NEW_LINE;
  //private int exitCode = -999;
}
