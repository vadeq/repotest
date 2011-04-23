/* file name  : zws\time\DurationBase.java
 * authors    : Sulakshan Shetty
 * created    : 02/13/2006 09:30:46
 * copyright  : Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 *
 * modifications:
 *
 */
package zws.time;


import java.lang.StringBuffer;

/** 
 * Impl class for interface Duration. 
 * @author Sulakshan Shetty
 */
public class DurationBase implements Duration 
{

    public static final long MSECS_IN_SEC  = 1000;
    public static final long MSECS_IN_MIN  = MSECS_IN_SEC * 60;
    public static final long MSECS_IN_HOUR = MSECS_IN_MIN * 60;
    public static final long MSECS_IN_DAY  = MSECS_IN_HOUR * 24;
    public static final long MSECS_IN_WEEK = MSECS_IN_DAY * 7;

    /** 
     * Constructor.
     */
    public DurationBase() 
    {
    }

    /** 
     * Constructor. 
     * @param startTime 
     */
    public DurationBase(java.util.Date startTime) 
    {
        if (startTime != null) setStartTime(new TimeBase(startTime));
    }

    /** 
     * 
     * @param startTime 
     * @param endTime 
     */
    public DurationBase(java.util.Date startTime, java.util.Date endTime) 
    {
        if (startTime != null) setStartTime(new TimeBase(startTime));
        if (endTime != null) setEndTime(new TimeBase(endTime));
    }

    /** 
     * Set duration start time 
     * @param startTime Start time
     */
    public void setStartTime(Time startTime)
    {
        m_startTime = startTime;
    }

    /** 
     * Set duration end time 
     * @param endTime End time
     */
    public void setEndTime(Time endTime)
    {
        m_endTime = endTime;
    }

    /**
     * Get the start time
     * @return Start time if set, null otherwise
     */
    public Time getStartTime()
    {
        return m_startTime;
    }

    /**
     * Get the end time 
     * @return End time if set, null otherwise
     */
    public Time getEndTime()
    {
        return m_endTime;
    }

    /**
     * Get number of weeks elapsed between start and end times
     * @return Number of weeks elapsed between start and end times
     */
    public int getWeeks()
    {
        return (int) (getDurationInMillis() / MSECS_IN_WEEK);
    }

    /**
     * Get number of days between start and end times
     * @return Number of days elapsed between start and end times
     */
    public int getDays()
    {
        return  (int) ((getDurationInMillis() % MSECS_IN_WEEK) / MSECS_IN_DAY);
    }

    /**
     * Get the number of hours between start and end times 
     * @return Number of hours elapsed between start and end times
     */
    public int getHours()
    {
        return (int) ((getDurationInMillis() % MSECS_IN_DAY) / MSECS_IN_HOUR);
    }

    /**
     * Get the number of minutes between start and end times 
     * @return Number of minutes elapsed between start and end times
     */
    public int getMinutes()
    {
        return (int) ((getDurationInMillis() % MSECS_IN_HOUR) / MSECS_IN_MIN);
    }

    /**
     * Get the number of seconds between start and end times 
     * @return Number of seconds elapsed between start and end times
     */
    public int getSeconds()
    {
        return (int) ((getDurationInMillis() % MSECS_IN_MIN) / MSECS_IN_SEC);
    }

    /** 
     * Stringify this instance 
     * @return String representation of this instance
     */
    public String toString() {
        StringBuffer buff = new StringBuffer();
        if (0 < getWeeks()) buff.append(getWeeks()).append("w ");
        if (0 < getDays()) buff.append(getDays()).append("d ");
        if (0 < getHours()) buff.append(getHours()).append("h ");
        if (0 < getMinutes()) buff.append(getMinutes()).append("m ");
        buff.append(getSeconds()).append("s");
        return buff.toString();
    }

    /** 
     * Get duration in milliseconds 
     * @return Duration in millisecs
     */
    public long getDurationInMillis()
    {
        long startTime = System.currentTimeMillis();
        long endTime = startTime;

        if (m_startTime != null && m_startTime.asCalendar() != null)
        { startTime = m_startTime.asCalendar().getTimeInMillis(); }

        if (m_endTime != null && m_endTime.asCalendar() != null)
        { endTime = m_endTime.asCalendar().getTimeInMillis(); }

        return endTime - startTime;
    }

    /** 
     * Duration start time
     */
    private Time m_startTime = null;

    /** 
     * Duration end time
     */
    private Time m_endTime = null;
}
