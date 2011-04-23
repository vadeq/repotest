package com.zws.functor.report.filter;

import com.zws.domo.document.Document;
import com.zws.functor.filter.DocumentCollectiveFilter;
import com.zws.util.comparator.metadata.HistoryOrder;

import java.util.*;

public class Latest_old extends DocumentCollectiveFilter {

  public void initialize() {
    comparator.setRevisionMetaData(getRevisionMetaData());
    comparator.setVersionMetaData(getVersionMetaData());
  }

  public Collection prepareData(Collection dataList) {
    Document doc;
    Iterator i = dataList.iterator();
    while(i.hasNext()) {
      doc = (Document)i.next();
      getDocSet(doc.get(getNameMetaData())).add(doc);
    }
    return documentMap.values();
  }

  public Collection getDocSet(String name) {
    Collection c = (Collection)documentMap.get(name);
    if (null==c) {
      c = new Vector();
      documentMap.put(name, c);
    }
    return c;
  }

  public Collection filterData(Collection docSets) {
    Collection docSet;
    Collection latestDocuments = new Vector();
    Iterator i = docSets.iterator();
    while (i.hasNext()) {
      docSet = (Collection) i.next();
      latestDocuments.add(findLatest(docSet));
    }
    return latestDocuments;
  }

  private Document findLatest(Collection docSet) {
    Iterator i = docSet.iterator();
    Document doc=null;
    Document latestDoc = null;
    while (i.hasNext()){
      doc = (Document) i.next();
      if (null==latestDoc)
        latestDoc = doc;
      comparator.setDoc(doc);
      comparator.setCompareTo(latestDoc);
      if(comparator.isLater()) latestDoc = doc;
    }
    return latestDoc;
  }

  public String getNameMetaData() { return nameMetaData; }
  public void setNameMetaData(String s) { nameMetaData =s ; }
  public String getRevisionMetaData() { return revisionMetaData; }
  public void setRevisionMetaData(String s) { revisionMetaData=s; }
  public String getVersionMetaData() { return versionMetaData; }
  public void setVersionMetaData(String s) { versionMetaData=s; }
  public String getNoValue() { return noValue; }
  public void setNoValue(String s) { noValue=s; }

  private String nameMetaData=null;
  private String versionMetaData=null;
  private String revisionMetaData=null;
  private String noValue = "-";

  private Map documentMap = new HashMap();
  private HistoryOrder comparator = new HistoryOrder();
}
