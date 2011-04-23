/* file name  : zws\service\recorder\RecorderService.java
 * authors    : Sulakshan Shetty
 * created    : 02/13/2006 11:23:12
 * copyright  : Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 *
 * modifications:
 *
 */
package zws.service.recorder;

import java.util.Date;
import java.util.SortedSet;
import zws.recorder.ExecutionRecord;

import java.rmi.RemoteException;

/** 
 * RecorderService interface. 
 * @author Sulakshan Shetty
 */
public interface RecorderService 
{
    /** 
     * Record process start
     * @param namespace Process namespace
     * @param name Process name
     * @param status Process status
     * @param dotedDate timestamp to record
     * @return Unique process ID
     * @throws Exception On error or invalid argument is specified
     */
    public long stampStartTime(String namespace, String name, String status, String dotedDate) throws RemoteException;

    /** 
     * Record process start
     * @param namespace Process namespace
     * @param name Process name
     * @param status Process status
     * @param dotedDate timestamp to record
     * @return Unique process ID
     * @throws Exception On error or invalid argument is specified
     */
    public long stampStartTime(String namespace, String name, String status, String dotedDate, String description) throws RemoteException;
    
    /** 
     * Record process start
     * @param namespace Process namespace
     * @param name Process name
     * @param dotedDate timestamp to record
     * @return Unique process ID
     * @throws Exception On error or invalid argument is specified
     */
    public long stampStartTime(String namespace, String name, String dotedDate) throws RemoteException;

   /** 
     * Record process start
     * @param namespace Process namespace
     * @param name Process name
     * @return Unique process ID
     * @throws RemoteException On error or invalid argument is specified
     */
    public long recordStartTime(String namespace, String name) throws RemoteException;

    /** 
     * Record process start
     * @param namespace Process namespace
     * @param name Process name
     * @param status Process status
     * @return Unique process ID
     * @throws RemoteException On error or invalid argument is specified
     */
    public long recordStartTime(String namespace, String name, String status) throws RemoteException;

    /** 
     * Record process start
     * @param namespace Process namespace
     * @param name Process name
     * @param status Process status
     * @return Unique process ID
     * @throws RemoteException On error or invalid argument is specified
     */
    public long recordStartTime(String namespace, String name, String status, String description) throws RemoteException;

    /** 
     * Record process child start 
     * @param parentId Parent process ID
     * @param namespace Child process namespace
     * @param name Child process name
     * @return Unique child process ID
     * @throws RemoteException On error or invalid argument is specified
     */
    public long recordChildStartTime(long parentId, String namespace, String name, String status, String description) throws RemoteException;
    
    /** 
     * Record process child start 
     * @param parentId Parent process ID
     * @param namespace Child process namespace
     * @param name Child process name
     * @return Unique child process ID
     * @throws RemoteException On error or invalid argument is specified
     */
    public long recordChildStartTime(long parentId, String namespace, String name, String status) throws RemoteException;

    /** 
     * Record process child start 
     * @param parentId Parent process ID
     * @param namespace Child process namespace
     * @param name Child process name
     * @return Unique child process ID
     * @throws RemoteException On error or invalid argument is specified
     */
    public long recordChildStartTime(long parentId, String namespace, String name) throws RemoteException;

    /** 
     * Record process end 
     * @param id Process ID
     * @param status Process status
     * @throws RemoteException On error or invalid argument is specified
     */
    public void recordEndTime(long id) throws RemoteException;

    /** 
     * Record process end 
     * @param id Process ID
     * @param status Process status
     * @throws RemoteException On error or invalid argument is specified
     */
    public void recordEndTime(long id, String status) throws RemoteException;

    /** 
     * Record process status 
     * @param id Process ID
     * @param status Process status
     * @throws RemoteException On error or invalid argument is specified
     */
    public void recordStatus(long id, String status) throws RemoteException;

    /** 
     * Record process activity 
     * @param id Process ID
     * @param domain Activity domain
     * @param node Activity node
     * @param msgType Activity message
     * @param msg Activity message 
     * @throws RemoteException On error or invalid argument is specified
     */
    public void recordActivity(long id, String domain, String node, String msgType, String msg) throws RemoteException;
    
    /** 
     * Delete process recording
     * @param id Process ID
     * @throws RemoteException On error or invalid argument is specified
     */
    public void deleteRecord(long id) throws RemoteException;

    /** 
     * Delete all recordings that ended prior to specified date 
     * @param cutOffTime Delete recordings prior to cut off time
     * @throws RemoteException On error or invalid argument is specified
     */
    public void purgeRecords(Date cutOffTime) throws RemoteException;
    
    /** 
     * Get entire process recording (includes any children recordings also) 
     * @param id Process ID
     * @return Entire process recording
     * @throws RemoteException On error or invalid argument is specified
     */
    public ExecutionRecord getRecording(long id) throws RemoteException;

    /** 
     * Get process status
     * @param id Process ID
     * @return Process status
     * @throws RemoteException On error or invalid argument is specified
     */
    public String getStatus(long id) throws RemoteException;

    /** 
     * Get process statuses
     * @param namespace Process namespace
     * @param name Process name
     * @return List of statuses
     * @throws RemoteException On error or invalid argument is specified
     */
    public SortedSet getRecordings(String namespace, String name) throws RemoteException;

    /** 
     * Get process statuses
     * @param namespace Process namespace
     * @return Last execution record
     * @throws RemoteException On error or invalid argument is specified
     */
    public ExecutionRecord getLastRecording(String namespace) throws RemoteException;

    /** 
     * Get process statuses
     * @param namespace Process namespace
     * @param name Process name
     * @return Last execution record for named process
     * @throws RemoteException On error or invalid argument is specified
     */
    public ExecutionRecord getLastRecording(String namespace, String name) throws RemoteException;
    
    /** 
     * Get process statuses
     * @param id Process id
     * @return All child recordings
     * @throws RemoteException On error or invalid argument is specified
     */
    public SortedSet getChildRecordings(long parent) throws RemoteException;
    
    /** 
     * Get Parent process statuses
     * @param id Process id
     * @return Parent execution record
     * @throws RemoteException On error or invalid argument is specified
     */
    public ExecutionRecord getParentRecording(long child) throws RemoteException;
    
    /** 
     * Get process activity list 
     * @param id Process ID
     * @return Process activity list
     * @throws RemoteException On error or invalid argument is specified
     */
    public SortedSet getActivity(long id) throws RemoteException;
    
    
    // method added by Rahul to getNames 
    
    public SortedSet getNames(String namespace) throws RemoteException;

    /**
     * @return
     * @throws RemoteException On error or invalid argument is specified
     */
    public SortedSet getNamespaces() throws RemoteException;

   
    
}
