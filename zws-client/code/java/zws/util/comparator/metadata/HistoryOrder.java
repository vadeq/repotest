package zws.util.comparator.metadata;/*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

//import com.zws.domo.document.DocumentInterface;

public class HistoryOrder {
/*
  public boolean isLater() {
    if(isLaterRevision()) return true;
    else if (isEqualRevision() && isLaterVersion()) return true;
    return false;
  }

  public boolean isEqual() { return isEqualRevision() && isEqualVersion(); }

  public boolean isEqualRevision() { return isEqual(getRevision(getDoc()), getRevision(getCompareTo())); }
  public boolean isEqualVersion() { return isEqual(getVersion(getDoc()), getVersion(getCompareTo())); }


  public boolean isLaterRevision() { return !isEarlierRevision() && !isEqualRevision(); }
  public boolean isEarlierRevision() {
    String rev = getRevision(getDoc());
    String compareTo = getRevision(getCompareTo());
    if (null==compareTo) return false;
    if (rev.equals(compareTo)) return false;
    if (rev.equals(noValue)) return true;
    if (rev.length() < compareTo.length()) return true;
    if (rev.length() > compareTo.length()) return false;
    if (rev.compareTo(compareTo) < 0 ) return true;
    return false;
  }

  public boolean isLaterVersion() { return !isEarlierVersion() && !isEqualVersion(); }
  public boolean isEarlierVersion() {
    String rev = getVersion(getDoc());
    String compareTo = getVersion(getCompareTo());
    if (null==rev || noValue.equals(rev)) rev="0";
    if (null==compareTo || noValue.equals(compareTo)) compareTo="0";
    if (rev.equals(compareTo)) return false;
    int r = Integer.valueOf(rev).intValue();
    int c = Integer.valueOf(compareTo).intValue();
    return r < c;
  }

  public boolean isEqual(String revVer, String compareTo) { //checks for an earlier rev or version (but not release level)
    if (null==revVer && null==compareTo) return true;
    if (revVer.equals(compareTo)) return true;
    return false;
  }
  private String getRevision(DocumentInterface doc) { return getValue(doc, revisionMetaData); }
  private String getVersion(DocumentInterface doc) { return getValue(doc, versionMetaData); }
  private String getValue(DocumentInterface doc, String metaData) {
    if (null==doc) return null;
    String val = doc.get(metaData);
    if (null==val) return noValue;
    return val.trim();
  }

  public DocumentInterface getDoc() { return doc; }
  public void setDoc(DocumentInterface d) { doc = d; }
  public DocumentInterface getCompareTo() { return compareTo; }
  public void setCompareTo(DocumentInterface d) { compareTo = d; }
//  public String getMetaData() { return metaData; }
//  public void setMetaData(String s) { metaData=s; }
  public String getRevisionMetaData() { return revisionMetaData; }
  public void setRevisionMetaData(String s) { revisionMetaData=s; }
  public String getVersionMetaData() { return versionMetaData; }
  public void setVersionMetaData(String s) { versionMetaData=s; }
  public String getNoValue() { return noValue; }
  public void setNoValue(String s) { noValue=s; }

  private DocumentInterface doc=null;
  private DocumentInterface compareTo=null;
//  private String metaData=null;
  private String versionMetaData=null;
  private String revisionMetaData=null;
  private String noValue = "-";

 */
 }