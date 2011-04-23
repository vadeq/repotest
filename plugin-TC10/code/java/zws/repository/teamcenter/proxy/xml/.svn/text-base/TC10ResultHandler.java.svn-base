package zws.repository.teamcenter.proxy.xml;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 29, 2004, 3:36 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.application.s;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.event.EventMaker;
import zws.event.VersionedDesignEvent;
import zws.exception.zwsException;
import zws.origin.Origin;
import zws.origin.OriginMaker;
import zws.repository.teamcenter.proxy.TC10ProxyRepositoryBase;
import zws.repository.teamcenter.TC10Constants;
import zws.util.RoutedEventBase;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public abstract class TC10ResultHandler extends DefaultHandler {

  public Collection getResults(){ return results; }

  protected Metadata unmarshallComponent(Attributes atts, boolean generateOrigin) throws Exception {
    if (generateOrigin) return unmarshallComponent(atts);
    Metadata data = unmarshallMetadata(atts);
    return data;
  }

  protected Metadata unmarshallComponent(Attributes atts) throws Exception {
    MetadataBase data = (MetadataBase)unmarshallMetadata(atts);
    
    String timeOfCreation=data.get(TC10Constants.CREATED_ON);
    if (timeOfCreation==null) timeOfCreation=data.get(TC10Constants.TIME);
    if (timeOfCreation==null || "".equals(timeOfCreation)) data.set(TC10Constants.CREATED_ON, "1970.1.1.12.0.0");
    //String s = Server.getDomainName()+delim+Server.getName()+delim+getRepositoryType()+delim+getRepositoryName()+delim+tc10.parseDate(data.get(TC10Constants.CREATED_ON))+delim+data.get(TC10Constants.BRANCH)+delim+data.get(TC10Constants.REVISION)+delim+data.get(TC10Constants.VERSION)+delim+data.get(TC10Constants.NAME);
    //+++ todo create ItemRevOrigin correctly
    //???String s = tc10.getDomainName()+delim+tc10.getServerName()+delim+getRepositoryType()+delim+tc10.getType()+delim+
    //???tc10.parseDate(data.get(TC10Constants.CREATED_ON))+delim+data.get(TC10Constants.BRANCH)+delim+data.get(TC10Constants.REVISION)+delim+data.get(TC10Constants.VERSION)+delim+data.get(TC10Constants.NAME);
    {}//Logwriter.printOnConsole(" Origin "+ s);  // TBD configure in client side initial properties
    
    //+++ todo create ItemRevOrigin correctly
    //Origin o = OriginMaker.materialize(s);
    //data.setOrigin(o);
    String s = atts.getValue("origin");
    Origin o = OriginMaker.materialize(s);
    data.setOrigin(o);    
    return data;
  }

  protected Metadata unmarshallMetadata(Attributes atts) throws Exception {
    Metadata data = new MetadataBase();
    String key, value;
    for (int idx = 0; idx < atts.getLength(); idx++){
      key = atts.getQName(idx);
      value = atts.getValue(idx);
      if (key.equalsIgnoreCase(TC10Constants.NAME)) key = TC10Constants.NAME;
      else if (key.equalsIgnoreCase(TC10Constants.REVISION)) key = TC10Constants.REVISION;
      else if (key.equalsIgnoreCase(TC10Constants.RELEASE_LEVEL)) key = TC10Constants.RELEASE_LEVEL;
      else if (key.equalsIgnoreCase(TC10Constants.CREATED_ON)) key = TC10Constants.CREATED_ON;
      else if (key.equalsIgnoreCase(TC10Constants.CREATED_BY)) key = TC10Constants.CREATED_BY;
      data.set(key,value);
    }
    return data;
  }

  protected Metadata unmarshallFastName(Attributes atts) throws Exception {
    MetadataBase data = new MetadataBase();
    for (int idx = 0; idx < atts.getLength(); idx++){
      if (atts.getQName(idx).equalsIgnoreCase(TC10Constants.N)) data.setName(atts.getValue(idx));
      else if (atts.getQName(idx).equalsIgnoreCase(TC10Constants.NAME)) data.setName(atts.getValue(idx));
    }
    return data;
  }

  private static String DEMOTION_EVENT = "demotion";
  private static String PROMOTION_EVENT = "promotion";

  protected RoutedEventBase unmarshallEvent(Attributes atts) throws Exception {
    Map parameters = new HashMap();
    String type=null, key, value;
    String fqcnamespace=null;
    for (int idx = 0; idx < atts.getLength(); idx++){
      key = atts.getQName(idx);
      value = atts.getValue(idx);
      if (key.equalsIgnoreCase(TC10Constants.TYPE)) {
        if (PROMOTION_EVENT.equals(value))fqcnamespace="event.release";
        else if (DEMOTION_EVENT.equals(value))fqcnamespace="event.release";
        else fqcnamespace="event.design";
        type = fqcnamespace + s.dot + value;
        continue;
      }
      else if (key.equalsIgnoreCase(TC10Constants.NAME)) key = TC10Constants.NAME;
      else if (key.equalsIgnoreCase(TC10Constants.REVISION)) key = TC10Constants.REVISION;
      else if (key.equalsIgnoreCase(TC10Constants.RELEASE_LEVEL)) key = TC10Constants.RELEASE_LEVEL;
      else if (key.equalsIgnoreCase(TC10Constants.CREATED_ON)) key = TC10Constants.CREATED_ON;
      else if (key.equalsIgnoreCase(TC10Constants.CREATED_BY)) key = TC10Constants.CREATED_BY;
      parameters.put(key, value);
    }
    RoutedEventBase ev=null;
    if (null==type) EventMaker.materialize(parameters);
    else ev = EventMaker.materialize(type, parameters);
    ev.setDatasourceName(tc10.getRepositoryName());
    ev.setDatasourceType(Origin.FROM_TEAMCENTER_10);
    if (ev instanceof VersionedDesignEvent) {
      String timeOfCreation=(String)ev.get(TC10Constants.CREATED_ON);
      if (timeOfCreation==null || "".equals(timeOfCreation)) timeOfCreation = "1970.1.1.12.0.0";
      
      //+todo convert to TC origin...
      //String s = Server.getDomainName()+delim+Server.getName()+delim+Origin.FROM_TEAMCENTER_10+delim+tc10.getRepositoryName()+
      //delim+tc10.parseDate(timeOfCreation)+delim+ev.get(TC10Constants.BRANCH)+delim+ev.get(TC10Constants.REVISION)+delim+ver+delim+ev.get(TC10Constants.NAME);
      
      //+todo convert to TC origin...
      //Origin o = OriginMaker.materialize(s);
      //((VersionedDesignEventBase)ev).setOrigin(o);
    }
    return ev;
  }

  public boolean hasError() { return error!=null; }
  public void throwError() throws Exception { if (error!=null) throw error; }

  public void logStatus(Attributes atts) {
  }
  public void logWarning(Attributes atts) {
  }
  public void logError(Attributes atts) {
    if (null!=error) return; //already logged an error!
    zwsException e = new zwsException();
    for (int i = 0; i < atts.getLength(); e.define(atts.getQName(i), atts.getValue(i++)));
    error=e;
  }
  public void logPerformance(Attributes atts) {
  }

  //public String getRepositoryType() { return zws.origin.Origin.FROM_TEAMCENTER_10_REV; }
  public String getRepositoryName() { return getTC10Repository().getRepositoryName(); }

  public TC10ProxyRepositoryBase getTC10Repository() { return tc10; }
  public void setTC10Repository(TC10ProxyRepositoryBase client) { tc10=client; }

  private Collection results=new Vector();
  private TC10ProxyRepositoryBase tc10=null;

  private Exception error = null;

  private static String delim = TC10Constants.ORIGIN_DELIMITER;
}
