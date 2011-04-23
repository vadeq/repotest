package zws.service.report; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.application.Configurator;
import zws.exception.InitializationError;
import zws.exception.NameNotFound;
import zws.report.Report;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class ReportServiceEJB implements SessionBean, ReportService {

  /**
   * Returns the named {@link Report}.
   * <p>
   * @param  reportName name of the report to retrieve
   * @return a Report
   * @throws RemoteException if the specified report was not found
   */  
  public Report getReport(String name) throws RemoteException {
    try{ 
      Report report = ReportSvc.find(name);
      {}//Logwriter.printOnConsole(report);
      return report;
    }
    catch (NameNotFound a) { throw new RemoteException(Server.getName(), a); }
    catch (InitializationError a) { throw new RemoteException("InitializationError", a); }
    catch (Exception a) { 
      a.printStackTrace();
      throw new RemoteException("", a); 
    }
  }
  /*
  private void checkSerializability(Report report) {
    zws.report.ReportBase r = (zws.report.ReportBase)report;
    {}//Logwriter.printOnConsole("AgentMapping Values");
    checkSerializability(r.getAgentMappings());  
    {}//Logwriter.printOnConsole("MetadataFieldsMap  Values");
    checkSerializability(r.getMetadataFieldsMap());  
    {}//Logwriter.printOnConsole("SearchAgent Values");
    checkSerializability(r.getSearchAgents());  
  }
  private void checkSerializability(java.util.Map m) {
    if (null==m) return;
    java.util.Iterator i = m.values().iterator();
    while (i.hasNext()) {
      Object o = i.next();
      if (o instanceof java.io.Serializable) {}//Logwriter.printOnConsole("  " + o.getClass() + " is Serializable");
      else {}//Logwriter.printOnConsole("  " + o.getClass() + " is not Serializable !!!!!!!!!!!!!");
    }
  }
*/
  
  /**
   * Returns the all the Reports that have been configured and loaded.
   * <p>
   * @return a Collection of all Report objects configured on the server
   * @throws RemoteException for any error condition
   */  
  public Collection getReports() throws RemoteException { 
    try { return ReportSvc.findAll(); }
    catch (InitializationError e){ throw new RemoteException("InitializationError", e); }
  }

  public void ejbCreate () { try {Configurator.load();} catch (Exception e) { e.printStackTrace(); } }
  public void ejbRemove() {}
  public void ejbPassivate() {}
  public void ejbActivate() {}
  public void setSessionContext(SessionContext ctx) {}
}
