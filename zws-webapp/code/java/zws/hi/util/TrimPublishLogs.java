/* DesignState - Design Compression Technology
 * @author: Rodney
 * @version: 1.0
 * Created on Apr 28, 2008 3:03:59 PM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.hi.util;

import com.zws.hi.Interactor;
import java.io.*;
//import java.sql.*;
import java.util.*;
import zws.util.TimeUtil;

import zws.application.Names;
import zws.database.DB;
import zws.database.Database;
import zws.security.Authentication;
import zws.service.recorder.qx.*;

import java.text.SimpleDateFormat;
import zws.application.Properties;

public class TrimPublishLogs extends Interactor {
  
  private List messages = new ArrayList();
  private Date purgeDate = new Date();
  private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

  public String trimLogs() {
    
    messages.clear();
    
    try {
      if (getHttpRequest().getParameter("purgeDate") == null ||
    	  getHttpRequest().getParameter("purgeDate").length() == 0) {
    	  messages.add("Invalid date");
      	  return "index";
      }
    
      RecorderClient client = RecorderClient.getClient();      
      client.purgeRecords(purgeDate);
      messages.add("Messages prior to " + getPurgeDate() + " have been purged from the recorder service.");
    } catch (Exception e) {
      messages.add("Error in " + getClass().getName() + ": " + e.getMessage());
      messages.add("This occurred while purging recorder service records.");     
    }
    return "index";
  }
  
  public String getPurgeDate() {
	  return sf.format(purgeDate);	  
  }
  
  public void setPurgeDate(String value) { 
	  try {
		  purgeDate = sf.parse(value); 
	  } catch (Exception e) {
		  purgeDate = null;
		  messages.add("Error parsing date: " + e.getMessage());
	  }
  }
 
  public void startRequest() throws Exception { messages.clear();}
  public List getMessages() { return messages; }
}
