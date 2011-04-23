package zws.data.workspace; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Dec 15, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import java.util.*;

import zws.util.RoutedUserData;

public interface Workspace extends RoutedUserData {
  boolean hasItem(String name);
  WorkspaceItem getItem(String name);
  Map getItems();
  Collection getItemList();
  String getLocalPath();
  String getDescription();
  
	public boolean hasNewItems();
	public Collection getNewItems();
	public boolean hasModifiedItems();
	public Collection getModifiedItems();
	public boolean hasUnmodifiedItems();
	public Collection getUnmodifiedItems();
	public boolean hasConflicts();
	public Collection getConflicts();
}
