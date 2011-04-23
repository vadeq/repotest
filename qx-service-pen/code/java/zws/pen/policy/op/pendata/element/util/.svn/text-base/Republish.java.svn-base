package zws.pen.policy.op.pendata.element.util;
/*
DesignState - Design Compression Technology
@author: ptoleti
@version: 1.0
Created on Aug 16, 2007 11:40:55 AM
Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

import zws.origin.Origin;
import zws.pen.policy.op.pendata.PENDataOpBase;


public class Republish extends PENDataOpBase{
  public void execute() throws Exception {
    Origin o = lookupSrcMetadata(getCurrentItem()).getOrigin();
    getPenData().getOriginsToRepublish().add(o);
    getPenData().bindPulishingContext(o);
    getPenData().setRepublishing();
  }
}

