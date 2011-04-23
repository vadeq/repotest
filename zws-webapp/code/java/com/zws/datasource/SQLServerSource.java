package com.zws.datasource;

import com.zws.application.Properties;
import com.zws.domo.document.Document;
import com.zws.domo.document.Reference;
//import com.zws.service.Database;
import com.zws.util.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;

//This class is written for the first time to meet requirements for Tejas.
//This, of course should be redesigned, however; keep in mind backwards
//compatibility with what is deployed at Tejas.

/*
 ID
 folder
 name
 type
 release
 revision
 version
 branch
 isGeneric isInstance
 createdBy
 createdOn
 description
 title_1
 latestVersion
 latestReleasedVersion
*/

public class SQLServerSource extends SQLSource {
  public String getType() { return DATA_SOURCE_SQL_SERVER_DATABASE; }

  private static void registerDriver() throws ClassNotFoundException {
    if (null== SQLServerDriver)
      SQLServerDriver = Class.forName(Properties.get(Properties.SQLServerDriverClass));
  }

  private Connection getConnection() throws SQLException, ClassNotFoundException {
/*
    try { // try to find struts datasource
      Connection c = Database.connect(getName());
      if (null != c)  return c;
    }
    catch (Exception e) {} // try to find datasource manually
    registerDriver();
    return DriverManager.getConnection(getConnectionURL()+";DatabaseName="+ getDatabaseName(),getUsername(),getPassword());
    */
      return null;
  }

  private void releaseConnection(Connection con) throws SQLException  { 
      //Database.release(getName(), con); 
  }

  public void call(String procedureName) throws Exception {
    Connection connection = getConnection();
    {} //System.out.println("Calling stored procedure: procedureName");
    CallableStatement statement = connection.prepareCall(procedureName);
    statement.execute();
  }

