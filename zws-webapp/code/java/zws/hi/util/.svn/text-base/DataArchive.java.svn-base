/* DesignState - Design Compression Technology
 * @author: Rodney
 * @version: 1.0
 * Created on Apr 28, 2008 8:35:48 AM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.hi.util;

import com.zws.hi.Interactor;
import java.io.*;
import java.sql.*;
import java.util.*;
import zws.application.Names;
import zws.database.DB;
import zws.database.Database;
import java.text.SimpleDateFormat;
import zws.application.Properties;

public class DataArchive extends Interactor {

  public static final String KEY_BACKUP_PATH = "zws-backup";
  public static final String KEY_BACKUP_TABLES = "zws-backup-tables";
  private List messages = new ArrayList();
  private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
  
  // set default tables
  private static String[] affectedTables = { "activity", "process", "family", "qxelement", "qxqueue", "synchronizationlog" };

  
  static {
	  ArrayList tables = new ArrayList(Properties.getCollection(KEY_BACKUP_TABLES));
	  affectedTables = new String[tables.size()];
	  tables.toArray(affectedTables);
  }
  
  public String archiveData() {
    
    messages.clear();    
    for (int i=0; i<affectedTables.length; i++) archiveTable(affectedTables[i]);
    return "index";
  }

  private void archiveTable(String tablename) {
 
    File table = new File(buildFilename(tablename));
    ResultSet rows = null;
    PreparedStatement statement = null;
    int numRows=0;
    
    try {  
      
      PrintStream out = new PrintStream(table);
      
      Database db = DB.source(Names.SYNCHRONIZATION_DATABASE);
      statement = db.prepareStatement("select * from " + tablename);
      rows = db.executeQuery(statement);
      
      // print out column headers
      ResultSetMetaData meta = rows.getMetaData();
      int colCount = meta.getColumnCount();
      
      for(int i=1; i<=colCount; i++) {
        out.print(meta.getColumnName(i));
        if (i != colCount) out.print(",");
      }
      out.println();
      out.flush();
      
      // print out data
      while (rows.next()) {        
        for (int d=1; d<=colCount; d++) {
          out.print(rows.getString(d));
          if (d != colCount) out.print(",");          
        }
       
        out.println();
        out.flush();
        numRows++;
      }      
      
      out.close();  
      
      messages.add("Extracted " + table + " [" + numRows + "]");
      
    } catch (Exception e) {
      messages.add("Error in " + getClass().getName() + ": " + e.getMessage());
      messages.add("This occurred while creating file " + table);
    } finally {      
      try { rows.close(); statement.close();} catch (Exception e) {};
    }
  }
 
  private String buildFilename(String tablename) {
    
    StringBuilder path = new StringBuilder(getBackupLocation());
    
    if ( path.charAt(path.length()-1) != File.separatorChar )
      path.append(File.separator);
    
    path.append(tablename);
    path.append("_");
    path.append(sdf.format(new java.util.Date()));
    path.append(".csv");
    
    return path.toString();
  }
    
  public String getBackupLocation() {
    return Properties.get(KEY_BACKUP_PATH);    
  }
  
  public void startRequest() throws Exception { messages.clear();}
  public String[] getAffectedTables() { return affectedTables; }
  public List getMessages()           { return messages; }

}
