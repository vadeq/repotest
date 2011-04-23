package com.zws.service.catalog;

import zws.application.Properties;
import zws.database.DB;
import zws.database.Database;
import zws.exception.*;

import com.zws.application.Config;
import com.zws.domo.catalog.*;
import com.zws.domo.document.Document;
//import com.zws.service.Database;
import com.zws.service.repository.RepositoryService;

import java.sql.*;
import java.util.*;

// A top level category with no parent is considered to be a catalog

public class CatalogService {
    /*
  private static String sqlDataSourceName = Properties.get(Properties.DesignStateDatabase);

  public CatalogService() {}

  public static Collection findCatalogs() throws SQLException {
    Catalog cat=null;
    Collection catalogs = new Vector();
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = connection.prepareStatement("SELECT * FROM category WHERE parentID=null");
    ResultSet resultSet = statement.executeQuery();
    while (resultSet.next()) {
      cat = unmarshallCatalog(resultSet);
      catalogs.add(cat);
    }
    Database.release(connection);
    return catalogs;
  }

  public static Catalog findCatalog(String name) throws SQLException {
    Catalog cat=null;
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = connection.prepareStatement("SELECT * FROM category WHERE name=? AND parentID=NULL");
    statement.setString(1,name);
    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) cat = unmarshallCatalog(resultSet);
    Database.release(connection);
    return cat;
  }

  public static Category find(String ID) throws SQLException {
    Category cat=null;
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = connection.prepareStatement("SELECT * FROM category WHERE ID=?");
    statement.setInt(1,Integer.valueOf(ID).intValue());
    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) cat = unmarshallCategory(resultSet);
    Database.release(connection);
    return cat;
  }

  public static Category find(String parentID, String categoryName) throws SQLException {
    Category cat=null;
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = connection.prepareStatement("SELECT * FROM category WHERE parentID=? AND name=?");
    statement.setInt(1,Integer.valueOf(parentID).intValue());
    statement.setString(2, categoryName);
    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) cat = unmarshallCategory(resultSet);
    Database.release(connection);
    return cat;
  }

  public static Category find(Path path) throws SQLException {
    Category cat=null;
    String categoryName=null;
    Connection connection = Database.connect(dataSourceName);
    Iterator i = path.getCategoryPath().iterator();
    cat = findCatalog(((Category)i.next()).getName());
    while (null!=cat && i.hasNext()){
      categoryName = (String) i.next();
      cat= find(cat.getID(), categoryName);
    }
    return cat;
  }

  private Category findSubCategory(Category parent, String subCategoryName) throws Exception {
    Category cat = null;
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = connection.prepareStatement("SELECT * FROM category WHERE parentID=? AND name=?");
    statement.setInt(1,Integer.valueOf(parent.getID()).intValue());
    statement.setString (2, subCategoryName);
    ResultSet resultSet = statement.executeQuery();
    if (resultSet.next()) cat = unmarshallCategory(resultSet);
    Database.release(connection);
    return cat;
  }

  public static Collection findSubCategories(Category category) throws SQLException {
    Category cat=null;
    Collection categories = new Vector();
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = connection.prepareStatement("SELECT * FROM category WHERE parentID=?");
    statement.setInt(1,Integer.valueOf(category.getID()).intValue());
    ResultSet resultSet = statement.executeQuery();
    while (resultSet.next()) {
      cat = unmarshallCategory(resultSet);
      categories.add(cat);
    }
    Database.release(connection);
    return categories;
  }

  public static Collection getDocuments(Category category) throws SQLException {
    Document doc=null;
    Collection documents = new Vector();
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = connection.prepareStatement("SELECT * FROM document left join category on categoryID=? and document.origin=category.documentOrigin");
    statement.setInt(1,Integer.valueOf(category.getID()).intValue());
    ResultSet resultSet = statement.executeQuery();
    while (resultSet.next()) {
      doc = RepositoryService.unmarshallDocument(resultSet);
      documents.add(doc);
    }
    Database.release(connection);
    return documents;
  }

  public static Collection getPath(Category category) throws SQLException {
    Collection paths = new Vector();
    Category cat = category;
    while (null!= cat.getParentID()){
      cat = find(cat.getParentID());
      paths.add(cat);
    }
    return paths;
  }

  public synchronized static void add(Category category) throws SQLException, Exception {
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = null;
    String parentID = null;
    Category parent = find(category.getParentID());
    if (null==parent) throw new Exception ("Category has no parent. Add it as a catalog instead");
    if (null!= find(category.getParentID(), category.getName())) return; //category already added
    statement = connection.prepareStatement("INSERT INTO category SET name=?, description=? parentID=?)");
    statement.setString(1,category.getName());
    statement.setString(2,category.getDescription());
    statement.setInt(3,Integer.valueOf(parent.getID()).intValue());
    statement.execute();
    Database.release(connection);
  }

  public synchronized static void add(Catalog cat) throws SQLException, Exception{
    if (null!= findCatalog(cat.getName())) return; //catalog already added
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = null;
    String parentID = null;
    statement = connection.prepareStatement("INSERT INTO category SET name=?, description=?)");
    statement.setString(1,cat.getName());
    statement.setString(2,cat.getDescription());
    statement.execute();
    Database.release(connection);
  }

  public synchronized static void add(Path path) throws Exception {
    if (null== path.getCatalogName()) throw new Exception ("Invalid Path: no catalog specified");
    Catalog c = new Catalog();
    c.setName(path.getCatalogName());
    add(c);
    String categoryName = null;
    Iterator i = path.getCategoryPath().iterator();
    Category cat = findCatalog(c.getName());
    while (null!= cat && i.hasNext()){
      cat.setName((String) i.next());
      cat.setParentID(cat.getID());
      cat.setID(null);
      add(cat);
      cat = find(cat.getParentID(), cat.getName()); //this populates the category's ID: for use as the next parent
    }
  }

  public synchronized static void categorize(Document doc, Category category) throws SQLException{
    Connection connection = Database.connect(dataSourceName);
    PreparedStatement statement = connection.prepareStatement("INSERT INTO categoryPoint VALUES(?,?)");
    statement.setInt(1,Integer.valueOf(category.getID()).intValue());
    statement.setString(2,doc.getOrigin());
    statement.execute();
    Database.release(connection);
  }

  public static Category unmarshallCategory(ResultSet r) throws SQLException {
    Category cat = new Category();
    int id = r.getInt("ID");
    cat.setName(r.getString("name"));
    cat.setDescription(r.getString("description"));
    cat.setParentID(""+r.getInt("parentID"));
    return cat;
  }

  public static Catalog unmarshallCatalog(ResultSet r) throws SQLException {
    Catalog cat = new Catalog();
    int id = r.getInt("ID");
    cat.setName(r.getString("name"));
    cat.setDescription(r.getString("description"));
    return cat;
  }

  private static String dataSourceName = Config.getProperty(Config.DATA_SOURCE_APPLICATION_DATABASE);
  */
}
