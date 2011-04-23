/*
 * file name : zws\service\recorder\RecorderService.java
 * authors : Sulakshan Shetty
 * created : 02/14/2006 08:13:02
 * copyright : Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 * modifications:
 */
package zws.service.recorder.qx;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import zws.Schema;
import zws.util.StringUtil;
import zws.util.TimeUtil;
import zws.recorder.ExecutionRecord;
import zws.recorder.ExecutionRecordBase;
import zws.database.Database;
import zws.database.DB;
import zws.application.Names;

/**
 * RecorderService implementation.
 *
 * @author Sulakshan Shetty
 */
public class RecorderSvc {

  /** Exceptions throws by methods in this class. */
  public static final Exception NO_DB_CONNECTION      = new Exception("No Db Connection");

  /** The Constant INVALID_NAMESPACE. */
  public static final Exception INVALID_NAMESPACE     = new Exception("Invalid Namespace specified");

  /** The Constant INVALID_NAME. */
  public static final Exception INVALID_NAME          = new Exception("Invalid Name specified");

  /** The Constant INVALID_STATUS. */
  public static final Exception INVALID_STATUS        = new Exception("Invalid Status specified");

  /** The Constant INVALID_PARENT_ID. */
  public static final Exception INVALID_PARENT_ID     = new Exception("Invalid Parent ID specified");

  /** The Constant INVALID_ID. */
  public static final Exception INVALID_ID            = new Exception("Invalid ID specified");

  /** The Constant INVALID_DOMAIN. */
  public static final Exception INVALID_DOMAIN        = new Exception("Invalid Domain specified");

  /** The Constant INVALID_NODE. */
  public static final Exception INVALID_NODE          = new Exception("Invalid Node specified");

  /** The Constant INVALID_MSG_TYPE. */
  public static final Exception INVALID_MSG_TYPE      = new Exception("Invalid Msg Type specified");

  /** The Constant INVALID_CATEGORY. */
  public static final Exception INVALID_CATEGORY      = new Exception("Invalid Category specified");

  /** The Constant INVALID_MSG. */
  public static final Exception INVALID_MSG           = new Exception("Invalid Msg specified");

  /** The Constant INVALID_CUT_OFF_TIME. */
  public static final Exception INVALID_CUT_OFF_TIME  = new Exception("Invalid Cut Off Time specified");

  /** The Constant RECORDER_ERROR. */
  public static final Exception RECORDER_ERROR        = new Exception("Recorder Error");

  private static final String SQL_INSERT = "insert into " + Schema.Table.PROCESS + " ("
        + Schema.Column.NAMESPACE + ", "
        + Schema.Column.NAME + ", " + Schema.Column.STARTTIME
        + ", " + Schema.Column.DESCRIPTION
        + ", " + Schema.Column.NOTES        
        + ") values (?, ?, ?, ?,?)";
  
  private static final String SQL_GET_ID = "select max(" + Schema.Column.ID + ") "
          + "from " + Schema.Table.PROCESS + " "
          + "where " +Schema.Column.NAMESPACE + "=? "
          + "and " +Schema.Column.NAME + "=? "
          + "and " + Schema.Column.STARTTIME + "=? "
          + "and " + Schema.Column.DESCRIPTION + "=?";
  /**
   * Record process start.
   *
   * @param dotedDate timestamp to record
   * @param status Process status
   * @param description the description
   * @param name Process name
   * @param namespace Process namespace
   *
   * @return Unique process ID
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static long stampStartTime(String namespace, String name, String status, String dotedDate, String description) throws Exception {
    long id;
    if (null == dotedDate) {
      id = recordStartTime(namespace, name, status, description);
    } else {
      Calendar time = TimeUtil.getCalendar(dotedDate);
      id = stampStartTime(namespace, name, time.getTimeInMillis(), description);
    }
    recordStatus(id, status);
    return id;
  }
  
  public static long stampStartTime(String namespace, String name, String status, String dotedDate, String description, String notes) throws Exception {
    long id;
    if (null == dotedDate) {
      id = recordStartTime(namespace, name, status, description, notes);
    } else {
      Calendar time = TimeUtil.getCalendar(dotedDate);
      id = stampStartTime(namespace, name, time.getTimeInMillis(), description, notes);
    }
    recordStatus(id, status);
    return id;
  }

  /**
   * Record process start.
   *
   * @param dotedDate timestamp to record
   * @param status Process status
   * @param name Process name
   * @param namespace Process namespace
   * @return Unique process ID
   * @throws Exception On error or invalid argument is specified
   */
  public static long stampStartTime(String namespace, String name,
      String status, String dotedDate) throws Exception {
    if (null == dotedDate) {
      recordStartTime(namespace, name, status);
    }
    Calendar time = TimeUtil.getCalendar(dotedDate);
    long id = stampStartTime(namespace, name, time.getTimeInMillis(), "process");
    recordStatus(id, status);
    return id;
  }

