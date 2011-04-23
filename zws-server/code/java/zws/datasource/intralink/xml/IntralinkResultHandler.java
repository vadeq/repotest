package zws.datasource.intralink.xml;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 29, 2004, 3:36 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.application.Names;
import zws.application.s;
import zws.data.IlinkMetadata;
import zws.data.intralink.RTPForm;
import zws.data.workspace.*;
import zws.event.*;
import zws.ownership.*;
import zws.folder.IntralinkFolder;
import zws.datasource.intralink.IntralinkSource;
import zws.exception.zwsException;
import zws.origin.*;
import zws.util.Storable;
import zws.util.RoutedEventBase;

import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public abstract class IntralinkResultHandler extends DefaultHandler {
  public Storable getStorable(){ return storable; }
  public void setStorable(Storable s) { storable=s; }
  public IntralinkSource getDatasource() { return datasource; }
  public void setDatasource(IntralinkSource s) { datasource=s; }
  public Collection getResults(){ return storable.getResults(); }

  protected IlinkMetadata unmarshallComponent(Attributes atts, boolean generateOrigin) throws Exception {
    if (generateOrigin) return unmarshallComponent(atts);
    IlinkMetadata data = unmarshallMetadata(atts);
    return data;
  }

  protected IlinkMetadata unmarshallComponent(Attributes atts) throws Exception {
    IlinkMetadata data = unmarshallMetadata(atts);
    String timeOfCreation=data.get(kCREATED_ON);
    if (timeOfCreation==null) timeOfCreation=data.get(kTIME);
    if (timeOfCreation==null || "".equals(timeOfCreation)) data.set(kCREATED_ON, "1970.1.1.12.0.0");
    String s = Server.getDomainName()+delim+Server.getName()+delim+getDatasource().getType()+delim+getDatasource().getName()+delim+getDatasource().parseDate(data.get(kCREATED_ON))+delim+data.get(kBRANCH)+delim+data.get(kREV)+delim+data.get(kVER)+delim+data.get(kNAME);
    IntralinkOrigin o =(IntralinkOrigin)OriginMaker.materialize(s);
    data.setOrigin(o);
    return data;
  }

  protected IlinkMetadata unmarshallMetadata(Attributes atts) throws Exception {
    IlinkMetadata data = new IlinkMetadata();
    String key, value;
    for (int idx = 0; idx < atts.getLength(); idx++){
      key = atts.getQName(idx);
      value = atts.getValue(idx);
      if (key.equalsIgnoreCase(kNAME)) key = kNAME;
      else if (key.equalsIgnoreCase(kBRANCH)) key = kBRANCH;
      else if (key.equalsIgnoreCase(kREV)) key = kREV;
      else if (key.equalsIgnoreCase(kVER)) key = kVER;
      else if (key.equalsIgnoreCase(kRELEASE_LEVEL)) key = kRELEASE_LEVEL;
      else if (key.equalsIgnoreCase(kFOLDER)) key = kFOLDER;
      else if (key.equalsIgnoreCase(kCREATED_ON)) key = kCREATED_ON;
      else if (key.equalsIgnoreCase(kCREATED_BY)) key = kCREATED_BY;
      data.set(key,value);
    }
    return data;
  }

  protected IlinkMetadata unmarshallFastName(Attributes atts) throws Exception {
    IlinkMetadata data = new IlinkMetadata();
    for (int idx = 0; idx < atts.getLength(); idx++){
      if (atts.getQName(idx).equalsIgnoreCase(kN)) data.setName(atts.getValue(idx));
      else if (atts.getQName(idx).equalsIgnoreCase(kNAME)) data.setName(atts.getValue(idx));
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
      if (key.equalsIgnoreCase(kTYPE)) {
        if (PROMOTION_EVENT.equals(value))fqcnamespace="event.release";
        else if (DEMOTION_EVENT.equals(value))fqcnamespace="event.release";
        else fqcnamespace="event.design";
        type = fqcnamespace + s.dot + value;
        continue;
      }
      else if (key.equalsIgnoreCase(kNAME)) key = kNAME;
      else if (key.equalsIgnoreCase(kBRANCH)) key = kBRANCH;
      else if (key.equalsIgnoreCase(kREV)) key = kREV;
      else if (key.equalsIgnoreCase(kVER)) key = kVER;
      else if (key.equalsIgnoreCase(kRELEASE_LEVEL)) key = kRELEASE_LEVEL;
      else if (key.equalsIgnoreCase(kFOLDER)) key = kFOLDER;
      else if (key.equalsIgnoreCase(kCREATED_ON)) key = kCREATED_ON;
      else if (key.equalsIgnoreCase(kCREATED_BY)) key = kCREATED_BY;
      parameters.put(key, value);
    }
    RoutedEventBase ev=null;
    if (null==type) EventMaker.materialize(parameters);
    else ev = EventMaker.materialize(type, parameters);
    ev.setDatasourceName(getDatasource().getName());
    ev.setDatasourceType(getDatasource().getType());
    String ver = (String)ev.get(kVER);
    if (null==ver) ver = "-1";
    if (ev instanceof VersionedDesignEvent) {
      String timeOfCreation=(String)ev.get(kCREATED_ON);
      if (timeOfCreation==null || "".equals(timeOfCreation)) timeOfCreation = "1970.1.1.12.0.0";
      String s = Server.getDomainName()+delim+Server.getName()+delim+getDatasource().getType()+delim+getDatasource().getName()+delim+getDatasource().parseDate(timeOfCreation)+delim+ev.get(kBRANCH)+delim+ev.get(kREV)+delim+ver+delim+ev.get(kNAME);
      IntralinkOrigin o =(IntralinkOrigin)OriginMaker.materialize(s);
      ((VersionedDesignEventBase)ev).setOrigin(o);
    }
    return ev;
  }

  protected IntralinkFolder unmarshallFolder(Attributes atts) throws Exception {
    IntralinkFolder f = new IntralinkFolder();
    String key, value;
    for (int idx = 0; idx < atts.getLength(); idx++){
      key = atts.getQName(idx);
      value = atts.getValue(idx);
      if (key.equalsIgnoreCase(kNAME)) f.setName(value);
      else if (key.equalsIgnoreCase(kLOCATION)) f.setParentPath(value);
      else if (key.equalsIgnoreCase(kDESCRIPTION)) f.setDescription(value);
      else if (key.equalsIgnoreCase(kFILE_SPACE)) f.setFileSpace(value);
      else if (key.equalsIgnoreCase(kRELEASE_SCHEME)) f.setReleaseScheme(value);
    }
    return f;
  }

  protected WorkspaceBase unmarshallWorkspace(Attributes atts, String datasourceName) throws Exception {
    WorkspaceBase ws= new WorkspaceBase();
    ws.setDatasourceName(datasourceName);
    String key, value;
    for (int idx = 0; idx < atts.getLength(); idx++){
      key = atts.getQName(idx);
      value = atts.getValue(idx);
      if (key.equalsIgnoreCase(kWORKSPACE)) ws.setName(value);
      else if (key.equalsIgnoreCase(kPATH)) ws.setLocalPath(value);
      else if (key.equalsIgnoreCase(kDESCRIPTION)) ws.setDescription(value);
    }
    return ws;
  }

  protected WorkspaceItemBase unmarshallWorkspaceItem(Workspace ws, Attributes atts) throws Exception {
    IlinkWorkspaceItem item = new IlinkWorkspaceItem();
    IlinkMetadata data= unmarshallMetadata(atts);
    String state = data.get("workspace-state");
    item.setMetadata(data);
    item.setState(state);    
    data.getAttributes().remove("workspace-state");
    //WorkspaceOrigin o = OriginMaker.materialize(ws, item.getName(), 0);
    //item.define(o);
    return item;
  }

  protected LockBase unmarshallLock(Attributes atts) {
    LockBase lock = new LockBase();
    String key, value;
    for (int idx = 0; idx < atts.getLength(); idx++){
      key = atts.getQName(idx);
      value = atts.getValue(idx);
      if (key.equalsIgnoreCase(kNAME)) lock.setName(value);
      else if (key.equalsIgnoreCase(kOWNER)) lock.setOwner(value);
    }
    return lock;
  }

  protected WorkspaceConflictBase unmarshallWorkspaceConflict(Attributes atts) {
	  WorkspaceConflictBase c= new WorkspaceConflictBase();
	  String key, value;
	  for (int idx = 0; idx < atts.getLength(); idx++){
	    key = atts.getQName(idx);
	    value = atts.getValue(idx);
	    if (key.equalsIgnoreCase(kNAME)) c.setName(value);
	    else if (key.equalsIgnoreCase(kTYPE)) c.setConflictKey(value);
	    else c.set(key, value);
	  }
	  return c;
  }
  
  protected RTPForm unmarshallRTPForm(Attributes atts) throws Exception {
    RTPForm form = new RTPForm();
    form.setServerName(Server.getName());
    form.setDatasourceName(datasource.getName());
    form.setName(atts.getValue("Name"));
    form.setFolder(atts.getValue("Folder"));
    form.setDescription(atts.getValue("Description"));
    form.setDateCreated(datasource.parseDate(atts.getValue("Time-Created")));
    return form;
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

  private Storable storable=null;
  private IntralinkSource datasource=null;
  private static String delim = Origin.delim;
  private static String kN = "n";
  private static String kNAME = Names.METADATA_NAME;
  private static String kFOLDER= Names.METADATA_FOLDER;
  private static String kRELEASE_LEVEL= Names.METADATA_RELEASE_LEVEL; 
  private static String kREV = Names.METADATA_REVISION;
  private static String kVER = Names.METADATA_VERSION;
  private static String kBRANCH = Names.METADATA_BRANCH;
  private static String kCREATED_ON = Names.METADATA_CREATED_ON;
  private static String kTIME = Names.METADATA_TIME;
  private static String kCREATED_BY = Names.METADATA_CREATED_BY;

  
  private static String kWORKSPACE= "workspace";
  private static String kPATH = "path";
  private static String kDESCRIPTION = "description";
  
  private static String kLOCATION = "location";
  private static String kFILE_SPACE = "file-space";
  private static String kRELEASE_SCHEME = "release-scheme";

  private static String kOWNER = "owner";
  private static String kTYPE= "type";
  
  private Exception error = null;
  private Collection status = new Vector();
  private Collection warnings = new Vector();
  private Collection errors = new Vector();
  private Collection performance= new Vector();
}