  public String getUserDefinedAttributes(String delimiter) throws Exception {
    String attributes="";
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement("SELECT * FROM "+ userDefinedAttributeTable);
    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) attributes += resultSet.getString("name");
    while (resultSet.next()) attributes += delimiter + resultSet.getString("name");
    releaseConnection(connection);
    return attributes;
  }

  private void addUserDefinedAttribute(String attributeName) throws Exception {
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement("INSERT INTO " + userDefinedAttributeTable + "  VALUES(?)");
    statement.setString(1, attributeName);
    statement.execute();
    releaseConnection(connection);
  }

  private void removeUserDefinedAttribute(String attributeName) throws Exception {
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement("DELETE FROM " + userDefinedAttributeTable + " WHERE name='"+attributeName+"'");
    statement.setString(1, attributeName);
    statement.execute();
    //Database.release(connection);
  }


  public static Document unmarshallDocument(ResultSet r) throws SQLException {
    Document doc = new Document();
    doc.setOrigin(r.getString("ID"));
    doc.setName(r.getString("name"));
    return doc;
  }

  public boolean contains(String origin) throws Exception {
    if (null==origin) return false;
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement("SELECT COUNT('z') FROM " + componentTable+ " WHERE ID=?");
    statement.setString(1, OriginUtil.updateDatasourceName(origin,"_"));
    ResultSet resultSet = statement.executeQuery();
    long count=0;
    if (resultSet.next()) count = resultSet.getLong(1);
    //connection.close();
    releaseConnection(connection);
    if (0<count)return true; else return false;
  }

  public boolean hasChildren(String origin) throws Exception {
    if (null==origin) return false;
//    {} //System.out.println("origin=" + origin);
    if (!contains(origin)) return false;
//    {} //System.out.println("assembly loaded");
    Connection connection = getConnection();
    String sql = "SELECT COUNT('z') FROM " + subComponentTable + " WHERE parentID=?";
    PreparedStatement statement = connection.prepareStatement(sql);
//    {} //System.out.println(sql + origin);
    statement.setString(1, OriginUtil.updateDatasourceName(origin,"_"));
    ResultSet resultSet = statement.executeQuery();
    long count=0;
    if (resultSet.next()) count = resultSet.getLong(1);
    //connection.close();
    releaseConnection(connection);
//    {} //System.out.println("count=" + count);
    if (0<count)return true; else return false;
  }

  public Collection findMissingSubComponents() throws Exception {
    Map c = new HashMap();
    Connection connection = getConnection();
    String sql =  "select childID from tblIlinkSUbComponent_stg sc where not exists ( select 'a' field1 from tblILinkComponent_Stg c_s where c_s.ID=childID UNION select 'b' field1 from tblILinkComponent c where c.ID=childID )";
    //"select parentID,childID from " + subComponentTable + " sc left join " +componentTable + " c on  sc.childid = c.id where c.id is null";
    PreparedStatement statement = connection.prepareStatement(sql);
    ResultSet resultSet = statement.executeQuery();
    String childID, name;
    while (resultSet.next()) {
      childID = resultSet.getString("childID");
      name = OriginUtil.getName(childID);
      if (null!= childID) c.put(name , name);
    }
    releaseConnection(connection);
    return c.values();
  }

  public Document find(String origin) throws Exception {
    if (null==origin) throw new NullPointerException("can not find a document with no origin");
    Document doc = null;
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + componentTable + "  WHERE ID=?");
    statement.setString(1, OriginUtil.updateDatasourceName(origin,"_"));
    ResultSet resultSet = statement.executeQuery();
    while (resultSet.next()) doc = unmarshallDocument(resultSet);
    //resultSet.close();
    releaseConnection(connection);
    return doc;
  }

  public void add(Document d) throws Exception{
//    resetOrigin(d);
    Profiler p = new Profiler();
    p.start("add", "add datasource: " + getName());
    d.setOrigin(OriginUtil.updateDatasourceName(d.getOrigin(),"_"));
    addDocumentChildren(d);  
//    addDocument(d);   //system attributes and user-defined attributes stored through BCP.  only store Children for Tejas (Hack)
    p.stop("add", "add datasource: " + getName());
    p.dump(System.out);
  }

  public void update(Document d) throws Exception{
//    resetOrigin(d);
    Profiler p = new Profiler();
    p.start("update", "update datasource: " + getName());
    d.setOrigin(OriginUtil.updateDatasourceName(d.getOrigin(),"_"));
    addDocumentChildren(d);  
//    updateDocument(d); //system attributes and user-defined attributes stored through BCP.  only store Children for Tejas (Hack)
    p.stop("update", "update datasource: " + getName());
    p.dump(System.out);
  }
  
  public void bulkCopy(String componentCSVFile, String attributeCSVFile) throws Exception{
    Profiler p = new Profiler();
    p.start("bulk copy", "bulk copy: " + getName());
    Connection connection = getConnection();

    PreparedStatement statement = connection.prepareStatement("BULK INSERT " + componentTable + " from '" + componentCSVFile + "' WITH (FIELDTERMINATOR = ',', ROWTERMINATOR = '\n' , CODEPAGE = 'RAW', DATAFILETYPE = 'char', FIRSTROW = 1 )"); 
    statement.execute();
    statement = connection.prepareStatement("BULK INSERT " + userAttributeTable + " from '" + attributeCSVFile + "' WITH (FIELDTERMINATOR = ',', ROWTERMINATOR = '\n' , CODEPAGE = 'RAW', DATAFILETYPE = 'char', FIRSTROW = 1 )"); 
    statement.execute();
    releaseConnection(connection);
    p.stop("bulk copy", "bulk copy: " + getName());
    p.dump(System.out);
  }

  public void truncate(String tableName) throws Exception{
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement("truncate table " + tableName); 
    statement.execute();
  }    
  
  public void delete(String origin) throws Exception{return;} //todo:

  private void addDocument(Document doc) throws Exception {
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement("INSERT INTO " + componentTable + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,null,null)");
    statement.setString(1, doc.getOrigin());
    statement.setString(2, getMetadataValue(doc, "Folder"));
    statement.setString(3, doc.getName());
    statement.setString(4, getMetadataValue(doc, "Type"));
    statement.setString(5, getMetadataValue(doc, "Release"));
    statement.setString(6, getMetadataValue(doc, "Revision"));
    statement.setString(7, getMetadataValue(doc, "Version"));
    statement.setString(8, getMetadataValue(doc, "Branch"));
    statement.setString(9, getMetadataValue(doc, "Generic"));
    statement.setString(10, getMetadataValue(doc, "Instance"));
    statement.setString(11, getMetadataValue(doc, "Created By"));
    statement.setString(12, getMetadataValue(doc, "Created On"));
    statement.setString(13, getMetadataValue(doc, "Description"));
    statement.execute();
    releaseConnection(connection);
    storeUserDefinedAttributes(doc);
    addDocumentChildren(doc);
  }

  private Collection getUserDefinedAttributes(Document doc) {
    Collection userAttributes = doc.getAttributes();
    Iterator i = userAttributes.iterator();
    KeyValue att = null;
    while (i.hasNext()){
      att = (KeyValue)i.next();
      if (att.getKey().equalsIgnoreCase("Folder")) i.remove();
      if (att.getKey().equalsIgnoreCase("Name")) i.remove();
      if (att.getKey().equalsIgnoreCase("Type")) i.remove();
      if (att.getKey().equalsIgnoreCase("Release")) i.remove();
      if (att.getKey().equalsIgnoreCase("Revision")) i.remove();
      if (att.getKey().equalsIgnoreCase("Version")) i.remove();
      if (att.getKey().equalsIgnoreCase("Branch")) i.remove();
      if (att.getKey().equalsIgnoreCase("Generic")) i.remove();
      if (att.getKey().equalsIgnoreCase("Instance")) i.remove();
      if (att.getKey().equalsIgnoreCase("Created By")) i.remove();
      if (att.getKey().equalsIgnoreCase("Created On")) i.remove();
      if (att.getKey().equalsIgnoreCase("Description")) i.remove();
    }
    return userAttributes;
  }

  private void storeUserDefinedAttributes(Document doc) throws Exception {
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement("INSERT into " + userAttributeTable + " VALUES(?,?,?)");
    Collection userAtts = getUserDefinedAttributes(doc);
    Iterator i = userAtts.iterator();
    String ID = doc.getOrigin();
    KeyValue att= null;
    while (i.hasNext()) {
      att = (KeyValue)i.next();
      if (att.getKey().equalsIgnoreCase("Origin")) continue;
      statement.setString(1, ID);
      statement.setString(2, att.getKey());
      statement.setString(3, (String) att.getValue());
      statement.execute();
    }
    releaseConnection(connection);
  }

  private String getMetadataValue(Document doc, String fieldName) {
    String value=null;
    value= doc.get(fieldName);
    if (null==value) value="";
    return value;
  }

  private void addDocumentChildren(Document doc) throws Exception {
    if (null==doc.getChildren()) return;
    Iterator i = doc.getChildren().iterator();
    while (i.hasNext()) addSubDocumentReference(doc.getOrigin(), (Reference)i.next());
  }

  private void addSubDocumentReference(String parentOrigin, Reference ref) throws Exception {
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement("INSERT INTO " + subComponentTable + " VALUES(?,?,?)");
    statement.setString(1,parentOrigin);
    ref.setOrigin(OriginUtil.updateDatasourceName(ref.getOrigin(),"_"));
    statement.setString(2, ref.getOrigin());
    statement.setInt(3,ref.getCount());
    statement.execute();
    releaseConnection(connection);
  }

