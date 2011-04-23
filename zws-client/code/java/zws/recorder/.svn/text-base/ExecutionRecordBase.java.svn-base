/* file name  : zws\recorder\ExecutionRecordBase.java
 * authors    : Sulakshan Shetty
 * created    : 02/13/2006 10:39:41
 * copyright  : Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 *
 * modifications:
 *
 */
package zws.recorder;

import java.util.SortedSet;
import java.util.TreeSet;
import java.lang.StringBuffer;
import java.util.Iterator;

import zws.time.Duration;
import zws.time.Time;

/** 
 * Impl for ExecutionRecord
 * @author Sulakshan Shetty
 */
public class ExecutionRecordBase implements ExecutionRecord 
{
    /** 
     * Constructor 
     */
    public ExecutionRecordBase() {
    }

    /** 
     * Constructor 
     * 
     * @param id Process ID
     * @param namespace Process namespace
     * @param name Process name
     */
    public ExecutionRecordBase(long id, String namespace, String name) 
    {
        m_id = id;
        m_namespace = namespace;
        m_name = name;
    }
 
    /** 
     * Get process id
     * @return Process ID
     */
    public long getID()
    {
        return m_id;
    }

    /** 
     * Get process namespace
     * @return Process namspace
     */
    public String getNamespace()
    {
        return m_namespace;
    }

    /** 
     * Get process name 
     * @return Process name
     */
    public String getName()
    {
        return m_name;
    }

    /** 
     * Get process status
     * @return Process status
     */
    public String getStatus()
    {
        return m_status;
    }

    /** 
     * Get process Desription
     * @return Process description
     */
    public String getDescription()
    {
      if (null==m_description) m_description ="ID="+getID();
        return m_description;
    }
    
    public String getNotes()
    {
        return m_notes;
    }

    /** 
     * Get process duration 
     * @return Process duration
     */
    public Duration getDuration()
    {
        return m_duration;
    }


    /** 
     * Get process duration 
     * @return Process duration
     */
    public Time getStartTime()
    {
        return getDuration().getStartTime();
    }
    
    
    /** 
     * Get process activity list 
     * @return Process activity list
     */
    public SortedSet getActivity()
    {
        return m_activityList;
    }

    /** 
     * Get process children list
     * @return Process children list
     */
    public SortedSet children()
    {
        return m_children;
    }
    
    /** 
     * Set process ID
     * @param id Process ID
     */
    public void setID(long id) 
    {
        m_id = id;
    }
    
    /** 
     * Set process namespace
     * @param namespace Process namespace
     */
    public void setNamespace(String namespace) 
    {
        m_namespace = namespace;
    }

    /** 
     * Set process name
     * @param name Process name 
     */
    public void setName(String name) 
    {
        m_name = name;
    }
    
    /** 
     * Set process status
     * @param status Process status 
     */
    public void setStatus(String status) 
    {
        m_status = status;
    }

    /** 
     * Get process Desription
     * @return Process description
     */
    public void setDescription(String s) {
      m_description=s;
    }
    public void setNotes(String s) {
      m_notes=s;
  }    
    
    /** 
     * Set process duration
     * @param dur Process duration 
     */
    public void setDuration(Duration dur) 
    {
        m_duration = dur;
    }

    /** 
     * Add activity to process activity list
     * @param a Activity record to add
     */
    public void add(ActivityRecord a) 
    {
        if (m_activityList == null) m_activityList = new TreeSet(new RecordComparator());

        m_activityList.add(a);
    }

    /** 
     * Set activities for this record
     * @param a 
     */
    public void setActivity(TreeSet a) 
    {
        m_activityList = a;
    }

    /** 
     * Add child process to process
     * @param e Child process to add 
     */
    public void add(ExecutionRecord e) 
    {
        if (m_children == null) m_children = new TreeSet(new RecordComparator());

        m_children.add(e);
    }

    /** 
     * Stringify this instance 
     * @return String representation of this instance
     */
    public String toString()
    {
        return toString(0);
    }

    /** 
     * Stringify this instance 
     * @param level Level at which the record occurs
     * @return Pretty string representation of this instance
     */
    private String toString(int level)
    {
        StringBuffer tab = new StringBuffer();
        for (int l = 0; l < level; l++) tab.append("  ");

        StringBuffer buff = new StringBuffer();

        buff.append(tab).append("process|id = ").append(m_id);
        buff.append("|namespace = ").append(m_namespace);
        buff.append("|name = ").append(m_name);
        buff.append("|status = ").append(m_status);
        buff.append("|duration = ").append(m_duration);

        // append activity information
        if (m_activityList != null && m_activityList.size() > 0)
        {
            for (Iterator ai = m_activityList.iterator(); ai.hasNext();)
            {
                buff.append("\n");
                buff.append(((ActivityRecordBase) ai.next()).toString(level + 1));
            }
        }

        // append children records information
        if (m_children != null && m_children.size() > 0)
        {
            for (Iterator ci = m_children.iterator(); ci.hasNext();)
            {
                buff.append("\n");
                buff.append(((ExecutionRecordBase) ci.next()).toString(level + 1));
            }
        }

        return buff.toString();
    }

    private long m_id;
    private String m_namespace;
    private String m_name;
    private String m_status;
    private String m_description;
    private String m_notes;
    private Duration m_duration;
    private TreeSet m_activityList;
    private TreeSet m_children;
}
