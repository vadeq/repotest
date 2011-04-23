/*
 * DesignState - Design Compression Technology @author: Rodney McCabe @version: 1.0 Created on 11/23/2007 Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved
 */

package zws.database;

import zws.exception.DriverNotFound;
import zws.exception.InvalidConfiguration;
import zws.exception.InvalidSyntax;
import zws.exception.NotConnected;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Oracle extends DatabaseBase {

  private String driverName = "oracle.jdbc.driver.OracleDriver";
  private String port = "1521";
  private String connectionUrl;
  public String getDatabaseType() {
    return getClass().getName();
  }

  // jdbc:oracle:thin:@<host>:<port>:<database>
  public String getConnectionURL() throws InvalidConfiguration {
    if (connectionUrl == null) throw new InvalidConfiguration("Invalid Connection URL");
    return connectionUrl;
  }

  public void setUrl(String connectionUrl) {
    this.connectionUrl = connectionUrl;
  }

  protected Connection getConnection() throws NotConnected, DriverNotFound {
    String url="";
    Connection oracleConnection = null;
    try {
    	  dbProperties.put("user", getUsername());
    	  dbProperties.put("password", getPassword());
    	  dbProperties.put("SetBigStringTryClob", "true");
    		if (null==getDriver()) {
    		  registerDriver();
    		}
    		url = getConnectionURL();
    		oracleConnection  = DriverManager.getConnection(url, dbProperties);
    		//return DriverManager.getConnection(url, getUsername(), getPassword());
    		} catch (InvalidConfiguration e) {
    			e.printStackTrace();
    			throw new NotConnected("Invalid Configuration: " + e.getMessage());
    		} catch (SQLException e) {
    			e.printStackTrace();
    			throw new NotConnected(url);
    		}
    		return oracleConnection;
	}

  public String limitResults(String sql, int rows) {

    StringBuffer query = new StringBuffer("select * from (");
    query.append(sql).append(") where rownum<=").append(rows);

    return query.toString();
  }

  public String formatDate(Calendar calendar) throws InvalidSyntax {

    StringBuffer b = new StringBuffer();

    b.append(calendar.get(Calendar.YEAR)).append(DASH);
    b.append(calendar.get(Calendar.MONTH)).append(DASH);
    b.append(calendar.get(Calendar.DAY_OF_MONTH));
    b.append(SPACE);
    b.append(calendar.get(Calendar.HOUR_OF_DAY)).append(COLON);
    b.append(calendar.get(Calendar.MINUTE)).append(COLON);
    b.append(calendar.get(Calendar.SECOND));

    return b.toString();
  }

  public Calendar parseDate(String value) throws InvalidSyntax {

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Calendar calendar = new GregorianCalendar();

    try {
      calendar.setTime(formatter.parse(value));
    } catch (java.text.ParseException e) {
      throw new InvalidSyntax(e.getMessage());
    }
    return calendar;
  }

  // exposes better naming for configuration
  public void setDriverName(String driverName) {
    setDriverFQCN(driverName);
  }

  // exposes better naming for configuration
  public String getDriverName() {
    return getDriverFQCN();
  }

  public String getDriverFQCN() {
    return driverName;
  }

  public void setDriverFQCN(String driverName) {
    this.driverName = driverName;
  }

  public String getPort() {
    return port;
  }

  public void setPort(String port) {
    this.port = port;
  }
}
