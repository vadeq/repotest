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
public class QxElementRecordBase implements QxElementRecord 
{
    /** 
     * Constructor 
     */
    public QxElementRecordBase() {}

    public String toString(){
        return toString(0);
    }
    
    private String toString(int level) {
        StringBuffer tab = new StringBuffer();
        for (int l = 0; l < level; l++) {
            tab.append("  ");
        }

        StringBuffer buff = new StringBuffer();
        buff.append(tab).append("queue|id = ").append(id);
        buff.append("|namespace   = ").append(namespace);
        buff.append("|name        = ").append(name);
        buff.append("|priority    = ").append(priority);
        buff.append("|qorder      = ").append(qorder);
        buff.append("|state       = ").append(state);
        buff.append("|context     = ").append(context);
        buff.append("|instruction = ").append(instruction);
        buff.append("|summary     = ").append(summary);
        buff.append("|notes       = ").append(notes);

        return buff.toString();
    }
    
   
    public String getName() {return name;}
    public void setName(String s) {name = s;}

    public String getState() {return state;}
    public void setState(String s) {state = s;}

    public long getId() {return id;}
    public void setId(long s) {id = s;}

    public String getNamespace() {return namespace;}
    public void setNamespace(String s) {namespace = s;}

    public int getPriority() {return priority;}
    public void setPriority(int s) {priority = s; }

    public int getQorder() {return qorder;}
    public void setQorder(int s) {qorder = s;}

    public String getContext() {return context;}
    public void setContext(String s) {context = s;}

    public String getInstruction() {return instruction;}
    public void setInstruction(String s) {instruction = s;}

    public String getSummary() {return summary;}
    public void setSummary(String s) {summary = s;}


    public String getNotes() {return notes;}
    public void setNotes(String s) {notes = s;}

    private String name = null;
    private String state = null;
    private long id = 0;
    private String namespace = null;
    private int priority = 0;
    private int qorder = 0;
    private String context = null;
    private String instruction = null;
    private String summary = null;
    private String notes = null;
    public QxElementRecord archive(long qid) {
      return null;
    }

   
}
