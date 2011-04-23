package zws.repository.ilink3.license; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on May 13, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.util.KeyValue;
import zws.util.Pair;
import zws.util.TokenPool;
import zws.exception.*;

import java.util.Map;
import java.util.HashMap;

import sun.rmi.runtime.Log;

public class IlinkTKLicensePoolSvc {
  

 public static void initializeTokenPool(String tokenPoolName, int maxTokens) throws DuplicateName {
   if (licensePoolMap.containsKey(key(tokenPoolName))) throw new DuplicateName("LicensePool: " + tokenPoolName);
   IlinkTKLicensePool iPool = new IlinkTKLicensePool(tokenPoolName, maxTokens);
   licensePoolMap.put(key(tokenPoolName), iPool);
 }

 public static void add(KeyValue keyValue) throws DuplicateName {
   String name = keyValue.getKey();
   int maxTokens = Integer.valueOf((String)keyValue.getValue()).intValue();
   initializeTokenPool(name, maxTokens);
 }

 public static IlinkTKLicensePool getLicensePool(String name) {
   return (IlinkTKLicensePool)licensePoolMap.get(key(name));
 }
 
 private static String key(String name) { return name.toLowerCase(); }
 
 private static Map licensePoolMap = new HashMap();
}
