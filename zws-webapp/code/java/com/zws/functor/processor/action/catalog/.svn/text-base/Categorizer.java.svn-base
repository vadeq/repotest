package com.zws.functor.processor.action.catalog;

import com.zws.domo.catalog.Category;
import com.zws.functor.catalog.CatalogPathMaker;
import com.zws.functor.processor.action.Action;
import com.zws.service.catalog.CatalogService;


public class Categorizer extends Action {

  public void execute() throws Exception {
    if (null!= catalogNameMetadata) pathMaker.setCatalogName(getDocument().get(catalogNameMetadata));
    else pathMaker.setCatalogName(getDocument().get(catalogName));
    if (null!= categoryPathMetadata) pathMaker.setCategoryPath(getDocument().get(categoryPathMetadata));
    else pathMaker.setCategoryPath(getDocument().get(catalogName));
    pathMaker.execute();
    /* temporarily disabled
    CatalogService.add(pathMaker.getPath());
    Category cat = CatalogService.find(pathMaker.getPath());
    CatalogService.categorize(getDocument(), cat);
    */
  }

  public String getCatalogName(){ return catalogName; }
  public void setCatalogName(String s) { catalogName=s; }

  public String getCategoryPath() { return categoryPath; }
  public void setCategoryPath(String s) { categoryPath=s; }
  public String getCategoryPathMetadata() { return categoryPathMetadata; }
  public void setCategoryPathMetadata(String s) { categoryPathMetadata=s; }
  public CatalogPathMaker getPathMaker() { return pathMaker; }
  public void setPathMaker(CatalogPathMaker f) { pathMaker =f; }

  private String catalogName=null;
  private String catalogNameMetadata=null;
  private String categoryPath = null;
  private String categoryPathMetadata=null;
  private CatalogPathMaker pathMaker = null;
}
