package zws.repository.ilink3.qx.client.op.xml;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on January 29, 2004, 3:36 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.application.s;
import zws.repository.ilink3.Ilink3Constants;
import zws.repository.ilink3.Ilink3RepositoryBase;
import zws.service.file.depot.FileDepotClient;
import zws.data.IlinkMetadata;
import zws.data.intralink.RTPForm;
import zws.data.workspace.*;
import zws.context.Context;
import zws.event.*;
import zws.ownership.*;
import zws.folder.IntralinkFolder;
import zws.exception.zwsException;
import zws.origin.*;
import zws.util.RemotePackage;
import zws.util.Storable;
import zws.util.RoutedEventBase;

import java.net.URL;
import java.util.*;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public abstract class IntralinkResultHandler extends DefaultHandler {
  public void configureParentContext(Context parent) { ctx.setParent(parent); }

  public Storable getStorable(){ return storable; }
  public void setStorable(Storable s) { storable=s; }
  public Collection getResults(){ return storable.getResults(); }

  protected IlinkMetadata unmarshallComponent(Attributes atts, boolean generateOrigin) throws Exception {
    if (generateOrigin) return unmarshallComponent(atts);
    IlinkMetadata data = unmarshallMetadata(atts);
    return data;
  }

  protected RemotePackage unmarshallRemoteFile(Attributes atts) throws Exception {
    RemotePackage rf = null;
    String location = null;
    String primaryName = null;
    URL url = null;
    String key, value;
    for (int idx = 0; idx < atts.getLength(); idx++){
      key = atts.getQName(idx);
      value = atts.getValue(idx);
      if (key.equalsIgnoreCase(Ilink3Constants.REMOTE_FILE_URL)) url = new URL(value);
      else if (key.equalsIgnoreCase(Ilink3Constants.REMOTE_FILE_INIQUE_ID)) location = value;
      else if (key.equalsIgnoreCase(Ilink3Constants.REMOTE_FILE_BINARY_NAME)) primaryName= value;      
    }
    RemotePackage r = FileDepotClient.materializeRemotePackage(url, primaryName, location);
    return r;
  }

  
  
  protected IlinkMetadata unmarshallComponent(Attributes atts) throws Exception {
    IlinkMetadata data = unmarshallMetadata(atts);
    String timeOfCreation=data.get(Ilink3Constants.CREATED_ON);
    if (timeOfCreation==null) timeOfCreation=data.get(Ilink3Constants.TIME);
    if (timeOfCreation==null || "".equals(timeOfCreation)) data.set(Ilink3Constants.CREATED_ON, "1970.1.1.12.0.0");
    //String s = Server.getDomainName()+delim+Server.getName()+delim+getRepositoryType()+delim+getRepositoryName()+delim+ilink3.parseDate(data.get(Ilink3Constants.CREATED_ON))+delim+data.get(Ilink3Constants.BRANCH)+delim+data.get(Ilink3Constants.REVISION)+delim+data.get(Ilink3Constants.VERSION)+delim+data.get(Ilink3Constants.NAME);
    String s = ilink3.getDomainName()+delim+ilink3.getServerName()+delim+getRepositoryType()+delim+ilink3.getType()+delim+ilink3.parseDate(data.get(Ilink3Constants.CREATED_ON))+delim+data.get(Ilink3Constants.BRANCH)+delim+data.get(Ilink3Constants.REVISION)+delim+data.get(Ilink3Constants.VERSION)+delim+data.get(Ilink3Constants.NAME);
    {}//Logwriter.printOnConsole(" Origin "+ s);  // TBD configure in client side initial properties
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
      if (key.equalsIgnoreCase(Ilink3Constants.NAME)) key = Ilink3Constants.NAME;
      else if (key.equalsIgnoreCase(Ilink3Constants.BRANCH)) key = Ilink3Constants.BRANCH;
      else if (key.equalsIgnoreCase(Ilink3Constants.REVISION)) key = Ilink3Constants.REVISION;
      else if (key.equalsIgnoreCase(Ilink3Constants.VERSION)) key = Ilink3Constants.VERSION;
      else if (key.equalsIgnoreCase(Ilink3Constants.RELEASE_LEVEL)) key = Ilink3Constants.RELEASE_LEVEL;
      else if (key.equalsIgnoreCase(Ilink3Constants.FOLDER)) key = Ilink3Constants.FOLDER;
      else if (key.equalsIgnoreCase(Ilink3Constants.CREATED_ON)) key = Ilink3Constants.CREATED_ON;
      else if (key.equalsIgnoreCase(Ilink3Constants.CREATED_BY)) key = Ilink3Constants.CREATED_BY;
      data.set(key,value);
    }
    return data;
  }

  protected IlinkMetadata unmarshallFastName(Attributes atts) throws Exception {
    IlinkMetadata data = new IlinkMetadata();
    for (int idx = 0; idx < atts.getLength(); idx++){
      if (atts.getQName(idx).equalsIgnoreCase(Ilink3Constants.N)) data.setName(atts.getValue(idx));
      else if (atts.getQName(idx).equalsIgnoreCase(Ilink3Constants.NAME)) data.setName(atts.getValue(idx));
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
      if (key.equalsIgnoreCase(Ilink3Constants.TYPE)) {
        if (PROMOTION_EVENT.equals(value))fqcnamespace="event.release";
        else if (DEMOTION_EVENT.equals(value))fqcnamespace="event.release";
        else fqcnamespace="event.design";
        type = fqcnamespace + s.dot + value;
        continue;
      }
      else if (key.equalsIgnoreCase(Ilink3Constants.NAME)) key = Ilink3Constants.NAME;
      else if (key.equalsIgnoreCase(Ilink3Constants.BRANCH)) key = Ilink3Constants.BRANCH;
      else if (key.equalsIgnoreCase(Ilink3Constants.REVISION)) key = Ilink3Constants.REVISION;
      else if (key.equalsIgnoreCase(Ilink3Constants.VERSION)) key = Ilink3Constants.VERSION;
      else if (key.equalsIgnoreCase(Ilink3Constants.RELEASE_LEVEL)) key = Ilink3Constants.RELEASE_LEVEL;
      else if (key.equalsIgnoreCase(Ilink3Constants.FOLDER)) key = Ilink3Constants.FOLDER;
      else if (key.equalsIgnoreCase(Ilink3Constants.CREATED_ON)) key = Ilink3Constants.CREATED_ON;
      else if (key.equalsIgnoreCase(Ilink3Constants.CREATED_BY)) key = Ilink3Constants.CREATED_BY;
      parameters.put(key, value);
    }
    RoutedEventBase ev=null;
    if (null==type) EventMaker.materialize(parameters);
    else ev = EventMaker.materialize(type, parameters);
    ev.setDatasourceName(ilink3.getRepositoryName());
    ev.setDatasourceType(Origin.FROM_ILINK);
    String ver = (String)ev.get(Ilink3Constants.VERSION);
    if (null==ver) ver = "-1";
    if (ev instanceof VersionedDesignEvent) {
      String timeOfCreation=(String)ev.get(Ilink3Constants.CREATED_ON);
      if (timeOfCreation==null || "".equals(timeOfCreation)) timeOfCreation = "1970.1.1.12.0.0";
      String s = Server.getDomainName()+delim+Server.getName()+delim+Origin.FROM_ILINK+delim+ilink3.getRepositoryName()+delim+ilink3.parseDate(timeOfCreation)+delim+ev.get(Ilink3Constants.BRANCH)+delim+ev.get(Ilink3Constants.REVISION)+delim+ver+delim+ev.get(Ilink3Constants.NAME);
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
      if (key.equalsIgnoreCase(Ilink3Constants.NAME)) f.setName(value);
      else if (key.equalsIgnoreCase(Ilink3Constants.LOCATION)) f.setParentPath(value);
      else if (key.equalsIgnoreCase(Ilink3Constants.DESCRIPTION)) f.setDescription(value);
      else if (key.equalsIgnoreCase(Ilink3Constants.FILE_SPACE)) f.setFileSpace(value);
      else if (key.equalsIgnoreCase(Ilink3Constants.RELEASE_SCHEME)) f.setReleaseScheme(value);
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
      if (key.equalsIgnoreCase(Ilink3Constants.WORKSPACE)) ws.setName(value);
      else if (key.equalsIgnoreCase(Ilink3Constants.PATH)) ws.setLocalPath(value);
      else if (key.equalsIgnoreCase(Ilink3Constants.DESCRIPTION)) ws.setDescription(value);
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
      if (key.equalsIgnoreCase(Ilink3Constants.NAME)) lock.setName(value);
      else if (key.equalsIgnoreCase(Ilink3Constants.OWNER)) lock.setOwner(value);
    }
    return lock;
  }

  protected WorkspaceConflictBase unmarshallWorkspaceConflict(Attributes atts) {
	  WorkspaceConflictBase c= new WorkspaceConflictBase();
	  String key, value;
	  for (int idx = 0; idx < atts.getLength(); idx++){
	    key = atts.getQName(idx);
	    value = atts.getValue(idx);
	    if (key.equalsIgnoreCase(Ilink3Constants.NAME)) c.setName(value);
	    else if (key.equalsIgnoreCase(Ilink3Constants.TYPE)) c.setConflictKey(value);
	    else c.set(key, value);
	  }
	  return c;
  }

  protected RTPForm unmarshallRTPForm(Attributes atts) throws Exception {
    RTPForm form = new RTPForm();
    form.setServerName(Server.getName());
    form.setDatasourceName(ilink3.getRepositoryName());
    form.setName(atts.getValue("Name"));
    form.setFolder(atts.getValue("Folder"));
    form.setDescription(atts.getValue("Description"));
    form.setDateCreated(ilink3.parseDate(atts.getValue("Time-Created")));
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

  public String getRepositoryType() { return zws.origin.Origin.FROM_ILINK; }
  //public String getRepositoryName() { return ctx.getString(ctx.REPOSITORY_NAME); }

  public Ilink3RepositoryBase getIlink3Svc() { return ilink3; }
  public void setRepository(Ilink3RepositoryBase client) { ilink3=client; }

  private Storable storable=null;
  private Context ctx = new Context();
  private Ilink3RepositoryBase ilink3=null;

  private Exception error = null;
  private Collection status = new Vector();
  private Collection warnings = new Vector();
  private Collection errors = new Vector();
  private Collection performance= new Vector();

  private static String delim = Ilink3Constants.ORIGIN_DELIMITER;
}
