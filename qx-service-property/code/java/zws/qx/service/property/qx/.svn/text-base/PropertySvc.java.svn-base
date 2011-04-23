package zws.qx.service.property.qx;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import zws.application.Properties;
import zws.database.Database;
import zws.database.DB;
import zws.util.KeyValue;

public class PropertySvc {

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

  public static void load(KeyValue pair) throws Exception {
	  PreparedStatement ps = null;
	    ResultSet rs = null;
	    String name = null;
	    String nameSpace = null;
	    String value = null;
	    try {
	      ps = database().prepareStatement("select NAMESPACE, NAME, PROPERTY_VALUE from properties");
	              

	      rs = database().executeQuery(ps);
	      while(rs.next()) {
	    	  nameSpace = rs.getString("NAMESPACE");
	    	  name = rs.getString("NAME");
	    	  value = rs.getString("PROPERTY_VALUE");
	    	  System.out.println(nameSpace + "-" + name + "-"+ value);
	    	  Properties.set(name, value);
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
   * Get database connection.
   *
   * @return Database connection
   *
   * @throws Exception the exception
   */
  private static Database database() throws Exception {
    //return DB.source(Names.PROPERTIES_DATABASE);
    return DB.source("zws-propertties-db");
  }


}
