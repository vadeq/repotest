package zws.service.email; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.server.email.Names;
import zws.email.EmailMessage;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.MimeMessage;

public class EmailSvc {
	public static void send(EmailMessage msg) throws Exception {
    Properties props = System.getProperties();
    props.put("mail.smtp.host", smtpHost);
    Session session = Session.getInstance(props,null);
    // create a new MimeMessage object (using the Session created above)
    Message message = new MimeMessage(session);
    message.setFrom(msg.getFrom());
    message.setRecipients(Message.RecipientType.TO, msg.getRecipients());
    message.setSubject(msg.getSubject());
    message.setContent(msg.getBody(), "text/plain");
    Transport.send(message);
  }

  private static String smtpHost =zws.application.Properties.get(Names.SMTP_HOST);
}