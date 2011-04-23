package com.zws.datasource;

import com.zws.domo.document.Document;
import com.zws.service.repository.RepositoryService;
import com.zws.util.OriginUtil;

import java.io.InputStream;
import java.util.Collection;
//todo: remove dependency on RepositoryService - implement persistence here

public class SQLSource extends DataSource {
  public String getType() { return DATA_SOURCE_SQL_DATABASE; }

  public boolean contains(String origin) throws Exception { 
      //return RepositoryService.contains(localizeOrigin(origin));
      return false;
  }
  public boolean hasChildren(String origin) throws Exception { 
    //return contains(origin) && RepositoryService.hasChildren(origin);
    return false;
  }
  public void call(String procedureName) throws Exception { throw new Exception("Unsupported Operation"); }

  public Document find(String origin) throws Exception {
    //return RepositoryService.findDocument(OriginUtil.updateDatasourceName(origin,getName()));
      return null;
  }
  public static Collection findAll() throws Exception { 
      //return RepositoryService.findAll();
    return null;
  }
  
  public Collection findDuplicateNames() throws Exception {
    //return RepositoryService.findDuplicateNames();
      return null;
  }
  public void add(Document d) throws Exception{
      /*
    resetOrigin(d);
    RepositoryService.addDocument(d);
    */
      
  }
  public void update(Document d) throws Exception{
      /*
    resetOrigin(d);
    RepositoryService.updateDocument(d);
    */
  }
  public void delete(String origin) throws Exception {
    //RepositoryService.deleteDocument(origin);
    return;
  } //todo:
  public void storeBinary(Document d, String path, String name, InputStream stream, int len) throws Exception { } //+++todo:

  public String getDatabaseName() { return databaseName; }
  public void setDatabaseName(String s ) { databaseName=s; }
  private String databaseName=null;
}
