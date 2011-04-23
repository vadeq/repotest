/* file name  : zws\recorder\ExecutionRecordBase.java
 * authors    : Sulakshan Shetty
 * created    : 02/13/2006 10:39:41
 * copyright  : Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 *
 * modifications:
 *
 */
package zws.queue;

import java.util.SortedSet;
import java.util.TreeSet;
import java.lang.StringBuffer;
import java.util.Iterator;

import zws.application.Names;
import zws.time.Duration;
import zws.time.Time;

/** 
 * Impl for ExecutionRecord
 * @author Sulakshan Shetty
 */
public class QxQueueRecordBase implements QxQueueRecord 
{
    /** 
     * Constructor 
     */
    public QxQueueRecordBase() {
    }


    public QxQueueRecordBase(String name){ 
      setQ_name(name);
      setQ_state(Names.STATUS_STARTED);
    }
    public QxQueueRecordBase(String name, String state){ 
      setQ_name(name);
      setQ_state(state);
    }
    public QxQueueRecordBase(String name, String state, String notes){ 
      setQ_name(name);
      setQ_state(state);
      setQ_notes(notes);
    }
 
    public String toString(){
        return toString(0);
    }
    
    private String toString(int level) {
        StringBuffer tab = new StringBuffer();
        for (int l = 0; l < level; l++) {
            tab.append("  ");
        }

        StringBuffer buff = new StringBuffer();
        buff.append(tab).append("queue|name = ").append(q_name);
        buff.append("|state = ").append(q_state);
        buff.append("|notes = ").append(q_notes);
        return buff.toString();
    }
    
    public String getQ_name() {return q_name;}
    public void setQ_name(String s) {q_name = s;}

    public String getQ_state() {return q_state;}
    public void setQ_state(String s) {q_state = s;}

    public String getQ_notes() {return q_notes;}
    public void setQ_notes(String s) {q_notes = s;}    

    private String q_name = null;
    private String q_state = null;
    private String q_notes = null;
   
}
