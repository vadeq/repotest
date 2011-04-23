/* file name  : zws\time\Duration.java
 * authors    : Sulakshan Shetty
 * created    : 02/14/2006 11:05:01
 * copyright  : Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 *
 * modifications:
 *
 */
package zws.time;

import java.io.Serializable;

/** 
 * Class to record time elapsed between start and end time. 
 * 
 * @author Sulakshan Shetty
 */
public interface Duration extends Serializable
{
    /**
     * Get the start time
     * @return Start time if set, null otherwise
     */
    public Time getStartTime();

    /**
     * Get the end time 
     * @return End time if set, null otherwise
     */
    public Time getEndTime();

    /**
     * Get number of weeks elapsed between start and end times
     * @return Number of weeks elapsed between start and end times
     */
    public int getWeeks();

    /**
     * Get number of days between start and end times
     * @return Number of days elapsed between start and end times
     */
    public int getDays();

    /**
     * Get the number of hours between start and end times 
     * @return Number of hours elapsed between start and end times
     */
    public int getHours();

    /**
     * Get the number of minutes between start and end times 
     * @return Number of minutes elapsed between start and end times
     */
    public int getMinutes();

    /**
     * Get the number of seconds between start and end times 
     * @return Number of seconds elapsed between start and end times
     */
    public int getSeconds();
}
