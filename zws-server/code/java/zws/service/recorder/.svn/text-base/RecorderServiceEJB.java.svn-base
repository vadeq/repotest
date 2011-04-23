package zws.service.recorder; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
Created on Feb 16, 2006
@version: 1.0
Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

import zws.application.Configurator;
import zws.recorder.*;
//impoer zws.util.Logwriter;

import java.util.Date;
import java.util.SortedSet;
import java.rmi.RemoteException;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

public class RecorderServiceEJB implements SessionBean, RecorderService {

 public long stampStartTime(String namespace, String name, String dotedDate) throws RemoteException {
   try { return RecorderSvc.stampStartTime(namespace, name, dotedDate); }
   catch(Throwable e) { throw new RemoteException(e.getMessage()); }
 }

 public long stampStartTime(String namespace, String name, String status, String dotedDate) throws RemoteException {
   try { return RecorderSvc.stampStartTime(namespace, name, status, dotedDate); }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }

 public long stampStartTime(String namespace, String name, String status, String dotedDate, String description) throws RemoteException {
   try { return RecorderSvc.stampStartTime(namespace, name, status, dotedDate, description); }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }

 public long recordStartTime(String namespace, String name) throws RemoteException {
   try { return RecorderSvc.recordStartTime(namespace, name); }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }

 public long recordStartTime(String namespace, String name, String status) throws RemoteException {
   try { return RecorderSvc.recordStartTime(namespace, name, status); }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }

 public long recordStartTime(String namespace, String name, String status, String description) throws RemoteException {
   try { return RecorderSvc.recordStartTime(namespace, name, status, description); }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }

 public long recordChildStartTime(long parentId, String namespace, String name, String status, String description) throws RemoteException{
   try { return RecorderSvc.recordChildStartTime(parentId, namespace, name, status, description); }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }

 public long recordChildStartTime(long parentId, String namespace, String name, String status) throws RemoteException{
   try { return RecorderSvc.recordChildStartTime(parentId, namespace, name, status); }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }

 public long recordChildStartTime(long parentId, String namespace, String name) throws RemoteException{
   try { return RecorderSvc.recordChildStartTime(parentId, namespace, name); }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }

 public void recordEndTime(long id) throws RemoteException{
   try { RecorderSvc.recordEndTime(id) ; }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }

 public void recordEndTime(long id, String status) throws RemoteException{
   try { RecorderSvc.recordEndTime(id, status) ; }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }

 public void recordStatus(long id, String status) throws RemoteException{
   try { RecorderSvc.recordStatus(id, status); }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }
 public void recordActivity(long id, String domain, String node, String msgType, String msg) throws RemoteException{
   try { RecorderSvc.recordActivity(id, domain, node, msgType, msg); }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }
 public void deleteRecord(long id) throws RemoteException{
   try { RecorderSvc.deleteRecord(id); }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }
 public void purgeRecords(Date cutOffTime) throws RemoteException{
   try { RecorderSvc.purgeRecords(cutOffTime); }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }
 public ExecutionRecord getRecording(long id) throws RemoteException{
   try { return RecorderSvc.getRecording(id) ; }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }
 public ExecutionRecord getLastRecording(String namespace) throws RemoteException{
   try { return RecorderSvc.getLastRecording(namespace) ; }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }
 public ExecutionRecord getLastRecording(String namespace, String name) throws RemoteException{
   try { return RecorderSvc.getLastRecording(namespace, name) ; }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }
 public SortedSet getChildRecordings(long id) throws RemoteException{
   try { return RecorderSvc.getChildRecordings(id) ; }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }
 public ExecutionRecord getParentRecording(long id) throws RemoteException{
   try { return RecorderSvc.getParentRecording(id) ; }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }
 public String getStatus(long id) throws RemoteException{
   try { return RecorderSvc.getStatus(id) ; }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }
 public SortedSet getRecordings(String namespace, String name) throws RemoteException{
   try { return RecorderSvc.getRecordings(namespace, name) ; }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }
 public SortedSet getActivity(long id) throws RemoteException{
   try { return RecorderSvc.getActivity(id); }
   catch(Exception e) { throw new RemoteException(e.getMessage()); }
 }

 public SortedSet getNames(String namespace) throws RemoteException{

     {}//Logwriter.printOnConsole("inside RecorderServiceEJB == > Getting namespace"+namespace);
     try { return RecorderSvc.getNames(namespace); }
     catch(Exception e) { throw new RemoteException(e.getMessage()); }
   }


 public SortedSet getNamespaces() throws RemoteException{

     {}//Logwriter.printOnConsole("inside RecorderServiceEJB == > Getting all namespaces");
     try { return RecorderSvc.getNamespaces(); }
     catch(Exception e) { throw new RemoteException(e.getMessage()); }
   }








 public String getServerName() { return serverName; }
 private String serverName=null;

 public void ejbCreate () { try {Configurator.load();} catch (Exception e) { e.printStackTrace(); } }
 public void ejbRemove() {}
 public void ejbPassivate() {}
 public void ejbActivate() {}
 public void setSessionContext(SessionContext ctx) {}
}
