package com.zws.functor.catalog;

import com.zws.domo.catalog.Path;
import com.zws.functor.Functor;

public class CatalogPathMaker extends Functor {

  public void execute() throws Exception {
    Path p = new Path();
    p.setCatalogName(getCatalogName());
    p.add(categoryPath);
    setPath(p);
  }

  public String getCatalogName() { return catalogName; }
  public void setCatalogName(String s) { catalogName = s; }
  public String getCategoryPath() { return categoryPath; }
  public void setCategoryPath(String s) { categoryPath=s; }
  public Path getPath() { return path; }
  public void setPath(Path p) { path=p; }

  public void setResult(Object o) { setPath((Path)o); }
  public Object getResult() { return getPath(); }

  private String catalogName = null;
  private String categoryPath = null;
  private Path path=null;
}
