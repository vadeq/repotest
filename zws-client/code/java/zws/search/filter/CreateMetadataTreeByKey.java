package zws.search.filter;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 1, 2004, 11:03 AM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.data.MetadataBase;
import zws.search.sortcomparator.SortMetadataByKey;

import java.util.*;

//assumes that the collection is sorted by keyFields
//creates a tree of metadata for each unique keyField
//tree is sorted by categoryFields
//+++todo: modify this to use Storable (define Storable.replaceStorage(Collection c); }
public abstract class CreateMetadataTreeByKey extends KeyFilter  {

  protected abstract void setTree(MetadataBase data, TreeSet tree);

  public boolean getSortInReverse() { return sortInReverse; }
  public void setSortInReverse(boolean b) { sortInReverse=b; }
  public boolean getCategorizeInReverse() { return categorizeInReverse; }
  public void setCategorizeInReverse(boolean b) { categorizeInReverse=b; }

  public void filter(Collection list) throws Exception {
    initialize();
    SortMetadataByKey sortComparator = new SortMetadataByKey();
    sortComparator.setKeyFields(getKeyFields());
    sortComparator.setAscendingOrder(!sortInReverse);
    resetStorage();
    initializeStorage(new TreeSet(sortComparator));
    
    String key, nextKey;
    MetadataBase data=null,nextData=null;
    TreeSet tree;
    SortMetadataByKey categoryComparator = new SortMetadataByKey();
    categoryComparator.setKeyFields(getCategoryFields());
    categoryComparator.setAscendingOrder(categorizeInReverse);
    Iterator i;
    i = list.iterator();
    if (i.hasNext()) data=(MetadataBase) i.next();
    while (i.hasNext()){
      store(data);
      tree = new TreeSet(categoryComparator);
      setTree(data, tree);
      key = makeKey(data);
      nextKey=key;
      nextData=null;
      while(i.hasNext() && key.equals(nextKey)) {
        nextData = (MetadataBase) i.next();
        nextKey = makeKey(nextData);
        if (key.equals(nextKey)) tree.add(nextData);
      }
      //check if last item in list is different category
      if (!i.hasNext() && !key.equals(nextKey)) store(nextData);  
      data=nextData;
    }
    list.clear();
    list.addAll(getResults());
  }

  public String getCategoryFields() { return categoryFields; }
  public void setCategoryFields(String s) { categoryFields=s; }

  private String categoryFields=null;
  private boolean sortInReverse=false;
  private boolean categorizeInReverse=false;
}
