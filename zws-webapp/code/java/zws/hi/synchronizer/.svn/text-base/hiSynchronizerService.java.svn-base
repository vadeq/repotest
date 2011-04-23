package zws.hi.synchronizer;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on August 13, 2004, 11:00 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Synchronizer;
import zws.application.Properties;
import zws.application.server.webapp.Names;

import com.zws.hi.hiItem;

public class hiSynchronizerService extends hiItem {

  public hiSynchronizerService() { super(); }
  public void bind() throws Exception { }
  public void adapt(Object o) { }
  public void render() { }

  public String purgeName() {
    if (null==name || "".equals(name.trim())) return ctrlOK;
    try {
      synkService.purgeByName(domainName, serverName, datasourceName, name);
      //logFormStatus("msg.item.saved", getID());
      return ctrlOK;
    }
    catch (Exception e) {
      e.printStackTrace();
      logFormError(keyRUNTIME_ERROR);
      return ctrlSYSTEM_ERROR;
    }
  }

  public String purgeUID() {
    if (null==uid || "".equals(uid.trim())) return ctrlOK;
    try {
      synkService.purgeByUID(domainName, serverName, datasourceName, uid);
      //logFormStatus("msg.item.saved", getID());
      return ctrlOK;
    }
    catch (Exception e) {
      e.printStackTrace();
      logFormError(keyRUNTIME_ERROR);
      return ctrlSYSTEM_ERROR;
    }
  }

  private void populate() {}

  public String getName() { return name; }
  public void setName(String s) { name=s; }

  public String getUniqueID() { return uid; }
  public void setUniqueID(String s) { uid=s; }

  private String name=null;
  private String uid=null;
  private String domainName="zwait"; //umm - make this scaleable at some point..
  private String serverName="DesignState-node-0"; 
  private String datasourceName=Properties.get(Names.DEFAULT_DATASOURCE_NAME); 
  private Synchronizer synkService = Synchronizer.getClient(Properties.get(Names.CENTRAL_SERVER));
}
