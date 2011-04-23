package zws;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on December 2, 2003, 11:14 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Names;
import zws.application.Properties;
import zws.email.EmailMessage;
import zws.service.email.EJBLocator;
import zws.service.email.EmailService;

import java.util.StringTokenizer;
  
public class Emailer {
  public static void send(String serverName, EmailMessage msg) {
    EmailService service = EJBLocator.findService(serverName);
    try { service.send(msg); }
    catch (Exception e) { e.printStackTrace(); }
  }

  public static void send (String from, String recipients, String subject, String body) throws Exception {
    EmailMessage message = new EmailMessage();
    message.setFrom(from);
    message.setSubject(subject);
    StringTokenizer tokens = new StringTokenizer(recipients, ";");
    if (!tokens.hasMoreTokens()) message.addRecipient(recipients.trim());
    else while (tokens.hasMoreTokens()) message.addRecipient(tokens.nextToken().trim());
    message.setBody(body);
    Emailer.send(Properties.get(Names.CENTRAL_SERVER), message);
  }  
  
}
