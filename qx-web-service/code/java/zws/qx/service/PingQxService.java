package zws.qx.service;/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Apr 5, 2007 1:18:21 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import zws.qx.Qx;
import zws.qx.QxContext;
import zws.qx.xml.QxXML;

/**
 * @author arbind
 *
 */
public class PingQxService implements Qx { 

  public QxXML executeQx(QxContext ctx, QxXML dataInstruction) {
    return new QxXML("<ping/>");
  }
}
