package zws.agile.px.apc;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur, Jason Brown @agile.com
Created on Mar 3, 2006
@version: 1.0
Copywrite (c) 2006 Zero Wait-State Inc. All rights reserved */

import zws.agile.px.pxActionBase;

import com.agile.px.*;
import com.agile.api.*;

public class StartAPC extends pxActionBase {

	public ActionResult doAction(IAgileSession session, INode actionNode, IDataObject affectedObject) {
		try {
	    initializePX();
  	  String user = session.getCurrentUser().getName();
		  String number = affectedObject.getName();
		  String result = launchAPC(user, number);
		  return new ActionResult(ActionResult.STRING, result); 
		}
		catch (Throwable th) { return new ActionResult(ActionResult.EXCEPTION, th); }
	}

	public String launchAPC(String author, String number) throws Exception{
    String urlPath = getServiceURLPath(DESIGNSTATE_APC_SERVICE_URL, URL_ACTION_START_APC);
    urlPath = addURLParameter(urlPath, URL_PARAMETER_NUMBER, number);
    urlPath = addURLParameter(urlPath, URL_PARAMETER_AUTHOR, author);
    String response = invokeService(urlPath);
    return response;
	}

	public void runDoAction (String number) {
 	  IAgileSession session=null;
		try {
			session = startAgileSession();
			IItem theItem = (IItem)session.getObject(ItemConstants.CLASS_PART, number);
			ActionResult result = doAction(session, null, theItem);
			debug("result: " + result.toString());
		}
		catch (APIException apiEx) { {} //System.out.println("runDoAction apiEx: " + apiEx.getMessage()); }
		  
		}
		catch (Exception e) { {} //System.out.println("runDoAction exception: " + e.getMessage()); }
		  
		}
		finally { disconnect(session); }
	}

	public static void main(String[] args) {
		try {
			String num = args[0];
			StartAPC apc = new StartAPC();
			apc.setDebugModeOn();
			apc.runDoAction(num);
		} 
	  catch (Exception e) { e.printStackTrace(); } 
  }

  //URL actions
  protected static String URL_ACTION_START_APC = "startAPC";

  public ActionResult doAction() {
    // TODO Auto-generated method stub
    return null;
  }
}