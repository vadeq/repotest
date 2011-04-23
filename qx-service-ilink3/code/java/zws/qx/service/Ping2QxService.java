/*
 DesignState - Design Compression Technology
 @author: arbind
 @version: 1.0
 Created on Mar 23, 2007 11:10:21 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
package zws.qx.service;

import zws.qx.Qx;
import zws.qx.QxContext;
import zws.qx.xml.QxXML;
import zws.repository.ilink3.Ilink3Constants;
import zws.repository.ilink3.Ilink3QxInvoker;

public class Ping2QxService implements Qx {

  public QxXML executeQx(QxContext ctx, QxXML instruction) {
    return new QxXML("<ping-2/>");
  }
}
