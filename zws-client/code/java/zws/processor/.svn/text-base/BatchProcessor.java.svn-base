package zws.processor;/*
DesignState - Design Compression Technology
@author jyelizarov
@version: 1.0
Created on September 30, 2003, 8:49 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.action.Action;
import zws.util.*;

public class BatchProcessor extends ProcessorBase {
	protected void process(DomainContext ctx) throws Exception {		
    setCurrentContext(ctx);
		{} //System.out.println("BatchProcessor : process");
		processActions(ctx);
	}
	
	protected void processActions(DomainContext ctx) throws Exception{
		PrototypeIterator i = actions.prototypeIterator();
		while(i.hasNext()){
			Action action = (Action)i.copyNext();
//			{} //System.out.println("BatchProcessor : processing action" + action.getClass().getName());
	  	action.setContext(ctx);
		  action.execute();
    }
  }

	public void add(Action action){ actions.add(action); }

  private PrototypeCollection actions = new PrototypeVector(); 
}
