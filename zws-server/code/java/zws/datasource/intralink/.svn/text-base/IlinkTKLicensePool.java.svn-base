package zws.datasource.intralink; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on May 13, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.util.TokenPool;
import zws.exception.*;

import java.util.Map;
import java.util.HashMap;

public class IlinkTKLicensePool {
  private static Map tokenPoolMap = new HashMap();
  private static Map licensePoolMap = new HashMap();

 private IlinkTKLicensePool (String name, String tokenPoolName) { 
   poolName = name; 
   pool=(TokenPool)tokenPoolMap.get(tokenPoolName);
 }

 public static void initializeTokenPool(String name, String tokenPoolName, int maxTokens) {
   if (!tokenPoolMap.containsKey(tokenPoolName)) {
     TokenPool pool = new TokenPool(maxTokens);
     tokenPoolMap.put(tokenPoolName, pool);
   }
   IlinkTKLicensePool iPool = new IlinkTKLicensePool(name, tokenPoolName);
   licensePoolMap.put(name, iPool);
 }
 
 public static IlinkTKLicensePool getLicensePool(String name) {
   return (IlinkTKLicensePool)licensePoolMap.get(name);
 }
 
 public Object takeToken() throws NoMoreTokens{ return pool.takeToken(); }

 public Object takeToken(long timeout) throws WaitedTooLong {
   try { return pool.takeToken(timeout); }
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
