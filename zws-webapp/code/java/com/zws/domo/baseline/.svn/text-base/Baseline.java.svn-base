package com.zws.domo.baseline;
import zws.application.Names;
import zws.origin.Origin;
import zws.origin.OriginMaker;

import com.zws.domo.BaseDomainObject;

import java.util.*;
/**
 * <p>Title: DesignState</p>
 * <p>Description: Design Compression Technology</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Zero Wait-State</p>
 * @author not attributable
 * @version 1.0
 */

public class Baseline extends BaseDomainObject{

  private String name = "";
  private String location = "";
  private boolean dirty = false;

  private HashMap fileEntries = new HashMap();

  public Baseline() {
  }

  public Baseline(String name, String location) {
    setName(name);
    setLocation(location);
  }

  public String getName(){
    return name;
  }

  public void setName(String name){
    this.name = name;
	setDirty(true);
  }

  public String getLocation(){
    return location;
  }

  public void setLocation(String location){
  	setDirty(true);
    this.location = location;
  }
  
  public void setDirty(boolean dirty){
  	this.dirty = dirty;
  }
  
  public boolean isDirty(){
  	return dirty;
  }

  public Collection getFiles(){
    return fileEntries.values();
  }

  public void addFile(Fileentry fileEntry){
       if(fileEntries.containsKey(fileEntry.getName())){
          Fileentry oldEntry = (Fileentry)fileEntries.get(fileEntry.getName());
          fileEntry.setPrimaryKey(oldEntry.getPrimaryKey());
          fileEntry.setChanged(true);
       }
		  setDirty(true);
       fileEntries.put(fileEntry.getName(), fileEntry);
  }

  public void removeFile(String name){
    fileEntries.remove(name);
	setDirty(true);
  }

  public void removeAllFiles(){
    fileEntries.clear();
	setDirty(true);
  }

  public Fileentry getFile(String name){
    if(fileEntries.containsKey(name))
      return (Fileentry)fileEntries.get(name);
    else
      return null;
  }

  public Collection getOrigins(String serverName, String sourceName) throws Exception {
   Collection c = new Vector();
   if (null==fileEntries) return c;
   Iterator i = fileEntries.values().iterator();
   Fileentry file;
   Origin o;
   String value;
   while(i.hasNext()) {
       file = (Fileentry)i.next();
       c.add(createOrigin(serverName, sourceName, file.getBranch(), file.getName(), file.getRevision(), file.getVersion(), (long)0));
   }
   return c;
  }
  
  private Origin createOrigin(String serverName, String sourceName, String branch, String name, String revision, String version, long createdOn) throws Exception {
    long dateCreated=0;
    String delim = Names.ORIGIN_DELIMITER;
    Origin o = OriginMaker.materialize(zws.Server.getDomainName(), serverName, Origin.FROM_ILINK, sourceName, createdOn, branch+delim+name+delim+revision+delim+version, name, null, null);
    //Origin o = new Origin(serverName, sourceName, Origin.ILINK, createdOn, branch+delim+name+delim+revision+delim+version);
    //o.setName(name);
    return o;
  }

  
  public void prune(){
 	Iterator setI = fileEntries.keySet().iterator();
 	while(setI.hasNext()){
 		String name = (String)setI.next();
		if(((Fileentry)fileEntries.get(name)).isDeleted())
			fileEntries.remove(name);
 	}
 
  }
}