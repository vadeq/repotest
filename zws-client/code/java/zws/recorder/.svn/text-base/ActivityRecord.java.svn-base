/* file name  : zws\recorder\ActivityRecord.java
 * authors    : Sulakshan Shetty
 * created    : 02/13/2006 10:27:29
 * copyright  : Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 *
 * modifications:
 *
 */
package zws.recorder;

import java.io.Serializable;

import zws.time.Time;

/** 
 * ActivityRecord interface 
 * 
 * @author Sulakshan Shetty
 */
public interface ActivityRecord extends Serializable
{
    
    /** 
     *Get process id this activity belongs to
     * 
     * @return Process ID
     */
    public long getID();

    /** 
     * Get domain
     * @return Domain
     */
    public String getDomain();
    
    /** 
     * Get node name 
     * 
     * @return Node name
     */
    public String getNodeName();
    
    /** 
     * Get the time the activity was recorded 
     * 
     * @return Activity timestamp
     */
    public Time getTimestamp();
    
    /** 
     * Get type of activity 
     * 
     * @return Activity type
     */
    public String getType();
    
    /** 
     * Get activity message 
     * 
     * @return Activity message
     */
    public String getMessage();
}
