package com.zws.service.config;

import com.zws.datasource.DataSource;
import com.zws.datasource.SQLSource;

import java.util.*;

public class DataSourceService {
  private static String RESOURCE_TYPE = "Data Source";
  private static DataSourceService service = null;

//todo: make constructor private when xml processor is finished
//  private DataSourceService(){};
  public static DataSourceService getInstance() {
    if (null==service) service = new DataSourceService();
    return service;
  }

  public static Collection getDataSourceNames(){ return dataSourcePrototypes.keySet(); }

  public static DataSource find(String name) throws ConfigurationNotFound {
    DataSource dataSource = (DataSource)dataSourcePrototypes.get(name);
    if (null==dataSource) throw new ConfigurationNotFound(RESOURCE_TYPE, name);
    return (DataSource) dataSource.copy();
  }

  public static Collection findAll() {
    Collection c = new Vector();
    Iterator i = dataSourcePrototypes.values().iterator();
    while (i.hasNext()) c.add(((DataSource)i.next()).copy());
    return c;
  }

  public static void add(SQLSource dataSource) throws DuplicateConfigurationName{ add((DataSource)dataSource); }

  public static void add(DataSource dataSource) throws DuplicateConfigurationName{
    if (null==dataSourcePrototypes.get(dataSource.getName()))
      dataSourcePrototypes.put(dataSource.getName(), dataSource);
    else throw new DuplicateConfigurationName(RESOURCE_TYPE, dataSource.getName());
  }

  public static DataSource remove(DataSource dataSource) { return remove(dataSource.getName()); }

  public static DataSource remove(String dataSourceName) { return (DataSource) dataSourcePrototypes.remove(dataSourceName); }

  public static void update(DataSource dataSource) {
    dataSourcePrototypes.remove(dataSource.getName());
    dataSourcePrototypes.put(dataSource.getName(), dataSource);
  }

  public static void unload() { dataSourcePrototypes.clear(); }

  private static Map dataSourcePrototypes = new HashMap();
}
