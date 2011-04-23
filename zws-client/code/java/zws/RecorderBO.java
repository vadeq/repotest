package zws;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
Created on Feb 16, 2006
@version: 1.0
Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */


import zws.recorder.*;



import java.util.SortedSet;


public class RecorderBO implements IRecorderBO {
  
    
    public RecorderBO() {
        // Default Constructor
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#stampStartTime(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public long stampStartTime(String namespace, String name, String status, String dotedDate) throws Exception {
        // +++ Auto-generated method stub
        return RecorderSvcImpl.stampStartTime(namespace,name,dotedDate);
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#stampStartTime(java.lang.String, java.lang.String, java.lang.String)
     */
    public long stampStartTime(String namespace, String name, String dotedDate) throws Exception {
        // +++ Auto-generated method stub
        return RecorderSvcImpl.stampStartTime(namespace,name,dotedDate);
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#recordStartTime(java.lang.String, java.lang.String)
     */
    public long recordStartTime(String namespace, String name) throws Exception {
        // +++ Auto-generated method stub
        return RecorderSvcImpl.recordStartTime(namespace,name);
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#recordStartTime(java.lang.String, java.lang.String, java.lang.String)
     */
    public long recordStartTime(String namespace, String name, String status) throws Exception {
        // +++ Auto-generated method stub
        return RecorderSvcImpl.recordStartTime(namespace,name,status);
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#recordChildStartTime(long, java.lang.String, java.lang.String, java.lang.String)
     */
    public long recordChildStartTime(long parentId, String namespace, String name, String status) throws Exception {
        // +++ Auto-generated method stub
        return RecorderSvcImpl.recordChildStartTime(parentId,namespace,status);
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#recordChildStartTime(long, java.lang.String, java.lang.String)
     */
    public long recordChildStartTime(long parentId, String namespace, String name) throws Exception {
        // +++ Auto-generated method stub
        return RecorderSvcImpl.recordChildStartTime(parentId,namespace,name);
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#recordEndTime(long)
     */
    public void recordEndTime(long id) throws Exception {
        // +++ Auto-generated method stub
        RecorderSvcImpl.recordEndTime(id);
        
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#recordEndTime(long, java.lang.String)
     */
    public void recordEndTime(long id, String status) throws Exception {
        // +++ Auto-generated method stub
        RecorderSvcImpl.recordEndTime(id);
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#recordStatus(long, java.lang.String)
     */
    public void recordStatus(long id, String status) throws Exception {
        // +++ Auto-generated method stub
        RecorderSvcImpl.recordStatus(id,status);
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#recordActivity(long, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    public void recordActivity(long id, String domain, String node, String msgType, String msg) throws Exception {
        // +++ Auto-generated method stub
        RecorderSvcImpl.recordActivity(id,domain,node,msgType,msg);
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#deleteRecord(long)
     */
    public void deleteRecord(long id) throws Exception {
        // +++ Auto-generated method stub
        RecorderSvcImpl.deleteRecord(id);
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#purgeRecords(java.util.Date)
     */
    public void purgeRecords(java.sql.Date cutOffTime) throws Exception {
        // +++ Auto-generated method stub
        RecorderSvcImpl.purgeRecords(cutOffTime);   
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#getRecording(long)
     */
    public ExecutionRecord getRecording(long id) throws Exception {
        // +++ Auto-generated method stub
        return RecorderSvcImpl.getRecording(id);
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#getStatus(long)
     */
    public String getStatus(long id) throws Exception {
        // +++ Auto-generated method stub
        return RecorderSvcImpl.getStatus(id);
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#getRecordings(java.lang.String, java.lang.String)
     */
    public SortedSet getRecordings(String namespace, String name) throws Exception {
        // +++ Auto-generated method stub
        return RecorderSvcImpl.getRecordings(namespace,name);
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#getLastRecording(java.lang.String)
     */
    public ExecutionRecord getLastRecording(String namespace) throws Exception {
        // +++ Auto-generated method stub
        return RecorderSvcImpl.getLastRecording(namespace);
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#getLastRecording(java.lang.String, java.lang.String)
     */
    public ExecutionRecord getLastRecording(String namespace, String name) throws Exception {
        // +++ Auto-generated method stub
        return RecorderSvcImpl.getLastRecording(namespace,name);
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#getChildRecordings(long)
     */
    public SortedSet getChildRecordings(long parent) throws Exception {
        // +++ Auto-generated method stub
        return RecorderSvcImpl.getChildRecordings(parent);
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#getParentRecording(long)
     */
    public ExecutionRecord getParentRecording(long child) throws Exception {
        // +++ Auto-generated method stub
        return RecorderSvcImpl.getParentRecording(child);
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#getActivity(long)
     */
    public SortedSet getActivity(long id) throws Exception {
        // +++ Auto-generated method stub
        return RecorderSvcImpl.getActivity(id);
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#getNames(java.lang.String)
     */
    public SortedSet getNames(String namespace) throws Exception {
        // +++ Auto-generated method stub
        return RecorderSvcImpl.getNames(namespace);
    }

    /* (non-Javadoc)
     * @see zws.IRecorderBO#getNamespaces()
     */
    public SortedSet getNamespaces() throws Exception {
        // +++ Auto-generated method stub
        return RecorderSvcImpl.getNamespaces();
    }
    
 

  

  
}
