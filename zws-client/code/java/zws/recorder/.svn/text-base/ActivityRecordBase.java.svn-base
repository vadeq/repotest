/* file name  : zws\recorder\ActivityRecordBase.java
 * authors    : Sulakshan Shetty
 * created    : 02/14/2006 12:51:50
 * copyright  : Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 *
 * modifications:
 *
 */
package zws.recorder;

import java.lang.StringBuffer;

import zws.time.Time;
import zws.time.TimeBase;

/** 
 * Impl for Activity interface. 
 * @author Sulakshan Shetty
 */
public class ActivityRecordBase implements ActivityRecord 
{
    /** 
     * Constructor
     */
    public ActivityRecordBase() 
    {
    }

    /** 
     *Get process id this activity belongs to
     * 
     * @return Process ID
     */
    public long getID()
    {
        return m_id;
    }
    
    /** 
     * Get domain
     * 
     * @return Domain
     */
    public String getDomain()
    {
        return m_domain;
    }

    /** 
     * Get node name 
     * 
     * @return Node name
     */
    public String getNodeName()
    {
        return m_nodeName;
    }
    
    /** 
     * Get the time the activity was recorded 
     * 
     * @return Activity timestamp
     */
    public Time getTimestamp()
    {
        return m_time;
    }
    
    /** 
     * Get type of activity 
     * 
     * @return Activity type
     */
    public String getType()
    {
        return m_type;
    }
    
    /** 
     * Get activity message 
     * 
     * @return Activity message
     */
    public String getMessage()
    {
        return m_msg;
    }
    
    public String getNotes()
    {
        return m_notes;
    }    
    
    

    /** 
     * Set activity process id
     * @param id Process id of the process the activity belongs to
     */
    public void setID(long id) 
    {
        m_id = id;
    }

    /** 
     * Set activity domain
     * @param d Activity domain
     */
    public void setDomain(String d) 
    {
        m_domain = d;
    }

    /** 
     * Set activity node name 
     * @param nn Activity node name 
     */
    public void setNodeName(String nn) 
    {
        m_nodeName = nn;
    }

    /** 
     * Set activity timestamp 
     * @param t Time
     */
    public void setTimestamp(Time t) 
    {
        m_time = t;

    }

    /** 
     * Set activity timestamp 
     * @param d Date
     */
    public void setTimestamp(java.util.Date d) 
    {
        m_time = new TimeBase(d);

    }

    /** 
     * Set activity type 
     * @param type Activity type 
     */
    public void setType(String type) 
    {
        m_type = type;
    }

    /** 
     * Set activity message 
     * @param msg Activity message 
     */
    public void setMessage(String msg) 
    {
        m_msg = msg;
    }
    
    public void setNotes(String notes) 
    {
        m_notes = notes;
    }

    /** 
     * Stringify this instance 
     * @return String representation of instance
     */
    public String toString()
    {
        return toString(0);
    }

    /** 
     * Stringify this instance 
     * @return String representation of instance
     */
    String toString(int level)
    {
        StringBuffer tab = new StringBuffer();
        for (int l = 0; l < level; l++)
        {
            tab.append("  ");
        }

        StringBuffer buff = new StringBuffer();

        buff.append(tab).append("activity|id = ").append(m_id);
        buff.append("|domain = ").append(m_domain);
        buff.append("|node = ").append(m_nodeName);
        buff.append("|time = ").append(m_time);
        buff.append("|type = ").append(m_type);
        buff.append("|msg = ").append(m_msg);
        buff.append("|notes = ").append(m_notes);

        return buff.toString();
    }

    private long m_id;
    private String m_domain;
    private String m_nodeName;
    private Time m_time;
    private String m_type;
    private String m_msg;
    private String m_notes;
    
}
