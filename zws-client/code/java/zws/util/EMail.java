package zws.util; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Sep 15, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.HTTPUptimeMonitor;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class EMail {
  public static void main(String args[]) {
      String recipients = "athakur@zerowait-state.com, dstewart@zerowait-state.com";
      String r[] = parseAddresses(recipients);
      try { 
          //send("a@zws.com", r, "EMail", "Hello World", "mail.zerowait-state.com");
        String arg[] = new String[6];
        arg[0]="http://zws-nt4:80/login.do?event=authenticate";
        arg[1]="zws-nt4";
        arg[2]="C:\\zws\\bin\\uptime\\bring-zws-down.bat";
        arg[3]="C:\\zws\\bin\\uptime\\bring-zws-up.bat";
        arg[4]="mail.zerowait-state.com";
        arg[5]="athakur@zerowait-state.com,  dstewart@zerowait-state.com; cgee@zerowait-state.com sporter@zerowait-state.com";
        HTTPUptimeMonitor.main(arg);
          }
      catch (Exception e) { e.printStackTrace(); }
  }
  
  public static String[] parseAddresses(String s) {
    char c;
    int idx=0;
	  String addresses[];
    StringBuffer buf = new StringBuffer();
	  String recipients=StringUtil.trimQuotes(s);
    for (idx=0; idx<recipients.length(); idx++) {
      c = recipients.charAt(idx);
      if (';'==c || ','==c || '\t'==c) buf.append(' ');
      else buf.append(c);
    }
    recipients = buf.toString();
	  StringTokenizer tokens = new StringTokenizer(recipients, " ");
	  if (!tokens.hasMoreTokens()) {
	    addresses = new String[1];
	    addresses[0] = recipients.trim();
	    return addresses;
	  }
	  addresses = new String[tokens.countTokens()];
	  idx=0;
	  while (tokens.hasMoreTokens()){
	    addresses[idx++] = tokens.nextToken().trim();
	  }
	  return addresses; 
  }
  
	public static void send(String from, Collection recipients, String subject, String message, String host) throws MessagingException {
	  String recipientList[] = new String[recipients.size()];
	  Iterator i = recipients.iterator();
	  int idx=0;
	  while(i.hasNext()) recipientList[idx++] = (String)i.next();
	  send(from, recipientList, subject, message, host);
	}

	public static void send(String from, String recipients[ ], String subject, String message, String host) throws MessagingException {
	    boolean debug = false;
	
       if (subject == null) subject = "";
       
	     //Set the host smtp address
	     Properties props = new Properties();
	     props.put("mail.smtp.host", host);
	
	    // create some properties and get the default Session
	    Session session = Session.getDefaultInstance(props, null);
	    session.setDebug(debug);
	
	    // create a message
	    Message msg = new MimeMessage(session);
	
	    // set the from and to address
	    InternetAddress addressFrom = new InternetAddress(from);
	    msg.setFrom(addressFrom);
	
	    InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
	    for (int i = 0; i < recipients.length; i++)
	    {
	        addressTo[i] = new InternetAddress(recipients[i]);
	    }
	    msg.setRecipients(Message.RecipientType.TO, addressTo);
	   
	
	    // Optional : You can also set your custom headers in the Email if you Want
	    msg.addHeader("MyHeaderName", "myHeaderValue");
	
	    // Setting the Subject and Content Type
	    msg.setSubject(subject);
	    msg.setContent(message, "text/plain");
	    Transport.send(msg);
	}

}
