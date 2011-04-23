package zws.event.hi.i2a;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 28, 2004, 2:42 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.event.hiProcessItemBase;


public class BroadcastPLMStatus extends hiProcessItemBase {
	public BroadcastPLMStatus() { setAction(BROADCAST_PLM_STATUS); }
	public BroadcastPLMStatus(String name) {
	  super();
	  setName(name);
	  setAction(BROADCAST_PLM_STATUS);
	}

  private static String BROADCAST_PLM_STATUS="broadcast-plm-status";
}
