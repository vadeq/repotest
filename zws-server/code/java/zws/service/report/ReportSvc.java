package zws.service.report; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.application.Properties;
import zws.data.transformer.metadata.MetadataRemappingSpec;
import zws.exception.*;
import zws.report.Report;
import zws.report.ReportBase;
import zws.search.SearchAgent;
import zws.service.PrototypeSvc;
import zws.service.search.SearchAgentSvc;
import zws.service.transformer.TransformerSvc;
import zws.util.*;

import java.util.*;

public class ReportSvc  {
  public static String NAMESPACE = "zws-report-service";
  public static String getNamespace() { return NAMESPACE; }
  public static Report find(String name) throws NameNotFound, InitializationError, Exception { 
      ReportBase r = (ReportBase)PrototypeSvc.lookup(NAMESPACE, name); 
      initialize(r);
      return r;
  }

  public static Collection getPrototypeNames() { return PrototypeSvc.getPrototypeNames(NAMESPACE); }
  public static PrototypeCollection findAll() throws InitializationError { 
    PrototypeCollection c = new PrototypeVector();
    Iterator i = PrototypeSvc.findAll(NAMESPACE).iterator();
    Report r;
    while (i.hasNext()) {
      r = (Report)i.next();
      r.initialize();
      c.add(r);
    }
    return c;
  }
  public static void add(Named op) throws DuplicateName{ PrototypeSvc.add(NAMESPACE, op); }
  public static void update(Named op) { remove(op.getName()); try{add(op);} catch (Exception a) {} }
  public static void remove(Named op) { remove(op.getName()); }
  public static void remove(String name) { PrototypeSvc.remove(NAMESPACE, name); }
  public static void unload() { PrototypeSvc.unload(NAMESPACE); }
   
  private static void initialize(ReportBase report) throws Exception {
    //if (null==report.getAgents()) {}//Logwriter.printOnConsole("getAgents is null");
    {}//Logwriter.printOnConsole("agent size = " + report.getSearchAgents().size());
//    if (null==report.getAgents() && 0 == report.getSearchAgents().size()) return;
    if(null!=report.getResults()) report.getResults().clear();
    report.clearLogs();
    report.getFailures();
    StringTokenizer tokens = new StringTokenizer(report.getAgents(), Names.DELIMITER);
    while (tokens.hasMoreTokens()) addAgent(report, tokens.nextToken().trim());
  }
  private static void addAgent(ReportBase report, String spec) throws Exception {
	  SearchAgent agent=null;
	  MetadataRemappingSpec mapping=null;
	  StringTokenizer tokens = new StringTokenizer(spec, Properties.get(Names.DELIMITER_REPORT_AGENT_MAPPING));
	  if (tokens.hasMoreTokens()) agent = SearchAgentSvc.find(tokens.nextToken().trim());
	  else throw new InitializationError("No Agent specified: '"+spec+"'");
	  if (tokens.hasMoreTokens()) mapping = (MetadataRemappingSpec)TransformerSvc.find(tokens.nextToken().trim());
	  else throw new InitializationError("Report ["+report.getName()+"]: No mapping specified: " + spec);
	  report.validate(mapping);
	  report.getAgentMappings().put(agent.getName(), mapping);
	  TransformerCollection set=null;
	  if (null!=agent.getSortComparator()) set = new TransformerTreeSet(agent.getSortComparator());
	  else set = new TransformerVector();
	  set.setTransformer(mapping);
	  agent.resetStorage();
	  agent.initializeStorage(set);
	  report.add(agent); 
  }
}