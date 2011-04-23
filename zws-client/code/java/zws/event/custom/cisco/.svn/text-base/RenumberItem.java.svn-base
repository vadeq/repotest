package zws.event.custom.cisco;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 28, 2004, 2:42 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

public class RenumberItem extends  CustomCiscoEventBase {
	public RenumberItem() { setAction(RENUMBER); }
	public RenumberItem(String name, String newName) {
	  super();
	  setName(name);
	  setNewName(newName);
	  setAction(RENUMBER);
	}

	public String getNewName() { return getString(NEW_NAME); }
	public void setNewName(String s) { set(NEW_NAME, s); }
	
	private String NEW_NAME="new-name";
  private static String RENUMBER="renumber-item";
}