/*
  private void addSubDocumentReference(String parentOrigin, Reference ref) throws Exception {
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement("INSERT INTO tbl-ILinkSubComponent VALUES(?,?,?,?)");
    statement.setString(1,parentOrigin);
    ref.setOrigin(OriginUtil.updateDatasourceName(ref.getOrigin(),"DesignState"));

    //Check to see if ref.getDocument is null, if so, then the component was not loaded
    String childName=OriginUtil.getIdentification(ref.getOrigin());
    String childType="#BadRef";
    if (null!=ref.getDocument()) {
      childName=ref.getDocument().getName();
      childType=ref.getDocument().get("Type");
    }
    statement.setString(2, childName);
    statement.setString(3, childType);
    statement.setInt(4,ref.getCount());
    statement.execute();
    Database.release(connection);
  }
*/

  private void removeDocumentChildren(Document doc) throws Exception {
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement("DELETE FROM " + subComponentTable + "  WHERE parentID=?");
    statement.setString(1,doc.getOrigin());
    statement.execute();
    releaseConnection(connection);
  }

  public void removeComponent(String name) throws Exception {
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement("DELETE FROM " + componentTable + "  WHERE ID like ?");
    statement.setString(1,"_:_:"+name+":%");
    statement.execute();
    releaseConnection(connection);
  }

  private void updateDocument(Document doc) throws Exception {
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement("UPDATE " + componentTable + " SET folder=?,  name=?, type=?, release=?, revision=?, version=?, branch=?, isGeneric=?, isInstance=?, createdBy=?, createdOn=?, description=? WHERE ID=?");
    statement.setString(1, getMetadataValue(doc, "Folder"));
    statement.setString(2, doc.getName());
    statement.setString(3, getMetadataValue(doc, "Type"));
    statement.setString(4, getMetadataValue(doc, "Release"));
    statement.setString(5, getMetadataValue(doc, "Revision"));
    statement.setString(6, getMetadataValue(doc, "Version"));
    statement.setString(7, getMetadataValue(doc, "Branch"));
    statement.setString(8, getMetadataValue(doc, "Generic"));
    statement.setString(9, getMetadataValue(doc, "Instance"));
    statement.setString(10, getMetadataValue(doc, "Created By"));
    statement.setString(11, getMetadataValue(doc, "Created On"));
    statement.setString(12, getMetadataValue(doc, "Description"));
    statement.setString(13, doc.getOrigin());
    statement.execute();
    releaseConnection(connection);
    storeUserDefinedAttributes(doc);
    removeDocumentChildren(doc);
    addDocumentChildren(doc);

  }

  //no need to use name for tejas implementation.
  //however future document entries may contain more than one binary, so each must be named.
  public void storeBinary(Document d, String path, String name, InputStream stream, int len) throws Exception{
    boolean update = false;
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement("Select componentID from " + pdfTable + " where componentID=?");
    statement.setString(1, d.getOrigin());
    ResultSet r = statement.executeQuery();
    if (r.next()) update=true;
    //connection.close();
    releaseConnection(connection);
    if (update) updateBinary(d, path, name, stream, len);
    else addBinary(d, path, name, stream,len);
  }

  public void addBinary(Document d, String path, String name, InputStream stream, int len) throws Exception{
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement("INSERT into " + pdfTable+ " VALUES(?,?)");
    statement.setString(1, d.getOrigin());
    statement.setBinaryStream(2, stream, len);
    statement.execute();
    //connection.close();
    releaseConnection(connection);
  }

  public void updateBinary(Document d, String path, String name, InputStream stream, int len) throws Exception{
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement("UPDATE " + pdfTable + " SET binary=? WHERE componentID=?");
    statement.setBinaryStream(1, stream, len);
    statement.setString(2, d.getOrigin());
    statement.execute();
//    connection.close();
    releaseConnection(connection);
  }

  public void writeBinary(String ID, OutputStream out) throws Exception{
    Connection connection = getConnection();
    PreparedStatement statement = connection.prepareStatement("Select binary from " + pdfTable + " WHERE componentID=?");
    statement.setString(1,ID);
    ResultSet r = statement.executeQuery();
    InputStream stream=null;
    if (r.next()){
      stream = r.getBinaryStream("binary");

      if (null!= stream) {
        int data = stream.read();
        while (data > -1) {
          out.write(data);
          data = stream.read();
        }
        out.close();
      }
    }
//    connection.close();
    releaseConnection(connection);
  }

  public String getConnectionURL() { return connectionURL; }
  public void setConnectionURL(String s) { connectionURL=s; }

  public String getUserAttributeTable() { return userAttributeTable; }
  public void setUserAttributeTable(String s) { userAttributeTable = s; }
  public String getComponentTable() { return componentTable; }
  public void setComponentTable(String s) { componentTable = s; }
  public String getSubComponentTable() { return subComponentTable; }
  public void setSubComponentTable (String s) { subComponentTable = s; }
  public String getPdfTable() { return pdfTable; }
  public void setPdfTable(String s) { pdfTable=s; }
  public String getUserDefinedAttributeTable() { return userDefinedAttributeTable; }
  public void setUserDefinedAttributeTable(String s) { userDefinedAttributeTable=s;}
  
  private static Class SQLServerDriver =null;
  private String connectionURL = "jdbc:microsoft:sqlserver://localhost:1433";
  private String userAttributeTable = "tblILinkAttribute_Stg";
  private String componentTable = "tblILinkComponent_Stg";
  private String subComponentTable = "tblILinkSubComponent_Stg";
  private String pdfTable = "tblILinkPDF_Stg";
  private String userDefinedAttributeTable = "tblILinkUserAttribute_Stg";
}
