package com.zws.datasource;

import com.zws.application.Constants;
import com.zws.application.Properties;
import com.zws.domo.document.Document;
import com.zws.domo.document.Reference;
import com.zws.util.KeyValue;
import com.zws.util.OriginUtil;

import java.io.InputStream;
import java.util.*;


public abstract class DataSource implements Cloneable {

  public abstract boolean contains(String origin) throws Exception;
  public abstract boolean hasChildren(String origin) throws Exception;
  public abstract Document find(String origin) throws Exception;
  public void store(Document d) throws Exception {
    if (contains(d.getOrigin())) update(d);
    else add(d);
  }
  public abstract void storeBinary(Document d, String path, String name, InputStream stream, int len) throws Exception;
  public abstract void add(Document d) throws Exception; //todo:
  public abstract void update(Document d) throws Exception; //todo:
  public abstract void delete(String origin) throws Exception; //todo:



  protected Document resetOrigin(Document doc) {
    doc.setOrigin(OriginUtil.updateSources(doc.getOrigin(),Properties.get(Constants.SERVICE_NAME), getName()));
    if (null==doc.getChildren()) return doc;
    Reference ref = null;
    Iterator i = doc.getChildren().iterator();
    while (i.hasNext()){
      ref = (Reference)i.next();
      ref.setOrigin(OriginUtil.updateSources(ref.getOrigin(), Properties.get(Constants.SERVICE_NAME), getName()));
    }
    return doc;
  }

  protected String localizeOrigin(String origin) {
    return OriginUtil.updateSources(origin, Properties.get(Constants.SERVICE_NAME), getName());
  }

  public DataSource copy(){
    try {return (DataSource) clone(); } //cloaning should be supported
    catch (CloneNotSupportedException e){throw new RuntimeException(e.getMessage()); }
  }
  public Object clone() throws CloneNotSupportedException
  { return super.clone(); }

  public String getName() { return name; }
  public void setName(String s) { name=s; }
  public String getUsername() { return username; }
  public void setUsername(String s) { username=s; }
  public String getPassword() { return password; }
  public void setPassword(String s) { password=s; }
  public abstract String getType();
  //schema methods
  public void addSchemaAttribute(KeyValue attribute){
  	schema.put(attribute.getKey(), attribute);
  }
  public Map getSchema(){ return schema; }

  private String name, username, password;
  private HashMap schema = new HashMap();

  public static String DATA_SOURCE_FILE_SYSTEM = "File System";
  public static String DATA_SOURCE_INTRALINK = "Pro/Intralink";
  public static String DATA_SOURCE_SQL_DATABASE = "SQL Database";
  public static String DATA_SOURCE_SQL_SERVER_DATABASE = "SQL Server Database";
}
