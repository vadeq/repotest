package com.zws.domo.document;

import com.zws.service.repository.RepositoryService;

public class Reference {
  public Reference() {}

  public String getOrigin() { return origin; }
  public void setOrigin(String s) { origin=s; }
  public int getCount() { return count; }
  public void setCount(int i) { count=i; }

  public Document getDocument() throws Exception {
      /*
    if (null==getOrigin()) throw new Exception("Reference to Document has NULL origin");
    if (1>getCount()) throw new Exception("Reference count for Document must be at least 1. origin="+getOrigin());
    try { return RepositoryService.findDocument(getOrigin()); }
    catch (Exception e) {
      throw new Exception("Document not found; origin="+getOrigin());
    }
    */
      return null;
  }

  public String toXML() {
    return "<ref count=\"" + getCount() + "\" origin=\"" + getOrigin() + "\"/>";
  }

  private String origin;
  private int count=1;
}
