package zws.processor;/*
DesignState - Design Compression Technology
@author jyelizarov
@version: 1.0
Created on September 30, 2003, 8:49 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.op.Op;
import zws.op.util.Getter;
import zws.scheduler.Task;
import zws.util.DomainContext;

import java.util.*;

public abstract class ProcessorBase extends Task implements Processor{
	public void execute() throws Exception { process();}

  public void process() throws Exception {
		if(contextList == null || contextList.size() == 0) process(getContext());
		else{
			Iterator i = contextList.iterator();
			while(i.hasNext()) process((DomainContext)i.next());
		}
	}

  protected abstract void process(DomainContext ctx) throws Exception;  

  public void addRun(DomainContext dc){
		if(contextList == null) contextList = new Vector();
		dc.setParent(getContext());
		contextList.add(dc);
	}
	public Collection getContextList() { return contextList; }	
	public DomainContext getContext() { 
    if (null==super.getContext()) setContext(new DomainContext());
    return super.getContext();
  }
  
  private String capitalize(String s) { return s.substring(0,1).toUpperCase() + s.substring(1); }
  protected Object get(String property) throws Exception {
    String prop = capitalize(property);
    Object value = null;
    Getter getter = new Getter();
    getter.setObject(this);
    try {
      if (Server.debugMode()) System.out.print("looking for " + property+":");
      getter.setPropertyName(property);
      getter.execute();
      value = getter.getResult();
      if (Server.debugMode()) {} //System.out.println(value);
      if (null!=value) return value;
    }
    catch (Exception e) {}
    try {
      if (Server.debugMode()) System.out.print("looking for " + "ctx"+prop+":");
      getter.setPropertyName("ctx"+ prop);
      getter.execute();
      value = getter.getResult();
      if (Server.debugMode()) {} //System.out.println(value+"~");
      if (null!=value) value = getCurrentContext().get((String)value);
      if (Server.debugMode()) {} //System.out.println(value);
      if (null!=value) return value;
    }
    catch (Exception e) {}
    try {
      if (Server.debugMode()) System.out.print("looking for " + property+"Op:");
      getter.setPropertyName(property+"Op");
      getter.execute();
      Op op = (Op)getter.getResult();
      op.setContext(getCurrentContext());
      op.execute();
      value = op.getResult();
      if (Server.debugMode()) {} //System.out.println(value);
      if (null!=value) return value;
    }
    catch (Exception e) {}
    try {
      if (Server.debugMode()) System.out.print("looking for " + "ctxDefault" + prop+":");
      getter.setPropertyName("ctxDefault"+ prop);
      getter.execute();
      value = getter.getResult();
      if (Server.debugMode()) {} //System.out.println(value+"~");
      if (null!=value) value = getCurrentContext().get((String)value);
      if (Server.debugMode()) {} //System.out.println("Current Context");
      if (Server.debugMode()) getCurrentContext().dump(System.out);
      if (Server.debugMode()) {} //System.out.println(value);
      if (null!=value) return value;
    }
    catch (Exception e) {
      //e.printStackTrace();
    }
    throw new Exception("property [" + property + "] not specified for process " + getName());
  }

  protected DomainContext getCurrentContext() { return currentContext; }
  protected void setCurrentContext(DomainContext ctx) { currentContext=ctx; }
  
  private DomainContext currentContext=null;
	private Collection contextList = null;  
}
