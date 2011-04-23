/* file name  : zws\time\TimeBase.java
 * authors    : Sulakshan Shetty
 * created    : 02/13/2006 09:51:28
 * copyright  : Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 *
 * modifications:
 *
 */
package zws.time;

import java.util.*;
import java.util.Calendar;
import java.util.Date;
import java.lang.StringBuffer;

/** 
 * Impl of Time interface 
 * @author Sulakshan Shetty
 */
public class TimeBase implements Time
{
    /** 
     * Constructor 
     */
    public TimeBase() 
    {
    }

    /** 
     * Constructor 
     * @param d 
     */
    public TimeBase(Date d)
    {
        m_calendar = Calendar.getInstance();
        m_calendar.setTime(d);
    }

    /** 
     * Constructor
     * @param c 
     */
    public TimeBase(Calendar c)
    {
        m_calendar = c;
    }
    
    /** 
     * Get year
     * @return Year int value or -1 if not initialized
     */
    public int getYear()
    {
        if (m_calendar == null) return -1;

        return m_calendar.get(Calendar.YEAR);
    }

    /** 
     * Get month 
     * @return Month int value or -1 if not initialized
     */
    public int getMonth()
    {
        if (m_calendar == null) return -1;

        return m_calendar.get(Calendar.MONTH)+1;
    }

    /** 
     * Get day 
     * @return Day int value  or -1 if not initialized
     */
    public int getDay()
    {
        if (m_calendar == null) return -1;

        return m_calendar.get(Calendar.DAY_OF_MONTH);
    }

    /** 
     * Get hour 
     * @return Hour int value or -1 if not initialized
     */
    public int getHour()
    {
       if (m_calendar == null) return -1;
        return m_calendar.get(Calendar.HOUR_OF_DAY);
    }

    /** 
     * Get minute 
     * @return Minute int value or -1 if not initialized
     */
    public int getMinute()
    {
        if (m_calendar == null) return -1;

        return m_calendar.get(Calendar.MINUTE);
    }

    /** 
     * Get second 
     * @return Second int value or -1 if not initialized
     */
    public int getSecond()
    {
        if (m_calendar == null) return -1;
       
        return m_calendar.get(Calendar.SECOND);
    }

    /** 
     * Get timezone 
     * @return Time zone or empty string if not initialized
     */
    public String getTimeZone()
    {
        if (m_calendar == null) return "";

        return m_calendar.getTimeZone().getDisplayName();
    }

    /** 
     * Return time as Calendar
     * @return Calendar or null if not initialized
     */
    public Calendar asCalendar()
    {
	    Calendar c = new GregorianCalendar();
	    c.setTime(m_calendar.getTime());
	    return c;
    }

    /** 
     * Return time as Date 
     * @return Date or null if not initialized
     */
    public Date asDate()
    {
        if (m_calendar == null) return null;

        return new Date(m_calendar.getTimeInMillis());
    }
    
    /** 
     * Set time as per Calendar
     * @param c Calendar to set time by
     */
    public void setTime(Calendar c) 
    {
        m_calendar = c;
    }

    /** 
     * Set time as per Date 
     * @param d Date to set time by 
     */
    public void setTime(Date d) 
    {
        m_calendar.setTime(d);
    }

    /** 
     * 
     * Set time as per arguments 
     * @param yr Year
     * @param mon Month
     * @param day Day
     * @param hr Hour
     * @param min Minute
     * @param sec Seconds
     * @param ms Millisecs
     */
    public void setTime(int yr, int mon, int day, int hr, int min, int sec, int ms) 
    {
        m_calendar.set(yr, mon-1, day, hr, min, sec); // TODO: need to set millisecs
    }

    /** 
     * Stringify this instance 
     * @return String representation of this instance
     */
    public String toString()
    {
        StringBuffer buff = new StringBuffer();

        // format mm/dd/yyyy hh:mm:ss
        buff.append(getMonth()).append("/").append(getDay()).append("/").append(getYear());
        buff.append(" ").append(getHour()).append(":").append(getMinute());
        buff.append(":").append(getSecond());

        return buff.toString();
    }

    /** 
     * Calendar placeholder
     */
    private Calendar m_calendar;
}
