package zws.repository.teamcenter.qx.program; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.qx.Qx;
import zws.qx.QxContext;
import zws.qx.QxWebClient;
import zws.qx.xml.QxXML;
import zws.repository.teamcenter.proxy.TC10ProxyRepositoryBase;
//import zws.util.{}//Logwriter;

public class TC10Poet implements Qx {

  private TC10Poet(TC10ProxyRepositoryBase rep) {setRepository(rep); }

  public static TC10Poet materializePoet(TC10ProxyRepositoryBase rep) {
    TC10Poet p = new TC10Poet(rep);
    return p;
  }
 
  public QxXML executeQx(QxContext ctx, QxXML dataInstruction) {
    Object token = null;
    QxXML response = null;
    try {
      getRepository().getContext();
      //retrieve these variables from the parent context...
      ctx.set(QxContext.JAVA_SERVICES_PACKAGE, "zws.qx.service");
      ctx.set(QxContext.JAVA_SERVICE_CLASSNAME, "TC10QxWebService");
      ctx.set(QxContext.SOAP_HOSTNAME, getRepository().getHostName());
      ctx.set(QxContext.SOAP_SERVICES_PATH, getRepository().getServicesPath());
      ctx.set(QxContext.SOAP_SERVICE_NAME, "QxWebService");
      ctx.set(QxContext.SOAP_SERVICE_OPERATION, "runQx");
      ctx.set(QxContext.REPOSITORY_NAME, getRepository().getRepositoryName());
      ctx.set(Names.DOMAIN_NAME, getRepository().getDomainName());
      ctx.set(Names.SERVER_NAME, getRepository().getServerName());

      QxWebClient client = QxWebClient.materializeClient();
      {}//Logwriter.printOnConsole("sending instruction....");
      {}//Logwriter.printOnConsole(instruction);
	    response = client.executeQx(ctx, dataInstruction);
      {}//Logwriter.printOnConsole("------------Got the following response...." + this.getClass().getName());
      {}//Logwriter.printOnConsole(""+response);
      {}//Logwriter.printOnConsole("------------Got the following response...." + this.getClass().getName());
    }
	  catch(Exception e) {
      e.printStackTrace();
      response = new QxXML("<exception message='"+e.getMessage()+"'/>");
    }
    return response;
  }

  public TC10ProxyRepositoryBase getRepository() { return tc10; }
  public void setRepository(TC10ProxyRepositoryBase client) { tc10=client; }

  //protected QxXML instruction = new QxXML();
  private TC10ProxyRepositoryBase tc10;
}
