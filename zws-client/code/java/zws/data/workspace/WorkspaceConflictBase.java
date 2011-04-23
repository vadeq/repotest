package zws.data.workspace; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Dec 15, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.util.RoutedUserDataBase;

import java.util.*;
import java.io.Serializable; 

public class WorkspaceConflictBase extends RoutedUserDataBase implements WorkspaceConflict, Serializable {
	public String getConflictKey() { return key; }
	public void  setConflictKey(String s) { key=s; }
	public Map getParameters() { return parameters; }
	public void set(String parameter, String value) { parameters.put(parameter, value); }
	public String get(String parameter) { return (String)parameters.get(parameter); }
	
	private String key;
	private Map parameters = new HashMap();
}
