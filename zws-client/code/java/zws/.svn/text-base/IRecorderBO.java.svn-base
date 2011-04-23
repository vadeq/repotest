package zws;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
Created on Feb 16, 2006
@version: 1.0
Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

import zws.recorder.*;

import java.util.SortedSet;


public interface IRecorderBO {
  
    /** 
     * Record process start
     * @param namespace Process namespace
     * @param name Process name
     * @param status Process status
     * @param dotedDate timestamp to record
     * @return Unique process ID
     * @throws Exception On error or invalid argument is specified
     */
    public long stampStartTime(String namespace, String name, String status, String dotedDate) throws Exception;
    
    /** 
     * Record process start
     * @param namespace Process namespace
     * @param name Process name
     * @param dotedDate timestamp to record
     * @return Unique process ID
     * @throws Exception On error or invalid argument is specified
     */
    public long stampStartTime(String namespace, String name, String dotedDate) throws Exception;

   /** 
     * Record process start
     * @param namespace Process namespace
     * @param name Process name
     * @return Unique process ID
     * @throws Exception On error or invalid argument is specified
     */
    public long recordStartTime(String namespace, String name) throws Exception;

    /** 
     * Record process start
     * @param namespace Process namespace
     * @param name Process name
     * @param status Process status
     * @return Unique process ID
     * @throws Exception On error or invalid argument is specified
     */
    public long recordStartTime(String namespace, String name, String status) throws Exception;

    /** 
     * Record process child start 
     * @param parentId Parent process ID
     * @param namespace Child process namespace
     * @param name Child process name
     * @return Unique child process ID
     * @throws Exception On error or invalid argument is specified
     */
    public long recordChildStartTime(long parentId, String namespace, String name, String status) throws Exception;

    /** 
     * Record process child start 
     * @param parentId Parent process ID
     * @param namespace Child process namespace
     * @param name Child process name
     * @return Unique child process ID
     * @throws Exception On error or invalid argument is specified
     */
    public long recordChildStartTime(long parentId, String namespace, String name) throws Exception;

    /** 
     * Record process end 
     * @param id Process ID
     * @param status Process status
     * @throws Exception On error or invalid argument is specified
     */
    public void recordEndTime(long id) throws Exception;

    /** 
     * Record process end 
     * @param id Process ID
     * @param status Process status
     * @throws Exception On error or invalid argument is specified
     */
    public void recordEndTime(long id, String status) throws Exception;

    /** 
     * Record process status 
     * @param id Process ID
     * @param status Process status
     * @throws Exception On error or invalid argument is specified
     */
    public void recordStatus(long id, String status) throws Exception;

    /** 
     * Record process activity 
     * @param id Process ID
     * @param domain Activity domain
     * @param node Activity node
     * @param msgType Activity message
     * @param msg Activity message 
     * @throws Exception On error or invalid argument is specified
     */
    public void recordActivity(long id, String domain, String node, String msgType, String msg) throws Exception;
    
    /** 
     * Delete process recording
     * @param id Process ID
     * @throws Exception On error or invalid argument is specified
     */
    public void deleteRecord(long id) throws Exception;

    /** 
     * Delete all recordings that ended prior to specified date 
     * @param cutOffTime Delete recordings prior to cut off time
     * @throws Exception On error or invalid argument is specified
     */
    public void purgeRecords(java.sql.Date cutOffTime) throws Exception;
    
    /** 
     * Get entire process recording (includes any children recordings also) 
     * @param id Process ID
     * @return Entire process recording
     * @throws Exception On error or invalid argument is specified
     */
    public ExecutionRecord getRecording(long id) throws Exception;

    /** 
     * Get process status
     * @param id Process ID
     * @return Process status
     * @throws Exception On error or invalid argument is specified
     */
    public String getStatus(long id) throws Exception;

    /** 
     * Get process statuses
     * @param namespace Process namespace
     * @param name Process name
     * @return List of statuses
     * @throws Exception On error or invalid argument is specified
     */
    public SortedSet getRecordings(String namespace, String name) throws Exception;

    /** 
     * Get process statuses
     * @param namespace Process namespace
     * @return Last execution record
     * @throws Exception On error or invalid argument is specified
     */
    public ExecutionRecord getLastRecording(String namespace) throws Exception;

    /** 
     * Get process statuses
     * @param namespace Process namespace
     * @param name Process name
     * @return Last execution record for named process
     * @throws Exception On error or invalid argument is specified
     */
    public ExecutionRecord getLastRecording(String namespace, String name) throws Exception;
    
    /** 
     * Get process statuses
     * @param id Process id
     * @return All child recordings
     * @throws Exception On error or invalid argument is specified
     */
    public SortedSet getChildRecordings(long parent) throws Exception;
    
    /** 
     * Get Parent process statuses
     * @param id Process id
     * @return Parent execution record
     * @throws Exception On error or invalid argument is specified
     */
    public ExecutionRecord getParentRecording(long child) throws Exception;
    
    /** 
     * Get process activity list 
     * @param id Process ID
     * @return Process activity list
     * @throws Exception On error or invalid argument is specified
     */
    public SortedSet getActivity(long id) throws Exception;
    
    
    // method added by Rahul to getNames 
    
    public SortedSet getNames(String namespace) throws Exception;

    /**
     * @return
     * @throws Exception On error or invalid argument is specified
     */
    public SortedSet getNamespaces() throws Exception;

}


