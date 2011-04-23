package com.zws.px;

//Copyright © 1995-2003 Agile Software Corporation, 6373 San Ignacio Avenue,
//San Jose, California 95119-1200 U.S.A.; Telephone 408.284.4000, Facsimile
//408.284.4002, or <http://www.agile.com/>. All rights reserved.
//
//This file, which has been provided by Agile Software Corporation as part of
//an Agile Software« product for use ONLY by licensed users of the product,
//includes CONFIDENTIAL and PROPRIETARY information of Agile Software
//Corporation.
//
//USE OF THIS SOFTWARE IS GOVERNED BY THE TERMS AND CONDITIONS OF THE LICENSE
//AGREEMENT AND LIMITED WARRANTY FURNISHED WITH THE PRODUCT.
//
//IN PARTICULAR, YOU WILL INDEMNIFY AND HOLD AGILE SOFTWARE CORPORATION, ITS
//RELATED COMPANIES AND ITS SUPPLIERS, HARMLESS FROM AND AGAINST ANY CLAIMS
//OR LIABILITIES ARISING OUT OF THE USE, REPRODUCTION, OR DISTRIBUTION OF
//YOUR PROGRAMS, INCLUDING ANY CLAIMS OR LIABILITIES ARISING OUT OF OR
//RESULTING FROM THE USE, MODIFICATION, OR DISTRIBUTION OF PROGRAMS OR FILES
//CREATED FROM, BASED ON, AND/OR DERIVED FROM THIS SAMPLE SOURCE CODE FILE.
//===========================================================================

import java.net.URL;
import java.net.URLConnection;
import java.io.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.agile.px.*;
import com.agile.api.*;

public class CreateModel implements ICustomAction {
	
	boolean pxSuccess = false;
	private static ResourceBundle myResource = null;
	IAgileSession theSession = null;
	String pxName = "CreateModel";
	private static Logger logger = Logger.getLogger(CreateModel.class);
	
	String zwsURL = "";
	String username = "";
	String password = "";
	String agileURL = "";
	
	public ActionResult doAction(IAgileSession session, INode actionNode, IDataObject affectedObject) {
		String result = "";
		theSession = session;
		//helper = new AgileHelper(theSession);
		processProperties();
		//return new ActionResult(ActionResult.STRING, "Hello World");
		
		logger.debug("running PX: " + pxName);
		try {
			result = doWork(affectedObject);
			//return new ActionResult(ActionResult.STRING, "Hello World after doWork: " + result);
			if (pxSuccess) {
				
				return new ActionResult(ActionResult.STRING, result);
			}
			else {
				Exception pxEx = new Exception(result);
				throw pxEx;
			}
		}
		catch (Throwable th) {
			return new ActionResult(ActionResult.EXCEPTION, th);
		}
	}
	
	public String doWork (IDataObject dataObject) throws Exception{
		String result = "";
		try {
			//get part number from object
			String partNumber = dataObject.getName();
			//I would change httpPostToZWS to return message instead of URL
			result = httpPostToZWS(partNumber);
			logger.debug("httpPost result: " + result);
			//based on result you can set px success to true or false
			pxSuccess = true;
		}
		catch (APIException apiEx) {
			logger.error("doWork APIEx: " + apiEx.getMessage());
			return apiEx.getMessage();
		}
		
		return result;
	}
	
	public void processProperties()
	{
		try 
		{
			String s;
			Locale defaultLocale = Locale.getDefault();
			myResource  = ResourceBundle.getBundle("ZWS_PX",defaultLocale);
			if (myResource != null)
			{
				zwsURL = myResource.getString("App.zwsURL");
				username = myResource.getString("App.username");
				password = myResource.getString("App.password");
				agileURL = myResource.getString("App.agileURL");
			
			}
		}catch (MissingResourceException ex )
		{
			{} //System.out.println("missing resource " +ex);
		}
	}

	public String httpPostToZWS(String partNum)
    {
		String returnURL = "";
		String nextLine = "";
		String urlOutput = "";
		PrintStream outStream;
    DataInputStream inStream;
    try {
		  returnURL = zwsURL + partNum;
		  returnURL += "&username="+username;
		  returnURL += "&password="+password;

			logger.debug("returnURL = " + returnURL);
			
      URL url = new URL(returnURL);
			URLConnection urlConn = url.openConnection();
      urlConn.setDoInput(true);
      urlConn.setDoOutput(true);
      urlConn.setUseCaches(false);
      inStream = new DataInputStream(urlConn.getInputStream());
      while((nextLine = inStream.readLine()) != null) { 
        urlOutput += nextLine;
        logger.debug("urlOutput = " + urlOutput);
        inStream.close();
      }
    }
    catch(Exception exception) {
      logger.error("Error: " + exception);
    }
		return returnURL;
  }
	
	public IAgileSession connect(String url, String user, String pass) throws APIException {
		logger.debug("Create Session with : " +url +" " + user +" " + pass);
		AgileSessionFactory factory = AgileSessionFactory.getInstance(url);
		HashMap params = new HashMap();
		params.put(AgileSessionFactory.USERNAME, user);
		params.put(AgileSessionFactory.PASSWORD, pass);
		theSession = factory.createSession(params);
		//new code for 9.2 logging
		Properties props = new Properties();
		props.put("log4j.threshold", "ON");
		PropertyConfigurator.configure(props);
		//end of new code
		logger.debug("session logged in user: " + theSession.getCurrentUser().getName());
		return theSession; 
	}
	
	public void disconnect()
	{
		if ( theSession != null )
		{
			logger.debug("Disconnecting...");
			theSession.close();
			theSession=null;
		}
		
	} 
	
	public void runDoAction (String number) {
		try {
			//process properties
			logger.debug("Running " + pxName + "....");
			processProperties();
			connect(agileURL, username, password);
			IItem theItem = (IItem)theSession.getObject(ItemConstants.CLASS_PART, number);
			ActionResult result = doAction(theSession, null, theItem);
			logger.debug("result: " + result.toString());
		}
		catch (APIException apiEx) {
			{} //System.out.println("runDoAction apiEx: " + apiEx.getMessage());
		}
		finally {
			disconnect();
		}
	}
	
	public static void main(String[] args) {
		try {
			String num = args[0];

			CreateModel cm = new CreateModel();
			cm.runDoAction(num);
			// Get the current user name
		} catch (Exception e) {
			e.printStackTrace();
		} 
   }
	
}