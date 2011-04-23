package com.zws.functor.processor.action.airborn;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on April 7, 2004, 5:37 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.email.EmailMessage;

import com.zws.datasource.SQLSource;
import com.zws.domo.document.Document;
import com.zws.functor.processor.action.Action;
import com.zws.service.config.DataSourceService;

import java.io.File;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.MimeMessage;

public class PurgeDeletedFiles extends Action {
  public void execute() {
    try {
      Collection deletes = new Vector();
      Collection cache = getDatasource().findAll();
      {} //System.out.println("Search Database contains " + cache.size() + " entries...");
      Iterator i = cache.iterator();
      Document doc;
      File f;
      while (i.hasNext()) {
        doc = (Document)i.next();
        f = new File(doc.get(getFolderMetadata()), doc.get(getSourceFilenameMetadata()));
        if (!f.exists()) {
          {} //System.out.println("Found Database entry with missing source:" + doc.getOrigin() +"~"+doc.getName()+". No file located at: " + f.getAbsolutePath());
          getDatasource().delete(doc.getOrigin());
          deletes.add(doc);
        }
      }
      {} //System.out.println("Purged " + deletes.size() + " files.");
      notifyDeletedFiles(deletes);
    }
    catch(Exception e) { e.printStackTrace(); }
  }
  
  private void notifyDeletedFiles(Collection deletes) throws Exception {
    if (null==deletes || 0==deletes.size()) return;
    String msg = "Following entries have been deleted from the Search Database:" + com.zws.application.Constants.LINE_SEPARATOR;
    Iterator i = deletes.iterator();
    Document doc;
    while (i.hasNext()) {
      doc = (Document) i.next();
      msg += doc.getName() + " Sourcefile no longer exists at location:" + doc.get(getFolderMetadata()) + " [" + doc.getOrigin() + "]" + com.zws.application.Constants.LINE_SEPARATOR;
    }
    {} //System.out.println(msg);
    sendMessage(msg);
  }

  private void sendMessage(String body) throws Exception {
    EmailMessage msg = new EmailMessage();
    msg.addRecipient("BokaB@airborn.com");
    msg.setFrom("DesignState@airborn.com");
    msg.setSubject("Processing completed");
    msg.setBody(body);
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

  public SQLSource getDatasource() throws Exception { return (SQLSource)DataSourceService.find(datasourceName); }
  public String getDatasourceName() { return datasourceName; }
  public void setDatasourceName(String s) { datasourceName=s; }
  public String getFolderMetadata() { return folderMetadata; }
  public void setFolderMetadata(String s) { folderMetadata=s; }
  public String getSourceFilenameMetadata() { return sourceFilenameMetadata; }
  public void setSourceFilenameMetadata(String s) { sourceFilenameMetadata=s; }
  private String folderMetadata = "Folder";
  private String sourceFilenameMetadata = "ds-sourcefile-filename";
  private String datasourceName = null;
  private static String smtpHost = "smtp.airborn.com";  //zws.application.Properties.get(Names.SMTP_HOST);
}
