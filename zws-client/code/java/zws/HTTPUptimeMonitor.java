package zws; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Sep 14, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.util.*;
import zws.application.Names;
import java.util.*;

public class HTTPUptimeMonitor {
    private static int RESPONSE_WAIT_TIME = 5; //seconds
    private static int POLLING_INTERVAL = 10; //seconds
    private static int  SERVER_SHUTDOWN_DURATION= 5; //seconds
    private static int  SERVER_STARTUP_DURATION= 15; //seconds

  private HTTPUptimeMonitor() {};
    
  public static void main(String[] args) {
   // Create a URLConnection object for a URL
   //remotehost = "zws-nt4";
   //String url = "http://"+remotehost+":80/login.do?username=admin&password=admi&event=authenticate";
   //String upCommand= "c:\\zws\\bin\\uptime\\bring-zws-up.bat"; 
   //String downCommand = "c:\\zws\\bin\\uptime\\bring-zws-down.bat";
   {} //System.out.println("args:" + args.length);
   url=StringUtil.trimQuotes(args[0]);
   remoteHost=StringUtil.trimQuotes(args[1]);
   downCommand=StringUtil.trimQuotes(args[2]);
   upCommand=StringUtil.trimQuotes(args[3]);
   smtpHost=StringUtil.trimQuotes(args[4]);
   String recipients= StringUtil.trimQuotes(args[5]);
   mailTo = EMail.parseAddresses(recipients);
   String d = smtpHost;
   while (d.indexOf('.')!=d.lastIndexOf('.')) d = d.substring(d.indexOf('.')+1);
   FROM += d;
   {} //System.out.println(args[0]);
   {} //System.out.println(args[1]);
   {} //System.out.println(args[2]);
   {} //System.out.println(args[3]);
   {} //System.out.println(args[4]);
   {} //System.out.println(args[5]);
   {} //System.out.println("________________________________________________________________________________");
   {} //System.out.println(" ");
   {} //System.out.println("Zero Wait-State HTTP Uptime monitor started");
   {} //System.out.println("________________________________________________________________________________");
   {} //System.out.println("URL: " + url);
   {} //System.out.println("remote host:" + remoteHost);
   {} //System.out.println("local down command:" + downCommand + " " + remoteHost);
   {} //System.out.println("local up command:" + upCommand+ " " + remoteHost);
   {} //System.out.println("smtp host:" + smtpHost);
   {} //System.out.println("mail recipients:" + recipients);
   {} //System.out.println("================================================================================");
   monitor();
  }

  private static void monitor() {
    long nowInMillis;
    long dailyTimeStampInMillis;
    init();
    String subject  = "HTTP Uptime monitor started: "+ remoteHost + ".";
    String message = "Monitoring from " + Server.getHostName() +  Names.NEW_LINE; 
    message +="URL: " + url +  Names.NEW_LINE;
    message += "remote host: " + remoteHost +  Names.NEW_LINE;
    message += "local down command:" + downCommand + " " + remoteHost +  Names.NEW_LINE;
    message += "local up command:" + upCommand+ " " + remoteHost +  Names.NEW_LINE;
    try { EMail.send(FROM, mailTo, subject, message, smtpHost); }
    catch (Exception e) { e.printStackTrace(); }
    dailyTimeStamp = getToday();
    dailyTimeStampInMillis = dailyTimeStamp.getTimeInMillis();
    int year,month,day;
    year = dailyTimeStamp.get(Calendar.YEAR);
    month = dailyTimeStamp.get(Calendar.MONTH);
    day = dailyTimeStamp.get(Calendar.DAY_OF_MONTH);
    dailyTimeStamp = new GregorianCalendar(year, month, day, 5,0,0);
    Calendar currentTimeStamp;
    int i=0;
    while(true) {
      i++;
      try {
        int code = getResponseCode();
        for (int x=0; x < (i%8); x++) System.out.print(".");
        {} //System.out.println("Http Response=" + HTTPCodes[code] + "[" +code + "] for " + url);
        if (code<200 || code>204) restartServer(code);
      } 
      catch (Exception e) { e.printStackTrace(); restartServer(601); }
      Sleep.sleep(POLLING_INTERVAL*1000);
      currentTimeStamp = new GregorianCalendar();
      nowInMillis = currentTimeStamp.getTimeInMillis();
      if (nowInMillis > (dailyTimeStampInMillis + (1000*60*60*24))) {
        dailyTimeStamp = getToday();
        dailyTimeStampInMillis = dailyTimeStamp.getTimeInMillis();
        subject  = "HTTP Uptime monitor running: "+ remoteHost + ".";
        message = "Monitoring from " + Server.getHostName() +  Names.NEW_LINE; 
        message +="URL: " + url +  Names.NEW_LINE;
        message += "remote host: " + remoteHost +  Names.NEW_LINE;
        message += "local down command:" + downCommand + " " + remoteHost +  Names.NEW_LINE;
        message += "local up command:" + upCommand+ " " + remoteHost +  Names.NEW_LINE;
        try { EMail.send(FROM, mailTo, subject, message, smtpHost); }
        catch (Exception e) { e.printStackTrace(); }
      }
    }
  }  
  
  private static Calendar getToday() {
  	int year,month,day;
	  Calendar c= new GregorianCalendar();
	  year = c.get(Calendar.YEAR);
	  month = c.get(Calendar.MONTH);
	  day = c.get(Calendar.DAY_OF_MONTH);
	  c = new GregorianCalendar(year, month, day, 5,0,0);
	  return c;
  }

