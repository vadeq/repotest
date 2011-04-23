package com.zws.datasource;

import com.zws.domo.document.Document;

import java.io.InputStream;

public class FileSystemSource extends DataSource {
  public String getType() { return DATA_SOURCE_FILE_SYSTEM; }
//  public Document find(Document d)throws Exception {return null;} //todo:
  public boolean contains(String origin)throws Exception {return false;} //todo:
  public boolean hasChildren(String origin) { return false; }
  public Document find(String origin)throws Exception {return null;} //todo:
  public void add(Document d) throws Exception{return;} //todo:
  public void update(Document d) throws Exception{return;} //todo:
  public void storeBinary(Document d, String path, String name, InputStream stream, int len) throws Exception { }

//  public void delete(Document d) throws Exception{return;} //todo:
  public void delete(String origin) throws Exception{return;} //todo:
}