  /**
   * Record process start.
   *
   * @param dotedDate timestamp to record
   * @param name Process name
   * @param namespace Process namespace
   *
   * @return Unique process ID
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static long stampStartTime(String namespace, String name,
      String dotedDate) throws Exception {
    if (null == dotedDate) {
      recordStartTime(namespace, name);
    }
    Calendar time = TimeUtil.getCalendar(dotedDate);
    long id = stampStartTime(namespace, name, time.getTimeInMillis(), "process");
    return id;
  }

  /**
   * Record process start.
   *
   * @param status Process status
   * @param name Process name
   * @param namespace Process namespace
   *
   * @return Unique process ID
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static long recordStartTime(String namespace, String name, String status) throws Exception {
    long id = recordStartTime(namespace, name);
    recordStatus(id, status);
    return id;
  }

  /**
   * Record process start.
   *
   * @param status Process status
   * @param description the description
   * @param name Process name
   * @param namespace Process namespace
   *
   * @return Unique process ID
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static long recordStartTime(String namespace, String name, String status, String description) throws Exception {
    long id = stampStartTime(namespace, name, System.currentTimeMillis(), description);
    recordStatus(id, status);
    return id;
  }
  
  public static long recordStartTime(String namespace, String name, String status, String description, String notes) throws Exception {
    long id = stampStartTime(namespace, name, System.currentTimeMillis(), description, notes);
    recordStatus(id, status);
    return id;
  }

  /**
   * Record process start.
   *
   * @param name Process name
   * @param namespace Process namespace
   *
   * @return Unique process ID
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static long recordStartTime(String namespace, String name) throws Exception {
    return stampStartTime(namespace, name, System.currentTimeMillis(), "process");
  }
  public static long stampStartTime(String namespace, String name, long stamp, String description) throws Exception {
    return stampStartTime(namespace, name, stamp, description, null);
  }
  
  /**
   * Record process start.
   *
   * @param stamp time in millis
   * @param description the description
   * @param name Process name
   * @param namespace Process namespace
   *
   * @return Unique process ID
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static long stampStartTime(String namespace, String name, long stamp, String description, String notes) throws Exception {
    // ensure arguments are valid
    if (namespace == null || namespace.length() == 0) {
      throw INVALID_NAMESPACE;
    }
    if (name == null || name.length() == 0) {
      throw INVALID_NAME;
    }

    PreparedStatement ps = null;
    ResultSet rs = null;
    long id = -1;
    
    try {
      // create entry in process
      /*PROCESS ID          NUMBER
        PROCESS NAMESPACE   VARCHAR2
        PROCESS NAME        VARCHAR2
        PROCESS STARTTIME   TIMESTAMP(6)
        PROCESS ENDTIME     TIMESTAMP(6)
        PROCESS STATUS      VARCHAR2
        PROCESS DESCRIPTION VARCHAR2
        PROCESS NOTES       VARCHAR2*/
      Timestamp t = new Timestamp(stamp);
      ps = database().prepareStatement(SQL_INSERT);
      ps.setString(1, StringUtil.truncateWithIndicator(namespace, 3999));
      ps.setString(2, StringUtil.truncateWithIndicator(name,3999));
      ps.setTimestamp(3, t);
      ps.setString(4, StringUtil.truncateWithIndicator(description,3999));
      
      ps.setString(5, StringUtil.truncateWithIndicator(notes,3999));
      database().executeUpdate(ps);

