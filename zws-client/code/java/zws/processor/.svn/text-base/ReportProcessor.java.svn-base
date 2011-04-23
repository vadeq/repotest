package zws.processor;/*
DesignState - Design Compression Technology
@author athakur
@version: 1.0
Created on January 13, 2004, 2:00 PM
Copywrite (c) 2004 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.application.Names;
import zws.data.Metadata;
import zws.report.Report;
import zws.service.report.EJBLocator;
import zws.service.report.ReportServiceRemote;
import zws.util.DomainContext;

import java.util.*;

public class ReportProcessor extends BatchProcessor {

  protected void process(DomainContext ctx) throws Exception {
    setCurrentContext(ctx);
    if (Server.debugMode()) { 
      {} //System.out.println("ctx dump:");
      ctx.dump(System.out);
      {} //System.out.println("Current context dump: ");
      getCurrentContext().dump(System.out);
    }
/*
      report = new zws.report.ReportBase();
      Collection results = new Vector();
      Metadata dat = new Metadata();
      dat.setOrigin("DesignState-node1", "TVS_ENFORCER", "id");
      dat.setName("name.drw");
      results.add(dat);
      report.setResults(results);
*/  
   
    Report r=report;
    
    try { if (null==r) r = (Report)get("report"); }
    catch (Exception e) { r = retrieveReport(); }
    if (null==r) throw new Exception("No report specified");
    String criteria = (String)get("searchCriteria");
    if (null==criteria) throw new Exception ("No search criteria specified");
    r.setCriteria(criteria);
    r.generate();
    if (Server.debugMode()) {}{} //System.out.println(r.toString());
		Iterator i = r.getResults().iterator();
    Metadata data;
    Collection tempLog = new Vector();
    Collection errorLog = new Vector();
	  while(i.hasNext()){
		  data = (Metadata)i.next();
		  ctx.set(Names.CTX_METADATA, data);
      try { 
        processActions(ctx);
        tempLog.add("Processed " + data.getName());
      }
      catch(Exception e) {
        e.printStackTrace();
        errorLog.add("Error Processing " + data.getName());
      }
    }
    i = tempLog.iterator();
    while (i.hasNext()) {}{} //System.out.println(i.next());
    i = errorLog.iterator();
    while (i.hasNext()) {}{} //System.out.println(i.next());
  }

  private Report retrieveReport() throws Exception {
    String server = (String)get("reportingServer");
    String name = (String)get("reportName");
    if (null==server) throw new Exception ("No reporting server specified");
    if (null==name) throw new Exception("No report name specified");
		ReportServiceRemote service = EJBLocator.findService(server);
		return service.getReport(name);
  }
  	
	public Report getReport() { return report; }
	public void setReport(Report r) { report=r;}
  public String getReportingServer() { return reportingServer; }
  public void setReportingServer(String s) { reportingServer=s; }
  public String getCtxReportingServer() { return ctxReportingServer; }
  public void setCtxReportingServer(String s) { ctxReportingServer=s; }
  public String getCtxDefaultReportingServer() { return ctxDefaultReportingServer; }
  public void setCtxDefaultReportingServer(String s) { ctxDefaultReportingServer=s; }
  public String getReportName() { return reportName; }
  public void setReportName(String s) { reportName=s; }
  public String getCtxReportName() { return ctxReportName; }
  public void setCtxReportName(String s) { ctxReportName=s; }
  public String getCtxDefaultReportName() { return ctxDefaultReportName; }
  public void setCtxDefaultReportName(String s) { ctxDefaultReportName=s; }
  public String getSearchCriteria() { return searchCriteria; }
  public void setSearchCriteria(String s) { searchCriteria=s; }
  public String getCtxSearchCriteria() { return ctxSearchCriteria; }
  public void setCtxSearchCriteria(String s) { ctxSearchCriteria=s; }
  public String getCtxDefaultSearchCriteria() { return ctxDefaultSearchCriteria; }
  public void setCtxDefaultSearchCriteria(String s) { ctxDefaultSearchCriteria=s; }

  private Report report=null;
  private String ctxReport=null; //points to a report object stored in context
  private String ctxDefaultReport=null;
  
  private String reportingServer=null;
  private String ctxReportingServer=null;
  private String ctxDefaultReportingServer="reporting-server";
  private String reportName=null;
  private String ctxReportName=null;
  private String ctxDefaultReportName="report-name";
  private String searchCriteria=null;
  private String ctxSearchCriteria=null;
  private String ctxDefaultSearchCriteria="search-criteria";
}
