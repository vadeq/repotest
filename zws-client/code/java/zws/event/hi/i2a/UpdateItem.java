package zws.event.hi.i2a;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 28, 2004, 2:42 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.event.hiProcessItemBase;


public class UpdateItem extends hiProcessItemBase {
	public UpdateItem() { setAction(UPDATE); }
	public UpdateItem(String name) {
	  super();
	  setName(name);
	  setAction(UPDATE);
	}

  private static String UPDATE="update";
}
