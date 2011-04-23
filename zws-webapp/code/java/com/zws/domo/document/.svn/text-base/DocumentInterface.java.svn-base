package com.zws.domo.document;

import com.zws.functor.finder.Finder;

import java.util.Collection;

public interface DocumentInterface {
  public Document getDocument();
  public String getOrigin();

  public String get(String metadataField);
  public String getName();
  public String getRevision();
  public String getVersion();
  public Finder getFinder();

  public String getPath();
  public String toXML();
  public void setHistoryComparator(HistoryComparator c);
  public void addToHistory(DocumentInterface doc);
  public Collection getHistory();

  public void add(Reference r);
  public Collection getChildren();
  public void setChildren(Collection c);
}