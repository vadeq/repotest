package zws.processor;/*
DesignState - Design Compression Technology
@author jyelizarov
@version: 1.0
Created on September 30, 2003, 8:49 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.DomainContext;

import java.util.Iterator;

public class SerialProcessor extends ProcessorList {

	protected void process(DomainContext ctx) throws Exception {
		Iterator pI = getProcessors().iterator();
		while(pI.hasNext()){
			Processor p = (Processor)pI.next();
      p.getContext().setParent(ctx);
			p.process();
		}
	}
}
