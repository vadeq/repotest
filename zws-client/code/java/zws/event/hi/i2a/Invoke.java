package zws.event.hi.i2a;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 28, 2004, 2:42 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.event.hiEventBase;

public class Invoke extends hiEventBase {
	public String getName() { return getAction(); }
	public void setName(String s) { setAction(s); }
	public String getAction() { return getString(ACTION); }
	public void setAction(String s) { set(ACTION, s); }

	private static String ACTION="action";
}
