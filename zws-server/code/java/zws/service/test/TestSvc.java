package zws.service.test; 

/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.application.Names;
//import zws.util.{}//Logwriter;

import java.util.*;

import com.agile.api.*;

public class TestSvc {
  public static String NL = Names.NEW_LINE;
  public static String runTest(String url, String user, String psswd) {
    ClassLoader.getSystemClassLoader();
    {}//Logwriter.printOnConsole("Server: Starting Test");
    String s = "Starting Test" + NL;
    {}//Logwriter.printOnConsole("Server: Invoking Ping to DesignState-node-0");
    s += "Invoking Ping to DesignState-node-0" + NL;
    try {
     {}//Logwriter.printOnConsole("Server: Finding DesignState-node-0 service");
     TestService x = EJBLocator.findService("DesignState-node-0");
     {}//Logwriter.printOnConsole("Server: Found DesignState-node-0 service");
     {}//Logwriter.printOnConsole("Server: Pinging Node 0:");
     String p = x.ping();
     s += p + NL;
     {}//Logwriter.printOnConsole("Server: Node 0 ping'd successfully: " + p);
    }
    catch (Exception e) {
      {}//Logwriter.printOnConsole("Server: !!! Error pingging Node 0: " + e.getMessage());
	    s += " !!!!!Exception thrown!!!!!" + NL;
	    s += e.getMessage();
	    //e.printStackTrace();
	  }

    {}//Logwriter.printOnConsole("Server: Invoking Agile Login");
    s += "Invoking Agile Login" + NL;
    try {
      {}//Logwriter.printOnConsole("Server: logging " + user + ":" + psswd + " into " + url);
      loginToAgile(url, user, psswd);
      {}//Logwriter.printOnConsole("Server: " + user + " logged in");
    }
	  catch (APIException ex)  {
		  s += "Server: !!! Error Logging " + user + ":" + psswd + " into " + url + NL;
		  {}//Logwriter.printOnConsole("Server: !!! Error loggin in " + user + ":" + psswd + " into " + url);
	    Throwable rootCause = ex.getRootCause();
      if (rootCause != null) {}//Logwriter.printOnConsole("Server: Root Cause" + rootCause.getMessage());
      else  {}//Logwriter.printOnConsole("Server: Error message: " + ex.getMessage());
      {}//Logwriter.printOnConsole("Server: Stack Trace");
	    ex.printStackTrace();
	    s += " !!!!!Exception thrown!!!!!" + NL;
	    s += ex.getMessage();
		  }
    catch (Exception e) {
  		s += "Server: !!! Error Logging " + user + ":" + psswd + " into " + url + NL;
		  {}//Logwriter.printOnConsole("Server: !!! Error loggin in " + user + ":" + psswd + " into " + url);
      s += " !!!!!Exception thrown!!!!!" + NL;
      s += e.getMessage();
      //e.printStackTrace();
    }
    return s;
  }

  public static void loginToAgile(String url, String user, String psswd) throws Exception {
    //user = "user19";
    //password = "user19";
    //url = "http://pwebdev.cisco.com/pls";
	  IAgileSession session=null;
		HashMap params = new HashMap();
		params.put(AgileSessionFactory.USERNAME, user);
	  params.put(AgileSessionFactory.PASSWORD, psswd);
    params.put(AgileSessionFactory.URL, url);
    try {
    	{}//Logwriter.printOnConsole("Server: Creating Session for " + user + ":" + psswd + " on " + url);
      session = AgileSessionFactory.createSessionEx(params);
  	  {}//Logwriter.printOnConsole("Server: Session Created for " + user);
    }
    catch (Exception e) {
      {}//Logwriter.printOnConsole("Server: !!! Error Creating Session for " + user + ":" + psswd + " on " + url);
      throw e;
    }
  }

  public static String ping() {
    Calendar c = new GregorianCalendar();
    String s = "" + c.get(Calendar.YEAR) + ".";
    s = c.get(Calendar.MONTH) + ".";
    s += c.get(Calendar.DATE) + ".";
    s += c.get(Calendar.HOUR) + ".";
    s += c.get(Calendar.MINUTE) + ".";
    s += c.get(Calendar.SECOND) + ".";
    s += c.get(Calendar.MILLISECOND);
    return s;
  }
}
