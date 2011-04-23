package zws.data.workspace; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Dec 15, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.data.Metadata;
import zws.exception.CanNotMaterialize;
import zws.origin.*;
import zws.util.RoutedUserDataBase;
import java.util.*;
import java.io.Serializable;

public class WorkspaceBase extends RoutedUserDataBase implements Workspace, Serializable {
  public WorkspaceBase() {
    setDomainName(Server.getDomainName());
    setServerName(Server.getName());
  }
  public String getLocalPath() { return localPath; }
  public void setLocalPath(String s) { localPath=s; }
  
  public String getDescription() { return description; }
  public void setDescription(String s) { description=s; }

  public WorkspaceItem getItem(String name) { return (WorkspaceItem)items.get(name); }
  public boolean hasItem(String name) { return items.containsKey(name); }
  public Map getItems() { return items; }
  public Collection getItemList() { return items.values(); }
  public void add(Metadata metadata) throws CanNotMaterialize {
	  WorkspaceItemBase item = new WorkspaceItemBase();
	  item.setMetadata(metadata);
    String state = metadata.get("workspace-state");
    item.setState(state);    
    metadata.getAttributes().remove("workspace-state");
	  add(item);
  }

  public void add(WorkspaceItemBase item) {
	  WorkspaceOrigin origin = OriginMaker.materialize(this, item.getName(), 0);
	  item.define(origin);
	  items.put(item.getName(), item);
	}
 
	public boolean hasNewItems() {
	  WorkspaceItem item;
	  Iterator i = items.values().iterator();
	  while(i.hasNext()) {
	   item = (WorkspaceItem) i.next();
	   if (item.isNew()) return true;  
	  }
	  return false;
	}
	
	public Collection getNewItems() {
	  Collection list = new Vector();
	  WorkspaceItem item;
	  Iterator i = items.values().iterator();
		while(i.hasNext()) {
		 item = (WorkspaceItem) i.next();
		 if (item.isNew()) list.add(item);  
		}
	  return list;
	}
	
	public boolean hasModifiedItems() {
	  WorkspaceItem item;
	  Iterator i = items.values().iterator();
	  while(i.hasNext()) {
	    item = (WorkspaceItem) i.next();
		  if (item.isModified()) return true;  
		}
	  return false;
	}
	
	public Collection getModifiedItems() {
	  Collection list = new Vector();
	  WorkspaceItem item;
	  Iterator i = items.values().iterator();
		while(i.hasNext()) {
		 item = (WorkspaceItem) i.next();
		 if (item.isModified()) list.add(item);  
		}
	  return list;
	}
	
	public boolean hasUnmodifiedItems() {
    WorkspaceItem item;
	  Iterator i = items.values().iterator();
		while(i.hasNext()) {
		 item = (WorkspaceItem) i.next();
		 if (item.isUnmodified()) return true;  
		}
		return false;
  }
	
	public Collection getUnmodifiedItems() {
	  Collection list = new Vector();
	  WorkspaceItem item;
	  Iterator i = items.values().iterator();
		while(i.hasNext()) {
		 item = (WorkspaceItem) i.next();
		 if (item.isUnmodified()) list.add(item);  
		}
	  return list;
	}
	
	public boolean hasConflicts() {
	  WorkspaceItem item;
	  Iterator i = items.values().iterator();
		while(i.hasNext()) {
		 item = (WorkspaceItem) i.next();
		 if (item.hasConflicts()) return true;  
		}
	  return false;
	}

	public Collection getConflicts() {
	  Collection conflicts = new Vector();
	  WorkspaceItem item;
	  Iterator i = items.values().iterator();
		while(i.hasNext()) {
		 item = (WorkspaceItem) i.next();
		 if (item.hasConflicts()) conflicts.add(item);  
		}
	  return conflicts;
	}

  private Map items = new HashMap();
  
  private String localPath=null;
  private String description=null;
}
