package zws.processor;/*
DesignState - Design Compression Technology
@author jyelizarov
@version: 1.0
Created on September 30, 2003, 8:49 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.Collection;
import java.util.Vector;

public abstract class ProcessorList extends ProcessorBase {
	public void add(Processor p) throws Exception {
		p.getContext().setParent(getContext());
		processors.add(p); 
	}

  protected Collection getProcessors() { return processors; }
  private Collection processors = new Vector();
}