  private static void restartServer(int err) {
    String subject = "Restarting Server: " + remoteHost + " [" + err + "]!!";
    String message = "Description of last Response:" +  Names.NEW_LINE;
    message += "URL: " + url +  Names.NEW_LINE;
    message += "Response: "+ HTTPCodes[err] + "[" + err + "]"+  Names.NEW_LINE;
    message += "Attempting to restart server: " + remoteHost +  Names.NEW_LINE;
    try { EMail.send(FROM, mailTo, subject, message, smtpHost); }
    catch (Exception e) { e.printStackTrace(); }
 
    {} //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    {} //System.out.println("Attempting restart server: " + remoteHost);
    ExecShell down = new ExecShell();
    down.setExecutable(downCommand);
    down.addCommandLineArgument(remoteHost, false);
    try {
      {} //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      {} //System.out.println("Bringing server down on " + remoteHost + " with " + downCommand);
      down.execute();
      down.waitFor();
      Sleep.sleep(SERVER_SHUTDOWN_DURATION*1000);
    }
    catch(Exception e) {
      subject = "ERROR Restarting Server on " + remoteHost;
      message = "Could not execute bring down command: " + downCommand +  Names.NEW_LINE;
      try { EMail.send(FROM, mailTo, subject, message, smtpHost); }
      catch (Exception ex) { ex.printStackTrace(); }
      {} //System.out.println("Could not execute bring down command: " + downCommand);
      {} //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
    	
    ExecShell up = new ExecShell();
    up.setExecutable(upCommand);
    up.addCommandLineArgument(remoteHost, false);
   try {
     {} //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
     {} //System.out.println("Bringing server up on " + remoteHost + " with " + upCommand);
     up.execute();
     up.waitFor();
     Sleep.sleep(SERVER_STARTUP_DURATION*1000);
     int code = getResponseCode();
     if (code>199 && code<206) {
       subject = "Server is back up: " + remoteHost + "[" + code + "].";
       message = "Http Response=" + HTTPCodes[code] + "[" +code + "] for " + url;
       {} //System.out.println(subject);
       {} //System.out.println(message);
       try { EMail.send(FROM, mailTo, subject, message, smtpHost); }
       catch (Exception ex) { ex.printStackTrace(); }
     }
     else {
       subject = "Could not bring server back up"+ " [" + code + "]!";
       message = "Http Response=" + HTTPCodes[code] + "[" +code + "] for " + url;
       {} //System.out.println(subject);
       {} //System.out.println(message);
       EMail.send(FROM, mailTo, subject, message, smtpHost);
     }
     {} //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
   }
   catch(Exception e) {
     subject = "ERROR Restarting Server on " + remoteHost;
     message = "Could not execute bring up command: " + upCommand;
     {} //System.out.println(subject);
     {} //System.out.println(message);
     {} //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
     try { EMail.send(FROM, mailTo, subject, message, smtpHost); }
     catch (Exception ex) { ex.printStackTrace(); }
   }
  }
  
  private static int getResponseCode() throws Exception{
   HTTPPing urlPing = new HTTPPing(url);
   urlPing.execute();
   Sleep.sleep(RESPONSE_WAIT_TIME*1000);
   int code = urlPing.getResponseCode();
   //if (599<code) urlPing.getThread().stop();
   return code;
   //URL httpurl = new URL(url);
   //HttpURLConnection conn = (HttpURLConnection)httpurl.openConnection();
   //return conn.getResponseCode();
	}

  private static void init() {
      //2xx - Success
      HTTPCodes[200] = "Success: OK";
      HTTPCodes[201] = "Success: Document Created";
      HTTPCodes[202] = "Success: Request accepted for asynchronous processing";
      HTTPCodes[203] = "Success: Partial Information: Returned information may be cached or private.";
      HTTPCodes[204] = "Success: OK: But No Response";
      //3xx Redirections
      HTTPCodes[301] = "Redirected: Document Moved";
      HTTPCodes[302] = "Redirected: Document Found";
      HTTPCodes[303] = "Redirected: See Body for method to access document";
      HTTPCodes[304] = "Redirected: 304 Not modified - use local copy if you cached it";
      //4xx Client Error
      HTTPCodes[400] = "Client Error: Bad Request - Impossible or syntax error";
      HTTPCodes[401] = "Client Error: Unauthorized";
      HTTPCodes[402] = "Client Error: Payment Required";
      HTTPCodes[403] = "Client Error: Forbidden";
      HTTPCodes[404] = "Client Error: Document not found";
      HTTPCodes[411] = "Client Error: No such group - (NCSA httpd) the newsgroup in news:newsgroup doesn't exist";
      //5xx Server Error
      HTTPCodes[500] = "Server Error: Internal";
      HTTPCodes[501] = "Server Error: Not Implemented";
      HTTPCodes[502] = "Server Error: Timed Out";
      //6xxx Made up errors
      HTTPCodes[600] = "Connection Error: Was the server started?";
      HTTPCodes[601] = "Unhandled Exception";
  }
  
  static String HTTPCodes[] =new String[1000];
  static String url;
  static String upCommand;
  static String downCommand;
  static String remoteHost;
  static String smtpHost;
  static String mailTo[];
  static String FROM = "zwsUPtime@";
  static Calendar dailyTimeStamp = null;
}
