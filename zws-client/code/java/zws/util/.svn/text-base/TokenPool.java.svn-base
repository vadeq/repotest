package zws.util; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on May 13, 2005
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import zws.exception.*;
import java.util.*;
import java.io.FileWriter;

public class TokenPool {
 //initialize the pool with max free tokens
 public TokenPool(int max) {
  maxTokensAvailable= max;
  for (int idx=0; idx<max; idx++) freeTokens.add(new Object());
 }
 
 public Object takeToken() throws NoMoreTokens{
  Object token = takeTokenFromPool();
  if (token==null) throw new NoMoreTokens(maxTokensAvailable);
  return token;
 }
 
 private synchronized Object takeTokenFromPool(){
  Object token = null;
  if (freeTokens.size() > 0) {
    token = freeTokens.remove(0);
    //log("T");
  }
  else if (maxTokensAvailable==0) token = new Object();
  return token;
 }
 
 public synchronized Object takeToken(long timeoutInMilliSeconds) throws WaitedTooLong {
  Object token;
  long interval = 200;
  long waitTime=0;
  long startTime = new Date().getTime();
  //log("T?." + freeTokens.size());
  while ((token=takeTokenFromPool()) == null) {
   //log("W."+waitTime);
   try { wait(interval); }
   catch (InterruptedException e) {}
   waitTime+=interval;
   if ((new Date().getTime() - startTime) >= timeoutInMilliSeconds) {
     //log("E."+waitTime);
     throw new WaitedTooLong("takeToken");
   }
  }
  //log("T!." + freeTokens.size());
  return token;
 }

 //releases a token for use
 public synchronized void releaseToken(Object token) {
  if (freeTokens.size() >= maxTokensAvailable) return;
  freeTokens.add(token);
  //log("R!" + freeTokens.size());
  notify();
 }


 private void log(String s) {
   try { getLogger().write(s+zws.application.Names.NEW_LINE); getLogger().flush(); }
   catch (Exception e) { e.printStackTrace(); }
 }

 private FileWriter getLogger() {
   try { if (null==log) log = new FileWriter("C:\\zws-dojo\\testing\\stress\\result\\search\\log.txt"); }
   catch (Exception e) { e.printStackTrace(); }
   return log;
 }

 public int getMaxTokens() { return maxTokensAvailable; }
 private int maxTokensAvailable=0;
 private List freeTokens= Collections.synchronizedList(new ArrayList());
 private FileWriter log = null; 
}