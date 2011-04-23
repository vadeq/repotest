package com.zws.service;
import java.net.InetAddress;
public class IDGenerator {
  private static IDGenerator service;
  private static long current= System.currentTimeMillis();

  public static IDGenerator getService() { if (null==service) service=new IDGenerator(); return service; }
  public static synchronized String getnewID() {
    String id = "ZWS";
    try { id += InetAddress.getLocalHost().toString() + current++; }
    catch (Exception e){ id += current++; }
    return id;
  }
  private IDGenerator() { }
}
