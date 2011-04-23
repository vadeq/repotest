package zws.action.partner.SandS;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 30, 2004, 3:34 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.IntralinkAccess;
import zws.action.ActionBase;
import zws.data.Metadata;
import zws.data.intralink.RTPForm;
import zws.origin.IntralinkOrigin;
import zws.origin.Origin;
import zws.security.Authentication;
import zws.util.KeyValue;

import com.zws.domo.baseline.Baseline;
import com.zws.domo.baseline.Fileentry;
import com.zws.exception.EntryNotFound;
//import com.zws.service.Database;
import com.zws.service.baseline.BaselineService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

public class CreatePromotionBaselines extends ActionBase{

  public void execute () throws Exception {
    initializeDatasource();
    String server = getRequiredString("serverName");
    String source = getRequiredString("datasourceName");
    String days = getString("dayInterval");
    String hours = getString("hourInterval");
    String date = getString("datedAfter");
    String level = getRequiredString("promotedTo");
    String datedAfter;
    if(null==days && null==hours ) datedAfter = date;
    else datedAfter = calculateDate(days, hours);    
    Collection forms = IntralinkAccess.getAccess().findRTPForms(server, source, datedAfter, level, null);
    if (null==forms || 0==forms.size()) return;
    Iterator i = forms.iterator();
    RTPForm form;
    String location = getString("baselineFolderLocation");
    String eco;
    Baseline baseline=null;
    while (i.hasNext()) {
     baseline=null; 
     form = (RTPForm)i.next();
     {} //System.out.println(form);
     eco = lookupECO(form.getName());
     if (null==eco) continue;
     try { baseline = BaselineService.getService().find(eco); }
     catch (EntryNotFound e) {} //ignore
     Authentication id = new Authentication(username, password);
     if (null==baseline) BaselineService.getService().save(createBaseline(eco, form), id);
     else BaselineService.getService().merge(createBaseline(eco, form), id);
    }
  }

  private Connection dbConnection() throws Exception {
    String url = "jdbc:mysql://"+getHost()+":"+getPort()+"/"+(String)zws.application.Properties.get("DesignState-database");
    if (null!=getUsername()) url += "?user="+getUsername();
    if (null!=getPassword()) url += ";password="+getPassword();
    driver = Class.forName(getDriverFQCN());
    return DriverManager.getConnection(url);
  }
 private void initializeDatasource() throws Exception {
  String sourceName=(String)zws.application.Properties.get("DesignState-database");
  //Database.release(sourceName, dbConnection()); //pool a couple of connections
  //Database.release(sourceName, dbConnection()); 
  //hack -  uses new DB to get connection and pool it into old Database
  // so that BaselineService has an available connection
 }
  
  private String lookupECO(String formName) throws Exception {
   String line = null; 
   BufferedReader br = new BufferedReader(new FileReader(ecoRTPMappingFile));
   KeyValue pair, match=null;
   while ((line = br.readLine()) != null) {
       pair = parseECORTP(line);
       if (pair.getValue().toString().equals(formName.trim())) match=pair;
   }
   br.close();                 
   br = null;
   if (null==match) return null;
   return match.getKey();
  }

  private KeyValue parseECORTP(String line) {
   StringTokenizer tokens = new StringTokenizer(line, ",");
   if (2!=tokens.countTokens()) return null;
   KeyValue pair = new KeyValue();
   pair.setKey(tokens.nextToken().trim());
   pair.setValue(tokens.nextToken().trim());
   return pair;
  }
  
  private Baseline createBaseline(String eco, RTPForm form) throws Exception {
    if (null==form.getComponents()) return null;
    Baseline baseline = new Baseline();
    baseline.setName(eco);
    baseline.setLocation(baselineFolderLocation);
    Iterator i = form.getComponents().iterator();
    Origin o;
    while(i.hasNext())addFile(baseline, ((Metadata)i.next()).getOrigin());
    return baseline;
  }
  
  private void addFile(Baseline b, Origin o) throws Exception {
     IntralinkOrigin origin = (IntralinkOrigin)o;
     Fileentry f = new Fileentry();
     f.setName(origin.getName());
     f.setBranch(origin.getBranch());
     f.setRevision(origin.getRevision());
     f.setVersion(""+origin.getVersion());
     b.addFile(f);
  }

  private static int idx=0;
  private static synchronized int getCount(){ return idx++; } 
  private String calculateDate(String iDays, String iHours){  	
    int iIDays=0, iIHours=0;
    if(iDays != null && iDays.trim().length() > 0) iIDays = Integer.parseInt(iDays);
    if(iHours != null && iHours.trim().length() > 0) iIHours = Integer.parseInt(iHours);
 	  String retdate = null;
    Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_MONTH, -iIDays);
		cal.add(Calendar. HOUR_OF_DAY, -iIHours);
  	//10.06.2003.11:32
  	retdate = cal.get(Calendar.MONTH)+ 1 + "." + cal.get(Calendar.DAY_OF_MONTH) + "." + cal.get(Calendar.YEAR) + "." + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
    return retdate;
  }
		  
  public String getServerName() { return serverName; }
  public void setServerName(String s) { serverName=s; }
  public String getDatasourceName() { return datasourceName; }
  public void setDatasourceName(String s) { datasourceName=s; }
  public String getDatedAfter() { return datedAfter; }
  public void setDatedAfter(String s) { datedAfter = s; } 
  public String getDayInterval() { return dayInterval; }
  public void setDayInterval(String s) { dayInterval = s; }
  public String getHourInterval() { return hourInterval; }
  public void setHourInterval(String s) { hourInterval = s; }
  public String getPromotedTo() { return promotedTo; }
  public void setPromotedTo(String s) { promotedTo = s; }
  public String getBaselineFolderLocation() { return baselineFolderLocation; }
  public void setBaselineFolderLocation(String s) { baselineFolderLocation=s; }
  public String getECORTPMappingFile() { return ecoRTPMappingFile; }
  public void setECORTPMappingFile(String s) { ecoRTPMappingFile = s; }
  
  public String getUsername() { return username; }
  public void setUsername(String s) { username=s; }
  public String getPassword() { return password; }
  public void setPassword(String s) { password=s; }
  
  private String serverName=null;
  private String datasourceName=null;

  private String username=null;
  private String password=null;
  private String ecoRTPMappingFile;
  
  private String datedAfter=null;
  private String dayInterval=null;
  private String hourInterval=null;
  private String promotedTo=null;
  
  private String baselineFolderLocation=null;

  public String getHost() { return host; }
  public void setHost(String s) { host=s; }
  public String getDriverFQCN() { return driverFQCN; }
  public void setDriverFQCN(String fqcn) { driverFQCN = fqcn; }
  public String getPort() { return port; }
  public void setPort(String s) { port=s; }
   
  transient private Class driver=null;
  private String driverFQCN="org.gjt.mm.mysql.Driver";
  private String port="3306";
  private String host="localhost";
}
