package zws.processor;/*
DesignState - Design Compression Technology
@author jyelizarov
@version: 1.0
Created on September 30, 2003, 8:49 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.DomainContext;
import zws.util.Named;

public interface Processor extends Named {
	public void process() throws Exception;
	public DomainContext getContext();
	public void setContext(DomainContext ctx);
	public void addRun(DomainContext ctx);
}
