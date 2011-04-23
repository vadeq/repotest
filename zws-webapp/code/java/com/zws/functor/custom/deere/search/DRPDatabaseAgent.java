package com.zws.functor.custom.deere.search;

import zws.database.DB;
import zws.database.Database;

import com.zws.domo.document.Document;
import com.zws.functor.custom.deere.finder.DRPFinder;
import com.zws.functor.finder.Finder;
import com.zws.functor.search.SearchAgent;
//import com.zws.service.Database;
import com.zws.util.Profiler;

import java.sql.*;
import java.util.*;

public class DRPDatabaseAgent extends SearchAgent {

  private Document unmarshallDocument(ResultSet r) throws SQLException {
    //what to do here:
    //d = new Document(uniqueURI)
    //meta = doc.getMetadata()
    //foreach field=r.fields: meta.setValue(field.name, f.value)
    //return d
    String drpName, name, value, rev, ver, dec, unit;
    name = r.getString("PART_NUMBER"); if (null==name) name=""; name=name.trim();
    rev = r.getString("REVISION"); if (null==rev) rev=""; rev=rev.trim();
    ver=r.getString("VERSION_NUMBER"); if (null==ver) ver=""; ver=ver.trim();
    dec=r.getString("DECISION"); if(null==dec) dec=""; dec=dec.trim();
    unit=r.getString("VERSION_UNIT"); if (null==unit)unit=""; unit=unit.trim();
    drpName = name + rev + ver + dec + unit + getDataSourceName();
    Document doc = new Document();
    doc.setName(name);
    String fieldName;
    Iterator i = getFieldList().iterator();
    while (i.hasNext()) {
      fieldName = i.next().toString();
      value = r.getString(fieldName);
      if (null==value) value="";
      doc.set(fieldName, value.trim());
    }
    return doc;
  }

  private Collection getFieldList(){
    Collection c = new Vector();
    c.add("COMP_ID");
    c.add("DCBDA");
    c.add("DECISION");
    c.add("DRP_IMAGE_FOR_REP");
    c.add("FILE_NAME");
    c.add("FRAME_NUMBER");
    c.add("IMAGE");
    c.add("NUMBER_OF_FRAMES");
    c.add("PART_NUMBER");
    c.add("REVISION");
    c.add("SEQUENCE_DATE");
    c.add("SHEET_NUMBER");
    c.add("SHEET_SIZE");
    c.add("VERSION_NUMBER");
    c.add("VERSION_UNIT");
    return c;
  }

  //modify this function to execute the query
  //getFieldList() will get you all the metadata attributes that are entered in the system
  //getCriteria();  //String:search criteria to find documents
  //getOffset();    //long: index into resultset to start returning rows
  //getMaxCount();  //long: number of rows to return
  //getSortBy();    //String: sorted by this field (not used yet)
  //getSortAscending();  // boolean: true if data should be sorted in ascending order

  public void execute(){
    Profiler profiler = new Profiler();
    profiler.start("DRP-search-agent", "DRP-search-agent");
    profiler.start("DRP-search-agent", "init");
    Collection c=null;
    String sql,crit, fieldList;
    Document doc = null;
    Map results = new HashMap();
    ResultSet resultSet = null;
    Connection connection;
    profiler.stop("DRP-search-agent", "init");
    try {
      profiler.start("DRP-search-agent", "connect");
      //connection=Database.connect(getDataSourceName());
      profiler.stop("DRP-search-agent", "connect");
      //Statement statement = database().createStatement();
      StringTokenizer tok = new StringTokenizer(getCriteria(), " ");
      sql = "SELECT * FROM CIDBP01.GMS01_DRP WHERE PART_NUMBER LIKE '";
      while (tok.hasMoreTokens()) {
        crit = tok.nextToken().toUpperCase().replace('*', '%') + "%";
        {} //System.out.println("query:" + sql +crit+"'");
        profiler.start("DRP-search-agent", "query for " + crit);
        PreparedStatement statement = database().prepareStatement(sql +crit+"'");
        resultSet = database().executeQuery(statement);
        //resultSet = statement.executeQuery(sql +crit+"'");
        profiler.stop("DRP-search-agent", "query for " + crit);
        profiler.start("DRP-search-agent", "unmarshall results");
        while (resultSet.next()) {
          doc = unmarshallDocument(resultSet);
          Document newDoc=map(doc);
          doc.setFinder(createFinder(doc));
          add(newDoc);
        }
        profiler.stop("DRP-search-agent", "unmarshall results");
//        resultSet.close();
      }
//      connection.close();
      //Database.release(connection);
      c = results.values();
    }
    catch (Exception e) { e.printStackTrace(); }
    profiler.stop("DRP-search-agent", "DRP-search-agent");
    profiler.dump(System.out);
  }

  public Finder createFinder(Object document) {
    DRPFinder finder = new DRPFinder();
    Document doc = (Document) document;
    finder.setBinary(doc.get("IMAGE"));
    finder.setImageName(doc.get("IMAGE"));
    finder.setDataSource(this);
    return finder;
  }

  private Database database()  throws Exception { return DB.source(getDataSourceName()); }
  
}