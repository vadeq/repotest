/* file name  : zws\recorder\ExecutionRecord.java
 * authors    : Sulakshan Shetty
 * created    : 02/13/2006 10:36:38
 * copyright  : Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 *
 * modifications:
 *
 */
package zws.recorder;


import java.util.SortedSet;
import java.io.Serializable;

import zws.time.Duration;
import zws.time.Time;

/** 
 * ExecutionRecord interface. 
 * @author Sulakshan Shetty
 */
public interface ExecutionRecord extends Serializable
{
    /** 
     * Get process id
     * @return Process ID
     */
    public long getID();

    /** 
     * Get process namespace
     * @return Process namespace
     */
    public String getNamespace();

    /** 
     * Get process name 
     * @return Process name
     */
    public String getName();

    /** 
     * Get process status
     * @return Process status
     */
    public String getStatus();

    public String getDescription();
    public String getNotes();
    
    
    /** 
     * Get process duration 
     * @return Process duration
     */
    public Duration getDuration();


    /** 
     * Get process duration 
     * @return Process duration
     */
    public Time getStartTime();
    /** 
     * Get process activity list 
     * @return Process activity list
     */
    public SortedSet getActivity();

    /** 
     * Get process children list
     * @return Process children list
     */
    public SortedSet children();
}
