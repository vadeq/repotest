package zws.email; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.Serializable;
import java.util.*;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


public class EmailMessage implements Serializable {

  public Address getFrom() { return from; }
  public void setFrom(String s) throws AddressException { from = new InternetAddress(s); }
  public String getSubject() { return subject; }
  public void setSubject(String s) { subject=s; }
  public String getBody() { return body; }
  public void setBody(String s) { body=s; }

  public void addRecipient(String s) throws AddressException { recipients.add(new InternetAddress(s)); }
  public Address[] getRecipients() {
    Address[] r = new Address[recipients.size()];
    Iterator i = recipients.iterator();
    int idx=0;
    while (i.hasNext()) r[idx++] = (InternetAddress)i.next();
    return r;
  }
  public boolean removeRecipient(String s) { return recipients.remove(s); }

  private Address from=null;
  private Collection recipients = new Vector();
  private String subject="";
  private String body="";
}