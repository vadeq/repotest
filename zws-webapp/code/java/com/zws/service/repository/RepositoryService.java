package com.zws.service.repository;

import com.zws.application.Config;
import com.zws.application.Constants;
import com.zws.domo.document.Document;
import com.zws.domo.document.Reference;
import com.zws.exception.DuplicateEntry;
//import com.zws.service.Database;
import com.zws.util.KeyValue;

import java.sql.*;
import java.util.*;

public class RepositoryService {
  public static void addDocument(Document doc) throws Exception {}
  public static void updateDocument(Document doc) throws Exception {}
  public static Document findDocument(String origin) throws SQLException { return null; }
  public static Document loadChildren(Document doc) throws SQLException { return null; }
    /*
  private RepositoryService() { }
  public static RepositoryService getService(){ if (service==null) service=new RepositoryService(); return service; }

  private static String metadataField(Collection attributes) {
    String s="";
    KeyValue pair;
    String delim = Constants.METADATA_DELIMITER;
    Iterator i = attributes.iterator();
    while (i.hasNext()){
      pair = (KeyValue)i.next();
      s += pair.getKey() +"="+pair.getValue() + delim;
    }
    return s;
  }

  public static boolean contains(String origin) throws SQLException {
    if (null==origin) return false;
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = connection.prepareStatement("SELECT COUNT('z') FROM document WHERE origin=?");
    statement.setString(1, origin);
    ResultSet resultSet = statement.executeQuery();
    long count=0;
    if (resultSet.next()) count = resultSet.getLong(1);
    Database.release(connection);
    if (0<count)return true; else return false;
  }

  public static boolean hasChildren(String origin) throws SQLException {
    if (null==origin) return false;
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = connection.prepareStatement("SELECT COUNT('z') FROM subDocument WHERE parentOrigin=?");
    statement.setString(1, origin);
    ResultSet resultSet = statement.executeQuery();
    long count=0;
    if (resultSet.next()) count = resultSet.getLong(1);
    Database.release(connection);
    if (0<count)return true; else return false;
  }

  public static boolean hasChild(String parentOrigin, String childOrigin) throws SQLException {
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = connection.prepareStatement("SELECT * FROM subDocument WHERE parentOrigin=? AND childOrigin=?");
    statement.setString(1, parentOrigin);
    statement.setString(2, childOrigin);
    ResultSet resultSet = statement.executeQuery();
    boolean childExists=false;
    if (resultSet.next()) childExists=true;
//    resultSet.close();
    Database.release(connection);
    return childExists;
  }

  public static void addDocument(Document doc) throws Exception {
    String metadata = metadataField(doc.getAttributes());
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = connection.prepareStatement("INSERT INTO document VALUES(?,?,?,?)");
    String path = doc.get(Constants.METADATA_PATH);
    statement.setString(1, doc.getOrigin());
    statement.setString(2, path);      //+++todo remove path as a column
    statement.setString(3, doc.getName());
    statement.setString(4, metadata);
    statement.execute();
    Database.release(connection);
    addDocumentChildren(doc);
  }

  public static void updateDocument(Document doc) throws Exception {
    String metadata = metadataField(doc.getAttributes());
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = connection.prepareStatement("UPDATE document Set metadata=? WHERE origin=?");
    String path = doc.get(Constants.METADATA_PATH);
    statement.setString(1, metadata);
    statement.setString(2, doc.getOrigin());
    statement.execute();
    Database.release(connection);
    updateDocumentChildren(doc);
  }

  public static void addDocumentChildren(Document doc) throws Exception {
    if (null==doc.getChildren()) return;
    Iterator i = doc.getChildren().iterator();
    while (i.hasNext()) addSubDocumentReference(doc.getOrigin(), (Reference)i.next());
  }

  public static void updateDocumentChildren(Document doc) throws Exception {
    if (null==doc.getChildren()) return;
    Iterator i = doc.getChildren().iterator();
    while (i.hasNext()) updateSubDocumentReference(doc.getOrigin(), (Reference)i.next());
  }

  public static void addSubDocumentReference(String parentOrigin, Reference ref) throws Exception {
    if (null!=findDocumentReference(parentOrigin, ref.getOrigin())) throw new DuplicateEntry("repository reference", ref.getOrigin());
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = connection.prepareStatement("INSERT INTO subDocument VALUES(?,?,?)");
    statement.setString(1,parentOrigin);
    statement.setString(2,ref.getOrigin());
    statement.setInt(3,ref.getCount());
    statement.execute();
    Database.release(connection);
  }

  public static void updateSubDocumentReference(String parentOrigin, Reference ref) throws Exception {
    if (!hasChild(parentOrigin, ref.getOrigin())) {
      addSubDocumentReference(parentOrigin, ref);
      return;
    }
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = connection.prepareStatement("UPDATE subDocument SET count=? WHERE parentOrigin=? AND childOrigin=?");
    statement.setString(1,parentOrigin);
    statement.setString(2,ref.getOrigin());
    statement.setInt(3,ref.getCount());
    statement.execute();
    Database.release(connection);
  }

  public static Reference findDocumentReference(String parentOrigin, String childOrigin) throws SQLException {
    Reference ref = null;
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = connection.prepareStatement("SELECT * FROM subDocument WHERE parentOrigin=? AND childOrigin=?");
    statement.setString(1, parentOrigin);
    statement.setString(2, childOrigin);
    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) ref = unmarshallReference(resultSet);
//    resultSet.close();
    Database.release(connection);
    return ref;
  }

  public static Document loadChildren(Document doc) throws SQLException {
    if (null==doc) return null;
    Reference ref = null;
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = connection.prepareStatement("SELECT * FROM subDocument WHERE parentOrigin=?");
    statement.setString(1, doc.getOrigin());
    ResultSet resultSet = statement.executeQuery();
    while (resultSet.next()) doc.add(unmarshallReference(resultSet));
//    resultSet.close();
    Database.release(connection);
    return doc;
  }

  public static Document findDocument(String origin) throws SQLException {
    Document doc = null;
    if (null==origin) throw new NullPointerException("can not find a document with no origin");
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = connection.prepareStatement("SELECT * FROM document WHERE origin=?");
    statement.setString(1, origin);
    ResultSet resultSet = statement.executeQuery();
    while (resultSet.next()) doc = unmarshallDocument(resultSet);
//    resultSet.close();
    Database.release(connection);
    loadChildren(doc);
    return doc;
  }

  public static Collection findAll() throws Exception{
    Document doc = null;
    Collection c = new Vector();
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = connection.prepareStatement("SELECT * FROM document");
    ResultSet resultSet = statement.executeQuery();
    while (resultSet.next()) {
      doc = unmarshallDocument(resultSet);
      loadChildren(doc);
      c.add(doc);
    }
//    resultSet.close();
    Database.release(connection);
    return c;
  }

  public static Collection findDuplicateNames()throws Exception  {
    Document doc = null;
    Collection c = new Vector();
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = connection.prepareStatement("DROP TABLE IF EXISTS dups;");
    statement.execute();
    statement = connection.prepareStatement("create temporary table dups SELECT origin, name, count(*) cnt FROM document GROUP BY name HAVING cnt > 1;");
    statement.execute();
    statement = connection.prepareStatement("select document.* from document, dups where document.name=dups.name order by document.name;");
    ResultSet resultSet = statement.executeQuery();
    while (resultSet.next()) {
      doc = unmarshallDocument(resultSet);
//      loadChildren(doc);
      c.add(doc);
    }
    Database.release(connection);
    return c;
  }

  
  public static void deleteDocument(String origin) throws Exception {
    try {
      if (null==origin) throw new NullPointerException("can not delete a document with no origin");
      Connection connection = Database.connect(dataSourceName);
      PreparedStatement statement = connection.prepareStatement("DELETE FROM document WHERE origin=?");
      statement.setString(1, origin);
      statement.execute();
      Database.release(connection);
    }
    catch (Exception e) { e.printStackTrace(); throw e; }
  }

  private static Collection getFields() {
    Collection keyValues;
    Collection fields = new Vector();
    try {
      keyValues = MetadataConfigurator.getConfiguration();
      Iterator i = keyValues.iterator();
      while (i.hasNext())fields.add(((KeyValue)i.next()).getKey());
    }
    catch (Exception e){ e.printStackTrace(); }
      return fields;
  }

  public static Document unmarshallDocument(ResultSet r) throws SQLException {
    String data;
    int split;
    String name = r.getString("name");
    if (null==name) name = r.getString("document.name");
    String origin = r.getString("origin");
    if (null==origin) origin = r.getString("document.origin");
    String pdfName = name.substring(0, name.length()-4);
    Document doc = new Document();
    doc.setName(name);
    doc.setOrigin(origin);
    String mdata = r.getString("metadata");
    if (null==mdata) mdata = r.getString("document.metadata");
    StringTokenizer tok = new StringTokenizer(mdata, Constants.METADATA_DELIMITER);
    String att, val;
    while(tok.hasMoreTokens()) {
      data = tok.nextToken();
      split = data.indexOf("=");
      if (-1!=split) { 
        att=data.substring(0,split);
        val=data.substring(split+1);
      }
      else {
        att=data;
        val=" ";
      }
      doc.set(att,val);
    }
    return doc;
  }

  public static Reference unmarshallReference(ResultSet r) throws SQLException {
    Reference ref = new Reference();
    ref.setOrigin(r.getString("childOrigin"));
    ref.setCount(r.getInt("count"));
    return ref;
  }

  private static RepositoryService service = null; //singleton
  private static String dataSourceName = Config.getProperty(Config.DATA_SOURCE_APPLICATION_DATABASE);
  private static Collection searchAgents = null;
  */
}
