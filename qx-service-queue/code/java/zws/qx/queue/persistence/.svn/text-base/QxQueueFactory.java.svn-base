package zws.qx.queue.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Collection;
import zws.Schema;
import zws.queue.QxQueueRecordBase;
import zws.util.StringUtil;
import zws.database.Database;
import zws.database.DB;
import zws.application.Names;

public class QxQueueFactory {

  private static final String SQL_Q_INSERT = "insert into " + Schema.Table.QX_QUEUE + " (" 
                    + Schema.Column.NAME + ", " 
                    + Schema.Column.STATE + ", " 
                    + Schema.Column.NOTES + ") values (?, ?, ?)";

  
  public static boolean createQueue(String qName) throws Exception {
    return createQueue(qName, Names.STATUS_STARTED, null);
  }
  public static boolean createQueue(String qName, String notes) throws Exception {
    return createQueue(qName, Names.STATUS_STARTED, notes);
  }

  private static boolean createQueue(String qName, String qState, String notes) throws Exception {
    if (qName == null || qName.length() == 0) { throw INVALID_NAME; }
    if (qState == null || qState.length() == 0) { throw INVALID_STATE; }

    PreparedStatement ps = null;
    ResultSet rs = null;
    boolean result = true;

    try {
      /*
       * QXQUEUE  NAME  VARCHAR2
         QXQUEUE STATE VARCHAR2
         QXQUEUE NOTES VARCHAR2
       */
      
      ps = database().prepareStatement(SQL_Q_INSERT);
      ps.setString(1, StringUtil.truncateWithIndicator(qName, 3999));
      ps.setString(2, StringUtil.truncateWithIndicator(qState, 3999));
      ps.setString(3, StringUtil.truncateWithIndicator(notes, 3999));
      database().executeUpdate(ps);
    } catch (Exception e) {
      result = false;
      e.printStackTrace();
      throw e;
    } catch (Throwable t) {
      t.printStackTrace();
      result = false;
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
    return result;
  }

  public static boolean startQueue(String qName) throws Exception {
    return updateQueueState(qName, Names.STATUS_STARTED);
  }
  
  public static boolean stopQueue(String qName) throws Exception {
    return updateQueueState(qName, Names.STATUS_STOPPED);
  }
  
  private static boolean updateQueueState(String qName, String qState) throws Exception {
    if (qName == null || qName.length() == 0) { throw INVALID_NAME; }
    if (qState == null || qState.length() == 0) { throw INVALID_NAME; }
    boolean result = true;
    PreparedStatement ps1 = null;
    ResultSet rs1 = null;
    try {
      // children gone, now delete self
      ps1 = database().prepareStatement(UPDATE_Q_STATE);
      ps1.setString(1, qState);
      ps1.setString(2, qName);
      database().executeUpdate(ps1);
    } catch (Exception e) {
      result = false;
      e.printStackTrace();
      throw e;
    } catch (Throwable t) {
      t.printStackTrace();
      result = false;
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
    }
    return result;
  }
  public static boolean destroyQueue(String qName) throws Exception {
    if (qName == null || qName.length() == 0) { throw INVALID_NAME; }
    boolean result = true;
    PreparedStatement ps1 = null;
    ResultSet rs1 = null;

    try {
      // children gone, now delete self
      ps1 = database().prepareStatement("delete from " + Schema.Table.QX_QUEUE + " where " + Schema.Column.NAME + " = ?");
      ps1.setString(1, qName);
      database().executeUpdate(ps1);
    } catch (Exception e) {
      result = false;
      e.printStackTrace();
      throw e;
    } catch (Throwable t) {
      t.printStackTrace();
      result = false;
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
    }
    return result;
  }

  public static String getQueueState(String qName) throws Exception {
    QxQueueRecordBase qRecord = getQueue(qName);
    return qRecord.getQ_state();
  }
  public static QxQueueRecordBase getQueue(String qName) throws Exception {
    if (qName == null || qName.length() == 0) { throw INVALID_NAME; }
    QxQueueRecordBase qRecord = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
      ps = database().prepareStatement("select * from " + Schema.Table.QX_QUEUE + " where " + Schema.Column.NAME + " = ?");
      ps.setString(1, qName);
      rs = database().executeQuery(ps);
      if (rs.next()) {
        qRecord = Schema.unmarshallQueue(rs);
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    } catch (Throwable t) {
      t.printStackTrace();
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
    return qRecord;
  }
  
  public static Collection getQueues() throws Exception {
    String sql = "select * from " + Schema.Table.QX_QUEUE;
    return getQueuesByQuery(sql, null);
  }
  
  public static Collection getActiveQueues() throws Exception {
    String sql = "select * from " + Schema.Table.QX_QUEUE + " where " + Schema.Column.STATE+ " = ?";
    return getQueuesByQuery(sql, new Object[] {Names.STATUS_STARTED});
  }
  
  public static boolean isActive(String qName) throws Exception {
    boolean result = false;
    QxQueueRecordBase queue = getQueue(qName);
    result = Names.STATUS_STARTED.equalsIgnoreCase(queue.getQ_state());
    return result;
  }
 
  private static Collection getQueuesByQuery(String sql, Object[] args) throws Exception {
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
      return Schema.unmarshallQueues(rs);
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

  public static final String UPDATE_Q_STATE = "update " 
                                              + Schema.Table.QX_QUEUE + 
                                              " set "
                                              + Schema.Column.STATE +
                                              "=? where " 
                                              + Schema.Column.NAME + 
                                              " = ?";
  
  
  /** Exceptions throws by methods in this class. */
  public static final Exception NO_DB_CONNECTION = new Exception("No Db Connection");

  /** The Constant INVALID_NAMESPACE. */
  public static final Exception INVALID_NAMESPACE = new Exception("Invalid Namespace specified");

  /** The Constant INVALID_NAME. */
  public static final Exception INVALID_NAME = new Exception("Invalid Name specified");

  public static final Exception INVALID_STATE = new Exception("Invalid State specified");

  /** The Constant INVALID_STATUS. */
  public static final Exception INVALID_STATUS = new Exception("Invalid Status specified");

  /** The Constant INVALID_PARENT_ID. */
  public static final Exception INVALID_PARENT_ID = new Exception("Invalid Parent ID specified");

  /** The Constant INVALID_ID. */
  public static final Exception INVALID_ID = new Exception("Invalid ID specified");

  /** The Constant INVALID_DOMAIN. */
  public static final Exception INVALID_DOMAIN = new Exception("Invalid Domain specified");

  /** The Constant INVALID_NODE. */
  public static final Exception INVALID_NODE = new Exception("Invalid Node specified");

  /** The Constant INVALID_MSG_TYPE. */
  public static final Exception INVALID_MSG_TYPE = new Exception("Invalid Msg Type specified");

  /** The Constant INVALID_CATEGORY. */
  public static final Exception INVALID_CATEGORY = new Exception("Invalid Category specified");

  /** The Constant INVALID_MSG. */
  public static final Exception INVALID_MSG = new Exception("Invalid Msg specified");

  /** The Constant INVALID_CUT_OFF_TIME. */
  public static final Exception INVALID_CUT_OFF_TIME = new Exception("Invalid Cut Off Time specified");

  /** The Constant RECORDER_ERROR. */
  public static final Exception RECORDER_ERROR = new Exception("Recorder Error");

  /**
   * Get database connection.
   * 
   * @return Database connection
   * @throws Exception the exception
   */
  private static Database database() throws Exception {
    return DB.source(Names.QX_QUEUE_DATABASE);
  }

}
