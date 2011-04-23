package zws.event.hi.i2a;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 28, 2004, 2:42 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.event.hiProcessItemBase;


public class PromoteItem extends hiProcessItemBase{
 	public PromoteItem(String name) {
 	  super();
 	  setName(name);
 	  setAction(PROMOTE);
 	}
	public PromoteItem() { setAction(PROMOTE); }
	  
  private static String PROMOTE="promote";
}
