package zws.event.hi.i2a;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 28, 2004, 2:42 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.event.hiProcessItemBase;


public class UpdateWithNewPartNumber extends hiProcessItemBase {
	public UpdateWithNewPartNumber() { setAction(UPDATE_WITH_NEW_PART_NUMBER); }
	public UpdateWithNewPartNumber(String name) {
	  super();
	  setName(name);
	  setAction(UPDATE_WITH_NEW_PART_NUMBER);
	}

  private static String UPDATE_WITH_NEW_PART_NUMBER="update-with-new-part-number";
}
