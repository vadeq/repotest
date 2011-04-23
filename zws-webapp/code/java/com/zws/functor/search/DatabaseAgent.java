package com.zws.functor.search;

import zws.database.DB;
import zws.database.Database;


import com.zws.application.Constants;
import com.zws.domo.document.Document;
import com.zws.functor.finder.Finder;
//import com.zws.service.Database;
import com.zws.service.repository.RepositoryService;

import java.sql.*;
import java.util.Collection;
import java.util.StringTokenizer;

public class DatabaseAgent extends SearchAgent {

  //customize this function to create a Document from a result set
  private static Document unmarshallDocument(ResultSet r) throws SQLException {
    //what to do here:
    //d = new Document(uniqueURI)
    //meta = doc.getMetadata()
    //foreach field=r.fields: meta.setValue(field.name, f.value)
    //return d

    String data;
    int split;
    String name = r.getString("name");
    String pdfName = name.substring(0, name.length()-4);
    Document doc = new Document();
    doc.setName(name);
    StringTokenizer tok = new StringTokenizer(r.getString("metadata"), Constants.METADATA_DELIMITER);
    while(tok.hasMoreTokens()) {
      data = tok.nextToken();
      split = data.indexOf("=");
      doc.set(data.substring(0,split),data.substring(split+1));
    }
    return doc;
  }

  //modify this function to execute the query
  //getFieldList() will get you all the metadata attributes that are entered in the system
  //getCriteria();  //String:search criteria to find documents
  //getOffset();    //long: index into resultset to start returning rows
  //getMaxCount();  //long: number of rows to return
  //getSortBy();    //String: sorted by this field (not used yet)
  //getSortAscending();  // boolean: true if data should be sorted in ascending order

  public void execute() throws Exception{
    Collection c=null;
    String sql,crit, fieldList;
    Document doc = null;
    ResultSet resultSet = null;
    Connection connection;
    try {
      //connection=Database.connect(getDataSourceName());
      //Statement statement = connection.createStatement();
      StringTokenizer tok = new StringTokenizer(getCriteria(), " ");
      sql = "SELECT * from document WHERE ";
      while (tok.hasMoreTokens()) {
        crit = tok.nextToken().replace('*', '%');
        if (!"%".equals(crit)) crit+="%";
        PreparedStatement statement = database().prepareStatement(sql + "name LIKE '"+crit+"'");
        resultSet = database().executeQuery(statement);
        //resultSet = statement.executeQuery(sql + "name LIKE '"+crit+"'");
        while (resultSet.next()) {
          doc = unmarshallDocument(resultSet);
          doc=map(doc);
          doc.setFinder(createFinder(null));
          RepositoryService.loadChildren(doc);
          add(doc);
        }
//        resultSet.close();
      }
//      connection.close();
      //Database.release(connection);
    }
    catch (Exception e) { e.printStackTrace(); }
  }

  public Finder createFinder(Object ignore) { return (Finder)getFinderPrototype().copy(); }
  private Database database()  throws Exception { return DB.source(getDataSourceName()); }
}