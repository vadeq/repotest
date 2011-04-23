package zws.time;/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Created on April 12, 2004, 1:25 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.Serializable;

public interface TimeOfDay extends Serializable{
  public int getHour();
  public int getMinute();
  public int getSecond();
  public int getMillisecond();
}
