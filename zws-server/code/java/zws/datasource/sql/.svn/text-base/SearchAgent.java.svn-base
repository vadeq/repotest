package zws.datasource.sql;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.datasource.DatasourceSearchAgentBase;
//impoer zws.util.{}//Logwriter;

import java.sql.*;
import java.util.StringTokenizer;

public class SearchAgent extends DatasourceSearchAgentBase {

  public void executeQuery() throws Exception {
    {}//Logwriter.printOnConsole("SQL Search: starting" );
    SQLSource source = (SQLSource)getDatasource();
    Connection c = source.getConnection();
    String clause = constructWhereClause();
    if ("".equals(clause.trim())) {
      {}//Logwriter.printOnConsole("Empty Where Clause for criteria = " + getCriteria());
      return;
    }
    String sql = "SELECT ";
    sql += " * from "+source.getDatabaseName()+"."+source.getTableName()+" WHERE "+ clause;
    if (0 < getMaxCount()) { // +++ update for other types of databases besides db2
//      if (SQLSource.DB2.equals(SQLSource.getDBType())) 
      sql += " FETCH FIRST "+getMaxCount()+" ROWS ONLY";
    }
    {}//Logwriter.printOnConsole("SQL Search: "  + sql);
    PreparedStatement s = c.prepareStatement(sql);
    ResultSet r = s.executeQuery();
    while (r.next()) store(unmarshall(r));
    r.close();
    s.close();
    source.release(c);
  }
  
  private String constructWhereClause() { //create criteria formatters instead!!
    StringTokenizer tokens = new StringTokenizer(getCriteria().toString(), "=");
    String criteria=tokens.nextToken();
    String token = null;
    while (tokens.hasMoreTokens()) {
      token = tokens.nextToken();
      if (-1 < token.indexOf('*')) criteria += "~"+token;
      else criteria += "="+token;
    }
    StringBuffer spec = new StringBuffer(criteria);
    StringBuffer whereClause = new StringBuffer();
    String mode = "normal";
    for (int idx=0; idx<getCriteria().toString().length(); idx++){
      if ('[' == spec.charAt(idx)) whereClause.append('(');
      else if (']' == spec.charAt(idx)) whereClause.append(')');
      else if ('&' == spec.charAt(idx)) whereClause.append("AND");
      else if ('|' == spec.charAt(idx)) whereClause.append("OR");
      else if ('=' == spec.charAt(idx)) {
        whereClause.append("='");
        mode = "value";
      }
      else if ('~' == spec.charAt(idx)) {
        whereClause.append(" LIKE '");
        mode = "value";
      }
      else if ("value".equals(mode)){
        if ('*' == spec.charAt(idx)) whereClause.append('%');
        else if (' ' == spec.charAt(idx)){
          whereClause.append("' ");
          mode="normal";
        }
        else whereClause.append(spec.charAt(idx));
      }
      else whereClause.append(spec.charAt(idx));
    }
    
    return whereClause.toString();
  }
  
  private Metadata unmarshall(ResultSet r) {
    try {
      MetadataBase data = new MetadataBase();
      ResultSetMetaData rsmd = r.getMetaData();
      int numberOfColumns = rsmd.getColumnCount();
      for (int col=1; col <= numberOfColumns; col++) data.set(rsmd.getColumnLabel(col), r.getString(col));
      data.setOrigin(((SQLSource)getDatasource()).createOrigin(data));
      return data;
    }
    catch(Exception e) {e.printStackTrace(); return null; } //do something w/ this exception;
  }
}
