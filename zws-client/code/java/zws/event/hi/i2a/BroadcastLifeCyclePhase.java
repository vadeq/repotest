package zws.event.hi.i2a;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 28, 2004, 2:42 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.event.hiProcessItemBase;


public class BroadcastLifeCyclePhase extends hiProcessItemBase {
	public BroadcastLifeCyclePhase() { setAction(BROADCAST_LIFE_CYCLE_PHASE); }
	public BroadcastLifeCyclePhase(String name) {
	  super();
	  setName(name);
	  setAction(BROADCAST_LIFE_CYCLE_PHASE);
	}

  private static String BROADCAST_LIFE_CYCLE_PHASE="broadcast-life-cycle-phase";
}
