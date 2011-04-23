package zws;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on September 30, 2003, 8:49 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.NameNotFound;
import zws.exception.ServerNotFound;
import zws.report.Report;
import zws.service.report.EJBLocator;
import zws.service.report.ReportServiceRemote;

import java.util.Collection;

public class Reporter {

/**
   * Returns the named {@link Report} that has been configured and loaded on the given server.
   * <p>
   * @param  serverName name of the server to retrieve the report from
   * @param  reportName name of the report to retrieve
   * @return a Report
   * @throws ServerNotFound if the specified serverName is not found
   * @throws NameNotFound if the specified report was not found on the specified server
   */  
  public static Report getReport(String serverName, String reportName) throws ServerNotFound, NameNotFound { 
    //Report report;
    ReportServiceRemote service;
    if (null==serverName) throw new ServerNotFound(serverName);
    if (null==reportName) throw new NameNotFound(reportName, "Reports");
    try { service = EJBLocator.findService(serverName); }
    catch (Exception e) { e.printStackTrace(); throw new ServerNotFound(serverName); }
    try { return service.getReport(reportName); }
    catch (Exception e) { e.printStackTrace(); throw new NameNotFound(reportName, "Reports"); }
  }

/**
   * Returns the all the Reports that have been configured and loaded on the given server.
   * <p>
   * @param  serverName name of the server to retrieve the reports from
   * @return a Collection of all Report objects configured on the server
   * @throws ServerNotFound if the specified serverName is not found
   * @throws Exception if an error occurres while retrieving the reports.
   */  
  public static Collection getAllReports(String serverName) throws Exception {
    //Collection reports;
    ReportServiceRemote service;
    if (null==serverName) throw new ServerNotFound(serverName);
    try { service = EJBLocator.findService(serverName); }
    catch (Exception e) { e.printStackTrace(); throw new ServerNotFound(serverName); }
    return service.getReports();
  }
}
