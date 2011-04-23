package com.zws.functor.report.filter;

import com.zws.domo.document.DocumentInterface;
import com.zws.domo.document.HistoryComparator;
import com.zws.functor.filter.DocumentCollectiveFilter;

import java.util.*;

public class Latest extends DocumentCollectiveFilter {

  public void initialize() { documentMap.clear(); }

  public Collection prepareData(Collection dataList) {
    DocumentInterface doc=null;
    Iterator i = dataList.iterator();
    while(i.hasNext()) {
      doc = (DocumentInterface)i.next();
      doc.setHistoryComparator(getComparator());
      getDocSet(doc.get(getNameMetadata())).add(doc);
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
    DocumentInterface doc;
    Collection docSet;
    Collection latestDocuments = new Vector();
    Iterator i = docSets.iterator();
    while (i.hasNext()) {
      docSet = (Collection) i.next();
      doc = findLatest(docSet);
      latestDocuments.add(doc);
      docSet.remove(doc);
      if (getCreateHistory()) constructHistory(doc, docSet);
    }
    return latestDocuments;
  }

  private void constructHistory(DocumentInterface doc, Collection docSet) {
    DocumentInterface d;
    Iterator h = docSet.iterator();
    while (h.hasNext()) {
      d = (DocumentInterface)h.next();
      if (d!=doc) doc.addToHistory(d.getDocument());
    }
  }

  private DocumentInterface findLatest(Collection docSet) {
    Iterator i = docSet.iterator();
    DocumentInterface doc=null;
    DocumentInterface latestDoc=null;
    while (i.hasNext()){
      doc = (DocumentInterface)i.next();
      if (null==latestDoc) latestDoc = doc;
      if(0<getComparator().compare(doc, latestDoc)) latestDoc = doc;
    }
    {} //System.out.println ("latest : " + latestDoc.get("Branch") + " " + latestDoc.getRevision() + " " +    					latestDoc.getVersion());
    
    return latestDoc;
  }

  public String getNameMetadata() { return comparator.getNameMetadata(); }
  public void setNameMetadata(String s) { comparator.setNameMetadata(s) ; }
  public String getRevisionMetadata() { return comparator.getRevisionMetadata(); }
  public void setRevisionMetadata(String s) { comparator.setRevisionMetadata(s); }
  public String getVersionMetadata() { return comparator.getVersionMetadata(); }
  public void setVersionMetadata(String s) { comparator.setVersionMetadata(s); }
  public String getBranchMetadata() { return comparator.getBranchMetadata(); }
  public void setBranchMetadata(String s) { comparator.setBranchMetadata(s); }
  public String getNoValue() { return comparator.getNoValue(); }
  public void setNoValue(String s) { comparator.setNoValue(s); }

  public boolean getCreateHistory() { return createHistory; }
  public void setCreateHistory(boolean b) {createHistory=b; }

  public HistoryComparator getComparator() { return comparator; }
  public void setComparator(HistoryComparator c) { comparator=c; }

  private String nameMetadata="name";
  private String versionMetadata=null;
  private String revisionMetadata=null;
  private String branchMetadata=null;
  private String noValue = "-";

  private boolean createHistory = false;

  private Map documentMap = new HashMap();
  private HistoryComparator comparator = new HistoryComparator();
}
