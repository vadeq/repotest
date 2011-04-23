/* file name  : zws\service\recorder\RecorderService.java
 * authors    : Sulakshan Shetty
 * created    : 02/13/2006 11:23:12
 * copyright  : Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 *
 * modifications:
 *
 */
package zws.service.recorder.qx;

import java.util.Date;
import java.util.SortedSet;
import zws.recorder.ExecutionRecord;

import java.rmi.RemoteException;

/**
 * RecorderService interface.
 * @author Sulakshan Shetty
 */
public interface RecorderService {
    /**
     * Record process start.
     * @param namespace Process namespace
     * @param name Process name
     * @param status Process status
     * @param dotedDate timestamp to record
     * @throws RemoteException On error or invalid argument is specified
     */
    Long stampStartTime(String namespace, String name, String status, String dotedDate) throws RemoteException;

    /**
     * Record process start.
     * @param namespace Process namespace
     * @param name Process name
     * @param status Process status
     * @param dotedDate timestamp to record
     * @param description description
     * @throws RemoteException On error or invalid argument is specified
     */
    Long stampStartTime(String namespace, String name, String status, String dotedDate, String description) throws RemoteException;

    /**
     * Record process start.
     * @param namespace Process namespace
     * @param name Process name
     * @param status Process status
     * @param dotedDate timestamp to record
     * @param description description
     * @param notes notes
     * @throws RemoteException On error or invalid argument is specified
     */
    Long stampStartTime(String namespace, String name, String status, String dotedDate, String description, String notes) throws RemoteException;

    /**
     * Record process start.
     * @param namespace Process namespace
     * @param name Process name
     * @param dotedDate timestamp to record
     * @throws RemoteException On error or invalid argument is specified
     */
    Long stampStartTime(String namespace, String name, String dotedDate) throws RemoteException;

   /**
     * Record process start.
     * @param namespace Process namespace
     * @param name Process name
     * @throws RemoteException On error or invalid argument is specified
     */
    Long recordStartTime(String namespace, String name) throws RemoteException;

    /**
     * Record process start.
     * @param namespace Process namespace
     * @param name Process name
     * @param status Process status
     * @throws RemoteException On error or invalid argument is specified
     */
    Long recordStartTime(String namespace, String name, String status) throws RemoteException;

    /**
     * Record process start.
     * @param namespace Process namespace
     * @param name Process name
     * @param status Process status
     * @param description description
     * @throws RemoteException On error or invalid argument is specified
     */
    Long recordStartTime(String namespace, String name, String status, String description) throws RemoteException;


    /**
     * Record process start.
     * @param namespace Process namespace
     * @param name Process name
     * @param status Process status
     * @param description description
     * @param notes notes
     * @throws RemoteException On error or invalid argument is specified
     */
    Long recordStartTime(String namespace, String name, String status, String description, String notes) throws RemoteException;

    
    /**
     * Record process child start.
     * @param parentId Parent process ID
     * @param namespace Child process namespace
     * @param name Child process name
     * @param status status
     * @param description description
     * @return Unique child process ID
     * @throws RemoteException On error or invalid argument is specified
     */
    Long recordChildStartTime(Long parentId, String namespace, String name, String status, String description) throws RemoteException;

    /**
     * Record process child start.
     * @param parentId Parent process ID
     * @param namespace Child process namespace
     * @param name Child process name
     * @param status status
     * @param description description
     * @param notes notes 
     * @return Unique child process ID
     * @throws RemoteException On error or invalid argument is specified
     */
    Long recordChildStartTime(Long parentId, String namespace, String name, String status, String description, String notes) throws RemoteException;

    /**
     * Record process child start.
     * @param parentId Parent process ID
     * @param namespace Child process namespace
     * @param name Child process name
     * @param status status
     * @return Unique child process ID
     * @throws RemoteException On error or invalid argument is specified
     */
    Long recordChildStartTime(Long parentId, String namespace, String name, String status) throws RemoteException;

    /**
     * Record process child start.
     * @param parentId Parent process ID
     * @param namespace Child process namespace
     * @param name Child process name
     * @throws RemoteException On error or invalid argument is specified
     */
    Long recordChildStartTime(Long parentId, String namespace, String name) throws RemoteException;

