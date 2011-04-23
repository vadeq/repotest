package zws.datasource.intralinkSQL;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.Server;
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.datasource.DatasourceSearchAgentBase;
import zws.origin.Origin;
import zws.origin.OriginMaker;
//impoer zws.util.{}//Logwriter;

import java.sql.*;
import java.util.StringTokenizer;

public class SearchAgent extends DatasourceSearchAgentBase {

  public void executeQuery() throws Exception {
    {}//Logwriter.printOnConsole("ProiOracle Search: starting" );
    ProiOracleSource source = (ProiOracleSource)getDatasource();
    Connection c = source.getConnection();
	if (null!=getCriteria() && "".equals(getCriteria().toString().trim())) {
	  {}//Logwriter.printOnConsole("Empty Where Clause for criteria = " + getCriteria());
	  return;
	}
    String clause = constructWhereClause();
    
    String sql = "SELECT " + getSelectClause();
    sql += " from "+getFromClause()+" WHERE "+ clause;
    if (0 < getMaxCount()) { // +++ update for other types of databases besides db2
	  sql += " AND ROWNUM <= "+getMaxCount();  // For Oracle
      
}
    {}//Logwriter.printOnConsole("ProiOracle Search: "  + sql);
    PreparedStatement s = c.prepareStatement(sql);
    ResultSet r = s.executeQuery();
    while (r.next()) store(unmarshall(r));
    r.close();
    s.close();
    source.release(c);
  }
  
  private String constructWhereClause() {
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
    
    return whereClause.toString() + " AND " + getPredefinedWhereClause();
  }
  
  private Metadata unmarshall(ResultSet r) {
    try {
      MetadataBase data = new MetadataBase();
      ResultSetMetaData rsmd = r.getMetaData();
      int numberOfColumns = rsmd.getColumnCount();
      for (int col=1; col <= numberOfColumns; col++) data.set(rsmd.getColumnLabel(col), r.getString(col));
      String name = data.get(PINAME);
      
	String branch, revision, version, createdOn;
	branch = data.get(ProiOracleSource.BRANCH);
	revision = data.get(ProiOracleSource.REVISION);
	version = data.get(ProiOracleSource.VERSION);
  createdOn = data.get(ProiOracleSource.CREATED_ON);  
	Origin o = OriginMaker.materialize(Server.getDomainName()+delim+Server.getName()+delim+getDatasource().getType()+delim+getDatasource().getName()+delim+createdOn+branch+delim+revision+delim+version+delim+name);
  data.setOrigin(o);
  {}//Logwriter.printOnConsole("***** 2 Data = " + data);
  return data;
  }
    catch(Exception e) {e.printStackTrace(); return null; } //do something w/ this exception;
  }
  public String getFromClause() { return fromClause; }
  public String getPredefinedWhereClause() { return predefinedWhereClause; }
  public String getSelectClause() { return selectClause; }
  public void setFromClause(String s) { fromClause = s; }
  public void setPredefinedWhereClause(String s) { predefinedWhereClause = s; }
  public void setSelectClause(String string) { selectClause = string; }

  private String selectClause = "";
  private String fromClause = "";
  private String predefinedWhereClause = "";
   
  public static String PINAME = "PINAME";
  private static String delim = Origin.delim;
}

