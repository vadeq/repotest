package zws.qx;/*
DesignState - Design Compression Technology.
@author: Santhos Avunuri, Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.qx.xml.QxXML;

import javax.activation.DataHandler;

public abstract class QxTransferBase implements QxTransfer {

  /**
   * Invoke a program and process binary data with a given execution context. 
   * 
   * @param ctx                 An execution context.
   * @param dataInstruction     The program to be run (represented in QxXML).
   * @param binary              Binary data to be processed.
   * @return                    Response from the execution (also in QxXML).
  */
  public QxXML transferQx(QxContext ctx, QxXML dataInstruction, DataHandler binary) {
    String xml = "<exception type=\"operation-not-supported\" />";
    return new QxXML(xml);
  }

  /**
   * Invoke a program and process binary data with a given execution context. 
   * 
   * @param ctx                 An execution context.
   * @param dataInstruction     The program to be run (represented in QxXML).
   * @return                    Response from the execution (also in QxXML).
  */
  public DataHandler transferQx(QxContext ctx, QxXML dataInstruction) { return null; }
}
