package zws.qx.queue.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.SortedSet;
import zws.Schema;
import zws.queue.QxElementRecordBase;
import zws.util.StringUtil;
import zws.database.Database;
import zws.database.DB;
import zws.Schema.Column;
import zws.application.Names;

public class QxQueueSvc {

 
  public static SortedSet getAllActiveElements(String namespace, String name) throws Exception {
    return getActiveElements(namespace,name,0);
  }
  public static SortedSet getAllPendingElements(String namespace, String name) throws Exception {
    return getPendingElements(namespace,name,0);
  }
  
  public static SortedSet getAllProcessedElement(String namespace, String name) throws Exception {
    return getProcessedElement(namespace,name,0);
  }
  
  
  public static synchronized boolean reQueue(long id) throws Exception {
    return executeUpdate(SQL_UPDATE_Q_ELEMENT, new Object[] {Names.STATUS_PENDING, new Long(id)});
  }  
  
  public static synchronized boolean enQueue(String namespace, String qName, int priority, String context, String instruction) throws Exception {
    int order = getNextOrder(priority, namespace,qName);
    return enQueue(namespace, qName, priority, order, Names.STATUS_PENDING, context, instruction, "","");
  }
  
  public static synchronized boolean enQueue(String namespace, String qName, int priority, 
                                String context, String instruction, 
                                String summary, String annotations) throws Exception {
    int order = getNextOrder(priority, namespace,qName);
    return enQueue(namespace, qName, priority, order, Names.STATUS_PENDING, context, instruction, summary, annotations);
  }
  
