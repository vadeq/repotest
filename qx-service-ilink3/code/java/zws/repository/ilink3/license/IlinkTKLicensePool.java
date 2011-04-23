package zws.repository.ilink3.license; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on May 13, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.util.TokenPool;
import zws.exception.NoMoreTokens;
import zws.exception.WaitedTooLong;

public class IlinkTKLicensePool {

 protected IlinkTKLicensePool (String name, int maxTokens) { 
   poolName = name; 
   pool= new TokenPool(maxTokens);
 }
 
 public Object takeToken() throws NoMoreTokens{ return pool.takeToken(); }

 public Object takeToken(long timeoutInMilliSeconds) throws WaitedTooLong {
   try { 
     Object token = pool.takeToken(timeoutInMilliSeconds); 
     {} //System.out.println("Taking License...");
     return token;
   }
   catch(WaitedTooLong e) { 
     throw new WaitedTooLong("err.taking.ilink.tk.license", e.getTimeout());
   }
 }

 public void releaseToken(Object token) {
   pool.releaseToken(token); 
 }

 private String poolName = null;
 private TokenPool pool = null; 
}
