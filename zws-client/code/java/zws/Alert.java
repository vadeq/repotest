package zws; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
Created on Apr 21, 2006
@version: 1.0
Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

import zws.application.*;
import zws.security.Authentication;
import zws.util.EMail;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.StringTokenizer;

import javax.mail.MessagingException;

public class Alert {
 
  public static void notify(String subject, String message) {
   try {
     EMail.send(getFromEmail(), getAlertRecipients(), subject, message, getHostServer());
   }
   catch (MessagingException e) {
       System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
       System.out.println(e.getMessage());
       System.out.println("___________________________________________");
       System.out.println("Subject: " + subject);
       System.out.println(message);
       System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
       e.printStackTrace();
   }
  }
  
  public static void notify(String subject, String message, Collection recipients) {
    try {
      EMail.send(getFromEmail(), recipients, subject, message, getHostServer());
    }
    catch (MessagingException e) {
      System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      System.out.println(e.getMessage());
      System.out.println("___________________________________________");
      System.out.println("Subject: " + subject);
      System.out.println(message);
      System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
      e.printStackTrace();
    }
   }


/*  public static void notify(String subject, String message, Authentication id) {
    if (null==id) {
      notify(subject, message);
      return;
    }
    String msg = message + Names.NEW_LINE;
    msg += Names.NEW_LINE + Names.NEW_LINE+ "________________________________________" +Names.NEW_LINE ;
    msg += "User: " +id.getUsername();
    notify (subject, msg);
  }
  */
  
  public static void notify(String subject, String message, String recipients) {
    try {
      if(null==recipients) throw new Exception("no recipients exists to send mail");
      String rec = recipients;
      //normalize some delimeters
      if (0<rec.lastIndexOf(',')) rec = rec.replaceAll(",", Names.DELIMITER);
      if (0<rec.lastIndexOf(' ')) rec = rec.replaceAll(" ", Names.DELIMITER);
      StringTokenizer tokens = new StringTokenizer(rec, Names.DELIMITER);
      
      Collection c = getAlertRecipients();
      if (!tokens.hasMoreTokens()) c.add(rec);
      else {
        while (tokens.hasMoreTokens()) {
          c.add(tokens.nextToken());
        }
      }
      Map recipientsMap = new HashMap();
      Iterator i = c.iterator();
      while(i.hasNext()) {
        rec = (String)i.next();
        rec = rec.trim();
        if (!"".equals(rec)) recipientsMap.put(rec, rec);
      }
      c = recipientsMap.values();
      //zws.util.PrintUtil.print(c);
      EMail.send(getFromEmail(), c, subject, message, getHostServer());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void notify(String subject, String message, String[] recipients) {
    try {
      if(null==recipients) throw new Exception("no recipients exists to send mail");
      EMail.send(getFromEmail(), recipients, subject, message, getHostServer());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

	public static String getFromEmail() { return Properties.get(Names.EMAIL_FROM_ADDRESS); }
	public static String getHostServer() { return Properties.get(Names.EMAIL_SMTP_SERVER); }

	public static Collection getAlertRecipients() {
		String recipients= Properties.get(Names.EMAIL_RECIPIENTS);
		StringTokenizer tokens = new StringTokenizer(recipients, Names.DELIMITER);
		Collection r = new Vector();
		if (!tokens.hasMoreTokens()) r.add(recipients);
		while(tokens.hasMoreTokens()) r.add(tokens.nextToken());
		return r;
  }

}