    /**
     * Record process end.
     * @param id Process ID
     * @throws RemoteException On error or invalid argument is specified
     */
    void recordEndTime(Long id) throws RemoteException;

    /**
     * Record process end.
     * @param id Process ID
     * @param status Process status
     * @throws RemoteException On error or invalid argument is specified
     */
    void recordEndTime(Long id, String status) throws RemoteException;

    /**
     * Record process status.
     * @param id Process ID
     * @param status Process status
     * @throws RemoteException On error or invalid argument is specified
     */
    void recordStatus(Long id, String status) throws RemoteException;

    /**
     * Record process activity.
     * @param id Process ID
     * @param domain Activity domain
     * @param node Activity node
     * @param msgType Activity message
     * @param msg Activity message
     * @throws RemoteException On error or invalid argument is specified
     */
    void recordActivity(Long id, String domain, String node, String msgType, String msg) throws RemoteException;
    /**
     * Record process activity.
     * @param id Process ID
     * @param domain Activity domain
     * @param node Activity node
     * @param msgType Activity message
     * @param msg Activity message
     * @param notes Activity notes
     * @throws RemoteException On error or invalid argument is specified
     */
    void recordActivity(Long id, String domain, String node, String msgType, String msg, String notes) throws RemoteException;
    

    /**
     * Delete process recording.
     * @param id Process ID
     * @throws RemoteException On error or invalid argument is specified
     */
    void deleteRecord(Long id) throws RemoteException;

    /**
     * Delete all recordings that ended prior to specified date.
     * @param cutOffTime Delete recordings prior to cut off time
     * @throws RemoteException On error or invalid argument is specified
     */
    void purgeRecords(Date cutOffTime) throws RemoteException;

    /**
     * Get entire process recording (includes any children recordings also).
     * @param id Process ID
     * @return Entire process recording
     * @throws RemoteException On error or invalid argument is specified
     */
    ExecutionRecord getRecording(Long id) throws RemoteException;

    /**
     * Get process status.
     * @param id Process ID
     * @return Process status
     * @throws RemoteException On error or invalid argument is specified
     */
    String getStatus(Long id) throws RemoteException;

    /**
     * Get process statuses.
     * @param namespace Process namespace
     * @param name Process name
     * @return List of statuses
     * @throws RemoteException On error or invalid argument is specified
     */
    SortedSet getRecordings(String namespace, String name) throws RemoteException;
    
    SortedSet getRecordingsByStatus(String namespace, String status) throws RemoteException;

    /**
     * Get process statuses.
     * @param namespace Process namespace
     * @return Last execution record
     * @throws RemoteException On error or invalid argument is specified
     */
    ExecutionRecord getLastRecording(String namespace) throws RemoteException;

    /**
     * Get process statuses.
     * @param namespace Process namespace
     * @param name Process name
     * @return Last execution record for named process
     * @throws RemoteException On error or invalid argument is specified
     */
    ExecutionRecord getLastRecording(String namespace, String name) throws RemoteException;

    /**
     * Get process statuses.
     * @param parent Process id
     * @return All child recordings
     * @throws RemoteException On error or invalid argument is specified
     */
    SortedSet getChildRecordings(Long parent) throws RemoteException;

    /**
     * Get Parent process statuses.
     * @param child Process id
     * @return Parent execution record
     * @throws RemoteException On error or invalid argument is specified
     */
    ExecutionRecord getParentRecording(Long child) throws RemoteException;

    /**
     * Get process activity list.
     * @param id Process ID
     * @return Process activity list
     * @throws RemoteException On error or invalid argument is specified
     */
    SortedSet getActivity(Long id) throws RemoteException;


    /**
     * @param namespace namespace
     * @return Names
     * @throws RemoteException RemoteException
     */
    SortedSet getNames(String namespace) throws RemoteException;

    /**
     * @return  Namespaces
     * @throws RemoteException On error or invalid argument is specified
     */
    SortedSet getNamespaces() throws RemoteException;
}
