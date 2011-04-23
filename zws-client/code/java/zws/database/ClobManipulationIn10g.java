package zws.database;

// Java SQL classes
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
// Oracle JDBC driver class
import oracle.jdbc.OracleDriver;
// Java IO classes
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

// Java Util classesimport java.util.Properties;
/**
 * This class demonstrates the Oracle JDBC 10g enhanced features for inserting and retrieving CLOB 
 * data from the database. Using the new features, large data of more than 32765 bytes can be 
 * inserted into the database using the existing PreparedStatement.setString() and PreparedStatement.getString() 
 * methods.
 */
public class ClobManipulationIn10g {
  /* Database Connection object */
  private Connection conn = null;

  /* Variables to hold database details */private String url = null;

  private String user = null;

  private String password = null;

  // Create a property object to hold the username, password and  
  // the new property SetBigStringTryClob.  
  private Properties props = new Properties();

  /* String to hold file name */
  private String fileName = null;

  /**
   * * Default Constructor to instantiate and get a handle to class methods * and variables.
   */
  public ClobManipulationIn10g(String fileName) {
    this.fileName = fileName;
  }

  public ClobManipulationIn10g() {
   
  }
  /** * Main runnable class. */
  public static void main(String[] args) throws SQLException {
    // Instantiate the main class.    
    //ClobManipulationIn10g clobManipulationIn10g = new ClobManipulationIn10g(args[0]);
    ClobManipulationIn10g clobManipulationIn10g = new ClobManipulationIn10g();
    // Load the Oracle JDBC driver class.   
    DriverManager.registerDriver(new OracleDriver());
    // Load the database details into the variables.   
    //String dbUrl = "jdbc:oracle:thin:@<database host machine>:<port>:<SID>";
    String dbUrl = "jdbc:oracle:thin:@10.10.10.185:1521:zwsdev";
    clobManipulationIn10g.url = dbUrl;
    // Replace the username where the table 'clob_tab' was created.    
    clobManipulationIn10g.user = "DesignState";
    // Replace the password of the username.    
    clobManipulationIn10g.password = "zero0";
    // Populate the property object to hold the username, password and    
    // the new property 'SetBigStringTryClob' which is set to true. Setting    
    // this property allows inserting of large data using the existing   
    // setString() method, to a CLOB column in the database.   
    clobManipulationIn10g.props.put("user", clobManipulationIn10g.user);
    clobManipulationIn10g.props.put("password", clobManipulationIn10g.password);
    clobManipulationIn10g.props.put("SetBigStringTryClob", "true");
    // Check if the table 'CLOB_TAB' is present in the database.   
    clobManipulationIn10g.checkTables();
    // Call the methods to insert and select CLOB from the database.    
    clobManipulationIn10g.insertClob();
    clobManipulationIn10g.selectClob();
  }

  /*  *  This method will insert the data into a CLOB column in the database.  
   * *  Oracle JDBC 10g has enhanced the existing PreparedStatement.setString()  
   * *  method for setting the data more than 32765 bytes. So, using setString(),  
   * *  it is now easy to insert CLOB data into the database directly.  */
  private void insertClob() throws SQLException {
    // Create a PreparedStatement object.    
    PreparedStatement pstmt = null;
    try { // Create the database connection, if it is closed.      
      if ((conn == null) || conn.isClosed()) {
        // Connect to the database.        
        conn = DriverManager.getConnection(this.url, this.props);
      }
      // Create SQL query to insert data into the CLOB column in the database.      
      String sql = "INSERT INTO activity(id, domain,node,type,message,annotations) VALUES(?,?,?,?,?,?)";
      // Read a big file(larger than 32765 bytes)      
      //String str = this.readFile();
      // Create the OraclePreparedStatement object      
      pstmt = conn.prepareStatement(sql);
      // Use the same setString() method which is enhanced to insert      
      // the CLOB data. The string data is automatically transformed into a      
      // clob and inserted into the database column. Make sure that the      
      // Connection property - 'SetBigStringTryClob' is set to true for      
      // the insert to happen.      
      pstmt.setDouble(1, 67591);
      pstmt.setString(2,"domain1");
      pstmt.setString(3,"node1");
      pstmt.setString(4,"type1");
      pstmt.setString(5,"message1");
      pstmt.setString(6,"Clob value");
      // Execute the PreparedStatement     
      pstmt.executeUpdate();
    } catch (SQLException sqlex) {
      // Catch Exceptions and display messages accordingly.       
      System.out.println("SQLException while connecting and inserting into " + "the database table: " + sqlex.toString());
    } catch (Exception ex) {
      System.out.println("Exception while connecting and inserting into the" + " database table: " + ex.toString());
    } finally {
      // Close the Statement and the connection objects.       
      if (pstmt != null) pstmt.close();
      if (conn != null) conn.close();
    }
  }

