package zws.data.workspace;/*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Dec 15, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.origin.*;
import java.util.*;
import java.io.Serializable;

public class WorkspaceItemBase implements WorkspaceItem, Serializable {
  public String getName() { return metadata.getName(); }
  public Origin getOrigin() { return origin; } //construct workspaceOrigin
  
	public void define(WorkspaceOrigin o) { origin = o; }
	public void setMetadata(Metadata m) { metadata=m; }
	
	public String get(String property) { return metadata.get(property);}
	public Map getAttributes() { return metadata.getAttributes();}

	public boolean isNew() { return (NEW.equalsIgnoreCase(state)); }
	public boolean isModified() { return (MODIFIED.equalsIgnoreCase(state)); }
	public boolean isUnmodified() { return (UNMODIFIED.equalsIgnoreCase(state)); }
	public boolean isLocked() {return (null!=owner); }
	public boolean hasConflicts() {return (null!=conflicts && conflicts.size()>0); }

	public String getState() { return state; }
	public void setState(String s) {state=s;}
	public String getLockOwner() {return owner; }
	public void lock(String lockedBy) { owner=lockedBy; }
	public void unlock() { owner=null; }
	public Collection getConflicts() { return conflicts; }
	public void add(WorkspaceConflict c) { 
	  if (null==conflicts) conflicts = new Vector();
	  conflicts.add(c);
	}

	private WorkspaceOrigin origin;
	protected Metadata metadata;
	private String state=UNMODIFIED;
	private String owner=null;
	private Collection conflicts=null;
}