package zws.log.status;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.log.MessageBase;

public class Status extends MessageBase {
  public Status(String messageKey) { super(messageKey); }
  public Status(String messageKey, String arg1) { super(messageKey, arg1); }
  public Status(String messageKey, String arg1, String arg2) { super(messageKey, arg1, arg2); }
}