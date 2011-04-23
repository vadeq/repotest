/* file name  : zws\recorder\RecordComparator.java
 * authors    : Sulakshan Shetty
 * created    : 02/13/2006 10:51:34
 * copyright  : Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 *
 * modifications:
 *
 */
package zws.recorder;

import java.util.Comparator;
import java.io.Serializable;

/** 
 * RecordComparator compares two ExecutionRecord OR ActivityRecord objects based on 
 * the start time or timestamp
 * @author Sulakshan Shetty
 */
public class RecordComparator implements Comparator, Serializable
{
    /** 
     * Constructor
     */
    public RecordComparator() 
    {
    }
    
    /** 
     * Compare two ExecutionRecord or ActivityRecord objects 
     * @param obj1 ExecutionRecord or ActivityRecord object to compare
     * @param obj2 ExecutionRecord or ActivityRecord object to compare
     * @return -1 if obj1 &lt; obj2 OR 0 if obj1 == obj2 OR +1 if obj1 &gt; obj2
     */
    public int compare(Object obj1, Object obj2) 
    {
        if (obj1 instanceof ExecutionRecord && obj2 instanceof ExecutionRecord)
        { return compare((ExecutionRecord) obj1, (ExecutionRecord) obj2); }
        else if (obj1 instanceof ActivityRecord && obj2 instanceof ActivityRecord)
        { return compare((ActivityRecord) obj1, (ActivityRecord) obj2); }
        return 1;
    }

    /** 
     * Compare two ExecutionRecord objects 
     * @param er1 Execution record 1
     * @param er2 Execution record 2
     * @return -1 if obj1 &lt; obj2 OR 0 if obj1 == obj2 OR +1 if obj1 &gt; obj2
     */
    public int compare(ExecutionRecord er1, ExecutionRecord er2) 
    {
        long time1 = 0, time2 = 0;

        if (er1 != null && er1.getDuration() != null && er2.getDuration().getStartTime() != null)
        {
            time1 = er1.getDuration().getStartTime().asCalendar().getTimeInMillis();
        }

        if (er2 != null && er2.getDuration() != null && er2.getDuration().getStartTime() != null)
        {
            time2 = er2.getDuration().getStartTime().asCalendar().getTimeInMillis();
        }

        return (time1 < time2 ? 1 : -1);
    }

    /** 
     * Compare two ActivityRecord objects 
     * @param ar1 Activity record 1
     * @param ar2 Activity record 2
     * @return -1 if obj1 &lt; obj2 OR 0 if obj1 == obj2 OR +1 if obj1 &gt; obj2
     */
    public int compare(ActivityRecord ar1, ActivityRecord ar2) 
    {
        long time1 = 0, time2 = 0;

        if (ar1 != null && ar1.getTimestamp() != null)
        {
            time1 = ar1.getTimestamp().asCalendar().getTimeInMillis();
        }

        if (ar2 != null && ar2.getTimestamp() != null)
        {
            time2 = ar2.getTimestamp().asCalendar().getTimeInMillis();
        }

        return (time1 < time2 ? 1 : -1);
    }
}
