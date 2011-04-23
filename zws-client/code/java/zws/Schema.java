package zws; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import zws.time.DurationBase;
import zws.queue.QxElementRecordBase;
import zws.queue.QxElementComparator;
import zws.queue.QxQueueRecordBase;
import zws.recorder.ExecutionRecordBase;
import zws.recorder.ActivityRecordBase;
import zws.recorder.RecordComparator;


/*

 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Apr 2, 2007
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */


/** 
 * Holds DB constants and static methods 
 * 
 * @author Sulakshan Shetty
 */
public class Schema
{
    
    /** 
     * Table names used in recorder
     * 
     * @author SpinSci (www.spinsci.com)
     */
    public static class Table
    {
        public static final String PROCESS      = "process";
        public static final String FAMILY       = "family";
        public static final String ACTIVITY     = "activity";
        public static final String QX_QUEUE     = "qxqueue";
        public static final String QX_ELEMENT     = "qxelement";
    }

    /** 
     * Column names of tables used in recorder 
     * 
     * @author SpinSci (www.spinsci.com)
     */
    public static class Column
    {
        public static final String ID           = "id";
        public static final String NAMESPACE    = "namespace";
        public static final String NAME         = "name";
        public static final String STARTTIME    = "starttime";
        public static final String ENDTIME      = "endtime";
        public static final String STATUS       = "status";
        public static final String DESCRIPTION  = "description";
        public static final String PARENTID     = "parentid";
        public static final String CHILDID      = "childid";
        public static final String DOMAIN       = "domain";
        public static final String NODE         = "node";
        public static final String TIMESTAMP    = "timestamp";
        public static final String TYPE         = "type";
        public static final String MESSAGE      = "message";
        public static final String NOTES        = "notes";
        public static final String ANNOTATIONS  = "annotations";
        public static final String STATE        = "state";
        public static final String PRIORITY     = "priority";
        public static final String QORDER       = "qorder";        
        public static final String CONTEXT      = "context";
        public static final String INSTRUCTION  = "instruction";
        public static final String SUMMARY      = "summary";
       
    }


    public static Collection unmarshallQueues(ResultSet rs) throws SQLException {
      Collection ers = null;
      while (rs.next()){
          if (ers == null) ers = new ArrayList();
          ers.add(unmarshallQueue(rs));
      }
      return ers;
  }

  public static QxQueueRecordBase unmarshallQueue(ResultSet rs) throws SQLException {
    QxQueueRecordBase qBase = new QxQueueRecordBase(rs.getString(Column.NAME), rs.getString(Column.STATE));
    if(null != rs.getString(Column.NOTES)) { qBase.setQ_notes(rs.getString(Column.NOTES)); }
    return qBase;
  }
  
    public static TreeSet unmarshallQElements(ResultSet rs) throws SQLException {
      TreeSet ers = new TreeSet(new QxElementComparator());
      while (rs.next()){
          //if (ers == null) ers = new TreeSet(new QxElementComparator());
          ers.add(unmarshallQElement(rs));
      }
      return ers;
  }

