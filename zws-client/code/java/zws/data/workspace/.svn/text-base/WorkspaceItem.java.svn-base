package zws.data.workspace; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Dec 15, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.origin.Origin;
import java.util.*;

public interface WorkspaceItem {
  public Origin getOrigin();
  public String getName();
  public String get(String property);
  public Map getAttributes();
  public String getState();
	public boolean isNew();
	public boolean isModified();
	public boolean isUnmodified();
	public boolean isLocked();
	public boolean hasConflicts();
		
  public String getLockOwner();
  public Collection getConflicts();

	public static String UNMODIFIED="unchanged";
	public static String MODIFIED="modified";
	public static String NEW="new";  
}