  private static boolean enQueue(String namespace, String qName, 
                                int priority, int qorder, 
                                String qState, 
                                String context, String instruction, 
                                String summary, String annotations) throws Exception {

    if (namespace == null || qName.length() == 0) { throw INVALID_NAMESPACE; }
    if (qName == null || qName.length() == 0) { throw INVALID_NAME; }
    if (qState == null || qState.length() == 0) { throw INVALID_STATE; }
    if (instruction == null || instruction.length() == 0) { throw INVALID_INSTRUCTION; }
    if (instruction == null || instruction.length() == 0) { throw INVALID_INSTRUCTION; }
    if (summary == null) {summary ="";}
    if (annotations == null) {annotations ="";}
    /*
     *  QXELEMENT ID          NUMBER
        QXELEMENT NAMESPACE   VARCHAR2
        QXELEMENT NAME        VARCHAR2
        QXELEMENT PRIORITY    NUMBER
        QXELEMENT QORDER      NUMBER
        QXELEMENT STATE       VARCHAR2
        QXELEMENT CONTEXT     CLOB
        QXELEMENT INSTRUCTION CLOB
        QXELEMENT SUMMARY     VARCHAR2
        QXELEMENT ANNOTATIONS CLOB

     */
    Object[] args = new Object[] {StringUtil.truncateWithIndicator(namespace, 3999),
                                  StringUtil.truncateWithIndicator(qName, 3999),
                                  new Integer(priority), 
                                  new Integer(qorder),
                                  StringUtil.truncateWithIndicator(qState, 3999),
                                  context, 
                                  instruction, 
                                  StringUtil.truncateWithIndicator(summary, 3999), 
                                  annotations};
    
    return executeUpdate(SQL_Q_ELEMENT_INSERT, args);      
  }

 
  public static QxElementRecordBase deQueue(String namespace, String qName) throws Exception {
    QxElementRecordBase qElement = null;
    if (qName == null || qName.length() == 0) { throw INVALID_NAME; }
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    PreparedStatement ps_id = null;
    ResultSet rs_id = null;
    
    long id = -1;
    try {
      // identify item id to dequeue 
      //when queue is large get only ID for speed
        ps_id = database().prepareStatement(SQL_GET_NEXT_Q_ID);
        ps_id.setString(1, Names.STATUS_PENDING);
        ps_id.setString(2, namespace);
        ps_id.setString(3, qName);
        ps_id.setMaxRows(1);
        rs_id = database().executeQuery(ps_id);
        if(rs_id.next() && null != rs_id.getString(Column.ID)){
          // use item id to retrieve element
          id = (new Long(rs_id.getString(Column.ID)).longValue());
          ps = database().prepareStatement(SQL_GET_Q_ELEMENT_BY_ID);
          ps.setLong(1, id);
          rs = database().executeQuery(ps);
          rs.next();
          qElement = Schema.unmarshallQElement(rs);
          // move the q element to processing state 
          executeUpdate(SQL_UPDATE_Q_ELEMENT, new Object[] {Names.STATUS_PROCESSING, new Long(id)});
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
      
      if (rs_id != null) {
        rs_id.close();
        rs_id = null;
      }
      if (ps_id != null) {
        ps_id.close();
        ps_id = null;
      }
      
    }
    return qElement;
  }
  
  
  public static boolean archive(long id) throws Exception {
    if (id<0) { throw INVALID_ID; }
    return executeUpdate(SQL_UPDATE_Q_ELEMENT, new Object[] {Names.STATUS_COMPLETE, new Long(id)});
  }
  
  public static void cancelPendingElements(String namespace, String name) throws Exception {
    cancelElementsByState(Names.STATUS_PENDING, namespace,name );
  }

  public static void cancelActiveElements(String namespace, String name) throws Exception {
    cancelElementsByState(Names.STATUS_PROCESSING, namespace,name );
  }

  public static void cancelAllElements(String namespace, String name) throws Exception {
    cancelPendingElements(namespace, name);
    cancelActiveElements(namespace, name);
  }
 
  public static void cancelElementsByState(String state, String namespace, String name) throws Exception {
    executeUpdate(SQL_DELETE_Q_ELEMENT, new Object[] {state,namespace,name});
  }
  
  public static void cancelElementByID(long id) throws Exception {
    executeUpdate(SQL_DELETE_Q_ELEMENT_BY_ID, new Object[] {new Long(id)});
  }
  
  public static SortedSet getActiveElements(String namespace, String name, int count) throws Exception {
    return getQElementByState(Names.STATUS_PROCESSING, namespace,name, count);
  }
  public static SortedSet getPendingElements(String namespace, String name, int count) throws Exception {
    return getQElementByState(Names.STATUS_PENDING, namespace,name, count);
  }
  
  public static SortedSet getProcessedElement(String namespace, String name, int count) throws Exception {
    return getQElementByState(Names.STATUS_COMPLETE, namespace,name, count);
  }
  
  private static SortedSet getQElementByState(String state, String namespace, String name, int count) throws Exception {
    if (state == null || state.length() == 0) { throw INVALID_STATE; }
   
    PreparedStatement ps = null;
    ResultSet rs = null;
    SortedSet records = null;
    try {
      ps = database().prepareStatement(SQL_GET_PROCESSING_ELEMENT_BY_STATE);

      // fill in any arguments
      ps.setString(1, state);
      ps.setString(2, namespace);
      ps.setString(3, name);
      ps.setMaxRows(count);
      rs = database().executeQuery(ps);
      records = Schema.unmarshallQElements(rs);
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
    
    return records;
  }
  
  private static boolean executeUpdate(String sql, Object[] args) throws Exception {
    PreparedStatement ps = null;
    ResultSet rs = null;
    boolean result = true;
    try {
      ps = database().prepareStatement(sql);

      // fill in any arguments
      if (args != null) {
        for (int a = 0; a < args.length; a++) {
          if (args[a] instanceof String) {
            ps.setString(a + 1, (String) args[a]);
          } else if (args[a] instanceof Long) {
            ps.setLong(a + 1, ((Long) args[a]).longValue());
          } else if (args[a] instanceof Integer) {
            ps.setLong(a + 1, ((Integer) args[a]).intValue());
          } 
        }
      }
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


  private static synchronized int getNextOrder(int priority, String namespace, String name) throws Exception {
    PreparedStatement ps = null;
    ResultSet rs = null;
    int order = 0;
    try {
      ps = database().prepareStatement(SQL_GET_NEXT_ORDER);
      ps.setLong(1, priority);
      ps.setString(2, namespace);
      ps.setString(3, name);
      rs = database().executeQuery(ps);    
      if (rs.next()) { 
        order = rs.getInt(1); 
      }
        order++;
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
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
    return order;
  }
  
  private static final String SQL_Q_ELEMENT_INSERT = "insert into " + Schema.Table.QX_ELEMENT + " (" 
                                                    + Schema.Column.NAMESPACE + ", " 
                                                    + Schema.Column.NAME + ", " 
                                                    + Schema.Column.PRIORITY + ", " 
                                                    + Schema.Column.QORDER + ", " 
                                                    + Schema.Column.STATE + ", " 
                                                    + Schema.Column.CONTEXT + ", " 
                                                    + Schema.Column.INSTRUCTION + ", " 
                                                    + Schema.Column.SUMMARY + ", " 
                                                    + Schema.Column.ANNOTATIONS + ") values (?,?,?,?,?,?,?,?,?)";

private static final String SQL_GET_NEXT_ORDER =  "select max(" + Schema.Column.QORDER +")" +
                                                    " from "
                                                    + Schema.Table.QX_ELEMENT + 
                                                    " where ("
                                                    + Schema.Column.PRIORITY +
                                                    "=? and "
                                                    + Schema.Column.NAMESPACE+
                                                    "=? and "
                                                    + Schema.Column.NAME +
                                                    "=?" +
                                                    ")";

private static final String SQL_GET_PROCESSING_ELEMENT_BY_STATE = "SELECT "
                                                                  + Schema.Column.ID + ", "
                                                                  + Schema.Column.NAMESPACE + ", " 
                                                                  + Schema.Column.NAME + ", " 
                                                                  + Schema.Column.PRIORITY + ", " 
                                                                  + Schema.Column.QORDER + ", " 
                                                                  + Schema.Column.STATE + ", " 
                                                                  + Schema.Column.CONTEXT + ", " 
                                                                  + Schema.Column.INSTRUCTION + ", " 
                                                                  + Schema.Column.SUMMARY + ", " 
                                                                  + Schema.Column.ANNOTATIONS +
                                                                  " FROM "
                                                                  + Schema.Table.QX_ELEMENT + 
                                                                  " WHERE ("
                                                                  + Schema.Column.STATE + 
                                                                  "=? and "
                                                                  + Schema.Column.NAMESPACE+
                                                                  "=? and "
                                                                  + Schema.Column.NAME +
                                                                  "=?" +
                                                                  ")";

private static final String SQL_UPDATE_Q_ELEMENT = "update " + Schema.Table.QX_ELEMENT + " set "
                                                    + Schema.Column.STATE +
                                                    "=? where ("
                                                    + Schema.Column.ID +
                                                    "=?" +
                                                    ")";

private static final String SQL_DELETE_Q_ELEMENT = "delete " + Schema.Table.QX_ELEMENT + 
                                                    " where ("
                                                    + Schema.Column.STATE + "=?" +
                                                    " and " 
                                                    + Schema.Column.NAMESPACE+ "=?" +
                                                    " and " 
                                                    + Schema.Column.NAME + "=?" +
                                                    ")";

private static final String SQL_DELETE_Q_ELEMENT_BY_ID = "delete " + Schema.Table.QX_ELEMENT + 
                                                     " where ("
                                                     + Schema.Column.ID + "=?" +
                                                     ")";


/*select  id, priority, qorder, state from qxelement group by id, priority, qorder, state
having (state='pending') order by priority,qorder;*/
private static final String SQL_GET_Q_ELEMENT_BY_ID ="select  " 
                                    + Schema.Column.ID + ", "
                                    + Schema.Column.NAMESPACE + ", " 
                                    + Schema.Column.NAME + ", " 
                                    + Schema.Column.PRIORITY + ", " 
                                    + Schema.Column.QORDER +  ", " 
                                    + Schema.Column.STATE + ", " 
                                    + Schema.Column.CONTEXT + ", " 
                                    + Schema.Column.INSTRUCTION + ", " 
                                    + Schema.Column.SUMMARY + ", " 
                                    + Schema.Column.ANNOTATIONS + 
                                    " from " + Schema.Table.QX_ELEMENT + 
                                    " where (" 
                                    + Schema.Column.ID + 
                                    "=?)";


private static final String SQL_GET_NEXT_Q_ID ="select  "
                                  + Schema.Column.ID + 
                                  " from " + Schema.Table.QX_ELEMENT + 
                                  " where (" 
                                  + Schema.Column.STATE + 
                                  "=? AND "  
                                  + Schema.Column.NAMESPACE + 
                                  "=? AND " 
                                  + Schema.Column.NAME + 
                                  "=?" +
                                  ") order by "
                                  + Schema.Column.PRIORITY + "," 
                                  + Schema.Column.QORDER;

  /** Exceptions throws by methods in this class. */
  public static final Exception NO_DB_CONNECTION = new Exception("No Db Connection");

  /** The Constant INVALID_NAMESPACE. */
  public static final Exception INVALID_NAMESPACE = new Exception("Invalid Namespace specified");

  /** The Constant INVALID_NAME. */
  public static final Exception INVALID_NAME = new Exception("Invalid Name specified");

  public static final Exception INVALID_STATE = new Exception("Invalid State specified");
  public static final Exception INVALID_INSTRUCTION = new Exception("Invalid Instruction specified");

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
    return DB.source(Names.QX_ELEMENT_DATABASE);
  }

}
