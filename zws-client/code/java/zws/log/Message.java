package zws.log;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.util.Calendar;

/*
  zws.log package provides a mechanism to capture runtime execution information for:
  - status
  - warning
  - failure
  Not all exceptions should be handled as failure conditions: Some are simply warnings.
  This package provides a way to capture timestamp and log these runtime conditions.
*/
public interface Message {
  public Calendar getTime();
  public String getMessageKey();
  public String getMessage();
  public Exception getException();
}