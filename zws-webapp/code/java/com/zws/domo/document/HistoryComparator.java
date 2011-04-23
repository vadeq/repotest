package com.zws.domo.document;

import com.zws.util.comparator.metadata.HistoryOrder;

import java.util.Comparator;

public class HistoryComparator implements Comparator {

  public int compare (Object o1, Object o2) { return compare ((DocumentInterface)o1, (DocumentInterface)o2); }

  public int compare (DocumentInterface d1, DocumentInterface d2) {
    HistoryOrder docCheck = new HistoryOrder();
    if (!isEqualMetadata(d1, d2, nameMetadata, true)) throw new RuntimeException("Can not compare document history for documents with different names: " + d1.get(nameMetadata) + "<>"+d2.get(nameMetadata));
    docCheck.setDoc(d1);
    docCheck.setCompareTo(d2);
    docCheck.setRevisionMetaData(getRevisionMetadata());
    docCheck.setVersionMetaData(getVersionMetadata());
	docCheck.setBranchMetaData(getBranchMetadata());
    docCheck.setNoValue(getNoValue());
    if (docCheck.isLater()) return -1;
    if (docCheck.isEqual()) return 0;
    return 1;
  }

  private boolean isEqualMetadata(DocumentInterface d1, DocumentInterface d2, String metadata, boolean ignoreCase) {
    String s1 = d1.get(metadata);
    String s2 = d2.get(metadata);
    if (null==s1)s1=""; if (null==s2) s2="";
    if (ignoreCase) return s1.equalsIgnoreCase(s2);
    else return s1.equals(s2);
  }

  public String getNameMetadata() { return nameMetadata; }
  public void setNameMetadata(String s) { nameMetadata=s; }
  public String getRevisionMetadata() { return revisionMetaData; }
  public void setRevisionMetadata(String s) { revisionMetaData=s; }
  public String getVersionMetadata() { return versionMetaData; }
  public void setVersionMetadata(String s) { versionMetaData=s; }
  public String getBranchMetadata() { return branchMetaData; }
  public void setBranchMetadata(String s) { branchMetaData=s; }
  public String getNoValue() { return noValue; }
  public void setNoValue(String s) { noValue=s; }

  private String nameMetadata="name";
  private String versionMetaData=null;
  private String revisionMetaData=null;
  private String branchMetaData=null;
  private String noValue="-";
}
