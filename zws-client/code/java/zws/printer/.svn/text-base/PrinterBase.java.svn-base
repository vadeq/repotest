package zws.printer;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on March 1, 2005, 12:35 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import java.io.Serializable;

public class PrinterBase implements Printer, Serializable {
  public PrinterBase() { order = getNewCount(); }

  public String getName() { return getAlias(); }
  public String getAlias() {
    if (null==alias) return getPath();
    return alias;
  }
  public void setAlias(String s) { alias=s; }
  public String getPath() { return path; }
  public void setPath(String s) { path=s; }
  public String getDriverName() { return driverName; }
  public void setDriverName(String s) { driverName=s; }
  public String getPort() { return port; }
  public void setPort(String s) { port=s; }
  
  public Object copy() { return shallowCopy(); }
  public Object deepCopy() { return shallowCopy(); }
  public Object shallowCopy() {
    PrinterBase printer = new PrinterBase();
    if (null!=alias) printer.setAlias(alias);
    printer.setPath(path);
    printer.setDriverName(driverName);
    printer.setPort(port);
    return printer;
  }
  public boolean supportsDeepCopy() { return true; }
  public int getOrder() { return order; }
  
  private static synchronized int getNewCount() { return count++; }

  public void inactivate() {};
  
  private static int count = 0;
  private String alias=null;
  private String path=null;
  private String driverName=null;
  private String port=null;
  private int order=-1;
}