  /*  * This method reads the CLOB data from the database by using getString()  
   * * method.  */
  private void selectClob() throws SQLException {
    // Create a PreparedStatement object    
    PreparedStatement pstmt = null;
    // Create a ResultSet to hold the records retrieved.    
    ResultSet rset = null;
    try { // Create the database connection, if it is closed.      
      if ((conn == null) || conn.isClosed()) {
        // Connect to the database.        
        conn = DriverManager.getConnection(this.url, this.props);
      }
      // Create SQL query statement to retrieve records having CLOB data from     the database.     
      String sqlCall = "SELECT annotations FROM activity";
      pstmt = conn.prepareStatement(sqlCall);
      // Execute the PrepareStatement      
      rset = pstmt.executeQuery();
      String clobVal = null;
      // Get the CLOB value from the resultset      
      while (rset.next()) {
        clobVal = rset.getString(1);
        System.out.println("CLOB length: " + clobVal.length());
        System.out.println("CLOB value: " + clobVal);
      }
    } catch (SQLException sqlex) {
      // Catch Exceptions and display messages accordingly.        
      System.out.println("SQLException while connecting and querying the " + "database table: " + sqlex.toString());
    } catch (Exception ex) {
      System.out.println("Exception while connecting and querying the " + "database table: " + ex.toString());
    } finally {
      // Close the resultset, statement and the connection objects.        if (rset !=null) rset.close();
      if (pstmt != null) pstmt.close();
      if (conn != null) conn.close();
    }
  }

  /**
   * * Method to check if the table ('CLOB_TAB') exists in the database; if not * then it is created. * * Table Name: CLOB_TAB * Column Name Type * ----------------------------------- * col_col CLOB
   */
  private void checkTables() {
    Statement stmt = null;
    ResultSet rset = null;
    try {
      // Create the database connection, if it is closed.      
      if ((conn == null) || conn.isClosed()) {
        // Connect to the database.        
        conn = DriverManager.getConnection(this.url, this.props);
      } // Create Statement object      
      stmt = conn.createStatement();
      // Check if the table is present      
      rset = stmt.executeQuery(" SELECT table_name FROM user_tables " + " WHERE table_name = 'CLOB_TAB' ");
      // If the table is not present, then create the table.      
      if (!rset.next()) { // Table does not exist, create it        
        stmt.executeUpdate(" CREATE TABLE clob_tab(clob_col CLOB)");
      }
    } catch (SQLException sqlEx) {
      System.out.println("Could not create table clob_tab : " + sqlEx.toString());
    } finally {
      try {
        if (rset != null) rset.close();
        if (stmt != null) stmt.close();
        if (conn != null) conn.close();
      } catch (SQLException ex) {
        System.out.println("Could not close objects in checkTables method : " + ex.toString());
      }
    }
  }

  /*  * This method reads the specified text file and, returns the content  * as a string.  */
  private String readFile() throws FileNotFoundException, IOException {
    // Read the file whose content has to be passed as String      
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    String nextLine = "";
    StringBuffer sb = new StringBuffer();
    while ((nextLine = br.readLine()) != null) {
      sb.append(nextLine);
    } // Convert the content into to a string      
    String clobData = sb.toString();
    // Return the data.      
    return clobData;
  }
}