  public static QxElementRecordBase unmarshallQElement(ResultSet rs) throws SQLException {
    QxElementRecordBase qelement = new QxElementRecordBase();
    if(null != rs.getString(Column.ID))          { qelement.setId(new Long(rs.getString(Column.ID)).longValue()); }
    if(null != rs.getString(Column.NAMESPACE))   { qelement.setNamespace(rs.getString(Column.NAMESPACE)); }
    if(null != rs.getString(Column.NAME))        { qelement.setName(rs.getString(Column.NAME)); }
    if(null != rs.getString(Column.PRIORITY))    { qelement.setPriority(new Integer(rs.getString(Column.PRIORITY)).intValue()); }
    if(null != rs.getString(Column.QORDER))      { qelement.setQorder(new Integer(rs.getString(Column.QORDER)).intValue()); }
    if(null != rs.getString(Column.STATE))       { qelement.setState(rs.getString(Column.STATE)); }
    if(null != rs.getString(Column.CONTEXT))     { qelement.setContext(rs.getString(Column.CONTEXT)); }
    if(null != rs.getString(Column.INSTRUCTION)) { qelement.setInstruction(rs.getString(Column.INSTRUCTION)); }
    if(null != rs.getString(Column.SUMMARY))     { qelement.setSummary(rs.getString(Column.SUMMARY)); }
    if(null != rs.getString(Column.ANNOTATIONS))       { qelement.setNotes(rs.getString(Column.ANNOTATIONS)); }
    return qelement;
  }
  
  
    /** 
     * Unmarshall records from process table into SortedSet of ExecutionRecordBase
     * 
     * @param rs ResultSet to unmarshall from 
     * @return SortedSet of ExecutionRecordBase objects
     * @throws SQLException 
     */
    public static TreeSet unmarshallRecordings(ResultSet rs) throws SQLException {
        TreeSet ers = null;
        while (rs.next()){
            if (ers == null) ers = new TreeSet(new RecordComparator());
            ers.add(unmarshallRecording(rs));
        }
        return ers;
    }
    /** 
     * Unmarshall record from process table into ExecutionRecordBase
     * Confirm ResultSet containts data prior to calling this method.
     * 
     * @param rs ResultSet to unmarshall from 
     * @return ExecutionRecordBase
     * @throws SQLException 
     */
    public static ExecutionRecordBase unmarshallRecording(ResultSet rs) throws SQLException {
        long id = rs.getLong(Column.ID);
        String ns = rs.getString(Column.NAMESPACE);
        String n = rs.getString(Column.NAME);
        ExecutionRecordBase er = new ExecutionRecordBase(id, ns, n);
        String status = rs.getString(Column.STATUS);
        String description = rs.getString(Column.DESCRIPTION);
        
        if(null!=status) er.setStatus(status);
        er.setDuration(new DurationBase(rs.getTimestamp(Column.STARTTIME), 
                                    rs.getTimestamp(Column.ENDTIME)));
        if (null!=description) er.setDescription(description);
        return er;
    }

    /** 
     * Unmarshall records from activity table into SortedSet of ActivityRecordBase
     * 
     * @param rs ResultSet to unmarshall from 
     * @return List of ActivityRecordBase
     * @throws SQLException 
     */
    public static TreeSet unmarshallActivities(ResultSet rs) throws SQLException
    {
        TreeSet arbs = null;

        while (rs.next())
        {
            if (arbs == null) arbs = new TreeSet(new RecordComparator());
            arbs.add(unmarshallActivity(rs));
        }

        return arbs;
    }

    /** 
     * Unmarshall record from activity table into ActivityRecordBase
     * Confirm ResultSet containts data prior to calling this method.
     * 
     * @param rs ResultSet to unmarshall from 
     * @return ActivityRecordBase
     * @throws SQLException 
     */
    static ActivityRecordBase unmarshallActivity(ResultSet rs) throws SQLException
    {
        ActivityRecordBase arb = new ActivityRecordBase();
        arb.setID(rs.getLong(Column.ID));
        arb.setDomain(rs.getString(Column.DOMAIN));
        arb.setNodeName(rs.getString(Column.NODE));
        arb.setTimestamp(rs.getTimestamp(Column.TIMESTAMP));
        arb.setType(rs.getString(Column.TYPE));
        arb.setMessage(rs.getString(Column.MESSAGE));
        arb.setNotes(rs.getString(Column.ANNOTATIONS));
        return arb;
    }
    
    
    /**
     * @param rs
     * @return
     * @throws SQLException
     */
    public static TreeSet getNamespaceNames(ResultSet rs) throws SQLException {
        // +++ Auto-generated method stub
        TreeSet names = new TreeSet(new RecordComparator());
        while (rs.next())
        {
            {} //System.out.println("Getting NamespaceNames inside Schema" +rs.getString(Column.NAME) );
            names.add(rs.getString(Column.NAME));
        }
        return names;
    }
    /**
     * @param rs
     * @return
     */
    public static SortedSet getNamespaces(ResultSet rs)throws SQLException {
        TreeSet namespaces = new TreeSet(new RecordComparator());
        while (rs.next())
        {
            {} //System.out.println("Getting Namespaces inside Schema" +rs.getString(Column.NAMESPACE) );
            namespaces.add(rs.getString(Column.NAMESPACE));
        }
        return namespaces;
    }
}

