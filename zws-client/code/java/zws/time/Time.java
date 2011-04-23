/* file name  : zws\time\Time.java
 * authors    : Sulakshan Shetty
 * created    : 02/13/2006 09:48:00
 * copyright  : Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 *
 * modifications:
 *
 */
package zws.time;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/** 
 * Time interface.
 * @author Sulakshan Shetty
 */
public interface Time extends Serializable
{
    /** 
     * Get year
     * @return Year int value
     */
    public int getYear();

    /** 
     * Get month 
     * @return Month int value
     */
    public int getMonth();

    /** 
     * Get day 
     * @return Day int value 
     */
    public int getDay();

    /** 
     * Get hour 
     * @return Hour int value
     */
    public int getHour();

    /** 
     * Get minute 
     * @return Minute int value
     */
    public int getMinute();

    /** 
     * Get second 
     * @return Second int value
     */
    public int getSecond();

    /** 
     * Get timezone 
     * @return Time zone
     */
    public String getTimeZone();
    
    /** 
     * Return time as Calendar
     * @return Calendar
     */
    public Calendar asCalendar();

    /** 
     * Return time as Date 
     * @return Date
     */
    public Date asDate();
}
