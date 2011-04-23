package com.zws.util.comparator.metadata;

import com.zws.domo.document.DocumentInterface;

public class HistoryOrder {

  public boolean isLater() {
  	boolean result = false;
  	if(isEqualBranch()){
  		if(isEqualRevision())
  			if(isLaterVersion())
  				result = true;
  		else if(isLaterRevision())
  			result = true;;
  		
  	}else if(isLaterBranch())
  		result = true;
  	return result;
  }

  public boolean isEqual() { return isEqualRevision() && isEqualVersion(); }

  public boolean isEqualRevision() { return isEqual(getRevision(getDoc()), getRevision(getCompareTo())); }
  public boolean isEqualVersion() { return isEqual(getVersion(getDoc()), getVersion(getCompareTo())); }
  public boolean isEqualBranch() { return isEqual(getBranch(getDoc()), getBranch(getCompareTo())); }


  public boolean isLaterBranch() { return !isEarlierBranch() && !isEqualBranch(); }
  public boolean isEarlierBranch() {
	  String br = getBranch(getDoc());
	  String compareTo = getBranch(getCompareTo());
	  if (null==compareTo) return false;
	  if (br.equals(compareTo)) return false;
	  if (br.equals(noValue)) return true;
	  if (br.length() < compareTo.length()) return true;
	  if (br.length() > compareTo.length()) return false;
	  if (br.compareTo(compareTo) < 0 ) return true;
	  return false;
	}
  
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
  private String getBranch(DocumentInterface doc) { return getValue(doc, branchMetaData); }

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
  public String getBranchMetaData() { return branchMetaData; }
  public void setBranchMetaData(String s) { branchMetaData=s; }
  public String getNoValue() { return noValue; }
  public void setNoValue(String s) { noValue=s; }

  private DocumentInterface doc=null;
  private DocumentInterface compareTo=null;
//  private String metaData=null;
  private String versionMetaData=null;
  private String revisionMetaData=null;
  private String branchMetaData=null;
  private String noValue = "-";
}