      /* Get the ID that was saved.
        This method is used because oracle does 
        the getGeneratedKeys a bit differently */ 
      ps = database().prepareStatement(SQL_GET_ID);
      ps.setString(1, StringUtil.truncateWithIndicator(namespace, 3999));
      ps.setString(2, StringUtil.truncateWithIndicator(name, 3999));
      ps.setTimestamp(3, t);
      ps.setString(4, StringUtil.truncateWithIndicator(description, 3999));
      rs = database().executeQuery(ps);    

      if (rs.next()) id = rs.getLong(1);
        else throw RECORDER_ERROR;
    
	  return id;
	} catch (Exception e) {
	  throw e;
	} catch (Throwable t) {
	  throw new Exception(RECORDER_ERROR.getMessage(), t);
	} finally {
	  if (rs != null) {
  		rs.close();
  		rs = null;
	  }
	  if (ps != null) {
  		ps.close();
  		ps = null;
	  }
	}
  }

  /**
   * Record process child start.
   *
   * @param parentId Parent process ID
   * @param status the status
   * @param name Child process status
   * @param namespace Child process namespace
   *
   * @return Unique child process ID
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static long recordChildStartTime(long parentId, String namespace, String name, String status) throws Exception {
    long id = recordChildStartTime(namespace, name, "sub-process", parentId);
    recordStatus(id, status);
    return id;
  }

  /**
   * Record process child start.
   *
   * @param parentId Parent process ID
   * @param status the status
   * @param description the description
   * @param name Child process status
   * @param namespace Child process namespace
   *
   * @return Unique child process ID
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static long recordChildStartTime(long parentId, String namespace, String name, String status, String description) throws Exception {
   return recordChildStartTime(parentId, namespace, name, status, description, null);
  }
  
  public static long recordChildStartTime(long parentId, String namespace, String name, String status, String description, String notes) throws Exception {
    long id = recordChildStartTime(namespace, name, description, parentId, notes);
    recordStatus(id, status);
    return id;
  }  

  /**
   * Record process child start.
   *
   * @param parentId Parent process ID
   * @param name Child process name
   * @param namespace Child process namespace
   *
   * @return Unique child process ID
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static long recordChildStartTime(long parentId, String namespace, String name) throws Exception {
    return recordChildStartTime(namespace, name, "sub-process", parentId);
  }

  private static long recordChildStartTime(String namespace, String name, String description, long parentId) throws Exception {
    return recordChildStartTime(namespace, name, description, parentId, null);
  }
  /**
   * Record process child start.
   *
   * @param parentId Parent process ID
   * @param description the description
   * @param name Child process name
   * @param namespace Child process namespace
   *
   * @return Unique child process ID
   *
   * @throws Exception On error or invalid argument is specified
   */
  private static long recordChildStartTime(String namespace, String name, String description, long parentId, String notes) throws Exception {
    // ensure arguments are valid
    if (parentId < 0) {
      throw INVALID_PARENT_ID;
    }
    if (namespace == null || namespace.length() == 0) {
      throw INVALID_NAMESPACE;
    }
    if (name == null || name.length() == 0) {
      throw INVALID_NAME;
    }

    PreparedStatement ps = null;

    try {
      // record child process
      long childId = stampStartTime(namespace, name,System.currentTimeMillis(), description, notes);
      // create enty in family
      ps = database().prepareStatement("insert into " + Schema.Table.FAMILY + " values (?, ?)");
      ps.setLong(1, parentId);
      ps.setLong(2, childId);
      database().executeUpdate(ps);

      return childId;
    } catch (Exception e) {
      throw e;
    } catch (Throwable t) {
      throw new Exception(RECORDER_ERROR.getMessage(), t);
    } finally {
      if (ps != null) {
        ps.close();
        ps = null;
      }
    }
  }

  /**
   * Record process end.
   *
   * @param id Process ID
   * @param status the status
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static void recordEndTime(long id, String status) throws Exception {
    recordEndTime(id);
    recordStatus(id, status);
  }

  /**
   * Record process end.
   *
   * @param id Process ID
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static void recordEndTime(long id) throws Exception {
    // ensure arguments are valid
    if (id < 0) {
      throw INVALID_ID;
    }

    PreparedStatement ps = null;

    try {
      // update endtime
      ps = database().prepareStatement(
          "update " + Schema.Table.PROCESS + " set " + Schema.Column.ENDTIME
              + " = ? where " + Schema.Column.ID + " = ?");

      ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
      ps.setLong(2, id);
      database().executeUpdate(ps);
    } catch (Exception e) {
      throw e;
    } catch (Throwable t) {
      throw new Exception(RECORDER_ERROR.getMessage(), t);
    } finally {
      if (ps != null) {
        ps.close();
        ps = null;
      }
    }
  }

  /**
   * Record process status.
   *
   * @param id Process ID
   * @param status Process status
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static void recordStatus(long id, String status) throws Exception {
    // ensure arguments are valid
    if (id < 0) {
      throw INVALID_ID;
    }
    if (status == null) {
      status = "";
    }
    PreparedStatement ps = null;

    try {
      // update status
      ps = database().prepareStatement(
          "update " + Schema.Table.PROCESS + " set " + Schema.Column.STATUS
              + " = ? where " + Schema.Column.ID + " = ?");

      ps.setString(1, status);
      ps.setLong(2, id);
      database().executeUpdate(ps);
    } catch (Exception e) {
      throw e;
    } catch (Throwable t) {
      throw new Exception(RECORDER_ERROR.getMessage(), t);
    } finally {
      if (ps != null) {
        ps.close();
        ps = null;
      }
    }
  }
  public static void recordActivity(long id, String domain, String node,
      String type, String msg) throws Exception {
    recordActivity(id, domain, node, type, msg, null);
  }
  /**
   * Record process activity.
   *
   * @param id Process ID
   * @param node Activity node
   * @param domain Activity domain
   * @param type Activity type
   * @param msg Activity message
   * @param notes Activity notes
   * @throws Exception On error or invalid argument is specified
   */
  public static void recordActivity(long id, String domain, String node,
      String type, String msg, String annotations) throws Exception {
    // ensure arguments are valid
    if (id < 0) {
      throw INVALID_ID;
    }
    if (domain == null || domain.length() == 0) {
      throw INVALID_DOMAIN;
    }
    if (node == null || node.length() == 0) {
      throw INVALID_NODE;
    }
    if (type == null || type.length() == 0) {
      throw INVALID_MSG_TYPE;
    }
    // if (msg == null || msg.length() == 0) throw INVALID_MSG;
    if (msg == null || msg.length() == 0) {
      msg = "Un-Known Error";
    }

    PreparedStatement ps = null;

    try {
      // create enty in activity
      /*
        ACTIVITY  ID          NUMBER
        ACTIVITY  DOMAIN      VARCHAR2
        ACTIVITY  NODE        VARCHAR2
        ACTIVITY  TIMESTAMP   TIMESTAMP(6)
        ACTIVITY  TYPE        VARCHAR2
        ACTIVITY  MESSAGE     CLOB
        ACTIVITY  ANNOTATIONS CLOB

       */
      ps = database()
          .prepareStatement(
              "insert into " + Schema.Table.ACTIVITY
                  + " values (?, ?, ?, ?, ?, ?, ?)");
      ps.setLong(1, id);
      ps.setString(2, StringUtil.truncateWithIndicator(domain,3999));
      ps.setString(3, StringUtil.truncateWithIndicator(node,3999));
      ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
      ps.setString(5, StringUtil.truncateWithIndicator(type,3999));
      ps.setString(6, msg);
      
      ps.setString(7, annotations);
      database().executeUpdate(ps);
    } catch (Exception e) {
      throw e;
    } catch (Throwable t) {
      throw new Exception(RECORDER_ERROR.getMessage(), t);
    } finally {
      if (ps != null) {
        ps.close();
        ps = null;
      }
    }
  }

  /**
   * Delete process recording.
   *
   * @param id Process ID
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static void deleteRecord(long id) throws Exception {
    // ensure arguments are valid
    if (id < 0) {
      throw INVALID_ID;
    }

    PreparedStatement ps1 = null, ps2 = null;
    ResultSet rs1 = null;

    try {
      // first delete the children recordings
      ps1 = database().prepareStatement(
          "select " + Schema.Column.CHILDID + " from " + Schema.Table.FAMILY
              + " where " + Schema.Column.PARENTID + " = ?");
      ps1.setLong(1, id);

      rs1 = database().executeQuery(ps1);
      while (rs1.next()) {
        deleteRecord(rs1.getLong(Schema.Column.CHILDID));
      }

      // children gone, now delete self
      ps2 = database().prepareStatement(
          "delete from " + Schema.Table.PROCESS + " where " + Schema.Column.ID
              + " = ?");
      ps2.setLong(1, id);
      database().executeUpdate(ps2);
    } catch (Exception e) {
      throw e;
    } catch (Throwable t) {
      throw new Exception(RECORDER_ERROR.getMessage(), t);
    } finally {
      if (rs1 != null) {
        rs1.close();
        rs1 = null;
      }
      if (ps1 != null) {
        ps1.close();
        ps1 = null;
      }
      if (ps2 != null) {
        ps2.close();
        ps2 = null;
      }
    }
  }

  /**
   * Delete all recordings that ended prior to specified date.
   *
   * @param cutOffTime Delete recordings prior to cut off time
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static void purgeRecords(Date cutOffTime) throws Exception {
    // ensure arguments are valid
    if (cutOffTime == null) {
      throw INVALID_CUT_OFF_TIME;
    }

    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      // get list of top level processes which have start time prior to cut off
      // time
      ps = database().prepareStatement(
          "select p." + Schema.Column.ID + " from " + Schema.Table.PROCESS
              + " p" + " left join " + Schema.Table.FAMILY + " f on p."
              + Schema.Column.ID + " = f." + Schema.Column.CHILDID
              + " where f." + Schema.Column.CHILDID + " is null" + " and p."
              + Schema.Column.STARTTIME + " < ?");

      ps.setTimestamp(1, new Timestamp(cutOffTime.getTime()));

      rs = database().executeQuery(ps);
      while (rs.next()) {
        deleteRecord(rs.getLong(Schema.Column.ID));
      }
    } catch (Exception e) {
      throw e;
    } catch (Throwable t) {
      throw new Exception(RECORDER_ERROR.getMessage(), t);
    } finally {
      if (rs != null) {
        rs.close();
        rs = null;
      }
      if (ps != null) {
        ps.close();
        ps = null;
      }
    }
  }

  /**
   * Get entire process recording (includes any children recordings also).
   *
   * @param id Process ID
   *
   * @return Entire process recording
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static ExecutionRecord getRecording(long id) throws Exception {
    // ensure arguments are valid
    if (id < 0) {
      throw INVALID_ID;
    }

    ExecutionRecordBase rec = null;

    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      rec = getRecordingById(id, true);

      if (rec != null) {
        // get list of children recordings
        ps = database().prepareStatement(
            "select " + Schema.Column.CHILDID + " from " + Schema.Table.FAMILY
                + " where " + Schema.Column.PARENTID + " = ?");
        ps.setLong(1, id);

        rs = database().executeQuery(ps);
        while (rs.next()) {
          ExecutionRecord childRec = getRecording(rs
              .getLong(Schema.Column.CHILDID));
          if (childRec != null) {
            rec.add(childRec);
          }
        }
      }
    } catch (Exception e) {
      throw e;
    } catch (Throwable t) {
      throw new Exception(RECORDER_ERROR.getMessage(), t);
    } finally {
      if (rs != null) {
        rs.close();
        rs = null;
      }
      if (ps != null) {
        ps.close();
        ps = null;
      }
    }

    return rec;
  }

  /**
   * Get last recording for specified namespace. Returns single recording
   * without children recording information.
   *
   * @param namespace Namespace to query by
   *
   * @return Last execution record (most recent starttime)
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static ExecutionRecord getLastRecording(String namespace)
      throws Exception {
    if (namespace == null || namespace.length() == 0) {
      throw INVALID_NAMESPACE;
    }

  	String sql = "select * from " + Schema.Table.PROCESS
          + " where " + Schema.Column.NAMESPACE + " = ?" + " order by "
          + Schema.Column.STARTTIME + " desc ";
  
    sql = database().limitResults(sql, 1);
    return getRecordingByQuery(sql, new Object[] {namespace});
  }

  /**
   * Get last recording for specified namespace and name. Returns single
   * recording without children recording information.
   *
   * @param name Name to query by
   * @param namespace Namespace to query by
   *
   * @return Last execution record (most recent starttime)
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static ExecutionRecord getLastRecording(String namespace, String name)
      throws Exception {
    if (namespace == null || namespace.length() == 0) {
      throw INVALID_NAMESPACE;
    }
    if (name == null || name.length() == 0) {
      throw INVALID_NAME;
    }

    String sql = "select * from " + Schema.Table.PROCESS
        + " where " + Schema.Column.NAMESPACE + " = ?" + " and "
        + Schema.Column.NAME + " =?" + " order by " + Schema.Column.STARTTIME
        + " desc ";

    sql = database().limitResults(sql, 1);
    return getRecordingByQuery(sql, new Object[] {namespace, name});
  }

  /**
   * Get recordings. Returns only single level of recordings.
   *
   * @param name Name
   * @param namespace Namespace
   *
   * @return List of recordings if found, null otherwise
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static SortedSet getRecordings(String namespace, String name)
      throws Exception {
    SortedSet result = null;
    if (namespace == null || namespace.length() == 0) {
      throw INVALID_NAMESPACE;
    }
    if (name == null || name.length() == 0) {
      throw INVALID_NAME;
    }

    result = getRecordingsByQuery("select * from " + Schema.Table.PROCESS
        + " where " + Schema.Column.NAMESPACE + " = ? and "
        + Schema.Column.NAME + " =?", new Object[] {namespace, name});
    {} //System.out.println("getRecordings: " + result);
    return result;
  }
  
  public static SortedSet getRecordingsByStatus(String namespace, String status) throws Exception {
	  SortedSet result = null;
	  if (namespace == null || namespace.length() == 0) throw INVALID_NAMESPACE;
	  if (status == null || status.length() == 0)  throw INVALID_STATUS;

	  result = getRecordingsByQuery("select * from " + Schema.Table.PROCESS
			  + " where " + Schema.Column.NAMESPACE + " = ? and "
			  + Schema.Column.STATUS + " =?", new Object[] {namespace, status});
	  {} //System.out.println("getRecordings: " + result);
	  return result;
  }

  /**
   * Get child recordings. Returns only single level of recordings.
   *
   * @param id Parent id
   *
   * @return List of child recordings if found, null otherwise
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static SortedSet getChildRecordings(long id) throws Exception {
    if (id < 0) {
      throw INVALID_ID;
    }

    return getRecordingsByQuery("select c.* from " + Schema.Table.PROCESS
        + " p, " + Schema.Table.PROCESS + " c, " + Schema.Table.FAMILY
        + " f where p." + Schema.Column.ID + " = ? and p." + Schema.Column.ID
        + " = f." + Schema.Column.PARENTID + " and c." + Schema.Column.ID
        + " = f." + Schema.Column.CHILDID, new Object[] {new Long(id)});
  }

  /**
   * Get parent recording. Returns only single level of recordings.
   *
   * @param id Child id
   *
   * @return Parent recording if found, null otherwise
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static ExecutionRecord getParentRecording(long id) throws Exception {
    if (id < 0) {
      throw INVALID_ID;
    }

    return getRecordingByQuery("select p.* from " + Schema.Table.PROCESS
        + " p, " + Schema.Table.PROCESS + " c, " + Schema.Table.FAMILY
        + " f where c." + Schema.Column.ID + " = ? and p." + Schema.Column.ID
        + " = f." + Schema.Column.PARENTID + " and c." + Schema.Column.ID
        + " = f." + Schema.Column.CHILDID, new Object[] {new Long(id)});
  }

  /**
   * Get process status.
   *
   * @param id Process ID
   *
   * @return Process status if found, null otherwise
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static String getStatus(long id) throws Exception {
    // ensure arguments are valid
    if (id < 0) {
      throw INVALID_ID;
    }

    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      // query status
      ps = database().prepareStatement(
          "select " + Schema.Column.STATUS + " from " + Schema.Table.PROCESS
              + " where " + Schema.Column.ID + " = ?");

      ps.setLong(1, id);
      rs = database().executeQuery(ps);
      if (rs.next()) {
        return rs.getString(Schema.Column.STATUS);
      }
      return null;
    } catch (Exception e) {
      throw e;
    } catch (Throwable t) {
      throw new Exception(RECORDER_ERROR.getMessage(), t);
    } finally {
      if (rs != null) {
        rs.close();
        rs = null;
      }
      if (ps != null) {
        ps.close();
        ps = null;
      }
    }
  }

  /**
   * Get process activity list.
   *
   * @param id Process ID
   *
   * @return Process activity list if found, null otherwise
   *
   * @throws Exception On error or invalid argument is specified
   */
  public static SortedSet getActivity(long id) throws Exception {
    // ensure arguments are valid
    if (id < 0) {
      throw INVALID_ID;
    }

    return getActivitiesById(id);
  }

  /**
   * Get execution recording by specified query. Assume query will return only
   * single execution record - if multiple returned only the first one from the
   * ResultSet will be returned.
   *
   * @param sql Prepared statement SQL to retrieve ExecutionRecord by
   * @param args Arguments for the prepared statement if any (supports String, Long, Date)
   *
   * @return ExecutionRecording if found, null otherwise
   *
   * @throws Exception the exception
   */
  private static ExecutionRecordBase getRecordingByQuery(String sql, Object[] args) throws Exception {
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      ps = database().prepareStatement(sql);

      // fill in any arguments
      if (args != null) {
        for (int a = 0; a < args.length; a++) {
          if (args[a] instanceof String) {
            ps.setString(a + 1, (String) args[a]);
          } else if (args[a] instanceof Long) {
            ps.setLong(a + 1, ((Long) args[a]).longValue());
          } else if (args[a] instanceof Timestamp) {
            ps.setTimestamp(a + 1, (Timestamp) args[a]);
          }
        }
      }

      rs = database().executeQuery(ps);
      if (rs.next()) {
        return Schema.unmarshallRecording(rs);
      } else {
        return null;
      }
    } catch (Exception e) {
      throw e;
    } catch (Throwable t) {
      throw new Exception(RECORDER_ERROR.getMessage(), t);
    } finally {
      if (rs != null) {
        rs.close();
        rs = null;
      }
      if (ps != null) {
        ps.close();
        ps = null;
      }
    }
  }

  /**
   * Get list of execution recordings by specified query. Returns only one level
   * of recordings.
   *
   * @param sql Prepared statement SQL to retrieve ExecutionRecord by
   * @param args Arguments for the prepared statement if any (supports String, Long, Date)
   *
   * @return ExecutionRecordings if found, null otherwise
   *
   * @throws Exception the exception
   */
  private static SortedSet getRecordingsByQuery(String sql, Object[] args) throws Exception {
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      ps = database().prepareStatement(sql);

      // fill in any arguments
      if (args != null) {
        for (int a = 0; a < args.length; a++) {
          if (args[a] instanceof String) {
            ps.setString(a + 1, (String) args[a]);
          } else if (args[a] instanceof Long) {
            ps.setLong(a + 1, ((Long) args[a]).longValue());
          } else if (args[a] instanceof Timestamp) {
            ps.setTimestamp(a + 1, (Timestamp) args[a]);
          }
        }
      }

      rs = database().executeQuery(ps);
      return Schema.unmarshallRecordings(rs);
    } catch (Exception e) {
      throw e;
    } catch (Throwable t) {
      throw new Exception(RECORDER_ERROR.getMessage(), t);
    } finally {
      if (rs != null) {
        rs.close();
        rs = null;
      }
      if (ps != null) {
        ps.close();
        ps = null;
      }
    }
  }

  /**
   * Get execution recording by specified query. Assume query will return only
   * single execution record - if multiple returned only the first one from the
   * ResultSet will be returned.
   * @param id Process id to load record for
   * @param getActivities Get activities for this process also
   * @return ExecutionRecording if found, null otherwise
   * @throws Exception the exception
   */
  private static ExecutionRecordBase getRecordingById(long id,
      boolean getActivities) throws Exception {
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      ps = database().prepareStatement(
          "select * from " + Schema.Table.PROCESS + " where "
              + Schema.Column.ID + " = ?");

      ps.setLong(1, id);

      rs = database().executeQuery(ps);
      if (rs.next()) {
        ExecutionRecordBase erb = Schema.unmarshallRecording(rs);
        if (getActivities) {
          erb.setActivity(getActivitiesById(id));
        }
        return erb;
      }

      return null;
    } catch (Exception e) {
      throw e;
    } catch (Throwable t) {
      throw new Exception(RECORDER_ERROR.getMessage(), t);
    } finally {
      if (rs != null) {
        rs.close();
        rs = null;
      }
      if (ps != null) {
        ps.close();
        ps = null;
      }
    }
  }

  /**
   * Get activity list for given process id.
   *
   * @param id Process id
   *
   * @return Activity list for process, null if no activities recorded
   *
   * @throws Exception the exception
   */
  private static TreeSet getActivitiesById(long id) throws Exception {
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      ps = database().prepareStatement(
          "select * from " + Schema.Table.ACTIVITY + " where "
              + Schema.Column.ID + " = ?");
      ps.setLong(1, id);
      rs = database().executeQuery(ps);

      return Schema.unmarshallActivities(rs);
    } catch (Exception e) {
      throw e;
    } catch (Throwable t) {
      throw new Exception(RECORDER_ERROR.getMessage(), t);
    } finally {
      if (rs != null) {
        rs.close();
        rs = null;
      }
      if (ps != null) {
        ps.close();
        ps = null;
      }
    }
  }

  // Method added by Rahul to get Names for activity

  /**
   * Gets the names.
   *
   * @param namespace the namespace
   *
   * @return the names
   *
   * @throws Exception the exception
   */
  public static TreeSet getNames(String namespace) throws Exception {

    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      ps = database().prepareStatement(
          "select name from " + Schema.Table.PROCESS + " where "
              + Schema.Column.NAMESPACE + " = ?");

      //{}//Logwriter.printOnConsole("11111111111111111111");
      //{}//Logwriter.printOnConsole("select name from " + Schema.Table.PROCESS + " where " + Schema.Column.NAMESPACE + " = ?");
      //{}//Logwriter.printOnConsole("11111111111111111111");

      ps.setString(1, namespace);
      rs = database().executeQuery(ps);

      return Schema.getNamespaceNames(rs);
    } catch (Exception e) {
      throw e;
    } catch (Throwable t) {
      throw new Exception(RECORDER_ERROR.getMessage(), t);
    } finally {
      if (rs != null) {
        rs.close();
        rs = null;
      }
      if (ps != null) {
        ps.close();
        ps = null;
      }
    }
  }

  /**
   * Gets the namespaces.
   *
   * @return the namespaces
   *
   * @throws Exception the exception
   */
  public static SortedSet getNamespaces() throws Exception {

    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      ps = database().prepareStatement(
          "select distinct " + Schema.Column.NAMESPACE + " from "
              + Schema.Table.PROCESS);

      {}//Logwriter.printOnConsole("11111111111111111111");
      {}//Logwriter.printOnConsole("select distinct " + Schema.Column.NAMESPACE + " from " + Schema.Table.PROCESS);
      {}//Logwriter.printOnConsole("11111111111111111111");

      rs = database().executeQuery(ps);

      return Schema.getNamespaces(rs);
    } catch (Exception e) {
      throw e;
    } catch (Throwable t) {
      throw new Exception(RECORDER_ERROR.getMessage(), t);
    } finally {
      if (rs != null) {
        rs.close();
        rs = null;
      }
      if (ps != null) {
        ps.close();
        ps = null;
      }
    }
  }

  // end of method added by Rahul

  /**
   * Get database connection.
   *
   * @return Database connection
   *
   * @throws Exception the exception
   */
  private static Database database() throws Exception {
    return DB.source(Names.RECORDER_DATABASE);
  }

}
