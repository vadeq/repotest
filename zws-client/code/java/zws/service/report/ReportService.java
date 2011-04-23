package zws.service.report; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.report.Report;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Collection;

public interface ReportService extends Serializable {

/**
   * Returns the named {@link Report}.
   * <p>
   * @param  reportName name of the report to retrieve
   * @return a Report
   * @throws RemoteException if the specified report was not found
   */  
  public Report getReport(String name) throws RemoteException;

/**
   * Returns the all the Reports that have been configured and loaded.
   * <p>
   * @return a Collection of all Report objects configured on the server
   * @throws RemoteException for any error condition
   */  
  public Collection getReports() throws RemoteException;

}
