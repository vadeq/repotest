package zws.datasource; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.exception.NameNotFound;
import zws.exception.UnsupportedOperation;
import zws.search.SearchAgent;

public interface DatasourceSearchAgent extends SearchAgent {
  public void setDatasource(String name) throws NameNotFound;

  public boolean getIsOriginalSource();
  public void setIsOriginalSource(boolean b);

  public void enableCache() throws UnsupportedOperation;
  public void disableCache()throws UnsupportedOperation;
}