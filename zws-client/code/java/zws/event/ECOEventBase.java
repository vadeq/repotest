package zws.event;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on July 28, 2004, 10:34 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.RoutedEventBase;

public class ECOEventBase extends RoutedEventBase implements ECOEvent {
  public String getECOStatus() { return getString(ECO_STATUS); }
  public void setECOStatus(String s) { set(ECO_STATUS, s); }
  
  private static String ECO_STATUS="eco-status";    
}
