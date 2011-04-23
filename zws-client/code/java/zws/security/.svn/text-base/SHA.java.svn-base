package zws.security;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 23, 2004, 11:30 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.util.HexString;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA {
  public static String encode(String clearText) throws NoSuchAlgorithmException {
   MessageDigest md = MessageDigest.getInstance("SHA"); 
   md.update(clearText.getBytes());
   return HexString.bufferToHex(md.digest());
  }
 
}
