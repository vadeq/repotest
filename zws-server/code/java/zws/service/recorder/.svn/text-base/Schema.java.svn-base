/* file name  : zws\service\recorder\Schema.java
 * authors    : Sulakshan Shetty
 * created    : 02/15/2006 07:25:25
 * copyright  : Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 *
 * modifications:
 *
 */
package zws.service.recorder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.SortedSet;
import java.util.TreeSet;

import zws.time.DurationBase;
//impoer zws.util.{}//Logwriter;
import zws.recorder.ExecutionRecord;
import zws.recorder.ExecutionRecordBase;
import zws.recorder.ActivityRecord;
import zws.recorder.ActivityRecordBase;
import zws.recorder.RecordComparator;

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
        public static final String DESCRIPTION = "description";
        public static final String PARENTID     = "parentid";
        public static final String CHILDID      = "childid";
        public static final String DOMAIN       = "domain";
        public static final String NODE         = "node";
        public static final String TIMESPAMP    = "timestamp";
        public static final String TYPE         = "type";
        public static final String MESSAGE      = "message";
    }

    /** 
     * Unmarshall records from process table into SortedSet of ExecutionRecordBase
     * 
     * @param rs ResultSet to unmarshall from 
     * @return SortedSet of ExecutionRecordBase objects
     * @throws SQLException 
     */
    public static TreeSet unmarshallRecordings(ResultSet rs) throws SQLException
    {
        TreeSet ers = null;

        while (rs.next())
        {
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
    public static ExecutionRecordBase unmarshallRecording(ResultSet rs) throws SQLException
    {
        long id = rs.getLong(Column.ID);
        String ns = rs.getString(Column.NAMESPACE);
        String n = rs.getString(Column.NAME);

        ExecutionRecordBase er = new ExecutionRecordBase(id, ns, n);
        String status = rs.getString(Column.STATUS);
        String description = rs.getString(Column.DESCRIPTION);
        if (null!=status) er.setStatus(status);
        if (null!=description) er.setStatus(description);
        er.setDuration(new DurationBase(rs.getTimestamp(Column.STARTTIME), 
                                    rs.getTimestamp(Column.ENDTIME)));
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
        arb.setTimestamp(rs.getTimestamp(Column.TIMESPAMP));
        arb.setType(rs.getString(Column.TYPE));
        arb.setMessage(rs.getString(Column.MESSAGE));
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
            {}//Logwriter.printOnConsole("Getting NamespaceNames inside Schema" +rs.getString(Column.NAME) );
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
            {}//Logwriter.printOnConsole("Getting Namespaces inside Schema" +rs.getString(Column.NAMESPACE) );
            namespaces.add(rs.getString(Column.NAMESPACE));
        }
        return namespaces;
    }
}
