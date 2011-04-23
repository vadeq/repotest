package zws.agile.px;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
Created on Mar 3, 2006
@version: 1.0
Copywrite (c) 2006 Zero Wait-State Inc. All rights reserved */

import com.agile.px.*;
import com.agile.api.*;

public class FireAgileEvent extends  pxActionBase {
	public ActionResult doAction(IAgileSession session, INode actionNode, IDataObject affectedObject) {
		try {
		  initializePX();
		  debug("starting action");
		  zws.util.RoutedEventBase ev = materializeEvent(session, actionNode, affectedObject);
		  String response = fire(ev);
		  debug("response: " + response);
		  return new ActionResult(ActionResult.STRING, response);
		}
		catch (Exception e) {
		  debug(e);
			return new ActionResult(ActionResult.EXCEPTION, e); 
	  }
		catch (Throwable th) {
		  debug(th);
		  return new ActionResult(ActionResult.EXCEPTION, th); 
		}
	}
}